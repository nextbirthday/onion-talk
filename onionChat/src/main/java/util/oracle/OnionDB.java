package util.oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OnionDB {

    public static final String _DRIVER = "oracle.jdbc.driver.OracleDriver";
    public static final String _URL = "jdbc:oracle:thin:@focusrite.iptime.org:54300:orcl11";
    public static String _USER = "onion";
    public static String _PW = "ring";

    public static Connection con = null;

    public static Connection getConnection() {

        try {
            Class.forName(_DRIVER);
            con = DriverManager.getConnection(_URL, _USER, _PW);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    public void freeConnection(Connection con, Statement stmt) {

        if (stmt != null) {

            try {
                stmt.close();
            } catch (SQLException e) {
            }
        }

        if (con != null) {

            try {
                con.close();
            } catch (SQLException e) {
            }
        }
    } // end of freeConnection

    public void freeConnection(Connection con, PreparedStatement pstmt) {

        if (pstmt != null) {

            try {
                pstmt.close();
            } catch (SQLException e) {
            }
        }

        if (con != null) {

            try {
                con.close();
            } catch (SQLException e) {
            }
        }
    } // end of freeConnection

    // 사용한 자원 반납하기 - SELECT - 커서 조작이 필요하다.

    public void freeConnection(Connection con, PreparedStatement pstmt, ResultSet rs) {

        if (rs != null) {

            try {
                rs.close();
            } catch (SQLException e) {
            }
        }

        if (pstmt != null) {

            try {
                pstmt.close();
            } catch (SQLException e) {
            }
        }

        if (con != null) {

            try {
                con.close();
            } catch (SQLException e) {
            }
        }
    } // end of freeConnection

    public void freeConnection(Connection con, Statement stmt, ResultSet rs) {

        if (rs != null) {

            try {
                rs.close();
            } catch (SQLException e) {
            }
        }

        if (stmt != null) {

            try {
                stmt.close();
            } catch (SQLException e) {
            }
        }

        if (con != null) {

            try {
                con.close();
            } catch (SQLException e) {
            }
        }
    } // end of freeConnection

}
