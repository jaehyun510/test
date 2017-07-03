package webtest;

import java.util.Scanner;

public class calMoney {

	public static void main(String[] args){
		 Scanner sc = new Scanner(System.in);
		  System.out.print("받은 돈은 얼마죠? ");
		  Double getMoney = sc.nextDouble();
		  System.out.println("==== "+getMoney+"원");
		  
		  System.out.println("어떤 상품을 골랐나요?");
		  int getMenu = sc.nextInt();
		  double g = 0;
		  double gTax = 0;
		  double gResult = 0;
		  if(getMenu == 1){
			  g = 7500;
			  if(getMoney < g){
				  System.out.println("고를수 없는 상품입니다.");
				  gResult = getMoney;
			  }else{
				  gTax = (g * 0.1);
				  System.out.println("상품의 총액은 : "+ g + " 원");
				  System.out.println("부가세는 : "+ gTax + " 원");
				  gResult = getMoney-g;  
			  }
		  }else if(getMenu == 2){
			  g = 500;
			  if(getMoney < g){
				  System.out.println("고를수 없는 상품입니다.");
				  gResult = getMoney;
			  }else{
				  gTax = (g * 0.1);
				  System.out.println("상품의 총액은 : "+ g + " 원");
				  System.out.println("부가세는 : "+ gTax + " 원");
				  gResult = getMoney-g;  
			  }
		  }else if(getMenu == 3){
			  g = 7000;
			  if(getMoney < g){
				  System.out.println("고를수 없는 상품입니다.");
				  gResult = getMoney;
			  }else{
				  gTax = (g * 0.1);
				  System.out.println("상품의 총액은 : "+ g + " 원");
				  System.out.println("부가세는 : "+ gTax + " 원");
				  gResult = getMoney-g;  
			  }
		  }else{
			  System.out.println("상품이 준비되지 않았습니다.");
		  }
		  System.out.println("잔돈은 : "+ gResult);
	}
}
