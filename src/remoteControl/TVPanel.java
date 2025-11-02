// JAVA 프로그래밍 - https://codereading101.github.io/JAVA/
// 소스파일 - https://github.com/CodeReading101/JAVA/blob/main/src/remoteControl/TVPanel.java

package remoteControl;
import java.awt.*;
import javax.swing.*;

// 리모컨으로 제어하는 TV GUI 클래스
public class TVPanel extends JPanel implements RemoteControl
{
	private boolean power;
	private int channel, volume;
	private ImageIcon[] imgChannel, imgVolume;
	private JLabel lblChannel, lblVolume;

	// TV 화면 초기화
	public TVPanel( final String imgPath ) {
		// 채널 이미지 준비
		final String[] channelFile = { "EBS.gif", "SBS.gif", "KBS.gif", "MBC.gif", "blank.gif" };
		imgChannel = new ImageIcon[channelFile.length];
		for ( int i = 0; i < channelFile.length; i++ ) {
			imgChannel[i] = new ImageIcon( new ImageIcon( imgPath + channelFile[i] ).getImage().getScaledInstance( 250, 120, Image.SCALE_SMOOTH ) );
		}

		// 음량 이미지 준비
		final String[] volumeFile = { "volume0.gif", "volume1.gif", "volume2.gif", "volume3.gif" };
		imgVolume = new ImageIcon[volumeFile.length];
		for ( int i = 0; i < volumeFile.length; i++ ) {
			imgVolume[i] = new ImageIcon( new ImageIcon( imgPath + volumeFile[i] ).getImage().getScaledInstance( 80, 120, Image.SCALE_SMOOTH ) );
		}
		// 이미지를 바탕으로 채널 및 음량 초기화
		power = OFF;
		channel = imgChannel.length - 1;
		volume = 0;
		lblChannel = new JLabel( imgChannel[channel] );
		lblVolume =  new JLabel( imgVolume[volume] );
		add( lblChannel );
		add( lblVolume );
	}

	// 리모컨 전원 버튼을 누르면 TV 전원 상태 변경
	public void clickPower() {
		if( power == OFF ) {
			power = ON;
			channel = 0;
			volume = 1;
			lblChannel.setIcon( imgChannel[channel] );
			lblVolume.setIcon( imgVolume[volume] );
		}
		else {
			power = OFF;
			channel = imgChannel.length - 1;
			volume = 0;
			lblChannel.setIcon( imgChannel[channel] );
			lblVolume.setIcon( imgVolume[volume] );
		}
	}

	// 리모컨 상(△) 버튼을 누르면 TV 채널 번호 증가
	public void clickUp() {
		if( power == ON ) {
			channel = ( channel + 1 ) % ( imgChannel.length - 1 );
			lblChannel.setIcon( imgChannel[channel] );
		}
	}

	// 리모컨 하(▽) 버튼을 누르면 TV 채널 번호 감소
	public void clickDown() {
		if( power == ON ) {
			channel = ( channel + ( imgChannel.length - 2 ) ) % ( imgChannel.length - 1 );
			lblChannel.setIcon( imgChannel[channel] );
		}
	}

	// 리모컨 좌(◁) 버튼을 누르면 TV 음량 감소
	public void clickLeft() {
		if( power == ON ) {
			volume = ( volume + ( imgVolume.length - 1 ) ) % imgVolume.length;
			lblVolume.setIcon( imgVolume[volume] );
		}
	}

	// 리모컨 우(▷) 버튼을 누르면 TV 음량 증가
	public void clickRight() {
		if( power == ON ) {
			volume = ( volume + 1 ) % imgVolume.length;
			lblVolume.setIcon( imgVolume[volume] );
		}
	}
}

