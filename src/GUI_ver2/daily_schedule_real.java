package GUI_ver2;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class daily_schedule_real extends JPanel {
    public daily_schedule_real(){
        // 함수로 만들어야 함
        //패널에 넣어야할 것들
        String[] measure = {"타이머","블로그","Github"};
        JPanel all = new JPanel();
        JPanel center = new JPanel();
        JPanel center_left = new JPanel();
        JPanel center_right = new JPanel();
        JPanel north = new JPanel(new GridLayout(1,2));
        JPanel north_left = new JPanel();
        JPanel north_right = new JPanel(new GridLayout(1,2));
        JButton edit = new JButton("수정");
        JButton enter = new JButton("확인");
        JButton del = new JButton("삭제");
        JLabel name = new JLabel("달성해야 할 목적 적기");
        JLabel time = new JLabel("시간");
        JLabel how = new JLabel("측정 : ");
        JButton timer = new JButton("타이머 넣기");
        JComboBox<String> how_to = new JComboBox<String>(measure);

        //패널 배치
        all.setLayout(new BorderLayout());

        north_left.add(name);
        north_left.setBorder(new LineBorder(Color.GREEN,3));
        north_right.add(edit);
        north_right.add(del);
        north_right.setBorder(new LineBorder(Color.BLUE,3));
        north.add(north_left);
        north.add(north_right);
        north.setBorder(new LineBorder(Color.RED,3));

        center.add(time);
        time.setPreferredSize(new Dimension(100,30));
        time.setBorder(new LineBorder(Color.CYAN,3));
        time.setHorizontalAlignment(JLabel.CENTER);
        center.add(time);
        center.add(how);
        center.add(how_to);
        center.add(timer);
        center.add(center_left);
        center.add(center_right);
        timer.setVisible(true);

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
                        break;
                    case 1:
                    case 2:
                        timer.setVisible(false);
                        break;
                }
            }
        });

        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new daily_schedule_edit();
            }
        });

        del.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                // DB에 삭제할 것을 저장 후, 사용자가 프로그램을 종료 시 DB에서 완전 삭제
                setVisible(false);
            }
        });
    }

}
