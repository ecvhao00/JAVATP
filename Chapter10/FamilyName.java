// JAVA 프로그래밍 - https://codereading101.github.io/JAVA/
// 소스파일 - https://github.com/CodeReading101/JAVA/blob/main/Chapter10/FamilyName.java

import java.util.Scanner;
public class FamilyName
{
	public static void main( String[] args ) {
		Scanner scan = new Scanner( System.in );
		// 이름 2개를 입력
		System.out.print( "A양, 이름을 입력하세요: " );
		String nameA = scan.next();
		System.out.print( "B군, 이름을 입력하세요: " );
		String nameB = scan.next();
		// 이름에서 첫 음절을 비교하기
		// 첫 음절이 같으면 성이 같습니다 출력
		if ( nameA.charAt(0) == nameB.charAt(0) )
			System.out.print( nameA + "과 " + nameB + "은 성이 같습니다 " );
		// 첫 음절이 다르면 성이 다릅니다 출력
		else
			System.out.print( nameA + "과 " + nameB + "은 성이 다릅니다 " );
		scan.close();
	}
}

