package com.huawei.nlz.leetcode.solution;//给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
//
// 例如，给出 n = 3，生成结果为：
//
// [
//  "((()))",
//  "(()())",
//  "(())()",
//  "()(())",
//  "()()()"
//]
//
// Related Topics 字符串 回溯算法

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {
    public List<String> generateParenthesis(int n) {
        if (n <= 0)
            return null;
        List<String> list = new ArrayList<>();
        int l = n, r = n;
        generate(list, n, l, r, "");
        return list;
    }

    private static void generate(List<String> list, int n, int l, int r, String str) {
        if (str.length() == 2 * n) {
            list.add(str);
            return;
        }
        if (r > l) {
            if (l > 0) {
                generate(list, n, l - 1, r, str + "(");
            }
            if (r > 0) {
                generate(list, n, l, r - 1, str + ")");
            }
        } else {
            if (l > 0) {
                generate(list, n, l - 1, r, str + "(");
            }
        }
    }
}
