package MJ;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class chgBG extends JFrame {
	
	public chgBG () {
		super ("창1"); // 창의 제목
		setTitle ("배경 바꾸기");
		setSize (600, 900);
		JPanel BG = new JPanel ();
		JPanel BG1 = new JPanel ();
		JPanel BG2 = new JPanel ();
		JPanel BG3 = new JPanel ();
		JPanel BG4 = new JPanel ();
		JPanel BG5 = new JPanel ();
		JButton jbtn_ChanBG = new JButton ("배경화면1");
		JButton jbtn_ChanBG2 = new JButton ("배경화면2");
		JButton jbtn_ChanBG3 = new JButton ("배경화면3");
		JButton jbtn_ChanBG4 = new JButton ("배경화면4");
		JButton jbtn_ChanBG5 = new JButton ("배경화면5");
		JButton jbtn_ChanBG6 = new JButton ("배경화면6");
		BG.add (jbtn_ChanBG);
//		BG1.add (jbtn_ChanBG2);
//		BG2.add (jbtn_ChanBG3);
//		BG3.add (jbtn_ChanBG4);
//		BG4.add (jbtn_ChanBG5);
//		BG5.add (jbtn_ChanBG6);
		add (BG);
		add (BG1);
		add (BG2);
		add (BG3);
		add (BG4);
		add (BG5);
		
		
		Dimension frameSize = getSize ();
		
		Dimension windowSize = Toolkit.getDefaultToolkit ().getScreenSize ();
		setLocation ((windowSize.width - frameSize.width) / 2, (windowSize.height - frameSize.height) / 2); // 창이 화면 중앙에 뜨게 함
		setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		setVisible (true);
	}
		
		public static void main (String[]args){
			
			
		}
		
	}
