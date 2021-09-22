package com.data.struct.queue;

import com.sun.jmx.remote.internal.ArrayQueue;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * 定一个经过编码的字符串，返回它解码后的字符串。
 *
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 *
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 *
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
 *
 *
 * 输入：s = "3[a2[c]]"
 * 输出："accaccacc"
 输入：s = "2[abc]3[cd]ef"
 输出："abcabccdcdcdef"
 */
public class NumQueue {

    public String decodeString(String s) {
        int num = 0;
        ArrayDeque<StringBuilder> resStack = new ArrayDeque<>();
        Stack<Integer> numStack = new Stack<>();
        StringBuilder res = new StringBuilder();
        char[] chars=s.toCharArray();
        for(char c:chars){
            if(Character.isDigit(c)){
                num =  c - '0';
                numStack.add(num);
            }else if(c== '['){
                resStack.push(res);
                res = new StringBuilder();
            }else if(c==']'){
                StringBuilder pre = resStack.pop();
                int n=numStack.pop();
                for(int i=0;i<n;i++){
                    pre.append(res);
                }
                res = pre;

            }else{
                res.append(c);
            }
        }
        return res.toString();
    }


    public String decodeString2(String s) {
        int num = 0;
        StringBuilder res = new StringBuilder();
        Deque<Integer> numStack = new ArrayDeque<>();
        Deque<StringBuilder> resStack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                num = num * 10 + c - '0';
            } else if (c == '[') {
                resStack.push(res);
                numStack.push(num);
                res = new StringBuilder();
                num = 0;
            } else if (c == ']') {
                StringBuilder pre = resStack.pop();
                int n = numStack.pop();
                for (int i = 0; i < n; i++) {
                    pre.append(res);
                }
                res = pre;
            } else {
                res.append(c);
            }
        }
        return res.toString();
    }



    public String decodeString1(String s) {
        ArrayDeque<Character> resStack = new ArrayDeque<>();
        char[] chars=s.toCharArray();
        for(char c:chars){
            if(c== '['){
                continue;
            }else if(c==']'){
                StringBuilder res = new StringBuilder();
                boolean isNumber=true;
                while (isNumber) {
                    char resChar = resStack.pop();
                    if (Character.isDigit(resChar)) {
                        isNumber = false;
                        int num = resChar - '0';
                        for (int i = 0; i < num-1; i++) {
                            res.append(res);
                        }
                    } else {
                        res.append(resChar);
                    }
                }
                char[] chars1=res.toString().toCharArray();
                for(char s1:chars1){
                    resStack.push(s1);
                }
            }else{
                resStack.push(c);
            }
        }
        return resStack.toString();
    }

    public static void main(String[] args) {
        NumQueue numQueue=new NumQueue();
        System.out.println(numQueue.decodeString("3[a2[c]]"));

    }
}
