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

	// 더블 버퍼링에 대해 전체 화면에 담는 인스턴트
	private Image screenImage;
	private Graphics screenGraphic;

	// 배경을 담을 객체

	private ImageIcon exitButtonEnterImage = new ImageIcon((Main.class.getResource("../images/exitButtonEntered.png")));
	private ImageIcon exitButtonBasicImage = new ImageIcon((Main.class.getResource("../images/exitButtonBasic.png")));

	private Image introBackground = new ImageIcon(Main.class.getResource("../images/introBackground.jpg")).getImage();
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/munuBar.png"))); //memu인데 오타남 ㅎ

	private JButton exitButton = new JButton(exitButtonBasicImage);

	private int mouseX, mouseY; //마우스의 X, Y좌표 값

	public DynamicBeat() {
		setUndecorated(true);
		setTitle("Dynamic Beat");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT); // 전체 게임창 크기지정
		setResizable(false); // 게임창을 사용자가 임의적으로 줄일 수 없음
		setLocationRelativeTo(null); // 실행 시 컴퓨터 정중앙에 뜸
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 게임을 종료하면 프로그램 종료
		setVisible(true); // 게임창을 눈에 보이게 함
		setBackground(new Color(0, 0, 0, 0)); // 배경이 하얀색으로 바뀜
		setLayout(null); // 버튼이나 JLabel을 넣었을 때 그 자리위치에 꽁치됨(?)
		
		exitButton.setBounds(1245, 0, 30, 30);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter(){
			//마우스가 해당 버튼에 올라왔을 때 이벤트 처리
			@Override
			public void mouseEntered(MouseEvent e) {
				exitButton.setIcon(exitButtonEnterImage); //그림을 바꿈 (basic->entered)
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));//마우스가 올라갔을 때 손가락 모양으로 바뀜
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3",false);//마우스가 올라갔을 때 버튼 소리
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(exitButtonBasicImage); //마우스가 버튼에서 내려왔을 때 그림 바꿈
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) { //마우스가 exit버튼을 클릭했을 때 종료
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

			//마우스가 해당 버튼을 눌렀을 때 이벤트 처리
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});

		//menuBar를 드래그 했을 때 발생하는 이벤트 처리
		menuBar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen(); 
				int y = e.getYOnScreen();
				setLocation(x - mouseX, y - mouseY); //JFram의 위치를 바뀌줌=게임창의 위치를 바꿈=메뉴바를 잡고 게임위치 수정
			}
		});
		add(menuBar);

		Music introMusic = new Music("iu-night.mp3", true); // true이기 때문에 종료시키기 전까지 무한 반복
		introMusic.start();
	}

	// paint는 JFrame을 상속받은 가장 첫번째로 화면을 그려주는 함수
	// 프로그램이 종료 할때 까지 반복하여 보여줌
	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		screenDraw(screenGraphic);
		g.drawImage(screenImage, 0, 0, null); // 게임 창에 이미지 불러옴
	}

	public void screenDraw(Graphics g) {
		g.drawImage(introBackground, 0, 0, null);
		printComponents(g); // JFrame에 추가해서 그려줌 - 항상 고정되는 이미지 메뉴바, 버튼
		this.repaint();
	}
}
