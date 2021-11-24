package Network.server;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ServerGui extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;    //씨리얼 UID ? -> 뭐에 쓰는건지 찾아봐야함
    private JTextArea jta = new JTextArea(40, 25);  //text 공간, 원래 40, 25
    private JTextField jtf = new JTextField(25);    //text 크기?, 원래 25
    private ServerBackground server = new ServerBackground();   //ServerBackground클래스 객체 server 생성
    //setGui
    //setting
    //addClient
    //removeClient
    //sendMessage
    //sendOne -> 특정 사용자에게 보내는 것
    //class Receiver extends Thread
    //Receiver
    //run

    public ServerGui() throws IOException {     //어찌보면 생성자?

        add(jta, BorderLayout.CENTER);      //Container 선언이 안되어 있네, 그래도 그냥 이해하기, 컴포넌트 추가 ㅇㅇ
        add(jtf, BorderLayout.SOUTH);       //너도 마찬가지, 실직적 글자 들어가는 공간? SOUTH
        jtf.addActionListener(this);        //actionPerformed, 서버 채팅창에 적은 것 콘솔과 서버로 보내기

        setDefaultCloseOperation(EXIT_ON_CLOSE);    //x누르면 완전 종료
        setVisible(true);   //보이게 한 후
        setBounds(200, 100, 400, 600);  //200, 100, 400, 600
        setTitle("서버부분");

        server.setGui(this);    //ServerBackground클래스 객체 server의 ServerGui는 생성된 객체의 ServerGui
        server.setting();       //이전 창에서 정의한 setting 그대로 사용
    }

    public static void main(String[] args) throws IOException {
        new ServerGui();    //시작
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String msg = "서버 : " + jtf.getText() + "\n";
        System.out.print(msg);
        server.sendMessage(msg);
        jtf.setText("");
    }

    public void appendMsg(String msg) {
        jta.append(msg);
    }
}
