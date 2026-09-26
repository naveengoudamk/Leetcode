#include <string>
#include <vector>
#include <unordered_map>

using namespace std;

class Solution {
public:
    string evaluate(string s, vector<vector<string>>& knowledge) {
        // Step 1: Build a hash map for quick lookups
        unordered_map<string, string> dict;
        for (const auto& pair : knowledge) {
            dict[pair[0]] = pair[1];
        }
        
        string result = "";
        string key = "";
        bool inside_bracket = false;
        
        // Step 2: Iterate through the string
        for (char c : s) {
            if (c == '(') {
                inside_bracket = true;
                key = ""; // Reset key for the new bracket pair
            } else if (c == ')') {
                inside_bracket = false;
                // Look up the key in our dictionary
                if (dict.count(key)) {
                    result += dict[key];
                } else {
                    result += '?';
                }
            } else {
                if (inside_bracket) {
                    key += c; // Build the key
                } else {
                    result += c; // Build the normal text
                }
            }
        }
        
        return result;
    }
};
