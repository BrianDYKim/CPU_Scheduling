package com.company.GUI;

import com.company.GUI.dialogs.GenerateProcessDialog;
import com.company.Main;
import com.company.dataStructure.MyProcess;
import com.company.scheduler.FCFSScheduler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuBarPanel extends JPanel {

    private JButton addProcessButton = new JButton("Add Process");

    private GenerateProcessDialog generateProcessDialog = null; // 프로세스 옵션을 설정하는 다이얼로그

    public MenuBarPanel() {
        setSize(Main.MAIN_SCREEN_WIDTH, 30);
        setBackground(Color.GRAY);
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setVisible(true);

        addProcessButton.setFocusPainted(false);
        addProcessButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(generateProcessDialog == null) {
                    generateProcessDialog = new GenerateProcessDialog(Main.mainScreen, "프로세스 생성하기");
                }
                generateProcessDialog.setVisible(true);
                addProcessButton.requestFocus();
            }
        });
        add(addProcessButton);
    }
}
