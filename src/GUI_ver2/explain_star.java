package GUI_ver2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class explain_star extends JFrame {
    public explain_star(){
        JPanel all = new JPanel(new BorderLayout());
        JPanel North = new JPanel();
        JPanel center = new JPanel();
        JPanel South = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JTextArea something = new JTextArea(10,30);
        JLabel explain = new JLabel("적고 싶은 것을 적으세요.");
        JButton end = new JButton("확인");

        North.add(explain);
        center.add(something);
        South.add(end);
        all.add(BorderLayout.NORTH,North);
        all.add(BorderLayout.CENTER, center);
        all.add(BorderLayout.SOUTH, South);

        add(all);

        setVisible(true);
        setSize(900,600);

        end.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
