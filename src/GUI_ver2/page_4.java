package GUI_ver2;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

class panel7 extends JPanel {
    private Image image; // 배경이미지
    private String[] DayofWeek = {"월","화","수","목","금","토","일"};
    private Boolean[] DayOfWeek_bool = {false,false,false,false,false,false,false};
    private JTextField Goal_txt = new JTextField();
    private JTextField Period_txt = new JTextField();
    private JTextField Comment_txt = new JTextField();

    public panel7() {
        setLayout(new BorderLayout());

        JLabel Goal = new JLabel("목표");
        Goal.setHorizontalAlignment(SwingConstants.CENTER);
        Goal.setBounds(70, 96, 70, 20);
        Goal.setForeground(Color.white);
        this.add(Goal);

        JLabel Period = new JLabel("기간");
        Period.setHorizontalAlignment(SwingConstants.CENTER);
        Period.setBounds(70, 156, 70, 20);
        Period.setForeground(Color.white);
        add(Period);

        JLabel Comment = new JLabel("코멘트");
        Comment.setHorizontalAlignment(SwingConstants.CENTER);
        Comment.setBounds(70, 216, 70, 20);
        Comment.setForeground(Color.white);
        add(Comment);

        JLabel DayOfWeek = new JLabel("요일");
        DayOfWeek.setHorizontalAlignment(SwingConstants.CENTER);
        DayOfWeek.setBounds(70, 276, 70, 20);
        DayOfWeek.setForeground(Color.white);
        add(DayOfWeek);

        JButton Mon = new JButton("월");
        Mon.setFont(new Font("굴림", Font.PLAIN, 10));
        Mon.setBounds(85, 316, 45, 30);
        Mon.setBackground(new Color(0,0,0,0));
        Mon.setForeground(Color.white);
        Mon.setOpaque(false);
        Mon.setContentAreaFilled(false);
        Mon.setFocusPainted(false);
        add(Mon);

        JButton Tue = new JButton("화");
        Tue.setFont(new Font("굴림", Font.PLAIN, 10));
        Tue.setBounds(130, 316, 45, 30);
        Tue.setForeground(Color.white);
        Tue.setOpaque(false);
        Tue.setContentAreaFilled(false);
        Tue.setFocusPainted(false);
        add(Tue);

        JButton Wen = new JButton("수");
        Wen.setFont(new Font("굴림", Font.PLAIN, 10));
        Wen.setBounds(175, 316, 45, 30);
        Wen.setForeground(Color.white);
        Wen.setOpaque(false);
        Wen.setContentAreaFilled(false);
        Wen.setFocusPainted(false);
        add(Wen);

        JButton Thu = new JButton("목");
        Thu.setFont(new Font("굴림", Font.PLAIN, 10));
        Thu.setBounds(220, 316, 45, 30);
        Thu.setForeground(Color.white);
        Thu.setOpaque(false);
        Thu.setContentAreaFilled(false);
        Thu.setFocusPainted(false);
        add(Thu);

        JButton Fri = new JButton("금");
        Fri.setFont(new Font("굴림", Font.PLAIN, 10));
        Fri.setBounds(265, 316, 45, 30);
        Fri.setForeground(Color.white);
        Fri.setOpaque(false);
        Fri.setContentAreaFilled(false);
        Fri.setFocusPainted(false);
        add(Fri);

        JButton Sat = new JButton("토");
        Sat.setFont(new Font("굴림", Font.PLAIN, 10));
        Sat.setBounds(310, 316, 45, 30);
        Sat.setForeground(Color.white);
        Sat.setOpaque(false);
        Sat.setContentAreaFilled(false);
        Sat.setFocusPainted(false);
        add(Sat);

        JButton Sun = new JButton("일");
        Sun.setFont(new Font("굴림", Font.PLAIN, 10));
        Sun.setBounds(355, 316, 45, 30);
        Sun.setForeground(Color.white);
        Sun.setOpaque(false);
        Sun.setContentAreaFilled(false);
        Sun.setFocusPainted(false);
        add(Sun);

        JButton Save_btn = new JButton("Save RoadMap");
        Save_btn.setFont(new Font("굴림", Font.PLAIN, 10));
        Save_btn.setBounds(265, 495, 120, 20);
        Save_btn.setForeground(Color.white);
        Save_btn.setOpaque(false);
        Save_btn.setContentAreaFilled(false);
        Save_btn.setFocusPainted(false);
        add(Save_btn);

        JLabel RoadMap_Create = new JLabel("로드맵 생성");
        RoadMap_Create.setFont(new Font("굴림", Font.PLAIN, 20));
        RoadMap_Create.setHorizontalAlignment(SwingConstants.CENTER);
        RoadMap_Create.setBounds(100, 0, 250, 72);
        RoadMap_Create.setForeground(Color.white);
        add(RoadMap_Create);

        Goal_txt.setBounds(140, 96, 250, 20);
        add(Goal_txt);
        Goal_txt.setColumns(10);


        Period_txt.setColumns(10);
        Period_txt.setBounds(140, 156, 250, 20);
        add(Period_txt);


        Comment_txt.setColumns(10);
        Comment_txt.setBounds(140, 222, 250, 20);
        add(Comment_txt);

        //요일을 클릭하면 색이 변하게 함. boolean으로 만들었고 true면 하는 것, false면 안하는 것으로 인식
        Mon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DayOfWeek_bool[0] = !(DayOfWeek_bool[0]);
                if(DayOfWeek_bool[0]){
                    Mon.setOpaque(true);
                    Mon.setBackground(Color.ORANGE);
                }
                else {
                    Mon.setOpaque(false);
                }
            }
        });

        Tue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DayOfWeek_bool[1] = !(DayOfWeek_bool[1]);
                if(DayOfWeek_bool[1].equals(true)){
                    Tue.setOpaque(true);
                    Tue.setBackground(Color.ORANGE);
                }
                else {
                    Tue.setOpaque(false);
                }
            }
        });

        Wen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DayOfWeek_bool[2] = !(DayOfWeek_bool[2]);
                if(DayOfWeek_bool[2].equals(true)){
                    Wen.setOpaque(true);
                    Wen.setBackground(Color.ORANGE);
                }
                else{
                    Wen.setOpaque(false);
                }
            }
        });

        Thu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DayOfWeek_bool[3] = !(DayOfWeek_bool[3]);
                if(DayOfWeek_bool[3].equals(true)){
                    Thu.setOpaque(true);
                    Thu.setBackground(Color.ORANGE);
                }
                else {
                    Thu.setOpaque(false);
                }
            }
        });

        Fri.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DayOfWeek_bool[4] = !(DayOfWeek_bool[4]);
                if(DayOfWeek_bool[4].equals(true)){
                    Fri.setOpaque(true);
                    Fri.setBackground(Color.ORANGE);
                }
                else {
                    Fri.setOpaque(false);
                }
            }
        });

        Sat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DayOfWeek_bool[5] = !(DayOfWeek_bool[5]);
                if(DayOfWeek_bool[5].equals(true)){
                    Sat.setOpaque(true);
                    Sat.setBackground(Color.ORANGE);
                }
                else {
                    Sat.setOpaque(false);
                }
            }
        });

        Sun.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DayOfWeek_bool[6] = !(DayOfWeek_bool[6]);
                if(DayOfWeek_bool[6].equals(true)){
                    Sun.setOpaque(true);
                    Sun.setBackground(Color.ORANGE);
                }
                else {
                    Sun.setOpaque(false);
                }
            }
        });

        Save_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 확인용
                System.out.println(goToDB());
                System.out.println(getTime());
                Save_btn.setText("생성되었습니다.");
                Save_btn.setEnabled(false);
            }
        });


        image = new ImageIcon("src\\GUI_ver1\\image\\login_background_1.jpg").getImage();
        JPanel background = new JPanel() {
            public void paintComponent(Graphics g) {
                Dimension d = getSize();
                g.drawImage(image, 0, 0, d.width, d.height, null);

                setOpaque(false); // 그림 투명도
                super.paintComponent(g);
            }
        };
        this.add(background);
    }
    public String getgoal(){ return  Goal_txt.getText(); }

    public String getperiod(){ return  Period_txt.getText(); }

    public String getTime(){ return  Comment_txt.getText(); }

    public String WeekOfDay(){
        String s ="";
        for (int i = 0; i < 7; i++) {
            if(DayOfWeek_bool[i]){
                s += ""+DayofWeek[i]+",";
            }
        }
        s = s.substring(0, s.length() - 1);
        return s;
    }

    public String goToDB(){
        return getgoal()+"%"+getperiod()+"%"+ getTime()+"%"+WeekOfDay();
    }
}

