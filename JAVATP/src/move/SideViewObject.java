// JAVA 프로그래밍 - https://codereading101.github.io/JAVA/
// 소스파일 - https://github.com/CodeReading101/JAVA/blob/main/src/move/SideViewObject.java

package move;

import java.awt.*;
import java.awt.event.*;

// 사이드 뷰 캐릭터
public class SideViewObject extends TopViewObject
{
	private final int SCOPE = 3;

	public SideViewObject( int[][] map, int x, int y, String imagePath ) {
		super( map, x, y, imagePath );
	}

	// 키를 누르면 이동방향을 설정하거나 점프 이동
	@Override
	public void keyPressed( KeyEvent event ) {
		super.keyPressed( event );
		switch( event.getKeyCode() ) {
		case 'Q': case 'q':
			jump( LEFT );
			break;
		case 'E': case 'e':
			jump( RIGHT );
			break;
		}
	}

	// 위쪽이 비어있으면 캐릭터 점프 이동
	public void jump( int directionX ) {
		final int JUMP_HEIGHT = 2;
		for( int i = 1; i <= JUMP_HEIGHT; i++ ) {
			if( map[y+i*UP][x] == WALL )
				return;
		}
		move( directionX, UP*JUMP_HEIGHT );
	}

	// 중력을 고려하여 바닥까지 캐릭터 낙하
	@Override
	public void move( int directionX, int directionY ) {
		for(; ( map[y+directionY][x+directionX] != WALL ) && ( map[y+directionY+1][x+directionX] != WALL ); directionY++ );
		super.move( directionX, directionY );
	}

	// 캐릭터와 주변 맵을 출력
	@Override
	public void paint( Graphics g ){
		for( int y = this.y - SCOPE, y2 = 0; y <= this.y + SCOPE; y++, y2++ ){
			for( int x = this.x - SCOPE, x2 = 0; x <= this.x + SCOPE; x++, x2++ ){
				int index = WALL;
				if ( ( this.x == x ) && ( this.y == y ) )
					index = CHARACTER;
				else if ( ( minX <= x ) && ( x <= maxX ) && ( minY <= y ) && ( y <= maxY ) )
					index = map[y][x];
				g.drawImage( image[index], x2*IMGSIZE, y2*IMGSIZE, IMGSIZE, IMGSIZE, null );
			}
		}
	}

	@Override
	public int backgroundWidth(){
		return IMGSIZE * ( 2*SCOPE + 1 );
	}
	@Override
	public int backgroundHeight(){
		return IMGSIZE * ( 2*SCOPE + 1 );
	}
}

