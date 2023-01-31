package client.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings( "serial" )
public class SettingsView extends JFrame implements ActionListener {
	String imgPath = "./src/main/java/client/view/images/";
	ImageIcon i = new ImageIcon(imgPath+ "1.png");
	Image im=i.getImage();
	JFrame jf_setting = new JFrame (); // 메인 프레임
	JLabel msg = new JLabel ();//메시지
	JButton btn_chgBG = new JButton ("배경화면 변경"); // 배경화면 변경 버튼
	JButton btn_mem_info = new JButton ("회원정보 변경");// 닉네임 변경 버튼
	JButton btn_out = new JButton ("탈퇴하기");// 탈퇴하기 버튼
	JButton btn_home = new JButton ("친구");// 홈 가기 버튼
	JButton btn_lobby = new JButton ("채팅");// 로비 가기 버튼
	JButton btn_settings = new JButton ("설정");// 설정 가기 버튼
	JButton btn_logout = new JButton ("로그아웃");// 종료 버튼
	
	Font msgf = new Font ("맑은 고딕", Font.BOLD, 12);
	
	public void initDisplay () {
//		============================버튼 액션===============================
		btn_chgBG.addActionListener (this); // 배경화면 액션 버튼
		btn_mem_info.addActionListener (this); // 닉네임 액션 버튼
		btn_out.addActionListener (this);// 탈퇴하기 액션 버튼
		btn_home.addActionListener (this);// 홈 가기 액션 버튼
		btn_lobby.addActionListener (this);// 로비 가기 액션 버튼
		btn_settings.addActionListener (this);// 설정 가기 액션 버튼
		btn_logout.addActionListener (this);// 종료 액션 버튼
		
//		======================================창 조절 관련 옵션 ================================
		jf_setting.setTitle ("설정"); // 프레임 이름
		jf_setting.setSize (400, 600); // 프레임 사이즈
		jf_setting.setLayout (null); // 레이아웃 설정
		jf_setting.setResizable (false);// 창 크기 조절 불가
		jf_setting.setLocationRelativeTo (null); // 창 위치 중앙
		jf_setting.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);// 창 닫기 버튼 누르면 프로그램 종료
		
//		=================버튼위치====================
		btn_chgBG.setBounds (40, 50, 310, 80); // 배경화면 변경 버튼 위치, 크기 설정
		btn_mem_info.setBounds (40, 200, 310, 80);// 닉네임 변경 버튼 위치, 크기 설정
		btn_out.setBounds (40, 350, 310, 80);// 탈퇴하기 버튼 위치, 크기 설정
		btn_home.setBounds (0, 530, 100, 40);// 홈 가기 버튼 위치, 크기 설정
		btn_lobby.setBounds (100, 530, 100, 40);// 로비 가기 버튼 위치, 크기 설정
		btn_settings.setBounds (200, 530, 100, 40);// 설정 가기 버튼 위치, 크기 설정
		btn_logout.setBounds (300, 530, 100, 40);// 종료 버튼 위치, 크기 설정
		
//		==================폰트==================
		msg.setFont (msgf);
		btn_chgBG.setFont (msgf);// 배경화면 변경 버튼 폰트 설정
		btn_mem_info.setFont (msgf);// 닉네임 변경 버튼 폰트 설정
		btn_out.setFont (msgf);// 탈퇴하기 버튼 폰트 설정
		btn_home.setFont (msgf);// 홈 가기 버튼 폰트 설정
		btn_lobby.setFont (msgf);// 로비 가기 버튼 폰트 설정
		btn_settings.setFont (msgf);// 설정 가기 버튼 폰트 설정
		btn_logout.setFont (msgf);// 종료 버튼 폰트 설정
		
//		=================버튼추가=======================
		jf_setting.add (btn_chgBG);
		jf_setting.add (btn_mem_info);
		jf_setting.add (btn_out);
		jf_setting.add (btn_home);
		jf_setting.add (btn_lobby);
		jf_setting.add (btn_settings);
		jf_setting.add (btn_logout);
		jf_setting.setVisible (true);
	}
	
	class BGpanel extends JPanel{
		public void paintComponent(Graphics g) {
			super.paintComponent (g);
			g.drawImage (im, 0, 0, this);
		}
	}
