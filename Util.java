package yutproject14;
import java.util.Scanner;
//
public class Util {
	public int numberCheck() {
		Scanner sc = new Scanner(System.in);
		if (sc.hasNextInt()) {
			return sc.nextInt();
		} else {
			sc.nextLine();
			return -1;
		}
	}
}