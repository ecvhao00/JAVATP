// JAVA 프로그래밍 - https://codereading101.github.io/JAVA/
// 소스파일 - https://github.com/CodeReading101/JAVA/blob/main/Chapter12/TVControl.java

import java.util.Scanner;
import remoteControl.TV;
public class TVControl
{
	public static void main( String[] args ) {
		Scanner scan = new Scanner( System.in );
		String result = "";

		// TV를 제어하는 리모컨 준비
		TV tv = new TV();

		// 리모컨 입력에 따라 TV 제어
		do {
			System.out.print( "TV 리모컨의 상, 하, 좌, 우, 전원, 종료 중 하나를 입력하세요 : ");
			result = scan.next();

			// 리모컨 전원 선택시 TV 제어
			if ( result.equals( "전원" ) )
				tv.clickPower();
			// 리모컨 상(△) 선택시 TV 제어
			else if ( result.equals( "상" ) )
				tv.clickUp();
			// 리모컨 하(▽) 선택시 TV 제어
			else if ( result.equals( "하" ) )
				tv.clickDown();
			// 리모컨 좌(◁) 선택시 TV 제어
			else if ( result.equals( "좌" ) )
				tv.clickLeft();
			// 리모컨 우(▷) 선택시 TV 제어
			else if ( result.equals( "우" ) )
				tv.clickRight();

		} while( result.equals( "전원" ) || result.equals( "상" ) || result.equals( "하" ) || result.equals( "좌" ) || result.equals( "우" ) );

	}
}


