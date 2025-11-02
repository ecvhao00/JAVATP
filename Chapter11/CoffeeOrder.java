// JAVA 프로그래밍 - https://codereading101.github.io/JAVA/
// 소스파일 - https://github.com/CodeReading101/JAVA/blob/main/Chapter11/CoffeeOrder.java

import java.util.Scanner;
// 커피 클래스
class Coffee
{
	private static int total  = 0;
	private String name;
	private int cost;
	private int count;
	// 커피 주문 초기화
	public Coffee( String name, int cost ) {
		this.name = name;
		this.cost = cost;
		this.count = 0;
	}
	// 커피 주문 추가
	public void add() {
		this.count++;
		this.total += this.cost;
	}
	// 커피 주문 내역을 문자열로 표현
	public String toString() {
		if( this.count == 0 )
			return "";
		else
			return this.name + "   " + this.cost + "   " + this.count + "\n";
	}
	// 커피 주문 총합계를 반환
	public static int total() {
		return total;
	}
}
public class CoffeeOrder
{
	public static void main (String[] args ) {
		Scanner scan = new Scanner( System.in );
		// 커피 주문 준비
		Coffee[] coffee = {
		                    new Coffee( "아메리카노", 3500 ),
		                    new Coffee( "라      떼", 4000 ),
		                    new Coffee( "에스프레소", 3000 )
		                   };
		// 커피 주문 받기
		System.out.print( "아메리카노(1), 라떼(2), 에스프레소(3) 중 하나를 선택하세요: " );
		for( int order = scan.nextInt(); ( order == 1 ) || ( order == 2 ) || ( order == 3 ); order = scan.nextInt() ) {
			coffee[ order - 1 ].add();
			System.out.print( "아메리카노(1), 라떼(2), 에스프레소(3) 중 하나를 선택하세요: " );
		}
		// 영수증 출력
		System.out.println( "\n항      목   단가  개수\n-----------------------" );
		for( Coffee item : coffee )
			System.out.print(
			                  item );
		System.out.println( "-----------------------\n합      계   "
		                    + Coffee.total() + "원" );
		scan.close();
	}
}

