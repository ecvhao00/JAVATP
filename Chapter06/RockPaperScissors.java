// JAVA 프로그래밍 - https://codereading101.github.io/JAVA/
// 소스파일 - https://github.com/CodeReading101/JAVA/blob/main/Chapter06/RockPaperScissors.java

import java.util.Scanner;
public class RockPaperScissors
{
	public static void main( String[] args ) {
		Scanner scan = new Scanner( System.in );
		// A양 가위바위보 선택
		System.out.print( "A양, 가위, 바위, 보 중 하나를 선택하세요: " );
		String playerA = scan.next();
		System.out.println( playerA + "를 냈습니다." );
		// B군 가위바위보 선택
		System.out.print( "B군, 가위, 바위, 보 중 하나를 선택하세요: " );
		String playerB = scan.next();
		System.out.println( playerB + "를 냈습니다." );
		// 가위바위보 승패 출력
		// 먼저 A양과 B군이 같으면 비김
		if ( playerA.equals( playerB ) ) {
			System.out.print( "A양과 B군이 비겼습니다." );
		}
		// A양이 가위이고 B군이 보를 낸 경우 A양 승리
		else if ( playerA.equals( "가위" ) && playerB.equals( "보" ) )	 {
			System.out.print( "A양이 이겼습니다." );
		}
		// A양이 바위이고 B군이 가위를 낸 경우 A양 승리
		else if ( playerA.equals( "바위" ) && playerB.equals( "가위" ) ) {
			System.out.print( "A양이 이겼습니다." );
		}
		// A양이 보이고 B군이 바위를 낸 경우 A양 승리
		else if ( playerA.equals( "보" ) && playerB.equals( "바위" ) ) {
			System.out.print( "A양이 이겼습니다." );
		}
		// 나머지 경우 B군 승리
		else {
			System.out.print( "B군이 이겼습니다." );
		}
		scan.close();
	}
}

