class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;

        int minIndex = 0;
        int maxIndex = 0;

        // Find indices of minimum and maximum
        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[minIndex]) {
                minIndex = i;
            }

            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }

        int left = Math.min(minIndex, maxIndex);
        int right = Math.max(minIndex, maxIndex);

        // Option 1: remove both from the front
        int fromFront = right + 1;

        // Option 2: remove both from the back
        int fromBack = n - left;

        // Option 3: remove one from each side
        int fromBothSides = (left + 1) + (n - right);

        return Math.min(fromFront, Math.min(fromBack, fromBothSides));
    }
}