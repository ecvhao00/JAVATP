// JAVA 프로그래밍 - https://codereading101.github.io/JAVA/
// 소스파일 - https://github.com/CodeReading101/JAVA/blob/main/src/rpsGame/RockPaperScissors.java

package rpsGame;
import java.util.Scanner;

// 가위바위보 클래스
public class RockPaperScissors
{
	private int player;
	private final int SCISSORS = 0;
	private final int ROCK = 1;
	private final int PAPER = 2;
	// 가위바위보 초기화
	public RockPaperScissors() {
		this.player = SCISSORS;
	}

	// 가위바위보 선택: 키보드 입력
	public void select( Scanner scan ) {
		String input = "";
		do {
			System.out.print( "가위, 바위, 보 중 하나를 입력하세요 : ");
			input = scan.next();
		} while( !input.equals( "가위" ) && !input.equals( "바위" ) && !input.equals( "보" ) );
		scan.close();

		if ( input.equals( "가위" ) )
			this.player = SCISSORS;
		else if ( input.equals( "바위" ) )
			this.player = ROCK;
		else
			this.player = PAPER;
	}

	// 가위바위보 선택: 임의값
	public void select() {
		this.player = (int)( Math.random() * 3 );
	}

	// 가위바위보를 문자열로 표현
	public String toString() {
		if ( this.player == SCISSORS )
			return "가위";
		else if ( this.player == ROCK )
			return "바위";
		else
			return "보";
	}

	// 가위바위보를 숫자로 표현
	public int toInteger() {
		return this.player;
	}

	// 상대편과 같은지 비교
	public boolean equals( RockPaperScissors counterpart ) {
		return this.player == counterpart.toInteger();
	}

	// 상대편을 이겼는지 비교
	public boolean win( RockPaperScissors counterpart ) {
		return win( counterpart.toInteger() );
	}

	public boolean win( int counterpart ) {
		if ( ( ( this.player == SCISSORS ) && ( counterpart == PAPER ) )
				|| ( ( this.player == PAPER ) && ( counterpart == ROCK ) )
				|| ( ( this.player == ROCK ) && ( counterpart == SCISSORS ) ) )
			return true;
		else
			return false;
	}
}

