// JAVA 프로그래밍 - https://codereading101.github.io/JAVA/
// 소스파일 - https://github.com/CodeReading101/JAVA/blob/main/src/remoteControl/TV.java

package remoteControl;
import java.util.Scanner;

// TV 클래스
public class TV implements RemoteControl
{
	// TV 전원, 채널, 음량 변수
	private boolean power;
	private int channel;
	private int volume;

	// TV 생성자로 TV 상태 초기화
	public TV() {
		this.power = OFF;
		this.channel = 90;
		this.volume = 10;
	}

	// 리모컨 전원 선택시 전원을 켜거나 끄기
	@Override
	public void clickPower() {
		if( this.power == OFF ) {
			this.power = ON;
			System.out.println( "TV 전원을 켭니다" );
		}
		else {
			this.power = OFF;
			System.out.println( "TV 전원을 끕니다" );
		}
	}

	// 리모컨 상(△) 선택시 채널을 +1 이동
	@Override
	public void clickUp() {
		System.out.println( "현재 채널은 " + ( ++this.channel ) + "번입니다" );
	}

	// 리모컨 하(▽) 선택시 채널을 -1 이동
	@Override
	public void clickDown() {
		System.out.println( "현재 채널은 " + ( --this.channel ) + "번입니다" );
	}

	// 리모컨 좌(◁) 선택시 음량을 -1 이동
	@Override
	public void clickLeft() {
		System.out.println( "현재 음량은 " + ( --this.volume ) + "입니다" );
	}

	// 리모컨 우(▷) 선택시 음량을 +1 이동
	@Override
	public void clickRight() {
		System.out.println( "현재 음량은 " + ( ++this.volume ) + "입니다" );
	}
}

