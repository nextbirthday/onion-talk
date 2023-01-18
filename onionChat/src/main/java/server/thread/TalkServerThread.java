package server.thread;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

import lombok.extern.log4j.Log4j2;
import server.TalkServer;
import util.command.Protocol;

@Log4j2
public class TalkServerThread extends Thread {
    
    // 서버로부터 클라이언트 연결정보를 받아오기 위한 전역변수 추가
    
    TalkServer         talkServer;
    Socket             client;
    ObjectOutputStream oos;
    ObjectInputStream  ois;
    
    List<TalkServerThread> userList;
    List<String>           nicknameList;
    
    String user_id;
    String user_nick;
    String user_msg;
    
    public TalkServerThread( List<TalkServerThread> userList, Socket socket, TalkServer talkServer ) throws IOException {
        oos = new ObjectOutputStream( socket.getOutputStream() );
        ois = new ObjectInputStream( socket.getInputStream() );
        this.client = socket;
        this.userList = userList;
        this.talkServer = talkServer;
        log.info( userList.size() + ", " + userList );
    }
    
    @Override
    public void run() {
        
        try {
            talk_stop: while ( true ) {
                
                StringTokenizer st      = null; // 200|tomato|오늘뭐해
                String          receive = ( String ) ois.readObject();
                
                log.info( receive );
                
                if ( receive != null && receive.trim().length() > 0 ) {
                    st = new StringTokenizer( receive, Protocol.SEPARATOR );
                }
                
                int protocol = 0;
                
                if ( st != null ) {
                    protocol = Integer.parseInt( st.nextToken() );
                }
                
                switch ( protocol ) {
                    case Protocol.TALK_IN: {
                        String nickname = st.nextToken();
                        String message  = st.nextToken();
                        
                        this.broadCasting( Protocol.TALK_IN + Protocol.SEPARATOR + nickname + Protocol.SEPARATOR + message );
                        
                        for ( TalkServerThread talkServerThread : userList ) {
                            talkServerThread.send( Protocol.ENTER_ROOM + Protocol.SEPARATOR + nickname );
                        }
                        
                    }
                        break;
                    
                    case Protocol.MESSAGE: {
                        
                        String nickname = st.nextToken();
                        String message  = st.nextToken();
                        
                        this.broadCasting( Protocol.MESSAGE + Protocol.SEPARATOR + nickname + Protocol.SEPARATOR + message );
                        
                        // String error = "🎉빈 문자열이 입력되었습니다!🎉";
                        // oos.writeObject( Protocol.ERROR + Protocol.SEPARATOR + error );
                        
                        break;
                    }
                    
                    case Protocol.TALK_OUT: {
                        
                    }
                        break talk_stop;//
                }
                
            }
            // Talk_OUT이면 여기로 탈출
        }
        catch (
        
        IOException e ) {
            e.printStackTrace();
        }
        catch ( ClassNotFoundException e ) {
            e.printStackTrace();
        }
        
    }
    
    /**
     * 현재 입장해 있는 친구들 모두에게 메시지 전송하기 구현
     * 
     * @param message
     */
    public void broadCasting( Object message ) {
        
        for ( TalkServerThread talkServerThread : userList ) {// 여러사람
            talkServerThread.send( message );
        }
    }
    
    /**
     * 클라이언트에게 말하기
     * <p>
     * 
     * @param message
     */
    public void send( Object message ) {
        
        try {
            oos.writeObject( message );
        }
        catch ( Exception e ) {
            e.printStackTrace(); // stack에 쌓여있는 error message log print
        }
    }
    
}
