package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import client.view.MainView;
import lombok.extern.log4j.Log4j2;
import util.dto.Account;
import util.oracle.OracleConnection;

@Log4j2
public class MainFriendLogic {
    private MainView          view;
    private Connection        conn;
    private PreparedStatement pstmt;
    private ResultSet         rs;
    
    /**
     * @author leehs
     *         검색버튼을 눌렀을 때 입력된 ID를 DB서버와 연동해서 가입된 아이디인지 확인한다
     *         
     * @param id (DB COLUMN NAME : USER_ID)
     * @return 검색버튼을 눌렀을 때 DB에 ID가 있으면 내용을, 없으면 0을 반환
     */

    public Account friendIdFind( String id ) {
        Account result = null;
        StringBuilder sql = new StringBuilder();
        sql.append( "    SELECT USER_ID, USER_NAME         " );
        sql.append( "    FROM ONION.INFO      " );
        sql.append( "    WHERE USER_ID = ?     " );
                
        try {
            conn = OracleConnection.getConnection();
            pstmt = conn.prepareStatement( sql.toString() );
            pstmt.setString( 1, id );
            rs = pstmt.executeQuery();
            // ResultSet
            if ( rs.next() ) {
                result = new Account();
                result.setUser_id(rs.getString("USER_ID"));
                result.setUser_name(rs.getString("USER_NAME"));
                
            }else{
                result = new Account();
                result.setUser_id("없음");
            }
        }
        catch ( Exception e ) {
            e.printStackTrace();
        }
        finally {
            OracleConnection.freeConnection( conn, pstmt, rs );
        }
        
        return result;
    }
}