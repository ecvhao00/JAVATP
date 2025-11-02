// JAVA 프로그래밍 - https://codereading101.github.io/JAVA/
// 소스파일 - https://github.com/CodeReading101/JAVA/blob/main/Chapter11/PassByValueVsPassByReference.java


// 숫자 클래스
class Int
{
	protected int value;
	// 값 초기화
	public Int( int value ) {
		this.value = value;
	}
	// 값 문자열 표현
	public String toString() {
		return this.value + " ";
	}
}
public class PassByValueVsPassByReference
{
	// 전달인자 수정
	public static void change( int parameter1, Int parameter2, Int parameter3 ) {
		System.out.println( "change() 변경전  parameter1 = " + parameter1 + "    parameter2 = " + parameter2 + "   parameter3 = " + parameter3 );
		// Pass by Value 후 값 수정
		parameter1 = 100;
		// Pass by Reference 후 새 객체를 생성하여 수정
		parameter2 = new Int( 200 );
		// Pass by Reference 후 객체 값을 직접 수정
		parameter3.value = 300;
		System.out.println( "change() 변경후  parameter1 = " + parameter1 + "  parameter2 = " + parameter2 + " parameter3 = " + parameter3 );
	}
	public static void main( String[] args ) {
		// 전달인자 초기화
		int argument1 = 1;
		Int argument2 = new Int( 2 );
		Int argument3 = new Int( 3 );
		// 전달인자 수정
		System.out.println( "change() 호출전   argument1 = " +  argument1 + "     argument2 = " +  argument2 + "    argument3 = " +  argument3 );
		change( argument1, argument2, argument3 );
		System.out.println( "change() 호출후   argument1 = " +  argument1 + "     argument2 = " +  argument2 + "    argument3 = " +  argument3 );
	}
}

