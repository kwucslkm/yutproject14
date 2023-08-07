package yutproject14;
//
public class ProjectYDTO {
	//
	private int retryChkno;
	private int nowyutCnt;
	private int sumPositionCnt;
	private String player;
	private Integer totalyutcnt;
	//
	public Integer getTotalyutcnt() {
		return totalyutcnt;
	}
	public void setTotalyutcnt(Integer totalyutcnt) {
		this.totalyutcnt = totalyutcnt;
	}
	public int getRetryChkno() {
		return retryChkno;
	}
	public void setRetryChkno(int retryChkno) {
		this.retryChkno = retryChkno;
	}
	public int getSumPositionCnt() {
		return sumPositionCnt;
	}
	public void setSumPositionCnt(int sumPositionCnt) {
		this.sumPositionCnt = sumPositionCnt;
	}
	public String getPlayer() {
		return player;
	}
	public void setPlayer(String player) {
		this.player = player;
	}
	public int getNowyutCnt() {
		return nowyutCnt;
	}
	public void setNowyutCnt(int nowyutCnt) {
		this.nowyutCnt = nowyutCnt;
	}
//	@Override
//	public String toString() {
//		//
//		String s2 = "  ";
//		String str = totalyutcnt + "\t" + player + "\t" + nowyutCnt + "\t" + sumPositionCnt + "\t" + retryChkno;
//		return str;
//	}
}