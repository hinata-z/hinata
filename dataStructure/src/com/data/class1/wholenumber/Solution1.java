package com.data.class1.wholenumber;
//给定两个整数 a 和 b ，求它们的除法的商 a/b ，要求不得使用乘号 '*'、除号 '/' 以及求余符号 '%'
public class Solution1 {

    public static void main(String[] args) {
        int a=15, b=4;
        int i=0;
        while (a>0){
            a=a-b;
            if(a<0){
               continue;
            }
            i++;
        }
        System.out.println(i);

        // 01111
        // 00010

    }
}
