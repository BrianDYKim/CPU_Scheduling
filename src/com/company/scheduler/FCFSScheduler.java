package com.company.scheduler;

import com.company.Main;
import com.company.dataStructure.MyProcess;
import com.company.dataStructure.ProcessHistory;
import com.company.dataStructure.ReadyQueue;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

/** FCFS 스케쥴링을 구현한 클래스입니다.*/
public class FCFSScheduler {
    private ReadyQueue readyQueue = Main.readyQueue; // Main에서 Ready Queue를 가져옴
    private DefaultTableModel tableModel = Main.mainScreen.getProcessTablePanel().getModel();

    private ArrayList<ProcessHistory> processHistoryArrayList = new ArrayList<>(); // 프로세스의 history를 저장하는 변수

    private int totalTime = 0; // 프로그램이 실행되고있는 경과 시간
    private int waitingTimeSum = 0; // waiting time의 총합
    private int turnaroundTimeSum = 0; // turnaround time의 총합

    private int count = 0; // 프로세스 카운터
    private int queueSize = readyQueue.getSize();

    /** Constructor */
    public FCFSScheduler() {
        MyProcess dispatchedProcess; // CPU 상에 dispatch 되어있는 Process
        processSort(); // arrival time을 기준으로 readyQueue를 정렬하고 시작함.

        // ready Queue의 모든 프로세스를 소모할 때 까지 진행되는 loop
        while(count < queueSize) {
            count++;
            dispatchedProcess = (MyProcess) readyQueue.deQueue();

            int startTime = totalTime;

            // 프로세스의 멤버 변수들을 임시 저장
            int id = dispatchedProcess.getId();
            int burstTime = dispatchedProcess.getBurstTime();
            int priority = dispatchedProcess.getPriority();
            int arrivalTime = dispatchedProcess.getArrivalTime();

            dispatchedProcess.setWaitingTime(totalTime - dispatchedProcess.getArrivalTime()); // waiting time을 setting

            while(dispatchedProcess.getBurstTime() != 0) {
                dispatchedProcess.setBurstTime(dispatchedProcess.getBurstTime() - 1); // burstTime을 1 감소시킴
                totalTime += 1; // 경과 시간을 1 증가시킴.
            }

            dispatchedProcess.setTurnaroundTime(totalTime - dispatchedProcess.getArrivalTime()); // turnaround time을 설정

            // ready queue에 갱신된 프로세스를 저장
            readyQueue.enQueue(new MyProcess(id, burstTime, arrivalTime, priority,
                    dispatchedProcess.getTurnaroundTime(), dispatchedProcess.getWaitingTime()));

            // Process History를 갱신
            processHistoryArrayList.add(new ProcessHistory("Procsss" + Integer.toString(id), startTime, totalTime));

            // 총합 계산
            waitingTimeSum += dispatchedProcess.getWaitingTime();
            turnaroundTimeSum += dispatchedProcess.getTurnaroundTime();

            // 테이블 갱신
            tableModel.insertRow(id, new Object[]{Integer.toString(id), Integer.toString(burstTime), Integer.toString(arrivalTime),
                    Integer.toString(priority), Integer.toString(dispatchedProcess.getTurnaroundTime()),
                    Integer.toString(dispatchedProcess.getWaitingTime())});
            // 밀려난 행 삭제
            tableModel.removeRow(id + 1);
        }

        // 테이블 갱신
        tableModel.insertRow(readyQueue.getSize(), new Object[]{"total(FCFS)", "", "", "",
                Double.toString((double)turnaroundTimeSum / readyQueue.getSize()),
                Double.toString((double)waitingTimeSum / readyQueue.getSize())});
    }

    public ArrayList<ProcessHistory> getProcessHistoryArrayList() {
        return processHistoryArrayList;
    }

    /** Waiting time 기준으로 프로세스를 정렬하는 메소드입니다.*/
    public void processSort() {
        int min;

        for(int i = 0; i < readyQueue.getSize() - 1; i++) {
            min = i;
            MyProcess comp1, comp2; // 비교 대상을 저장하는 객체

            for(int j = i + 1; j < readyQueue.getSize(); j++) {
                comp1 = (MyProcess) readyQueue.peek(min);
                comp2 = (MyProcess) readyQueue.peek(j);

                if(comp1.getArrivalTime() > comp2.getArrivalTime())
                    min = j;
            }
            readyQueue.swap(i, min);
        }
    }
}
