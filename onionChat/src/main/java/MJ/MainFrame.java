package MJ;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class MainFrame extends Frame implements MouseListener {
	Label lbl;
	Font fnt = new Font("굴림", Font.BOLD, 30);
	MainFrame() {
		super("배경화면 1");
		this.setVisible(true);
		this.setBounds(700, 400, 300, 300);
		this.setLayout(null);
		this.addWindowListener(new MyWinExit());
		lbl = new Label("다음 (클릭) ▶▶▶",1);
		lbl.setBackground(Color.yellow);
		lbl.setBounds(70, 120, 130, 20);
		add(lbl);
		lbl.addMouseListener(this);
	}
	//paint() 메소드 구현
	public void paint(Graphics g){
		g.drawString("배경화면1", 70, 100);
	}
	// 윈도우 종료 클래스
	public class MyWinExit extends WindowAdapter {
		public void windowClosing(WindowEvent we) {
			System.exit(0); // 윈도 종료
		}
	}
	@Override  //마우스 이벤트  인터페이스 구현
	public void mouseClicked(MouseEvent e) {
		new MainFrame_Second(); //여기가 프레임 전환 역할
		this.setVisible(false);
	}
	@Override    //마우스 이벤트  인터페이스 구현
	public void mousePressed(MouseEvent e) {/*구현생략*/}
	@Override  //마우스 이벤트  인터페이스 구현
	public void mouseReleased(MouseEvent e) {/*구현생략*/}
	@Override  //마우스 이벤트  인터페이스 구현
	public void mouseEntered(MouseEvent e) {/*구현생략*/}
	@Override  //마우스 이벤트  인터페이스 구현
	public void mouseExited(MouseEvent e) {/*구현생략*/}
}//첫 번째 프레임 끝

class MainFrame_Second extends Frame implements MouseListener {
	private static final long serialVersionUID = 1L;
	Label lbl;
	MainFrame_Second() {
		super("창 전환  2번 프레임");
		this.setVisible(true);
		this.setBounds(700, 400, 300, 300);
		this.setLayout(null);
		this.addWindowListener(new MyWinExit());
		
		lbl = new Label("다음 (클릭) ▶▶▶",1);
		lbl.setBackground(Color.yellow);
		lbl.setBounds(70, 120, 130, 20);
		add(lbl);
		lbl.addMouseListener(this);
	}
	//paint() 메소드 구현
	public void paint(Graphics g){
		g.drawString("여기는 두 번째 프레임22222", 70, 100);
	}
	// 윈도우 종료 클래스
	public class MyWinExit extends WindowAdapter {
		public void windowClosing(WindowEvent we) {
			System.exit(0); // 윈도 종료
		}
	}
	@Override  //마우스 이벤트  인터페이스 구현
	public void mouseClicked(MouseEvent e) {
		new MainFrame();   //여기가 프레임 전환 역할
		this.setVisible(false);
	}
	@Override    //마우스 이벤트  인터페이스 구현
	public void mousePressed(MouseEvent e) {/*구현생략*/}
	@Override  //마우스 이벤트  인터페이스 구현
	public void mouseReleased(MouseEvent e) {/*구현생략*/}
	@Override  //마우스 이벤트  인터페이스 구현
	public void mouseEntered(MouseEvent e) {/*구현생략*/}
	@Override  //마우스 이벤트  인터페이스 구현
	public void mouseExited(MouseEvent e) {/*구현생략*/}
}
//두 번째 프레임 끝

//메인 함수
