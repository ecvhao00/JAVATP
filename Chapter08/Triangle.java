// JAVA 프로그래밍 - https://codereading101.github.io/JAVA/
// 소스파일 - https://github.com/CodeReading101/JAVA/blob/main/Chapter08/Triangle.java

import java.util.Scanner;
public class Triangle
{
	public static void main( String[] args ) {
		Scanner scan = new Scanner( System.in );
		// 사용자에게 삼각형의 크기를 입력 받기
		System.out.print( "삼각형 크기를 입력하세요: " );
		int size = scan.nextInt();
		// 삼각형 크기만큼 한 줄씩 출력
		for ( int line = 1; line <= size; line++ ) {
			// '삼각형 크기 - 줄번호'만큼 공백 ' '을 출력
			for ( int blank = size - line; blank > 0; blank-- ) {
				System.out.print( " " );
			}
			// '2 * 줄번호 - 1'만큼 별모양 '*'를 출력
			for ( int star = 2 * line - 1; star > 0; star-- ) {
				System.out.print( "*" );
			}
			// 끝에 빈줄을 출력
			System.out.println();
		}
		scan.close();
	}
}

