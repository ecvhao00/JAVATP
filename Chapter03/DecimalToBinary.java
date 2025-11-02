// JAVA 프로그래밍 - https://codereading101.github.io/JAVA/
// 소스파일 - https://github.com/CodeReading101/JAVA/blob/main/Chapter03/DecimalToBinary.java

import java.util.Scanner;
public class DecimalToBinary
{
	public static void main( String[] args ) {
		Scanner scan = new Scanner( System.in );
		// 8부터 15사이의 10진수를 입력
		System.out.print( "8부터 15사이의 10진수를 입력하세요: " );
		int decimal = scan.nextInt();
		// 10진수에서 2진수의 각 자리수 2^3, 2^2, 2^1, 2^0을 순서대로 추출
		// 2진수 1000 = 2^3 = 8의 자리수
		int binary1000 = decimal / 8;
		// 2진수 100 = 2^2 = 4의 자리수
		int binary100 = ( decimal % 8 ) / 4;
		// 2진수 10 = 2^1 = 2의 자리수
		int binary10 = ( decimal % 4 ) / 2;
		// 2진수 1 = 2^0 = 1의 자리수
		int binary1 = decimal % 2;
		// 2진수 각 자리수의 값을 바탕으로 10진수를 2진수로 변환
		int binary = binary1000 * 1000 + binary100 * 100 + binary10 * 10 + binary1;
		// 10진수를 2진수로 변환한 결과를 출력
		System.out.print( "10진수 " + decimal + "은 2진수 " + binary + "입니다." );
		scan.close();
	}
}

