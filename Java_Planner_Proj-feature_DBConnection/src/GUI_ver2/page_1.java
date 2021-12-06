package GUI_ver2;

import Features.*;
import Features.Timer;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import static GUI_ver2.Main.client;

class panel1 extends JPanel{   // 1 페이지 panel 생성
    private Vector<Point> v_M = new Vector<Point>();
    private Vector<Point> v = new Vector<Point>();
    private Image image;        // 배경 이미지
    private Image starProcedure[] = new Image[7]; // 진행도를 별자리로 나타내기...........
    private JButton star[] = new JButton[7]; // 별자리 그림
    int count = 0;
    private int[] progress; // 일정 진행률

    public panel1(int[] progress){
        this.progress = progress;

        drawPanel();
    }

    public void drawPanel() {
        class panel1_StarLight extends JPanel{ // 별자리 찍는 패널
            panel1_StarLight(){
                Point one = new Point(76,196);           // 양자리 별 좌표(선을 그어줄 좌표)
                Point two = new Point(198,128);
                Point three = new Point(271,150);
                Point four = new Point(290,174);

                v.add(one);
                v.add(two);
                v.add(three);
                v.add(four);

                Point one_M = new Point((int)one.getX()-3,(int) one.getY()-2);           // 별과 잇는 선에 맞춰서 별 좌표를 새로 입력해줌
                Point two_M = new Point((int)two.getX()-3,(int) two.getY()-2);
                Point three_M = new Point((int)three.getX()-3,(int) three.getY()-2);
                Point four_M = new Point((int)four.getX()-3,(int) four.getY()-2);
                v_M.add(one_M);
                v_M.add(two_M);
                v_M.add(three_M);
                v_M.add(four_M);

                //setBackground(Color.BLACK);
                setLayout(null);

                for(int i=0; i<4; i++){                     // 별 만들어주기
                    JLabel label = new JLabel("*");
                    label.setForeground(Color.red);
                    label.setLocation(v_M.get(i));
                    label.setSize(10,10);
                    add(label);
                }
            }

            public void paintComponent(Graphics g) {    // 양자리 별을 이어주는 선 그어주기
                super.paintComponent(g);
                g.setColor(Color.WHITE);
                for(int i=0; i<count; i++) {
                    Point s = v.elementAt(i);
                    Point e = v.elementAt(i+1);
                    g.drawLine((int)s.getX(), (int)s.getY(), (int)e.getX(), (int)e.getY());
                }
            }
        }

        panel1_StarLight star_light = new panel1_StarLight();
//        JPanel btnpan = new JPanel();                          // 현재 버튼을 누르면 별자리를 이어주는 버튼을 임시로 생성함.
//        JButton btn = new JButton("누르면 선 증가");
//        btnpan.add(btn);
//        btnpan.setBorder(new LineBorder(Color.ORANGE));

        setLayout(null);
        //add(BorderLayout.SOUTH, btnpan);

//        // 별빛 구현
//        int num = (int) (Math.random()*100+15);
//        for(int i=0;i<num;i++){
//            JLabel la = new JLabel("*");
//            la.setForeground(Color.YELLOW);
//            la.setSize(5,5);
//            la.setLocation((int) (Math.random()*600),(int) (Math.random()*600));
//            star_light.add(la);
//        }

//        btn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                star_light.setVisible(false);
//                if(count<3){
//                    count++;
//                }
//                star_light.setVisible(true);
//            }
//        });

        // 천칭자리 그리기
        for(int i = 0; i < 7; i++) {
            //사진크기를 조정 -> 함수로 생성해야할 필요 있음(사진을 버튼 크기에 맞추려면)
            String path = "src\\GUI_ver2\\image\\천칭자리_" + i + ".png";
            starProcedure[i] = new ImageIcon(path).getImage();
            starProcedure[i] = starProcedure[i].getScaledInstance(450, 520, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(starProcedure[i]);
            star[i] = new JButton(icon);
            star[i].setBounds(100, 0, 330,400);
            star[i].setContentAreaFilled(false);
            star[i].setBorderPainted(false);
            star[i].setFocusPainted(false);
            if(progress[0] >= i) {
                star[i].setVisible(true);
            }
            else {
                star[i].setVisible(false);
            }
            star[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    progress[0]++;
                    removeAll();
                    drawPanel();
                    revalidate();
                    repaint();
                }
            });
            this.add(star[i]);
        }

