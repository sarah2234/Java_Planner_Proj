package GUI_ver2;

import GUI_ver2.Calendar_gui;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Vector;

class panel5 extends JPanel {     // 5 페이지 panel 생성
    private Vector<String> goals = new Vector<>(); // (Cal_showWorklist)schedule_lists에 넘기기 위한 변수
    private Vector<String> times = new Vector<>(); // (Cal_showWorklist)schedule_lists에 넘기기 위한 변수
    private JPanel schedule_lists; // 새로운 리스트가 생성되었을 때 재할당

    panel5(){
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

class panel6 extends JPanel{    // 6 페이지 panel 생성
    //로드맵은 5개만
    private JLabel[] map = new JLabel[5];
    private Vector<JButton> edit_btn = new Vector<JButton>();
    private int cnt = 0;
    private Image image_2;
    private int Measure_int;
    private Vector<DataSetting> set = new Vector<DataSetting>();
    private JPanel Center;
    JPanel content;

    public panel6(){
        this.setLayout(null);

        //데이터 임의로 지정
        DataSetting one = new DataSetting("공부","2","2","월,수,금","깃허브");
        set.add(one);
        drawPanel();
    }

    private void drawPanel() {
        content = new JPanel();
        content.setBounds(0, 0, 450, 520);
        this.add(content);
        content.setLayout(null);

        // 중앙 : 리스트
        Center = new JPanel();
        Center.setBounds(55, 135, 340, 350);
        content.add(Center);
        Center.setBackground(new Color(0,0,0,0));
        Center.setLayout(null);

        for (int i = 0; i < 5; i++) {
            JButton btn = new JButton("수정");
            btn.setBounds(285, i*45,57, 45);
            btn.setHorizontalAlignment(SwingConstants.CENTER);
            btn.setFont(new Font("맑은 고딕", Font.PLAIN, 8));
            edit_btn.add(btn);
        }

        // 넣어둔 것들을 출력
        for (int i = 0; i < set.size(); i++) {
            map[i] = new JLabel(set.elementAt(i).getAll());
            map[i].setBounds(0, i*45, 285, 45);
            Center.add(map[i]);
            Center.add(edit_btn.elementAt(i));
            edit_btn.elementAt(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new EditRoadMap_gui();
                    JButton cd = (JButton) e.getSource();
                    cd.setEnabled(false);
                }
            });
        }

        JLabel scheduleTitle = new JLabel("My RoadMap");
        scheduleTitle.setForeground(Color.WHITE);
        scheduleTitle.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
        scheduleTitle.setBounds(250, 43, 210, 49);
        content.add(scheduleTitle);

        // 배경화면 생성
        image_2 = new ImageIcon("C:\\Users\\RC\\IdeaProjects\\javafirst\\src\\GUI_ver1\\image\\background.jpeg").getImage();
        JPanel background = new JPanel() {
            public void paintComponent(Graphics g) {
                Dimension d = getSize();
                g.drawImage(image_2, 0, 0, d.width, d.height, null);

                setOpaque(false); // 그림 투명도
                super.paintComponent(g);
            }
        };
        background.setBounds(0,0,450, 530);
        content.add(background);
    }

    class EditRoadMap_gui extends JFrame {

        JTextField goal_txt;
        JTextField Period_txt;
        JTextField Time_txt;
        JTextField DayOfWeek_txt;
        JTextField URL;
        JButton EditButton;

