package model;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import client.view.LoginView;
import lombok.extern.log4j.Log4j2;
import util.dto.Account;
import util.oracle.DBSessionFactory;

@Log4j2( topic = "login" )
public class SignInLogic {
    
    LoginView loginView;
    
    private SqlSessionFactory sqlSessionFactory;
    private SqlSession        sqlSession;
    
    public SignInLogic() {}
    
    public SignInLogic( LoginView loginview ) {
        this.loginView = loginview;
    }
    
    /**
     * @author HOJAE
     *         사용자로부터 ID, PW, Status Message(상태메시지)를 파라미터로 받아온다.
     *         사용자로부터 입력받은 ID와 PW가 DB 서버의 record와 일치하면 USER_NICK을 DB에서 가져온다.
     *         DB로부터 가져온 닉네임을 Account 클래스 user_nick 값에 초기화
     *         <p>
     * @param account
     * @return ID와 닉네임이 들어있는 Account 객체
     */
    public Account signIn( Account account ) {
        
        sqlSessionFactory = DBSessionFactory.getInstance();
        sqlSession = sqlSessionFactory.openSession();
        
        Account result = sqlSession.selectOne( "login.signIn", account );
        
        log.info( result );
        
        return result;
    }
}
