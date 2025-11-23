// JAVA 프로그래밍 - https://codereading101.github.io/JAVA/
// 소스파일 - https://github.com/CodeReading101/JAVA/blob/main/src/move/TopViewObject.java

package move;

import java.awt.*;
import javax.swing.*;

public class TopViewObject extends ObjectByKey
{
        protected int[][] map;
        protected Image[] image;

        // 기본 타입
        protected final int PATH = 0;
        protected final int WALL = 1;
        protected final int CHARACTER = 2;
        protected final int CLUE = 3;

        // 추가 벽/단서 스프라이트 타입 확장
        protected Image[] wallSprites = new Image[10];
        protected Image[] clueSprites = new Image[10];

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

        private final String[] suspects = { "123", "456", "789", "엄준식" };
        private final int culpritIndex = 1;

        private boolean awaitingAccusation = false;
        private boolean accusationResolved = false;
        private String accusationMessage = null;

        private boolean initialDialogueStarted = false;


        public TopViewObject(int[][] map, int x, int y, final String imagePath) {

                super(imagePath+"character.png", x, y, 0, 0, map[0].length-1, map.length-1);

                this.map = new int[map.length][map[0].length];
                this.initialMap = new int[map.length][map[0].length];

                for (int row = 0; row < map.length; row++) {
                        System.arraycopy(map[row], 0, this.map[row], 0, map[row].length);
                        System.arraycopy(map[row], 0, this.initialMap[row], 0, map[row].length);
                }

                this.startX = x;
                this.startY = y;

                this.image = new Image[4];
                this.image[PATH     ] = new ImageIcon(imagePath + "path.png").getImage();
                this.image[WALL     ] = new ImageIcon(imagePath + "wall.png").getImage();
                this.image[CHARACTER] = new ImageIcon(imagePath + "character.png").getImage();
                this.image[CLUE     ] = new ImageIcon(imagePath + "clue.png").getImage();

                // 기본 벽/단서 스프라이트도 하나 등록
                wallSprites[0] = image[WALL];
                clueSprites[0] = image[CLUE];
                
                //스프라이트 추가 벽과 단서 당 9개씩만 가능
                setWallSprite(1, "img/bullet.png");
                setClueSprite(1, "img/paper.png");

                // 시작 대사
                startInitialDialogue(
                        "…여기가 어디지?",
                        "어딘가에서 사건의 냄새가 난다.",
                        "조사를 시작해보자."
                );
        }


        //추가: 벽 스프라이트 등록
        public void setWallSprite(int type, String imgPath) {
                if (type < 0 || type >= wallSprites.length) return;
                wallSprites[type] = new ImageIcon(imgPath).getImage();
        }

        //추가: 단서 스프라이트 등록
        public void setClueSprite(int type, String imgPath) {
                if (type < 0 || type >= clueSprites.length) return;
                clueSprites[type] = new ImageIcon(imgPath).getImage();
        }


        public void startInitialDialogue(String... lines) {
                if (initialDialogueStarted) return;
                initialDialogueStarted = true;
                startDialogue(lines);
        }

        public void registerClue(int tileX, int tileY, String... lines) {
                java.awt.Point point = new java.awt.Point(tileX, tileY);
                String[] copy = (lines == null) ? new String[0] : lines.clone();
                clueDialogues.put(new java.awt.Point(point), copy);
                baseClueDialogues.put(new java.awt.Point(point), copy.clone());
                map[tileY][tileX] = CLUE;
                initialMap[tileY][tileX] = CLUE;
        }
        public void setClueSpriteIndex(int tileX, int tileY, int spriteIndex) {
            if (tileX >= 0 && tileX < map[0].length && tileY >= 0 && tileY < map.length) {
                // 단서의 맵 값을 '20 + spriteIndex'로 설정하여 paint 로직을 타게 함
                int newTileValue = 20 + spriteIndex;
                
                // 맵 값이 3이거나 20 이상일 때만 변경을 허용 (단서 등록된 위치에만)
                int currentTile = map[tileY][tileX];
                if (currentTile == CLUE || (currentTile >= 20 && currentTile < 30)) {
                    this.map[tileY][tileX] = newTileValue;
                    this.initialMap[tileY][tileX] = newTileValue;
                }
            }
        }


        public boolean hasActiveDialogue() { return !activeDialogue.isEmpty(); }
        public String getCurrentDialogue() { return activeDialogue.peekFirst(); }

        public int getActionPoints() { return actionPoints; }
        public int getMaxActionPoints() { return maxActionPoints; }
        public int getCurrentDay() { return currentDay; }
        public int getMaxDay() { return maxDay; }

        public boolean isDayTransitionActive() {
                return dayTransitionTicks > 0 && dayAnnouncement != null;
        }
        public String getDayAnnouncement() { return dayAnnouncement; }

        public boolean isAwaitingAccusation() {
                return awaitingAccusation && !accusationResolved;
        }

        public String[] getSuspects() { return suspects.clone(); }

        public boolean hasAccusationResult() {
                return accusationResolved && (accusationMessage != null);
        }
        public String getAccusationMessage() { return accusationMessage; }


