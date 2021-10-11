# Keira Gatt
# Student #: 19334557
# Date: 11.10.21
#
# Construct a Binary Search Tree & find lowest common ancestor for 2 given keys


class BSTNode:
    def __init__(self, val=None):  # Structure for node
        self.left = None  # Nodes to the left and right of itself
        self.right = None
        self.val = val  # Value in node
        self.size = 1  # Number of nodes in tis subtree (including itself)

    # Return number of key-value pairs in BST
    def bst_size(self):
        if self.val is None:
            return 0
        else:
            return self.size

    # Check if tree is empty (size = 0)
    def is_empty(self):
        return self.bst_size() == 0

    # Insert new node into BST
    def insert(self, val):
        if not self.val:  # If root is null -> set new value as root
            self.val = val
            self.size = 1
            return

        if self.val == val:  # If the value already exists in BST don't add new node
            return

        if val < self.val:  # If new value less than current node -> go left
            if self.left:  # If tree has a left subtree go left
                self.left.insert(val)
            else:  # Else create a left subtree
                self.left = BSTNode(val)

        if val > self.val:  # If new value less than current node -> go right
            if self.right:  # If tree has a right subtree go left
                self.right.insert(val)
            else:  # Else create a right subtree
                self.right = BSTNode(val)

        # Set size of tree
        if self.left is not None and self.right is not None:  # If left & right subtree exist
            self.size = 1 + self.left.bst_size() + self.right.bst_size()
        elif self.right is not None:  # If only right subtree exist
            self.size = 1 + self.right.bst_size()
        else:  # If only left subtree exist
            self.size = 1 + self.left.bst_size()

        return

    # Call recursive method to print BST inorder & format output string
    def inorder(self):
        if self.is_empty():  # Check for empty tree
            print("Can't print an empty tree!")
            return -1

        rec_res = self.inorder_rec(self.val, []).copy()  # Call Recursive inorder method & put result in array

        # Format output String
        output_res = [None] * ((len(rec_res) * 2) - 1)
        flag = 0
        a = 0

        for x in range(0, (len(rec_res) * 2) - 1):
            if flag == 0:
                output_res[x] = rec_res[a]
                a += 1
                flag = 1
            else:
                output_res[x] = ", "
                flag = 0

        return ''.join(output_res)

    def inorder_rec(self, x, str_buffer):

        if self is not None:

            if self.left is not None:
                self.left.inorder_rec(x, str_buffer)

            str_buffer.append(str(self.val))

            if self.right is not None:
                self.right.inorder_rec(x, str_buffer)

        return str_buffer

    # Check if value exists in BST
    def exists(self, val):
        if val == self.val:  # If value == current node
            return True

        if val < self.val:  # If value < current node go left
            if self.left is None:  # If there is no left subtree return false
                return False
            return self.left.exists(val)  # Else go left

        # If value > current node go right
        if self.right is None:  # If there is no right subtree return false
            return False
        return self.right.exists(val)  # Else go right

    # Check if both values are in BST
    def val_check(self, val1, val2):
        if self.exists(val1):
            if self.exists(val2):
                return True
        return False

    # Find LCA for 2 given values
    def lca(self, n1, n2):
        flag = self.val_check(n1, n2)  # Check both values are in BST

        if not flag:
            print("Invalid number(s)")
            return -1

        # If both n1 and n2 are smaller than self, then LCA
        # lies in left
        if (self.val > n1 and self.val > n2):
            return self.left.lca(n1, n2)

        # If both n1 and n2 are greater than self, then LCA
        # lies in right
        if (self.val < n1 and self.val < n2):
            return self.right.lca(n1, n2)

        return self.val
