package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Vector;

import lombok.extern.log4j.Log4j2;
import util.dto.Account;
import util.dto.Friend;
import util.oracle.OracleConnection;

@Log4j2( topic = "database" )
public class MainFriendLogic {
    
    private Connection        conn;
    private PreparedStatement pstmt;
    private ResultSet         rs;
    
    /**
     * @author leehs
     *         사용자가 로그인하면 DB에서 사용자의 친구목록 테이블에서 친구 RECORDS를 불러온다
     *         
     * @param Account - 사용자가 로그인한 정보
     * @return 친구 RECORDS
     */
    public List<Friend> friendList( Account myAccount ) {
        
        Friend        friend;
        List<Friend>  friendList = new Vector<>();
        StringBuilder sql        = new StringBuilder();
        
        sql.append( "    SELECT FRIEND_ID        " );
        sql.append( "    FROM ONION." + myAccount.getUser_id() ); // 프랜드 리스트에서 친구아이디 전부 가져오기
        
        try {
            conn = OracleConnection.getConnection();
            pstmt = conn.prepareStatement( sql.toString() );
            rs = pstmt.executeQuery();
            
            while ( rs.next() ) {
                friend = new Friend();
                friend.setFriend_id( rs.getString( "FRIEND_ID" ) );
                friendList.add( friend );
            }
            
            log.info( friendList );
        }
        catch ( Exception e ) {
            log.error( "error", e );
        }
        finally {
            OracleConnection.freeConnection( conn, pstmt, rs );
        }
        
        return friendList;
    }
    
    /**
     * @author leehs
     *         검색버튼을 눌렀을 때 입력된 ID를 DB서버와 연동해서 가입된 아이디인지 확인한다
     *         
     * @param id (DB COLUMN NAME : USER_ID)
     * @return 검색버튼을 눌렀을 때 DB에 ID가 있으면 내용을, 없으면 '없음'을 반환
     */
    public Account friendIdFind( String id ) {
        Account       result = null;
        StringBuilder sql    = new StringBuilder();
        sql.append( "    SELECT USER_ID, USER_NAME         " );
        sql.append( "    FROM ONION.INFO      " );
        sql.append( "    WHERE USER_ID = ?     " );
        
        try {
            conn = OracleConnection.getConnection();
            pstmt = conn.prepareStatement( sql.toString() );
            pstmt.setString( 1, id );
            rs = pstmt.executeQuery();
            
            // ResultSet
            if ( rs.next() ) {
                result = new Account();
                result.setUser_id( rs.getString( "USER_ID" ) );
                result.setUser_name( rs.getString( "USER_NAME" ) );
                
            }
            else {
                result = new Account();
                result.setUser_id( "없음" );
            }
        }
        catch ( Exception e ) {
            log.error( "error", e );
        }
        finally {
            OracleConnection.freeConnection( conn, pstmt, rs );
        }
        
        return result;
    }
    
    /*
     * 
     * @author leehs
     * 검색버튼을 눌렀을 때 입력된 ID를 DB서버와 연동해서 사용자의 친구목록 테이블에 RECORD에 존재하는지 확인한다.
     * 
     * @param
     * 
     * @return 친구아이디 추가를 눌렀을때 친구ID가 중복이 아니면 0을, ID가 중복이면 1을 반환
     */
    public int friendIDCheck( String id, String friend ) {
        int result = 0;
        
        StringBuilder sql = new StringBuilder();
        
        sql.append( "    SELECT FRIEND_ID        " );
        sql.append( "    FROM " + id ); //
        sql.append( "     WHERE FRIEND_ID = ?" ); //
        
        try {
            conn = OracleConnection.getConnection();
            pstmt = conn.prepareStatement( sql.toString() );
            pstmt.setString( 1, id );
            pstmt.setString( 1, friend );
            rs = pstmt.executeQuery();
            
            log.info( id );
            
            // ResultSet
            if ( rs.next() ) {
                result++;
            }
        }
        catch ( Exception e ) {
            log.error( "error", e );
        }
        finally {
            OracleConnection.freeConnection( conn, pstmt, rs );
        }
        return result;
    }
    
