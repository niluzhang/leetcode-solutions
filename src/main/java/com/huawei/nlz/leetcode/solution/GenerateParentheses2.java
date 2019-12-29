package com.huawei.nlz.leetcode.solution;

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses2 {
    public List<String> generateParenthesis(int n) {
        /*
         * 在生成串的过程中始终保持当前)数小于等于当前(数即合法。(当然，到最后一定是二者数量相等)
         */
        List<String> ans = new ArrayList();
        backtrack(ans, "", 0, 0, n);
        return ans;
    }

    /**
     * @param ans   存放结果串的集合
     * @param cur   当前构造出来的串
     * @param open  左括号数
     * @param close 右括号数
     * @param max   最大括号数
     */
    public void backtrack(List<String> ans, String cur, int open, int close, int max) {
        if (cur.length() == max * 2) {
            ans.add(cur);
            return;
        }

        if (open < max) {
            // 只要左括号数没到达最大那么就可以添加一个左括号
            backtrack(ans, cur + "(", open + 1, close, max);
        }
        if (close < open) {
            // 如果当前右括号数比左括号数少，那么就可以添加一个右括号
            backtrack(ans, cur + ")", open, close + 1, max);
        }
    }
}
