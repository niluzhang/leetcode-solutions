package com.huawei.nlz.leetcode.solution;//给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
//
// 有效字符串需满足：
//
//
// 左括号必须用相同类型的右括号闭合。
// 左括号必须以正确的顺序闭合。
//
//
// 注意空字符串可被认为是有效字符串。
//
// 示例 1:
//
// 输入: "()"
//输出: true
//
//
// 示例 2:
//
// 输入: "()[]{}"
//输出: true
//
//
// 示例 3:
//
// 输入: "(]"
//输出: false
//
//
// 示例 4:
//
// 输入: "([)]"
//输出: false
//
//
// 示例 5:
//
// 输入: "{[]}"
//输出: true
// Related Topics 栈 字符串

import java.util.Stack;

public class ValidParentheses {
    /**
     * 题20：有效的括号
     *
     * @param s 字符串
     * @return true 括号合法; false 括号不合法
     */
    public boolean isValid(String s) {
        if (s == null) {
            throw new IllegalArgumentException();
        }
        if (s.length() == 0) {
            return true;
        }
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i <= s.length() - 1; i++) {
            char ch = s.charAt(i);
            if (isLeft(ch)) {
                stack.push(ch);
            } else {
                if (!stack.isEmpty()) {
                    Character tmp = stack.pop();
                    if (!match(ch, tmp)) {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    private boolean isLeft(char ch) {
        return ch == '(' || ch == '[' || ch == '{';
    }

    private boolean isRight(char ch) {
        return ch == ')' || ch == ']' || ch == '}';
    }

    private boolean match(char right, char left) {
        if (right == ')' && left == '(') return true;
        if (right == ']' && left == '[') return true;
        if (right == '}' && left == '{') return true;
        return false;
    }
}
