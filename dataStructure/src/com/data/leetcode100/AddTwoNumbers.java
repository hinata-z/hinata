package com.data.leetcode100;

import java.util.List;

public class AddTwoNumbers {

    static class ListNode{
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode root=new ListNode(0);
        ListNode currentNode=root;
        int a=0;
        for(ListNode la=l1,lb=l2;la!=null;la=la.next,lb=lb.next){
            int sum=la.val+lb.val+a;
            a=sum/10;
//            currentNode.val=(sum)%10;
//            currentNode.next=new ListNode();
//            currentNode=currentNode.next;
            ListNode sumNode = new ListNode(sum % 10);
            currentNode.next = sumNode;
            currentNode = sumNode;

        }

        return root.next;
    }


    public static void main(String[] args) {
        ListNode  a1=new ListNode();
        a1.val=2;
        a1.next=new ListNode(4);
        a1.next.next=new ListNode(3);
        ListNode  a2=new ListNode();
        a2.val=5;
        a2.next=new ListNode(6);
        a2.next.next=new ListNode(4);
        int arr[]={2,4,3};
        int arr2[]={5,6,4};


        addTwoNumbers(a1,a2);
    }
}
