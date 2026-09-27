import java.util.Stack;

class Solution {
    public String reverseParentheses(String s) {
        Stack<Character> stack = new Stack<>();
        
        for (char ch : s.toCharArray()) {
            if (ch == ')') {
                // Queue will help maintain the reversed order when we push back to stack
                StringBuilder sb = new StringBuilder();
                
                // Pop until matching opening parenthesis
                while (!stack.isEmpty() && stack.peek() != '(') {
                    sb.append(stack.pop());
                }
                
                // Pop the '(' itself
                if (!stack.isEmpty()) {
                    stack.pop();
                }
                
                // Push the reversed characters back onto the stack
                for (int i = 0; i < sb.length(); i++) {
                    stack.push(sb.charAt(i));
                }
            } else {
                // Push letters and '(' onto the stack
                stack.push(ch);
            }
        }
        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }

        return result.reverse().toString();
    }
}
