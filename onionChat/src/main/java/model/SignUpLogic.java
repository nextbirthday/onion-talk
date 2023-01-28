package model;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import client.view.JoinView;
import lombok.extern.log4j.Log4j2;
import util.dto.Account;
import util.oracle.DBSessionFactory;

@Log4j2( topic = "login" )
public class SignUpLogic {
    
    private JoinView view;
    
    private SqlSessionFactory sqlSessionFactory;
    private SqlSession        sqlSession;
    
    public SignUpLogic() {}
    
    public SignUpLogic( JoinView view ) {
        this.view = view;
    }
    
    /**
     * @author HOJAE
     *         회원가입 창에서 ID 중복체크를 위한 메서드
     *         <p>
     *         사용자가 ID 텍스트필드에 입력한 ID가 ONION.ACCOUNT TABLE의 USER_ID COLUMN의 data가 존재하는지 비교
     *         중복된 아이디를 입력했으면 int result 0 -> 1 즉, 리턴된 record가 하나라도 있으면 result 값이 0보다 커진다.
     *         
     * @return ID 중복체크 버튼을 눌렀을 때 ID가 중복이 아니면 0을, ID가 중복이면 1을 반환
     */
    public int idCheck() {
        
        int result = 0;
        
        sqlSessionFactory = DBSessionFactory.getInstance();
        sqlSession = sqlSessionFactory.openSession();
        
        String userID = sqlSession.selectOne( "login.idCheck", view.getJtf_id().getText() );
        
        if ( userID != null ) {
            result++;
        }
        
        return result;
    }
    
    /**
     * @author HOJAE
     *         Account DTO를 파라미터로 받아서 오라클 INSERT 를 수행하는 메서드.
     *         
     * @param account - Account 타입 객체
     * @return INSERT 성공 시 : 성공한 개수, 실패 시 0
     */
    public int register( Account account ) {
        
        sqlSessionFactory = DBSessionFactory.getInstance();
        sqlSession = sqlSessionFactory.openSession();
        
        int result = sqlSession.insert( "login.register", account );
        
        log.info( account );
        
        return result;
    }
}
