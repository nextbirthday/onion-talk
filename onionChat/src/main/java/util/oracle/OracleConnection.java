package util.oracle;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.ibatis.io.Resources;

import lombok.extern.log4j.Log4j2;

@Log4j2( topic = "database" )
public class OracleConnection {
    
    private OracleConnection() {}
    
    private static final String RESOURCE = "connection.properties";
    
    private static Connection con;
    
    public static synchronized Connection getConnection() throws SQLException, ClassNotFoundException, IOException {
        
        if ( con == null || con.isClosed() ) {
            Reader     reader = Resources.getResourceAsReader( RESOURCE );
            Properties props  = new Properties();
            props.load( reader );
            
            final String driver   = props.getProperty( "driver" );
            final String url      = props.getProperty( "url" );
            final String user     = props.getProperty( "username" );
            final String password = props.getProperty( "password" );
            
            Class.forName( driver );
            con = DriverManager.getConnection( url, user, password );
        }
        return con;
    }
    
    // 사용한 자원을 반납하는 코드는 명시적으로 하는 것을 원칙으로 하고 있다.
    // 반납하는 순서는 생성된 역순으로 진행한다. - 자바튜닝팀 지적사항
    // 사용한 자원 반납하기 -INSERT, UPDATE, DELETE
    public static void freeConnection( Connection con, PreparedStatement pstmt ) {
        
        try {
            if ( pstmt != null && !pstmt.isClosed() )
                pstmt.close();
            
            if ( con != null && !con.isClosed() )
                con.close();
        }
        catch ( Exception e ) {
            log.error( "리소스 반납 예외 발생 : ", e );
        }
    }
    
    // 사용한 자원 반납하기 - SELECT - 커서 조작이 필요하다.
    public static void freeConnection( Connection con, PreparedStatement pstmt, ResultSet rs ) {
        
        try {
            if ( rs != null && !rs.isClosed() )
                rs.close();
            
            if ( pstmt != null && !pstmt.isClosed() )
                pstmt.close();
            
            if ( con != null && !con.isClosed() )
                con.close();
        }
        catch ( Exception e ) {
            log.error( "리소스 반납 예외 발생 : ", e );
        }
    }
}
