// JAVA 프로그래밍 - https://codereading101.github.io/JAVA/
// 소스파일 - https://github.com/CodeReading101/JAVA/blob/main/src/move/ObjectOnMap.java

package move;

// 맵에 있는 캐릭터 클래스
public class ObjectOnMap
{
	protected int[][] map;
	protected int x, y, minX, minY, maxX, maxY;
	protected final int LEFT = -1, RIGHT = 1, UP = -1, DOWN = 1, STOP = 0;
	protected final int PATH = 0, WALL = 1, CHARACTER = 2;
	protected final String symbol[] = { "  ", "\033[44m  \033[0m", "옷" };
	public ObjectOnMap( int[][] map, int x, int y ) {
		this.map = map;
		this.x = x;
		this.y = y;
		this.minX = 0;
		this.minY = 0;
		this.maxX = ( map == null ) ? 0 : map[0].length-1;
		this.maxY = ( map == null ) ? 0 : map.length-1;
	}

	// 캐릭터 이동
	public void move( char direction ) {
		// 이동 방향 확인
		int directionX = STOP, directionY = STOP;
		switch( direction ) {
			case 'W': case 'w':
				directionY = UP;
				break;
			case 'A': case 'a':
				directionX = LEFT;
				break;
			case 'S': case 's':
				directionY = DOWN;
				break;
			case 'D': case 'd':
				directionX = RIGHT;
				break;
		}
		move( directionX, directionY );
	}

	// 벽인지 허용 범위인지 확인하고 캐릭터 이동
	public void move( int directionX, int directionY ) {
		if( map[this.y+directionY][this.x+directionX] == WALL )
			return;

		this.x += directionX;
		this.y += directionY;
		this.x = ( this.x <= minX ) ? minX : this.x;
		this.y = ( this.y <= minY ) ? minY : this.y;
		this.x = ( this.x >= maxX ) ? maxX : this.x;
		this.y = ( this.y >= maxY ) ? maxY : this.y;
	}

	// 캐릭터 및 전체 맵 표현
	@Override
	public String toString() {
		String strMap = "\033[1;1f";
		for( int y = minY; y <= maxY; y++ ) {
			for( int x = minY; x <= maxX; x++ ) {
				strMap += symbol[ map[y][x] ];
			}
			strMap += "\n";
		}
		return strMap + this.appear();
	}

	// 캐릭터만 나타나기
	public String appear() {
		return "\033[" + (this.y+1) + ";" + (this.x*2+1) + "f"+ symbol[ CHARACTER ];
	}

	// 캐릭터만 사라지기
	public String disappear() {
		return "\033[" + (this.y+1) + ";" + (this.x*2+1) + "f  ";
	}
}

