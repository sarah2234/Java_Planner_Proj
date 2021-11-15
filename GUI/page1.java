package GUI;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class panel1 extends JPanel{   // 1 페이지 panel 생성
    public panel1(){
        JLabel label = new JLabel("이것은 1page입니다.");
        JTextField txt = new JTextField(10);
        add(label);
        add(txt);
    }
}

class panel2 extends JPanel{    // 2 페이지 panel 생성
    public panel2(){
        JLabel label = new JLabel("이것은 2page입니다.");
        JTextField txt = new JTextField(10);
        add(label);
        add(txt);
    }
}

public class page1 extends JFrame{     // 첫번째 화면 프레임 생성
    public page1(Dimension dim){      // 화면 크기를 유지하기 위해서 화면 크기를 인자로 받는다
        panel1 first = new panel1();
        panel2 second = new panel2();
        JPanel split = new JPanel();
        JPanel btn_1 = new JPanel();
        JButton jb_nxt = new JButton("다음");
        JButton jb_end = new JButton("종료");

        Container c = getContentPane();
        c.setLayout(new BorderLayout());                      // 컨테이너는 Border 배치 관리자를 이용한다.
        split.setLayout(new GridLayout(1,2));       // 중앙에 들어갈 패널은 Grid 배치 관리자를 이용한다.

        first.setBorder(new LineBorder(Color.GREEN,3)); // 패널 크기를 육안으로 확인하기 위해 경계선 그어줌
        split.add(first, BorderLayout.EAST);
        second.setBorder(new LineBorder(Color.YELLOW,3)); // first와 동일
        split.add(second, BorderLayout.WEST);

        c.add(split, BorderLayout.CENTER);                        // split 패널은 중앙에 배치

        btn_1.add(jb_end, BorderLayout.EAST);
        btn_1.add(jb_nxt, BorderLayout.WEST);
        c.add(btn_1, BorderLayout.SOUTH);                        // btn_1 패널은 남쪽에 위치

        setSize(dim);                                           // 프레임 사이즈를 받은 크기로 설정한다
        setLocationRelativeTo(null);                            // 화면이 중앙으로 오게 함
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jb_nxt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Dimension dim = new Dimension(getWidth(), getHeight());   // 프레임의 크기와 높이 리턴
                new page2(dim);                                           // page2를 보여주고 크기값을 넘겨줌
                setVisible(false);                                        // 화면은 사라지게 한다.
            }
        });

        jb_end.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}
