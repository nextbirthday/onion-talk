package client.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsBG extends JFrame implements ActionListener {
	//	JPanel panel = new JPanel();
	String imgPath = "C:\\Users\\thdau\\Desktop\\SP\\img\\";
	JFrame jf_setting = new JFrame ("BackGround Settings");
	JLabel jlb_bgname = new JLabel ("배경화면 설정");
	JButton btn_add = new JButton ("등록");
	JButton btn_cancel = new JButton ("돌아가기");
	JButton btn_setbg1 = new JButton  ("배경화면 1");
	JButton btn_setbg2 = new JButton  ("배경화면 2");
	JButton btn_setbg3 = new JButton ("배경화면 3");
	JButton btn_setbg4 = new JButton ("배경화면 4");
	JButton btn_setbg5 = new JButton ("배경화면 5");
	JButton btn_home = new JButton ("홈");// 홈 가기 버튼
	JButton btn_lobby = new JButton ("로비");// 로비 가기 버튼
	JButton btn_settings = new JButton ("설정");// 설정 가기 버튼
	JButton btn_logout = new JButton ("로그아웃");// 종료 버튼
	
	Font msgf = new Font ("맑은 고딕", Font.BOLD, 20);
	
//	Label lbl = new Label();
//	SettingsBG(){
//	super("배경화면 설정");
//	this.setVisible (true);
//	this.setSize (500, 500);
//	this.setLayout (null);
//	this.addWindowListener (new MyWinExit ());
//	lbl=new Label("배경화면 설정1");
//	lbl.setBounds (100, 50, 300, 50);
//	add(lbl);
//	lbl.addMouseListener (this);
//	}
//
//	@Override
//	public void actionPerformed (ActionEvent e) {
//
//	}
//
//	@Override
//	public void mouseClicked (MouseEvent e) {
//
//	}
//
//	@Override
//	public void mousePressed (MouseEvent e) {
//
//	}
//
//	@Override
//	public void mouseReleased (MouseEvent e) {
//
//	}
//
//	@Override
//	public void mouseEntered (MouseEvent e) {
//
//	}
//
//	@Override
//	public void mouseExited (MouseEvent e) {
//
//	}
//}
//
//	public class MyWinExit extends WindowAdapter {
//		public void windowClosing (WindowEvent e) {
//			System.exit (0);
//		}
//
//
//	}
	
	
	public void initDisplay () {
		btn_add.addActionListener(this);
		btn_cancel.addActionListener(this);
		btn_setbg1.addActionListener(this);
		btn_setbg2.addActionListener(this);
		btn_setbg3.addActionListener(this);
		btn_setbg4.addActionListener(this);
		btn_setbg5.addActionListener(this);
		btn_home.addActionListener(this);
		btn_lobby.addActionListener(this);
		btn_settings.addActionListener(this);
		btn_logout.addActionListener(this);
		
		jf_setting.setTitle ("배경화면 설정");
		jf_setting.setLayout (null);
		jf_setting.setLocation (720,170);
		jf_setting.setSize (600, 900);
		jf_setting.setResizable (false);
		jf_setting.setDefaultCloseOperation (DISPOSE_ON_CLOSE);
		jf_setting.setVisible (true);
		
		btn_setbg1.setFont (msgf);
		btn_setbg2.setFont (msgf);
		btn_setbg3.setFont (msgf);
		btn_setbg4.setFont (msgf);
		btn_setbg5.setFont (msgf);
		btn_add.setFont (msgf);
		btn_cancel.setFont (msgf);
		
		btn_home.setFont (msgf);
		btn_lobby.setFont (msgf);
		btn_settings.setFont (msgf);
		btn_logout.setFont (msgf);
		
		jlb_bgname.setFont (msgf);
		jlb_bgname.setBounds (100, 50, 200, 40); // 배경화면 설정 라벨 위치
		btn_add.setBounds (300, 50, 80, 40);// 선택된 배경화면 등록 버튼 위치
		btn_cancel.setBounds (400, 50, 130, 40);// 돌아가기 버튼 위치
		btn_setbg1.setBounds (50, 100, 500, 50);// 배경화면 1 버튼 위치
		btn_setbg2.setBounds (50, 170, 500, 50);// 배경화면 2 버튼 위치
		btn_setbg3.setBounds (50, 240, 500, 50);// 배경화면 3 버튼 위치
		btn_setbg4.setBounds (50, 310, 500, 50);// 배경화면 4 버튼 위치
		btn_setbg5.setBounds (50, 380, 500, 50);// 배경화면 5 버튼 위치
		btn_home.setBounds (0, 830, 150, 40);// 홈 가기 버튼 위치, 크기 설정
		btn_lobby.setBounds (150, 830, 150, 40);// 로비 가기 버튼 위치, 크기 설정
		btn_settings.setBounds (300, 830, 150, 40);// 설정 가기 버튼 위치, 크기 설정
		btn_logout.setBounds (450, 830, 150, 40);// 종료 버튼 위치, 크기 설정
		
		jf_setting.add (btn_add);
		jf_setting.add (btn_cancel);
		jf_setting.add (btn_setbg1);
		jf_setting.add (btn_setbg2);
		jf_setting.add (btn_setbg3);
		jf_setting.add (btn_setbg4);
		jf_setting.add (btn_setbg5);
		jf_setting.add (jlb_bgname);
		jf_setting.add (btn_home);
		jf_setting.add (btn_lobby);
		jf_setting.add (btn_settings);
		jf_setting.add (btn_logout);
		
		jf_setting.add (btn_home);
		jf_setting.add (btn_lobby);
		jf_setting.add (btn_settings);
		jf_setting.add (btn_logout);
		
	}
	
	@Override
	public void actionPerformed (ActionEvent e) {
		Object obj = e.getSource ();
		if (obj == btn_home) {
			jf_setting.dispose ();
//			MainPage mp = new MainPage ();
//			mp.initDisplay ();
		}
		else if (obj == btn_lobby) {
			jf_setting.dispose ();
//			LobbyPage lp = new LobbyPage ();
//			lp.initDisplay ();
		}
		else if (obj == btn_settings) {
			jf_setting.dispose ();
			SettingsView sv = new SettingsView ();
			sv.initDisplay ();
		}
		else if (obj == btn_logout) {
			System.exit (0);
		}
		else if (obj == btn_add) {
			JOptionPane.showMessageDialog (jf_setting, "선택하신 배경화면이 등록되었습니다.");
			jf_setting.dispose ();
			SettingsView sv = new SettingsView ();
			sv.initDisplay ();
		}
		else if (obj == btn_cancel) { // 돌아가기 버튼 누르면 SettingsView로 돌아감
			jf_setting.dispose ();
			SettingsView sv = new SettingsView ();
			sv.initDisplay ();
		}
		else if (obj == btn_setbg1) {
			JOptionPane.showMessageDialog (jf_setting, "배경화면 1이 선택되었습니다.");
		}
		else if (obj == btn_setbg2) {
			JOptionPane.showMessageDialog (jf_setting, "배경화면 2이 선택되었습니다.");
		}
		else if (obj == btn_setbg3) {
			JOptionPane.showMessageDialog (jf_setting, "배경화면 3이 선택되었습니다.");
		}
		else if (obj == btn_setbg4) {
			JOptionPane.showMessageDialog (jf_setting, "배경화면 4이 선택되었습니다.");
		}
		else if (obj == btn_setbg5) {
			JOptionPane.showMessageDialog (jf_setting, "배경화면 5이 선택되었습니다.");
		}
		
		
	}
	
	public static void main (String[] args) {
		SettingsBG sbg = new SettingsBG ();
		sbg.initDisplay ();
	}
}