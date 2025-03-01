package com.example.therapistbluelock;

import java.util.ArrayList;
import java.util.List;

public class Walkgaittestdata {

    String totalDistance,avgStandtime,meanVelocity;
    String stepCountwalk,breakcount;

    String swingtime;
    String stance;
    String stride;
    String strideper;
    String step;
    String cade;

    String activeTime;

    public String getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(String totalDistance) {
        this.totalDistance = totalDistance;
    }

    public String getAvgStandtime() {
        return avgStandtime;
    }

    public void setAvgStandtime(String avgStandtime) {
        this.avgStandtime = avgStandtime;
    }

    public String getMeanVelocity() {
        return meanVelocity;
    }

    public void setMeanVelocity(String meanVelocity) {
        this.meanVelocity = meanVelocity;
    }

    public String getStepCountwalk() {
        return stepCountwalk;
    }

    public void setStepCountwalk(String stepCountwalk) {
        this.stepCountwalk = stepCountwalk;
    }

    public String getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(String activeTime) {
        this.activeTime = activeTime;
    }

    public String getBreakcount() {
        return breakcount;
    }

    public void setBreakcount(String breakcount) {
        this.breakcount = breakcount;
    }

    public Walkgaittestdata(String totalDistance, String avgStandtime, String swingtime, String stance, String stride, String meanVelocity, String cade, String step, String strideper ,String stepCountwalk, String activeTime, String breakcount) {
        this.totalDistance = totalDistance;
        this.avgStandtime = avgStandtime;
        this.swingtime = swingtime;
        this.stance = stance;
        this.stride = stride;
        this.meanVelocity = meanVelocity;
        this.cade = cade;
        this.step = step;
        this.strideper = strideper;
        this.stepCountwalk = stepCountwalk;
        this.activeTime = activeTime;
        this.breakcount = breakcount;
    }



    public String getStance() {
        return stance;
    }

    public void setStance(String stance) {
        this.stance = stance;
    }


    public String getSwingtime() {
        return swingtime;
    }

    public void setSwingtime(String swingtime) {
        this.swingtime = swingtime;
    }

    public String getStride() {
        return stride;
    }

    public void setStride(String stride) {
        this.stride = stride;
    }

    public String getStrideper() {
        return strideper;
    }

    public void setStrideper(String strideper) {
        this.strideper = strideper;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public String getCade() {
        return cade;
    }

    public void setCade(String cade) {
        this.cade = cade;
    }



}
