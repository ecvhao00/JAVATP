// JAVA 프로그래밍 - https://codereading101.github.io/JAVA/
// 소스파일 - https://github.com/CodeReading101/JAVA/blob/main/Chapter01/MazeMap.java


public class MazeMap
{
	public static void main( String[] args ) {
		// 먼저 미로 맵을 출력
		System.out.println( "\033[44m                  \033[0m " );
		System.out.println( "\033[44m  \033[0m      \033[44m  \033[0m " );
		System.out.println( "\033[44m      \033[0m  \033[44m  \033[0m  \033[44m      \033[0m " );
		System.out.println( "\033[44m  \033[0m      \033[44m  \033[0m      \033[44m  \033[0m " );
		System.out.println( "\033[44m  \033[0m  \033[44m          \033[0m  \033[44m  \033[0m " );
		System.out.println( "\033[44m  \033[0m              \033[44m  \033[0m " );
		System.out.println( "\033[44m                  \033[0m " );
		// 캐릭터 및 출구를 출력
		System.out.print( "\033[31m\033[2;3f옷\033[34m\033[2;17f문\033[9;1f " );
	}
}

