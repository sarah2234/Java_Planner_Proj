package GUI_ver2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Main extends JFrame{
    JPanel now = new JPanel();
    Container c = getContentPane();
    public Main(){
        // panel, btn 정의
        page_1 ex = new page_1();        // page1을 받아올 것이니 임시로 설정
        JPanel btn_panel = new JPanel(new GridLayout(1,2));   // 아래의 메인 패널
        JPanel left = new JPanel();       // 왼쪽 패널(설정, 종료 버튼)
        JPanel right = new JPanel();     // 오른쪽 패널(1,2,3,4 페이지로 이동 가능)
        JButton btn1 = new JButton("1 장");
        JButton btn2 = new JButton("2 장");
        JButton btn3 = new JButton("3 장");
        JButton btn4 = new JButton("4 장");
        JButton setting = new JButton("My page");
        JButton end = new JButton("종료");

        now = ex;                         // 현재 패널은 1장

        //배치하기
        c.setLayout(new BorderLayout());

        now.setLayout(new GridLayout());
        c.add(BorderLayout.CENTER, now);

        c.add(BorderLayout.SOUTH, btn_panel);
        btn_panel.add(left);
        btn_panel.add(right);

        left.add(setting);
        left.add(end);

        right.add(btn1);
        right.add(btn2);
        right.add(btn3);
        right.add(btn4);
        //프레임 설정
        setTitle("Stardust");
        setSize(900,600);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        btn1.setEnabled(false);
        //버튼 이벤트
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                now.removeAll();              // 현재 패널 삭제
                //버튼 활성화/비활성화 시키기
                btn1.setEnabled(false);
                btn2.setEnabled(true);
                btn3.setEnabled(true);
                btn4.setEnabled(true);
                //누른 버튼에 해당하는 페이지를 생성한다.
                now = new page_1();
                now.setLayout(new GridLayout());
                c.add(BorderLayout.CENTER, now);

                setVisible(true);
            }
        });

        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                now.removeAll();

                btn1.setEnabled(true);
                btn2.setEnabled(false);
                btn3.setEnabled(true);
                btn4.setEnabled(true);

                now = new page_2();
                now.setLayout(new GridLayout());
                c.add(BorderLayout.CENTER, now);
                setVisible(true);
            }
        });

        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                now.removeAll();

                btn1.setEnabled(true);
                btn2.setEnabled(true);
                btn3.setEnabled(false);
                btn4.setEnabled(true);

                now = new page_3();
                now.setLayout(new GridLayout());
                c.add(BorderLayout.CENTER, now);
                setVisible(true);

            }
        });

        btn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                now.removeAll();

                btn1.setEnabled(true);
                btn2.setEnabled(true);
                btn3.setEnabled(true);
                btn4.setEnabled(false);

                now = new page_4();
                now.setLayout(new GridLayout());
                c.add(BorderLayout.CENTER, now);
                setVisible(true);

            }
        });

        setting.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        end.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        new Main();
    }
}
