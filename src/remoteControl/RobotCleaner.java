// JAVA 프로그래밍 - https://codereading101.github.io/JAVA/
// 소스파일 - https://github.com/CodeReading101/JAVA/blob/main/src/remoteControl/RobotCleaner.java

package remoteControl;
// Robot 클래스
public class RobotCleaner implements RemoteControl
{
	private boolean power;
	// Robot 생성자로 로봇청소기 상태 초기화
	public RobotCleaner() {
		this.power = OFF;
	}
	// 리모컨 전원 선택시 전원을 켜거나 끄기
	@Override
	public void clickPower() {
		if( this.power == OFF ) {
			this.power = ON;
			System.out.println( "로봇청소기 전원을 켭니다" );
		}
		else {
			this.power = OFF;
			System.out.println( "로봇청소기 전원을 끕니다" );
		}
	}
	// 리모컨 상(△) 선택시 직진
	@Override
	public void clickUp() {
		System.out.println( "직진합니다" );
	}
	// 리모컨 하(▽) 선택시 후진
	@Override
	public void clickDown() {
		System.out.println( "후진합니다" );
	}
	// 리모컨 좌(◁) 선택시 좌회전
	@Override
	public void clickLeft() {
		System.out.println( "좌회전합니다" );
	}
	// 리모컨 우(▷) 선택시 우회전
	@Override
	public void clickRight() {
		System.out.println( "우회전합니다" );
	}
}

