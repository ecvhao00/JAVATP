// JAVA 프로그래밍 - https://codereading101.github.io/JAVA/
// 소스파일 - https://github.com/CodeReading101/JAVA/blob/main/src/move/ViewPanel.java

package move;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// 뷰 패널
public class ViewPanel extends JPanel
{
	protected ObjectByKey character;
	protected Timer timer;

	public ViewPanel( ObjectByKey character ){
                // 키리스너에 방향키로 이동하는 캐릭터를 등록
                this.character = character;
                addKeyListener( character );
                if ( character instanceof TopViewObject ) {
                        final TopViewObject topView = (TopViewObject) character;
                        addKeyListener( new KeyAdapter() {
                                @Override
                                public void keyPressed( KeyEvent event ) {
                                        if ( topView.hasActiveDialogue() ) {
                                                topView.advanceDialogue( false );
                                                event.consume();
                                                repaint();
                                        }
                                }
                        } );
                        addMouseListener( new MouseAdapter() {
                                @Override
                                public void mouseClicked( MouseEvent event ) {
                                        if ( topView.hasActiveDialogue() ) {
                                                topView.advanceDialogue( true );
                                                event.consume();
                                                requestFocusInWindow();
                                                repaint();
                                        }
                                }
                        } );
                }
                setFocusable( true );
                requestFocus();
                setPreferredSize( new Dimension( character.backgroundWidth(), character.backgroundHeight() ) );

		// 주기적으로 활성화되는 타이머 등록
		timer = new Timer( 100, new ActionListener() {
		                	@Override
		                	public void actionPerformed( ActionEvent event ) {
		                		update();
		                		repaint();
		                	}
		                });
		timer.start();
	}

	// 최신 정보 업데이트
	protected void update() {
		character.move();
	}

	// 화면 출력
	@Override
	public void paint( Graphics g ){
                super.paint( g );
                character.paint( g );
                if ( character instanceof TopViewObject ) {
                        TopViewObject topView = (TopViewObject) character;
                        if ( topView.hasActiveDialogue() ) {
                                Graphics2D g2 = (Graphics2D) g.create();
                                int width = getWidth();
                                int height = getHeight();
                                int margin = 20;
                                int boxHeight = Math.min( 140, height - margin * 2 );
                                int boxWidth = width - margin * 2;
                                int boxX = margin;
                                int boxY = height - boxHeight - margin;

                                g2.setColor( new Color( 0, 0, 0, 170 ) );
                                g2.fillRoundRect( boxX, boxY, boxWidth, boxHeight, 20, 20 );
                                g2.setColor( Color.WHITE );
                                g2.setStroke( new BasicStroke( 2f ) );
                                g2.drawRoundRect( boxX, boxY, boxWidth, boxHeight, 20, 20 );

                                Font baseFont = getFont();
                                Font textFont = baseFont.deriveFont( Font.BOLD, 18f );
                                g2.setFont( textFont );
                                int textX = boxX + 24;
                                int textY = boxY + 40;
                                String dialogue = topView.getCurrentDialogue();
                                if ( dialogue != null ) {
                                        for ( String line : dialogue.split( "\\n" ) ) {
                                                g2.drawString( line, textX, textY );
                                                textY += g2.getFontMetrics().getHeight();
                                        }
                                }

                                Font hintFont = baseFont.deriveFont( Font.PLAIN, 12f );
                                g2.setFont( hintFont );
                                String hint = "아무 키나 누르거나 클릭하면 계속됩니다.";
                                int hintWidth = g2.getFontMetrics().stringWidth( hint );
                                g2.drawString( hint, boxX + boxWidth - hintWidth - 24, boxY + boxHeight - 16 );
                                g2.dispose();

                                topView.markDialogueReady();
                        }
                }
        }
}

