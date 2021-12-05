package GUI_ver2;

import org.openqa.selenium.interactions.Mouse;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
    private JLabel plans[] = new JLabel[7];
    private int cnt = 0;
    private Image image;

    public panel2(){
        this.setLayout(null);

        for(int i = 0; i < 7; i++) {
            plans[i] = new JLabel();
            plans[i].setBounds(67, 142 + 30 * i, 308, 22);
            plans[i].setVisible(true);
            plans[i].setForeground(Color.WHITE);
        }

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
            plans[i].setFont(new Font("맑은 고딕", Font.PLAIN, 12));
            plans[i].setBounds(67, 142 + 30 * i, 308, 22);
            content.add(plans[i]);
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
                if(cnt < 7) {
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

    class addingSchedule_gui extends JPanel {
        JLabel addingScheduleLable;
        JLabel timeLabel;
        JLabel addressLabel;
        JLabel commentLabel;
        JTextField addressTextField;
        JTextField commentTextField;
        JTextField timeTextField;
        JButton createButton;
        Image image;

        public addingSchedule_gui() {
            this.setLayout(null);
            //this.setBounds(73, 102, 292, 306);

            for(int i = 0; i < 7 ; i++) {
                plans[i].setVisible(false);
            }

            addingScheduleLable = new JLabel("새로운 일정 추가");
            addingScheduleLable.setForeground(Color.WHITE);
            addingScheduleLable.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
            addingScheduleLable.setHorizontalAlignment(SwingConstants.CENTER);
            addingScheduleLable.setBounds(41, 10, 207, 25);
            this.add(addingScheduleLable);

            timeLabel = new JLabel("시간");
            timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
            timeLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
            timeLabel.setForeground(Color.WHITE);
            timeLabel.setBounds(29, 74, 65, 25);
            timeLabel.setVisible(true);
            this.add(timeLabel);

            addressLabel = new JLabel("웹 주소");
            addressLabel.setHorizontalAlignment(SwingConstants.CENTER);
            addressLabel.setForeground(Color.WHITE);
            addressLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
            addressLabel.setBounds(29, 184, 65, 25);
            this.add(addressLabel);

            addressTextField = new JTextField();
            addressTextField.setBounds(132, 188, 116, 21);
            addressTextField.setBackground(Color.white);
            addressTextField.setForeground(Color.gray);
            addressTextField.setBorder(BorderFactory.createEmptyBorder(2 , 5 , 2 , 5));
            this.add(addressTextField);
            //addressTextField.setColumns(10);

            commentLabel = new JLabel("코멘트");
            commentLabel.setHorizontalAlignment(SwingConstants.CENTER);
            commentLabel.setForeground(Color.WHITE);
            commentLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
            commentLabel.setBounds(29, 129, 65, 25);
            this.add(commentLabel);

            commentTextField = new JTextField();
            //commentTextField.setColumns(10);
            commentTextField.setBounds(132, 133, 116, 21);
            commentTextField.setBackground(Color.white);
            commentTextField.setForeground(Color.gray);
            commentTextField.setBorder(BorderFactory.createEmptyBorder(2 , 5 , 2 , 5));
            this.add(commentTextField);

            timeTextField = new JTextField();
            //timeTextField.setColumns(10);
            timeTextField.setBounds(132, 78, 116, 21);
            timeTextField.setBackground(Color.white);
            timeTextField.setForeground(Color.gray);
            timeTextField.setBorder(BorderFactory.createEmptyBorder(2 , 5 , 2 , 5));
            this.add(timeTextField);

            Image btn_image = new ImageIcon("src\\GUI_ver2\\image\\예시3.png").getImage();
            Image into_btn_image = btn_image.getScaledInstance(76,31,Image.SCALE_SMOOTH);
            ImageIcon real_btn_image = new ImageIcon(into_btn_image);

            // 버튼에 마우스 대면 배경이 날아가는 오류!!
            createButton = new JButton(real_btn_image);
            createButton.setForeground(Color.WHITE);
            createButton.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
            createButton.setOpaque(false);
            createButton.setContentAreaFilled(false);
            createButton.setBorderPainted(false);
            createButton.setFocusPainted(false);
            createButton.setBounds(193, 252, 76, 31);
            createButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    plans[cnt].setText("* " + getTime() + " " + getComment());
                    plans[cnt].setFont(new Font("맑은 고딕", Font.PLAIN, 12));
                    for(JLabel plan_ : plans) {
                        plan_.setVisible(true);
                    }
                    setVisible(false);
                    removeAll();
                    drawPanel();
                    revalidate();
                    repaint();
                    cnt++;
                }
            });

            this.add(createButton);

            image = new ImageIcon("src\\GUI_ver2\\image\\rectangle.png").getImage();
            JPanel background = new JPanel() {
                public void paintComponent(Graphics g) {
                    Dimension d = getSize();
                    g.drawImage(image, 0, 0, 292, 306, null);
                }
            };
            background.setBounds(0,0,450, 530);
            this.add(background);
        }

        public String getComment() {
            return commentTextField.getText();
        }

        public String getTime() {
            return timeTextField.getText();
        }

        public String getAddress() {
            return addressTextField.getText();
        }
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
