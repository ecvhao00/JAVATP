// JAVA 프로그래밍 - https://codereading101.github.io/JAVA/
// 소스파일 - https://github.com/CodeReading101/JAVA/blob/main/Chapter12/AllApplianceControl.java

import java.util.Scanner;
import remoteControl.*;

public class AllApplianceControl
{
	public static void main (String[] args ) {
		Scanner scan = new Scanner( System.in );

		// 가전제품(TV, 에어컨, 로봇청소기)을 제어하는 리모컨 준비
		RemoteControl[] appliance = {
		                        new TV(),
		                        new AirConditioner(),
		                        new RobotCleaner()
		                     };

		// 리모컨 입력에 따라 가전제품(TV, 에어컨, 로봇청소기) 제어
		String result = "";
		do {
			System.out.print( "리모컨의 상, 하, 좌, 우, 전원, 종료 중 하나를 입력하세요 : ");
			result = scan.next();

			// 리모컨 전원 선택시 가전제품 제어
			if ( result.equals( "전원" ) ) {
				for ( int i = 0; i < appliance.length; i++ ) {
					appliance[i].clickPower();
				}
			}
			// 리모컨 상(△) 선택시 가전제품 제어
			else if ( result.equals( "상" ) ) {
				for ( int i = 0; i < appliance.length; i++ ) {
					appliance[i].clickUp();
				}
			}
			// 리모컨 하(▽) 선택시 가전제품 제어
			else if ( result.equals( "하" ) ) {
				for ( int i = 0; i < appliance.length; i++ ) {
					appliance[i].clickDown();
					}
				}
			// 리모컨 좌(◁) 선택시 가전제품 제어
			else if ( result.equals( "좌" ) ) {
				for ( int i = 0; i < appliance.length; i++ ) {
					appliance[i].clickLeft();
				}
			}
			// 리모컨 우(▷) 선택시 가전제품 제어
			else if ( result.equals( "우" ) ) {
				for ( int i = 0; i < appliance.length; i++ ) {
					appliance[i].clickRight();
				}
			}
		} while( result.equals( "전원" ) || result.equals( "상" ) || result.equals( "하" ) || result.equals( "좌" ) || result.equals( "우" ) );

	}
}

