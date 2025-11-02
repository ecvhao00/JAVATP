// JAVA 프로그래밍 - https://codereading101.github.io/JAVA/
// 소스파일 - https://github.com/CodeReading101/JAVA/blob/main/Chapter07/Elevator.java

import java.util.Scanner;
public class Elevator
{
	public static void main( String[] args ) {
		Scanner scan = new Scanner( System.in );
		// 엘리베이터에서 내릴 층수 입력받기
		System.out.print( "1층~5층 중 몇 층으로 올라가시나요 : " );
		int destinationFloor = scan.nextInt();
		// 출발 메시지 출력
		System.out.println( destinationFloor + "층으로 올라갑니다. 문이 닫힙니다." );
		// 1층부터 목적지까지 한 층씩 이동하기
		int currentFloor = 1;
		while( currentFloor <= destinationFloor ) {
			System.out.println( currentFloor + "층" );
			currentFloor++;
		}
		// 도착 메시지 출력
		System.out.println( "딩~동~댕~동~~ " + destinationFloor + "층입니다. 문이 열립니다" );
		scan.close();
	}
}

