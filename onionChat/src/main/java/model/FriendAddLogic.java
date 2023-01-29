package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import lombok.extern.log4j.Log4j2;
import util.dto.Account;
import util.oracle.OracleConnection;

@Log4j2( topic = "database" )
public class FriendAddLogic {
    
    private Connection        conn;
    private PreparedStatement pstmt;
    private ResultSet         rs;
    
    public Account friendIDCheck( String friendID ) {
        
        Account account = new Account();
        
        StringBuilder sql = new StringBuilder();
        
        sql.append( "    SELECT USER_NAME, USER_ID, USER_NICK  " );
        sql.append( "    FROM ONION.INFO            " );
        sql.append( "    WHERE USER_ID = ?          " );
        
        try {
            conn = OracleConnection.getConnection();
            pstmt = conn.prepareStatement( sql.toString() );
            pstmt.setString( 1, friendID );
            rs = pstmt.executeQuery();
            
            log.info( sql.toString() );
            
            while ( rs.next() ) {
                account.setUser_name( rs.getString( "user_name" ) );
                account.setUser_id( rs.getString( "user_id" ) );
                account.setUser_nick( rs.getString( "user_nick" ) );
            }
            log.info( account );
        }
        catch ( Exception e ) {
            log.error( "error", e );
        }
        
        return account;
    }
    
    public int friendAdd( Account account, String friendID ) {
        
        int result = 0;
        
        StringBuilder sql = new StringBuilder();
        log.debug( "MyAccount = {}, friendID = {}", account, friendID );
        
        sql.append( " INSERT INTO ONION." + account.getUser_id() + " ( USER_ID, FRIEND_ID, FRIEND_REG )  VALUES( ? , ? , SYSDATE )  " );
        
        try {
            conn = OracleConnection.getConnection();
            pstmt = conn.prepareStatement( sql.toString() );
            pstmt.setString( 1, account.getUser_id() );
            pstmt.setString( 2, friendID );
            result = pstmt.executeUpdate();
            
            log.info( sql.toString() );
            
        }
        catch ( Exception e ) {
            log.error( "error", e );
        }
        return result;
    }
}
