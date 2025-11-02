// JAVA 프로그래밍 - https://codereading101.github.io/JAVA/
// 소스파일 - https://github.com/CodeReading101/JAVA/blob/main/Chapter14/BombDodgeGameMain.java

import javax.swing.*;
import move.*;

public class BombDodgeGameMain
{
	public static void main( String[] args )
	{
		final String imagePath = "C:\\Users\\user\\Downloads\\JAVA-main\\src\\move\\image\\";
		final int WIDTH = 300;
		final int HEIGHT= 600;

		// 판을 틀에 끼우고 실행 준비 완료
		BombDodgePanel panel = new BombDodgePanel( new CollidableObject( imagePath+"character.png", WIDTH/2, HEIGHT-CollidableObject.IMGSIZE, WIDTH, HEIGHT ), imagePath+"bomb.png" );
		JFrame frame = new JFrame ( "폭탄 피하기 게임" );
		frame.getContentPane().add( panel );
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame.pack();
		frame.setVisible( true );
	}
}


