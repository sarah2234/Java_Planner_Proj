package GUI_ver2;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

class panel7 extends JPanel{     // 7 페이지 panel 생성
    //어떤 요일이 true면 그 요일에 로드맵을 하겠다는 의미
    private Boolean[] DayOfWeek_bool = {false,false,false,false,false,false,false};
    private JTextField Goal_txt = new JTextField();
    private JTextField Period_txt = new JTextField();
    private JTextField Time_txt = new JTextField();
    private JTextField HowTo_txt = new JTextField();
    public panel7(){
        String[] measure = {"타이머","블로그","Github"};
        JComboBox<String> how_to = new JComboBox<String>(measure); // 측정방법 콤보박스

        //배치하기
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
        Save_btn.setBounds(265, 495, 120, 20);
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
        ////// 여기까지 요일에 관한 버튼 이벤트

        // 작성한 모든 값을 받아오고 DB에 연결해줘야 하는 코드를 작성해야함.
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
    // 후에 값을 받아오는 함수를 미리 몇개 작성
    public String getgoal(){ return  Goal_txt.getText(); }

    public String getperiod(){ return  Period_txt.getText(); }

    public String getTime(){ return  Time_txt.getText(); }
}

class panel8 extends JPanel{     // 8 페이지 panel 생성
    private JTextField Search_txt;
    private int roadmap_num=3;
    private Vector<List_RoadMap> V_List = new Vector<List_RoadMap>();   //받아온 로드맵 값을 넣고, 출력하게 하는 벡터
    private int cnt=0;
    public panel8(){
        //배치 시작
        setLayout(null);

        Search_txt = new JTextField();
        Search_txt.setBounds(40, 60, 265, 20);
        add(Search_txt);
        Search_txt.setColumns(10);

        JButton search_btn = new JButton("찾기");
        search_btn.setBounds(330, 60, 95, 20);
        add(search_btn);

        JLabel RoadMap_Search = new JLabel("RoadMap Search");
        RoadMap_Search.setBounds(40, 10, 265, 30);
        add(RoadMap_Search);

        JPanel All = new JPanel();
        All.setBounds(0, 90, 450, 500);
        add(All);
        All.setLayout(null);

        JPanel North = new JPanel();
        North.setBounds(0, 0, 450, 45);
        All.add(North);
        North.setLayout(null);

        JLabel goal = new JLabel("목표");
        goal.setHorizontalAlignment(SwingConstants.CENTER);
        goal.setBounds(0, 0, 75, 45);
        North.add(goal);

        JLabel period = new JLabel("기간");
        period.setHorizontalAlignment(SwingConstants.CENTER);
        period.setBounds(75, 0, 75, 45);
        North.add(period);

        JLabel time = new JLabel("시간");
        time.setHorizontalAlignment(SwingConstants.CENTER);
        time.setBounds(150, 0, 75, 45);
        North.add(time);

        JLabel DayOfWeek = new JLabel("요일");
        DayOfWeek.setHorizontalAlignment(SwingConstants.CENTER);
        DayOfWeek.setBounds(225, 0, 75, 45);
        North.add(DayOfWeek);

        JLabel HowTo = new JLabel("측정방법");
        HowTo.setHorizontalAlignment(SwingConstants.CENTER);
        HowTo.setBounds(300, 0, 75, 45);
        North.add(HowTo);

        JLabel Join = new JLabel("참여");
        Join.setHorizontalAlignment(SwingConstants.CENTER);
        Join.setBounds(375, 0, 80, 45);
        North.add(Join);

        //중앙 패널에 Vector를 이용해서 값을 출력해줄 것이다.
        JPanel Center = new JPanel();
        Center.setBounds(0, 45, 450, 330);
        All.add(Center);
        Center.setLayout(null);

        // 다음으로 넘어가는 버튼, 이전으로 넘어가는 버튼 2개 생성해서 넣을 패널
        JPanel South = new JPanel();
        South.setBounds(0, 375, 450, 60);
        All.add(South);
        South.setLayout(null);

        // 처음에는 이전 페이지가 없으니 비활성화, cnt변수를 이용해서 cnt=0이면 비활성화, 아니면 활성화
        JButton left = new JButton("◀");
        left.setBounds(5, 5, 50, 50);
        South.add(left);
        left.setEnabled(false);

        JButton right = new JButton("▶");
        right.setBounds(380, 5, 50, 50);
        South.add(right);

        All.setBorder(new LineBorder(Color.BLUE));

        for (int i = 0; i < 11; i++) {
            List_RoadMap new_List = new List_RoadMap(i+1+11*cnt);
            new_List.setBounds(0,i*30,450,30);
            new_List.setBorder(new LineBorder(Color.BLACK));
            V_List.add(new_List);
            Center.add(V_List.elementAt(i));
        }

        //버튼 이벤트(페이지 넘기기)
        left.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cnt--;
                Center.setVisible(false);
                Center.removeAll();
                for (int i = 0; i < 11; i++) {
                    List_RoadMap new_List = new List_RoadMap(i+1+11*cnt);
                    new_List.setBounds(0,i*30,450,30);
                    new_List.setBorder(new LineBorder(Color.BLACK));
                    V_List.add(new_List);
                    Center.add(V_List.elementAt(i+11*cnt));
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
                Center.setVisible(false);
                Center.removeAll();
                for (int i = 0; i < 11; i++) {
                    List_RoadMap new_List = new List_RoadMap(i+1+11*cnt);
                    new_List.setBounds(0,i*30,450,30);
                    new_List.setBorder(new LineBorder(Color.BLACK));
                    V_List.add(new_List);
                    Center.add(V_List.elementAt(i+11*cnt));
                }
                Center.setVisible(true);
                if(cnt != 0)
                    left.setEnabled(true);
            }
        });
    }
}




public class page_4 extends JPanel{
    public page_4(){
        panel7 P_7 = new panel7();
        panel8 P_8 = new panel8();
        JPanel main = new JPanel();

        main.setLayout(new GridLayout(1,2));
        P_7.setBorder(new LineBorder(Color.RED,3)); // 패널 크기를 육안으로 확인하기 위해 경계선 그어줌
        P_8.setBorder(new LineBorder(Color.PINK,3)); // 위와 동일
        main.add(P_7);
        main.add(P_8);
        this.add(main);
    }
}
