package GUI;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

class panel7 extends JPanel{     // 7 페이지 panel 생성
    public panel7(){
        JLabel label = new JLabel("이것은 7page입니다.");
        JTextField txt = new JTextField(10);
        add(label);
        add(txt);
    }
}

class panel8 extends JPanel{     // 8 페이지 panel 생성
    public panel8(){
        JLabel label = new JLabel("이것은 8page입니다.");
        JTextField txt = new JTextField(10);
        add(label);
        add(txt);
    }
}

public class page4 extends JFrame{     // 네번째 화면 프레임 생성
    public page4(Dimension dim){      // 화면 크기를 유지하기 위해서 화면 크기를 인자로 받는다
        panel7 seventh = new panel7();
        panel8 eighth = new panel8();
        JPanel split = new JPanel();
        JPanel btn = new JPanel();
        JButton jb_nxt = new JButton("처음으로");
        JButton jb_bf =  new JButton("이전");

        Container c = getContentPane();
        c.setLayout(new BorderLayout());                      // 컨테이너는 Border 배치 관리자를 이용한다.
        split.setLayout(new GridLayout(1,2));       // 중앙에 들어갈 패널은 Grid 배치 관리자를 이용한다.

        seventh.setBorder(new LineBorder(Color.RED,3)); // 패널 크기를 육안으로 확인하기 위해 경계선 그어줌
        split.add(seventh);
        eighth.setBorder(new LineBorder(Color.PINK,3)); // 위와 동일
        split.add(eighth);
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
                new page3(dim);
                setVisible(false);
            }
        });

        jb_nxt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Dimension dim = new Dimension(getWidth(), getHeight());
                new page1(dim);
                setVisible(false);
            }
        });
    }
}
