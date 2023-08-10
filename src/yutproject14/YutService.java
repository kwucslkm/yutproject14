package yutproject14;

import java.util.Map;
import java.util.Random;
import java.util.Scanner;

//
public class YutService extends YutDeathRoad {

	public int callResultPrint(int gamecntA, int gamecntB) {

		if (gamecntA >= 20) {
			printResultGame("A");
			return 1;
		} else if (gamecntB >= 20) {
			printResultGame("B");
			return 2;
		}
		return 0;
	}

	public void printResultGame(String player) {
		clearSpace(23);
		System.out.println("축하합니다!! 참가자 " + player + " 승리!!");
		ShowResult(player);
	}

	public void clearScreen(int line) {
		pRepository.clearScreen(line);
	}

	public int startThrow(boolean play) {
		if (play) { // 게임을 시작 합니다.
			clearSpace(22);
			System.out.print("참가자 B press '2' enter> ");
		} else {
			clearSpace(22);
			System.out.print("참가자 A press '1' enter> ");
		}
		return util.numberCheck();

	}

	public void reinput() {// 잘못 눌렀을경우 다시 뿌려줌
		Map<Integer, ProjectYDTO> prDTO = pRepository.remap();
		if (prDTO.size() == 0) {
			System.out.println("처음 플레이입니다. 정확히 눌러 주세요");
		} else {
			ProjectYDTO prDTOl = null;
			for (int r : prDTO.keySet()) {
				prDTOl = prDTO.get(r);
			}
			clearScreen(5);
			System.out
					.println("\u001B[36m ■■■■■■■■■■■■■■■■■■■■■■■■■ YUT RACE GAME !! ■■■■■■■■■■■■■■■■■■■■■■■■\u001B[0m");
			clearScreen(6);
			StartYut1();
			clearSpace(40);
			YboardRows(prDTOl);
			clearSpace(30);
			System.out.println("잘못 누르셨습니다. ");
		}
	}

	public void StartYut1() {

		pRepository.StartYut1();
	}

	public void playTostring(String play) {
		Map<Integer, ProjectYDTO> prDTO = pRepository.remap();
		String retryword = null;
		String nowY = null;
		int tryC = 1;
		for (Integer r : prDTO.keySet()) {
			int playercnt = 0;
			if (prDTO.get(r).getPlayer().equals(play)) {
				playercnt = tryC++;
				int nowyut = prDTO.get(r).getNowyutCnt();
				if (nowyut == 1) {
					nowY = "도";
				} else if (nowyut == 2) {
					nowY = "개";
				} else if (nowyut == 3) {
					nowY = "걸";
				} else if (nowyut == 4) {
					nowY = "윷";
				} else if (nowyut == 5) {
					nowY = "모";
				} else {
					nowY = "빽도";
				}
				int retry = prDTO.get(r).getRetryChkno();
				if (retry == 1) {
					retryword = "한번더함";
				} else {
					retryword = "";
				}
				clearSpace(11);
				System.out.println(prDTO.get(r).getTotalyutcnt() + "\t  " + prDTO.get(r).getPlayer() + "\t  "
						+ playercnt + "\t  " + nowY + "\t  " + prDTO.get(r).getSumPositionCnt() + "\t  " + retryword);
			}
		}
	}

	public void ShowResult(String play) { // 승부가 끝나고 history를 출력해주는 메소드
		System.out.println();
		clearSpace(11);
		System.out.println("전체\t플레이어\t참여차수\t  윷\t 말위치\t  한번더");
		resultLine();
		//
		if (play.equals("A")) {
			playTostring("A");
			resultLine();
			playTostring("B");
		} else {
			playTostring("B");
			resultLine();
			playTostring("A");
		}
//		
	}

	public void resultLine() {
		clearSpace(10);
		System.out.println("--------------------------------------------------");
	}

	public void placeNum(int gameGoal) {
		pRepository.placeNum(gameGoal);

	}
}