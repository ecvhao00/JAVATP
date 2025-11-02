// JAVA 프로그래밍 - https://codereading101.github.io/JAVA/
// 소스파일 - https://github.com/CodeReading101/JAVA/blob/main/Chapter13/PresentDropGUIMain.java

import javax.swing.*;

public class PresentDropGUIMain
{
	public static void main(String[] args)
	{
		final String background = "C:\\Users\\user\\Downloads\\JAVA-main\\Chapter13\\image\\background.jpg";

		// 틀에 판을 끼우고 실행 준비 완료
		JFrame frame = new JFrame ( "선물 배달" );
		frame.getContentPane().add( new PresentDropPanel( background ) );
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	}
}


