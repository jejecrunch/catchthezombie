package dynamic_beat_1;

import javax.swing.JFrame;

public class DynamicBeat extends JFrame{

	public DynamicBeat() {
		setTitle("Dynamic Beat");
		setSize(Main.SCREEN_WIDTH,Main.SCREEN_HEIGHT); //전체 게임창 크기지정
		setResizable(false); //게임창을 사용자가 임의적으로 줄일 수 없음
		setLocationRelativeTo(null); //실행 시 컴퓨터 정중앙에 뜸
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //게임을 종료하면 프로그램 종료
		setVisible(true); //게임창을 눈에 보이게 함
		
		
		
	}
}
