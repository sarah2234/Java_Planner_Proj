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
    public panel1(){
        Point one = new Point(76,196);           // 양자리 별 좌표(선을 그어줄 좌표)
        Point two = new Point(198,128);
        Point three = new Point(271,150);
        Point four = new Point(290,174);
//        //실험
//        int k = (int) one.getX()-3;
//        Point[] arr = new Point[5];
//        Point zero_M = new Point((int) one.getX()-3, (int) one.getY()-2);

        v.add(one);
        v.add(two);
        v.add(three);
        v.add(four);

        Point one_M = new Point(73,194);           // 별과 잇는 선에 맞춰서 별 좌표를 새로 입력해줌
        Point two_M = new Point(195,126);
        Point three_M = new Point(268,148);
        Point four_M = new Point(287,172);
        v_M.add(one_M);
        v_M.add(two_M);
        v_M.add(three_M);
        v_M.add(four_M);
        this.setBackground(Color.BLACK);
        this.setLayout(null);

        for(int i=0; i<4; i++){                     // 별 만들어주기
            JLabel label = new JLabel("*");
            label.setForeground(Color.red);
            label.setLocation(v_M.get(i));
            label.setSize(10,10);
            add(label);
        }
        // 별빛 구현
        int num = (int) (Math.random()*100+15);
        for(int i=0;i<num;i++){
            JLabel la = new JLabel("*");
            la.setForeground(Color.YELLOW);
            la.setSize(5,5);
            la.setLocation((int) (Math.random()*450),(int) (Math.random()*600));
            add(la);
        }
    }

    public void paintComponent(Graphics g) {    // 양자리 별을 이어주는 선 그어주기
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        for(int i=0; i<v.size()-1; i++) {
            Point s = v.elementAt(i);
            Point e = v.elementAt(i+1);
            g.drawLine((int)s.getX(), (int)s.getY(), (int)e.getX(), (int)e.getY());
        }
    }
}

class panel2 extends JPanel{    // 2 페이지 panel 생성
    private Vector<daily_schedule> V_S = new Vector<daily_schedule>();
    private int cnt = 0;
    public panel2(){
        JPanel all = new JPanel(new BorderLayout());
        JPanel center = new JPanel(new FlowLayout());
        JPanel south_btn = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton s_btn = new JButton("추가");
        JLabel sch = new JLabel("추가 버튼을 눌러 일정을 추가하세요.");

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
                daily_schedule new_one = new daily_schedule();
                V_S.add(new_one);
                center.add(V_S.elementAt(cnt));
                cnt++;
                center.setVisible(true);
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
