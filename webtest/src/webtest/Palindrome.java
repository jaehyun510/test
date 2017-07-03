package webtest;

import java.util.Scanner;
public class Palindrome {
 public static String ReverseWhile(String ABC,int size)
 {
  String Reverse = "";
  while(size !=0)
  {
   Reverse = Reverse + ABC.charAt(size-1);
   size--;
  }
  return Reverse;
 }
 
 public static String ReverseRecursive(String ABC, int size)
 {
  if(size == 0){
   return "";
  }
  else{
   return ABC.charAt(size-1) + ReverseRecursive(ABC,size-1);
  }
  
 }
 
 public static void main(String[] args){
  String Str1;
  String ReverseStr1;
  String ReverseStr2;
  int SizeLine;
  String Continue = "y";
  
  Scanner scan = new Scanner(System.in);
  
  do{
   System.out.print("문자열을 입력하세요: ");
   Str1 = scan.nextLine();
   SizeLine = Str1.length();
   ReverseStr1 = ReverseRecursive(Str1,SizeLine);
   
   if(Str1.equals(ReverseStr1)){
	   System.out.println(Str1+"은 회문입니다.");
   }
   else{
    System.out.println(Str1+"은 회문이 아닙니다.");
   }
   	System.out.print("문자열이 더 있습니까? <y 혹은 n>: ");
   	Continue = scan.next();
   	scan.nextLine();
  
  }while(Continue.equals("y"));
  
 }
}