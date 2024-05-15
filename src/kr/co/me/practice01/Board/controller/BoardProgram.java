package kr.co.me.practice01.Board.controller;

import java.util.Scanner;

import kr.co.me.practice01.Board.vo.Board;
import kr.co.me.practice01.Board.vo.User;

public class BoardProgram {
	private User[] users;
	private int uIndex;
	private Board[] writings;
	private int bIndex;
	private Scanner sc;
	
	public BoardProgram() {
		sc = new Scanner(System.in);
		users = new User[100];
		uIndex = 0;
		writings = new Board[100];
		bIndex = 0;
	}
	
	public void main() {
		//프로그램 실행
		while(true) {
			System.out.println("\n===== 우리들이 만드는 게시판 =====\n");
			System.out.println("1. 회원가입");
			System.out.println("2. 회원정보 조회");
			System.out.println("3. 게시판 들어가기");
			System.out.println("4. 회원 탈퇴");
			System.out.println("0. 프로그램 종료");
			System.out.print("선택 >> ");
			int selectMainMenu = sc.nextInt();
			System.out.println();
			switch(selectMainMenu) {
			case 1://회원가입
				signUp();
				break;
			case 2://회원정보 조회
				lookMemInfor();
				break;
			case 3://게시판 들어가기
				goToBoard();
				break;
			case 4://회원 탈퇴
				deleteMembership();
				break;
			case 0://프로그램 종료
				System.out.println("프로그램을 종료합니다....");
				return;
			default:
				break;
			}//switch(selectMainMenu)
		}//while(true)
	}//main()
	
	public void signUp() {
		System.out.println("\n------- 회원가입 -------\n");
		while(true) {
			System.out.print("ID를 입력하세요 : ");
			String id = sc.next();
			int searchUIndex = searchUser(id);
			if(searchUIndex == -1) {
				System.out.print("이름을 입력하세요 : ");
				String name = sc.next();
				System.out.print("PW를 입력하세요 : ");
				String pw = sc.next();
				System.out.print("나이를 입력하세요 : ");
				int age = sc.nextInt();
				users[uIndex++] = new User(name, id, pw, age);
				System.out.println("회원가입이 완료되었습니다.");
				return;
			}//if 존재하지 않는 경우
			else {
				System.out.println("이미 존재하는 ID 입니다 다시 입력하세요");
			}//else 존재할 경우
		}//while(signUp)
	}//signUp()
	
	public void lookMemInfor() {
		//1.로그인
		int signIn = signIn();
		if(signIn == -1) {
			System.out.println("일치하는 ID가 존재하지 않습니다.");
		}//if 아이디 없음
		else if(signIn ==-2) {
			System.out.println("비밀번호가 일치하지 않습니다.");
		}//else if 비밀번호 불일치
		else {
			System.out.println("\n---- 회원 정보 ----\n");
			System.out.println("이름\t: "+users[signIn].getName());
			System.out.println("ID\t: "+users[signIn].getId());
			System.out.println("PW\t: "+users[signIn].getPw());
			System.out.println("나이\t: "+users[signIn].getAge());
			System.out.print("수정하시겠습니까?[y/n] : ");
			char Answer = sc.next().charAt(0);
			switch(Answer) {
			case 'y':
				updateUser(signIn);
				return;
			default:
				break;
			}//switch(Answer)
		}//else
	}//lookMemInfor()
	
	public void updateUser(int index) {
		System.out.print("비밀번호 수정 : ");
		String modifyPw = sc.next();
		System.out.println("나이 수정 : ");
		int modifyAge = sc.nextInt();
		users[index].setAge(modifyAge);
		users[index].setPw(modifyPw);
		System.out.println("회원정보 수정 완료");
	}//updateUser()
	
	public void goToBoard() {
		char answer;
		System.out.println("\n------ 게시판 ------\n");
		if(bIndex == 0) {
			System.out.println("게시글이 존재하지 않습니다");
			System.out.print("게시글을 작성하시겠습니까?[y/n] : ");
			answer = sc.next().charAt(0);
		}//if
		else {
			boolean show = true;
			while(show) {
				showAllTitle();
				System.out.print("조회할 게시글 선택 : ");
				int showbIndex = sc.nextInt()-1;
				if(showbIndex<bIndex) {
					showWritings(showbIndex);
					System.out.println();
					System.out.print("추가적으로 다른 게시물을 보시겠습니까?[y/n] : ");
					char keep = sc.next().charAt(0);
					if(keep == 'n') {
						show = false;
					}//if
				}//if 옳은 게시글 선택
				else {
					System.out.println("선택하신 게시글이 존재하지 않습니다.");
				}//else
			}//while(true)게시글 조회
			System.out.print("게시글을 작성하시겠습니까?[y/n] : ");
			answer = sc.next().charAt(0);
		}//else
		if(answer == 'y') {
			makeNewBoard();
		}//if
	}//goToBoard()
	
	public void showAllTitle() {
		for(int i=0; i<bIndex; i++) {
			System.out.println((i+1)+". "+writings[i].getTitle());
		}//for
	}//showAllTitle()
	
	public void showWritings(int index) {
		System.out.println("<"+writings[index].getTitle()+">");
		System.out.println("작성자 : "+writings[index].getAuthor()+"\t 작성시간 : "+writings[index].getTime());
		System.out.println("-------------------------------");
		System.out.println(writings[index].getContents());
	}//showWritings()
	
	public void makeNewBoard() {
		//1.로그인
		int signIn = signIn();
		if(signIn == -1) {
			System.out.println("일치하는 ID가 존재하지 않습니다.");
		}//if 아이디 없음
		else if(signIn ==-2) {
			System.out.println("비밀번호가 일치하지 않습니다.");
		}//else if 비밀번호 불일치
		else {
			System.out.print("제목 : ");
			sc.nextLine();
			String title = sc.nextLine();
			System.out.print("내용 : ");
			String contents = sc.nextLine();
			writings[bIndex++] = new Board(title, contents, users[signIn].getId());
		}//else
	}//makeNewBoard()
	
	public void deleteMembership() {
		int signIn = signIn();
		if(signIn == -1) {
			System.out.println("일치하는 ID가 존재하지 않습니다.");
		}//if 아이디 없음
		else if(signIn ==-2) {
			System.out.println("비밀번호가 일치하지 않습니다.");
		}//else if 비밀번호 불일치
		else {
			for(int i = signIn; i<uIndex-1; i++) {
				users[i]=users[i+1];
			}//for
			users[--uIndex] = null;
			System.out.println("탈퇴가 완료되었습니다.");
		}
	}//deleteMembership()
	
	public int searchUser(String id) {
		for(int i = 0; i<uIndex; i++) {
			if(id.equals(users[i].getId())) {
				return i;
			}//if
		}//for
		return -1;
	}//searchUser
	
	public int signIn() {
		System.out.print("ID : ");
		String id = sc.next();
		System.out.print("PW : ");
		String pw = sc.next();
		int searchUIndex = searchUser(id);
		if(searchUIndex == -1) {
			return -1;
		}//if
		else if(pw.equals(users[searchUIndex].getPw())){
			return searchUIndex;
		}//else if
		else {
			return -2;
		}//else
	}//signIn()
}
