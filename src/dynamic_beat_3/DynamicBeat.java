package dynamic_beat_3;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class DynamicBeat extends JFrame{
	
	//���� ���۸��� ���� ��ü ȭ�鿡 ��� �ν���Ʈ
	private Image screenImage;
	private Graphics screenGraphic;
	
	//����� ���� ��ü
	private Image introBackground;
	
	public DynamicBeat() {
		setTitle("Dynamic Beat");
		setSize(Main.SCREEN_WIDTH,Main.SCREEN_HEIGHT); //��ü ����â ũ������
		setResizable(false); //����â�� ����ڰ� ���������� ���� �� ����
		setLocationRelativeTo(null); //���� �� ��ǻ�� ���߾ӿ� ��
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //������ �����ϸ� ���α׷� ����
		setVisible(true); //����â�� ���� ���̰� ��
	
		introBackground = new ImageIcon(Main.class.getResource("../images/introBackground.jpg")).getImage();	
	
		Music introMusic = new Music("iu-night.mp3",true); //true�̱� ������ �����Ű�� ������ ���� �ݺ�
		introMusic.start();
	}
	
	//paint�� JFrame�� ��ӹ��� ���� ù��°�� ȭ���� �׷��ִ� �Լ�
	//���α׷��� ���� �Ҷ� ���� �ݺ��Ͽ� ������
	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		screenDraw(screenGraphic);
		g.drawImage(screenImage, 0, 0, null); // ���� â�� �̹��� �ҷ���
	}
	public void screenDraw(Graphics g) {
		g.drawImage(introBackground, 0, 0, null);
		this.repaint();
	}
}
