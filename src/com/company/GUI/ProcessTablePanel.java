package com.company.GUI;

import com.company.Main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ProcessTablePanel extends JPanel {

    // 테이블 관련 상수 정의입니다.
    private final String[] header = {"#" ,"burstTime", "arrivalTime", "Priority", "turnaroundTime", "waitingTime"};
    private String[][] contents;
    private DefaultTableModel model;

    // 테이블 변수 선언입니다.
    private JTable processTable;
    private JScrollPane processTableScrollPane;

    public ProcessTablePanel() {
        setSize(Main.MAIN_SCREEN_WIDTH, 400);
        setLayout(new BorderLayout());
        setBackground(Color.GRAY);

        model = new DefaultTableModel(contents, header);

        processTable = new JTable(model);
        processTableScrollPane = new JScrollPane(processTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        processTableScrollPane.setSize(Main.MAIN_SCREEN_WIDTH - 20, 380);
        add(processTableScrollPane);

    }

    // processTable의 getter method
    public JTable getProcessTable() {
        return this.processTable;
    }

    // model의 getter method
    public DefaultTableModel getModel() {
        return this.model;
    }
}
