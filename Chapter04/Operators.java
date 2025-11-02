// JAVA 프로그래밍 - https://codereading101.github.io/JAVA/
// 소스파일 - https://github.com/CodeReading101/JAVA/blob/main/Chapter04/Operators.java


public class Operators
{
	public static void main( String[] args ) {
		// 산술연산자 실습 및 출력
		System.out.println( " 3 + 4 = " + ( 3 + 4 ) );
		System.out.println( " 5 - 3 = " + ( 5 - 3 ) );
		System.out.println( " 2 * 3 = " + ( 2 * 3 ) );
		System.out.println( "10 / 3 = " + (10 / 3 ) );
		System.out.println( "10 % 3 = " + (10 % 3 ) + "\n" );
		// 비교연산자 실습 및 출력: 1(참), 0(거짓)
		System.out.println( "5 == 5 = " + ( 5 == 5 ) + ",   3 == 7 = " + ( 3 == 7 ) );
		System.out.println( "9 != 2 = " + ( 9 != 2 ) + ",   8 != 8 = " + ( 8 != 8 ) );
		System.out.println( "4 <  5 = " + ( 4 < 5 ) + ",   7 <  2 = " + ( 7 < 2 ) );
		System.out.println( "7 >  2 = " + ( 7 > 2 ) + ",   4 >  5 = " + ( 4 > 5 ) );
		System.out.println( "4 <= 5 = " + ( 4 <= 5 ) + ",   7 <= 2 = " + ( 7 <= 2 ) );
		System.out.println( "7 >= 2 = " + ( 7 >= 2 ) + ",   4 >= 5 = " + ( 4 >= 5 ) + "\n" );
		// 논리연산자 실습 및 출력: 1(참), 0(거짓)
		System.out.println( "  !false   = " + !false + ",	 !true   = " + !true );
		System.out.println( "false && false = " + ( false && false ) + ",   false && true = " + ( false && true ) + ",   true && false = " + ( true && false ) + ",   true && true = " + ( true && true ) );
		System.out.println( "false || false = " + ( false || false ) + ",   false || true = " + ( false || true ) + ",   true || false = " + ( true || false ) + ",   true || true = " + ( true || true ) );
	}
}

