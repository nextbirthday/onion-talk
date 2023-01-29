package model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import lombok.extern.log4j.Log4j2;
import util.dto.Account;
import util.oracle.OracleConnection;

@Log4j2( topic = "database" )
public class StatusMessageLogic {
    private Connection        conn;
    private PreparedStatement pstmt;
    
    public int statusMessage( Account account, String statusMessage ) {
        
        int           result = 0;
        StringBuilder sql    = new StringBuilder();
        
        sql.append( "  UPDATE ONION.INFO             " );
        sql.append( "  SET                           " );
        sql.append( "  USER_MSG = ?               " );
        sql.append( "  WHERE USER_ID = ?           " );
        
        try {
            conn = OracleConnection.getConnection();
            pstmt = conn.prepareStatement( sql.toString() );
            pstmt.setString( 1, statusMessage );
            pstmt.setString( 2, account.getUser_id() );
            
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
