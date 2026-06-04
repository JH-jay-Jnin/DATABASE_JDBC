package org.edu.member.dao;

import org.edu.member.common.JDBCUtil;
import org.edu.member.vo.Member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CjhMemberDaoImpl implements MemberDao {

    // JDBCUtil을 통해 Connection 객체 가져오기
    private Connection conn = JDBCUtil.getConnection();

    // 회원 등록
    @Override
    public int create(Member member) throws SQLException {
        // Statement를 사용하는 경우 sql문 ==> 이어쓰기 너무 불편하다!!
        /*
        String sql = "INSERT INTO members VALUES (DEFAULT, "
                + member.getMemberId() + ", "
                + member.getMemberPw() +", "
                + member.getMemberName()+ ", "
                + member.getMemberRole()+", 'N');";
         */

        // PreparedStatement
        // - Statement의 자식으로 좀 더 향상된 기능을 제공
        // - ? (위치 홀더)를 이용하여 SQL에 작성되어지는 리터럴을 동적으로 제어
        // - 오타 위험 감소, 가독성 상승

        // sql문 작성 시 세미콜론(;)은 안쓰는 것이 관례
        String sql = "INSERT INTO members VALUES (DEFAULT, ?, ?, ?, ?, 'N')";

        // try-with-resources문을 사용하여 작업이 끝나면 pstmt.close()가 자동 호출된다.
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) { // 생성할 때부터 sql문 쓰게 되어있다.
            pstmt.setString(1, member.getId());
            pstmt.setString(2, member.getPassword());
            pstmt.setString(3, member.getName());
            pstmt.setString(4, member.getRole());

            // SELECT : executeQuery(); -> ResultSet 반환
            // DML (SELECT 제외) : executeUpdate(); -> 성공한 행의 개수 반환
            int result = pstmt.executeUpdate();

            // 성공 시 커밋 == 조건
            if (result == 1) conn.commit();

            return result; // 성공한 행의 개수 반환
        }

    }

    // 회원 정보 수정
    @Override
    public int modify(Member modify) throws SQLException {

        String sql = "UPDATE members SET name = ?, role = ? WHERE no = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, modify.getName());
            pstmt.setString(2, modify.getRole());
            pstmt.setInt(3, modify.getNo());

            int result = pstmt.executeUpdate();

            if (result == 1) conn.commit();

            return result;
        }
    }

    // 회원 정보 삭제
    @Override
    public int delete(String id) throws SQLException {
        String sql = "DELETE FROM members WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            int result = pstmt.executeUpdate();
            if (result == 1) conn.commit();
            return result;
        }
    }

    @Override
    public Member getDeptName(int memberNo) throws SQLException {
        Member mem = null;

        // 여러 줄로 작성하는 경우 띄어쓰기 주의! ( 왠만하면 \n 그냥 쓰도록! )
        String sql = "SELECT no, name, m.dept_no, dept_name\n" +
                "FROM members m\n" +
                "LEFT JOIN departments d ON m.dept_no = d.dept_no\n" +
                "WHERE no = ?\n";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, memberNo);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    // no == pk라서, 조회 성공 시 1행만 존재한다.

                    mem = new Member();
                    mem.setMemberNo(rs.getInt("NO"));
                    mem.setMemberName(rs.getString("NAME"));
                    mem.setDeptNo(rs.getInt("DEPT_NO"));
                    mem.setDeptName(rs.getString("DEPT_NAME"));

                }

            }

        }
        return mem; // 존재하면 mem을 반환, 없으면 초기 기본 값인 Null을 반환한다.
    }
}


