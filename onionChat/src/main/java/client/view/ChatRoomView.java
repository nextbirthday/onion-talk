package client.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SuppressWarnings( "serial" )
public class ChatRoomView extends JFrame implements ActionListener {
    
    JPanel      centerPanel   = new JPanel();
    JPanel      southPanel    = new JPanel();
    JButton     sendButton    = new JButton( "전송" );
    JTextField  chatTextField = new JTextField( 20 );
    JTextArea   chatDisplay   = new JTextArea( 15, 38 );
    JScrollPane scrollPane    = new JScrollPane( chatDisplay, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                    JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
    String      nickname;
    
    public ChatRoomView() {}
    
    public ChatRoomView( String nickname ) {
        // 전역변수로 선언된 nickname 초기화 시점
        this.nickname = nickname;
    }
    
    public void initChatRoomView() {
        
        centerPanel.setLayout( new BorderLayout() );
        centerPanel.add( "Center", scrollPane );
        centerPanel.add( "South", southPanel );
        
        southPanel.setLayout( new BorderLayout() );
        southPanel.add( "Center", chatTextField );
        southPanel.add( "East", sendButton );
        chatDisplay.setLineWrap( true );// 자동줄바꾸기
        chatDisplay.setEditable( false );
        chatDisplay.setBackground( Color.cyan );
        chatTextField.addActionListener( this );
        sendButton.addActionListener( this );
        chatDisplay.append( nickname + "님이 입장하셨습니다.\n" );
        this.setLayout( new GridLayout( 1, 2 ) );
        this.add( centerPanel );
        this.setTitle( "[" + nickname + "]" + "님과의채팅방" );
        this.setVisible( true );
        this.setSize( 400, 600 );
        this.setDefaultCloseOperation( DISPOSE_ON_CLOSE );
    }
    
    @Override
    public void actionPerformed( ActionEvent e ) {
        Object object = e.getSource();
        
        if ( chatTextField == object || sendButton == object ) {
            String message = chatTextField.getText();
            chatDisplay.append( message + "\n" );
            chatTextField.setText( "" );
        }
    }
    
    public static void main( String[] args ) {
        ChatRoomView c = new ChatRoomView();
        c.initChatRoomView();
    }
}
