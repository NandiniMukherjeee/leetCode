from math import gcd
from typing import List


class Solution:
    def findKthSmallest(self, coins: List[int], k: int) -> int:
        coins.sort()

        # Remove redundant coins.
        # If a smaller coin divides this coin, all multiples of this
        # coin are already covered.
        filtered = []
        for coin in coins:
            if not any(coin % prev == 0 for prev in filtered):
                filtered.append(coin)

        coins = filtered
        n = len(coins)

        def count(x: int) -> int:
            """
            Count distinct positive integers <= x that are divisible
            by at least one coin.
            """
            result = 0

            for mask in range(1, 1 << n):
                lcm = 1
                bits = 0

                for i in range(n):
                    if mask & (1 << i):
                        lcm = lcm * coins[i] // gcd(lcm, coins[i])

                        # If LCM is already greater than x,
                        # this subset contributes 0.
                        if lcm > x:
                            break

                        bits += 1

                if lcm > x:
                    continue

                if bits % 2 == 1:
                    result += x // lcm
                else:
                    result -= x // lcm

            return result

        # The answer cannot exceed min(coins) * k
        left = 1
        right = coins[0] * k

        while left < right:
            mid = (left + right) // 2

            if count(mid) >= k:
                right = mid
            else:
                left = mid + 1

        return left