package com.huawei.nlz.leetcode.solution;//报数序列是一个整数序列，按照其中的整数的顺序进行报数，得到下一个数。其前五项如下：
//
// 1.     1
//2.     11
//3.     21
//4.     1211
//5.     111221
//
//
// 1 被读作 "one 1" ("一个一") , 即 11。
//11 被读作 "two 1s" ("两个一"）, 即 21。
//21 被读作 "one 2", "one 1" （"一个二" , "一个一") , 即 1211。
//
// 给定一个正整数 n（1 ≤ n ≤ 30），输出报数序列的第 n 项。
//
// 注意：整数顺序将表示为一个字符串。
//
//
//
// 示例 1:
//
// 输入: 1
//输出: "1"
//
//
// 示例 2:
//
// 输入: 4
//输出: "1211"
//
// Related Topics 字符串

import java.util.ArrayList;
import java.util.List;

public class CountAndSay {
    public String countAndSay(int n) {
        // 1.     1
        //2.     11
        //3.     21
        //4.     1211
        //5.     111221
        //6.     312211
        //7.     13112221
        //8.     1113213211
        //9.     31131211131221
        //10.    13211311123113112211
        // 思路：把1-n的报数序列都依次生成。
        if (n <= 0) {
            throw new IllegalArgumentException("invalid input");
        }
        String[] seqs = new String[n];
        seqs[0] = "1";
        for (int i = 1; i <= n - 1; i++) {
            String prev = seqs[i - 1];
            List<Tuple2<Character, Integer>> tuple2s = new ArrayList<>();
            countNumbers(prev, tuple2s);
            seqs[i] = generateStr(tuple2s);
        }
        return seqs[n - 1];
    }

    private void countNumbers(String str, List<Tuple2<Character, Integer>> tuple2s) {
        char prevChar = str.charAt(0);
        int charCounter = 1;
        for (int i = 1; i <= str.length() - 1; i++) {
            char curChar = str.charAt(i);
            if (prevChar != curChar) {
                Tuple2<Character, Integer> tuple2 = new Tuple2<>(prevChar, charCounter);
                tuple2s.add(tuple2);
                prevChar = curChar;
                charCounter = 1;
            } else {
                charCounter++;
            }
        }
        Tuple2<Character, Integer> tuple2 = new Tuple2<>(prevChar, charCounter);
        tuple2s.add(tuple2);
    }

    private String generateStr(List<Tuple2<Character, Integer>> tuple2s) {
        StringBuilder sb = new StringBuilder();
        for (Tuple2<Character, Integer> tuple2 : tuple2s) {
            int number = tuple2.getVal1() - '0';
            int count = tuple2.getVal2();
            sb.append(count).append(number);
        }
        return sb.toString();
    }

    private static class Tuple2<T1, T2> {
        private T1 val1;
        private T2 val2;

        private Tuple2() {
        }

        private Tuple2(T1 val1, T2 val2) {
            this.val1 = val1;
            this.val2 = val2;
        }

        public T1 getVal1() {
            return val1;
        }

        public T2 getVal2() {
            return val2;
        }
    }
}
