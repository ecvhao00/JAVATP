// JAVA 프로그래밍 - https://codereading101.github.io/JAVA/
// 소스파일 - https://github.com/CodeReading101/JAVA/blob/main/Chapter07/ValidRockPaperScissors.java

import java.util.Scanner;
public class ValidRockPaperScissors
{
	public static void main( String[] args ) {
		Scanner scan = new Scanner( System.in );
		String player = "";
		do {
			// 사용자가 가위바위보중 하나를 선택
			System.out.print( "가위, 바위, 보 중 하나를 입력하세요: " );
			player = scan.nextLine();
		// 사용자가 가위바위보를 잘못 입력하면 다시 선택
		} while( !player.equals("가위") && !player.equals("바위") && !player.equals("보") );
		// 사용자가 선택한 결과를 출력
		System.out.println( player + "를 냈습니다." );
		scan.close();
	}
}

