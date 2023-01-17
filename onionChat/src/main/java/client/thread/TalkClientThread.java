package client.thread;

import java.io.ObjectInputStream;
import java.util.StringTokenizer;
import java.util.Vector;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class TalkClientThread extends Thread {
    
    TalkClient        tc;
    ObjectInputStream ois;
    
    public TalkClientThread() {}
    
    public TalkClientThread( TalkClient tc, ObjectInputStream ois ) {
        this.tc = tc;
        this.ois = ois;
    }
    
    @Override
    public void run() {
        System.out.println( " TalkClientThread run 호출" );
        
        try {
            
            while ( true ) {
                Object receive = null;
                
                receive = ( String ) ois.readObject();
                log.info( receive );
                System.out.println( "서버에서 전송된 message = " + receive );
                
                StringTokenizer st = null;
                
                int protocol = 0;
                
                if ( receive != null ) {
                    st = new StringTokenizer( ( String ) receive, util.command.Protocol.separator );
                    log.info( st.countTokens() );
                    // log.info( st.nextToken() );
                    
                    protocol = Integer.parseInt( st.nextToken() );
                    System.out.println( "protocol:" + protocol );
                }
                
                switch ( protocol ) {
                    
                    case util.command.Protocol.TALK_IN: {
                        String nickName = st.nextToken();
                        String message  = st.nextToken();
                        tc.jta_display.append( nickName + ": " + message );
                        break;
                    }
                    // 200|tomato|메시지
                    case util.command.Protocol.MESSAGE: {
                        System.out.println( "util.command.Protocol.MESSAGE" );
                        String nickName = st.nextToken();
                        String message  = st.nextToken();
                        tc.jta_display.append( nickName + ": " + message + "\n" );
                    }
                        break;
                    
                    default:
                        System.out.println( "해당하는 프로토콜이 존재하지 않습니다." );
                        break;
                }
            }
        }
        catch ( Exception e ) {
            e.printStackTrace();
        }
    }
}