//	==================================메인 메소드======================================
	public static void main (String[] args) {
		SettingsView sv = new SettingsView ();
		sv.initDisplay ();
		
		BGpanel bg = sv.new BGpanel ();
		
	}
//	================================액션 퍼폼=======================================
	@Override
	public void actionPerformed (ActionEvent e) {
		Object obj = e.getSource ();
		
		if (obj == btn_chgBG) {
			setTitle ("배경화면 변경");
			jf_setting.dispose ();
			SettingsBG sv = new SettingsBG ();
			sv.initDisplay ();
			System.out.println ("배경화면 변경");
			/* 파일 탐색기방식으로 변경 하려면 아래 주석 해제
			JFileChooser fileChooser = new JFileChooser (); // 파일 선택 창
			int result = fileChooser.showOpenDialog (jf_setting); // 파일 선택 창 띄우기
			if (result == JFileChooser.APPROVE_OPTION) { // 파일 선택 후 확인 버튼 누르면
				File file = fileChooser.getSelectedFile (); // 선택한 파일 가져오기
				System.out.println ("파일 경로 : " + file.getPath ()); // 파일 경로 출력
				System.out.println ("파일 이름 : " + file.getName ());// 파일 이름 출력
				String filePath = file.getPath ();
				String fileName = file.getName ();
			}*/
		} else if (obj == btn_mem_info) {
			setTitle ("회원정보 변경");
			jf_setting.dispose ();
			InfoChangeView mi = new InfoChangeView ();
			mi.initDisplay ();
			System.out.println ("회원정보 변경");
//			String newName = JOptionPane.showInputDialog (jf_setting, "변경할 닉네임 입력", "닉네임 변경", JOptionPane.INFORMATION_MESSAGE);
//			System.out.println ("변경할 닉네임 : " + newName);
			// DB에 저장
		
		} else if (obj == btn_out) {
			setTitle ("탈퇴하기");
			System.out.println ("탈퇴하기");

			// 탈퇴하기
			int result = JOptionPane.showConfirmDialog (jf_setting, "정말 탈퇴하시겠습니까?", "탈퇴", JOptionPane.YES_NO_OPTION);
			if (result == JOptionPane.YES_OPTION) {
				JOptionPane.showMessageDialog (jf_setting, "다음에 또 만나요~", "탈퇴", JOptionPane.INFORMATION_MESSAGE);
				jf_setting.dispose ();
			if (result == JOptionPane.NO_OPTION) {
				JOptionPane.showMessageDialog (jf_setting, "탈퇴를 취소합니다.", "탈퇴", JOptionPane.INFORMATION_MESSAGE);
			}
			}
//			=========================================하단 버튼 4개
		} else if (obj == btn_home) {
			setTitle ("홈");
			System.out.println ("홈");
			MainView mv = new MainView ();
			mv.initDisplay ();
			jf_setting.dispose ();
			// 홈으로 이동
			
		} else if (obj == btn_lobby) {
			setTitle ("로비");
			System.out.println ("로비");
			ChatList cl = new ChatList ();
			cl.initDisplay ();
			jf_setting.dispose ();
			// 로비로 이동
			
		} else if (obj == btn_settings) {
			setTitle ("설정");
			System.out.println ("설정");
			// 설정으로 이동
			
		} else if (obj == btn_logout) {
			setTitle ("로그아웃");
			System.out.println ("로그아웃");
			int result = JOptionPane.showConfirmDialog (jf_setting, "로그아웃 하시겠습니까?", "로그아웃 확인", JOptionPane.YES_NO_OPTION);
			if (result == JOptionPane.YES_OPTION) {
				JOptionPane.showMessageDialog (jf_setting, "다음에 또 만나요~", "로그아웃", JOptionPane.INFORMATION_MESSAGE);
				LoginView lv = new LoginView ();
				lv.initDisplay ();
				
				if (result == JOptionPane.NO_OPTION) {
					JOptionPane.showMessageDialog (jf_setting, "로그아웃 취소", "로그아웃 취소", JOptionPane.INFORMATION_MESSAGE);
				}
				jf_setting.dispose ();
			}
		}

			
		}
	}