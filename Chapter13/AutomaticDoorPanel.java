// JAVA 프로그래밍 - https://codereading101.github.io/JAVA/
// 소스파일 - https://github.com/CodeReading101/JAVA/blob/main/Chapter13/AutomaticDoorPanel.java

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
// 자동문 클래스
public class AutomaticDoorPanel extends JPanel
{
	private JPanel door;
	private JButton button;
	private boolean open;
	// 자동문 초기화
	public AutomaticDoorPanel() {
		// 먼저, 자동문은 닫힌 상태로 초기화
		door = this;
		open = false;
		setBackground( Color.cyan );
		setPreferredSize( new Dimension( 250, 400 ) );
		// 클릭하면 반응하는 버튼을 자동문에 부착
		button = new JButton( "문열기" );
		button.addActionListener( new ClickListener() );
		add( button );
	}
	// 버튼 클릭시 반응
	private class ClickListener implements ActionListener {
		@Override
		public void actionPerformed( ActionEvent event ) {
			// 자동문이 닫혀 있으면 열기
			if ( open == false ) {
				open = true;
				door.setBackground( Color.white );
				button.setText( "문닫기" );
			}
			// 자동문이 열려 있으면 닫기
			else {
				open = false;
				door.setBackground( Color.cyan );
				button.setText( "문열기" );
			}
		}
	}
}

