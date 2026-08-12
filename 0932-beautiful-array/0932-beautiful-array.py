class Solution:
    def beautifulArray(self, n):
        ans = [1]

        for _ in range(n):
            new = []

            for x in ans:
                if 2 * x - 1 <= n:
                    new.append(2 * x - 1)

            for x in ans:
                if 2 * x <= n:
                    new.append(2 * x)

            if len(new) == n:
                return new

            ans = new

        return ans