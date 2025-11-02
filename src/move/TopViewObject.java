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
        private final java.util.Deque<String> activeDialogue = new java.util.ArrayDeque<>();
        private boolean dialogueReady = false;
        private boolean skipNextAdvance = false;

	public TopViewObject( int[][] map, int x, int y, final String imagePath ) {
		super( imagePath+"character.png", x, y, 0, 0, map[0].length-1, map.length-1 );
		this.map = map;
		this.image = new Image[4];
		this.image[PATH     ] = new ImageIcon( imagePath + "path.png" ).getImage();
		this.image[WALL     ] = new ImageIcon( imagePath + "wall.png" ).getImage();
		this.image[CHARACTER] = new ImageIcon( imagePath + "character.png" ).getImage();
		this.image[CLUE     ] = new ImageIcon( imagePath + "clue.png" ).getImage();		
	}
	
        public void registerClue( int tileX, int tileY, String... lines ) {
                java.awt.Point point = new java.awt.Point( tileX, tileY );
                String[] copy = ( lines == null ) ? new String[0] : lines.clone();
                clueDialogues.put( point, copy );
        }

        public boolean hasActiveDialogue() {
                return !activeDialogue.isEmpty();
        }

        public String getCurrentDialogue() {
                return activeDialogue.peekFirst();
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

