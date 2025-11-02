// JAVA 프로그래밍 - https://codereading101.github.io/JAVA/
// 소스파일 - https://github.com/CodeReading101/JAVA/blob/main/Chapter09/WordSort.java

import java.util.Scanner;
public class WordSort
{
	public static void main( String[] args ) {
		Scanner scan = new Scanner( System.in );
		// 단어들 입력
		String[] words = { "", "", "", "", "" };
		for ( int index = 0; index < words.length; index++ ) {
			System.out.print( "단어를 입력하세요: " );
			words[index] = scan.next();
		}
		// 단어 정렬
		// 마지막 칸에 가장 큰 값을 저장하면서 한 칸씩 앞으로 영역 좁혀가기
		for ( int last = words.length - 1; 0 < last; last-- ) {
			// 영역의 첫번째 칸부터 인접한 두 값을 비교하면서
			for ( int index = 0; index < last; index++ ) {
				// 앞칸 단어가 뒤칸 단어보다 알파벳순으로 뒤면, 두 단어를 맞바꾸기
				if ( words[index].compareTo( words[index+1] ) > 0 ) {
					String temp = words[index];
					words[index] = words[index+1];
					words[index+1] = temp;
				}
			}
		}
		// 정렬된 단어 출력
		for ( String word : words )
			System.out.print( word + " " );
		scan.close();
	}
}

