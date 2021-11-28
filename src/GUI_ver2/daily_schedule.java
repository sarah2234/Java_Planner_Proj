package GUI_ver2;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class daily_schedule extends JPanel {
    public daily_schedule(){
        // 함수로 만들어야 함
        //패널에 넣어야할 것들
        String[] measure = {"타이머","블로그","Github"};
        JPanel all = new JPanel();
        JPanel center = new JPanel();
        JPanel north = new JPanel(new GridLayout(1,2));
        JPanel north_left = new JPanel();
        JPanel north_right = new JPanel(new GridLayout(1,2));
        JButton btn1 = new JButton("수정");
        JButton btn2 = new JButton("삭제");
        JButton name = new JButton("달성해야할 목적 적기");
        JButton time = new JButton("시작시간 ~ 종료시간");
        JLabel how = new JLabel("측정 : ");
        JTextField txt = new JTextField(10);
        JButton search = new JButton("찾기");
        JButton timer = new JButton("타이머 넣기");
        JComboBox<String> how_to = new JComboBox<String>(measure);

        //패널 배치
        all.setLayout(new BorderLayout());

        north_left.add(name);
        north_left.setBorder(new LineBorder(Color.GREEN,3));
        north_right.add(btn1);
        north_right.add(btn2);
        north_right.setBorder(new LineBorder(Color.BLUE,3));
        north.add(north_left);
        north.add(north_right);
        north.setBorder(new LineBorder(Color.RED,3));

        center.add(time);
        center.add(how);
        center.add(how_to);
        center.add(timer);
        center.add(txt);
        center.add(search);
        timer.setVisible(true);
        txt.setVisible(false);
        search.setVisible(false);

        center.setBorder(new LineBorder(Color.ORANGE,3));
        all.add(BorderLayout.NORTH,north);
        all.add(BorderLayout.CENTER,center);
        all.setBorder(new LineBorder(Color.YELLOW,3));

        //프레임으로 패널 넣기
        this.add(all);

        how_to.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox) e.getSource();
                int index = cb.getSelectedIndex();
                switch (index){
                    case 0:
                        timer.setVisible(true);
                        txt.setVisible(false);
                        search.setVisible(false);
                        break;
                    case 1:
                    case 2:
                        timer.setVisible(false);
                        txt.setVisible(true);
                        search.setVisible(true);
                        break;
                }
            }
        });
    }

}
