package client.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import client.thread.TalkClientThread;
import lombok.extern.log4j.Log4j2;
import model.FriendAddLogic;
import model.StatusMessageLogic;
import util.command.Protocol;
import util.dto.Account;
import util.dto.Friend;

@Log4j2
@SuppressWarnings( "serial" )
public class MainView extends JFrame implements ActionListener, MouseListener, ListSelectionListener {
    
    private Socket             client;
    private ObjectOutputStream oos;
    private ObjectInputStream  ois;
    
    // Account DTO를 받아오기 위한 전역변수 선언
    Account myAccount;
    
    // 친구 이름(아이디)를 받아오기 위한 전역변수 선언
    List<Friend> friendList = new Vector<>();
    Friend       friend;
    
    // 선언부
    String    imgPath   = "src\\main\\resources\\images\\";
    ImageIcon imageIcon = new ImageIcon( imgPath + "main.png" );
    JFrame    jf        = new JFrame();
    
    JPanel jp_north  = new JPanel(); // 위쪽 도화지
    JPanel jp_center = new JPanel();// 가운데 도화지
    JPanel jp_south  = new JPanel(); // 아래 버튼 도화지
    
    JLabel       jlb_cht  = new JLabel();
    JLabel       jlb_id   = new JLabel();
    JLabel       jlb_list = new JLabel( "친구추가" );
    JLabel       jlb_msg  = new JLabel( " " ); // 상태메시지 레이블
    EtchedBorder eborder  = new EtchedBorder( EtchedBorder.RAISED ); // 라벨 테두리
    
    JButton jbtn_change_msg    = new JButton( "상태메시지 수정" ); // 상태메세지 수정 버튼
    JButton jbtn_add_friend    = new JButton( "추가" ); // 친구추가 버튼
    JButton jbtn_delete_friend = new JButton( "x" ); // 친구삭제 버튼
    
    // 아래 버튼
    JButton jbtn_home     = new JButton( "친구목록" ); // 친구목록 버튼
    JButton jbtn_chatroom = new JButton( "채팅목록" );// 채팅방 버튼
    JButton jbtn_setting  = new JButton( "설정" );// 설정 버튼
    JButton jbtn_logout   = new JButton( "로그아웃" );// 로그아웃 버튼
    JButton lithButton    = new JButton( "리스항구" );
    
    // generic type 명시할 것
    DefaultListModel<String> model      = new DefaultListModel<>(); // JList에 보이는 실제 데이터
    JList<String>            list       = new JList<>( model );; // 리스트
    JScrollPane              scrolled   = new JScrollPane( list );
    JTextField               inputField = new JTextField( 10 ); // 테스트 입력 Field
    JButton                  addBtn     = new JButton( "검색" );; // 검색 버튼
    JButton                  delBtn     = new JButton( "삭제" ); // 삭제 버튼
    String                   existStatusMessage;
    
    public MainView() {}
    
    // 생성자
    public MainView( Account account, List<Friend> friendList ) {
        this.myAccount = account;
        this.existStatusMessage = account.getUser_msg();
        this.friendList = friendList;
        log.info( this.myAccount );
        log.info( friendList );
        
        initDisplay();
        
        connectSocket();
    }
    
    private void connectSocket() {
        
        try {
            client = new Socket( "focusrite.iptime.org", 20000 );
            oos = new ObjectOutputStream( client.getOutputStream() );
            ois = new ObjectInputStream( client.getInputStream() );
            oos.writeObject( Protocol.SIGN_IN + Protocol.SEPARATOR + myAccount.getUser_nick() + Protocol.SEPARATOR + "로그인" );
            
            log.info( "nickname : {}", myAccount.getUser_nick() );
            
            TalkClientThread oisThread = new TalkClientThread( oos, ois );
            oisThread.setDaemon( true );
            oisThread.start();
        }
        catch ( Exception e ) {
            log.error( "소켓 연결 실패 :", e );
        }
    }
    
