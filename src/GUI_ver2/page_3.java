package GUI_ver2;

import GUI.Calendar_gui;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

class panel5 extends JPanel {     // 5 페이지 panel 생성
    panel5(){
        setLayout(new BorderLayout());
        add(BorderLayout.CENTER, new Cal_Inf());
        add(BorderLayout.NORTH, new Calendar_gui());
    }
}

class panel6 extends JPanel{     // 6 페이지 panel 생성
    panel6(){
        JLabel label = new JLabel("<html>이것은 6page입니다.<br/><br/>내가 선택한 로드맵을 띄우는 곳</html>");
        add(label);
    }
}

public class page_3 extends JPanel{
    public page_3(){
        panel5 P_5 = new panel5();
        panel6 P_6 = new panel6();
        JPanel main = new JPanel();

        main.setLayout(new GridLayout(1,2));
        P_5.setBorder(new LineBorder(Color.CYAN,3)); // 패널 크기를 육안으로 확인하기 위해 경계선 그어줌
        P_6.setBorder(new LineBorder(Color.BLUE,3)); // 위와 동일
        main.add(P_5);
        main.add(P_6);
        this.add(main);
    }
}
