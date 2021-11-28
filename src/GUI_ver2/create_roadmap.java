package GUI_ver2;
import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;

public class create_roadmap extends JFrame {
    public create_roadmap(){                                 // 나만의 로드맵 생성 기능
        JPanel buttons = new JPanel();
        JButton cre_aim = new JButton("로드맵 단계 생성");     // 생성, 삭제, 완성 버튼 생성
        JButton cre_measure = new JButton("로드맵 단계 삭제");
        JButton cre_end = new JButton("완료");
        setTitle("create roadmap");
        setSize(900,600);
        this.setLayout(new BorderLayout());                     // 배치 관리자 이용
        add(buttons, BorderLayout.NORTH);
        buttons.add(cre_aim);
        buttons.add(cre_measure);
        buttons.add(cre_end);
        setVisible(true);
    }
}