    public void initDisplay() {
        
        lithButton.addActionListener( this );
        addBtn.addActionListener( this );
        jbtn_logout.addActionListener( this );
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        // this.setContentPane( new MyPanel() );
        this.setLayout( new BorderLayout( 50, 20 ) );// 배치
        
        list.addListSelectionListener( this ); // 항목 선택시
        list.setSelectionMode( ListSelectionModel.SINGLE_SELECTION ); // 하나만 선택 될 수 있도록
        list.addMouseListener( this );
        
        // ==========이벤트 속성 추가=================
        jbtn_change_msg.addActionListener( this ); // 엔터 처리
        addBtn.addMouseListener( this ); // 아이템 추가
        delBtn.addMouseListener( this ); // 아이템 삭제
        
        // 상단 - 닉네임 - 상태메세지
        jp_north.add( jlb_id );// 닉네임
        // jlb_nick.setText( account.getUser_nick() );
        jp_north.add( jlb_msg ); // 상태메세지
        // jlb_msg.setText( account.getUser_msg() );
        jp_north.add( jbtn_change_msg );
        jlb_id.setBounds( 40, 20, 100, 35 );
        jlb_msg.setBounds( 100, 20, 200, 35 );
        jlb_msg.setToolTipText( "수정버튼을 누르면 메세지를 변경하실 수 있습니다" );
        jbtn_change_msg.setBounds( 280, 20, 60, 35 );
        jlb_msg.setBorder( eborder ); // 메세지 테두리 설정
        
        // 하단 -탭
        jp_south.add( jbtn_home );
        jp_south.add( jbtn_chatroom );
        jp_south.add( jbtn_setting );
        jp_south.add( jbtn_logout );
        jp_south.add( lithButton );
        jp_south.setBackground( new Color( 146, 208, 80 ) );
        
        // 가운데 - 친구목록
        
        list.setSize( 400, 600 );
        jp_center.add( jlb_list );
        this.add( "Center", jp_center );
        this.setLayout( new BorderLayout() );
        
        // JPanel topPanel=new JPanel(new FlowLayout(10,10,FlowLayout.LEFT));
        jp_center.add( inputField );
        jp_center.add( addBtn );
        jp_center.add( delBtn ); // 위쪽 패널 [textfield] [add] [del]
        jp_center.setBorder( BorderFactory.createEmptyBorder( 10, 10, 10, 10 ) ); // 상, 좌, 하, 우 공백(Padding)
        jp_center.setBackground( new Color( 146, 208, 80 ) ); // 판넬색 변경
        scrolled = new JScrollPane( list );
        scrolled.setPreferredSize( new Dimension( 300, 400 ) ); // jlist 사이즈 변경
        scrolled.setBorder( BorderFactory.createEmptyBorder( 0, 0, 10, 10 ) );// jlist 상하좌우공백
        jp_center.add( scrolled );
        // this.add(jp_center,"North");
        scrolled.getVerticalScrollBar().setValue( scrolled.getVerticalScrollBar().getMaximum() );
        
        // 로그인 시 기존 상태메시지 DB서버에서 불러오기
        jlb_msg.setText( existStatusMessage );
        
        // 로그인 시 아이디 화면에 뿌려주기
        jlb_id.setText( "ID: " + myAccount.getUser_id() );
        
        // 로그인시 받아온 친구record 화면에 뿌려주기
        for ( int i = 0; i < friendList.size(); i++ ) {
            friend = friendList.get( i );
            model.addElement( friend.getFriend_id() );
        }
        
        this.add( "North", jp_north );
        this.add( "Center", jp_center ); // 가운데 list
        this.add( "South", jp_south );
        
        this.setTitle( "친구목록" );
        this.setLocation( 500, 100 );
        this.setSize( 400, 600 );
        this.setVisible( true );
    }
    
    @Override
    public void mouseClicked( MouseEvent e ) {}
    
