#include <vector>
#include <unordered_map>
#include <algorithm>

class Solution {
public:
    int maxNumberOfFamilies(int n, std::vector<std::vector<int>>& reservedSeats) {
        // Map to store the bitmask of reserved seats for each row.
        // We only care about rows that have at least one reservation.
        std::unordered_map<int, int> row_masks;
        
        for (const auto& seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];
            // We only care about columns 2 through 9.
            if (col >= 2 && col <= 9) {
                // Set the bit corresponding to the seat column.
                // Shift by (col - 2) so column 2 is bit 0, column 3 is bit 1, etc.
                row_masks[row] |= (1 << (col - 2));
            }
        }
        
        // Initially assume all n rows can accommodate 2 families.
        int total_groups = 2 * n;
        
        // Define bitmasks for the three possible 4-seat blocks.
        // Seats 2,3,4,5   -> bits 0,1,2,3 -> binary 00001111 -> 15
        // Seats 4,5,6,7   -> bits 2,3,4,5 -> binary 00111100 -> 60
        // Seats 6,7,8,9   -> bits 4,5,6,7 -> binary 11110000 -> 240
        int left_block   = 15;  
        int middle_block = 60;  
        int right_block  = 240; 
        
        // Process only the rows that have reservations.
        for (const auto& [row, mask] : row_masks) {
            // A row with reservations starts with a penalty of 2 groups (deducted from total_groups)
            total_groups -= 2; 
            
            bool can_left   = (mask & left_block) == 0;
            bool can_right  = (mask & right_block) == 0;
            bool can_middle = (mask & middle_block) == 0;
            
            if (can_left && can_right) {
                // Both left and right blocks are completely free.
                total_groups += 2;
            } else if (can_left || can_right || can_middle) {
                // At least one of the three options is free.
                total_groups += 1;
            }
        }
        
        return total_groups;
    }
};
