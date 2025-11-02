// JAVA 프로그래밍 - https://codereading101.github.io/JAVA/
// 소스파일 - https://github.com/CodeReading101/JAVA/blob/main/Chapter03/OverflowUnderflow.java


public class OverflowUnderflow
{
	public static void main( String[] args ) {
		// int의 최대값과 최소값으로 변수 초기화
		final int max = +2147483647;
		final int min = -2147483648;
		// int의 최대값에 1을 더하거나 최소값에서 1을 빼면 오류 발생
		int overflow = max + 1;
		int underflow = min - 1;
		// overflow 및 underflow 결과 출력
		System.out.println( "max     = " + max );
		System.out.println( "max + 1 = " + overflow + " ( overflow 발생 )" );
		System.out.println( "min     = " + min );
		System.out.println( "min - 1 = " + underflow + " ( underflow 발생 )" );
	}
}

