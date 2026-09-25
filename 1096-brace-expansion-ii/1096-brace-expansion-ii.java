import java.util.*;

class Solution {
    private int index;

    public List<String> braceExpansionII(String expression) {
        this.index = 0;
        Set<String> expandedSet = parseExpression(expression);
        
        // Sort the unique words alphabetically
        List<String> result = new ArrayList<>(expandedSet);
        Collections.sort(result);
        return result;
    }

    // Handles the union operation (comma-separated terms)
    private Set<String> parseExpression(String s) {
        Set<String> result = new HashSet<>();
        
        while (index < s.length()) {
            Set<String> term = parseTerm(s);
            result.addAll(term);
            
            if (index < s.length() && s.charAt(index) == ',') {
                index++; // Skip ','
            } else {
                break; // End of expression or closing brace
            }
        }
        return result;
    }

    // Handles concatenation of consecutive factors
    private Set<String> parseTerm(String s) {
        Set<String> result = new HashSet<>();
        result.add(""); // Base element for Cartesian product
        
        while (index < s.length() && (Character.isLetter(s.charAt(index)) || s.charAt(index) == '{')) {
            Set<String> factor = parseFactor(s);
            Set<String> concatenated = new HashSet<>();
            
            for (String r : result) {
                for (String f : factor) {
                    concatenated.add(r + f);
                }
            }
            result = concatenated;
        }
        return result;
    }

    // Handles individual letters or nested brace expressions
    private Set<String> parseFactor(String s) {
        Set<String> result = new HashSet<>();
        
        if (s.charAt(index) == '{') {
            index++; // Skip '{'
            result = parseExpression(s);
            index++; // Skip '}'
        } else {
            result.add(String.valueOf(s.charAt(index)));
            index++; // Move past the letter
        }
        return result;
    }
}
