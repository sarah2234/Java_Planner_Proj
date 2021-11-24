package Network.client;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;


public class ClientGui extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JTextArea jta = new JTextArea(40, 25);
    private JTextField jtf = new JTextField(25);
    private ClientBackground client = new ClientBackground();
    //setGui
    //connet
    //sendMessage
    //setNickname

    private static String nickName;

    public ClientGui() {
        setTitle("클라이언트");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(jta, BorderLayout.CENTER);  //채팅이 남아있는 공간
        add(jtf, BorderLayout.SOUTH);   //채팅을 치는 공간
        jtf.addActionListener(this);  //채팅 받으면 actionPerformed 실행
        //추가
        JButton btn = new JButton("Accept");
        add(btn, BorderLayout.NORTH);
        btn.addActionListener(new MyActionListener());
        //추가
        setVisible(true);
        setBounds(800, 100, 400, 600);


        client.setGui(this);    //gui에 값 넣기
        client.setNickname(nickName);   //nickName 설정
        client.connet();    //서버와 연결
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("당신의 닉네임부터 설정하세요 : ");
        nickName = scanner.nextLine();  //닉네임 설정
        scanner.close();

        new ClientGui();    //실행
    }

    @Override
    // 말치면 보내는 부분
    public void actionPerformed(ActionEvent e) {    //채팅을 치는 공간에 있는 내용을 서버로 보냄
        String msg = nickName + ":" + jtf.getText() + "\n";
        client.sendMessage(msg);
        jtf.setText("");
    }

    //버튼 누르면 반응하는 부분
    class MyActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String msg;
            JButton b = (JButton)e.getSource();
            if(b.getText().equals("Accept")) {
                msg = nickName + ":" + b.getText() + "\n";
                client.sendOne(msg);
                b.setText("Cancel");

            }
            else {
                msg = nickName + ":" + b.getText() + "\n";
                client.sendOne(msg);
                b.setText("Accept");
            }
        }
    }

    public void appendMsg(String msg) { //채팅창에 받은 문자열 표시됨
        jta.append(msg);
    }

}
