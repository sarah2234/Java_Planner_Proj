package GUI_ver2;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
public class Cal_Inf extends JPanel{
    public Cal_Inf(){
        JLabel goal = new JLabel("목표");
        JLabel time = new JLabel("시간");
        JLabel how = new JLabel("측정방법");
        JTextField goal_txt = new JTextField(10);
        JTextField time_txt = new JTextField(10);
        JTextField how_txt = new JTextField(10);

        setLayout(new GridLayout(3,2,5,5));
        goal.setBorder(new LineBorder(Color.ORANGE));
        goal_txt.setBorder(new LineBorder(Color.ORANGE));
        time.setBorder(new LineBorder(Color.ORANGE));
        time_txt.setBorder(new LineBorder(Color.ORANGE));
        how.setBorder(new LineBorder(Color.ORANGE));
        how_txt.setBorder(new LineBorder(Color.ORANGE));

        add(goal);
        add(goal_txt);
        add(time);
        add(time_txt);
        add(how);
        add(how_txt);

        setBorder(new LineBorder(Color.BLUE));
    }

}
