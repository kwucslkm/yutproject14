package yutproject14;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
//
public class YutService {
	Scanner sc = new Scanner(System.in);
	ProjectYRepository pRepository = new ProjectYRepository();
	Random rd = new Random();// 랜덤 객체 생성
	static int goalNum = 0;
	int totalcnt = 0;
	int playerAcnt;
	int playerBcnt;
	int poCntA = 0; // A누적 점수
	int poCntB = 0; // B누적 점수
	public void printResultGame(String player) {
		clearSpace(23);
		System.out.println("축하합니다!! 참가자 "+player+" 승리!!");
		ShowResult(player);
	}
	public void clearScreen(int line) {
		pRepository.clearScreen(line);
	}
	public void clearSpace(int space) {
		pRepository.clearSpace(space);
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
	public ProjectYDTO YboardRows(ProjectYDTO proYDTO) {
		goalNum = 20;
		String boardO = " o ";
		String boardA = "\u001B[32m(A)\u001B[0m";
		String boardB = "\u001B[33m(B)\u001B[0m";
		String boardAB = "\u001B[32m(A\u001B[0m\u001B[33mB)\u001B[0m";
		String boardBA = "\u001B[33m(B\u001B[0m\u001B[32mA)\u001B[0m";
		Map<Integer, ProjectYDTO> prDTO = pRepository.remap();
		System.out.println("\n");
		System.out.println("                             * Death Road *");
		pRepository.placeNum(goalNum);
		pRepository.deathroad1();// 데스로드 호출
		System.out.print(" ");
		int catchM = 0; // 잡히는 상황 시 메시지 출력
//		
		for (int i = 0; i <= goalNum; i = i + 1) {
			if (poCntA == -1 && i == 0) {// 처음에 빽도가 나온 상황
				poCntA = 19;
			} else if (poCntB == -1 && i == 0) {
				poCntB = 19;
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
		pRepository.deathroad1();// 데스로드 호출
		if (catchM == 1) {
			clearSpace(20);
			System.out.println("   앗 " + proYDTO.getPlayer() + " 님 잡았습니다. 한번더");
			proYDTO.setRetryChkno(1);
		}
		System.out.println();
		return proYDTO;
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