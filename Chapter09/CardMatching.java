// JAVA 프로그래밍 - https://codereading101.github.io/JAVA/
// 소스파일 - https://github.com/CodeReading101/JAVA/blob/main/Chapter09/CardMatching.java

import java.util.Scanner;
public class CardMatching
{
	public static void main( String[] args ) {
		Scanner scan = new Scanner( System.in );
		int[][] output = { { 11, 12, 13, 14 },
		                   { 21, 22, 23, 24 },
		                   { 31, 32, 33, 34 },
		                   { 41, 42, 43, 44 } };
		int[][] color = { { 0, 1, 2, 7 },
		                  { 3, 5, 6, 0 },
		                  { 2, 4, 6, 3 },
		                  { 4, 5, 1, 7 } };
		int card1 = -1, card2 = -1, card1Row = -1, card1Column = -1, card2Row = -1, card2Column = -1;
		do {
			// 먼저 같은 색 카드 두 장을 선택하면 카드번호 대신 카드색 설정
			if( ( card1 != card2 ) && ( color[card1Row][card1Column] == color[card2Row][card2Column] ) ) {
				output[card1Row][card1Column] = color[card1Row][card1Column];
				output[card2Row][card2Column] = color[card2Row][card2Column];
			}
			// 카드 게임 현황 출력
			for( int row = 0; row < output.length; row++ ) {
				for( int column = 0; column < output[0].length; column ++ ) {
					// 사용자가 선택한 카드이거나 이미 짝을 맞춘 카드는 색깔 출력
					if( ( output[row][column] <= 7 ) || ( output[row][column] == card1 ) || ( output[row][column] == card2 ) ) {
						System.out.print( "\033[4" + color[row][column] + "m" );
						System.out.print( "\033[" + (row*3+1) + ";" + (column*4+1) + "f    " );
						System.out.print( "\033[" + (row*3+2) + ";" + (column*4+1) + "f    " );
						System.out.print( "\033[" + (row*3+3) + ";" + (column*4+1) + "f    " );
						System.out.print( "\033[0m" );
					}
					// 아니면 11 - 44의 카드번호를 출력
					else {
						System.out.print( "\033[" + (row*3+1) + ";" + (column*4+1) + "f    " );
						System.out.print( "\033[" + (row*3+2) + ";" + (column*4+1) + "f " + (row+1) + (column+1) + " " );
						System.out.print( "\033[" + (row*3+3) + ";" + (column*4+1) + "f    " );
					}
				}
			}
			// 카드 두 장을 새로 선택
			System.out.print( "\033[15;1f\033[2K첫번째 카드를 선택하세요: " );
			card1 = scan.nextInt();
			card1Row = card1 / 10 - 1;
			card1Column = card1 % 10 - 1;
			System.out.print( "\033[16;1f\033[2K두번째 카드를 선택하세요: " );
			card2 = scan.nextInt();
			card2Row = card2 / 10 - 1;
			card2Column = card2 % 10 - 1;
		// 새로 선택한 카드가 11 - 44 사이면 게임 계속 진행
		} while( ( 0 <= card1Row ) && ( card1Row < 4 ) && ( 0 <= card1Column ) && ( card1Column < 4 )
		      && ( 0 <= card2Row ) && ( card2Row < 4 ) && ( 0 <= card2Column ) && ( card2Column < 4 ) );
		scan.close();
	}
}

