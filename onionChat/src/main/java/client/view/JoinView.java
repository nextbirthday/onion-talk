package client.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import util.dto.Account;

@SuppressWarnings( "serial" )
public class JoinView implements ActionListener {
    
    boolean        isIdCheck      = false;
    String         imgPath        = "C:\\Users\\HOJAE\\Desktop\\Java\\workout\\images\\";
    JDialog        jdl_join       = new JDialog(); // 회원가입 프레임
    JPanel         jp_join        = new JPanel( null ); // 회원가입 도화지
    JLabel         jlb_name       = new JLabel( "이름" );
    JLabel         jlb_id         = new JLabel( "아이디" );
    JLabel         jlb_pw         = new JLabel( "비밀번호" );
    JLabel         jlb_pw2        = new JLabel( "비밀번호확인" );
    JLabel         jlb_birth      = new JLabel( "생년월일" );
    JLabel         jlb_phone      = new JLabel( "전화번호" );
    JLabel         jlb_nickName   = new JLabel( "닉네임" );
    JLabel         jlb_idAvble    = new JLabel( "사용가능한 아이디 입니다." );
    JLabel         jlb_idNotAvble = new JLabel( "중복된 아이디 입니다." );
    JLabel         jlb_title      = new JLabel( "회원가입" );// 회원가입 , title 라벨
    JTextField     jtf_name       = new JTextField(); // 이름
    JTextField     jtf_id         = new JTextField(); // 아이디
    JPasswordField jtf_pw         = new JPasswordField(); // 비밀번호
    JPasswordField jtf_pw2        = new JPasswordField(); // 비밀번호 확인
    JTextField     jtf_birth      = new JTextField(); // 생년월일
    JTextField     jtf_phone      = new JTextField(); // 폰번호
    JTextField     jtf_nickName   = new JTextField(); // 닉네임
    ImageIcon      imgic_join     = new ImageIcon( imgPath + "bt_join.png" ); // 로그인 버튼 이미지
    JButton        jbtn_idconfirm = new JButton( "중복검사" ); // 로그인 버튼
    JButton        jbtn_join      = new JButton( imgic_join );// 회원가입 버튼
    Font           f_join         = new Font( "맑은 고딕", Font.PLAIN, 25 );
    Font           f_label        = new Font( "맑은 고딕", Font.PLAIN, 12 );
    
    SignUpModel signUpModel = new SignUpModel( this );
    
    public void initDisplay() {
        
        jbtn_join.addActionListener( this );
        jbtn_idconfirm.addActionListener( this );
        // 타이틀 부분
        ImageIcon bookIcon = new ImageIcon( imgPath + "title.png" );
        jdl_join.setIconImage( bookIcon.getImage() );
        jdl_join.setTitle( "양파쿵야 톡" );
        // 정보입력 부분
        jp_join.add( jtf_name );// 이름
        jp_join.add( jlb_name );
        jtf_name.setBounds( 95, 100, 180, 35 );
        jlb_name.setBounds( 57, 100, 200, 35 );
        jlb_name.setFont( f_label );
        jp_join.add( jtf_id );// 아이디
        jp_join.add( jlb_id );
        jtf_id.setBounds( 95, 145, 180, 35 );
        jlb_id.setBounds( 45, 145, 200, 35 );
        jlb_id.setFont( f_label );
        jp_join.add( jlb_idAvble );// 아이디 중복검사 결과
        jlb_idAvble.setVisible( false );
        jp_join.add( jlb_idNotAvble );
        jlb_idAvble.setBounds( 95, 180, 180, 35 );
        jlb_idNotAvble.setBounds( 95, 180, 200, 35 );
        jlb_idNotAvble.setVisible( false );
        jp_join.add( jtf_pw );// 비밀번호
        jp_join.add( jlb_pw );
        jtf_pw.setBounds( 95, 210, 180, 35 );
        jlb_pw.setBounds( 35, 210, 200, 35 );
        jlb_pw.setFont( f_label );
        jp_join.add( jtf_pw2 );// 비밀번호확인
        jp_join.add( jlb_pw2 );
        jtf_pw2.setBounds( 95, 255, 180, 35 );
        jlb_pw2.setBounds( 10, 255, 200, 35 );
        jlb_pw2.setFont( f_label );
        jp_join.add( jtf_birth );// 생년월일
        jp_join.add( jlb_birth );
        jtf_birth.setBounds( 95, 300, 180, 35 );
        jlb_birth.setBounds( 35, 300, 200, 35 );
        jlb_birth.setFont( f_label );
        jp_join.add( jtf_phone );// 전화번호
        jp_join.add( jlb_phone );
        jtf_phone.setBounds( 95, 345, 180, 35 );
        jlb_phone.setBounds( 35, 345, 200, 35 );
        jlb_phone.setFont( f_label );
        jp_join.add( jtf_nickName );// 닉네임
        jp_join.add( jlb_nickName );
        jtf_nickName.setBounds( 95, 390, 180, 35 );
        jlb_nickName.setBounds( 45, 390, 200, 35 );
        
        jbtn_idconfirm.setBorderPainted( false ); // 아이디 중복검사 버튼 외곽 라인 없애기
        jbtn_idconfirm.setForeground( Color.WHITE ); // 아이디 중복검사 버튼 텍스트 색깔 (흰색)
        jbtn_idconfirm.setBackground( new Color( 0, 64, 0 ) ); // 아이디 중복검사 버튼 색깔 넣기 (갈색)
        jbtn_idconfirm.setBounds( 285, 145, 90, 35 );
        jbtn_join.setBounds( 42, 450, 300, 45 );
        jlb_title.setFont( f_join );// 회원가입 라벨 붙이기
        jlb_title.setBounds( 20, 30, 125, 45 );
        jp_join.add( jlb_title );// 회원가입 라벨 왼쪽 상단에 붙이기
        jp_join.add( jbtn_join );// 회원가입 버튼
        jp_join.add( jbtn_idconfirm );// 아이디 중복검사 버튼
        jp_join.setBackground( new Color( 146, 208, 80 ) ); // 도화지 색깔 노란색
        // JDialog, 회원가입 메인창 정의
        jdl_join.setTitle( "회원가입" );
        jdl_join.setContentPane( jp_join );
        jdl_join.setSize( 400, 600 );
        jdl_join.setLocationRelativeTo( null );// 창 가운데서 띄우기
        jdl_join.setVisible( true );
    }
    
