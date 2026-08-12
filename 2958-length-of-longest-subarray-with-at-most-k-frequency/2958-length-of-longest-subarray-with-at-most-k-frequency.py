class Solution:
    def maxSubarrayLength(self, nums, k):
        freq = {}
        left = 0
        ans = 0

        for right in range(len(nums)):
            # Add current element
            freq[nums[right]] = freq.get(nums[right], 0) + 1

            # If frequency becomes greater than k
            while freq[nums[right]] > k:
                freq[nums[left]] -= 1
                left += 1

            # Current window is good
            ans = max(ans, right - left + 1)

        return ans