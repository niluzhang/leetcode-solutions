package com.huawei.nlz.leetcode.solution;

public class MaximumSubarray2 {
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
         * 递归的基准条件定义为lo和hi相等的时候，
         * 因为所有的情况最终都会落到这两个场景上，且易于计算。
         */
        if (lo == hi) {
            return arr[lo];
        }

        int mid = (lo + hi) / 2;
        int leftSideMax = maxSubSeq0(arr, lo, mid);
        int rightSideMax = maxSubSeq0(arr, mid + 1, hi); // 注意由于基准条件是lo和hi相等，所以这里mid要加1，否则会陷入无限递归。
        // 计算横跨中间的子序列的最大和，即必须选择mid与mid+1。
        // 以mid为基准往低地址端算，求出最大和。
        int maxLeftBorderSum = arr[mid];
        int currentLeftBorderSum = 0;
        for (int i = mid; i >= lo; i--) {
            currentLeftBorderSum += arr[i];
            if (currentLeftBorderSum > maxLeftBorderSum) {
                maxLeftBorderSum = currentLeftBorderSum;
            }
        }
        // 以mid+1为基准往高地址端算，求出最大和。
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

    // ----------------草稿-----------------
    // range: [lo, lo+1], mid: lo
    // range: [lo, lo+2], mid: lo+1
    // range: [lo, lo+3], mid: lo+1
    // range: [lo, lo+4], mid: lo+2
}
