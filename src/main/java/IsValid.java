import java.util.Stack;

public class IsValid {
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
