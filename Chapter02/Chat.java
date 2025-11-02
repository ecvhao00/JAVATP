// JAVA 프로그래밍 - https://codereading101.github.io/JAVA/
// 소스파일 - https://github.com/CodeReading101/JAVA/blob/main/Chapter02/Chat.java

import java.util.Scanner;
public class Chat
{
	public static void main( String[] args ) {
		Scanner scan = new Scanner( System.in );
		// 친구와 대화하기
		// 이름 물어보고 답하기
		System.out.print( "안녕? 넌 이름이 뭐야: " );
		String name = scan.next();
		System.out.println( "만나서 반가워, " + name + ". " );
		// 직업 물어보고 답하기
		System.out.print( "넌 주로 뭐하고 살아: " );
		String job = scan.next();
		System.out.println( job + "? 나도 마찬가지야 ㅎㅎ " );
		// 취미 물어보고 답하기
		System.out.print( "그럼 심심할 때 뭐하니: " );
		String hobby = scan.next();
		System.out.println( hobby + "? 나랑 똑같네. " );
		// 좋아하는 색 물어보고 답하기
		System.out.print( "혹시 좋아하는 색은 뭐야 : " );
		String color = scan.next();
		System.out.println( color + "이라구? 와! 이것도 나랑 똑같네. " );
		// 친하게 지내자고 인사하기
		System.out.println( "앞으로 우리 친하게 지내자." );
		scan.close();
	}
}

