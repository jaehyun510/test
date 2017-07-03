package webtest;
import java.util.Scanner;

public class test1 {
	public static void main(String[] args)
	{
	Scanner a = new Scanner(System.in);
	System.out.print("수를 입력하시오.");
	int x = a.nextInt();
	int i=0;
	while(i < x){
		int j= 0;
			while(j<x){
				if(i>j){
					System.out.print(" ");
				}else if(i<=j){
					System.out.print("*");
				}
				j++;
			}
			i++;
			System.out.println();
		}
	}
}