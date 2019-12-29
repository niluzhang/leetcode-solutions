package com.huawei.nlz.leetcode.solution;//将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
//
// 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
//
// L   C   I   R
//E T O E S I I G
//E   D   H   N
//
//
// 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
//
// 请你实现这个将字符串进行指定行数变换的函数：
//
// string convert(string s, int numRows);
//
// 示例 1:
//
// 输入: s = "LEETCODEISHIRING", numRows = 3
//输出: "LCIRETOESIIGEDHN"
//
//
// 示例 2:
//
// 输入: s = "LEETCODEISHIRING", numRows = 4
//输出: "LDREOEIIECIHNTSG"
//解释:
//
//L     D     R
//E   O E   I I
//E C   I H   N
//T     S     G
// Related Topics 字符串

public class ZigZagConversion {
    public String convert(String s, int numRows) {
        if (s == null || numRows <= 0) {
            throw new IllegalArgumentException("invalid input");
        }

        if (numRows == 1) {
            return s;
        }

        int len = s.length();

        Character[][] chs = new Character[numRows][len];

        int direction = 0;   // 用direction 0/1分别表示往下放和往右上方向放

        int x = 0;
        int y = 0;
        int currentCount = 0;  //在当前方向上已经放的字符的数目
        for (int i = 0; i <= len - 1; i++) {
            Character ch = s.charAt(i);
            if (direction == 0) {
                // 往下放
                if (currentCount < numRows - 1) {
                    chs[x++][y] = ch;
                    currentCount++;
                } else {
                    if (currentCount == numRows - 1) {
                        chs[x][y] = ch;

                        // 转换方向
                        direction = 1;
                        currentCount = 1;
                        x--;
                        y++;
                    }
                }
            } else {
                if (currentCount < numRows - 1) {
                    chs[x][y] = ch;
                    currentCount++;
                    x--;
                    y++;
                } else {
                    if (currentCount == numRows - 1) {
                        chs[x][y] = ch;

                        direction = 0;
                        currentCount = 1;
                        x++;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= numRows - 1; i++) {
            Character[] curLine = chs[i];
            for (int j = 0; j <= curLine.length - 1; j++) {
                if (curLine[j] != null) {
                    sb.append(curLine[j]);
                }
            }
        }

        return sb.toString();
    }
}
