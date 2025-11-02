// JAVA 프로그래밍 - https://codereading101.github.io/JAVA/
// 소스파일 - https://github.com/CodeReading101/JAVA/blob/main/src/remoteControl/AirConditioner.java

package remoteControl;

// AirConditioner 클래스
public class AirConditioner implements RemoteControl
{
	// 에어컨 전원, 온도, 풍량 변수
	private boolean power;
	private int temperature, windStrength;

	// AirConditioner 생성자로 에어컨 상태 초기화
	public AirConditioner() {
		this.power = OFF;
		this.temperature = 19;
		this.windStrength = 5;
	}

	// 리모컨 전원 선택시 전원을 켜거나 끄기
	@Override
	public void clickPower() {
		if( this.power == OFF ) {
			this.power = ON;
			System.out.println( "에어컨 전원을 켭니다" );
		}
		else {
			this.power = OFF;
			System.out.println( "에어컨 전원을 끕니다" );
		}
	}

	// 리모컨 상(△) 선택시 온도를 +1 이동
	@Override
	public void clickUp() {
		System.out.println( "현재 온도는 " + ( ++this.temperature ) + "도입니다" );
	}

	// 리모컨 하(▽) 선택시 온도를 -1 이동
	@Override
	public void clickDown() {
		System.out.println( "현재 온도는 " + ( --this.temperature ) + "도입니다" );
	}

	// 리모컨 좌(◁) 선택시 풍량을 -1 이동
	@Override
	public void clickLeft() {
		System.out.println( "현재 풍량은 " + ( --this.windStrength ) + "입니다" );
	}

	// 리모컨 우(▷) 선택시 풍량을 +1 이동
	@Override
	public void clickRight() {
		System.out.println( "현재 풍량은 " + ( ++this.windStrength ) + "입니다" );
	}
}

