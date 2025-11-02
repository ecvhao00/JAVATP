// JAVA 프로그래밍 - https://codereading101.github.io/JAVA/
// 소스파일 - https://github.com/CodeReading101/JAVA/blob/main/Chapter14/BulletDodgeGameMain.java

import javax.swing.*;
import move.*;

public class BulletDodgeGameMain
{
	public static void main( String[] args )
	{
		final String imagePath = "C:\\Users\\user\\Downloads\\JAVA-main\\src\\move\\image\\";
		final int WIDTH = 600;
		final int HEIGHT= 300;

		// 판을 틀에 끼우고 실행 준비 완료
		BulletDodgePanel panel = new BulletDodgePanel( new CollidableObject( imagePath+"character.png", WIDTH-CollidableObject.IMGSIZE, HEIGHT/2, WIDTH, HEIGHT ), imagePath+"bullet.png" );
		JFrame frame = new JFrame ( "총알 피하기 게임" );
		frame.getContentPane().add( panel );
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame.pack();
		frame.setVisible( true );

	}
}


