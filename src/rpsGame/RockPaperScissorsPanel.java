// JAVA 프로그래밍 - https://codereading101.github.io/JAVA/
// 소스파일 - https://github.com/CodeReading101/JAVA/blob/main/src/rpsGame/RockPaperScissorsPanel.java

package rpsGame;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
// 가위바위보 클래스
public class RockPaperScissorsPanel extends JPanel implements ActionListener
{
	protected final int SCISSORS = 0, ROCK = 1, PAPER = 2, QUESTIONMARK = 3;
	protected final int BASE = 0,  WIN = 1;
	protected JLabel imgPlayerA, imgPlayerB, strPlayers;
	protected JButton[] button;
	protected ImageIcon[][] image;
	protected Timer timer;
	// 가위바위보 초기화
	public RockPaperScissorsPanel( final String imgPath ) {
		// 가위바위보 3가지, 미선택 1가지, 승패 2가지를 고려하여 이미지를 준비
		image = new ImageIcon[4][2];
		image[SCISSORS    ][BASE] = new ImageIcon( new ImageIcon( imgPath + "scissors.gif"     ).getImage().getScaledInstance( 200, 200, Image.SCALE_SMOOTH ) );
		image[SCISSORS    ][WIN]  = new ImageIcon( new ImageIcon( imgPath + "scissors_win.gif" ).getImage().getScaledInstance( 200, 200, Image.SCALE_SMOOTH ) );
		image[ROCK        ][BASE] = new ImageIcon( new ImageIcon( imgPath + "rock.gif"         ).getImage().getScaledInstance( 200, 200, Image.SCALE_SMOOTH ) );
		image[ROCK        ][WIN]  = new ImageIcon( new ImageIcon( imgPath + "rock_win.gif"     ).getImage().getScaledInstance( 200, 200, Image.SCALE_SMOOTH ) );
		image[PAPER       ][BASE] = new ImageIcon( new ImageIcon( imgPath + "paper.gif"        ).getImage().getScaledInstance( 200, 200, Image.SCALE_SMOOTH ) );
		image[PAPER       ][WIN]  = new ImageIcon( new ImageIcon( imgPath + "paper_win.gif"    ).getImage().getScaledInstance( 200, 200, Image.SCALE_SMOOTH ) );
		image[QUESTIONMARK][BASE] = new ImageIcon( new ImageIcon( imgPath + "questionmark.gif" ).getImage().getScaledInstance( 200, 200, Image.SCALE_SMOOTH ) );
		image[QUESTIONMARK][WIN]  = new ImageIcon( new ImageIcon( imgPath + "questionmark.gif" ).getImage().getScaledInstance( 200, 200, Image.SCALE_SMOOTH ) );
		// 화면 위쪽에 가위바위보 이미지 초기화
		this.imgPlayerA = new JLabel( image[QUESTIONMARK][BASE] );
		this.imgPlayerB = new JLabel( image[QUESTIONMARK][BASE] );
		this.strPlayers = new JLabel( "<html><body>[너]<br>[나]</body></html>", JLabel.CENTER );
		this.add( this.imgPlayerB );
		this.add( this.strPlayers );
		this.add( this.imgPlayerA );
		// 화면 아래쪽에 가위바위보 입력 버튼 초기화
		button = new JButton[3];
		button[SCISSORS] = new JButton( new ImageIcon( image[SCISSORS][BASE].getImage().getScaledInstance( 30, 30, Image.SCALE_SMOOTH ) ) );
		button[ROCK    ] = new JButton( new ImageIcon( image[ROCK    ][BASE].getImage().getScaledInstance( 30, 30, Image.SCALE_SMOOTH ) ) );
		button[PAPER   ] = new JButton( new ImageIcon( image[PAPER   ][BASE].getImage().getScaledInstance( 30, 30, Image.SCALE_SMOOTH ) ) );
		button[SCISSORS].addActionListener( this );
		button[ROCK    ].addActionListener( this );
		button[PAPER   ].addActionListener( this );
		this.add( new JLabel("  가위 바위 보를 선택하세요  ") );
		this.add( button[SCISSORS] );
		this.add( button[ROCK] );
		this.add( button[PAPER] );
		this.setBackground( Color.cyan );
		this.setPreferredSize( new Dimension(220, 530) );
		// 가위바위보 준비
		this.timer = new Timer( 1000, new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent event ) {
				ready();
				timer.stop();
			}
		});
	}
	// 물음표 제시하고 버튼 활성화
	public void ready() {
		imgPlayerA.setIcon( image[QUESTIONMARK][BASE] );
		imgPlayerB.setIcon( image[QUESTIONMARK][BASE] );
		button[0].setEnabled( true );
		button[1].setEnabled( true );
		button[2].setEnabled( true );
	}
	// 가위바위보 내기
	@Override
	public void actionPerformed( ActionEvent event ) {
		// 승패 결과 제시
		int playerA = select( event );
		int playerB = (int)( Math.random() * 3 );
		show( playerA, playerB );
		// 다음 판은 잠시 대기
		timer.start();
	}
	// 승패 결과 제시하고 버튼 비활성화
	public void show( int playerA, int playerB ) {
		int playerA_winOrLose = BASE, playerB_winOrLose = BASE;
		if ( ( ( playerA == SCISSORS ) && ( playerB == PAPER ) )
				|| ( ( playerA == ROCK ) && ( playerB == SCISSORS ) )
				|| ( ( playerA == PAPER ) && ( playerB == ROCK ) ) ) {
			playerA_winOrLose = WIN;
		}
		else if ( ( ( playerA == PAPER ) && ( playerB == SCISSORS ) )
				|| ( ( playerA == SCISSORS ) && ( playerB == ROCK ) )
				|| ( ( playerA == ROCK ) && ( playerB == PAPER ) ) ) {
			playerB_winOrLose = WIN;
		}
		imgPlayerA.setIcon( image[ playerA ][ playerA_winOrLose ] );
		imgPlayerB.setIcon( image[ playerB ][ playerB_winOrLose ] );
		button[0].setEnabled( false );
		button[1].setEnabled( false );
		button[2].setEnabled( false );
	}
	public int select( ActionEvent event ) {
		if( event.getSource() == button[SCISSORS] )
			return SCISSORS;
		else if ( event.getSource() == button[ROCK] )
			return ROCK;
		else
			return PAPER;
	}
}

