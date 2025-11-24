// JAVA 프로그래밍 - https://codereading101.github.io/JAVA/
// 소스파일 - https://github.com/CodeReading101/JAVA/blob/main/src/move/TopViewObject.java

package move;

import java.awt.*;
import javax.swing.*;

public class TopViewObject extends ObjectByKey
{
        // ------------------------------
        // 맵과 이미지 관련 필드
        // ------------------------------
        protected int[][] map;          // 현재 플레이 중인 맵 (타일 번호로 구성)
        protected Image[] image;        // 기본 이미지(길, 벽, 캐릭터, 단서)

        // 기본 타입
        protected final int PATH = 0;
        protected final int WALL = 1;
        protected final int CHARACTER = 2;
        protected final int CLUE = 3;

        // 추가 벽/단서 스프라이트 타입 확장
        // 추가로 등록할 수 있는 벽/단서 스프라이트 저장소 (인덱스 0~9 사용)
        protected Image[] wallSprites = new Image[10];
        protected Image[] clueSprites = new Image[10];

        // 단서 좌표별 대사 저장소 (맵 좌표 → 대사 배열)
        private final java.util.Map<java.awt.Point, String[]> clueDialogues = new java.util.HashMap<>();
        private final java.util.Map<java.awt.Point, String[]> baseClueDialogues = new java.util.HashMap<>();
        private final java.util.Deque<String> activeDialogue = new java.util.ArrayDeque<>();

        // 초기 상태를 기억해 두었다가 날짜가 바뀔 때 복구하기 위해 사용
        private final int[][] initialMap;

        // 시작 위치 (매일 리셋할 때 돌아갈 자리)
        private final int startX;
        private final int startY;

        private boolean dialogueReady = false;
        private boolean skipNextAdvance = false;

        // 행동력/일차 관리 (초보자가 게임 루프를 이해하기 쉽게 따로 묶음)
        private int actionPoints = 3;
        private final int maxActionPoints = 3;

        private int currentDay = 1;
        private final int maxDay = 4;

        // 다음 날로 넘어갈지 여부 및 화면에 띄울 안내 문구
        private boolean pendingNextDay = false;
        private int dayTransitionTicks = 0;
        private String dayAnnouncement = null;

        // 용의자 정보 보기/범인 지목 진행에 필요한 상태 변수
        private boolean suspectInfoPromptPending = false;
        private boolean awaitingSuspectInfoChoice = false;

        private final String[] suspects = { "123", "456", "789", "엄준식" };
        private final int culpritIndex = 1;   // 정답은 2번째(인덱스 1)

        // 최종 진술용 초상화(1280x960 이미지 권장)
        private final Image[] suspectPortraits = new Image[suspects.length];

        // 마지막 날에 출력될 최종 진술 스크립트와 진행 상태
        private String[][] finalStatementScripts;
        private boolean finalStatementActive = false;
        private int finalStatementIndex = -1;
        private int activePortraitIndex = -1;

        private boolean awaitingAccusation = false;
        private boolean accusationResolved = false;
        private String accusationMessage = null;

        // 결말 이벤트 진행 여부
        private boolean finalStatementsPresented = false;

        private boolean initialDialogueStarted = false;


        public TopViewObject(int[][] map, int x, int y, final String imagePath) {

                super(imagePath+"character.png", x, y, 0, 0, map[0].length-1, map.length-1);

                // 1) 맵 복사: 원본을 훼손하지 않고, 나중에 리셋할 수 있도록 두 벌을 만든다.
                this.map = new int[map.length][map[0].length];
                this.initialMap = new int[map.length][map[0].length];

                for (int row = 0; row < map.length; row++) {
                        System.arraycopy(map[row], 0, this.map[row], 0, map[row].length);
                        System.arraycopy(map[row], 0, this.initialMap[row], 0, map[row].length);
                }

                // 2) 시작 위치 기억 (리셋 시 필요)
                this.startX = x;
                this.startY = y;

                // 3) 기본 타일 이미지 로드
                this.image = new Image[4];
                this.image[PATH     ] = new ImageIcon(imagePath + "path.png").getImage();
                this.image[WALL     ] = new ImageIcon(imagePath + "wall.png").getImage();
                this.image[CHARACTER] = new ImageIcon(imagePath + "character.png").getImage();
                this.image[CLUE     ] = new ImageIcon(imagePath + "clue.png").getImage();

                // 기본 벽/단서 스프라이트도 하나 등록
                wallSprites[0] = image[WALL];
                clueSprites[0] = image[CLUE];

                // 4) 추가 스프라이트 샘플 등록 (인덱스 1 사용)
                //    필요한 이미지를 경로만 바꿔서 덮어쓰면 됨
                setWallSprite(1, "img/bullet.png");
                setClueSprite(1, "img/paper.png");

                // 4-1) 마지막 날에 쓸 기본 최종 진술 스크립트 생성
                buildDefaultFinalStatementScripts();

                // 5) 시작 대사 (게임 시작 후 바로 출력)
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

        // 맵의 특정 좌표에 단서(대사)를 등록하고, 타일을 CLUE 로 바꾼다.
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

        public boolean isAwaitingSuspectInfoChoice() { return awaitingSuspectInfoChoice; }

        public boolean hasAccusationResult() {
                return accusationResolved && (accusationMessage != null);
        }
        public String getAccusationMessage() { return accusationMessage; }


        public void advanceDialogue(boolean bypassGuard) {
                // 대사를 한 줄 넘긴다. bypassGuard 가 true 면 첫 줄 보호를 무시한다.
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
                        handleDialogueFinished();
                } else {
                        skipNextAdvance = true;
                }
        }

