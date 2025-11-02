// JAVA 프로그래밍 - https://codereading101.github.io/JAVA/
// 소스파일 - https://github.com/CodeReading101/JAVA/blob/main/Chapter09/MazeMapArray.java


public class MazeMapArray
{
	public static void main( String[] args ) {
		// 미로 맵을 2차원 배열로 초기화
		int[][] map = { { 1,1,1,1,1,1,1,1,1 },
		                { 1,2,0,0,1,0,0,0,3 },
		                { 1,1,1,0,1,0,1,1,1 },
		                { 1,0,0,0,1,0,0,0,1 },
		                { 1,0,1,1,1,1,1,0,1 },
		                { 1,0,0,0,0,0,0,0,1 },
		                { 1,1,1,1,1,1,1,1,1 }
		              };
		// 길, 벽, 옷, 문의 출력값 설정
		String[] symbol = { "  ", "\033[44m  \033[0m", "\033[31m옷\033[0m", "\033[34m문\033[0m" };
		// 2차원 미로 맵을 한 칸씩 출력
		for( int row = 0; row < map.length; row++ ) {
			for( int column = 0; column < map[0].length; column ++ ) {
				System.out.print( symbol[ map[row][column] ] );
			}
			System.out.println();
		}
	}
}

