package yutproject14;

//
public class YutGame extends YutService {

	public void startGame(int gameGoal) {
		clearScreen(5);
		System.out.println("\u001B[36m ■■■■■■■■■■■■■■■■■■■■■■■■■ YUT RACE GAME !! ■■■■■■■■■■■■■■■■■■■■■■■■\u001B[0m");
		clearScreen(6);
		StartYut1(); // 시작 윷가락모양 표현 메소드 호출
		clearScreen(5);
		System.out.println("  '4개의 엿가락'을 던져 20칸 '죽음의 도로'를 달려 GOAL에 먼저 도달하면 '승리' 합니다.");
		System.out.println("     도:1칸, 개:2칸, 걸:3칸, 윷:4칸(한번더), 모:5칸(한번더),앞사람잡으면(한번더)");
		System.out.println("            게임은 총 3판을 진행해서 두 번을 먼저 이기면 승리입니다.  ");
		System.out.println("         먼저 시작하실 분을 정하시고 참가자 A가 되신분은 '1'을 눌러 시작하세요\n");
		placeNum(gameGoal);
		System.out.println();
		System.out.println(
				"\u001B[31m" + " ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■" + "\u001B[0m");
		for (int i = 0; i < 20; i = i + 1) {
			if (i == 0) {
				System.out.print("\u001B[32m (A\u001B[0m\u001B[33mB)\u001B[0m");
			} else if (i == 1) {
				System.out.print("o ");
			} else {
				System.out.print(" o ");
			}
		}
		System.out.println(" Goal");
		System.out.println(
				"\u001B[31m" + " ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■" + "\u001B[0m");
		System.out.println(" ■■■■■■■■■■■■■■■■■■■■■■■ YUT GAME Start!!! ■■■■■■■■■■■■■■■■■■■■■■■■■\n");
	}

	public int PositionSumCnt(int nowPositionCnt, String player) {// 누적 점수 저장 메소드
		if (player.equals("A")) {
			poCntA += nowPositionCnt;
			return poCntA;
		} else {
			poCntB += nowPositionCnt;
			return poCntB;
		}
	}

	public ProjectYDTO throwYut(String player) {
		ProjectYDTO proYDTO = new ProjectYDTO();
		totalcnt++;
		if (player.equals("A")) {
			playerAcnt++;
		} else {
			playerBcnt++;
		}
		String nowyutCode = "";// 4개 윷 투척 조합 점수 기록 코드 변수
		int nowPositionCnt = 0; // 한번던질때 진행 하는 칸수
		int sumPositionCnt = 0; // 누적점수
		int retryChk = 0; // 다시 윷 던지기 변수
		String nowMal = "";//
		System.out.println("\u001B[36m ■■■■■■■■■■■■■■■■■■■■■■■■■■■ YUT GAME !! ■■■■■■■■■■■■■■■■■■■■■■■■■■■\u001B[0m");
		clearScreen(5);
		for (int i = 0; i < 4; i++) {
			if (rd.nextInt(2) == 1) {
				nowyutCode = nowyutCode + "1";// 윷가락 코드를 따기위한
				pRepository.Yut1();// 랜덤윷 호출
			} else {
				nowPositionCnt++;// 윗면이 안나오면 합산- 도 개 걸 윷 확인(1.도 2.개 3.걸 4.윷 5.모)
				if (i == 0) {
					nowyutCode = nowyutCode + "2";
					pRepository.Yut3();
				} else {
					nowyutCode = nowyutCode + "0";
					pRepository.Yut2();
				}
			}
		}
		clearScreen(4);
		if (nowPositionCnt == 0) {// '모'가 나올경우 5점으로 바꿔줌
			nowPositionCnt = 5; // '모'가 나올경우 5점으로 바꿔줌
		} else if (nowyutCode.equals("2111")) {// 빽도가 나올경우
			nowPositionCnt = -1; // 빽도가 나올경우 -1점으로 바꿔 줌
		}
		if (nowPositionCnt == 4 || nowPositionCnt == 5) {// 윷이나 모가 나오면 재실행 변수를 1로 바꿈
			retryChk = 1;
		}
		nowMal = pRepository.nowyutresult(nowPositionCnt);// 화면에 현재 윷 던지 결과를 출력
		System.out.println();// 여기서 플레이어와 현재 카운트와 다시실행 값을 넘겨줘야
		//
		proYDTO.setPlayer(player); // DTO 에 플레이어 저장
		proYDTO.setNowyutCnt(nowPositionCnt);// DTO 에 현재 윷카운트값 저장
		if (retryChk == 1) // DTO 에 재실행 변수를 저장
			proYDTO.setRetryChkno(retryChk);
		else {
			proYDTO.setRetryChkno(retryChk);
		}
		//
		sumPositionCnt = PositionSumCnt(nowPositionCnt, player);
		proYDTO.setSumPositionCnt(sumPositionCnt);// DTO 에 누적카운트값(말의 위치값) 저장
		proYDTO.setTotalyutcnt(totalcnt); // 전체 윷 던진 트라이 수
		pRepository.save(proYDTO);// 맵리스트에 윷 한 번 던졌을때의 값들을 저장
		clearSpace(13);// "\n " + nowyutCode + " " +
		System.out.println("참가자 " + player + "님" + nowMal);// 현재윷 던진 결과값을 출력
		clearSpace(13);
		System.out.println("  " + player + " 님 위치 점수는 -> " + sumPositionCnt + " 입니다.");
		//
		YboardRows(proYDTO);
		clearSpace(23);
		System.out.println("   현재 A의 위치는 " + poCntA + "입니다.");
		clearSpace(23);
		System.out.print("   현재 B의 위치는 " + poCntB + "입니다.\n");
		return proYDTO;
	}
}
