package GUI_ver2;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

// Jframe으로 변환해야 함.
public class daily_schedule_edit extends JFrame implements ActionListener{
    private JTextField name = new JTextField(15);
    JButton enter = new JButton("확인");
    private Vector<String> Time_H = new Vector<String>();
    private Vector<String> Time_M = new Vector<String>();
    int index_S_H;
    int index_S_M;
    int index_E_H;
    int index_E_M;
    public daily_schedule_edit(){
        for (int i = 0; i < 24; i++) {
            Time_H.add(String.valueOf(i));
        }
        for (int i = 0; i < 60; i++) {
            Time_M.add(String.valueOf(i));
        }
        // 함수로 만들어야 함
        //패널에 넣어야할 것들
        String[] measure = {"타이머","블로그","Github"};
        JPanel all = new JPanel();
        JPanel center = new JPanel();
        JPanel time = new JPanel();
        JPanel north = new JPanel(new GridLayout(1,2));
        JPanel north_left = new JPanel();
        JPanel north_right = new JPanel(new GridLayout(1,2));
        JComboBox<String> time_H_start = new JComboBox<String>(Time_H);
        JComboBox<String> time_M_start = new JComboBox<String>(Time_M);
        JComboBox<String> time_H_end = new JComboBox<String>(Time_H);
        JComboBox<String> time_M_end = new JComboBox<String>(Time_M);
        JLabel when_1 = new JLabel("시간 : [");
        JLabel when_2 = new JLabel("]");
        JLabel how = new JLabel("측정 : ");
        JLabel mid1 = new JLabel(":");
        JLabel mid2 = new JLabel(":");
        JLabel by = new JLabel("~");
        JTextField txt = new JTextField(30);
        JButton search = new JButton("찾기");
        JComboBox<String> how_to = new JComboBox<String>(measure);

        //패널 배치
        all.setLayout(new BorderLayout());

        north_left.add(name);
        north_left.setBorder(new LineBorder(Color.GREEN,3));
        north_right.add(enter);
        north_right.setBorder(new LineBorder(Color.BLUE,3));
        north.add(north_left);
        north.add(north_right);
        north.setBorder(new LineBorder(Color.RED,3));

        time.setBorder(new LineBorder(Color.CYAN,3));
        time.add(when_1);
        time.add(time_H_start);
        time.add(mid1);
        time.add(time_M_start);
        time.add(by);
        time.add(time_H_end);
        time.add(mid2);
        time.add(time_M_end);
        time.add(when_2);

        center.add(time);
        center.add(how);
        center.add(how_to);
        center.add(txt);
        center.add(search);
        txt.setVisible(false);
        search.setVisible(false);

        center.setBorder(new LineBorder(Color.ORANGE,3));
        all.add(BorderLayout.NORTH,north);
        all.add(BorderLayout.CENTER,center);
        all.setBorder(new LineBorder(Color.YELLOW,3));

        add(all);

        setSize(800,250);
        setVisible(true);

        time_H_start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox) e.getSource();
                index_S_H = cb.getSelectedIndex();
            }
        });

        time_M_start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox) e.getSource();
                index_S_M = cb.getSelectedIndex();
            }
        });

        time_H_end.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox) e.getSource();
                index_E_H = cb.getSelectedIndex();
            }
        });

        time_M_end.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox) e.getSource();
                index_E_M = cb.getSelectedIndex();
            }
        });

        how_to.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox) e.getSource();
                int index = cb.getSelectedIndex();
                switch (index){
                    case 0:
                        txt.setVisible(false);
                        search.setVisible(false);
                        break;
                    case 1:
                    case 2:
                        txt.setVisible(true);
                        search.setVisible(true);
                        break;
                }
            }
        });

        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(index_S_H<index_E_H){
                    dispose();
                }
                else if (index_S_H==index_E_H){
                    if(index_S_M<=index_E_M){
                        dispose();
                    }
                }
                else{
                    JOptionPane none = new JOptionPane();
                    none.showMessageDialog(null, "시간 조정을 제대로 해야합니다.");
                }
            }
        });
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String txtin = name.getText();
        if(e.getSource() == enter){
            System.out.println(txtin);
        }
    }
}
