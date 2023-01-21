package client.thread;

import java.io.ObjectInputStream;
import java.util.StringTokenizer;
import java.util.Vector;

import lombok.extern.log4j.Log4j2;
import util.command.Protocol;

@Log4j2( topic = "logger" )
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
                String receive = ( String ) ois.readObject();
                log.debug( "클라이언트 수신 메시지 : {}", receive );
                
                StringTokenizer st       = new StringTokenizer( receive, Protocol.SEPARATOR );
                int             protocol = Integer.parseInt( st.nextToken() );
                String          nickName = st.nextToken();
                String          message  = st.nextToken();
                
                switch ( protocol ) {
                    
                    // 입장
                    case Protocol.TALK_IN:
                        tc.jta_display.append( nickName + message + "\n" );
                        
                        Vector<String> temp = new Vector<>();
                        temp.add( nickName );
                        tc.dtm.addRow( temp );
                        break;
                    
                    // 메시지 수신
                    case Protocol.MESSAGE:
                        tc.jta_display.append( "[" + nickName + "]" + message + "\n" );
                        tc.jta_display.setCaretPosition( tc.jta_display.getDocument().getLength() );
                        break;
                    
                    // 로그아웃
                    case Protocol.TALK_OUT:
                        tc.jta_display.append( nickName + ": " + message + "\n" );
                        
                        for ( int i = 0; i < tc.dtm.getRowCount(); i++ )
                            if ( nickName.equals( tc.dtm.getValueAt( i, 0 ) ) ) {
                                tc.dtm.removeRow( i );
                                break;
                            }
                        break;
                    
                    default:
                        log.error( "no matched protocol" );
                        break;
                }
            }
        }
        catch ( Exception e ) {
            log.error( "Exception : ", e );
        }
    }
}
