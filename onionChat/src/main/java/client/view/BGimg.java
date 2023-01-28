package client.view;

import javax.swing.*;
import java.awt.*;

class BGimg extends JFrame {
    JButton b1;
    JLabel l1;

    public BGimg() {
        setTitle("배경화면 설정");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
/*
	One way
	-----------------
	setLayout(new BorderLayout());
	JLabel background=new JLabel(new ImageIcon("C:\\Users\\Computer\\Downloads\\colorful design.png"));
	add(background);
	background.setLayout(new FlowLayout());
	l1=new JLabel("Here is a button");
	b1=new JButton("I am a button");
	background.add(l1);
	background.add(b1);
*/
// Another way
        setLayout(new BorderLayout());
        setContentPane(new JLabel(new ImageIcon("C:\\Users\\thdau\\Desktop\\SP\\1.png")));
        setLayout(new FlowLayout());
//        l1=new JLabel("Here is a button");
//        b1=new JButton("I am a button");
//        add(l1);
//        add(b1);
        // Just for refresh :) Not optional!
        setSize(399, 399);
        setSize(400, 600);
    }
//    public static void main(String args[])
//    {
//        new BGimg();
//    }
//}
}