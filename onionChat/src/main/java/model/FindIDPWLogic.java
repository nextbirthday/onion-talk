package model;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import lombok.extern.log4j.Log4j2;
import util.dto.Account;
import util.oracle.DBSessionFactory;

@Log4j2( topic = "database" )
public class FindIDPWLogic {
    
    private SqlSessionFactory sqlSessionFactory;
    private SqlSession        sqlSession;
    
    /**
     * @author HOJAE
     *         로그인 창에서 ID/PW 찾기 버튼을 눌렀을 때 ID와 PW를 DB서버와 연동해서
     *         사용자가 입력한 정보가 맞으면 사용자에게 ID와 PW를 보여준다.
     *         
     * @param username (DB COLUMN NAME : USER_NAME)
     * @param phone    (DB COLUMN NAME : USER_PHONE)
     * @return ID 찾기 버튼을 눌렀을 때 입력한 이름과 번호가 맞으면 1을, 틀리면 1을 반환
     */
    public Account findID( String username, String phone ) {
        
        Account account = new Account();
        account.setUser_name( username );
        account.setUser_phone( phone );
        
        log.debug( "{}, {}", username, phone );
        
        sqlSessionFactory = DBSessionFactory.getInstance();
        sqlSession = sqlSessionFactory.openSession();
        
        return sqlSession.selectOne( "login.findID", account );
    }
    
    public Account pwFind( String username, String id ) {
        
        Account account = new Account();
        account.setUser_name( username );
        account.setUser_id( id );
        
        log.debug( "{}, {}", username, id );
        
        sqlSessionFactory = DBSessionFactory.getInstance();
        sqlSession = sqlSessionFactory.openSession();
        
        return sqlSession.selectOne( "login.pwFind", account );
    }
}
