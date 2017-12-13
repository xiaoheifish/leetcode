package com.company;
public class MergeList {
    public static void main(String[] args) {

        ListNode l1 = new ListNode(0);
        ListNode p1 = l1;
        l1.next = new ListNode(1);
        l1 = l1.next;
        l1.next = new ListNode(2);
        l1 = l1.next;
        l1.next = new ListNode(4);
        ListNode p2 = p1.next;
        System.out.println(p2);
        ListNode l2 = new ListNode(0);
        ListNode p4 = l2;
        l2.next = new ListNode(1);
        l2 = l2.next;
        l2.next = new ListNode(3);
        l2 = l2.next;
        l2.next = new ListNode(4);
        ListNode p3 = p4.next;
        System.out.println(p3);
        System.out.println(recurMerge(p2, p3));
    }
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode listNode = new ListNode(0);
        ListNode p1 = l1, p2 = l2, tempNode = listNode;
        int p1Value = Integer.MAX_VALUE, p2Value = Integer.MAX_VALUE;
        while(p1 != null || p2 !=null){
            if(p1 != null){
                p1Value = p1.val;
            }
            if(p2 != null){
                p2Value = p2.val;
            }
            if(p1Value <= p2Value){
                tempNode.next = new ListNode(p1Value);
                p1 = p1.next;
            }else{
                tempNode.next = new ListNode(p2Value);
                p2 = p2.next;
            }
            tempNode = tempNode.next;
            p1Value = Integer.MAX_VALUE;
            p2Value = Integer.MAX_VALUE;
        }
        return listNode.next;
    }

    public static ListNode recurMerge(ListNode l1, ListNode l2){
        if(l1 == null){
            return l2;
        } else if(l2 == null){
            return l1;
        } else if(l1.val < l2.val){
            l1.next = recurMerge(l1.next, l2);
            return l1;
        } else{
            l2.next = recurMerge(l1, l2.next);
            return l2;
        }
    }

}