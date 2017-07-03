package webtest;

public class startTest {
	static String star = "*";
	public static void main(String[] args){
		for (int i = 1; i < 6; i++) {
			for (int j = 0; j < i; j++) {
				System.out.print(star);
			}
			for (int j = i; j < 5; j++) {
				System.out.print(" ");
			}
			for (int j = 5+i; j < 10; j++) {
				System.out.print(" ");
			}
			for (int j = 5; j < 5+i; j++) {
				System.out.print(star);
			}
			System.out.println();
		}//1차 for
		
		for (int i = 0; i < 6; i++) {
			for (int j = i; j < 5; j++) {
				System.out.print(star);
			}
			for (int j = 0; j < i; j++) {
				System.out.print(" ");
			}
			for (int j = 5; j < 5+i; j++) {
				System.out.print(" ");
			}
			for (int j = 5+i; j < 10; j++) {
				System.out.print(star);
			}
			System.out.println();
		}//2차 for
	}
}
