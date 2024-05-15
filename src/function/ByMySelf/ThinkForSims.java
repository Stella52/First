package function.ByMySelf;

import java.util.Random;
import java.util.Scanner;

public class ThinkForSims {
	String name;
	int time = 6;
	String hour = "☀️ 오전 6시";
	int money = 10000;
	int clear = 10;//청결도
	int feeling = 10;//기분
	int hunger = 10;//허기
	int energy = 10;//에너지
	boolean happy = true;//행복도: 행복하면 true, 불행하면 false

	public String sunMoon() {// 아침, 낮은 해, 저녁, 새벽은 달
		int clock = time%24;
		String star;
		if(clock<18) { 
			star = "☀️ ";
		}//if 밝음
		else {
			star = "🌙 ";
		}//else 밤
		return star;
	}//sunMoon 시간대에 따른 이모티콘 출력
	
	public String hour() {
		String clock;
		int num = time%24;
		if(num<12) {
			clock = "오전 "+num +"시";
		}//if 오전
		else if(num ==12) {
			clock = "오후 12시";
		}//else if 오후 12시
		else {
			clock = "오후 "+(num-12)+"시";
		}//else 오후
		return clock;
	}//hour 시간 출력
	
	public void working() {
		Random r = new Random();
		int clock = time%24;
		if(clock<6 || clock+8>23) {
			System.out.println("지금은 시간이 늦어서 일을 할 수 없습니다! 날이 밝으면 도전해 봅시다!");
		}//새벽엔 일 못함
		else {
		time+=8;
		int occ = r.nextInt(3);
		switch(occ) {
		case 0://일을 열심히함, 성공적인 끝마침
			System.out.println(name+"(이)는 열심히 일을 했습니다! 참 보람차군요!");
			money+=500;
			hunger = changeStates(hunger, -2);
			feeling = changeStates(feeling, -3);
			energy = changeStates(energy, -3);
			clear = changeStates(clear, -1);
			break;
		case 1://일을 게을리함
			System.out.println(name+"(이)는 농땡이를 부렸습니다!");
			System.out.println("돈은 적게 벌었지만 그래도 지루하진 않았네요ㅎㅎ");
			money+=300;
			hunger = changeStates(hunger, -2);
			feeling = changeStates(feeling, 1);
			energy = changeStates(energy, -2);
			clear = changeStates(clear, -1);
			break;
		case 2://그지같은 일
			System.out.println(name+"이(는) 일을 하다 실수를 저질렀습니다! ㅠㅠ");
			System.out.println("상사한테 혼나고 돈도 못받았군요! 참 슬픈 일입니다");
			hunger = changeStates(hunger, -2);
			feeling = changeStates(feeling, -5);
			energy = changeStates(energy, -3);
			clear = changeStates(clear, -1);
			break;
		}//switch 상황 별
		}//else 일 가능
	}//working 일나감>돈 +500 허기 -2, 기분-2, 에너지 -2, 청결도-1 시간 +8(새벽이면 일 못나감)
	
	public void eating() {
		money-=100;
		hunger = changeStates(hunger, 5);
		feeling = changeStates(feeling, 1);
		energy = changeStates(energy, -1);
		clear = changeStates(clear, -1);
		time+=1;
		System.out.println(name+"이(는) 밥을 맛있게 먹었습니다!");
	}//eating 밥먹음>돈 -100 허기 +5,  에너지 -1, 청결도-1 시간 +1 기분+1
	
	public void sleeping() {
		time+=6;
		hunger = changeStates(hunger, -1);
		feeling = changeStates(feeling, 1);
		energy = changeStates(energy, 5);
		clear = changeStates(clear, -1);
		System.out.println(name+"이(는) 꿈나라로 향합니다. 안녕히 주무세요!");
	}//sleeping 잠>시간+6, 허기-1,에너지 +5 기분+1 청결 -1

