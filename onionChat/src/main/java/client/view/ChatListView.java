package client.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

// 채팅 목록
@SuppressWarnings( "serial" )
public class ChatListView extends JFrame implements ActionListener {
    
    JPanel  jp_main  = new JPanel(); // 메인 도화지
    JPanel  jp_south = new JPanel(); // 하반부 버튼 도화지
    JPanel  jp_north = new JPanel(); // 삭제 버튼 도화지
    JLabel  jlb_list = new JLabel( "채팅 목록" );
    JButton btn_exit = new JButton( "삭제" );
    
    // 하단부 버튼
    JButton btn_home   = new JButton( "홈" );
    JButton btn_chat   = new JButton( "채팅방" );
    JButton btn_set    = new JButton( "설정" );
    JButton btn_logout = new JButton( "로그아웃" );
    
    DefaultListModel<String> model    = new DefaultListModel<>(); // JList에 보이는 실제 데이터
    JList<String>            list     = new JList<>( model );; // 리스트
    JScrollPane              scrolled = new JScrollPane( list );
    
    // DB에서 가져온 ChatLists[]
    // String[] ChatLists = null;
    
    /////////////////// [[DB 연동 시작]] ////////////////////
    // 물리적으로 떨어져있는 오라클 서버에 접속하는데 필요한 공통 코드
    // DBConnectionMgr dbMgr = new DBConnectionMgr(); // Driverclass,커넥션정보
    // Connection con = null; // 인터페이스
    // PreparedStatement pstmt = null; // 인터페이스 - 동적쿼리처리
    // ResultSet rs = null; // 오라클서버의 커서를 조작하는 인터페이스 - next(), previous()
    /////////////////// [[DB 연동 끝]] ////////////////////
    
    // 생성자
    public ChatListView() {
        initDisplay();
    }
    
    // 화면
    public void initDisplay() {
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        
        //// 채팅방 목록
        jp_main.add( jlb_list );
        jp_main.setBackground( new Color( 169, 209, 142 ) );
        scrolled = new JScrollPane( list );
        scrolled.setPreferredSize( new Dimension( 350, 450 ) ); // jlist 사이즈 변경
        scrolled.setBorder( BorderFactory.createEmptyBorder( 0, 0, 20, 20 ) ); // jlist 상하좌우공백
        jp_main.add( scrolled );
        this.add( "Center", jp_main );
        
        //// 임시 채팅방
        model.addElement( "내방" );
        model.addElement( "주방" );
        model.addElement( "안방" );
        
        //// 상단부 대화방버튼
        jp_north.add( btn_exit );
        jp_north.setLayout( new FlowLayout( FlowLayout.RIGHT ) );
        this.add( "North", jp_north );
        
        //// 하단부 버튼
        jp_south.add( btn_home );
        jp_south.add( btn_chat );
        jp_south.add( btn_set );
        jp_south.add( btn_logout );
        this.add( "South", jp_south );
        
        //// 타이틀, 화면 띄우기
        this.setTitle( "양파쿵야 Talk" );
        this.setSize( 400, 600 );
        this.setLocationRelativeTo( null ); // 창 가운데
        this.setVisible( true );
    }
    
    public static void main( String[] args ) {
        new ChatListView();
    }
    
    @Override
    public void actionPerformed( ActionEvent arg ) {
        btn_exit.addActionListener( this );
    }
    
    ////// 채팅방 나감과 동시에 DB 채팅내역 날리기 해야함 - 어케함?
    public void MouseEvent( MouseEvent e ) {
        
        if ( e.getSource() == btn_exit ) {
            remove( list );
        }
        else {
            JOptionPane.showMessageDialog( btn_exit, "삭제할 대화방을 선택하세요" );
        }
    }
}