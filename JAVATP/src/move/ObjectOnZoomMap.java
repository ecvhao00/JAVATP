// JAVA 프로그래밍 - https://codereading101.github.io/JAVA/
// 소스파일 - https://github.com/CodeReading101/JAVA/blob/main/src/move/ObjectOnZoomMap.java

package move;
// 줌 맵에 있는 캐릭터 클래스
public class ObjectOnZoomMap extends ObjectOnMap
{
	public static final int SCOPE = 3;
	public ObjectOnZoomMap( int[][] map, int x, int y ) {
		super( map, x, y );
	}
	// 캐릭터 및 주변 맵 표현
	@Override
	public String toString() {
		String zoomMap = "\033[1;1f";
		for( int y = this.y - SCOPE, offsetY = y; y <= this.y + SCOPE; y++ ){
			for( int x = this.x - SCOPE, offsetX = x; x <= this.x + SCOPE; x++ ){
				int index = WALL;
				if ( ( x == this.x ) && ( y == this.y ) )
					index = CHARACTER;
				else if ( ( 0 <= x ) && ( x <= maxX ) && ( 0 <= y ) && ( y <= maxY ) )
					index = map[y][x];
				zoomMap += symbol[ index ];
			}
			zoomMap += "\n";
		}
		return zoomMap;
	}
}

