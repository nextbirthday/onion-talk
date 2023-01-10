package MJ;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.EventHandler;

public class SettingsBG extends JFrame implements ActionListener {
	//	JPanel panel = new JPanel();
	String imgPath = "C:\\Users\\thdau\\Desktop\\SP\\img\\";
	JFrame jf_setting = new JFrame ("BackGround Settings");
	JLabel jlb_bgname = new JLabel ("배경화면 설정");
	JButton jbtn_add = new JButton ("등록");
	JButton jbtn_cancel = new JButton ("돌아가기");
	JButton jbtn_bg1 = new JButton  ("배경화면 1");
	JButton jbtn_bg2 = new JButton  ("배경화면 2");
	JButton jbtn_bg3 = new JButton ("배경화면 3");
	JButton jbtn_bg4 = new JButton ("배경화면 4");
	JButton jbtn_bg5 = new JButton ("배경화면 5");
	JButton jbtn_home = new JButton ("홈");// 홈 가기 버튼
	JButton jbtn_lobby = new JButton ("로비");// 로비 가기 버튼
	JButton jbtn_settings = new JButton ("설정");// 설정 가기 버튼
	JButton jbtn_logout = new JButton ("로그아웃");// 종료 버튼
	
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
		jbtn_add.addActionListener(this);
		jbtn_cancel.addActionListener(this);
		jbtn_bg1.addActionListener(this);
		jbtn_bg2.addActionListener(this);
		jbtn_bg3.addActionListener(this);
		jbtn_bg4.addActionListener(this);
		jbtn_bg5.addActionListener(this);
		jbtn_home.addActionListener(this);
		jbtn_lobby.addActionListener(this);
		jbtn_settings.addActionListener(this);
		jbtn_logout.addActionListener(this);
		
		jf_setting.setTitle ("배경화면 설정");
		jf_setting.setLayout (null);
		jf_setting.setLocation (720,170);
		jf_setting.setSize (600, 900);
		jf_setting.setResizable (false);
		jf_setting.setDefaultCloseOperation (DISPOSE_ON_CLOSE);
		jf_setting.setVisible (true);
		
		jbtn_bg1.setFont (msgf);
		jbtn_bg2.setFont (msgf);
		jbtn_bg3.setFont (msgf);
		jbtn_bg4.setFont (msgf);
		jbtn_bg5.setFont (msgf);
		jbtn_add.setFont (msgf);
		jbtn_cancel.setFont (msgf);
		
		jbtn_home.setFont (msgf);
		jbtn_lobby.setFont (msgf);
		jbtn_settings.setFont (msgf);
		jbtn_logout.setFont (msgf);
		
		jlb_bgname.setFont (msgf);
		jlb_bgname.setBounds (100, 50, 200, 40); // 배경화면 설정 라벨 위치
		jbtn_add.setBounds (300, 50, 80, 40);// 선택된 배경화면 등록 버튼 위치
		jbtn_cancel.setBounds (400, 50, 130, 40);// 돌아가기 버튼 위치
		jbtn_bg1.setBounds (50, 100, 500, 50);// 배경화면 1 버튼 위치
		jbtn_bg2.setBounds (50, 170, 500, 50);// 배경화면 2 버튼 위치
		jbtn_bg3.setBounds (50, 240, 500, 50);// 배경화면 3 버튼 위치
		jbtn_bg4.setBounds (50, 310, 500, 50);// 배경화면 4 버튼 위치
		jbtn_bg5.setBounds (50, 380, 500, 50);// 배경화면 5 버튼 위치
		jbtn_home.setBounds (0, 830, 150, 40);// 홈 가기 버튼 위치, 크기 설정
		jbtn_lobby.setBounds (150, 830, 150, 40);// 로비 가기 버튼 위치, 크기 설정
		jbtn_settings.setBounds (300, 830, 150, 40);// 설정 가기 버튼 위치, 크기 설정
		jbtn_logout.setBounds (450, 830, 150, 40);// 종료 버튼 위치, 크기 설정
		
		jf_setting.add (jbtn_add);
		jf_setting.add (jbtn_cancel);
		jf_setting.add (jbtn_bg1);
		jf_setting.add (jbtn_bg2);
		jf_setting.add (jbtn_bg3);
		jf_setting.add (jbtn_bg4);
		jf_setting.add (jbtn_bg5);
		jf_setting.add (jlb_bgname);
		jf_setting.add (jbtn_home);
		jf_setting.add (jbtn_lobby);
		jf_setting.add (jbtn_settings);
		jf_setting.add (jbtn_logout);
		
		jf_setting.add (jbtn_home);
		jf_setting.add (jbtn_lobby);
		jf_setting.add (jbtn_settings);
		jf_setting.add (jbtn_logout);
		
	}
	
	@Override
	public void actionPerformed (ActionEvent e) {
		Object obj = e.getSource ();
		if (obj == jbtn_home) {
			jf_setting.dispose ();
//			MainPage mp = new MainPage ();
//			mp.initDisplay ();
		}
		else if (obj == jbtn_lobby) {
			jf_setting.dispose ();
//			LobbyPage lp = new LobbyPage ();
//			lp.initDisplay ();
		}
		else if (obj == jbtn_settings) {
			jf_setting.dispose ();
			SettingsView sv = new SettingsView ();
			sv.initDisplay ();
		}
		else if (obj == jbtn_logout) {
			System.exit (0);
		}
		else if (obj == jbtn_add) {
			JOptionPane.showMessageDialog (jf_setting, "선택하신 배경화면이 등록되었습니다.");
			jf_setting.dispose ();
			SettingsView sv = new SettingsView ();
			sv.initDisplay ();
		}
		else if (obj == jbtn_cancel) { // 돌아가기 버튼 누르면 SettingsView로 돌아감
			jf_setting.dispose ();
			SettingsView sv = new SettingsView ();
			sv.initDisplay ();
		}
		else if (obj == jbtn_bg1) {
			JOptionPane.showMessageDialog (jf_setting, "배경화면 1이 선택되었습니다.");
		}
		else if (obj == jbtn_bg2) {
			JOptionPane.showMessageDialog (jf_setting, "배경화면 2이 선택되었습니다.");
		}
		else if (obj == jbtn_bg3) {
			JOptionPane.showMessageDialog (jf_setting, "배경화면 3이 선택되었습니다.");
		}
		else if (obj == jbtn_bg4) {
			JOptionPane.showMessageDialog (jf_setting, "배경화면 4이 선택되었습니다.");
		}
		else if (obj == jbtn_bg5) {
			JOptionPane.showMessageDialog (jf_setting, "배경화면 5이 선택되었습니다.");
		}
		
		
	}
	
	public static void main (String[] args) {
		SettingsBG sbg = new SettingsBG ();
		sbg.initDisplay ();
	}
}