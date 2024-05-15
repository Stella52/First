package function.snail;

import java.util.Scanner;

public class Snail {
	public void exam7() {
		Scanner sc = new Scanner(System.in);
		System.out.print("만들 사각형의 한 변의 길이를 입력하세요 : ");
		int size = sc.nextInt();
		int[][] arr = new int[size][size];// 5=2*2+1
		int k = 1;
		// System.out.println("hi");

		for (int i = 0;  i <( arr.length+1)/2; i++) {
			for (int j = 0; j < arr.length - 2 * i; j++) {
				arr[i][i + j] = k++;
			} // for1 ->방향
			for (int j = 0; j < arr.length - 1 - 2 * i; j++) {
				arr[i + j + 1][arr.length - 1 - i] = k++;
			} // for2 위->아래 방향
			for (int j = 0; j < arr.length - 1 - 2 * i; j++) {
				arr[arr.length - 1 - i][arr.length - 2 - i - j] = k++;
			} // for3 <- 방향
			for (int j = 0; j < arr.length - 2 - 2 * i; j++) {
				arr[arr.length - 2 - i - j][i] = k++;
			} // for4 아래->위 방향
		} // for

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				System.out.print(arr[i][j] + "\t");
			} // for arr 행의 각 열 전체 출력
			System.out.println();
		} // for 전체출력

	}// exam7  일반화
	
	public void snail() {
		//달팽이배열
		Scanner sc = new Scanner(System.in);
		System.out.print("2차원 배열 크기 입력(정방형) > ");
		int size = sc.nextInt();
		int[][] arr = new int[size][size];	//입력받은크기의 정방형 배열 생성
		int k = 1;							//배열안의 채울 값 변수
		int inc =1;							//인덱스번호 증감용 변수
		int r=0;							//2차원배열 행 인덱스번호
		int c=-1;							//2차원배열 열 인덱스번호
		while(size>0) {
			for(int i=0;i<size;i++) {
				c=c+inc;
				arr[r][c]=k;
				k++;
			}
			size--;							//횟수가 줄어드므로
			for(int i=0;i<size;i++) {
				r=r+inc;
				arr[r][c]=k;
				k++;
			}
			inc = -inc;						//방향이 바뀌므로
		}
		//비교: 내가 한것: 한바퀴가 한번 <-> 두 변의 움직임이 한번, (ㄱ, ㄴ 모양을 방향을 inc의 부호를 바꿔서 표현함)
		for(int i=0;i<arr.length;i++) {
			for(int j=0;j<arr[i].length;j++) {
				System.out.print(arr[i][j]+"\t");
			}
			System.out.println();
		}
	}//snail
	
	
}
