package yutproject14;

import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class YutDeathRoad {
	Scanner sc = new Scanner(System.in);
	ProjectYRepository pRepository = new ProjectYRepository();
	Random rd = new Random();// 랜덤 객체 생성
	Util util = new Util();
	static int goalNum = 0;
	int totalcnt = 0;
	int playerAcnt;
	int playerBcnt;
	int poCntA = 0; // A누적 점수
	int poCntB = 0; // B누적 점수

	public void clearSpace(int space) {
		pRepository.clearSpace(space);
	}

	public ProjectYDTO YboardRows(ProjectYDTO proYDTO) {
//		goalNum = 20;
		String boardO = " o ";
		String boardA = "\u001B[32m(A)\u001B[0m";
		String boardB = "\u001B[33m(B)\u001B[0m";
		String boardAB = "\u001B[32m(A\u001B[0m\u001B[33mB)\u001B[0m";
		String boardBA = "\u001B[33m(B\u001B[0m\u001B[32mA)\u001B[0m";
		Map<Integer, ProjectYDTO> prDTO = pRepository.remap();
		System.out.println("\n");
		System.out.println("                             * Death Road *");
		pRepository.placeNum(goalNum);
		System.out.println();
		pRepository.deathroad1(goalNum);// 데스로드 호출
		System.out.println();
		System.out.print(" ");
		int catchM = 0; // 잡히는 상황 시 메시지 출력
//	
		for (int i = 0; i <= goalNum; i = i + 1) {
			if (poCntA == -1 && i == 0) {// 처음에 빽도가 나온 상황
				poCntA = goalNum-1;
			} else if (poCntB == -1 && i == 0) {
				poCntB = goalNum-1;
			}
			if (i == poCntA && i == poCntB) { // 따라잡은 상황
				if (proYDTO.getPlayer().equals("A")) {
					poCntB = 0;
					System.out.print(boardAB);
				} else {
					poCntA = 0;
					System.out.print(boardBA);
				}
				catchM = 1;
			} else if (poCntB < poCntA && i == poCntB && i != goalNum) {
				System.out.print(boardB);
			} else if (poCntB < poCntA && i == poCntA && i != goalNum) {
				System.out.print(boardA);
			} else if (poCntB > poCntA && i == poCntA && i != goalNum) {
				System.out.print(boardA);
			} else if (poCntB > poCntA && i == poCntB && i != goalNum) {
				System.out.print(boardB);
			} else if (i == goalNum) {
				if (poCntA >= goalNum) {// 승리자가 나와서 골에 들어가고 게임 종료
					System.out.print("\u001B[32m(A Goal!) \u001B[0m");
				} else if (poCntB >= goalNum) {
					System.out.print("\u001B[33m(B Goal!) \u001B[0m");
				} else {
					System.out.print("(GOAL!)");
				}
			} else {
				System.out.print(boardO);
			}
		}
		System.out.println();
		pRepository.deathroad1(goalNum);// 데스로드 호출
		System.out.println();
		if (catchM == 1) {
			clearSpace(20);
			System.out.println("   앗 " + proYDTO.getPlayer() + " 님 잡았습니다. 한번더");
			proYDTO.setRetryChkno(1);
		}
		System.out.println();
		return proYDTO;
	}
}
