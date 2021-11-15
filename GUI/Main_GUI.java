package GUI;
import java.awt.Dimension;

public class Main_GUI {
    Main_GUI(){
        Dimension dim = new Dimension(900,600);       // 초기 화면 크기를 설정해줌
        new page1(dim);
    }
    public static void main(String[] args) {
        new Main_GUI();
    }
}
