// JAVA 프로그래밍 - https://codereading101.github.io/JAVA/
// 소스파일 - https://github.com/CodeReading101/JAVA/blob/main/Chapter04/TaxiFare.java

import java.util.Scanner;
public class TaxiFare
{
	public static void main( String[] args ) {
		Scanner scan = new Scanner( System.in );
		// 사용자에게 이동거리를 입력받기
		System.out.print( "택시의 이동거리(m)를 입력하세요(1600이상): " );
		int distance = scan.nextInt();
		// 택시요금은 1.6km까지 기본료가 4800원이고 131m 당 100원 부과
		int taxiFare = 4800 + ( distance - 1600 + 130 ) / 131 * 100;
		// 택시요금 출력
		System.out.print( distance + "m 이동시 택시요금은 " + taxiFare + "원입니다 " );
		scan.close();
	}
}