    public void removeItem( int index ) {
        
        if ( index < 0 ) {
            if ( model.size() == 0 )
                return; // 아무것도 저장되어 있지 않으면 return
            index = 0; // 그 이상이면 가장 상위 list index
        }
        model.remove( index );
    }
    
    // MouseListener
    @Override
    public void mousePressed( MouseEvent e ) {
        
    }
    
    @Override
    public void mouseReleased( MouseEvent e ) {
        
        if ( e.getClickCount() == 2 ) {
            
            int    selected     = list.getSelectedIndex();
            String selectedItem = model.getElementAt( selected );
            String friendNick   = selectedItem.split( "\\(" )[0];
            
            Account friendAccount = new Account();
            friendAccount.setUser_nick( friendNick );
            
            // List<Account> accountList = new Vector<>();
            // 내 정보
            // accountList.add( myAccount );
            // 채팅방 생성할 친구 정보
            // accountList.add( friendAccount );
            // accountList.forEach( user -> log.info( user.toString() ) );
            
            try {
                oos.writeObject( Protocol.TALK_IN + Protocol.SEPARATOR + friendAccount.getUser_nick() + Protocol.SEPARATOR + "입장" );
            }
            catch ( IOException ex ) {
                log.error( "IOException :", ex );
            }
        }
    }
    
    @Override
    public void mouseEntered( MouseEvent e ) {}
    
    @Override
    public void mouseExited( MouseEvent e ) {}
    
    // ListSelectionListener
    @Override
    public void valueChanged( ListSelectionEvent e ) {
        
        if ( !e.getValueIsAdjusting() ) { // 이거 없으면 mouse 눌릴때, 뗄때 각각 한번씩 호출되서 총 두번 호출
            log.info( "selected :{}", list.getSelectedValue() );
        }
    }
    
    private void showDialog( String message ) {
        // 첫 번째 파라미터 자리에 null이 들어가 있으면 지 혼자이고, Component가 들어오면
        JOptionPane.showMessageDialog( this, message );
    }
    
    @Override
    public void actionPerformed( ActionEvent e ) {
        Object obj = e.getSource();
        
        if ( obj == jbtn_change_msg ) {
            String statusMessage = JOptionPane.showInputDialog( jf, "변경할 상태메세지를 입력하세요", "", JOptionPane.INFORMATION_MESSAGE );
            
            // Condition: 공백 입력 허용불가
            if ( statusMessage.length() > 0 ) {
                int result = 0;
                myAccount.setUser_msg( statusMessage );
                result = new StatusMessageLogic().setStatusMessage( myAccount );
                
                log.info( myAccount.toString() + ", " + "statusMessage = " + statusMessage + ", result = " + result );
                
                if ( result == 0 ) {
                    showDialog( "상태메시지 변경에 실패했습니다." );
                }
                else {
                    showDialog( "상태메시지 변경에 성공했습니다." );
                    jlb_msg.setText( statusMessage );
                }
            }
        }
        
        if ( obj == addBtn ) {
            
            String         friendID       = inputField.getText();
            FriendAddLogic friendAddLogic = new FriendAddLogic();
            Account        friendAccount  = new Account();
            
            if ( friendID == null || friendID.length() == 0 ) {
                showDialog( "친구 아이디를 입력해주세요." );
            }
            else {
                friendAccount = friendAddLogic.friendIDCheck( friendID );
                
                log.info( friendAccount );
                
                if ( friendAccount.getUser_id() == null ) {
                    showDialog( "존재하지 않는 회원입니다." );
                    return;
                }
                else {
                    new FriendAddView( this, myAccount, friendAccount );
                }
            }
            inputField.setText( "" );
            inputField.requestFocus();
        }
        
        if ( obj == lithButton ) {
            LithHarbor lithHarbor = new LithHarbor( "harbor.wav" );
        }
        
        if ( obj == jbtn_logout ) {
            this.dispose();
        }
    }
}