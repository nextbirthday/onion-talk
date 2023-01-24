package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import client.view.ChatView;
import lombok.extern.log4j.Log4j2;
import util.dto.ChatNote;
import util.oracle.OracleConnection;

@Log4j2( topic = "database" )
public class ChatNoteLogic {
    private Connection        conn;
    private PreparedStatement pstmt;
    private ResultSet         rs;
    
    public ChatNoteLogic() {}
    
    public ChatNoteLogic( ChatView chatview ) {}
    
    /**
     * @author eun
     *         사용자와 1:1 혹은 1:n 채팅이 시작되면 입력한 메시지를 DB에 넣어준다
     *         대화 종료 후 새롭게 대화 시작될 시 Chat_id, Chat_user를 확인하고 Chat_Note를 Chat_date를
     *         시간순으로 가져온다
     * @param
     * @return
     */
    public List<ChatNote> chatNote() {
        List<ChatNote> chatList = new ArrayList<>();
        StringBuilder  sql      = new StringBuilder();
        sql.append( "   SELECT ch_id, ch_user, ch_note, ch_date      " );
        sql.append( "   FROM chatnote                 " );
        
        try {
            conn = OracleConnection.getConnection();
            pstmt = conn.prepareStatement( sql.toString() );
            rs = pstmt.executeQuery();
            ChatNote cn = null;
            
            while ( rs.next() ) {
                cn = new ChatNote();
                cn.setCh_id( rs.getString( "ch_id" ) );
                cn.setCh_user( rs.getString( "ch_user" ) );
                cn.setCh_note( rs.getString( "ch_note" ) );
                cn.setCh_date( rs.getString( "ch_date" ) );
                chatList.add( cn );
            }
            chatList.forEach( e -> log.debug( e ) );
        }
        catch ( Exception e ) {
            log.error( "Exception :", e );
        }
        finally {
            OracleConnection.freeConnection( conn, pstmt, rs );
        }
        return chatList;
    }// end of chatNote
    
    // 소켓 접속해 채팅 시 소켓 서버에 전송되고 그 내용을 DB에 저장
    public int chatMsgInsert( String user_id, String your_id, String ch_message ) {
        int           result = 0;
        StringBuilder sql    = new StringBuilder();
        // sql.append("insert into chatnote values('user','tomato','메세지',sysdate)");
        sql.append( "insert into chatnote values('?', '?', '?', sysdate)" );
        
        try {
            conn = OracleConnection.getConnection();
            pstmt = conn.prepareStatement( sql.toString() );
            // 파라미터의 string 값에 ? 값으로 변경해서 사용
            pstmt.setString( 1, user_id );
            pstmt.setString( 2, your_id );
            pstmt.setString( 3, ch_message );
            result = pstmt.executeUpdate( sql.toString() );
            log.debug( "result = {}", result );
        }
        catch ( Exception e ) {
            log.error( "Exception :", e );
        }
        finally {
            OracleConnection.freeConnection( conn, pstmt );
        }
        return result;
    }
}