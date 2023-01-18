package client.thread;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import lombok.extern.log4j.Log4j2;
import util.command.Protocol;
import util.dto.Account;

@Log4j2
@SuppressWarnings( "serial" )
public class TalkClient extends JFrame implements ActionListener {
    Account account;
    
    Socket             socket;
    ObjectOutputStream oos;
    ObjectInputStream  ois;
    
    String             nickname;
    JPanel             jp_second       = new JPanel();
    JPanel             jp_second_south = new JPanel();
    JButton            jbtn_one        = new JButton( "1:1" );
    JButton            jbtn_change     = new JButton( "대화명변경" );
    JButton            jbtn_font       = new JButton( "글자색" );
    JButton            jbtn_exit       = new JButton( "나가기" );
    String             cols[]          = { "대화명" };
    String             data[][]        = new String[0][1];
    DefaultTableModel  dtm             = new DefaultTableModel( data, cols );
    JTable             jtb             = new JTable( dtm );
    JScrollPane        jsp             = new JScrollPane( jtb );
    JPanel             jp_first        = new JPanel();
    JPanel             jp_first_south  = new JPanel();
    JTextField         jtf_msg         = new JTextField( "" );// south속지 center
    JButton            jbtn_send       = new JButton( "전송" );// south속지 east
    public JTextArea   jta_display     = new JTextArea( 15, 38 );
    public JScrollPane jsp_display     = new JScrollPane( jta_display, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                    JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
    
    public TalkClient() {}
    
    public TalkClient( Account account ) {
        this.account = account;
        initDisplay();
        init();
        log.info( account );
    }
    
    public void initDisplay() {
        jtf_msg.addActionListener( this );
        jbtn_exit.addActionListener( this );
        jbtn_change.addActionListener( this );
        jbtn_send.addActionListener( this );
        // 사용자의 닉네임 받기
        nickname = JOptionPane.showInputDialog( "닉네임을 입력하세요." );
        this.setLayout( new GridLayout( 1, 2 ) );
        jp_second.setLayout( new BorderLayout() );
        jp_second.add( "Center", jsp );
        jp_second_south.setLayout( new GridLayout( 2, 2 ) );
        jp_second_south.add( jbtn_one );
        jp_second_south.add( jbtn_change );
        jp_second_south.add( jbtn_font );
        jp_second_south.add( jbtn_exit );
        jp_second.add( "South", jp_second_south );
        jp_first.setLayout( new BorderLayout() );
        jp_first_south.setLayout( new BorderLayout() );
        jp_first_south.add( "Center", jtf_msg );
        jp_first_south.add( "East", jbtn_send );
        jp_first.add( "Center", jsp_display );
        jp_first.add( "South", jp_first_south );
        jta_display.setLineWrap( true );
        jta_display.setEditable( false );
        this.add( jp_first );
        this.add( jp_second );
        this.setSize( 800, 550 );
        this.setVisible( true );
        this.setDefaultCloseOperation( DISPOSE_ON_CLOSE );
    }
    
    // 소켓 관련 초기화
    public void init() {
        
        try {
            // 서버측의 ip주소 작성하기
            
            socket = new Socket( "focusrite.iptime.org", 20000 );
            // socket = new Socket( "localhost", 20000 );
            
            oos = new ObjectOutputStream( socket.getOutputStream() );
            ois = new ObjectInputStream( socket.getInputStream() );
            
            // initDisplay에서 닉네임이 결정된 후 init메소드가 호출되므로 서버에게 내가 입장한 사실을 알린다.
            oos.writeObject( Protocol.TALK_IN + Protocol.SEPARATOR + nickname + Protocol.SEPARATOR + "님이 입장하셨습니다." );
            
            log.info( nickname );
            
            TalkClientThread tct = new TalkClientThread( this, ois );
            tct.start();
        }
        catch ( Exception e ) {
            System.out.println( e.toString() );
        }
    }
    
    public static void main( String[] args ) {
        TalkClient talkClient = new TalkClient();
        talkClient.initDisplay();
        
        talkClient.init();
    }
    
    @Override
    public void actionPerformed( ActionEvent e ) {
        Object object = e.getSource();
        
        if ( object == jbtn_send || object == jtf_msg ) {
            String message = jtf_msg.getText();
            
            if ( message == null || message.trim().length() == 0 ) {
                JOptionPane.showMessageDialog( this, "메세지를 입력하세요." );
                return;
            }
            
            try {
                System.out.println( "jbtn_send" + " or " + "jtf_msg" );
                oos.writeObject( Protocol.MESSAGE + Protocol.SEPARATOR + nickname + Protocol.SEPARATOR + message );
                System.out.println( message );
            }
            catch ( IOException e1 ) {
                e1.printStackTrace();
            }
            jtf_msg.setText( "" );
        }
        
        if ( object == jbtn_exit ) {
            
            try {
                oos.writeObject( Protocol.TALK_OUT + Protocol.SEPARATOR );
            }
            catch ( IOException e1 ) {
                e1.printStackTrace();
            }
            this.dispose();
        }
    }
}
