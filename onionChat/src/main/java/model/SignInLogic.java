package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import client.view.LoginView;
import lombok.extern.log4j.Log4j2;
import util.dto.Account;
import util.oracle.OracleConnection;

@Log4j2
public class SignInLogic {
    private Connection        conn;
    private PreparedStatement pstmt;
    private ResultSet         rs;
    LoginView                 loginView;
    
    public SignInLogic() {}
    
    public SignInLogic( LoginView loginview ) {
        this.loginView = loginview;
    }
    
    /**
     * @author HOJAE
     *         사용자로부터 ID와 PW를 파라미터로 받아온다.
     *         사용자로부터 입력받은 ID와 PW가 DB 서버의 record와 일치하면 USER_NICK을 DB에서 가져온다.
     *         DB로부터 가져온 닉네임을 Account 클래스 user_nick 값에 초기화
     *         
     * @param account
     * @return ID와 닉네임이 들어있는 Account 객체
     */
    public Account signIn( Account account ) {
        
        StringBuilder sql = new StringBuilder();
        sql.append( "   SELECT USER_ID, USER_NICK      " );
        sql.append( "   FROM ONION.INFO                 " );
        sql.append( "   WHERE USER_ID = ?           " );
        sql.append( "   AND USER_PW = ?             " );
        
        try {
            conn = OracleConnection.getConnection();
            pstmt = conn.prepareStatement( sql.toString() );
            pstmt.setString( 1, account.getUser_id() );
            pstmt.setString( 2, account.getUser_pw() );
            rs = pstmt.executeQuery();
            
            if ( rs.next() ) {
                account.setUser_id( rs.getString( "USER_ID" ) );
                account.setUser_nick( rs.getString( "USER_NICK" ) );
                log.info( account.toString() );
            }
        }
        catch ( Exception e ) {
            e.printStackTrace();
        }
        finally {
            OracleConnection.freeConnection( conn, pstmt, rs );
        }
        return account;
    }
}
