class Solution:
    def sumGame(self, num: str) -> bool:
        n = len(num)
        left_sum = right_sum = 0
        left_q = right_q = 0

        for i, ch in enumerate(num):
            if ch == '?':
                if i < n // 2:
                    left_q += 1
                else:
                    right_q += 1
            else:
                if i < n // 2:
                    left_sum += int(ch)
                else:
                    right_sum += int(ch)

        # Alice gets one extra move if the number of '?' is odd.
        if (left_q + right_q) % 2 == 1:
            return True

        # Bob wins only if the difference can be balanced exactly.
        return 2 * (left_sum - right_sum) != 9 * (right_q - left_q)