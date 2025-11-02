// JAVA 프로그래밍 - https://codereading101.github.io/JAVA/
// 소스파일 - https://github.com/CodeReading101/JAVA/blob/main/Chapter06/LivingPeriod.java

import java.util.Scanner;
public class LivingPeriod
{
	public static void main( String[] args ) {
		Scanner scan = new Scanner( System.in );
		// 생일 및 오늘 날짜를 입력
		System.out.print( "생년을 입력하세요: " );
		int birthYear = scan.nextInt();
		System.out.print( "생월을 입력하세요: " );
		int birthMonth = scan.nextInt();
		System.out.print( "생일을 입력하세요: " );
		int birthDay = scan.nextInt();
		System.out.print( "올해를 입력하세요: " );
		int thisYear = scan.nextInt();
		System.out.print( "이번 달을 입력하세요: " );
		int thisMonth = scan.nextInt();
		System.out.print( "오늘 날짜를 입력하세요: " );
		int thisDay = scan.nextInt();
		// 먼저 1년1월1일부터 생일 날짜까지 전체 날짜 계산
		// 1년1월1일부터 직전년도 12월31일까지 날짜 계산
		int birthTotalDays = ( birthYear - 1 ) * 365 + ( birthYear - 1 ) / 4 - ( birthYear - 1 ) / 100 + ( birthYear - 1 ) / 400;
		// 해당년도 1월1일부터 직전달 말일까지 날짜 계산
		switch ( birthMonth - 1 ) {
			case 12:
				birthTotalDays += 31;
			case 11:
				birthTotalDays += 30;
			case 10:
				birthTotalDays += 31;
			case 9:
				birthTotalDays += 30;
			case 8:
				birthTotalDays += 31;
			case 7:
				birthTotalDays += 31;
			case 6:
				birthTotalDays += 30;
			case 5:
				birthTotalDays += 31;
			case 4:
				birthTotalDays += 30;
			case 3:
				birthTotalDays += 31;
			case 2:
				birthTotalDays += 28;
				// 해당년도가 윤년이면 2월29일을 반영
				if ( ( ( birthYear % 400 ) == 0 ) || ( ( ( birthYear % 100 ) != 0 ) && ( ( birthYear % 4 ) == 0 ) ) ) {
					birthTotalDays++;
				}
			case 1:
				birthTotalDays += 31;
		}
		// 해당달 1일부터 해당일까지 날짜 계산
		birthTotalDays += birthDay;
		// 1년1월1일부터 오늘까지 전체 날짜 계산
		// 1년1월1일부터 작년12월31일까지 날짜 계산
		int thisTotalDays = ( thisYear - 1 ) * 365 + ( thisYear - 1 ) / 4 - ( thisYear - 1 ) / 100 + ( thisYear - 1 ) / 400;
		// 올해1월1일부터 지난달 말일까지 날짜 계산
		switch ( thisMonth - 1 ) {
			case 12:
				thisTotalDays += 31;
			case 11:
				thisTotalDays += 30;
			case 10:
				thisTotalDays += 31;
			case 9:
				thisTotalDays += 30;
			case 8:
				thisTotalDays += 31;
			case 7:
				thisTotalDays += 31;
			case 6:
				thisTotalDays += 30;
			case 5:
				thisTotalDays += 31;
			case 4:
				thisTotalDays += 30;
			case 3:
				thisTotalDays += 31;
			case 2:
				thisTotalDays += 28;
				// 올해가 윤년이면 2월29일을 반영
				if ( ( ( thisYear % 400 ) == 0 ) || ( ( ( thisYear % 100 ) != 0 ) && ( ( thisYear % 4 ) == 0 ) ) ) {
					thisTotalDays++;
				}
			case 1:
				thisTotalDays += 31;
		}
		// 이번달 1일부터 오늘까지 날짜 계산
		thisTotalDays += thisDay;

		// 몇 일째 살아왔는지 계산 후 출력
		int duration = thisTotalDays - birthTotalDays + 1;
		System.out.print( birthYear + "년 " + birthMonth + "월 " + birthDay + "일부터 " + thisYear + "년 " + thisMonth + "월 " + thisDay + "일까지 " + duration + "일째 잘 견디고 잘 살아내셨어요 당신을 응원합니다" );
		scan.close();
	}
}

