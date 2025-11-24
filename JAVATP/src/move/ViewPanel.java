// JAVA 프로그래밍 - https://codereading101.github.io/JAVA/
// 소스파일 - https://github.com/CodeReading101/JAVA/blob/main/src/move/ViewPanel.java

package move;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// 뷰 패널
public class ViewPanel extends JPanel
{
        // 캐릭터 객체(키 입력도 받고, 그리기도 담당)
        protected ObjectByKey character;
        // 0.1초마다 호출되어 화면을 갱신해 주는 Swing Timer
        protected Timer timer;
        // 옵션: 깔아둘 배경 이미지 (없으면 null)
        private Image backgroundImage;

        // 배경 이미지를 사용하지 않을 때 호출하는 생성자
        public ViewPanel( ObjectByKey character ){
                this( character, null );
        }

        // 배경 이미지 경로까지 받을 수 있는 생성자
        public ViewPanel( ObjectByKey character, String backgroundImagePath ){
                // ------------------------------
                // 1) 캐릭터 보관 및 키 입력 등록
                //    - character 는 ObjectByKey 를 상속한 모든 객체를 받을 수 있음
                //    - addKeyListener 로 방향키 입력을 캐릭터에게 전달
                // ------------------------------
                this.character = character;
                addKeyListener( character );

                // ------------------------------
                // 2) 배경 이미지가 지정되면 미리 로드해 둠
                // ------------------------------
                if ( backgroundImagePath != null && backgroundImagePath.isEmpty() == false )
                        this.backgroundImage = new ImageIcon( backgroundImagePath ).getImage();

                // ------------------------------
                // 3) TopViewObject 전용 입력 처리
                //    - 숫자키로 선택지 입력 (용의자 정보, 범인 지목 등)
                //    - 대사 진행을 키/마우스로 넘길 수 있도록 리스너 추가
                // ------------------------------
                if ( character instanceof TopViewObject ) {
                        final TopViewObject topView = (TopViewObject) character;
                        addKeyListener( new KeyAdapter() {
                                @Override
                                public void keyPressed( KeyEvent event ) {
                                        // 용의자 정보 보기 여부(1/2) 대기 중이면 먼저 처리
                                        if ( topView.isAwaitingSuspectInfoChoice() ) {
                                                Boolean decision = translateYesNo( event.getKeyCode() );
                                                if ( decision != null ) {
                                                        topView.chooseSuspectInfo( decision );
                                                        event.consume();
                                                        repaint();
                                                }
                                                return; // 다른 입력은 무시
                                        }
                                        // 범인 지목(1~4) 입력 대기 중이면 처리
                                        if ( topView.isAwaitingAccusation() ) {
                                                int choice = translateChoice( event.getKeyCode() );
                                                if ( choice >= 0 ) {
                                                        topView.chooseSuspect( choice );
                                                        event.consume();
                                                        repaint();
                                                }
                                                return;
                                        }
                                        // 대사가 출력 중이면 아무 키나 누르면 다음 줄로 진행
                                        if ( topView.hasActiveDialogue() ) {
                                                topView.advanceDialogue( false );
                                                event.consume();
                                                repaint();
                                        }
                                }

                                // 숫자키(1~4)를 0~3 인덱스로 변환
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

                                // 1 → 예, 2 → 아니오, 나머지는 null
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
                                        // 클릭으로도 대사를 넘길 수 있게 처리
                                        if ( topView.hasActiveDialogue() ) {
                                                topView.advanceDialogue( true );
                                                event.consume();
                                                requestFocusInWindow();
                                                repaint();
                                        }
                                }
                        } );
                }

                // ------------------------------
                // 4) 패널 기본 설정
                //    - setFocusable 로 키 입력 가능하게 설정
                //    - preferred size 는 캐릭터가 정의한 배경 크기를 그대로 사용
                // ------------------------------
                setFocusable( true );
                requestFocus();
                setPreferredSize( new Dimension( character.backgroundWidth(), character.backgroundHeight() ) );