        public void markDialogueReady() {
                // paint()에서 대사를 그린 후 호출 → 이제 키 입력을 받아도 안전함을 표시
                if (!activeDialogue.isEmpty())
                        dialogueReady = true;
        }

        private void handleDialogueFinished() {
                // 대사가 모두 소진되었을 때 후속 동작 처리
                setFrozen(false);

                if (finalStatementActive) {
                        // 다음 인물의 최종 진술을 이어서 보여줌
                        advanceFinalStatement();
                        return;
                }

                maybeAdvanceDay();
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
                if (!finalStatementActive)
                        maybeAdvanceDay();
        }


        private void consumeAction() {
                // 단서를 습득하거나 특정 이벤트 발생 시 행동력 감소
                if (actionPoints > 0) {
                        actionPoints--;
                        if (actionPoints <= 0)
                                pendingNextDay = true;
                }
        }

        private void maybeAdvanceDay() {
                // 대사 출력이 모두 끝났다면 다음 날로 넘어갈 준비
                if (pendingNextDay && activeDialogue.isEmpty())
                        advanceDay();
        }


        private void advanceDay() {
                pendingNextDay = false;

                if (currentDay >= maxDay) {
                        if (!finalStatementsPresented)
                                startFinalStatements();
                        else
                                startAccusation();
                        return;
                }

                currentDay++;
                actionPoints = maxActionPoints;
                resetWorld();

                dayTransitionTicks = 20;
                dayAnnouncement = "-" + currentDay + "일차-";
                suspectInfoPromptPending = true;
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
                // 매 프레임 호출: 날짜 전환 애니메이션 타이머와 용의자 정보 팝업 관리
                if (dayTransitionTicks > 0) {
                        dayTransitionTicks--;
                        if (dayTransitionTicks == 0)
                                dayAnnouncement = null;
                }

                if (dayTransitionTicks == 0 && suspectInfoPromptPending && !hasActiveDialogue()
                                && !awaitingSuspectInfoChoice) {
                        openSuspectInfoPrompt();
                }
        }


        private void startFinalStatements() {
                // 마지막 날 이전에 모든 용의자의 최종 진술을 한 번씩 보여준다.
                finalStatementsPresented = true;
                finalStatementActive = true;
                finalStatementIndex = 0;
                activePortraitIndex = 0;
                suspectInfoPromptPending = false;
                awaitingSuspectInfoChoice = false;
                dayTransitionTicks = 0;
                dayAnnouncement = null;

                beginCurrentFinalStatement();
        }

        private void beginCurrentFinalStatement() {
                // 현재 인덱스에 해당하는 인물의 초상화 + 대사를 시작
                if (finalStatementScripts == null || finalStatementScripts.length == 0) {
                        // 안전장치: 스크립트가 비어있다면 바로 범인 지목으로 이동
                        finalStatementActive = false;
                        startAccusation();
                        return;
                }

                if (finalStatementIndex < 0 || finalStatementIndex >= finalStatementScripts.length) {
                        finalStatementActive = false;
                        startAccusation();
                        return;
                }

                activePortraitIndex = finalStatementIndex;
                startDialogue(finalStatementScripts[finalStatementIndex]);
        }

