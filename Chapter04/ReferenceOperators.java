// JAVA 프로그래밍 - https://codereading101.github.io/JAVA/
// 소스파일 - https://github.com/CodeReading101/JAVA/blob/main/Chapter04/ReferenceOperators.java


public class ReferenceOperators
{
	public static void main( String[] args ) {
		String color = new String( "green" );
		String green = new String( "green" );
		String blue = new String( "blue" );
		String red = new String( "red" );
		System.out.print( "  color = green\n" );
		// 첫째 ==: 참조값 비교
		// 예) (obj01)와 (obj02)을 비교하면 거짓
		System.out.print( "( color == green ) = " + ( color == green ) + ",     " );
		System.out.print( "( color == blue ) = " + ( color == blue ) + ",      "  );
		System.out.print( "( color == red ) = " + ( color == red ) + "\n" );
		// 둘째 equals: 참조한 객체에 저장된 값이 같은지 비교후 boolean 반환
		// 예) 문자열 'green'과 'green'을 비교하면 참
		System.out.print( "  color.equals( green ) = " + color.equals( green ) + ", "  );
		System.out.print( "  color.equals( blue ) = " + color.equals( blue ) + ", "  );
		System.out.print( "  color.equals( red ) = " + color.equals( red )  + "\n" );
		// 셋째 compareTo: 참조한 객체에 저장된 값을 비교후 int 반환
		// 예) 'g'-'b'= 5, 'g'-'r'= -11, 'g'-'g'= 0, 'r'-'r'= 0, 'e'-'e'= 0, 'n'-'n'= 0
		System.out.print( "  color.compareTo( green ) = " + color.compareTo( green )  + ", " );
		System.out.print( "  color.compareTo( blue ) = " + color.compareTo( blue ) + ",  "  );
		System.out.print( "  color.compareTo( red ) = " + color.compareTo( red ) + "\n" );
	}
}

