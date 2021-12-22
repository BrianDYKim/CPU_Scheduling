package com.company.GUI.dialogs;

import com.company.dataStructure.ProcessHistory;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GanttChartDialog extends JDialog {
    private JLabel upperLabel = new JLabel();
    private JLabel lowerLabel = new JLabel();
    private JLabel startLabel = new JLabel();
    private JLabel finalLabel = new JLabel();

    // 프로세스의 끝점을 알리는 라벨들을 저장하는 변수
    private ArrayList<JLabel> endLineLabelArrayList = new ArrayList<>();
    // 프로세스의 이름을 나타내는 라벨들을 저장하는 변수
    private ArrayList<JLabel> processNameLabelList = new ArrayList<>();
    // 프로세스의 종료 지점을 나타내는 라벨들을 저장하는 변수
    private ArrayList<JLabel> endTimeLabelList = new ArrayList<>();

    private int totalLength;

    /** Constructor
     * @param frame (JFrame)
     * @param title (String)
     * @param processHistoryArrayList (ArrayList<ProcessHistory>)
     * */
    public GanttChartDialog(JFrame frame, String title, ArrayList<ProcessHistory> processHistoryArrayList) {
        super(frame, title, true);

        setSize(1200, 450);
        setLocationRelativeTo(null);
        setLayout(null);

        totalLength = processHistoryArrayList.get(processHistoryArrayList.size() - 1).getFinishTime();

        upperLabel.setOpaque(true);
        upperLabel.setBackground(Color.RED);
        upperLabel.setBounds(13, 10, totalLength * 7, 3);
        add(upperLabel);

        lowerLabel.setOpaque(true);
        lowerLabel.setBackground(Color.RED);
        lowerLabel.setBounds(13, 80, totalLength * 7, 3);
        add(lowerLabel);

        startLabel.setOpaque(true);
        startLabel.setBackground(Color.RED);
        startLabel.setBounds(10, 10, 3, 73);
        add(startLabel);

        finalLabel.setOpaque(true);
        finalLabel.setBackground(Color.RED);
        finalLabel.setBounds(13 + totalLength * 7, 10, 3, 73);
        add(finalLabel);

        processHistoryArrayList.stream().forEach(processHistory -> {
            JLabel processLabel = new JLabel();
            processLabel.setOpaque(true);
            processLabel.setBackground(Color.RED);
            processLabel.setBounds(13 + processHistory.getFinishTime() * 7, 10, 2, 73);
            endLineLabelArrayList.add(processLabel);

            JLabel processNameLabel = new JLabel();
            char id = processHistory.getProcessId().charAt(processHistory.getProcessId().length() - 1);
            processNameLabel.setText("P" + id);
            processNameLabel.setBounds(13 + processHistory.getFinishTime() * 7 - 20, 90,
                    5 * (processHistory.getFinishTime() - processHistory.getStartTime()), 20);
            processNameLabelList.add(processNameLabel);

            JLabel endTimeLabel = new JLabel(Integer.toString(processHistory.getFinishTime()));
            endTimeLabel.setBounds(13 + processHistory.getFinishTime() * 7, 80, 20, 20);
            endTimeLabelList.add(endTimeLabel);
        });

        endLineLabelArrayList.stream().forEach(label -> {
            add(label);
        });

        processNameLabelList.stream().forEach(label -> {
            add(label);
        });

        endTimeLabelList.stream().forEach(label -> {
            add(label);
        });

        endLineLabelArrayList = null;
        processNameLabelList = null;
        endTimeLabelList = null;
    }
}
