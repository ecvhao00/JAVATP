// JAVA 프로그래밍 - https://codereading101.github.io/JAVA/
// 소스파일 - https://github.com/CodeReading101/JAVA/blob/main/Chapter02/Sea.java

import java.util.Scanner;
public class Sea
{
	public static void main( String[] args ) {
		Scanner scan = new Scanner( System.in );
		// 붉은 태양과 푸른 바다를 출력
		System.out.print( "\033[31m" );
		System.out.println( "   \\_/  " );
		System.out.println( "  _/ \\_ " );
		System.out.println( "   \\_/  " );
		System.out.print( "   / \\  " );
		System.out.print( "\033[0m \n  \n\033[44m" );
		System.out.println( "~~~~~~ ~~~~~ ~~~~~~~~ ~~  ~~~~~~~~~~ " );
		System.out.println( "~     ~~   ~  ~   ~~~~~~~   ~~~    ~ " );
		System.out.println( "  ~ ~~~~~~~      ~~          ~    ~~ " );
		System.out.println( "~  ~       ~ ~   ~    ~~~~~~~    ~ ~ " );
		System.out.print( "      ~             ~        ~    ~  " );
		// 밤바다로 변경하려면 입력 받기
		System.out.print( "\033[0m\033[12;1f밤바다를 보려면 문자를 입력하세요: " );
		char input = scan.next().charAt(0);
		// 밤바다를 출력
		System.out.print( "\033[33m\033[1;3f" );
		System.out.println( "   *    *    .      *   .   *    .  " );
		System.out.println( "  .    *         .       *.     .   " );
		System.out.println( "             .    *         *     * " );
		System.out.println( "    *  .       *    .   . *     *   " );
		System.out.println( "   .    *        .       .   *    .\033[0m\033[12;1f " );
		scan.close();
	}
}

