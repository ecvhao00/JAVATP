// JAVA 프로그래밍 - https://codereading101.github.io/JAVA/
// 소스파일 - https://github.com/CodeReading101/JAVA/blob/main/Chapter05/FindBall.java

import java.util.Scanner;
public class FindBall
{
	public static void main( String[] args ) {
		Scanner scan = new Scanner( System.in );
		// 먼저 3개 컵 중에서 하나에 공 숨기기
		int ball = (int)( Math.random() * 3 ) + 1;
		System.out.println( "  ___    ___    ___  " );
		System.out.println( " |   |  |   |  |   | " );
		System.out.println( " | 1 |  | 2 |  | 3 | \n\n\n" );
		// 사용자에게 공을 숨긴 컵 번호 입력 받기
		System.out.print( " 1, 2, 3 중에서 공을 숨긴 컵을 찾으세요: " );
		int cup = scan.nextInt();
		// 공 찾기 결과 출력
		System.out.print( "\033[4;" + (ball*7-3) + "fO\n\033[5;" + (cup*7-5) + "f" );
		System.out.print( ( cup == ball ) ? "찾았다!" : "놓쳤다!" );
		scan.close();
	}
}

