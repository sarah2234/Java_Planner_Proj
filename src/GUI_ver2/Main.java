package GUI_ver2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Main extends JFrame {
    private JPanel now = new JPanel();
    private Container c = getContentPane();
    protected boolean loggedIn[] = new boolean[1];
    private JPanel page_1 = new page_1();
    private JPanel page_2 = new page_3();
    private JPanel page_3 = new page_4();
    protected JPanel login_gui = new Login_gui(loggedIn);
    outline outline;

    public Main(){
        c.setLayout(null);
        loggedIn[0] = false;

        // setBounds에서 width: 900으로 하면 오른쪽 패널의 오른쪽 경계선이 보이지 않음
        page_1.setLayout(new GridLayout());
        page_1.setBounds(0,0,885,520);
        page_1.setVisible(false);
        c.add(page_1);
        page_2.setLayout(new GridLayout());
        page_2.setBounds(0,0,885,520);
        page_2.setVisible(false);
        c.add(page_2);
        page_3.setLayout(new GridLayout());
        page_3.setBounds(0,0,885,520);
        page_3.setVisible(false);
        c.add(page_3);
        login_gui.setLayout(new GridLayout());
        login_gui.setBounds(0,0,885,520);
        login_gui.setVisible(true);
        c.add(login_gui);

        now = login_gui;

        outline = new outline();
        outline.setBounds(0,520,900,80);
        c.add(outline);


        //프레임 설정
        setTitle("Stardust");
        setSize(900,600);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

    class outline extends JPanel {
        private JPanel btn_panel;

        public outline() {
            JPanel btn_panel = new JPanel(new GridLayout(1,2));   // 아래의 메인 패널
            JPanel left = new JPanel();       // 왼쪽 패널(설정, 종료 버튼)
            JPanel right = new JPanel();     // 오른쪽 패널(1,2,3,4 페이지로 이동 가능)
            right.setLayout(new FlowLayout());
            JButton btn1 = new JButton("1 장");
            JButton btn2 = new JButton("2 장");
            JButton btn3 = new JButton("3 장");
            JButton setting = new JButton("My page");
            JButton end = new JButton("종료");

            this.add(btn_panel);

            btn_panel.add(left);
            btn_panel.add(right);

            left.add(setting);
            left.add(end);

            right.add(btn1);
            right.add(btn2);
            right.add(btn3);
            //프레임 설정

            setVisible(true);

            setting.setEnabled(false);

            //버튼 이벤트
            btn1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(loggedIn[0]) {
                        //버튼 활성화/비활성화 시키기
                        btn1.setEnabled(false);
                        btn2.setEnabled(true);
                        btn3.setEnabled(true);
                        setting.setEnabled(true);

                        //누른 버튼에 해당하는 페이지를 생성한다.
                        page_1.setVisible(true);
                        page_2.setVisible(false);
                        page_3.setVisible(false);
                        login_gui.setVisible(false);
                        now = page_1;
                    }

                    setVisible(true);
                }
            });

            btn2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(loggedIn[0]) {
                        btn1.setEnabled(true);
                        btn2.setEnabled(false);
                        btn3.setEnabled(true);
                        setting.setEnabled(true);

                        page_1.setVisible(false);
                        page_2.setVisible(true);
                        page_3.setVisible(false);
                        login_gui.setVisible(false);
                        now = page_2;
                    }

                    setVisible(true);
                }
            });

            btn3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(loggedIn[0]) {
                        btn1.setEnabled(true);
                        btn2.setEnabled(true);
                        btn3.setEnabled(false);
                        setting.setEnabled(true);

                        now = page_3;
                        page_1.setVisible(false);
                        page_2.setVisible(false);
                        page_3.setVisible(true);
                        login_gui.setVisible(false);
                    }

                    setVisible(true);

                }
            });


            setting.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(loggedIn[0]) {
                        btn1.setEnabled(true);
                        btn2.setEnabled(true);
                        btn3.setEnabled(true);
                        setting.setEnabled(false);

                        now = page_3;
                        page_1.setVisible(false);
                        page_2.setVisible(false);
                        page_3.setVisible(false);
                        login_gui.setVisible(true); // 임시로 login 페이지 띄우기
                    }

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
    }

    public static void main(String[] args) {
        new Main();
    }
}