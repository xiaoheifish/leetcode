package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        int[] nums = {1,1,2,3,3,4};
        System.out.println(removeDuplicates(nums));
    }

    public static int removeDuplicates(int[] nums) {
        int j = 0;
        int i = 0;
        while(i < nums.length){
            if(nums[i] == nums[j]){
                i++;
            }else{
                i++;
                j++;
            }
        }
        return ++j;
    }
    public static int[] remove(int[] nums, int value){
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == value){
                for(int j = i; j < nums.length - 1; j++){
                    nums[j] = nums[j + 1];
                }
            }
        }
        return nums;
    }
}
