package webtest;


public class keyboardTest {
	
//	static String star = "☆";
	static String star = "*";
	public static void main(String[] args){
		
    for (int i = 1; i < 4; i++) {
 
        for (int j = i; j < 4; j++) {
            System.out.print(" ");
        }
        for (int j = 0; j < i; j++) {
            System.out.print(star);
        }
        for (int j = 0; j < i - 1; j++) {
            System.out.print(star);
        }
        System.out.println();
    }
 
    // 하단 역삼각형을 출력하는 반복문
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < i; j++) {
                System.out.print(" ");
            }
            for (int j = i; j < 4; j++) {
                System.out.print(star);
        }
        for (int j = i + 1; j < 4; j++) {
            System.out.print(star);
        }
        System.out.println();
        }
    }
}
