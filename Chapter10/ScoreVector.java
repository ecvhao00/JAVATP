// JAVA 프로그래밍 - https://codereading101.github.io/JAVA/
// 소스파일 - https://github.com/CodeReading101/JAVA/blob/main/Chapter10/ScoreVector.java

import java.util.Scanner;
import java.util.Vector;
import java.util.Collections;
public class ScoreVector {
	public static void main( String[] args ) {
		Scanner scan = new Scanner( System.in );
		Vector<Integer> scores = new Vector<Integer>();
		// 점수 입력
		while( true ) {
			System.out.print( "0점 ~ 100점 사이의 점수를 입력하세요: " );
			Integer score = scan.nextInt();
			if ( ( score < 0 ) || ( 100 < score ) )
				break;
			scores.add( score );
		}
		// 점수 정렬
		Collections.sort( scores );
		// 점수 출력
		for( Integer score : scores )
			System.out.print( score + " "  );
		scan.close();
	}
}

