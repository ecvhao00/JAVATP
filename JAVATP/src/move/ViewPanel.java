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
                                        if ( topView.isAwaitingSuspectInfoChoice() ) {
                                                Boolean decision = translateYesNo( event.getKeyCode() );
                                                if ( decision != null ) {
                                                        topView.chooseSuspectInfo( decision );
                                                        event.consume();
                                                        repaint();
                                                }
                                                return;
                                        }
                                        if ( topView.isAwaitingAccusation() ) {
                                                int choice = translateChoice( event.getKeyCode() );
                                                if ( choice >= 0 ) {
                                                        topView.chooseSuspect( choice );
                                                        event.consume();
                                                        repaint();
                                                }
                                                return;
                                        }
                                        if ( topView.hasActiveDialogue() ) {
                                                topView.advanceDialogue( false );
                                                event.consume();
                                                repaint();
                                        }
                                }

                                private int translateChoice( int keyCode ) {
                                        switch ( keyCode ) {
                                        case KeyEvent.VK_1:
                                        case KeyEvent.VK_NUMPAD1:
                                                return 0;
                                        case KeyEvent.VK_2:
                                        case KeyEvent.VK_NUMPAD2:
                                                return 1;
                                        case KeyEvent.VK_3:
                                        case KeyEvent.VK_NUMPAD3:
                                                return 2;
                                        case KeyEvent.VK_4:
                                        case KeyEvent.VK_NUMPAD4:
                                                return 3;
                                        default:
                                                return -1;
                                        }
                                }

                                private Boolean translateYesNo( int keyCode ) {
                                        if ( keyCode == KeyEvent.VK_1 || keyCode == KeyEvent.VK_NUMPAD1 )
                                                return Boolean.TRUE;
                                        if ( keyCode == KeyEvent.VK_2 || keyCode == KeyEvent.VK_NUMPAD2 )
                                                return Boolean.FALSE;
                                        return null;
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
                if ( character instanceof TopViewObject )
                        ( (TopViewObject) character ).tick();
        }

        // 화면 출력
        @Override
        public void paint( Graphics g ){
                super.paint( g );
                character.paint( g );
                if ( character instanceof TopViewObject ) {
                        TopViewObject topView = (TopViewObject) character;
                        Graphics2D overlay = (Graphics2D) g.create();
                        overlay.setFont( getFont().deriveFont( Font.BOLD, 20f ) );
                        overlay.setColor( new Color( 0, 0, 0, 150 ) );
                        String status = topView.getCurrentDay() + "일차  행동력 "
                                        + topView.getActionPoints() + "/" + topView.getMaxActionPoints();
                        FontMetrics fm = overlay.getFontMetrics();
                        int padding = 10;
                        int textWidth = fm.stringWidth( status );
                        int textHeight = fm.getHeight();
                        overlay.fillRoundRect( 12, 12, textWidth + padding * 2, textHeight + padding * 2, 12, 12 );
                        overlay.setColor( Color.WHITE );
                        overlay.drawString( status, 12 + padding, 12 + padding + fm.getAscent() );

                        if ( topView.isDayTransitionActive() ) {
                                String announcement = topView.getDayAnnouncement();
                                if ( announcement != null ) {
                                        overlay.setFont( overlay.getFont().deriveFont( 48f ) );
                                        FontMetrics centerMetrics = overlay.getFontMetrics();
                                        int width = getWidth();
                                        int height = getHeight();
                                        int annWidth = centerMetrics.stringWidth( announcement );
                                        int annHeight = centerMetrics.getHeight();
                                        overlay.setColor( new Color( 0, 0, 0, 180 ) );
                                        overlay.fillRoundRect( ( width - annWidth ) / 2 - 40,
                                                               ( height - annHeight ) / 2 - 30,
                                                               annWidth + 80,
                                                               annHeight + 60,
                                                               30, 30 );
                                        overlay.setColor( Color.WHITE );
                                        overlay.drawString( announcement,
                                                            ( width - annWidth ) / 2,
                                                            ( height + centerMetrics.getAscent() ) / 2 - 10 );
                                }
                        }

                        if ( topView.isAwaitingSuspectInfoChoice() ) {
                                Graphics2D prompt = (Graphics2D) g.create();
                                int width = getWidth();
                                int height = getHeight();
                                int boxWidth = Math.min( 520, width - 80 );
                                int boxHeight = 200;
                                int boxX = ( width - boxWidth ) / 2;
                                int boxY = ( height - boxHeight ) / 2;

                                prompt.setColor( new Color( 0, 0, 0, 190 ) );
                                prompt.fillRoundRect( boxX, boxY, boxWidth, boxHeight, 22, 22 );
                                prompt.setColor( Color.WHITE );
                                prompt.setStroke( new BasicStroke( 2.5f ) );
                                prompt.drawRoundRect( boxX, boxY, boxWidth, boxHeight, 22, 22 );

                                Font titleFont = getFont().deriveFont( Font.BOLD, 24f );
                                prompt.setFont( titleFont );
                                String question = "용의자의 정보를 보시겠습니까?";
                                FontMetrics titleMetrics = prompt.getFontMetrics();
                                prompt.drawString( question,
                                                   boxX + ( boxWidth - titleMetrics.stringWidth( question ) ) / 2,
                                                   boxY + 70 );

                                Font optionFont = getFont().deriveFont( Font.PLAIN, 18f );
                                prompt.setFont( optionFont );
                                String yes = "예(1)";
                                String no = "아니오(2)";
                                prompt.drawString( yes, boxX + 80, boxY + 120 );
                                prompt.drawString( no, boxX + boxWidth - 80 - prompt.getFontMetrics().stringWidth( no ), boxY + 120 );

                                prompt.dispose();
                        }
                        overlay.dispose();

                        if ( topView.isAwaitingAccusation() ) {
                                // 범인 지목 안내창
                                Graphics2D accuse = (Graphics2D) g.create();
                                accuse.setColor( new Color( 0, 0, 0, 190 ) );
                                int width = getWidth();
                                int height = getHeight();
                                int boxWidth = Math.min( 520, width - 80 );
                                int boxHeight = 260;
                                int boxX = ( width - boxWidth ) / 2;
                                int boxY = ( height - boxHeight ) / 2;
                                accuse.fillRoundRect( boxX, boxY, boxWidth, boxHeight, 24, 24 );
                                accuse.setColor( Color.WHITE );
                                accuse.setStroke( new BasicStroke( 3f ) );
                                accuse.drawRoundRect( boxX, boxY, boxWidth, boxHeight, 24, 24 );

                                Font titleFont = getFont().deriveFont( Font.BOLD, 26f );
                                accuse.setFont( titleFont );
                                FontMetrics titleMetrics = accuse.getFontMetrics();
                                String title = "용의자를 지목하세요";
                                accuse.drawString( title,
                                                   boxX + ( boxWidth - titleMetrics.stringWidth( title ) ) / 2,
                                                   boxY + 50 );

                                Font listFont = getFont().deriveFont( Font.PLAIN, 20f );
                                accuse.setFont( listFont );
                                FontMetrics listMetrics = accuse.getFontMetrics();
                                int listY = boxY + 90;
                                String[] suspects = topView.getSuspects();
                                for ( int i = 0; i < suspects.length; i++ ) {
                                        String line = ( i + 1 ) + ". " + suspects[i];
                                        accuse.drawString( line,
                                                           boxX + 40,
                                                           listY + i * ( listMetrics.getHeight() + 4 ) );
                                }

                                Font hintFont = getFont().deriveFont( Font.PLAIN, 14f );
                                accuse.setFont( hintFont );
                                String hint = "1~4 키를 눌러 범인을 선택하세요.";
                                accuse.drawString( hint,
                                                   boxX + ( boxWidth - accuse.getFontMetrics().stringWidth( hint ) ) / 2,
                                                   boxY + boxHeight - 30 );
                                accuse.dispose();
                        }

                        if ( topView.hasAccusationResult() ) {
                                // 범인 지목 결과 출력
                                Graphics2D result = (Graphics2D) g.create();
                                result.setFont( getFont().deriveFont( Font.BOLD, 56f ) );
                                String message = topView.getAccusationMessage();
                                FontMetrics resultMetrics = result.getFontMetrics();
                                int width = getWidth();
                                int height = getHeight();
                                result.setColor( new Color( 0, 0, 0, 200 ) );
                                result.fillRoundRect( ( width - 600 ) / 2,
                                                      ( height - 180 ) / 2,
                                                      600,
                                                      180,
                                                      30, 30 );
                                result.setColor( Color.WHITE );
                                result.drawString( message,
                                                   ( width - resultMetrics.stringWidth( message ) ) / 2,
                                                   ( height + resultMetrics.getAscent() ) / 2 );
                                result.dispose();
                        }

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

