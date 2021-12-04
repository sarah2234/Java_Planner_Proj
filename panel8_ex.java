package sample;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;


public class panel8_ex extends JFrame {
    public panel8_ex(){
        roadMap_search a = new roadMap_search();
        add(a);
        setSize(450,600);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new panel8_ex();
    }
}

class roadMap_search extends JPanel{
    private JTextField Search_txt;
    private int roadmap_num=3;
    private Vector<List_RoadMap> V_List = new Vector<List_RoadMap>();
    private int cnt=0;

    public roadMap_search() {
        setLayout(null);

        Search_txt = new JTextField();
        Search_txt.setBounds(40, 60, 265, 20);
        add(Search_txt);
        Search_txt.setColumns(10);

        JButton search_btn = new JButton("찾기");
        search_btn.setBounds(330, 60, 95, 20);
        add(search_btn);

        JLabel RoadMap_Search = new JLabel("RoadMap Search");
        RoadMap_Search.setBounds(40, 10, 265, 30);
        add(RoadMap_Search);

        JPanel All = new JPanel();
        All.setBounds(0, 90, 450, 500);
        add(All);
        All.setLayout(null);

        JPanel North = new JPanel();
        North.setBounds(0, 0, 450, 45);
        All.add(North);
        North.setLayout(null);

        JLabel goal = new JLabel("목표");
        goal.setHorizontalAlignment(SwingConstants.CENTER);
        goal.setBounds(0, 0, 75, 45);
        North.add(goal);

        JLabel period = new JLabel("기간");
        period.setHorizontalAlignment(SwingConstants.CENTER);
        period.setBounds(75, 0, 75, 45);
        North.add(period);

        JLabel time = new JLabel("시간");
        time.setHorizontalAlignment(SwingConstants.CENTER);
        time.setBounds(150, 0, 75, 45);
        North.add(time);

        JLabel DayOfWeek = new JLabel("요일");
        DayOfWeek.setHorizontalAlignment(SwingConstants.CENTER);
        DayOfWeek.setBounds(225, 0, 75, 45);
        North.add(DayOfWeek);

        JLabel HowTo = new JLabel("측정방법");
        HowTo.setHorizontalAlignment(SwingConstants.CENTER);
        HowTo.setBounds(300, 0, 75, 45);
        North.add(HowTo);

        JLabel Join = new JLabel("참여");
        Join.setHorizontalAlignment(SwingConstants.CENTER);
        Join.setBounds(375, 0, 80, 45);
        North.add(Join);

        JPanel Center = new JPanel();
        Center.setBounds(0, 45, 450, 375);
        All.add(Center);
        Center.setLayout(null);

        JPanel South = new JPanel();
        South.setBounds(0, 415, 450, 60);
        All.add(South);
        South.setLayout(null);

        JButton left = new JButton("◀");
        left.setBounds(5, 5, 50, 50);
        South.add(left);
        left.setEnabled(false);

        JButton right = new JButton("▶");
        right.setBounds(380, 5, 50, 50);
        South.add(right);

        All.setBorder(new LineBorder(Color.BLUE));

        for (int i = 0; i < 12; i++) {
            List_RoadMap new_List = new List_RoadMap(i+1+12*cnt);
            new_List.setBounds(0,i*30,450,30);
            new_List.setBorder(new LineBorder(Color.BLACK));
            V_List.add(new_List);
            Center.add(V_List.elementAt(i));
        }

        left.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cnt--;
                System.out.println(cnt);
                Center.setVisible(false);
                Center.removeAll();
                for (int i = 0; i < 12; i++) {
                    List_RoadMap new_List = new List_RoadMap(i+1+12*cnt);
                    new_List.setBounds(0,i*30,450,30);
                    new_List.setBorder(new LineBorder(Color.BLACK));
                    V_List.add(new_List);
                    Center.add(V_List.elementAt(i+12*cnt));
                }
                Center.setVisible(true);
                if(cnt==0){
                    left.setEnabled(false);
                }
            }
        });

        right.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cnt++;
                System.out.println(cnt);
                Center.setVisible(false);
                Center.removeAll();
                for (int i = 0; i < 12; i++) {
                    List_RoadMap new_List = new List_RoadMap(i+1+12*cnt);
                    new_List.setBounds(0,i*30,450,30);
                    new_List.setBorder(new LineBorder(Color.BLACK));
                    V_List.add(new_List);
                    Center.add(V_List.elementAt(i+12*cnt));
                }
                Center.setVisible(true);
                if(cnt != 0)
                    left.setEnabled(true);
            }
        });
    }
}
