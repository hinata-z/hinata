package com.data.struct.stack;

public class LinkNodeStack {

    //链表头，相当于栈顶
    private LinkNode head;

    //出栈
    public void pop(){
        if(empty()){
            throw new IllegalStateException("栈为空……");
        }else
            head = head.next;
    }
    // 入栈
    public void push(int val){
        if(empty()){
            head=new LinkNode(val,val,null);
        }else
            head=new LinkNode(val,Math.min(val,head.min),head);
    }
    //判断栈是否为空
    private boolean empty() {
        return head == null;
    }
    public Integer min(){
        if (empty())
            throw new IllegalStateException("栈为空……");
        return head.min;

    }


    class LinkNode{
        Integer min;
        int val;
        LinkNode next;

        LinkNode(int val ,int min,LinkNode linkNode){
            this.val=val;
            this.min=min;
            this.next=linkNode;
        }


    }

    public static void main(String[] args) {
        LinkNodeStack linkNodeStack=new LinkNodeStack();
        linkNodeStack.push(13);
        linkNodeStack.push(2);
        linkNodeStack.push(6);
        linkNodeStack.push(11);
        System.out.println(linkNodeStack.min());
    }
}
