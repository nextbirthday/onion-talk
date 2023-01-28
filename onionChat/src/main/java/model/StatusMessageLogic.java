package model;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import lombok.extern.log4j.Log4j2;
import util.dto.Account;
import util.oracle.DBSessionFactory;

@Log4j2( topic = "login" )
public class StatusMessageLogic {
    
    private SqlSessionFactory sqlSessionFactory;
    private SqlSession        sqlSession;
    
    public int setStatusMessage( Account account ) {
        
        sqlSessionFactory = DBSessionFactory.getInstance();
        sqlSession = sqlSessionFactory.openSession();
        
        int result = sqlSession.update( "login.setStatusMessage", account );
        
        log.info( account );
        
        return result;
    }
}
