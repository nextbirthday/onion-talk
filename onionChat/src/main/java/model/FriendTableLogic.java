package model;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import lombok.extern.log4j.Log4j2;
import util.dto.Account;
import util.oracle.DBSessionFactory;

@Log4j2( topic = "friend" )
public class FriendTableLogic {
    
    private SqlSessionFactory sqlSessionFactory;
    private SqlSession        sqlSession;
    
    /**
     * 사용자가 로그인에 성공하면 Oracle 서버에 친구목록 테이블을 생성하는 메서드이다.
     * 테이블 네임은 사용자의 아이디로 생성한다.
     * 
     * @param account
     */
    public void createFriendTable( Account account ) {
        
        sqlSessionFactory = DBSessionFactory.getInstance();
        sqlSession = sqlSessionFactory.openSession();
        
        String userId = account.getUser_id();
        
        String tableName = sqlSession.selectOne( "friend.tableCheck", userId.toUpperCase() );
        
        if ( tableName != null )
            return;
        else {
            int result = sqlSession.update( "friend.createFriendTable", userId.toUpperCase() );
            log.info( result );
        }
    }
    
}
