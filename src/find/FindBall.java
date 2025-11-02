// JAVA 프로그래밍 - https://codereading101.github.io/JAVA/
// 소스파일 - https://github.com/CodeReading101/JAVA/blob/main/src/find/FindBall.java

package find;
import java.util.Scanner;

// 공 찾기 클래스
public class FindBall
{
	protected String message;
	protected String meet;
	protected String dodge;
	private int ball;
	private int cup;

	// 공 찾기 초기화
	public FindBall() {
		this.message = "1, 2, 3 중에서 공을 숨긴 컵을 찾으세요: ";
		this.meet   = "찾았다!\n";
		this.dodge  = "놓쳤다!\n";
		this.ball = 1;
		this.cup = 0;
	}

	// 공 숨기기
	public void hide() {
		this.ball = (int)( Math.random() * 3 ) + 1;
	}

	// 공 찾기
	public void find( Scanner scan ) {
		System.out.print( message );
		this.cup = scan.nextInt();
	}

	// 공 찾기 결과 출력
	@Override
	public String toString() {
		String str = "";
		str += "  ___    ___    ___  \n";
		str += " |   |  |   |  |   | \n";
		str += " | 1 |  | 2 |  | 3 | \n";
		str += blanks( ball ) + "   O   \n";
		str += blanks( cup ) + ( ( this.cup == this.ball ) ? meet : dodge );
		return str;
	}

	// 공백 확보
	private String blanks( int column ) {
		String result = "";
		for ( int i = 1; i < column; i++ )
			result += "       ";
		return result;
	}
}

