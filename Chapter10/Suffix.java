// JAVA 프로그래밍 - https://codereading101.github.io/JAVA/
// 소스파일 - https://github.com/CodeReading101/JAVA/blob/main/Chapter10/Suffix.java

import java.util.Scanner;
public class Suffix
{
	public static void main( String[] args ) {
		Scanner scan = new Scanner( System.in );
		// 단어 입력
		System.out.print( "-ly, -ment, -ance로 끝나는 단어를 입력하세요: " );
		String input = scan.nextLine();
		// 단어 분석
		String[] suffixes = { "ly", "ment", "ance" };
		String[] words = input.split( " " );
		for( String word : words ) {
			System.out.print( word + " = " );
			// 어근
			String root = word;
			for( String suffix : suffixes )
				root = root.replace( suffix, "" );
			System.out.print( root );
			// 접미사
			for( String suffix : suffixes ) {
				if ( word.contains( suffix ) ) {
					int index = word.indexOf( suffix );
					System.out.println( "-" + word.substring( index ) );
					break;
				}
			}
		}
		scan.close();
	}
}

