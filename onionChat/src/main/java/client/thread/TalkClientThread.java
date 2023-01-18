package client.thread;

import java.io.ObjectInputStream;
import java.util.StringTokenizer;
import java.util.Vector;

import lombok.extern.log4j.Log4j2;
import util.command.Protocol;

@Log4j2
public class TalkClientThread extends Thread {
    
    TalkClient                tc;
    private ObjectInputStream ois;
    
    public TalkClientThread() {}
    
    public TalkClientThread( TalkClient tc, ObjectInputStream ois ) {
        this.tc = tc;
        this.ois = ois;
    }
    
    @Override
    public void run() {
        
        try {
            
            while ( true ) {
                String receive = null;
                
                receive = ( String ) ois.readObject();
                log.info( "서버에서 전송된 message = " + receive );
                
                StringTokenizer st = null;
                
                int protocol = 0;
                
                st = new StringTokenizer( receive, Protocol.SEPARATOR );
                log.info( st.countTokens() );
                
                protocol = Integer.parseInt( st.nextToken() );
                log.info( "protocol = " + protocol );
                
                switch ( protocol ) {
                    
                    case Protocol.TALK_IN: {
                        
                        String nickName = st.nextToken();
                        String message  = st.nextToken();
                        tc.jta_display.append( nickName + message + "\n" );
                        break;
                    }
                    
                    case Protocol.MESSAGE: {
                        System.out.println( "Protocol.MESSAGE" );
                        String nickName = st.nextToken();
                        String message  = st.nextToken();
                        tc.jta_display.append( nickName + ": " + message + "\n" );
                        break;
                    }
                    
                    case Protocol.ENTER_ROOM: {
                        String nickName = st.nextToken();
                        
                        Vector<String> temp = new Vector<>();
                        temp.add( nickName );
                        tc.dtm.addRow( temp );
                        log.info( temp );
                        break;
                    }
                    case Protocol.ERROR: {
                        String error = st.nextToken();
                        tc.jta_display.append( error + "\n" );
                        break;
                    }
                    
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
