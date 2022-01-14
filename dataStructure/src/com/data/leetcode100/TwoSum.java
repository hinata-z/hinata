package com.data.leetcode100;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 *
 * 你可以按任意顺序返回答案。
 *
 输入：nums = [2,7,11,15], target = 9
 输出：[0,1]
 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。



 */
public class TwoSum {
    // 暴力输出发
    public static int[] twoSum(int[] nums, int target) {
        for(int i=0;i<nums.length-1;i++){
            for(int k=i+1;k<nums.length;k++){
                if(nums[i]+nums[k]==target){
                    return new int[]{i,k};
                }
            }
        }
        return null;
    }

    /**
     * Map 中去解决
     *
     */
    public static int[] twoSum1(int[] nums, int target) {
        Map<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<nums.length;i++){
           if(map.containsKey(target-nums[i])){
               return new int[]{map.get(target-nums[i]),i};
           }
           map.put(nums[i],i);
        }
        return null;
    }

    public static void main(String[] args) {
        int[] mums={3,2,4};
        System.out.println(Arrays.toString(twoSum1(mums,6)) );
    }

}
