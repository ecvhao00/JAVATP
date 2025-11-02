// JAVA 프로그래밍 - https://codereading101.github.io/JAVA/
// 소스파일 - https://github.com/CodeReading101/JAVA/blob/main/Chapter13/QuestionPanel.java

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
// 질문 패널 클래스
public class QuestionPanel extends JPanel
{
	private JRadioButton option1, option2;
	private String nextOption2;
	private Container frame;
	private CardLayout cards;
	// 질문 패널 초기화
	public QuestionPanel( Container frame, CardLayout cards, String[] question ) {
		// 질문 및 선택항목 제시 / 선택시 반응할 리스너 등록
		setLayout( new GridLayout( 3, 1 ) );
		JLabel label = new JLabel( question[1] );
		label.setFont( new Font( "굴림", Font.BOLD, 17 ) );
		add( label );
		// 선택항목1 제시
		ClickListener click = new ClickListener();
		if( ( question.length > 2 ) && ( question[2] != null ) && !question[2].equals("") ) {
			option1 = new JRadioButton( question[2] );
			option1.setFont( new Font( "굴림", Font.BOLD, 17 ) );
			option1.addActionListener( click );
			add( option1 );
		}
		// 선택항목2 제시
		if( ( question.length > 4 ) && ( question[3] != null ) && !question[3].equals("") ) {
			option2 = new JRadioButton( question[3] );
			option2.setFont( new Font( "굴림", Font.BOLD, 17 ) );
			option2.addActionListener( click );
			add( option2 );
			nextOption2 = question[4];
		}
		// 다음 질문으로 이동할 수 있도록 준비
		this.frame = frame;
		this.cards= cards;
	}
	// 항목 선택시 다음 질문으로 이동
	private class ClickListener implements ActionListener {
		@Override
		public void actionPerformed( ActionEvent event ) {
			if ( ( event.getSource() == option1 ) && ( option1.getText().equals( "처음으로" ) ) ) {
				option1.setSelected( false );
				cards.first( frame );
			}
			else if ( ( event.getSource() == option1 ) && ( option1.getText().equals( "이전으로" ) ) ) {
				option1.setSelected( false );
				cards.previous( frame );
			}
			else if ( event.getSource() == option1 ) {
				option1.setSelected( false );
				cards.next( frame );
			}
			else if ( event.getSource() == option2 ) {
				option2.setSelected( false );
				cards.show( frame, nextOption2 );
			}
		}
	}
}

