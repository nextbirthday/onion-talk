package client.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

import model.FindIDPWLogic;
import util.dto.Account;

@SuppressWarnings( "serial" )
public class FindIdPwView extends JDialog {
    FindIDPWLogic findIdPwLogic = new FindIDPWLogic();
    SignUpModel   model         = new SignUpModel();
    Account       acc           = new Account();
    String        mid           = null;
    JDialog       jdl_infomiss  = new JDialog();// 아이디/비밀번호찾기 프레임
    // CardLayout card = new CardLayout();
    // JPanel jp_cardbox = new JPanel();
    JPanel  jp_idmiss       = new JPanel(); // 1.첫 화면 도화지
    JPanel  jp_idfind       = new JPanel(); // 2.아이디 찾기 성공했을 시 도화지
    JPanel  jp_pwmiss       = new JPanel(); // 3.비밀번호찾기 탭 눌렀을 때 도화지
    JPanel  jp_pwfind       = new JPanel(); // 4.비밀번호찾기 성공했을 시 도화지
    JPanel  jp_north        = new JPanel();
    JLabel  jlb_idfind      = new JLabel();// 2번 도화지에 붙이는 JLabel
    JLabel  jlb_name        = new JLabel( "이름" ); // 1번 도화지에 붙는 JLabel
    JLabel  jlb_phone       = new JLabel( "전화번호" ); // 1번 도화지에 붙는 JLabel
    JLabel  jlb_id          = new JLabel( "아이디" ); // 3번 도화지에 붙는 JLabel
    JLabel  jlb_name2       = new JLabel( "이름" ); // 3번 도화지에 붙는 JLabel
    JLabel  jlb_pwupdate    = new JLabel( "비밀번호" );
    JLabel  jlb_pwupdate2   = new JLabel( "비밀번호확인" );
    JButton jbtn_pwupdate   = new JButton( "비밀번호 변경" );// 3번 도화지에 붙이는 비밀번호 찾기 버튼 (성공시 4번 도화지로 전환)
    JButton jbtn_pwsearch   = new JButton( "비밀번호 찾기" );// 3번 도화지에 붙이는 비밀번호 찾기 버튼 (성공시 4번 도화지로 전환)
    JButton jbtn_gotopwmiss = new JButton( "비밀번호찾기" ); // 2번 도화지에 붙는 비밀번호 찾기 버튼(성공지 3번도화지로 전환)
    JButton jbtn_idsearch   = new JButton( "아이디찾기" );// 아이디 찾기 검색버튼 (성공지 2번도화지로 전환)
    JButton jbtn_idmiss     = new JButton( "아이디찾기" );// 아이디 찾기 상단메뉴(JPanel-1번도화지 전환용)
    JButton jbtn_pwmiss     = new JButton( "비밀번호찾기" ); // 비밀번호 찾기 상단메뉴(JPanel-3번도화지 전환용)
    // 1번도화지에 붙는 JTextField , 이름/전화번호 입력
    JPasswordField jtf_pwupdate2 = new JPasswordField() {
                                     @Override
                                     public void setBorder( Border border ) {
                                         
                                     }
                                 };
    JPasswordField jtf_pwupdate  = new JPasswordField() {
                                     @Override
                                     public void setBorder( Border border ) {
                                         
                                     }
                                 };
    JTextField     jtf_name      = new JTextField() {
                                     @Override
                                     public void setBorder( Border border ) {
                                         
                                     }
                                 };
    JTextField     jtf_phone     = new JTextField() {
                                     @Override
                                     public void setBorder( Border border ) {
                                         
                                     }
                                 };
    JTextField     jtf_name2     = new JTextField() {
                                     @Override
                                     public void setBorder( Border border ) {
                                         
                                     }
                                 };
    JTextField     jtf_id        = new JTextField() {
                                     @Override
                                     public void setBorder( Border border ) {
                                         
                                     }
                                 };
    FindIdPwView   infomiss      = null;
    
    Font f = new Font( "맑은 고딕", Font.PLAIN, 13 );
    
