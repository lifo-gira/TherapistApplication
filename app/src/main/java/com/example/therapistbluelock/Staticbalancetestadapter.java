package com.example.therapistbluelock;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Staticbalancetestadapter extends RecyclerView.Adapter<Staticbalancetestadapter.ExerciseViewHolder>{

    private final List<Staticbalancetestdata> exerciseList; // Use ExerciseCycleAssessment
    private final Context context;

    public Staticbalancetestadapter(Context context, List<Staticbalancetestdata> exerciseList) {
        this.context = context;
        this.exerciseList = exerciseList;
    }

    @NonNull
    @Override
    public ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.assess_cycle, parent, false);
        return new ExerciseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseViewHolder holder, int position) {
        Staticbalancetestdata exercise = exerciseList.get(position); // Use ExerciseCycleAssessment
        // Set the Cycle Count based on position (starting from 1)
        holder.cycleCount.setText("Cycle " + (position + 1)); // Display Cycle Count starting from 1
        holder.rangeOfMotion.setText(String.valueOf(exercise.getTime()));
        Log.e("Statice Balance Test data","Inside static balance test adapter");
        if("active".equalsIgnoreCase(exercise.getEyesstatus())){
            holder.supportvalue.setText("Eyes Open");
        }else{
            holder.supportvalue.setText("Eyes Closed");
        }
    }

    @Override
    public int getItemCount() {
        return exerciseList.size();
    }

    public class ExerciseViewHolder extends RecyclerView.ViewHolder {
        TextView cycleCount; // Reference for Cycle Count
        TextView rangeOfMotion; // Reference for Range of Motion
        CardView indivicard;

        LinearLayout first_linear,second_linear,sit_to_stand,stand_to_shift,walk_time,steps_covered,ascent_time,decent_time,turn_time,support_unsupport;
        TextView range_of_motion,active_ed,time,maximum_rom,sittostandtext,sittostandvalue,standtoshifttext,standtoshiftvalue,walktext,walkvalue,supportvalue;
        public ExerciseViewHolder(@NonNull View itemView) {
            super(itemView);
            cycleCount = itemView.findViewById(R.id.cycle_count); // Update to the new IDs
            rangeOfMotion = itemView.findViewById(R.id.cyclerom); // Update to the new IDs
            indivicard = itemView.findViewById(R.id.indivicard);

            first_linear=itemView.findViewById(R.id.first_linear);
            second_linear=itemView.findViewById(R.id.second_linear);
            sit_to_stand=itemView.findViewById(R.id.sit_to_stand);
            stand_to_shift=itemView.findViewById(R.id.stand_to_shift);
            walk_time=itemView.findViewById(R.id.walk_time);
            steps_covered=itemView.findViewById(R.id.steps_covered);
            ascent_time=itemView.findViewById(R.id.ascent_time);
            decent_time=itemView.findViewById(R.id.decent_time);
            turn_time=itemView.findViewById(R.id.turn_time);
            support_unsupport=itemView.findViewById(R.id.support_unsupport);
            range_of_motion=itemView.findViewById(R.id.range_of_motion);
            active_ed=itemView.findViewById(R.id.active_ed);
            time=itemView.findViewById(R.id.time);
            maximum_rom=itemView.findViewById(R.id.maximum_rom);

            first_linear.setVisibility(View.VISIBLE);
            second_linear.setVisibility(View.VISIBLE);
            cycleCount.setVisibility(View.VISIBLE);
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
            support_unsupport.setVisibility(View.VISIBLE);
            supportvalue=itemView.findViewById(R.id.supportvalue);

        }
    }
}
