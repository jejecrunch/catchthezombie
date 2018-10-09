package dynamic_beat_4;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DynamicBeat extends JFrame {

	// ���� ���۸��� ���� ��ü ȭ�鿡 ��� �ν���Ʈ
	private Image screenImage;
	private Graphics screenGraphic;

	// ����� ���� ��ü

	private ImageIcon exitButtonEnterImage = new ImageIcon((Main.class.getResource("../images/exitButtonEntered.png")));
	private ImageIcon exitButtonBasicImage = new ImageIcon((Main.class.getResource("../images/exitButtonBasic.png")));

	private Image introBackground = new ImageIcon(Main.class.getResource("../images/introBackground.jpg")).getImage();
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/munuBar.png"))); //memu�ε� ��Ÿ�� ��

	private JButton exitButton = new JButton(exitButtonBasicImage);

	private int mouseX, mouseY; //���콺�� X, Y��ǥ ��

	public DynamicBeat() {
		setUndecorated(true);
		setTitle("Dynamic Beat");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT); // ��ü ����â ũ������
		setResizable(false); // ����â�� ����ڰ� ���������� ���� �� ����
		setLocationRelativeTo(null); // ���� �� ��ǻ�� ���߾ӿ� ��
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ������ �����ϸ� ���α׷� ����
		setVisible(true); // ����â�� ���� ���̰� ��
		setBackground(new Color(0, 0, 0, 0)); // ����� �Ͼ������ �ٲ�
		setLayout(null); // ��ư�̳� JLabel�� �־��� �� �� �ڸ���ġ�� ��ġ��(?)
		
		exitButton.setBounds(1245, 0, 30, 30);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter(){
			//���콺�� �ش� ��ư�� �ö���� �� �̺�Ʈ ó��
			@Override
			public void mouseEntered(MouseEvent e) {
				exitButton.setIcon(exitButtonEnterImage); //�׸��� �ٲ� (basic->entered)
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));//���콺�� �ö��� �� �հ��� ������� �ٲ�
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3",false);//���콺�� �ö��� �� ��ư �Ҹ�
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(exitButtonBasicImage); //���콺�� ��ư���� �������� �� �׸� �ٲ�
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) { //���콺�� exit��ư�� Ŭ������ �� ����
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3",false);
				buttonEnteredMusic.start();
				try {
					Thread.sleep(1000);
				}catch(InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0);
			}
		});
		
		add(exitButton);


		menuBar.setBounds(0, 0, 1280, 30);
		menuBar.addMouseListener(new MouseAdapter() {

			//���콺�� �ش� ��ư�� ������ �� �̺�Ʈ ó��
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});

		//menuBar�� �巡�� ���� �� �߻��ϴ� �̺�Ʈ ó��
		menuBar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen(); 
				int y = e.getYOnScreen();
				setLocation(x - mouseX, y - mouseY); //JFram�� ��ġ�� �ٲ���=����â�� ��ġ�� �ٲ�=�޴��ٸ� ��� ������ġ ����
			}
		});
		add(menuBar);

		Music introMusic = new Music("iu-night.mp3", true); // true�̱� ������ �����Ű�� ������ ���� �ݺ�
		introMusic.start();
	}

	// paint�� JFrame�� ��ӹ��� ���� ù��°�� ȭ���� �׷��ִ� �Լ�
	// ���α׷��� ���� �Ҷ� ���� �ݺ��Ͽ� ������
	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		screenDraw(screenGraphic);
		g.drawImage(screenImage, 0, 0, null); // ���� â�� �̹��� �ҷ���
	}

	public void screenDraw(Graphics g) {
		g.drawImage(introBackground, 0, 0, null);
		printComponents(g); // JFrame�� �߰��ؼ� �׷��� - �׻� �����Ǵ� �̹��� �޴���, ��ư
		this.repaint();
	}
}
