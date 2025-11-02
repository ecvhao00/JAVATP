// JAVA 프로그래밍 - https://codereading101.github.io/JAVA/
// 소스파일 - https://github.com/CodeReading101/JAVA/blob/main/Chapter14/SideViewGameMain.java

import javax.swing.*;
import move.SideViewObject;
import move.ViewPanel;

public class SideViewGameMain
{
	public static void main( String[] args )
	{
		final String imagePath = "C:\\Users\\user\\Downloads\\JAVA-main\\src\\move\\image\\";
		int[][] map = { { 1,1,1,1,1,1,1,1,1,1,1,1,1 },
		                { 1,0,0,0,0,0,0,0,0,0,0,0,1 },
		                { 1,0,0,0,1,1,1,0,0,0,0,0,1 },
		                { 1,0,0,0,0,0,0,0,0,0,0,0,1 },
		                { 1,0,0,0,0,0,1,1,1,1,0,0,1 },
		                { 1,0,0,0,0,0,0,0,0,0,0,0,1 },
		                { 1,0,0,1,1,1,1,0,0,0,1,0,1 },
		                { 1,0,0,0,0,0,0,0,0,0,0,0,1 },
		                { 1,1,1,1,1,1,1,1,1,1,1,1,1 }
		              };

		// 판을 틀에 끼우고 실행 준비 완료
		ViewPanel panel = new ViewPanel( new SideViewObject( map, 4, 5, imagePath ) );
		JFrame frame = new JFrame( "사이드 뷰 맵" );
		frame.getContentPane().add( panel );
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame.pack();
		frame.setVisible(true);
	}
}


