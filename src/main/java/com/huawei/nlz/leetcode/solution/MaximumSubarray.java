package com.huawei.nlz.leetcode.solution;//给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
//
// 示例:
//
// 输入: [-2,1,-3,4,-1,2,1,-5,4],
//输出: 6
//解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
//
//
// 进阶:
//
// 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
// Related Topics 数组 分治算法 动态规划

public class MaximumSubarray {
    public int maxSubArray(int[] nums) {
        if (null == nums) {
            throw new IllegalArgumentException("invalid input.");
        }

        if (nums.length == 0) {
            return 0;
        }

        return maxSubSeq0(nums, 0, nums.length - 1);
    }

    private int maxSubSeq0(int[] arr, int lo, int hi) {
        /*
         * 递归的基准条件定义为lo和hi相等或者只差1的时候，
         * 因为所有的情况最终都会落到这两个场景上，且易于计算。
         */
        if (lo == hi) {
            return arr[lo];
        }
        if (hi - lo == 1) {
            return max3(arr[lo], arr[hi], arr[lo] + arr[hi]);
        }

        int mid = (lo + hi) / 2;
        int leftSideMax = maxSubSeq0(arr, lo, mid);
        int rightSideMax = maxSubSeq0(arr, mid + 1, hi);
        // 计算横跨中间的子序列的最大和，即必须选择mid与mid+1。
        // 以mid为基准往低地址端算，求出最大和，若为负则以0记。
        int maxLeftBorderSum = arr[mid];
        int currentLeftBorderSum = 0;
        for (int i = mid; i >= lo; i--) {
            currentLeftBorderSum += arr[i];
            if (currentLeftBorderSum > maxLeftBorderSum) {
                maxLeftBorderSum = currentLeftBorderSum;
            }
        }
        // 以mid+1为基准往高地址端算，求出最大和，若为负则以0记。
        int maxRightBorderSum = arr[mid + 1];
        int currentRightBorderSum = 0;
        for (int i = mid + 1; i <= hi; i++) {
            currentRightBorderSum += arr[i];
            if (currentRightBorderSum > maxRightBorderSum) {
                maxRightBorderSum = currentRightBorderSum;
            }
        }

        return max3(leftSideMax, rightSideMax, maxLeftBorderSum + maxRightBorderSum);
    }

    private int max2(int a, int b) {
        return Math.max(a, b);
    }

    private int max3(int a, int b, int c) {
        int abMax = Math.max(a, b);
        int abcMax = Math.max(abMax, c);
        return abcMax;
    }
}
