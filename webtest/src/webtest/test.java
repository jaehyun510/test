package webtest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class test {
      public static void main(String[] args) {
      List<String> list = new ArrayList<String>();
      ArrayList<String> str = new ArrayList<String>();
          list.add("1,2,3,4,5");
          list.add("a,b,c,d,e");
          list.add("q,w,e,r,t");
          list.add("6,7,8,9,10");
          for(int j=0 ; j<list.size(); j++){
        	  System.out.println(list.get(j));
          }
          System.out.println(list);
          str.add("1,2,3,4,5");
          str.add("a,b,c,d,e");
          System.out.println("========");
          System.out.println(str);
          System.out.println(str.get(0));
          System.out.println("========");
          
          
          String[] array1 = new String[3];
          array1[0] = "asList Test1";
          array1[1] = "asList Test2";
          array1[2] = "asList Test3";
          ArrayList<String> arrayList1 = new ArrayList<>(Arrays.asList(array1));
          System.out.println(arrayList1);
          for(int i=0 ; i<arrayList1.size(); i++){
          System.out.println(arrayList1.get(i));
          }
          System.out.println("========");

          
          ArrayList<String> arrayList2 = new ArrayList<>();
          arrayList2.add("toArray Test1");
          arrayList2.add("toArray Test2");
          arrayList2.add("toArray Test3");
          String[] array2 = arrayList2.toArray(new String[arrayList2.size()]);
          for(int k=0 ; k<arrayList2.size(); k++){
              System.out.println(array2[k]);
              }

      }
}
