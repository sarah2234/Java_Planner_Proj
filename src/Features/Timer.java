package Features;

import java.time.LocalDateTime;

public class Timer  extends  Thread {
    private String startTime; // 타이머 작동 시작 시간
    private long timerSec; // 걸리는 시간을 초 단위로 저장
    private boolean stop; // 일시정지 여부
    private boolean finished; // 완료 여부

    public Timer(String startTime, long timerSec) { // 시작 시각, 타이머 사용할 시간(?) 입력
        this.startTime = startTime;
        this.timerSec = timerSec;
        stop = false;
        finished = false;
    }

    public long getTimerSec() {
        return timerSec;
    } // 남은 시간 반환

    public void setStop(boolean b) {
        stop = b; // true일 때 카운트 멈추기
    }

    public boolean isStop() {
        return stop;
    } // 타이머가 멈춰있는지에 대해 반환

    public void setFinished(boolean b) {
        finished = b;
    } // true일 때 타미어 종료시키기

    public boolean isFinished() {
        return finished;
    } // 타이머가 종료되어 있는지에 대해 반환

    public void run() {
        long startHour = Integer.parseInt(startTime.substring(0, startTime.indexOf(":"))); // 시작 시각의 시 저장
        long startMinute = Integer.parseInt(startTime.substring(startTime.indexOf(":") + 1)); // 시작 시각의 분 저장

        LocalDateTime current;

        while(!finished) {// 타이머 끝나기 전까지 루프
            current = LocalDateTime.now();
            // 현재 시작 시간임
            if (current.getHour() >= startHour && current.getMinute() >= startMinute) {
                try {
                    Thread.sleep(1000);
                    if (!stop) { // 타이머가 멈춘 상태가 아니라면 남은 시간에서 1초를 빼기
                        timerSec -= 1;
                        System.out.println("Resume : " + timerSec); // 확인용 메세지
                        if (timerSec <= 0) {
                            finished = true; // 시간을 모두 소모하면 타이머 종료
                            System.out.println("Finished."); // 확인용 메세지
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

//    public static void main(String[] args) {
//        Timer timer = new Timer("01:00", "01:02"); // 1분 카운트 (예시)
//        timer.start(); // 카운트 시작
//        J j = new J(timer); // 예시
//    }
}

// 예시 클래스 (사용 방법은 keyAdapter의 keyPressed 메소드에 있음)
// 나중에 지울 것!
//class J extends JFrame {
//    private Timer timer;
//    private boolean stop = false;
//    public J(Timer timer) {
//        this.timer = timer;
//        setTitle("dfd");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 프로그램 종료 시 프로세스도 종료
//        Container container = getContentPane();
//
//        container.setLayout(new BorderLayout(5, 7)); // awt
//        container.addKeyListener(new KeyAdapter() {
//            @Override
//            public void keyPressed(KeyEvent e) {
//                if(!stop && e.getKeyChar() == 'p') {
//                    timer.setStop(true); // 타이머 멈추기
//                    System.out.println("Paused.");
//                    stop = true;
//                }
//                else if(stop && e.getKeyChar() == 'r') {
//                    timer.setStop(false); // 타이머 재개하기
//                    stop = false;
//                }
//                else if(e.getKeyChar() == 'q') {
//                    timer.setFinished(true);
//                    System.out.println("Quit.");
//                }
//                if(!timer.isAlive()) { // timer.isFinished()를 써도 됨
//                    System.out.println("Timer is finished.");
//                }
//            }
//        });
//
//        container.setFocusable(true);
//        container.requestFocus();
//        setSize(600, 300);
//        setVisible(true);
//    }
//}