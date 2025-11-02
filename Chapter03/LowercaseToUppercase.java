// JAVA 프로그래밍 - https://codereading101.github.io/JAVA/
// 소스파일 - https://github.com/CodeReading101/JAVA/blob/main/Chapter03/LowercaseToUppercase.java

import java.util.Scanner;
public class LowercaseToUppercase
{
	public static void main( String[] args ) {
		Scanner scan = new Scanner( System.in );
		// 알파벳 소문자를 입력받기
		System.out.print( "알파벳 소문자 한 글자를 입력하세요: " );
		char lowercase = scan.next().charAt(0);
		// 알파벳 소문자를 대문자로 변환하기
		char uppercase = (char)( (int) lowercase - (int) 'a' + (int) 'A' );
		// 입력값 출력하기
		System.out.print( "소문자 " + lowercase + "의 대문자는 " + uppercase + "입니다^^" );
		scan.close();
	}
}

