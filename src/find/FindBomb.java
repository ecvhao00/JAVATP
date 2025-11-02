// JAVA 프로그래밍 - https://codereading101.github.io/JAVA/
// 소스파일 - https://github.com/CodeReading101/JAVA/blob/main/src/find/FindBomb.java

package find;
// 폭탄 피하기 클래스
public class FindBomb extends FindBall
{
	// 폭탄 피하기 초기화 : 공 찾기 대신 폭탄 피하기에 맞게 메시지 설정
	public FindBomb() {
		super();
		super.message = "1, 2, 3 중에서 폭탄을 숨긴 상자를 찾으세요: ";
		super.meet   = " 펑!! \n";
		super.dodge  = " 휴우!\n";
	}
}

