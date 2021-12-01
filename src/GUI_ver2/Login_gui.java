package GUI_ver2;

import org.checkerframework.checker.units.qual.A;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

class panel9 extends JPanel {
    private Image image; // 배경이미지

    public panel9() {
        setLayout(new BorderLayout());
        image = new ImageIcon("src\\GUI_ver2\\image\\login_background_1.jpg").getImage();
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
}

class panel10 extends JPanel {
    private Image image; // 배경이미지
    private JPanel signUp;

    public panel10() {
        // 로그인 창 생성
        this.setLayout(null);
        JPanel content = new JPanel();
        content.setLayout(null);
        content.setSize(300, 400);
        content.setBorder(BorderFactory.createEmptyBorder(10 , 10 , 10 , 10));
        content.setBounds(75, 60, 300, 400);
        content.setBackground(Color.pink);
        content.setOpaque(false);
        this.add(content);

        // 타이틀 생성
        JLabel title = new JLabel("<html>Login Page</html>");
        title.setFont(new Font("SansSerif", Font.BOLD, 30));
        title.setBounds(100, 280, 100, 100);
        title.setForeground(Color.white);
        title.setLocation(20, 80);
        content.add(title);

        JTextField id_field = new JTextField(20);
        id_field.setBackground(Color.white);
        id_field.setForeground(Color.gray);
        id_field.setBorder(BorderFactory.createEmptyBorder(2 , 5 , 2 , 5));
        id_field.setBounds(20, 180, 200, 20);
        content.add(id_field);

        JTextField pwd_field = new JTextField(20);
        pwd_field.setBackground(Color.white);
        pwd_field.setForeground(Color.gray);
        pwd_field.setBorder(BorderFactory.createEmptyBorder(2 , 5 , 2 , 5));
        pwd_field.setBounds(20, 220, 200, 20);
        content.add(pwd_field);

        JButton login_button = new JButton("Login");
        login_button.setForeground(Color.white);
        login_button.setFont(new Font("SansSerif", Font.BOLD, 15));
        login_button.setBounds(-10, 270, 100, 20);
        login_button.setOpaque(false);
        login_button.setContentAreaFilled(false);
        login_button.setBorderPainted(false);
        content.add(login_button);

        JButton signUp_button = new JButton("Sign Up");
        signUp_button.setForeground(Color.white);
        signUp_button.setFont(new Font("SansSerif", Font.BOLD, 15));
        signUp_button.setBounds(-4, 290, 100, 20);
        signUp_button.setOpaque(false);
        signUp_button.setContentAreaFilled(false);
        signUp_button.setBorderPainted(false);
        content.add(signUp_button);

        signUp_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                removeAll();
                revalidate();
                repaint();
                setSignUp();
            }
        });

        // 배경화면 생성
        image = new ImageIcon("src\\GUI_ver2\\image\\login_background_2.jpg").getImage();
        JPanel background = new JPanel() {
            public void paintComponent(Graphics g) {
                Dimension d = getSize();
                g.drawImage(image, 0, 0, d.width, d.height, null);

                setOpaque(false); // 그림 투명도
                super.paintComponent(g);
            }
        };
        background.setBounds(0,0,450, 530);
        this.add(background);
    }

    public void setSignUp() {
        // 로그인 창 생성
        this.setLayout(null);
        JPanel content = new JPanel();
        content.setLayout(null);
        content.setSize(300, 400);
        content.setBorder(BorderFactory.createEmptyBorder(10 , 10 , 10 , 10));
        content.setBounds(75, 60, 300, 400);
        content.setBackground(Color.pink);
        content.setOpaque(false);
        this.add(content);

        // 배경화면 생성
        image = new ImageIcon("src\\GUI_ver2\\image\\login_background_2.jpg").getImage();
        JPanel background = new JPanel() {
            public void paintComponent(Graphics g) {
                Dimension d = getSize();
                g.drawImage(image, 0, 0, d.width, d.height, null);

                setOpaque(false); // 그림 투명도
                super.paintComponent(g);
            }
        };
        background.setBounds(0,0,450, 530);
        this.add(background);

        // 타이틀 생성
        JLabel title = new JLabel("<html>Sign Up</html>");
        title.setFont(new Font("SansSerif", Font.BOLD, 30));
        title.setBounds(100, 280, 100, 100);
        title.setForeground(Color.white);
        title.setLocation(20, 80);
        content.add(title);

        JTextField id_field = new JTextField("ID", 20);
        id_field.setBackground(Color.white);
        id_field.setForeground(Color.gray);
        id_field.setBorder(BorderFactory.createEmptyBorder(2 , 5 , 2 , 5));
        id_field.setBounds(20, 180, 200, 20);
        content.add(id_field);

        JTextField pwd_field = new JTextField("Password",20);
        pwd_field.setBackground(Color.white);
        pwd_field.setForeground(Color.gray);
        pwd_field.setBorder(BorderFactory.createEmptyBorder(2 , 5 , 2 , 5));
        pwd_field.setBounds(20, 230, 200, 20);
        content.add(pwd_field);

        JTextField pwd2_field = new JTextField("Check your password",20);
        pwd2_field.setBackground(Color.white);
        pwd2_field.setForeground(Color.gray);
        pwd2_field.setBorder(BorderFactory.createEmptyBorder(2 , 5 , 2 , 5));
        pwd2_field.setBounds(20, 260, 200, 20);
        content.add(pwd2_field);

        JButton signUp_button = new JButton("Sign Up");
        signUp_button.setForeground(Color.white);
        signUp_button.setFont(new Font("SansSerif", Font.BOLD, 15));
        signUp_button.setBounds(-4, 300, 100, 20);
        signUp_button.setOpaque(false);
        signUp_button.setContentAreaFilled(false);
        signUp_button.setBorderPainted(false);
        content.add(signUp_button);

        signUp_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeAll();
                image = new ImageIcon("src\\GUI_ver2\\image\\login_background_success.jpg").getImage();
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
                revalidate();
                repaint();
            }
        });

        this.setVisible(true);
    }
}

public class Login_gui extends JPanel
{
    public Login_gui() {
        panel9 P_9 = new panel9();
        panel10 P_10 = new panel10();
        JPanel main = new JPanel();

        main.setLayout(new GridLayout(1, 2));
        main.add(P_9);
        main.add(P_10);
        this.add(main);
    }
}
