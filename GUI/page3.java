package GUI;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

class panel5 extends JPanel {     // 5 페이지 panel 생성
    panel5(){
        JLabel label = new JLabel("이것은 5page입니다.");
        JTextField txt = new JTextField(10);
        add(label);
        add(txt);
    }
}

class panel6 extends JPanel{     // 6 페이지 panel 생성
    panel6(){
        JLabel label = new JLabel("이것은 6page입니다.");
        JTextField txt = new JTextField(10);
        add(label);
        add(txt);
    }
}

public class page3 extends JFrame{     // 세번째 화면 프레임 생성
    public page3(Dimension dim){      // 화면 크기를 유지하기 위해서 화면 크기를 인자로 받는다
        panel5 fifth = new panel5();
        panel6 sixth = new panel6();
        JPanel split = new JPanel();
        JPanel btn = new JPanel();
        JButton jb_nxt = new JButton("다음");
        JButton jb_bf =  new JButton("이전");

        Container c = getContentPane();
        c.setLayout(new BorderLayout());                      // 컨테이너는 Border 배치 관리자를 이용한다.
        split.setLayout(new GridLayout(1,2));       // 중앙에 들어갈 패널은 Grid 배치 관리자를 이용한다.

        fifth.setBorder(new LineBorder(Color.CYAN,3)); // 패널 크기를 육안으로 확인하기 위해 경계선 그어줌
        split.add(fifth);
        sixth.setBorder(new LineBorder(Color.BLUE,3)); // 위와 동일
        split.add(sixth);
        btn.add(jb_bf, BorderLayout.EAST);
        btn.add(jb_nxt, BorderLayout.WEST);

        add(split, BorderLayout.CENTER);                        // split 패널은 중앙에 배치
        add(btn, BorderLayout.SOUTH);                          // btn 패널은 남쪽에 위치

        setSize(dim);                                           // 프레임 사이즈를 받은 크기로 설정한다
        setLocationRelativeTo(null);                            // 화면이 중앙으로 오게 함
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jb_bf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Dimension dim = new Dimension(getWidth(), getHeight());   // 프레임의 크기와 높이 리턴
                new page2(dim);
                setVisible(false);
            }
        });

        jb_nxt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Dimension dim = new Dimension(getWidth(), getHeight());
                new page4(dim);
                setVisible(false);
            }
        });
    }
}
