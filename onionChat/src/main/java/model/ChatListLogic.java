package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.oracle.OnionDB;
import util.oracle.OracleConnection;

public class ChatListLogic {
  private Connection conn;
  private PreparedStatement pstmt;
  private ResultSet rs;

  public List<String> chatList() { // String 타입으로 가져올 것
    List<String> chatList = new ArrayList<>(); // 같은 타입으로 맞춰줌
    StringBuilder sql = new StringBuilder();
    sql.append("   SELECT ch_title FROM chatlist   ");
    sql.append("   ORDER BY ch_time                ");
    try {
      conn = OnionDB.getConnection();
      pstmt = conn.prepareStatement(sql.toString());
      rs = pstmt.executeQuery();
      String roomName = null;
      while (rs.next()) {
        roomName = rs.getString("ch_title");
        chatList.add(roomName);
      }
      System.out.println(chatList);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      OracleConnection.freeConnection(conn, pstmt, rs);
    }
    return chatList;
  }

  // 채팅방 삭제 시 데이터 삭제

  public List<String> chatDelete() {
    List<String> chatList = new ArrayList<>();
    StringBuilder sql = new StringBuilder();
    sql.append("   DELETE FROM chatlist   ");
    sql.append("   WHERE ch_no = ?       ");
    try {
      conn = OnionDB.getConnection();
      pstmt = conn.prepareStatement(sql.toString());
      rs = pstmt.executeQuery();
      while (rs.next()) {

      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      OracleConnection.freeConnection(conn, pstmt, rs);
    }
    return chatList;
  }

  // 채팅방 변경시 변경된 이름 데이터 저장

  public List<String> chatUpdate(String chatupdname, String chatname, int chatno) {
    List<String> chatList = new ArrayList<>();
    StringBuilder sql = new StringBuilder();
    sql.append("   UPDATE chatlist       ");
    sql.append("   set ch_no = ?,        ");
    sql.append("   ch_title = ?          ");
    sql.append("   WHERE ch_title = ?'   ");
    try {
      conn = OnionDB.getConnection();
      pstmt = conn.prepareStatement(sql.toString());
      pstmt.setInt(1, chatno);
      pstmt.setString(2, chatupdname);
      pstmt.setString(3, chatname);
      rs = pstmt.executeQuery();
      String roomName = null;
      while (rs.next()) {
        roomName = rs.getString("");
        chatList.add(roomName);
      }
      System.out.println(chatList);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      OracleConnection.freeConnection(conn, pstmt, rs);
    }
    return chatList;
  }

  public static void main(String[] args) {
    ChatListLogic cll = new ChatListLogic();
    cll.chatList();
  }
}
