package client.view;

import javax.swing.*;
import java.awt.*;
import java.io.File;

class ImagePanel extends JPanel {

    public Image img;

    public ImagePanel(Image img) {
        this.img = img;
        setSize(400, 600);
        setPreferredSize(new Dimension(400, 600));
        setLayout(null);
    }

    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }
}

class chgIMG {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(400,600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        ImagePanel panel = new ImagePanel(new ImageIcon("C:\\Users\\thdau\\Desktop\\SP\\1.png").getImage());
        File f = new File("C:\\Users\\thdau\\Desktop\\SP\\1.png");
        frame.add(panel);
        frame.pack();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



    }
}

