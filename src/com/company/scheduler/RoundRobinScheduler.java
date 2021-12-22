package com.company.scheduler;

import com.company.Main;
import com.company.dataStructure.MyProcess;
import com.company.dataStructure.ProcessHistory;
import com.company.dataStructure.ReadyQueue;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class RoundRobinScheduler {
    private ReadyQueue readyQueue = Main.readyQueue;
    private ReadyQueue<MyProcess> tempReadyQueue = new ReadyQueue<MyProcess>(30);
    private DefaultTableModel tableModel = Main.mainScreen.getProcessTablePanel().getModel();

    private ArrayList<ProcessHistory> processHistoryArrayList = new ArrayList<>(); // 프로세스의 history를 저장하는 변수

    private int totalTime = 0; // 프로그램이 실행되고있는 경과 시간
    private String timeUnitString = (String) JOptionPane.showInputDialog(Main.mainScreen, "단위 시간을 입력하시오.",
            "Round Robin 단위 시간 입력", JOptionPane.PLAIN_MESSAGE, null, null, null); // Round Robin을 돌리는 단위 시간
    private int timeUnit = Integer.parseInt(timeUnitString);
    private int waitingTimeSum = 0; // waiting time의 총합
    private int turnaroundTimeSum = 0; // turnaround time의 총합

    private int count = 0; // 프로세스 카운터
    private int terminatedProcessCount = 0;
    private int queueSize = readyQueue.getSize();

    /** Constructor*/
    public RoundRobinScheduler() {
        MyProcess dispatchedProcess;
        processSort(); // arrival time 기준으로 process를 정렬
        copyQueue(); // 큐를 복사하고 시작함.

        // 프로세스가 모두 소진될 때까지
        while(!readyQueue.isEmpty()) {

            /**
             * ready Queue를 한 바퀴 도는 loop --> 한 바퀴가 종료될 떄 마다 count를 0으로 초기화
             * terminate된 process는 따로 빼서 저장. 나중에 queue에 삽입함.
             */
            while(count < queueSize) { // dequeue를 수시로 하지않고 terminate된 프로세스는 따로 뺴기 떄문임.
                count++;
                dispatchedProcess = (MyProcess) readyQueue.peek(count - 1 - terminatedProcessCount);

                // arrival time이 현재 클럭보다 늦게 존재하는 경우(즉, 아직 프로세스가 도착하지 않은 경우)
                if(dispatchedProcess.getArrivalTime() > totalTime) {
                    count = 0;
                    continue; // 다시 처음으로 돌아가면 됨.
                }

                int startTime = totalTime;
                int finishTime;

                // waiting time 설정
                ((MyProcess)readyQueue.peek(count - 1 - terminatedProcessCount)).setWaitingTime(dispatchedProcess.getWaitingTime() + totalTime - dispatchedProcess.getFinishTime());

                // 프로세스 진행
                if(dispatchedProcess.getBurstTime() >= timeUnit) {
                    ((MyProcess)readyQueue.peek(count - 1 - terminatedProcessCount)).setBurstTime(dispatchedProcess.getBurstTime() - timeUnit);
                    totalTime += timeUnit;
                    finishTime = totalTime;
                    ((MyProcess) readyQueue.peek(count - 1 - terminatedProcessCount)).setFinishTime(finishTime); // finish time 갱신

                    // process history 갱신
                    processHistoryArrayList.add(new ProcessHistory("Procsss" + dispatchedProcess.getId(), startTime, totalTime));
                    System.out.println("Process" + dispatchedProcess.getId() + "기록 : " + startTime + ", " + totalTime);
                }
                // 남은 burst time이 time unit보다 적은 경우에는 프로세스를 terminate시키고 turnaround time을 결정지음.
                else {
                    totalTime += dispatchedProcess.getBurstTime();
                    finishTime = totalTime;
                    dispatchedProcess.setTurnaroundTime(totalTime - dispatchedProcess.getArrivalTime());

                    // process history 갱신
                    processHistoryArrayList.add(new ProcessHistory("Procsss" + dispatchedProcess.getId(), startTime, totalTime));
                    System.out.println("Process" + dispatchedProcess.getId() + "기록 : " + startTime + ", " + totalTime);

                    // waiting time, turnaround time 총합 계산
                    waitingTimeSum += dispatchedProcess.getWaitingTime();
                    turnaroundTimeSum += dispatchedProcess.getTurnaroundTime();

                    // 테이블 갱신
                    tableModel.insertRow(dispatchedProcess.getId(), new Object[]{dispatchedProcess.getId(), Integer.toString(tempReadyQueue.peek(dispatchedProcess.getId()).getBurstTime()),
                    Integer.toString(tempReadyQueue.peek(dispatchedProcess.getId()).getArrivalTime()), Integer.toString(tempReadyQueue.peek(dispatchedProcess.getId()).getPriority()),
                    Integer.toString(dispatchedProcess.getTurnaroundTime()), Integer.toString(dispatchedProcess.getWaitingTime())});

                    // 밀려난 행 삭제
                    tableModel.removeRow(dispatchedProcess.getId() + 1);

                    // 해당 프로세스 제거
                    readyQueue.getProcessVector().remove(count - 1 - terminatedProcessCount);
                    System.out.println("Process" + Integer.toString(dispatchedProcess.getId()) + " terminated.");
                    terminatedProcessCount++;
                }

            }
            count = 0;
            queueSize -= terminatedProcessCount;
            terminatedProcessCount = 0;
        }

        // 테이블 갱신
        tableModel.insertRow(tempReadyQueue.getSize(), new Object[]{"total(Round Robin)", "", "", "",
                Double.toString((double)turnaroundTimeSum / readyQueue.getSize()),
                Double.toString((double)waitingTimeSum / readyQueue.getSize())});

        // ready queue 원상복구
        readyQueue = tempReadyQueue;
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

    /** ready Queue를 tempQueue에 복사하는 메소드입니다.*/
    public void copyQueue() {
        readyQueue.getProcessVector().stream().forEach(process -> {
            tempReadyQueue.enQueue((MyProcess) process);
        });
    }

}
