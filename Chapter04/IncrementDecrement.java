// JAVA 프로그래밍 - https://codereading101.github.io/JAVA/
// 소스파일 - https://github.com/CodeReading101/JAVA/blob/main/Chapter04/IncrementDecrement.java


public class IncrementDecrement
{
	public static void main( String[] args ) {
		// 증가연산자 ++의 전위연산자와 후위연산자 실습 및 출력
		int two = 2;
		int pre = ++ two;
		System.out.println( "pre  = ++2 는 2+1(3) 실행후 pre에 " + pre + " 대입" );
		two = 2;
		int post = two ++;
		System.out.println( "post = 2++ 는 post에 " + post + " 대입후 2+1(3) 실행" );
		// 감소연산자 --의 전위연산자와 후위연산자 실습 및 출력
		two = 2;
		pre = -- two;
		System.out.println( "pre  = --2 는 2-1(1) 실행후 pre에 " + pre + " 대입" );
		two = 2;
		post = two --;
		System.out.println( "post = 2-- 는 post에 " + post + " 대입후 2-1(1) 실행" );
	}
}

