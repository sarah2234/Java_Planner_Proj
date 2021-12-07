package GUI_ver2;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import static GUI_ver2.Main.client;

class panel5 extends JPanel {     // 5 페이지 panel 생성
    //DB에서 받은 요소를 이 벡터에 넣는다. 그리고 그것을 이용
    private Vector<String> goals = new Vector<>();
    private Vector<String> times = new Vector<>();
    private Vector<String> comments = new Vector<>();
    private Vector<String> howto = new Vector<>();

    private String year;
    private String month;
    private String day;

    panel5(){
        Calendar_gui Cal = new Calendar_gui();
        // 스케줄 입력 패널
        setLayout(new BorderLayout());

        JPanel SouthPanel = new JPanel(new BorderLayout());
        JPanel date_Panel = new JPanel();
        date_Panel.setLayout(null);

        JPanel List_scd = new JPanel(new GridLayout(6,1));
        JLabel title = new JLabel("리스트");
        JLabel[] list_scd = new JLabel[5];
        List_scd.add(title);

        for (int i = 0; i < goals.size(); i++) {
            list_scd[i].setText("* " + goals.elementAt(i)+" "+times.elementAt(i)+" "+comments.elementAt(i)+" "+howto.elementAt(i));
            list_scd[i].setVisible(true);
            List_scd.add(list_scd[i]);
        }

        JLabel WhatDate = new JLabel("날짜 : ");
        WhatDate.setBounds(10, 10,40,60);
        date_Panel.add(WhatDate);
        JLabel date_old = new JLabel();

        // 날짜 전환 확인 버튼 패널
        JButton check = new JButton("확인");
        check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                year = Cal.getYear_num();
                month = Cal.getMonth_num();
                day = Cal.getDay_num();

                String date_change = year+"년 "+month+"월 "+day+"일";

                date_old.setText(date_change);
                date_old.setBounds(50, 10, 150, 60);
                System.out.println("in "+year+" "+month+" "+day);

                date_Panel.add(date_old);
                date_Panel.setVisible(false);
                date_Panel.setVisible(true);
            }
        });

        check.setBounds(350,40,60, 20);
        date_Panel.add(check);

        JButton submit = new JButton("추가");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Cal_Inf();
            }
        });

        submit.setBounds(350,10,60, 20);
        date_Panel.add(submit);

        SouthPanel.add(BorderLayout.NORTH, date_Panel);
        SouthPanel.add(BorderLayout.CENTER, List_scd);

        date_Panel.setBorder(new LineBorder(Color.BLUE));
        date_Panel.setPreferredSize(new Dimension(350,100));
        add(BorderLayout.CENTER, SouthPanel);
        add(BorderLayout.NORTH, Cal);
    }

    public class Cal_Inf extends JFrame{
        private JTextField goal_txt;
        private JTextField time_txt;
        private JTextField comment_txt;
        private JTextField howto_txt;
        private String[] measure = {"타이머","블로그","Github"};
        private int measure_int;
        private JComboBox<String> how_to = new JComboBox<String>(measure);
        public Cal_Inf(){
            JPanel All = new JPanel();
            All.setLayout(null);

            JLabel goal_label = new JLabel("목표", JLabel.CENTER);
            goal_label.setBounds(10, 20, 50, 18);

            JLabel time_label = new JLabel("시간", JLabel.CENTER);
            time_label.setBounds(10, 40, 50, 18);

            JLabel comment = new JLabel("코멘트", JLabel.CENTER);
            comment.setBounds(10, 60, 50, 18);

            JLabel Howto = new JLabel("측정방법",JLabel.CENTER);
            Howto.setBounds(10, 80,50,18);

            JButton addit = new JButton("추가");
            addit.setBounds(200,140,60,50);

            goal_txt = new JTextField(10);
            goal_txt.setBounds(70, 20, 50, 18);

            time_txt = new JTextField(10);
            time_txt.setBounds(70, 40, 50, 18);

            comment_txt = new JTextField(10);
            comment_txt.setBounds(70, 60, 100, 18);

            how_to.setBounds(70,80,80,18);

            howto_txt = new JTextField(10);
            howto_txt.setBounds(70,100,150,18);

            goal_txt.setBorder(new LineBorder(Color.ORANGE));
            time_txt.setBorder(new LineBorder(Color.ORANGE));
            comment_txt.setBorder(new LineBorder(Color.ORANGE));

            All.add(goal_label);
            All.add(goal_txt);
            All.add(time_label);
            All.add(time_txt);
            All.add(comment);
            All.add(comment_txt);
            All.add(Howto);
            All.add(how_to);
            All.add(howto_txt);
            All.add(addit);
            howto_txt.setVisible(false);

            how_to.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JComboBox cb = (JComboBox) e.getSource();
                    int index = cb.getSelectedIndex();
                    measure_int = index;
                    switch (index){
                        case 0:
                            howto_txt.setVisible(false);
                            break;
                        case 1:
                        case 2:
                            howto_txt.setVisible(true);
                            break;
                    }
                }
            });

            addit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                    // 데이터 보내주기
                    goals.add(getGoal());
                    times.add(getTime());
                    comments.add(getComment());
                    howto.add(getHowto());
                    System.out.println(getGoal());
                    System.out.println(getTime());
                    System.out.println(getComment());
                    System.out.println(getHowto());
                    System.out.println(getURL());
                    refresh();
                }
            });

            All.setBorder(new LineBorder(Color.BLUE));

            add(All);

            setSize(280,230);
            setVisible(true);
        }

        public String getGoal() {
            return goal_txt.getText();
        }

        public String getTime() {
            return time_txt.getText();
        }

        public String getComment(){
            return comment_txt.getText();
        }

        public String getURL(){
            return howto_txt.getText();
        }

        public String getHowto(){
            return measure[measure_int];
        }
    }

    // 새로 고침
    void refresh() {
        removeAll();
        revalidate();
        repaint();

        Calendar_gui Cal = new Calendar_gui();

        setLayout(new BorderLayout());

        JPanel SouthPanel = new JPanel(new BorderLayout());
        JPanel date_Panel = new JPanel();
        date_Panel.setLayout(null);

        JPanel List_scd = new JPanel(new GridLayout(6,1));
        JLabel title = new JLabel("리스트");
        JLabel[] list_scd = new JLabel[5];
        List_scd.add(title);

        for (int i = 0; i < 5; i++) {
            list_scd[i] = new JLabel();
        }


        for (int i = 0; i < goals.size(); i++) {
            list_scd[i].setText("* " + goals.elementAt(i)+" "+times.elementAt(i)+" "+comments.elementAt(i)+" "+howto.elementAt(i));
            list_scd[i].setVisible(true);
            List_scd.add(list_scd[i]);
        }

        JLabel WhatDate = new JLabel("날짜 : ");
        WhatDate.setBounds(10, 10,40,60);
        date_Panel.add(WhatDate);
        JLabel date_old = new JLabel();

        // 확인 버튼 패널
        JButton check = new JButton("확인");
        check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                year = Cal.getYear_num();
                month = Cal.getMonth_num();
                day = Cal.getDay_num();

                String date_change = year+"년 "+month+"월 "+day+"일";

                date_old.setText(date_change);
                date_old.setBounds(50, 10, 150, 60);
                System.out.println("in"+year+" "+month+" "+day);

                date_Panel.add(date_old);
                date_Panel.setVisible(false);
                date_Panel.setVisible(true);
            }
        });

        check.setBounds(350,40,60, 20);
        date_Panel.add(check);

        JButton submit = new JButton("추가");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Cal_Inf();
            }
        });

        submit.setBounds(350,10,60, 20);
        date_Panel.add(submit);

        SouthPanel.add(BorderLayout.NORTH, date_Panel);
        SouthPanel.add(BorderLayout.CENTER, List_scd);

        date_Panel.setBorder(new LineBorder(Color.BLUE));
        date_Panel.setPreferredSize(new Dimension(350,100));
        add(BorderLayout.CENTER, SouthPanel);
        add(BorderLayout.NORTH, Cal);
    }
}

