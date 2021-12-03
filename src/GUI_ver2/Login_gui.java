package GUI_ver2;

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
    private boolean loggedIn[]; // 로그인 했는지 체크
    private JTextField id_field;
    private JTextField password_field;

    public panel10(boolean loggedIn[]) {
        this.loggedIn = loggedIn;

        // 로그인 창 생성
        this.setLayout(null);

        JButton login_button = new JButton("Login");
        login_button.setForeground(Color.white);
        login_button.setFont(new Font("SansSerif", Font.BOLD, 15));
        login_button.setBounds(68, 317, 84, 37);
        login_button.setOpaque(false);
        login_button.setContentAreaFilled(false);
        login_button.setBorderPainted(false);
        this.add(login_button);

        id_field = new JTextField();
        id_field.setBounds(91, 255, 241, 21);
        id_field.setBackground(Color.white);
        id_field.setForeground(Color.gray);
        id_field.setBorder(BorderFactory.createEmptyBorder(2 , 5 , 2 , 5));
        this.add(id_field);
        id_field.setColumns(10);

        password_field = new JTextField();
        password_field.setBounds(91, 286, 241, 21);
        password_field.setBackground(Color.white);
        password_field.setForeground(Color.gray);
        password_field.setBorder(BorderFactory.createEmptyBorder(2 , 5 , 2 , 5));
        this.add(password_field);
        password_field.setColumns(10);

        JLabel title = new JLabel("Log");
        new JLabel("<html>Login Page</html>");
        title.setFont(new Font("SansSerif", Font.BOLD, 30));
        title.setForeground(Color.white);
        title.setBounds(92, 122, 68, 87);
        this.add(title);

        JLabel lblIn = new JLabel("In");
        lblIn.setForeground(Color.WHITE);
        lblIn.setFont(new Font("SansSerif", Font.BOLD, 30));
        lblIn.setBounds(92, 158, 68, 87);
        this.add(lblIn);

        JButton signUp_button = new JButton("Sign Up");
        signUp_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        signUp_button.setOpaque(false);
        signUp_button.setForeground(Color.WHITE);
        signUp_button.setFont(new Font("SansSerif", Font.BOLD, 15));
        signUp_button.setContentAreaFilled(false);
        signUp_button.setBorderPainted(false);
        signUp_button.setBounds(68, 352, 92, 25);
        this.add(signUp_button);
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

        JButton signUp_button = new JButton("Sign Up");
        signUp_button.addActionListener(new ActionListener() {
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
                loggedIn[0] = true;
            }
        });
        signUp_button.setOpaque(false);
        signUp_button.setForeground(Color.WHITE);
        signUp_button.setFont(new Font("SansSerif", Font.BOLD, 15));
        signUp_button.setContentAreaFilled(false);
        signUp_button.setBorderPainted(false);
        signUp_button.setBounds(68, 348, 92, 37);
        this.add(signUp_button);

        id_field = new JTextField();
        id_field.setText("ID");
        id_field.setBounds(91, 255, 241, 21);
        id_field.setBackground(Color.white);
        id_field.setForeground(Color.gray);
        id_field.setBorder(BorderFactory.createEmptyBorder(2 , 5 , 2 , 5));
        this.add(id_field);
        id_field.setColumns(10);

        password_field = new JTextField();
        password_field.setText("Password");
        password_field.setBounds(91, 286, 241, 21);
        password_field.setBackground(Color.white);
        password_field.setForeground(Color.gray);
        password_field.setBorder(BorderFactory.createEmptyBorder(2 , 5 , 2 , 5));
        this.add(password_field);
        password_field.setColumns(10);

        JLabel title = new JLabel("Sign");
        new JLabel("<html>Login Page</html>");
        title.setFont(new Font("SansSerif", Font.BOLD, 30));
        title.setForeground(Color.white);
        title.setBounds(92, 122, 68, 87);
        this.add(title);

        JLabel lblIn = new JLabel("Up");
        lblIn.setForeground(Color.WHITE);
        lblIn.setFont(new Font("SansSerif", Font.BOLD, 30));
        lblIn.setBounds(92, 158, 68, 87);
        this.add(lblIn);

        JTextField txtCheckYourPassword = new JTextField();
        txtCheckYourPassword.setText("Check your password");
        txtCheckYourPassword.setColumns(10);
        txtCheckYourPassword.setBounds(91, 317, 241, 21);
        txtCheckYourPassword.setBackground(Color.white);
        txtCheckYourPassword.setForeground(Color.gray);
        txtCheckYourPassword.setBorder(BorderFactory.createEmptyBorder(2 , 5 , 2 , 5));
        this.add(txtCheckYourPassword);


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
        JLabel signUpTitle = new JLabel("<html>Sign Up</html>");
        signUpTitle.setFont(new Font("SansSerif", Font.BOLD, 30));
        signUpTitle.setBounds(100, 280, 100, 100);
        signUpTitle.setForeground(Color.white);
        signUpTitle.setLocation(20, 80);
        this.add(signUpTitle);


        this.setVisible(true);
    }
}

public class Login_gui extends JPanel
{
    public Login_gui(boolean loggedIn[]) {
        panel9 P_9 = new panel9();
        panel10 P_10 = new panel10(loggedIn);
        JPanel main = new JPanel();

        main.setLayout(new GridLayout(1, 2));
        main.add(P_9);
        main.add(P_10);
        this.add(main);
    }
}
