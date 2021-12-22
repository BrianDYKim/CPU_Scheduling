package com.company.dataStructure;

public class MyProcess {
    private int burstTime; // CPU burst time
    private int arrivalTime; // 도착 시간
    private int priority; // 우선순위
    private int turnaroundTime = 0; // turnaround time : 프로세스가 처리되는데 걸리는 시간
    private int waitingTime = 0; // waiting time : 프로세스가 ready Queue에 대기하는 시간의 총합
    private int id;

    private int finishTime = 0; // 프로세스가 마지막으로 종료된 시점을 저장하는 변수

    public MyProcess(int id, int burstTime, int arrivalTime, int priority) {
        this.id = id;
        this.burstTime = burstTime;
        this.arrivalTime = arrivalTime;
        this.priority = priority;
    }

    public MyProcess(int id, int burstTime, int arrivalTime, int priority, int turnaroundTime, int waitingTime) {
        this.id = id;
        this.burstTime = burstTime;
        this.arrivalTime = arrivalTime;
        this.priority = priority;
        this.turnaroundTime = turnaroundTime;
        this.waitingTime = waitingTime;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getTurnaroundTime() {
        return turnaroundTime;
    }

    public void setTurnaroundTime(int turnaroundTime) {
        this.turnaroundTime = turnaroundTime;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(int finishTime) {
        this.finishTime = finishTime;
    }
}
