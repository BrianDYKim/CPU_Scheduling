package com.company.dataStructure;

public class ProcessHistory {
    private String processId;
    private int startTime;
    private int finishTime;

    public ProcessHistory(String processId, int startTime, int finishTime) {
        this.processId = processId;
        this.startTime = startTime;
        this.finishTime = finishTime;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(int finishTime) {
        this.finishTime = finishTime;
    }
}
