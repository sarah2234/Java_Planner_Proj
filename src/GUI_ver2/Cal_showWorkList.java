package GUI_ver2;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class Cal_showWorkList extends JPanel {
    private Vector<String> goals;
    private Vector<String> times;

    public Cal_showWorkList(Vector<String> goals, Vector<String> times) {
        this.goals = goals;
        this.times = times;

        this.setLayout(new GridLayout(6,1));
        JLabel title = new JLabel("리스트");
        title.setBorder(BorderFactory.createEmptyBorder(0 , 10 , 0 , 0));
        this.add(title);

        for(int i = 0; i < goals.size(); i++) {
            JLabel list = new JLabel("* " + goals.get(i) + times.get(i));
            this.add(list);
        }

        setPreferredSize(new Dimension(350,200));
    }
}
