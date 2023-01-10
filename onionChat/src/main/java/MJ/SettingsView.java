package MJ;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class SettingsView extends JFrame implements ActionListener {
	String imgPath = "C:\\Users\\thdau\\Desktop\\SP\\img\\";
	JFrame jf_settings = new JFrame (); // 메인 프레임
	JLabel msg = new JLabel ();//메시지
	JButton jbtn_chgBG = new JButton ("배경화면 변경"); // 배경화면 변경 버튼
	JButton jbtn_chgName = new JButton ("닉네임 변경");// 닉네임 변경 버튼
	JButton jbtn_chgPW = new JButton ("비밀번호 변경");// 비밀번호 변경 버튼
	JButton jbtn_signout = new JButton ("탈퇴하기");// 탈퇴하기 버튼
	JButton jbtn_home = new JButton ("홈");// 홈 가기 버튼
	JButton jbtn_lobby = new JButton ("로비");// 로비 가기 버튼
	JButton jbtn_settings = new JButton ("설정");// 설정 가기 버튼
	JButton jbtn_logout = new JButton ("로그아웃");// 종료 버튼
	
	Font msgf = new Font ("맑은 고딕", Font.BOLD, 20);
	
	public void initDisplay () {
		jbtn_chgBG.addActionListener (this); // 배경화면 액션 버튼
		jbtn_chgName.addActionListener (this); // 닉네임 액션 버튼
		jbtn_chgPW.addActionListener (this);// 비밀번호 액션 버튼
		jbtn_signout.addActionListener (this);// 탈퇴하기 액션 버튼
		jbtn_home.addActionListener (this);// 홈 가기 액션 버튼
		jbtn_lobby.addActionListener (this);// 로비 가기 액션 버튼
		jbtn_settings.addActionListener (this);// 설정 가기 액션 버튼
		jbtn_logout.addActionListener (this);// 종료 액션 버튼
		
		jf_settings.setTitle ("설정"); // 프레임 이름
		jf_settings.setSize (600, 900); // 프레임 사이즈
		jf_settings.setLayout (null); // 레이아웃 설정
		jf_settings.setResizable (false);// 창 크기 조절 불가
		jf_settings.setLocationRelativeTo (null); // 창 위치 중앙
		jf_settings.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);// 창 닫기 버튼 누르면 프로그램 종료
		
		jbtn_chgBG.setBounds (50, 50, 500, 50); // 배경화면 변경 버튼 위치, 크기 설정
		jbtn_chgName.setBounds (50, 120, 500, 50);// 닉네임 변경 버튼 위치, 크기 설정
		jbtn_chgPW.setBounds (50, 190, 500, 50);// 비밀번호 변경 버튼 위치, 크기 설정
		jbtn_signout.setBounds (50, 260, 500, 50);// 탈퇴하기 버튼 위치, 크기 설정
		jbtn_home.setBounds (0, 830, 150, 40);// 홈 가기 버튼 위치, 크기 설정
		jbtn_lobby.setBounds (150, 830, 150, 40);// 로비 가기 버튼 위치, 크기 설정
		jbtn_settings.setBounds (300, 830, 150, 40);// 설정 가기 버튼 위치, 크기 설정
		jbtn_logout.setBounds (450, 830, 150, 40);// 종료 버튼 위치, 크기 설정
		
		jf_settings.add (jbtn_chgBG);
		jf_settings.add (jbtn_chgName);
		jf_settings.add (jbtn_chgPW);
		jf_settings.add (jbtn_signout);
		jf_settings.add (jbtn_home);
		jf_settings.add (jbtn_lobby);
		jf_settings.add (jbtn_settings);
		jf_settings.add (jbtn_logout);
		
		jf_settings.setVisible (true);
	}
	
	public static void main (String[] args) {
		SettingsView sv = new SettingsView ();
		sv.initDisplay ();
		
	}
	
	@Override
	public void actionPerformed (ActionEvent e) {
		Object obj = e.getSource ();
		
		if (obj == jbtn_chgBG) {
			setTitle ("배경화면 변경");
			jf_settings.dispose ();
			SettingsBG sv = new SettingsBG ();
			sv.initDisplay ();
			System.out.println ("배경화면 변경");
			/* 파일 탐색기방식으로 변경 하려면 아래 코드 주석 해제
			JFileChooser fileChooser = new JFileChooser (); // 파일 선택 창
			int result = fileChooser.showOpenDialog (jf_settings); // 파일 선택 창 띄우기
			if (result == JFileChooser.APPROVE_OPTION) { // 파일 선택 후 확인 버튼 누르면
				File file = fileChooser.getSelectedFile (); // 선택한 파일 가져오기
				System.out.println ("파일 경로 : " + file.getPath ()); // 파일 경로 출력
				System.out.println ("파일 이름 : " + file.getName ());// 파일 이름 출력
				String filePath = file.getPath ();
				String fileName = file.getName ();
			}*/
			
		} else if (obj == jbtn_chgName) {
			setTitle ("닉네임 변경");
			System.out.println ("닉네임 변경");
			// 닉네임 변경
			String newName = JOptionPane.showInputDialog (jf_settings, "변경할 닉네임 입력", "닉네임 변경", JOptionPane.INFORMATION_MESSAGE);
			System.out.println ("변경할 닉네임 : " + newName);
			// DB에 저장
		} else if (obj == jbtn_chgPW) {
			setTitle ("비밀번호 변경");
			System.out.println ("비밀번호 변경");
			// 비밀번호 변경
			String newPW = JOptionPane.showInputDialog (jf_settings, "변경할 비밀번호 입력", "비밀번호 변경", JOptionPane.INFORMATION_MESSAGE);
			System.out.println ("변경할 비밀번호 : " + newPW);
			// DB에 저장
		} else if (obj == jbtn_signout) {
			setTitle ("탈퇴하기");
			System.out.println ("탈퇴하기");
			// 탈퇴하기
			int result = JOptionPane.showConfirmDialog (jf_settings, "정말 탈퇴하시겠습니까?", "탈퇴", JOptionPane.YES_NO_OPTION);
			if (result == JOptionPane.YES_OPTION) {
				JOptionPane.showMessageDialog (jf_settings, "탈퇴되었습니다.", "탈퇴", JOptionPane.INFORMATION_MESSAGE);
				// DB에서 삭제
				// 로비로 이동
			}
		} else if (obj == jbtn_home) {
			setTitle ("홈");
			System.out.println ("홈");
			// 홈으로 이동ㅎ
		} else if (obj == jbtn_lobby) {
			setTitle ("로비");
			System.out.println ("로비");
			// 로비로 이동
		} else if (obj == jbtn_settings) {
			setTitle ("설정");
			System.out.println ("설정");
			// 설정으로 이동
		} else if (obj == jbtn_logout) {
			setTitle ("로그아웃");
			System.out.println ("로그아웃");
			System.exit (0);
			
			
		}
	}
}
/*} else if (obj == jbtn_chgName) {
			System.out.println ("닉네임 변경");
			// 닉네임 변경
			String nickname = JOptionPane.showInputDialog (jf_settings, "변경할 닉네임을 입력하세요");
			if (nickname != null) {
				System.out.println ("변경할 닉네임 : " + nickname);
				// 닉네임 변경
				// DB에 저장
			}
		} else if (obj == jbtn_chgPW) {
			System.out.println ("비밀번호 변경");
			// 비밀번호 변경
			String password = JOptionPane.showInputDialog (jf_settings, "변경할 비밀번호를 입력하세요");
			if (password != null) {
				System.out.println ("변경할 비밀번호 : " + password);
			
				
			
			} else if (obj == jbtn_signout){
				System.out.println ("탈퇴");
				// 탈퇴 확인
				String out = JOptionPane.showInputDialog (jf_settings, "정말로 탈퇴하시겠습니까?");
				if (out != null) {
					System.out.println ("정말로 탈퇴하시겠습니까 ? : " + out);
				}
			}*/