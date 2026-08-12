class Solution:
    def buildTree(self, inorder, postorder):
        # Store index of every value in inorder
        pos = {value: i for i, value in enumerate(inorder)}

        # Last element of postorder is the root
        post_index = len(postorder) - 1

        def build(left, right):
            nonlocal post_index

            # No elements
            if left > right:
                return None

            # Root
            root_value = postorder[post_index]
            post_index -= 1

            root = TreeNode(root_value)

            # Root position in inorder
            mid = pos[root_value]

            # Build RIGHT first
            root.right = build(mid + 1, right)

            # Then build LEFT
            root.left = build(left, mid - 1)

            return root

        return build(0, len(inorder) - 1)  