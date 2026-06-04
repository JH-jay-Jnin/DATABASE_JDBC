package org.edu.member.vo;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
// VO (Value Object) : 값 자체를 표현하고 의미를 갖는 객체 ==> id, password, name, role을 담기 위한 장바구니 ( == 객체 !)
public class Member { // ==> 무조건 테이블명과 같을 이유가 없다.
    private int no;
    private String id;
    private String password;
    private String name;
    private String role;
    private char deletedYn;

    // 부서 추가 ( 6월 4일 )
    private int deptNo;
    private String deptName;

    public String getMemberId() {
        return null;
    }

    public void setMemberNo(int no) {
    }

    public void setMemberName(String name) {
    }
}
