package client.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;

// 채팅 목록
@SuppressWarnings( "serial" )
public class ChatList extends JFrame implements ActionListener {
    // 선언부
    String    imgPath   = "D:\\vscode_java\\dev_java\\app\\src\\main\\java\\dev_java\\images\\oniontalk\\";
    ImageIcon titleIcon = new ImageIcon( imgPath + "onion22.png" );
    JFrame    jf        = new JFrame();
    JPanel    jp_main   = new JPanel(); // 메인 도화지
    JPanel    jp_btn    = new JPanel(); // 버튼 도화지
    JPanel    jp_south  = new JPanel(); // 하반부 버튼 도화지
    JPanel    jp_north  = new JPanel(); // 삭제 버튼 도화지
    JLabel    jlb_list  = new JLabel( "채팅 목록" );
    
    // 채팅방 입장 버튼
    JButton btn_enterRoom  = new JButton( "입장" );
    JButton btn_enterRoom2 = new JButton( "입장" );
    JButton btn_enterRoom3 = new JButton( "입장" );
    JButton btn_enterRoom4 = new JButton( "입장" );
    JButton btn_exit       = new JButton( "삭제" );
    
    // 하단부 버튼
    JButton btn_home   = new JButton( "홈" );
    JButton btn_chat   = new JButton( "채팅방" );
    JButton btn_set    = new JButton( "설정" );
    JButton btn_logout = new JButton( "로그아웃" );
    
    // 생성
    public ChatList() {
        btn_enterRoom.addActionListener( this );
        btn_enterRoom2.addActionListener( this );
        btn_enterRoom3.addActionListener( this );
        btn_enterRoom4.addActionListener( this );
        btn_home.addActionListener( this );
        btn_chat.addActionListener( this );
        btn_set.addActionListener( this );
        btn_logout.addActionListener( this );
        btn_exit.addActionListener( this );
        initDisplay();
    }
    
    // 화면
    public void initDisplay() {
        ImageIcon titleIcon = new ImageIcon( imgPath + "onion.png" );
        jf.setIconImage( titleIcon.getImage() ); // 타이틀 이미지
        jf.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        
        // 판넬 색상
        jp_main.setBackground( new Color( 169, 209, 142 ) );
        jp_btn.setBackground( new Color( 169, 209, 142 ) );
        jp_south.setBackground( new Color( 169, 209, 142 ) );
        
        // 채팅방 버튼 색상
        btn_enterRoom.setBackground( new Color( 84, 130, 53 ) );
        btn_enterRoom2.setBackground( new Color( 84, 130, 53 ) );
        btn_enterRoom3.setBackground( new Color( 84, 130, 53 ) );
        btn_enterRoom4.setBackground( new Color( 84, 130, 53 ) );
        
        btn_exit.setBackground( new Color( 84, 130, 53 ) );
        btn_exit.setForeground( Color.WHITE );
        
        // 버튼 테두리
        btn_enterRoom.setForeground( Color.WHITE );
        btn_enterRoom2.setForeground( Color.WHITE );
        btn_enterRoom3.setForeground( Color.WHITE );
        btn_enterRoom4.setForeground( Color.WHITE );
        
        // 버튼 판넬 세로 정렬
        jp_btn.setLayout( new BoxLayout( jp_btn, BoxLayout.Y_AXIS ) );
        
        // 버튼 판넬에 붙히기
        jp_btn.add( btn_enterRoom );
        jp_btn.add( Box.createVerticalStrut( 10 ) );
        
        jp_btn.add( btn_enterRoom2 );
        jp_btn.add( Box.createVerticalStrut( 10 ) );
        jp_btn.add( btn_enterRoom3 );
        jp_btn.add( Box.createVerticalStrut( 10 ) );
        jp_btn.add( btn_enterRoom4 );
        
        jp_north.add( btn_exit );
        jp_north.setLayout( new FlowLayout( FlowLayout.RIGHT ) );
        
        // 하단부 버튼 색상
        btn_home.setBackground( new Color( 226, 240, 217 ) );
        btn_chat.setBackground( new Color( 226, 240, 217 ) );
        btn_set.setBackground( new Color( 226, 240, 217 ) );
        btn_logout.setBackground( new Color( 226, 240, 217 ) );
        
        // 판넬에 하단부 버튼 붙히기
        jp_south.add( btn_home );
        jp_south.add( btn_chat );
        jp_south.add( btn_set );
        jp_south.add( btn_logout );
        
        // 속지 붙히기
        jf.add( "North", jp_north );
        jf.add( "Center", jp_btn );
        jf.add( "South", jp_south );
        
        // 타이틀, 화면 띄우기
        jf.setTitle( "양파쿵야 Talk" );
        jf.setSize( 400, 600 );
        jf.setLocationRelativeTo( null ); // 창 가운데 띄우기
        jf.setVisible( true );
        ;
    }
    
    public static void main( String[] args ) {
        new ChatList();
    }
    
    @Override
    public void actionPerformed( ActionEvent arg0 ) {
        // TODO Auto-generated method stub
        
    }
}