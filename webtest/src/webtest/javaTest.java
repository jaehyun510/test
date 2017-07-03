package webtest;

import java.util.Scanner;

public class javaTest {
	public static void main(String args[]) {
		int row = 5;
		int col = 10;
	
		final int[][] size= new int[row][col];
		String[][] seats = new String[row][col];
		int count = 0 ;
	
		while(true) {
			int a=1;
			if(count < 1){
				for (int i = 0; i < row; i++) {
				  for (int j = 0; j < col; j++) {
					  size[i][j] = a++;
					  seats[i][j] = String.valueOf(size[i][j]);
				  }
				}
				count = count+1;
			}
			System.out.println("----------------------------");
			 for (int j = 0; j < row; j++) {
				 for (int i = 0; i < col; i++) {
					System.out.print(seats[j][i] + " ");
					if(size[j][i] % 10 == 0){
						System.out.println("\n");
					}
				 }
			}
			System.out.println("----------------------------");
			
			System.out.print("원하시는 좌석번호를 입력하세요(종료는 -1): ");
			Scanner scan= new Scanner(System.in);
			int s = scan.nextInt();
			if (s == -1){
			  break;
			}else{
				if(s == 0){
					System.out.println("유효하지 않는 자리입니다.");
				}else if(s <= 10){
//					System.out.println(seats[0][s-1]+"          ????");
					if((seats[0][s-1]).equals(String.valueOf(s))){
						seats[0][s-1]="X";
						System.out.println("예약되었습니다.");
					}else if((seats[0][s-1]).equals("X")){
						System.out.println("이미 예약되었습니다.");
					}
				}else if(s <= 20){
					System.out.println(s +" 찎은값");
					System.out.println(seats[1][s-11] +  "  넣을값");
					if((seats[1][s-11]).equals(String.valueOf(s))){
						seats[1][s-11]="X";
						System.out.println("예약되었습니다.");
					}else if((seats[1][s-11]).equals("X")){
						System.out.println("이미 예약되었습니다.");
					}
				}else if(s <= 30){
					if((seats[2][s-21]).equals(String.valueOf(s))){
						seats[2][s-21]="X";
						System.out.println("예약되었습니다.");
					}else if((seats[2][s-21]).equals("X")){
						System.out.println("이미 예약되었습니다.");
					}
				}else if(s <= 40){
					if((seats[3][s-31]).equals(String.valueOf(s))){
						seats[3][s-31]="X";
						System.out.println("예약되었습니다.");
					}else if((seats[3][s-31]).equals("X")){
						System.out.println("이미 예약되었습니다.");
					}
				}else if(s <= 50){
					if((seats[4][s-41]).equals(String.valueOf(s))){
						seats[4][s-41]="X";
						System.out.println("예약되었습니다.");
					}else if((seats[4][s-41]).equals("X")){
						System.out.println("이미 예약되었습니다.");
					}
				}
			}
		}
	}
}