// JAVA 프로그래밍 - https://codereading101.github.io/JAVA/
// 소스파일 - https://github.com/CodeReading101/JAVA/blob/main/Chapter13/RockPaperScissorsGUIMain.java

import javax.swing.*;
import rpsGame.RockPaperScissorsPanel;

public class RockPaperScissorsGUIMain
{
	public static void main( String[] args ) {
		final String imagePath = "C:\\Users\\user\\Downloads\\JAVA-main\\src\\rpsGame\\image\\";

		// 틀에 판을 끼우고 실행 준비 완료
		JFrame frame = new JFrame( "가위바위보" );
		frame.getContentPane().add( new RockPaperScissorsPanel( imagePath ) );
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame.pack();
		frame.setVisible( true );
	}
}


