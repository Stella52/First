package function.kakao;

import java.util.Scanner;

public class CodeTest {
	public void kakao() {//함수를 따로 만들어 봅시다
		Scanner sc = new Scanner(System.in);
		//입력받기 : 지도의 한 변의 크기 n, 배열  arr1, arr2
		int size;
		while(true) {
			System.out.print("지도의 한 변의 크기를 입력하세요(1<n<16)  : ");
			int num = sc.nextInt();
			if(1<num && num<16) {
				size=num;
				break;
			}//if 알맞은 n값 받은 경우
			else {
				System.out.println("범위를 잘못 입력하셨습니다. 다시 입력하세요.");
			}//else 잘못 입력받은 경우
		}//while 알맞은 n값 받기
	
		boolean [][] map1 = new boolean [size][size];
		boolean [][] map2= new boolean [size][size];
		int[] arr1= new int [size];
		int[] arr2=new int [size];
		
		//배열에서 받을수 있는 정수값의 최댓값 구하기
		int max=1;
		for(int i=0; i<size;i++) {
			max*=2;
		}//for 최댓값 구하기
		
		//System.out.println("최댓값 : "+(max-1));
		
		System.out.println("arr1의 정수 값을 입력하세요");
		for(int i=0; i<size; i++) {
			System.out.print(i+1+" 번째 값 : ");
			int temp = sc.nextInt();
			if(0<=temp && temp<=max-1) {
				arr1[i] = temp;
			}//if 범위에 맞는 정수값을 입력 받은 경우
			else {
				i--;
				System.out.println("다시 입력하세요.");
			}//else 잘못 입력 받은 경우
			//arr1[i]=sc.nextInt();
			//지정된 범위내로 받아야함>>if 쓸것
		}//for arr1 배열 값 입력받기
		
		System.out.println("arr2의 정수 값을 입력하세요");
		for(int i=0; i<size; i++) {
			System.out.print(i+1+" 번째 값 : ");
			int temp = sc.nextInt();
			if(0<=temp && temp<=max-1) {
				arr2[i] = temp;
			}//if 범위에 맞는 정수값을 입력 받은 경우
			else {
				i--;
				System.out.println("다시 입력하세요.");
			}//else 잘못 입력 받은 경우
			//지정된 범위내로 받아야함>>if 쓸것
		}//for arr2 배열 값 입력받기
		
		int bin;//이진수로 바꾸기 위한 변수
		//map1에 arr1값 넣기
		for(int i=0; i<size; i++) {
			bin=arr1[i];
			for(int j=0; j<size; j++) {
				if(bin%2==1) {
					map1[i][size-1-j] = true;
				}//홀수
				if(bin/2==0) {
					break;
				}//if 이진법 바꾸기 끝
				bin/=2;
			}//for map[i]행 채우기
		}//for 이진법으로 바꾸기
		
		for(int i=0; i<size; i++) {
			bin=arr2[i];
			for(int j=0; j<size; j++) {
				if(bin%2==1) {
					map2[i][size-1-j] = true;
				}//홀수
				if(bin/2==0) {
					break;
				}//if 이진법 바꾸기 끝
				bin/=2;
			}//for map[i]행 채우기
		}//for 이진법으로 바꾸기
		boolean[][]  map = new boolean [size][size];
		for(int i=0; i<size;i++) {
			for(int j=0; j<size; j++) {
				map[i][j] = map1[i][j]||map2[i][j];
			}//for
		}//for
		System.out.println("===== map1 출력 =====");
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				if(map1[i][j]) {
					System.out.print("#\t");
				}//if true>#
				else {
					System.out.print(" \t");
				}//else false>" "
			}//for
			System.out.println();
		}//for map1 출력
		System.out.println("===== map2 출력 =====");
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				if(map2[i][j]) {
					System.out.print("#\t");
				}//if true>#
				else {
					System.out.print(" \t");
				}//else false>" "
			}//for
			System.out.println();
		}//for map 2출력
		System.out.println("===== map 출력 =====");
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				if(map[i][j]) {
					System.out.print("#\t");
				}//if true>#
				else {
					System.out.print(" \t");
				}//else false>" "
			}//for
			System.out.println();
		}//for map 출력
	}//kakao

}
