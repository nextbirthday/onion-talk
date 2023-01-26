package client.thread;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.StringTokenizer;
import java.util.Vector;

import lombok.extern.log4j.Log4j2;
import util.command.Protocol;

@Log4j2( topic = "logger" )
public class TalkClientThread extends Thread {
    
    private ObjectOutputStream oos;
    private ObjectInputStream  ois;
    private TalkClient         tc;
    
    public TalkClientThread( ObjectOutputStream oos, ObjectInputStream ois ) {
        this.oos = oos;
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
                String          nickname = st.nextToken();
                String          message  = st.nextToken();
                
                switch ( protocol ) {
                    
                    // 입장
                    case Protocol.TALK_IN:
                        tc = new TalkClient( nickname, oos );
                        tc.jta_display.append( nickname + message + "\n" );
                        
                        Vector<String> temp = new Vector<>();
                        temp.add( nickname );
                        tc.dtm.addRow( temp );
                        break;
                    
                    // 메시지 수신
                    case Protocol.MESSAGE:
                        tc.jta_display.append( "[" + nickname + "]" + message + "\n" );
                        tc.jta_display.setCaretPosition( tc.jta_display.getDocument().getLength() );
                        break;
                    
                    // 상대방이 나갔을 경우 채팅방 나갔을 경우 목록에서 삭제
                    case Protocol.TALK_OUT:
                        tc.jta_display.append( nickname + ": " + message + "\n" );
                        
                        for ( int i = 0; i < tc.dtm.getRowCount(); i++ )
                            if ( nickname.equals( tc.dtm.getValueAt( i, 0 ) ) ) {
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
