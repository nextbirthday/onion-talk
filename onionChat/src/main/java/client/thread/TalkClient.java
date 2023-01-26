package client.thread;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import lombok.extern.log4j.Log4j2;
import util.command.Protocol;

@Log4j2( topic = "logger" )
@SuppressWarnings( "serial" )
public class TalkClient extends JFrame implements ActionListener {
    
    ObjectOutputStream oos;
    
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
    
    public TalkClient( String nickname, ObjectOutputStream oos ) {
        this.nickname = nickname;
        this.oos = oos;
        initDisplay();
    }
    
    public void initDisplay() {
        setTitle( nickname );
        jtf_msg.addActionListener( this );
        jbtn_exit.addActionListener( this );
        jbtn_change.addActionListener( this );
        jbtn_send.addActionListener( this );
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
    
    @Override
    public void actionPerformed( ActionEvent e ) {
        Object object = e.getSource();
        
        try {
            
            // 채팅 메시지 송신
            if ( object == jbtn_send || object == jtf_msg ) {
                String message = jtf_msg.getText();
                
                if ( message == null || message.length() == 0 ) {
                    // 아무것도 입력되지않았으면 아래 코드를 실행하지 않는다. 화이트스페이스는 허용한다.
                    return;
                }
                
                oos.writeObject( Protocol.MESSAGE + Protocol.SEPARATOR + nickname + Protocol.SEPARATOR + message );
                jtf_msg.setText( "" );
            }
            else if ( object == jbtn_exit ) {
                oos.writeObject( Protocol.TALK_OUT + Protocol.SEPARATOR + nickname + Protocol.SEPARATOR + "님이 퇴장하셨습니다." );
                this.dispose();
            }
            else {
                return;
            }
        }
        catch ( Exception ex ) {
            log.error( "Exception : ", ex );
        }
    }
}
