package com.huawei.nlz.leetcode.solution;//给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
//
// 你可以假设数组中无重复元素。
//
// 示例 1:
//
// 输入: [1,3,5,6], 5
//输出: 2
//
//
// 示例 2:
//
// 输入: [1,3,5,6], 2
//输出: 1
//
//
// 示例 3:
//
// 输入: [1,3,5,6], 7
//输出: 4
//
//
// 示例 4:
//
// 输入: [1,3,5,6], 0
//输出: 0
//
// Related Topics 数组 二分查找

public class SearchInsertPosition {
    public int searchInsert(int[] nums, int target) {
        if (null == nums) {
            throw new IllegalArgumentException("invalid input nums, it cannot be null.");
        }

        int lo = 0;
        int hi = nums.length - 1;

        while (lo <= hi) {
            int mid = (lo + hi) / 2;

            int meetFlag = meet(nums, mid, target);
            if (meetFlag == 0) {
                return mid;
            } else if (meetFlag == 1) {
                return mid + 1;
            } else if (meetFlag == -1) {
                return mid;
            } else if (meetFlag == 2) {
                lo = mid + 1;
            } else if (meetFlag == -2) {
                hi = mid - 1;
            } else if (meetFlag == 3) {
                return mid + 1;
            } else if (meetFlag == -3) {
                return mid - 1;
            }
        }

        return -1;
    }

    /**
     * target值是否"合适于"这个位置。
     * 相比于传统二分查找值，这个"合适"除了相等外，还有与比该位置大但比该位置后一个位置小，以及比该位置小但比该位置前一个位置大等两种场景。
     * 为了调用处能知道具体是何种"合适"，这里通过返回值来标记：
     * 1. 返回0，标识loc处与target直接相等
     * 2. 返回1，标识nums[loc] < target && nums[loc + 1] > target
     * 3. 返回-1，标识nums[loc] > target && nums[loc - 1] < target
     * 4. 返回2， 标识nums[loc] < target && nums[loc + 1] <= target，nums[loc + 1] == target通过返回值3来标识
     * 5. 返回-2，标识nums[loc] > target && nums[loc - 1] >= target，nums[loc - 1] == target通过返回值-3来标识
     *
     * @param nums   数组
     * @param loc    待判定位置
     * @param target 目标值
     * @return 参见上述
     */
    private int meet(int[] nums, int loc, int target) {
        if (nums[loc] == target) {
            return 0;
        }
        if (nums[loc] < target) {
            // 判定后一位
            int higher1 = loc + 1;
            if (higher1 > nums.length - 1) {
                // 已达数组末尾，直接插在higher1处，按约定，返回3
                return 3;
            }
            int tmp = nums[higher1];
            if (tmp > target) {
                return 1;
            } else if (tmp == target) {
                return 3;
            } else {
                return 2;
            }
        } else {
            // 判定前一位
            int lower1 = loc - 1;
            if (lower1 < 0) {
                return 0;
            }
            int tmp = nums[lower1];
            if (tmp < target) {
                return -1;
            } else if (tmp == target) {
                return -3;
            } else {
                return -2;
            }
        }
    }

    /* ------------草稿------------ */

    // (lo+lo)/2=lo
    // (lo+lo+1)/2=lo
    // (lo+lo+2)/2=lo+1
    // (lo+lo+3)/2=lo+1
    // (lo+lo+4)/2=lo+2
    // (lo+lo+5)/2=lo+2
    // ...
}
