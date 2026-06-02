package org.scoula.jdbc_ex.dao;

import org.scoula.jdbc_ex.domain.UserVO;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UserDao {
    // 구현해야하는 기능을 추상메서드(구현이 빠져있는 메서드)로 정의해두자.
    // == CRUD기능을 메서드로 정의

    // 회원 등록
    int create(UserVO user) throws SQLException;

    // 회원 목록 조회
    List<UserVO> getList() throws SQLException // ==> 한 명이면 그냥 List에 넣으면 되는데 getlist()로 되어있기 때문에 더 많은 값을 정의해서 저장하자!
    ;
    // 회원 정보 조회
    Optional<UserVO> get(String id) throws SQLException; // return할 값이 없으면 회원이 있는지 없는지 확인하기 위한 Optional 메서드

    // 회원 수정
    int update(UserVO user) throws SQLException;

    // 회원 삭제
    int delete(String id) throws SQLException;

}

