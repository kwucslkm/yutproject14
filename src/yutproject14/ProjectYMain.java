package yutproject14;

import java.util.Scanner;

//
public class ProjectYMain {
	public static void main(String[] args) {
//		YutService service = new YutService();
		YutGame yutGame = new YutGame();
		Scanner sc = new Scanner(System.in);
//		Util util = new Util();
		//
		int gameGoal = 20; // 최종 Goal 수
		boolean play = false;// 참가자 순서를 바꿔 줄 변수(change turn)
		int gamecntA = 0;// A말의 점수를 저장 할 변수
		int gamecntB = 0;// B말의 점수를 저장 할 변수
		//
		yutGame.startGame(gameGoal);

		while (true) {// palyer A 와 player B 번갈아 가며 윷을 던집니다.
			System.out.println();// player의 점수를 출력 합니다.
			//
			if (yutGame.callResultPrint(gamecntA, gamecntB) != 0) {
				break;
			}

			// 게임을 시작하기 위해 버튼 안내 출력
			int menu = yutGame.startThrow(play);

			yutGame.clearScreen(80);
			if (menu == 1 && play == false) {
				ProjectYDTO resultThrow = yutGame.throwYut("A");// 윷을 던지는 메소드 호출 하여 DTO타입 변수에 리턴값을 담는다.
				gamecntA = resultThrow.getSumPositionCnt();// A의 위치값을 받아 변수에 담는다.
				if (resultThrow.getRetryChkno() == 1) {// 윷이나 모가 나오거나 앞 플레이어를 잡을 경우 다시 한 번 던집니다.
					continue;
				}
				play = true;// turn 전환
				continue;
			} else if (menu == 2 && play == true) {
				ProjectYDTO resultThrow = yutGame.throwYut("B");
				gamecntB = resultThrow.getSumPositionCnt();// B 위치값을 받아 변수에 담는다.
				if (resultThrow.getRetryChkno() == 1) {// 윷이나 모가 나오거나 앞 플레이어를 잡을 경우 다시 한 번 던집니다.
					continue;
				}
				play = false;// turn 전환
				continue;
			} else {
				yutGame.reinput();
			}
		}
		yutGame.clearScreen(2);
		yutGame.clearSpace(28);
		System.out.print("<< 게임  종료 >>");
	}
}