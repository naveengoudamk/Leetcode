class Solution {
public:
    int largestOverlap(vector<vector<int>>& img1, vector<vector<int>>& img2) {
        int n = img1.size();
        vector<pair<int, int>> v1, v2;
        
        // Step 1: Collect coordinates of all 1s
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (img1[i][j] == 1) v1.push_back({i, j});
                if (img2[i][j] == 1) v2.push_back({i, j});
            }
        }
        
        // Step 2: Count frequencies of each translation vector
        unordered_map<int, int> counts;
        int max_overlap = 0;
        
        for (auto& p1 : v1) {
            for (auto& p2 : v2) {
                int dr = p2.first - p1.first;
                int dc = p2.second - p1.second;
                // Encode the unique shift vector into a single integer key
                int key = dr * 100 + dc; 
                counts[key]++;
                max_overlap = max(max_overlap, counts[key]);
            }
        }
        
        return max_overlap;
    }
};