class panel8 extends JPanel {
    private JTextField Search_txt;
    private Vector<List_RoadMap> V_List = new Vector<List_RoadMap>();
    private int cnt=0;
    public panel8() {
        setLayout(null);

        JLabel RoadMap_Search = new JLabel("RoadMap Search");
        RoadMap_Search.setBounds(125, 40, 210, 50);
        RoadMap_Search.setForeground(Color.white);
        RoadMap_Search.setHorizontalAlignment(SwingConstants.CENTER);
        add(RoadMap_Search);

        Search_txt = new JTextField();
        Search_txt.setBounds(125, 90, 200, 20);

        add(Search_txt);
        Search_txt.setColumns(10);

        JButton search_btn = new JButton("찾기");
        search_btn.setBounds(340, 90, 55, 20);
        search_btn.setFont(new Font("굴림", Font.PLAIN, 10));
        search_btn.setForeground(Color.white);
        search_btn.setContentAreaFilled(false);
        add(search_btn);

        JPanel North = new JPanel();
        North.setBounds(55,110,340,20);
        North.setBackground(new Color(0,0,0,0));
        North.setLayout(null);
        add(North);

        JLabel goal = new JLabel("목표");
        goal.setHorizontalAlignment(SwingConstants.CENTER);
        goal.setBounds(0, 0, 65, 20);
        goal.setBorder(new LineBorder(Color.BLACK));
        North.add(goal);

        JLabel period = new JLabel("기간");
        period.setHorizontalAlignment(SwingConstants.CENTER);
        period.setBounds(65, 0, 65, 20);
        period.setBorder(new LineBorder(Color.BLACK));
        North.add(period);

        JLabel DayOfWeek = new JLabel("요일");
        DayOfWeek.setHorizontalAlignment(SwingConstants.CENTER);
        DayOfWeek.setBounds(130, 0, 150, 20);
        DayOfWeek.setBorder(new LineBorder(Color.BLACK));
        North.add(DayOfWeek);

        JLabel Join = new JLabel("참여");
        Join.setHorizontalAlignment(SwingConstants.CENTER);
        Join.setBounds(280, 0, 60, 20);
        Join.setBorder(new LineBorder(Color.BLACK));
        North.add(Join);

        JPanel Center = new JPanel();
        Center.setBounds(55,130,340,250);
        Center.setLayout(null);
        Center.setBackground(new Color(0,0,0,0));
        add(Center);


        JPanel South = new JPanel(new GridLayout(1,2));
        South.setBounds(55,420,340,25);
        South.setBackground(new Color(0,0,0,0));
        add(South);

        JButton left = new JButton("◀");
        South.add(left);
        left.setEnabled(false);

        // 만약 이 페이지를 채울 정도가 아니면 이것도 Enabled 시켜야 함
        // 로드맵 수는 벡터의 크기로 알 수 있을 듯 (V_List 벡터를 이용 => 지금은 무한으로 생성하게 했으나 이건 조정이 필요할 듯)
        JButton right = new JButton("▶");
        South.add(right);
//        if (V_List.size()<10){
//            right.setEnabled(false);
//        }



        for (int i = 0; i < 10; i++) {
            List_RoadMap new_List = new List_RoadMap(i+1+10*cnt);
            new_List.setBounds(0,i*25,340,25);
            new_List.setBackground(new Color(0,0,0,0));
            new_List.setBorder(new LineBorder(Color.BLACK));
            V_List.add(new_List);
            Center.add(V_List.elementAt(i));
        }

        left.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cnt--;
                System.out.println(cnt);
                Center.setVisible(false);
                Center.removeAll();
                for (int i = 0; i < 10; i++) {
                    List_RoadMap new_List = new List_RoadMap(i+1+10*cnt);
                    new_List.setBounds(0,i*25,340,25);
                    new_List.setBackground(new Color(0,0,0,0));
                    new_List.setBorder(new LineBorder(Color.BLACK));
                    V_List.add(new_List);
                    Center.add(V_List.elementAt(i+10*cnt));
                }
                Center.setVisible(true);
                if(cnt==0){
                    left.setEnabled(false);
                }
            }
        });

        right.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cnt++;
                System.out.println(cnt);
                Center.setVisible(false);
                Center.removeAll();
                for (int i = 0; i < 10; i++) {
                    List_RoadMap new_List = new List_RoadMap(i+1+10*cnt);
                    new_List.setBounds(0,i*25,340,25);
                    new_List.setBackground(new Color(0,0,0,0));
                    new_List.setBorder(new LineBorder(Color.BLACK));
                    V_List.add(new_List);
                    Center.add(V_List.elementAt(i+10*cnt));
                }
                Center.setVisible(true);
                if(cnt != 0)
                    left.setEnabled(true);
            }
        });



        // 배경화면 생성
        Image image = new ImageIcon("src\\GUI_ver2\\image\\background.jpeg").getImage();
        JPanel background = new JPanel() {
            public void paintComponent(Graphics g) {
                Dimension d = getSize();
                g.drawImage(image, 0, 0, d.width, d.height, null);

                setOpaque(false); // 그림 투명도
                super.paintComponent(g);
            }
        };
        background.setBounds(0,0,450, 530);
        add(background);

    }
}

public class page_4 extends JPanel{
    public page_4(){
        panel7 P_7 = new panel7();
        panel8 P_8 = new panel8();
        JPanel main = new JPanel();

        main.setLayout(new GridLayout(1,2));
        main.add(P_7);
        main.add(P_8);
        this.add(main);
    }
}
