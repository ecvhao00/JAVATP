// JAVA 프로그래밍 - https://codereading101.github.io/JAVA/
// 소스파일 - https://github.com/CodeReading101/JAVA/blob/main/Chapter05/FlipCoin.java

import java.util.Scanner;
public class FlipCoin
{
	public static void main( String[] args ) {
		Scanner scan = new Scanner( System.in );
		// 먼저 동전을 임의로 던져서 받고 숨기기
		int coin = ( (int)( Math.random() * 2 ) ) + 1;
		// 사용자는 동전의 앞면과 뒷면 중 하나를 선택
		System.out.print( "동전의 앞면(1), 뒷면(2) 중 하나를 선택하세요: " );
		int user = scan.nextInt();
		// 사용자가 맞추었는지 결과 출력
		System.out.print( ( coin == user ) ? "맞았습니다" : "틀렸습니다" );
		scan.close();
	}
}

