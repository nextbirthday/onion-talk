package model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import lombok.extern.log4j.Log4j2;
import util.dto.Account;
import util.oracle.OracleConnection;

@Log4j2( topic = "database" )
public class FriendTableLogic {
    
    private Connection        conn;
    private PreparedStatement pstmt;
    private ResultSet         rs;
    
    /**
     * 사용자가 로그인에 성공하면 Oracle 서버에 친구목록 테이블을 생성하는 메서드이다.
     * 테이블 네임은 사용자의 아이디로 생성한다.
     * 
     * @param account
     */
    public void createFriendTable( Account account ) {
        
        // String sql = "CREATE TABLE " + account.getUser_id()
        // + " (USER_ID VARCHAR2(40), FRIEND_ID VARCHAR2(40), CONSTRAINT FRIEND_ID_uq UNIQUE(FRIEND_ID), FRIEND_REG DATE DEFAULT
        // SYSDATE ) ";
        
        String sql = " CREATE TABLE " + account.getUser_id()
                        + " ( USER_ID VARCHAR2(40), FRIEND_ID VARCHAR2(40), FRIEND_REG DATE DEFAULT SYSDATE ) ";
        
        try {
            conn = OracleConnection.getConnection();
            pstmt = conn.prepareStatement( sql );
            pstmt.execute( sql );
        }
        catch ( ClassNotFoundException | SQLException | IOException e ) {
            log.error( "error", e );
        }
        finally {
            OracleConnection.freeConnection( conn, pstmt );
        }
    }
    
    /**
     * Main 화면에서
     * 
     * @param account  - 사용자의 계정정보
     * @param friendID - Main 화면에서 입력받은 친구의 ID
     * @return INSERT 여부 - 성공 1, 실패 0
     */
    public int insertFriend( Account account, String friendID ) {
        
        int result = 0;
        
        StringBuilder sql = new StringBuilder();
        
        sql.append( " INSERT INTO ? " );
        sql.append( " (USER_ID, FRIEND_ID, FRIEND_REG) " );
        sql.append( " VALUES(? , ?, SYSDATE); " );
        
        try {
            conn = OracleConnection.getConnection();
            pstmt = conn.prepareStatement( sql.toString() );
            pstmt.setString( 1, account.getUser_id() );
            pstmt.setString( 2, account.getUser_id() );
            pstmt.setString( 3, friendID );
            log.info( account.toString() );
            result = pstmt.executeUpdate();
            
            if ( result > 0 ) {
                log.info( "친구 추가 성공!" );
            }
        }
        catch ( Exception e ) {
            log.error( "error", e );
        }
        finally {
            OracleConnection.freeConnection( conn, pstmt, rs );
        }
        return result;
    }
    
}
