package webtest;

import java.util.Scanner;

public class Time2 
{ 
	  public static void main(String[] args) 
      { 
		  int count = 0; // 입력받은 갯수 
		  int total = 0; // 전체성적
		  Scanner sc = new Scanner(System.in);
		  
		  System.out.println("이름을 입력하시오 : ");
		  String name = sc.next();
		  
		  int[] score = new int[3];
		  System.out.println("국어 성적을 입력하시오 : ");
		  int Korean = sc.nextInt();
		  if(Korean >= 0){
			  score[count] = Korean;
			  count++;
		  }
		  System.out.println("영어 성적을 입력하시오 : ");
		  int English = sc.nextInt();
		  if(English >= 0){
			  score[count] = English;
			  count++;
		  }
		  System.out.println("수학 성적을 입력하시오 : ");
		  int Math = sc.nextInt();
		  if(Math >= 0){
			  score[count] = Math;
		  }
	      System.out.println("이름	국어 	영어	 수학 	총점	 평균 "); 
	      System.out.println("========================================================="); 
            for(int i=0;i < score.length;i++) { 
                  total += score[i]; 
            } 
            System.out.println(name + "\t" + Korean + "\t" + English + "\t" +Math + "\t" +total + "\t" + ((float)total/score.length)); 
            System.out.println("=========================================================");
      } 
} 