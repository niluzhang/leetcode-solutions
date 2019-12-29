package com.huawei.nlz.leetcode.solution;//给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
//
// 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
//
// 你可以假设 nums1 和 nums2 不会同时为空。
//
// 示例 1:
//
// nums1 = [1, 3]
//nums2 = [2]
//
//则中位数是 2.0
//
//
// 示例 2:
//
// nums1 = [1, 2]
//nums2 = [3, 4]
//
//则中位数是 (2 + 3)/2 = 2.5
//
// Related Topics 数组 二分查找 分治算法

public class MedianOfTwoSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1 == null) {
            return median(nums2);
        }
        if (nums2 == null) {
            return median(nums1);
        }

        int len1 = nums1.length;
        int len2 = nums2.length;
        int[] mergeArr = new int[len1 + len2];

        int ptr1 = 0;
        int ptr2 = 0;
        int idx = 0;
        while (ptr1 < len1 && ptr2 < len2) {
            int val1 = nums1[ptr1];
            int val2 = nums2[ptr2];
            if (val1 < val2) {
                mergeArr[idx++] = val1;
                ptr1++;
            } else {
                mergeArr[idx++] = val2;
                ptr2++;
            }
        }
        if (ptr1 < len1) {
            System.arraycopy(nums1, ptr1, mergeArr, idx, len1 - ptr1);
        }
        if (ptr2 < len2) {
            System.arraycopy(nums2, ptr2, mergeArr, idx, len2 - ptr2);
        }
        return median(mergeArr);
    }

    private double median(int[] arr) {
        if (null == arr) {
            throw new IllegalArgumentException("invalid input");
        }
        int len = arr.length;
        if (len % 2 == 0) {
            return (arr[len / 2 - 1] + arr[len / 2]) * 1.0 / 2;
        } else {
            return arr[len / 2];
        }
    }
}
