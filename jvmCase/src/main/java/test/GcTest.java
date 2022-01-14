package test;

import java.util.ArrayList;
import java.util.List;

public class GcTest {
    public void get(){

    }

    public static void main(String[] args) {
        List<Integer> list=new ArrayList<>();
        int i=0;
        while (true){
            i++;
           if(i/10000==0){
               System.out.println(i);
           }
            list.add(i);
        }
    }
}
