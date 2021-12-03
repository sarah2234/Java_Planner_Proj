package GUI_ver2;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
public class Cal_Inf extends JPanel{
    private JTextField goal_txt;
    private JTextField time_txt;
    private JTextField how_txt;

    public Cal_Inf(){
        setLayout(null);

        JLabel goal_label = new JLabel("목표", JLabel.CENTER);
        goal_label.setBounds(10, 0, 50, 18);

        JLabel time_label = new JLabel("시간", JLabel.CENTER);
        time_label.setBounds(10, 20, 50, 18);

        JLabel how_label = new JLabel("측정방법", JLabel.CENTER);
        how_label.setBounds(10, 40, 50, 18);

        goal_txt = new JTextField(10);
        goal_txt.setBounds(70, 0, 200, 18);

        time_txt = new JTextField(10);
        time_txt.setBounds(70, 20, 200, 18);

        how_txt = new JTextField(10);
        how_txt.setBounds(70, 40, 200, 18);

        //goal_label.setBorder(new LineBorder(Color.ORANGE));
        goal_txt.setBorder(new LineBorder(Color.ORANGE));
        //time_label.setBorder(new LineBorder(Color.ORANGE));
        time_txt.setBorder(new LineBorder(Color.ORANGE));
        //how_label.setBorder(new LineBorder(Color.ORANGE));
        how_txt.setBorder(new LineBorder(Color.ORANGE));

        add(goal_label);
        add(goal_txt);
        add(time_label);
        add(time_txt);
        add(how_label);
        add(how_txt);

        setBorder(new LineBorder(Color.BLUE));
    }

    public String getGoal() {
        return goal_txt.getText();
    }

    public String getTime() {
        return time_txt.getText();
    }
}