        // 배경화면 생성
        image = new ImageIcon("src\\GUI_ver2\\image\\login_background_1.jpg").getImage();
        JPanel background = new JPanel() {
            public void paintComponent(Graphics g) {
                Dimension d = getSize();
                g.drawImage(image, 0, 0, d.width, d.height, null);

                setOpaque(false); // 그림 투명도
                super.paintComponent(g);
            }
        };
        background.setBounds(0,0,450, 520);
        this.add(background);
    }
}

class panel2 extends JPanel{    // 2 페이지 panel 생성
    private TrayIconHandler trayIcon[];
    int[] progress = new int[1]; // 진행률
    private JLabel plans[] = new JLabel[7];     //일일 계획
    private JButton checklist[] = new JButton[7]; // 일정 완성했는지에 대한 여부
    private boolean done[] = new boolean[7]; // 일정 완성했는지에 대한 여부
    private boolean usingCrawler[] = new boolean[7]; // 각 일정이 웹크롤링을 사용하는지에 대한 여부
    private String urls[] = new String[7]; // url 받아오기
    private int planCnt = 0; // 일정 개수
    private Image image;        // 배경 이미지
    private Vector<Timer> timers;       // 일정에 타이머 선택 시 사용 => 배열로 해도 되겠지만 임시로 벡터 사용함
    private boolean stop = false; // 일정 멈춤

    private String startHour; // 타이머 시작하는 시간
    private String endHour; // 타이머 끝나는 시간

    private Vector<String> Time_H = new Vector<String>();
    private Vector<String> Time_M = new Vector<String>();
    int index_S_H;      //Start Hour
    int index_S_M;      //Start Minute
    int index_E_H;      //End Hour
    int index_E_M;      //End Minute

    private String schedule;     //서버로부터 받은 내용 저장

    public panel2(TrayIconHandler trayIcon[]){
        this.setLayout(null);

        timers = new Vector<>();

        for(int i = 0; i < 7; i++) {        //각각 일정당 여부들 초기화
            plans[i] = new JLabel();
            done[i] = false;
            usingCrawler[i] = false;
            urls[i] = "";
        }

        this.trayIcon = trayIcon;
        client.setSgui(this);
        drawPanel();


    }

