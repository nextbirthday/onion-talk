package server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;

import server.thread.TalkServerThread;

@SuppressWarnings( "serial" )
public class TalkServer extends JFrame implements Runnable {
    
    ServerSocket serverSocket;
    Socket       socket;
    
    List<TalkServerThread> clientList = new Vector<>();
    
    @Override
    public void run() {
        connect();
    }
    
    public void connect() {
        int port = 5000;
        
        try {
            serverSocket = new ServerSocket( port );
            
            while ( true ) {
                // 클라이언트 시그널 대기
                socket = serverSocket.accept();
                System.out.println( "client info:" + socket + "\n" );
                System.out.println( "client info:" + socket.getInetAddress() + "\n" );
                
                // TalkServerThread 클래스로 client 연결정보 넘겨주기
                TalkServerThread tst = new TalkServerThread( this );
                
                tst.start(); // TalkServerThread run() call
            }
        }
        catch ( Exception e ) {
            e.printStackTrace();
        }
    }
    
    public static void main( String[] args ) {
        TalkServer ts     = new TalkServer();
        Thread     thread = new Thread( ts );
        
        thread.start();
    }
    
}
