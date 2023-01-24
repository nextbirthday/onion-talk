package client.view;

import javax.swing.*;
import java.awt.*;

public class BG1 extends JFrame {
    JScrollPane scrollPane;
    static ImageIcon icon;
    ImageIcon icon1;
    ImageIcon icon2;
    ImageIcon icon3;
    ImageIcon icon4;

    public BG1() {
        icon  = new ImageIcon("C:\\Users\\thdau\\Desktop\\SP\\1.png");
        icon1  = new ImageIcon("C:\\Users\\thdau\\Desktop\\SP\\2.png");
        icon2  = new ImageIcon("C:\\Users\\thdau\\Desktop\\SP\\3.png");
        icon3  = new ImageIcon("C:\\Users\\thdau\\Desktop\\SP\\4.png");
        icon4  = new ImageIcon("C:\\Users\\thdau\\Desktop\\SP\\5.png");

        //배경 Panel 생성후 컨텐츠페인으로 지정
        JPanel background = new JPanel() {
            public void paintComponent(Graphics g) {
                g.drawImage(icon1.getImage(), 0, 0, null);
                setOpaque(false); //그림을 표시하게 설정,투명하게 조절
                super.paintComponent(g);
            }
        };



//        JButton button = new JButton("버튼");
//        background.add(button);
        scrollPane = new JScrollPane(background);
        setContentPane(scrollPane);
    }

    public static void main(String[] args) {
        BG1 frame = new BG1();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 600);
        frame.setVisible(true);
    }
}