	public void shower() {
		hunger = changeStates(hunger, -1);
		feeling = changeStates(feeling, 1);
		clear = changeStates(clear, 5);
		time++;
		System.out.println(name+"이(는) 샤워를 합니다! 아이 깨끗해!");
	}//shower 샤워 청결도+5, 허기 -1 기분+1 시간 +1

	public void playing() {
		hunger = changeStates(hunger, -2);
		feeling = changeStates(feeling, 5);
		energy = changeStates(energy, -3);
		clear = changeStates(clear, -2);
		time+=3;
		money-=200;
		System.out.println(name+"이(는) 놀러갑니다! 돈을 좀 썼지만 재밌으면 그만이죠!!");
	}//playing 놀기>기분+5, 에너지-3 허기 -2  청결도-2 돈-200 시간 +3
	
	public boolean happiness() {
		boolean checkHpp;
		if(clear>5 && hunger>3 && energy>2 && feeling>6) {
			checkHpp = true;
		}//if 행복한 경우
		else {
			checkHpp = false;
		}//else 불행한 경우
		return checkHpp;
	}//행복도
	
	public void condition() {
		happy = happiness();
		if(!happy) {
			showSadFace(1);
			System.out.println(name+"은(는) 현재 불행하다고 느끼고 있습니다 ㅠㅠ  상태를 확인해주세요!");
		}//불행한 경우
	}//condition 상태 이야기해주기
	
	public void showState() {
		System.out.println("========= "+name+"의 상태 =========");
		System.out.print("허기\t: "+hunger+"\t");
		showStateBar(hunger);
		System.out.print("재미\t: "+feeling+"\t");
		showStateBar(feeling);
		System.out.print("에너지\t: "+energy+"\t");
		showStateBar(energy);
		System.out.print("위생\t: "+clear+"\t");
		showStateBar(clear);
		System.out.println("===========================");
	}//showState 상태보기
	
	public void showFace(int select) {
		switch(select) {
		case 1:
			System.out.println("   ⌒⌒");
			System.out.println("(＾▽＾ )");
			System.out.println("/      \\");
			System.out.print("\\______/");
			sleep(400);
			System.out.print("    ♬\t");
			sleep(400);
			System.out.print("♪");
			sleep(400);
			System.out.print("♩\t");
			sleep(400);
			System.out.print("♪");
			sleep(400);
			System.out.print("♬");
			sleep(400);
			System.out.println("♩\t");
			sleep(400);
			break;
		case 2:
			System.out.println("    ⌒⌒");
			System.out.println(" (^ڡ^ )");
			System.out.println("/      \\");
			System.out.print("\\______/");
			showdots(400, "🍙");
			break;
		case 3:
			System.out.println("   ⌒⌒");
			System.out.println(" (  -_-) 💤");
			System.out.println("/      \\");
			System.out.print("\\______/");
			showdots(400,"🛌");
			break;
		case 5:
			System.out.println("   ⌒⌒");
			System.out.println(" ( ಠ_ಠ)   ");
			System.out.println("/      \\ ");
			System.out.print("\\______/");
			showdots(400,"💰");
			break;
		case 4:
			System.out.println("   ⌒⌒     🚿");
			System.out.println(" ( ＾▽＾)");
			System.out.println("/      \\");
			System.out.print("\\______/");
			showdots(400,"💧");
			break;
		default:
			System.out.println("   ⌒⌒");
			System.out.println(" (◕‿◕)");
			System.out.println("/      \\");
			System.out.println("\\______/");
			sleep(400);
			break;
		}//switch select
	}//showFace 표정 출력
	
	public void showSadFace(int num) {
		switch(num) {
		case 1:
			System.out.println("   ⌒⌒");
			System.out.println(" (ಥ﹏ಥ)");
			System.out.println("/      \\");
			System.out.println("\\______/");
			break;
		case 2:
			System.out.println("   ⌒ ⌒");
			System.out.println(" ( X﹏X )");
			System.out.println(" /      \\");
			System.out.println(" \\______/");
			break;
		}//switch
	}//showSadFace 슬픈 표정
	
