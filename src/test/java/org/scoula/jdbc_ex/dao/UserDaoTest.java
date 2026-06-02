package org.scoula.jdbc_ex.dao;

import lombok.SneakyThrows;
import org.junit.jupiter.api.*;
import org.scoula.jdbc_ex.common.JDBCUtil;
import org.scoula.jdbc_ex.domain.UserVO;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserDaoTest {

    UserDao dao = new UserDaoImpl();

    @SneakyThrows
    @AfterAll
    static void tearDown() {
        JDBCUtil.close();
    }

    @Test
    void create() throws SQLException {
        UserVO user = new UserVO("app", "1234", "app", "admin");
        int count = dao.create(user);
        Assertions.assertEquals(1, count);
    }

    @Test
    void getList() {
    }

    // get 메서드는 특정한 한 사람만 가져오도록 설계
    // 한 사람에 대한 필드만 저장하는 UserVO user = dao.get("ssamz3")를 한다.
    // 그리고 바로 뒤에 orElseThrow(NoSuchElementException::new);를 하면 자동으로 없는 user에 대하 찾아준다.
    @Test
    void get() {
    }

    @DisplayName("user의 정보를 수정합니다.")
    @Test
    void update() {
    }

    // user를 삭제하는 구문에 대해서는 row가 삭제되었는지 확인해야하므로, (1, row)를 써서 확인한다
    @Test
    void delete() {
    }
}