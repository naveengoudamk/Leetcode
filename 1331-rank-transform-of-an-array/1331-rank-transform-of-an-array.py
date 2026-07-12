class Solution(object):
    def arrayRankTransform(self, arr):
        # Find unique elements and sort them
        sorted_unique = sorted(list(set(arr)))
        
        # Create a mapping from element to its rank (1-indexed)
        rank_map = {}
        for rank, num in enumerate(sorted_unique, 1):
            rank_map[num] = rank
            
        # Replace each element with its rank
        return [rank_map[num] for num in arr]