    public void initDisplay() {
        
        // 상단 메뉴바의 도화지에 버튼 두 개 붙이기+버튼정의
        jbtn_pwmiss.setBorderPainted( false );
        jbtn_pwmiss.setBackground( Color.white );
        jbtn_pwmiss.setFont( f );
        jbtn_idmiss.setBorderPainted( false );
        jbtn_idmiss.setBackground( new Color( 146, 208, 80 ) );
        jbtn_idmiss.setFont( f );
        jp_north.add( jbtn_idmiss );
        jp_north.add( jbtn_pwmiss );
        
        // 상단 메뉴바를 위한 도화지 붙이기
        jp_north.setLayout( new GridLayout() );
        jdl_infomiss.add( "North", jp_north );
        
        // 4번도화지에 컨텐츠 정의
        jlb_pwupdate.setBounds( 40, 78, 80, 40 );
        jlb_pwupdate.setFont( f );
        jlb_pwupdate2.setBounds( 15, 128, 80, 40 );
        jlb_pwupdate2.setFont( f );
        jtf_pwupdate.setBounds( 100, 78, 250, 40 );
        jtf_pwupdate2.setBounds( 100, 128, 250, 40 );
        jbtn_pwupdate.setBorderPainted( false );
        jbtn_pwupdate.setForeground( Color.WHITE );
        jbtn_pwupdate.setBackground( new Color( 64, 64, 64 ) );
        jbtn_pwupdate.setBounds( 100, 193, 180, 40 );
        jp_pwfind.add( jlb_pwupdate );
        jp_pwfind.add( jlb_pwupdate2 );
        jp_pwfind.add( jtf_pwupdate );
        jp_pwfind.add( jtf_pwupdate2 );
        jp_pwfind.add( jbtn_pwupdate );
        jp_pwfind.setLayout( new BorderLayout() );
        
        // 3번 도화지에 컨텐츠 정의
        jbtn_pwsearch.setBorderPainted( false );
        jbtn_pwsearch.setForeground( Color.WHITE );
        jbtn_pwsearch.setBackground( new Color( 64, 64, 64 ) );
        jbtn_pwsearch.setBounds( 100, 193, 180, 40 );
        jbtn_pwsearch.setFont( f );
        jp_pwmiss.add( jbtn_pwsearch );
        jlb_id.setBounds( 45, 128, 80, 40 );
        jlb_id.setFont( f );
        jlb_name2.setBounds( 52, 78, 80, 40 );
        jlb_name2.setFont( f );
        jp_pwmiss.add( jlb_name2 );
        jp_pwmiss.add( jlb_id );
        jtf_name2.setBounds( 100, 78, 250, 40 );
        jtf_id.setBounds( 100, 128, 250, 40 );
        jp_pwmiss.add( jtf_id );
        jp_pwmiss.add( jtf_name2 );
        jp_pwmiss.setLayout( new BorderLayout() );
        
        // 2번 도화지에 컨텐츠 정의
        jbtn_gotopwmiss.setBorderPainted( false );
        jbtn_gotopwmiss.setForeground( Color.WHITE );
        jbtn_gotopwmiss.setBackground( new Color( 64, 64, 64 ) );
        jbtn_gotopwmiss.setBounds( 90, 180, 200, 35 );
        jlb_idfind.setBounds( 95, 90, 300, 35 );
        jlb_idfind.setFont( new Font( "맑은 고딕", Font.BOLD, 13 ) );
        jp_idfind.add( jbtn_gotopwmiss );
        jp_idfind.add( jlb_idfind );
        jp_idfind.setLayout( new BorderLayout() );
        
        // 1번 도화지에 컨텐츠 정의
        jlb_name.setBounds( 50, 50, 40, 40 );
        jlb_name.setFont( f );
        jlb_phone.setBounds( 30, 100, 80, 40 );
        jlb_phone.setFont( f );
        jp_idmiss.add( jlb_name );
        jp_idmiss.add( jlb_phone );
        jtf_name.setBounds( 100, 50, 250, 40 );
        jtf_phone.setBounds( 100, 100, 250, 40 );
        jp_idmiss.add( jtf_phone );
        jp_idmiss.add( jtf_name );
        jbtn_idsearch.setBorderPainted( false );
        jbtn_idsearch.setForeground( Color.WHITE );
        jbtn_idsearch.setBackground( new Color( 64, 64, 64 ) );
        jbtn_idsearch.setBounds( 100, 165, 180, 40 );
        jbtn_idsearch.setFont( f );
        jp_idmiss.add( jbtn_idsearch );
        jp_idmiss.setLayout( new BorderLayout() );
        
        // jdl_infomiss.add("Center",jp_cardbox);
        // JDialog, 메인 프레임 정의
        jp_pwfind.setSize( 400, 300 );
        jp_pwmiss.setSize( 400, 300 );
        jp_idfind.setSize( 400, 300 );
        jp_idmiss.setSize( 400, 300 );
        jdl_infomiss.add( "Center", jp_pwfind );// 4번도화지 orange
        jdl_infomiss.add( "Center", jp_pwmiss );// 3번도화지 orange
        jdl_infomiss.add( "Center", jp_idfind );// 2번도화지 gray
        jdl_infomiss.add( "Center", jp_idmiss );// 1번도화지
        jp_pwfind.setVisible( false );
        jp_pwmiss.setVisible( false );
        jp_idfind.setVisible( false );
        jp_idmiss.setVisible( true );
        jdl_infomiss.setTitle( "아이디/비밀번호 찾기" );
        jdl_infomiss.setSize( 400, 300 );
        jdl_infomiss.setVisible( true );
        infomiss = new FindIdPwView();
        
        /// action
        jtf_id.setText( "" );
        jtf_name.setText( "" );
        jtf_name2.setText( "" );
        jtf_phone.setText( "" );
        // Object obj = e.getSource();
        // 상단 메뉴 "아이디찾기"버튼
        jbtn_idmiss.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                jp_pwfind.setVisible( false );
                jp_pwmiss.setVisible( false );
                jp_idfind.setVisible( false );
                jp_idmiss.setVisible( true );
                // card.show(jp_cardbox,"1");
                System.out.println( "1번카드" );
                jbtn_pwmiss.setBackground( Color.white );
                jbtn_idmiss.setBackground( new Color( 146, 208, 80 ) );
            }
        } );
        // 상단메뉴 "비밀번호 찾기" 버튼
        jbtn_pwmiss.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                // card.show(jp_cardbox,"3");
                jp_idmiss.setVisible( false );
                jp_idfind.setVisible( false );
                jp_pwfind.setVisible( false );
                jp_pwmiss.setVisible( true );
                jbtn_pwmiss.setBackground( new Color( 146, 208, 80 ) );
                jbtn_idmiss.setBackground( Color.white );
                System.out.println( "3번카드" );
            }
        } );
        // 1번도화지 "아이디 찾기"버튼
        jbtn_idsearch.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                System.out.println( "2번카드" );
                acc = findIdPwLogic.findID( jtf_name.getText(), jtf_phone.getText() );
                // acc = model.findID( jtf_name.getText(), jtf_phone.getText() );
                
                if ( acc.getUser_id() != null ) {
                    // jp_idfind.setVisible( true );
                    JOptionPane.showMessageDialog( jbtn_idsearch, "ID는 " + acc.getUser_id() + " 입니다." );
                }
                else {
                    JOptionPane.showMessageDialog( null, "일치하는 회원정보가 없습니다.\n이름과 전화번호를 확인해주세요. " );
                }
            }
        } );
        
        // 2번도화지 "비밀번호 찾기"버튼
        // jbtn_gotopwmiss.addActionListener( new ActionListener() {
        // public void actionPerformed( ActionEvent e ) {
        // jp_idfind.setVisible( false );
        // jp_idmiss.setVisible( false );
        // jp_pwfind.setVisible( false );
        // jp_pwmiss.setVisible( true );
        // jbtn_idmiss.setBackground( Color.WHITE );
        // jbtn_pwmiss.setBackground( new Color( 146, 208, 80 ) );
        // System.out.println( "3번카드" );
        // }
        // } );
        
        // 3번도화지 "비밀번호 찾기"버튼
        jbtn_pwsearch.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                System.out.println( "4번카드" );
                acc = findIdPwLogic.pwFind( jtf_name2.getText(), jtf_id.getText() );
                // acc = model.pwFind( jtf_name2.getText(), jtf_id.getText() );
                
                if ( acc.getUser_pw() != null ) {
                    JOptionPane.showMessageDialog( jbtn_pwsearch, "회원님의 PW는 " + acc.getUser_pw() + " 입니다." );
                }
                else {
                    JOptionPane.showMessageDialog( null, "일치하는 회원정보가 없습니다.\n이름과 아이디를 확인해주세요. " );
                }
                
            }
        } );
        // // 4번도화지 "비밀번호 변경"버튼
        // jbtn_pwupdate.addActionListener( new ActionListener() {
        // public void actionPerformed( ActionEvent e ) {
        // String vpw = new String( jtf_pwupdate.getText() );
        // // String.valueOf( jtf_pwupdate.getPassword()
        // System.out.println( vpw + "," + mid );
        // }
        // } );
    }
    
    public static void main( String[] args ) {
        FindIdPwView minfo = new FindIdPwView();
        minfo.initDisplay();
    }
    
}
