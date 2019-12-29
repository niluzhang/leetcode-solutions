package com.huawei.nlz.leetcode.solution;//给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
//
// 注意：
//
// 答案中不可以包含重复的四元组。
//
// 示例：
//
// 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
//
//满足要求的四元组集合为：
//[
//  [-1,  0, 0, 1],
//  [-2, -1, 1, 2],
//  [-2,  0, 0, 2]
//]
//
// Related Topics 数组 哈希表 双指针

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FourSum {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        if (nums == null || nums.length < 4) {
            return Collections.emptyList();
        }

        List<List<Integer>> resultList = new ArrayList<>();

        List<Integer> numList = IntStream.of(nums).boxed().collect(Collectors.toList());
        Iterator<Integer> iterator = numList.iterator();
        while (iterator.hasNext()) {
            int tmp = iterator.next();
            iterator.remove();
            List<List<Integer>> lists = threeSum(numList, target - tmp);
            for (List<Integer> list : lists) {
                list.add(tmp);
                list.sort(Comparator.comparingInt(a -> a));
            }
            resultList.addAll(lists);
        }

        // 对结果List去重
        Set<List<Integer>> filterSet = new LinkedHashSet<>(resultList);

        return new ArrayList<>(filterSet);
    }

    private List<List<Integer>> threeSum(List<Integer> nums, int target) {
        if (nums == null || nums.size() < 3) {
            return Collections.emptyList();
        }

        nums = new ArrayList<>(nums);

        // 去除出现次数超出3次的超出部分的数字
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i <= nums.size() - 1; i++) {
            Integer times = map.get(nums.get(i));
            if (null == times) {
                map.put(nums.get(i), 1);
            } else {
                times++;
                if (times > 3) times = 3;
                map.put(nums.get(i), times);
            }
        }

        List<List<Integer>> resultList = new ArrayList<>();

        List<Integer> linkedList = new LinkedList<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Integer number = entry.getKey();
            Integer times = entry.getValue();
            for (int i = 0; i <= times - 1; i++) {
                linkedList.add(number);
            }
        }

        Iterator<Integer> iterator = linkedList.iterator();
        while (iterator.hasNext()) {
            Integer a = iterator.next();
            iterator.remove();

            Set<Integer> tmpSet = new HashSet<>();

            Iterator<Integer> tmpIterator = linkedList.iterator();
            while (tmpIterator.hasNext()) {
                Integer b = tmpIterator.next();
                int c = target - a - b;
                if (tmpSet.contains(c)) {
                    List<Integer> result = new ArrayList<>(3);
                    result.add(a);
                    result.add(b);
                    result.add(c);
                    result.sort(Comparator.comparingInt(o -> o));
                    resultList.add(result);
                }
                tmpSet.add(b);
            }
        }

        return resultList;
    }
}
