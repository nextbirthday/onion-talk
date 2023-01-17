package server.thread;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.StringTokenizer;

import server.TalkServer;

public class TalkServerThread extends Thread {
    
    TalkServer             talkServer;
    List<TalkServerThread> userList;
    
    String user_id;
    String user_nick;
    String user_msg;
    
    // 서버로부터 클라이언트 연결정보를 받아오기 위한 전역변수 추가
    Socket             client;
    ObjectOutputStream oos;
    ObjectInputStream  ois;
    
    public TalkServerThread( List<TalkServerThread> userList, Socket socket, TalkServer talkServer ) throws IOException {
        oos = new ObjectOutputStream( socket.getOutputStream() );
        ois = new ObjectInputStream( socket.getInputStream() );
        this.client = socket;
        this.userList = userList;
        this.talkServer = talkServer;
        
        // 나 보다 먼저 들어와 있는 사람들에 대한 처리
        
        userList.add( this );// 현재 들어온 클라이언트 스레드
        String enterMsg = "100#tomato";
        this.broadCasting( enterMsg );// 로그인하고 입장했다는 정보전송하기
    }
    
    @Override
    public void run() {
        
        try {
            talk_stop: while ( true ) {
                StringTokenizer st = null;
                // 200|tomato|오늘뭐해
                String receive = ( String ) ois.readObject();
                
                if ( receive != null && receive.trim().length() > 0 ) {
                    st = new StringTokenizer( receive, util.command.Protocol.separator );
                }
                
                int protocol = 0;
                
                if ( st != null ) {
                    protocol = Integer.parseInt( st.nextToken() );
                }
                
                switch ( protocol ) {
                    case util.command.Protocol.TALK_IN: {
                        String nickName = st.nextToken();
                        String msg      = st.nextToken();
                    }
                        break;
                    case util.command.Protocol.MESSAGE: {
                        String nickName = st.nextToken();
                        String msg      = st.nextToken();
                    }
                        break;
                    case util.command.Protocol.TALK_OUT: {
                        
                    }
                        break talk_stop;//
                }
                
            }
            // Talk_OUT이면 여기로 탈출
        }
        catch ( IOException e ) {
            e.printStackTrace();
        }
        catch ( ClassNotFoundException e ) {
            e.printStackTrace();
        }
        finally {
            
        }
        
    }
    
    // 현재 입장해 있는 친구들 모두에게 메시지 전송하기 구현
    public void broadCasting( String message ) {
        
        for ( TalkServerThread talkServerThread : userList ) {// 여러사람
            talkServerThread.send( message );
        }
    }
    
    // 클라이언트에게 말하기 구현
    public void send( String message ) {
        
        try {
            oos.writeObject( message );
        }
        catch ( Exception e ) {
            e.printStackTrace(); // stack에 쌓여있는 error message log print
        }
    }
    
}
