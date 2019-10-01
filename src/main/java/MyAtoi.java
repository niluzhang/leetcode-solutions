public class MyAtoi {
    /**
     * 题8：字符串转整数
     *
     * @param str 字符串
     * @return 整数
     */
    public int myAtoi(String str) {
        if (isTrimEmpty(str)) {
            return 0;
        }

        boolean spaceArea = true;
        boolean firstNonSpaceChar = false;
        boolean positive = true;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= str.length() - 1; i++) {
            char ch = str.charAt(i);
            if (!spaceArea || !Character.isSpace(ch)) {
                spaceArea = false;
                if (!firstNonSpaceChar) {
                    firstNonSpaceChar = true;
                    if (ch != '+') {
                        if (ch == '-') {
                            positive = false;
                        } else if (Character.isDigit(ch)) {
                            sb.append(ch);
                        } else {
                            return 0;
                        }
                    }
                } else {
                    if (Character.isDigit(ch)) {
                        sb.append(ch);
                    } else {
                        break;
                    }
                }
            }
        }

        if (isTrimEmpty(sb.toString())) {
            return 0;
        }

        try {
            int val = Integer.parseInt(sb.toString());
            return positive ? val : -val;
        } catch (Exception e) {
            return positive ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        }
    }

    private boolean isTrimEmpty(String s) {
        return s == null || s.trim().equals("");
    }
}
