package YprojectNew;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
public class ProjectYRepository {
	//
	Random rd = new Random();// 랜덤 객체 생성
	Map<Integer, ProjectYDTO> Ymap = new HashMap<>();
	ProjectYDTO prDTO = new ProjectYDTO();
//
	public void save(ProjectYDTO proYDTO) {
		//
		Ymap.put(proYDTO.getTotalyutcnt(), proYDTO);
	}
	public Map<Integer, ProjectYDTO> remap() {
		return Ymap;
	}
	public void YboardColum() {
	}
	public String randomspace() {// 윷가락의 위치를 변경하여 콘솔에 조금의 재미를 부여
		int randoms = rd.nextInt(10);
		String randomSpace = "";
		for (int i = 0; i < randoms; i++) {
			randomSpace = " " + randomSpace + " ";
		}
		return randomSpace;
	}
	public void actiontimer() {
		System.out.print(" . ");

		try {

			Thread.sleep(1000); //1초 대기

		} catch (InterruptedException e) {

			e.printStackTrace();

		}

		System.out.print(" . ");
	}
	public void Yut1() {
		String randomyut = randomspace();
		System.out.println(randomyut + "                 _______________");
		System.out.println(randomyut + "                |___X___X___X___|" + 1);
	}
	public void Yut2() {
		String randomyut = randomspace();
		System.out.println(randomyut + "                 _______________");
		System.out.println(randomyut + "                |_______________|" + 0);
	}
	public void Yut3() {
		String randomyut = randomspace();
		System.out.println(randomyut + "                 _______________");
		System.out.println(randomyut + "                |_B_____________|" + 2);
	}
	public String nowyutresult(int nowPositionCnt) {
		String nowMal;
		if (nowPositionCnt == 1) {// 도 개 걸 윷 모 결과를 화면에 출력해줌 문장을 저장
			nowMal = " => 이번 결과 도!!  1 칸 전진!! ";
		} else if (nowPositionCnt == 2) {
			nowMal = " => 이번 결과 개!!  2 칸 전진!! ";
		} else if (nowPositionCnt == 3) {
			nowMal = " => 이번 결과 걸!!  3 칸 전진!! ";
		} else if (nowPositionCnt == 4) {
			nowMal = " => 이번 결과 윷!!  4 칸 전진!! 그리고 한 번 더~~";
		} else if (nowPositionCnt == 5) {
			nowMal = " => 이번 결과 모!!  5 칸 전진!! 그리고 한 번 더~~";
		} else {
			nowMal = " => 빽~도 입니다. 행운이길~! ";
		}
		return nowMal;
	}
	public void deathroad1() {
		System.out.println("\n\u001B[31m" + " ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■"
				+ "\u001B[0m");
	}
}