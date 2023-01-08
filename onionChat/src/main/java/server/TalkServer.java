package server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import server.thread.TalkServerThread;

@SuppressWarnings( "serial" )
public class TalkServer extends JFrame implements ActionListener {
    
    ServerSocket serverSocket;
    Socket       socket;
    
    JTextArea   logTextArea   = new JTextArea( 10, 30 );
    JScrollPane logScrollPane = new JScrollPane( logTextArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                    JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
    
    List<TalkServerThread> clientList = new Vector<>();
    
    public void initDisplay() {
        this.add( "Center", logScrollPane );
        this.setSize( 500, 400 );
        this.setVisible( true );
    }
    
    public void connect( int port ) {
        
        try {
            serverSocket = new ServerSocket( port );
            logTextArea.append( getWarningString() );
            
            while ( true ) {
                socket = serverSocket.accept(); // 클라이언트 시그널 대기
                logTextArea.append( "client info:" + socket + "\n" );
                logTextArea.append( "client info:" + socket.getInetAddress() + "\n" );
            }
        }
        catch ( Exception e ) {}
    }
    
    public static void main( String[] args ) {
        
    }
    
    @Override
    public void actionPerformed( ActionEvent e ) {
        
    }
    
}
