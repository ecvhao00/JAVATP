// JAVA 프로그래밍 - https://codereading101.github.io/JAVA/
// 소스파일 - https://github.com/CodeReading101/JAVA/blob/main/Chapter14/TopViewGameMain.java

import javax.swing.*;
import move.TopViewObject;
import move.ViewPanel;

public class TopViewGameMain
{
	public static void main( String[] args )
	{
		final String imagePath = "C:\\Users\\minky\\Desktop\\학교\\수업\\1-2\\자바\\JAVA-main\\src\\move\\image\\";
		// 미로 맵을 2차원 배열로 초기화
		int[][] map = { { 1,1,1,1,1,1,1,1,1 },
		                { 1,0,0,1,0,0,1,0,1 },
		                { 1,1,0,1,0,1,1,0,1 },
		                { 1,0,3,1,0,0,0,0,1 },
		                { 1,0,1,1,1,3,1,0,1 },
		                { 1,0,0,0,0,0,1,0,1 },
		                { 1,1,1,1,1,1,1,1,1 }
		              };
		
		// 판을 틀에 끼우고 실행 준비 완료
                TopViewObject character = new TopViewObject( map, 1, 1, imagePath );
                character.registerClue( 2, 3,
                                        "이곳에서 오래된 메모를 발견했다.",
                                        "'출구는 북동쪽에 있다'고 적혀 있다." );
                character.registerClue( 5, 4,
                                        "바닥에 작은 열쇠가 떨어져 있다.",
                                        "주머니에 넣었다." );

                ViewPanel panel = new ViewPanel( character );
		

		JFrame frame = new JFrame( "탑 뷰 맵" );
		frame.getContentPane().add( panel );
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame.pack();
		frame.setVisible(true);
	}
}


