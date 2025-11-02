// JAVA 프로그래밍 - https://codereading101.github.io/JAVA/
// 소스파일 - https://github.com/CodeReading101/JAVA/blob/main/Chapter08/Summation.java

import java.util.Scanner;
public class Summation
{
	public static void main( String[] args ) {
		Scanner scan = new Scanner( System.in );
		// 숫자 n 입력받기
		System.out.print( "숫자를 입력하세요: " );
		int num = scan.nextInt();
		// n부터 0까지의 합계 계산하기
		System.out.print( "Σ " + num + " = " );
		int sum = 0;
		for(; num > 0; num-- ) {
			System.out.print( num + " + " );
			sum += num;
		}
		// 합계 출력하기
		System.out.print( num + " = " + sum );
		scan.close();
	}
}

