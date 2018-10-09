package dynamic_beat_3;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class DynamicBeat extends JFrame{
	
	//더블 버퍼링에 대해 전체 화면에 담는 인스턴트
	private Image screenImage;
	private Graphics screenGraphic;
	
	//배경을 담을 객체
	private Image introBackground;
	
	public DynamicBeat() {
		setTitle("Dynamic Beat");
		setSize(Main.SCREEN_WIDTH,Main.SCREEN_HEIGHT); //전체 게임창 크기지정
		setResizable(false); //게임창을 사용자가 임의적으로 줄일 수 없음
		setLocationRelativeTo(null); //실행 시 컴퓨터 정중앙에 뜸
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //게임을 종료하면 프로그램 종료
		setVisible(true); //게임창을 눈에 보이게 함
	
		introBackground = new ImageIcon(Main.class.getResource("../images/introBackground.jpg")).getImage();	
	
		Music introMusic = new Music("iu-night.mp3",true); //true이기 때문에 종료시키기 전까지 무한 반복
		introMusic.start();
	}
	
	//paint는 JFrame을 상속받은 가장 첫번째로 화면을 그려주는 함수
	//프로그램이 종료 할때 까지 반복하여 보여줌
	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		screenDraw(screenGraphic);
		g.drawImage(screenImage, 0, 0, null); // 게임 창에 이미지 불러옴
	}
	public void screenDraw(Graphics g) {
		g.drawImage(introBackground, 0, 0, null);
		this.repaint();
	}
}