        public void advanceDialogue(boolean bypassGuard) {
                if (activeDialogue.isEmpty() || !dialogueReady)
                        return;

                if (skipNextAdvance && !bypassGuard) {
                        skipNextAdvance = false;
                        return;
                }

                skipNextAdvance = false;
                activeDialogue.removeFirst();
                dialogueReady = false;

                if (activeDialogue.isEmpty()) {
                        setFrozen(false);
                        maybeAdvanceDay();
                } else {
                        skipNextAdvance = true;
                }
        }

        public void markDialogueReady() {
                if (!activeDialogue.isEmpty())
                        dialogueReady = true;
        }



        private void startDialogue(String[] lines) {
                activeDialogue.clear();
                if (lines != null) {
                        for (String line : lines)
                                activeDialogue.addLast(line == null ? "" : line);
                }

                dialogueReady = false;
                skipNextAdvance = !activeDialogue.isEmpty();
                setFrozen(!activeDialogue.isEmpty());
                maybeAdvanceDay();
        }


        private void consumeAction() {
                if (actionPoints > 0) {
                        actionPoints--;
                        if (actionPoints <= 0)
                                pendingNextDay = true;
                }
        }

        private void maybeAdvanceDay() {
                if (pendingNextDay && activeDialogue.isEmpty())
                        advanceDay();
        }


        private void advanceDay() {
                pendingNextDay = false;

                if (currentDay >= maxDay) {
                        startAccusation();
                        return;
                }

                currentDay++;
                actionPoints = maxActionPoints;
                resetWorld();

                dayTransitionTicks = 20;
                dayAnnouncement = "-" + currentDay + "일차-";
        }


        private void resetWorld() {
                for (int row = 0; row < initialMap.length; row++)
                        System.arraycopy(initialMap[row], 0, map[row], 0, initialMap[row].length);

                clueDialogues.clear();
                for (java.util.Map.Entry<java.awt.Point, String[]> entry : baseClueDialogues.entrySet()) {
                        java.awt.Point point = entry.getKey();
                        clueDialogues.put(new java.awt.Point(point), entry.getValue().clone());
                }

                this.x = startX;
                this.y = startY;

                this.directionX = STOP;
                this.directionY = STOP;
        }


        public void tick() {
                if (dayTransitionTicks > 0) {
                        dayTransitionTicks--;
                        if (dayTransitionTicks == 0)
                                dayAnnouncement = null;
                }
        }


        private void startAccusation() {
                awaitingAccusation = true;
                accusationResolved = false;
                accusationMessage = null;
                setFrozen(true);
                dayTransitionTicks = 0;
                dayAnnouncement = null;
        }


        public void chooseSuspect(int index) {
                if (!awaitingAccusation || accusationResolved) return;
                if (index < 0 || index >= suspects.length) return;

                awaitingAccusation = false;
                accusationResolved = true;

                boolean success = (index == culpritIndex);
                accusationMessage = success ? "범인 찾기 성공!" : "범인 찾기 실패..";
        }


        @Override
        public void move(int directionX, int directionY) {
                int nextX = x + directionX;
                int nextY = y + directionY;
                
                int nextTile = map[nextY][nextX];

                boolean isSolidWall = (nextTile == WALL) || (nextTile > 10 && nextTile < 20);

                if (!isSolidWall) {

                	boolean isClueTile = (nextTile == CLUE) || (nextTile >= 20 && nextTile < 30);

                    if (isClueTile) {
                        java.awt.Point point = new java.awt.Point(nextX, nextY);
                        
                        // registerClue는 맵 좌표 (Col, Row)를 키로 사용하므로 Point를 사용하여 단서 대화를 가져옵니다.
                        String[] lines = clueDialogues.remove(point); 
                        
                        startDialogue(lines);
                        
                        // 단서를 획득했으므로 맵 타일을 길(PATH)로 바꿉니다.
                        map[nextY][nextX] = PATH; 
                        
                        consumeAction();
                    }

                    super.move(directionX, directionY);
                }
            }


        @Override
        public void paint(Graphics g) {
                for (int y = 0; y < map.length; y++) {
                        for (int x = 0; x < map[0].length; x++) {

                                int tile = map[y][x];
                                Image toDraw = null;

                                // 캐릭터 우선
                                if (this.x == x && this.y == y) {
                                        toDraw = image[CHARACTER];

                                } else if (tile == PATH) {
                                        toDraw = image[PATH];

                                } else if (tile == WALL) {
                                        toDraw = wallSprites[0];   // 기본 벽

                                } else if (tile > 10 && tile < 20) {  
                                        // 예: 11 → wallSprites[1], 12 → wallSprites[2]
                                        int idx = tile - 10;
                                        toDraw = wallSprites[idx];

                                } else if (tile == CLUE) {
                                        toDraw = clueSprites[0];

                                } else if (tile >= 20 && tile < 30) {
                                        // 예: 20 → clueSprites[0], 21 → clueSprites[1]
                                        int idx = tile - 20;
                                        toDraw = clueSprites[idx];
                                }

                                if (toDraw != null)
                                        g.drawImage(toDraw, x * IMGSIZE, y * IMGSIZE, IMGSIZE, IMGSIZE, null);
                        }
                }
        }


        @Override public int backgroundWidth(){ return IMGSIZE * ( map[0].length ); }
        @Override public int backgroundHeight(){ return IMGSIZE * ( map.length ); }
}


