package client.view;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import util.dto.Account;
import util.oracle.DBSessionFactory;

public class TestBatis {
    private static SqlSessionFactory sqlSessionFactory;
    
    public static void main( String[] args ) {
        
        LoginApp login = new LoginApp();
        // account.setUser_id( "tomato" );
        // account.setUser_pw( "123" );
        // System.out.println( account.toString() );
        // System.out.println( account.hashCode() );
        login.initDisplay();
        Account account = new Account();
        account.setUser_id( login.jtf_id.getText() );
        account.setUser_pw( String.valueOf( login.jtf_pw.getPassword() ) );
        System.out.println( account.toString() );
        
        sqlSessionFactory = DBSessionFactory.getInstance();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        account = sqlSession.selectOne( "signIn", account );
        // System.out.println( account.toString() );
        // System.out.println( account.hashCode() );
    }
}
