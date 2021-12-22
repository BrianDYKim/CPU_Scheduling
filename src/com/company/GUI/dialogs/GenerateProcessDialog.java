package com.company.GUI.dialogs;

import com.company.Main;
import com.company.dataStructure.MyProcess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// 다이얼로그로 입력을 burstTime, arrivalTime, priority를 받습니다.
public class GenerateProcessDialog extends JDialog {

    private JPanel mainPanel = new JPanel();
    private ProcessInformationPanel processInformationPanel = new ProcessInformationPanel();
    private JPanel buttonPanel = new JPanel();

    private JButton generateButton = new JButton("Generate");
    private JButton cancelButton = new JButton("Cancel");

    public GenerateProcessDialog(JFrame frame, String title) {
        super(frame, title, true);

        setSize(450, 450);
        setLocationRelativeTo(null);

        mainPanel.setLayout(new BorderLayout());
        add(mainPanel);

        mainPanel.add(processInformationPanel, BorderLayout.CENTER);

        generateButton.setFocusPainted(false);
        generateButton.setContentAreaFilled(false);
        generateButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int burstTime = processInformationPanel.getBurstTime();
                int arrivalTime = processInformationPanel.getArrivalTime();
                int priority = processInformationPanel.getPriority();

                System.out.println("burstTime : " + burstTime);
                System.out.println("arrivalTime : " + arrivalTime);
                System.out.println("priority : " + priority);

                Main.readyQueue.enQueue(new MyProcess(Main.readyQueue.getSize(), burstTime, arrivalTime, priority));

                /** 프로세스 추가를 테이블에 반영 */
                // model에 새로 추가된 프로세스를 추가함
                Main.mainScreen.getProcessTablePanel().getModel().insertRow(Main.readyQueue.getSize() - 1,
                        new Object[]{Integer.toString(Main.readyQueue.getSize() - 1), Integer.toString(burstTime),
                                Integer.toString(arrivalTime), Integer.toString(priority), "0", "0"});
                // model의 변경 사항을 테이블에 반영함
                Main.mainScreen.getProcessTablePanel().getProcessTable().updateUI();

                // TextField에 다시 입력을 받기 원할하도록 0으로 초기화
                processInformationPanel.initializeBurstTimeTextField();
                processInformationPanel.initializeArrivalTimeTextField();
                processInformationPanel.initializePriorityTextField();

                dispose();
            }
        });

        cancelButton.setFocusPainted(false);
        cancelButton.setContentAreaFilled(false);
        cancelButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                processInformationPanel.initializeBurstTimeTextField();
                processInformationPanel.initializeArrivalTimeTextField();
                processInformationPanel.initializePriorityTextField();

                dispose(); // 다이얼로그 나가기
            }
        });

        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(generateButton);
        buttonPanel.add(cancelButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
    }

    // Process의 정보를 입력하는 Grid Layout을 반영한 Frame
    class ProcessInformationPanel extends JPanel {

        // 라벨에 관련된 변수들
        private JLabel burstTimeLabel = new JLabel("burstTime");
        private JLabel arrivalTimeLabel = new JLabel("arrivalTime");
        private JLabel priorityLabel = new JLabel("priority");

        // JTextField 변수들
        private JTextField burstTimeTextField = new JTextField();
        private JTextField arrivalTimeTextField = new JTextField();
        private JTextField priorityTextField = new JTextField();

        public ProcessInformationPanel() {
            setLayout(new GridLayout(3, 2));
            setBackground(Color.GRAY);
            setVisible(true);

            burstTimeLabel.setFont(new Font("SanSerif", Font.BOLD, 15));
            add(burstTimeLabel);

            add(burstTimeTextField);

            arrivalTimeLabel.setFont(new Font("SanSerif", Font.BOLD, 15));
            add(arrivalTimeLabel);

            add(arrivalTimeTextField);

            priorityLabel.setFont(new Font("SanSerif", Font.BOLD, 15));
            add(priorityLabel);

            add(priorityTextField);
        }

        public int getBurstTime() {
            return Integer.parseInt(this.burstTimeTextField.getText());
        }

        public int getArrivalTime() {
            return Integer.parseInt(this.arrivalTimeTextField.getText());
        }

        public int getPriority() {
            return Integer.parseInt(this.priorityTextField.getText());
        }

        public void initializeBurstTimeTextField() {
            this.burstTimeTextField.setText("");
        }

        public void initializeArrivalTimeTextField() {
            this.arrivalTimeTextField.setText("");
        }

        public void initializePriorityTextField() {
            this.priorityTextField.setText("");
        }
    }
}
