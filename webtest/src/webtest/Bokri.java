package webtest;

public class Bokri {
	public static void main(String[] args){
		
		int money = 1000000;
		double r = 0.025;
		int i = 0;
		do{
			money *= (1+r) ;
		}while(i<4);
		System.out.println(money);
	}
}
