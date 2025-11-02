// JAVA 프로그래밍 - https://codereading101.github.io/JAVA/
// 소스파일 - https://github.com/CodeReading101/JAVA/blob/main/Chapter13/CardMatchingPanel.java

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// 카드 맞추기 보드 클래스
public class CardMatchingPanel extends JPanel {
	protected Card card1, card2;

	// 16장의 카드를 보드에 배치
	public CardMatchingPanel( int[][] color ) {
		card1 = null;
		card2 = null;
		setLayout( new GridLayout( color.length, color[0].length ) );
		setPreferredSize( new Dimension( 400, 400 ) );
		setFocusable( true );
		requestFocus();

		Card[][] card = new Card[color.length][color[0].length];
		ClickListener click = new ClickListener();
		Color[] palette = { Color.pink, Color.red, Color.orange, Color.yellow, Color.green, Color.blue, Color.cyan, Color.gray };
		for( int y=0; y < card.length; y++ ){
			for( int x=0; x < card[0].length; x++ ){
				card[y][x] = new Card( palette[ color[y][x] ] );
				card[y][x].addActionListener( click );
				add( card[y][x] );
			}
		}
	}

	// 카드 클릭
	private class ClickListener implements ActionListener {
		@Override
		public void actionPerformed( ActionEvent event ) {
			// 기존 두 장의 카드 색이 같으면 선택 유지하고, 다르면 선택 해제
			if ( ( card1 != null ) && ( card2 != null ) ) {
				if ( !card1.color().equals( card2.color() ) ) {
					card1.unselected();
					card2.unselected();
				}

				// 새로운 카드 두 장을 선택할 수 있도록 준비
				card1 = null;
				card2 = null;
			}

			// 첫번째 카드 선택
			if ( card1 == null  ) {
				card1 = (Card)event.getSource();
				card1.selected();
			}
			// 두번째 카드 선택
			else if ( card2 == null  ) {
				card2 = (Card)event.getSource();
				card2.selected();
			}
		}
	}
}


