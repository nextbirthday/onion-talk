package client.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings( "serial" )
public class SettingsBG extends JFrame implements ActionListener {
	String imgPath = "C:\\Users\\thdau\\Desktop\\SP\\img\\";// 이미지 경로
	JPanel BGpanel = new JPanel ();
	Container con = getContentPane (); // 컨테이너 생성
	JPanel jp_setting = new JPanel (); // 배경화면 설정 창
	JFrame jf_setting = new JFrame (); // 배경화면 설정 창
	JLabel jlb_bgname = new JLabel ("배경화면 설정"); // 배경화면 설정 라벨
	JButton btn_add = new JButton ("등록"); // 등록 버튼
	JButton btn_cancel = new JButton ("돌아가기");
	JButton btn_setbg1 = new JButton ("배경화면 1");
	JButton btn_setbg2 = new JButton ("배경화면 2");
	JButton btn_setbg3 = new JButton ("배경화면 3");
	JButton btn_setbg4 = new JButton ("배경화면 4");
	JButton btn_setbg5 = new JButton ("배경화면 5");
	JButton btn_home = new JButton ("홈");// 홈 가기 버튼
	JButton btn_lobby = new JButton ("로비");// 로비 가기 버튼
	JButton btn_settings = new JButton ("설정");// 설정 가기 버튼
	JButton btn_logout = new JButton ("로그아웃");// 종료 버튼
	ImageIcon icon = new ImageIcon (imgPath + "onion1.jpg");
	Font msgf = new Font ("맑은 고딕", Font.BOLD, 12);
	
	
	//	======================================= 화면 설계 ====================================================
	public void initDisplay () {
		
		con.add (jp_setting);
		btn_add.addActionListener (this);
		btn_cancel.addActionListener (this);
		btn_setbg1.addActionListener (this);
		btn_setbg2.addActionListener (this);
		btn_setbg3.addActionListener (this);
		btn_setbg4.addActionListener (this);
		btn_setbg5.addActionListener (this);
		btn_home.addActionListener (this);
		btn_lobby.addActionListener (this);
		btn_settings.addActionListener (this);
		btn_logout.addActionListener (this);
		
		jf_setting.setTitle ("배경화면 설정");
		jf_setting.setLayout (null);
		jf_setting.setSize (400, 600);
		jf_setting.setResizable (false);
		jf_setting.setLocationRelativeTo (null); // 창을 가운데로
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
		jlb_bgname.setBounds (60, 50, 150, 30); // 배경화면 설정 라벨 위치
		btn_add.setBounds (190, 50, 60, 30);// 선택된 배경화면 등록 버튼 위치
		btn_cancel.setBounds (260, 50, 90, 30);// 돌아가기 버튼 위치
		btn_setbg1.setBounds (40, 100, 310, 30);// 배경화면 1 버튼 위치
		btn_setbg2.setBounds (40, 170, 310, 30);// 배경화면 2 버튼 위치
		btn_setbg3.setBounds (40, 240, 310, 30);// 배경화면 3 버튼 위치
		btn_setbg4.setBounds (40, 310, 310, 30);// 배경화면 4 버튼 위치
		btn_setbg5.setBounds (40, 380, 310, 30);// 배경화면 5 버튼 위치
		btn_home.setBounds (0, 530, 100, 40);// 홈 가기 버튼 위치, 크기 설정
		btn_lobby.setBounds (100, 530, 100, 40);// 로비 가기 버튼 위치, 크기 설정
		btn_settings.setBounds (200, 530, 100, 40);// 설정 가기 버튼 위치, 크기 설정
		btn_logout.setBounds (300, 530, 100, 40);// 종료 버튼 위치, 크기 설정
		
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
		//		============================================================================
	}
	
	
		
		
		//	======================================= 이벤트 처리 ====================================================
		@Override
		public void actionPerformed (ActionEvent e){
//		======================================하단버튼4개=======================================
			Object obj = e.getSource ();
			
			if (obj == btn_home) {
//			jp_setting.dispose ();  // 배경화면 설정 창 닫기
				jlb_bgname.setText ("Home");
				jlb_bgname.setIcon (icon);
//			MainPage mp = new MainPage ();
//			mp.initDisplay ();
			
			} else if (obj == btn_lobby) {
				ChatList cl = new ChatList ();
				cl.initDisplay ();
				jf_setting.dispose ();
				
			} else if (obj == btn_settings) {
				jf_setting.dispose ();
				SettingsView sv = new SettingsView ();
				sv.initDisplay ();
				
			} else if (obj == btn_logout) {
				int result = JOptionPane.showConfirmDialog (jp_setting, "로그아웃 하시겠습니까?", "로그아웃 확인", JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.NO_OPTION) {
				
				}
				System.exit (0);

//=====================================등록, 돌아가기 버튼 ===========================================
			} else if (obj == btn_add) {
				JOptionPane.showMessageDialog (jp_setting, "선택하신 배경화면이 등록되었습니다.");
				//			jp_setting.dispose (); // 배경화면 등록 후 설정창 닫기
				//			SettingsView sv = new SettingsView (); // 설정창 다시 띄우기
				//			sv.initDisplay ();
			} else if (obj == btn_cancel) { // 돌아가기 버튼 누르면 SettingsView로 돌아감
				jf_setting.dispose ();
				SettingsView sv = new SettingsView ();
				sv.initDisplay ();

//===================================배경화면 변경 버튼===========================================================
			} else if (obj == btn_setbg1) {
				setContentPane (new JLabel (new ImageIcon ("C:\\Users\\thdau\\Desktop\\SP\\img\\onion1.png")));
				JOptionPane.showMessageDialog (jp_setting, "배경화면 1이 선택되었습니다.");
//				SettingsView sv = new SettingsView ();
//				sv.initDisplay ();
//  			jf_setting.dispose ();
			} else if (obj == btn_setbg2) {
				JOptionPane.showMessageDialog (jp_setting, "배경화면 2이 선택되었습니다.");
			} else if (obj == btn_setbg3) {
				JOptionPane.showMessageDialog (jp_setting, "배경화면 3이 선택되었습니다.");
			} else if (obj == btn_setbg4) {
				JOptionPane.showMessageDialog (jp_setting, "배경화면 4이 선택되었습니다.");
			} else if (obj == btn_setbg5) {
				JOptionPane.showMessageDialog (jp_setting, "배경화면 5이 선택되었습니다.");
			}
		}
		
		public static void main (String[]args){
			SettingsBG sbg = new SettingsBG ();
			sbg.initDisplay ();
			
		}
	}