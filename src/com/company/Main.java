package com.company;

import com.company.GUI.MainScreen;
import com.company.dataStructure.MyProcess;
import com.company.dataStructure.ReadyQueue;

public class Main {

    // Ready Queue 변수입니다.
    public static ReadyQueue<MyProcess> readyQueue = new ReadyQueue<MyProcess>(30); // 프로세스의 최대 크기는 30으로 지정

    // 화면 크기를 나타내는 상수입니다.
    public static final int MAIN_SCREEN_WIDTH = 900;
    public static final int MAIN_SCREEN_HEIGHT = 720;

    // 메인 화면 변수입니다.
    public static MainScreen mainScreen;

    public static void main(String[] args) {
        mainScreen = new MainScreen();
    }
}
