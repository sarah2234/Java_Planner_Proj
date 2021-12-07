package GUI_ver2;
// 추가
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import static GUI_ver2.Main.client;

//목표,기간,코멘트,요일,참가여부를 파라미터로 보내줘야함.
// 목표+i 같은 곳을 받은 변수로 넣기

public class List_RoadMap extends JPanel {
    public List_RoadMap(String Month, String Week, String Comment, String Aim, String RoadId){

        setLayout(null);

        JLabel Goal = new JLabel(Aim);
        Goal.setHorizontalAlignment(SwingConstants.CENTER);
        Goal.setBounds(0, 0, 65, 25);
        Goal.setBorder(new LineBorder(Color.WHITE));
        Goal.setForeground(Color.white);
        add(Goal);

        JLabel Period = new JLabel(Month);
        Period.setHorizontalAlignment(SwingConstants.CENTER);
        Period.setBounds(65, 0, 65, 25);
        Period.setBorder(new LineBorder(Color.WHITE));
        Period.setForeground(Color.white);
        add(Period);

        JLabel DayOfWeek = new JLabel(Week);
        DayOfWeek.setHorizontalAlignment(SwingConstants.CENTER);
        DayOfWeek.setBounds(130, 0, 150, 25);
        DayOfWeek.setBorder(new LineBorder(Color.WHITE));
        DayOfWeek.setForeground(Color.white);
        add(DayOfWeek);

        JButton join = new JButton("참여");
        join.setFont(new Font("굴림", Font.PLAIN, 8));
        join.setBounds(280, 0, 60, 25);
        join.setBorder(new LineBorder(Color.WHITE));
        join.setForeground(Color.white);
        join.setBackground(Color.black);
        add(join);

        //코멘트를 팁으로 넣어줘야한다.
        this.setToolTipText(Comment);

        join.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client.sendRoadId(RoadId);
                System.out.println(RoadId);
            }
        });
    }
}