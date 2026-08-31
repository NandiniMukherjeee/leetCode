class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {

        int minDistance = Integer.MAX_VALUE;
        int maxDistance = -1;

        ListNode prev = head;
        ListNode curr = head.next;

        int index = 1;
        int firstCritical = -1;
        int lastCritical = -1;

        while (curr.next != null) {

            int prevVal = prev.val;
            int currVal = curr.val;
            int nextVal = curr.next.val;

            // Check if current node is a critical point
            boolean isCritical =
                    (currVal > prevVal && currVal > nextVal) ||
                    (currVal < prevVal && currVal < nextVal);

            if (isCritical) {

                // First critical point
                if (firstCritical == -1) {
                    firstCritical = index;
                }

                // Calculate distance from previous critical point
                if (lastCritical != -1) {
                    int distance = index - lastCritical;
                    minDistance = Math.min(minDistance, distance);
                }

                lastCritical = index;
            }

            prev = curr;
            curr = curr.next;
            index++;
        }

        // Fewer than 2 critical points
        if (firstCritical == -1 || firstCritical == lastCritical) {
            return new int[]{-1, -1};
        }

        // Maximum distance = last - first
        maxDistance = lastCritical - firstCritical;

        return new int[]{minDistance, maxDistance};
    }
}