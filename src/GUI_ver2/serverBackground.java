package GUI_ver2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class serverBackground {

    private ServerSocket serverSocket;
    private Socket socket;
    private serverGui gui;
    private String msg;

    /** XXX 03. 세번째 중요한것. 사용자들의 정보를 저장하는 맵입니다. */
    private Map<String, DataOutputStream> clientsMap = new HashMap<String, DataOutputStream>();

    public final void setGui(serverGui gui) {
        this.gui = gui;
    }

    public void setting() throws IOException {
        Collections.synchronizedMap(clientsMap); // 사용자 정보 Collections에 정리
        serverSocket = new ServerSocket(7777);  //서버 포트번호는 변경 가능 대충 안겹칠만한거 하기
        while (true) {
            /** XXX 01. 첫번째. 서버가 할일 분담. 계속 접속받는것. */
            System.out.println("서버 대기중...");
            socket = serverSocket.accept(); //계속 사용자를 받는다.
            System.out.println(socket.getInetAddress() + "에서 접속했습니다."); //접속한 사용자 IP주소
            serverBackground.Receiver receiver = new serverBackground.Receiver(socket);   //새로운 사용자 쓰레드 생성 후 소켓정보 넣기
            receiver.start();
        }
    }

    public static void main(String[] args) throws IOException {
        Network.server.ServerBackground serverBackground = new Network.server.ServerBackground(); //ServerBackground 객체 생성
        serverBackground.setting(); //ServerBackground 객체 세팅
    }

    // 맵의내용(클라이언트) 저장과 삭제
    public void addClient(String nick, DataOutputStream out) throws IOException {
        //System.out.println("Check2\n");
        clientsMap.put(nick, out);  //사용자명에 해당하는 정보 저장
        //sendMessage(nick + "님이 접속하셨습니다.\n");  //접속했다는 메세지 보냄

    }

    public void removeClient(String nick) {
        //sendMessage(nick + "님이 나가셨습니다.\n");   //나갔다는 메세지 보내기
        clientsMap.remove(nick);    //사용자명에 해당하는 정보 삭제
    }

    // 메시지 내용 전파
    public void sendMessage(String msg) {   //모든 사용자에게 보내는 것
        Iterator<String> it = clientsMap.keySet().iterator();   //it에 닉네임set 순차적으로 넣음
        String key = "";
        while (it.hasNext()) {  //it 끝까지
            key = it.next();    //key에 닉네임 넣어주기
            try {
                clientsMap.get(key).writeUTF(msg);  //닉네임에 해당하는 정보에 한글로 msg쓰기
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendOne(String nick, String msg) {
        try {
            clientsMap.get(nick).writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // -----------------------------------------------------------------------------
    class Receiver extends Thread {
        private DataInputStream in;
        private DataOutputStream out;
        private String nick;    //사용자명

        //로그인 파트?
        /** XXX 2. 리시버가 한일은 자기 혼자서 네트워크 처리 계속..듣기.. 처리해주는 것. */
        public Receiver(Socket socket) throws IOException {         //receiver 초기화
            out = new DataOutputStream(socket.getOutputStream());   //사용자에게 줄 값
            in = new DataInputStream(socket.getInputStream());      //사용자로부터 입력 받은 값
            nick = in.readUTF();
            addClient(nick, out);
            System.out.println("Receiver" + nick);
        }

        public void run() {     //사용자로부터 받을 내용
            try {// 계속 듣기만!!
                while (in != null) {
                    msg = in.readUTF();
                    System.out.println(msg);
                    String[] token = msg.split("#");
                    if("pwd".equals(token[0])) {         //아이디는 nick, 비밀번호는 pwd -> DB에서 비교 후 맞으면 check 뿌리기
                        sendOne(nick, "pwd#" + "check");     //사용자로부터 받은 내용을 사용자에게 뿌림, 단 문자열에 # 포함되면 안됨
                        gui.appendMsg(msg);              //사용자로부터 받은 내용을 서버 gui에 뿌림
                    }
                    else if("pwd1".equals(token[0])) {      //아이디는 nick, 비밀번호는 pwd1 -> DB에 저장하기
                        System.out.println("checkServer");
                        sendOne(nick, "pwd1#" + token[1]);
                        gui.appendMsg(msg);
                    }
                    else if("pwd2".equals(token[0])) {   //한명에게 보냄
                        System.out.println("checkServer");
                        sendOne(nick, "pwd2#" + token[1]);
                        gui.appendMsg(msg);
                    }
                }
            } catch (IOException e) {
                // 사용접속종료시 여기서 에러 발생. 그럼나간거에요.. 여기서 리무브 클라이언트 처리 해줍니다.
                removeClient(nick);
            }
        }
    }
}
