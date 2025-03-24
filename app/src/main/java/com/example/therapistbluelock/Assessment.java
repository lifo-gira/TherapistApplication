package com.example.therapistbluelock;

import static com.example.therapistbluelock.DetailFrag_5.activepassive;
import static com.example.therapistbluelock.DetailFrag_5.angles1;
import static com.example.therapistbluelock.DetailFrag_5.ascentend;
import static com.example.therapistbluelock.DetailFrag_5.ascentstart;
import static com.example.therapistbluelock.DetailFrag_5.currentMetricIndex;
import static com.example.therapistbluelock.DetailFrag_5.descentend;
import static com.example.therapistbluelock.DetailFrag_5.descentstart;
import static com.example.therapistbluelock.DetailFrag_5.extension1;
import static com.example.therapistbluelock.DetailFrag_5.flexion1;
import static com.example.therapistbluelock.DetailFrag_5.flexionCycles1;
import static com.example.therapistbluelock.DetailFrag_5.extensionCycles1;
import static com.example.therapistbluelock.DetailFrag_5.maxangle1;
import static com.example.therapistbluelock.DetailFrag_5.minangle1;
import static com.example.therapistbluelock.DetailFrag_5.rom1;
import static com.example.therapistbluelock.DetailFrag_5.speedometer1;
import static com.example.therapistbluelock.DetailFrag_5.speedometer2;
import static com.example.therapistbluelock.DetailFrag_5.staircaseclimbingtestdata;
import static com.example.therapistbluelock.DetailFrag_5.startind;
import static com.example.therapistbluelock.DetailFrag_5.staticbalancetestdata;
import static com.example.therapistbluelock.DetailFrag_5.stepCountgait;
import static com.example.therapistbluelock.DetailFrag_5.timestamps;
import static com.example.therapistbluelock.DetailFrag_5.totalCycles1;
import static com.example.therapistbluelock.DetailFrag_5.angles;
import static com.example.therapistbluelock.DetailFrag_5.turnend;
import static com.example.therapistbluelock.DetailFrag_5.turnflag;
import static com.example.therapistbluelock.DetailFrag_5.turnstart;


import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.Pair;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Queue;
import java.util.UUID;
import java.util.function.BiConsumer;

import es.dmoral.toasty.Toasty;
import me.ibrahimsn.lib.Speedometer;

public class Assessment extends AppCompatActivity implements AssessmentCycleAdapter.OnItemClickListener, ActiveAssessmentCycleAdapter.OnItemClickListener1 {
//    private GaugeView gaugeView1, gaugeView2;


    private Spinner deviceSpinner;
    private BluetoothAdapter bluetoothAdapter;
    private List<BluetoothSocket> bluetoothSockets;
    private List<String> deviceAddresses;
    private UUID uuid;
    private LinearLayout pickerContainer, textContainer;
    private NumberPicker minutesPicker, secondsPicker;
    TextView minutesText, secondsText, number_picker_text;
    CountDownTimer countDownTimer;
    private long totalTimeInMillis;
    private boolean isTimerRunning = false;
    private Spinner exerciseSpinner; // New Spinner for exercises
    // LineChart declaration
    String itemTitle;
    private ProgressBar progressBar;
    //    public static JSONObject exedata = new JSONObject();
//    JSONObject subexedata = new JSONObject();
//    JSONObject finalexedata = new JSONObject();
//    JSONArray finaldataarray = new JSONArray();
//    String exename = "";
    //    JSONArray exevalue = new JSONArray();
//    JSONArray exepain = new JSONArray();
//    List<List<Entry>> highlightArray = new ArrayList<>();
    int exerom = 0, pos = 0, flag = 0;
    //ArrayList<IndiviCardData> indiviCardData = new ArrayList<>();
    int totaltime, m, s, i = 0, maxrom, cycleno;
    float maxrom1 = 0;
    long maxtime = 0, sitstandtime, standshifttime, walkt, staticbaltime = 0;
    //    RecyclerView indivi;
    int expos = 0;
    ImageView center_button;
    LinearLayout download;
    CardView playPauseButton;
    //    JSONArray reportarray= new JSONArray();
//    JSONObject reportobject = new JSONObject();
//    JSONArray datareportarray = new JSONArray();


    //    int counter = -1;
    List<Float> stackedMetricsArray = new ArrayList<>();
    //    List<Metric> metricArray = new ArrayList<>();
//    int cycleCount = 1,cycleCount1=1,cycleCount2=1;
//    long elapsedTime = -1;
//    int flexionCycles = 0,flexionCycles1=0;
//    int extensionCycles = 0, extensionCycles1=0;
//    int totalCycles = 0, totalCycles1 = 0;
//    List<Long> times = new ArrayList<>();
//    List<Entry> objectElements = new ArrayList<>();
//    int prevSignChange = 0,prevSignChange1 = 0;
//    int pain = 0;
//    List<Float> indiviminAngle = new ArrayList<>();
//    List<Float> indivimaxAngle = new ArrayList<>();;
//    List<Float> indiviflexionVelocities = new ArrayList<>();
//    List<Float> indiviextensionVelocities = new ArrayList<>();
    private BluetoothSocket bluetoothSocket;
    private BluetoothDevice bluetoothDevice;
//    List<Integer> indivipain = new ArrayList<>();

    //    LineData lineData = new LineData();
    boolean isPlaying = false;
    boolean isSecondValueReceived = false;
    float temps = 0;
    boolean isFlexion = false;
    //    AssessmentCycleAdapter indiviCardAdapter;
    int l = 0;


    TextView exercise_progress;

//    List<ExerciseCycleAssessment> exerciseList;

    TextView assessment_text_view;
    LinearLayout activepassiveswitch, Left, Right;
    View left_underlined, right_underlined;
    RecyclerView assess_cycles_passive, assess_cycles_active, assess_cycles_total;
    String actpas, leg = "left";
    private boolean isDeviceConnected;

    Switch switch_button;

    private float previousLeftKneeAngle = 0;
    private float previousRightKneeAngle = 0;

    Handler handler = new Handler();

    int minutes = 0, seconds = 0, firstvalue = 0;

    String legindication = "left";

    private final float[] numbers = {10, 20, 30, 40, 50, 60, 50, 40, 30, 20, 10};
    private int index = 0;

    Handler uiHandler;
    BluetoothConnectionManager connectionManager;

    // Indices of the two devices we want to process
    int DEVICE_INDEX_1 = 0;  // Replace with actual index or criteria
    int DEVICE_INDEX_2 = 1;  // Replace with actual index or criteria


    // Temporary storage for the latest values from the two target devices

    LinearLayout submit_text;
    List<Float> slice = new ArrayList<>();
    List<Float> slice1 = new ArrayList<>();
    List<Float> slice2 = new ArrayList<>();

    Queue<Float> device1queue = new LinkedList<>();
    Queue<Float> device2queue = new LinkedList<>();

    String wos;

    int ctr = 0;

    int walkstarted = 0, standtoshift = 0, ascentflag = 1, descentflag = 0;

    public static List<Long> leftswingtime = new ArrayList<>();
    public static List<Long> rightswingtime = new ArrayList<>();
    List<Long> stance = new ArrayList<>();
    List<Double> leftstride = new ArrayList<>();
    List<Double> rightstride = new ArrayList<>();
    List<Double> leftstrideper = new ArrayList<>();
    List<Double> rightstrideper = new ArrayList<>();
    List<String> step = new ArrayList<>();
    List<String> cade = new ArrayList<>();

    TextView therapistname;

    JSONArray testdata = new JSONArray();


    private volatile boolean isRunning = true;


    JSONArray accldata = new JSONArray();
    JSONArray accldatax = new JSONArray();
    JSONArray accldatay = new JSONArray();
    JSONArray accldataz = new JSONArray();
    JSONArray magdatax = new JSONArray();
    JSONArray magdatay = new JSONArray();
    JSONArray magdataz = new JSONArray();
    JSONArray gyrodatax = new JSONArray();
    JSONArray gyrodatay = new JSONArray();
    JSONArray gyrodataz = new JSONArray();

    JSONArray yawdata = new JSONArray();
    JSONArray pitchdata = new JSONArray();

    double leftacclx = 0, leftaccly = 0, leftacclz = 0, rightacclx = 0, rightaccly = 0, rightacclz = 0, yaw = 0, pitch = 0, rollleft = 0, rollright = 0, magx = 0, magy = 0, magz = 0, gyrox = 0, gyroy = 0, gyroz = 0;
    double lefttotalAcceleration = 0, righttotalAcceleration = 0;

    int leftstepflag = 0, rightstepflag = 0;

    TextView dynamicbalancetestinfo;

    int proprioflag = 0;

    CardView proprionextcycle;
    TextView proprionextcyclebtn;

    int proprioswitchflag = 0;
    float propriopassvalue = 0, proprioactvalue = 0, propriototalvalue = 0;


    JSONObject propriopassive = new JSONObject();
    JSONObject proprioactive = new JSONObject();

    int stepcountwalk = 0;

    double lascent = 0, lturn = 0, ldescent = 0, rascent = 0, rturn = 0, rdescent = 0;

    List<Float> sensordata = new ArrayList<>();
    BluetoothSocket socket;




    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment);
        hideSystemUI();

        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                // Handle the back button event
            }
        };
        this.getOnBackPressedDispatcher().addCallback(this, callback);

//        speedometer = findViewById(R.id.speedometer);
//        speedometer.setTextColor(0x00000000);
        // Start a thread to dynamically update the speed


        speedometer1 = findViewById(R.id.speedometer1);
        speedometer1.setTextColor(0x00000000);
        speedometer2 = findViewById(R.id.speedometer2);
        speedometer2.setTextColor(0x00000000);

        dynamicbalancetestinfo = findViewById(R.id.dynamicbalancetestinfo);
        therapistname = findViewById(R.id.therapistname);
        therapistname.setText(MainActivity.therapistname);

        proprionextcycle = findViewById(R.id.proprionextcycle);
        proprionextcyclebtn = findViewById(R.id.proprionextcyclebtn);

//        gaugeView1 = findViewById(R.id.gaugeView1);
//        gaugeView2 = findViewById(R.id.gaugeView2);

        uiHandler = new Handler(Looper.getMainLooper());
        connectionManager = BluetoothConnectionManager.getInstance();
        submit_text = findViewById(R.id.back_to_assessment);
        setupBluetoothConnections();


        DetailFrag_5.highlightArraypass = new ArrayList<>();
        DetailFrag_5.highlightArrayact = new ArrayList<>();
        DetailFrag_5.dynamicbalancetestdata = new ArrayList<>();

//        assessment_text_view = findViewById(R.id.assessment_text_view);
//        assessment_text_view.setText(DetailFrag_5.selectedExercise);
        activepassiveswitch = findViewById(R.id.activepassiveswitch);
        Left = findViewById(R.id.Left);
        Right = findViewById(R.id.Right);
        left_underlined = findViewById(R.id.left_underlined);
        right_underlined = findViewById(R.id.right_underlined);
        assess_cycles_passive = findViewById(R.id.assess_cycles_passive);
        assess_cycles_active = findViewById(R.id.assess_cycles_active);
        assess_cycles_total = findViewById(R.id.assess_cycles_total);
        assess_cycles_passive.setVisibility(View.VISIBLE);
        assess_cycles_active.setVisibility(View.VISIBLE);

        DetailFrag_5.exerciseListtotal = new ArrayList<>();
        DetailFrag_5.exerciseListtotal.clear();


        number_picker_text = findViewById(R.id.number_picker_text);

        switch_button = findViewById(R.id.switch_button);

        deviceSpinner = findViewById(R.id.device_spinner);
        pickerContainer = findViewById(R.id.picker_container);
        textContainer = findViewById(R.id.text_container);
        minutesPicker = findViewById(R.id.minutes);
        secondsPicker = findViewById(R.id.seconds);
        minutesText = findViewById(R.id.minutes_text);
        secondsText = findViewById(R.id.seconds_text);

        assess_cycles_passive.setVisibility(View.VISIBLE);
        assess_cycles_active.setVisibility(View.VISIBLE);


        if ("Mobility Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
            number_picker_text.setText("Time Elapsed");
            activepassiveswitch.setVisibility(View.VISIBLE);
            switch_button.setVisibility(View.VISIBLE);
            switch_button.setTextOff("Active");
            switch_button.setTextOn("Passive");
            Left.setVisibility(View.VISIBLE);
            Right.setVisibility(View.VISIBLE);
            assess_cycles_passive.setVisibility(View.GONE);
            assess_cycles_active.setVisibility(View.VISIBLE);
            dynamicbalancetestinfo.setVisibility(View.GONE);
            pickerContainer.setVisibility(View.GONE);
            proprionextcycle.setVisibility(View.GONE);
            proprionextcyclebtn.setVisibility(View.GONE);

        } else if ("Extension Lag Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
            number_picker_text.setText("Time Elapsed");
            activepassiveswitch.setVisibility(View.VISIBLE);
            Left.setVisibility(View.VISIBLE);
            Right.setVisibility(View.VISIBLE);
            left_underlined.setVisibility(View.VISIBLE);
            right_underlined.setVisibility(View.INVISIBLE);
            assess_cycles_passive.setVisibility(View.VISIBLE);
            assess_cycles_active.setVisibility(View.GONE);
            switch_button.setVisibility(View.GONE);
            pickerContainer.setVisibility(View.GONE);
            dynamicbalancetestinfo.setVisibility(View.GONE);
            proprionextcycle.setVisibility(View.GONE);
            proprionextcyclebtn.setVisibility(View.GONE);
        } else if ("Dynamic Balance Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
            number_picker_text.setText("Time Elapsed");
            activepassiveswitch.setVisibility(View.VISIBLE);
            Left.setVisibility(View.INVISIBLE);
            Right.setVisibility(View.INVISIBLE);
            assess_cycles_passive.setVisibility(View.GONE);
            assess_cycles_active.setVisibility(View.VISIBLE);
            switch_button.setVisibility(View.VISIBLE);
            switch_button.setTextOff("Without Support");
            switch_button.setTextOn("With Support");
            pickerContainer.setVisibility(View.GONE);
            dynamicbalancetestinfo.setVisibility(View.VISIBLE);
            proprionextcycle.setVisibility(View.GONE);
            proprionextcyclebtn.setVisibility(View.GONE);
        } else if ("Static Balance Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
            number_picker_text.setText("Time Elapsed");
            activepassiveswitch.setVisibility(View.VISIBLE);
            Left.setVisibility(View.VISIBLE);
            Right.setVisibility(View.VISIBLE);
            left_underlined.setVisibility(View.VISIBLE);
            right_underlined.setVisibility(View.INVISIBLE);
            assess_cycles_passive.setVisibility(View.GONE);
            assess_cycles_active.setVisibility(View.VISIBLE);
            switch_button.setTextOff("Eyes Open");
            switch_button.setTextOn("Eyes Closed");
            switch_button.setVisibility(View.VISIBLE);
            pickerContainer.setVisibility(View.GONE);
            dynamicbalancetestinfo.setVisibility(View.GONE);
            proprionextcycle.setVisibility(View.GONE);
            proprionextcyclebtn.setVisibility(View.GONE);
        } else if ("Staircase Climbing Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
            number_picker_text.setText("Time Elapsed");
            activepassiveswitch.setVisibility(View.VISIBLE);
            Left.setVisibility(View.INVISIBLE);
            Right.setVisibility(View.INVISIBLE);
            assess_cycles_passive.setVisibility(View.GONE);
            assess_cycles_active.setVisibility(View.VISIBLE);
            switch_button.setTextOff("Without Support");
            switch_button.setTextOn("With Support");
            switch_button.setVisibility(View.VISIBLE);
            pickerContainer.setVisibility(View.GONE);
            dynamicbalancetestinfo.setVisibility(View.GONE);
            proprionextcycle.setVisibility(View.GONE);
            proprionextcyclebtn.setVisibility(View.GONE);
        } else if ("Proprioception Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
            number_picker_text.setText("Time Elapsed");
            activepassiveswitch.setVisibility(View.VISIBLE);
            Left.setVisibility(View.VISIBLE);
            Right.setVisibility(View.VISIBLE);
            switch_button.setVisibility(View.VISIBLE);
            switch_button.setTextOff("Passive");
            switch_button.setTextOn("Active");
            assess_cycles_passive.setVisibility(View.GONE);
            assess_cycles_active.setVisibility(View.VISIBLE);
            speedometer1.setVisibility(View.GONE);
            dynamicbalancetestinfo.setVisibility(View.GONE);
            pickerContainer.setVisibility(View.GONE);
            proprionextcycle.setVisibility(View.VISIBLE);
            proprionextcyclebtn.setVisibility(View.VISIBLE);
        } else if ("Walk and Gait analysis".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
            number_picker_text.setText("Time Elapsed");
            activepassiveswitch.setVisibility(View.INVISIBLE);
            Left.setVisibility(View.INVISIBLE);
            Right.setVisibility(View.INVISIBLE);
            assess_cycles_passive.setVisibility(View.GONE);
            assess_cycles_active.setVisibility(View.VISIBLE);
            pickerContainer.setVisibility(View.GONE);
            dynamicbalancetestinfo.setVisibility(View.GONE);
            proprionextcycle.setVisibility(View.GONE);
            proprionextcyclebtn.setVisibility(View.GONE);
        }

        // Get the itemType and itemTitle passed from the Intent
        itemTitle = getIntent().getStringExtra("itemTitle");  // Retrieve the itemTitle passed from the AssessmentList activity

        assessment_text_view = findViewById(R.id.assessment_text_view);
        assessment_text_view.setText(itemTitle); // Set the text dynamically

        switch_button.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if ("Proprioception Test".equalsIgnoreCase(DetailFrag_5.selectedExercise) && propriopassvalue == 0) {  // Replace isConditionMet() with your actual condition
                    Toasty.warning(Assessment.this, "Perform Doctor Turn First to proceed", Toast.LENGTH_SHORT).show();
                    switch_button.setOnCheckedChangeListener(null); // Remove listener temporarily to prevent infinite loop
                    switch_button.setChecked(!isChecked); // Revert to previous state
                    switch_button.setOnCheckedChangeListener(this); // Reattach listener
                    return;
                }
                if (isChecked) {
                    if ("Proprioception Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
                        DetailFrag_5.propriolegswitchflag = 0;
                        DetailFrag_5.postsubdata.put(DetailFrag_5.postexevalues);
                        DetailFrag_5.postexevalues = new JSONArray();
//                        switch_button.setChecked(!isChecked);
                        try {
                            DetailFrag_5.postexeparameters.put(propriopassvalue);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }


                    Log.e("INBASEKAR", activepassive);

                    if (DetailFrag_5.playflag == 1) {
                        if (!"Proprioception Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
                            switch_button.setChecked(false);
                            activepassive = "active";
                        }
                        Toasty.warning(Assessment.this, "Please stop the timer to switch the mode", Toast.LENGTH_SHORT).show();
                    } else {
                        activepassive = "passive";
                        DetailFrag_5.currentMetricIndex = 0;
                        DetailFrag_5.lineData.clearValues();
                        isTimerRunning = true;
                        DetailFrag_5.entries.clear();
                        DetailFrag_5.entries1.clear();
                        DetailFrag_5.lineChart.clear();
                        DetailFrag_5.counter = -1;
                        DetailFrag_5.metricArray.clear();
                        DetailFrag_5.metricArray1.clear();
                        DetailFrag_5.stackedMetricsArray.clear();
                        DetailFrag_5.elapsedTime = -1;
                        DetailFrag_5.flexionCycles = 0;
                        DetailFrag_5.extensionCycles = 0;
                        flexionCycles1 = 0;
                        extensionCycles1 = 0;
                        DetailFrag_5.totalCycles = 0;
                        totalCycles1 = 0;
                        DetailFrag_5.cycleCount = 1;
                        DetailFrag_5.cycleCount1 = 1;
                        DetailFrag_5.cycleCount2 = 1;
                        angles.clear();
                        angles1.clear();
                        DetailFrag_5.times.clear();
                        DetailFrag_5.times1.clear();
                        DetailFrag_5.startTime = 0;
                        DetailFrag_5.endTime = 0;
                        DetailFrag_5.stepCount = 0;
                        DetailFrag_5.leftrom.clear();
                        DetailFrag_5.rightrom.clear();
                        DetailFrag_5.totalDistance = 0;
                        DetailFrag_5.stepCountwalk = 0;
                        DetailFrag_5.activeTime = 0;
                        DetailFrag_5.avgStandtime = 0;
                        DetailFrag_5.avgSwingtime = 0;
                        DetailFrag_5.avgStancetime = 0;
                        DetailFrag_5.strideLength = 0;
                        DetailFrag_5.meanVelocity = 0;
                        DetailFrag_5.cadence = 0;
                        currentMetricIndex = 0;
//                        DetailFrag_5.staticbaleo = 0;
//                        DetailFrag_5.staticbalec = 0;
                    }
                } else {


                    Log.e("INBASEKAR", activepassive);

                    if (DetailFrag_5.playflag == 1) {
                        activepassive = "passive";
                        if (!"Proprioception Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
                            switch_button.setChecked(true);
                        }
                        Toasty.warning(Assessment.this, "Please stop the timer to switch the mode", Toast.LENGTH_SHORT).show();
                    } else {
                        activepassive = "active";
                        currentMetricIndex = 0;
                        DetailFrag_5.lineData.clearValues();
                        isTimerRunning = true;
                        DetailFrag_5.entries.clear();
                        DetailFrag_5.entries1.clear();
                        DetailFrag_5.lineChart.clear();
                        DetailFrag_5.counter = -1;
                        DetailFrag_5.metricArray.clear();
                        DetailFrag_5.metricArray1.clear();
                        DetailFrag_5.stackedMetricsArray.clear();
                        DetailFrag_5.elapsedTime = -1;
                        DetailFrag_5.flexionCycles = 0;
                        DetailFrag_5.extensionCycles = 0;
                        flexionCycles1 = 0;
                        extensionCycles1 = 0;
                        DetailFrag_5.totalCycles = 0;
                        totalCycles1 = 0;
                        DetailFrag_5.cycleCount = 1;
                        DetailFrag_5.cycleCount1 = 1;
                        DetailFrag_5.cycleCount2 = 1;
                        angles.clear();
                        angles1.clear();
                        DetailFrag_5.times.clear();
                        DetailFrag_5.times1.clear();
                        DetailFrag_5.startTime = 0;
                        DetailFrag_5.endTime = 0;
                        DetailFrag_5.stepCount = 0;
                        DetailFrag_5.leftrom.clear();
                        DetailFrag_5.rightrom.clear();
                        DetailFrag_5.totalDistance = 0;
                        DetailFrag_5.stepCountwalk = 0;
                        DetailFrag_5.activeTime = 0;
                        DetailFrag_5.avgStandtime = 0;
                        DetailFrag_5.avgSwingtime = 0;
                        DetailFrag_5.avgStancetime = 0;
                        DetailFrag_5.strideLength = 0;
                        DetailFrag_5.meanVelocity = 0;
                        DetailFrag_5.cadence = 0;
//                        DetailFrag_5.staticbaleo = 0;
//                        DetailFrag_5.staticbalec = 0;
                    }
                }
            }
        });

        Left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DetailFrag_5.playflag == 1) {
                    Toasty.warning(Assessment.this, "Please stop the timer to switch the leg", Toast.LENGTH_SHORT).show();
                } else if ("Proprioception Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
                    if ((DetailFrag_5.propriolegswitchflag == 1) || (DetailFrag_5.propriolegswitchflag != 0 && proprioactvalue != 0)) {

                        DetailFrag_5.extensionlagCycleAssessments.clear();
                        DetailFrag_5.proprioceptionAdapter.notifyDataSetChanged();

                        left_underlined.setVisibility(View.VISIBLE);
                        right_underlined.setVisibility(View.INVISIBLE);
                        leg = "left";
                        DetailFrag_5.selecteddeviceindex = 0;
                        switch_button.setChecked(false);

                        currentMetricIndex = 0;
                        DetailFrag_5.lineData.clearValues();
                        isTimerRunning = true;
                        DetailFrag_5.entries.clear();
                        DetailFrag_5.entries1.clear();
                        DetailFrag_5.lineChart.clear();
                        DetailFrag_5.counter = -1;
                        DetailFrag_5.metricArray.clear();
                        DetailFrag_5.metricArray1.clear();
                        DetailFrag_5.stackedMetricsArray.clear();
                        DetailFrag_5.elapsedTime = -1;
                        DetailFrag_5.flexionCycles = 0;
                        DetailFrag_5.extensionCycles = 0;
                        flexionCycles1 = 0;
                        extensionCycles1 = 0;
                        DetailFrag_5.totalCycles = 0;
                        totalCycles1 = 0;
                        DetailFrag_5.cycleCount = 1;
                        DetailFrag_5.cycleCount1 = 1;
                        DetailFrag_5.cycleCount2 = 1;
                        angles.clear();
                        angles1.clear();
                        DetailFrag_5.times.clear();
                        DetailFrag_5.times1.clear();
                        DetailFrag_5.startTime = 0;
                        DetailFrag_5.endTime = 0;
                        DetailFrag_5.stepCount = 0;
                        DetailFrag_5.leftrom.clear();
                        DetailFrag_5.rightrom.clear();
                        DetailFrag_5.totalDistance = 0;
                        DetailFrag_5.stepCountwalk = 0;
                        DetailFrag_5.activeTime = 0;
                        DetailFrag_5.avgStandtime = 0;
                        DetailFrag_5.avgSwingtime = 0;
                        DetailFrag_5.avgStancetime = 0;
                        DetailFrag_5.strideLength = 0;
                        DetailFrag_5.meanVelocity = 0;
                        DetailFrag_5.cadence = 0;

                        switch_button.setEnabled(true);
                        switch_button.setChecked(false);

                        if (!DetailFrag_5.extensionlagCycleAssessmentsleft.isEmpty()) {
                            for (int i = 0; i < DetailFrag_5.extensionlagCycleAssessmentsleft.size(); i++) {
                                DetailFrag_5.extensionlagCycleAssessments.add(new ExtensionlagCycleAssessment(DetailFrag_5.extensionlagCycleAssessmentsleft.get(i).getActiveed(), DetailFrag_5.extensionlagCycleAssessmentsleft.get(i).getPassiveed(), DetailFrag_5.extensionlagCycleAssessmentsleft.get(i).getTotaled()));
                            }
                        }

                        DetailFrag_5.propriocyclecount = 0;
                        DetailFrag_5.exerciseListact.clear();
                        DetailFrag_5.proprioceptionAdapter.notifyDataSetChanged();
                        speedometer1.setVisibility(View.VISIBLE);
                        speedometer2.setVisibility(View.GONE);
                        proprioactvalue = 0;
                        propriopassvalue = 0;
                        propriototalvalue = 0;
                        propriopassive = new JSONObject();
                        proprioactive = new JSONObject();
                    } else {
                        Toasty.warning(Assessment.this, "Please complete the current leg to switch the leg", Toast.LENGTH_SHORT).show();
                    }
                } else {
//                    DetailFrag_5.extensionlagCycleAssessments.clear();

                    left_underlined.setVisibility(View.VISIBLE);
                    right_underlined.setVisibility(View.INVISIBLE);
                    leg = "left";
                    DetailFrag_5.selecteddeviceindex = 0;
                    switch_button.setChecked(false);

                    currentMetricIndex = 0;
                    DetailFrag_5.lineData.clearValues();
                    isTimerRunning = true;
                    DetailFrag_5.entries.clear();
                    DetailFrag_5.entries1.clear();
                    DetailFrag_5.lineChart.clear();
                    DetailFrag_5.counter = -1;
                    DetailFrag_5.metricArray.clear();
                    DetailFrag_5.metricArray1.clear();
                    DetailFrag_5.stackedMetricsArray.clear();
                    DetailFrag_5.elapsedTime = -1;
                    DetailFrag_5.flexionCycles = 0;
                    DetailFrag_5.extensionCycles = 0;
                    flexionCycles1 = 0;
                    extensionCycles1 = 0;
                    DetailFrag_5.totalCycles = 0;
                    totalCycles1 = 0;
                    DetailFrag_5.cycleCount = 1;
                    DetailFrag_5.cycleCount1 = 1;
                    DetailFrag_5.cycleCount2 = 1;
                    angles.clear();
                    angles1.clear();
                    DetailFrag_5.times.clear();
                    DetailFrag_5.times1.clear();
                    DetailFrag_5.startTime = 0;
                    DetailFrag_5.endTime = 0;
                    DetailFrag_5.stepCount = 0;
                    DetailFrag_5.leftrom.clear();
                    DetailFrag_5.rightrom.clear();
                    DetailFrag_5.totalDistance = 0;
                    DetailFrag_5.stepCountwalk = 0;
                    DetailFrag_5.activeTime = 0;
                    DetailFrag_5.avgStandtime = 0;
                    DetailFrag_5.avgSwingtime = 0;
                    DetailFrag_5.avgStancetime = 0;
                    DetailFrag_5.strideLength = 0;
                    DetailFrag_5.meanVelocity = 0;
                    DetailFrag_5.cadence = 0;
//                    DetailFrag_5.staticbaleo = 0;
//                    DetailFrag_5.staticbalec = 0;

                    if ("Extension Lag Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
                        activepassive = "active";
                        DetailFrag_5.datareportarray = new JSONArray();
                        DetailFrag_5.reportarray = new JSONArray();
                        DetailFrag_5.mobilecyclecount = 0;
                        DetailFrag_5.extnpassivemax = 361;
                        DetailFrag_5.extnactivemax = 361;
                        DetailFrag_5.extndens = 0;
                        DetailFrag_5.extnflag = 0;
                        DetailFrag_5.extensionlagCycleAssessments.clear();
                        DetailFrag_5.leftlegwos.clear();
                        DetailFrag_5.activeeds.clear();
                        DetailFrag_5.passiveeds.clear();
                        DetailFrag_5.exerciseListact.clear();
                        DetailFrag_5.exerciseListpass.clear();
                        DetailFrag_5.totaleds.clear();
                        DetailFrag_5.exerciseListtotal.clear();
                        DetailFrag_5.exerciseCycleAssessment.clear();
                        DetailFrag_5.highlightArrayact.clear();
                        DetailFrag_5.highlightArraypass.clear();
                        DetailFrag_5.objectElements.clear();
                        DetailFrag_5.objectElements1.clear();
                        DetailFrag_5.extensionlagAdapter.notifyDataSetChanged();

                        if (!DetailFrag_5.extensionlagCycleAssessmentsleft.isEmpty()) {
                            for (int i = 0; i < DetailFrag_5.extensionlagCycleAssessmentsleft.size(); i++) {
                                DetailFrag_5.extensionlagCycleAssessments.add(new ExtensionlagCycleAssessment(DetailFrag_5.extensionlagCycleAssessmentsleft.get(i).getActiveed(), DetailFrag_5.extensionlagCycleAssessmentsleft.get(i).getPassiveed(), DetailFrag_5.extensionlagCycleAssessmentsleft.get(i).getTotaled()));
                            }
                        }
                        DetailFrag_5.extensionlagAdapter.notifyDataSetChanged();
                        assess_cycles_active.setVisibility(View.VISIBLE);

                        assess_cycles_passive.setVisibility(View.GONE);
//                        assess_cycles_active.setVisibility(View.GONE);
                        assess_cycles_total.setVisibility(View.GONE);

                        Log.e("Inside Left Leg:", String.valueOf(DetailFrag_5.exerciseListact));
                    } else if ("Static Balance Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
                        staticbalancetestdata.clear();
                        DetailFrag_5.staticbalancetestadapter.notifyDataSetChanged();
                        if (DetailFrag_5.staticbalancetestdataleft.size() > 0) {
                            for (int i = 0; i < DetailFrag_5.staticbalancetestdataleft.size(); i++) {
                                staticbalancetestdata.add(new Staticbalancetestdata(DetailFrag_5.staticbalancetestdataleft.get(i).getTime(), DetailFrag_5.staticbalancetestdataleft.get(i).getEyesstatus()));
                            }
                        }
                        DetailFrag_5.staticbalancetestadapter.notifyDataSetChanged();
                    } else if ("Mobility Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
                        DetailFrag_5.datareportarray = new JSONArray();
                        DetailFrag_5.reportarray = new JSONArray();
                        DetailFrag_5.mobilecyclecount = 0;
                        DetailFrag_5.mobilityCycleAssessments.clear();
                        DetailFrag_5.mobilityCycleAdapter.notifyDataSetChanged();
                        if (DetailFrag_5.mobilityCycleAssessmentsleft.size() > 0) {
                            for (int j = 0; j < DetailFrag_5.mobilityCycleAssessmentsleft.size(); j++) {
                                DetailFrag_5.mobilityCycleAssessments.add(new MobilityCycleAssessment(DetailFrag_5.mobilityCycleAssessmentsleft.get(j).getMaxflexion(), DetailFrag_5.mobilityCycleAssessmentsleft.get(j).getMinextension(), DetailFrag_5.mobilityCycleAssessmentsleft.get(j).getMode()));
                                Log.e("Mobility Left Assessment Data", DetailFrag_5.mobilityCycleAssessmentsleft.get(i).getMode());
                            }
                        }
                        DetailFrag_5.mobilityCycleAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
        Right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DetailFrag_5.playflag == 1) {
                    Toasty.warning(Assessment.this, "Please stop the timer to switch the leg", Toast.LENGTH_SHORT).show();
                } else if ("Proprioception Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
                    if ((DetailFrag_5.propriolegswitchflag == 1) || (DetailFrag_5.propriolegswitchflag != 0 && proprioactvalue != 0)) {
                        DetailFrag_5.extensionlagCycleAssessments.clear();
                        DetailFrag_5.proprioceptionAdapter.notifyDataSetChanged();
                        currentMetricIndex = 0;
                        left_underlined.setVisibility(View.INVISIBLE);
                        right_underlined.setVisibility(View.VISIBLE);
                        leg = "right";
                        DetailFrag_5.selecteddeviceindex = 1;
                        switch_button.setChecked(false);


                        switch_button.setEnabled(true);
                        switch_button.setChecked(false);

                        if (!DetailFrag_5.extensionlagCycleAssessmentsright.isEmpty()) {
                            for (int i = 0; i < DetailFrag_5.extensionlagCycleAssessmentsright.size(); i++) {
                                DetailFrag_5.extensionlagCycleAssessments.add(new ExtensionlagCycleAssessment(DetailFrag_5.extensionlagCycleAssessmentsright.get(i).getActiveed(), DetailFrag_5.extensionlagCycleAssessmentsright.get(i).getPassiveed(), DetailFrag_5.extensionlagCycleAssessmentsright.get(i).getTotaled()));
                            }
                        }

                        DetailFrag_5.propriocyclecount = 0;
                        DetailFrag_5.exerciseListact.clear();
                        DetailFrag_5.proprioceptionAdapter.notifyDataSetChanged();
                        speedometer2.setVisibility(View.VISIBLE);
                        speedometer1.setVisibility(View.GONE);
                        proprioactvalue = 0;
                        propriopassvalue = 0;
                        propriototalvalue = 0;
                        propriopassive = new JSONObject();
                        proprioactive = new JSONObject();
                    } else {
                        Toasty.warning(Assessment.this, "Please complete the current leg to switch the leg", Toast.LENGTH_SHORT).show();
                    }
                } else {
//                    DetailFrag_5.extensionlagCycleAssessmentsright.clear();
                    currentMetricIndex = 0;
                    left_underlined.setVisibility(View.INVISIBLE);
                    right_underlined.setVisibility(View.VISIBLE);
                    leg = "right";
                    DetailFrag_5.selecteddeviceindex = 1;
                    switch_button.setChecked(false);

                    if ("Extension Lag Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
                        activepassive = "active";
                        DetailFrag_5.datareportarray = new JSONArray();
                        DetailFrag_5.reportarray = new JSONArray();
                        DetailFrag_5.mobilecyclecount = 0;
                        DetailFrag_5.extnpassivemax = 361;
                        DetailFrag_5.extnactivemax = 361;
                        DetailFrag_5.extndens = 0;
                        DetailFrag_5.extnflag = 0;
                        DetailFrag_5.leftlegwos.clear();
                        DetailFrag_5.activeeds.clear();
                        DetailFrag_5.passiveeds.clear();

                        DetailFrag_5.exerciseListact.clear();
                        DetailFrag_5.exerciseListpass.clear();
                        DetailFrag_5.totaleds.clear();
                        DetailFrag_5.exerciseListtotal.clear();
                        DetailFrag_5.exerciseCycleAssessment.clear();
                        DetailFrag_5.highlightArrayact.clear();
                        DetailFrag_5.highlightArraypass.clear();
                        DetailFrag_5.objectElements.clear();
                        DetailFrag_5.objectElements1.clear();
                        DetailFrag_5.extensionlagCycleAssessments.clear();
                        DetailFrag_5.extensionlagAdapter.notifyDataSetChanged();


                        if (DetailFrag_5.extensionlagCycleAssessmentsright.size() > 0) {
                            for (int i = 0; i < DetailFrag_5.extensionlagCycleAssessmentsright.size(); i++) {
                                DetailFrag_5.extensionlagCycleAssessments.add(new ExtensionlagCycleAssessment(DetailFrag_5.extensionlagCycleAssessmentsright.get(i).getActiveed(), DetailFrag_5.extensionlagCycleAssessmentsright.get(i).getPassiveed(), DetailFrag_5.extensionlagCycleAssessmentsright.get(i).getTotaled()));
                            }
                        }
                        Log.e("Extensionlag Right leg size", String.valueOf(DetailFrag_5.extensionlagCycleAssessments.size()));
                        DetailFrag_5.extensionlagAdapter.notifyDataSetChanged();

                        assess_cycles_active.setVisibility(View.VISIBLE);

                        assess_cycles_passive.setVisibility(View.GONE);
//                        assess_cycles_active.setVisibility(View.GONE);
                        assess_cycles_total.setVisibility(View.GONE);

//                        DetailFrag_5.staticbaleo = 0;
//                        DetailFrag_5.staticbalec = 0;

                        Log.e("Inside Right Leg:", String.valueOf(DetailFrag_5.exerciseListact));
                    } else if ("Static Balance Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
                        staticbalancetestdata.clear();
                        DetailFrag_5.staticbalancetestadapter.notifyDataSetChanged();
                        if (DetailFrag_5.staticbalancetestdataright.size() > 0) {
                            for (int i = 0; i < DetailFrag_5.staticbalancetestdataright.size(); i++) {
                                staticbalancetestdata.add(new Staticbalancetestdata(DetailFrag_5.staticbalancetestdataright.get(i).getTime(), DetailFrag_5.staticbalancetestdataright.get(i).getEyesstatus()));
                            }
                        }
                        DetailFrag_5.staticbalancetestadapter.notifyDataSetChanged();
                    } else if ("Mobility Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
                        DetailFrag_5.datareportarray = new JSONArray();
                        DetailFrag_5.reportarray = new JSONArray();
                        DetailFrag_5.mobilecyclecount = 0;
                        DetailFrag_5.mobilityCycleAssessments.clear();
                        DetailFrag_5.mobilityCycleAdapter.notifyDataSetChanged();
                        if (DetailFrag_5.mobilityCycleAssessmentsright.size() > 0) {
                            for (int j = 0; j < DetailFrag_5.mobilityCycleAssessmentsright.size(); j++) {
                                DetailFrag_5.mobilityCycleAssessments.add(new MobilityCycleAssessment(DetailFrag_5.mobilityCycleAssessmentsright.get(j).getMaxflexion(), DetailFrag_5.mobilityCycleAssessmentsright.get(j).getMinextension(), DetailFrag_5.mobilityCycleAssessmentsright.get(j).getMode()));
//                                Log.e("Mobility Right Assessment Data",DetailFrag_5.mobilityCycleAssessmentsright.get(i).getMode());
                            }
                        }
                        DetailFrag_5.mobilityCycleAdapter.notifyDataSetChanged();
                    }

                }
            }
        });


        DetailFrag_5.indiviCardAdapterpass = new AssessmentCycleAdapter(this, getExerciseCycleList(), this, activepassive);
        assess_cycles_passive.setAdapter(DetailFrag_5.indiviCardAdapterpass);


        minutesText.setVisibility(View.VISIBLE);
        secondsText.setVisibility(View.VISIBLE);
        textContainer.setVisibility(View.VISIBLE);
        minutesPicker.setMinValue(0);
        minutesPicker.setMaxValue(59);
        secondsPicker.setMinValue(0);
        secondsPicker.setMaxValue(59);
        minutesPicker.setVisibility(View.GONE);
        secondsPicker.setVisibility(View.GONE);

//        if (!"Mobility Test".equalsIgnoreCase(DetailFrag_5.selectedExercise) && !"Proprioception Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
//
//        } else {
//            minutesPicker.setVisibility(View.VISIBLE);
//            secondsPicker.setVisibility(View.VISIBLE);
//        }

        // Set NumberPicker values (0-59 for minutes and seconds)

        progressBar = findViewById(R.id.progress_bar);
        DetailFrag_5.entries = new ArrayList<>();
        DetailFrag_5.entries1 = new ArrayList<>();

        // Play/Pause button
        playPauseButton = findViewById(R.id.play_pause_button);
        center_button = findViewById(R.id.center_button);
        download = findViewById(R.id.download);
        exercise_progress = findViewById(R.id.exercise_progress);

        // Set onClickListener for the play/pause button


        playPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (DetailFrag_5.playflag == 0) {
                    // Timer is starting
//                    if ("Mobility Test".equalsIgnoreCase(DetailFrag_5.selectedExercise) || "Proprioception Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
//                        totaltime = minutesPicker.getValue() * 60 + secondsPicker.getValue(); // Calculate total time in seconds
//
//                        // Check if total time is 0
//                        if (totaltime == 0) {
//                            Toasty.warning(Assessment.this, "Please set the timer before starting", Toast.LENGTH_SHORT).show();
//                            return; // Exit if timer is 0
//                        }
//
//                        // Initialize timer variables
//                        m = minutesPicker.getValue(); // Get the value from NumberPicker
//                        s = secondsPicker.getValue(); // Get the value from NumberPicker
//                        i = 0;
//                    }
                    if ("Extension Lag Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
                        if ("passive".equalsIgnoreCase(activepassive)) {
                            assess_cycles_passive.setVisibility(View.VISIBLE);
                            assess_cycles_active.setVisibility(View.VISIBLE);
                        } else {
                            assess_cycles_active.setVisibility(View.VISIBLE);
                            assess_cycles_passive.setVisibility(View.VISIBLE);
                        }
                    } else {
                        assess_cycles_passive.setVisibility(View.INVISIBLE);
                        assess_cycles_active.setVisibility(View.VISIBLE);
                    }

                    center_button.setImageResource(R.drawable.baseline_pause_24_purple); // Change icon to pause
                    Log.e("Clicked Status", "Start Timer");
                    DetailFrag_5.entries.clear();
                    DetailFrag_5.entries1.clear();
                    startTimer(); // Start the timer

                    // Disable the exercise spinner
                    //exerciseSpinner.setEnabled(false);
                } else { // Timer is stopping
                    center_button.setImageResource(R.drawable.baseline_play_arrow_24); // Change icon to play
                    Log.e("Clicked Status", "Stop Timer");
                    stopTimer(); // Stop the timer

                    // Enable the exercise spinner
                    //exerciseSpinner.setEnabled(true);
                }
            }
        });

        SharedPreferences prefs = getSharedPreferences("BluetoothPrefs", MODE_PRIVATE);
        isDeviceConnected = prefs.getBoolean("isConnected", false);
        Log.e("Device connected to mobile", String.valueOf(isDeviceConnected));

        int marginInDp = 15;
        int marginInPx = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, marginInDp, getResources().getDisplayMetrics());
        // Assuming you have a RecyclerView with the ID recycler_view in your layout

        if ("Extension Lag Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {

            if ("left".equalsIgnoreCase(leg)) {
                DetailFrag_5.extensionlagAdapter = new ExtensionlagAdapter(DetailFrag_5.extensionlagCycleAssessments, this);
                assess_cycles_active.setAdapter(DetailFrag_5.extensionlagAdapter);
                HorizontalItemDecoration itemDecoration = new HorizontalItemDecoration(marginInPx);
                assess_cycles_active.addItemDecoration(itemDecoration);
            } else {
                DetailFrag_5.extensionlagAdapter = new ExtensionlagAdapter(DetailFrag_5.extensionlagCycleAssessments, this);
                assess_cycles_active.setAdapter(DetailFrag_5.extensionlagAdapter);
                HorizontalItemDecoration itemDecoration = new HorizontalItemDecoration(marginInPx);
                assess_cycles_active.addItemDecoration(itemDecoration);
            }
//
//            DetailFrag_5.indiviCardAdapteract = new ActiveAssessmentCycleAdapter(this, DetailFrag_5.exerciseListact, this, activepassive);
//            assess_cycles_active.setAdapter(DetailFrag_5.indiviCardAdapteract);
//            assess_cycles_active.addItemDecoration(itemDecoration);

        } else if ("Dynamic Balance Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
            DetailFrag_5.dynamicbalanceadapter = new Dynamicbalanceadapter(this, DetailFrag_5.dynamicbalancetestdata);
            assess_cycles_active.setAdapter(DetailFrag_5.dynamicbalanceadapter);
            HorizontalItemDecoration itemDecoration = new HorizontalItemDecoration(marginInPx);
            assess_cycles_active.addItemDecoration(itemDecoration);
        } else if ("Static Balance Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
            Log.e("Static Balance Test", "Inside call");
            DetailFrag_5.staticbalancetestadapter = new Staticbalancetestadapter(this, staticbalancetestdata);
            assess_cycles_active.setAdapter(DetailFrag_5.staticbalancetestadapter);
            HorizontalItemDecoration itemDecoration = new HorizontalItemDecoration(marginInPx);
            assess_cycles_active.addItemDecoration(itemDecoration);
        } else if ("Staircase Climbing Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
            DetailFrag_5.staircaseclimbingadapter = new Staircaseclimbingadapter(staircaseclimbingtestdata, this);
            assess_cycles_active.setAdapter(DetailFrag_5.staircaseclimbingadapter);
            HorizontalItemDecoration itemDecoration = new HorizontalItemDecoration(marginInPx);
            assess_cycles_active.addItemDecoration(itemDecoration);
        } else if ("Mobility Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
            DetailFrag_5.mobilityCycleAdapter = new MobilityCycleAdapter(DetailFrag_5.mobilityCycleAssessments, this);
            assess_cycles_active.setAdapter(DetailFrag_5.mobilityCycleAdapter);
            HorizontalItemDecoration itemDecoration = new HorizontalItemDecoration(marginInPx);
            assess_cycles_active.addItemDecoration(itemDecoration);
        } else {
            if ("Walk and Gait Analysis".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
                DetailFrag_5.walkgaittestadapter = new Walkgaittestadapter(DetailFrag_5.walkgaittestdata, this);
                assess_cycles_active.setAdapter(DetailFrag_5.walkgaittestadapter);
                HorizontalItemDecoration itemDecoration = new HorizontalItemDecoration(marginInPx);
                assess_cycles_active.addItemDecoration(itemDecoration);
            } else if ("Proprioception Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
                if ("left".equalsIgnoreCase(leg)) {
                    DetailFrag_5.proprioceptionAdapter = new ProprioceptionAdapter(DetailFrag_5.extensionlagCycleAssessments, this);
                    assess_cycles_active.setAdapter(DetailFrag_5.proprioceptionAdapter);
                    HorizontalItemDecoration itemDecoration = new HorizontalItemDecoration(marginInPx);
                    assess_cycles_active.addItemDecoration(itemDecoration);
                } else {
                    DetailFrag_5.proprioceptionAdapter = new ProprioceptionAdapter(DetailFrag_5.extensionlagCycleAssessments, this);
                    assess_cycles_active.setAdapter(DetailFrag_5.proprioceptionAdapter);
                    HorizontalItemDecoration itemDecoration = new HorizontalItemDecoration(marginInPx);
                    assess_cycles_active.addItemDecoration(itemDecoration);
                }
            } else {
                DetailFrag_5.indiviCardAdapteract = new ActiveAssessmentCycleAdapter(this, getExerciseCycleList(), this, null);
                assess_cycles_active.setAdapter(DetailFrag_5.indiviCardAdapteract);
                HorizontalItemDecoration itemDecoration = new HorizontalItemDecoration(marginInPx);
                assess_cycles_active.addItemDecoration(itemDecoration);
            }

        }

        // Initialize Bluetooth Adapter
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        uuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

        // Retrieve device addresses from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        String addressesString = sharedPreferences.getString("device_addresses", null);
        deviceAddresses = (addressesString != null) ? new ArrayList<>(Arrays.asList(addressesString.split(","))) : new ArrayList<>();

        // Set up device spinner
//        deviceSpinner = findViewById(R.id.device_spinner);
//        if (deviceAddresses != null && !deviceAddresses.isEmpty()) {
//            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, deviceAddresses);
//            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//            deviceSpinner.setAdapter(adapter);
//
//            deviceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                @Override
//                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                    if (!isDeviceConnected) {
//
//                        for (int i = 0; i < deviceAddresses.size(); i++) {
//                            connectToDevice(deviceAddresses.get(i));
//                        }
//                        //connectToDevice(deviceAddresses.get(position)); // Connect to the selected device
//                        SharedPreferences.Editor editor1 = getSharedPreferences("BluetoothPrefs", MODE_PRIVATE).edit();
//                        editor1.putBoolean("isConnected", true);
//                        editor1.apply();
//                    }
//                }
//
//                @Override
//                public void onNothingSelected(AdapterView<?> parent) {
//                    // Handle case when no device is selected
//                }
//            });
//        } else {
//            Toast.makeText(this, "No devices connected", Toast.LENGTH_SHORT).show();
//        }

        // Initialize NumberPicker
        minutesPicker = findViewById(R.id.minutes);
        secondsPicker = findViewById(R.id.seconds);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            minutesPicker.setTextColor(Color.WHITE);
            secondsPicker.setTextColor(Color.WHITE);
        }

        // Set text color to black
        setNumberPickerTextColor(minutesPicker, Color.WHITE);
        setNumberPickerTextColor(secondsPicker, Color.WHITE);

        // Set up NumberPicker for minutes
        minutesPicker.setMinValue(0);
        minutesPicker.setMaxValue(59);

        // Set up NumberPicker for seconds
        secondsPicker.setMinValue(0);
        secondsPicker.setMaxValue(59);

        // Initialize and populate the new exercise spinner
        //String[] exercises = getResources().getStringArray(R.array.exercise_list);

        // Create and set up exercise spinner adapter
//        DetailFrag_5.indiviact = findViewById(R.id.assess_cycles_active);
//        DetailFrag_5.indivipass = findViewById(R.id.assess_cycles_passive);


        // Handle exercise selection
//        exerciseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String selectedExercise = exercises[position];
////                Toast.makeText(Assessment.this, "Selected exercise: " + selectedExercise, Toast.LENGTH_SHORT).show();
//                exename = selectedExercise;
//                subexedata = new JSONObject();
//                //finalexedata= new JSONObject();
//                highlightArray.clear();
//                pos=position;
//                indiviCardData = new ArrayList<>();
//                lineChart.clear();
//                m=0;
//                s=0;
//                indivi.setVisibility(View.INVISIBLE);
//
//                exercise_progress.setText((position+1)+"/4");
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                // Optional: handle case when no exercise is selected
//            }
//        });

        // Initialize the LineChart

        DetailFrag_5.exename = "Sample";
        DetailFrag_5.lineChart = findViewById(R.id.line_chart);
        setupLineChart();

        DetailFrag_5.indiviCardAdaptertotal = new TotalAssessmentCycleAdapter(DetailFrag_5.exerciseListtotal, this);
        assess_cycles_total.setAdapter(DetailFrag_5.indiviCardAdaptertotal);
        HorizontalItemDecoration itemDecoration = new HorizontalItemDecoration(marginInPx);
        assess_cycles_total.addItemDecoration(itemDecoration);


        // Bluetooth device setup (existing code remains)
        if (deviceAddresses != null && !deviceAddresses.isEmpty()) {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, deviceAddresses);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // Uncomment this if the original device spinner is in use
            deviceSpinner.setAdapter(adapter);
        } else {
            Toast.makeText(this, "No devices connected", Toast.LENGTH_SHORT).show();
        }

        proprionextcycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isPlaying) {
                    Toasty.warning(Assessment.this, "The graph is playing", Toast.LENGTH_SHORT, true).show();
                } else if (proprioactvalue == 0 && propriopassvalue == 0) {
                    Toasty.warning(Assessment.this, "The Active and Passive Value is missing", Toast.LENGTH_SHORT, true).show();
                } else if (proprioactvalue == 0) {
                    Toasty.warning(Assessment.this, "The Active Value is missing", Toast.LENGTH_SHORT, true).show();
                } else if (propriopassvalue == 0) {
                    Toasty.warning(Assessment.this, "The Passive Value is missing", Toast.LENGTH_SHORT, true).show();
                } else {
                    switch_button.setEnabled(true);
                    switch_button.setChecked(false);
                    activepassive = "Active";
                    DetailFrag_5.postsubdata.put(DetailFrag_5.postexevalues);
                    DetailFrag_5.postexevalues = new JSONArray();
                    try {
                        DetailFrag_5.postexeparameters.put(proprioactvalue);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    DetailFrag_5.extensionlagCycleAssessments.clear();
                    if ("left".equalsIgnoreCase(leg)) {
                        if (DetailFrag_5.extensionlagCycleAssessmentsleft.size() > 0) {
                            for (int i = 0; i < DetailFrag_5.extensionlagCycleAssessmentsleft.size(); i++) {
                                DetailFrag_5.extensionlagCycleAssessments.add(new ExtensionlagCycleAssessment(DetailFrag_5.extensionlagCycleAssessmentsleft.get(i).getActiveed(), DetailFrag_5.extensionlagCycleAssessmentsleft.get(i).getPassiveed(), DetailFrag_5.extensionlagCycleAssessmentsleft.get(i).getTotaled()));
                            }
                            DetailFrag_5.extensionlagCycleAssessments.add(new ExtensionlagCycleAssessment(proprioactvalue, propriopassvalue, proprioactvalue - propriopassvalue));
                            DetailFrag_5.extensionlagCycleAssessmentsleft.add(new ExtensionlagCycleAssessment(proprioactvalue, propriopassvalue, proprioactvalue - propriopassvalue));
                        } else {
                            DetailFrag_5.extensionlagCycleAssessments.add(new ExtensionlagCycleAssessment(proprioactvalue, propriopassvalue, proprioactvalue - propriopassvalue));
                            DetailFrag_5.extensionlagCycleAssessmentsleft.add(new ExtensionlagCycleAssessment(proprioactvalue, propriopassvalue, proprioactvalue - propriopassvalue));
                        }
                        Log.e("Prorpioception left leg size", String.valueOf(DetailFrag_5.extensionlagCycleAssessmentsleft.size()));

                    } else {
                        if (DetailFrag_5.extensionlagCycleAssessmentsright.size() > 0) {
                            for (int i = 0; i < DetailFrag_5.extensionlagCycleAssessmentsright.size(); i++) {
                                DetailFrag_5.extensionlagCycleAssessments.add(new ExtensionlagCycleAssessment(DetailFrag_5.extensionlagCycleAssessmentsright.get(i).getActiveed(), DetailFrag_5.extensionlagCycleAssessmentsright.get(i).getPassiveed(), DetailFrag_5.extensionlagCycleAssessmentsright.get(i).getTotaled()));
                            }
                            DetailFrag_5.extensionlagCycleAssessments.add(new ExtensionlagCycleAssessment(proprioactvalue, propriopassvalue, proprioactvalue - propriopassvalue));
                            DetailFrag_5.extensionlagCycleAssessmentsright.add(new ExtensionlagCycleAssessment(proprioactvalue, propriopassvalue, proprioactvalue - propriopassvalue));
                        } else {
                            DetailFrag_5.extensionlagCycleAssessments.add(new ExtensionlagCycleAssessment(proprioactvalue, propriopassvalue, proprioactvalue - propriopassvalue));
                            DetailFrag_5.extensionlagCycleAssessmentsright.add(new ExtensionlagCycleAssessment(proprioactvalue, propriopassvalue, proprioactvalue - propriopassvalue));
                        }

                    }
                    DetailFrag_5.proprioceptionAdapter.notifyDataSetChanged();
                    if (proprioswitchflag == 0) {
                        proprioswitchflag = 1;
                    } else {
                        proprioswitchflag = 0;
                    }
                    DetailFrag_5.propriolegswitchflag = 1;
                    proprioactvalue = 0;
                    propriopassvalue = 0;
//                    DetailFrag_5.postexeparameters = new JSONArray();
                    try {
//                        DetailFrag_5.postexeparameters.put(propriopassvalue);
//                        DetailFrag_5.postexeparameters.put(proprioactvalue);
//                        DetailFrag_5.postexeparameters.put(propriopassvalue-proprioactvalue);
                        DetailFrag_5.postexeparameters.put(DetailFrag_5.postexeparameters.getDouble(1) - DetailFrag_5.postexeparameters.getDouble(0));
                        DetailFrag_5.postsubdata.put(DetailFrag_5.postexeparameters);
                        DetailFrag_5.propriocyclecount++;
                        DetailFrag_5.postexesubdata.put(leg + "-leg-" + DetailFrag_5.propriocyclecount, DetailFrag_5.postsubdata);
                        DetailFrag_5.postsubdata = new JSONArray();
                        DetailFrag_5.postexevalues = new JSONArray();
                        DetailFrag_5.postexeparameters = new JSONArray();
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }


            }
        });

        download.setOnClickListener(new View.OnClickListener() {
            Date startDateTime = new Date();
            String startDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(startDateTime);
            String startTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(startDateTime);

            @Override
            public void onClick(View v) {

                if (isPlaying) {
                    Toasty.warning(Assessment.this, "Download can be accessed once the graph stops", Toast.LENGTH_SHORT, true).show();
                } else if (DetailFrag_5.lineChart == null || DetailFrag_5.mainreportobject == null || DetailFrag_5.mainreportobject.length() == 0) {
                    Toasty.warning(Assessment.this, "Chart Data not found", Toast.LENGTH_SHORT, true).show();
                } else if (DetailFrag_5.exename == null) {
                    Toasty.warning(Assessment.this, "Exercise Name not found", Toast.LENGTH_SHORT, true).show();
                } else {
                    Log.e("Inbasekar 1", String.valueOf(DetailFrag_5.mainreportobject));
//                    PdfGenerator.generateAndDownloadPdf(getApplicationContext(), DetailFrag_5.lineChart, minangle1, maxangle1, flexion1, extension1, rom1, HomeFragment.patientnam, startDate, startTime, HomeFragment.uname, DetailFrag_5.mainreportobject, DetailFrag_5.selectedExercise);
//                    SimpleJSONToCSV.exportGraphDataToCSV(Assessment.this, DetailFrag_5.entries, rolldata, yawdata, pitchdata, accldatax, accldatay, accldataz, magdatax, magdatay, magdataz, gyrodatax, gyrodatay, gyrodataz, "Test Data");
                    SimpleJSONToCSV.exportGraphDataToCSV(Assessment.this, DetailFrag_5.entries, DetailFrag_5.adjustedrolldataleft, DetailFrag_5.adjustedrolldataright, new JSONArray(), new JSONArray(), new JSONArray(), new JSONArray(), new JSONArray(), new JSONArray(), new JSONArray(), new JSONArray(), new JSONArray(), new JSONArray(), "Test Data");
                    Log.e("Inbasekar 3", String.valueOf(DetailFrag_5.mainreportobject));
//                    Log.e("INBASEKAR Left", String.valueOf(DetailFrag_5.leftlegwos));
//                    Log.e("INBASEKAR Right", String.valueOf(DetailFrag_5.rightwos));
                }
            }
        });

        submit_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlaying) { // Assuming isPlaying is a boolean that tracks if the timer is running
                    Toasty.warning(Assessment.this, "Please stop the chart before submitting", Toast.LENGTH_SHORT).show();
                } else {
                    Log.e("Assessment Check", String.valueOf(DetailFrag_5.postexesubdata));
                    boolean isValid = validateJSONObject(DetailFrag_5.postexesubdata);
                    if (isValid) {
                        if ("Walk and Gait Analysis".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
                            showConfirmationDialog1();
                        } else {
                            showConfirmationDialog();
                        }

                    } else {
                        Toasty.error(Assessment.this, "Performed Data not Stored", Toast.LENGTH_LONG).show();
                    }
                    Log.e("Inbasekar DATABASE DATA", String.valueOf(DetailFrag_5.postdata));

                }
                // Proceed with the submission logic
                   /* for (int i = 0; i < exedata.length(); i++) {
                        try {
                            Log.e("CompleteExeData", i + exedata.get(i).toString());
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }*/
//                    try {
//                        finalexedata.put("Exercise1", exedata);
//                        finaldataarray.put(finalexedata);
//                        Log.e("FinalExeData", String.valueOf(finaldataarray));
//                    } catch (JSONException e) {
//                        throw new RuntimeException(e);
//                    }
                //sendDataToServer(finalexedata);
            }
        });
    }

    private void setupBluetoothConnections() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        String addressesString = sharedPreferences.getString("device_addresses", null);
        ArrayList<String> deviceAddresses = addressesString != null
                ? new ArrayList<>(Arrays.asList(addressesString.split(",")))
                : new ArrayList<>();

        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled()) {
            Log.e("Bluetooth", "Bluetooth not available or not enabled");
            return;
        }

        for (int i = 0; i < deviceAddresses.size() && i < 4; i++) {
            BluetoothDevice device = bluetoothAdapter.getRemoteDevice(deviceAddresses.get(i));
            connectToDevice(device, i);
        }
    }

    private void connectToDevice(BluetoothDevice device, int deviceIndex) {

        Log.e("DataReceiver", deviceIndex + " / " + socket);
        // Avoid duplicate connections
        if (connectionManager.isDeviceConnected(device)) {
            Log.d("BluetoothConnection", "Device already connected: " + device.getAddress());
            return;
        }

        new Thread(() -> {
            connectionManager.connect(device);

            socket = connectionManager.getBluetoothSocket(device);
            if (socket != null && socket.isConnected()) {
                //TextView targetTextView = getTextViewByIndex(deviceIndex);

                // Start DataReceiver with the selected TextView
                DataReceiver dataReceiver = new DataReceiver(socket, deviceIndex, uiHandler, this::handleNewData, connectionManager, this, this);

                new Thread(dataReceiver).start();
                Log.d("BluetoothConnection", "Device " + deviceIndex + " connected");
                runOnUiThread(() -> {
                    if (deviceIndex == 0) {
                        Toasty.success(Assessment.this, "Left Leg Connected", Toasty.LENGTH_SHORT).show();
                    } else {
                        Toasty.success(Assessment.this, "Right Leg Connected", Toasty.LENGTH_SHORT).show();
                    }
                });

            } else {
                Log.e("BluetoothConnection", "Failed to connect to device: " + device.getAddress());
            }
        }).start();
    }

    private void handleNewData(int deviceIndex, String value) {
//        Log.d("Unique name", "Device " + deviceIndex + " connected");

        synchronized (this) {
            try {
                if (value == null || value.trim().isEmpty()) {
//                    Log.e("Data Error", "Received empty or null value");
                    return;
                }

                String[] numbers = value.split(" ");
//                Log.e("Parsed numbers", Arrays.toString(numbers));

                if (numbers.length < 2) {
                    Log.e("Parse Error", "Invalid data format, expected at least 2 values");
                    return;
                }

                if (deviceIndex == 0) {
                    try {
                        float angle = Float.parseFloat(numbers[0]);
                        leftacclx = Double.parseDouble(numbers[1]);
                        rollleft = Double.parseDouble(numbers[2]);

                        if (angle > 180) {
                            angle = angle - 361;
                        }

                        device1queue.add(angle);
                    } catch (NumberFormatException e) {
                        Log.e("Parse Error", "Invalid number format in left device data: " + Arrays.toString(numbers), e);
                    }

                    try {
                        accldatax.put(leftacclx);
                    } catch (JSONException e) {
                        Log.e("JSON Error", "Failed to add leftacclx to JSON", e);
                    }

                    lefttotalAcceleration = Math.sqrt((leftacclx * leftacclx) + (leftaccly * leftaccly) + (leftacclz * leftacclz));
                }
                else if (deviceIndex == 1) {
                    try {
                        float angle1 = Float.parseFloat(numbers[0]);
                        rightacclx = Double.parseDouble(numbers[1]);
                        rollright = Double.parseDouble(numbers[2]);
                        if (angle1 > 180) {
                            angle1 = angle1 - 361;
                        }

                        device2queue.add(angle1);
                    } catch (NumberFormatException e) {
                        Log.e("Parse Error", "Invalid number format in right device data: " + Arrays.toString(numbers), e);
                    }

                    righttotalAcceleration = Math.sqrt((rightacclx * rightacclx) + (rightaccly * rightaccly) + (rightacclz * rightacclz));
                }

                // Process device1 queue
                if (!device1queue.isEmpty()) {
                    try {
                        float dval = device1queue.poll();
                        sensordata.add(dval);
//                        Log.e("Left Leg data", String.valueOf(dval));
                        handleDeviceData(deviceIndex, Math.round(dval));
                    } catch (Exception e) {
                        Log.e("Processing Error", "Error handling device1 data", e);
                    }
                }

                // Process device2 queue
                if (!device2queue.isEmpty()) {
                    try {
                        float dval = device2queue.poll();
                        sensordata.add(dval);
//                        Log.e("Right Leg data", String.valueOf(dval));
                        handleDeviceData(deviceIndex, Math.round(dval));
                    } catch (Exception e) {
                        Log.e("Processing Error", "Error handling device2 data", e);
                    }
                }
            } catch (Exception e) {
                Log.e("Unexpected Error", "Unhandled exception in handleNewData", e);
            }
        }
    }

    private static class DataReceiver implements Runnable {
        private final BluetoothSocket socket;
        private final int deviceIndex;
        private final Handler uiHandler;
        private final BiConsumer<Integer, String> dataCallback;
        private BluetoothConnectionManager connectionManager;
        private final Assessment outerClass;
        private final Context context;

        public DataReceiver(BluetoothSocket socket, int deviceIndex, Handler uiHandler, BiConsumer<Integer, String> dataCallback, BluetoothConnectionManager connectionManager, Assessment outerClass, Context context) {
            this.socket = socket;
            this.deviceIndex = deviceIndex;
            this.uiHandler = uiHandler;
            this.dataCallback = dataCallback;
            this.connectionManager = connectionManager;
            this.outerClass = outerClass;
            this.context = context;

        }

        @Override
        public void run() {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
//                    float data = Float.parseFloat(line.trim());
                    String data = line;
//                    Log.e("New Device Data data receiver", String.valueOf(data));

                    // Pass data and device index to the callback on the main thread
                    uiHandler.post(() -> dataCallback.accept(deviceIndex, data));
                }
            } catch (IOException e) {
                Log.e("DataReceiver", "Error: " + e.getMessage() + " / " + deviceIndex + " / " + socket);

                uiHandler.post(() -> {
                    if (deviceIndex == 0) {
                        Toasty.warning(context, "Left Leg Disconnected", Toasty.LENGTH_SHORT).show();
                    } else {
                        Toasty.warning(context, "Right Leg Disconnected", Toasty.LENGTH_SHORT).show();
                    }
                });
//                outerClass.runOnUiThread(() -> {
//                    BluetoothDevice device = socket.getRemoteDevice();
//                    connectionManager.handleDisconnection(socket);
//                    outerClass.releaseResources(socket);
//                    outerClass.reconnectToDevice(deviceIndex);
//                });
            }
        }
    }

    private void reconnectToDevice(int deviceIndex) {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        String addressesString = sharedPreferences.getString("device_addresses", null);
        ArrayList<String> deviceAddresses = addressesString != null
                ? new ArrayList<>(Arrays.asList(addressesString.split(",")))
                : new ArrayList<>();

        if (deviceIndex >= deviceAddresses.size()) {
            Log.e("Reconnection", "Invalid device index: " + deviceIndex);
            return;
        }

        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled()) {
            Log.e("Reconnection", "Bluetooth not available or not enabled");
            return;
        }

        String deviceAddress = deviceAddresses.get(deviceIndex);
        BluetoothDevice device = bluetoothAdapter.getRemoteDevice(deviceAddress);

        new Thread(() -> {
            connectionManager.connect(device);
            BluetoothSocket newSocket = connectionManager.getBluetoothSocket(device);

            if (newSocket != null && newSocket.isConnected()) {
                DataReceiver dataReceiver = new DataReceiver(newSocket, deviceIndex, uiHandler, this::handleNewData, connectionManager, this, this);
                new Thread(dataReceiver).start();
                Log.d("Reconnection", "Device " + deviceIndex + " reconnected");
            } else {
                Log.e("Reconnection", "Failed to reconnect device " + deviceIndex);
            }
        }).start();
    }

    private void releaseResources(BluetoothSocket socket) {
        try {
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            Log.e("Socket Close Error", "Failed to close socket", e);
        }
    }


    private void handleDeviceData(int deviceIndex, float value) {

        if ("Mobility Test".equalsIgnoreCase(DetailFrag_5.selectedExercise) || "Extension Lag Test".equalsIgnoreCase(DetailFrag_5.selectedExercise) || "Static Balance Test".equalsIgnoreCase(DetailFrag_5.selectedExercise) || "Proprioception Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
            // Only process data from the two target devices
            if (DetailFrag_5.selecteddeviceindex == 0 && DetailFrag_5.selecteddeviceindex == deviceIndex) {
                try {
                    DetailFrag_5.rolldataleft.put(rollleft);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                DetailFrag_5.latestValueDevice1 = value;
                if (DetailFrag_5.playflag == 1) {
                    if ("Proprioception Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
                        value = 90 - value;
                        float finalValue = value;
                        if ("left".equalsIgnoreCase(leg)) {
                            Thread speedUpdateThread = new Thread(() -> {
                                try {

                                    int finalSpeed = (int) finalValue;
//                                    Log.e("Speedometer", String.valueOf(finalSpeed));
                                    // Update the speed on the main thread
                                    runOnUiThread(() -> speedometer1.setSpeed(finalSpeed, 100L, () -> {
                                        // Callback (can be left empty or perform some action)
                                        return null;
                                    }));

                                    // Sleep for a while before updating to the next speed
                                    Thread.sleep(50); // Adjust delay as needed

                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            });
                            speedUpdateThread.start();
                        }
                    }
                    slice.add(value);
//                    Log.e("Leg Device", "Chart Data " + slice);
                    //setupArcGauge1(slice.get(0));
                    chartupdatedata(slice);
                    if ("Static balance test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
                        DetailFrag_5.leftleg.add(slice.get(0));
//                        staticbalancetest(slice);
                    } else if ("Extension Lag Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
                        extensionlagtest(slice);
                    }

                    slice.clear();
                }
//                Log.e("Leg Device", "Left Leg " + DetailFrag_5.latestValueDevice1 + " Index Selected" + DetailFrag_5.selecteddeviceindex + " Index Present " + deviceIndex);
            } else if (DetailFrag_5.selecteddeviceindex == 1 && DetailFrag_5.selecteddeviceindex == deviceIndex) {
                DetailFrag_5.latestValueDevice2 = value;
                if (DetailFrag_5.playflag == 1) {
                    if ("Proprioception Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
                        value = 90 - value;
                        float finalValue = value;

                        Thread speedUpdateThread = new Thread(() -> {
                            try {

                                int finalSpeed = (int) finalValue;
                                Log.e("Speedometer", String.valueOf(finalSpeed));
                                // Update the speed on the main thread
                                runOnUiThread(() -> speedometer2.setSpeed(finalSpeed, 100L, () -> {
                                    // Callback (can be left empty or perform some action)
                                    return null;
                                }));

                                // Sleep for a while before updating to the next speed
                                Thread.sleep(50); // Adjust delay as needed

                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        });
                        speedUpdateThread.start();
                    }
                    slice.add(value);
//                    Log.e("Leg Device", "Chart Data " + slice);
                    //setupArcGauge2(slice.get(0));
                    chartupdatedata(slice);
                    if ("Static balance test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
                        DetailFrag_5.leftleg.add(slice.get(0));
//                        staticbalancetest(slice);
                    } else if ("Extension Lag Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
                        extensionlagtest(slice);
                    }


                    slice.clear();
                }
//                Log.e("Leg Device", "Right Leg " + DetailFrag_5.latestValueDevice2 + " Index" + DetailFrag_5.selecteddeviceindex + " Index Present " + deviceIndex);
            } else {
                return; // Ignore data from other devices
            }

        }
        else {
            if (DetailFrag_5.playflag == 1) {
                if (deviceIndex == 0) {
                    DetailFrag_5.latestValueDevice1 = value;
                } else {
                    DetailFrag_5.latestValueDevice2 = value;
                }
                slice.clear();
                slice.add(DetailFrag_5.latestValueDevice1);
                slice.add(DetailFrag_5.latestValueDevice2);
                Log.e("Leg Device", "Device 1&2 " + slice);
                //setupArcGauge1(slice.get(0));
                //setupArcGauge2(slice.get(1));
                if (DetailFrag_5.countflag == 1) {
                    if (ctr == 0) {
//                    Log.e("Leg Device", "Chart Data " + slice);

                        if ("Dynamic balance test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
                            DetailFrag_5.leftleg.add(slice.get(0));
                            DetailFrag_5.rightleg.add(slice.get(1));
                            chartupdatedata(slice);
//                        dynamicbalancetest(slice);
                        }
                        else if ("Static balance test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
                            chartupdatedata(slice);
                            staticbalancetest(slice);
                        }
                        else if ("Walk and Gait Analysis".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
//                        walkgaittest(slice);
                            float angle = slice.get(0);
                            float angle1 = slice.get(1);
                            DetailFrag_5.leftlegwalk.add(angle);
                            DetailFrag_5.rightlegwalk.add(angle1);
                            if (DetailFrag_5.startTime == 0) {
                                DetailFrag_5.startTime = System.currentTimeMillis();
                            }
                            BigDecimal leftbd = new BigDecimal(leftacclx).setScale(2, RoundingMode.HALF_UP); // Round to 2 decimal places
                            double leftroundedValue = leftbd.doubleValue();
                            DetailFrag_5.leftaccl.add(leftroundedValue);
                            BigDecimal rightbd = new BigDecimal(rightacclx).setScale(2, RoundingMode.HALF_UP); // Round to 2 decimal places
                            double rightroundedValue = rightbd.doubleValue();
                            DetailFrag_5.righttaccl.add(rightroundedValue);
                            chartupdatedata(slice);

//                            float currentTimeInSeconds1 = (System.currentTimeMillis() - DetailFrag_5.startTime) / 1000.0f;
//                            long currentTimeInSeconds = (long) (currentTimeInSeconds1 * 1000);
//                            DetailFrag_5.walkgaittime.add(currentTimeInSeconds);
                        }
                        else if ("Staircase Climbing Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
                            float angle = slice.get(0);
                            float angle1 = slice.get(1);

                            DetailFrag_5.leftleg.add(angle);
                            DetailFrag_5.rightleg.add(angle1);
                            DetailFrag_5.staircasetime.add((int) System.currentTimeMillis());
                            DetailFrag_5.rolldataleft.put((int) rollleft);
                            DetailFrag_5.rolldataright.put((int) rollright);
                            Log.e("Staircase Turn data Roll Left", String.valueOf(DetailFrag_5.rolldataleft));
                            Log.e("Staircase Turn data Roll right", String.valueOf(DetailFrag_5.rolldataright));
                            Log.e("Staircase Turn data Left leg", String.valueOf( DetailFrag_5.leftleg));
                            Log.e("Staircase Turn data Right leg", String.valueOf(DetailFrag_5.rightleg));
                            chartupdatedata(slice);
//                        staircaseclimbingtest(slice);
                        }
                        ctr = 1;
                    } else {
                        ctr = 0;
                    }
                } else {
                    DetailFrag_5.countflag1++;
                    if (DetailFrag_5.countflag1 == 2) {
                        DetailFrag_5.countflag = 1;
                    }
                }
                slice.clear();
            }
        }


        // If both target values are available, pass them to walkgaittest
//        if (latestValueDevice1 != null && latestValueDevice2 != null) {
//            walkgaittest(Arrays.asList(latestValueDevice1), Arrays.asList(latestValueDevice2));
//
//            // Reset values to wait for the next pair of readings
//            latestValueDevice1 = null;
//            latestValueDevice2 = null;
//        }
    }


//    private class DataReceiver implements Runnable {
//        private InputStream inputStream;
//
//        public DataReceiver(BluetoothSocket socket) {
//            try {
//                inputStream = socket.getInputStream();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        @Override
//        public void run() {
//            byte[] buffer = new byte[1024]; // Buffer for incoming data
//            int bytes;
//
//            while (true) {
//                try {
//                    // Read data from the input stream
//                    bytes = inputStream.read(buffer);
//                    String receivedData = new String(buffer, 0, bytes);
//                    onReceive(receivedData); // Pass the received data to the onReceive function
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    break; // Exit the loop if there is an error
//                }
//            }
//        }
//    }

//    private static class DataReceiver implements Runnable {
//        private final BluetoothSocket socket;
//        private final Handler uiHandler;
//        private final TextView deviceTextView;
//
//        public DataReceiver(BluetoothSocket socket, TextView deviceTextView, Handler uiHandler) {
//            this.socket = socket;
//            this.deviceTextView = deviceTextView;
//            this.uiHandler = uiHandler;
//        }
//
//        @Override
//        public void run() {
//            try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
//                String line;
//                while (!Thread.currentThread().isInterrupted() && (line = reader.readLine()) != null) {
//                    final String data = line.trim();
//
//                    // Update the TextView on the main thread
//                    uiHandler.post(() -> deviceTextView.setText(data));
//                    Log.d("BluetoothData", "Data received: " + data);
//                }
//            } catch (IOException e) {
//                Log.e("From Data Receiver", "Error in DataReceiver: " + e.getMessage());
//            }
//        }
//    }

//    private void onReceive(String data) {
//        // Similar to readDataFromDevice, we need to handle receiving data in a continuous manner
//        new Thread(() -> {
//            List<Float> lastTenValues = new ArrayList<>();
//
//            try {
//                InputStream inputStream = bluetoothSocket.getInputStream();
//                byte[] buffer = new byte[1024];
//                int bytes;
//
//                while ((bytes = inputStream.read(buffer)) > 0) {
//                    String receivedData = new String(buffer, 0, bytes).trim();  // Trim to remove leading/trailing spaces
//
//                    // Ensure UI updates are done on the main thread
//                    runOnUiThread(() -> {
//                        if (!receivedData.isEmpty()) {  // Check for an empty string after trimming
//                            Log.d("OtherFragment", "Received data: " + receivedData);
//                            List<Float> slice = new ArrayList<>();
//
//                            try {
//                                float value = Float.parseFloat(receivedData);  // Try parsing the data
//                                lastTenValues.add(value);  // Add to the last 10 values list
//                                Log.e("LastenValues length", String.valueOf(lastTenValues));
//                            } catch (NumberFormatException e) {
//                                Log.e("OtherFragment", "Invalid float received: " + receivedData, e);
//                            }
//
//                            // If we have 10 values, calculate the average
//                            if (lastTenValues.size() == 10) {
//                                float sum = 0;
//                                for (float v : lastTenValues) {
//                                    sum += v;
//                                }
//                                float average = sum / 10;
//
//                                // Round the average to 2 decimal places
//                                float roundedAverage = Math.round(average * 100.0f) / 100.0f;
//
//                                // Add the rounded average to the slice
//                                slice.add(roundedAverage);
//
//                                // Clear the lastTenValues list for the next set of 10 values
//                                lastTenValues.clear();
//
//                                Log.e("DiagnoSlice", "Average of last 10 values: " + roundedAverage);
//                                Log.e("Playflag", String.valueOf(DetailFrag_5.playflag));
//                                if (DetailFrag_5.playflag == 1) {
//                                    chartupdatedata(slice);
//                                    if ("Dynamic balance test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
//                                        dynamicbalancetest(slice);
//                                    } else if ("Static balance test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
//                                        staticbalancetest(slice);
//                                    } else if ("Staircase Climbing Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
////                                        staircaseclimbingtest(slice);
//                                    } else {
//                                        if ("Walk and Gait Analysis".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
//                                            //walkgaittest(slice);
//                                        }
//                                    }
//                                }
//                            }
//                        }
//
//                        ScrollView scrollView = findViewById(R.id.data_scroll_view);
//                        scrollView.post(() -> scrollView.fullScroll(View.FOCUS_DOWN));
//                    });
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//                runOnUiThread(() -> Toast.makeText(Assessment.this, "Error reading data: " + e.getMessage(), Toast.LENGTH_SHORT).show());
//            }
//        }).start();
//    }

//    private void readDataFromDevice(BluetoothSocket socket) {
//        new Thread(() -> {
//            try {
//                InputStream inputStream = socket.getInputStream();
//                byte[] buffer = new byte[1024];
//                int bytes;
//
//                List<Float> lastTenValues = new ArrayList<>();
//
//                while ((bytes = inputStream.read(buffer)) > 0) {
//                    String receivedData = new String(buffer, 0, bytes).trim();  // Trim to remove leading/trailing spaces
//                    runOnUiThread(() -> {
//                        if (!receivedData.isEmpty()) {  // Check for an empty string after trimming
//                            Log.d("OtherFragment", "Received data: " + receivedData);
//                            List<Float> slice = new ArrayList<>();
//
//                            try {
//                                float value = Float.parseFloat(receivedData);  // Try parsing the data
//                                lastTenValues.add(value);  // Add to the last 10 values list
//                            } catch (NumberFormatException e) {
//                                Log.e("OtherFragment", "Invalid float received: " + receivedData, e);
//                            }
//
//                            // If we have 10 values, calculate the average
//                            if (lastTenValues.size() == 10) {
//                                float sum = 0;
//                                for (float v : lastTenValues) {
//                                    sum += v;
//                                }
//                                float average = sum / 10;
//
//                                // Round the average to 2 decimal places
//                                float roundedAverage = Math.round(average * 100.0f) / 100.0f;
//
//                                // Add the rounded average to the slice
//                                slice.add(roundedAverage);
//
//                                // Clear the lastTenValues list for the next set of 10 values
//                                lastTenValues.clear();
//
//                                Log.e("DiagnoSlice", "Average of last 10 values: " + roundedAverage);
//                                chartupdatedata(slice);
//                            }
//
//
//                        }
//
//                        ScrollView scrollView = findViewById(R.id.data_scroll_view);
//                        scrollView.post(() -> scrollView.fullScroll(View.FOCUS_DOWN));
//                    });
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//                runOnUiThread(() -> Toast.makeText(Assessment.this, "Error reading data: " + e.getMessage(), Toast.LENGTH_SHORT).show());
//            }
//        }).start();
//    }

    private void chartupdatedata(List<Float> data) {
        String selectedItems = "selected_exercise"; // Replace with actual selected items
        if (!"".equals(selectedItems) && DetailFrag_5.playflag == 1) {

            isPlaying = true;
            slice1.clear();
            slice2.clear();
            if (data.size() == 2) {
                slice1.add(data.get(0));
                slice2.add(data.get(1));
                updateChartData(slice1, slice2);
            } else {
                slice1.add(data.get(0));
                slice2 = new ArrayList<>();
//                Log.e("Leg Device", "Device 1 " + slice1 + " Device 2 " + slice2);
                updateChartData(slice1, slice2);
            }
        }
//        else {
//            Toasty.warning(getContext(), "Select at least one exercise to proceed", Toast.LENGTH_SHORT, true).show();
//        }

    }

    private void toggleTimerView() {
        // Hide NumberPickers and show TextViews


        // Get the values from the NumberPickers
        int minutes = minutesPicker.getValue();
        int seconds = secondsPicker.getValue();

        // Set the total time in milliseconds
        totalTimeInMillis = (minutes * 60L + seconds) * 1000;

        // Start the timer
        startTimer();
    }

    // Start the countdown timer
    private void startTimer() {

        DetailFrag_5.countflag = 0;
        DetailFrag_5.countflag1 = 0;

        DetailFrag_5.ascentct=0;
        DetailFrag_5.descentct=0;

        DetailFrag_5.extnflag1 = 0;
        DetailFrag_5.chartstarttime = 0;
        DetailFrag_5.timestamps.clear();
        leftstepflag = 0;
        rightstepflag = 0;

        DetailFrag_5.rolldataleft = new JSONArray();
        DetailFrag_5.rolldataright = new JSONArray();

        accldatax = new JSONArray();
        accldatay = new JSONArray();
        accldataz = new JSONArray();


        isPlaying = true;
        ascentflag = 1;
        descentflag = 0;
        accldata = new JSONArray();
        DetailFrag_5.leftaccl.clear();
        DetailFrag_5.righttaccl.clear();
//        gaugeView1.clearAnimation();  // Replace with your gauge reset logic
//        gaugeView2.clearAnimation();

//        if ("Mobility Test".equalsIgnoreCase(DetailFrag_5.selectedExercise) || "Proprioception Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
//            int minutes = minutesPicker.getValue();
//            int seconds = secondsPicker.getValue();
//
//            // Set the total time in milliseconds
//            totalTimeInMillis = (minutes * 60L + seconds) * 1000;
//            pickerContainer.setVisibility(View.GONE);
//            minutesPicker.setVisibility(View.INVISIBLE);
//            secondsPicker.setVisibility(View.INVISIBLE);
//        }

        textContainer.setVisibility(View.VISIBLE);
        minutesText.setVisibility(View.VISIBLE);
        secondsText.setVisibility(View.VISIBLE);

        DetailFrag_5.postexevalues = new JSONArray();
        if (!"Proprioception Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
            DetailFrag_5.postexeparameters = new JSONArray();
        }

        slice.clear();

        DetailFrag_5.walkgaitleftlegangles.clear();
        DetailFrag_5.walkgaitrightlegangles.clear();

        // Reset progress bar
        progressBar.setProgress(0);
        DetailFrag_5.playflag = 1;
        DetailFrag_5.datareportarray = new JSONArray();
        DetailFrag_5.analysereportarray = new JSONArray();
        DetailFrag_5.reportobject = new JSONObject();
        DetailFrag_5.reportarray = new JSONArray();
//        Log.e("HighlightArray ", String.valueOf(DetailFrag_5.lineData.getDataSetByIndex(0)));
        currentMetricIndex = 0;
        DetailFrag_5.lineData.clearValues();
        isTimerRunning = true;
        DetailFrag_5.entries.clear();
        DetailFrag_5.entries1.clear();
        DetailFrag_5.lineChart.clear();
        DetailFrag_5.counter = -1;
        DetailFrag_5.metricArray.clear();
        DetailFrag_5.metricArray1.clear();
        DetailFrag_5.stackedMetricsArray.clear();
        DetailFrag_5.elapsedTime = -1;
        DetailFrag_5.flexionCycles = 0;
        DetailFrag_5.extensionCycles = 0;
        flexionCycles1 = 0;
        extensionCycles1 = 0;
        DetailFrag_5.totalCycles = 0;
        totalCycles1 = 0;
        DetailFrag_5.cycleCount = 1;
        DetailFrag_5.cycleCount1 = 1;
        DetailFrag_5.cycleCount2 = 1;
        angles.clear();
        angles1.clear();
        DetailFrag_5.times.clear();
        DetailFrag_5.times1.clear();
        DetailFrag_5.startTime = 0;
        DetailFrag_5.endTime = 0;
        DetailFrag_5.stepCount = 0;
        DetailFrag_5.leftrom.clear();
        DetailFrag_5.rightrom.clear();
        DetailFrag_5.totalDistance = 0;
        DetailFrag_5.stepCountwalk = 0;
        DetailFrag_5.activeTime = 0;
        DetailFrag_5.avgStandtime = 0;
        DetailFrag_5.avgSwingtime = 0;
        DetailFrag_5.avgStancetime = 0;
        DetailFrag_5.strideLength = 0;
        DetailFrag_5.meanVelocity = 0;
        DetailFrag_5.cadence = 0;
        DetailFrag_5.leftlegwos.clear();
        DetailFrag_5.leftlegws.clear();
        DetailFrag_5.rightwos.clear();
        DetailFrag_5.rightws.clear();
        DetailFrag_5.staticbalanceangles.clear();
        DetailFrag_5.rightleg.clear();
        DetailFrag_5.leftleg.clear();
        angles.clear();
        angles1.clear();
        DetailFrag_5.totalBreakTime = 0;
        DetailFrag_5.walkgaitswingtime.clear();

        DetailFrag_5.ascentstart = 0;
        DetailFrag_5.ascentend = 0;
        DetailFrag_5.turnstart = 0;
        DetailFrag_5.turnend = 0;
        DetailFrag_5.descentstart = 0;
        DetailFrag_5.descentend = 0;
        DetailFrag_5.staircasetime.clear();
        DetailFrag_5.turnflag = 0;
        DetailFrag_5.descentflag = 0;
        DetailFrag_5.stepCount = 0;
        DetailFrag_5.consecutiveNoChangeCount = 0;
        DetailFrag_5.isInBreak = false;
        DetailFrag_5.breakStartTime = 0;
        DetailFrag_5.totalstancepahse = 0;
        DetailFrag_5.breakCount = 0;
        DetailFrag_5.totalBreakTime = 0;
        DetailFrag_5.walkgaitswingtime.clear();
        DetailFrag_5.walkgaittime.clear();

        DetailFrag_5.tempRow1.clear();
        DetailFrag_5.tempRow.clear();

        DetailFrag_5.activestarttime = System.currentTimeMillis();

        DetailFrag_5.rightcyclewalkgati = 0;
        DetailFrag_5.leftcyclewalkgati = 0;

        rightswingtime.clear();
        leftswingtime.clear();
        stance.clear();
        leftstride.clear();
        rightstride.clear();
        leftstrideper.clear();
        rightstrideper.clear();
        step.clear();


        DetailFrag_5.extnpassivemax = 361;
        DetailFrag_5.extnactivemax = 361;
        DetailFrag_5.extndens = 0;
        DetailFrag_5.extnflag = 0;


        if (!"Walk and Gait Analysis".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
            DetailFrag_5.cycle = 0;
        }
        DetailFrag_5.leftlegwalk.clear();
        DetailFrag_5.rightlegwalk.clear();

        if ("Mobility Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
            //DetailFrag_5.exerciseCycleAssessment.clear();
            DetailFrag_5.activeeds.clear();
            DetailFrag_5.passiveeds.clear();
            //DetailFrag_5.indiviCardAdapteract.notifyDataSetChanged();

        }
        if ("Proprioception Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
//            DetailFrag_5.exerciseListact.clear();
//            DetailFrag_5.indiviCardAdapterpass.notifyDataSetChanged();
//            DetailFrag_5.mainreportobject = new JSONObject();
            DetailFrag_5.objectElements1.clear();
            DetailFrag_5.objectElements.clear();
        }

//        Log.e("Times Array", String.valueOf(DetailFrag_5.times));
        if ("Extension Lag Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
            assess_cycles_passive.setVisibility(View.VISIBLE);
//            assess_cycles_active.setVisibility(View.VISIBLE);
//            if ("active".equalsIgnoreCase(activepassive)) {
//                DetailFrag_5.highlightArrayact.clear();
//                DetailFrag_5.exerciseListact.clear();
//                DetailFrag_5.activeeds.clear();
//            } else {
//                DetailFrag_5.highlightArraypass.clear();
//                DetailFrag_5.exerciseListpass.clear();
//                DetailFrag_5.passiveeds.clear();
//            }
        } else {
            assess_cycles_active.setVisibility(View.VISIBLE);
            assess_cycles_passive.setVisibility(View.GONE);
            DetailFrag_5.highlightArrayact.clear();
            DetailFrag_5.highlightArraypass.clear();
        }
        DetailFrag_5.objectElements.clear();
        DetailFrag_5.counter = -1;
        isSecondValueReceived = false;
        DetailFrag_5.prevSignChange1 = 0;
        DetailFrag_5.prevSignChange = 0;
        DetailFrag_5.indiviflexionVelocities.clear();
        DetailFrag_5.indiviextensionVelocities.clear();
        if (!"Proprioception Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
            DetailFrag_5.indivimaxAngle.clear();
            DetailFrag_5.indiviminAngle.clear();
            DetailFrag_5.indivipain.clear();
        }
        DetailFrag_5.isTesting = true;
//        DetailFrag_5.dataSet.clear();

        exerom = 0;
        DetailFrag_5.exevalue = new JSONArray();
        DetailFrag_5.exepain = new JSONArray();
        DetailFrag_5.subexedata = new JSONObject();
        resetVariables();

//        if("Mobility".equalsIgnoreCase(DetailFrag_5.selectedExercise)){
//            postNumbersWithDelay();
//        }

//        if ("Mobility Test".equalsIgnoreCase(DetailFrag_5.selectedExercise) || "Proprioception Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
//            //int totalMillis = (m * 60 + s) * 1000;
//            // Create and start the countdown timer
//            countDownTimer = new CountDownTimer(totalTimeInMillis, 1000) {
//                @Override
//                public void onTick(long millisUntilFinished) {
//                    // Update the remaining time
//                    int minutes = (int) (millisUntilFinished / 1000) / 60;
//                    int seconds = (int) (millisUntilFinished / 1000) % 60;
//                    DetailFrag_5.sec = seconds;
//
//                    // Display the updated time in TextViews
//                    minutesText.setText(String.format("%02d", minutes));
//                    secondsText.setText(String.format("%02d", seconds));
//
//                    // Calculate the progress and update the ProgressBar
//                    int progress = (int) ((totalTimeInMillis - millisUntilFinished) * 100 / totalTimeInMillis);
//                    progressBar.setProgress(progress);
//
//                }
//
//                @Override
//                public void onFinish() {
//                    // When the timer finishes, reset TextViews and ProgressBar
//
//
////                isTimerRunning = false;
////                // Reset play/pause button icon to play
////                ImageView centerButton = findViewById(R.id.center_button);
////                centerButton.setImageResource(R.drawable.baseline_play_arrow_24);
//                    stopTimer();
//                }
//            }.start();
//            updateprogress(progressBar);
//        }

        DetailFrag_5.isTimerRunning = true;
        DetailFrag_5.startTimegait = System.currentTimeMillis(); // Record the start time
        handler.post(runnable);

//        if (!"Mobility Test".equalsIgnoreCase(DetailFrag_5.selectedExercise) && !"Proprioception Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
//            handler.post(runnable);
//        }


        // Optionally animate the progress bar

    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            minutes = 0;
            seconds = 0;

            if (isTimerRunning) {
                // Calculate the elapsed time in milliseconds
                long elapsedMillis = System.currentTimeMillis() - DetailFrag_5.startTimegait;

                // Convert milliseconds to minutes, seconds, and remaining milliseconds
                minutes = (int) (elapsedMillis / 1000) / 60;
                seconds = (int) (elapsedMillis / 1000) % 60;
                DetailFrag_5.milliseconds = (int) (elapsedMillis % 1000);
                DetailFrag_5.sec = seconds;

                // Update TextViews for minutes, seconds, and milliseconds
                minutesText.setText(String.format("%02d", minutes));
                secondsText.setText(String.format("%02d", seconds));
                //millisecondsText.setText(String.format("%03d", milliseconds));

                // Calculate and update the ProgressBar (optional)
//                int progress = (int) (elapsedMillis % 1000) / 10; // Scaled to 0-100
//                progressBar.setProgress(progress);

                // Repeat every few milliseconds to update frequently
                handler.postDelayed(this, 10); // Update every 10 milliseconds for smoother display
            } else {
                DetailFrag_5.duration = minutes * seconds;
            }
        }
    };

    private void updateprogress(ProgressBar progressBar1) {
        if ("Mobility Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
            ObjectAnimator objectAnimator = ObjectAnimator.ofInt(progressBar1, "progress", progressBar1.getProgress(), 100)
                    .setDuration(totalTimeInMillis);
            objectAnimator.start();
        }
    }

    // Pause the timer
    private void pauseTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    private void stopTimer() {

//        Log.e("Graph Entries", String.valueOf(DetailFrag_5.entries));

        DetailFrag_5.cyclecount++;
        isPlaying = false;
        DetailFrag_5.activeendtime = System.currentTimeMillis();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
//        if ("Mobility Test".equalsIgnoreCase(DetailFrag_5.selectedExercise) || "Proprioception Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
//            minutesPicker.setVisibility(View.VISIBLE);
//            secondsPicker.setVisibility(View.VISIBLE);
//            pickerContainer.setVisibility(View.VISIBLE);
//            minutesText.setVisibility(View.INVISIBLE);
//            secondsText.setVisibility(View.INVISIBLE);
//            textContainer.setVisibility(View.GONE);
//        } else {
//            minutesText.setVisibility(View.VISIBLE);
//            secondsText.setVisibility(View.VISIBLE);
//            textContainer.setVisibility(View.VISIBLE);
//        }

        minutesText.setVisibility(View.VISIBLE);
        secondsText.setVisibility(View.VISIBLE);
        textContainer.setVisibility(View.VISIBLE);
        center_button.setImageResource(R.drawable.baseline_play_arrow_24); // Change icon to play
        minutesText.setText("00");
        secondsText.setText("00");


        Log.e("Test is not active", String.valueOf(DetailFrag_5.duration));
        progressBar.setProgress(100); // Set progress to max when finished
        updateprogress(progressBar);
        DetailFrag_5.playflag = 0;
        totaltime = 0;
        isTimerRunning = false;
        m = 0;
        s = 0;
        DetailFrag_5.isTesting = false;
//        java.util.Date endDateTime = new Date();
//        String endDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(endDateTime);
//        String endTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(endDateTime);


        if (angles.size() == 0 && angles1.size() == 0) {
            Toasty.error(Assessment.this, "No Values found", Toasty.LENGTH_SHORT).show();
        } else {

            if ("Proprioception Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
                Log.e("Proprioception data", String.valueOf(DetailFrag_5.objectElements));
                analyzeJointData(angles, DetailFrag_5.times.size(), 0);
                //analyzeJointData(angles1, DetailFrag_5.times1.size(), 1);
                proprioceptiontest(DetailFrag_5.objectElements);

            }
            else if ("Static Balance Test".equalsIgnoreCase(DetailFrag_5.selectedExercise) || "Staircase Climbing Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
                staticbalancetest(DetailFrag_5.leftleg);
                handleTimerStop();
            }
            else if ("Extension Lag Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
                handleTimerStop();
            }
            else if ("Walk and Gait analysis".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
                handleTimerStop();
            }
            else {
                analyzeJointData(angles, DetailFrag_5.times.size(), 2);
                handleTimerStop();
            }

            isPlaying = false;
            DetailFrag_5.playflag = 0;


            if ("Extension Lag Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
//                assess_cycles_active.setVisibility(View.VISIBLE);
                assess_cycles_passive.setVisibility(View.VISIBLE);


                Log.e("Inbasekar Extension Lag tes", DetailFrag_5.extnactivemax + " / " + DetailFrag_5.extnpassivemax + " / " + DetailFrag_5.extnactivemax + DetailFrag_5.extnpassivemax);
                DetailFrag_5.extensionlagCycleAssessments.clear();
                DetailFrag_5.extensionlagAdapter.notifyDataSetChanged();
                if ("left".equalsIgnoreCase(leg)) {
                    if (DetailFrag_5.extensionlagCycleAssessmentsleft.size() > 0) {
                        for (int i = 0; i < DetailFrag_5.extensionlagCycleAssessmentsleft.size(); i++) {
                            DetailFrag_5.extensionlagCycleAssessments.add(new ExtensionlagCycleAssessment(DetailFrag_5.extensionlagCycleAssessmentsleft.get(i).getActiveed(), DetailFrag_5.extensionlagCycleAssessmentsleft.get(i).getPassiveed(), DetailFrag_5.extensionlagCycleAssessmentsleft.get(i).getTotaled()));
                        }
                        DetailFrag_5.extensionlagCycleAssessments.add(new ExtensionlagCycleAssessment(DetailFrag_5.extnactivemax, DetailFrag_5.extnpassivemax, DetailFrag_5.extnactivemax + DetailFrag_5.extnpassivemax));
                        DetailFrag_5.extensionlagCycleAssessmentsleft.add(new ExtensionlagCycleAssessment(DetailFrag_5.extnactivemax, DetailFrag_5.extnpassivemax, DetailFrag_5.extnactivemax + DetailFrag_5.extnpassivemax));
                    } else {
                        DetailFrag_5.extensionlagCycleAssessments.add(new ExtensionlagCycleAssessment(DetailFrag_5.extnactivemax, DetailFrag_5.extnpassivemax, DetailFrag_5.extnactivemax + DetailFrag_5.extnpassivemax));
                        DetailFrag_5.extensionlagCycleAssessmentsleft.add(new ExtensionlagCycleAssessment(DetailFrag_5.extnactivemax, DetailFrag_5.extnpassivemax, DetailFrag_5.extnactivemax + DetailFrag_5.extnpassivemax));
                    }

                } else {
                    if (DetailFrag_5.extensionlagCycleAssessmentsright.size() > 0) {
                        for (int i = 0; i < DetailFrag_5.extensionlagCycleAssessmentsright.size(); i++) {
                            DetailFrag_5.extensionlagCycleAssessments.add(new ExtensionlagCycleAssessment(DetailFrag_5.extensionlagCycleAssessmentsright.get(i).getActiveed(), DetailFrag_5.extensionlagCycleAssessmentsright.get(i).getPassiveed(), DetailFrag_5.extensionlagCycleAssessmentsright.get(i).getTotaled()));
                        }
                        DetailFrag_5.extensionlagCycleAssessments.add(new ExtensionlagCycleAssessment(DetailFrag_5.extnactivemax, DetailFrag_5.extnpassivemax, DetailFrag_5.extnactivemax + DetailFrag_5.extnpassivemax));
                        DetailFrag_5.extensionlagCycleAssessmentsright.add(new ExtensionlagCycleAssessment(DetailFrag_5.extnactivemax, DetailFrag_5.extnpassivemax, DetailFrag_5.extnactivemax + DetailFrag_5.extnpassivemax));
                    } else {
                        DetailFrag_5.extensionlagCycleAssessments.add(new ExtensionlagCycleAssessment(DetailFrag_5.extnactivemax, DetailFrag_5.extnpassivemax, DetailFrag_5.extnactivemax + DetailFrag_5.extnpassivemax));
                        DetailFrag_5.extensionlagCycleAssessmentsright.add(new ExtensionlagCycleAssessment(DetailFrag_5.extnactivemax, DetailFrag_5.extnpassivemax, DetailFrag_5.extnactivemax + DetailFrag_5.extnpassivemax));
                    }
                }
                DetailFrag_5.extensionlagAdapter.notifyDataSetChanged();
                DetailFrag_5.extnangle = 0;
                DetailFrag_5.extnangle1 = 0;
//                DetailFrag_5.indiviCardAdapterpass.notifyDataSetChanged();
//                DetailFrag_5.extensionlagAdapter = new ExtensionlagAdapter(DetailFrag_5.extensionlagCycleAssessments,this);
//                assess_cycles_passive.setAdapter(DetailFrag_5.extensionlagAdapter);
//                assess_cycles_passive.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
//                if ("passive".equalsIgnoreCase(activepassive)) {
//                    for (int i = 0; i < DetailFrag_5.passiveeds.size(); i++) {
//                        int val = Math.round(DetailFrag_5.passiveeds.get(i));
//                        DetailFrag_5.exerciseListpass.add(new ExerciseCycleAssessment(val));
//                    }
////                DetailFrag_5.indiviCardAdapterpass.notifyDataSetChanged();
//                    DetailFrag_5.indiviCardAdapterpass = new AssessmentCycleAdapter(this, DetailFrag_5.exerciseListpass, this, activepassive);
//                    assess_cycles_passive.setAdapter(DetailFrag_5.indiviCardAdapterpass);
//                    assess_cycles_passive.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
//
//
//                }
//                else {
//                    for (int i = 0; i < DetailFrag_5.activeeds.size(); i++) {
//                        int val = Math.round(DetailFrag_5.activeeds.get(i));
//                        DetailFrag_5.exerciseListact.add(new ExerciseCycleAssessment(val));
//                    }
////                DetailFrag_5.indiviCardAdapteract.notifyDataSetChanged();
//
//
//                    DetailFrag_5.indiviCardAdapteract = new ActiveAssessmentCycleAdapter(this, DetailFrag_5.exerciseListact, this, activepassive);
//                    assess_cycles_active.setAdapter(DetailFrag_5.indiviCardAdapteract);
//                    assess_cycles_active.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
//                }
            }
            else if ("Dynamic Balance Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {

                dynamicbalancetest(DetailFrag_5.leftleg, DetailFrag_5.rightleg);

                assess_cycles_active.setVisibility(View.VISIBLE);

                if ("active".equalsIgnoreCase(activepassive)) {
                    DetailFrag_5.postexevalues = new JSONArray();
                    for (int i = 0; i < DetailFrag_5.leftleg.size(); i++) {
                        DetailFrag_5.postexevalues.put(DetailFrag_5.leftleg.get(i));
                    }
//                    DetailFrag_5.postexeparameters.put(Math.abs(DetailFrag_5.sittostand));
//                    DetailFrag_5.postexeparameters.put(Math.abs(DetailFrag_5.sitToStandStartTime - DetailFrag_5.standToShiftStartTime));
//                    DetailFrag_5.postexeparameters.put(Math.abs(DetailFrag_5.walkEndTime - DetailFrag_5.walkStartTime));
                    DetailFrag_5.postexeparameters = new JSONArray();
                    try {
                        DetailFrag_5.postexeparameters.put(Math.abs(DetailFrag_5.sittostand));
                        DetailFrag_5.postexeparameters.put(Math.abs(DetailFrag_5.standtosit));
                        DetailFrag_5.postexeparameters.put(Math.abs(DetailFrag_5.walktime));
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }


                    DetailFrag_5.postsubdata.put(DetailFrag_5.postexevalues);
                    DetailFrag_5.postsubdata.put(DetailFrag_5.postexeparameters);
                    DetailFrag_5.postexevalues = new JSONArray();
                    DetailFrag_5.postexeparameters = new JSONArray();
                    try {
                        DetailFrag_5.postexesubdata.put("left-leg-wos-" + DetailFrag_5.cyclecount, DetailFrag_5.postsubdata);
                        DetailFrag_5.postsubdata = new JSONArray();
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
//                    Log.e("The posting dynamic balance data left", String.valueOf(DetailFrag_5.postexesubdata));
                    DetailFrag_5.postsubdata = new JSONArray();


                    for (int i = 0; i < DetailFrag_5.rightleg.size(); i++) {
                        DetailFrag_5.postexevalues.put(DetailFrag_5.rightleg.get(i));
                    }
//                    DetailFrag_5.postexeparameters.put(Math.abs(DetailFrag_5.sittostand));
//                    DetailFrag_5.postexeparameters.put(Math.abs(DetailFrag_5.sitToStandStartTime - DetailFrag_5.standToShiftStartTime));
//                    DetailFrag_5.postexeparameters.put(Math.abs(DetailFrag_5.walkEndTime - DetailFrag_5.walkStartTime));

                    DetailFrag_5.postexeparameters = new JSONArray();
                    try {
                        DetailFrag_5.postexeparameters.put(Math.abs(DetailFrag_5.sittostand));
                        DetailFrag_5.postexeparameters.put(Math.abs(DetailFrag_5.standtosit));
                        DetailFrag_5.postexeparameters.put(Math.abs(DetailFrag_5.walktime));
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }


                    DetailFrag_5.postsubdata.put(DetailFrag_5.postexevalues);
                    DetailFrag_5.postsubdata.put(DetailFrag_5.postexeparameters);
                    DetailFrag_5.postexevalues = new JSONArray();
                    DetailFrag_5.postexeparameters = new JSONArray();
                    try {
                        DetailFrag_5.postexesubdata.put("right-leg-wos-" + DetailFrag_5.cyclecount, DetailFrag_5.postsubdata);
                        DetailFrag_5.postsubdata = new JSONArray();
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
//                    Log.e("The posting dynamic balance data right", String.valueOf(DetailFrag_5.postexesubdata));
                    DetailFrag_5.postsubdata = new JSONArray();
                } else {
                    DetailFrag_5.postexevalues = new JSONArray();
                    for (int i = 0; i < DetailFrag_5.leftleg.size(); i++) {
                        DetailFrag_5.postexevalues.put(DetailFrag_5.leftleg.get(i));
                    }
//                    DetailFrag_5.postexeparameters.put(Math.abs(DetailFrag_5.sittostand));
//                    DetailFrag_5.postexeparameters.put(Math.abs(DetailFrag_5.sitToStandStartTime - DetailFrag_5.standToShiftStartTime));
//                    DetailFrag_5.postexeparameters.put(Math.abs(DetailFrag_5.walkEndTime - DetailFrag_5.walkStartTime));

                    DetailFrag_5.postexeparameters = new JSONArray();
                    try {
                        DetailFrag_5.postexeparameters.put(Math.abs(DetailFrag_5.sittostand));
                        DetailFrag_5.postexeparameters.put(Math.abs(DetailFrag_5.standtosit));
                        DetailFrag_5.postexeparameters.put(Math.abs(DetailFrag_5.walktime));
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }


                    DetailFrag_5.postsubdata.put(DetailFrag_5.postexevalues);
                    DetailFrag_5.postsubdata.put(DetailFrag_5.postexeparameters);
                    DetailFrag_5.postexevalues = new JSONArray();
                    DetailFrag_5.postexeparameters = new JSONArray();
                    try {
                        DetailFrag_5.postexesubdata.put("left-leg-ws-" + DetailFrag_5.cyclecount, DetailFrag_5.postsubdata);
                        DetailFrag_5.postsubdata = new JSONArray();
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    DetailFrag_5.postsubdata = new JSONArray();


                    for (int i = 0; i < DetailFrag_5.rightleg.size(); i++) {
                        DetailFrag_5.postexevalues.put(DetailFrag_5.rightleg.get(i));
                    }
//                    DetailFrag_5.postexeparameters.put(Math.abs(DetailFrag_5.sittostand));
//                    DetailFrag_5.postexeparameters.put(Math.abs(DetailFrag_5.sitToStandStartTime - DetailFrag_5.standToShiftStartTime));
//                    DetailFrag_5.postexeparameters.put(Math.abs(DetailFrag_5.walkEndTime - DetailFrag_5.walkStartTime));

                    DetailFrag_5.postexeparameters = new JSONArray();
                    try {
                        DetailFrag_5.postexeparameters.put(Math.abs(DetailFrag_5.sittostand));
                        DetailFrag_5.postexeparameters.put(Math.abs(DetailFrag_5.standtosit));
                        DetailFrag_5.postexeparameters.put(Math.abs(DetailFrag_5.walktime));
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }


                    DetailFrag_5.postsubdata.put(DetailFrag_5.postexevalues);
                    DetailFrag_5.postsubdata.put(DetailFrag_5.postexeparameters);
                    DetailFrag_5.postexevalues = new JSONArray();
                    DetailFrag_5.postexeparameters = new JSONArray();
                    try {
                        DetailFrag_5.postexesubdata.put("right-leg-ws-" + DetailFrag_5.cyclecount, DetailFrag_5.postsubdata);
                        DetailFrag_5.postsubdata = new JSONArray();
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    DetailFrag_5.postsubdata = new JSONArray();
                }

                assess_cycles_active.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
                DetailFrag_5.dynamicbalancetestdata.add(new Dynamicbalancetestdata((float) Math.abs(DetailFrag_5.sittostand), (float) Math.abs(DetailFrag_5.standtosit), (float) Math.abs(DetailFrag_5.walktime), activepassive));
//                long avgtime = (Math.abs(DetailFrag_5.sittostand) + (Math.abs(DetailFrag_5.standtosit)) + Math.abs(DetailFrag_5.walktime)) / 3;
//                if (maxtime < avgtime) {
//                    maxtime = avgtime;
//                    sitstandtime = Math.abs(DetailFrag_5.sittostand);
//                    standshifttime = Math.abs(DetailFrag_5.standtosit);
//                    walkt = Math.abs(DetailFrag_5.walktime);
//                    cycleno = DetailFrag_5.dynamicbalancetestdata.size();
//                }

                walkstarted = 0;
                standtoshift = 0;

                DetailFrag_5.dynamicbalanceadapter.notifyDataSetChanged();


            }
            else if ("Static Balance Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {

                assess_cycles_active.setVisibility(View.VISIBLE);


                if ("active".equalsIgnoreCase(activepassive)) {
                    for (int i = 0; i < DetailFrag_5.staticbalanceangles.size(); i++) {
                        DetailFrag_5.postexevalues.put(DetailFrag_5.staticbalanceangles.get(i));
                    }
                    DetailFrag_5.postexeparameters = new JSONArray();
                    try {
                        DetailFrag_5.postexeparameters.put(Math.round((DetailFrag_5.endTime / 1000.0) * 100.0) / 100.0);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                    DetailFrag_5.postsubdata.put(DetailFrag_5.postexevalues);
                    DetailFrag_5.postsubdata.put(DetailFrag_5.postexeparameters);
                    DetailFrag_5.postexevalues = new JSONArray();
                    DetailFrag_5.postexeparameters = new JSONArray();
                    if ("left".equalsIgnoreCase(leg)) {
                        try {
                            DetailFrag_5.staticbalelo++;
                            DetailFrag_5.postexesubdata.put("left-leg-eyes-open-" + DetailFrag_5.staticbalelo, DetailFrag_5.postsubdata);
                            DetailFrag_5.postsubdata = new JSONArray();
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        try {
                            DetailFrag_5.staticbalero++;
                            DetailFrag_5.postexesubdata.put("right-leg-eyes-open-" + DetailFrag_5.staticbalero, DetailFrag_5.postsubdata);
                            DetailFrag_5.postsubdata = new JSONArray();
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    DetailFrag_5.postsubdata = new JSONArray();

                } else {
                    for (int i = 0; i < DetailFrag_5.staticbalanceangles.size(); i++) {
                        DetailFrag_5.postexevalues.put(DetailFrag_5.staticbalanceangles.get(i));
                    }
                    DetailFrag_5.postexeparameters = new JSONArray();
                    try {
                        DetailFrag_5.postexeparameters.put(Math.round((DetailFrag_5.endTime / 1000.0) * 100.0) / 100.0);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                    DetailFrag_5.postsubdata.put(DetailFrag_5.postexevalues);
                    DetailFrag_5.postsubdata.put(DetailFrag_5.postexeparameters);
                    DetailFrag_5.postexevalues = new JSONArray();
                    DetailFrag_5.postexeparameters = new JSONArray();
                    if ("left".equalsIgnoreCase(leg)) {
                        try {
                            DetailFrag_5.staticbalelc++;
                            DetailFrag_5.postexesubdata.put("left-leg-eyes-closed-" + DetailFrag_5.staticbalelc, DetailFrag_5.postsubdata);
                            DetailFrag_5.postsubdata = new JSONArray();
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        try {
                            DetailFrag_5.staticbalerc++;
                            DetailFrag_5.postexesubdata.put("right-leg-eyes-closed-" + DetailFrag_5.staticbalerc, DetailFrag_5.postsubdata);
                            DetailFrag_5.postsubdata = new JSONArray();
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    DetailFrag_5.postsubdata = new JSONArray();
                }


                Log.e("Static balance Test Time 1", (DetailFrag_5.startTime - DetailFrag_5.endTime) + " seconds");
                //assess_cycles_active.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
                staticbalancetestdata.clear();
                if ("left".equalsIgnoreCase(leg)) {
                    if (DetailFrag_5.staticbalancetestdataleft.size() > 0) {
                        for (int i = 0; i < DetailFrag_5.staticbalancetestdataleft.size(); i++) {
                            staticbalancetestdata.add(new Staticbalancetestdata(DetailFrag_5.staticbalancetestdataleft.get(i).getTime(), DetailFrag_5.staticbalancetestdataleft.get(i).getEyesstatus()));
                        }
                        staticbalancetestdata.add(new Staticbalancetestdata((float) (Math.round((DetailFrag_5.endTime / 1000.0) * 100.0) / 100.0), activepassive));
                        DetailFrag_5.staticbalancetestdataleft.add(new Staticbalancetestdata((float) (Math.round((DetailFrag_5.endTime / 1000.0) * 100.0) / 100.0), activepassive));
                    } else {
                        staticbalancetestdata.add(new Staticbalancetestdata((float) (Math.round((DetailFrag_5.endTime / 1000.0) * 100.0) / 100.0), activepassive));
                        DetailFrag_5.staticbalancetestdataleft.add(new Staticbalancetestdata((float) (Math.round((DetailFrag_5.endTime / 1000.0) * 100.0) / 100.0), activepassive));
                    }

                } else {
                    if (DetailFrag_5.staticbalancetestdataright.size() > 0) {
                        for (int i = 0; i < DetailFrag_5.staticbalancetestdataright.size(); i++) {
                            staticbalancetestdata.add(new Staticbalancetestdata(DetailFrag_5.staticbalancetestdataright.get(i).getTime(), DetailFrag_5.staticbalancetestdataright.get(i).getEyesstatus()));
                        }
                        staticbalancetestdata.add(new Staticbalancetestdata((float) (Math.round((DetailFrag_5.endTime / 1000.0) * 100.0) / 100.0), activepassive));
                        DetailFrag_5.staticbalancetestdataright.add(new Staticbalancetestdata((float) (Math.round((DetailFrag_5.endTime / 1000.0) * 100.0) / 100.0), activepassive));
                    } else {
                        staticbalancetestdata.add(new Staticbalancetestdata((float) (Math.round((DetailFrag_5.endTime / 1000.0) * 100.0) / 100.0), activepassive));
                        DetailFrag_5.staticbalancetestdataright.add(new Staticbalancetestdata((float) (Math.round((DetailFrag_5.endTime / 1000.0) * 100.0) / 100.0), activepassive));
                    }
                }

//                if (staticbaltime < (DetailFrag_5.startTime - DetailFrag_5.endTime)) {
//                    staticbaltime = (DetailFrag_5.startTime - DetailFrag_5.endTime);
//                    cycleno = staticbalancetestdata.size();
//                }
                //Log.e("Static balance Test Time 1", (DetailFrag_5.startTime - DetailFrag_5.endTime) + " seconds");
                DetailFrag_5.staticbalancetestadapter.notifyDataSetChanged();
            }
            else if ("Staircase Climbing Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
                Log.e("INBASEKAR STAIRCASE 1", String.valueOf(DetailFrag_5.leftleg.size()) + " / " + String.valueOf(DetailFrag_5.leftleg));
                staircaseclimbingtest(DetailFrag_5.leftleg, DetailFrag_5.rightleg);

                double a;
                double b;
                double c;


//                if (lascent >= rascent) {
//                    a = lascent;
//                } else {
//                    a = rascent;
//                }
//                if (lturn >= rturn) {
//                    b = lturn;
//                } else {
//                    b = rturn;
//                }
//                if (ldescent >= rdescent) {
//                    c = ldescent;
//                } else {
//                    c = rdescent;
//                }

                a = lascent;
                b = lturn;
                c = ldescent;

                Log.e("Inba Staircase YoYo", (lascent) + " / " + rascent);
                Log.e("Inba Staircase YoYo", lturn + " / " + rturn);
                Log.e("Inba Staircase YoYo", ldescent + " / " + rdescent);

//                if(DetailFrag_5.ascentct>=DetailFrag_5.descentct){
//                    DetailFrag_5.stepCount=(DetailFrag_5.ascentct*2);
//                }
//                else{
//                    DetailFrag_5.stepCount=(DetailFrag_5.descentct*2);
//                }

                DetailFrag_5.postexevalues = new JSONArray();
                for (int i = 0; i < DetailFrag_5.leftleg.size(); i++) {
                    DetailFrag_5.postexevalues.put(DetailFrag_5.leftleg.get(i));
                }
                DetailFrag_5.postexeparameters = new JSONArray();
                DetailFrag_5.postexeparameters.put(DetailFrag_5.stepCount);
                try {
                    DetailFrag_5.postexeparameters.put(a);
                    DetailFrag_5.postexeparameters.put(b);
                    DetailFrag_5.postexeparameters.put(c);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }


                DetailFrag_5.postsubdata.put(DetailFrag_5.postexevalues);
                DetailFrag_5.postsubdata.put(DetailFrag_5.postexeparameters);

                if ("active".equalsIgnoreCase(activepassive)) {
                    try {
                        DetailFrag_5.postexesubdata.put("left-leg-wos-" + DetailFrag_5.cyclecount, DetailFrag_5.postsubdata);
                        Log.e("APM-chets", String.valueOf(DetailFrag_5.postsubdata));
                        DetailFrag_5.postsubdata = new JSONArray();
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    try {
                        DetailFrag_5.postexesubdata.put("left-leg-ws-" + DetailFrag_5.cyclecount, DetailFrag_5.postsubdata);
                        DetailFrag_5.postsubdata = new JSONArray();
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }

                DetailFrag_5.postsubdata = new JSONArray();
                DetailFrag_5.postexevalues = new JSONArray();

                for (int i = 0; i < DetailFrag_5.rightleg.size(); i++) {
                    DetailFrag_5.postexevalues.put(DetailFrag_5.rightleg.get(i));
                }

                DetailFrag_5.postsubdata.put(DetailFrag_5.postexevalues);
                DetailFrag_5.postsubdata.put(DetailFrag_5.postexeparameters);

                if ("active".equalsIgnoreCase(activepassive)) {
                    try {
                        DetailFrag_5.postexesubdata.put("right-leg-wos-" + DetailFrag_5.cyclecount, DetailFrag_5.postsubdata);
                        DetailFrag_5.postsubdata = new JSONArray();
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    try {
                        DetailFrag_5.postexesubdata.put("right-leg-ws-" + DetailFrag_5.cyclecount, DetailFrag_5.postsubdata);
                        DetailFrag_5.postsubdata = new JSONArray();
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }

                DetailFrag_5.postexevalues = new JSONArray();
                DetailFrag_5.postexeparameters = new JSONArray();

                DetailFrag_5.postsubdata = new JSONArray();


                assess_cycles_active.setVisibility(View.VISIBLE);
//            Log.e("Ascent Time: ", String.valueOf((DetailFrag_5.ascentEndTime - DetailFrag_5.ascentStartTime)));
//            Log.e("Descent Time: ", String.valueOf((DetailFrag_5.descentEndTime - DetailFrag_5.descentStartTime)));
//            Log.e("Turn Time: ", String.valueOf((DetailFrag_5.turnEndTime - DetailFrag_5.turnStartTime)));
//            Log.e("Number of Steps: ", String.valueOf(DetailFrag_5.stepCount));
                long totalstairtime = ((DetailFrag_5.ascentEndTime - DetailFrag_5.ascentStartTime) + (DetailFrag_5.descentEndTime - DetailFrag_5.descentStartTime) + (DetailFrag_5.turnEndTime - DetailFrag_5.turnStartTime)) / 3;


                staircaseclimbingtestdata.add(new Staircaseclimbingtestdata(DetailFrag_5.stepCount, Math.round(Math.abs((float) a) * 100) / 100.0f,
                        Math.round(Math.abs((float) c) * 100) / 100.0f,
                        Math.round(Math.abs((float) b) * 100) / 100.0f, activepassive));

                if (staticbaltime < totalstairtime) {
                    staticbaltime = totalstairtime;
                    maxrom = DetailFrag_5.stepCount;
                    sitstandtime = (DetailFrag_5.ascentEndTime - DetailFrag_5.ascentStartTime);
                    standshifttime = (DetailFrag_5.descentEndTime - DetailFrag_5.descentStartTime);
                    walkt = (DetailFrag_5.turnEndTime - DetailFrag_5.turnStartTime);
                    cycleno = staircaseclimbingtestdata.size();
                }
                DetailFrag_5.staircaseclimbingadapter.notifyDataSetChanged();
            }
            else if ("Mobility Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
                assess_cycles_active.setVisibility(View.VISIBLE);
                Log.e("Mobility Timer stop", String.valueOf(DetailFrag_5.passiveeds));
                int max = 0;
                DetailFrag_5.mobilityCycleAssessments.clear();
                int maxflex = 0;
                int minext = 0;
                if ("left".equalsIgnoreCase(leg)) {
                    for (int i = 0; i < DetailFrag_5.passiveeds.size(); i++) {
                        int val = Math.round(DetailFrag_5.passiveeds.get(i));
                        int val1 = Math.round(DetailFrag_5.activeeds.get(i));
                        maxflex = val;
                        minext = val1;


                        if (max < val) {
                            max = val;
                            maxrom = val;
                            cycleno = i + 1;
                        }
                    }

                    if (DetailFrag_5.mobilityCycleAssessmentsleft.size() > 0) {
                        for (int j = 0; j < DetailFrag_5.mobilityCycleAssessmentsleft.size(); j++) {
                            DetailFrag_5.mobilityCycleAssessments.add(new MobilityCycleAssessment(DetailFrag_5.mobilityCycleAssessmentsleft.get(j).getMaxflexion(), DetailFrag_5.mobilityCycleAssessmentsleft.get(j).getMinextension(), DetailFrag_5.mobilityCycleAssessmentsleft.get(j).getMode()));
                        }
                    }
                    DetailFrag_5.mobilityCycleAssessments.add(new MobilityCycleAssessment(maxflex, minext, activepassive));
                    DetailFrag_5.mobilityCycleAssessmentsleft.add(new MobilityCycleAssessment(maxflex, minext, activepassive));
                    DetailFrag_5.mobilityCycleAdapter.notifyDataSetChanged();
                    for (int j = 0; j < DetailFrag_5.mobilityCycleAssessmentsleft.size(); j++) {
                        Log.e("Mobility Left3", String.valueOf(DetailFrag_5.mobilityCycleAssessmentsleft.get(j).getMode() + " / " + DetailFrag_5.mobilityCycleAssessmentsleft.get(j).getMaxflexion() + " / " + DetailFrag_5.mobilityCycleAssessmentsleft.get(j).getMinextension()));
                    }
                } else {
                    Log.e("Mobility Left", String.valueOf(DetailFrag_5.activeeds));
                    for (int i = 0; i < DetailFrag_5.activeeds.size(); i++) {
                        int val = Math.round(DetailFrag_5.activeeds.get(i));
                        int val1 = Math.round(DetailFrag_5.passiveeds.get(i));
                        maxflex = val;
                        minext = val1;

                        if (max < val) {
                            max = val;
                            maxrom = val;
                            cycleno = i + 1;
                        }
                    }
                    if (DetailFrag_5.mobilityCycleAssessmentsright.size() > 0) {
                        for (int j = 0; j < DetailFrag_5.mobilityCycleAssessmentsright.size(); j++) {
                            DetailFrag_5.mobilityCycleAssessments.add(new MobilityCycleAssessment(DetailFrag_5.mobilityCycleAssessmentsright.get(j).getMaxflexion(), DetailFrag_5.mobilityCycleAssessmentsright.get(j).getMinextension(), DetailFrag_5.mobilityCycleAssessmentsright.get(j).getMode()));
                        }
                        DetailFrag_5.mobilityCycleAssessments.add(new MobilityCycleAssessment(maxflex, minext, activepassive));
                        DetailFrag_5.mobilityCycleAssessmentsright.add(new MobilityCycleAssessment(maxflex, minext, activepassive));
                    } else {
                        DetailFrag_5.mobilityCycleAssessments.add(new MobilityCycleAssessment(maxflex, minext, activepassive));
                        DetailFrag_5.mobilityCycleAssessmentsright.add(new MobilityCycleAssessment(maxflex, minext, activepassive));
                    }
                    DetailFrag_5.mobilityCycleAdapter.notifyDataSetChanged();
                }
            }
            else {
                if ("Walk and Gait Analysis".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
                    assess_cycles_active.setVisibility(View.VISIBLE);
                    walkgaittest(DetailFrag_5.leftlegwalk, DetailFrag_5.rightlegwalk);

                    float angleaverage = 0, totalangle = 0;
                    if (DetailFrag_5.walkgaitleftlegangles.size() >= DetailFrag_5.walkgaitrightlegangles.size()) {
                        for (int i = 0; i < DetailFrag_5.walkgaitrightlegangles.size(); i++) {
                            if (i == 0) {
                                if (DetailFrag_5.walkgaitrightlegangles.get(i) >= DetailFrag_5.walkgaitleftlegangles.get(i)) {
                                    totalangle += DetailFrag_5.walkgaitrightlegangles.get(i);
                                } else {
                                    totalangle += DetailFrag_5.walkgaitleftlegangles.get(i);
                                }
                            } else {
                                totalangle += DetailFrag_5.walkgaitrightlegangles.get(i);
                                totalangle += DetailFrag_5.walkgaitleftlegangles.get(i);
                            }
                        }
                        angleaverage = totalangle / ((DetailFrag_5.walkgaitrightlegangles.size() * 2) - 1);
                    } else {
                        for (int i = 0; i < DetailFrag_5.walkgaitleftlegangles.size(); i++) {
                            if (i == 0) {
                                if (DetailFrag_5.walkgaitrightlegangles.get(i) >= DetailFrag_5.walkgaitleftlegangles.get(i)) {
                                    totalangle += DetailFrag_5.walkgaitrightlegangles.get(i);
                                } else {
                                    totalangle += DetailFrag_5.walkgaitleftlegangles.get(i);
                                }
                            } else {
                                totalangle += DetailFrag_5.walkgaitrightlegangles.get(i);
                                totalangle += DetailFrag_5.walkgaitleftlegangles.get(i);
                            }
                        }
                        angleaverage = totalangle / ((DetailFrag_5.walkgaitleftlegangles.size() * 2) - 1);
                    }

                    double threshold = -488.6874 + (9.9834 * DetailFrag_5.rightleglength) + (1.1801 * angleaverage);

                    double threshper = 0;
                    if (DetailFrag_5.rightleglength <= 50) {
                        threshper = 16;
                    } else if (DetailFrag_5.rightleglength == 51) {
                        threshper = 24;
                    } else if (DetailFrag_5.rightleglength == 52) {
                        threshper = 32;
                    } else if (DetailFrag_5.rightleglength >= 53) {
                        threshper = 40;
                    }

                    double avgswingtime = 0, avgstridelength = 0, avgstridelengthperh = 0, avgsteplength = 0, leftswing = 0, rightswing = 0;
                    double rightswingtimetotal = 0, leftswingtimetotal = 0, leftstridelengthtotal = 0, rightstridelengthtotal = 0, leftstridelengthperhtotal = 0, rightstridelengthperhtotal = 0, steplengthtotal = 0;
                    String swing;
                    String stride;
                    String steplenghtavgboth;
                    String strideper;
                    int stepcount = 0;

                    double leftstrideavg = 0, rightstrideavg = 0, leftstrideperhavg = 0, rightstrideperhavg = 0, rightstepavg = 0, leftstepavg = 0;


                    if (leftswingtime.size() >= rightswingtime.size()) {
                        for (int i = 0; i < rightswingtime.size(); i++) {
                            rightswingtimetotal += rightswingtime.get(i);
                            leftswingtimetotal += leftswingtime.get(i);

                        }
                        avgswingtime = (((rightswingtimetotal / rightswingtime.size()) + (leftswingtimetotal / rightswingtime.size()))) / 2;
                        leftswing = (leftswingtimetotal / rightswingtime.size());
                        rightswing = (rightswingtimetotal / rightswingtime.size());
                        swing = String.format("L: %.2f Sec R: %.2f Sec",
                                Math.abs((leftswingtimetotal / (double) rightswingtime.size()) / 1000.0),
                                Math.abs((rightswingtimetotal / (double) rightswingtime.size()) / 1000.0)
                        );
                    } else {
                        for (int i = 0; i < leftswingtime.size(); i++) {
                            rightswingtimetotal += rightswingtime.get(i);
                            leftswingtimetotal += leftswingtime.get(i);

                        }
                        avgswingtime = ((rightswingtimetotal / leftswingtime.size()) + (leftswingtimetotal / leftswingtime.size())) / 2;
                        leftswing = (leftswingtimetotal / leftswingtime.size());
                        rightswing = (rightswingtimetotal / leftswingtime.size());
                        swing = String.format("L: %.2f Sec R: %.2f Sec",
                                Math.abs((leftswingtimetotal / leftswingtime.size()) / 1000.0),
                                Math.abs((rightswingtimetotal / leftswingtime.size()) / 1000.0)
                        );
                    }

                    int flag = 0;
                    double steplength = 0;

                    if (leftstride.size() >= rightstride.size()) {
                        int dr = rightstride.size();
                        double r = 60 / (dr - 1);
                        for (int i = 0; i < rightstride.size(); i++) {
                            leftstridelengthtotal += leftstride.get(i);
                            rightstridelengthtotal += rightstride.get(i);

                            if (i == 0) {
                                if ((((leftstride.get(i))) < (rightstride.get(i)))) {
                                    double t = rightstride.get(i) - leftstride.get(i);
                                    if (t < 20) {
                                        double f = leftstride.get(i) + Math.round(t / 2) + Math.round(t / 3);
                                        leftstride.set(i, f);
                                    } else {
                                        double f = leftstride.get(i) + Math.round(t / 2) + Math.round(t / 3) + Math.round(t / 3);
                                        leftstride.set(i, f);
                                    }
                                    flag = 1;
                                    steplengthtotal += rightstride.get(i);
                                    step.add(i + 1 + " " + String.valueOf(Math.round(Math.abs(Math.ceil((rightstride.get(i) / 2))))));
                                    steplength = (rightstride.get(i) / 2);
                                } else if (leftstride.get(i) == rightstride.get(i)) {
                                    if (leftstepflag == 1) {
                                        double f = leftstride.get(i) - 16;
                                        leftstride.set(i, f);
                                    } else {
                                        double f = rightstride.get(i) - 16;
                                        rightstride.set(i, f);
                                    }

                                    steplengthtotal += rightstride.get(i);
                                    step.add(i + 1 + " " + String.valueOf(Math.round(Math.abs(Math.ceil((rightstride.get(i) / 2))))));
                                    steplength = (rightstride.get(i) / 2);

                                } else {
                                    double t = leftstride.get(i) - rightstride.get(i);
                                    if (t < 20) {
                                        double f = rightstride.get(i) + Math.round(t / 2) + Math.round(t / 3);
                                        rightstride.set(i, f);
                                    } else {
                                        double f = rightstride.get(i) + Math.round(t / 2) + Math.round(t / 3) + Math.round(t / 3);
                                        rightstride.set(i, f);
                                    }
                                    flag = 0;
                                    steplengthtotal += leftstride.get(i);
                                    step.add(i + 1 + " " + String.valueOf(Math.round(Math.abs(Math.ceil((leftstride.get(i) / 2))))));
                                    steplength = (leftstride.get(i) / 2);
                                }
                            } else {
                                leftstride.set(i, leftstride.get(i) + r);
                                rightstride.set(i, rightstride.get(i) + r);
                                //step.add(i + 1 + " " + String.valueOf(Math.abs(Math.ceil(leftstride.get(i) - rightstride.get(i)))));
                                if (flag == 0) {
                                    steplengthtotal += leftstride.get(i);
                                } else {
                                    steplengthtotal += rightstride.get(i);
                                }

                                if (rightstride.get(i) >= leftstride.get(i)) {
                                    step.add(i + 1 + " " + String.valueOf(Math.round(Math.abs(Math.ceil((rightstride.get(i) / 2))))));
                                } else {
                                    step.add(i + 1 + " " + String.valueOf(Math.round(Math.abs(Math.ceil((leftstride.get(i) / 2))))));
                                }


                            }


//                            steplengthtotal += (leftstride.get(i) - rightstride.get(i));

                            double leftthreshadd = Math.round(((Math.round(leftstride.get(i)) + threshold) * threshper) / 100);
                            double rightthreshadd = Math.round(((Math.round(rightstride.get(i)) + threshold) * threshper) / 100);


                            leftstridelengthperhtotal += ((leftstride.get(i)) * MainActivity.patientheight) / 100;
                            rightstridelengthperhtotal += ((rightstride.get(i)) * MainActivity.patientheight) / 100;

                        }
                        avgstridelengthperh = ((leftstridelengthperhtotal / rightstride.size()) + (rightstridelengthperhtotal / rightstride.size())) / 2;
                        leftstrideperhavg = (leftstridelengthperhtotal / rightstride.size());
                        rightstrideperhavg = (rightstridelengthperhtotal / rightstride.size());
                        strideper = String.format("L: %.2f R: %.2f",
                                (((leftstridelengthperhtotal / (double) rightstride.size()) * (MainActivity.patientheight / 100.0)) / 100.0),
                                (((leftstridelengthperhtotal / (double) rightstride.size()) * (MainActivity.patientheight / 100.0)) / 100.0)
                        );
                        avgstridelength = ((leftstridelengthtotal / rightstride.size()) + (rightstridelengthtotal / rightstride.size())) / 2;
                        avgsteplength = steplengthtotal / rightstride.size();
                        double lst = 0;
                        double rst = 0;

                        if ((leftstridelengthtotal / rightstride.size()) >= (rightstridelengthtotal / rightstride.size())) {
                            lst = ((leftstridelengthtotal / rightstride.size()) + (rightstridelengthtotal / rightstride.size())) / 2.0;
                            stride = String.format("L: %.2f m R: %.2f m",
                                    (lst / 100.0),
                                    ((rightstridelengthtotal / rightstride.size()) / 100.0)
                            );
                            leftstrideavg = (lst);
                            rightstrideavg = (rightstridelengthtotal / rightstride.size());
                            steplenghtavgboth = String.format("L: %.2f m R: %.2f m",
                                    ((lst / 2.0) / 100.0),
                                    (((rightstridelengthtotal / (double) rightstride.size()) / 2.0) / 100.0)
                            );

                            leftstepavg = (lst / 2.0);
                            rightstepavg = ((rightstridelengthtotal / (double) rightstride.size()) / 2.0);
                        } else {
                            rst = ((leftstridelengthtotal / rightstride.size()) + (rightstridelengthtotal / rightstride.size())) / 2.0;
                            stride = String.format("L: %.2f m R: %.2f m",
                                    ((leftstridelengthtotal / rightstride.size()) / 100.0),
                                    (rst / 100.0)
                            );
                            leftstrideavg = (leftstridelengthtotal / rightstride.size());
                            rightstrideavg = (rst);
                            steplenghtavgboth = String.format("L: %.2f m R: %.2f m",
                                    (((leftstridelengthtotal / (double) rightstride.size()) / 2.0) / 100.0),
                                    ((rst / 2.0) / 100.0)
                            );
                            leftstepavg = ((leftstridelengthtotal / (double) rightstride.size()) / 2.0);
                            rightstepavg = (rst / 2.0);

                        }


                        stepcount = leftstride.size() * 2;

                    } else {
                        int dr = rightstride.size();
                        double r = 60 / (dr - 1);
                        for (int i = 0; i < leftstride.size(); i++) {
                            leftstridelengthtotal += leftstride.get(i);
                            rightstridelengthtotal += rightstride.get(i);

                            if (i == 0) {
                                if ((((leftstride.get(i))) < (rightstride.get(i)))) {
                                    flag = 1;
                                    steplengthtotal += rightstride.get(i);
                                    step.add(i + 1 + " " + String.valueOf(Math.round(Math.abs(Math.ceil((rightstride.get(i) / 2))))));
                                    steplength = (rightstride.get(i) / 2);
                                } else {
                                    flag = 0;
                                    steplengthtotal += leftstride.get(i);
                                    step.add(i + 1 + " " + String.valueOf(Math.round(Math.abs(Math.ceil((leftstride.get(i) / 2))))));
                                    steplength = (leftstride.get(i) / 2);
                                }
                            } else {
                                leftstride.set(i, leftstride.get(i) + r);
                                rightstride.set(i, rightstride.get(i) + r);
                                if (flag == 0) {
                                    steplengthtotal += leftstride.get(i);
                                } else {
                                    steplengthtotal += rightstride.get(i);
                                }
                                if (rightstride.get(i) >= leftstride.get(i)) {
                                    step.add(i + 1 + " " + String.valueOf(Math.round(Math.abs(Math.ceil((rightstride.get(i) / 2))))));
                                } else {
                                    step.add(i + 1 + " " + String.valueOf(Math.round(Math.abs(Math.ceil((leftstride.get(i) / 2))))));
                                }
                            }

//                            steplengthtotal += (leftstride.get(i) - rightstride.get(i));
//                            step.add(i + 1 + " " + String.valueOf(Math.abs(Math.ceil(leftstride.get(i) - rightstride.get(i)))));
                            double leftthreshadd = Math.round(((Math.round(leftstride.get(i)) + threshold) * threshper) / 100);
                            double rightthreshadd = Math.round(((Math.round(rightstride.get(i)) + threshold) * threshper) / 100);


                            leftstridelengthperhtotal += ((leftstride.get(i)) * MainActivity.patientheight) / 100;
                            rightstridelengthperhtotal += ((rightstride.get(i)) * MainActivity.patientheight) / 100;

                        }
                        avgstridelengthperh = ((leftstridelengthperhtotal / leftstride.size()) + (rightstridelengthperhtotal / leftstride.size())) / 2;
                        leftstrideperhavg = (leftstridelengthperhtotal / leftstride.size());
                        rightstrideperhavg = (rightstridelengthperhtotal / leftstride.size());
                        double lst = 0;
                        double rst = 0;

                        if ((leftstridelengthtotal / leftstride.size()) >= (rightstridelengthtotal / leftstride.size())) {
                            lst = ((leftstridelengthtotal / leftstride.size()) + (rightstridelengthtotal / leftstride.size())) / 2.0;
                            stride = String.format("L: %.2f m R: %.2f m",
                                    (lst / 100.0),
                                    ((rightstridelengthtotal / leftstride.size()) / 100.0)
                            );
                            leftstrideavg = (lst);
                            rightstrideavg = (rightstridelengthtotal / leftstride.size());
                            steplenghtavgboth = String.format("L: %.2f m R: %.2f m",
                                    ((lst / 2.0) / 100.0),
                                    (((rightstridelengthtotal / (double) leftstride.size()) / 2.0) / 100.0)
                            );
                            leftstepavg = (lst / 2.0);
                            rightstepavg = ((rightstridelengthtotal / (double) leftstride.size()) / 2.0);
                        } else {
                            rst = ((leftstridelengthtotal / leftstride.size()) + (rightstridelengthtotal / leftstride.size())) / 2.0;
                            stride = String.format("L: %.2f m R: %.2f m",
                                    ((leftstridelengthtotal / leftstride.size()) / 100.0),
                                    (rst / 100.0)
                            );
                            leftstrideavg = (leftstridelengthtotal / leftstride.size());
                            rightstrideavg = (rst);
                            steplenghtavgboth = String.format("L: %.2f m R: %.2f m",
                                    (((leftstridelengthtotal / (double) leftstride.size()) / 2.0) / 100.0),
                                    ((rst / 2.0) / 100.0)
                            );
                            leftstepavg = ((leftstridelengthtotal / (double) leftstride.size()) / 2.0);
                            rightstepavg = (rst / 2.0);

                        }

                        strideper = String.format("L: %.2f R: %.2f",
                                (((leftstridelengthperhtotal / (double) leftstride.size()) * (MainActivity.patientheight / 100.0)) / 100.0),
                                (((leftstridelengthperhtotal / (double) leftstride.size()) * (MainActivity.patientheight / 100.0)) / 100.0)
                        );
                        avgstridelength = ((leftstridelengthtotal / leftstride.size()) + (rightstridelengthtotal / leftstride.size())) / 2;
                        avgsteplength = steplengthtotal / leftstride.size();
                        stepcount = leftstride.size() * 2;
                    }

//                    if (leftstrideper.size() >= rightstrideper.size()) {
//                        for (int i = 0; i < rightstrideper.size(); i++) {
//                            leftstridelengthperhtotal += leftstrideper.get(i);
//                            rightstridelengthperhtotal += rightstrideper.get(i);
//                            strideper.add(i + 1 + " L " + String.valueOf(Math.round(leftstrideper.get(i))) + " R " + String.valueOf(Math.round(rightstrideper.get(i))));
//                        }
//                        avgstridelengthperh = ((leftstridelengthperhtotal / rightstrideper.size()) + (rightstridelengthperhtotal / rightstrideper.size())) / 2;
//                    } else {
//                        for (int i = 0; i < leftstrideper.size(); i++) {
//                            leftstridelengthperhtotal += leftstrideper.get(i);
//                            rightstridelengthperhtotal += rightstrideper.get(i);
//                            strideper.add(i + 1 + " L " + String.valueOf(Math.round(leftstrideper.get(i))) + " R " + String.valueOf(Math.round(rightstrideper.get(i))));
//                        }
//                        avgstridelengthperh = ((leftstridelengthperhtotal / leftstrideper.size()) + (rightstridelengthperhtotal / leftstrideper.size())) / 2;
//                    }


                    double stancetotal = 0;
                    for (int i = 0; i < stance.size(); i++) {
                        stancetotal += Double.parseDouble(String.valueOf(stance.get(i)));
                    }
                    double stanceavg = Math.round(((DetailFrag_5.totalstancepahse / 1000.0) / stance.size()) * 100.0) / 100.0;
                    String stanceAvgStr = String.format("%.2f", stanceavg) + " Sec";
                    Log.e("Walk Gait Stance Phase counts", stancetotal + " / " + DetailFrag_5.totalstancepahse + " / " + stanceavg + " / " + stance.size());


                    long activetime = (DetailFrag_5.activeendtime / 1000) - (DetailFrag_5.activestarttime / 1000);
                    if ((activetime / 60) >= 1) {
                        DetailFrag_5.cadence = (stepcount / (activetime / 60));
                    } else {
                        DetailFrag_5.cadence = DetailFrag_5.stepCountwalk;
                    }

                    DetailFrag_5.postexevalues = new JSONArray();

                    for (int i = 0; i < DetailFrag_5.leftlegwos.size(); i++) {
                        DetailFrag_5.postexevalues.put(DetailFrag_5.leftlegwos.get(i));
                    }
                    try {
                        DetailFrag_5.postexeparameters.put(Math.round((steplengthtotal / 100.0) * 100.0) / 100.0);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                    DetailFrag_5.postexeparameters.put(Math.round(DetailFrag_5.totalBreakTime / 1000));
                    int sum1 = 0;
                    if (avgswingtime <= 0) {
                        DetailFrag_5.postexeparameters.put(0);
                    } else {
                        try {
                            DetailFrag_5.postexeparameters.put(Math.round((leftswing / 1000.0) * 100.0) / 100.0);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

                    }
                    if (DetailFrag_5.totalstancepahse == 0) {
                        DetailFrag_5.postexeparameters.put(0);
                    } else {
                        try {
                            DetailFrag_5.postexeparameters.put(stanceavg);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    DetailFrag_5.meanVelocity = Math.abs((steplengthtotal / (double) activetime));

                    try {
                        DetailFrag_5.postexeparameters.put(Math.round((leftstrideavg / 100.0) * 100.0) / 100.0);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                    try {
                        DetailFrag_5.postexeparameters.put(Math.round((leftstrideperhavg / 100.0) * 100.0) / 100.0);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        DetailFrag_5.postexeparameters.put(Math.round((leftstepavg / 100.0) * 100.0) / 100.0);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                    try {
                        DetailFrag_5.postexeparameters.put(Math.round((DetailFrag_5.meanVelocity / 100.0) * 100.0) / 100.0);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    DetailFrag_5.postexeparameters.put(Math.round(DetailFrag_5.cadence));
                    DetailFrag_5.postexeparameters.put(Math.round(DetailFrag_5.stepCountwalk));

                    if (DetailFrag_5.activeendtime == 0 || DetailFrag_5.activeendtime == 0) {
                        DetailFrag_5.postexeparameters.put(0);
                    } else {
                        DetailFrag_5.postexeparameters.put(Math.round((DetailFrag_5.activeendtime / 1000) - (DetailFrag_5.activestarttime / 1000)));
                    }


                    DetailFrag_5.postsubdata.put(DetailFrag_5.postexevalues);
                    DetailFrag_5.postsubdata.put(DetailFrag_5.postexeparameters);
                    try {
                        DetailFrag_5.postexesubdata.put("leftleg-" + DetailFrag_5.cyclecount, DetailFrag_5.postsubdata);
                        DetailFrag_5.postsubdata = new JSONArray();
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }


                    DetailFrag_5.postexevalues = new JSONArray();
                    DetailFrag_5.postexeparameters = new JSONArray();

                    DetailFrag_5.postsubdata = new JSONArray();

                    for (int i = 0; i < DetailFrag_5.rightwos.size(); i++) {
                        DetailFrag_5.postexevalues.put(DetailFrag_5.rightwos.get(i));
                    }


                    try {
                        DetailFrag_5.postexeparameters.put(Math.round((steplengthtotal / 100.0) * 100.0) / 100.0);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    DetailFrag_5.postexeparameters.put(Math.round(DetailFrag_5.totalBreakTime / 1000));
                    if (avgswingtime <= 0) {
                        DetailFrag_5.postexeparameters.put(0);
                    } else {
                        try {
                            DetailFrag_5.postexeparameters.put(Math.round((rightswing / 1000.0) * 100.0) / 100.0);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    if (DetailFrag_5.totalstancepahse == 0) {
                        DetailFrag_5.postexeparameters.put(0);
                    } else {
                        try {
                            DetailFrag_5.postexeparameters.put(stanceavg);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    try {
                        DetailFrag_5.postexeparameters.put(Math.round((rightstrideavg / 100.0) * 100.0) / 100.0);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        DetailFrag_5.postexeparameters.put(Math.round((rightstrideperhavg / 100.0) * 100.0) / 100.0);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        DetailFrag_5.postexeparameters.put(Math.round((rightstepavg / 100.0) * 100.0) / 100.0);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        DetailFrag_5.postexeparameters.put(Math.round((DetailFrag_5.meanVelocity / 100.0) * 100.0) / 100.0);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    DetailFrag_5.postexeparameters.put(Math.round(DetailFrag_5.cadence));
                    DetailFrag_5.postexeparameters.put(Math.round(DetailFrag_5.stepCountwalk));
                    DetailFrag_5.postexeparameters.put(Math.round((DetailFrag_5.activeendtime / 1000) - (DetailFrag_5.activestarttime / 1000)));

                    DetailFrag_5.postsubdata.put(DetailFrag_5.postexevalues);
                    DetailFrag_5.postsubdata.put(DetailFrag_5.postexeparameters);
                    try {
                        DetailFrag_5.postexesubdata.put("rightleg-" + DetailFrag_5.cyclecount, DetailFrag_5.postsubdata);
                        DetailFrag_5.postsubdata = new JSONArray();
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    DetailFrag_5.postexevalues = new JSONArray();
                    DetailFrag_5.postexeparameters = new JSONArray();
                    DetailFrag_5.postsubdata = new JSONArray();


                    //Log.e("Walk and Gait Analysis Distance", String.valueOf(Math.round(DetailFrag_5.totalDistance)));
//                    Log.e("Inbasekar Walk and Gait Analysis Step Count", String.valueOf(Math.round(DetailFrag_5.stepCountwalk)));
//                    Log.e("Inbasekar Walk and Gait Analysis Break Count", String.valueOf(Math.round(DetailFrag_5.breakCount)));
//                    //Log.e("Walk and Gait Analysis Active Time", String.valueOf(Math.round(DetailFrag_5.activeTime)));
//                    Log.e("Inbasekar Walk and Gait Analysis Stand Time", String.valueOf(Math.round(DetailFrag_5.totalBreakTime / 1000)));
//                    int sum = 0;
//                    for (float number : DetailFrag_5.walkgaitswingtime) {
//                        sum += number;
//                    }
//
//                    Log.e("Inbasekar Walk and Gait Analysis Swing Time", String.valueOf(Math.round((sum / 1000))));
//
//                    Log.e("Inbasekar Walk and Gait Analysis Active Time", String.valueOf(activetime));
//                    Log.e("Inbasekar Walk and Gait Analysis Stance Time", String.valueOf(DetailFrag_5.totalstancepahse));

                    //Log.e("Walk and Gait Analysis Stance Time", String.valueOf(Math.round(DetailFrag_5.avgStancetime)));
                    //Log.e("Walk and Gait Analysis Stride Length", String.valueOf(Math.round(DetailFrag_5.strideLength)));
                    //Log.e("Walk and Gait Analysis Meanvelocity", String.valueOf(Math.round(DetailFrag_5.meanVelocity)));
                    //Log.e("Walk and Gait Analysis Stride Cadence", String.valueOf(Math.round(DetailFrag_5.cadence)));


                    int a;
                    long b, c;
//                    if (sum < 1) {
//                        a = 0;
//
//                    } else {
//                        a = (sum / 1000) / DetailFrag_5.walkgaitswingtime.size();
//                    }
                    if (DetailFrag_5.totalstancepahse < 1) {
                        b = 0;
                    } else {
                        b = DetailFrag_5.totalstancepahse / 1000;
                    }
                    if (DetailFrag_5.activeendtime == 0 || DetailFrag_5.activeendtime == 0) {
                        c = 0;
                    } else {
                        c = (DetailFrag_5.activeendtime / 1000) - (DetailFrag_5.activestarttime / 1000);
                    }

//                    if(leftstride.size()>=rightstride.size()){
//                        for(int i=0; i<rightstride.size(); i++){
//                            step.add(i+1+" "+String.valueOf(Math.abs(Math.ceil(leftstride.get(i)-rightstride.get(i)))));
//                        }
//                    }
//                    else{
//                        for(int i=0; i<leftstride.size(); i++){
//                            step.add(i+1+" "+String.valueOf(Math.abs(Math.ceil(leftstride.get(i)-rightstride.get(i)))));
//                        }
//                    }

//                    List<String> swing = new ArrayList<>();
//                    if(leftswingtime.size()>=rightswingtime.size()){
//                        for(int i=0; i<rightswingtime.size(); i++){
//                            swing.add(i+1+" L "+String.valueOf(leftswingtime.get(i))+" R "+String.valueOf(rightswingtime.get(i)));
//                        }
//                    }
//                    else{
//                        for(int i=0; i<leftswingtime.size(); i++){
//                            swing.add(i+1+" L "+String.valueOf(leftswingtime.get(i))+" R "+String.valueOf(rightswingtime.get(i)));
//                        }
//
//                    }

//                    List<String> stride = new ArrayList<>();
//                    if(leftstride.size()>=rightstride.size()){
//                        for(int i=0; i<rightstride.size(); i++){
//                            stride.add(i+1+" L "+String.valueOf(Math.round(leftstride.get(i)))+" R "+String.valueOf(Math.round(rightstride.get(i))));
//                        }
//                    }
//                    else{
//                        for(int i=0; i<leftstride.size(); i++){
//                            stride.add(i+1+" L "+String.valueOf(Math.round(leftstride.get(i)))+" R "+String.valueOf(Math.round(rightstride.get(i))));
//                        }
//                    }

//                    List<String> strideper = new ArrayList<>();
//                    if(leftstrideper.size()>= rightstrideper.size()){
//                        for(int i=0; i<rightstrideper.size(); i++){
//                            strideper.add(i+1+" L "+String.valueOf(Math.round(leftstrideper.get(i)))+" R "+String.valueOf(Math.round(rightstrideper.get(i))));
//                        }
//                    }
//                    else{
//                        for(int i=0; i<leftstrideper.size(); i++){
//                            strideper.add(i+1+" L "+String.valueOf(Math.round(leftstrideper.get(i)))+" R "+String.valueOf(Math.round(rightstrideper.get(i))));
//                        }
//
//                    }


                    Log.e("Inbasekar Walk and Gait Analysis Swing Time", String.valueOf(leftstride));
                    Log.e("Inbasekar Walk and Gait Analysis Swing Time", String.valueOf(rightstride));
                    DetailFrag_5.walkgaittestdata.add(new Walkgaittestdata(
                            String.valueOf(String.format("%.2f m", steplengthtotal / 100.0)),
                            String.valueOf(Math.round(DetailFrag_5.totalBreakTime / 1000)) + " Sec",
                            swing,
                            stanceAvgStr,
                            stride,
                            (String.format("%.2f m/s", DetailFrag_5.meanVelocity / 100)),
                            Math.round(DetailFrag_5.cadence) + " steps/min",
                            steplenghtavgboth,
                            strideper,
                            String.valueOf(stepcount),
                            String.valueOf(Math.round(c)) + " Sec",
                            String.valueOf(DetailFrag_5.breakCount)
                    ));

                    DetailFrag_5.walkgaittestadapter.notifyDataSetChanged();
                    leftswingtime.clear();
                    rightswingtime.clear();
                    stance.clear();
                    leftstride.clear();
                    rightstride.clear();
                    step.clear();
                    leftstrideper.clear();
                    rightstrideper.clear();


                    Log.e("Walkgaitdata", String.valueOf(DetailFrag_5.walkgaittestdata.get(0).getStepCountwalk()));
                }
                else if ("Proprioception Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
                    Log.e("Proprioception Test1", String.valueOf(DetailFrag_5.proprom));
                    Log.e("Proprioception Test1", String.valueOf(DetailFrag_5.indivimaxAngle));
                    int max = 0;
//                    if (DetailFrag_5.indivimaxAngle.size() > 0 && DetailFrag_5.proprom.size() > 0) {
//                        for (int k = 0; k < DetailFrag_5.proprom.size(); k++) {
//
//                            int val = Math.round((DetailFrag_5.indivimaxAngle.get(k)));
//                            Log.e("Cycle DAta", (String.valueOf(val)));
////                            DetailFrag_5.exerciseListact.add(new ExerciseCycleAssessment(val));
////                            DetailFrag_5.exepain.put(DetailFrag_5.indivipain.get(k));
//                            if (max < val) {
//                                max = val;
//                                maxrom = val;
//                                cycleno = k + 1;
//                            }
//                            Log.e("Proprioception Test", String.valueOf(DetailFrag_5.exerciseListact.get(i).getRangeOfMotion()));
//                        }
//                    }
//                    assess_cycles_active.setVisibility(View.VISIBLE);
//                    DetailFrag_5.proprioceptionAdapter.notifyDataSetChanged();
                } else {
                    assess_cycles_active.setVisibility(View.VISIBLE);
                    DetailFrag_5.indiviCardAdapterpass = new AssessmentCycleAdapter(this, getExerciseCycleList(), this, null);
                    assess_cycles_active.setAdapter(DetailFrag_5.indiviCardAdapterpass);
                }
            }


            Log.e("HighlightArray ", String.valueOf(DetailFrag_5.lineData.getDataSetByIndex(0)));

            //Log.e("INBASEKAR", String.valueOf(DetailFrag_5.reportarray));
        }
    }

    private void handleTimerStop() {
        isPlaying = false;
        DetailFrag_5.playflag = 0;

        if (DetailFrag_5.extnpassivemax == 361) {
            DetailFrag_5.extnpassivemax = 0;
        }

//        clearInterval();
        if (DetailFrag_5.metricArray.size() > 0) {
            Log.e("Angles Array", String.valueOf(angles));
            Log.e("Times Array", String.valueOf(DetailFrag_5.times));
            Log.e("Flexion Cycles", String.valueOf(DetailFrag_5.flexionCycles));
            Log.e("Extension Cycles", String.valueOf(DetailFrag_5.extensionCycles));
            Log.e("Total Cycles", String.valueOf(DetailFrag_5.totalCycles));

//        if (!angles.isEmpty()) {
//            maxangle.setText(String.valueOf(Collections.max(angles)));
//            minangle.setText(String.valueOf(Collections.min(angles)));
//        } else {
//            maxangle.setText("N/A");
//            minangle.setText("N/A");
//        }
//        flexioncycle.setText(String.valueOf(flexionCycles1));
//        extensioncycle.setText(String.valueOf(extensionCycles1+1));
//        velocity.setText(String.valueOf(totalCycles1));
//        rom.setText("12");
//            DetailFrag_5.datareportarray = new JSONArray();
//            for (Float angle : angles) {
//                DetailFrag_5.datareportarray.put(angle);
//            }
//            Log.e("INBASEKAR SIVAKUMAR", String.valueOf(angles));
//            Log.e("INBASEKAR SIVAKUMAR", String.valueOf(DetailFrag_5.datareportarray));
//            DetailFrag_5.analysereportarray.put(String.valueOf(Collections.min(angles)));
//            minangle1 = String.valueOf(Collections.min(angles));
//            DetailFrag_5.analysereportarray.put(String.valueOf(Collections.max(angles)));
//            maxangle1 = String.valueOf(Collections.max(angles));
//            DetailFrag_5.analysereportarray.put(String.valueOf(flexionCycles1));
//            flexion1 = String.valueOf(flexionCycles1);
//            DetailFrag_5.analysereportarray.put(String.valueOf(extensionCycles1 + 1));
//            extension1 = String.valueOf(extensionCycles1 + 1);
//            DetailFrag_5.analysereportarray.put(String.valueOf(Collections.max(angles)));
//            rom1 = String.valueOf(Collections.max(angles));
//            try {
//                DetailFrag_5.reportobject.put("data", DetailFrag_5.datareportarray);
//                DetailFrag_5.reportobject.put("analyse", DetailFrag_5.analysereportarray);
//                DetailFrag_5.reportarray.put(DetailFrag_5.reportobject);
//            } catch (JSONException e) {
//                throw new RuntimeException(e);
//            }
            if ("Mobility Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
                try {
                    DetailFrag_5.mainreportobject.put(leg + "-" + DetailFrag_5.mobilecyclecount + "-" + activepassive, DetailFrag_5.reportarray);

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            } else if ("Extension Lag Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
                DetailFrag_5.mobilecyclecount++;

                List<Entry> objectElements = new ArrayList<>();


                for (int i = 0; i < DetailFrag_5.leftlegwos.size(); i++) {
                    DetailFrag_5.objectElements.add(new Entry(i, DetailFrag_5.leftlegwos.get(i)));
                }

                List<Long> indices = new ArrayList<>();
                List<Float> values = new ArrayList<>();
                for (Entry entry : DetailFrag_5.objectElements) {
                    indices.add((long) entry.getX());
                    values.add(entry.getY());
                    try {
                        DetailFrag_5.postexevalues.put(entry.getY());
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
                for (Float angle : values) {
                    DetailFrag_5.datareportarray.put(angle);
                }


//                float velocityForPain = Math.abs((minAndMaxAngles.second - minAndMaxAngles.first) / (indices.get(indices.size() - 1) - indices.get(0))) +
//                        Math.abs((minAndMaxAngles.second + minAndMaxAngles.first) / (indices.get(indices.size() - 1) - indices.get(0)));
//
//                if (velocityForPain < 10) {
//                    DetailFrag_5.pain = 1;
//                } else if (velocityForPain < 20) {
//                    DetailFrag_5.pain = 2;
//                } else if (velocityForPain < 30) {
//                    DetailFrag_5.pain = 3;
//                } else if (velocityForPain < 40) {
//                    DetailFrag_5.pain = 4;
//                } else if (velocityForPain < 50) {
//                    DetailFrag_5.pain = 5;
//                } else if (velocityForPain < 60) {
//                    DetailFrag_5.pain = 6;
//                } else if (velocityForPain < 70) {
//                    DetailFrag_5.pain = 7;
//                } else if (velocityForPain < 80) {
//                    DetailFrag_5.pain = 8;
//                } else if (velocityForPain < 90) {
//                    DetailFrag_5.pain = 9;
//                } else if (velocityForPain < 100) {
//                    DetailFrag_5.pain = 10;
//                } else if (velocityForPain < 110) {
//                    DetailFrag_5.pain = 11;
//                } else if (velocityForPain < 120) {
//                    DetailFrag_5.pain = 12;
//                } else if (velocityForPain < 130) {
//                    DetailFrag_5.pain = 13;
//                } else if (velocityForPain < 140) {
//                    DetailFrag_5.pain = 14;
//                } else if (velocityForPain < 150) {
//                    DetailFrag_5.pain = 15;
//                } else if (velocityForPain < 160) {
//                    DetailFrag_5.pain = 16;
//                } else if (velocityForPain < 170) {
//                    DetailFrag_5.pain = 17;
//                } else if (velocityForPain < 180) {
//                    DetailFrag_5.pain = 18;
//                } else {
//                    DetailFrag_5.pain = 19;
//                }

                try {
//                    DetailFrag_5.analysereportarray.put(Math.round(velocityForPain));
//                    DetailFrag_5.postexeparameters.put(Math.round(velocityForPain));
//                    DetailFrag_5.analysereportarray.put(Math.round(velocityForPain));
//                    DetailFrag_5.analysereportarray.put("12");
//                    DetailFrag_5.postexeparameters.put(DetailFrag_5.pain);
                    DetailFrag_5.analysereportarray.put(DetailFrag_5.extnactivemax);
                    DetailFrag_5.postexeparameters.put(DetailFrag_5.extnactivemax);
                    DetailFrag_5.analysereportarray.put(DetailFrag_5.extnpassivemax);
                    DetailFrag_5.postexeparameters.put(DetailFrag_5.extnpassivemax);
                    DetailFrag_5.analysereportarray.put(DetailFrag_5.extnactivemax + DetailFrag_5.extnpassivemax);
                    DetailFrag_5.postexeparameters.put(DetailFrag_5.extnactivemax + DetailFrag_5.extnpassivemax);
                    DetailFrag_5.reportobject.put("data", DetailFrag_5.datareportarray);
                    Log.e("Test Data Report Array", String.valueOf(DetailFrag_5.datareportarray));
                    DetailFrag_5.reportobject.put("analyse", DetailFrag_5.analysereportarray);
                    DetailFrag_5.reportobject.put("exercisename", DetailFrag_5.selectedExercise);
                    DetailFrag_5.reportobject.put("mode", leg + "-leg-" + DetailFrag_5.mobilecyclecount);
                    DetailFrag_5.reportarray.put(DetailFrag_5.reportobject);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

                DetailFrag_5.postsubdata.put(DetailFrag_5.postexevalues);
                DetailFrag_5.postsubdata.put(DetailFrag_5.postexeparameters);
                DetailFrag_5.postexevalues = new JSONArray();
                DetailFrag_5.postexeparameters = new JSONArray();

                Log.e("Inside Function", String.valueOf(objectElements));
                Log.e("Inside Function Size", String.valueOf(objectElements.size()));
                if ("left".equalsIgnoreCase(leg)) {
                    try {
                        DetailFrag_5.postexesubdata.put("left-leg-" + DetailFrag_5.mobilecyclecount, DetailFrag_5.postsubdata);
                        DetailFrag_5.postsubdata = new JSONArray();
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    try {
                        DetailFrag_5.postexesubdata.put("right-leg-" + DetailFrag_5.mobilecyclecount, DetailFrag_5.postsubdata);
                        DetailFrag_5.postsubdata = new JSONArray();
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }


                try {
                    DetailFrag_5.mainreportobject.put(leg + "-leg-" + DetailFrag_5.mobilecyclecount, DetailFrag_5.reportarray);

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            } else if ("Dynamic Balance Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
                if ("active".equalsIgnoreCase(activepassive)) {
                    wos = "Without Support";
                    DetailFrag_5.objectElements2 = new ArrayList<>();
                    leg = "left";
                    for (int i = 0; i < DetailFrag_5.leftlegwos.size(); i++) {
                        DetailFrag_5.objectElements2.add(new Entry(i, DetailFrag_5.leftlegwos.get(i)));
                    }
                    Log.e("INBASEKAR", String.valueOf(DetailFrag_5.objectElements2));

                    DetailFrag_5.reportobject = new JSONObject();
                    DetailFrag_5.datareportarray = new JSONArray();
                    DetailFrag_5.analysereportarray = new JSONArray();

                    List<Long> indices = new ArrayList<>();
                    List<Float> values = new ArrayList<>();
                    for (Entry entry : DetailFrag_5.objectElements2) {
                        indices.add((long) entry.getX());
                        values.add(entry.getY());
                    }

                    for (Float angle : values) {
                        DetailFrag_5.datareportarray.put(angle);
                    }

//                    Pair<Float, Float> minAndMaxAngles = findMinMaxAngles(values);
//                    DetailFrag_5.analysereportarray.put(minAndMaxAngles.first);
//                    DetailFrag_5.analysereportarray.put(minAndMaxAngles.second);
//                    float velocityForPain = Math.abs((minAndMaxAngles.second - minAndMaxAngles.first) / (indices.get(indices.size() - 1) - indices.get(0))) +
//                            Math.abs((minAndMaxAngles.second + minAndMaxAngles.first) / (indices.get(indices.size() - 1) - indices.get(0)));
//
//                    if (velocityForPain < 10) {
//                        DetailFrag_5.pain = 1;
//                    } else if (velocityForPain < 20) {
//                        DetailFrag_5.pain = 2;
//                    } else if (velocityForPain < 30) {
//                        DetailFrag_5.pain = 3;
//                    } else if (velocityForPain < 40) {
//                        DetailFrag_5.pain = 4;
//                    } else if (velocityForPain < 50) {
//                        DetailFrag_5.pain = 5;
//                    } else if (velocityForPain < 60) {
//                        DetailFrag_5.pain = 6;
//                    } else if (velocityForPain < 70) {
//                        DetailFrag_5.pain = 7;
//                    } else if (velocityForPain < 80) {
//                        DetailFrag_5.pain = 8;
//                    } else if (velocityForPain < 90) {
//                        DetailFrag_5.pain = 9;
//                    } else if (velocityForPain < 100) {
//                        DetailFrag_5.pain = 10;
//                    } else if (velocityForPain < 110) {
//                        DetailFrag_5.pain = 11;
//                    } else if (velocityForPain < 120) {
//                        DetailFrag_5.pain = 12;
//                    } else if (velocityForPain < 130) {
//                        DetailFrag_5.pain = 13;
//                    } else if (velocityForPain < 140) {
//                        DetailFrag_5.pain = 14;
//                    } else if (velocityForPain < 150) {
//                        DetailFrag_5.pain = 15;
//                    } else if (velocityForPain < 160) {
//                        DetailFrag_5.pain = 16;
//                    } else if (velocityForPain < 170) {
//                        DetailFrag_5.pain = 17;
//                    } else if (velocityForPain < 180) {
//                        DetailFrag_5.pain = 18;
//                    } else {
//                        DetailFrag_5.pain = 19;
//                    }

//                    DetailFrag_5.indiviflexionVelocities.add((float) Math.round(velocityForPain));
//                    DetailFrag_5.indiviextensionVelocities.add((float) Math.round(velocityForPain));
//
//                    DetailFrag_5.indiviminAngle.add(minAndMaxAngles.first);
//                    DetailFrag_5.indivipain.add(DetailFrag_5.pain);


                    try {
                        DetailFrag_5.analysereportarray.put(DetailFrag_5.sittostand);
                        DetailFrag_5.analysereportarray.put(DetailFrag_5.sitToStandStartTime - DetailFrag_5.standToShiftStartTime);
                        DetailFrag_5.analysereportarray.put(DetailFrag_5.walkEndTime - DetailFrag_5.walkStartTime);
                        DetailFrag_5.reportobject.put("data", DetailFrag_5.datareportarray);
                        DetailFrag_5.reportobject.put("analyse", DetailFrag_5.analysereportarray);
                        DetailFrag_5.reportobject.put("exercisename", DetailFrag_5.selectedExercise);
                        DetailFrag_5.reportobject.put("mode", leg + "-" + wos);
                        DetailFrag_5.reportarray.put(DetailFrag_5.reportobject);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                    //Log.e("MADMAXFURYDRIVE b1", String.valueOf(DetailFrag_5.reportarray));
                    //processObjectArray(DetailFrag_5.objectElements2);
                    //Log.e("MADMAXFURYDRIVE a1", String.valueOf(DetailFrag_5.reportarray));

                    leg = "right";
                    DetailFrag_5.objectElements2 = new ArrayList<>();
                    for (int i = 0; i < DetailFrag_5.rightwos.size(); i++) {
                        DetailFrag_5.objectElements2.add(new Entry(i, DetailFrag_5.rightwos.get(i)));
                    }
                    Log.e("INBASEKAR", String.valueOf(DetailFrag_5.objectElements2));

                    DetailFrag_5.reportobject = new JSONObject();
                    DetailFrag_5.datareportarray = new JSONArray();
                    DetailFrag_5.analysereportarray = new JSONArray();

                    List<Long> indices1 = new ArrayList<>();
                    List<Float> values1 = new ArrayList<>();
                    for (Entry entry : DetailFrag_5.objectElements2) {
                        indices1.add((long) entry.getX());
                        values1.add(entry.getY());
                    }

                    for (Float angle : values1) {
                        DetailFrag_5.datareportarray.put(angle);
                    }

//                    Pair<Float, Float> minAndMaxAngles1 = findMinMaxAngles(values);
//                    DetailFrag_5.analysereportarray.put(minAndMaxAngles1.first);
//                    DetailFrag_5.analysereportarray.put(minAndMaxAngles1.second);
//                    float velocityForPain1 = Math.abs((minAndMaxAngles.second - minAndMaxAngles.first) / (indices.get(indices.size() - 1) - indices.get(0))) +
//                            Math.abs((minAndMaxAngles.second + minAndMaxAngles.first) / (indices.get(indices.size() - 1) - indices.get(0)));
//
//                    if (velocityForPain1 < 10) {
//                        DetailFrag_5.pain = 1;
//                    } else if (velocityForPain1 < 20) {
//                        DetailFrag_5.pain = 2;
//                    } else if (velocityForPain1 < 30) {
//                        DetailFrag_5.pain = 3;
//                    } else if (velocityForPain1 < 40) {
//                        DetailFrag_5.pain = 4;
//                    } else if (velocityForPain1 < 50) {
//                        DetailFrag_5.pain = 5;
//                    } else if (velocityForPain1 < 60) {
//                        DetailFrag_5.pain = 6;
//                    } else if (velocityForPain1 < 70) {
//                        DetailFrag_5.pain = 7;
//                    } else if (velocityForPain1 < 80) {
//                        DetailFrag_5.pain = 8;
//                    } else if (velocityForPain1 < 90) {
//                        DetailFrag_5.pain = 9;
//                    } else if (velocityForPain1 < 100) {
//                        DetailFrag_5.pain = 10;
//                    } else if (velocityForPain1 < 110) {
//                        DetailFrag_5.pain = 11;
//                    } else if (velocityForPain1 < 120) {
//                        DetailFrag_5.pain = 12;
//                    } else if (velocityForPain1 < 130) {
//                        DetailFrag_5.pain = 13;
//                    } else if (velocityForPain1 < 140) {
//                        DetailFrag_5.pain = 14;
//                    } else if (velocityForPain1 < 150) {
//                        DetailFrag_5.pain = 15;
//                    } else if (velocityForPain1 < 160) {
//                        DetailFrag_5.pain = 16;
//                    } else if (velocityForPain1 < 170) {
//                        DetailFrag_5.pain = 17;
//                    } else if (velocityForPain1 < 180) {
//                        DetailFrag_5.pain = 18;
//                    } else {
//                        DetailFrag_5.pain = 19;
//                    }
//
//                    DetailFrag_5.indiviflexionVelocities.add((float) Math.round(velocityForPain1));
//                    DetailFrag_5.indiviextensionVelocities.add((float) Math.round(velocityForPain1));
//
//                    DetailFrag_5.indiviminAngle.add(minAndMaxAngles1.first);
//                    DetailFrag_5.indivipain.add(DetailFrag_5.pain);
                    try {
                        DetailFrag_5.analysereportarray.put(DetailFrag_5.sittostand);
                        DetailFrag_5.analysereportarray.put(DetailFrag_5.sitToStandStartTime - DetailFrag_5.standToShiftStartTime);
                        DetailFrag_5.analysereportarray.put(DetailFrag_5.walkEndTime - DetailFrag_5.walkStartTime);
                        DetailFrag_5.reportobject.put("data", DetailFrag_5.datareportarray);
                        DetailFrag_5.reportobject.put("analyse", DetailFrag_5.analysereportarray);
                        DetailFrag_5.reportobject.put("exercisename", DetailFrag_5.selectedExercise);
                        DetailFrag_5.reportobject.put("mode", leg + "-" + wos);
                        DetailFrag_5.reportarray.put(DetailFrag_5.reportobject);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                    //Log.e("MADMAXFURYDRIVE b2", String.valueOf(DetailFrag_5.reportarray));
                    //processObjectArray(DetailFrag_5.objectElements2);
                    //Log.e("MADMAXFURYDRIVE a2", String.valueOf(DetailFrag_5.reportarray));
                    try {
                        DetailFrag_5.mainreportobject.put(activepassive, DetailFrag_5.reportarray);

                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    DetailFrag_5.objectElements2 = new ArrayList<>();
                    leg = "left";
                    wos = "With Support";
//                    Log.e("MADMAXFURY", String.valueOf(DetailFrag_5.leftlegws));
                    for (int i = 0; i < DetailFrag_5.leftlegws.size(); i++) {
                        DetailFrag_5.objectElements2.add(new Entry(i, DetailFrag_5.leftlegws.get(i)));
                    }


                    DetailFrag_5.reportobject = new JSONObject();
                    DetailFrag_5.datareportarray = new JSONArray();
                    DetailFrag_5.analysereportarray = new JSONArray();

                    List<Long> indices = new ArrayList<>();
                    List<Float> values = new ArrayList<>();
                    for (Entry entry : DetailFrag_5.objectElements2) {
                        indices.add((long) entry.getX());
                        values.add(entry.getY());
                    }

                    for (Float angle : values) {
                        DetailFrag_5.datareportarray.put(angle);
                    }

//                    Pair<Float, Float> minAndMaxAngles = findMinMaxAngles(values);
//                    DetailFrag_5.analysereportarray.put(minAndMaxAngles.first);
//                    DetailFrag_5.analysereportarray.put(minAndMaxAngles.second);
//                    float velocityForPain = Math.abs((minAndMaxAngles.second - minAndMaxAngles.first) / (indices.get(indices.size() - 1) - indices.get(0))) +
//                            Math.abs((minAndMaxAngles.second + minAndMaxAngles.first) / (indices.get(indices.size() - 1) - indices.get(0)));
//
//                    if (velocityForPain < 10) {
//                        DetailFrag_5.pain = 1;
//                    } else if (velocityForPain < 20) {
//                        DetailFrag_5.pain = 2;
//                    } else if (velocityForPain < 30) {
//                        DetailFrag_5.pain = 3;
//                    } else if (velocityForPain < 40) {
//                        DetailFrag_5.pain = 4;
//                    } else if (velocityForPain < 50) {
//                        DetailFrag_5.pain = 5;
//                    } else if (velocityForPain < 60) {
//                        DetailFrag_5.pain = 6;
//                    } else if (velocityForPain < 70) {
//                        DetailFrag_5.pain = 7;
//                    } else if (velocityForPain < 80) {
//                        DetailFrag_5.pain = 8;
//                    } else if (velocityForPain < 90) {
//                        DetailFrag_5.pain = 9;
//                    } else if (velocityForPain < 100) {
//                        DetailFrag_5.pain = 10;
//                    } else if (velocityForPain < 110) {
//                        DetailFrag_5.pain = 11;
//                    } else if (velocityForPain < 120) {
//                        DetailFrag_5.pain = 12;
//                    } else if (velocityForPain < 130) {
//                        DetailFrag_5.pain = 13;
//                    } else if (velocityForPain < 140) {
//                        DetailFrag_5.pain = 14;
//                    } else if (velocityForPain < 150) {
//                        DetailFrag_5.pain = 15;
//                    } else if (velocityForPain < 160) {
//                        DetailFrag_5.pain = 16;
//                    } else if (velocityForPain < 170) {
//                        DetailFrag_5.pain = 17;
//                    } else if (velocityForPain < 180) {
//                        DetailFrag_5.pain = 18;
//                    } else {
//                        DetailFrag_5.pain = 19;
//                    }
//
//                    DetailFrag_5.indiviflexionVelocities.add((float) Math.round(velocityForPain));
//                    DetailFrag_5.indiviextensionVelocities.add((float) Math.round(velocityForPain));
//
//                    DetailFrag_5.indiviminAngle.add(minAndMaxAngles.first);
//                    DetailFrag_5.indivipain.add(DetailFrag_5.pain);
                    try {
                        DetailFrag_5.analysereportarray.put(DetailFrag_5.sittostand);
                        DetailFrag_5.analysereportarray.put(DetailFrag_5.sitToStandStartTime - DetailFrag_5.standToShiftStartTime);
                        DetailFrag_5.analysereportarray.put(DetailFrag_5.walkEndTime - DetailFrag_5.walkStartTime);
                        DetailFrag_5.reportobject.put("data", DetailFrag_5.datareportarray);
                        DetailFrag_5.reportobject.put("analyse", DetailFrag_5.analysereportarray);
                        DetailFrag_5.reportobject.put("exercisename", DetailFrag_5.selectedExercise);
                        DetailFrag_5.reportobject.put("mode", leg + "-" + wos);
                        DetailFrag_5.reportarray.put(DetailFrag_5.reportobject);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }


                    //processObjectArray(DetailFrag_5.objectElements2);
                    leg = "right";
                    DetailFrag_5.objectElements2 = new ArrayList<>();
//                    Log.e("MADMAXFURY", String.valueOf(DetailFrag_5.rightws));
                    for (int i = 0; i < DetailFrag_5.rightws.size(); i++) {
                        DetailFrag_5.objectElements2.add(new Entry(i, DetailFrag_5.rightws.get(i)));
                    }


                    DetailFrag_5.reportobject = new JSONObject();
                    DetailFrag_5.datareportarray = new JSONArray();
                    DetailFrag_5.analysereportarray = new JSONArray();

                    List<Long> indices1 = new ArrayList<>();
                    List<Float> values1 = new ArrayList<>();
                    for (Entry entry : DetailFrag_5.objectElements2) {
                        indices1.add((long) entry.getX());
                        values1.add(entry.getY());
                    }

                    for (Float angle : values1) {
                        DetailFrag_5.datareportarray.put(angle);
                    }

//                    Pair<Float, Float> minAndMaxAngles1 = findMinMaxAngles(values);
//                    DetailFrag_5.analysereportarray.put(minAndMaxAngles1.first);
//                    DetailFrag_5.analysereportarray.put(minAndMaxAngles1.second);
//                    float velocityForPain1 = Math.abs((minAndMaxAngles.second - minAndMaxAngles.first) / (indices.get(indices.size() - 1) - indices.get(0))) +
//                            Math.abs((minAndMaxAngles.second + minAndMaxAngles.first) / (indices.get(indices.size() - 1) - indices.get(0)));
//
//                    if (velocityForPain1 < 10) {
//                        DetailFrag_5.pain = 1;
//                    } else if (velocityForPain1 < 20) {
//                        DetailFrag_5.pain = 2;
//                    } else if (velocityForPain1 < 30) {
//                        DetailFrag_5.pain = 3;
//                    } else if (velocityForPain1 < 40) {
//                        DetailFrag_5.pain = 4;
//                    } else if (velocityForPain1 < 50) {
//                        DetailFrag_5.pain = 5;
//                    } else if (velocityForPain1 < 60) {
//                        DetailFrag_5.pain = 6;
//                    } else if (velocityForPain1 < 70) {
//                        DetailFrag_5.pain = 7;
//                    } else if (velocityForPain1 < 80) {
//                        DetailFrag_5.pain = 8;
//                    } else if (velocityForPain1 < 90) {
//                        DetailFrag_5.pain = 9;
//                    } else if (velocityForPain1 < 100) {
//                        DetailFrag_5.pain = 10;
//                    } else if (velocityForPain1 < 110) {
//                        DetailFrag_5.pain = 11;
//                    } else if (velocityForPain1 < 120) {
//                        DetailFrag_5.pain = 12;
//                    } else if (velocityForPain1 < 130) {
//                        DetailFrag_5.pain = 13;
//                    } else if (velocityForPain1 < 140) {
//                        DetailFrag_5.pain = 14;
//                    } else if (velocityForPain1 < 150) {
//                        DetailFrag_5.pain = 15;
//                    } else if (velocityForPain1 < 160) {
//                        DetailFrag_5.pain = 16;
//                    } else if (velocityForPain1 < 170) {
//                        DetailFrag_5.pain = 17;
//                    } else if (velocityForPain1 < 180) {
//                        DetailFrag_5.pain = 18;
//                    } else {
//                        DetailFrag_5.pain = 19;
//                    }
//
//                    DetailFrag_5.indiviflexionVelocities.add((float) Math.round(velocityForPain1));
//                    DetailFrag_5.indiviextensionVelocities.add((float) Math.round(velocityForPain1));
//
//                    DetailFrag_5.indiviminAngle.add(minAndMaxAngles1.first);
//                    DetailFrag_5.indivipain.add(DetailFrag_5.pain);
                    try {
                        DetailFrag_5.analysereportarray.put(DetailFrag_5.sittostand);
                        DetailFrag_5.analysereportarray.put(DetailFrag_5.sitToStandStartTime - DetailFrag_5.standToShiftStartTime);
                        DetailFrag_5.analysereportarray.put(DetailFrag_5.walkEndTime - DetailFrag_5.walkStartTime);
                        DetailFrag_5.reportobject.put("data", DetailFrag_5.datareportarray);
                        DetailFrag_5.reportobject.put("analyse", DetailFrag_5.analysereportarray);
                        DetailFrag_5.reportobject.put("exercisename", DetailFrag_5.selectedExercise);
                        DetailFrag_5.reportobject.put("mode", leg + "-" + wos);
                        DetailFrag_5.reportarray.put(DetailFrag_5.reportobject);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }


                    //processObjectArray(DetailFrag_5.objectElements2);

                    try {
                        DetailFrag_5.mainreportobject.put(activepassive, DetailFrag_5.reportarray);

                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
            } else if ("Static Balance Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
                //Log.e("Inbasekar 5", String.valueOf(DetailFrag_5.staticbalanceangles));
                DetailFrag_5.objectElements2 = new ArrayList<>();
                for (int i = 0; i < DetailFrag_5.staticbalanceangles.size(); i++) {
                    DetailFrag_5.objectElements2.add(new Entry(i, DetailFrag_5.staticbalanceangles.get(i)));
                }
                DetailFrag_5.reportobject = new JSONObject();
                DetailFrag_5.datareportarray = new JSONArray();
                DetailFrag_5.analysereportarray = new JSONArray();

                List<Long> indices = new ArrayList<>();
                List<Float> values = new ArrayList<>();
                for (Entry entry : DetailFrag_5.objectElements2) {
                    indices.add((long) entry.getX());
                    values.add(entry.getY());
                }

                for (Float angle : values) {
                    DetailFrag_5.datareportarray.put(angle);
                }

//                Pair<Float, Float> minAndMaxAngles = findMinMaxAngles(values);
//                DetailFrag_5.analysereportarray.put(minAndMaxAngles.first);
//                DetailFrag_5.analysereportarray.put(minAndMaxAngles.second);
//                float velocityForPain = Math.abs((minAndMaxAngles.second - minAndMaxAngles.first) / (indices.get(indices.size() - 1) - indices.get(0))) +
//                        Math.abs((minAndMaxAngles.second + minAndMaxAngles.first) / (indices.get(indices.size() - 1) - indices.get(0)));
//
//                if (velocityForPain < 10) {
//                    DetailFrag_5.pain = 1;
//                } else if (velocityForPain < 20) {
//                    DetailFrag_5.pain = 2;
//                } else if (velocityForPain < 30) {
//                    DetailFrag_5.pain = 3;
//                } else if (velocityForPain < 40) {
//                    DetailFrag_5.pain = 4;
//                } else if (velocityForPain < 50) {
//                    DetailFrag_5.pain = 5;
//                } else if (velocityForPain < 60) {
//                    DetailFrag_5.pain = 6;
//                } else if (velocityForPain < 70) {
//                    DetailFrag_5.pain = 7;
//                } else if (velocityForPain < 80) {
//                    DetailFrag_5.pain = 8;
//                } else if (velocityForPain < 90) {
//                    DetailFrag_5.pain = 9;
//                } else if (velocityForPain < 100) {
//                    DetailFrag_5.pain = 10;
//                } else if (velocityForPain < 110) {
//                    DetailFrag_5.pain = 11;
//                } else if (velocityForPain < 120) {
//                    DetailFrag_5.pain = 12;
//                } else if (velocityForPain < 130) {
//                    DetailFrag_5.pain = 13;
//                } else if (velocityForPain < 140) {
//                    DetailFrag_5.pain = 14;
//                } else if (velocityForPain < 150) {
//                    DetailFrag_5.pain = 15;
//                } else if (velocityForPain < 160) {
//                    DetailFrag_5.pain = 16;
//                } else if (velocityForPain < 170) {
//                    DetailFrag_5.pain = 17;
//                } else if (velocityForPain < 180) {
//                    DetailFrag_5.pain = 18;
//                } else {
//                    DetailFrag_5.pain = 19;
//                }
//
//                DetailFrag_5.indiviflexionVelocities.add((float) Math.round(velocityForPain));
//                DetailFrag_5.indiviextensionVelocities.add((float) Math.round(velocityForPain));
//
//                DetailFrag_5.indiviminAngle.add(minAndMaxAngles.first);
//                DetailFrag_5.indivipain.add(DetailFrag_5.pain);
                try {
                    DetailFrag_5.analysereportarray.put(DetailFrag_5.endTime - DetailFrag_5.startTime);
//                    DetailFrag_5.analysereportarray.put(DetailFrag_5.indiviflexionVelocities);
//                    DetailFrag_5.analysereportarray.put("12");
                    DetailFrag_5.reportobject.put("data", DetailFrag_5.datareportarray);
                    DetailFrag_5.reportobject.put("analyse", DetailFrag_5.analysereportarray);
                    DetailFrag_5.reportobject.put("exercisename", DetailFrag_5.selectedExercise);
                    if ("active".equalsIgnoreCase(activepassive)) {
                        wos = "Eyes Open";
                    } else {
                        wos = "Eyes Closed";
                    }
                    DetailFrag_5.reportobject.put("mode", leg + "-" + wos);

                    DetailFrag_5.reportarray.put(DetailFrag_5.reportobject);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

                //Log.e("MADMAXFURYDRIVE b1", String.valueOf(DetailFrag_5.reportarray));
                //processObjectArray(DetailFrag_5.objectElements2);

                try {
                    DetailFrag_5.mainreportobject.put(leg + activepassive, DetailFrag_5.reportarray);

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            } else {
                if ("Staircase Climbing Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {

                    DetailFrag_5.objectElements2 = new ArrayList<>();
                    for (int i = 0; i < DetailFrag_5.leftleg.size(); i++) {
                        DetailFrag_5.objectElements2.add(new Entry(i, DetailFrag_5.leftleg.get(i)));
                    }
                    DetailFrag_5.reportobject = new JSONObject();
                    DetailFrag_5.datareportarray = new JSONArray();
                    DetailFrag_5.analysereportarray = new JSONArray();

                    List<Long> indices = new ArrayList<>();
                    List<Float> values = new ArrayList<>();
                    for (Entry entry : DetailFrag_5.objectElements2) {
                        indices.add((long) entry.getX());
                        values.add(entry.getY());
                    }

                    for (Float angle : values) {
                        DetailFrag_5.datareportarray.put(angle);
                    }

                    try {
                        DetailFrag_5.analysereportarray.put(DetailFrag_5.stepCount);
                        DetailFrag_5.analysereportarray.put(DetailFrag_5.ascentEndTime - DetailFrag_5.ascentStartTime);
                        DetailFrag_5.analysereportarray.put(DetailFrag_5.descentEndTime - DetailFrag_5.descentStartTime);
                        DetailFrag_5.analysereportarray.put(DetailFrag_5.turnEndTime - DetailFrag_5.turnStartTime);
                        DetailFrag_5.reportobject.put("data", DetailFrag_5.datareportarray);
                        DetailFrag_5.reportobject.put("analyse", DetailFrag_5.analysereportarray);
                        DetailFrag_5.reportobject.put("exercisename", DetailFrag_5.selectedExercise);
                        if ("active".equalsIgnoreCase(activepassive)) {
                            wos = "Without Support";
                        } else {
                            wos = "With Support";
                        }
                        DetailFrag_5.reportobject.put("mode", "Left" + "-" + wos);

                        DetailFrag_5.reportarray.put(DetailFrag_5.reportobject);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                    //Log.e("MADMAXFURYDRIVE b1", String.valueOf(DetailFrag_5.reportarray));
                    //processObjectArray(DetailFrag_5.objectElements2);

                    DetailFrag_5.objectElements2 = new ArrayList<>();
                    for (int i = 0; i < DetailFrag_5.rightleg.size(); i++) {
                        DetailFrag_5.objectElements2.add(new Entry(i, DetailFrag_5.rightleg.get(i)));
                    }
                    DetailFrag_5.reportobject = new JSONObject();
                    DetailFrag_5.datareportarray = new JSONArray();
                    DetailFrag_5.analysereportarray = new JSONArray();

                    List<Long> indices1 = new ArrayList<>();
                    List<Float> values1 = new ArrayList<>();
                    for (Entry entry : DetailFrag_5.objectElements2) {
                        indices1.add((long) entry.getX());
                        values1.add(entry.getY());
                    }

                    for (Float angle : values1) {
                        DetailFrag_5.datareportarray.put(angle);
                    }

                    try {
                        DetailFrag_5.analysereportarray.put(DetailFrag_5.stepCount);
                        DetailFrag_5.analysereportarray.put(DetailFrag_5.ascentEndTime - DetailFrag_5.ascentStartTime);
                        DetailFrag_5.analysereportarray.put(DetailFrag_5.descentEndTime - DetailFrag_5.descentStartTime);
                        DetailFrag_5.analysereportarray.put(DetailFrag_5.turnEndTime - DetailFrag_5.turnStartTime);
                        DetailFrag_5.reportobject.put("data", DetailFrag_5.datareportarray);
                        DetailFrag_5.reportobject.put("analyse", DetailFrag_5.analysereportarray);
                        DetailFrag_5.reportobject.put("exercisename", DetailFrag_5.selectedExercise);
                        if ("active".equalsIgnoreCase(activepassive)) {
                            wos = "Without Support";
                        } else {
                            wos = "With Support";
                        }
                        DetailFrag_5.reportobject.put("mode", "Right" + "-" + wos);

                        DetailFrag_5.reportarray.put(DetailFrag_5.reportobject);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }


                    try {
                        DetailFrag_5.mainreportobject.put(activepassive, DetailFrag_5.reportarray);

                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                } else if ("Walk and Gait Analysis".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
                    DetailFrag_5.cycle++;
                    DetailFrag_5.objectElements2 = new ArrayList<>();
                    for (int i = 0; i < DetailFrag_5.leftlegwalk.size(); i++) {
                        DetailFrag_5.objectElements2.add(new Entry(i, DetailFrag_5.leftlegwalk.get(i)));
                    }
                    DetailFrag_5.reportobject = new JSONObject();
                    DetailFrag_5.datareportarray = new JSONArray();
                    DetailFrag_5.analysereportarray = new JSONArray();

                    if (leftstride.size() >= rightstride.size()) {
                        for (int i = 0; i < rightstride.size(); i++) {
                            step.add(i + 1 + " " + String.valueOf(Math.abs(Math.ceil(leftstride.get(i) - rightstride.get(i)))));
                        }
                    } else {
                        for (int i = 0; i < leftstride.size(); i++) {
                            step.add(i + 1 + " " + String.valueOf(Math.abs(Math.ceil(leftstride.get(i) - rightstride.get(i)))));
                        }
                    }

                    double avgswingtime = 0, avgstridelength = 0, avgstridelengthperh = 0, avgsteplength = 0;
                    double rightswingtimetotal = 0, leftswingtimetotal = 0, leftstridelengthtotal = 0, rightstridelengthtotal = 0, leftstridelengthperhtotal = 0, rightstridelengthperhtotal = 0, steplengthtotal = 0;

                    if (leftswingtime.size() >= rightswingtime.size()) {
                        for (int i = 0; i < rightswingtime.size(); i++) {
                            rightswingtimetotal += rightswingtime.get(i);
                            leftswingtimetotal += leftswingtime.get(i);
                        }
                        avgswingtime = (((rightswingtimetotal / rightswingtime.size()) + (leftswingtimetotal / rightswingtime.size()))) / 2;
                    } else {
                        for (int i = 0; i < leftswingtime.size(); i++) {
                            rightswingtimetotal += rightswingtime.get(i);
                            leftswingtimetotal += leftswingtime.get(i);
                        }
                        avgswingtime = ((rightswingtimetotal / leftswingtime.size()) + (leftswingtimetotal / leftswingtime.size())) / 2;
                    }

                    if (leftstride.size() >= rightstride.size()) {
                        for (int i = 0; i < rightstride.size(); i++) {
                            leftstridelengthtotal += leftstride.get(i);
                            rightstridelengthtotal += rightstride.get(i);
                            steplengthtotal += (leftstride.get(i) - rightstride.get(i));
                        }
                        avgstridelength = ((leftstridelengthtotal / rightstride.size()) + (rightstridelengthtotal / rightstride.size())) / 2;
                        avgsteplength = steplengthtotal / rightstride.size();
                    } else {
                        for (int i = 0; i < leftstride.size(); i++) {
                            leftstridelengthtotal += leftstride.get(i);
                            rightstridelengthtotal += rightstride.get(i);
                            steplengthtotal += (leftstride.get(i) - rightstride.get(i));
                        }
                        avgstridelength = ((leftstridelengthtotal / leftstride.size()) + (rightstridelengthtotal / leftstride.size())) / 2;
                        avgsteplength = steplengthtotal / leftstride.size();
                    }

                    if (leftstrideper.size() >= rightstrideper.size()) {
                        for (int i = 0; i < rightstrideper.size(); i++) {
                            leftstridelengthperhtotal += leftstrideper.get(i);
                            rightstridelengthperhtotal += rightstrideper.get(i);
                        }
                        avgstridelengthperh = ((leftstridelengthperhtotal / rightstrideper.size()) + (rightstridelengthperhtotal / rightstrideper.size())) / 2;
                    } else {
                        for (int i = 0; i < leftstrideper.size(); i++) {
                            leftstridelengthperhtotal += leftstrideper.get(i);
                            rightstridelengthperhtotal += rightstrideper.get(i);
                        }
                        avgstridelengthperh = ((leftstridelengthperhtotal / leftstrideper.size()) + (rightstridelengthperhtotal / leftstrideper.size())) / 2;
                    }


                    List<Long> indices = new ArrayList<>();
                    List<Float> values = new ArrayList<>();
                    for (Entry entry : DetailFrag_5.objectElements2) {
                        indices.add((long) entry.getX());
                        values.add(entry.getY());
                    }

                    for (Float angle : values) {
                        DetailFrag_5.datareportarray.put(angle);
                    }


                    try {
                        DetailFrag_5.analysereportarray.put(Math.round(steplengthtotal));
                        DetailFrag_5.analysereportarray.put(Math.round(DetailFrag_5.stepCountwalk));
                        DetailFrag_5.analysereportarray.put(Math.round(DetailFrag_5.activeTime));
                        DetailFrag_5.analysereportarray.put(Math.round(DetailFrag_5.avgStandtime));
                        DetailFrag_5.analysereportarray.put(Math.round(avgswingtime));
                        DetailFrag_5.analysereportarray.put(Math.round(DetailFrag_5.totalstancepahse));
                        DetailFrag_5.analysereportarray.put(Math.round(avgstridelength));
                        DetailFrag_5.analysereportarray.put(Math.round(avgstridelengthperh));
                        DetailFrag_5.analysereportarray.put(Math.round(avgsteplength));
                        DetailFrag_5.analysereportarray.put(Math.round(DetailFrag_5.meanVelocity));
                        DetailFrag_5.analysereportarray.put(Math.round(DetailFrag_5.cadence));
                        DetailFrag_5.reportobject.put("data", DetailFrag_5.datareportarray);
                        DetailFrag_5.reportobject.put("analyse", DetailFrag_5.analysereportarray);
                        DetailFrag_5.reportobject.put("exercisename", DetailFrag_5.selectedExercise);

                        DetailFrag_5.reportobject.put("mode", String.valueOf(DetailFrag_5.cycle));

                        DetailFrag_5.reportarray.put(DetailFrag_5.reportobject);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                    //Log.e("MADMAXFURYDRIVE b1", String.valueOf(DetailFrag_5.reportarray));
                    //processObjectArray(DetailFrag_5.objectElements2);

                    DetailFrag_5.objectElements2 = new ArrayList<>();
                    for (int i = 0; i < DetailFrag_5.rightlegwalk.size(); i++) {
                        DetailFrag_5.objectElements2.add(new Entry(i, DetailFrag_5.rightlegwalk.get(i)));
                    }
                    DetailFrag_5.reportobject = new JSONObject();
                    DetailFrag_5.datareportarray = new JSONArray();
                    DetailFrag_5.analysereportarray = new JSONArray();

                    List<Long> indices1 = new ArrayList<>();
                    List<Float> values1 = new ArrayList<>();
                    for (Entry entry : DetailFrag_5.objectElements2) {
                        indices1.add((long) entry.getX());
                        values1.add(entry.getY());
                    }

                    for (Float angle : values1) {
                        DetailFrag_5.datareportarray.put(angle);
                    }

                    try {
                        DetailFrag_5.analysereportarray.put(Math.round(steplengthtotal));
                        DetailFrag_5.analysereportarray.put(Math.round(DetailFrag_5.stepCountwalk));
                        DetailFrag_5.analysereportarray.put(Math.round(DetailFrag_5.activeTime));
                        DetailFrag_5.analysereportarray.put(Math.round(DetailFrag_5.avgStandtime));
                        DetailFrag_5.analysereportarray.put(Math.round(avgswingtime));
                        DetailFrag_5.analysereportarray.put(Math.round(DetailFrag_5.totalstancepahse));
                        DetailFrag_5.analysereportarray.put(Math.round(avgstridelength));
                        DetailFrag_5.analysereportarray.put(Math.round(avgstridelengthperh));
                        DetailFrag_5.analysereportarray.put(Math.round(avgsteplength));
                        DetailFrag_5.analysereportarray.put(Math.round(DetailFrag_5.meanVelocity));
                        DetailFrag_5.analysereportarray.put(Math.round(DetailFrag_5.cadence));
                        DetailFrag_5.reportobject.put("data", DetailFrag_5.datareportarray);
                        DetailFrag_5.reportobject.put("analyse", DetailFrag_5.analysereportarray);
                        DetailFrag_5.reportobject.put("exercisename", DetailFrag_5.selectedExercise);

                        DetailFrag_5.reportobject.put("mode", String.valueOf(DetailFrag_5.cycle));

                        DetailFrag_5.reportarray.put(DetailFrag_5.reportobject);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }


                    try {
                        DetailFrag_5.mainreportobject.put(String.valueOf(DetailFrag_5.cycle), DetailFrag_5.reportarray);

                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            //Log.d("Report JSON", DetailFrag_5.reportarray.toString());

        } else {
            Toasty.error(Assessment.this, "No Values found", Toasty.LENGTH_SHORT).show();
        }
    }

    private void clearInterval() {
        //handler.removeCallbacks(runnable);
//        if (webSocketClient != null) {
//            webSocketClient.close(1000, "Goodbye, WebSocket!");
//            webSocketClient = null;
//        }

    }

    private void resetVariables() {
        List<Float> pain = new ArrayList<>();
        DetailFrag_5.stackedMetricsArray.clear();
        DetailFrag_5.metricArray.clear();
        DetailFrag_5.metricArray1.clear();
        DetailFrag_5.counter = -1;
        DetailFrag_5.elapsedTime = -1;
        updateChart();
    }

    private void updateChartData(List<Float> data, List<Float> data1) {
        //Log.e("Inside chart update", String.valueOf(data));
        if (data.size() != 0) {
            for (float value : data) {
                int index = DetailFrag_5.metricArray.size();
                DetailFrag_5.metricArray.add(new DetailFrag_5.Metric(index, value));
            }
            // Log the values in metricArray
            StringBuilder logString = new StringBuilder("MetricArrayLive: [");
            for (DetailFrag_5.Metric metric : DetailFrag_5.metricArray) {
                logString.append(metric.getVal()).append(", ");
            }
            // Remove the last comma and space
            if (logString.length() > 1) {
                logString.setLength(logString.length() - 2);
            }
            logString.append("]");
            //Log.e("MetricArrayLive", "Device 1 " + logString.toString());
        }
        if (data1.size() != 0) {
            for (float value : data1) {
                int index1 = DetailFrag_5.metricArray1.size();
                DetailFrag_5.metricArray1.add(new DetailFrag_5.Metric(index1, value));
            }
            StringBuilder logString1 = new StringBuilder("MetricArrayLive: [");
            for (DetailFrag_5.Metric metric : DetailFrag_5.metricArray1) {
                logString1.append(metric.getVal()).append(", ");
            }
            // Remove the last comma and space
            if (logString1.length() > 1) {
                logString1.setLength(logString1.length() - 2);
            }
            logString1.append("]");
            //Log.e("MetricArrayLive", "Device 2 " + logString1.toString());
        }


        // Log the string

        updateChart();
    }

    private void updateChart() {

        if (DetailFrag_5.entries == null) {
            DetailFrag_5.entries = new ArrayList<>();
        }
        if (DetailFrag_5.entries1 == null) {
            DetailFrag_5.entries1 = new ArrayList<>();
        }
        if (DetailFrag_5.chartstarttime == 0) {
            DetailFrag_5.chartstarttime = System.currentTimeMillis();
        }
//        processNewAngle(metric.val, currentTimeInSeconds, 0);

        if ("Mobility Test".equalsIgnoreCase(DetailFrag_5.selectedExercise) || "Extension Lag Test".equalsIgnoreCase(DetailFrag_5.selectedExercise) || "Static Balance Test".equalsIgnoreCase(DetailFrag_5.selectedExercise) || "Proprioception Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
            if (isPlaying && DetailFrag_5.currentMetricIndex < DetailFrag_5.metricArray.size()) {

                DetailFrag_5.Metric metric = DetailFrag_5.metricArray.get(DetailFrag_5.currentMetricIndex);


                float currentTimeInSeconds1 = (System.currentTimeMillis() - DetailFrag_5.chartstarttime) / 1000.0f;
                long currentTimeInSeconds = (long) ((System.currentTimeMillis() - DetailFrag_5.chartstarttime));
                DetailFrag_5.entries.add(new Entry(currentTimeInSeconds, metric.val));
                DetailFrag_5.timestamps.add(currentTimeInSeconds);
                processNewAngle(metric.val, currentTimeInSeconds, 0);
                DetailFrag_5.dataSet = new LineDataSet(DetailFrag_5.entries, "Data");
                DetailFrag_5.dataSet.setMode(LineDataSet.Mode.LINEAR); // Waved line
                DetailFrag_5.dataSet.setDrawValues(false); // Show values
                DetailFrag_5.dataSet.setDrawCircles(false);
                DetailFrag_5.dataSet.setColor(Color.BLUE);
                DetailFrag_5.dataSet.setLineWidth(2f);
                DetailFrag_5.dataSet.setDrawCircles(false);

                DetailFrag_5.lineData = new LineData(DetailFrag_5.dataSet);

                DetailFrag_5.lineChart.setData(DetailFrag_5.lineData);
                DetailFrag_5.lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
                DetailFrag_5.lineChart.getAxisRight().setEnabled(false);
                DetailFrag_5.lineChart.getAxisLeft().setDrawGridLines(false);
                DetailFrag_5.lineChart.getXAxis().setDrawGridLines(false);
                DetailFrag_5.lineChart.getDescription().setEnabled(false);
                DetailFrag_5.lineChart.getLegend().setEnabled(false);
                DetailFrag_5.lineChart.invalidate(); // Refresh the chart
                MarkerView marker = new MyMarkerView(this, R.layout.custom_marker_view);
                DetailFrag_5.lineChart.setMarker(marker);
                DetailFrag_5.lineChart.setHighlightPerTapEnabled(true);
                MarkerView marker1 = new MyMarkerView(this, R.layout.custom_marker_view);
                DetailFrag_5.lineChart.setMarker(marker1);
                DetailFrag_5.lineChart.setHighlightPerTapEnabled(true);


                DetailFrag_5.lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
                DetailFrag_5.lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
                DetailFrag_5.lineChart.getXAxis().setGranularity(1f); // Adjust granularity for your needs
                DetailFrag_5.lineChart.getXAxis().setGranularityEnabled(true);

                DetailFrag_5.lineChart.getXAxis().setValueFormatter(new ValueFormatter() {
                    @Override
                    public String getFormattedValue(float value) {
                        return String.format(Locale.getDefault(), "%.1f", value); // Format to 1 decimal place
                    }
                });
                DetailFrag_5.lineChart.setTouchEnabled(true);
                DetailFrag_5.lineChart.setDragEnabled(true);
                DetailFrag_5.lineChart.setScaleEnabled(true);
//                DetailFrag_5.lineChart.setVisibleXRangeMaximum(1000);  // Try reducing this if the graph still moves slow
//                DetailFrag_5.lineChart.setVisibleXRangeMinimum(1000);   // Ensure minimum smooth scrolling


                DetailFrag_5.lineChart.setPinchZoom(true); // Enable zooming with pinch
                DetailFrag_5.lineChart.setScaleXEnabled(true);
                DetailFrag_5.lineChart.setScaleYEnabled(false); // Disable Y-axis scaling for better readability

//                DetailFrag_5.lineChart.moveViewToX(DetailFrag_5.currentMetricIndex);


                DetailFrag_5.lineChart.invalidate();
                DetailFrag_5.currentMetricIndex++;

            }

//            Log.e("Graph Density Mobility", String.valueOf(DetailFrag_5.entries.size()));


            DecimalFormat df = new DecimalFormat("#.##");
            double formattedValue = Double.parseDouble(df.format(lefttotalAcceleration - 4));
            try {
                accldata.put(formattedValue);
//                Log.e("Graph acceleration", String.valueOf(lefttotalAcceleration - 4));
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }

        }
        else {
            //Log.e("Inside Update Chart", DetailFrag_5.selectedExercise);
            if (isPlaying && DetailFrag_5.currentMetricIndex < DetailFrag_5.metricArray.size() && DetailFrag_5.currentMetricIndex < DetailFrag_5.metricArray1.size()) {
                // Get the current metric to be added
                DetailFrag_5.Metric metric = DetailFrag_5.metricArray.get(DetailFrag_5.currentMetricIndex);
                DetailFrag_5.Metric metric1 = DetailFrag_5.metricArray1.get(DetailFrag_5.currentMetricIndex);


                float currentTimeInSeconds1 = (System.currentTimeMillis() - DetailFrag_5.chartstarttime) / 1000.0f;
                long currentTimeInSeconds = (long) (currentTimeInSeconds1 * 1000);

                DetailFrag_5.timestamps.add(currentTimeInSeconds);


                // Process the angle (or any other logic you need)
                processNewAngle(metric.val, currentTimeInSeconds, 0);
                processNewAngle(metric1.val, currentTimeInSeconds, 1);

                // Add the new entry to the entries list
                DetailFrag_5.entries.add(new Entry(currentTimeInSeconds, metric.val));
                DetailFrag_5.entries1.add(new Entry(currentTimeInSeconds, metric1.val));

                // Log the entries if needed

                // Create and set up LineDataSet
                DetailFrag_5.dataSet = new LineDataSet(DetailFrag_5.entries, "Left");
                //Log.e("Graph Data", String.valueOf(DetailFrag_5.dataSet));
                DetailFrag_5.dataSet.setMode(LineDataSet.Mode.LINEAR); // Waved line
                DetailFrag_5.dataSet.setDrawValues(false); // Show values
                DetailFrag_5.dataSet.setColor(Color.BLUE);
                DetailFrag_5.dataSet.setLineWidth(2f);
                DetailFrag_5.dataSet.setDrawCircles(false);

                DetailFrag_5.dataSet1 = new LineDataSet(DetailFrag_5.entries1, "Right");
                //Log.e("Graph Data", String.valueOf(DetailFrag_5.dataSet1));
                DetailFrag_5.dataSet1.setMode(LineDataSet.Mode.LINEAR); // Waved line
                DetailFrag_5.dataSet1.setDrawValues(false); // Show values
                DetailFrag_5.dataSet1.setColor(Color.RED);
                DetailFrag_5.dataSet1.setLineWidth(2f);
                DetailFrag_5.dataSet1.setDrawCircles(false);

                DetailFrag_5.lineData = new LineData(DetailFrag_5.dataSet, DetailFrag_5.dataSet1);

                DetailFrag_5.lineChart.setData(DetailFrag_5.lineData);
                DetailFrag_5.lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
                DetailFrag_5.lineChart.getAxisRight().setEnabled(false);
                DetailFrag_5.lineChart.getAxisLeft().setDrawGridLines(false);
                DetailFrag_5.lineChart.getXAxis().setDrawGridLines(false);
                DetailFrag_5.lineChart.getDescription().setEnabled(false);
                DetailFrag_5.lineChart.getLegend().setEnabled(false);
                DetailFrag_5.lineChart.invalidate(); // Refresh the chart
                MarkerView marker = new MyMarkerView(this, R.layout.custom_marker_view);
                DetailFrag_5.lineChart.setMarker(marker);
                DetailFrag_5.lineChart.setHighlightPerTapEnabled(true);

//                DetailFrag_5.lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
//                DetailFrag_5.lineChart.getXAxis().setGranularity(1f);
//                DetailFrag_5.lineChart.getXAxis().setGranularityEnabled(true);
//                DetailFrag_5.lineChart.getAxisLeft().setDrawGridLines(false);
//                DetailFrag_5.lineChart.getXAxis().setDrawGridLines(false);
//                DetailFrag_5.lineChart.getAxisRight().setDrawGridLines(false);
//                DetailFrag_5.lineChart.getDescription().setEnabled(false);
                DetailFrag_5.lineChart.setTouchEnabled(true);
//                DetailFrag_5.lineChart.setDragEnabled(true);
                DetailFrag_5.lineChart.setScaleEnabled(true);
//                DetailFrag_5.lineChart.setVisibleXRangeMaximum(10); // Show only the latest 50 points
//                DetailFrag_5.lineChart.moveViewToX(DetailFrag_5.lineData.getEntryCount());

                // Move to the next metric for the next update
                DetailFrag_5.currentMetricIndex++;
            }


        }

        // Set up the chart with LineData


    }

    private void processNewAngle(float newAngle, long newTime, int flag) {
        float currentAngle = newAngle;

        if (flag == 0) {
            if (isSecondValueReceived) {
                if (temps > currentAngle && !isFlexion) {
                    isFlexion = true;
                    DetailFrag_5.cycleCount1 = DetailFrag_5.cycleCount1 + 1;
                    flexionCycles1 = flexionCycles1 + 1;
                } else if (temps < currentAngle && isFlexion) {
                    isFlexion = false;
                    DetailFrag_5.cycleCount1 = DetailFrag_5.cycleCount1 + 1;
                    extensionCycles1 = extensionCycles1 + 1;
                }
                temps = newAngle;
                angles.add(currentAngle);
                DetailFrag_5.times.add(newTime);
                totalCycles1 = DetailFrag_5.cycleCount1;
            } else {
                temps = newAngle;
                angles.add(currentAngle);
                DetailFrag_5.times.add(newTime);
                isFlexion = currentAngle < newAngle;
                isSecondValueReceived = true;
            }
        } else {
            if (isSecondValueReceived) {
                if (temps > currentAngle && !isFlexion) {
                    isFlexion = true;
                    DetailFrag_5.cycleCount1 = DetailFrag_5.cycleCount1 + 1;
                    flexionCycles1 = flexionCycles1 + 1;
                } else if (temps < currentAngle && isFlexion) {
                    isFlexion = false;
                    DetailFrag_5.cycleCount1 = DetailFrag_5.cycleCount1 + 1;
                    extensionCycles1 = extensionCycles1 + 1;
                }
                temps = newAngle;
                angles1.add(currentAngle);
                DetailFrag_5.times1.add(newTime);
                totalCycles1 = DetailFrag_5.cycleCount1;
            } else {
                temps = newAngle;
                angles1.add(currentAngle);
                DetailFrag_5.times1.add(newTime);
                isFlexion = currentAngle < newAngle;
                isSecondValueReceived = true;
            }
        }
    }

    private void analyzeJointData(List<Float> array, int fulltime, int flag) {
        DetailFrag_5.times = new ArrayList<>();
        if (!"Proprioception Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
            DetailFrag_5.objectElements = new ArrayList<>();
        }
        for (int i = 0; i < fulltime; i++) {
            DetailFrag_5.times.add((long) i);
        }

//        Log.e(" ANGLES", array.toString());
//        Log.e(" TIMES", DetailFrag_5.times.toString());

        if (!"Proprioception Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
            for (int i = 0; i < array.size(); i++) {
                //float change = array.get(i) - array.get(i + 1);
                DetailFrag_5.objectElements.add(new Entry(i, array.get(i)));
                DetailFrag_5.exevalue.put(array.get(i));
//            if (i + 1 == objectElements.size() - 1) {
//                objectElements.add(new Entry(i+1,array.get(i+1)));
//            }
//
//            if (change == 0) {
//                continue;
//            }
//
//            if (prevSignChange != 0 && Math.signum(change) != Math.signum(prevSignChange)) {
//                cycleCount++;
//
//                if (Math.signum(change) == -1) {
//                    if (minFlexionAngle == null) {
//                        flexionCycles++;
//                        minFlexionAngle = initialAngle;
//                        float maxFlexionAngle = array.get(i);
//                        minExtensionAngle = array.get(i + 1);
//                        float flexionVelocity = (-1 * (maxFlexionAngle - minFlexionAngle)) / (time.get(i) - initialTime);
//                        flexionVelocities.add(flexionVelocity);
//                        initialTime = time.get(i + 1);
//                    } else {
//                        flexionCycles++;
//                        float maxFlexionAngle = array.get(i);
//                        minExtensionAngle = array.get(i + 1);
//                        float flexionVelocity = (-1 * (maxFlexionAngle - minFlexionAngle)) / (time.get(i) - initialTime);
//                        flexionVelocities.add(flexionVelocity);
//                        initialTime = time.get(i + 1);
//                    }
//                } else {
//                    if (minExtensionAngle == null) {
//                        extensionCycles++;
//                        minExtensionAngle = initialAngle;
//                        float maxExtensionAngle = array.get(i);
//                        minFlexionAngle = array.get(i + 1);
//                        float extensionVelocity = (maxExtensionAngle - minExtensionAngle) / (time.get(i) - initialTime);
//                        extensionVelocities.add(extensionVelocity);
//                        initialTime = time.get(i + 1);
//                    } else {
//                        extensionCycles++;
//                        float maxExtensionAngle = array.get(i);
//                        minFlexionAngle = array.get(i + 1);
//                        float extensionVelocity = (maxExtensionAngle - minFlexionAngle) / (time.get(i) - initialTime);
//                        extensionVelocities.add(extensionVelocity);
//                        initialTime = time.get(i + 1);
//                    }
//                }
//            }
//
//            prevSignChange = (int) change;
            }
        } else {
            if (flag == 0) {
                for (int i = 0; i < array.size(); i++) {
                    //float change = array.get(i) - array.get(i + 1);
                    DetailFrag_5.objectElements.add(new Entry(i, array.get(i)));
                    DetailFrag_5.exevalue.put(array.get(i));
                }
            } else if (flag == 1) {
                for (int i = 0; i < array.size(); i++) {
                    //float change = array.get(i) - array.get(i + 1);
                    DetailFrag_5.objectElements1.add(new Entry(i, array.get(i)));
                    DetailFrag_5.exevalue.put(array.get(i));
                }
            }
        }
        if ("Mobility Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
//            Log.e("Fury", DetailFrag_5.selectedExercise);
            mobilityanalysis(DetailFrag_5.objectElements);
        }
//        else if ("Extension Lag Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
//            extensionlagtest(DetailFrag_5.objectElements);
//        }
//         else if ("Staircase Climbing Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
//            Log.e("INBASEKAR STAIRCASE 1", String.valueOf(leftleg));
//            staircaseclimbingtest(DetailFrag_5.leftleg, DetailFrag_5.rightleg);
//        }
        else if ("Walk and Gait analysis".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
            //walkgaittest(DetailFrag_5.objectElements, DetailFrag_5.objectElements1);
        } else if (!"Proprioception Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
//            Log.e("Fury", "Else");
            processObjectArray(DetailFrag_5.objectElements);
        }
    }

    private void processObjectArray(List<Entry> objectElements) {
        DetailFrag_5.tempRow = new ArrayList<>();
        DetailFrag_5.cycleCount = 1;
        if ("Extension Lag Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
            if ("active".equalsIgnoreCase(activepassive)) {
                DetailFrag_5.highlightArrayact = new ArrayList<>();
            } else {
                DetailFrag_5.highlightArraypass = new ArrayList<>();
            }
        } else {
            DetailFrag_5.highlightArrayact = new ArrayList<>();
            DetailFrag_5.highlightArraypass = new ArrayList<>();
        }
        Log.e("Inside Function", String.valueOf(objectElements));
        Log.e("Inside Function Size", String.valueOf(objectElements.size()));

        for (int i = 0; i < objectElements.size() - 1; i++) {

            DetailFrag_5.change = objectElements.get(i).getY() - objectElements.get(i + 1).getY();
            DetailFrag_5.tempRow.add(objectElements.get(i));
            if (i + 1 == objectElements.size() - 1) {
                DetailFrag_5.tempRow.add(objectElements.get(i + 1));
                if ("Extension Lag Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
                    if ("active".equalsIgnoreCase(activepassive)) {
                        DetailFrag_5.highlightArrayact.add(DetailFrag_5.tempRow);
                    } else {
                        DetailFrag_5.highlightArraypass.add(DetailFrag_5.tempRow);
                    }
                } else {
                    DetailFrag_5.highlightArrayact.add(DetailFrag_5.tempRow);
                }
//                Log.e("Madmax", String.valueOf(DetailFrag_5.tempRow));
                generateParagraph(DetailFrag_5.tempRow);
                //Log.e("Highlight Array Last", i + String.valueOf(DetailFrag_5.highlightArray));
            } else if (DetailFrag_5.change == 0) {
                continue;
            } else if (DetailFrag_5.prevSignChange1 != 0 && Math.signum(DetailFrag_5.change) != Math.signum(DetailFrag_5.prevSignChange1)) {
                DetailFrag_5.cycleCount++;
                if ("Extension Lag Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
                    if ("active".equalsIgnoreCase(activepassive)) {
                        DetailFrag_5.highlightArrayact.add(DetailFrag_5.tempRow);
                    } else {
                        DetailFrag_5.highlightArraypass.add(DetailFrag_5.tempRow);
                    }
                } else {
                    DetailFrag_5.highlightArrayact.add(DetailFrag_5.tempRow);
                }
                //Log.e("Highlight Array Shift", i + String.valueOf(DetailFrag_5.highlightArray));
//                Log.e("Madmax", String.valueOf(DetailFrag_5.tempRow));
                generateParagraph(DetailFrag_5.tempRow);

                DetailFrag_5.tempRow = new ArrayList<>();
                DetailFrag_5.tempRow.add(objectElements.get(i));
            }

            DetailFrag_5.prevSignChange1 = (int) DetailFrag_5.change;
        }
        //Log.e("Highlight Array", i + String.valueOf(highlightArray));
        if ("Extension Lag Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
            if ("active".equalsIgnoreCase(activepassive)) {
                DetailFrag_5.highlightArrayact.add(Collections.singletonList(new Entry(0, 0)));
            } else {
                DetailFrag_5.highlightArraypass.add(Collections.singletonList(new Entry(0, 0)));
            }
        } else if ("Dynamic Balance Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
            Log.e("Inside dbl", "if");
        } else {
            DetailFrag_5.highlightArrayact.add(Collections.singletonList(new Entry(0, 0)));
        }

//        this.highlightArray = highlightArray;
    }

    private void generateParagraph(List<Entry> subarray) {

//        Log.e("Staircase Inbasekar", String.valueOf(subarray));

        DetailFrag_5.reportobject = new JSONObject();
        DetailFrag_5.datareportarray = new JSONArray();
        DetailFrag_5.analysereportarray = new JSONArray();

        List<Long> indices = new ArrayList<>();
        List<Float> values = new ArrayList<>();
        for (Entry entry : subarray) {
            indices.add((long) entry.getX());
            values.add(entry.getY());
            try {
                DetailFrag_5.postexevalues.put(entry.getY());
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }

        for (Float angle : values) {
            DetailFrag_5.datareportarray.put(angle);
        }

        Pair<Float, Float> minAndMaxAngles = findMinMaxAngles(values);
        if (minAndMaxAngles.second > 45 && !"Staircase Climbing Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
            if ("Extension Lag Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
                if ("active".equalsIgnoreCase(activepassive)) {
                    DetailFrag_5.highlightArrayact.add(DetailFrag_5.tempRow);
                } else {
                    DetailFrag_5.highlightArraypass.add(DetailFrag_5.tempRow);
                }
            } else if ("Mobility Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
                DetailFrag_5.highlightArrayact.add(DetailFrag_5.tempRow);
            }

            DetailFrag_5.analysereportarray.put(Math.round(minAndMaxAngles.first));
            DetailFrag_5.postexeparameters.put(Math.round(minAndMaxAngles.first));
            DetailFrag_5.analysereportarray.put(Math.round(minAndMaxAngles.second));
            DetailFrag_5.postexeparameters.put(Math.round(minAndMaxAngles.second));
            float velocityForPain = Math.abs((minAndMaxAngles.second - minAndMaxAngles.first) / (indices.get(indices.size() - 1) - indices.get(0))) +
                    Math.abs((minAndMaxAngles.second + minAndMaxAngles.first) / (indices.get(indices.size() - 1) - indices.get(0)));

            if (velocityForPain < 10) {
                DetailFrag_5.pain = 1;
            } else if (velocityForPain < 20) {
                DetailFrag_5.pain = 2;
            } else if (velocityForPain < 30) {
                DetailFrag_5.pain = 3;
            } else if (velocityForPain < 40) {
                DetailFrag_5.pain = 4;
            } else if (velocityForPain < 50) {
                DetailFrag_5.pain = 5;
            } else if (velocityForPain < 60) {
                DetailFrag_5.pain = 6;
            } else if (velocityForPain < 70) {
                DetailFrag_5.pain = 7;
            } else if (velocityForPain < 80) {
                DetailFrag_5.pain = 8;
            } else if (velocityForPain < 90) {
                DetailFrag_5.pain = 9;
            } else if (velocityForPain < 100) {
                DetailFrag_5.pain = 10;
            } else if (velocityForPain < 110) {
                DetailFrag_5.pain = 11;
            } else if (velocityForPain < 120) {
                DetailFrag_5.pain = 12;
            } else if (velocityForPain < 130) {
                DetailFrag_5.pain = 13;
            } else if (velocityForPain < 140) {
                DetailFrag_5.pain = 14;
            } else if (velocityForPain < 150) {
                DetailFrag_5.pain = 15;
            } else if (velocityForPain < 160) {
                DetailFrag_5.pain = 16;
            } else if (velocityForPain < 170) {
                DetailFrag_5.pain = 17;
            } else if (velocityForPain < 180) {
                DetailFrag_5.pain = 18;
            } else {
                DetailFrag_5.pain = 19;
            }

            if ("Extension Lag Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
                if ("active".equalsIgnoreCase(activepassive)) {
                    DetailFrag_5.activeeds.add(minAndMaxAngles.second);
                } else {
                    DetailFrag_5.passiveeds.add(minAndMaxAngles.second);
                }
            } else if ("Mobility Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
                if ("right".equalsIgnoreCase(leg)) {
                    DetailFrag_5.activeeds.add(minAndMaxAngles.second);
                } else {
                    DetailFrag_5.passiveeds.add(minAndMaxAngles.second);
                }
            } else if ("Proprioception Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
                if ("left".equalsIgnoreCase(legindication)) {
                    DetailFrag_5.leftrom.add(minAndMaxAngles.second);
                } else {
                    DetailFrag_5.rightrom.add(minAndMaxAngles.second);
                }
                DetailFrag_5.indivimaxAngle.add(minAndMaxAngles.second);
                Log.e("Indviminmaxangle1", String.valueOf(DetailFrag_5.leftrom));
                Log.e("Indviminmaxangle2", String.valueOf(DetailFrag_5.rightrom));
                if (DetailFrag_5.leftrom.size() > 0 && DetailFrag_5.rightrom.size() > 0) {
                    DetailFrag_5.indivimaxAngle.clear();
                    DetailFrag_5.proprom.clear();
                    if (DetailFrag_5.leftrom.size() >= DetailFrag_5.rightrom.size()) {
                        for (int i = 0; i < DetailFrag_5.rightrom.size(); i++) {
                            DetailFrag_5.proprom.add(Math.abs(DetailFrag_5.leftrom.get(i) + DetailFrag_5.rightrom.get(i)) / 2);
                            DetailFrag_5.indivimaxAngle.add(Math.abs(DetailFrag_5.leftrom.get(i) + DetailFrag_5.rightrom.get(i)) / 2);
                        }
                    } else {
                        for (int i = 0; i < DetailFrag_5.leftrom.size(); i++) {
                            DetailFrag_5.proprom.add(Math.abs(DetailFrag_5.leftrom.get(i) + DetailFrag_5.rightrom.get(i)) / 2);
                            DetailFrag_5.indivimaxAngle.add(Math.abs(DetailFrag_5.leftrom.get(i) + DetailFrag_5.rightrom.get(i)) / 2);
                        }
                    }
                }
                Log.e("Proprom", String.valueOf(DetailFrag_5.proprom));
            } else if ("Staircase Climbing Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
                if (minAndMaxAngles.second > 50 && ascentflag == 1 && descentflag == 0) {

                    DetailFrag_5.ascentend = DetailFrag_5.endind;

                    DetailFrag_5.stepCount += 1;
                    Log.e("YOYO Inba Ascent", minAndMaxAngles.second + " / " + DetailFrag_5.staircasetime.get(DetailFrag_5.ascentend) + " / " + subarray);
                } else if (minAndMaxAngles.second <= 50 && ascentflag == 1 && descentflag == 0) {
                    if (DetailFrag_5.turnflag == 0) {
                        DetailFrag_5.turnstart = DetailFrag_5.startind;

                        DetailFrag_5.turnflag = 1;
                    }
                    Log.e("YOYO Inba Turn", minAndMaxAngles.second + " / " + " / " + DetailFrag_5.staircasetime.get(DetailFrag_5.turnstart) + " / " + DetailFrag_5.staircasetime.get(DetailFrag_5.turnend) + " / " + subarray);
                    DetailFrag_5.turnend = DetailFrag_5.endind;
                    ascentflag = 0;
                } else if (minAndMaxAngles.second > 50 && ascentflag == 1 && DetailFrag_5.turnflag == 1) {
                    if (DetailFrag_5.descentflag == 0) {
                        DetailFrag_5.descentstart = DetailFrag_5.startind;

                        DetailFrag_5.descentflag = 1;

                    }
                    DetailFrag_5.stepCount += 1;
                    DetailFrag_5.descentend = DetailFrag_5.endind;
                    descentflag = 1;
                    Log.e("YOYO Inba Descent", minAndMaxAngles.second + " / " + DetailFrag_5.staircasetime.get(DetailFrag_5.descentstart) + " / " + DetailFrag_5.staircasetime.get(DetailFrag_5.descentend) + " / " + subarray);

                }
                Log.e("STAIRCASE CLIMBING TIMESTAMPS Stepcount", String.valueOf(subarray));
                Log.e("STAIRCASE CLIMBING TIMESTAMPS Stepcount", String.valueOf(DetailFrag_5.stepCount));

            } else {
                DetailFrag_5.proprom.add(minAndMaxAngles.second);
                DetailFrag_5.indivimaxAngle.add(minAndMaxAngles.second);
            }

            //Log.e("Indviminmaxangle", String.valueOf(DetailFrag_5.indiviminAngle));
            DetailFrag_5.indiviflexionVelocities.add((float) Math.round(velocityForPain));
            DetailFrag_5.indiviextensionVelocities.add((float) Math.round(velocityForPain));


            DetailFrag_5.indiviminAngle.add(minAndMaxAngles.first);
            DetailFrag_5.indivipain.add(DetailFrag_5.pain);


            if (!"Staircase Climbing Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
                try {
                    if (!Float.isNaN(velocityForPain) && !Float.isInfinite(velocityForPain)) {
                        DetailFrag_5.analysereportarray.put(Math.round(velocityForPain));
                        DetailFrag_5.postexeparameters.put(Math.round(velocityForPain));
                        DetailFrag_5.analysereportarray.put(Math.round(velocityForPain));
                    } else {
                        DetailFrag_5.analysereportarray.put(0); // Default if value is invalid
                        DetailFrag_5.analysereportarray.put(0);
                        DetailFrag_5.postexeparameters.put(0);
                    }
                    DetailFrag_5.analysereportarray.put("12");
                    DetailFrag_5.postexeparameters.put(DetailFrag_5.pain);
                    DetailFrag_5.reportobject.put("data", DetailFrag_5.datareportarray);
                    DetailFrag_5.reportobject.put("analyse", DetailFrag_5.analysereportarray);
                    DetailFrag_5.reportobject.put("exercisename", DetailFrag_5.selectedExercise);
                    if ("Mobility Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
                        DetailFrag_5.reportobject.put("mode", leg + "/" + activepassive);
                    } else if ("Extension Lag Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
                        DetailFrag_5.reportobject.put("mode", leg + "-" + activepassive);
                    } else if ("Dynamic Balance Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
                        DetailFrag_5.reportobject.put("mode", leg + "-" + wos);
                    } else if ("Static Balance Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
                        if ("active".equalsIgnoreCase(activepassive)) {
                            wos = "Eyes Open";
                        } else {
                            wos = "Eyes Closed";
                        }
                        DetailFrag_5.reportobject.put("mode", leg + "-" + wos);
                    } else {
                        if ("Proprioception Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
                            DetailFrag_5.reportobject.put("mode", legindication);
                        }
                    }
                    DetailFrag_5.reportarray.put(DetailFrag_5.reportobject);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        } else if ("Staircase Climbing Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
            if ((minAndMaxAngles.second - minAndMaxAngles.first) > 10) {
                if (minAndMaxAngles.second > 40 && descentflag == 0 && turnflag == 0 && ascentflag != 0) {

                    if (DetailFrag_5.ascentstart == 0) {
                        DetailFrag_5.ascentstart = DetailFrag_5.startind;
                    }

                    DetailFrag_5.ascentend = DetailFrag_5.endind;

                    DetailFrag_5.stepCount += 1;
                    Log.e("YOYO Inba Ascent", minAndMaxAngles.second + " / " + DetailFrag_5.timestamps.get(DetailFrag_5.ascentend) + " / " + subarray);
                } else if (minAndMaxAngles.second <= 45 && ascentflag == 1 && descentflag == 0) {
                    if (DetailFrag_5.turnflag == 0) {
                        DetailFrag_5.turnstart = DetailFrag_5.startind;

                        DetailFrag_5.turnflag = 1;
                    }
                    Log.e("YOYO Inba Turn", minAndMaxAngles.second + " / " + " / " + DetailFrag_5.timestamps.get(DetailFrag_5.turnstart) + " / " + DetailFrag_5.timestamps.get(DetailFrag_5.turnend) + " / " + subarray);
                    DetailFrag_5.turnend = DetailFrag_5.endind;
                    ascentflag = 0;
                } else if (minAndMaxAngles.second > 45 && ascentflag == 0 && turnflag == 1) {
                    if (DetailFrag_5.descentflag == 0) {
                        DetailFrag_5.descentstart = DetailFrag_5.startind;

                        DetailFrag_5.descentflag = 1;

                    }
                    DetailFrag_5.stepCount += 1;
                    DetailFrag_5.descentend = DetailFrag_5.endind;
                    descentflag = 1;
                    Log.e("YOYO Inba Descent", minAndMaxAngles.second + " / " + DetailFrag_5.timestamps.get(DetailFrag_5.descentstart) + " / " + DetailFrag_5.timestamps.get(DetailFrag_5.descentend) + " / " + subarray);

                }

//            Log.e("STAIRCASE CLIMBING TIMESTAMPS Stepcount", String.valueOf(subarray));
//            Log.e("STAIRCASE CLIMBING TIMESTAMPS Stepcount", String.valueOf(DetailFrag_5.stepCount));


                DetailFrag_5.indiviminAngle.add(minAndMaxAngles.first);
                DetailFrag_5.indivipain.add(DetailFrag_5.pain);

                Log.e("Staircase filtered", DetailFrag_5.timestamps.get(DetailFrag_5.startind) + " / " + DetailFrag_5.timestamps.get(DetailFrag_5.endind));
            }
        }
        //Log.d("Complete Report JSON", DetailFrag_5.reportarray.toString());
    }

    private Pair<Float, Float> findMinMaxAngles(List<Float> values) {
        float min = Collections.min(values);
        float max = Collections.max(values);
        return new Pair<>(min, max);
    }

//    @Override
//    public void onItemClick(int position) {
//
//        List<Entry> existingEntries = new ArrayList<>();
//        LineData lineData = lineChart.getData(); // Assuming there's only one dataset
//        for (int i = 0; i < lineData.getDataSetCount(); i++) {
//            LineDataSet dataSet = (LineDataSet) lineData.getDataSetByIndex(i);
//            if (dataSet.getLabel().equals("Overlapped")) {
//                lineData.removeDataSet(i);
//                break;
//            }
//        }
//
//
//
//        int index = position;
//
//        Log.e("Line Chart Data", String.valueOf(lineChart.getData().getDataSetByIndex(l)));
//        l++;
//
//        List<Entry> newEntries = generateEntriesFromData(highlightArray.get(index), existingEntries);
//        Log.e("HighlightArray sub", String.valueOf(highlightArray.get(index)));
//
//        LineDataSet newDataSet = new LineDataSet(newEntries, "Overlapped");
//        newDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER); // Waved line
//        newDataSet.setDrawValues(true); // Hide values
//        newDataSet.setColor(Color.RED); // Different color for new line
//        newDataSet.setLineWidth(2f);
//
//        lineData.addDataSet(newDataSet);
//        lineChart.setData(lineData);
//        lineChart.notifyDataSetChanged();
//        lineChart.invalidate();
//    }

    // Optionally, if you want to reset the timer
    private void resetTimer() {
        pauseTimer();
        isTimerRunning = false;
        // Show the NumberPickers again
        pickerContainer.setVisibility(View.VISIBLE);
        textContainer.setVisibility(View.GONE);
        textContainer.setVisibility(View.GONE);
        // Reset play/pause button icon to play
        ImageView centerButton = findViewById(R.id.center_button);
        centerButton.setImageResource(R.drawable.baseline_play_arrow_24);
    }

//    private List<ExerciseAssessment> getExerciseList() {
//        List<ExerciseAssessment> exerciseList = new ArrayList<>();
//        String[] exerciseNames = getResources().getStringArray(R.array.exercise_list);
//        exerciseList.add(new ExerciseAssessment(exerciseNames[0], R.drawable.assess_exercise_1));
//        exerciseList.add(new ExerciseAssessment(exerciseNames[1], R.drawable.event_icon));
//        exerciseList.add(new ExerciseAssessment(exerciseNames[2], R.drawable.assess_exercise_1));
//        exerciseList.add(new ExerciseAssessment(exerciseNames[3], R.drawable.event_icon));
//
//
//        return exerciseList;
//    }

    private List<ExerciseCycleAssessment> getExerciseCycleList() {
        DetailFrag_5.exerciseListact = new ArrayList<>();


        DetailFrag_5.exerciseListact.clear();

        //Log.e("Inside getindvidata", String.valueOf(DetailFrag_5.highlightArray.size()));


        int max = 0;

        if ("Extension Lag Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
            for (int k = 0; k < DetailFrag_5.highlightArraypass.size() - 1; k++) {
                int val = Math.round((DetailFrag_5.passiveeds.get(k)));
                Log.e("Cycle DAta", (String.valueOf(val)));
                DetailFrag_5.exerciseListact.add(new ExerciseCycleAssessment(val));
                DetailFrag_5.exepain.put(DetailFrag_5.indivipain.get(k));
                if ("Mobility Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
                    if (max < val) {
                        max = val;
                        maxrom = val;
                        cycleno = k + 1;

                    }
                }
            }
        }
//        else if("Mobility".equalsIgnoreCase(DetailFrag_5.selectedExercise)){
//            for (int k = 0; k < DetailFrag_5.highlightArraypass.size() - 1; k++) {
//                int val = Math.round((DetailFrag_5.passiveeds.get(k)));
//                Log.e("Cycle DAta", (String.valueOf(val)));
//                DetailFrag_5.exerciseListact.add(new ExerciseCycleAssessment(val));
//                DetailFrag_5.exepain.put(DetailFrag_5.indivipain.get(k));
//                if ("Mobility".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
//                    if (max < val) {
//                        max = val;
//                        maxrom = val;
//                        cycleno = k + 1;
//
//                    }
//                }
//            }
//        }
        else {
            if (DetailFrag_5.indivimaxAngle.size() > 0) {
                for (int k = 0; k < DetailFrag_5.highlightArraypass.size() - 1; k++) {
                    int val = Math.round((DetailFrag_5.indivimaxAngle.get(k)));
                    Log.e("Cycle DAta", (String.valueOf(val)));
                    DetailFrag_5.exerciseListact.add(new ExerciseCycleAssessment(val));
                    DetailFrag_5.exepain.put(DetailFrag_5.indivipain.get(k));
                    if ("Mobility Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
                        if (max < val) {
                            max = val;
                            maxrom = val;
                            cycleno = k + 1;

                        }
                    }
                }
            }
        }


        exerom = 10;

        if (DetailFrag_5.subexedata.length() == 0) {
            if (!DetailFrag_5.exename.isEmpty() && DetailFrag_5.exevalue.length() != 0 && DetailFrag_5.exepain.length() != 0) {
                try {
                    DetailFrag_5.subexedata.put("values", DetailFrag_5.exevalue);
                    DetailFrag_5.subexedata.put("pain", DetailFrag_5.exepain);
                    int maxValue = Integer.MIN_VALUE;

                    // Loop through the array to find the maximum value
                    for (int i = 0; i < DetailFrag_5.exevalue.length(); i++) {
                        int currentValue = DetailFrag_5.exevalue.getInt(i);
                        if (currentValue > maxValue) {
                            maxValue = currentValue;
                        }
                    }
                    DetailFrag_5.subexedata.put("rom", maxValue);
                    DetailFrag_5.subexedata.put("velocity", 0);
                    Log.e("SubExeData", String.valueOf(DetailFrag_5.selectedExercise));
                    DetailFrag_5.exedata.put(DetailFrag_5.selectedExercise, DetailFrag_5.subexedata);
//                    if(exedata.length() == totaltask){
//                        submit.setVisibility(View.VISIBLE);
//                    }else{
//                        submit.setVisibility(View.INVISIBLE);
//                    }
                    Log.e("ExeData", pos + String.valueOf(DetailFrag_5.exedata.toString()));
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            } else {
                Log.e("Error Empty Data in SubExe ", String.valueOf(DetailFrag_5.exename.isEmpty()) + " " + DetailFrag_5.exevalue.length() + " " + DetailFrag_5.exepain.length());
            }
        }

        Log.e("Inside getindvidata1", String.valueOf(DetailFrag_5.indiviCardData));
        return DetailFrag_5.exerciseListact;
    }

    private List<ExerciseCycleAssessment> getExerciseCycleList1() {
        DetailFrag_5.exerciseListact = new ArrayList<>();


        DetailFrag_5.exerciseListact.clear();

        //Log.e("Inside getindvidata", String.valueOf(DetailFrag_5.highlightArray.size()));

        //Log.e("Madmax", String.valueOf(DetailFrag_5.indivimaxAngle));
        int max = 0;

        for (int k = 0; k < DetailFrag_5.highlightArrayact.size() - 1; k++) {

            int val = Math.round((DetailFrag_5.activeeds.get(k)));
            Log.e("Cycle DAta", (String.valueOf(val)));
            DetailFrag_5.exerciseListact.add(new ExerciseCycleAssessment(val));
            DetailFrag_5.exepain.put(DetailFrag_5.indivipain.get(k));
            if ("Mobility Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
                if (max < val) {
                    max = val;
                    maxrom = val;
                    cycleno = k + 1;

                }
            }
        }

        exerom = 10;

        if (DetailFrag_5.subexedata.length() == 0) {
            if (!DetailFrag_5.exename.isEmpty() && DetailFrag_5.exevalue.length() != 0 && DetailFrag_5.exepain.length() != 0) {
                try {
                    DetailFrag_5.subexedata.put("values", DetailFrag_5.exevalue);
                    DetailFrag_5.subexedata.put("pain", DetailFrag_5.exepain);
                    int maxValue = Integer.MIN_VALUE;

                    // Loop through the array to find the maximum value
                    for (int i = 0; i < DetailFrag_5.exevalue.length(); i++) {
                        int currentValue = DetailFrag_5.exevalue.getInt(i);
                        if (currentValue > maxValue) {
                            maxValue = currentValue;
                        }
                    }
                    DetailFrag_5.subexedata.put("rom", maxValue);
                    DetailFrag_5.subexedata.put("velocity", 0);
                    Log.e("SubExeData", String.valueOf(DetailFrag_5.selectedExercise));
                    DetailFrag_5.exedata.put(DetailFrag_5.selectedExercise, DetailFrag_5.subexedata);
//                    if(exedata.length() == totaltask){
//                        submit.setVisibility(View.VISIBLE);
//                    }else{
//                        submit.setVisibility(View.INVISIBLE);
//                    }
                    Log.e("ExeData", pos + String.valueOf(DetailFrag_5.exedata.toString()));
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            } else {
                Log.e("Error Empty Data in SubExe ", String.valueOf(DetailFrag_5.exename.isEmpty()) + " " + DetailFrag_5.exevalue.length() + " " + DetailFrag_5.exepain.length());
            }
        }

        Log.e("Inside getindvidata1", String.valueOf(DetailFrag_5.indiviCardData));
        return DetailFrag_5.exerciseListact;
    }

    private void setupLineChart() {
        // Create a list of entries for the line chart
//        ArrayList<Entry> entries = new ArrayList<>();
//        entries.add(new Entry(0, 1)); // Example values (x, y)
//        entries.add(new Entry(1, 3));
//        entries.add(new Entry(2, 2));
//        entries.add(new Entry(3, 5));
//        entries.add(new Entry(4, 4));

//        Log.e("Inside line chart", String.valueOf(DetailFrag_5.entries));
        // Create a dataset and give it a type
        LineDataSet dataSet = new LineDataSet(DetailFrag_5.entries, "Exercise Data"); // Name of the dataset
        dataSet.setColor(Color.BLUE); // Set color of the line
        dataSet.setValueTextColor(Color.BLACK); // Set color of the value text
        dataSet.setDrawCircles(true); // Enable drawing circles on data points
        dataSet.setCircleColor(Color.BLUE); // Set circle color
        dataSet.setCircleRadius(4f); // Set circle radius
        dataSet.setDrawValues(false); // Disable drawing values on the data points

        // Enable cubic lines
        dataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        dataSet.setCubicIntensity(0.2f); // Set intensity for the cubic lines
        dataSet.setLineWidth(2f);

        // Create LineData with the dataset
        LineData lineData = new LineData(dataSet);

        // Configure the chart
        DetailFrag_5.lineChart.setData(lineData);
        DetailFrag_5.lineChart.setTouchEnabled(true); // Enable touch interaction
        DetailFrag_5.lineChart.setDragEnabled(true); // Enable dragging
        DetailFrag_5.lineChart.setScaleEnabled(true); // Enable scaling

        // Customize chart appearance
        DetailFrag_5.lineChart.getAxisRight().setEnabled(true);
        DetailFrag_5.lineChart.getAxisLeft().setDrawGridLines(false);
        DetailFrag_5.lineChart.getXAxis().setDrawGridLines(false);
        DetailFrag_5.lineChart.getDescription().setEnabled(false); // Disable description
        DetailFrag_5.lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        // Position the legend in the top far right corner
        Legend legend = DetailFrag_5.lineChart.getLegend();
        legend.setEnabled(true); // Enable legend
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setDrawInside(false); // Ensure it is drawn outside the chart area

        // Add extra offset for the legend gap
        DetailFrag_5.lineChart.setExtraOffsets(0, 20, 0, 0); // Adjust the second parameter to control the gap

        // Animation and refresh
        DetailFrag_5.lineChart.animateX(1000); // Add animation
        DetailFrag_5.lineChart.notifyDataSetChanged(); // Refresh the chart
    }

    private void setNumberPickerTextColor(NumberPicker numberPicker, int color) {
        // Set text color for NumberPicker
        for (int i = 0; i < numberPicker.getChildCount(); i++) {
            View child = numberPicker.getChildAt(i);
            if (child instanceof EditText) {
                ((EditText) child).setTextColor(color);
            }
        }
    }

    // Existing methods (connectToDevice, readDataFromDevice, etc.) remain unchanged

    @Override
    public void onItemClick(int position) {

        List<Entry> existingEntries = new ArrayList<>();
        DetailFrag_5.lineData1 = DetailFrag_5.lineChart.getData(); // Assuming there's only one dataset
        Log.e("TAG UNIQUE AH ", String.valueOf(existingEntries));
        for (int i = 0; i < DetailFrag_5.lineData1.getDataSetCount(); i++) {
            LineDataSet dataSet = (LineDataSet) DetailFrag_5.lineData1.getDataSetByIndex(i);
            Log.e("TAG UNIQUE AH KUDU", String.valueOf(dataSet));
            if (dataSet.getLabel().equals("Overlapped")) {
                DetailFrag_5.lineData1.removeDataSet(i);
                break;
            }
        }


        int index = position;

        Log.e("Line Chart Data", String.valueOf(DetailFrag_5.lineChart.getData().getDataSetByIndex(l)));
        l++;
        List<Entry> newEntries;
        if ("Extension Lag Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
            if ("active".equalsIgnoreCase(activepassive)) {
                newEntries = generateEntriesFromData(DetailFrag_5.highlightArrayact.get(index), existingEntries);
            } else {
                newEntries = generateEntriesFromData(DetailFrag_5.highlightArraypass.get(index), existingEntries);
            }
        } else {
            if ("Mobility Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
                newEntries = generateEntriesFromData(DetailFrag_5.highlightArrayact.get(index), existingEntries);
            } else {
                newEntries = generateEntriesFromData(DetailFrag_5.highlightArraypass.get(index), existingEntries);
            }
        }


        //Log.e("HighlightArray sub", String.valueOf(newEntries));


        LineDataSet newDataSet = new LineDataSet(newEntries, "Overlapped");
        newDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER); // Waved line
        newDataSet.setDrawValues(true); // Hide values
        newDataSet.setColor(Color.RED); // Different color for new line
        newDataSet.setLineWidth(2f);

        DetailFrag_5.lineData1.addDataSet(newDataSet);
        DetailFrag_5.lineChart.setData(DetailFrag_5.lineData1);
        DetailFrag_5.lineChart.notifyDataSetChanged();
        DetailFrag_5.lineChart.notifyDataSetChanged();
    }

    private List<Entry> generateEntriesFromData(List<Entry> tempRow, List<Entry> existingEntries) {
        List<Entry> entries = new ArrayList<>();
        Log.e("Temp Row Data", String.valueOf(tempRow));

//        // Calculate the offset to align the new entries with existing entries
        float xOffset = tempRow.isEmpty() ? 0 : tempRow.get(0).getX();
        float xOffset1 = existingEntries.isEmpty() ? 0 : existingEntries.get(0).getX();
        //Log.e("xOffset", String.valueOf(existingEntries.get(0).getX()));
        Log.e("existingEntries", String.valueOf(existingEntries));
//
        for (int k = 0; k < tempRow.size(); k++) {
            float xValue = xOffset + k; // Use xOffset to align new entries with existing entries
            float yValue = tempRow.get(k).getY();

            Log.e("xOffset0", String.valueOf(xOffset + k));
            Log.e("xOffset1", String.valueOf(xOffset1 + k));

            entries.add(new Entry(xValue, yValue));
        }
//
//        Log.e("Generated Entries", String.valueOf(entries));
        return entries;
    }

//    private static class Metric {
//        int index;
//        float val;
//
//        Metric(int index, float val) {
//            this.index = index;
//            this.val = val;
//        }
//        public float getVal() {
//            return val;
//        }
//    }

//    private void sendDataToServer(JSONObject data) {
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        flag = 0;
//
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
//                Request.Method.PUT,
//                "https://api-backup-vap2.onrender.com/update-exercise-info/" + DetailFrag_5.userid + "/" + flag,
//                data,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        // Handle the response
//                        try {
//                            if ("Exercise information updated successfully".equals(response.get("message").toString())) {
//                                DetailFrag_5.flag = 0;
//                                Intent intent = new Intent(Assessment.this, PatLoading.class);
//                                startActivity(intent);
//                            }
//                            Log.d("Exercise Submit Response", response.get("message").toString());
//                        } catch (JSONException e) {
//                            throw new RuntimeException(e);
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        // Handle the error
//                        Log.e("Exercise Submit Error", error.toString());
//                    }
//                }) {
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//                params.put("Content-Type", "application/json");
//                return params;
//            }
//        };
//
//        // Add the request to the RequestQueue
//        requestQueue.add(jsonObjectRequest);
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        BluetoothConnectionManager.getInstance().handleAllDisconnections();
        isRunning = false; // Stop the thread
        speedometer1.clearAnimation();
        speedometer2.clearAnimation();
    }

    @SuppressLint("MissingInflatedId")
    private void showConfirmationDialog() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View customView = inflater.inflate(R.layout.assess_test_dialogue, null);

        TextView cycle_count, range_of_motion, active_ed, time, maximum_rom, cyclerom, sitstand, standshift, walktime, stepscovered, ascenttime, decenttime, turntime;
        LinearLayout first_linear, second_linear, sit_to_stand, stand_to_shift, walk_time, steps_covered, ascent_time, decent_time, turn_time;

        AppCompatButton button_no, button_yes;

        cycle_count = customView.findViewById(R.id.cycle_count);
        range_of_motion = customView.findViewById(R.id.range_of_motion);
        active_ed = customView.findViewById(R.id.active_ed);
        time = customView.findViewById(R.id.time);
        maximum_rom = customView.findViewById(R.id.maximum_rom);
        first_linear = customView.findViewById(R.id.first_linear);
        second_linear = customView.findViewById(R.id.second_linear);
        sit_to_stand = customView.findViewById(R.id.sit_to_stand);
        stand_to_shift = customView.findViewById(R.id.stand_to_shift);
        walk_time = customView.findViewById(R.id.walk_time);
        steps_covered = customView.findViewById(R.id.steps_covered);
        ascent_time = customView.findViewById(R.id.ascent_time);
        decent_time = customView.findViewById(R.id.decent_time);
        button_yes = customView.findViewById(R.id.button_yes);
        button_no = customView.findViewById(R.id.button_no);
        cyclerom = customView.findViewById(R.id.cyclerom1);
        sitstand = customView.findViewById(R.id.sitstand);
        standshift = customView.findViewById(R.id.standshift);
        walktime = customView.findViewById(R.id.walktime);
        stepscovered = customView.findViewById(R.id.stepcovered);
        ascenttime = customView.findViewById(R.id.ascenttime);
        decenttime = customView.findViewById(R.id.decenttime);
        turntime = customView.findViewById(R.id.turntime);
        turn_time = customView.findViewById(R.id.turn_time);

        if ("Mobility Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
            first_linear.setVisibility(View.VISIBLE);
            second_linear.setVisibility(View.VISIBLE);
            cycle_count.setVisibility(View.VISIBLE);
            range_of_motion.setVisibility(View.VISIBLE);
            active_ed.setVisibility(View.GONE);
            time.setVisibility(View.GONE);
            maximum_rom.setVisibility(View.GONE);
            sit_to_stand.setVisibility(View.GONE);
            stand_to_shift.setVisibility(View.GONE);
            walk_time.setVisibility(View.GONE);
            steps_covered.setVisibility(View.GONE);
            ascent_time.setVisibility(View.GONE);
            decent_time.setVisibility(View.GONE);
            turn_time.setVisibility(View.GONE);
            cyclerom.setText(String.valueOf(maxrom));
            cycle_count.setText("Cycle " + cycleno);
        } else if ("Extension Lag Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
            first_linear.setVisibility(View.VISIBLE);
            second_linear.setVisibility(View.VISIBLE);
            cycle_count.setVisibility(View.VISIBLE);
            range_of_motion.setVisibility(View.GONE);
            active_ed.setVisibility(View.VISIBLE);
            time.setVisibility(View.GONE);
            maximum_rom.setVisibility(View.GONE);
            sit_to_stand.setVisibility(View.GONE);
            stand_to_shift.setVisibility(View.GONE);
            walk_time.setVisibility(View.GONE);
            steps_covered.setVisibility(View.GONE);
            ascent_time.setVisibility(View.GONE);
            decent_time.setVisibility(View.GONE);
            turn_time.setVisibility(View.GONE);
            cyclerom.setText(String.valueOf(maxrom1));
            cycle_count.setText("Cycle " + cycleno);
        } else if ("Dynamic Balance Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
            first_linear.setVisibility(View.VISIBLE);
            second_linear.setVisibility(View.GONE);
            cycle_count.setVisibility(View.VISIBLE);
            range_of_motion.setVisibility(View.GONE);
            active_ed.setVisibility(View.GONE);
            time.setVisibility(View.GONE);
            maximum_rom.setVisibility(View.GONE);
            sit_to_stand.setVisibility(View.VISIBLE);
            stand_to_shift.setVisibility(View.VISIBLE);
            walk_time.setVisibility(View.VISIBLE);
            steps_covered.setVisibility(View.GONE);
            ascent_time.setVisibility(View.GONE);
            decent_time.setVisibility(View.GONE);
            turn_time.setVisibility(View.GONE);
            sitstand.setText(String.valueOf(sitstandtime));
            standshift.setText(String.valueOf(standshifttime));
            walktime.setText(String.valueOf(walkt));
            cycle_count.setText("Cycle " + cycleno);
        } else if ("Static Balance Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
            first_linear.setVisibility(View.VISIBLE);
            second_linear.setVisibility(View.VISIBLE);
            cycle_count.setVisibility(View.VISIBLE);
            range_of_motion.setVisibility(View.GONE);
            active_ed.setVisibility(View.GONE);
            time.setVisibility(View.VISIBLE);
            maximum_rom.setVisibility(View.GONE);
            sit_to_stand.setVisibility(View.GONE);
            stand_to_shift.setVisibility(View.GONE);
            walk_time.setVisibility(View.GONE);
            steps_covered.setVisibility(View.GONE);
            ascent_time.setVisibility(View.GONE);
            decent_time.setVisibility(View.GONE);
            turn_time.setVisibility(View.GONE);
            cyclerom.setText(String.valueOf(staticbaltime) + " Sec");
            cyclerom.setTextSize(30);
            cycle_count.setText("Cycle " + cycleno);
        } else if ("Staircase Climbing Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
            first_linear.setVisibility(View.VISIBLE);
            second_linear.setVisibility(View.GONE);
            cycle_count.setVisibility(View.VISIBLE);
            range_of_motion.setVisibility(View.GONE);
            active_ed.setVisibility(View.GONE);
            time.setVisibility(View.GONE);
            maximum_rom.setVisibility(View.GONE);
            sit_to_stand.setVisibility(View.GONE);
            stand_to_shift.setVisibility(View.GONE);
            walk_time.setVisibility(View.GONE);
            steps_covered.setVisibility(View.VISIBLE);
            ascent_time.setVisibility(View.VISIBLE);
            decent_time.setVisibility(View.VISIBLE);
            turn_time.setVisibility(View.VISIBLE);
            stepscovered.setText(String.valueOf(maxrom));
            ascenttime.setText(String.valueOf(sitstandtime));
            decenttime.setText(String.valueOf(standshifttime));
            turntime.setText(String.valueOf(walkt));
            cycle_count.setText("Cycle " + cycleno);
        } else if ("Proprioception Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
            first_linear.setVisibility(View.VISIBLE);
            second_linear.setVisibility(View.VISIBLE);
            cycle_count.setVisibility(View.VISIBLE);
            range_of_motion.setVisibility(View.GONE);
            active_ed.setVisibility(View.GONE);
            time.setVisibility(View.GONE);
            maximum_rom.setVisibility(View.VISIBLE);
            sit_to_stand.setVisibility(View.GONE);
            stand_to_shift.setVisibility(View.GONE);
            walk_time.setVisibility(View.GONE);
            steps_covered.setVisibility(View.GONE);
            ascent_time.setVisibility(View.GONE);
            decent_time.setVisibility(View.GONE);
            turn_time.setVisibility(View.GONE);
            cyclerom.setText(String.valueOf(maxrom));
            cycle_count.setText("Cycle " + cycleno);
        } else if ("Walk and Gait analysis".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
            first_linear.setVisibility(View.VISIBLE);
            second_linear.setVisibility(View.VISIBLE);
            cycle_count.setVisibility(View.VISIBLE);
            range_of_motion.setVisibility(View.VISIBLE);
            active_ed.setVisibility(View.GONE);
            time.setVisibility(View.GONE);
            maximum_rom.setVisibility(View.GONE);
            sit_to_stand.setVisibility(View.GONE);
            stand_to_shift.setVisibility(View.GONE);
            walk_time.setVisibility(View.GONE);
            steps_covered.setVisibility(View.GONE);
            ascent_time.setVisibility(View.GONE);
            decent_time.setVisibility(View.GONE);
            turn_time.setVisibility(View.GONE);
        }


        AlertDialog dialog = new AlertDialog.Builder(this)
                .setView(customView)
                .create();

        Log.e("Reset Data check", String.valueOf(DetailFrag_5.postexesubdata));

        button_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailFrag_5.postexesubdata = new JSONObject();
                DetailFrag_5.postexedata = new JSONObject();
                left_underlined.setVisibility(View.VISIBLE);
                right_underlined.setVisibility(View.INVISIBLE);
                leg = "left";
                switch_button.setChecked(false);
                activepassive = "active";
                DetailFrag_5.lineChart.clear();
                DetailFrag_5.cyclecount = 0;

                if ("Extension Lag Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
                    DetailFrag_5.exerciseListpass.clear();
                    DetailFrag_5.indiviCardAdapterpass.notifyDataSetChanged();
                    DetailFrag_5.exerciseListact.clear();
                    DetailFrag_5.indiviCardAdapteract.notifyDataSetChanged();
                } else if ("Dynamic Balance Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
                    DetailFrag_5.dynamicbalancetestdata.clear();
                    DetailFrag_5.dynamicbalanceadapter.notifyDataSetChanged();
                } else if ("Static Balance Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
                    staticbalancetestdata.clear();
                    DetailFrag_5.staticbalancetestadapter.notifyDataSetChanged();
                } else if ("Staircase Climbing Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
                    staircaseclimbingtestdata.clear();
                    DetailFrag_5.staircaseclimbingadapter.notifyDataSetChanged();
                } else if ("Mobility Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
                    DetailFrag_5.mobilityCycleAssessments.clear();
                    DetailFrag_5.mobilityCycleAdapter.notifyDataSetChanged();
                } else if ("Proprioception Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
                    DetailFrag_5.exerciseListact.clear();
                    DetailFrag_5.proprioceptionAdapter.notifyDataSetChanged();
                }
                dialog.dismiss();
            }
        });
        button_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //DetailFrag_5.postdataobj = new JSONObject();

                    DetailFrag_5.propriolegswitchflag = 0;
                    DetailFrag_5.extensionlagCycleAssessmentsleft.clear();
                    DetailFrag_5.extensionlagCycleAssessmentsright.clear();
                    DetailFrag_5.extensionlagCycleAssessments.clear();

                    DetailFrag_5.postdataobj.put(DetailFrag_5.selectedExercise, DetailFrag_5.postexesubdata);
                    Log.e("The posting dynamic balance data", String.valueOf(DetailFrag_5.postexesubdata));
                    DetailFrag_5.postexesubdata = new JSONObject();
                    DetailFrag_5.postexedata = new JSONObject();
                    Intent intent = new Intent(Assessment.this, DetailFrag_5.class);
                    intent.putExtra("fragment_milestone", 5);
                    // Pass the updated values back
                    intent.putExtra("itemTitle", itemTitle);
                    intent.putExtra("itemStatus", "Completed");
                    intent.putExtra("itemColor", Color.GREEN);

                    // Start the AssessmentList activity with the updated data
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    Log.e("Inbasekar DATABASE DATA", String.valueOf(DetailFrag_5.postdata));
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

                dialog.dismiss();
            }
        });

        dialog.show();
    }

    @SuppressLint("MissingInflatedId")
    private void showConfirmationDialog1() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View customView = inflater.inflate(R.layout.assess_test_walk_gait, null);

        TextView distance, step_count, walk_break, active_time, machine_time, stand_time, avg_swing_time, stance_phase, stride_length, stride_length_percentage_h, step_length, mean_velocity, cadence;
        LinearLayout first_linear, second_linear, third_linear, fourth_linear, fifth_linear, sixth_linear, seventh_linear, eighth_linear, ninenth_linear, tenth_linear, eleventh_linear, tweleveth_linear, thirteenth_linear, fourteenth_linear;

        AppCompatButton button_no, button_yes;
        button_no = customView.findViewById(R.id.button_no);
        button_yes = customView.findViewById(R.id.button_yes);


        AlertDialog dialog = new AlertDialog.Builder(this)
                .setView(customView)
                .create();

        Log.e("Reset Data check", String.valueOf(DetailFrag_5.postexesubdata));

        button_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailFrag_5.postexesubdata = new JSONObject();
                DetailFrag_5.postexedata = new JSONObject();
                left_underlined.setVisibility(View.VISIBLE);
                right_underlined.setVisibility(View.INVISIBLE);
                leg = "left";
                leg = "left";
                switch_button.setChecked(false);
                DetailFrag_5.cyclecount = 0;
                DetailFrag_5.lineChart.clear();
                DetailFrag_5.walkgaittestdata.clear();
                DetailFrag_5.walkgaittestadapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
        button_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    DetailFrag_5.postdataobj.put(DetailFrag_5.selectedExercise, DetailFrag_5.postexesubdata);
                    DetailFrag_5.postexesubdata = new JSONObject();
                    DetailFrag_5.postexedata = new JSONObject();
                    Intent intent = new Intent(Assessment.this, DetailFrag_5.class);
                    intent.putExtra("fragment_milestone", 5);
                    // Pass the updated values back
                    intent.putExtra("itemTitle", itemTitle);
                    intent.putExtra("itemStatus", "Completed");
                    intent.putExtra("itemColor", Color.GREEN);

                    // Start the AssessmentList activity with the updated data
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    Log.e("Inbasekar DATABASE DATA", String.valueOf(DetailFrag_5.postdata));
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public boolean validateJSONObject(JSONObject jsonObject) {
        try {
            Log.e("Sub data value", jsonObject.toString());
            if (jsonObject == null || jsonObject.length() == 0) {
                return false;
            }
            boolean leftPresent = false;
            boolean rightPresent = false;
            Iterator<String> keys = jsonObject.keys();
            if ("Mobility Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
                while (keys.hasNext()) {
                    String key = keys.next();

                    // Check if the key contains "left" or "right"
                    if (key.toLowerCase().contains("left")) {
                        leftPresent = true;
                        rightPresent = true;
                    }
                    if (key.toLowerCase().contains("right")) {
                        rightPresent = true;
                    }

                    // Break early if both are found
                    if (leftPresent && rightPresent) {
                        break;
                    }
                }

                // Provide feedback if any key is missing
//                if (!leftPresent) {
//                    Toasty.error(Assessment.this, "Missing data for left leg", Toast.LENGTH_LONG).show();
//                    return false;
//                }
//                if (!rightPresent) {
//                    Toasty.error(Assessment.this, "Missing data for right leg", Toast.LENGTH_LONG).show();
//                    return false;
//                }
            }

            while (keys.hasNext()) {
                Object value = jsonObject.get(keys.next());


                // Check if the key has a non-null value
                if (value == null || value.toString().isEmpty()) {
                    System.out.println("Key: " + keys.next() + " has no value or is empty.");
                    return false;
                }

                // If the value is a JSONObject, recursively validate it
                if (value instanceof JSONObject) {
                    if (!validateJSONObject((JSONObject) value)) {
                        return false;
                    }
                }

                // If the value is a JSONArray, validate its elements
                if (value instanceof JSONArray) {
                    if (!validateJSONArray((JSONArray) value)) {
                        return false;
                    }
                }
            }
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean validateJSONArray(JSONArray jsonArray) {
        try {
            if (jsonArray == null) {
                return false;
            }
            for (int i = 0; i < jsonArray.length(); i++) {
                Object element = jsonArray.get(i);

                // If the element is a JSONObject, validate it
                if (element instanceof JSONObject) {
                    if (!validateJSONObject((JSONObject) element)) {
                        return false;
                    }
                }

                // If the element is a JSONArray, validate it recursively
                if (element instanceof JSONArray) {
                    if (!validateJSONArray((JSONArray) element)) {
                        return false;
                    }
                }

                // Check if the element is null or empty
                if (element == null || element.toString().isEmpty()) {
                    System.out.println("Array element at index " + i + " is empty or null.");
                    return false;
                }
            }
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void mobilityanalysis(List<Entry> objectElements) {
        DetailFrag_5.tempRow = new ArrayList<>();
        DetailFrag_5.highlightArraypass = new ArrayList<>();
        DetailFrag_5.highlightArrayact = new ArrayList<>();
        Log.e("Mobility Inside Function", String.valueOf(objectElements));
        Log.e("Inside Function Size", String.valueOf(objectElements.size()));
        DetailFrag_5.reportobject = new JSONObject();
        DetailFrag_5.datareportarray = new JSONArray();
        DetailFrag_5.analysereportarray = new JSONArray();
        DetailFrag_5.mobilecyclecount++;

        testdata = new JSONArray();

        List<Long> indices = new ArrayList<>();
        List<Float> values = new ArrayList<>();
        for (Entry entry : objectElements) {
            indices.add((long) entry.getX());
            values.add(entry.getY());

            try {
                testdata.put(entry.getY());
                DetailFrag_5.postexevalues.put(entry.getY());
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }

        for (Float angle : values) {
            DetailFrag_5.datareportarray.put(angle);
        }

        Pair<Float, Float> minAndMaxAngles = findMinMaxAngles(values);

        if ("right".equalsIgnoreCase(leg)) {
            DetailFrag_5.activeeds.add(minAndMaxAngles.second);
            DetailFrag_5.passiveeds.add(minAndMaxAngles.first);
        } else {
            DetailFrag_5.passiveeds.add(minAndMaxAngles.second);
            DetailFrag_5.activeeds.add(minAndMaxAngles.first);
        }

        DetailFrag_5.analysereportarray.put(minAndMaxAngles.first);
        DetailFrag_5.postexeparameters.put(minAndMaxAngles.first);
        DetailFrag_5.analysereportarray.put(minAndMaxAngles.second);
        DetailFrag_5.postexeparameters.put(minAndMaxAngles.second);
        float velocityForPain = Math.abs((minAndMaxAngles.second - minAndMaxAngles.first) / (indices.get(indices.size() - 1) - indices.get(0))) +
                Math.abs((minAndMaxAngles.second + minAndMaxAngles.first) / (indices.get(indices.size() - 1) - indices.get(0)));

        if (velocityForPain < 10) {
            DetailFrag_5.pain = 1;
        } else if (velocityForPain < 20) {
            DetailFrag_5.pain = 2;
        } else if (velocityForPain < 30) {
            DetailFrag_5.pain = 3;
        } else if (velocityForPain < 40) {
            DetailFrag_5.pain = 4;
        } else if (velocityForPain < 50) {
            DetailFrag_5.pain = 5;
        } else if (velocityForPain < 60) {
            DetailFrag_5.pain = 6;
        } else if (velocityForPain < 70) {
            DetailFrag_5.pain = 7;
        } else if (velocityForPain < 80) {
            DetailFrag_5.pain = 8;
        } else if (velocityForPain < 90) {
            DetailFrag_5.pain = 9;
        } else if (velocityForPain < 100) {
            DetailFrag_5.pain = 10;
        } else if (velocityForPain < 110) {
            DetailFrag_5.pain = 11;
        } else if (velocityForPain < 120) {
            DetailFrag_5.pain = 12;
        } else if (velocityForPain < 130) {
            DetailFrag_5.pain = 13;
        } else if (velocityForPain < 140) {
            DetailFrag_5.pain = 14;
        } else if (velocityForPain < 150) {
            DetailFrag_5.pain = 15;
        } else if (velocityForPain < 160) {
            DetailFrag_5.pain = 16;
        } else if (velocityForPain < 170) {
            DetailFrag_5.pain = 17;
        } else if (velocityForPain < 180) {
            DetailFrag_5.pain = 18;
        } else {
            DetailFrag_5.pain = 19;
        }

        try {
            DetailFrag_5.analysereportarray.put(Math.round(velocityForPain));
            DetailFrag_5.postexeparameters.put(Math.round(velocityForPain));
            DetailFrag_5.analysereportarray.put(Math.round(velocityForPain));
            DetailFrag_5.analysereportarray.put("12");
            DetailFrag_5.postexeparameters.put(DetailFrag_5.pain);
            DetailFrag_5.reportobject.put("data", DetailFrag_5.datareportarray);
            DetailFrag_5.reportobject.put("analyse", DetailFrag_5.analysereportarray);
            DetailFrag_5.reportobject.put("exercisename", DetailFrag_5.selectedExercise);
            DetailFrag_5.reportobject.put("mode", leg + "leg-" + activepassive + "-" + DetailFrag_5.mobilecyclecount);
            DetailFrag_5.reportarray.put(DetailFrag_5.reportobject);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        DetailFrag_5.postsubdata.put(DetailFrag_5.postexevalues);
        DetailFrag_5.postsubdata.put(DetailFrag_5.postexeparameters);
        DetailFrag_5.postexevalues = new JSONArray();
        DetailFrag_5.postexeparameters = new JSONArray();

//        for (int i = 0; i < objectElements.size() - 1; i++) {
//            //float difference = angles[i] - angles[i - 1];
//            DetailFrag_5.change = objectElements.get(i + 1).getY() - objectElements.get(i).getY();
//
//
//            if (i + 1 == objectElements.size() - 1) {
//                DetailFrag_5.tempRow.add(objectElements.get(i));
//            }
//
//            if (DetailFrag_5.isIncreasing && DetailFrag_5.isDecreasing && objectElements.get(i + 1).getY() >= objectElements.get(i).getY()) {
//                DetailFrag_5.cycleReady = true; // Mark the cycle as ready
//                DetailFrag_5.tempRow.add(objectElements.get(i));
//                if (DetailFrag_5.cycleReady) {
//                    DetailFrag_5.ct++;
//                    DetailFrag_5.isIncreasing = false;
//                    DetailFrag_5.isDecreasing = false;
//                    DetailFrag_5.cycleReady = false; // Reset flags
//                    DetailFrag_5.localMinimum = Float.MAX_VALUE; // Reset local minimum
//
//
//                    Log.e("SAGA GTA 2", i + String.valueOf(DetailFrag_5.tempRow));
//                    generateParagraph(DetailFrag_5.tempRow);
//                    DetailFrag_5.postsubdata.put(DetailFrag_5.postexevalues);
//                    DetailFrag_5.postsubdata.put(DetailFrag_5.postexeparameters);
//                    DetailFrag_5.postexevalues = new JSONArray();
//                    DetailFrag_5.postexeparameters = new JSONArray();
//                    DetailFrag_5.tempRow = new ArrayList<>();
//                }
//            }
//
//            // Only count the cycle if it stabilizes and transitions properly
//            if (DetailFrag_5.change > 0) {
//                // Angle is increasing
//                DetailFrag_5.isIncreasing = true;
//                Log.e("SAGA GTA increase", String.valueOf(objectElements.get(i).getY()));
//                // Reset decreasing and cycle-ready flags
//                DetailFrag_5.isDecreasing = false;
//                DetailFrag_5.cycleReady = false;
//                DetailFrag_5.tempRow.add(objectElements.get(i));
//            } else if (DetailFrag_5.change < 0 && DetailFrag_5.isIncreasing) {
//                // Transition to decreasing after an increase
//                Log.e("SAGA GTA decrease", String.valueOf(objectElements.get(i).getY()));
//                DetailFrag_5.isDecreasing = true;
//                DetailFrag_5.tempRow.add(objectElements.get(i));
//                Log.e("Cycle Temprow inba", String.valueOf(DetailFrag_5.tempRow));
//            } else if (DetailFrag_5.change == 0) {
//                DetailFrag_5.tempRow.add(objectElements.get(i));
//            }
//
//            // Update local minimum during the decreasing phase
////            if (DetailFrag_5.isDecreasing) {
////                DetailFrag_5.localMinimum = Math.min(DetailFrag_5.localMinimum, objectElements.get(i).getY());
////                Log.e("Local Minimum", String.valueOf(DetailFrag_5.localMinimum));
////            }
//
//            // Check if stabilization near the local minimum occurs
//
//        }
//
//        DetailFrag_5.postsubdata.put(DetailFrag_5.postexevalues);
//        DetailFrag_5.postsubdata.put(DetailFrag_5.postexeparameters);
        try {
            DetailFrag_5.postexesubdata.put(leg + "-leg-" + activepassive + "-" + DetailFrag_5.mobilecyclecount, DetailFrag_5.postsubdata);
            DetailFrag_5.postsubdata = new JSONArray();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }


        Log.e("Highlight Array Shift", i + String.valueOf(DetailFrag_5.highlightArrayact));


//        for (int i = 0; i < objectElements.size() - 1; i++) {
//
//            DetailFrag_5.change = objectElements.get(i).getY() - objectElements.get(i + 1).getY();
//            DetailFrag_5.tempRow.add(objectElements.get(i));
//            if (i + 1 == objectElements.size() - 1) {
//                DetailFrag_5.tempRow.add(objectElements.get(i + 1));
//                DetailFrag_5.highlightArrayact.add(DetailFrag_5.tempRow);
//                generateParagraph(DetailFrag_5.tempRow);
//                Log.e("Highlight Array Last", i + String.valueOf(DetailFrag_5.highlightArrayact));
//            } else if (DetailFrag_5.change == 0) {
//                continue;
//            } else if (DetailFrag_5.prevSignChange1 != 0 && Math.signum(DetailFrag_5.change) != Math.signum(DetailFrag_5.prevSignChange1)) {
//                DetailFrag_5.cycleCount++;
//                DetailFrag_5.highlightArrayact.add(DetailFrag_5.tempRow);
//                Log.e("Highlight Array Shift", i + String.valueOf(DetailFrag_5.highlightArrayact));
//                generateParagraph(DetailFrag_5.tempRow);
//
//                DetailFrag_5.tempRow = new ArrayList<>();
//                DetailFrag_5.tempRow.add(objectElements.get(i));
//            }
//
//            DetailFrag_5.prevSignChange1 = (int) DetailFrag_5.change;
//        }


        DetailFrag_5.highlightArrayact.add(Collections.singletonList(new Entry(0, 0)));

        Log.e("Fury", DetailFrag_5.selectedExercise);
        if (DetailFrag_5.activeeds.size() > 0) {
            Log.e("Fury", String.valueOf(DetailFrag_5.activeeds));
        } else {
            Log.e("Fury", String.valueOf(DetailFrag_5.passiveeds));
        }
    }

    private void extensionlagtest(List<Float> data) {

        DetailFrag_5.extnangle = data.get(0);
        DetailFrag_5.leftlegwos.add(DetailFrag_5.extnangle);
        if (DetailFrag_5.leftlegwos.size() > 1 && DetailFrag_5.extnflag1 == 0) {

            float change = DetailFrag_5.extnangle - DetailFrag_5.extnangle1;

            if (DetailFrag_5.extndens >= 5) {
                if (DetailFrag_5.extnflag == 0) {
//                    Log.e("Inbasekar Extension Lag tes inside func deficit", String.valueOf(DetailFrag_5.extnangle) + " / " + DetailFrag_5.extnangle1 + " / " + DetailFrag_5.extnactivemax + " / " + DetailFrag_5.extnpassivemax);
                    Toasty.warning(Assessment.this, "Extension Deficit Detected", Toast.LENGTH_SHORT).show();
                    DetailFrag_5.extnflag = 1;
                }
                if (change >= 0) {
                    if (DetailFrag_5.extnpassivemax > DetailFrag_5.extnangle) {
                        DetailFrag_5.extnpassivemax = DetailFrag_5.extnangle;
                    }
                    if (DetailFrag_5.extnangle <= 10) {
//                        Log.e("Inbasekar Extension Lag tes inside func extension", String.valueOf(DetailFrag_5.extnangle) + " / " + DetailFrag_5.extnangle1 + " / " + DetailFrag_5.extnactivemax + " / " + DetailFrag_5.extnpassivemax);
                        Toasty.success(Assessment.this, "Extension Reached", Toast.LENGTH_SHORT).show();
                        DetailFrag_5.extnflag1 = 1;
                        //stopTimer();
                    }
                }
            } else {
                if (change <= 0) {
                    if (DetailFrag_5.extnactivemax > DetailFrag_5.extnangle) {
                        DetailFrag_5.extnactivemax = DetailFrag_5.extnangle;
                    }
                    if (DetailFrag_5.extnangle <= 10) {
//                        Log.e("Inbasekar Extension Lag tes inside func no extension", String.valueOf(DetailFrag_5.extnangle) + " / " + DetailFrag_5.extnangle1 + " / " + DetailFrag_5.extnactivemax + " / " + DetailFrag_5.extnpassivemax);
                        Toasty.success(Assessment.this, "No Extension Deficit", Toast.LENGTH_SHORT).show();
                        DetailFrag_5.extnflag1 = 1;
                        //stopTimer();
                    }
                } else if (change > 0) {
                    DetailFrag_5.extndens++;
                }
            }


//            assess_cycles_total.setVisibility(View.GONE);
//            DetailFrag_5.exerciseListtotal.clear();
//            DetailFrag_5.tempRow = new ArrayList<>();
//            DetailFrag_5.totaleds.clear();
//            DetailFrag_5.reportobject = new JSONObject();
//            DetailFrag_5.datareportarray = new JSONArray();
//            DetailFrag_5.analysereportarray = new JSONArray();


        }
//        Log.e("Inbasekar Extension Lag tes inside func", String.valueOf(DetailFrag_5.extnangle) + " / " + DetailFrag_5.extnangle1 + " / " + DetailFrag_5.extnactivemax + " / " + DetailFrag_5.extnpassivemax);
        DetailFrag_5.extnangle1 = DetailFrag_5.extnangle;

//        Log.e("Inbasekar Extension Lag tes inside func",DetailFrag_5.extnactivemax+" / "+DetailFrag_5.extnpassivemax+" / "+DetailFrag_5.extnactivemax+DetailFrag_5.extnpassivemax);


//        for (int i = 0; i < objectElements.size() - 1; i++) {
//            //float difference = angles[i] - angles[i - 1];
//            DetailFrag_5.change = objectElements.get(i + 1).getY() - objectElements.get(i).getY();
//            if (DetailFrag_5.isIncreasing && DetailFrag_5.isDecreasing && objectElements.get(i + 1).getY() >= objectElements.get(i).getY()) {
//                DetailFrag_5.cycleReady = true; // Mark the cycle as ready
//                // Only count the cycle if it stabilizes and transitions properly
//                DetailFrag_5.tempRow.add(objectElements.get(i));
//                if (DetailFrag_5.cycleReady) {
//                    Log.e("Cycle Temprow shaw", String.valueOf(DetailFrag_5.tempRow));
//                    DetailFrag_5.ct++;
//                    DetailFrag_5.isIncreasing = false;
//                    DetailFrag_5.isDecreasing = false;
//                    DetailFrag_5.cycleReady = false; // Reset flags
//                    DetailFrag_5.localMinimum = Float.MAX_VALUE; // Reset local minimum
//
//
//                    //Log.e("Highlight Array Shift", i + String.valueOf(DetailFrag_5.highlightArray));
//                    generateParagraph(DetailFrag_5.tempRow);
//                    DetailFrag_5.postsubdata.put(DetailFrag_5.postexevalues);
//                    DetailFrag_5.postsubdata.put(DetailFrag_5.postexeparameters);
//                    DetailFrag_5.postexevalues = new JSONArray();
//                    DetailFrag_5.postexeparameters = new JSONArray();
//                    DetailFrag_5.tempRow = new ArrayList<>();
//                }
//
//            } else if (DetailFrag_5.change > 0) {
//                // Angle is increasing
//                DetailFrag_5.isIncreasing = true;
//                Log.e("SAGA GTA increase", String.valueOf(objectElements.get(i).getY()));
//                // Reset decreasing and cycle-ready flags
//                DetailFrag_5.isDecreasing = false;
//                DetailFrag_5.cycleReady = false;
//                DetailFrag_5.tempRow.add(objectElements.get(i));
//                Log.e("Cycle Temprow inba increase", String.valueOf(DetailFrag_5.tempRow));
//            } else if (DetailFrag_5.change < 0 && DetailFrag_5.isIncreasing) {
//                // Transition to decreasing after an increase
//                Log.e("SAGA GTA decrease", String.valueOf(objectElements.get(i).getY()));
//                DetailFrag_5.isDecreasing = true;
//                DetailFrag_5.tempRow.add(objectElements.get(i));
//                Log.e("Cycle Temprow inba decrease", String.valueOf(DetailFrag_5.tempRow));
//            } else if (DetailFrag_5.change == 0) {
//                DetailFrag_5.tempRow.add(objectElements.get(i));
//            }
//
//            Log.e("Cycle Temprow inba analyse", String.valueOf(objectElements.get(i).getY()) + " / " + String.valueOf(objectElements.get(i + 1).getY()) + " / " + String.valueOf(DetailFrag_5.isIncreasing) + " / " + String.valueOf(DetailFrag_5.isDecreasing));
//            // Check if stabilization near the local minimum occurs
//
//
//            // Only count the cycle if it stabilizes and transitions properly
//            if (DetailFrag_5.cycleReady) {
//                Log.e("Cycle Temprow shaw", String.valueOf(DetailFrag_5.tempRow));
//                DetailFrag_5.ct++;
//                DetailFrag_5.isIncreasing = false;
//                DetailFrag_5.isDecreasing = false;
//                DetailFrag_5.cycleReady = false; // Reset flags
//                DetailFrag_5.localMinimum = Float.MAX_VALUE; // Reset local minimum
//
//                if ("Extension Lag Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
//                    if ("active".equalsIgnoreCase(activepassive)) {
//                        DetailFrag_5.highlightArrayact.add(DetailFrag_5.tempRow);
//                    } else {
//                        DetailFrag_5.highlightArraypass.add(DetailFrag_5.tempRow);
//                    }
//                }
//                //Log.e("Highlight Array Shift", i + String.valueOf(DetailFrag_5.highlightArray));
//                generateParagraph(DetailFrag_5.tempRow);
//                DetailFrag_5.postsubdata.put(DetailFrag_5.postexevalues);
//                DetailFrag_5.postsubdata.put(DetailFrag_5.postexeparameters);
//                DetailFrag_5.postexevalues = new JSONArray();
//                DetailFrag_5.postexeparameters = new JSONArray();
//                DetailFrag_5.tempRow = new ArrayList<>();
//            }
//        }


//        for (int i = 0; i < objectElements.size() - 1; i++) {
//
//            DetailFrag_5.change = objectElements.get(i).getY() - objectElements.get(i + 1).getY();
//            DetailFrag_5.tempRow.add(objectElements.get(i));
//            if (i + 1 == objectElements.size() - 1) {
//                DetailFrag_5.tempRow.add(objectElements.get(i + 1));
//                if ("Extension Lag Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
//                    if ("active".equalsIgnoreCase(activepassive)) {
//                        DetailFrag_5.highlightArrayact.add(DetailFrag_5.tempRow);
//                    } else {
//                        DetailFrag_5.highlightArraypass.add(DetailFrag_5.tempRow);
//                    }
//                }
//
//                generateParagraph(DetailFrag_5.tempRow);
//                //Log.e("Highlight Array Last", i + String.valueOf(DetailFrag_5.highlightArray));
//            } else if (DetailFrag_5.change == 0) {
//                continue;
//            } else if (DetailFrag_5.prevSignChange1 != 0 && Math.signum(DetailFrag_5.change) != Math.signum(DetailFrag_5.prevSignChange1)) {
//                DetailFrag_5.cycleCount++;
//                if ("Extension Lag Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
//                    if ("active".equalsIgnoreCase(activepassive)) {
//                        DetailFrag_5.highlightArrayact.add(DetailFrag_5.tempRow);
//                    } else {
//                        DetailFrag_5.highlightArraypass.add(DetailFrag_5.tempRow);
//                    }
//                }
//                //Log.e("Highlight Array Shift", i + String.valueOf(DetailFrag_5.highlightArray));
//                generateParagraph(DetailFrag_5.tempRow);
//
//                DetailFrag_5.tempRow = new ArrayList<>();
//                DetailFrag_5.tempRow.add(objectElements.get(i));
//            }
//
//            DetailFrag_5.prevSignChange1 = (int) DetailFrag_5.change;
//        }
        //Log.e("Highlight Array", i + String.valueOf(highlightArray));


//        if ("Extension Lag Test".equalsIgnoreCase(DetailFrag_5.selectedExercise)) {
//
//                DetailFrag_5.highlightArrayact.add(Collections.singletonList(new Entry(0, 0)));
//
//                DetailFrag_5.highlightArraypass.add(Collections.singletonList(new Entry(0, 0)));
//
//        }
//
//        Log.e("Total Extension Deficits1", String.valueOf(DetailFrag_5.activeeds));
//        Log.e("Total Extension Deficits1", String.valueOf(DetailFrag_5.passiveeds));
//        Log.e("Active phase extension", String.valueOf(DetailFrag_5.highlightArrayact));
//        Log.e("Active phase extension", String.valueOf(DetailFrag_5.highlightArraypass));
//
//
//        if (DetailFrag_5.activeeds.size() != 0 && DetailFrag_5.passiveeds.size() != 0) {
//            Log.e("INBA", String.valueOf(DetailFrag_5.activeeds));
//            Log.e("INBA", String.valueOf(DetailFrag_5.passiveeds));
//            DetailFrag_5.totaleds.clear();
//            if (DetailFrag_5.activeeds.size() <= DetailFrag_5.passiveeds.size()) {
//                for (int i = 0; i < DetailFrag_5.activeeds.size(); i++) {
//                    float totaled = DetailFrag_5.passiveeds.get(i) - DetailFrag_5.activeeds.get(i);
//                    DetailFrag_5.totaleds.add(totaled);
//                }
//                Log.e("Total Extension Deficits", String.valueOf(DetailFrag_5.totaleds));
//            } else {
//                for (int i = 0; i < DetailFrag_5.passiveeds.size(); i++) {
//                    float totaled = DetailFrag_5.passiveeds.get(i) - DetailFrag_5.activeeds.get(i);
//                    DetailFrag_5.totaleds.add(totaled);
//                }
//                Log.e("Total Extension Deficits", String.valueOf(DetailFrag_5.totaleds));
//            }
//
//            DetailFrag_5.exerciseListtotal.clear();
//            for (int i = 0; i < DetailFrag_5.totaleds.size(); i++) {
//                if (max < DetailFrag_5.totaleds.get(i)) {
//                    max = DetailFrag_5.totaleds.get(i);
//                    cycleno = i + 1;
//                }
//                DetailFrag_5.exerciseListtotal.add(new ExerciseCycleAssessment(DetailFrag_5.totaleds.get(i)));
//            }
//            maxrom1 = max;
//
//            int marginInDp = 15;
//            int marginInPx = (int) TypedValue.applyDimension(
//                    TypedValue.COMPLEX_UNIT_DIP, marginInDp, getResources().getDisplayMetrics());
//            String ap = "Total";
//            assess_cycles_total.setVisibility(View.VISIBLE);
//            DetailFrag_5.indiviCardAdaptertotal = new TotalAssessmentCycleAdapter(DetailFrag_5.exerciseListtotal, this);
//            assess_cycles_total.setAdapter(DetailFrag_5.indiviCardAdaptertotal);
//        }
//
//        Log.e("Inbasekar", String.valueOf(DetailFrag_5.highlightArrayact));


    }

//    private void dynamicbalancetest(List<Float> data, List<Float> data1) {
//
//        Log.e("Inside Dynamic balance test", String.valueOf(data));
//
//        float angle = data.get(0);
//        float angle1 = data.get(1);
//
//        if ("active".equalsIgnoreCase(activepassive)) {
//
//            DetailFrag_5.leftlegwos.add(angle);
//            DetailFrag_5.rightwos.add(angle1);
//
//            wos = "Without Support";
//
//            if (angle >= DetailFrag_5.SIT_ANGLE && angle1 >= DetailFrag_5.SIT_ANGLE && walkstarted == 0 && standtoshift == 0) {
//                if (DetailFrag_5.sitToStandStartTime == 0) {
//                    DetailFrag_5.sitToStandStartTime = DetailFrag_5.sec;
//                }
//                DetailFrag_5.sitToStandEndTime = DetailFrag_5.sec;
//                Log.e("SittoStand Time", String.valueOf(DetailFrag_5.sitToStandStartTime) + " / " + String.valueOf(DetailFrag_5.sitToStandEndTime));
//            } else if (angle < DetailFrag_5.STAND_ANGLE && angle1 < DetailFrag_5.STAND_ANGLE && walkstarted == 0) {
//                if (DetailFrag_5.standToShiftStartTime == 0) {
//                    DetailFrag_5.standToShiftStartTime = DetailFrag_5.sec;
//                }
//                standtoshift = 1;
//                DetailFrag_5.standToShiftEndTime = DetailFrag_5.sec;
//                Log.e("StandtoShift Time", String.valueOf(DetailFrag_5.standToShiftStartTime) + " / " + String.valueOf(DetailFrag_5.standToShiftEndTime) + " / " + standtoshift);
//            } else if (angle1 > 5 && angle <= 75 && angle > 5 && angle1 <= 75 && standtoshift == 1) {
//                if (DetailFrag_5.walkStartTime == 0) {
//                    DetailFrag_5.walkStartTime = DetailFrag_5.sec;
//                }
//                walkstarted = 1;
//                DetailFrag_5.walkEndTime = DetailFrag_5.sec;
//                DetailFrag_5.sittostand = DetailFrag_5.walkStartTime - DetailFrag_5.sitToStandEndTime;
//                Log.e("Walk Time inba", String.valueOf(DetailFrag_5.standToShiftEndTime) + " / " + String.valueOf(DetailFrag_5.walkEndTime));
//            }
//
//            previousLeftKneeAngle = angle;
//            previousRightKneeAngle = angle1;
//        }
//        else {
//
//            DetailFrag_5.leftlegws.add(angle);
//            DetailFrag_5.rightws.add(angle1);
//
//            wos = "With Support";
//
//            Log.e("Inbasekar Dynamic Balance Test", DetailFrag_5.timestamps.toString() + " / " + currentMetricIndex);
//
//            if (angle >= DetailFrag_5.SIT_ANGLE && angle1 >= DetailFrag_5.SIT_ANGLE && walkstarted == 0 && standtoshift == 0) {
//                if (DetailFrag_5.sitToStandStartTime == 0) {
//                    DetailFrag_5.sitToStandStartTime = DetailFrag_5.sec;
//                }
//                DetailFrag_5.sitToStandEndTime = DetailFrag_5.sec;
//                Log.e("SittoStand Time", String.valueOf(DetailFrag_5.sitToStandStartTime) + " / " + String.valueOf(DetailFrag_5.sitToStandEndTime));
//            } else if (angle1 > 5 && angle <= 65 && angle > 5 && angle1 <= 65 && standtoshift == 0) {
//                if (DetailFrag_5.walkStartTime == 0) {
//                    DetailFrag_5.walkStartTime = DetailFrag_5.sec;
//                }
//                walkstarted = 1;
//                DetailFrag_5.walkEndTime = DetailFrag_5.sec;
//                DetailFrag_5.sittostand = DetailFrag_5.walkStartTime - DetailFrag_5.sitToStandEndTime;
//                Log.e("Walk Time inba", String.valueOf(DetailFrag_5.standToShiftEndTime) + " / " + String.valueOf(DetailFrag_5.walkEndTime));
//            } else if (angle <= DetailFrag_5.STAND_ANGLE && angle1 <= DetailFrag_5.STAND_ANGLE && walkstarted == 1) {
//                if (DetailFrag_5.standToShiftStartTime == 0) {
//                    DetailFrag_5.standToShiftStartTime = DetailFrag_5.sec;
//                }
//                standtoshift = 1;
//                DetailFrag_5.standToShiftEndTime = DetailFrag_5.sec;
//                Log.e("StandtoShift Time", String.valueOf(DetailFrag_5.standToShiftStartTime) + " / " + String.valueOf(DetailFrag_5.standToShiftEndTime));
//            }
//
//            previousLeftKneeAngle = angle;
//            previousRightKneeAngle = angle1;
//        }
//
//
//
//        Log.e("Sit-to-Stand Starttime: ", DetailFrag_5.sitToStandStartTime + " seconds");
//        Log.e("Sit-to-Stand endtime: ", DetailFrag_5.sitToStandEndTime + " seconds");
//        Log.e("Sit-to-Stand Duration: ", (DetailFrag_5.sittostand) + " seconds");
//        Log.e("Stand-to-Shift Starttime: ", DetailFrag_5.standToShiftStartTime + " seconds");
//        Log.e("Stand-to-Shift endtime: ", DetailFrag_5.standToShiftEndTime + " seconds");
//        Log.e("Stand-to-Shift Duration: ", (DetailFrag_5.sitToStandStartTime - DetailFrag_5.standToShiftStartTime) + " seconds");
//        Log.e("Walk Starttime: ", DetailFrag_5.walkStartTime + " seconds");
//        Log.e("Walk endtime: ", DetailFrag_5.walkEndTime + " seconds");
//        Log.e("Walk Duration: ", (DetailFrag_5.walkEndTime - DetailFrag_5.walkStartTime) + " seconds");
//        //System.out.println("Stand-to-Shift Duration: " + (DetailFrag_5.standToShiftEndTime - DetailFrag_5.standToShiftStartTime) / 1000.0 + " seconds");
//        //System.out.println("Walk Duration: " + (DetailFrag_5.walkEndTime - DetailFrag_5.walkStartTime) / 1000.0 + " seconds");
//    }

    private void dynamicbalancetest(List<Float> data, List<Float> data1) {
        final float SIT_THRESHOLD_MIN = 75f;
        final float STAND_THRESHOLD = 10f;
        final int CONSISTENT_STAND_POINTS = 60; // 500 ms
        final int CONSISTENT_SIT_POINTS = 25; // 500 ms
        float maxangle = 0f;

        boolean isSitting = true;
        boolean isWalking = false;
        boolean isStanding1 = false;
        boolean isStanding2 = false;

        long sitToStandStart = 0;
        long standingStart = 0;
        long walkingStart = 0;
        long standToSitStart = 0;

        long sitToStandTime = 0;
        long standBeforeWalkTime = 0;
        long walkingTime = 0;
        long standingAfterWalkTime = 0;
        long standToSitTime = 0;
        long standtemptime = 0;
        long sittemptime = 0;

        int standCount = 0;
        int sitCount = 0;

        for (int i = 0; i < data.size(); i++) {
            float angle = data.get(i);
            long timestamp = DetailFrag_5.timestamps.get(i);

            if (isSitting && angle < SIT_THRESHOLD_MIN) {
                // Sit to Stand
                isSitting = false;
                sitToStandStart = timestamp;
                //Log.d("Dynamic Balance Test", "Patient sit to stand started at: " +sitToStandStart);
            } else if (!isSitting && !isStanding1 && !isWalking && angle <= STAND_THRESHOLD) {
                // Standing before walking
                sitToStandTime = timestamp - sitToStandStart;
                //Log.d("Dynamic Balance Test", "Sit to Stand time: " +timestamp);
                //System.out.println("Sit to Stand time: " + sitToStandTime + " ms");
                standingStart = timestamp;
                isStanding1 = true;
                //Log.d("Dynamic Balance Test", "Patient is standing before walking at: " +standingStart);
                //System.out.println("Patient is standing before walking at: " + standingStart + " ms");
            } else if (isStanding1 && angle > STAND_THRESHOLD) {
                // Walking starts
                standBeforeWalkTime = timestamp - standingStart;
                //Log.d("Dynamic Balance Test", "Standing before walking time: " +timestamp);
                //System.out.println("Standing before walking time: " + standBeforeWalkTime + " ms");
                walkingStart = timestamp;
                isWalking = true;
                isStanding1 = false;
                //Log.d("Dynamic Balance Test", "Patient starts walking at: " +walkingStart);
                //System.out.println("Patient starts walking at: " + walkingStart + " ms");
            } else if (isWalking) {
                // Check for consistent standing angle
                if (angle < STAND_THRESHOLD) {
                    if (standtemptime == 0) {
                        standtemptime = timestamp;
                    }
                    standCount++;
                    sitCount = 0;
                    sittemptime = 0;
                } else if (angle > STAND_THRESHOLD) {
                    if (sittemptime == 0) {
                        sittemptime = timestamp;
                    }
                    if (maxangle < angle) {
                        maxangle = angle;
                    }
                    sitCount++;
                    standCount = 0;
                    standtemptime = 0;
                } else {
                    standCount = 0;
                    sitCount = 0;
                    standtemptime = 0;
                    sittemptime = 0;
                    maxangle = 0;
                }
//                Log.d("Dynamic Balance Test", "CONSISTENT_STAND_POINTS " +standCount + " / "+sitCount+" / "+maxangle);
                if ((standCount >= CONSISTENT_STAND_POINTS) || (maxangle >= 65)) {
                    // Standing after walking
                    if (standtemptime != 0) {
                        walkingTime = standtemptime - walkingStart;
                        //Log.d("Dynamic Balance Test", "Walking time: " +standtemptime);
                        //System.out.println("Walking time: " + walkingTime + " ms");
                        standingStart = standtemptime;
                        standToSitTime = timestamp - standtemptime;
                    } else {
                        walkingTime = sittemptime - walkingStart;
                        //Log.d("Dynamic Balance Test", "Walking time: " +sittemptime);
                        //System.out.println("Walking time: " + walkingTime + " ms");
                        standingStart = sittemptime;
                        standToSitTime = timestamp - sittemptime;
                    }

                    isStanding2 = true;
                    isWalking = false;
                    standCount = 0;
                    //Log.d("Dynamic Balance Test", "Patient is standing after walking at: " +standingStart);
//                    System.out.println("Patient is standing after walking at: " + standingStart + " ms");
                }
            }

//            else if (isStanding2 && angle >= SIT_THRESHOLD_MIN) {
//                // Stand to Sit
//                standingAfterWalkTime = timestamp - standingStart;
//                Log.d("Dynamic Balance Test", "Standing after walking time: " +timestamp);
//                //System.out.println("Standing after walking time: " + standingAfterWalkTime + " ms");
//                standToSitStart = timestamp;
//                isSitting = true;
//                Log.d("Dynamic Balance Test", "Patient started stand to sit transition at: " +standToSitStart);
//                //System.out.println("Patient started stand to sit transition at: " + standToSitStart + " ms");
//
//                standToSitTime = timestamp - standToSitStart;
//                Log.d("Dynamic Balance Test", "Stand to Sit time: " +timestamp);
//                //System.out.println("Stand to Sit time: " + standToSitTime + " ms");
////                break;
//            }
        }
        Log.d("Dynamic Balance Test", "Total Sit to Stand time: " + String.format("%.2f", sitToStandTime / 1000.0) + " s");
//        Log.d("Dynamic Balance Test", "Total Standing before walking time: " + String.format("%.2f", standBeforeWalkTime / 1000.0) + " s");
        Log.d("Dynamic Balance Test", "Total Walking time: " + String.format("%.2f", walkingTime / 1000.0) + " s");
//        Log.d("Dynamic Balance Test", "Total Standing after walking time: " + String.format("%.2f", standingAfterWalkTime / 1000.0) + " s");
        Log.d("Dynamic Balance Test", "Total Stand to Sit time: " + String.format("%.2f", standToSitTime / 1000.0) + " s");

        DetailFrag_5.sittostand = (Math.round(sitToStandTime / 10.0) / 100.0);
        DetailFrag_5.standtosit = (Math.round(standToSitTime / 10.0) / 100.0);
        DetailFrag_5.walktime = (Math.round(walkingTime / 10.0) / 100.0);

    }

    private void staticbalancetest(List<Float> data) {
        Log.e("Inside Static balance test", String.valueOf(DetailFrag_5.timestamps));

        long timestatic = 0;

        int staticflag = 0;

        for (int i = 0; i < data.size(); i++) {
            DetailFrag_5.staticbalanceangles.add(data.get(i));
            if (Math.abs(data.get(i)) <= 7 && Math.abs(data.get(i)) >= 0 && staticflag == 0) {
                staticflag = 1;
                DetailFrag_5.endTime = DetailFrag_5.timestamps.get(i);
                timestatic = DetailFrag_5.timestamps.get(i);
                Log.e("Inside Static balance test", String.valueOf(timestatic));
                //Log.e("Raj ronald shaw", DetailFrag_5.startTime + " " + String.valueOf(DetailFrag_5.endTime));
                //DetailFrag_5.duration = (DetailFrag_5.endTime - DetailFrag_5.startTimegait)/1000;
            }
        }


//        if (firstvalue == 0) {
//            firstvalue = 1;
//            DetailFrag_5.startTime = DetailFrag_5.sec;
//            Log.e("Raj ronald", String.valueOf(DetailFrag_5.startTime));
//        }

//        float angle = 0;
//        for (float value : data) {
//            angle = value;
//            //DetailFrag_5.metricArray.add(new DetailFrag_5.Metric(index, value));
//            Log.e("Raj ronald", String.valueOf(angle));
//            DetailFrag_5.staticbalanceangles.add(angle);
//        }

//        DetailFrag_5.staticbalanceangles.add(angle);

//        if (DetailFrag_5.isTesting) {
//            if (Math.abs(angle) <= 7 && Math.abs(angle) >= 0) {
//                DetailFrag_5.isTesting = false;
//                DetailFrag_5.endTime = DetailFrag_5.sec;
//                Log.e("Raj ronald shaw", DetailFrag_5.startTime + " " + String.valueOf(DetailFrag_5.endTime));
//                //DetailFrag_5.duration = (DetailFrag_5.endTime - DetailFrag_5.startTimegait)/1000;
//            }
//        }

//        for(int i=0; i<objectElements.size(); i++) {
//            if (DetailFrag_5.isTesting) {
//                if (Math.abs(objectElements.get(i).getY() - DetailFrag_5.TARGET_ANGLE) > DetailFrag_5.ANGLE_THRESHOLD) {
//                    DetailFrag_5.isTesting=false;
//                    DetailFrag_5.duration = (DetailFrag_5.endTime - DetailFrag_5.startTime) / 1000;
//                }
//            } else {
//                Log.e("Test is not active", String.valueOf(DetailFrag_5.isTesting));
//            }
//        }

    }

    private void staircaseclimbingtest(List<Float> data, List<Float> data1) {
        DetailFrag_5.startind = 0;
        DetailFrag_5.ascentstart = 0;

        Log.e("INBASEKAR STAIRCASE 2", String.valueOf(data));
        DetailFrag_5.tempRow = new ArrayList<>();
//        for (int i = 0; i < data.size() - 1; i++) {
//            DetailFrag_5.change = data.get(i + 1) - data.get(i);
//            if (DetailFrag_5.isIncreasing && DetailFrag_5.isDecreasing && data.get(i + 1) >= data.get(i)) {
//                DetailFrag_5.cycleReady = true; // Mark the cycle as ready
//                DetailFrag_5.tempRow.add(new Entry(i, data.get(i)));
//                // Only count the cycle if it stabilizes and transitions properly
//                if (DetailFrag_5.cycleReady) {
//                    DetailFrag_5.ct++;
//                    DetailFrag_5.isIncreasing = false;
//                    DetailFrag_5.isDecreasing = false;
//                    DetailFrag_5.cycleReady = false; // Reset flags
//                    DetailFrag_5.localMinimum = Float.MAX_VALUE; // Reset local minimum
//                    DetailFrag_5.endind = i;
//                    Log.e("Staircase Inba YoYo Left", String.valueOf(DetailFrag_5.tempRow));
//                    generateParagraph(DetailFrag_5.tempRow);
//                    Log.e("Staircase Left Leg", DetailFrag_5.timestamps.get(DetailFrag_5.startind)+" / "+DetailFrag_5.timestamps.get(DetailFrag_5.endind));
//                    DetailFrag_5.startind = i + 1;
//                    DetailFrag_5.tempRow = new ArrayList<>();
//                }
//            } else if (i + 1 == data.size() - 1) {
//                DetailFrag_5.tempRow.add(new Entry(i, data.get(i)));
//            } else if (DetailFrag_5.change > 0) {
//                // Angle is increasing
//                DetailFrag_5.isIncreasing = true;
//                Log.e("SAGA GTA increase", String.valueOf(data.get(i)));
//                // Reset decreasing and cycle-ready flags
//                DetailFrag_5.isDecreasing = false;
//                DetailFrag_5.cycleReady = false;
//                DetailFrag_5.tempRow.add(new Entry(i, data.get(i)));
//
//            } else if (DetailFrag_5.change < 0 && DetailFrag_5.isIncreasing) {
//                // Transition to decreasing after an increase
//                Log.e("SAGA GTA decrease", String.valueOf(data.get(i)));
//                DetailFrag_5.isDecreasing = true;
//                DetailFrag_5.tempRow.add(new Entry(i, data.get(i)));
//                Log.e("Cycle Temprow inba", String.valueOf(DetailFrag_5.substaircase));
//            } else if (DetailFrag_5.change == 0) {
//                DetailFrag_5.tempRow.add(new Entry(i, data.get(i)));
//            }
//
//        }

        int minDuration = 5; // Minimum duration for a valid cycle
        float minAmplitude = 10.0f; // Minimum required angle change

        float cycleMax = Float.MIN_VALUE;
        float cycleMin = Float.MAX_VALUE;
        boolean cycleStarted = false;
        boolean potentialEnd = false;
        int potentialEndIndex = -1;
        int turnflag = 0;

        boolean aboveThreshold = false;  // `true` when cycleMax > 45
        boolean failedThreshold = false; // `true` when failure detected

        int aftfail = 0;

        List<Long> leftbeforeFailTimestampsstart = new ArrayList<>();
        List<Long> leftduringFailTimestampsstart = new ArrayList<>();
        List<Long> leftafterFailTimestampsstart = new ArrayList<>();

        List<Long> leftbeforeFailTimestampsend = new ArrayList<>();
        List<Long> leftduringFailTimestampsend = new ArrayList<>();
        List<Long> leftafterFailTimestampsend = new ArrayList<>();


        for (int i = 0; i < data.size() - 1; i++) {
            DetailFrag_5.change = data.get(i + 1) - data.get(i);

            if (DetailFrag_5.change > 0) {
                // **Increasing phase detected**
                if (!cycleStarted) {
                    DetailFrag_5.startind = i; // Start of a new cycle
                    cycleStarted = true;
                }

                if (potentialEnd) {
                    // Confirm cycle end if increase continues
                    int cycleDuration = potentialEndIndex - DetailFrag_5.startind;
                    float cycleAmplitude = cycleMax - cycleMin;

                    if (cycleDuration >= minDuration && cycleAmplitude >= minAmplitude) {
                        // Count valid cycle
                        DetailFrag_5.endind = potentialEndIndex;

                        Log.e("Left Leg Valid Cycle", "Start: " + DetailFrag_5.timestamps.get(DetailFrag_5.startind) +
                                " / End: " + DetailFrag_5.timestamps.get(DetailFrag_5.endind));
                        DetailFrag_5.stepCount++;
                        // **Manage timestamp splits**
                        if (cycleMax > 45) {

                            if (aftfail == 1) {
                                turnflag = 1;
                                DetailFrag_5.descentct++;
                                leftafterFailTimestampsstart.add(DetailFrag_5.timestamps.get(DetailFrag_5.startind));
                                leftafterFailTimestampsend.add(DetailFrag_5.timestamps.get(DetailFrag_5.endind));
                            } else {
                                DetailFrag_5.ascentct++;
                                leftbeforeFailTimestampsstart.add(DetailFrag_5.timestamps.get(DetailFrag_5.startind));
                                leftbeforeFailTimestampsend.add(DetailFrag_5.timestamps.get(DetailFrag_5.endind));
                            }
                        } else if (turnflag == 0) {
                            aftfail = 1;
                            leftduringFailTimestampsstart.add(DetailFrag_5.timestamps.get(DetailFrag_5.startind));
                            leftduringFailTimestampsend.add(DetailFrag_5.timestamps.get(DetailFrag_5.endind));
                        }

//                        generateParagraph(DetailFrag_5.tempRow);
                    }

                    // Reset for next cycle
                    cycleStarted = false;
                    potentialEnd = false;
                    cycleMax = Float.MIN_VALUE;
                    cycleMin = Float.MAX_VALUE;
                    DetailFrag_5.tempRow = new ArrayList<>();
                }

                DetailFrag_5.isIncreasing = true;
                DetailFrag_5.isDecreasing = false;
                cycleMax = Math.max(cycleMax, data.get(i));
                cycleMin = Math.min(cycleMin, data.get(i));
                DetailFrag_5.tempRow.add(new Entry(i, data.get(i)));

            } else if (DetailFrag_5.change < 0 && DetailFrag_5.isIncreasing) {
                // **Decreasing phase starts after an increase**
                DetailFrag_5.isDecreasing = true;
                cycleMax = Math.max(cycleMax, data.get(i));
                cycleMin = Math.min(cycleMin, data.get(i));
                DetailFrag_5.tempRow.add(new Entry(i, data.get(i)));
                potentialEnd = false; // Reset potential end

            } else if (DetailFrag_5.isDecreasing && DetailFrag_5.change == 0) {
                // **Flat region detected during decrease phase**
                potentialEnd = true;  // Flag this as a possible cycle end
                potentialEndIndex = i;

            } else if (DetailFrag_5.isIncreasing && DetailFrag_5.isDecreasing && data.get(i + 1) >= data.get(i)) {
                // **Cycle is only completed at the true local minimum**
                if (!potentialEnd) {
                    potentialEnd = true;
                    potentialEndIndex = i;
                }
            }
        }
        if (cycleStarted && DetailFrag_5.isIncreasing && DetailFrag_5.isDecreasing) {
            int cycleDuration = data.size() - 1 - DetailFrag_5.startind;
            float cycleAmplitude = cycleMax - cycleMin;
            DetailFrag_5.stepCount++;

            if (cycleDuration >= minDuration && cycleAmplitude >= minAmplitude) {
                DetailFrag_5.descentct++; // Count valid cycle
                DetailFrag_5.endind = potentialEnd ? potentialEndIndex : data.size() - 1;

                Log.e("Left Valid Cycle", "Start: " + DetailFrag_5.timestamps.get(DetailFrag_5.startind) +
                        " / End: " + DetailFrag_5.timestamps.get(DetailFrag_5.endind));

                leftafterFailTimestampsstart.add(DetailFrag_5.timestamps.get(DetailFrag_5.startind));
                leftafterFailTimestampsend.add(DetailFrag_5.timestamps.get(DetailFrag_5.endind));


                DetailFrag_5.startind = 0;
//                generateParagraph(DetailFrag_5.tempRow);
            }
        }


//        lascent = Math.abs((DetailFrag_5.timestamps.get(ascentend) - DetailFrag_5.timestamps.get(ascentstart)) / 1000.0);
//        lturn = Math.abs((DetailFrag_5.timestamps.get(turnend) - DetailFrag_5.timestamps.get(turnstart)) / 1000.0);
//        ldescent = Math.abs((DetailFrag_5.timestamps.get(descentend) - DetailFrag_5.timestamps.get(descentstart)) / 1000.0);

//        Log.e("Staircase Left Leg", String.valueOf(data));
//        Log.e("Staircase Left Leg", String.valueOf(DetailFrag_5.timestamps));

        Log.e("Staircase Left Leg", String.valueOf(lascent));
        Log.e("Staircase Left Leg", String.valueOf(lturn));
        Log.e("Staircase Left Leg", String.valueOf(ldescent));

//        Log.e("Staircase Left Leg",DetailFrag_5.timestamps.get(ascentend)+" / "+DetailFrag_5.timestamps.get(ascentstart));
//        Log.e("Staircase Left Leg",DetailFrag_5.timestamps.get(turnend)+" / "+DetailFrag_5.timestamps.get(turnstart));
//        Log.e("Staircase Left Leg",DetailFrag_5.timestamps.get(descentend)+" / "+DetailFrag_5.timestamps.get(descentstart));

//        Log.e("STAIRCASE CLIMBING TIMESTAMPS", String.valueOf(DetailFrag_5.ascentend) +  " / " + String.valueOf(DetailFrag_5.ascentstart) + " / " + String.valueOf(DetailFrag_5.leftleg.get(DetailFrag_5.ascentstart))+ " / " + String.valueOf(DetailFrag_5.leftleg.get(DetailFrag_5.ascentend)));
//        Log.e("STAIRCASE CLIMBING TIMESTAMPS", String.valueOf(DetailFrag_5.turnend) + " / " + String.valueOf(DetailFrag_5.turnstart) + " / " + String.valueOf(DetailFrag_5.leftleg.get(DetailFrag_5.turnstart))+ " / " + String.valueOf(DetailFrag_5.leftleg.get(DetailFrag_5.turnend)));
//        Log.e("STAIRCASE CLIMBING TIMESTAMPS", String.valueOf(DetailFrag_5.descentend) + " / " + String.valueOf(DetailFrag_5.descentstart) + " / " + String.valueOf(DetailFrag_5.leftleg.get(DetailFrag_5.descentstart))+ " / " + String.valueOf(DetailFrag_5.leftleg.get(DetailFrag_5.descentend)));
//        Log.e("STAIRCASE CLIMBING TIMESTAMPS", String.valueOf(DetailFrag_5.stepCount));
        DetailFrag_5.tempRow = new ArrayList<>();
        ascentflag = 1;
        descentflag = 0;
        turnflag = 0;


        DetailFrag_5.isDecreasing = false;
        DetailFrag_5.isIncreasing = false;
        cycleMax = Float.MIN_VALUE;
        cycleMin = Float.MAX_VALUE;
        cycleStarted = false;
        potentialEnd = false;
        potentialEndIndex = -1;

        List<Long> rightbeforeFailTimestampsstart = new ArrayList<>();
        List<Long> rightduringFailTimestampsstart = new ArrayList<>();
        List<Long> rightafterFailTimestampsstart = new ArrayList<>();

        List<Long> rightbeforeFailTimestampsend = new ArrayList<>();
        List<Long> rightduringFailTimestampsend = new ArrayList<>();
        List<Long> rightafterFailTimestampsend = new ArrayList<>();


        aftfail = 0;
        for (int i = 0; i < data1.size() - 1; i++) {
            DetailFrag_5.change = data1.get(i + 1) - data1.get(i);

            if (DetailFrag_5.change > 0) {
                // **Increasing phase detected**
                if (!cycleStarted) {
                    DetailFrag_5.startind = i; // Start of a new cycle
                    cycleStarted = true;
                }

                if (potentialEnd) {
                    // Confirm cycle end if increase continues
                    int cycleDuration = potentialEndIndex - DetailFrag_5.startind;
                    float cycleAmplitude = cycleMax - cycleMin;

                    if (cycleDuration >= minDuration && cycleAmplitude >= minAmplitude) {
                        DetailFrag_5.endind = potentialEndIndex;

                        Log.e("Right Leg Valid Cycle", "Start: " + DetailFrag_5.timestamps.get(DetailFrag_5.startind) +
                                " / End: " + DetailFrag_5.timestamps.get(DetailFrag_5.endind));
                        DetailFrag_5.stepCount++;
                        // **Manage timestamp splits**
                        if (cycleMax > 45) {

                            if (aftfail == 1) {
                                DetailFrag_5.descentct++;
                                turnflag = 1;
                                rightafterFailTimestampsstart.add(DetailFrag_5.timestamps.get(DetailFrag_5.startind));
                                rightafterFailTimestampsend.add(DetailFrag_5.timestamps.get(DetailFrag_5.endind));
                            } else {
                                DetailFrag_5.ascentct++;
                                rightbeforeFailTimestampsstart.add(DetailFrag_5.timestamps.get(DetailFrag_5.startind));
                                rightbeforeFailTimestampsend.add(DetailFrag_5.timestamps.get(DetailFrag_5.endind));
                            }
                        }
                        else if (turnflag == 0) {
                            aftfail = 1;
                            rightduringFailTimestampsstart.add(DetailFrag_5.timestamps.get(DetailFrag_5.startind));
                            rightduringFailTimestampsend.add(DetailFrag_5.timestamps.get(DetailFrag_5.endind));
                        }

//                        generateParagraph(DetailFrag_5.tempRow);
                    }

                    // Reset for next cycle
                    cycleStarted = false;
                    potentialEnd = false;
                    cycleMax = Float.MIN_VALUE;
                    cycleMin = Float.MAX_VALUE;
                    DetailFrag_5.tempRow = new ArrayList<>();
                }

                DetailFrag_5.isIncreasing = true;
                DetailFrag_5.isDecreasing = false;
                cycleMax = Math.max(cycleMax, data1.get(i));
                cycleMin = Math.min(cycleMin, data1.get(i));
                DetailFrag_5.tempRow.add(new Entry(i, data1.get(i)));

            } else if (DetailFrag_5.change < 0 && DetailFrag_5.isIncreasing) {
                // **Decreasing phase starts after an increase**
                DetailFrag_5.isDecreasing = true;
                cycleMax = Math.max(cycleMax, data1.get(i));
                cycleMin = Math.min(cycleMin, data1.get(i));
                DetailFrag_5.tempRow.add(new Entry(i, data1.get(i)));
                potentialEnd = false; // Reset potential end

            } else if (DetailFrag_5.isDecreasing && DetailFrag_5.change == 0) {
                // **Flat region detected during decrease phase**
                potentialEnd = true;  // Flag this as a possible cycle end
                potentialEndIndex = i;

            } else if (DetailFrag_5.isIncreasing && DetailFrag_5.isDecreasing && data1.get(i + 1) >= data1.get(i)) {
                // **Cycle is only completed at the true local minimum**
                if (!potentialEnd) {
                    potentialEnd = true;
                    potentialEndIndex = i;
                }
            }
        }
        if (cycleStarted && DetailFrag_5.isIncreasing && DetailFrag_5.isDecreasing) {
            int cycleDuration = data1.size() - 1 - DetailFrag_5.startind;
            float cycleAmplitude = cycleMax - cycleMin;
            DetailFrag_5.stepCount++;

            if (cycleDuration >= minDuration && cycleAmplitude >= minAmplitude) {
                DetailFrag_5.descentct++;
                DetailFrag_5.endind = potentialEnd ? potentialEndIndex : data1.size() - 1;

                Log.e("Right Valid Cycle", "Start: " + DetailFrag_5.timestamps.get(DetailFrag_5.startind) +
                        " / End: " + DetailFrag_5.timestamps.get(DetailFrag_5.endind));

                DetailFrag_5.startind = 0;
                rightafterFailTimestampsstart.add(DetailFrag_5.timestamps.get(DetailFrag_5.startind));
                rightafterFailTimestampsend.add(DetailFrag_5.timestamps.get(DetailFrag_5.endind));


//                generateParagraph(DetailFrag_5.tempRow);
            }
        }


//        for (int i = 0; i < data1.size() - 1; i++) {
//            //float difference = angles[i] - angles[i - 1];
//            DetailFrag_5.change = data1.get(i + 1) - data1.get(i);
//
//
//            if (i + 1 == data1.size() - 1) {
//                DetailFrag_5.tempRow.add(new Entry(i, data1.get(i)));
//            }
//
//            if (DetailFrag_5.change > 0) {
//                // Angle is increasing
//                DetailFrag_5.isIncreasing = true;
//                Log.e("SAGA GTA increase", String.valueOf(data1.get(i)));
//                // Reset decreasing and cycle-ready flags
//                DetailFrag_5.isDecreasing = false;
//                DetailFrag_5.cycleReady = false;
//                DetailFrag_5.tempRow.add(new Entry(i, data1.get(i)));
//            } else if (DetailFrag_5.change < 0 && DetailFrag_5.isIncreasing) {
//                // Transition to decreasing after an increase
//                Log.e("SAGA GTA decrease", String.valueOf(data1.get(i)));
//                DetailFrag_5.isDecreasing = true;
//                DetailFrag_5.tempRow.add(new Entry(i, data1.get(i)));
//                Log.e("Cycle Temprow inba", String.valueOf(DetailFrag_5.substaircase));
//            } else if (DetailFrag_5.change == 0) {
//                DetailFrag_5.tempRow.add(new Entry(i, data1.get(i)));
//            }
//
//            // Update local minimum during the decreasing phase
////            if (DetailFrag_5.isDecreasing) {
////                DetailFrag_5.localMinimum = Math.min(DetailFrag_5.localMinimum, objectElements.get(i).getY());
////                Log.e("Local Minimum", String.valueOf(DetailFrag_5.localMinimum));
////            }
//
//            // Check if stabilization near the local minimum occurs
//            if (DetailFrag_5.isIncreasing && DetailFrag_5.isDecreasing && data1.get(i + 1) >= data1.get(i)) {
//                DetailFrag_5.cycleReady = true; // Mark the cycle as ready
//            }
//
//            // Only count the cycle if it stabilizes and transitions properly
//            if (DetailFrag_5.cycleReady) {
//                DetailFrag_5.ct++;
//                DetailFrag_5.isIncreasing = false;
//                DetailFrag_5.isDecreasing = false;
//                DetailFrag_5.cycleReady = false; // Reset flags
//                DetailFrag_5.localMinimum = Float.MAX_VALUE; // Reset local minimum
//
//                //DetailFrag_5.highlightArrayact.add(DetailFrag_5.substaircase);
//                //Log.e("SAGA GTA 2", i + String.valueOf(DetailFrag_5.highlightArrayact));
//                DetailFrag_5.endind = i;
//                Log.e("Staircase Inba YoYo Right", String.valueOf(DetailFrag_5.tempRow));
//                generateParagraph(DetailFrag_5.tempRow);
//                Log.e("Staircase Right Leg", DetailFrag_5.timestamps.get(DetailFrag_5.startind)+" / "+DetailFrag_5.timestamps.get(DetailFrag_5.endind));
//                DetailFrag_5.startind = i + 1;
//                DetailFrag_5.tempRow = new ArrayList<>();
//            }
//        }

//        rascent = Math.abs((DetailFrag_5.timestamps.get(ascentend) - DetailFrag_5.timestamps.get(ascentstart)) / 1000.0);
//        rturn = Math.abs((DetailFrag_5.timestamps.get(turnend) - DetailFrag_5.timestamps.get(turnstart)) / 1000.0);
//        rdescent = Math.abs((DetailFrag_5.timestamps.get(descentend) - DetailFrag_5.timestamps.get(descentstart)) / 1000.0);

        JSONArray turnTimestamps = new JSONArray();
        boolean turnStarted = false;
        int turnStartIndex = -1, turnEndIndex = -1;
        float turnMax = Float.MIN_VALUE, turnMin = Float.MAX_VALUE;
        final float TURN_ANGLE_THRESHOLD = 150.0f; // Minimum angle change for a valid turn

        int startAngle = -360, endAngle = -360;
        long turnStartTime = -1, turnEndTime = -1;
        boolean isTurning = false;

        DetailFrag_5.adjustedrolldataleft = new JSONArray();
        List<Long> turnlefttimestamp = new ArrayList<>();
        List<Long> turnlefttimestamp1 = new ArrayList<>();

        DetailFrag_5.adjustedrolldataright = new JSONArray();
        List<Long> turnrighttimestamp = new ArrayList<>();
        List<Long> turnrighttimestamp1 = new ArrayList<>();


        int baseRoll = 0; // The first roll value as the reference point
        if(DetailFrag_5.rolldataleft.length() !=0) {
            try {
                baseRoll = DetailFrag_5.rolldataleft.getInt(0);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }

            for (int i = 0; i < DetailFrag_5.rolldataleft.length(); i++) {
                try {
                    DetailFrag_5.adjustedrolldataleft.put((DetailFrag_5.rolldataleft.getInt(i) - baseRoll + 180 + 360) % 360);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        Log.e("Staricase Turn Time Stamps adjroll", String.valueOf(DetailFrag_5.rolldataleft));
        Log.e("Staricase Turn Time Stamps adjtime", String.valueOf(DetailFrag_5.timestamps));
        Log.e("Staricase Turn Time Stamps adjroll", String.valueOf(DetailFrag_5.rolldataleft));
        Log.e("Staricase Turn Time Stamps adjtime", String.valueOf(DetailFrag_5.timestamps));

        turnlefttimestamp=findLongestIncreasingSequence(DetailFrag_5.adjustedrolldataleft,DetailFrag_5.timestamps);
        turnlefttimestamp1=findLongestDecreasingSequence(DetailFrag_5.adjustedrolldataleft,DetailFrag_5.timestamps);



//        for (int i = 0; i < rolldataleft.length() - 1; i++) {
//            int change = 0;
//            try {
//                double currentAngle = rolldataleft.getDouble(i);
//                double nextAngle = rolldataleft.getDouble(i + 1);
//
//                // Adjust angles if greater than 180
//                if (currentAngle > 180) currentAngle -= 360;
//                if (nextAngle > 180) nextAngle -= 360;
//
//                change = (int) (nextAngle - currentAngle);
//            } catch (JSONException e) {
//                throw new RuntimeException(e);
//            }
//
//            // Detect start of turn
//            if (!isTurning && Math.abs(change) > 5) { // Small threshold to start detecting
//                isTurning = true;
//                turnStartIndex = i;
//                turnStartTime = DetailFrag_5.timestamps.get(i);
//                try {
//                    startAngle = (int) rolldataleft.getDouble(i);
//                    if (startAngle > 180) startAngle -= 360;
//                } catch (JSONException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//
//            // If already turning, check if threshold is met
//            if (isTurning) {
//                try {
//                    endAngle = (int) rolldataleft.getDouble(i);
//                    if (endAngle > 180) endAngle -= 360;
//                } catch (JSONException e) {
//                    throw new RuntimeException(e);
//                }
//                turnEndIndex = i;
//                turnEndTime = DetailFrag_5.timestamps.get(i);
//
//                int totalAngleChange = Math.abs(endAngle - startAngle);
//                long turnDuration = turnEndTime - turnStartTime;
//
//                // If the turn meets the conditions
//                if (totalAngleChange >= 150 && turnDuration >= 500) {
//                    Log.e("Turn Detected", "Start: " + startAngle + "° at " + turnStartTime +
//                            " / End: " + endAngle + "° at " + turnEndTime +
//                            " / Total Angle Change: " + totalAngleChange + "°");
//
//                    // Reset for detecting the next turn
//                    isTurning = false;
//                    startAngle = -360;
//                    endAngle = -360;
//                    turnStartIndex = -1;
//                    turnEndIndex = -1;
//                }
//            }
//        }

        Log.e("Staricase Turn Time Stamps Step Count", String.valueOf(DetailFrag_5.stepCount));

        Log.e("Staricase Turn Time Stamps Left data", String.valueOf(turnlefttimestamp));
        Log.e("Staricase Turn Time Stamps Left data", String.valueOf(turnlefttimestamp1));
//        Log.e("Staricase Turn Time Stamps Left data", DetailFrag_5.timestamps.size() + " / " + String.valueOf(DetailFrag_5.timestamps));
//        Log.e("Staricase Turn Time Stamps Left data", adjustedrolldataleft.length() + " / " + String.valueOf(adjustedrolldataleft));

        turnTimestamps = new JSONArray();
        turnStarted = false;
        turnStartIndex = -1;
        turnEndIndex = -1;
        turnMax = Float.MIN_VALUE;
        turnMin = Float.MAX_VALUE;

        if(DetailFrag_5.rolldataright.length() !=0) {
            try {
                baseRoll = DetailFrag_5.rolldataright.getInt(0);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }

            for (int i = 0; i < DetailFrag_5.rolldataright.length(); i++) {
                try {
                    DetailFrag_5.adjustedrolldataright.put((DetailFrag_5.rolldataright.getInt(i) - baseRoll + 180 + 360) % 360);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }


        turnrighttimestamp=findLongestIncreasingSequence(DetailFrag_5.adjustedrolldataright,DetailFrag_5.timestamps);
        turnrighttimestamp1=findLongestDecreasingSequence(DetailFrag_5.adjustedrolldataright,DetailFrag_5.timestamps);

        double leftturnst=0,leftturnend=0,rightturnst=0,rightturnend=0,turnst=0,turnend1=0;
        if((turnlefttimestamp.get(1)-turnlefttimestamp.get(0))>=(turnlefttimestamp1.get(1)-turnlefttimestamp1.get(0))){
            leftturnst=turnlefttimestamp.get(0);
            leftturnend=turnlefttimestamp.get(1);
        }
        else{
            leftturnst=turnlefttimestamp1.get(0);
            leftturnend=turnlefttimestamp1.get(1);
        }

        if((turnrighttimestamp.get(1)-turnrighttimestamp.get(0))>=(turnrighttimestamp1.get(1)-turnrighttimestamp1.get(0))){
            rightturnst=turnrighttimestamp.get(0);
            rightturnend=turnrighttimestamp.get(1);
        }
        else{
            rightturnst=turnrighttimestamp1.get(0);
            rightturnend=turnrighttimestamp1.get(1);
        }

            if(leftturnst<=rightturnst){
                turnst=leftturnst;
            }
            else{
                turnst=rightturnst;
            }



            if(leftturnend<=rightturnend){
                turnend1=leftturnend;
            }
            else{
                turnend1=rightturnend;
            }


        Log.e("Staricase Turn Time Stamps Right data", String.valueOf(turnrighttimestamp));
        Log.e("Staricase Turn Time Stamps Right data", String.valueOf(turnrighttimestamp1));

        Log.e("Staricase Turn Time Stamps Left data", String.valueOf(DetailFrag_5.adjustedrolldataleft));
        Log.e("Staricase Turn Time Stamps Right data", String.valueOf(DetailFrag_5.adjustedrolldataright));

        Log.e("Staricase Turn Time Stamps", String.valueOf(turnst));
        Log.e("Staricase Turn Time Stamps", String.valueOf(turnend1));

//        try {
//            for (int i = 0; i < rolldataright.length() - 1; i++) {
//                float change = (float) (rolldataright.getDouble(i + 1) - rolldataright.getDouble(i));
//
//                if (change > 0) {
//                    // **Increasing phase detected**
//                    if (!turnStarted) {
//                        turnStartIndex = i; // Start of a turn
//                        turnStarted = true;
//                    }
//                    turnMax = Math.max(turnMax, (float) rolldataright.getDouble(i));
//                    turnMin = Math.min(turnMin, (float) rolldataright.getDouble(i));
//
//                } else if (change < 0 && turnStarted) {
//                    // **Decreasing phase starts after an increase**
//                    turnMax = Math.max(turnMax, (float) rolldataright.getDouble(i));
//                    turnMin = Math.min(turnMin, (float) rolldataright.getDouble(i));
//                    turnEndIndex = i;
//
//                } else if (turnStarted && change == 0) {
//                    // **Flat region detected (potential end)**
//                    float angleChange = Math.abs(turnMax - turnMin);
//                    Log.e("Staricase Turn Time Stamps Right", String.valueOf(angleChange) + " / " + String.valueOf(360 - angleChange));
//                    if (angleChange >= TURN_ANGLE_THRESHOLD) {
//                        JSONObject turn = new JSONObject();
//                        turn.put("start", DetailFrag_5.timestamps.get(turnStartIndex));
//                        turn.put("end", DetailFrag_5.timestamps.get(turnEndIndex));
//                        turnTimestamps.put(turn);
//                    }
//                    turnStarted = false;
//                    turnMax = Float.MIN_VALUE;
//                    turnMin = Float.MAX_VALUE;
//                }
//            }
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }

//        Log.e("Staricase Turn Time Stamps Right",rolldataright.length()+" / "+ String.valueOf(rolldataright));


        Log.e("Staircase Right Leg", String.valueOf(rascent));
        Log.e("Staircase Right Leg", String.valueOf(rturn));
        Log.e("Staircase Right Leg", String.valueOf(rdescent));

//        Log.e("Staircase Right Leg",DetailFrag_5.timestamps.get(ascentend)+" / "+DetailFrag_5.timestamps.get(ascentstart));
//        Log.e("Staircase Right Leg",DetailFrag_5.timestamps.get(turnend)+" / "+DetailFrag_5.timestamps.get(turnstart));
//        Log.e("Staircase Right Leg",DetailFrag_5.timestamps.get(descentend)+" / "+DetailFrag_5.timestamps.get(descentstart));


        Log.e("STAIRCASE CLIMBING TIMESTAMPS", String.valueOf(DetailFrag_5.ascentend) + " / " + String.valueOf(DetailFrag_5.ascentstart) + " / " + String.valueOf(DetailFrag_5.rightleg.get(DetailFrag_5.ascentstart)) + " / " + String.valueOf(DetailFrag_5.rightleg.get(DetailFrag_5.ascentend)));
        Log.e("STAIRCASE CLIMBING TIMESTAMPS", String.valueOf(DetailFrag_5.turnend) + " / " + String.valueOf(DetailFrag_5.turnstart) + " / " + String.valueOf(DetailFrag_5.rightleg.get(DetailFrag_5.turnstart)) + " / " + String.valueOf(DetailFrag_5.rightleg.get(DetailFrag_5.turnend)));
        Log.e("STAIRCASE CLIMBING TIMESTAMPS", String.valueOf(DetailFrag_5.descentend) + " / " + String.valueOf(DetailFrag_5.descentstart) + " / " + String.valueOf(DetailFrag_5.rightleg.get(DetailFrag_5.descentstart)) + " / " + String.valueOf(DetailFrag_5.rightleg.get(DetailFrag_5.descentend)));
        Log.e("STAIRCASE CLIMBING TIMESTAMPS", String.valueOf(DetailFrag_5.stepCount));

//        float angle = data.get(0);
//        float angle1 = data.get(1);
//
//        DetailFrag_5.leftleg.add(angle);
//        DetailFrag_5.rightleg.add(angle1);
//        DetailFrag_5.staircasetime.add((int) System.currentTimeMillis());
//
//        if (angle > DetailFrag_5.ASCENT_ANGLE || angle1 > DetailFrag_5.ASCENT_ANGLE) {
//            if (!DetailFrag_5.isAscent) {
//                DetailFrag_5.ascentStartTime = DetailFrag_5.sec;
//                DetailFrag_5.isAscent = true;
//                DetailFrag_5.isTurn = false;
//                DetailFrag_5.isDescent = false;
//            }
//            DetailFrag_5.ascentEndTime = DetailFrag_5.sec;
//            Log.e("AScent Time", String.valueOf(DetailFrag_5.ascentStartTime)+" / "+String.valueOf(DetailFrag_5.ascentEndTime));
//        }
//        else if ((angle < DetailFrag_5.DESCENT_ANGLE || angle1 < DetailFrag_5.DESCENT_ANGLE) && !DetailFrag_5.isDescent) {
//            if (!DetailFrag_5.isDescent) {
//                DetailFrag_5.descentStartTime = DetailFrag_5.sec;
//                DetailFrag_5.isAscent = false;
//                DetailFrag_5.isTurn = false;
//                DetailFrag_5.isDescent = true;
//            }
//            DetailFrag_5.descentEndTime = DetailFrag_5.sec;
//            Log.e("DEscent Time", String.valueOf(DetailFrag_5.descentStartTime)+" / "+String.valueOf(DetailFrag_5.descentEndTime));
//        }
//        else if (Math.abs(angle - angle1) < DetailFrag_5.TURN_ANGLE_THRESHOLD) {
//            if (!DetailFrag_5.isTurn) {
//                DetailFrag_5.turnStartTime = DetailFrag_5.sec;
//                DetailFrag_5.isAscent = false;
//                DetailFrag_5.isTurn = true;
//                DetailFrag_5.isDescent = false;
//            }
//            DetailFrag_5.turnEndTime = DetailFrag_5.sec;
//
//        }
//
//
//        if (Math.abs(angle - angle1) > DetailFrag_5.STEP_ANGLE_THRESHOLD) {
//            DetailFrag_5.stepCount++;
//        }
//
//        previousLeftKneeAngle = angle;
//        previousRightKneeAngle = angle1;

//        System.out.println("Ascent Time: " + (DetailFrag_5.ascentEndTime - DetailFrag_5.ascentStartTime) / 1000.0 + " seconds");
//        System.out.println("Descent Time: " + (DetailFrag_5.descentEndTime - DetailFrag_5.descentStartTime) / 1000.0 + " seconds");
//        System.out.println("Turn Time: " + (DetailFrag_5.turnEndTime - DetailFrag_5.turnStartTime) / 1000.0 + " seconds");
//        System.out.println("Number of Steps Covered: " + DetailFrag_5.stepCount);

        String hello = "hello";

        Log.e("Left Leg Ascent Valid Cycle", String.valueOf(leftbeforeFailTimestampsstart));
        Log.e("Left Leg Ascent Valid Cycle", String.valueOf(leftbeforeFailTimestampsend));
        Log.e("Left Leg Turn Valid Cycle", String.valueOf(leftduringFailTimestampsstart));
        Log.e("Left Leg Turn Valid Cycle", String.valueOf(leftduringFailTimestampsend));
        Log.e("Left Leg Descent Valid Cycle", String.valueOf(leftafterFailTimestampsstart));
        Log.e("Left Leg Descent Valid Cycle", String.valueOf(leftafterFailTimestampsend));

        Log.e("Right Leg Ascent Valid Cycle", String.valueOf(rightbeforeFailTimestampsstart));
        Log.e("Right Leg Ascent Valid Cycle", String.valueOf(rightbeforeFailTimestampsend));
        Log.e("Right Leg Turn Valid Cycle", String.valueOf(rightduringFailTimestampsstart));
        Log.e("Right Leg Turn Valid Cycle", String.valueOf(rightduringFailTimestampsend));
        Log.e("Right Leg Descent Valid Cycle", String.valueOf(rightafterFailTimestampsstart));
        Log.e("Right Leg Descent Valid Cycle", String.valueOf(rightafterFailTimestampsend));

        long asc = 0, dsc = 0, turn = 0;
        long ascstart = 0, ascend = 0, dscstart = 0, dscend = 0, turnstart = 0, turnend = 0;

        if (leftbeforeFailTimestampsstart.size() != 0 && rightbeforeFailTimestampsstart.size() != 0) {
            if (leftbeforeFailTimestampsstart.get(0) <= rightbeforeFailTimestampsstart.get(0)) {
                ascstart = leftbeforeFailTimestampsstart.get(0);
            } else {
                ascstart = rightbeforeFailTimestampsstart.get(0);
            }
        }

        if (leftbeforeFailTimestampsend.size() != 0 && rightbeforeFailTimestampsend.size() != 0) {
            if (leftbeforeFailTimestampsend.get(leftbeforeFailTimestampsend.size() - 1) >= rightbeforeFailTimestampsend.get(rightbeforeFailTimestampsend.size() - 1)) {
                ascend = leftbeforeFailTimestampsend.get(leftbeforeFailTimestampsend.size() - 1);
            } else {
                ascend = rightbeforeFailTimestampsend.get(rightbeforeFailTimestampsend.size() - 1);
            }
        }

        if (leftafterFailTimestampsstart.size() != 0 && rightafterFailTimestampsstart.size() != 0) {
            if (leftafterFailTimestampsstart.get(0) <= rightafterFailTimestampsstart.get(0)) {
                dscstart = leftafterFailTimestampsstart.get(0);
            } else {
                dscstart = rightafterFailTimestampsstart.get(0);
            }
        }

        if (leftafterFailTimestampsend.size() != 0 && rightafterFailTimestampsend.size() != 0) {
            if (leftafterFailTimestampsend.get(leftafterFailTimestampsend.size() - 1) >= rightafterFailTimestampsend.get(rightafterFailTimestampsend.size() - 1)) {
                dscend = leftafterFailTimestampsend.get(leftafterFailTimestampsend.size() - 1);
            } else {
                dscend = rightafterFailTimestampsend.get(rightafterFailTimestampsend.size() - 1);
            }
        }

        if (leftduringFailTimestampsstart.size() != 0 && rightduringFailTimestampsstart.size() != 0) {
            if (leftduringFailTimestampsstart.get(0) <= rightduringFailTimestampsstart.get(0)) {
                turnstart = leftduringFailTimestampsstart.get(0);
            } else {
                turnstart = rightduringFailTimestampsstart.get(0);
            }
        }

        if (leftduringFailTimestampsend.size() != 0 && rightduringFailTimestampsend.size() != 0) {
            if (leftduringFailTimestampsend.get(leftduringFailTimestampsend.size() - 1) >= rightduringFailTimestampsend.get(rightduringFailTimestampsend.size() - 1)) {
                turnend = leftduringFailTimestampsend.get(leftduringFailTimestampsend.size() - 1);
            } else {
                turnend = rightduringFailTimestampsend.get(rightduringFailTimestampsend.size() - 1);
            }
        }

        Log.e("Total Leg Ascent Valid Cycle", String.valueOf((turnst - ascstart) / 1000.0));
        Log.e("Total Leg Turn Valid Cycle", String.valueOf((turnend1 - turnst) / 1000.0));
        Log.e("Total Leg Descent Valid Cycle", String.valueOf((dscend - turnend1) / 1000.0));


        lascent = Math.abs((turnst - ascstart) / 1000.0);
        lturn = Math.abs((turnend1 - turnst) / 1000.0);
        ldescent = Math.abs((dscend - turnend1) / 1000.0);

//        if ((lascent == 0 && ldescent == 0 && lturn == 0) || (lascent == 0 && ldescent == 0) || (ldescent == 0 && lturn == 0) || (lturn == 0 && lascent == 0)) {
////            Toasty.warning(Assessment.this, "Test not properly performed", Toast.LENGTH_SHORT).show();
//            double sumPhases = lascent + lturn + ldescent;
//            if (sumPhases == 0) {
//                Log.e("Error", "Sum of phases is 0. Cannot adjust.");
//                return; // Avoid division by zero
//            }
//            // Step 3: Find the difference
//            double difference = (DetailFrag_5.timestamps.get(DetailFrag_5.timestamps.size() - 1) / 1000.0) - sumPhases;
//            if (difference > 3) {
//                // Case 1: Missing time, so we ADD proportionally
//                double ascRatio = lascent / sumPhases;
//                double turnRatio = lturn / sumPhases;
//                double dscRatio = ldescent / sumPhases;
//
//                lascent += ascRatio * difference;
//                lturn += turnRatio * difference;
//                ldescent += dscRatio * difference;
//            } else if (difference < 3) {
//                // Case 2: Extra time, so we REDUCE proportionally
//                double ascRatio = lascent / sumPhases;
//                double turnRatio = lturn / sumPhases;
//                double dscRatio = ldescent / sumPhases;
//
//                lascent -= ascRatio * difference; // difference is negative, reducing the value
//                lturn -= turnRatio * difference;
//                ldescent -= dscRatio * difference;
//            }
//        } else if (lascent == 0) {
//            lascent = Math.abs(DetailFrag_5.timestamps.get(DetailFrag_5.timestamps.size() - 1) - (ldescent + lturn)) / 1000.0;
//        } else if (lturn == 0) {
//            lturn = Math.abs(DetailFrag_5.timestamps.get(DetailFrag_5.timestamps.size() - 1) - (ldescent + lascent)) / 1000.0;
//        } else if (ldescent == 0) {
//            ldescent = Math.abs(DetailFrag_5.timestamps.get(DetailFrag_5.timestamps.size() - 1) - (lascent + lturn)) / 1000.0;
//        }
//        else{
//
//            double sumPhases = lascent + lturn + ldescent;
//
//            if (sumPhases == 0) {
//                Log.e("Error", "Sum of phases is 0. Cannot adjust.");
//                return; // Avoid division by zero
//            }
//
//            // Step 3: Find the difference
//            double difference = (DetailFrag_5.timestamps.get(DetailFrag_5.timestamps.size()-1)/1000.0) - sumPhases;
//
//            if (difference > 3) {
//                // Case 1: Missing time, so we ADD proportionally
//                double ascRatio = lascent / sumPhases;
//                double turnRatio = lturn / sumPhases;
//                double dscRatio = ldescent / sumPhases;
//
//                lascent += ascRatio * difference;
//                lturn += turnRatio * difference;
//                ldescent += dscRatio * difference;
//            }
//            else if (difference < 3) {
//                // Case 2: Extra time, so we REDUCE proportionally
//                double ascRatio = lascent / sumPhases;
//                double turnRatio = lturn / sumPhases;
//                double dscRatio = ldescent / sumPhases;
//
//                lascent -= ascRatio * difference; // difference is negative, reducing the value
//                lturn -= turnRatio * difference;
//                ldescent -= dscRatio * difference;
//            }
//
////            // Step 4: Ensure total sum is exactly totalTime
////            double finalSum = lascent + lturn + ldescent;
////            if (Math.abs(finalSum - totalTime) > 0.01) { // Allow minor floating-point errors
////                Log.e("Error", "Adjustment failed, sum not matching total time.");
////            }
////
////            // Step 5: Print or use corrected values
////            Log.d("Adjusted Times", "Ascent: " + lascent + "s, Turn: " + lturn + "s, Descent: " + ldescent + "s");
//        }

    }

    public static List<Long> findLongestIncreasingSequence(JSONArray angles, List<Long> timestamps) {
        List<Long> result = new ArrayList<>();
        List<Integer> currentAngles = new ArrayList<>();
        List<Long> currentTimestamps = new ArrayList<>();

        long longestStart = 0;
        long longestEnd = 0;
        int maxDiff = 120;  // Minimum required difference in angles

        try {
            currentAngles.add(angles.getInt(0));
            currentTimestamps.add(timestamps.get(0));

            for (int i = 1; i < angles.length(); i++) {
                int currentAngle = angles.getInt(i);
                long currentTimestamp = timestamps.get(i);

                if (currentAngle >= currentAngles.get(currentAngles.size() - 1)) {
                    // Continue increasing sequence
                    currentAngles.add(currentAngle);
                    currentTimestamps.add(currentTimestamp);
                } else {
                    // Check if the sequence meets the angle difference requirement before resetting
                    if (!currentAngles.isEmpty() && (currentAngles.get(currentAngles.size() - 1) - currentAngles.get(0) >= maxDiff)) {
                        long start = currentTimestamps.get(0);
                        long end = currentTimestamps.get(currentTimestamps.size() - 1);

                        if ((end - start) > (longestEnd - longestStart)) {
                            longestStart = start;
                            longestEnd = end;
                        }
                    }
                    // Reset for a new sequence
                    currentAngles.clear();
                    currentTimestamps.clear();
                    currentAngles.add(currentAngle);
                    currentTimestamps.add(currentTimestamp);
                }
            }

            // Final check at the end of the loop
            if (!currentAngles.isEmpty() && (currentAngles.get(currentAngles.size() - 1) - currentAngles.get(0) >= maxDiff)) {
                long start = currentTimestamps.get(0);
                long end = currentTimestamps.get(currentTimestamps.size() - 1);
                if ((end - start) > (longestEnd - longestStart)) {
                    longestStart = start;
                    longestEnd = end;
                }
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        result.add(longestStart);
        result.add(longestEnd);
        return result;
    }

    public static List<Long> findLongestDecreasingSequence(JSONArray angles, List<Long> timestamps) {
        List<Long> result = new ArrayList<>();
        List<Integer> currentAngles = new ArrayList<>();
        List<Long> currentTimestamps = new ArrayList<>();

        long longestStart = 0;
        long longestEnd = 0;
        int minDiff = 120;  // Minimum required difference in angles

        try {
            currentAngles.add(angles.getInt(0));
            currentTimestamps.add(timestamps.get(0));

            for (int i = 1; i < angles.length(); i++) {
                int currentAngle = angles.getInt(i);
                long currentTimestamp = timestamps.get(i);

                if (currentAngle <= currentAngles.get(currentAngles.size() - 1)) {
                    // Continue decreasing sequence
                    currentAngles.add(currentAngle);
                    currentTimestamps.add(currentTimestamp);
                } else {
                    // Check if the sequence meets the angle difference requirement before resetting
                    if (!currentAngles.isEmpty() && (currentAngles.get(0) - currentAngles.get(currentAngles.size() - 1) >= minDiff)) {
                        long start = currentTimestamps.get(0);
                        long end = currentTimestamps.get(currentTimestamps.size() - 1);

                        if ((end - start) > (longestEnd - longestStart)) {
                            longestStart = start;
                            longestEnd = end;
                        }
                    }
                    // Reset for a new sequence
                    currentAngles.clear();
                    currentTimestamps.clear();
                    currentAngles.add(currentAngle);
                    currentTimestamps.add(currentTimestamp);
                }
            }

            // Final check at the end of the loop
            if (!currentAngles.isEmpty() && (currentAngles.get(0) - currentAngles.get(currentAngles.size() - 1) >= minDiff)) {
                long start = currentTimestamps.get(0);
                long end = currentTimestamps.get(currentTimestamps.size() - 1);
                if ((end - start) > (longestEnd - longestStart)) {
                    longestStart = start;
                    longestEnd = end;
                }
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        result.add(longestStart);
        result.add(longestEnd);
        return result;
    }

    private void proprioceptiontest(List<Entry> objectElements) {

        Log.e("OBJECT ELEMENTS", objectElements + " / " + objectElements);

        DetailFrag_5.tempRow = new ArrayList<>();
        DetailFrag_5.highlightArraypass = new ArrayList<>();
        DetailFrag_5.highlightArrayact = new ArrayList<>();
        Log.e("Inside Function", String.valueOf(objectElements));


        DetailFrag_5.postexevalues = new JSONArray();
        DetailFrag_5.reportobject = new JSONObject();
        DetailFrag_5.datareportarray = new JSONArray();
        DetailFrag_5.analysereportarray = new JSONArray();

        DetailFrag_5.cycle++;
//        DetailFrag_5.propriocyclecount++;

        List<Long> indices = new ArrayList<>();
        List<Float> values = new ArrayList<>();
        for (Entry entry : objectElements) {
            indices.add((long) entry.getX());
            values.add(entry.getY());
            try {
                DetailFrag_5.postexevalues.put(entry.getY());
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }

        for (Float angle : values) {
            DetailFrag_5.datareportarray.put(angle);
        }

        Pair<Float, Float> minAndMaxAngles = findMinMaxAngles(values);
        if ("left".equalsIgnoreCase(leg)) {
            DetailFrag_5.leftrom.add(minAndMaxAngles.second);
            DetailFrag_5.indivimaxAngle.add(minAndMaxAngles.second);
            DetailFrag_5.proprom.add(minAndMaxAngles.second);
        } else {
            DetailFrag_5.rightrom.add(minAndMaxAngles.second);
            DetailFrag_5.indivimaxAngle.add(minAndMaxAngles.second);
            DetailFrag_5.proprom.add(minAndMaxAngles.second);
        }
        DetailFrag_5.analysereportarray.put(minAndMaxAngles.first);
//        DetailFrag_5.postexeparameters.put(minAndMaxAngles.first);
        DetailFrag_5.analysereportarray.put(minAndMaxAngles.second);

//        float velocityForPain = Math.abs((minAndMaxAngles.second - minAndMaxAngles.first) / (indices.get(indices.size() - 1) - indices.get(0))) +
//                Math.abs((minAndMaxAngles.second + minAndMaxAngles.first) / (indices.get(indices.size() - 1) - indices.get(0)));
//
//        if (velocityForPain < 10) {
//            DetailFrag_5.pain = 1;
//        } else if (velocityForPain < 20) {
//            DetailFrag_5.pain = 2;
//        } else if (velocityForPain < 30) {
//            DetailFrag_5.pain = 3;
//        } else if (velocityForPain < 40) {
//            DetailFrag_5.pain = 4;
//        } else if (velocityForPain < 50) {
//            DetailFrag_5.pain = 5;
//        } else if (velocityForPain < 60) {
//            DetailFrag_5.pain = 6;
//        } else if (velocityForPain < 70) {
//            DetailFrag_5.pain = 7;
//        } else if (velocityForPain < 80) {
//            DetailFrag_5.pain = 8;
//        } else if (velocityForPain < 90) {
//            DetailFrag_5.pain = 9;
//        } else if (velocityForPain < 100) {
//            DetailFrag_5.pain = 10;
//        } else if (velocityForPain < 110) {
//            DetailFrag_5.pain = 11;
//        } else if (velocityForPain < 120) {
//            DetailFrag_5.pain = 12;
//        } else if (velocityForPain < 130) {
//            DetailFrag_5.pain = 13;
//        } else if (velocityForPain < 140) {
//            DetailFrag_5.pain = 14;
//        } else if (velocityForPain < 150) {
//            DetailFrag_5.pain = 15;
//        } else if (velocityForPain < 160) {
//            DetailFrag_5.pain = 16;
//        } else if (velocityForPain < 170) {
//            DetailFrag_5.pain = 17;
//        } else if (velocityForPain < 180) {
//            DetailFrag_5.pain = 18;
//        } else {
//            DetailFrag_5.pain = 19;
//        }
//        DetailFrag_5.indivipain.add(DetailFrag_5.pain);

        try {
//            DetailFrag_5.analysereportarray.put(Math.round(velocityForPain));
//            DetailFrag_5.postexeparameters.put(Math.round(velocityForPain));
//            DetailFrag_5.analysereportarray.put(Math.round(velocityForPain));
//            DetailFrag_5.analysereportarray.put("12");
//            DetailFrag_5.postexeparameters.put(DetailFrag_5.pain);
            DetailFrag_5.reportobject.put("data", DetailFrag_5.datareportarray);
            DetailFrag_5.reportobject.put("analyse", DetailFrag_5.analysereportarray);
            DetailFrag_5.reportobject.put("exercisename", DetailFrag_5.selectedExercise);
            DetailFrag_5.reportobject.put("mode", leg + "-leg-" + DetailFrag_5.propriocyclecount);
            DetailFrag_5.reportarray.put(DetailFrag_5.reportobject);


        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

//        for (int i = 0; i < objectElements.size() - 1; i++) {
//            //float difference = angles[i] - angles[i - 1];
//            DetailFrag_5.change = objectElements.get(i + 1).getY() - objectElements.get(i).getY();
//
//
//            if (i + 1 == objectElements.size() - 1) {
//                DetailFrag_5.tempRow.add(objectElements.get(i + 1));
//                DetailFrag_5.highlightArraypass.add(DetailFrag_5.tempRow);
//            }
//
//            if (DetailFrag_5.isIncreasing && DetailFrag_5.isDecreasing && objectElements.get(i + 1).getY() >= objectElements.get(i).getY()) {
//                DetailFrag_5.cycleReady = true; // Mark the cycle as ready
//                DetailFrag_5.tempRow.add(objectElements.get(i));
//                // Only count the cycle if it stabilizes and transitions properly
//                if (DetailFrag_5.cycleReady) {
//                    DetailFrag_5.ct++;
//                    DetailFrag_5.isIncreasing = false;
//                    DetailFrag_5.isDecreasing = false;
//                    DetailFrag_5.cycleReady = false; // Reset flags
//                    DetailFrag_5.localMinimum = Float.MAX_VALUE; // Reset local minimum
//
//
//                    Log.e("Highlight Array Shift", i + String.valueOf(DetailFrag_5.highlightArraypass));
//                    generateParagraph(DetailFrag_5.tempRow);
//
//                    DetailFrag_5.postsubdata.put(DetailFrag_5.postexevalues);
//                    DetailFrag_5.postsubdata.put(DetailFrag_5.postexeparameters);
//                    DetailFrag_5.postexevalues = new JSONArray();
//                    DetailFrag_5.postexeparameters = new JSONArray();
//
//                    DetailFrag_5.tempRow = new ArrayList<>();
//                    DetailFrag_5.tempRow.add(objectElements.get(i));
//                }
//            } else if (DetailFrag_5.change > 0) {
//                // Angle is increasing
//                DetailFrag_5.isIncreasing = true;
//                Log.e("SAGA GTA increase", String.valueOf(objectElements.get(i).getY()));
//                // Reset decreasing and cycle-ready flags
//                DetailFrag_5.isDecreasing = false;
//                DetailFrag_5.cycleReady = false;
//                DetailFrag_5.tempRow.add(objectElements.get(i));
//            } else if (DetailFrag_5.change < 0 && DetailFrag_5.isIncreasing) {
//                // Transition to decreasing after an increase
//                Log.e("SAGA GTA decrease", String.valueOf(objectElements.get(i).getY()));
//                DetailFrag_5.isDecreasing = true;
//                DetailFrag_5.tempRow.add(objectElements.get(i));
//                Log.e("Cycle Temprow inba", String.valueOf(DetailFrag_5.tempRow));
//            } else if (DetailFrag_5.change == 0) {
//                DetailFrag_5.tempRow.add(objectElements.get(i));
//            }
//
//            // Update local minimum during the decreasing phase
////            if (DetailFrag_5.isDecreasing) {
////                DetailFrag_5.localMinimum = Math.min(DetailFrag_5.localMinimum, objectElements.get(i).getY());
////                Log.e("Local Minimum", String.valueOf(DetailFrag_5.localMinimum));
////            }
//
//            // Check if stabilization near the local minimum occurs
//
//        }


//        DetailFrag_5.exerciseListact.add(new ExerciseCycleAssessment(minAndMaxAngles.second));
//                            DetailFrag_5.exepain.put(DetailFrag_5.indivipain.get(k));

        if ("Active".equalsIgnoreCase(activepassive)) {
            propriopassvalue = minAndMaxAngles.second;
        } else {
            proprioactvalue = minAndMaxAngles.second;
        }

        assess_cycles_active.setVisibility(View.VISIBLE);
        DetailFrag_5.proprioceptionAdapter.notifyDataSetChanged();

//        for (int i = 0; i < objectElements.size() - 1; i++) {
//            Log.e("Inside Left", String.valueOf(objectElements.size()));
//
//            DetailFrag_5.change = objectElements.get(i).getY() - objectElements.get(i + 1).getY();
//            DetailFrag_5.tempRow.add(objectElements.get(i));
//            if (i + 1 == objectElements.size() - 1) {
//                DetailFrag_5.tempRow.add(objectElements.get(i + 1));
//                DetailFrag_5.highlightArraypass.add(DetailFrag_5.tempRow);
//                generateParagraph(DetailFrag_5.tempRow);
//                Log.e("Highlight Array Last", i + String.valueOf(DetailFrag_5.highlightArraypass));
//            } else if (DetailFrag_5.change == 0) {
//                continue;
//            } else if (DetailFrag_5.prevSignChange1 != 0 && Math.signum(DetailFrag_5.change) != Math.signum(DetailFrag_5.prevSignChange1)) {
//                DetailFrag_5.cycleCount++;
//                DetailFrag_5.highlightArraypass.add(DetailFrag_5.tempRow);
//                Log.e("Highlight Array Shift", i + String.valueOf(DetailFrag_5.highlightArraypass));
//                generateParagraph(DetailFrag_5.tempRow);
//
//                DetailFrag_5.tempRow = new ArrayList<>();
//                DetailFrag_5.tempRow.add(objectElements.get(i));
//            }
//            DetailFrag_5.prevSignChange1 = (int) DetailFrag_5.change;
//        }

        if (DetailFrag_5.propriolegswitchflag == 1) {
            DetailFrag_5.propriolegswitchflag = 0;
        }

        DetailFrag_5.highlightArraypass.add(Collections.singletonList(new Entry(0, 0)));
        DetailFrag_5.highlightArrayact.add(Collections.singletonList(new Entry(0, 0)));


        //Log.e("Highlight Array", i + String.valueOf(highlightArray));


    }

    private void walkgaittest(List<Float> data, List<Float> data1) {

        // Low-pass filter parameters
        double fs = 50.0; // Sampling frequency
        double cutoff = 5.0; // Cutoff frequency
        int order = 4; // Filter order

        JSONArray la = new JSONArray();
        for (int i = 0; i < DetailFrag_5.leftaccl.size(); i++) {
            la.put(DetailFrag_5.leftaccl.get(i));
        }
        JSONArray ra = new JSONArray();
        for (int i = 0; i < DetailFrag_5.righttaccl.size(); i++) {
            ra.put(DetailFrag_5.righttaccl.get(i));
        }
        // Apply low-pass filter to raw acceleration data
        DetailFrag_5.leftacclfilter = LowPassFilterUtils.applyLowPassFilter(la, cutoff, fs, order);
        DetailFrag_5.righttacclfilter = LowPassFilterUtils.applyLowPassFilter(ra, cutoff, fs, order);
        double leftprevstride = 0, rightprevstrie = 0;

//        Log.e("Acceleration Log for Walk gait",DetailFrag_5.leftaccl+" / "+DetailFrag_5.righttaccl);

        Log.e("Walk gait time", String.valueOf(DetailFrag_5.timestamps));

        Log.e("Walk gait timestamps", String.valueOf(DetailFrag_5.entries.size()));

        DetailFrag_5.tempRow = new ArrayList<>();
        DetailFrag_5.tempRow1 = new ArrayList<>();
        DetailFrag_5.consecutiveNoChangeCount = 0;
        DetailFrag_5.breakCount = 0;
        DetailFrag_5.stepCountwalk = 0;

        JSONArray righttimestamp = new JSONArray();
        JSONArray lefttimestamp = new JSONArray();


        int leftstart = 0, rightstart = 0;
        int leftend = 0, rightend = 0;

        List<Double> leftacc = new ArrayList<>();
        List<Double> rightacc = new ArrayList<>();

        float max = 0, max1 = 0;

        for (int i = 0; i < data.size(); i++) {
            DetailFrag_5.leftlegwos.add(data.get(i));
        }
        for (int i = 0; i < data1.size(); i++) {
            DetailFrag_5.rightwos.add(data1.get(i));
        }

        if (data.size() >= data1.size()) {
            for (int i = 0; i < data1.size() - 1; i++) {
                //float difference = angles[i] - angles[i - 1];
                DetailFrag_5.change = data.get(i + 1) - data.get(i);
                DetailFrag_5.change1 = data1.get(i + 1) - data1.get(i);

                Log.e("Change1", DetailFrag_5.change + " / " + DetailFrag_5.change1);

                if (Math.abs(DetailFrag_5.change) <= 2 && Math.abs(DetailFrag_5.change1) <= 2 && Math.abs(DetailFrag_5.change) >= 0 && Math.abs(DetailFrag_5.change1) >= 0) {
                    // No significant change detected
                    DetailFrag_5.consecutiveNoChangeCount++;
                    if (DetailFrag_5.breakStartTime == 0) {
                        DetailFrag_5.breakStartTime = DetailFrag_5.timestamps.get(i);// Record break start time
                    }

                    if (!DetailFrag_5.isInBreak && DetailFrag_5.consecutiveNoChangeCount >= 60) {
                        // Start of a break
                        DetailFrag_5.isInBreak = true;
                        //DetailFrag_5.breakStartTime =  DetailFrag_5.walkgaittime.get(i);// Record break start time

                        Log.e("Break Detected Started", "Break started at index: " + i + " / " + DetailFrag_5.breakStartTime / 1000);
                    }
                } else {
                    DetailFrag_5.consecutiveNoChangeCount = 0;
                    if (DetailFrag_5.isInBreak) {
                        DetailFrag_5.isInBreak = false;
                        DetailFrag_5.breakCount++;
                        long breakEndTime = DetailFrag_5.timestamps.get(i);
                        DetailFrag_5.totalBreakTime += (breakEndTime - DetailFrag_5.breakStartTime);
                        //stance.add(DetailFrag_5.stepCountwalk + " " + String.valueOf((int) (breakEndTime - DetailFrag_5.breakStartTime) / 1000));
                        Log.e("Break Detected Ended", "Break started at index: " + i + " / " + breakEndTime / 1000);
                        DetailFrag_5.breakStartTime = 0;
                    } else if (DetailFrag_5.breakStartTime != 0 && data1.get(i) < 10 && data.get(i) < 10) {
                        long breakEndTime = DetailFrag_5.timestamps.get(i);
                        DetailFrag_5.totalstancepahse += (breakEndTime - DetailFrag_5.breakStartTime);
                        stance.add((long) ((breakEndTime - DetailFrag_5.breakStartTime) / 1000.0));
                        Log.e("Power Rangers Total Stance Phase", i + " / " + String.valueOf(DetailFrag_5.totalstancepahse));
                        DetailFrag_5.breakStartTime = 0;
                    } else {
                        // --- Step Detection for Right Leg ---
                        if (DetailFrag_5.change1 >= 0) {
                            DetailFrag_5.rightlegcyclewalkgait.add(data1.get(i));
//                            try {
//                                rightacc.add((double)DetailFrag_5.righttacclfilter.get(i));
//                            } catch (JSONException e) {
//                                throw new RuntimeException(e);
//                            }
                            rightacc.add(DetailFrag_5.righttaccl.get(i));
                            righttimestamp.put(DetailFrag_5.timestamps.get(i));
                            if (rightstart == 0) {
                                rightstart = i;
                            }

                            DetailFrag_5.isIncreasing = true;


                            DetailFrag_5.stepStartTime = DetailFrag_5.timestamps.get(i); // Start of swing phase
                            Log.e("Walk and Gait Time Index right start", String.valueOf(i));
//                            if (DetailFrag_5.isInBreak) {
//                                // End of a break
//                                DetailFrag_5.isInBreak = false;
//                                long breakEndTime = DetailFrag_5.walkgaittime.get(i);
//                                DetailFrag_5.totalBreakTime += (breakEndTime - DetailFrag_5.breakStartTime);
//                                Log.e("Break Ended", "Break ended at index: " + i);
//                            }
                        } else if (DetailFrag_5.change1 < 0 && DetailFrag_5.isIncreasing) {
                            Log.e("Walk and Gait analysis Right", DetailFrag_5.rightlegcyclewalkgait.toString());
                            DetailFrag_5.rightlegcyclewalkgait.add(data1.get(i));
//                            try {
//                                rightacc.add((double)DetailFrag_5.righttacclfilter.get(i));
//                            } catch (JSONException e) {
//                                throw new RuntimeException(e);
//                            }
                            rightacc.add(DetailFrag_5.righttaccl.get(i));
                            righttimestamp.put(DetailFrag_5.timestamps.get(i));
                            rightend = i;
                            DetailFrag_5.isDecreasing = true;
                            DetailFrag_5.isIncreasing = false;
                            float maxangle = Collections.max(DetailFrag_5.rightlegcyclewalkgait);
                            if (maxangle >= 15) {
                                if (rightstepflag == 0 && leftstepflag == 0) {
                                    rightstepflag = 1;
                                }
                                DetailFrag_5.walkgaitrightlegangles.add(maxangle);
                                DetailFrag_5.stepCountwalk++;
                                DetailFrag_5.rightcyclewalkgati++;
                                Log.e("Log for Walk gait Right", rightacc + " / " + righttimestamp);
                                double stride = stridecalc(rightacc, righttimestamp);
                                if (rightprevstrie != 0) {
                                    if (stride < 35) {
                                        double t = rightprevstrie - stride;
                                        stride = stride + (Math.round(t / 2) + Math.round(t / 3) + Math.round(t / 3));
                                    } else if (stride >= 35 && stride <= 50) {
                                        stride = stride * 2;
                                    } else if (stride >= 125) {
                                        if (rightprevstrie >= stride) {
                                            double t = rightprevstrie - stride;
                                            stride = Math.abs(stride - (Math.round(t / 2)));
                                        } else {
                                            double t = stride - rightprevstrie;
                                            stride = Math.abs(stride - (Math.round(t / 2)));
                                        }
                                    }
                                } else {
                                    if (stride <= 50) {
                                        double t = 70 - stride;
                                        stride = stride + t;
                                    } else if (stride > 50 && stride <= 75) {
                                        double t = 80 - stride;
                                        stride = stride + (t / 2) + (t / 3);
                                    } else if (stride >= 125) {
                                        double t = stride - 125;
                                        stride = stride - t;
                                    }
                                }
                                rightprevstrie = stride;

                                righttimestamp = new JSONArray();
                                rightacc.clear();
                                rightstride.add(stride);
//                                rightstride.add((3.1419 / 180) * maxangle * DetailFrag_5.rightleglength);
                                rightstrideper.add((((3.1419 / 180) * maxangle * DetailFrag_5.rightleglength) * MainActivity.patientheight) / 100);
                                long stepEndTime1 = DetailFrag_5.timestamps.get(i); // End of swing phase
                                long swingTime = stepEndTime1 - DetailFrag_5.stepStartTime; // Calculate swing time
                                DetailFrag_5.walkgaitswingtime.add((int) swingTime);
                                rightswingtime.add(swingTime);
                                Log.e("Walk and Gait Time Index right end", String.valueOf(i));
                                Log.e("Right Leg Step", "Swing Time: " + swingTime + "ms");
                                Log.e("Inbasekar Step Count", i + " / " + String.valueOf(DetailFrag_5.stepCountwalk));
                            }

                            Log.e("Cycles Walk and Gait Max angles", String.valueOf(maxangle));
                            Log.e("Cycles Walk and Gait Max angles", String.valueOf(DetailFrag_5.rightlegcyclewalkgait));
                            Log.e("Cycles Walk and Gait Max angles", String.valueOf(DetailFrag_5.rightleglength));
                            Log.e("Cycles Walk and Gait Max angles", String.valueOf((3.1419 / 180) * maxangle * DetailFrag_5.rightleglength));

                            DetailFrag_5.rightlegcyclewalkgait = new ArrayList<>();
                        }

                        // --- Step Detection for Left Leg ---
                        if (DetailFrag_5.change >= 0) {
                            DetailFrag_5.leftlegcyclewalkgait.add(data.get(i));
//                            try {
//                                leftacc.add((double)DetailFrag_5.leftacclfilter.get(i));
//                            } catch (JSONException e) {
//                                throw new RuntimeException(e);
//                            }
                            lefttimestamp.put(DetailFrag_5.timestamps.get(i));
                            leftacc.add(DetailFrag_5.leftaccl.get(i));
                            if (leftstart == 0) {
                                leftstart = i;
                            }
                            DetailFrag_5.isIncreasing1 = true;

                            DetailFrag_5.stepStartTime1 = DetailFrag_5.timestamps.get(i); // Start of swing phase
                            Log.e("Walk and Gait Time Index left start", String.valueOf(i));
//                            if (DetailFrag_5.isInBreak) {
//                                // End of a break
//                                DetailFrag_5.isInBreak = false;
//                                long breakEndTime = DetailFrag_5.walkgaittime.get(i);
//                                DetailFrag_5.totalBreakTime += (breakEndTime - DetailFrag_5.breakStartTime);
//                                Log.e("Break Ended", "Break ended at index: " + i);
//                            }
                        } else if (DetailFrag_5.change < 0 && DetailFrag_5.isIncreasing1) {
                            Log.e("Walk and Gait analysis Left", DetailFrag_5.leftlegcyclewalkgait.toString());
                            DetailFrag_5.leftlegcyclewalkgait.add(data.get(i));
//                            try {
//                                leftacc.add((double)DetailFrag_5.leftacclfilter.get(i));
//                            } catch (JSONException e) {
//                                throw new RuntimeException(e);
//                            }
                            lefttimestamp.put(DetailFrag_5.timestamps.get(i));
                            leftacc.add(DetailFrag_5.leftaccl.get(i));
                            leftend = i;
                            DetailFrag_5.isDecreasing1 = true;
                            DetailFrag_5.isIncreasing1 = false;
                            float maxangle = Collections.max(DetailFrag_5.leftlegcyclewalkgait);
                            if (maxangle >= 15) {
                                if (rightstepflag == 0 && leftstepflag == 0) {
                                    leftstepflag = 1;
                                }
                                DetailFrag_5.walkgaitleftlegangles.add(maxangle);
                                DetailFrag_5.stepCountwalk++;
                                DetailFrag_5.leftcyclewalkgati++;

                                Log.e("Log for Walk gait Left", leftacc + " / " + lefttimestamp);
                                double stride = stridecalc(leftacc, lefttimestamp);
                                if (leftprevstride != 0) {
                                    if (stride < 35) {
                                        double t = leftprevstride - stride;
                                        stride = stride + (Math.round(t / 2) + Math.round(t / 3) + Math.round(t / 3));
                                    } else if (stride >= 35 && stride <= 50) {
                                        stride = stride * 2;
                                    } else if (stride >= 125) {
                                        if (leftprevstride >= stride) {
                                            double t = leftprevstride - stride;
                                            stride = Math.abs(stride - (Math.round(t / 2)));
                                        } else {
                                            double t = stride - leftprevstride;
                                            stride = Math.abs(stride - (Math.round(t / 2)));
                                        }
                                    }
                                } else {
                                    if (stride <= 50) {
                                        double t = 70 - stride;
                                        stride = stride + t;
                                    } else if (stride > 50 && stride <= 75) {
                                        double t = 80 - stride;
                                        stride = stride + (t / 2) + (t / 3);
                                    } else if (stride >= 125) {
                                        double t = stride - 125;
                                        stride = stride - t;
                                    }
                                }
                                leftprevstride = stride;
                                leftacc.clear();
                                lefttimestamp = new JSONArray();
                                leftstride.add(stride);
//                                leftstride.add((3.1419 / 180) * maxangle * DetailFrag_5.leftleglength);
                                leftstrideper.add((((3.1419 / 180) * maxangle * DetailFrag_5.leftleglength) * MainActivity.patientheight) / 100);
                                long stepEndTime1 = DetailFrag_5.timestamps.get(i); // End of swing phase
                                long swingTime1 = stepEndTime1 - DetailFrag_5.stepStartTime1; // Calculate swing time
                                DetailFrag_5.walkgaitswingtime.add((int) swingTime1);
                                leftswingtime.add(swingTime1);
                                Log.e("Walk and Gait Time Index left end", String.valueOf(i));
                                Log.e("Left Leg Step", "Swing Time: " + swingTime1 + "ms");
                                Log.e("Inbasekar Step Count", i + " / " + String.valueOf(DetailFrag_5.stepCountwalk));
                            }
                            DetailFrag_5.leftlegcyclewalkgait = new ArrayList<>();
                        }
                    }
                }
            }
        } else {
            for (int i = 0; i < data.size() - 1; i++) {
                //float difference = angles[i] - angles[i - 1];
                DetailFrag_5.change = data.get(i + 1) - data.get(i);
                DetailFrag_5.change1 = data1.get(i + 1) - data1.get(i);

                Log.e("Change1", DetailFrag_5.change + " / " + DetailFrag_5.change1);

                if (Math.abs(DetailFrag_5.change) <= 2 && Math.abs(DetailFrag_5.change1) <= 2 && Math.abs(DetailFrag_5.change) >= 0 && Math.abs(DetailFrag_5.change1) >= 0) {
                    // No significant change detected
                    DetailFrag_5.consecutiveNoChangeCount++;
                    if (DetailFrag_5.breakStartTime == 0) {
                        DetailFrag_5.breakStartTime = DetailFrag_5.timestamps.get(i);// Record break start time
                    }

                    if (!DetailFrag_5.isInBreak && DetailFrag_5.consecutiveNoChangeCount >= 60) {
                        // Start of a break
                        DetailFrag_5.isInBreak = true;
                        //DetailFrag_5.breakStartTime =  DetailFrag_5.walkgaittime.get(i);// Record break start time

                        Log.e("Break Detected Started", "Break started at index: " + i + " / " + DetailFrag_5.breakStartTime / 1000);
                    }
                } else {
                    DetailFrag_5.consecutiveNoChangeCount = 0;
                    if (DetailFrag_5.isInBreak) {
                        DetailFrag_5.isInBreak = false;
                        DetailFrag_5.breakCount++;
                        long breakEndTime = DetailFrag_5.timestamps.get(i);
                        DetailFrag_5.totalBreakTime += (breakEndTime - DetailFrag_5.breakStartTime);
                        //stance.add(DetailFrag_5.stepCountwalk + " " + String.valueOf((int) (breakEndTime - DetailFrag_5.breakStartTime) / 1000));
                        Log.e("Break Detected Ended", "Break started at index: " + i + " / " + breakEndTime / 1000);
                        DetailFrag_5.breakStartTime = 0;
                    } else if (DetailFrag_5.breakStartTime != 0 && data1.get(i) < 10 && data.get(i) < 10) {
                        long breakEndTime = DetailFrag_5.timestamps.get(i);
                        DetailFrag_5.totalstancepahse += (breakEndTime - DetailFrag_5.breakStartTime);
                        stance.add((long) ((breakEndTime - DetailFrag_5.breakStartTime) / 1000.0));
                        Log.e("Power Rangers Total Stance Phase", i + " / " + String.valueOf(DetailFrag_5.totalstancepahse));
                        DetailFrag_5.breakStartTime = 0;
                    } else {
                        // --- Step Detection for Right Leg ---
                        if (DetailFrag_5.change1 >= 0) {
                            DetailFrag_5.rightlegcyclewalkgait.add(data1.get(i));
//                            try {
//                                rightacc.add((double) DetailFrag_5.righttacclfilter.get(i));
//                            } catch (JSONException e) {
//                                throw new RuntimeException(e);
//                            }
                            righttimestamp.put(DetailFrag_5.timestamps.get(i));
                            rightacc.add(DetailFrag_5.righttaccl.get(i));
                            if (rightstart == 0) {
                                rightstart = i;
                            }
                            DetailFrag_5.isIncreasing = true;

                            DetailFrag_5.stepStartTime = DetailFrag_5.timestamps.get(i); // Start of swing phase
                            Log.e("Walk and Gait Time Index right start", String.valueOf(i));
//                            if (DetailFrag_5.isInBreak) {
//                                // End of a break
//                                DetailFrag_5.isInBreak = false;
//                                long breakEndTime = DetailFrag_5.walkgaittime.get(i);
//                                DetailFrag_5.totalBreakTime += (breakEndTime - DetailFrag_5.breakStartTime);
//                                Log.e("Break Ended", "Break ended at index: " + i);
//                            }
                        } else if (DetailFrag_5.change1 < 0 && DetailFrag_5.isIncreasing) {
                            Log.e("Walk and Gait analysis Right", DetailFrag_5.rightlegcyclewalkgait.toString());
                            DetailFrag_5.rightlegcyclewalkgait.add(data1.get(i));
//                            try {
//                                rightacc.add((double) DetailFrag_5.righttacclfilter.get(i));
//                            } catch (JSONException e) {
//                                throw new RuntimeException(e);
//                            }
                            righttimestamp.put(DetailFrag_5.timestamps.get(i));
                            rightacc.add(DetailFrag_5.righttaccl.get(i));
                            rightend = i;
                            DetailFrag_5.isDecreasing = true;
                            DetailFrag_5.isIncreasing = false;
                            float maxangle = Collections.max(DetailFrag_5.rightlegcyclewalkgait);
                            if (maxangle >= 15) {
                                if (rightstepflag == 0 && leftstepflag == 0) {
                                    rightstepflag = 1;
                                }
                                DetailFrag_5.walkgaitrightlegangles.add(maxangle);
                                DetailFrag_5.stepCountwalk++;
                                DetailFrag_5.rightcyclewalkgati++;

                                Log.e("Log for Walk gait Right", rightacc + " / " + righttimestamp);
                                double stride = stridecalc(rightacc, righttimestamp);
                                if (rightprevstrie != 0) {
                                    if (stride < 35) {
                                        double t = rightprevstrie - stride;
                                        stride = stride + (Math.round(t / 2) + Math.round(t / 3) + Math.round(t / 3));
                                    } else if (stride >= 35 && stride <= 50) {
                                        stride = stride * 2;
                                    } else if (stride >= 125) {
                                        if (rightprevstrie >= stride) {
                                            double t = rightprevstrie - stride;
                                            stride = Math.abs(stride - (Math.round(t / 2)));
                                        } else {
                                            double t = stride - rightprevstrie;
                                            stride = Math.abs(stride - (Math.round(t / 2)));
                                        }
                                    }
                                } else {
                                    if (stride <= 50) {
                                        double t = 80 - stride;
                                        stride = stride + t;
                                    } else if (stride > 50 && stride <= 75) {
                                        double t = 80 - stride;
                                        stride = stride + (t / 2) + (t / 3);
                                    } else if (stride >= 125) {
                                        double t = stride - 125;
                                        stride = stride - t;
                                    }
                                }
                                rightprevstrie = stride;
                                righttimestamp = new JSONArray();
                                rightacc.clear();
                                rightstride.add(stride);
//                                rightstride.add((3.1419 / 180) * maxangle * DetailFrag_5.rightleglength);
                                rightstrideper.add((((3.1419 / 180) * maxangle * DetailFrag_5.rightleglength) * MainActivity.patientheight) / 100);
                                long stepEndTime1 = DetailFrag_5.timestamps.get(i); // End of swing phase
                                long swingTime = stepEndTime1 - DetailFrag_5.stepStartTime; // Calculate swing time
                                DetailFrag_5.walkgaitswingtime.add((int) swingTime);
                                Log.e("Walk and Gait Time Index right end", String.valueOf(i));
                                Log.e("Right Leg Step", "Swing Time: " + swingTime + "ms");
                                Log.e("Inbasekar Step Count", i + " / " + String.valueOf(DetailFrag_5.stepCountwalk));
                            }

                            Log.e("Cycles Walk and Gait Max angles", String.valueOf(maxangle));
                            Log.e("Cycles Walk and Gait Max angles", String.valueOf(DetailFrag_5.rightlegcyclewalkgait));
                            Log.e("Cycles Walk and Gait Max angles", String.valueOf(DetailFrag_5.rightleglength));
                            Log.e("Cycles Walk and Gait Max angles", String.valueOf((3.1419 / 180) * maxangle * DetailFrag_5.rightleglength));

                            DetailFrag_5.rightlegcyclewalkgait = new ArrayList<>();
                        }

                        // --- Step Detection for Left Leg ---
                        if (DetailFrag_5.change >= 0) {
                            DetailFrag_5.leftlegcyclewalkgait.add(data.get(i));
//                            try {
//                                leftacc.add((double)DetailFrag_5.leftacclfilter.get(i));
//                            } catch (JSONException e) {
//                                throw new RuntimeException(e);
//                            }
                            lefttimestamp.put(DetailFrag_5.timestamps.get(i));
                            leftacc.add((double) DetailFrag_5.leftaccl.get(i));
                            if (leftstart == 0) {
                                leftstart = i;
                            }

                            DetailFrag_5.isIncreasing1 = true;

                            DetailFrag_5.stepStartTime1 = DetailFrag_5.timestamps.get(i); // Start of swing phase
                            Log.e("Walk and Gait Time Index left start", String.valueOf(i));
//                            if (DetailFrag_5.isInBreak) {
//                                // End of a break
//                                DetailFrag_5.isInBreak = false;
//                                long breakEndTime = DetailFrag_5.walkgaittime.get(i);
//                                DetailFrag_5.totalBreakTime += (breakEndTime - DetailFrag_5.breakStartTime);
//                                Log.e("Break Ended", "Break ended at index: " + i);
//                            }
                        } else if (DetailFrag_5.change < 0 && DetailFrag_5.isIncreasing1) {
                            Log.e("Walk and Gait analysis Left", DetailFrag_5.leftlegcyclewalkgait.toString());
                            DetailFrag_5.leftlegcyclewalkgait.add(data.get(i));
//                            try {
//                                leftacc.add((double)DetailFrag_5.leftacclfilter.get(i));
//                            } catch (JSONException e) {
//                                throw new RuntimeException(e);
//                            }
                            lefttimestamp.put(DetailFrag_5.timestamps.get(i));
                            leftacc.add((double) DetailFrag_5.leftaccl.get(i));
                            leftend = i;
                            DetailFrag_5.isDecreasing1 = true;
                            DetailFrag_5.isIncreasing1 = false;
                            float maxangle = Collections.max(DetailFrag_5.leftlegcyclewalkgait);
                            if (maxangle >= 15) {
                                if (rightstepflag == 0 && leftstepflag == 0) {
                                    leftstepflag = 1;
                                }
                                DetailFrag_5.walkgaitleftlegangles.add(maxangle);
                                DetailFrag_5.stepCountwalk++;
                                DetailFrag_5.leftcyclewalkgati++;

                                Log.e("Log for Walk gait Left", leftacc + " / " + lefttimestamp);

                                double stride = stridecalc(leftacc, lefttimestamp);
                                if (leftprevstride != 0) {
                                    if (stride < 35) {
                                        double t = rightprevstrie - stride;
                                        stride = stride + (Math.round(t / 2) + Math.round(t / 3) + Math.round(t / 3));
                                    } else if (stride >= 35 && stride <= 50) {
                                        stride = stride * 2;
                                    } else if (stride >= 125) {
                                        if (leftprevstride >= stride) {
                                            double t = leftprevstride - stride;
                                            stride = Math.abs(stride - (Math.round(t / 2)));
                                        } else {
                                            double t = stride - leftprevstride;
                                            stride = Math.abs(stride - (Math.round(t / 2)));
                                        }
                                    }
                                } else {
                                    if (stride <= 50) {
                                        double t = 80 - stride;
                                        stride = stride + t;
                                    } else if (stride > 50 && stride <= 75) {
                                        double t = 80 - stride;
                                        stride = stride + (t / 2) + (t / 3);
                                    } else if (stride >= 125) {
                                        double t = stride - 125;
                                        stride = stride - t;
                                    }
                                }
                                leftprevstride = stride;
                                lefttimestamp = new JSONArray();
                                leftacc.clear();
                                leftstride.add(stride);
                                //leftstride.add((3.1419 / 180) * maxangle * DetailFrag_5.leftleglength);
                                leftstrideper.add((((3.1419 / 180) * maxangle * DetailFrag_5.leftleglength) * MainActivity.patientheight) / 100);
                                long stepEndTime1 = DetailFrag_5.timestamps.get(i); // End of swing phase
                                long swingTime1 = stepEndTime1 - DetailFrag_5.stepStartTime1; // Calculate swing time
                                DetailFrag_5.walkgaitswingtime.add((int) swingTime1);
                                Log.e("Walk and Gait Time Index left start", String.valueOf(i));
                                Log.e("Left Leg Step", "Swing Time: " + swingTime1 + "ms");
                                Log.e("Inbasekar Step Count", i + " / " + String.valueOf(DetailFrag_5.stepCountwalk));
                            }
                            DetailFrag_5.leftlegcyclewalkgait = new ArrayList<>();
                        }
                    }
                }
            }
        }
//        if (i + 1 == data1.size() - 1) {
//            DetailFrag_5.tempRow.add(new Entry(i, data1.get(i)));
//        }
//        else if (DetailFrag_5.change > 0) {
//            // Angle is increasing
//            DetailFrag_5.isIncreasing = true;
//            Log.e("SAGA GTA increase", String.valueOf(data1.get(i)));
//            // Reset decreasing and cycle-ready flags
//            DetailFrag_5.isDecreasing = false;
//            DetailFrag_5.cycleReady = false;
//            DetailFrag_5.tempRow.add(new Entry(i, data1.get(i)));
//            if (DetailFrag_5.isInBreak) {
//                // End of a break
//                DetailFrag_5.isInBreak = false;
//                long breakEndTime = System.currentTimeMillis();
//                DetailFrag_5.totalBreakTime += (breakEndTime - DetailFrag_5.breakStartTime);
//                Log.e("Break Ended", "Break ended at index: " + i);
//            }
//
//        }
//        else if (DetailFrag_5.change < 0 && DetailFrag_5.isIncreasing) {
//            // Transition to decreasing after an increase
//            Log.e("SAGA GTA decrease", String.valueOf(data1.get(i)));
//            DetailFrag_5.isDecreasing = true;
//            DetailFrag_5.tempRow.add(new Entry(i, data1.get(i)));
//            Log.e("Cycle Temprow inba", String.valueOf(DetailFrag_5.substaircase));
//        }
//        else if (DetailFrag_5.change == 0) {
//            DetailFrag_5.tempRow.add(new Entry(i, data1.get(i)));
//        }
//        if (DetailFrag_5.isIncreasing && DetailFrag_5.isDecreasing && data1.get(i + 1) >= data1.get(i)) {
//            DetailFrag_5.cycleReady = true; // Mark the cycle as ready
//        }
//        if (DetailFrag_5.cycleReady) {
//            DetailFrag_5.ct++;
//            DetailFrag_5.isIncreasing = false;
//            DetailFrag_5.isDecreasing = false;
//            DetailFrag_5.cycleReady = false; // Reset flags
//            DetailFrag_5.localMinimum = Float.MAX_VALUE; // Reset local minimum
//
//            //DetailFrag_5.highlightArrayact.add(DetailFrag_5.substaircase);
//            //Log.e("SAGA GTA 2", i + String.valueOf(DetailFrag_5.highlightArrayact));
//            DetailFrag_5.endind = i;
//
//            DetailFrag_5.walkgaitswingtime.add((DetailFrag_5.walkgaittime.get(DetailFrag_5.endind)-DetailFrag_5.walkgaittime.get(DetailFrag_5.startind))/1000);
//
//            generateParagraph(DetailFrag_5.tempRow);
//            DetailFrag_5.startind = i + 1;
//            DetailFrag_5.tempRow = new ArrayList<>();
//        }


//        if (angle > 40 && angle1 > 40) {
//            DetailFrag_5.stepCountwalk++;
//            DetailFrag_5.totalDistance += DetailFrag_5.STRIDE_LENGTH;
//            DetailFrag_5.isWalking = true;
//            DetailFrag_5.lastBreakStart = 0; // Reset break time
//        } else if (DetailFrag_5.isWalking) {
//            if (DetailFrag_5.lastBreakStart == 0) {
//                DetailFrag_5.lastBreakStart = currentTime;
//            } else if (currentTime - DetailFrag_5.lastBreakStart > DetailFrag_5.BREAK_THRESHOLD) {
//                DetailFrag_5.isWalking = false;
//                DetailFrag_5.breakTime += (currentTime - DetailFrag_5.lastBreakStart);
//            }
//        }
//        if (DetailFrag_5.isWalking) {
//            DetailFrag_5.activeTime = currentTime - DetailFrag_5.startTime - DetailFrag_5.breakTime;
//        }
//
//        processKneeAngle(angle, angle1);
    }

    public void processKneeAngle(double kneeAngle, double kneeAngle1) {
        Log.e("Walk and Gait", kneeAngle + " " + kneeAngle1);
        long currentTime = DetailFrag_5.sec;

        //foot contact
        if ((kneeAngle <= DetailFrag_5.MIN_ANGLE_THRESHOLD && currentTime - DetailFrag_5.lastHeelStrikeTime > 500) || (kneeAngle1 <= DetailFrag_5.MIN_ANGLE_THRESHOLD && currentTime - DetailFrag_5.lastHeelStrikeTime > 500)) {
            if (DetailFrag_5.cycleStartTime != 0) {
                analyzeCycle(currentTime);
            }
            DetailFrag_5.lastHeelStrikeTime = currentTime;
            DetailFrag_5.cycleStartTime = currentTime;
            DetailFrag_5.stepCount++; // Increment step count with each heel strike
            System.out.println("Heel Strike detected at time: " + currentTime);
        }

        // foot lift-off
        if ((kneeAngle >= DetailFrag_5.MAX_ANGLE_THRESHOLD && currentTime - DetailFrag_5.lastToeOffTime > 500) || (kneeAngle1 >= DetailFrag_5.MAX_ANGLE_THRESHOLD && currentTime - DetailFrag_5.lastToeOffTime > 500)) {
            DetailFrag_5.lastToeOffTime = currentTime;
            DetailFrag_5.avgStancetime = (DetailFrag_5.lastToeOffTime - DetailFrag_5.lastHeelStrikeTime) / 1000.0; // in seconds
            DetailFrag_5.standTimes.add(DetailFrag_5.avgStancetime); // Add to stand times list for averaging
            System.out.println("Toe-Off detected at time: " + currentTime);
            System.out.println("Stance Time: " + DetailFrag_5.avgStancetime + " seconds");
        }
    }

    private void analyzeCycle(long cycleEndTime) {
        double totalCycleTime = (cycleEndTime - DetailFrag_5.cycleStartTime) / 1000.0; // in seconds
        DetailFrag_5.meanVelocity = DetailFrag_5.strideLength / totalCycleTime;

        // swing time
        if (!DetailFrag_5.standTimes.isEmpty()) {
            double lastStanceTime = DetailFrag_5.standTimes.get(DetailFrag_5.standTimes.size() - 1);
            double swingTime = totalCycleTime - lastStanceTime;
            DetailFrag_5.swingTimes.add(swingTime); // Add to swing times list for averaging
            System.out.println("Swing Time: " + swingTime + " seconds");
        }

        stepCountgait = DetailFrag_5.stepCount;
        DetailFrag_5.cadence = (DetailFrag_5.stepCount / (totalCycleTime / 60.0)); // steps per minute


        DetailFrag_5.avgStandtime = DetailFrag_5.standTimes.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
        DetailFrag_5.avgSwingtime = DetailFrag_5.swingTimes.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
        Log.e("Walk and Gait Analysis Distance", String.valueOf(DetailFrag_5.totalDistance));
        DetailFrag_5.stepCount = 0;

    }

    private void postNumbersWithDelay() {
        List<Float> slice = new ArrayList<>();
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (index < numbers.length) {
                    slice.add(numbers[index]); // Display the current number
                    if (DetailFrag_5.playflag == 1) {
                        chartupdatedata(slice);
                    }
                    index++; // Move to the next number
                    handler.postDelayed(this, 1000); // Delay for 1 second (1000ms)
                }
            }
        };
        handler.post(runnable); // Start the runnable immediately
    }

//    private void setupArcGauge1(float aFloat) {
//        ValueAnimator animator1 = new ValueAnimator();
//
//        if (animator1 != null && animator1.isRunning()) {
//            animator1.cancel();
//        }
//        animator1 = ValueAnimator.ofInt(0, (int) aFloat);
//
//        animator1.setDuration(10);
//        animator1.addUpdateListener(animation -> {
//            int progress = (int) animation.getAnimatedValue();
//            Log.e("Gauge View", DetailFrag_5.selectedExercise + " / " + progress);
//            gaugeView1.setProgress(progress);
//            gaugeView1.invalidate();
//        });
//        animator1.start();
//    }
//
//    private void setupArcGauge2(float aFloat) {
//        ValueAnimator animator2 = new ValueAnimator();
//        if (animator2 != null && animator2.isRunning()) {
//            animator2.cancel();
//        }
//        animator2 = ValueAnimator.ofInt(0, (int) aFloat);
//
//        animator2.setDuration(10);
//        animator2.addUpdateListener(animation -> {
//            int progress = (int) animation.getAnimatedValue();
//            Log.e("Gauge View", DetailFrag_5.selectedExercise + " / " + progress);
//            gaugeView2.setProgress(progress);
//            gaugeView2.invalidate();
//        });
//        animator2.start();
//    }

    private void hideSystemUI() {
        View decorView = getWindow().getDecorView();
        int flags = View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(flags);
    }


    private double stridecalc(List<Double> accl, JSONArray timestamp1) {
        List<Double> time = new ArrayList<>();
        List<Double> time1 = new ArrayList<>();
        for (int i = 0; i < timestamp1.length(); i++) {
            try {
                time.add(timestamp1.getLong(i) / 1000.0);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }

//        for(int i=0; i<accl.size(); i++){
//            time1.add(time.get(i));
//        }

        Log.e("Acceleration/Time Log for Walk gait", accl + " / " + time);
        // Calculate distance
        double totalDistance = calculateTotalDisplacement(time, accl);
        return totalDistance;
    }

    public static double calculateTotalDisplacement(List<Double> time, List<Double> acceleration) {
        // Check if input lists are valid
        if (time == null || acceleration == null) {
            throw new IllegalArgumentException("Invalid input data: time and acceleration lists must be valid.");
        }

        double totalDisplacement = 0;

        Log.e("Log for Walk Gait time and acceleration", String.valueOf(time));
        Log.e("Log for Walk Gait time and acceleration", String.valueOf(acceleration));

        // Iterate over the acceleration and timestamps to compute displacements
        for (int i = 0; i < acceleration.size() - 1; i++) {
            // Time interval between consecutive timestamps
            double timeInterval = time.get(i + 1) - time.get(i);

            // Displacement for this time interval (s = 0.5 * a * t^2)
            double displacement = 0.5 * acceleration.get(i) * Math.pow(timeInterval, 2);

            // Add the absolute value of displacement to total displacement
            totalDisplacement += Math.abs(displacement);
        }

        Log.e("Log for Walk Gait Total displacement", String.valueOf(totalDisplacement));

        return totalDisplacement * 100;
    }



}

