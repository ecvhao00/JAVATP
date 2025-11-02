// JAVA 프로그래밍 - https://codereading101.github.io/JAVA/
// 소스파일 - https://github.com/CodeReading101/JAVA/blob/main/Chapter13/Card.java

import java.awt.*;
import javax.swing.*;
// 카드 클래스
public class Card extends JButton {
	private Color color;
	// 카드 초기화
	public Card( Color color ) {
		super();
		this.color = color;
		this.setBackground( Color.white );
	}
	// 카드 선택시 카드 앞면(색) 제시
	public void selected() {
		this.setEnabled( false );
		this.setBackground( this.color );
	}
	// 카드 해제시 카드 뒤면(흰색) 제시
	public void unselected() {
		this.setEnabled( true );
		this.setBackground( Color.white );
	}
	// 카드 색
	public Color color() {
		return this.color;
	}
}

