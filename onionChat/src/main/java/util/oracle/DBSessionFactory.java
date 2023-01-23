package util.oracle;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class DBSessionFactory {
    // MyBatis에 틀이 있다.
    public static SqlSessionFactory sqlSessionFactory;
    
    private DBSessionFactory() {}
    
    public static synchronized SqlSessionFactory getInstance() {
        
        if ( sqlSessionFactory == null ) {
            
            try {
                final String resource    = "mybatis-config.xml";
                InputStream  inputStream = Resources.getResourceAsStream( resource );
                sqlSessionFactory = new SqlSessionFactoryBuilder().build( inputStream );
            }
            catch ( IOException e ) {
                log.error( "데이터베이스 연결 실패 : {}", e );
                e.printStackTrace();
            }
        }
        return sqlSessionFactory;
    }
}
