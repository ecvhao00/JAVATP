// JAVA 프로그래밍 - https://codereading101.github.io/JAVA/
// 소스파일 - https://github.com/CodeReading101/JAVA/blob/main/Chapter03/ScoreAverage.java

import java.util.Scanner;
public class ScoreAverage
{
	public static void main( String[] args ) {
		Scanner scan = new Scanner( System.in );
		// 과목별 점수를 입력
		System.out.print( "0점 ~ 100점 사이의 국어 점수를 입력하세요: " );
		int korean = scan.nextInt();
		System.out.print( "0점 ~ 100점 사이의 영어 점수를 입력하세요: " );
		int english = scan.nextInt();
		System.out.print( "0점 ~ 100점 사이의 수학 점수를 입력하세요: " );
		int mathematics = scan.nextInt();
		// 평균 점수를 계산
		double average = (double)( korean + english + mathematics ) / 3;
		// 과목별 점수 및 평균을 출력
		System.out.printf( "국어 %d점, 영어 %d점, 수학 %d점의 평균은 %.2f점입니다.",
							korean, english, mathematics, average );
		scan.close();
	}
}

