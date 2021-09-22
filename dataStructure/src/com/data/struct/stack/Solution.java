package com.data.struct.stack;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;


public class Solution {
    /** hard code
     *
    *)]])))}}
     *
     * /**
     *  * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
     *  *
     *  * 有效字符串需满足：
     *  *
     *  * 左括号必须用相同类型的右括号闭合。
     *  * 左括号必须以正确的顺序闭合。
     *  *
     *
     *  利用栈的后进先出思想
     * */

    public static boolean isValid(String s) {
         char[] charArray=s.replace("(",")")
                 .replace("{","}").replace("[","]").toCharArray();
        Stack<Character> stack=new Stack<>();
        for (char c : charArray) {
            if(stack.isEmpty() || stack.peek()!=c){
                stack.push(c);
            }else{
                stack.pop();
            }
        }
        if(stack.isEmpty()){
            return true;
        }
        return false;
    }

    /**
     * 请根据每日 气温 列表，重新生成一个列表。对应位置的输出为：要想观测到更高的气温，至少需要等待的天数。如果气温在这之后都不会升高，请在该位置用 0 来代替。
     *
     * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
     *
     * 提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。
     *


     */
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack=new Stack<>();
        for(String token: tokens){
            if(token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")){
                int num1=stack.pop();
                int num2=stack.pop();
                stack.push(num2+num1);
            }else{
                stack.push(Integer.valueOf(token));
            }
        }
        return stack.pop();
    }

    public  static  int[] dailyTemperatures(int[] temperatures) {
        int[] temp=new int[temperatures.length];
        Stack<Integer> stack=new Stack<>();
        for(int i=0;i<temperatures.length;i++){
            while (!stack.isEmpty() && temperatures[i]>temperatures[stack.peek()]){
                int idx=stack.pop();
                temp[idx]=i-idx;
            }
            stack.push(i);
//            for(int j=i+1;j<temperatures.length;j++){
//                if(temperatures[j]>temperatures[i]){
//                    temp[i]=j-i;
//                    break;
//                }
//            }
        }
        return temp;
    }


    public static void main(String[] args) {
        String s="()[]{{[}]}";
        System.out.println(isValid(s));
        int[]  ints= {23,12,33,44,22,11};
        System.out.println( Arrays.toString(dailyTemperatures(ints)));
    }
}
