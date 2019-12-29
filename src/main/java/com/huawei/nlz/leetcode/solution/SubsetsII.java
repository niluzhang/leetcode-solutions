package com.huawei.nlz.leetcode.solution;//给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
//
// 说明：解集不能包含重复的子集。
//
// 示例:
//
// 输入: [1,2,2]
//输出:
//[
//  [2],
//  [1],
//  [1,2,2],
//  [2,2],
//  [1,2],
//  []
//]
// Related Topics 数组 回溯算法

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SubsetsII {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        if (null == nums) {
            throw new IllegalArgumentException("invalid input");
        }

        int len = nums.length;
        if (len == 0) {
            return Collections.emptyList();
        }
        List<List<Integer>> result = new ArrayList<>();
        int[] selectFlag = new int[len];
        Arrays.fill(selectFlag, 0);
        // 记nums长度为len，生成0...0，0...1到1...1的所有可能。
        for (int i = 1; i <= Math.pow(2, len); i++) {
            result.add(genListBySelection(selectFlag, nums));
            selectFlag = plusOne(selectFlag);
        }

        List<List<Integer>> nonRepeatResult = new ArrayList<>();
        for (List<Integer> l : result) {
            boolean flag = false;
            for (List<Integer> nl : nonRepeatResult) {
                l.sort((n1, n2) -> n1 - n2);
                nl.sort((n1, n2) -> n1 - n2);
                if (l.equals(nl)) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                nonRepeatResult.add(l);
            }
        }
        return nonRepeatResult;
    }

    private List<Integer> genListBySelection(int[] selection, int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int idx = 0; idx <= nums.length - 1; idx++) {
            if (selection[idx] == 1) {
                list.add(nums[idx]);
            }
        }
        return list;
    }

    private int[] plusOne(int[] nums) {
        int len = nums.length;
        int processIdx = len - 1;
        while (processIdx >= 0) {
            nums[processIdx]++;
            if (nums[processIdx] == 1) {
                break;
            } else {
                nums[processIdx] = 0;
                processIdx--;
            }
        }
        return nums;
    }
}
