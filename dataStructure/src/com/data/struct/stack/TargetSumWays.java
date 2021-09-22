package com.data.struct.stack;
/**
 * 目标和
 * 给你一个整数数组 nums 和一个整数 target 。
 *
 * 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
 *
 * 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
 *
 *
 * 思路： dfs 遍历每一个数字在+，-后的结果
 */
public class TargetSumWays {
    static  int[] nums={1,1,1,1,1};
    static int count=0;
    public static int findTargetSumWays(int[] nums, int target) {
        dfsTarget(nums,target,0,0);
        return count;
    }
    static void dfsTarget(int[]nums, int target, int index, int sum){
        int n=nums.length;
        if(target==sum && index==n){
            count++;
            return ;
        }
        if (index >= n) {
            return;
        }

        dfsTarget(nums,target,index+1,sum+nums[index]);
        dfsTarget(nums,target,index+1,sum-nums[index]);
    }

    public static void main(String[] args) {
        System.out.println(findTargetSumWays(nums,5));
    }

}
