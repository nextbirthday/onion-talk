package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import client.view.JoinView;
import lombok.extern.log4j.Log4j2;
import util.dto.Account;
import util.oracle.OracleConnection;

@Log4j2( topic = "database" )
public class SignUpLogic {
    
    private JoinView          view;
    private Connection        conn;
    private PreparedStatement pstmt;
    private ResultSet         rs;
    
    public SignUpLogic() {}
    
    public SignUpLogic( JoinView view ) {
        this.view = view;
    }
    
    /**
     * @author HOJAE
     *         회원가입 창에서 ID 중복체크를 위한 메서드
     *         <p>
     *         사용자가 ID 텍스트필드에 입력한 ID가 ONION.ACCOUNT TABLE의 USER_ID COLUMN의 data가 존재하는지 비교
     *         중복된 아이디를 입력했으면 int result 0 -> 1 즉, 리턴된 record가 하나라도 있으면 result 값이 0보다 커진다.
     *         
     * @return ID 중복체크 버튼을 눌렀을 때 ID가 중복이 아니면 0을, ID가 중복이면 1을 반환
     */
    public int idCheck() {
        
        int result = 0;
        
        StringBuilder sql = new StringBuilder();
        
        sql.append( "      SELECT USER_ID " ); // COLUMN USER_ID
        sql.append( "  FROM ONION.INFO " ); // ACCOUNT TABLE
        sql.append( "  WHERE USER_ID = ?" ); // CONDITION USER_ID data와 ?일치하면
        
        try {
            conn = OracleConnection.getConnection();
            pstmt = conn.prepareStatement( sql.toString() );
            pstmt.setString( 1, view.getJtf_id().getText() );
            rs = pstmt.executeQuery();
            
            log.info( view.getJtf_id().getText() );
            
            // ResultSet
            if ( rs.next() ) {
                result++;
            }
        }
        catch ( Exception e ) {
            log.error( "Exception :", e );
        }
        finally {
            OracleConnection.freeConnection( conn, pstmt, rs );
        }
        return result;
    }
    
    /**
     * @author HOJAE
     *         Account DTO를 파라미터로 받아서 오라클 INSERT 를 수행하는 메서드.
     *         
     * @param account - Account 타입 객체
     * @return INSERT 성공 시 : 성공한 개수, 실패 시 0
     */
    public int register( Account account ) {
        StringBuilder sql = new StringBuilder();
        log.info( account.toString() );
        int result = 0;
        sql.append( " INSERT INTO ONION.INFO " );
        sql.append( " VALUES (?, ?, ?, ?, ?, ?, ?, SYSDATE ) " );
        
        try {
            conn = OracleConnection.getConnection();
            pstmt = conn.prepareStatement( sql.toString() );
            pstmt.setString( 1, account.getUser_id() );
            pstmt.setString( 2, account.getUser_pw() );
            pstmt.setString( 3, account.getUser_name() );
            pstmt.setString( 4, account.getUser_birth() );
            pstmt.setString( 5, account.getUser_phone() );
            pstmt.setString( 6, account.getUser_nick() );
            pstmt.setString( 7, null );
            // pstmt.setString( 8, null );
            result = pstmt.executeUpdate();
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
