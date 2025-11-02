// JAVA 프로그래밍 - https://codereading101.github.io/JAVA/
// 소스파일 - https://github.com/CodeReading101/JAVA/blob/main/Chapter05/WeaponItem.java

import java.util.Scanner;
public class WeaponItem
{
	public static void main( String[] args ) {
		Scanner scan = new Scanner( System.in );
		// 먼저, 캐릭터의 초기 위치를 출력
		System.out.println( "      / " );
		System.out.println( " [   옷   D" );
		System.out.println( "      -> \n" );
		// 이동 방향 입력 받기
		System.out.print( "방향키(WASD)와[Enter]를 입력하세요: " );
		char direction= scan.next().charAt(0);
		// WASD 입력에 따른 이동 결과 출력
		System.out.print( "\033[2;6f  " );
		switch( direction ) {
			// W는 위로 이동
			case 'w': case 'W':
				System.out.print( "\033[1;6f 옷/\033[7;15f" );
				break;
			// A는 왼쪽으로 이동
			case 'a': case 'A':
				System.out.print( "\033[2;1f 옷]\033[7;15f" );
				break;
			// S는 아래로 이동
			case 's': case 'S':
				System.out.print( "\033[3;6f 옷->\033[7;15f" );
				break;
			// D는 오른쪽으로 이동
			case 'd': case 'D':
				System.out.print( "\033[2J\033[4;15fSUCCESS!\033[7;15f" );
		}
		scan.close();
	}
}

