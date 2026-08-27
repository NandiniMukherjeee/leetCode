class Solution:
    def shortestBeautifulSubstring(self, s: str, k: int) -> str:
        ans = ""
        minLen = float('inf')

        n = len(s)

        for i in range(n):
            ones = 0

            for j in range(i, n):
                if s[j] == '1':
                    ones += 1

                if ones == k:
                    substring = s[i:j + 1]

                    if len(substring) < minLen:
                        minLen = len(substring)
                        ans = substring

                    elif len(substring) == minLen and substring < ans:
                        ans = substring

                # More than k ones means this substring
                # and all longer ones from i are invalid
                elif ones > k:
                    break

        return ans