    private void drawPanel() {
        JPanel content = new JPanel();
        content.setBounds(0, 0, 450, 520);
        this.add(content);
        content.setLayout(null);

        for(int i = 0; i < 7; i++) {
            String newPlan = plans[i].getText();
            plans[i] = new JLabel(newPlan);
            plans[i].setForeground(Color.WHITE);
            plans[i].setFont(new Font("맑은 고딕", Font.PLAIN, 14));
            plans[i].setBounds(100, 142 + 40 * i, 308, 22);
            content.add(plans[i]);

            if(done[i]) { // 해당 일정 끝났다고 표시하기
                //사진크기를 조정 -> 함수로 생성해야할 필요 있음(사진을 버튼 크기에 맞추려면)
                ImageIcon check = new ImageIcon("src\\GUI_ver2\\image\\check.png");
                Image check_img = check.getImage();
                check_img = check_img.getScaledInstance(14, 14, Image.SCALE_SMOOTH);
                ImageIcon check_icon = new ImageIcon(check_img);    //조절한 check image 넣기
                checklist[i] = new JButton(check_icon);
                if(planCnt > i ) { // 계획 개수만큼 허용
                    checklist[i].setVisible(true);
                }
                int finalI = i;
                checklist[i].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        done[finalI] = !done[finalI]; // toggle => 일정 아직 완료되지 않음으로 표시
                        progress[0]++;
                        removeAll();
                        drawPanel();
                        revalidate();
                        repaint();
                    }
                });
            }
            else { // 해당 일정 아직 안 끝냈다고 표시하기
                //사진크기를 조정 -> 함수로 생성해야할 필요 있음(사진을 버튼 크기에 맞추려면)
                ImageIcon noCheck = new ImageIcon("src\\GUI_ver2\\image\\noCheck.png");
                Image noCheck_img = noCheck.getImage();
                noCheck_img = noCheck_img.getScaledInstance(14, 14, Image.SCALE_SMOOTH);
                ImageIcon noCheck_icon = new ImageIcon(noCheck_img);
                checklist[i] = new JButton(noCheck_icon);
                checklist[i].setEnabled(false);
                if(planCnt > i ) { // 계획 개수만큼 허용
                    checklist[i].setVisible(true);
                    checklist[i].setEnabled(true);
                }
                int finalI = i;
                checklist[i].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(timers.get(finalI).isFinished()) {
                            if(usingCrawler[finalI]) { // 웹크롤링을 사용해야 함
                                WebCrawling webCrawling = new WebCrawling(urls[finalI]); // 해당 주소로 웹크롤링
                                webCrawling.checkPost();
                                if(webCrawling.isPosted()) {
                                    trayIcon[0].displayMessage("Stardust", "확인되었습니다!", TrayIcon.MessageType.INFO);
                                }
                                else {
                                    trayIcon[0].displayMessage("Stardust", "확인할 수 없습니다.", TrayIcon.MessageType.ERROR);
                                    return;
                                }
                            }
                            done[finalI] = !done[finalI]; // toggle => 일정 완료로 표시
                            progress[0]++;
                            removeAll();
                            drawPanel();
                            revalidate();
                            repaint();
                        }
                    }
                });
            }

            checklist[i].setBounds(67,148 + 40 * i, 14, 14);
            checklist[i].setContentAreaFilled(false);
            checklist[i].setBorderPainted(false);
            content.add(checklist[i]);
        }

        JLabel scheduleTitle = new JLabel("추가 버튼을 눌러 일정을 추가하세요.");
        scheduleTitle.setForeground(Color.WHITE);
        scheduleTitle.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
        scheduleTitle.setBounds(125, 43, 210, 49);
        content.add(scheduleTitle);

        JButton addButton = new JButton("추가");
        addButton.setBounds(320, 425, 76, 31);
        content.add(addButton);
        addButton.setForeground(Color.WHITE);
        addButton.setContentAreaFilled(false);
        addButton.setBorderPainted(false);
        addButton.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(planCnt < 7) {
                    removeAll();
                    addingSchedule_gui addingSchedule = new addingSchedule_gui();
                    addingSchedule.setBounds(85, 102, 292, 306);
                    addingSchedule.setVisible(true);
                    add(addingSchedule);
                    drawPanel();
                    revalidate();
                    repaint();
                }
            }
        });

        JButton timerButton = new JButton();
        if(!stop) {
            timerButton.setText("일정 멈추기");
        }
        else if(timers.size() >= 1) {
            timerButton.setText("일정 재개");
        }
        timerButton.setBounds(280, 80, 100, 31);
        content.add(timerButton);
        timerButton.setForeground(Color.WHITE);
        timerButton.setContentAreaFilled(false);
        timerButton.setBorderPainted(false);
        timerButton.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
        timerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        timerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!stop) {
                    stop = true;
                    for (Timer timer : timers) {
                        timer.setStop(true);
                        removeAll();
                        drawPanel();
                        revalidate();
                        repaint();
                    }
                }
                else {
                    stop = false;
                    for(Timer timer : timers) {
                        timer.setStop(false);
                        removeAll();
                        drawPanel();
                        revalidate();
                        repaint();
                    }
                }
            }
        });

        // 배경화면 생성
        image = new ImageIcon("src\\GUI_ver2\\image\\background.jpeg").getImage();
        JPanel background = new JPanel() {
            public void paintComponent(Graphics g) {
                Dimension d = getSize();
                g.drawImage(image, 0, 0, d.width, d.height, null);

                setOpaque(false); // 그림 투명도
                super.paintComponent(g);
            }
        };
        background.setBounds(0,0,450, 530);
        content.add(background);
    }

    public class addingSchedule_gui extends JPanel {
        JTextField goal_txt;
        JTextField comment_txt;
        JTextField URL;
        JComboBox HowTo_combo;
        JComboBox Time_H_S;
        JComboBox Time_M_S;
        JComboBox Time_H_E;
        JComboBox Time_M_E;
        JButton createButton;
        Image image;
        Image backgroundOpaque;

        public addingSchedule_gui() {
            this.setLayout(null);
            //this.setBounds(73, 102, 292, 306);

            for(int i = 0; i < 7 ; i++) {
                plans[i].setVisible(false);
            }

            //여기서 부터 새로 작성한 곳
            //시간 설정
            for (int i = 0; i < 24; i++) {
                Time_H.add(String.valueOf(i));
            }
            for (int i = 0; i < 60; i++) {
                Time_M.add(String.valueOf(i));
            }

            String[] measure = {"타이머","블로그","Github"};

            JLabel Goal = new JLabel("목표");
            Goal.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
            Goal.setHorizontalAlignment(SwingConstants.CENTER);
            Goal.setForeground(Color.white);
            Goal.setBounds(19, 30, 65, 20);
            this.add(Goal);

            JLabel Time = new JLabel("시간");
            Time.setHorizontalAlignment(SwingConstants.CENTER);
            Time.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
            Time.setForeground(Color.white);
            Time.setBounds(19, 80, 65, 20);
            this.add(Time);

            JLabel Comment = new JLabel("코멘트");
            Comment.setHorizontalAlignment(SwingConstants.CENTER);
            Comment.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
            Comment.setForeground(Color.white);
            Comment.setBounds(19, 130, 65, 20);
            this.add(Comment);

            JLabel HowTo = new JLabel("측정방법");
            HowTo.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
            HowTo.setForeground(Color.WHITE);
            HowTo.setBounds(19, 180, 65, 20);
            this.add(HowTo);

            goal_txt = new JTextField();
            goal_txt.setBounds(78, 30, 180, 20);
            goal_txt.setBackground(Color.white);
            goal_txt.setForeground(Color.gray);
            goal_txt.setBorder(BorderFactory.createEmptyBorder(2 , 5 , 2 , 5));
            this.add(goal_txt);

            comment_txt = new JTextField();
            comment_txt.setBackground(Color.white);
            comment_txt.setForeground(Color.gray);
            comment_txt.setBorder(BorderFactory.createEmptyBorder(2 , 5 , 2 , 5));
            comment_txt.setBounds(78, 130, 180, 20);
            this.add(comment_txt);

            HowTo_combo = new JComboBox(measure);
            HowTo_combo.setBackground(Color.white);
            HowTo_combo.setBounds(78, 180, 83, 20);
            this.add(HowTo_combo);

            Time_H_S = new JComboBox(Time_H);
            Time_H_S.setBackground(Color.white);
            Time_H_S.setBounds(78, 80, 40, 20);
            this.add(Time_H_S);

            Time_M_S = new JComboBox(Time_M);
            Time_M_S.setBackground(Color.white);
            Time_M_S.setBounds(128, 80, 40, 20);
            this.add(Time_M_S);

            Time_H_E = new JComboBox(Time_H);
            Time_H_E.setBackground(Color.white);
            Time_H_E.setBounds(178, 80, 40, 20);
            this.add(Time_H_E);

            Time_M_E = new JComboBox(Time_M);
            Time_M_E.setBackground(Color.white);
            Time_M_E.setBounds(228, 80, 40, 20);
            this.add(Time_M_E);

            JLabel comma = new JLabel(":");
            comma.setBounds(121, 80, 10, 20);
            this.add(comma);

            JLabel comma_1 = new JLabel(":");
            comma_1.setBounds(221, 80, 10, 20);
            this.add(comma_1);

            JLabel by = new JLabel("~");
            by.setBounds(169, 80, 10, 20);
            this.add(by);

            URL = new JTextField();
            URL.setBounds(78, 215, 180, 20);
            this.add(URL);
            URL.setBackground(Color.white);
            URL.setForeground(Color.gray);
            URL.setBorder(BorderFactory.createEmptyBorder(2 , 5 , 2 , 5));
            URL.setVisible(false);

            Time_H_S.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JComboBox cb = (JComboBox) e.getSource();
                    index_S_H = cb.getSelectedIndex();

                    System.out.println(index_S_H);
                }
            });

            Time_M_S.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JComboBox cb = (JComboBox) e.getSource();
                    index_S_M = cb.getSelectedIndex();

                    System.out.println(index_S_M);
                }
            });

            Time_H_E.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JComboBox cb = (JComboBox) e.getSource();
                    index_E_H = cb.getSelectedIndex();

                    System.out.println(index_E_H);
                }
            });

            Time_M_E.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JComboBox cb = (JComboBox) e.getSource();
                    index_E_M = cb.getSelectedIndex();

                    System.out.println(index_E_M);
                }
            });

            HowTo_combo.addActionListener(new ActionListener() {    //측정방법
                @Override
                public void actionPerformed(ActionEvent e) {
                    JComboBox cb = (JComboBox) e.getSource();
                    int index = cb.getSelectedIndex();
                    switch (index){
                        case 0:     //타이머
                            URL.setVisible(false);
                            break;
                        case 1:     //블로그
                        case 2:     //Github
                            URL.setVisible(true);
                            break;
                    }
                }
            });

            createButton = new JButton("생성");
            createButton.setForeground(Color.WHITE);
            createButton.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
            createButton.setOpaque(false);
            createButton.setContentAreaFilled(false);
            createButton.setBorderPainted(false);
            createButton.setFocusPainted(false);
            createButton.setBounds(193, 252, 76, 31);
            createButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(index_S_H<=index_E_H){
                        LocalDate now = LocalDate.now();        //서버에 오늘 날짜도 보내줘야함
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                        String Now = now.format(formatter);

                        String msg = Now + "%" + getGoal() + "%" + Time_H_S.getSelectedIndex() + ":" + Time_M_S.getSelectedIndex() + "~" +  //%토큰으로 나눔
                                Time_H_E.getSelectedIndex() + ":" + Time_M_E.getSelectedIndex() + "%" + getComment() + "%" +
                                HowTo_combo.getSelectedItem().toString() + "%" + getURL();

                        client.sendSchedule(msg);

                        plans[planCnt].setText("* [" + Time_H_S.getSelectedIndex() + ":" + Time_M_S.getSelectedIndex() + " ~ " +
                                Time_H_E.getSelectedIndex() + ":" + Time_M_E.getSelectedIndex() + "] " +
                                getGoal() + " - " + getComment());
                        plans[planCnt].setFont(new Font("맑은 고딕", Font.PLAIN, 14));

                        checklist[planCnt].setVisible(true); // 체크 박스 표시
                        checklist[planCnt].setEnabled(true);

                        urls[planCnt] = URL.getText();

                        for(JLabel plan_ : plans) {
                            plan_.setVisible(true);
                        }

                        addFeatures();

                        setVisible(false);
                        removeAll();
                        drawPanel();
                        revalidate();
                        repaint();
                        planCnt++;
                    }
                    else{
                        JOptionPane none = new JOptionPane();
                        none.showMessageDialog(null, "시간 조정을 제대로 해야합니다.");
                    }
                }
            });

            this.add(createButton);

            image = new ImageIcon("src\\GUI_ver2\\image\\rectangle.png").getImage();

            JPanel rectangle = new JPanel() {
                public void paintComponent(Graphics g) {
                    Dimension d = getSize();
                    g.drawImage(image, 0, 0, 292, 306, null);
                }
            };
            rectangle.setBounds(0,0,450, 530);
            this.add(rectangle);

            backgroundOpaque = new ImageIcon("src\\GUI_ver2\\image\\background_opaque.jpg").getImage();
            JPanel background = new JPanel() {
                public void paintComponent(Graphics g) {
                    Dimension d = getSize();
                    // 73, 102, 292, 306
                    g.drawImage(backgroundOpaque, 0, 0, d.width, d.height, null);
                }
            };
            background.setBounds(0,0,450, 530);
            this.add(background);
        }

        public String getComment() {
            return comment_txt.getText();
        }

        public String getGoal() {
            return goal_txt.getText();
        }

        public String getURL() {
            if(URL.getText() == null)
                return null;
            return URL.getText();
        }

        public void addFeatures() {
            /*
             타이머 추가
             */
            String startTime = Time_H_S.getSelectedIndex() + ":" + Time_M_S.getSelectedIndex();
            String endTime = Time_H_E.getSelectedIndex() + ":" + Time_M_E.getSelectedIndex();
            long timerSec = ((Time_H_E.getSelectedIndex() - Time_H_S.getSelectedIndex()) * 60 +
                    (Time_M_E.getSelectedIndex() - Time_M_S.getSelectedIndex())) * 60; // 걸리는 시간을 초 단위로 저장

            Timer timer = new Timer(startTime, timerSec);
            timer.start();
            timers.add(timer);

            /*
            tray alert 추가
            */
            LocalDateTime currentTime = LocalDateTime.now();
            // 지금보다 뒤의 시간으로 세팅하면 오늘 중 알람
            if(currentTime.getHour() <= Time_H_S.getSelectedIndex() && currentTime.getMinute() < Time_M_S.getSelectedIndex()) {
                trayIcon[0].addAlert(getGoal(), getComment(),
                        currentTime.getYear(), currentTime.getMonthValue(), currentTime.getDayOfMonth(),
                        Time_H_S.getSelectedIndex(), Time_M_S.getSelectedIndex(),
                        TrayIcon.MessageType.NONE);
            }
            // 지금보다 앞의 시간으로 세팅하면 내일 중 알람
            else {
                trayIcon[0].addAlert(getGoal(), getComment(),
                        currentTime.getYear(), currentTime.getMonthValue(), currentTime.getDayOfMonth() + 1,
                        Time_H_S.getSelectedIndex(), Time_M_S.getSelectedIndex(), TrayIcon.MessageType.NONE);
            }

            /*
             웹크롤링 추가
            */
            if(HowTo_combo.getSelectedIndex() != 0) {
                usingCrawler[planCnt] = true;
            }
        }
    }
    public void appendSchedule1(String msg) {
        drawPanel();

        System.out.println(msg);
        String[][] Sche = new String[7][6];
        String[] Token = msg.split("@");
        for(int i=0; i<1; i++) {

            String[] token = Token[i].split("%");
            for(int j=0; j<6; j++) {
                Sche[i][j] = token[j];
            }
            this.plans[planCnt].setText("* [" + token[1] + " ~ " +
                    token[2] + "] " +
                    token[0] + " - " + token[4]);
            this.plans[planCnt].setFont(new Font("맑은 고딕", Font.PLAIN, 14));

            this.checklist[planCnt].setVisible(true); // 체크 박스 표시
            this.checklist[planCnt].setEnabled(true);

            this.urls[planCnt] = null;       //url도 받아와야 된다.

            for(JLabel plan_ : this.plans) {
                plan_.setVisible(true);
            }

            String[] Stoken = token[1].split(":");
            String[] Etoken = token[2].split(":");
            int[] StartToken = new int[2];
            int[] EndToken = new int[2];
            for(int k=0; k<2; k++){
                StartToken[k] = Integer.parseInt(Stoken[k]);
            }
            for(int k=0; k<2; k++){
                EndToken[k] = Integer.parseInt(Etoken[k]);
            }
            /*
             타이머 추가
             */
            String startTime = token[1];
            String endTime = token[2];
            long timerSec = ((EndToken[0] - StartToken[0]) * 60 +
                    (EndToken[1] - StartToken[1])) * 60; // 걸리는 시간을 초 단위로 저장

            Timer timer = new Timer(startTime, timerSec);
            timer.start();
            this.timers.add(timer);

            /*
            tray alert 추가
            */
            LocalDateTime currentTime = LocalDateTime.now();
            // 지금보다 뒤의 시간으로 세팅하면 오늘 중 알람
            if(currentTime.getHour() <= StartToken[0] && currentTime.getMinute() < StartToken[1]) {
                this.trayIcon[0].addAlert(token[0], token[4],
                        currentTime.getYear(), currentTime.getMonthValue(), currentTime.getDayOfMonth(),
                        StartToken[0], StartToken[1],
                        TrayIcon.MessageType.NONE);
            }
            // 지금보다 앞의 시간으로 세팅하면 내일 중 알람
            else {
                this.trayIcon[0].addAlert(token[0], token[4],
                        currentTime.getYear(), currentTime.getMonthValue(), currentTime.getDayOfMonth() + 1,
                        StartToken[0], StartToken[1], TrayIcon.MessageType.NONE);
            }

            /*
             웹크롤링 추가
            */
            if(token[5] != "타이머") {
                this.usingCrawler[planCnt] = true;
            }

            //setVisible(false);
            removeAll();
            drawPanel();
            revalidate();
            repaint();
            this.planCnt++;
        }

    }
}


public class page_1 extends JPanel{
    public page_1(TrayIconHandler trayIcon[]){
        panel2 P_2 = new panel2(trayIcon);
        panel1 P_1 = new panel1(P_2.progress);
        JPanel main = new JPanel();

        main.setLayout(new GridLayout(1,2));
        main.add(P_1);
        P_2.setBorder(new LineBorder(Color.YELLOW,3));
        main.add(P_2);
        this.add(main);
    }
}
