package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import lombok.extern.log4j.Log4j2;
import util.dto.Account;
import util.oracle.OnionDB;
import util.oracle.OracleConnection;

@Log4j2
public class FindIDPWLogic {
    private Connection        conn;
    private PreparedStatement pstmt;
    private ResultSet         rs;
    
    /**
     * @author HOJAE
     *         로그인 창에서 ID/PW 찾기 버튼을 눌렀을 때 ID와 PW를 DB서버와 연동해서
     *         사용자가 입력한 정보가 맞으면 사용자에게 ID와 PW를 보여준다.
     *         
     * @param username (DB COLUMN NAME : USER_NAME)
     * @param phone    (DB COLUMN NAME : USER_PHONE)
     * @return ID 찾기 버튼을 눌렀을 때 입력한 이름과 번호가 맞으면 1을, 틀리면 1을 반환
     */
    public Account findID( String username, String phone ) {
        StringBuilder sql = new StringBuilder();
        Account       acc = new Account();
        
        sql.append( "    SELECT USER_ID          " );
        sql.append( "    FROM ONION.INFO      " );
        sql.append( "    WHERE USER_NAME = ?     " );
        sql.append( "    AND USER_PHONE = ?      " );
        
        log.info( username + ", " + phone );
        
        try {
            conn = OracleConnection.getConnection();
            pstmt = conn.prepareStatement( sql.toString() );
            pstmt.setString( 1, username );
            pstmt.setString( 2, phone );
            rs = pstmt.executeQuery();
            
            if ( rs.next() ) {
                acc.setUser_id( rs.getString( "USER_ID" ) );
            }
            
        }
        catch ( Exception e ) {
            e.printStackTrace();
        }
        finally {
            OracleConnection.freeConnection( conn, pstmt, rs );
        }
        
        return acc;
    }
    
    public Account pwFind( String username, String id ) {
        
        StringBuilder sql = new StringBuilder();
        Account       acc = new Account();
        
        sql.append( "    SELECT USER_PW          " );
        sql.append( "    FROM ONION.INFO      " );
        sql.append( "    WHERE USER_NAME = ?     " );
        sql.append( "    AND USER_ID = ?      " );
        
        log.info( "username = " + username + ", id = " + id );
        
        try {
            conn = OracleConnection.getConnection();
            pstmt = conn.prepareStatement( sql.toString() );
            pstmt.setString( 1, username );
            pstmt.setString( 2, id );
            rs = pstmt.executeQuery();
            
            if ( rs.next() ) {
                acc.setUser_pw( rs.getString( "USER_PW" ) );
            }
            
        }
        catch ( Exception e ) {
            e.printStackTrace();
        }
        finally {
            OracleConnection.freeConnection( conn, pstmt, rs );
        }
        return acc;
    }
}
