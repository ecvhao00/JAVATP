// JAVA 프로그래밍 - https://codereading101.github.io/JAVA/
// 소스파일 - https://github.com/CodeReading101/JAVA/blob/main/Chapter13/AutomaticDoorGUIMain.java

import javax.swing.*;

public class AutomaticDoorGUIMain
{
	public static void main( String[] args ) {
		// 프레임에 자동문 패널을 끼우고 실행 준비 완료
		JFrame frame = new JFrame( "자동문" );
		frame.getContentPane().add( new AutomaticDoorPanel() );
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame.pack();
		frame.setVisible(true);
	}
}


