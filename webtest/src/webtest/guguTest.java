package webtest;

import java.util.Scanner;

public class guguTest {
	
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
	  System.out.print("출력할 단은?");
	  int gugu = sc.nextInt();
	  System.out.println(gugu+" 단입니다");
	  
	  for(int i=1; i<=9; i++){
		  System.out.println(gugu+"x"+i+"="+(gugu*i));
	  }
	}
}
