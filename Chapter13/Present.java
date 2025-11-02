// JAVA 프로그래밍 - https://codereading101.github.io/JAVA/
// 소스파일 - https://github.com/CodeReading101/JAVA/blob/main/Chapter13/Present.java

import java.awt.*;
// 선물 클래스
public class Present
{
	int x, y;
	// 클릭 위치에 선물 생성
	public Present( int x, int y ) {
		this.x = x;
		this.y = y;
	}
	// 선물 낙하
	public void drop() {
		this.y += 10;
	}
	// 선물 그리기
	public void paint( Graphics g ) {
		g.setColor( Color.red );
		g.fillRect( x,    y,    10, 10 );
		g.fillRect( x+20, y,    10, 10 );
		g.fillRect( x,    y+20, 10, 10 );
		g.fillRect( x+20, y+20, 10, 10 );
		g.setColor( new Color( 0, 100, 0 ) );
		g.fillRect( x+10, y,    10, 30 );
		g.fillRect( x,    y+10, 30, 10 );
	}
}

