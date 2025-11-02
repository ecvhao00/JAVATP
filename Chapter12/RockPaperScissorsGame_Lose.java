// JAVA 프로그래밍 - https://codereading101.github.io/JAVA/
// 소스파일 - https://github.com/CodeReading101/JAVA/blob/main/Chapter12/RockPaperScissorsGame_Lose.java

import java.util.Scanner;
import rpsGame.RockPaperScissors_Lose;

public class RockPaperScissorsGame_Lose
{
	public static void main( String[] args ) {
		Scanner scan = new Scanner( System.in );
		// 지는 가위바위보 초기화
		RockPaperScissors_Lose playerA = new RockPaperScissors_Lose();
		RockPaperScissors_Lose playerB = new RockPaperScissors_Lose();

		// 지는 가위바위보 선택
		playerA.select( scan );
		playerB.select();
		System.out.println( "A양은 " +
		                                playerA
		                                        + "를 냈습니다." );
		System.out.println( "B군은 " +
		                                playerB
		                                        + "를 냈습니다." );

		// 지는 가위바위보 승패 출력
		if ( playerA.equals( playerB ) )
			System.out.println( "A양과 B군이 비겼습니다" );
		else if ( playerA.win( playerB ) )
			System.out.println( "A양이 이겼습니다" );
		else
			System.out.println( "B군이 이겼습니다" );

		scan.close();
	}
}


