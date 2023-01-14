package client.thread;

import java.util.StringTokenizer;
import java.util.Vector;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class TalkClientThread extends Thread {
    TalkClient tc = null;
    
    public TalkClientThread() {}
    
    public TalkClientThread( TalkClient tc ) {
        this.tc = tc;
    }
    
    // server측에서 client가 접속하면 접속자의 정보를 List<TalkServerThread> add(생성자) 했고
    // 메시지를 청취하자 마자 클라이언트측에 반복문을 돌려서 쓰기 한다.(전송함) - broadCastring(String message)
    @Override
    public void run() {
        boolean isStop = false;
        
        // run_stop:
        while ( !isStop ) {
            
            try {
                // 100#tomato 님 입장하였습니다.
                String message = "";
                message = ( String ) tc.ois.readObject();
                System.out.println( "서버에서 전송된 message:" + message );
                StringTokenizer st       = null;
                int             protocol = 0; // 100 200 300 400 500
                
                if ( message != null ) {
                    st = new StringTokenizer( message, "#" );
                    protocol = Integer.parseInt( st.nextToken() );
                }
                System.out.println( "protocol:" + protocol );
                
                switch ( protocol ) {
                    case util.command.Protocol.TALK_IN: {
                        String nickName = st.nextToken();
                        // tc.jta_display.append( nickName + "님이 입장하였습니다.\n" );
                        // JTable은 양식일 뿐이고 데이터셋은 DefaultTableModel이니까 거기에 닉네임을 출력함
                        Vector<String> temp = new Vector<>();
                        temp.add( nickName );
                        // 데이터셋 객체에 한 개 row 추가하기
                        // tc.dtm.addRow( temp );
                        break;
                    }
                    default:
                        System.out.println( "해당하는 프로토콜이 존재하지 않습니다." );
                }
            }
            catch ( Exception e ) {
                e.printStackTrace();
            }
        }
    }
    
}
