// JAVA 프로그래밍 - https://codereading101.github.io/JAVA/
// 소스파일 - https://github.com/CodeReading101/JAVA/blob/main/Chapter13/FindKeyGameMain.java

import java.awt.*;
import javax.swing.*;

public class FindKeyGameMain
{
	public static void main(String args[]) {
		// 질문 준비 : 질문번호, 질문, 선택항목1, 선택항목2, 선택항목2 클릭시 다음 질문번호
		String[][] questions = {
				{ "1", "열쇠를 찾아라!", "시작" },
				{ "2", "<html>약속이 있어서 나가야 하는데 차 키가 없다<br>열쇠는 어디에 있을까?<br>&nbsp;</html>", "문 밖으로 나간다", "여기서 찾아본다", "4" },
				{ "3", "<html>거실에 TV와 화분이 보인다<br>열쇠는 어디에 있을까?<br>&nbsp;</html>", "이전으로", "화분 주변", "6" },
				{ "4", "<html>서재에 책장과 책상이 보인다<br>열쇠는 어디에 있을까?<br>&nbsp;</html>", "책상 위", "책장 선반", "6" },
				{ "5", "열쇠 찾기 성공!", "처음으로" },
				{ "6", "열쇠 찾기 실패!", "처음으로" },
		};

		// 프레임에 여러 개의 질문 패널을 카드처럼 쌓아서 배치하고 실행 준비 완료
		final JFrame frame = new JFrame( "열쇠찾기" );
		final CardLayout cards = new CardLayout( 20, 20 );
		frame.setLayout( cards );
		for( String[] question : questions )
			frame.add( new QuestionPanel( frame.getContentPane(), cards, question ), question[0] );
		frame.setPreferredSize( new Dimension( 400,250 ) );
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame.pack();
		frame.setVisible(true);
	}
}

