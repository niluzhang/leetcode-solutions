package com.huawei.nlz.leetcode.solution;//给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。
//
// 说明:
//
//
// 初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
// 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
//
//
// 示例:
//
// 输入:
//nums1 = [1,2,3,0,0,0], m = 3
//nums2 = [2,5,6],       n = 3
//
//输出: [1,2,2,3,5,6]
// Related Topics 数组 双指针

public class MergeSortedArray {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (nums1 == null || nums2 == null) {
            throw new IllegalArgumentException("nums1 and nums2 cannot be null");
        }

        if (m < 0 || n < 0) {
            throw new IllegalArgumentException("m and n cannot be negative");
        }

        int idx = 0;
        int ptr1 = 0;
        int ptr2 = 0;

        int[] result = new int[m + n];

        while (ptr1 <= m - 1 && ptr2 <= n - 1) {
            int v1 = nums1[ptr1];
            int v2 = nums2[ptr2];
            if (v1 <= v2) {
                result[idx++] = v1;
                ptr1++;
            } else {
                result[idx++] = v2;
                ptr2++;
            }
        }

        if (ptr1 <= m - 1) {
            System.arraycopy(nums1, ptr1, result, idx, m - ptr1);
        }
        if (ptr2 <= n - 1) {
            System.arraycopy(nums2, ptr2, result, idx, n - ptr2);
        }

        System.arraycopy(result, 0, nums1, 0, m + n);
    }
}
