// JAVA 프로그래밍 - https://codereading101.github.io/JAVA/
// 소스파일 - https://github.com/CodeReading101/JAVA/blob/main/Chapter05/ScoreMessage.java

import java.util.Scanner;
public class ScoreMessage
{
	public static void main( String[] args ) {
		Scanner scan = new Scanner( System.in );
		// 사용자에게 점수 입력받기
		System.out.print( "0점 ~ 100점 사이의 점수를 입력하세요: " );
		int score = scan.nextInt();
		switch ( score / 10 ) {
			// 90점이상이면 와! 끝내주게 잘 했다를 출력
			case 10:
			case 9:
				System.out.print( "와! " );
			// 80점이상이면 끝내주게 잘 했다를 출력
			case 8:
				System.out.print( "끝내주게 " );
			// 70점이상이면 잘 했다를 출력
			case 7:
				System.out.print( "잘 " );
			// 나머지는 했다를 출력
			default:
				System.out.print( "했다" );
		}
		scan.close();
	}
}

