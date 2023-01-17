package client.thread;

import java.io.ObjectInputStream;
import java.util.StringTokenizer;
import java.util.Vector;

import lombok.extern.log4j.Log4j2;
import util.command.Protocol;

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
        
        try {
            
            while ( true ) {
                Object receive = null;
                
                receive = ( String ) ois.readObject();
                log.info( "서버에서 전송된 message = " + receive );
                
                StringTokenizer st = null;
                
                int protocol = 0;
                
                if ( receive != null ) {
                    st = new StringTokenizer( ( String ) receive, Protocol.SEPARATOR );
                    log.info( st.countTokens() );
                    
                    protocol = Integer.parseInt( st.nextToken() );
                }
                
                switch ( protocol ) {
                    
                    case util.command.Protocol.TALK_IN: {
                        String nickName = st.nextToken();
                        String message  = st.nextToken();
                        tc.jta_display.append( nickName + message + "\n" );
                        
                        /*
                         * JTable은 양식일 뿐이고 DataSet은 DefaultTableModel이니까 거기에 닉네임을 출력한다.
                         * 데이터셋 객체에 한 개 row 추가하기
                         */
                        Vector<String> temp = new Vector<>();
                        temp.add( nickName );
                        tc.dtm.addRow( temp );
                        
                        break;
                    }
                    case util.command.Protocol.MESSAGE: {
                        System.out.println( "Protocol.MESSAGE" );
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