        public EditRoadMap_gui() {
            setVisible(true);
            setSize(292, 325);

            this.setLayout(null);

            String[] measure = {"타이머","블로그","Github"};

            JLabel Goal = new JLabel("목적");
            Goal.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
            Goal.setHorizontalAlignment(SwingConstants.CENTER);
            Goal.setBounds(0, 30, 65, 25);
            this.add(Goal);

            JLabel Period = new JLabel("기간");
            Period.setHorizontalAlignment(SwingConstants.CENTER);
            Period.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
            Period.setBounds(0, 65, 65, 25);
            this.add(Period);

            JLabel Time = new JLabel("시간");
            Time.setHorizontalAlignment(SwingConstants.CENTER);
            Time.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
            Time.setBounds(0, 100, 65, 25);
            this.add(Time);

            JLabel HowTo = new JLabel("측정방법");
            HowTo.setHorizontalAlignment(SwingConstants.CENTER);
            HowTo.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
            HowTo.setBounds(0, 170, 65, 25);
            this.add(HowTo);

            goal_txt = new JTextField();
            goal_txt.setBounds(89, 30, 100, 25);
            this.add(goal_txt);
            goal_txt.setColumns(10);

            Time_txt = new JTextField();
            Time_txt.setColumns(10);
            Time_txt.setBounds(89, 100, 100, 25);
            this.add(Time_txt);

            JComboBox HowTo_combo = new JComboBox(measure);
            HowTo_combo.setBounds(89, 170, 70, 25);
            add(HowTo_combo);

            URL = new JTextField();
            URL.setBounds(89, 203, 150, 25);
            this.add(URL);
            URL.setColumns(10);

            JLabel DayOFWeek = new JLabel("요일");
            DayOFWeek.setHorizontalAlignment(SwingConstants.CENTER);
            DayOFWeek.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
            DayOFWeek.setBounds(0, 135, 65, 25);
            this.add(DayOFWeek);

            Period_txt = new JTextField();
            Period_txt.setBounds(89, 65, 100, 25);
            this.add(Period_txt);
            Period_txt.setColumns(10);

            JLabel Period_unit = new JLabel("달");
            Period_unit.setHorizontalAlignment(SwingConstants.CENTER);
            Period_unit.setBounds(190, 67, 30, 25);
            this.add(Period_unit);

            JLabel Time_unit = new JLabel("시간");
            Time_unit.setHorizontalAlignment(SwingConstants.CENTER);
            Time_unit.setBounds(190, 102, 30, 25);
            this.add(Time_unit);

            DayOfWeek_txt = new JTextField();
            DayOfWeek_txt.setBounds(89, 135, 100, 25);
            this.add(DayOfWeek_txt);
            DayOfWeek_txt.setColumns(10);
            URL.setVisible(false);

            HowTo_combo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JComboBox cb = (JComboBox) e.getSource();
                    int index = cb.getSelectedIndex();
                    Measure_int = index;
                    switch (index){
                        case 0:
                            URL.setVisible(false);
                            break;
                        case 1:
                        case 2:
                            URL.setVisible(true);
                            break;
                    }
                }
            });

            EditButton = new JButton("수정");
            EditButton.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
            EditButton.setBounds(193, 252, 76, 31);

            EditButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    for (int i = 0; i < edit_btn.size(); i++) {
                        edit_btn.elementAt(i).setEnabled(true);
                    }
                    //확인용
//                    DataSetting one = new DataSetting("공부","2","2","월,수,금","깃허브");
//                    set.add(one);
                    dispose();
                    content.setVisible(false);
                    content.removeAll();
                    drawPanel();
                    revalidate();
                    repaint();
                    content.setVisible(true);
                    // 입력 정보 보내주기
                }
            });

            this.add(EditButton);
        }

        public String getgoal() {
            return goal_txt.getText();
        }

        public String getPeriod(){
            return Period_txt.getText();
        }

        public String getTime() {
            return Time_txt.getText();
        }

        public String getDayOfWeek(){
            return DayOfWeek_txt.getText();
        }

        public String getAddress() {
            return URL.getText();
        }
    }

    // 이것을 이용해서 DB값을 받는다. 1. 이 함수를 new 해서 한번 정의, 2. Vector set에 그것을 집어넣기
    class DataSetting{
        String goal_Data;
        String period_Data;
        String time_Data;
        String dayofWeek;
        String howTo_Data;
        public DataSetting(String Goal_Data, String Period_Data, String Time_Data, String DayofWeek, String HowTo_Data){
            goal_Data = Goal_Data;
            period_Data = Period_Data;
            time_Data = Time_Data;
            dayofWeek = DayofWeek;
            howTo_Data = HowTo_Data;
        }
        public String getgoal_v() {
            return goal_Data;
        }

        public String getPeriod_v(){
            return period_Data;
        }

        public String getTime_v() {
            return time_Data;
        }

        public String getDayOfWeek_v(){
            return dayofWeek;
        }

        public String gethowto_v() {
            return howTo_Data;
        }
        public String getAll(){
            return "* "+goal_Data+", 기간 : "+period_Data+"달, 시간 : "+time_Data+"시간, "+dayofWeek+", "+howTo_Data;
        }
    }
}

public class page_3 extends JPanel{
    public page_3(){
        panel5 P_5 = new panel5();
        panel6 P_6 = new panel6();
        JPanel main = new JPanel();

        main.setLayout(new GridLayout(1,2));
        P_5.setBorder(new LineBorder(Color.CYAN,3)); // 패널 크기를 육안으로 확인하기 위해 경계선 그어줌
        P_6.setBorder(new LineBorder(Color.BLUE,3)); // 위와 동일
        main.add(P_5);
        main.add(P_6);
        this.add(main);
    }
}
