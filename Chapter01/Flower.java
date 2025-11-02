// JAVA 프로그래밍 - https://codereading101.github.io/JAVA/
// 소스파일 - https://github.com/CodeReading101/JAVA/blob/main/Chapter01/Flower.java


public class Flower
{
	public static void main( String[] args ) {
		// 빨강 꽃 모양을 출력
		System.out.print( "\033[31m\033[01m " );
		System.out.println( "    _   " );
		System.out.println( "  /\\ /\\ " );
		System.out.println( " ( -O- )" );
		System.out.println( "  \\/_\\/ " );
		// 초록 잎 모양을 출력
		System.out.println( "\033[32m    |   " );
		System.out.println( "  \\\\|// " );
		System.out.println( "  \\\\|// " );
	}
}

