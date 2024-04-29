package com.quartz.listener;

import org.quartz.*;

public class MySchedulerListener implements SchedulerListener {
    public void jobScheduled(Trigger trigger) {

    }

    public void jobUnscheduled(TriggerKey triggerKey) {

    }

    public void triggerFinalized(Trigger trigger) {

    }

    public void triggerPaused(TriggerKey triggerKey) {

    }

    public void triggersPaused(String s) {

    }

    public void triggerResumed(TriggerKey triggerKey) {

    }

    public void triggersResumed(String s) {

    }

    public void jobAdded(JobDetail jobDetail) {

    }

    public void jobDeleted(JobKey jobKey) {

    }

    public void jobPaused(JobKey jobKey) {

    }

    public void jobsPaused(String s) {

    }

    public void jobResumed(JobKey jobKey) {

    }

    public void jobsResumed(String s) {

    }

    public void schedulerError(String s, SchedulerException e) {

    }

    public void schedulerInStandbyMode() {

    }

    public void schedulerStarted() {

    }

    public void schedulerStarting() {

    }

    public void schedulerShutdown() {

    }

    public void schedulerShuttingdown() {

    }

    public void schedulingDataCleared() {

    }
}
