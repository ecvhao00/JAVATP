// JAVA 프로그래밍 - https://codereading101.github.io/JAVA/
// 소스파일 - https://github.com/CodeReading101/JAVA/blob/main/Chapter10/WordArrayList.java

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
public class WordArrayList {
	public static void main( String[] args ) {
		Scanner scan = new Scanner( System.in );
		ArrayList<String> words = new ArrayList<String>();
		// 단어 입력
		while( true ) {
			System.out.print( "단어를 입력하세요(종료시quit): " );
			String word = scan.nextLine();
			if ( word.equals("quit") )
				break;
			words.add( word );
		}
		// 단어 정렬
		Collections.sort( words );
		// 단어 출력
		for( String word : words )
			System.out.print( word + " " );
		scan.close();
	}
}

