package sample;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class road_create extends JPanel {
    private Boolean[] DayOfWeek_bool = {false,false,false,false,false,false,false};
    private JTextField Goal_txt = new JTextField();
    private JTextField Period_txt = new JTextField();
    private JTextField Time_txt = new JTextField();
    // 지우기
    private JTextField HowTo_txt = new JTextField();
    public road_create(){
        String[] measure = {"타이머","블로그","Github"};
        JComboBox<String> how_to = new JComboBox<String>(measure);

        setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 450, 600);
        add(panel);
        panel.setLayout(null);

        JLabel Goal = new JLabel("목표");
        Goal.setHorizontalAlignment(SwingConstants.CENTER);
        Goal.setBounds(70, 96, 70, 20);
        panel.add(Goal);

        JLabel Period = new JLabel("기간");
        Period.setHorizontalAlignment(SwingConstants.CENTER);
        Period.setBounds(70, 156, 70, 20);
        panel.add(Period);

        JLabel Time = new JLabel("시간");
        Time.setHorizontalAlignment(SwingConstants.CENTER);
        Time.setBounds(70, 222, 70, 20);
        panel.add(Time);

        JLabel HowTo = new JLabel("측정방법");
        HowTo.setHorizontalAlignment(SwingConstants.CENTER);
        HowTo.setBounds(70, 296, 70, 20);
        panel.add(HowTo);

        JLabel DayOfWeek = new JLabel("요일");
        DayOfWeek.setHorizontalAlignment(SwingConstants.CENTER);
        DayOfWeek.setBounds(70, 361, 70, 20);
        panel.add(DayOfWeek);

        JButton Mon = new JButton("월");
        Mon.setFont(new Font("굴림", Font.PLAIN, 10));
        Mon.setBounds(85, 400, 45, 30);
        Mon.setBackground(Color.BLUE);
        panel.add(Mon);

        JButton Tue = new JButton("화");
        Tue.setFont(new Font("굴림", Font.PLAIN, 10));
        Tue.setBounds(130, 400, 45, 30);
        Tue.setBackground(Color.BLUE);
        panel.add(Tue);

        JButton Wen = new JButton("수");
        Wen.setFont(new Font("굴림", Font.PLAIN, 10));
        Wen.setBounds(175, 400, 45, 30);
        Wen.setBackground(Color.BLUE);
        panel.add(Wen);

        JButton Thu = new JButton("목");
        Thu.setFont(new Font("굴림", Font.PLAIN, 10));
        Thu.setBounds(220, 400, 45, 30);
        Thu.setBackground(Color.BLUE);
        panel.add(Thu);

        JButton Fri = new JButton("금");
        Fri.setFont(new Font("굴림", Font.PLAIN, 10));
        Fri.setBounds(265, 400, 45, 30);
        Fri.setBackground(Color.BLUE);
        panel.add(Fri);

        JButton Sat = new JButton("토");
        Sat.setFont(new Font("굴림", Font.PLAIN, 10));
        Sat.setBounds(310, 400, 45, 30);
        Sat.setBackground(Color.BLUE);
        panel.add(Sat);

        JButton Sun = new JButton("일");
        Sun.setFont(new Font("굴림", Font.PLAIN, 10));
        Sun.setBounds(355, 400, 45, 30);
        Sun.setBackground(Color.BLUE);
        panel.add(Sun);

        JButton Save_btn = new JButton("Save RoadMap");
        Save_btn.setFont(new Font("굴림", Font.PLAIN, 10));
        Save_btn.setBounds(265, 505, 120, 20);
        panel.add(Save_btn);

        JLabel RoadMap_Create = new JLabel("로드맵 생성");
        RoadMap_Create.setFont(new Font("굴림", Font.PLAIN, 20));
        RoadMap_Create.setHorizontalAlignment(SwingConstants.CENTER);
        RoadMap_Create.setBounds(100, 0, 250, 72);
        panel.add(RoadMap_Create);

        Goal_txt.setBounds(140, 96, 250, 20);
        panel.add(Goal_txt);
        Goal_txt.setColumns(10);


        Period_txt.setColumns(10);
        Period_txt.setBounds(140, 156, 250, 20);
        panel.add(Period_txt);


        Time_txt.setColumns(10);
        Time_txt.setBounds(140, 222, 250, 20);
        panel.add(Time_txt);

        how_to.setBounds(140,296,65,20);
        panel.add(how_to);

        HowTo_txt.setColumns(10);
        HowTo_txt.setBounds(205, 296, 200, 20);
        panel.add(HowTo_txt);
        HowTo_txt.setVisible(false);


        //요일을 클릭하면 색이 변하게 함. boolean으로 만들었고 true면 하는 것, false면 안하는 것으로 인식
        Mon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DayOfWeek_bool[0] = !(DayOfWeek_bool[0]);
                if(DayOfWeek_bool[0].equals(true)){
                    Mon.setBackground(Color.ORANGE);
                }
                else
                    Mon.setBackground(Color.BLUE);
            }
        });

        Tue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DayOfWeek_bool[1] = !(DayOfWeek_bool[1]);
                if(DayOfWeek_bool[1].equals(true)){
                    Tue.setBackground(Color.ORANGE);
                }
                else
                    Tue.setBackground(Color.BLUE);
            }
        });

        Wen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DayOfWeek_bool[2] = !(DayOfWeek_bool[2]);
                if(DayOfWeek_bool[2].equals(true)){
                    Wen.setBackground(Color.ORANGE);
                }
                else
                    Wen.setBackground(Color.BLUE);
            }
        });

        Thu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DayOfWeek_bool[3] = !(DayOfWeek_bool[3]);
                if(DayOfWeek_bool[3].equals(true)){
                    Thu.setBackground(Color.ORANGE);
                }
                else
                    Thu.setBackground(Color.BLUE);
            }
        });

        Fri.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DayOfWeek_bool[4] = !(DayOfWeek_bool[4]);
                if(DayOfWeek_bool[4].equals(true)){
                    Fri.setBackground(Color.ORANGE);
                }
                else
                    Fri.setBackground(Color.BLUE);
            }
        });

        Sat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DayOfWeek_bool[5] = !(DayOfWeek_bool[5]);
                if(DayOfWeek_bool[5].equals(true)){
                    Sat.setBackground(Color.ORANGE);
                }
                else
                    Sat.setBackground(Color.BLUE);
            }
        });

        Sun.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DayOfWeek_bool[6] = !(DayOfWeek_bool[6]);
                if(DayOfWeek_bool[6].equals(true)){
                    Sun.setBackground(Color.ORANGE);
                }
                else
                    Sun.setBackground(Color.BLUE);
            }
        });

        Save_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 확인용
                System.out.println(getTime());;
            }
        });

        //측정방법 생성
        how_to.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox) e.getSource();
                int index = cb.getSelectedIndex();
                switch (index){
                    case 0:
                        HowTo_txt.setVisible(false);
                        break;
                    case 1:
                    case 2:
                        HowTo_txt.setVisible(true);
                        break;
                }
            }
        });
    }

    public String getgoal(){ return  Goal_txt.getText(); }

    public String getperiod(){ return  Period_txt.getText(); }

    public String getTime(){ return  Time_txt.getText(); }

}