class panel6 extends JPanel{    // 6 페이지 panel 생성
    //로드맵은 5개만
    private JLabel[] map = new JLabel[5];
    private boolean [] error = {false,false,false,false,false};
    private Vector<JButton> edit_btn = new Vector<JButton>();
    private int cnt = 0;
    private Image image_2;
    private Vector<DataSetting> set = new Vector<DataSetting>();
    private JPanel Center;
    JPanel content;

    public panel6(){
        this.setLayout(null);

        //데이터 임의로 지정
        DataSetting one = new DataSetting("공부","2","없음","월,수,금");
        DataSetting two = new DataSetting("공부","2","없다고","월,수,금");
        set.add(one);
        set.add(two);
        for (int i = 0; i < 5; i++) {
            Image btn_image = new ImageIcon("src\\GUI_ver2\\image\\수정버튼.png").getImage();
            Image into_btn_image = btn_image.getScaledInstance(77,72,Image.SCALE_SMOOTH);
            ImageIcon real_btn_image = new ImageIcon(into_btn_image);
            JButton btn = new JButton(real_btn_image);
            btn.setBounds(285, i*45,57, 45);
            btn.setHorizontalAlignment(SwingConstants.CENTER);
            btn.setForeground(Color.white);
            btn.setContentAreaFilled(false);
            btn.setFont(new Font("맑은 고딕", Font.PLAIN, 8));
            edit_btn.add(btn);
        }
        drawPanel();

        client.setJgui(this);
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

        // 넣어둔 것들을 출력
        for (int i = 0; i < set.size(); i++) {
            map[i] = new JLabel(set.elementAt(i).getAll());
            map[i].setBounds(0, i*45, 285, 45);
            map[i].setToolTipText(set.elementAt(i).getComment_v());
            Center.add(map[i]);
            Center.add(edit_btn.elementAt(i));
            edit_btn.elementAt(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new EditRoadMap_gui();
//                    System.out.print(set.size());
//                    System.out.println(" "+cnt);
//                    System.out.println();
                    cnt++;
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
        image_2 = new ImageIcon("src\\GUI_ver2\\image\\background.jpeg").getImage();
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
        JTextField Comment_txt;
        JTextField DayOfWeek_txt;
        JButton EditButton;

        public EditRoadMap_gui() {
            setVisible(true);
            setSize(292, 325);

            this.setLayout(null);

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

            JLabel Comment = new JLabel("코멘트");
            Comment.setHorizontalAlignment(SwingConstants.CENTER);
            Comment.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
            Comment.setBounds(0, 100, 65, 25);
            this.add(Comment);

            goal_txt = new JTextField();
            goal_txt.setBounds(89, 30, 100, 25);
            this.add(goal_txt);
            goal_txt.setColumns(10);

            Comment_txt = new JTextField();
            Comment_txt.setColumns(10);
            Comment_txt.setBounds(89, 100, 100, 25);
            this.add(Comment_txt);

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


            DayOfWeek_txt = new JTextField();
            DayOfWeek_txt.setBounds(89, 135, 100, 25);
            this.add(DayOfWeek_txt);
            DayOfWeek_txt.setColumns(10);


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

        public String getComment() {
            return Comment_txt.getText();
        }

        public String getDayOfWeek(){
            return DayOfWeek_txt.getText();
        }
    }

    // 이것을 이용해서 DB값을 받는다. 1. 이 함수를 new 해서 한번 정의, 2. Vector set에 그것을 집어넣기
    class DataSetting{
        String goal_Data;
        String period_Data;
        String comment_Data;
        String dayofWeek;
        public DataSetting(String Goal_Data, String Period_Data, String Comment_Data, String DayofWeek){
            goal_Data = Goal_Data;
            period_Data = Period_Data;
            comment_Data = Comment_Data;
            dayofWeek = DayofWeek;
        }
        public String getgoal_v() {
            return goal_Data;
        }

        public String getPeriod_v(){
            return period_Data;
        }

        public String getComment_v() {
            return comment_Data;
        }

        public String getDayOfWeek_v(){
            return dayofWeek;
        }

        public String getAll(){
            return "* "+goal_Data+", 기간 : "+period_Data+"달, "+dayofWeek;
        }
    }

    public void appendRId(String msg) {
        System.out.println(msg + " 6패널 도착");
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
