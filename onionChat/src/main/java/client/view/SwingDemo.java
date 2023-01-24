package client.view;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class SwingDemo extends javax.swing.JFrame {
    static Image img1 = Toolkit.getDefaultToolkit().getImage("C:\\Users\\thdau\\Desktop\\SP\\1.png");
    Image img2 = Toolkit.getDefaultToolkit().getImage("C:\\Users\\thdau\\Desktop\\SP\\2.png");
    Image img3 = Toolkit.getDefaultToolkit().getImage("C:\\Users\\thdau\\Desktop\\SP\\3.png");
    Image img4 = Toolkit.getDefaultToolkit().getImage("C:\\Users\\thdau\\Desktop\\SP\\4.png");
    Image img5 = Toolkit.getDefaultToolkit().getImage("C:\\Users\\thdau\\Desktop\\SP\\5.png");
    public SwingDemo() throws IOException {
        this.setContentPane(new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(img1, 0, 0, null);
                g.drawImage(img2, 0, 0, null);
                g.drawImage(img3, 0, 0, null);
                g.drawImage(img4, 0, 0, null);
                g.drawImage(img5, 0, 0, null);


            }


        });
        pack();
        setVisible(true);
        setBounds(0, 0, 400, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    public static void main(String[] args) throws Exception {
        new SwingDemo();
    }
}