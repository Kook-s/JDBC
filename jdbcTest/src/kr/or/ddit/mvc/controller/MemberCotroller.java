package kr.or.ddit.mvc.controller;

import java.util.List;
import java.util.Scanner;

import kr.or.ddit.mvc.service.IMemberService;
import kr.or.ddit.mvc.service.MemberServiceimpl;
import kr.or.ddit.mvc.vo.MemberVO;

public class MemberCotroller {

	private Scanner scan;
	private IMemberService service;// Service객체 변수 선언

	public MemberCotroller() {
		scan = new Scanner(System.in);
		service = MemberServiceimpl.getInstance();
	}

	public static void main(String[] args) {
		new MemberCotroller().startMember();
	}

	private void startMember() {
		// TODO Auto-generated method stub

		while (true) {
			int choice = displayMenu();

			switch (choice) {
			case 1: // 추가
				insertMember();
				break;
			case 2: // 삭제
				deleteMember();
				break;

			case 3: // 수정
				updateMember();
				break;

			case 4: // 전체출력
				displayAllMenu();
				break;
			case 5: // 전체출력
				updateMember2();
				break;
			case 6: // 전체출력
				updateMember3();
				break;

			case 0:
				System.out.println("종료 되었습니다.");
				return;
			default:
				System.out.println("작업 번호를 잘못 입력했습니다. 다시 입력해 주세요");
				break;
			}
		}

	}

	private void updateMember3() {
		// TODO Auto-generated method stub
		System.out.println();
		System.out.println("수정할 회원 정보를 입력하세요");
		System.out.print("회원ID >> ");
		String id = scan.next();
		int count = 0;
		MemberVO memVo = new MemberVO();
		memVo.setMem_id(id);
		count = service.getMemberCount(id);
		if (count == 0) {
			System.out.println(id + "은(는) 등록되지 않은 회원ID 입니다.");
			System.out.println("다른 회원ID를 입력하세요...");
			return;
		}
		scan.nextLine();

		System.out.print("새로운 비밀번호 >> ");
		String newPass = scan.nextLine().trim();
		memVo.setMem_pass(newPass);

		System.out.print("새로운 이름 >> ");
		String newName = scan.nextLine().trim();
		memVo.setMem_name(newName);

		System.out.print("새로운 전화번호 >> ");
		String newTel = scan.nextLine().trim();
		memVo.setMem_tel(newTel);

		System.out.print("새로운 주소 >> ");
		String newAddr = scan.nextLine().trim();
		memVo.setMem_addr(newAddr);

		int cnt = service.updateMember3(memVo);

		if (cnt > 0) {
			System.out.println("회원 ID가" + id + "인 회원정보 수정 성공!!!");
		} else {
			System.out.println("회원 ID가" + id + "인 회원정보 수정 실패!!!");
		}

	}

	private void updateMember2() {
		// TODO Auto-generated method stub
		System.out.println();
		System.out.println("수정할 회원 정보를 입력하세요");
		System.out.print("회원ID >> ");
		String id = scan.next();
		MemberVO memVo = new MemberVO();
		int count = 0;

		count = service.getMemberCount(id);
		if (count == 0) {
			System.out.println(id + "은(는) 등록되지 않은 회원ID 입니다.");
			System.out.println("다른 회원ID를 입력하세요...");
			return;
		}
		memVo.setMem_id(id);
		System.out.println();
		System.out.println("-----선택 정보 수정-----");
		System.out.println("1.새로운 회원 비밀번호");
		System.out.println("2.새로운 회원 이름");
		System.out.println("3.새로운 회원 전화번호");
		System.out.println("4.새로운 회원 주소");
		System.out.println("------------------------");
		System.out.print("선택 >>");

		switch (scan.nextInt()) {
		case 1:
			System.out.print("새로운 비밀번호 >> ");
			memVo.setMem_pass(scan.next());
			break;
		case 2:
			System.out.print("새로운 회원이름>> ");
			memVo.setMem_name(scan.next());
			break;
		case 3:
			System.out.print("새로운 전화번호 >> ");
			memVo.setMem_tel(scan.next());
			break;
		case 4:
			System.out.print("새로운 회원주소 >> ");
			scan.next();
			memVo.setMem_addr(scan.nextLine());
			break;

		default:
			System.out.println("잘못 입력했습니다. 다시 입력해주세요.");
			break;
		}
		int cnt = service.updateMember2(memVo);

		if (cnt > 0) {
			System.out.println("회원 ID가" + id + "인 회원정보 수정 성공!!!");
		} else {
			System.out.println("회원 ID가" + id + "인 회원정보 수정 실패!!!");
		}
	}

