package client.view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import lombok.extern.log4j.Log4j2;
import util.dto.Account;
import util.oracle.OracleConnection;

@Log4j2
public class SignUpModel {
    private JoinView          view;
    private Connection        conn;
    private PreparedStatement pstmt;
    private ResultSet         rs;
    
    public SignUpModel() {}
    
    public SignUpModel( JoinView view ) {
        this.view = view;
    }
    
    /**
     * 회원가입 창에서 ID 중복체크를 위한 메서드
     * <p>
     * 사용자가 ID 텍스트필드에 입력한 ID가 ONION.ACCOUNT TABLE의 USER_ID COLUMN의 data가 존재하는지 비교
     * 중복된 아이디를 입력했으면 int result 0 -> 1 즉, 리턴된 record가 하나라도 있으면 result 값이 0보다 커진다.
     * 
     * @return ID 중복체크 버튼을 눌렀을 때 ID가 중복이 아니면 0을, ID가 중복이면 1을 반환
     */
    public int idCheck() {
        int result = 0;
        
        StringBuilder sql = new StringBuilder();
        
        sql.append( "      SELECT USER_ID " ); // COLUMN USER_ID
        sql.append( "  FROM ONION.ACCOUNT " ); // ACCOUNT TABLE
        sql.append( "  WHERE USER_ID = ?" ); // CONDITION USER_ID data와 ?일치하면
        
        try {
            conn = OracleConnection.getConnection();
            pstmt = conn.prepareStatement( sql.toString() );
            pstmt.setString( 1, view.jtf_id.getText() );
            rs = pstmt.executeQuery();
            
            log.info( view.jtf_id.getText() );
            
            // ResultSet
            if ( rs.next() ) {
                result++;
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
    
    /**
     * Account DTO를 파라미터로 받아서 오라클 INSERT 를 수행하는 메서드.
     * 
     * @param account - Account 타입 객체
     * @return INSERT 성공 시 : 성공한 개수, 실패 시 0
     */
    public int register( Account account ) {
        StringBuilder sql    = new StringBuilder();
        int           result = 0;
        
        sql.append( " INSERT INTO ONION.ACCOUNT " );
        sql.append( " VALUES (?, ?, ?, ?, ?, ?) " );
        
        try {
            conn = OracleConnection.getConnection();
            pstmt = conn.prepareStatement( sql.toString() );
            pstmt.setString( 1, account.getUser_id() );
            pstmt.setString( 2, account.getUser_pw() );
            pstmt.setString( 3, account.getUser_name() );
            pstmt.setString( 4, account.getUser_nick() );
            pstmt.setString( 5, account.getUser_birth() );
            pstmt.setString( 6, account.getUser_phone() );
            log.info( account.toString() );
            result = pstmt.executeUpdate();
            
        }
        catch ( Exception e ) {
            e.printStackTrace();
        }
        finally {
            OracleConnection.freeConnection( conn, pstmt );
        }
        
        return result;
    }
    
    public Account signIn( String id, String pw ) {
        StringBuilder sql = new StringBuilder();
        Account       acc = new Account();
        sql.append( "   SELECT USER_NICK      " );
        sql.append( "   FROM ONION.ACCOUNT    " );
        sql.append( "   WHERE USER_ID = ?    " );
        sql.append( "   AND USER_PW = ?      " );
        
        try {
            conn = OracleConnection.getConnection();
            pstmt = conn.prepareStatement( sql.toString() );
            pstmt.setString( 1, id );
            pstmt.setString( 2, pw );
            rs = pstmt.executeQuery();
            
            if ( rs.next() ) {
                
                acc.setUser_nick( rs.getString( "USER_NICK" ) );
            }
            
        }
        catch ( Exception e ) {
            e.printStackTrace();
        }
        return acc;
    }
    
    /**
     * 사용자로부터 ID와 PW를 파라미터로 받아온다.
     * 사용자로부터 입력받은 ID와 PW가 DB 서버의 record와 일치하면 USER_NICK을 DB에서 가져온다.
     * DB로부터 가져온 닉네임을 Account 클래스 user_nick 값에 초기화
     * 
     * @param account
     * @return 닉네임 값이 들어있는 Account 객체
     */
    public Account signIn( Account account ) {
        
        StringBuilder sql = new StringBuilder();
        sql.append( "   SELECT USER_NICK      " );
        sql.append( "   FROM ONION.ACCOUNT    " );
        sql.append( "   WHERE USER_ID = ?    " );
        sql.append( "   AND USER_PW = ?      " );
        
        try {
            conn = OracleConnection.getConnection();
            pstmt = conn.prepareStatement( sql.toString() );
            pstmt.setString( 1, account.getUser_id() );
            pstmt.setString( 2, account.getUser_pw() );
            rs = pstmt.executeQuery();
            
            if ( rs.next() ) {
                account.setUser_nick( rs.getString( "USER_NICK" ) );
                LoginApp.myId = rs.getString( "USER_NICK" );
                log.info( LoginApp.myId );
            }
            
        }
        catch ( Exception e ) {
            e.printStackTrace();
        }
        return account;
    }
    
    /**
     * 로그인 창에서 ID/PW 찾기 버튼을 눌렀을 때 ID와 PW를 DB서버와 연동해서
     * 사용자가 입력한 정보가 맞으면 사용자에게 ID와 PW를 보여준다.
     * 
     * @param username (DB COLUMN NAME : USER_NAME)
     * @param phone    (DB COLUMN NAME : USER_PHONE)
     * @return ID 찾기 버튼을 눌렀을 때 입력한 이름과 번호가 맞으면 1을, 틀리면 1을 반환
     */
    public Account findID( String username, String phone ) {
        StringBuilder sql = new StringBuilder();
        Account       acc = new Account();
        
        sql.append( "    SELECT USER_ID          " );
        sql.append( "    FROM ONION.ACCOUNT      " );
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
        catch ( Exception e ) {}
        
        return acc;
    }
    
}
