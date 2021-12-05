package GUI_ver2;

import GUI_ver2.Calendar_gui;
import database.DBConnection;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Vector;

class panel5 extends JPanel {     // 5 페이지 panel 생성
    private DBConnection database;
    private Vector<String> goals = new Vector<>(); // (Cal_showWorklist)schedule_lists에 넘기기 위한 변수
    private Vector<String> times = new Vector<>(); // (Cal_showWorklist)schedule_lists에 넘기기 위한 변수
    private JPanel schedule_lists; // 새로운 리스트가 생성되었을 때 재할당

    panel5(DBConnection database){
        // 스케줄 입력 패널
        setLayout(new BorderLayout());

        JPanel scheduling = new JPanel();
        schedule_lists = new Cal_showWorkList(goals, times);

        scheduling.setLayout(null);
        Cal_Inf cal_inf = new Cal_Inf();
        cal_inf.setBounds(10, 10, 320, 60);
        scheduling.add(cal_inf);

        // 확인 버튼 패널
        JButton submit = new JButton("확인");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String goal = cal_inf.getGoal();
                String time = cal_inf.getTime();
                goals.add(goal);
                times.add(time);
                refresh();
            }
        });

        submit.setBounds(350,50,60, 20);
        scheduling.add(submit);

        scheduling.setBorder(new LineBorder(Color.BLUE));
        add(BorderLayout.CENTER, scheduling);
        add(BorderLayout.NORTH, new Calendar_gui());
        add(BorderLayout.SOUTH, schedule_lists);
    }

    // 새로 고침
    void refresh() {
        removeAll();
        revalidate();
        repaint();

        // 스케줄 입력 패널
        setLayout(new BorderLayout());

        JPanel scheduling = new JPanel();
        JPanel[] schedule_lists = {new Cal_showWorkList(goals, times)};

        scheduling.setLayout(null);
        Cal_Inf cal_inf = new Cal_Inf();
        cal_inf.setBounds(10, 10, 320, 60);
        scheduling.add(cal_inf);

        // 확인 버튼 패널
        JButton submit = new JButton("확인");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String goal = cal_inf.getGoal();
                String time = cal_inf.getTime();
                goals.add(goal);
                times.add(time);
                refresh();
            }
        });

        submit.setBounds(350,50,60, 20);
        scheduling.add(submit);

        scheduling.setBorder(new LineBorder(Color.BLUE));
        add(BorderLayout.CENTER, scheduling);
        add(BorderLayout.NORTH, new Calendar_gui());
        add(BorderLayout.SOUTH, schedule_lists[0]);
    }
}

class panel6 extends JPanel{     // 6 페이지 panel 생성
    private DBConnection database;

    panel6(DBConnection database){
        this.database = database;
        JLabel label = new JLabel("<html>이것은 6page입니다.<br/><br/>내가 선택한 로드맵을 띄우는 곳</html>");
        add(label);
    }
}

public class page_3 extends JPanel{
    public page_3(DBConnection database){
        panel5 P_5 = new panel5(database);
        panel6 P_6 = new panel6(database);
        JPanel main = new JPanel();

        main.setLayout(new GridLayout(1,2));
        P_5.setBorder(new LineBorder(Color.CYAN,3)); // 패널 크기를 육안으로 확인하기 위해 경계선 그어줌
        P_6.setBorder(new LineBorder(Color.BLUE,3)); // 위와 동일
        main.add(P_5);
        main.add(P_6);
        this.add(main);
    }
}
