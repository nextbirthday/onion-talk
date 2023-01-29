package server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Vector;

import lombok.extern.log4j.Log4j2;
import server.thread.TalkServerThread;

@Log4j2( topic = "talk-server" )
public class TalkServer implements Runnable {
    
    private static final List<TalkServerThread> userList = new Vector<>();
    
    @Override
    public void run() {
        ServerSocket serverSocket = null;
        final int    port         = 20000;
        
        try {
            serverSocket = new ServerSocket( port );
            log.info( "서버 소켓 바인딩, 포트번호 : {}", serverSocket.getLocalPort() );
            
            while ( true ) {
                log.info( "클라이언트 연결 대기" );
                Socket socket = serverSocket.accept();// 클라이언트 시그널 대기
                log.info( "클라이언트 접속 : {}", socket.getRemoteSocketAddress() );
                
                // TalkServerThread 클래스로 client 연결정보 넘겨주기
                TalkServerThread tst = new TalkServerThread( socket, userList );
                tst.start();
            }
        }
        catch ( Exception e ) {
            log.error( "Exception :", e );
        }
    }
    
    public static void main( String[] args ) {
        new Thread( new TalkServer() ).start();
    }
}
