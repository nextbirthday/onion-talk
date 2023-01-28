package model;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import lombok.extern.log4j.Log4j2;
import util.dto.Account;
import util.oracle.DBSessionFactory;

@Log4j2( topic = "friend" )
public class FriendAddLogic {
    
    private SqlSessionFactory sqlSessionFactory;
    private SqlSession        sqlSession;
    
    public Account friendIDCheck( String friendID ) {
        
        Account account = new Account();
        
        account.setUser_id( friendID );
        
        sqlSessionFactory = DBSessionFactory.getInstance();
        sqlSession = sqlSessionFactory.openSession();
        
        return sqlSession.selectOne( "friend.friendIDCheck", account );
    }
    
    public int friendAdd( Account account, String friendID ) {
        
        Map<String, Object> param = new HashMap<String, Object>();
        param.put( "user_id", account.getUser_id() );
        param.put( "friend_id", friendID );
        
        sqlSessionFactory = DBSessionFactory.getInstance();
        sqlSession = sqlSessionFactory.openSession();
        
        int result = sqlSession.insert( "friend.friendAdd", param );
        
        if ( result > 0 )
            sqlSession.commit();
        
        return result;
    }
}
