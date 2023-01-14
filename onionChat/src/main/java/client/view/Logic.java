package client.view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.dto.Account;
import util.oracle.OnionDB;
import util.oracle.OracleConnection;

public class Logic {
    
    private Connection        conn;
    private PreparedStatement pstmt;
    private ResultSet         rs;
    
    public int statusMessage( Account account ) {
        int           result = 0;
        StringBuilder sql    = new StringBuilder();
        
        sql.append( "  UPDATE ONION.INFO             " );
        sql.append( "  SET                           " );
        sql.append( "  USER_MSG = ?               " );
        sql.append( "  WHERE USER_ID = ?;            " );
        
        try {
            conn = OnionDB.getConnection();
            pstmt = conn.prepareStatement( sql.toString() );
            pstmt.setString( 1, null );
            pstmt.setString( 2, null );
            
            result = pstmt.executeUpdate();
        }
        catch ( Exception e ) {
            e.printStackTrace();
        }finally {
            OracleConnection.freeConnection( conn, pstmt );
        }
        
        return result;
        
    }
}