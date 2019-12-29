package com.huawei.nlz.leetcode.solution;//给定一个仅包含大小写字母和空格 ' ' 的字符串，返回其最后一个单词的长度。
//
// 如果不存在最后一个单词，请返回 0 。
//
// 说明：一个单词是指由字母组成，但不包含任何空格的字符串。
//
// 示例:
//
// 输入: "Hello World"
//输出: 5
//
// Related Topics 字符串

public class LengthOfLastWord {
    public int lengthOfLastWord(String s) {
        if (s == null) {
            return 0;
        }
        // 思路：一趟扫描，统计连续字母的长度，遇到空格又遇到字母后清空重新统计。
        int counter = 0;
        boolean meetSpace = true;
        for (int i = 0; i <= s.length() - 1; i++) {
            char ch = s.charAt(i);
            if (ch == ' ') {
                meetSpace = true;
            } else if (Character.isAlphabetic(ch) && meetSpace) {
                counter = 1;
                meetSpace = false;
            } else if(Character.isAlphabetic(ch) && !meetSpace){
                counter++;
            }
        }

        return counter;
    }
}