                // ------------------------------
                // 5) 0.1초마다 update() 와 repaint()를 호출하는 타이머 설정
                // ------------------------------
                timer = new Timer( 100, new ActionListener() {
                                        @Override
                                        public void actionPerformed( ActionEvent event ) {
                                                update();
                                                repaint();
                                        }
                                });
                timer.start();
        }

        // 최신 정보 업데이트: 캐릭터 이동 + TopViewObject 의 추가 로직
        protected void update() {
                character.move();
                if ( character instanceof TopViewObject )
                        ( (TopViewObject) character ).tick();
        }

        // 화면 출력
        @Override
        public void paint( Graphics g ){
                super.paint( g );

                // 1) 배경 이미지가 있다면 먼저 깔기
                if ( backgroundImage != null )
                        g.drawImage( backgroundImage, 0, 0, getWidth(), getHeight(), null );

                // 2) 캐릭터(맵 포함)를 그림
                character.paint( g );
                if ( character instanceof TopViewObject ) {
                        TopViewObject topView = (TopViewObject) character;

                        // ------------------------------------------
                        // 마지막 날: 최종 진술 초상화(1280x960 권장)를 크게 띄워준다
                        // ------------------------------------------
                        if ( topView.isFinalStatementInProgress() ) {
                                Graphics2D portraitLayer = (Graphics2D) g.create();
                                int width = getWidth();
                                int height = getHeight();

                                // 화면을 살짝 어둡게 만든 뒤 중앙에 초상화를 띄운다.
                                portraitLayer.setColor( new Color( 0, 0, 0, 140 ) );
                                portraitLayer.fillRect( 0, 0, width, height );

                                Image portrait = topView.getCurrentPortrait();
                                Dimension preferred = topView.getPreferredPortraitSize();

                                int baseW = ( portrait != null && portrait.getWidth( null ) > 0 ) ? portrait.getWidth( null ) : preferred.width;
                                int baseH = ( portrait != null && portrait.getHeight( null ) > 0 ) ? portrait.getHeight( null ) : preferred.height;

                                float scale = Math.min( (float) width / baseW, (float) height / baseH );
                                int drawW = Math.round( baseW * scale );
                                int drawH = Math.round( baseH * scale );
                                int drawX = ( width - drawW ) / 2;
                                int drawY = ( height - drawH ) / 2;

                                if ( portrait != null ) {
                                        portraitLayer.drawImage( portrait, drawX, drawY, drawW, drawH, null );
                                } else {
                                        // 초상화가 없으면 빈 배경만 표시 (버그 예방)
                                        portraitLayer.setColor( new Color( 30, 30, 30, 220 ) );
                                        portraitLayer.fillRoundRect( drawX, drawY, drawW, drawH, 30, 30 );
                                }

                                // 이름 라벨(상단) 표시
                                String name = topView.getCurrentPortraitName();
                                if ( name != null ) {
                                        portraitLayer.setFont( getFont().deriveFont( Font.BOLD, 28f ) );
                                        FontMetrics nameMetrics = portraitLayer.getFontMetrics();
                                        int labelPadding = 16;
                                        int labelWidth = nameMetrics.stringWidth( name ) + labelPadding * 2;
                                        int labelHeight = nameMetrics.getHeight() + labelPadding;
                                        int labelX = drawX + Math.max( 24, ( drawW - labelWidth ) / 2 );
                                        int labelY = drawY + 24;

                                        portraitLayer.setColor( new Color( 0, 0, 0, 190 ) );
                                        portraitLayer.fillRoundRect( labelX, labelY, labelWidth, labelHeight, 20, 20 );
                                        portraitLayer.setColor( Color.WHITE );
                                        portraitLayer.drawString( name, labelX + labelPadding, labelY + labelHeight - labelPadding / 2 );
                                }

                                portraitLayer.dispose();
                        }

                        // ------------------------------------------
                        // 상태(일차, 행동력) 오버레이 표시
                        // ------------------------------------------
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

                        // ------------------------------------------
                        // 일차 전환 연출 ("-2일차-"처럼 중앙에 표시)
                        // ------------------------------------------
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

                        // ------------------------------------------
                        // "용의자의 정보를 보시겠습니까?" 팝업 표시
                        // ------------------------------------------
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

                        // ------------------------------------------
                        // 범인 지목 창 (1~4 선택)
                        // ------------------------------------------
                        if ( topView.isAwaitingAccusation() ) {
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

                        // ------------------------------------------
                        // 범인 지목 결과 표시 (성공/실패 메시지)
                        // ------------------------------------------
                        if ( topView.hasAccusationResult() ) {
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

                        // ------------------------------------------
                        // 대화 상자 (NPC 대사 등)
                        // ------------------------------------------
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
