package com.example.therapistbluelock;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ReportFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import es.dmoral.toasty.Toasty;
import yalantis.com.sidemenu.interfaces.ScreenShotable;

public class HomeFragment extends Fragment implements ScreenShotable, PatientsAssignedAdapter.OnReportIconClickListener {

    private RecyclerView verticalRecyclerView;
    private RecyclerView newPatientRecyclerView;
    private PatientsAssignedAdapter patientsAdapter;
    List<PatientsAssigned> patientsAssigned = new ArrayList<>();
    private NewPatientAdapter newPatientAdapter;
    private ImageView kneeImage;
    LinearLayout viewAllPatient;
    TextView oldpat, newpat, totalpat,user_name,profilename;

    List<String> patientname = new ArrayList<>();
    AutoCompleteTextView auto_complete;
    ArrayAdapter<String> adapter;

    public static String uname, userid,therapistid,therapistname,patientnam;
    public static String patusername,patpassword;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

   ImageView downloadbtn;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // Handle back button logic
                // Example: Navigate back or show a confirmation dialog
                requireActivity().getSupportFragmentManager().popBackStack();
            }
        };

        // Attach the callback to the activity's OnBackPressedDispatcher
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);

        MainActivity.detailfragflag=0;

        // Initialize the vertical RecyclerView
        verticalRecyclerView = view.findViewById(R.id.patients_assigned);
        verticalRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        downloadbtn = view.findViewById(R.id.downloadbtn);

        patientsAdapter = new PatientsAssignedAdapter(this, patientsAssigned);
        verticalRecyclerView.setAdapter(patientsAdapter);
        oldpat = view.findViewById(R.id.oldpat);
        newpat = view.findViewById(R.id.newpat);
        totalpat = view.findViewById(R.id.totalpat);
        auto_complete = view.findViewById(R.id.auto_complete);
        adapter = new ArrayAdapter<>(requireContext(), R.layout.drop_list, patientname);
        auto_complete.setAdapter(adapter);
        loadData();
        int space = getResources().getDimensionPixelSize(R.dimen.recycler_item_space); // Define space in dimens.xml
        verticalRecyclerView.addItemDecoration(new ItemSpacingDecoration(space));
        // Load data for vertical RecyclerView

        user_name = view.findViewById(R.id.user_name);
        user_name.setText(MainActivity.therapistname);
        profilename = view.findViewById(R.id.profilename);
        profilename.setText(MainActivity.therapistname);

        auto_complete.setOnItemClickListener((parent, view1, position, id) -> {
            String selectedpatient = (String) parent.getItemAtPosition(position);

            for(int i=0; i<MainActivity.patientdata.length(); i++){
                try {
                    JSONObject jsonObject = MainActivity.patientdata.getJSONObject(i);
                    if(jsonObject.getInt("flag")>=0){
                        if(selectedpatient.equalsIgnoreCase(jsonObject.getString("patient_name"))){
                            loadData1(jsonObject.getString("patient_id"));
                            Fragment reportFragment = new OverviewFragment(); // Replace with your actual fragment class
                            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                            transaction.replace(R.id.content_frame, reportFragment); // Replace with the appropriate container ID
                            transaction.addToBackStack(null); // Add to back stack if you want to return
                            transaction.commit();
                        }

                    }
                    else if(jsonObject.getInt("flag") <= -2){
                        if (selectedpatient.equalsIgnoreCase(jsonObject.getString("user_id"))) {
                            Log.e("Lunar Eclipse", position + " / " + String.valueOf(jsonObject.getInt("flag")));
                            uname = jsonObject.getString("user_id");
                            patusername = uname;
                            userid = jsonObject.getString("patient_id");
                            Intent intent = new Intent(getContext(), CollectionDetails.class);
                            startActivity(intent);
                        }

                    }
                    else if(jsonObject.getInt("flag") == -1){
                        if (selectedpatient.equalsIgnoreCase(jsonObject.getString("patient_name"))) {
                            JSONObject jsonObject1 = jsonObject.getJSONObject("PersonalDetails");
                            MainActivity.patientheight = jsonObject1.getInt("Height");
                            uname = jsonObject.getString("user_id");
                            userid = jsonObject.getString("patient_id");
                            patientnam = jsonObject.getString("patient_name");
                            Intent intent = new Intent(getContext(), BluetoothConnection.class);
                            startActivity(intent);
                        }

                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        // Initialize the horizontal RecyclerView
        newPatientRecyclerView = view.findViewById(R.id.new_patient_recycle);
        newPatientRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        newPatientAdapter = new NewPatientAdapter();
        newPatientRecyclerView.setAdapter(newPatientAdapter);
        int spacing = getResources().getDimensionPixelSize(R.dimen.recycler_horizontal_item_space); // Adjust the spacing value as needed
        newPatientRecyclerView.addItemDecoration(new ItemSpacingDecoration(spacing));
        loadNewPatientData();
        kneeImage = view.findViewById(R.id.knee_image);
        startGlitchAnimation();

        viewAllPatient = view.findViewById(R.id.view_all_patient);
        viewAllPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the new fragment
                Fragment viewAllFragment = new PatientListFragment(); // Replace with your actual fragment class
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.content_frame, viewAllFragment); // Replace with the appropriate container ID
                transaction.addToBackStack(null); // Add to the back stack for navigation
                transaction.commit();
            }
        });

        downloadbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.patientdata.length()>0) {
                    saveToCSV();
                }
                else{
                    Toasty.warning(getContext(),"Data Loading On Progress...",Toasty.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    private void startGlitchAnimation() {
        // Floating up and down animation (translationY)
        ObjectAnimator floatUpAndDown = ObjectAnimator.ofFloat(kneeImage, "translationY", -10f, 10f); // Float up and down
        floatUpAndDown.setDuration(1000);  // Duration for the floating animation
        floatUpAndDown.setRepeatCount(ValueAnimator.INFINITE);  // Repeat infinitely
        floatUpAndDown.setRepeatMode(ValueAnimator.REVERSE);  // Smooth reverse effect
        floatUpAndDown.start();  // Start floating animation immediately

        // Flicker animation (alpha) that triggers for 1 second
        ObjectAnimator flicker = ObjectAnimator.ofFloat(kneeImage, "alpha", 1f, 0.7f, 1f); // Flicker effect
        flicker.setDuration(1000);  // Duration for the flicker effect (1 second)
        flicker.setRepeatCount(0);  // No repeat, runs once per trigger

        // Create a handler to trigger the flicker animation every 3 seconds
        Handler handler = new Handler();
        Runnable flickerRunnable = new Runnable() {
            @Override
            public void run() {
                flicker.start();  // Start the flicker animation
                handler.postDelayed(this, 3000);  // Schedule next trigger after 3 seconds
            }
        };

        // Start the first flicker after a short initial delay (optional)
        handler.postDelayed(flickerRunnable, 3000);  // Start after 3 seconds
    }

    @Override
    public void onReportIconClick(String pid) {
        // Handle navigation to a new fragment when the report icon is clicked

        loadData1(pid);
        Fragment reportFragment = new OverviewFragment(); // Replace with your actual fragment class
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, reportFragment); // Replace with the appropriate container ID
        transaction.addToBackStack(null); // Add to back stack if you want to return
        transaction.commit();
    }

    private void loadData() {
        // Example data for vertical RecyclerView
        RequestQueue queue = Volley.newRequestQueue(getContext());
        MainActivity.newpatients = 0;
        MainActivity.oldpatients = 0;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://api-wo6.onrender.com/patient-details/all",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.e("Pat", response);
                            MainActivity.completedata = new JSONArray(response);
                            MainActivity.patientdata = new JSONArray();
                            for (int i = 0; i < MainActivity.completedata.length(); i++) {
                                try {
                                    JSONObject jsonObject = MainActivity.completedata.getJSONObject(i);
                                    String therapistid = jsonObject.getString("therapist_id");
                                    String therapistassigned = jsonObject.getString("therapist_assigned");
                                    if (therapistid.equalsIgnoreCase(MainActivity.therapistuserid) && therapistassigned.equalsIgnoreCase(MainActivity.therapistusername)) {
                                        MainActivity.patientdata.put(jsonObject);
                                    }

                                } catch (JSONException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                            for(int i=0; i< MainActivity.patientdata.length(); i++){
                                JSONObject jsonObject = MainActivity.patientdata.getJSONObject(i);

                                if (jsonObject.getInt("flag") >= 0) {
                                    patientname.add(jsonObject.getString("patient_name"));
                                    MainActivity.oldpatients++;
                                    JSONObject jsonObject1 = jsonObject.getJSONObject("PersonalDetails");
                                    JSONArray jsonArray = jsonObject1.getJSONArray("pain_indication");
                                    StringBuilder stringBuilder = new StringBuilder();


                                    for (int j = 0; j < jsonArray.length(); j++) {
                                        stringBuilder.append(jsonArray.getString(j));
                                        stringBuilder.append(" "); // Custom delimiter
                                    }
                                    String pain = stringBuilder.toString();
//                String pain="Ankle Pain";
                                    patientsAssigned.add(new PatientsAssigned(jsonObject.getString("patient_name"), pain, jsonObject.getString("patient_id"), R.drawable.user_image, Integer.parseInt(jsonObject1.getString("Age")), jsonObject1.getString("Gender"), jsonObject.getString("user_id")));
                                }else if(jsonObject.getInt("flag") == -1){
                                    patientname.add(jsonObject.getString("patient_name"));
                                    MainActivity.newpatients++;
                                }
                                else {
                                    patientname.add(jsonObject.getString("user_id"));
                                    MainActivity.newpatients++;
                                }
                            }

                            adapter.notifyDataSetChanged();

                            oldpat.setText(String.valueOf(MainActivity.oldpatients));
                            newpat.setText(String.valueOf(MainActivity.newpatients));
                            totalpat.setText(String.valueOf(MainActivity.oldpatients + MainActivity.newpatients));
                            patientsAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                Log.e("Loading Data error",error.getMessage());
                Toast.makeText(getContext(), "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        // Set a custom retry policy (optional) to better handle slow responses
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000, // Timeout in ms (10 seconds)
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        ));

        queue.add(stringRequest);
    }

    private void loadData1(String pid) {
        for (int i = 0; i < MainActivity.patientdata.length(); i++) {
            try {
                JSONObject jsonObject = MainActivity.patientdata.getJSONObject(i);
                if (pid.equalsIgnoreCase(jsonObject.getString("patient_id"))) {
                    MainActivity.selectedpatientdata = MainActivity.patientdata.getJSONObject(i);
                }
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void loadNewPatientData() {
        newPatientAdapter.addPatient(new NewPatient("Sam Wilson", "Arm Injury", R.drawable.user_image));
        newPatientAdapter.addPatient(new NewPatient("Lucy Gray", "Wrist Pain", R.drawable.user_image));
        newPatientAdapter.addPatient(new NewPatient("Paul Green", "Hip Pain", R.drawable.user_image));
        newPatientAdapter.addPatient(new NewPatient("Sam Wilson", "Arm Injury", R.drawable.user_image));
        newPatientAdapter.addPatient(new NewPatient("Lucy Gray", "Wrist Pain", R.drawable.user_image));
        newPatientAdapter.addPatient(new NewPatient("Paul Green", "Hip Pain", R.drawable.user_image));
    }

    @Override
    public void takeScreenShot() {
        // Implement screenshot logic if needed
    }

    @Override
    public Bitmap getBitmap() {
        return null;
    }

    private void saveToCSV() {
//        if (dataBuffer.isEmpty()) {
//            Toast.makeText(this, "No data to save!", Toast.LENGTH_SHORT).show();
//            return;
//        }

        String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss", Locale.getDefault()).format(new Date());
        String fileName = timeStamp+"_assessment_results_summary" + ".csv";
        String folderName = "KneeonPatientData";




        List<Csvdatamodel> mobilitytest = new ArrayList<>();
        List<Csvdatamodel> extensionlagtest = new ArrayList<>();
        List<Csvdatamodel> proprioceptiontest = new ArrayList<>();
        List<Csvdatamodel> staticbalancetest = new ArrayList<>();
        List<Csvdatamodel> dynamicbalancetest = new ArrayList<>();
        List<Csvdatamodel> staircaseclimbingtest = new ArrayList<>();
        List<Csvdatamodel> walkgaittest = new ArrayList<>();

        List<String> assessmentresult = new ArrayList<>();


        try {
            for (int i = 0; i < MainActivity.patientdata.length(); i++) {
                JSONObject jsonObject = MainActivity.patientdata.getJSONObject(i);


                if (jsonObject.getInt("flag") >= 0) {

                    patientname.add(jsonObject.getString("patient_name"));
                    MainActivity.oldpatients++;
                    JSONObject jsonObject1 = jsonObject.getJSONObject("PersonalDetails");
                    JSONArray jsonArray = jsonObject.getJSONArray("Assessment");
                    JSONObject jsonObject2 = new JSONObject();
                    for(int j=0; j<jsonArray.length(); j++) {
                        jsonObject2 = jsonArray.getJSONObject(j);
                        JSONObject jsonObject3 = jsonObject2.getJSONObject("exercises");
                        Iterator<String> keys1 = jsonObject3.keys();
                        while(keys1.hasNext()){
                            String testname = keys1.next();
                            JSONObject jsonObject4 = new JSONObject();

                            if("Mobility Test".equalsIgnoreCase(testname)){
                                jsonObject4 = jsonObject3.getJSONObject(testname);
                                Iterator<String> keys2 = jsonObject4.keys();
                                while(keys2.hasNext()){
                                    String modename= keys2.next();
                                    JSONArray jsonArray1 = new JSONArray();
                                    jsonArray1=jsonObject4.getJSONArray(modename);
                                    JSONArray jsonArray2 = new JSONArray();
                                    jsonArray2 = jsonArray1.getJSONArray(1);
                                    List<String> assessmentValues = new ArrayList<>();
                                    assessmentValues.add("Min Extension: " + jsonArray2.getInt(0));
                                    assessmentValues.add("Max Flexion: " + jsonArray2.getInt(1));

                                    mobilitytest.add(new Csvdatamodel(jsonObject.getString("patient_name"), jsonObject1.getInt("Age"), modename, assessmentValues));

                                }
                            }
                            else if("Extension Lag Test".equalsIgnoreCase(testname)){
                                jsonObject4 = jsonObject3.getJSONObject(testname);
                                Iterator<String> keys2 = jsonObject4.keys();
                                while(keys2.hasNext()){
                                    String modename= keys2.next();
                                    JSONArray jsonArray1 = new JSONArray();
                                    jsonArray1=jsonObject4.getJSONArray(modename);
                                    JSONArray jsonArray2 = new JSONArray();
                                    jsonArray2 = jsonArray1.getJSONArray(1);
                                    List<String> assessmentValues = new ArrayList<>();
                                    assessmentValues.add("ActiveED: " + jsonArray2.getInt(0));
                                    assessmentValues.add("PassiveED: " + jsonArray2.getInt(1));
                                    assessmentValues.add("TotalED: " + jsonArray2.getInt(2));

                                    extensionlagtest.add(new Csvdatamodel(jsonObject.getString("patient_name"), jsonObject1.getInt("Age"), modename, assessmentValues));

                                }
                            }
                            else if("Proprioception Test".equalsIgnoreCase(testname)){
                                jsonObject4 = jsonObject3.getJSONObject(testname);
                                Iterator<String> keys2 = jsonObject4.keys();
                                while(keys2.hasNext()){
                                    String modename= keys2.next();
                                    JSONArray jsonArray1 = new JSONArray();
                                    jsonArray1=jsonObject4.getJSONArray(modename);
                                    JSONArray jsonArray2 = new JSONArray();
                                    jsonArray2 = jsonArray1.getJSONArray(2);
                                    List<String> assessmentValues = new ArrayList<>();
                                    assessmentValues.add("Proprioception Anlge: " + jsonArray2.getInt(1));
                                    assessmentValues.add("Error Angle: " + jsonArray2.getInt(2));

                                    proprioceptiontest.add(new Csvdatamodel(jsonObject.getString("patient_name"), jsonObject1.getInt("Age"), modename, assessmentValues));

                                }
                            }
                            else if("Static Balance Test".equalsIgnoreCase(testname)){
                                jsonObject4 = jsonObject3.getJSONObject(testname);
                                Iterator<String> keys2 = jsonObject4.keys();
                                while(keys2.hasNext()){
                                    String modename= keys2.next();
                                    JSONArray jsonArray1 = new JSONArray();
                                    jsonArray1=jsonObject4.getJSONArray(modename);
                                    JSONArray jsonArray2 = new JSONArray();
                                    jsonArray2 = jsonArray1.getJSONArray(1);
                                    List<String> assessmentValues = new ArrayList<>();
                                    assessmentValues.add("Balance Time: " + jsonArray2.getInt(0));
                                    staticbalancetest.add(new Csvdatamodel(jsonObject.getString("patient_name"), jsonObject1.getInt("Age"), modename, assessmentValues));

                                }
                            }
                            else if("Dynamic Balance Test".equalsIgnoreCase(testname)){
                                jsonObject4 = jsonObject3.getJSONObject(testname);
                                Iterator<String> keys2 = jsonObject4.keys();
                                while(keys2.hasNext()){
                                    String modename= keys2.next();
                                    JSONArray jsonArray1 = new JSONArray();
                                    if(modename.contains("left")) {
                                        jsonArray1 = jsonObject4.getJSONArray(modename);
                                        modename =modename.contains("wos") ? "Without Support" : "With Support";
                                        JSONArray jsonArray2 = new JSONArray();
                                        jsonArray2 = jsonArray1.getJSONArray(1);
                                        List<String> assessmentValues = new ArrayList<>();
                                        assessmentValues.add("Sit to Stand Time: " + jsonArray2.getInt(0));
                                        assessmentValues.add("Stand to Sit Time: " + jsonArray2.getInt(1));
                                        assessmentValues.add("Walk Time: " + jsonArray2.getInt(2));
                                        dynamicbalancetest.add(new Csvdatamodel(jsonObject.getString("patient_name"), jsonObject1.getInt("Age"), modename, assessmentValues));
                                    }
                                }
                            }
                            else if("Staircase Climbing Test".equalsIgnoreCase(testname)){
                                jsonObject4 = jsonObject3.getJSONObject(testname);
                                Iterator<String> keys2 = jsonObject4.keys();
                                while(keys2.hasNext()){
                                    String modename= keys2.next();
                                    JSONArray jsonArray1 = new JSONArray();
                                    if(modename.contains("left")) {
                                        jsonArray1 = jsonObject4.getJSONArray(modename);
                                        modename =modename.contains("wos") ? "Without Support" : "With Support";
                                        JSONArray jsonArray2 = new JSONArray();
                                        jsonArray2 = jsonArray1.getJSONArray(1);
                                        List<String> assessmentValues = new ArrayList<>();
                                        assessmentValues.add("Step Count: " + jsonArray2.getInt(0));
                                        assessmentValues.add("Ascent Time: " + jsonArray2.getInt(1));
                                        assessmentValues.add("Turn Time: " + jsonArray2.getInt(2));
                                        assessmentValues.add("Descent Time: " + jsonArray2.getInt(3));
                                        staircaseclimbingtest.add(new Csvdatamodel(jsonObject.getString("patient_name"), jsonObject1.getInt("Age"), modename, assessmentValues));
                                    }
                                }
                            }
                            else if("Walk and Gait Analysis".equalsIgnoreCase(testname)){
                                jsonObject4 = jsonObject3.getJSONObject(testname);
                                Iterator<String> keys2 = jsonObject4.keys();
                                while(keys2.hasNext()){
                                    String modename= keys2.next();
                                    JSONArray jsonArray1 = new JSONArray();
                                    jsonArray1 = jsonObject4.getJSONArray(modename);
                                    JSONArray jsonArray2 = new JSONArray();
                                    jsonArray2 = jsonArray1.getJSONArray(1);
                                    List<String> assessmentValues = new ArrayList<>();
                                    assessmentValues.add("Distance: " + jsonArray2.getInt(0));
                                    assessmentValues.add("Stand-Time: " + jsonArray2.getInt(1));
                                    assessmentValues.add("Swing-Time: " + jsonArray2.getInt(2));
                                    assessmentValues.add("Stance-Phase: " + jsonArray2.getInt(3));
                                    assessmentValues.add("Stride-Length: " + jsonArray2.getInt(4));
                                    assessmentValues.add("Stride-Length-%h: " + jsonArray2.getInt(5));
                                    assessmentValues.add("Step-Length: " + jsonArray2.getInt(6));
                                    assessmentValues.add("Mean-Velocity: " + jsonArray2.getInt(7));
                                    assessmentValues.add("Cadence: " + jsonArray2.getInt(8));
                                    assessmentValues.add("Step-Count: " + jsonArray2.getInt(9));
                                    assessmentValues.add("Active-Time: " + jsonArray2.getInt(10));
                                    walkgaittest.add(new Csvdatamodel(jsonObject.getString("patient_name"), jsonObject1.getInt("Age"), modename, assessmentValues));
                                }
                            }
                        }
                    }





                }
            }
        }
        catch (JSONException e) {
            throw new RuntimeException(e);
        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            ContentValues values = new ContentValues();
            values.put(MediaStore.MediaColumns.DISPLAY_NAME, fileName.replace(".csv", ".xlsx")); // Change to .xlsx
            values.put(MediaStore.MediaColumns.MIME_TYPE, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            values.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DOCUMENTS + "/" + folderName);

            Uri uri = requireContext().getContentResolver().insert(MediaStore.Files.getContentUri("external"), values);
            if (uri == null) {
                Log.e("BLE", "Failed to create file URI");
                return;
            }

            try (OutputStream outputStream = requireContext().getContentResolver().openOutputStream(uri)) {
                if (outputStream != null) {
                    Workbook workbook = new XSSFWorkbook();

                    // Create Bold Styles
                    CellStyle boldStyle = workbook.createCellStyle();
                    Font boldFont = workbook.createFont();
                    boldFont.setBold(true);
                    boldFont.setFontHeightInPoints((short) 12);
                    boldStyle.setFont(boldFont);

                    CellStyle boldStyle1 = workbook.createCellStyle();
                    Font boldFont1 = workbook.createFont();
                    boldFont1.setBold(true);
                    boldFont1.setFontHeightInPoints((short) 14);
                    boldStyle1.setFont(boldFont1);

                    CellStyle boldStyle2 = workbook.createCellStyle();
                    Font boldFont2 = workbook.createFont();
                    boldFont2.setBold(true);
                    boldFont2.setFontHeightInPoints((short) 11);
                    boldStyle2.setFont(boldFont2);

                    // Store all tests in a single map
                    Map<String, List<Csvdatamodel>> assessmentData = new LinkedHashMap<>();
                    assessmentData.put("Mobility Test", mobilitytest);
                    assessmentData.put("Extension Lag Test", extensionlagtest);
                    assessmentData.put("Proprioception Test", proprioceptiontest);
                    assessmentData.put("Static Balance Test", staticbalancetest);
                    assessmentData.put("Dynamic Balance Test", dynamicbalancetest);
                    assessmentData.put("Staircase Climbing Test", staircaseclimbingtest);
                    assessmentData.put("Walk and Gait Analysis", walkgaittest);

                    // Write each test into a separate sheet
                    for (Map.Entry<String, List<Csvdatamodel>> testEntry : assessmentData.entrySet()) {
                        String testName = testEntry.getKey();
                        List<Csvdatamodel> testRecords = testEntry.getValue();

                        if (!testRecords.isEmpty()) {
                            Sheet sheet = workbook.createSheet(testName); // Create new sheet for each test
                            int rowIndex = 0;

                            // Set the test name in the first row
                            Row testRow = sheet.createRow(rowIndex++);
                            Cell testCell = testRow.createCell(0);
                            testCell.setCellValue(testName);
                            testCell.setCellStyle(boldStyle1); // Apply Bold Style

                            // Map patients within this test
                            Map<String, List<Csvdatamodel>> groupedPatients = new LinkedHashMap<>();
                            for (Csvdatamodel patient : testRecords) {
                                groupedPatients.computeIfAbsent(patient.patientName, k -> new ArrayList<>()).add(patient);
                            }

                            // Write each patient's data in the test sheet
                            for (Map.Entry<String, List<Csvdatamodel>> patientEntry : groupedPatients.entrySet()) {
                                String patientName = patientEntry.getKey();
                                List<Csvdatamodel> patientRecords = patientEntry.getValue();

                                // Write patient name & age
                                Row patientRow = sheet.createRow(rowIndex++);
                                Cell patientCell = patientRow.createCell(0);
                                patientCell.setCellValue(patientName + ", Age: " + patientRecords.get(0).age);
                                patientCell.setCellStyle(boldStyle); // Apply Bold Style

                                // Write parameter headers
                                Row headerRow = sheet.createRow(rowIndex++);
                                Cell cell = headerRow.createCell(0);
                                cell.setCellValue("Assessment Mode");
                                cell.setCellStyle(boldStyle2);

                                int colIndex = 1;
                                for (String param : patientRecords.get(0).parameters) {
                                    Cell paramCell = headerRow.createCell(colIndex++);
                                    paramCell.setCellValue(param.split(":")[0]); // Extract parameter name
                                    paramCell.setCellStyle(boldStyle2);
                                }

                                // Write test results for the patient
                                for (Csvdatamodel patient : patientRecords) {
                                    Row dataRow = sheet.createRow(rowIndex++);
                                    dataRow.createCell(0).setCellValue(patient.assessmentMode);
                                    colIndex = 1;
                                    for (String param : patient.parameters) {
                                        dataRow.createCell(colIndex++).setCellValue(param.split(":")[1]); // Extract parameter value
                                    }
                                }

                                rowIndex++; // Extra spacing between patients
                            }
                        }
                    }

                    workbook.write(outputStream);
                    workbook.close();

                    Log.d("BLE", "Excel saved to Documents/" + folderName);
                    Toasty.success(getContext(),"Data Export Successful Please Check your Document Directory",Toasty.LENGTH_LONG).show();
                }
            }
            catch (Exception e) {
                Log.e("BLE", "Error saving Excel file", e);
            }
        }

    }
}
