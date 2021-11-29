package GUI_ver2;
import GUI_ver2.create_roadmap;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.JOptionPane;
import java.awt.*;
import java.awt.event.*;

class panel7 extends JPanel{     // 7 페이지 panel 생성
    public panel7(){
        JButton new_roadmap = new JButton("로드맵 생성");
        JButton edit_roadmap = new JButton("로드맵 수정");
        JButton del_roadmap = new JButton("로드맵 삭제");

        this.setLayout(new GridLayout(3,1,20,20));
        this.add(new_roadmap);
        this.add(edit_roadmap);
        this.add(del_roadmap);

        new_roadmap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new create_roadmap();
            }
        });

        edit_roadmap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("구현해야합니다.");
            }
        });


        del_roadmap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean road = false; // 로드맵이 있는지 없는지 임시로 지정함, 후에 로드맵 유무를 DB에서 얻을 수 있으면 수정
                if(!road){
                    JOptionPane none = new JOptionPane();
                    none.showMessageDialog(null, "삭제할 수 있는 로드맵이 없습니다.");
                }
                else{
                    int result= JOptionPane.showConfirmDialog(null, "제거하시겠습니까?","roadmap 삭제", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                    if(result == 0){
                        System.out.println("삭제될겁니다.");
                    }
                }
            }
        });
    }
}

class panel8 extends JPanel{     // 8 페이지 panel 생성
    public panel8(){
        JLabel label = new JLabel("<html>이것은 8page입니다.<br/><br/>로드맵을 검색하고 선택할 수 있는 화면<br/>네트워크가 구현되어야 구현 가능</html>");
        add(label);
    }
}

public class page_4 extends JPanel{
    public page_4(){
        panel7 P_7 = new panel7();
        panel8 P_8 = new panel8();
        JPanel main = new JPanel();

        main.setLayout(new GridLayout(1,2));
        P_7.setBorder(new LineBorder(Color.RED,3)); // 패널 크기를 육안으로 확인하기 위해 경계선 그어줌
        P_8.setBorder(new LineBorder(Color.PINK,3)); // 위와 동일
        main.add(P_7);
        main.add(P_8);
        this.add(main);
    }
}
