// JAVA 프로그래밍 - https://codereading101.github.io/JAVA/
// 소스파일 - https://github.com/CodeReading101/JAVA/blob/main/Chapter05/Month.java

import java.util.Scanner;
public class Month
{
	public static void main( String[] args ) {
		Scanner scan = new Scanner( System.in );
		// 사용자에게 달을 입력받기
		System.out.print( "1월 ~ 12월 사이의 월을 입력하세요: " );
		int month = scan.nextInt();
		// 입력받은 달의 영어이름을 출력
		switch ( month ) {
			// 1월이면 January를 출력
			case 1:
				System.out.print( "1월은 January입니다" );
				break;
			// 2월이면 February를 출력
			case 2:
				System.out.print( "2월은 February입니다" );
				break;
			// 3월이면 March를 출력
			case 3:
				System.out.print( "3월은 March입니다" );
				break;
			// 4월이면 April를 출력
			case 4:
				System.out.print( "4월은 April입니다" );
				break;
			// 5월이면 May를 출력
			case 5:
				System.out.print( "5월은 May입니다" );
				break;
			// 6월이면 June를 출력
			case 6:
				System.out.print( "6월은 June입니다" );
				break;
			// 7월이면 July를 출력
			case 7:
				System.out.print( "7월은 July입니다" );
				break;
			// 8월이면 August를 출력
			case 8:
				System.out.print( "8월은 August입니다" );
				break;
			// 9월이면 September를 출력
			case 9:
				System.out.print( "9월은 September입니다" );
				break;
			// 10월이면 October를 출력
			case 10:
				System.out.print( "10월은 October입니다" );
				break;
			// 11월이면 November를 출력
			case 11:
				System.out.print( "11월은 November입니다" );
				break;
			// 12월이면 December를 출력
			default:
				System.out.print( "12월은 December입니다" );
		}
		scan.close();
	}
}

