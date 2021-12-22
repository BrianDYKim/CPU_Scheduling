package com.company.GUI;

import com.company.Main;

import javax.swing.*;

public class MainScreen extends JFrame {

    private MenuBarPanel menuBarPanel = new MenuBarPanel();
    private SchedulingOptionPanel schedulingOptionPanel = new SchedulingOptionPanel();
    private ProcessTablePanel processTablePanel = new ProcessTablePanel();

    public MainScreen() {
        setTitle("CPU Scheduling");
        setSize(Main.MAIN_SCREEN_WIDTH, Main.MAIN_SCREEN_HEIGHT);
        setResizable(false);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        menuBarPanel.setBounds(0, 0, Main.MAIN_SCREEN_WIDTH, 30);
        add(menuBarPanel);

        schedulingOptionPanel.setBounds(0, 35, Main.MAIN_SCREEN_WIDTH, 30);
        add(schedulingOptionPanel);

        processTablePanel.setBounds(0, 70, Main.MAIN_SCREEN_WIDTH, 400);
        add(processTablePanel);
    }

    public ProcessTablePanel getProcessTablePanel() {
        return this.processTablePanel;
    }

    public SchedulingOptionPanel getSchedulingOptionPanel() {
        return this.schedulingOptionPanel;
    }
}
