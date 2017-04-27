package webtest;

import java.util.Random;
import java.util.Scanner;

public class test{
	int com = 0;
	public int game(int result){
		Scanner in =  new Scanner(System.in);
		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());
		com = rand.nextInt(100)+1;
		
		if(com == result){
			Random rand_new = new Random();
			rand_new.setSeed(System.currentTimeMillis());
			com = rand_new.nextInt(100)+1;
		}
		System.out.println(result+"한번돌리고난후");
		System.out.println(com+"커ㅏㅁ!!!!!!!!!!!!!!");
		while(true){
			int count = 0;
			System.out.println("업다운 게임");
			while(count<=6){
				if(count == 0){
					System.out.println("수를 입력해주세요(현재 남은 횟수 : "+(7-count)+"번)");
				}else{
					System.out.println("다시 수를 입력해주세요(현재 남은 횟수 : "+(7-count)+"번)");	
				}
				int user = in.nextInt();
				if(user>com){
					System.out.println("낮습니다.");
					}else if(user==com){
						System.out.println("성공.");
						System.out.println("재시작 : 1 종료 : 0 ");
						int restart = in.nextInt();
						if(restart==1){
							game(com);
							break;
						}else if(restart==0){
							System.out.println("종료.");
							System.exit(0);
							}else{
								System.out.println("다시 입력해주세요.");
							}
						}else if(user<com){
						System.out.println("높습니다.");
					}
				count++;
				}
			if(count >= 7){
				System.out.println("실패.");
				System.out.println("재시작 : 1 종료 : 0 ");
				int restart = in.nextInt();
				if(restart==0){
					System.exit(0);
				}else if(restart==1){
					game(com);
					}else{
						System.out.println("다시 입력해주세요");
					}
				}
			}
		}
		public static void main(String args[]){
			test a = new test();
			System.out.println("게임시작");
			a.game(0);
		}
	}