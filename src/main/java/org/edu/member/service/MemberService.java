package org.edu.member.service;

import lombok.Setter;
import org.edu.member.dao.MemberDao;
import org.edu.member.dao.MemberDaoImpl;
import org.edu.member.vo.Member;

import java.sql.SQLException;
import java.util.Scanner;

public class MemberService {
    private Scanner sc = new Scanner(System.in);

    // 수업
    private MemberDao dao = new MemberDaoImpl(); // 부모 타입 참조 변수인 MemberDao가 Interface이므로,

    // 숙제
    // private MemberDao dao = new MemberDaoImpl(); // 부모 타입 참조 변수인 MemberDao가 Interface이므로,
    // MemberDaoImpl 클래스로 오버라이딩 된 객체를 받아준다.

    public void displayMenu() {

        int menu = 0; // 메뉴 선택용 변수

        do {
            try {
                System.out.println("[메인 메뉴]");
                System.out.println("1. 회원 등록");
                System.out.println("2. 회원 목록 조회");
                System.out.println("3. 회원 정보 조회");
                System.out.println("4. 회원 수정");
                System.out.println("5. 회원 삭제");
                System.out.println("6. 회원 부서명 조회");
                System.out.println("0. 종료");
                System.out.print("메뉴 선택 >> ");

                menu = sc.nextInt();
                sc.nextLine(); // 입력 버퍼 개행문자 제거
                System.out.println(); // 줄바꿈

                switch (menu) {
                    case 1:
                        create();
                        break;
                    case 2:
                        // getList();
                        break;
                    case 3:
                        // get();
                        break;
                    case 4:
                        update();
                        break;
                    case 5:
                        delete();
                        break;

                    case 6:
                        getDeptName();
                        break;

                    case 0:
                        System.out.println("[프로그램 종료]");
                        break;
                    default:
                        System.out.println("잘못 입력하셨습니다. 메뉴를 다시 선택해주세요.");
                }

            } catch (Exception e) {
                sc.nextLine(); // 잘못된 입력 제거
                e.printStackTrace();
            }
        } while (menu != 0);
    }


    // 1. 회원 등록
    private void create() throws SQLException {
        System.out.println("=== 회원 등록 ===");

        // 아이디, 비밀번호, 이름, 권한 입력받아 변수에 저장
        System.out.print("id :");
        String memberId = sc.nextLine();

        System.out.print("password :");
        String memberPw = sc.nextLine();

        System.out.print("name :");
        String memberName = sc.nextLine();

        System.out.print("role :");
        String memberRole = sc.nextLine();

        System.out.print("dept_no: ");
        String memberDeptNO = sc.nextLine();


        // Member 객체 생성 후, 전달. (4개의 값을 담아주고 + 그걸 전달해준다!)
        Member member = new Member();
        member.setId(memberId);
        member.setPassword(memberPw);
        member.setName(memberName);
        member.setRole(memberRole);

        int result = dao.create(member); // MemberVO로 담기!

        // 회원 등록 성공 시 : "전동재님의 가입을 환영합니다."
        //         실패 시 : "회원 등록 실패 ㅋ"

        if (result == 1) {
            System.out.println(memberName + "님의 가입을 환영합니다.");
        } else {
            System.out.println("회원 등록 실패 ㅋㅋ");
        }

    }

    // 2. 회원 정보 수정
    private void update() throws SQLException {
        System.out.println("=== 회원 정보 수정 ===");

        System.out.print("no :");
        int memberNo = sc.nextInt();
        sc.nextLine();

        System.out.print("수정할 name :");
        String memberName = sc.nextLine();

        System.out.print("수정할 role :");
        String memberRole = sc.nextLine();

        Member Update_member = new Member();
        Update_member.setNo(memberNo);
        Update_member.setName(memberName);
        Update_member.setRole(memberRole);

        int result = dao.modify(Update_member);

        if (result == 1) {
            System.out.println("회원정보 수정 성공");
        } else {
            System.out.println("회원정보 수정 실패");
        }

    }

    // 3. 회원 정보 삭제
    // getList() : 회원 목록 전체 조회
    // get()     : 회원 번호가 일치하는 회원 한 명 조회
    // delete()  : 회원 번호가 일치하는 회원 삭제
    private void delete() throws SQLException {
        System.out.println("=== 회원 정보 삭제 ===");

        System.out.print("id :");
        String memberId = sc.next();

        int result = dao.delete(memberId);

        if (result == 1) {
            System.out.println("회원 삭제 성공");
        } else {
            System.out.println("회원 삭제 실패");
        }
    }

    // 2026.06.04 -- 회원 번호가 일치하는 회원의 번호, 이름, 부서코드, 부서명 조회
    private void getDeptName() throws SQLException {
        System.out.println("=== 회원의 부서명 조회 ===");

        System.out.println("검색할 회원 번호 :");
        int memberNo = sc.nextInt();

        Member member = dao.getDeptName(memberNo);

        if (member == null) {
            System.out.println("일치하는 회원이 없습니다.");
        } else{
            System.out.println(member);
        }
    }
}


