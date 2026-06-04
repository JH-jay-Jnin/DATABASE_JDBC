package org.edu.member.vo;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
// VO (Value Object) : 값 자체를 표현하고 의미를 갖는 객체 ==> id, password, name, role을 담기 위한 장바구니 ( == 객체 !)
public class Member {
        private int no;
        private String id;
        private String password;
        private String name;
        private String role;
        private char deletedYn;

    public String getMemberId() {
        return null;
    }
}
