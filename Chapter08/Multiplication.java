// JAVA 프로그래밍 - https://codereading101.github.io/JAVA/
// 소스파일 - https://github.com/CodeReading101/JAVA/blob/main/Chapter08/Multiplication.java


public class Multiplication
{
	public static void main( String[] args ) {
		// 구구단 한 줄씩 출력
		for( int row = 1; row <= 9; row++ ) {
			// 구구단 한 칸씩 출력
			for( int column = 1; column <= 9; column++ ) {
				System.out.printf(" %d * %d = %2d ", row, column, column * row );
			}
			System.out.println();
		}
	}
}

