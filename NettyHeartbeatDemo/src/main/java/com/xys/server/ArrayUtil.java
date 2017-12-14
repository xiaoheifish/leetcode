package com.xys.server;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.StringStack;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class ArrayUtil {
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

    public static Map<String ,String> map = new HashMap<String, String>();
    static {
        map.put(")","(");
        map.put("]","[");
        map.put("}","{");
    }

    public static boolean isValid(String testStr){
        Stack<String> stack = new Stack<String>();
        stack.push((String.valueOf(testStr.charAt(0))));
        for(int i = 1; i < testStr.length(); i++) {

            //System.out.println(map.get(String.valueOf(testStr.charAt(i))).equals(stack.peek()));
            if (stack.empty() || !(stack.peek().equals(map.get(String.valueOf(testStr.charAt(i)))))) {
                stack.push(String.valueOf(testStr.charAt(i)));
            } else {
                stack.pop();

            }
        }
        if (stack.empty()){
            return true;
        }else {
            return false;
        }
    }

    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        return longestCommonPrefix(strs, 0 , strs.length - 1);
    }

    private static String longestCommonPrefix(String[] strs, int l, int r) {
        if (l == r) {
            return strs[l];
        }
        else {
            int mid = (l + r)/2;
            String lcpLeft =   longestCommonPrefix(strs, l , mid);
            String lcpRight =  longestCommonPrefix(strs, mid + 1,r);
            return commonPrefix(lcpLeft, lcpRight);
        }
    }

    static String commonPrefix(String left,String right) {
        int min = Math.min(left.length(), right.length());
        for (int i = 0; i < min; i++) {
            if ( left.charAt(i) != right.charAt(i) )
                return left.substring(0, i);
        }
        return left.substring(0, min);
    }


    /*public static String longestCommonPrefix(String[] strs) {
        String result = "";
        if(strs.length == 0 || strs[0].length() == 0){
            return result;
        }
        if(strs.length== 1){
            return strs[0];
        }
        for(int i = 0; i < strs[0].length(); i++){
            for(int k = 1; k < strs.length; k++){
                if((strs[k].length() <= i)||strs[k].charAt(i) != strs[0].charAt(i) ){
                    return result;
                }
            }
            result += strs[0].charAt(i);

        }
        return result;
    }*/

    public static int charToInt(char a){
        int result = 0;
        switch (a) {
            case 'I':{
                result = 1;
            }
            break;
            case 'V':{
                result = 5;
            }
            break;
            case 'X':{
                result = 10;
            }
            break;
            case 'L':{
                result = 50;
            }
            break;
            case 'C':{
                result =  100;
            }
            break;
            case 'D':{
                result =  500;
            }
            break;
            case 'M':{
                result = 1000;
            }
            break;
            default:
                result =  0;
                break;
        }
        return result;

    }
    public static int romanToInt(String s) {
        int pre = 0, post = 0, result = 0;
        for(int i = 0; i < s.length(); i++){
            post = charToInt(s.charAt(i));
            if(post > pre && i != 0){
                result = result + post - pre;
            }else{
                if(i == s.length() - 1){
                    result = result + post;
                }else{
                    if((charToInt(s.charAt(i + 1)) <= post)) {
                        result = result + post;
                    }
                }

            }
            pre = post;
        }
        return result;
    }

    public static int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        for(int i = 0; i < nums.length; i++){
            for(int j = i + 1; j < nums.length; j++){
                if(nums[i] + nums[j] == target){
                    result[0] = i;
                    result[1] = j;
                    break;
                }
            }

        }
        return result;
    }

    public static int reverseNumber(int x){
        int result = 0;
        int tempx = x;
        while (tempx != 0){
            int a = tempx % 10;
            result = result * 10 + a;
            tempx = tempx / 10;
        }
        if(result > Math.pow(2,31) - 1 || result < -Math.pow(2,31)){
            return 0;
        }
        return result;
    }

    public static boolean isPalindrome(int x) {
        int number = 0;
        int tempx = x;
        while(x != 0 ){
            x = x / 10;
            number++;
        }
        boolean result = true;
        while(tempx != 0 ){
            int a = (int)(tempx /Math.pow(10, number - 1));
            int b = tempx % 10;
            if(a != b){
                result = false;
                break;
            }
            tempx = (tempx - tempx % 10 - (int)( (int)(tempx / Math.pow(10, number - 1)) * Math.pow(10,number - 1))) / 10;
            number = number - 2;
        }
        return result;
    }

    public static boolean isPalindTwo(int x){
        int revertNumber = 0;
        if (x < 0){
            return false;
        }
        while(x > revertNumber){
            revertNumber = revertNumber * 10 + x % 10;
            x = x / 10;
        }
        return x == revertNumber || x == revertNumber / 10;//奇数或者偶数
    }

    public static void generateListNode(){
        ListNode dummyNode = new ListNode(0);
        ListNode currNode = dummyNode;
        currNode.next = new ListNode(1);
        currNode = currNode.next;
        currNode.next = new ListNode(2);
        currNode = currNode.next;
        currNode.next = new ListNode(3);
        currNode = currNode.next;
        System.out.println(dummyNode.next);
    }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry=0;
        ListNode listNode=new ListNode(0);
        ListNode p1=l1,p2=l2,p3=listNode;
        while(p1!=null||p2!=null){
            if(p1!=null){
                carry+=p1.val;
                p1=p1.next;
            }
            if(p2!=null){
                carry+=p2.val;
                p2=p2.next;
            }
            p3.next=new ListNode(carry%10);
            p3=p3.next;
            carry/=10;
        }
        if(carry==1)
            p3.next=new ListNode(1);
        return listNode.next;
    }

    public static int lengthOfLongestSubstring(String s) {
        char[] myChar = s.toCharArray();
        int maxContinue = 0;
        int tempContinue = 1;
        for(int i = 0; i < s.length(); i++){
            for(int j = i + 1; j < s.length(); j++){
                int k = i;
                while(k < j){
                    if(myChar[j] == myChar[k]){
                        break;
                    }
                    k++;
                }
                if(k == j){
                    tempContinue++;
                    if(j == s.length() - 1){
                        if(tempContinue > maxContinue){
                            maxContinue = tempContinue;
                        }
                        tempContinue = 1;
                        break;
                    }
                } else {
                    if(tempContinue > maxContinue){
                        maxContinue = tempContinue;
                    }
                    tempContinue = 1;
                    break;
                }
            }
        }
        return maxContinue;
    }

    public static int maxLengthString(String s){
        int n = s.length();
        Set<Character> set = new HashSet<Character>();
        int ans=0, i=0, j =0;
        while(i < n && j <n){
            if(!set.contains(s.charAt(j))){
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            }else {
                set.remove(s.charAt(i++));
            }
        }
        return ans;
    }
}