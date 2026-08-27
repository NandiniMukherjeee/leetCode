from collections import Counter

class Solution:
    def lexGreaterPermutation(self, s: str, target: str) -> str:
        cnt = Counter(s)
        n = len(s)
        prefix = []

        # Match target for as long as possible.
        for i in range(n):
            c = target[i]

            if cnt[c] > 0:
                prefix.append(c)
                cnt[c] -= 1
            else:
                break

        # Backtrack from right to left.
        for i in range(len(prefix), -1, -1):

            # Restore the character at this position.
            if i < len(prefix):
                restored = prefix.pop()
                cnt[restored] += 1

            # Try the smallest available character > target[i].
            if i < n:
                for code in range(ord(target[i]) + 1, ord('z') + 1):
                    ch = chr(code)

                    if cnt[ch] > 0:
                        cnt[ch] -= 1

                        # Append remaining characters in sorted order.
                        suffix = []
                        for code2 in range(ord('a'), ord('z') + 1):
                            c2 = chr(code2)
                            suffix.append(c2 * cnt[c2])

                        return ''.join(prefix) + ch + ''.join(suffix)

        return ""