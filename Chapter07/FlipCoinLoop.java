// JAVA 프로그래밍 - https://codereading101.github.io/JAVA/
// 소스파일 - https://github.com/CodeReading101/JAVA/blob/main/Chapter07/FlipCoinLoop.java

import java.util.Scanner;
public class FlipCoinLoop
{
	public static void main( String[] args ) {
		Scanner scan = new Scanner( System.in );
		char again = '\0';
		do {
			// 동전 던지기 게임
			// 먼저 동전을 임의로 던져서 받고 숨기기
			int coin = (int)( Math.random() * 2 ) + 1;
			// 사용자는 동전의 앞면과 뒷면 중 하나를 선택
			System.out.print( "동전의 앞면(1), 뒷면(2) 중 하나를 선택하세요: " );
			int user = Integer.parseInt( scan.nextLine() );
			// 사용자가 동전의 상태를 맞추었는지 결과 출력
			System.out.println(  ( coin == user ) ? "맞았습니다" : "틀렸습니다" );
			// 사용자에게 게임을 한번 더 할지 묻기
			System.out.print( "동전 던지기 게임을 한 번 더 하시겠습니까?(y/n): " );
			again = scan.nextLine().charAt(0);
		} while( ( again == 'y' ) || ( again == 'Y' ) );
		scan.close();
	}
}

