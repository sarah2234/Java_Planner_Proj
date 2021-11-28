package GUI_ver2;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


//class panel3 extends JPanel {    // 3 페이지 panel 생성
//    public panel3(){
//        JButton img;
//        JButton img_2;
//        //사진크기를 조정 -> 함수로 생성해야할 필요 있음(사진을 버튼 크기에 맞추려면)
//        ImageIcon Cancer = new ImageIcon("src\\GUI_ver2\\image\\게자리.jpg");
//        Image Cancer_img = Cancer.getImage();
//        Image Change = Cancer_img.getScaledInstance(100,105,Image.SCALE_SMOOTH);
//        ImageIcon Change_Cancer = new ImageIcon(Change);
//
//        ImageIcon Leo = new ImageIcon("src\\GUI_ver2\\image\\사자자리.png");
//        Image Leo_img = Leo.getImage();
//        Image Change_2 = Leo_img.getScaledInstance(100,105,Image.SCALE_SMOOTH);
//        ImageIcon Change_Leo = new ImageIcon(Change_2);
//
//        //버튼에 사진 넣고 버튼 크기 조정
//        img = new JButton(Change_Cancer);
//        img_2 = new JButton(Change_Leo);
//        img.setPreferredSize(new Dimension(100,105));
//        img_2.setPreferredSize(new Dimension(100,105));
//
//        add(img);
//        add(img_2);
//
//        img.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//            }
//        });
//
//        img_2.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//            }
//        });
//    }
//}
//
//class panel4 extends JPanel{    // 4 페이지 panel 생성
//    public panel4(){
//        JLabel label = new JLabel("<html>이것은 4page입니다.<br/><br/>3page의 행동에 따라 별자리 설명을 띄운다.</html>");
//        add(label);
//    }
//}

class explain extends JPanel{
    public explain(ImageIcon star){

        JPanel Constellation = new JPanel(){
            public void paintComponent(Graphics g){
                Dimension d = getSize();
                g.drawImage(star.getImage(), 0,0,d.width,d.height,null);
            }
        };
        JPanel Explain = new JPanel();
        JPanel North = new JPanel();
        JPanel Write = new JPanel();
        JLabel exp_date = new JLabel("날짜");
        JLabel goal = new JLabel("목표");
        JLabel write = new JLabel("쓰고 싶은 말");

        Explain.setLayout(new GridLayout(2,1));
        Explain.add(exp_date);
        Explain.add(goal);
        Write.add(write);

        setLayout(new GridLayout(2,1));
        North.setLayout(new GridLayout(1,2));
        North.add(Constellation);
        North.add(Explain);
        add(North);
        add(Write);

        North.setBorder(new LineBorder(Color.red,3));
        Write.setBorder(new LineBorder(Color.BLUE,3));

        this.setBorder(new LineBorder(Color.GREEN));
    }
}

public class page_2 extends JPanel{
    public page_2(){
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel(new GridLayout());
        JPanel main = new JPanel();
        JButton img;
        JButton img_2;
        //사진크기를 조정 -> 함수로 생성해야할 필요 있음(사진을 버튼 크기에 맞추려면)
        ImageIcon Cancer = new ImageIcon("src\\GUI_ver2\\image\\게자리.jpg");
        Image Cancer_img = Cancer.getImage();
        Image Change = Cancer_img.getScaledInstance(100,105,Image.SCALE_SMOOTH);
        ImageIcon Change_Cancer = new ImageIcon(Change);

        ImageIcon Leo = new ImageIcon("src\\GUI_ver2\\image\\사자자리.png");
        Image Leo_img = Leo.getImage();
        Image Change_2 = Leo_img.getScaledInstance(100,105,Image.SCALE_SMOOTH);
        ImageIcon Change_Leo = new ImageIcon(Change_2);

        //버튼에 사진 넣고 버튼 크기 조정
        img = new JButton(Change_Cancer);
        img_2 = new JButton(Change_Leo);
        img.setPreferredSize(new Dimension(100,105));
        img_2.setPreferredSize(new Dimension(100,105));

        panel3.add(img);
        panel3.add(img_2);

        img.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel4.removeAll();
                panel4.setVisible(false);
                explain star_light = new explain(Change_Cancer);
                panel4.add(star_light);
                panel4.setVisible(true);
            }
        });

        img_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel4.removeAll();
                panel4.setVisible(false);
                explain star_light = new explain(Change_Leo);
                panel4.add(star_light);
                panel4.setVisible(true);
            }
        });

        main.setLayout(new GridLayout(1,2));
        panel3.setBorder(new LineBorder(Color.ORANGE,3)); // 패널 크기를 육안으로 확인하기 위해 경계선 그어줌
        panel4.setBorder(new LineBorder(Color.white,3)); // 위와 동일
        main.add(panel3);
        main.add(panel4);
        this.add(main);
    }
}
