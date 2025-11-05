// JAVA 프로그래밍 - https://codereading101.github.io/JAVA/
// 소스파일 - https://github.com/CodeReading101/JAVA/blob/main/src/move/TopViewObject.java

package move;

import java.awt.*;
import javax.swing.*;

// 탑 뷰 캐릭터
public class TopViewObject extends ObjectByKey
{
        protected int[][] map;
        protected Image[] image;
        protected final int PATH = 0, WALL = 1, CHARACTER = 2, CLUE = 3;
        private final java.util.Map<java.awt.Point, String[]> clueDialogues = new java.util.HashMap<>();
        private final java.util.Map<java.awt.Point, String[]> baseClueDialogues = new java.util.HashMap<>();
        private final java.util.Deque<String> activeDialogue = new java.util.ArrayDeque<>();
        private final int[][] initialMap;
        private final int startX;
        private final int startY;
        private boolean dialogueReady = false;
        private boolean skipNextAdvance = false;
        private int actionPoints = 3;
        private final int maxActionPoints = 3;
        private int currentDay = 1;
        private final int maxDay = 4;
        private boolean pendingNextDay = false;
        private int dayTransitionTicks = 0;
        private String dayAnnouncement = null;

        public TopViewObject( int[][] map, int x, int y, final String imagePath ) {
                super( imagePath+"character.png", x, y, 0, 0, map[0].length-1, map.length-1 );
                this.map = new int[map.length][map[0].length];
                this.initialMap = new int[map.length][map[0].length];
                for ( int row = 0; row < map.length; row++ ) {
                        System.arraycopy( map[row], 0, this.map[row], 0, map[row].length );
                        System.arraycopy( map[row], 0, this.initialMap[row], 0, map[row].length );
                }
                this.startX = x;
                this.startY = y;
                this.image = new Image[4];
                this.image[PATH     ] = new ImageIcon( imagePath + "path.png" ).getImage();
                this.image[WALL     ] = new ImageIcon( imagePath + "wall.png" ).getImage();
                this.image[CHARACTER] = new ImageIcon( imagePath + "character.png" ).getImage();
                this.image[CLUE     ] = new ImageIcon( imagePath + "clue.png" ).getImage();
        }

        public void registerClue( int tileX, int tileY, String... lines ) {
                java.awt.Point point = new java.awt.Point( tileX, tileY );
                String[] copy = ( lines == null ) ? new String[0] : lines.clone();
                clueDialogues.put( new java.awt.Point( point ), copy );
                baseClueDialogues.put( new java.awt.Point( point ), copy.clone() );
                map[tileY][tileX] = CLUE;
                initialMap[tileY][tileX] = CLUE;
        }

        public boolean hasActiveDialogue() {
                return !activeDialogue.isEmpty();
        }

        public String getCurrentDialogue() {
                return activeDialogue.peekFirst();
        }

        public int getActionPoints() {
                return actionPoints;
        }

        public int getMaxActionPoints() {
                return maxActionPoints;
        }

        public int getCurrentDay() {
                return currentDay;
        }

        public int getMaxDay() {
                return maxDay;
        }

        public boolean isDayTransitionActive() {
                return dayTransitionTicks > 0 && dayAnnouncement != null;
        }

        public String getDayAnnouncement() {
                return dayAnnouncement;
        }

        public void advanceDialogue( boolean bypassGuard ) {
                if ( activeDialogue.isEmpty() || !dialogueReady )
                        return;
                if ( skipNextAdvance && !bypassGuard ) {
                        skipNextAdvance = false;
                        return;
                }
                skipNextAdvance = false;
                activeDialogue.removeFirst();
                dialogueReady = false;
                if ( activeDialogue.isEmpty() ) {
                        setFrozen( false );
                        maybeAdvanceDay();
                } else {
                        skipNextAdvance = true;
                }
        }

        public void markDialogueReady() {
                if ( !activeDialogue.isEmpty() )
                        dialogueReady = true;
        }

        private void startDialogue( String[] lines ) {
                activeDialogue.clear();
                if ( lines != null ) {
                        for ( String line : lines )
                                activeDialogue.addLast( ( line == null ) ? "" : line );
                }
                dialogueReady = false;
                skipNextAdvance = !activeDialogue.isEmpty();
                setFrozen( !activeDialogue.isEmpty() );
                maybeAdvanceDay();
        }

        private void consumeAction() {
                if ( actionPoints > 0 ) {
                        actionPoints--;
                        if ( actionPoints <= 0 )
                                pendingNextDay = true;
                }
        }

        private void maybeAdvanceDay() {
                if ( pendingNextDay && activeDialogue.isEmpty() )
                        advanceDay();
        }

        private void advanceDay() {
                pendingNextDay = false;
                currentDay++;
                if ( currentDay > maxDay )
                        currentDay = 1;
                actionPoints = maxActionPoints;
                resetWorld();
                dayTransitionTicks = 20;
                dayAnnouncement = "-" + currentDay + "일차-";
        }

        private void resetWorld() {
                for ( int row = 0; row < initialMap.length; row++ )
                        System.arraycopy( initialMap[row], 0, map[row], 0, initialMap[row].length );
                clueDialogues.clear();
                for ( java.util.Map.Entry<java.awt.Point, String[]> entry : baseClueDialogues.entrySet() ) {
                        java.awt.Point point = entry.getKey();
                        clueDialogues.put( new java.awt.Point( point ), entry.getValue().clone() );
                }
                this.x = startX;
                this.y = startY;
                this.directionX = STOP;
                this.directionY = STOP;
        }

        public void tick() {
                if ( dayTransitionTicks > 0 ) {
                        dayTransitionTicks--;
                        if ( dayTransitionTicks == 0 )
                                dayAnnouncement = null;
                }
        }

        // 벽이 아니면 캐릭터 이동
        @Override
        public void move( int directionX, int directionY ) {
                int nextX = x + directionX;
                int nextY = y + directionY;
                if ( map[nextY][nextX] != WALL ) {
                        if ( map[nextY][nextX] == CLUE ) {
                                java.awt.Point point = new java.awt.Point( nextX, nextY );
                                String[] lines = clueDialogues.remove( point );
                                startDialogue( lines );
                                map[nextY][nextX] = PATH;
                                consumeAction();
                        }
                        super.move( directionX, directionY );
                }
        }

	// 캐릭터와 전체 맵을 출력
	@Override
	public void paint( Graphics g ){
		for( int y = 0; y <= map.length; y++ ){
			for( int x = 0; x <= map[0].length; x++ ){
				int index = WALL;
				if ( ( this.x == x ) && ( this.y == y ) )
					index = CHARACTER;
				else if ( ( minX <= x ) && ( x <= maxX ) && ( minY <= y ) && ( y <= maxY ) )
					index = map[y][x];
				g.drawImage( image[index], x*IMGSIZE, y*IMGSIZE, IMGSIZE, IMGSIZE, null );
			}
		}
	}

	@Override
	public int backgroundWidth(){
		return IMGSIZE * ( map[0].length );
	}
	@Override
	public int backgroundHeight(){
		return IMGSIZE * ( map.length );
	}
}

