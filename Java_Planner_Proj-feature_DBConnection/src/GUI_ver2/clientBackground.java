package GUI_ver2;

//import static GUI_ver2.Main.main;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class clientBackground {

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private Main Mgui;      //사용자 Gui
    private panel10 Lgui;
    private Calendar_gui Cgui;      //Calendar
    private panel2 Sgui;            //panel2
    private String msg;         //보낼 문자열 메세지
    private String nickName=null;    //사용자 이름(아이디 비번 받을 때 사용하면 좋을듯?), 맵 받을 때 엮버려도 되려나?

    public final void setMgui(Main Mgui) {   //객체의 gui 받는다.
        this.Mgui = Mgui;
    }

    public final void setLgui(panel10 Lgui) {
        this.Lgui = Lgui;
    }

    public final void setCgui(Calendar_gui Cgui) {    //panel2
        this.Cgui = Cgui;
    }

    public final void setSgui(panel2 Sgui) {    //panel2
        this.Sgui = Sgui;
    }

    public void connet() {      //server와 연결
        try {
            //socket host값은 자신의 컴퓨터 주소로 바꿔야 한다.
            socket = new Socket("180.64.51.37", 7777);  //180.64.51.37은 현재 serverPC의 공인 ip주소 값
            System.out.println("서버 연결됨.");

            out = new DataOutputStream(socket.getOutputStream());   //서버에게 줄 값
            in = new DataInputStream(socket.getInputStream());      //서버에게 받은 값

            // 접속하자마자 닉네임 전송하면. 서버가 이걸 닉네임으로 인식을 하고서 맵에 집어넣겠지요?
            out.writeUTF(nickName);     //연결된 서버에 nickName 보내기
            System.out.println("클라이언트 : 메시지 전송완료");
            while (in != null) {
                msg = in.readUTF(); //서버에게 받은 값을
                String[] token = msg.split("#");
                if("sch".equals(token[0])) {
                    System.out.println(msg);
                    Sgui.appendSchedule1(token[1]);
                }
                else if("sch2".equals(token[0])){
                    System.out.println(msg);
                    Cgui.appendSchedule2(msg);      //panel2 -> Calendar / -> Calendar
                }
                else if("pwd".equals(token[0])) {
                    System.out.println(msg);
                    Lgui.appendPwd(msg);
                }
                else if("pwd1".equals(token[0])){
                    System.out.println(msg);
                    Lgui.appendPwd1(msg); //사용자 gui에 출력
                }
                else if("pwd2".equals(token[0])){
                    System.out.println(msg);
                    Lgui.appendPwd2(msg);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {    //객체 생성 후 server와 connet한다.
        clientBackground clientBackground = new clientBackground();
        clientBackground.connet();
    }

//    public void sendId(String nickName) {
//        try {
//            out.writeUTF("id#" + nickName);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public void sendPwd(String pwd) {
        try {
            out.writeUTF("pwd#" + pwd);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendPwd1(String pwd1) {
        try {
            out.writeUTF("pwd1#" + pwd1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendPwd2(String pwd2) {
        try {
            out.writeUTF("pwd2#" + pwd2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendSchedule(String schedule) {
        try {
            out.writeUTF("sch#" + schedule);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public void sendMessage(String msg2) {  //모두에게 전해줄 내용
//        try {
//            out.writeUTF("all#" + msg2); //서버로 msg2 보냄(sendMessage는 앞에 all 붙여주기)
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

//    public void sendOne(String msg2) {     //나에게만 변할 내용
//        try {
//            out.writeUTF("one#" + msg2);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public void setNickname(String nickName) {
        this.nickName = nickName;
    }   //객체의 닉네임을 받는다. private이므로
}
