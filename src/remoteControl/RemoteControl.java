// JAVA 프로그래밍 - https://codereading101.github.io/JAVA/
// 소스파일 - https://github.com/CodeReading101/JAVA/blob/main/src/remoteControl/RemoteControl.java

package remoteControl;
// 전원, 상, 하, 좌, 우 버튼이 있는 리모컨
public interface RemoteControl
{
	boolean ON = true, OFF = false;
	void clickPower();
	void clickUp();
	void clickDown();
	void clickLeft();
	void clickRight();
}

