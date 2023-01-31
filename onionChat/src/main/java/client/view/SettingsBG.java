package client.view;

import util.dto.Account;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

@SuppressWarnings ("serial")
public class SettingsBG extends JFrame implements ActionListener {
	String imgPath = "C:\\Users\\thdau\\eclipse-workspace\\onion-talk\\onionChat\\src\\main\\java\\client\\view\\images\\";
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
	JButton btn_home = new JButton ("친구");// 홈 가기 버튼
	JButton btn_lobby = new JButton ("채팅");// 로비 가기 버튼
	JButton btn_settings = new JButton ("설정");// 설정 가기 버튼
	JButton btn_logout = new JButton ("로그아웃");// 종료 버튼
	
	Font msgf = new Font ("맑은 고딕", Font.BOLD, 12);
	
	Image Icon1 = new ImageIcon (imgPath + "1.png").getImage ().getScaledInstance (30, 30, 0);
	Image Icon2 = null;
	Image Icon3 = null;
	Image Icon4 = null;
	Image Icon5 = null;
	
	
	//	======================================= 이벤트 처리 ====================================================
	
	public static void main (String[] args) {
		SettingsBG sbg = new SettingsBG ();
		sbg.initDisplay ();
		
	}
	
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
	
	@Override
	public void actionPerformed (ActionEvent e) {
//		======================================하단버튼4개=======================================
		Object obj = e.getSource ();
		
		if (obj == btn_home) {
//			jp_setting.dispose ();  // 배경화면 설정 창 닫기
			jlb_bgname.setText ("Home");
			jlb_bgname.setIcon (new ImageIcon (imgPath + "home.png"));
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
			setTitle ("로그아웃");
			System.out.println ("로그아웃");
			int result = JOptionPane.showConfirmDialog (jf_setting, "로그아웃 하시겠습니까?", "로그아웃 확인", JOptionPane.YES_NO_OPTION);
			if (result == JOptionPane.YES_OPTION) {
				JOptionPane.showMessageDialog (jf_setting, "다음에 또 만나요~", "로그아웃", JOptionPane.INFORMATION_MESSAGE);
//				jf_setting.dispose ();
				System.exit (0);
				if (result == JOptionPane.NO_OPTION) {
					JOptionPane.showMessageDialog (jf_setting, "로그아웃 취소", "로그아웃 취소", JOptionPane.INFORMATION_MESSAGE);
				}
			}


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

//===========================================배경화면 변경 버튼===============================================
		} else if (obj == btn_setbg1) {
			if (jlb_bgname.getText ().equals ("배경화면 1")) {
				Icon1.getGraphics ();
			} else {
				BGset bGset = new BGset ();
				Graphics g = jf_setting.getGraphics ();
				bGset.paintComponent (g);
				
				Account account = new Account ();
				account.setUser_msg ("배경화면 1이 선택되었습니다.");
			}
			JOptionPane.showMessageDialog (jp_setting, "배경화면 1이 선택되었습니다.");
		} else if (obj == btn_setbg2) {
			if (jlb_bgname.getText ().equals ("")) {
				jlb_bgname.setIcon (new ImageIcon (imgPath + "1.png"));
			} else {
				BGset bGset = new BGset ();
				Graphics g = jf_setting.getGraphics ();
				bGset.paintComponent (g);
			}
			JOptionPane.showMessageDialog (jp_setting, "배경화면 2이 선택되었습니다.");
			
			
		} else if (obj == btn_setbg3) {
			if (jlb_bgname.getText ().equals (null)) {
				jlb_bgname.setIcon (new ImageIcon (imgPath + "1.png"));
			} else {
				BGset bGset = new BGset ();
				Graphics g = jf_setting.getGraphics ();
				bGset.paintComponent (g);
			}
			JOptionPane.showMessageDialog (jp_setting, "배경화면 3이 선택되었습니다.");
			
			
		} else if (obj == btn_setbg4) {
			if (jlb_bgname.getText ().equals ("Home")) {
				jlb_bgname.setIcon (new ImageIcon (imgPath + "1.png"));
			} else {
				BGset bGset = new BGset ();
				Graphics g = jf_setting.getGraphics ();
				bGset.paintComponent (g);
			}
			JOptionPane.showMessageDialog (jp_setting, "배경화면 4이 선택되었습니다.");
			
		} else if (obj == btn_setbg5) {
			if (jlb_bgname.getText ().equals ("Home")) {
				jlb_bgname.setIcon (new ImageIcon (imgPath + "1.png"));
			} else {
				BGset bGset = new BGset ();
				Graphics g = jf_setting.getGraphics ();
				bGset.paintComponent (g);
			}
			JOptionPane.showMessageDialog (jp_setting, "배경화면 5이 선택되었습니다.");
		}
	}
	
	class BGset extends JPanel {
		ImageIcon icon1 = new ImageIcon ("C:\\Users\\thdau\\eclipse-workspace\\onion-talk\\onionChat\\src\\main\\java\\client\\view\\images\\1.png");
		ImageIcon icon2 = new ImageIcon ("C:\\Users\\thdau\\eclipse-workspace\\onion-talk\\onionChat\\src\\main\\java\\client\\view\\images\\2.png");
		ImageIcon icon3 = new ImageIcon ("C:\\Users\\thdau\\eclipse-workspace\\onion-talk\\onionChat\\src\\main\\java\\client\\view\\images\\3.png");
		ImageIcon icon4 = new ImageIcon ("C:\\Users\\thdau\\eclipse-workspace\\onion-talk\\onionChat\\src\\main\\java\\client\\view\\images\\4.png");
		ImageIcon icon5 = new ImageIcon ("C:\\Users\\thdau\\eclipse-workspace\\onion-talk\\onionChat\\src\\main\\java\\client\\view\\images\\5.png");
		Image img1 = icon1.getImage ();
		Image img2 = icon2.getImage ();
		Image img3 = icon3.getImage ();
		Image img4 = icon4.getImage ();
		Image img5 = icon5.getImage ();
		
		public void paintComponent (Graphics g) {
			try {
				g.drawImage (ImageIO.read (new File ("C:\\Users\\thdau\\eclipse-workspace\\onion-talk\\onionChat\\src\\main\\java\\client\\view\\images\\1.png")), 0, 0, null);
				g.drawImage (ImageIO.read (new File ("C:\\Users\\thdau\\eclipse-workspace\\onion-talk\\onionChat\\src\\main\\java\\client\\view\\images\\2.png")), 0, 0, null);
				g.drawImage (ImageIO.read (new File ("C:\\Users\\thdau\\eclipse-workspace\\onion-talk\\onionChat\\src\\main\\java\\client\\view\\images\\3.png")), 0, 0, null);
				g.drawImage (ImageIO.read (new File ("C:\\Users\\thdau\\eclipse-workspace\\onion-talk\\onionChat\\src\\main\\java\\client\\view\\images\\4.png")), 0, 0, null);
				g.drawImage (ImageIO.read (new File ("C:\\Users\\thdau\\eclipse-workspace\\onion-talk\\onionChat\\src\\main\\java\\client\\view\\images\\5.png")), 0, 0, null);
//				setOpaque (false);
//				super.paintComponent (g);
			} catch (IOException e) {
				e.printStackTrace ();
			}
		}
	}
}