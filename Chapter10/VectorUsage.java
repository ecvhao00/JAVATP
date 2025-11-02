// JAVA 프로그래밍 - https://codereading101.github.io/JAVA/
// 소스파일 - https://github.com/CodeReading101/JAVA/blob/main/Chapter10/VectorUsage.java

import java.util.Vector;
public class VectorUsage
{
	// 벡터 상태 출력
	public static void print( Vector<String> list ) {
		System.out.print( "공간크기 " + list.capacity() + ", 총원소개수 " + list.size() + "," );
		for( int i = 0; i < list.size(); i++ )
			System.out.print( " (" + i + ") " + list.get(i) );
		if( list.isEmpty() )
			System.out.print( " 빈 벡터" );
		else if( list.contains( "cut in line" ) )
			System.out.print( " 새치기 발생" );
		System.out.println();
	}
	public static void main( String[] args ) {
		// 벡터 생성
		Vector<String> list = new Vector<String>( 3 );
		// 객체 추가
		list.add( "first " );
		list.add( "second" );
		list.add( "third " );
		print( list );
		list.add( 0, "cut in line" );
		list.add( 4, null );
		print( list );
		// 객체 제거
		list.remove( 0 );
		list.remove( 0 );
		print( list );
		// 벡터 비우기
		list.clear();
		print( list );
	}
}

