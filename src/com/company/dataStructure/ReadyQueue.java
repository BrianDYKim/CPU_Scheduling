package com.company.dataStructure;

import java.util.Vector;

public class ReadyQueue<MyProcess> {
    private int size;
    private int numOfData = 0;
    private Vector<MyProcess> processVector = new Vector<MyProcess>();

    public ReadyQueue(int size) {
        this.size = size;
    }

    public boolean isEmpty() {
        if(processVector.isEmpty())
            return true;
        else
            return false;
    }

    public boolean isFull() {
        if(processVector.size() == size)
            return true;
        else
            return false;
    }

    // Ready Queue에 프로세스를 추가하는 메소드입니다. 반환값은 null일수도 있습니다.
    public void enQueue(MyProcess process) {
        if(!isFull()) {
            processVector.add(process);
            this.numOfData += 1;
        }
        else {
            System.out.println("Ready Queue 포화 상태.");
            return;
        }
    }

    // Process를 Ready Queue에서 꺼내는 메소드입니다. 공백큐이면 null 반환, 아닐 경우 0번째 Process를 반환
    public MyProcess deQueue() {
        MyProcess tempProcess = null;

        try {
            tempProcess = processVector.remove(0);
            numOfData--;
            return tempProcess;
        } catch(Exception e) {
            System.out.println("Ready Queue 공백 상태.");
            return tempProcess;
        }
    }

    public MyProcess peek(int index) {
        return this.processVector.get(index);
    }

    public int getSize() {
        return this.numOfData;
    }

    public void swap(int index1, int index2) {
        MyProcess temp = processVector.get(index1);

        processVector.set(index1, processVector.get(index2));
        processVector.set(index2, temp);
    }

    public Vector<MyProcess> getProcessVector() {
        return this.processVector;
    }
}
