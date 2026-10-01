import java.util.Stack;

class Solution {
    public boolean isValid(String s) {
        // Stack to store the opening brackets
        Stack<Character> stack = new Stack<>();
        
        // Iterate through each character in the string
        for (char c : s.toCharArray()) {
            // If it's an opening bracket, push it onto the stack
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } 
            // If it's a closing bracket
            else {
                // If stack is empty, there is no matching opening bracket
                if (stack.isEmpty()) {
                    return false;
                }
                
                // Pop the top element and check if it matches the current closing bracket
                char top = stack.pop();
                if ((c == ')' && top != '(') ||
                    (c == '}' && top != '{') ||
                    (c == ']' && top != '[')) {
                    return false;
                }
            }
        }
        
        // If the stack is empty, all brackets were properly matched
        return stack.isEmpty();
    }
}
