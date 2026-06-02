package org.scoula.jdbc_ex;

import lombok.SneakyThrows;
import org.junit.jupiter.api.*;
import org.scoula.jdbc_ex.common.JDBCUtil;

import java.sql.*;

// 테스트 클래스에는 단위별로 메서드를 만들어서 테스트
// 메서드 전체를 내가 원하는 순서에 따라 실행하게 할 수 있다.
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CrudTest {
    Connection con = JDBCUtil.getConnection();

    @SneakyThrows
    @AfterAll
    // 다른 메서드 다 실행하고 나서 무조건 실행할 메서드를 넣어줌
    static void close() {
        JDBCUtil.close();
    }


    @Test
    @Order(1)
    @DisplayName("회원가입테스트함.")
    public void insertUser() throws SQLException {
        String sql = "insert into users (id, password, name, role) values(?, ?, ?, ?)";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, "winner3");
        pstmt.setString(2, "123456");
        pstmt.setString(3, "win3");
        pstmt.setString(4, "admin");
        int row = pstmt.executeUpdate(); // mysql로 sql문 전송 (네트워크 전송 ==> close가 반드시 필요)
        // 실행된 sql문 결과 row수
        System.out.println(row);
        Assertions.assertEquals(1,row);
        pstmt.close();
    }

    @Test
    @DisplayName("user 목록을 추출한다.")
    @Order(2) // 만약 전체를 실행한다면 Order(실행한다)(2)(두번째로)
    public void selectUser() throws SQLException {
        // JDBC 단계 1, 2는 이미 완료
        // JDBC 단계 3 - SQL문 정함.
        String sql = "SELECT * FROM users";
        // JDBC 단계 4 - SQL 객체 생성
        try (Statement stmt = con.createStatement(); // == throws 예외룰 추가해야함!
             // JDBC 단계 5 - SQL 전송 (MySQL서버로)
             ResultSet rs = stmt.executeQuery(sql);
             ) {
                        // JDBC 단계 6 - SQL 결과를 받아와서 처리
                        while (rs.next()) {
                        String name = rs.getString("name");
                        System.out.println(name);
                    }
            // JDBC 단계 7 - close(자원 해제 == 연결해제) => @AfterAll 로 알아서 실행되게 코딩함
//      stmt.close(); // stmt는 따로 close를 해줘야함! 하지만 요새는 try/catch를 더 선호한다!
//      rs.close();

        }
    }

    @Test
    @DisplayName("user 목록을 추출한다.")
    @Order(3) // 만약 전체를 실행한다면 Order(실행한다)(2)(두번째로)
    public void selectUserByID() throws SQLException {
        // JDBC 단계 1, 2는 이미 완료
        // JDBC 단계 3 - SQL문 정함.
        String sql = "SELECT * FROM users WHERE id = ?";
        // JDBC 단계 4 - SQL 객체 생성
        try (PreparedStatement stmt = con.prepareStatement(sql)) { // == throws 예외룰 추가해야함!
            stmt.setString(1, "admin");

            // JDBC 단계 5 - SQL 전송 (MySQL서버로)
            try (ResultSet rs = stmt.executeQuery()) {
                // JDBC 단계 6 - SQL 결과를 받아와서 처리
                while (rs.next()) {
                    String name = rs.getString("name");
                    System.out.println(name);
                    String role = rs.getString("role");
                    System.out.println(role);
                }
            }
        }
    }
}