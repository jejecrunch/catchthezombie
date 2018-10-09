package dynamic_beat_4;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class Music extends Thread{

	private Player player;
	private boolean isLoop; //무한반복인지 한 번 재생인지
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

	//음악이 현재 어느 위치에 실행 되고 있는지 알려주는 메소드 getTime
	public int getTime() {
		if (player ==null)
			return 0;
		return player.getPosition();
	}

	//음악이 언제 실행하고 있던 관에 항상 종료 할 수 있는 함수
	public void close() {
		isLoop = false;
		player.close();
		this.interrupt(); //해당 스레드를 중지 상태로 만듬
	}

	@Override
	public void run() {
		try {
			do {
				player.play();
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				player = new Player(bis);
			}while(isLoop); //isLoop가 true 값일 경우 해당 곡은 무한 루프
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
