package org.scoula.jdbc_ex;

import org.junit.jupiter.api.Test;
import org.scoula.jdbc_ex.common.JDBCUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ConnectionTest {

     // db연결 테스트 메스드 정의해주면 된다!
    // 메서드 단위로 기능을 테스트해볼 수 있음. (JUnit 라이브러리 사용함)
    @Test
    public void testConnection() throws ClassNotFoundException, SQLException {
        // JDBC 단계별 테스트 해보자.!!!
        // 1. jdbc드라이버 세팅(로딩), jdbc connector
        Class.forName("com.mysql.cj.jdbc.Driver"); // 외부파일 연결
        System.out.println(" 1. jdbc드라이버 세팅(로딩) 성공");

        // 2. db 연결해보기 ==> DriverManager가 DB에 연결하는 것!
        // db 연결시 필요한 데이터 3가지 : url(ip + port + db명), username, pw
        String url = "jdbc:mysql://127.0.0.1:3306/jdbc_ex";
        String id = "scoula";
        String password = "wlgjs0417";
        Connection con = DriverManager.getConnection(url, id, password);
        System.out.println("2. db연결 성공>> " + con);

        // 자원해제
        con.close();

    }

    @Test
    public void testConnection2 () {
        Connection con = JDBCUtil.getConnection();
        try {
            JDBCUtil.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}