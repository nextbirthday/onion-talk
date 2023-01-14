package util.oracle;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.ibatis.io.Resources;

public class OracleConnection {
    private OracleConnection() {}
    
    private static final String     RESOURCE = "connection.properties";
    private static final Properties PROPS    = new Properties();
    private static final String     DRIVER   = "oracle.jdbc.driver.OracleDriver";
    
    private static Connection con;
    
    public static synchronized Connection getConnection() throws SQLException, ClassNotFoundException, IOException {
        
        Reader reader = Resources.getResourceAsReader( RESOURCE );
        PROPS.load( reader );
        final String url      = PROPS.getProperty( "url" );
        final String user     = PROPS.getProperty( "username" );
        final String password = PROPS.getProperty( "password" );
        
        if ( con == null || con.isClosed() ) {
            Class.forName( DRIVER );
            con = DriverManager.getConnection( url, user, password );
        }
        
        return con;
    }
    
    // 사용한 자원을 반납하는 코드는 명시적으로 하는 것을 원칙으로 하고 있다.
    // 반납하는 순서는 생성된 역순으로 진행한다. - 자바튜닝팀 지적사항
    // 사용한 자원 반납하기 -INSERT, UPDATE, DELETE
    public static void freeConnection( Connection con, Statement stmt ) {
        
        if ( stmt != null ) {
            
            try {
                stmt.close();
            }
            catch ( SQLException e ) {}
        }
        
        if ( con != null ) {
            
            try {
                con.close();
            }
            catch ( SQLException e ) {}
        }
    } // end of freeConnection
    
    public static void freeConnection( Connection con, PreparedStatement pstmt ) {
        
        if ( pstmt != null ) {
            
            try {
                pstmt.close();
            }
            catch ( SQLException e ) {}
        }
        
        if ( con != null ) {
            
            try {
                con.close();
            }
            catch ( SQLException e ) {}
        }
    } // end of freeConnection
    
    // 사용한 자원 반납하기 - SELECT - 커서 조작이 필요하다.
    public static void freeConnection( Connection con, PreparedStatement pstmt, ResultSet rs ) {
        
        if ( rs != null ) {
            
            try {
                rs.close();
            }
            catch ( SQLException e ) {}
        }
        
        if ( pstmt != null ) {
            
            try {
                pstmt.close();
            }
            catch ( SQLException e ) {}
        }
        
        if ( con != null ) {
            
            try {
                con.close();
            }
            catch ( SQLException e ) {}
        }
    } // end of freeConnection
    
    public static void freeConnection( Connection con, Statement stmt, ResultSet rs ) {
        
        if ( rs != null ) {
            
            try {
                rs.close();
            }
            catch ( SQLException e ) {}
        }
        
        if ( stmt != null ) {
            
            try {
                stmt.close();
            }
            catch ( SQLException e ) {}
        }
        
        if ( con != null ) {
            
            try {
                con.close();
            }
            catch ( SQLException e ) {}
        }
    } // end of freeConnection
    
}
