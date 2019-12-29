package com.huawei.nlz.leetcode.solution;//罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。
//
// 字符          数值
//I             1
//V             5
//X             10
//L             50
//C             100
//D             500
//M             1000
//
// 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做 XXVII, 即为 XX + V + II 。
//
// 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
//
//
// I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
// X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
// C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
//
//
// 给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。
//
// 示例 1:
//
// 输入: "III"
//输出: 3
//
// 示例 2:
//
// 输入: "IV"
//输出: 4
//
// 示例 3:
//
// 输入: "IX"
//输出: 9
//
// 示例 4:
//
// 输入: "LVIII"
//输出: 58
//解释: L = 50, V= 5, III = 3.
//
//
// 示例 5:
//
// 输入: "MCMXCIV"
//输出: 1994
//解释: M = 1000, CM = 900, XC = 90, IV = 4.
// Related Topics 数学 字符串

import java.util.HashMap;
import java.util.Map;

public class Roman2Integer {
    public int romanToInt(String s) {
        if (s == null) {
            throw new IllegalArgumentException("invalid input.");
        }

        Map<String, Integer> characterValueMap = new HashMap<String, Integer>() {
            {
                put("M", 1000);
                put("CM", 900);
                put("D", 500);
                put("CD", 400);
                put("C", 100);
                put("XC", 90);
                put("L", 50);
                put("XL", 40);
                put("X", 10);
                put("IX", 9);
                put("V", 5);
                put("IV", 4);
                put("I", 1);
            }
        };

        Map<String, Integer> charAppearanceTimes = new HashMap<String, Integer>() {
            {
                put("M", 0);
                put("CM", 0);
                put("D", 0);
                put("CD", 0);
                put("C", 0);
                put("XC", 0);
                put("L", 0);
                put("XL", 0);
                put("X", 0);
                put("IX", 0);
                put("V", 0);
                put("IV", 0);
                put("I", 0);
            }
        };
        // 从串中提取各个成分
        for (int i = 0; i <= s.length() - 1; i++) {
            char ch = s.charAt(i);
            if (ch != 'C' && ch != 'X' && ch != 'I') {
                incrementChOccurTimes(charAppearanceTimes, String.valueOf(ch));
            } else {
                if (i <= s.length() - 2) {
                    char nextCh = s.charAt(i + 1);
                    int curVal = characterValueMap.get(String.valueOf(ch));
                    int nextVal = characterValueMap.get(String.valueOf(nextCh));
                    if (curVal < nextVal) {
                        String str = String.valueOf(ch) + nextCh;
                        incrementChOccurTimes(charAppearanceTimes, str);
                        i++; // i额外加一次
                    } else {
                        incrementChOccurTimes(charAppearanceTimes, String.valueOf(ch));
                    }
                } else {
                    // 到达串末尾，不可能与其他字符组合
                    incrementChOccurTimes(charAppearanceTimes, String.valueOf(ch));
                }
            }
        }

        int result = 0;
        for (Map.Entry<String, Integer> entry : charAppearanceTimes.entrySet()) {
            String ch = entry.getKey();
            Integer times = entry.getValue();
            result = result + characterValueMap.get(ch) * times;
        }

        return result;
    }

    private void incrementChOccurTimes(Map<String, Integer> occurTimesMap, String ch) {
        int times = occurTimesMap.get(ch);
        occurTimesMap.put(ch, times + 1);
    }
}
