package webtest;

public class primaryNum {
	public static void main(String[] args){
        for(int i=2; i<=1000; i++){ // 2부터 시작  
            int count=0;  
            for(int j=1; j<=i; j++){ 
                if(i%j==0) 
                count+=1; 
            }  
            if(count == 2) // 2개일 때가 소수! 
                System.out.println(i+" 은(는) 소수");
        }            
    }  
}
