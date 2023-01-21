package server.thread;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.StringTokenizer;

import lombok.extern.log4j.Log4j2;
import util.command.Protocol;

@Log4j2( topic = "talk-server" )
public class TalkServerThread extends Thread {
    
    // 서버로부터 클라이언트 연결정보를 받아오기 위한 전역변수 추가
    
    private List<TalkServerThread> userList;
    private Socket                 socket;
    private ObjectOutputStream     oos;
    private ObjectInputStream      ois;
    
    String nickname = null;
    
    String user_id;
    String user_nick;
    String user_msg;
    
    public TalkServerThread( Socket socket, List<TalkServerThread> userList ) throws IOException {
        this.socket = socket;
        this.userList = userList;
    }
    
    @Override
    public void run() {
        
        try {
            oos = new ObjectOutputStream( socket.getOutputStream() );
            ois = new ObjectInputStream( socket.getInputStream() );
            
            talk_stop: while ( true ) {
                StringTokenizer st      = null; // 200|tomato|오늘뭐해
                String          receive = ( String ) ois.readObject();
                
                log.debug( "서버 수신 메시지 : {}", receive );
                
                if ( receive != null && receive.trim().length() > 0 ) {
                    st = new StringTokenizer( receive, Protocol.SEPARATOR );
                }
                
                int protocol = 0;
                
                if ( st != null ) {
                    protocol = Integer.parseInt( st.nextToken() );
                }
                
                switch ( protocol ) {
                    case Protocol.TALK_IN: {
                        userList.add( this );
                        nickname = st.nextToken();
                        String message = st.nextToken();
                        log.info( "{} 입장, 현재 접속자 수 : {}", nickname, userList.size() );
                        
                        // 현재 접속 중인 유저(스레드) 전체 루프
                        // n번째 유저는 n-1번째까지 ~님이 입장하셨습니다. 가 반복되는 이슈
                        for ( TalkServerThread user : userList ) {
                            
                            // 다른 유저들의 [100#닉네임#님이 입장하셨습니다.]를 나에게 전송
                            if ( !user.equals( this ) ) {
                                send( Protocol.TALK_IN + Protocol.SEPARATOR + user.nickname + Protocol.SEPARATOR + message );
                            }
                        }
                        
                        broadCasting( Protocol.TALK_IN + Protocol.SEPARATOR + nickname + Protocol.SEPARATOR + message );
                    }
                        break;
                    
                    case Protocol.MESSAGE: {
                        
                        String nickname = st.nextToken();
                        String message  = st.nextToken();
                        
                        broadCasting( Protocol.MESSAGE + Protocol.SEPARATOR + nickname + Protocol.SEPARATOR + message );
                        break;
                    }
                    case Protocol.TALK_OUT: {
                        // 유저 리스트에서 로그아웃한 유저 삭제 및 다른 유저에게 퇴장메시지 송신
                        String nickname = st.nextToken();
                        String message  = st.nextToken();
                        userList.remove( this );
                        broadCasting( Protocol.TALK_OUT + Protocol.SEPARATOR + nickname + Protocol.SEPARATOR + message );
                        log.info( "{} 퇴장, 현재 접속자 수 : {}", nickname, userList.size() );
                    }
                        break talk_stop; // TALK_OUT일 때 루프 종료
                }
            }
        }
        catch ( Exception e ) {
            log.error( "Exception : ", e );
        }
    }
    
    /**
     * 현재 입장해 있는 친구들 모두에게 메시지 전송하기 구현
     * 
     * @param message
     */
    public void broadCasting( Object message ) {
        
        for ( TalkServerThread user : userList ) {// 여러사람
            user.send( message );
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
            log.error( "Exception : ", e );
        }
    }
    
}
