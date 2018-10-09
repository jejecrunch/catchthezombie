package dynamic_beat_4;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class Music extends Thread{

	private Player player;
	private boolean isLoop; //���ѹݺ����� �� �� �������
	private File file;
	private FileInputStream fis;
	private BufferedInputStream bis;

	public Music(String name, boolean isLoop) {
		try {
			this.isLoop = isLoop;
			file = new File(Main.class.getResource("../music/"+name).toURI());
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			player = new Player(bis);	
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	//������ ���� ��� ��ġ�� ���� �ǰ� �ִ��� �˷��ִ� �޼ҵ� getTime
	public int getTime() {
		if (player ==null)
			return 0;
		return player.getPosition();
	}

	//������ ���� �����ϰ� �ִ� ���� �׻� ���� �� �� �ִ� �Լ�
	public void close() {
		isLoop = false;
		player.close();
		this.interrupt(); //�ش� �����带 ���� ���·� ����
	}

	@Override
	public void run() {
		try {
			do {
				player.play();
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				player = new Player(bis);
			}while(isLoop); //isLoop�� true ���� ��� �ش� ���� ���� ����
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}