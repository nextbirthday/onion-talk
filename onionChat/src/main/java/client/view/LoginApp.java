package client.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

import lombok.extern.log4j.Log4j2;
import util.dto.Account;

@Log4j2
@SuppressWarnings( "serial" )
public class LoginApp implements ActionListener {
    
    public static String myId     = null; // 나의 아이디
    String               imgPath  = "C:\\Users\\HOJAE\\Desktop\\Java\\workout\\images\\";
    JLabel               msg      = new JLabel();
    JFrame               jf_login = new JFrame(); // 메인 프레임
    JPanel               jp_login = new JPanel( null ); // 제일 큰 도화지
    // 아이디, 비밀번호 입력을 위한 JTextField (테두리선을 지우기위해 클래스 재정의)
    JTextField     jtf_id          = new JTextField() {
                                       @Override
                                       public void setBorder( Border border ) {}
                                   };
    JPasswordField jtf_pw          = new JPasswordField() {
                                       @Override
                                       public void setBorder( Border border ) {}
                                   };
    JLabel         jlb_join        = new JLabel(); // 회원가입 라벨버튼
    JLabel         jlb_infomissing = new JLabel(); // 아이디/비밀번호 분실 라벨버튼
    JLabel         jlb_idtext      = new JLabel( "  아이디를 입력하세요" );
    JLabel         jlb_pwtext      = new JLabel( "  비밀번호를 입력하세요" );
    Font           f_join          = new Font( "맑은 고딕", Font.PLAIN, 12 );
    ImageIcon      img_main        = new ImageIcon( imgPath + "main2.png" ); // 메인 바나나 이미지 아이콘
    ImageIcon      img_loginbt     = new ImageIcon( imgPath + "bt_login.png" ); // 로그인 이미지 아이콘
    JButton        jbtn_main       = new JButton( img_main ); // 메인 사진 붙이기용 버튼
    JButton        jbtn_login      = new JButton( img_loginbt ); // 로그인 버튼
    Font           msgf            = new Font( "맑은 고딕", Font.PLAIN, 12 );
    
    public void initDisplay() {
        jf_login.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        // ActionListener
        jbtn_login.addActionListener( this );
        // 아이디를 입력해주세요, 비밀번호를 입력해주세요. 미리 보여질 글자를 담고있는 JLabel 정의
        msg.setFont( msgf );
        // msg.setText("로그인");
        // (60, 400, 270, 45)
        msg.setBounds( 65, 445, 270, 45 );
        jlb_idtext.setForeground( Color.GRAY );
        jlb_pwtext.setForeground( Color.GRAY );
        jlb_idtext.setBounds( 60, 300, 270, 45 );
        jlb_pwtext.setBounds( 60, 340, 270, 45 );
        jp_login.add( jlb_idtext );
        jp_login.add( jlb_pwtext );
        // 도화지에 JTextField(ip,pw입력), JLabel(회원가입, 분실정보찾기), JButton(바나나이미지, 로그인버튼) 붙임
        jp_login.add( msg );
        jp_login.add( jtf_id );
        jp_login.add( jtf_pw );
        jp_login.add( jlb_join );
        jp_login.add( jlb_infomissing );
        jp_login.add( jbtn_login );
        jp_login.add( jbtn_main );
        
        // 아이디 비밀번호 입력창 고정 및 비밀번호 암호 *로 표시
        jtf_id.setBounds( 60, 300, 270, 45 );
        jtf_pw.setBounds( 60, 340, 270, 45 );
        jtf_pw.setEchoChar( '♣' );
        // MouseListener 회원가입, 아이디/비밀번호 분실에 대한 JLabel 버튼화
        // 아이디/비밀번호 찾기 라벨버튼 정의
        jlb_infomissing.setText( "<HTML><U>ID/PW 분실</U></HTML>" );
        jlb_infomissing.setForeground( Color.BLACK );
        jlb_infomissing.setFont( f_join );
        jlb_infomissing.setBounds( 280, 500, 200, 20 );
        jlb_infomissing.addMouseListener( new MouseAdapter() {
            @Override
            public void mousePressed( MouseEvent e ) {
                // TODO Auto-generated method stub
                super.mousePressed( e );
                FindIdPwView fipv = new FindIdPwView();
                fipv.initDisplay();
            }
            
        } );
        // 회원가입 라벨버튼 정의
        jlb_join.setText( "<HTML><U>회원가입</U></HTML>" );
        jlb_join.setForeground( Color.BLACK );
        jlb_join.setFont( f_join );
        jlb_join.setBounds( 30, 500, 150, 20 );
        jlb_join.addMouseListener( new MouseAdapter() {
            @Override
            public void mousePressed( MouseEvent e ) {
                super.mousePressed( e );
                JoinView join = new JoinView();
                join.initDisplay();
            }
            
        } );
        // 로그인 버튼 정의
        jbtn_login.setBorderPainted( false );
        jbtn_login.setBounds( 60, 400, 270, 45 );
        // KeyListener : 엔터키 누르면 로그인 버튼 눌림
        jbtn_login.addKeyListener( new KeyAdapter() {
            @Override
            public void keyPressed( KeyEvent e ) {
                
                if ( e.getKeyCode() == KeyEvent.VK_ENTER ) {
                    // join.initDisplay();
                }
            }
        } );
        jbtn_main.setBackground( new Color( 146, 208, 80 ) );
        jbtn_main.setBorderPainted( false ); // 버튼 외곽선 없애기
        jbtn_main.setBounds( 60, 35, 270, 250 );
        jp_login.setBackground( new Color( 146, 208, 80 ) ); // 도화지 색깔 노란색
        // JFrame, 메인프레임 정의
        jf_login.setTitle( "양파쿵야 톡" );
        jf_login.setContentPane( jp_login ); // 액자에 도화지 끼우기
        jf_login.setSize( 400, 600 );
        jf_login.setLocationRelativeTo( null );// 창 가운데서 띄우기
        jf_login.setVisible( true );
    }
    
    private void showDialog( String message ) {
        // showMessageDialog (Component parentComponent, Object message)
        // 첫 번째 파라미터 자리에 null이 들어가 있으면 지 혼자이고, Component가 들어오면
        JOptionPane.showMessageDialog( jf_login, message );
    }
    
    public static void main( String[] args ) {
        LoginApp pt = new LoginApp();
        pt.initDisplay();
    }
    
    @Override
    public void actionPerformed( ActionEvent e ) {
        // join = new Join();
        // if(jtf_id.getText() 뭘 포함하지 않으면 false) {
        //
        // }else {
        // JOptionPane.showMessageDialog( null, "형식에 맞게 입력해주세요." );
        // }
        Object obj = e.getSource();
        
        if ( obj == jbtn_login ) {
            SignUpModel model = new SignUpModel();
            
            model.signIn( new Account( jtf_id.getText(), String.valueOf( jtf_pw.getPassword() ), null, null, null, myId, null, null ) );
            System.out.println( myId );
            
            if ( myId != null ) {
                // ChatRoomView chatView = new ChatRoomView( true, myId );
                FriendList friendList = new FriendList();
                jf_login.dispose();
            }
            else {
                showDialog( "아이디 또는 비밀번호를 확인해주세요." );
            }
        }
    }
}
