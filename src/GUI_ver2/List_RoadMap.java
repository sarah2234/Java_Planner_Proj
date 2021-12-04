package GUI_ver2;
// 추가
import javax.swing.*;
import java.awt.*;

public class List_RoadMap extends JPanel {
    public List_RoadMap(int i){
        setLayout(null);

        JLabel Goal = new JLabel("목표"+i);
        Goal.setHorizontalAlignment(SwingConstants.CENTER);
        Goal.setBounds(0, 0, 75, 30);
        add(Goal);

        JLabel Period = new JLabel("기간"+i);
        Period.setHorizontalAlignment(SwingConstants.CENTER);
        Period.setBounds(75, 0, 75, 30);
        add(Period);

        JLabel Time = new JLabel("시간"+i);
        Time.setHorizontalAlignment(SwingConstants.CENTER);
        Time.setBounds(150, 0, 75, 30);
        add(Time);

        JLabel DayOfWeek = new JLabel("요일"+i);
        DayOfWeek.setHorizontalAlignment(SwingConstants.CENTER);
        DayOfWeek.setBounds(225, 0, 75, 30);
        add(DayOfWeek);

        JLabel HowTo = new JLabel("측정방식"+i);
        HowTo.setHorizontalAlignment(SwingConstants.CENTER);
        HowTo.setBounds(300, 0, 75, 30);
        add(HowTo);

        JButton join = new JButton("참여");
        join.setFont(new Font("굴림", Font.PLAIN, 8));
        join.setBounds(385, 0, 50, 30);
        add(join);
    }
}