	private void displayAllMenu() {
		System.out.println();
		System.out.println("---------------------------------------------------------");
		System.out.println(" ID   비밀번호   이 름     전화번호           주소   ");
		System.out.println("---------------------------------------------------------");

		if (service.getAllMember().size() == 0 || service.getAllMember() == null) {
			System.out.println("등록된 회원 정보가 하나도 없습니다.");
		}
		for (MemberVO memVo : service.getAllMember()) {
			System.out.println(memVo);
		}
		System.out.println("---------------------------------------------------------");
	}

	private void updateMember() {
		System.out.println();
		System.out.println("수정할 회원 정보를 입력하세요");
		System.out.print("회원ID >> ");
		String id = scan.next();

		int count = 0;

		count = service.getMemberCount(id);
		if (count == 0) {
			System.out.println(id + "은(는) 등록되지 않은 회원ID 입니다.");
			System.out.println("다른 회원ID를 입력하세요...");
			return;
		}

		System.out.print("새로운 비밀번호 >> ");
		String pass = scan.next();
		System.out.print("새로운 회원이름>> ");
		String name = scan.next();
		System.out.print("새로운 전화번호 >> ");
		String tel = scan.next();
		scan.nextLine();// 버퍼 지우기
		System.out.print("새로운 회원주소 >> ");
		String addr = scan.nextLine();

		MemberVO memVo = new MemberVO();
		memVo.setMem_id(id);
		memVo.setMem_pass(pass);
		memVo.setMem_name(name);
		memVo.setMem_tel(tel);
		memVo.setMem_addr(addr);

		int cnt = service.updateMember(memVo);

		if (cnt > 0) {
			System.out.println("회원 ID가" + id + "인 회원정보 수정 성공!!!");
		} else {
			System.out.println("회원 ID가" + id + "인 회원정보 수정 실패!!!");
		}

	}

	private void deleteMember() {
		// TODO Auto-generated method stub
		System.out.println();
		System.out.println("삭제할 회원 정보를 입력하세요");
		System.out.print("회원ID >> ");
		String id = scan.next();
		int cnt = 0;

		cnt = service.deleteMember(id);

		if (cnt > 0) {
			System.out.println("회원 ID가" + id + "인 회원 정보 삭제 성공!!!");
		} else {
			System.out.println(id + "회원은 없는 회원이거나 삭제 작업에 실패했습니다...");
		}

	}

	private void insertMember() {
		System.out.println();
		System.out.println("추가할 회원 정보를 입력하세요...");

		String id = null;
		int count = 0;
		do {
			System.out.print("회원ID >> ");
			id = scan.next();
			count = service.getMemberCount(id);
			if (count > 0) {
				System.out.println(id + "은(는) 이미 등록도니 회원ID 입니다.");
				System.out.println("다른 회원ID를 입력하세요...");
			}
		} while (count > 0);
		System.out.print("비밀번호 >> ");
		String pass = scan.next();
		System.out.print("회원이름>> ");
		String name = scan.next();
		System.out.print("전화번호 >> ");
		String tel = scan.next();
		scan.nextLine();// 버퍼 지우기
		System.out.print("회원주소 >> ");
		String addr = scan.nextLine();

		// 입력이한 자료들 VO객체에 담는다.
		MemberVO memVo = new MemberVO();
		memVo.setMem_id(id);
		memVo.setMem_pass(pass);
		memVo.setMem_name(name);
		memVo.setMem_tel(tel);
		memVo.setMem_addr(addr);

		int cnt = service.insertMember(memVo);

		if (cnt > 0) {
			System.out.println(id + "회원 정보 추가 완료!!!");
		} else {
			System.out.println(id + "회원 정보 추가 실패!!!");
		}

	}

	private int displayMenu() {
		System.out.println();
		System.out.println("--------------------");
		System.out.println(" 1. 자료 추가 ");
		System.out.println(" 2. 자료 삭제 ");
		System.out.println(" 3. 자료 수정 (전체항목 수정)");
		System.out.println(" 4. 전체 자료 출력");
		System.out.println(" 5. 자료 수정2 (수정항목 선택)");
		System.out.println(" 6. 자료 수정3 (입력항목만 수정)");
		System.out.println(" 0. 작업 끝.");
		System.out.println("--------------------");
		System.out.print(" 선택하세요 >>");

		return scan.nextInt();

	}
}