  /* 작성중 ////////////////////////////////////////////////////////////////////////////////////
    * @author leehs
    *         검색버튼을 눌렀을 때 입력된 ID를 DB서버와 연동해서 친구추가된 아이디인지 확인한다
    *         
    * @param 
    * @return 친구아이디 추가를 눌렀을때 친구ID가 중복이 아니면 0을, ID가 중복이면 1을 반환
    */
    public int FriendCheck(Account myAccount, String id) {
        int result = 0;
        
        StringBuilder sql = new StringBuilder();
        
        sql.append( "    SELECT FRIEND_ID        " );
        sql.append( "    FROM "+ myAccount.getUser_id() ); // 
        sql.append( "     WHERE FRIEND_ID = ?" ); // 
        

        try {
            conn = OracleConnection.getConnection();
            pstmt = conn.prepareStatement( sql.toString() );
            pstmt.setString( 1, id );
            rs = pstmt.executeQuery();
            
            log.info( id );
            
            // ResultSet
            if ( rs.next() ) {
                result++;
            }
        }
        catch ( Exception e ) {
            log.error( "Exception :", e );
        }
        finally {
            OracleConnection.freeConnection( conn, pstmt, rs );
        }
        return result;
    }
    

    /**
     * @author leehs
     *         [친구를 등록하시겠습니까] > [예] 를 누르면 친구 정보가 친구 db에 저장된다.
     *         사용자 이름으로된 테이블이 없으면 사용자 이름으로 새로운 테이블을 만들고 친구 정보를 저장
     *         사용자 이름으로된 테이블이 있으면 친구 정보를 저장
     *         
     * @param 사용자가 친구추가를 원하는 아이디 (DB COLUMN NAME : FRIRND_ID)
     * @return 사용자명 테이블에 순번, 친구아이디, 친구일자를 등록한다.
     */
    public int friendAdd( String inputText ) {
        int result = 0;
        log.info( inputText );
        
        StringBuilder sql = new StringBuilder();
        sql.append( "    INSERT INTO ONION.?        " ); // friendlist에 인서트하기
        sql.append( "    VALUES (SEQ_FRIEND_NO.NEXTVAL, ?, SYSDATE ) " );
        
        try {
            conn = OracleConnection.getConnection();
            pstmt = conn.prepareStatement( sql.toString() );
            
            pstmt.setString( 1, inputText ); // 테이블명을 친구 아이디로
            
            result = pstmt.executeUpdate();
            
            if ( result == 0 ) {
                System.out.println( "친구추가 실패" );
            }
            else {
                System.out.println( "친구추가성공" );
            }
            
        }
        catch ( Exception e ) {
            log.error( "error", e );
        }
        finally {
            OracleConnection.freeConnection( conn, pstmt );
        }
        
        return result;
    }

    /**
     * @author leehs
     *         친구를 삭제한다
     *         
     * @param 사용자가 친구삭제를 원하는 아이디 (DB COLUMN NAME : FRIRND_ID)
     * @return db에 있는 친구 정보를 삭제한다
     */
    public int frienddel(Account myAccount, String id ) {
            //Account account = new Account();
            int result = 0;
            StringBuilder sql = new StringBuilder();
            sql.append( "    DELETE FROM " + myAccount.getUser_id() );
            sql.append( "    WHERE FRIEND_ID = ?          " );

            try {
                conn = OracleConnection.getConnection();
                pstmt = conn.prepareStatement( sql.toString() );
                pstmt.setString( 1, id );
                result = pstmt.executeUpdate();
                if (result == 1)
                System.out.println("삭제 성공");
            else
                System.out.println("실패");
        } catch (Exception e) {
            log.error("Exception :", e);
        } finally {
            OracleConnection.freeConnection(conn, pstmt, rs);
        }
        return result;
    }

    public int friendAdd2( Account account, String friendID ) {
        
        int result = 0;
        
        StringBuilder sql = new StringBuilder();        

        sql.append( " INSERT INTO " + friendID + " ( USER_ID, FRIEND_ID, FRIEND_REG )  VALUES( ? , ? , SYSDATE )  " );
        try {
            conn = OracleConnection.getConnection();
            pstmt = conn.prepareStatement( sql.toString() );
            pstmt.setString( 1, friendID );
            pstmt.setString( 2, account.getUser_id());
            result = pstmt.executeUpdate();
            
            log.info( sql.toString() );
            
        }
        catch ( Exception e ) {
            log.error( "error", e );
        }
        return result;
    }

}