package com.company.GUI;

import com.company.GUI.dialogs.GanttChartDialog;
import com.company.Main;
import com.company.dataStructure.ProcessHistory;
import com.company.scheduler.FCFSScheduler;
import com.company.scheduler.PriorityScheduler;
import com.company.scheduler.RoundRobinScheduler;
import com.company.scheduler.SJFScheduler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class SchedulingOptionPanel extends JPanel {
    private JButton FCFSButton = new JButton("FCFS");
    private JButton SJFButton = new JButton("SJF");
    private JButton priorityButton = new JButton("Priority");
    private JButton RRButton = new JButton("Round Robin");

    private GanttChartDialog ganttChartDialog = null;

    private ArrayList<ProcessHistory> processHistoryArrayList;

    public SchedulingOptionPanel() {
        setSize(Main.MAIN_SCREEN_WIDTH, 30);
        setBackground(Color.GRAY);
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setVisible(true);

        FCFSButton.setFocusable(false);
        FCFSButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                FCFSScheduler scheduler = new FCFSScheduler();

                processHistoryArrayList = scheduler.getProcessHistoryArrayList();

                for(ProcessHistory process : processHistoryArrayList) {
                    System.out.println(process.getProcessId() + " : " + process.getStartTime() + ", " + process.getFinishTime());
                }

                if(ganttChartDialog == null) {
                    ganttChartDialog = new GanttChartDialog(Main.mainScreen, "Gantt Chart", processHistoryArrayList);
                }
                ganttChartDialog.setVisible(true);
                FCFSButton.requestFocus();
                processHistoryArrayList = null;
            }
        });
        add(FCFSButton);

        SJFButton.setFocusable(false);
        SJFButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                SJFScheduler scheduler = new SJFScheduler();

                processHistoryArrayList = scheduler.getProcessHistoryArrayList();

                for(ProcessHistory process : processHistoryArrayList) {
                    System.out.println(process.getProcessId() + " : " + process.getStartTime() + ", " + process.getFinishTime());
                }

                if(ganttChartDialog == null) {
                    ganttChartDialog = new GanttChartDialog(Main.mainScreen, "Gantt Chart", processHistoryArrayList);
                }
                ganttChartDialog.setVisible(true);
                SJFButton.requestFocus();
                processHistoryArrayList = null;
            }
        });
        add(SJFButton);

        priorityButton.setFocusable(false);
        priorityButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                PriorityScheduler scheduler = new PriorityScheduler();

                processHistoryArrayList = scheduler.getProcessHistoryArrayList();

                processHistoryArrayList.stream().forEach(processHistory -> {
                    System.out.println(processHistory.getProcessId() + " : " + processHistory.getStartTime() + ", " +
                            processHistory.getFinishTime());
                });

                if(ganttChartDialog == null) {
                    ganttChartDialog = new GanttChartDialog(Main.mainScreen, "Gantt Chart", processHistoryArrayList);
                }
                ganttChartDialog.setVisible(true);
                priorityButton.requestFocus();
                processHistoryArrayList = null;
            }
        });
        add(priorityButton);

        RRButton.setFocusable(false);
        RRButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                RoundRobinScheduler scheduler = new RoundRobinScheduler();

                processHistoryArrayList = scheduler.getProcessHistoryArrayList();

                processHistoryArrayList.stream().forEach(processHistory -> {
                    System.out.println(processHistory.getProcessId() + " : " + processHistory.getStartTime() + ", " +
                            processHistory.getFinishTime());
                });

                if(ganttChartDialog == null) {
                    ganttChartDialog = new GanttChartDialog(Main.mainScreen, "Gantt Chart", processHistoryArrayList);
                }
                ganttChartDialog.setVisible(true);
                RRButton.requestFocus();
                processHistoryArrayList = null;
            }
        });
        add(RRButton);
    }

    public ArrayList<ProcessHistory> getProcessHistoryArrayList() {
        return this.processHistoryArrayList;
    }
}
