// JAVA 프로그래밍 - https://codereading101.github.io/JAVA/
// 소스파일 - https://github.com/CodeReading101/JAVA/blob/main/src/rpsGame/RockPaperScissors_Lose.java

package rpsGame;
// 지는 가위바위보 클래스
public class RockPaperScissors_Lose extends RockPaperScissors {
	// 지는 가위바위보 초기화
	public RockPaperScissors_Lose() {
		super();
	}
	// 가위바위보 승패를 반대로 변경
	@Override
	public boolean win( int counterpart ) {
		return !
		        super.win( counterpart );
	}
}

