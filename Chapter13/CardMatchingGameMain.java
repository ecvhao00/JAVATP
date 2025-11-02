// JAVA 프로그래밍 - https://codereading101.github.io/JAVA/
// 소스파일 - https://github.com/CodeReading101/JAVA/blob/main/Chapter13/CardMatchingGameMain.java

import javax.swing.*;

public class CardMatchingGameMain
{
	public static void main(String[] args)
	{
		final int[][] color = { { 0, 1, 2, 7 },
		                        { 3, 5, 6, 0 },
		                        { 2, 4, 6, 3 },
		                        { 4, 5, 1, 7 } };

		// 틀에 판을 끼우고 실행 준비 완료
		JFrame frame = new JFrame ( "같은 색깔 찾기" );
		frame.getContentPane().add( new CardMatchingPanel( color ) );
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	}
}


