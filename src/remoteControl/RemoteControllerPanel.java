// JAVA 프로그래밍 - https://codereading101.github.io/JAVA/
// 소스파일 - https://github.com/CodeReading101/JAVA/blob/main/src/remoteControl/RemoteControllerPanel.java

package remoteControl;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// 리모컨 GUI 클래스
public class RemoteControllerPanel extends JPanel implements ActionListener
{
	protected JButton[] button;
	public final static int POWER = 0, UP = 1, DOWN = 2, LEFT = 3, RIGHT = 4;
	// 리모컨으로 제어할 수 있는 가전제품 : TV, 에어컨, 로봇청소기
	protected RemoteControl appliance;

	// 리모컨 GUI 초기화
	public RemoteControllerPanel( String imgPath ) {
		this.appliance = null;

		// 리모컨 버튼의 이미지 및 리스너 초기화
		final String[] strButton = { "power.gif", "up.gif", "down.gif", "left.gif", "right.gif" };
		button = new JButton[strButton.length];
		for ( int i = 0; i < strButton.length; i++ ) {
			button[i] = new JButton( new ImageIcon( new ImageIcon( imgPath + strButton[i] ).getImage().getScaledInstance( 30, 30, Image.SCALE_SMOOTH ) ) );
			button[i].addActionListener( this );
		}

		this.setPreferredSize( new Dimension( 240, 120 ) );
		this.setLayout( new BorderLayout() );
		this.add( button[POWER], BorderLayout.CENTER );
		this.add( button[UP   ], BorderLayout.NORTH );
		this.add( button[DOWN ], BorderLayout.SOUTH );
		this.add( button[LEFT ], BorderLayout.WEST );
		this.add( button[RIGHT], BorderLayout.EAST );
	}


	// 가전제품(TV, 로봇청소기, 에어컨)을 제어하는 리모컨 GUI 초기화
	public RemoteControllerPanel( String imgPath, RemoteControl appliance ) {
		this( imgPath );
		this.appliance = appliance;
	}
	// 리모컨 버튼을 클릭시 가전제품 상태 업데이트
	@Override
	public void actionPerformed( ActionEvent event ) {
		if ( ( event.getSource() == button[POWER] ) && ( appliance != null ) )
			appliance.clickPower();
		else if ( ( event.getSource() == button[UP] ) && ( appliance != null ) )
			appliance.clickUp();
		else if ( ( event.getSource() == button[DOWN] ) && ( appliance != null ) )
			appliance.clickDown();
		else if ( ( event.getSource() == button[LEFT] ) && ( appliance != null ) )
			appliance.clickLeft();
		else if ( ( event.getSource() == button[RIGHT] ) && ( appliance != null ) )
			appliance.clickRight();
	}
}