	public int changeStates(int state, int num) {
		if(state+num>10) {
			state = 10;
		}//if 최댓값 초과
		else if(state+num<0) {
			state = 0;
		}//else if 최소값 미만
		else {
			state +=num;
		}//초과 안함
		return state;
	}//changeStates 상태 바꾸기> 최소값 0최대값 10 적용?
	
	public void showStateBar(int num) {
		for(int i=0; i<num;i++) {
			System.out.print("■");
		}//for ■출력
		for(int i=0; i<10-num; i++) {
			System.out.print("□");
		}//for □출력
		System.out.println();
	}//
	
	public void showdots(int num, String dots) {
		sleep(num);
		System.out.print("    "+dots+"\t");
		sleep(num);
		System.out.print(dots);
		sleep(num);
		System.out.print(dots+"\t");
		sleep(num);
		System.out.print(dots);
		sleep(num);
		System.out.print(dots);
		sleep(num);
		System.out.println(dots+"\t");
		sleep(num);
	}//show dots 시간차 점 찍기
	
	public int countZero() {
		int count=0;
		if(hunger ==0) {
			count++;
		}
		if(feeling ==0) {
			count++;
		}
		if(clear ==0) {
			count++;
		}
		if(energy ==0) {
			count++;
		}
		return count;
	}//countZero 상태가 0인것들 개수
	
	// 일단 실행 프로그램
	public void start() {
		Scanner sc = new Scanner(System.in);
		System.out.println("======== Game Start ========");
		System.out.print("캐릭터의 이름을 정해주세요 : ");
		name = sc.nextLine();
		sleep(500);
		System.out.println("\t*");
		sleep(500);
		System.out.println("\t*");
		sleep(500);
		System.out.println("\t*");
		sleep(500);
		System.out.println("   ⌒⌒");
		System.out.println(" (◕‿◕)");
		System.out.println("/      \\");
		System.out.println("\\______/");
		sleep(500);
		System.out.println("안녕하세요! "+name+"을(를) 도와주러 와줘서 고마워요!!");
		System.out.println(name+"(이)가 행복할 수 있도록 그의 선택을 도와주세요!!");
		while (true) {
			hour = sunMoon()+hour();
			System.out.println("===========================");
			System.out.println("현재 시각 : "+hour);
			System.out.println("가진 돈 : "+money);
			System.out.println("\n========= "+name+"의 선택 =========");
			System.out.println("1. 놀기");
			System.out.println("2. 밥 먹기");
			System.out.println("3. 잠 자기");
			System.out.println("4. 샤워 하기");
			System.out.println("5. 일 가기");
			System.out.println("0. 상태 보기");
			System.out.println("9. 게임 끝내기");
			System.out.print("선택 >> ");
			int select = sc.nextInt();
			switch(select) {
			case 1://놀기
				showFace(select);
				playing();
				break;
			case 2://밥먹기
				showFace(select);
				eating();
				break;
			case 3://잠
				showFace(select);
				sleeping();
				break;
			case 4://샤워
				showFace(select);
				shower();
				break;
			case 5://직장
				int clock = time%24;
				if(clock<6 || clock>9) {
					showSadFace(1);
				}//if 일 못함
				else {
					showFace(select);
				}//일 가능
				working();
				break;
			case 0:
				showState();
				break;
			case 9:
				showFace(select);
				System.out.println("이제 "+name+"(이)와 헤어질 시간이에요!");
				System.out.println("당신의 하루도 행복하기를 "+name+"(이)와 기도할게요:)");
				System.out.println();
				sleep(500);
				System.out.print("게임을 종료합니다...");
				showdots(500,".");
				return;
			}//switch select
			if(countZero()>2) {
				showSadFace(2);
				System.out.println(name+"(이)는 너무 힘들어서 그만 세상을 떠나고 말았습니다..ㅜㅠ");
				System.out.print("게임을 종료합니다...");
				showdots(500, ".");
				break;
			}//if 상태 0인거 3개 이상>>게임 오버
			condition();
			
		} // while 프로그램 실행중...
	}// start
	
	public void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}//sleep 시간차

}