        private void advanceFinalStatement() {
                finalStatementIndex++;

                if (finalStatementIndex >= finalStatementScripts.length) {
                        // 모든 진술이 끝났으니 범인 지목 창을 띄움
                        finalStatementActive = false;
                        activePortraitIndex = -1;
                        startAccusation();
                        return;
                }

                beginCurrentFinalStatement();
        }

        private void openSuspectInfoPrompt() {
                // "용의자 정보를 볼까요?" 팝업을 띄우기 전에 이동을 멈춘다.
                suspectInfoPromptPending = false;
                awaitingSuspectInfoChoice = true;
                setFrozen(true);
        }

        public void chooseSuspectInfo(boolean viewInfo) {
                // 팝업 선택 결과 처리: true 면 정보 대사를 출력, false 면 그대로 진행
                if (!awaitingSuspectInfoChoice)
                        return;

                awaitingSuspectInfoChoice = false;

                if (viewInfo)
                        startDialogue(buildSuspectInfoLines());
                else
                        setFrozen(false);
        }

        private String[] buildSuspectInfoLines() {
                java.util.List<String> info = new java.util.ArrayList<>();
                info.add("용의자 정보를 정리해보자.");
                info.add("123: 밤마다 산책을 나가. 오늘도 예외가 아니래.");
                info.add("456: 사건 시간엔 친구와 통화했다고 주장해.");
                info.add("789: 현장 근처에서 목격됐지만 우연이라더군.");
                info.add("엄준식: 여전히 자신의 결백을 강조하고 있어.");
                return info.toArray(new String[0]);
        }


        private void startAccusation() {
                awaitingAccusation = true;
                accusationResolved = false;
                accusationMessage = null;
                setFrozen(true);
                dayTransitionTicks = 0;
                dayAnnouncement = null;
                finalStatementActive = false;
                activePortraitIndex = -1;
        }


        public void chooseSuspect(int index) {
                // 범인을 선택한다. 잘못된 입력이면 무시, 맞으면 성공 메시지 출력
                if (!awaitingAccusation || accusationResolved) return;
                if (index < 0 || index >= suspects.length) return;

                awaitingAccusation = false;
                accusationResolved = true;

                boolean success = (index == culpritIndex);
                accusationMessage = success ? "범인 찾기 성공!" : "범인 찾기 실패..";
        }

        // --------------- 최종 진술 + 초상화 관련 도우미 ---------------
        public void setSuspectPortrait(int index, String imgPath) {
                // index: suspects 배열 인덱스, 1280x960 이미지를 권장
                if (index < 0 || index >= suspectPortraits.length) return;
                suspectPortraits[index] = new ImageIcon(imgPath).getImage();
        }

        public boolean isFinalStatementInProgress() {
                return finalStatementActive && activePortraitIndex >= 0;
        }

        public Image getCurrentPortrait() {
                if (!isFinalStatementInProgress()) return null;
                return suspectPortraits[activePortraitIndex];
        }

        public String getCurrentPortraitName() {
                if (!isFinalStatementInProgress()) return null;
                return suspects[activePortraitIndex];
        }

        public Dimension getPreferredPortraitSize() {
                // 기본 권장 크기 1280x960을 반환 (실제 이미지 크기가 다를 수 있음)
                return new Dimension(1280, 960);
        }

        private void buildDefaultFinalStatementScripts() {
                // suspects 배열 길이와 맞춘 기본 최종 진술 스크립트 세팅
                finalStatementScripts = new String[][]{
                        {
                                "123: ...내가 범인이라고? 난 그냥 길을 잃었을 뿐이야.",
                                "거칠게 굴었을진 몰라도, 이런 일에 손댈 사람은 아니라고."
                        },
                        {
                                "456: 증거가 없잖아. 난 내 무고를 믿어달라구.",
                                "오늘 밤 내내 친구와 통화하며 시간을 보냈어."
                        },
                        {
                                "789: 당신이 본 건 오해야. 난 아무 잘못 없어.",
                                "현장 근처에 있던 건 맞지만, 그냥 돌아다닌 것뿐이야."
                        },
                        {
                                "엄준식: 하... 이런 상황이라니. 그래도 난 결백하다.",
                                "나와 관련된 단서가 있다면, 직접 보여주길 바라."
                        },
                };
        }


        @Override
        public void move(int directionX, int directionY) {
                // 방향키로 이동 요청이 들어왔을 때의 충돌/단서 처리
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
                // 1) 맵 타일을 먼저 그림 → 2) 캐릭터와 단서/벽 순서대로 출력
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


