package webtest;

import java.util.Scanner;

public class wTest {
	public static int[] convertBinary(int num) {
	    int i = 0, temp[] = new int[10];
	    int binary[];
	    while (num > 0) {
	        temp[i++] = num % 2;
	        num /= 2;
	    }
	    binary = new int[i];
	    int k = 0;
	    for (int j = i - 1; j >= 0; j--) {
	        binary[k++] = temp[j];
	    }

	    return binary;
	}
	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			
			Scanner input = new Scanner(System.in);
			System.out.println("실행결과 ");
			System.out.println("Enter a decimal number: ");
			int decimal = input.nextInt();
			int[] result = convertBinary(decimal);
			for (int j = 0; j < result.length; j++) {
				System.out.print(result[j]);
				
			}
			System.out.println(" ");
		}
	}
}