    // // 단위테스트용
    // public static void main( String[] args ) {
    // JoinView join = new JoinView();
    // join.initDisplay();
    // }
    
    private void showDialog( String message ) {
        // showMessageDialog (Component parentComponent, Object message)
        // 첫 번째 파라미터 자리에 null이 들어가 있으면 지 혼자이고, Component가 들어오면
        JOptionPane.showMessageDialog( jdl_join, message );
    }
    
    @Override
    public void actionPerformed( ActionEvent e ) {
        Object object = e.getSource();
        
        if ( object == jbtn_idconfirm ) {
            int result = signUpModel.idCheck();
            
            if ( result == 0 ) {
                jlb_idNotAvble.setVisible( false );
                jlb_idAvble.setVisible( true );
                isIdCheck = true;
            }
            else {
                jlb_idAvble.setVisible( false );
                jlb_idNotAvble.setVisible( true );
                isIdCheck = false;
            }
        }
        
        /*
         * 회원가입 창에서 회원가입을 눌렀을 때 DTO를 Model로 보내주고
         * 그 Model에서 DTO로 쿼리(Query)를 구성한다. 구성된 쿼리를 DB(오라클)로 날려준다.
         * 그리고 그 결과를 Model에서 받아서 결과에 맞게 로직을 처리한다.
         * 사용자가 조건에 맞게 Account 정보 입력 시 회원가입 성공 창을 띄워준다.
         */
        
        if ( object == jbtn_join ) {
            
            if ( isIdCheck ) {
                
                // AND 또는 OR 연산자 사용 시 앞 쪽 조건문의 True, false 여부에 따라 뒤쪽 조건문의 실행여부 판단.
                
                if ( jtf_id.getText().length() > 0 && jtf_name.getText().length() > 0 && jtf_nickName.getText().length() > 0
                                && jtf_birth.getText().length() > 0 && jtf_phone.getText().length() > 0 ) {
                    
                    if ( String.valueOf( jtf_pw.getPassword() ).length() > 0
                                    && String.valueOf( jtf_pw.getPassword() ).equals( String.valueOf( jtf_pw2.getPassword() ) ) ) {
                        int result = signUpModel.register( new Account( jtf_id.getText(), String.valueOf( jtf_pw.getPassword() ),
                                        jtf_name.getText(), jtf_nickName.getText(), jtf_birth.getText(), jtf_phone.getText() ) );
                        
                        if ( result > 0 ) {
                            showDialog( "회원가입 완료" );
                            jdl_join.dispose();
                        }
                        else {
                            showDialog( "회원가입 실패, 다시 시도해주세요." );
                        }
                        
                    }
                    else {
                        showDialog( "비밀번호가 일치하지 않습니다." );
                    }
                    
                }
                else {
                    showDialog( "입력되지 않은 정보가 있습니다." );
                }
                
            }
            else {
                showDialog( "ID 중복체크를 먼저 진행해주세요." );
            }
            
        }
    }
}
