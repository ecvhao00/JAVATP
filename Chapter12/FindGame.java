// JAVA 프로그래밍 - https://codereading101.github.io/JAVA/
// 소스파일 - https://github.com/CodeReading101/JAVA/blob/main/Chapter12/FindGame.java

import java.util.Scanner;
import find.FindBall;
import find.FindBomb;
public class FindGame
{
	public static void main( String[] args ) {
		Scanner scan = new Scanner( System.in );
		// 첫째 공 찾기 게임
		FindBall game = new FindBall();
		// 공 숨기기
		game.hide();
		// 공 찾기
		game.find( scan );
		// 공 찾기 결과 출력
		System.out.println(
		                    game );
		// 둘째 폭탄 피하기 게임
		game = new FindBomb();
		// 폭탄 숨기기
		game.hide();
		// 폭탄 피하기
		game.find( scan );
		// 폭탄 피하기 결과 출력
		System.out.println(
		                    game );

	}
}

