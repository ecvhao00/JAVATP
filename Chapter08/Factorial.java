// JAVA 프로그래밍 - https://codereading101.github.io/JAVA/
// 소스파일 - https://github.com/CodeReading101/JAVA/blob/main/Chapter08/Factorial.java

import java.util.Scanner;
public class Factorial
{
	public static void main( String[] args ) {
		Scanner scan = new Scanner( System.in );
		// 숫자 n 입력받기
		System.out.print( "숫자를 입력하세요: " );
		int num = scan.nextInt();
		// n부터 1까지의 곱 계산하기
		System.out.print( num + "! = " );
		int factorial = 1;
		for(; num > 1; num-- ) {
			System.out.print( num + " * " );
			factorial *= num;
		}
		// 곱셈 결과 출력하기
		System.out.print( num + " = " + factorial );
		scan.close();
	}
}

