package GUI_ver2;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

class panel1 extends JPanel{   // 1 페이지 panel 생성
    private Vector<Point> v_M = new Vector<Point>();
    private Vector<Point> v = new Vector<Point>();
    int count=0;
    public panel1(){
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

                setBackground(Color.BLACK);
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
        JPanel btnpan = new JPanel();                          // 현재 버튼을 누르면 별자리를 이어주는 버튼을 임시로 생성함.
        JButton btn = new JButton("누르면 선 증가");
        btnpan.add(btn);
        btnpan.setBorder(new LineBorder(Color.ORANGE));

        setLayout(new BorderLayout());
        add(BorderLayout.CENTER,star_light);
        add(BorderLayout.SOUTH, btnpan);

        // 별빛 구현
        int num = (int) (Math.random()*100+15);
        for(int i=0;i<num;i++){
            JLabel la = new JLabel("*");
            la.setForeground(Color.YELLOW);
            la.setSize(5,5);
            la.setLocation((int) (Math.random()*600),(int) (Math.random()*600));
            star_light.add(la);
        }

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                star_light.setVisible(false);
                if(count<3){
                    count++;
                }
                star_light.setVisible(true);
            }
        });
    }
}

class panel2 extends JPanel{    // 2 페이지 panel 생성
    private Vector<daily_schedule_real> V_S = new Vector<daily_schedule_real>();
    private int cnt = 0;
    public panel2(){
        JPanel all = new JPanel(new BorderLayout());
        JPanel center = new JPanel(new FlowLayout());
        JPanel south_btn = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton s_btn = new JButton("추가");
        JButton txt = new JButton("완료 시 나올 화면");
        JLabel sch = new JLabel("추가 버튼을 눌러 일정을 추가하세요.");

        south_btn.add(txt);
        south_btn.add(s_btn);
        south_btn.setBorder(new LineBorder(Color.RED,3));
        center.add(sch);
        center.setBorder(new LineBorder(Color.BLUE,3));
        setLayout(new BorderLayout());
        all.add(BorderLayout.CENTER, center);
        all.add(BorderLayout.SOUTH, south_btn);
        this.add(all);

        s_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cnt == 0){
                    sch.setVisible(false);
                }
                center.setVisible(false);
                daily_schedule_real new_one = new daily_schedule_real();
                V_S.add(new_one);
                center.add(V_S.elementAt(cnt));
                cnt++;
                center.setVisible(true);
            }
        });

        txt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new explain_star();
            }
        });
    }
}

public class page_1 extends JPanel{
    public page_1(){
        panel1 P_1 = new panel1();
        panel2 P_2 = new panel2();
        JPanel main = new JPanel();

        main.setLayout(new GridLayout(1,2));
        main.add(P_1);
        P_2.setBorder(new LineBorder(Color.YELLOW,3));
        main.add(P_2);
        this.add(main);
    }
}
