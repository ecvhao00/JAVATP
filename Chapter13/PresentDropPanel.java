// JAVA 프로그래밍 - https://codereading101.github.io/JAVA/
// 소스파일 - https://github.com/CodeReading101/JAVA/blob/main/Chapter13/PresentDropPanel.java

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

// 선물 배달 패널 클래스
public class PresentDropPanel extends JPanel {
	protected ArrayList<Present> presents;
	protected Image background;
	public PresentDropPanel( String background ) {
		this.presents = new ArrayList<Present>();
		this.background = new ImageIcon( background ).getImage();
		setPreferredSize( new Dimension( 400, 400 ) );
		setFocusable( true );
		requestFocus();

		// 마우스를 클릭할 때마다 선물 생성
		this.addMouseListener( new MouseAdapter() {
			@Override
			public void mouseClicked( MouseEvent event ) {
				presents.add( new Present( event.getX(), event.getY() ) );
			}
		} );
		// 각 선물은 주기적으로 조금씩 낙하
		Timer timer = new Timer( 50, new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent event ) {
				for( Present present : presents )
					present.drop();
				repaint();
			}
		} );
		timer.start();
	}

	// 선물 및 배경의 화면 업데이트
	@Override
	public void paint( Graphics g ) {
		super.paintComponent( g );
		g.drawImage( background, 0, 0, null );
		for( Present present : presents )
			present.paint(g);
	}
}


