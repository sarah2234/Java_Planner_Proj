package Features;

import java.awt.*;
import java.awt.event.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.logging.Logger;


// image 디렉터리를 'Mark Directory as Resource Root' 설정해놓기 (IntelliJ)

/*
시스템 트레이 : 우측 하단
트레이 메뉴 : '숨겨진 아이콘 표시' 클릭 시 나타나는 메뉴
Tooltip : 트레이 아이콘에 마우스를 올렸을 시 나타나는 설명(?)
트레이 메뉴 기능 : 트레이 아이콘을 우클릭 했을 시 나타나는 메뉴(트레이 메뉴에서 지원하지 않음)
풍선 알림 : 트레이 아이콘에서 좀 더 추가하여 알림을 띄워주는 기능
*/

public class TrayIconHandler {
    private TrayIcon trayIcon;
    private final Logger LOG = Logger.getGlobal(); // get global logger object with the name Logger.
    private ArrayList<Thread> alerts = new ArrayList<>(); // 알람 목록

    public TrayIconHandler() {
        registerTrayIcon(
                Toolkit.getDefaultToolkit().getImage("src\\GUI_ver2\\image\\login_background_1.jpg"), // 트레이 아이콘 이미지 설정
                "Stardust", // 툴팁(설명) 설정
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) { // 더블 클릭
                        // opening your application
                        System.exit(0); // 종료
                    }
                }
        );

        // 종료하는 메뉴 생성
        addMenuItem("Exit", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { // 마우스 우클릭
                System.exit(0);
            }
        });

        // 알람을 추가하는 메뉴 생성
//        addMenuItem("Add new alert", new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.out.println("Added new alert."); // 확인용 메세지
//                Thread alert = new Thread(new SystemTrayAlert());
//                alert.start(); // 알람 병행실행
//                alerts.add(alert);
//            }
//        });

        //Features.TrayIconHandler.displayMessage("Opensource Project", "This is detail.", TrayIcon.MessageType.NONE);
    }

    /*
    registerTrayIcon : 트레이 아이콘 등록 함수
    image : 시스템 트레이에 등록될 아이콘 이미지.
    toolTip : 아이콘에 마우스를 올렸을 때 보여질 메세지.
    action : 아이콘을 더블 클릭했을 때 실행 할 행동을 정의한 ActionListener.
    */
    private void registerTrayIcon(Image image, String tooltip, ActionListener action) {
        if (SystemTray.isSupported()) {
            if(trayIcon != null) trayIcon = null;
            trayIcon = new TrayIcon(image);
            trayIcon.setImageAutoSize(true);

            if (tooltip != null) trayIcon.setToolTip(tooltip);

            if (action != null) trayIcon.addActionListener(action);

            try {
                for (TrayIcon registeredTrayIcon : SystemTray.getSystemTray().getTrayIcons()) {
                    SystemTray.getSystemTray().remove(registeredTrayIcon);
                }
                SystemTray.getSystemTray().add(trayIcon);
            } catch (AWTException e) { // Signals that an Abstract Window Toolkit exception has occurred
                LOG.severe("Error occured when adding system tray.");
            }
        } else LOG.severe("System tray is not supported.");
    }

    /*
    getPopupMenu : 더블 클릭 시 메뉴 팝업 처리 함수
    */
    private PopupMenu getPopupMenu() {
        PopupMenu popupMenu = trayIcon.getPopupMenu(); // returns the popup menu associated with this TrayIcon

        if(popupMenu == null) {
            popupMenu = new PopupMenu();
        }
        return popupMenu;
    }


    /*
    isRegistered : 트레이 아이콘과 팝업 메뉴 등록 여부 반환 함수
    */
    public boolean isRegistered() {
        return (trayIcon != null && getPopupMenu() != null);
    }

    /*
    addMenuItem : 메뉴 아이템 추가하여 팝업 메뉴 수정하는 함수
    */
    public void addMenuItem(String label, ActionListener action) {
        if(!isRegistered()) return;
        MenuItem menuItem = new MenuItem(label);
        PopupMenu popupMenu = getPopupMenu(); // 메뉴 불러오기
        menuItem.addActionListener(action); // Adds the specified action listener to receive ActionEvents from this TrayIcon(action events occur when selecting the tray icon)
        popupMenu.add(menuItem); // add new menuItem
        trayIcon.setPopupMenu(popupMenu);
    }

    /*
    displayMessage : 알람 메세지 표시
     */
    public void displayMessage(String title, String detail, TrayIcon.MessageType messageType) {
        if(!isRegistered()) return;

        trayIcon.displayMessage(title, detail, messageType);
    }

    /*
    addAlert : 알람 추가
     */
    public void addAlert(String title, String detail, int year, int month, int day, int hour, int minute, TrayIcon.MessageType messageType) {
        if(!isRegistered()) return;

        System.out.println("Added new alert."); // 확인용 메세지
        Thread alert = new Thread(new SystemTrayAlert(title, detail, year, month, day, hour, minute, messageType));
        alert.start(); // 알람 병행실행
        alerts.add(alert);
    }

    // 시스템 트레이 알람 띄우는 클래스
    class SystemTrayAlert implements Runnable {
        private long time; // 입력한 시각과 현재 시각 차이 저장
        private String title;
        private String detail;
        private TrayIcon.MessageType messageType;

        // 입력한 시각과 현재 시각 차이 후에 알람 나타남
        public SystemTrayAlert(String title, String detail, int year, int month, int day, int hour, int minute, TrayIcon.MessageType messageType) {
            LocalDateTime currentTime = LocalDateTime.now();
            LocalDateTime deadlineTime = LocalDateTime.of(year, month, day, hour, minute);
            Duration duration = Duration.between(currentTime, deadlineTime);
            time = duration.getSeconds() * 1000;

            this.title = title;
            this.detail = detail;
            this.messageType = messageType;
        }

        // 테스트용: 5초 뒤 알람 나타남
        public SystemTrayAlert() {
            time = 5000;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(time);
                // 작업 수행
                work();
            } catch (InterruptedException e) { // 인터럽트가 발생하여 대기 중이던 상태가 예상보다 빨리 끝남
                e.printStackTrace();
            }
        }

        public void work() {
            System.out.println("alert"); // 확인용 메세지
            displayMessage(title, detail, messageType); // 알람 띄우기
        }
    }

    // 사용 방법
//    public static void main(String[] args) {
//        Features.TrayIconHandler.registerTrayIcon(
//                Toolkit.getDefaultToolkit().getImage("image/status-busy.png"), // 트레이 아이콘 이미지 설정
//                "Example", // 툴팁(설명) 설정
//                new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent e) { // 더블 클릭
//                        // opening your application
//                        System.exit(0); // 종료
//                    }
//                }
//        );
//
//        // 종료하는 메뉴 생성
//        Features.TrayIconHandler.addMenuItem("Exit", new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) { // 마우스 우클릭
//                System.exit(0);
//            }
//        });
//
//        // 알람을 추가하는 메뉴 생성
//        Features.TrayIconHandler.addMenuItem("Add new alert", new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.out.println("Added new alert."); // 확인용 메세지
//                Thread alert = new Thread(new SystemTrayAlert());
//                alert.start(); // 알람 병행실행
//                alerts.add(alert);
//            }
//        });
//
//        //Features.TrayIconHandler.displayMessage("Opensource Project", "This is detail.", TrayIcon.MessageType.NONE);
//    }
}



// 참고 : https://blog.silentsoft.org/archives/6
