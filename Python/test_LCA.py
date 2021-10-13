# Keira Gatt
# Student #: 19334557
# Date: 07.10.21

# Unit Tests for LCA.py
# Code coverage analysed using Coverage.py
# As of 07.10.21 code coverage = 100%

import unittest
from LCA import BSTNode


class TestBSTNode(unittest.TestCase):

    # Test is_empty()
    # Return:
    # If empty tree return true
    # Else return false

    def test_is_empty(self):
        bst = BSTNode()                                                # create instance of BSTNode class
        self.assertTrue(bst.is_empty(), "isEmpty() should be true")     # test with empty tree

        bst.insert(1)                                                  # add node to tree
        self.assertFalse(bst.is_empty(), "isEmpty() should be false")   # test with non-empty tree

    # Test bst_size()
    # Return:
    # If empty tree return 0
    # Else return no. of nodes in tree
    def test_size(self):
        bst = BSTNode()
        self.assertEqual(0, bst.bst_size())     # test with empty tree

        bst.insert(1)                           # add nodes to tree
        bst.insert(4)
        bst.insert(10)
        bst.insert(11)
        self.assertEqual(4, bst.bst_size())

    # Test inorder()
    # Return:
    # String of tree printed inorder
    # If tree is empty returns -1

    def test_inorder(self):
        bst = BSTNode()
        self.assertEqual(-1, bst.inorder(), "inOrder() : BST Keys Inorder") # test with empty tree

        nums = [1, 5, 3, 3, 4, 5, 6]  # add nodes
        for num in nums:
            bst.insert(num)

        self.assertEqual("1, 3, 4, 5, 6", bst.inorder(), "inOrder() : BST Keys Inorder")

    # Test lca(int n1 , int n2) & insert(int val)
    # Return:
    # LCA of the 2 keys given
    # If tree is empty or keys do not exist in tree return -1
    def test_lca_insert(self):
        bst = BSTNode()
        nums1 = [3, 5, 6, 2, 7, 4, 0, 1, 8]

        for num in nums1:
            bst.insert(num)

        self.assertEqual(3, bst.lca(5, 1)) # both keys in tree
        self.assertEqual(-1, bst.lca(50, 1))  # key 1 not in tree
        self.assertEqual(-1, bst.lca(5, 10))  # key 2 not in tree
        self.assertEqual(-1, bst.lca(50, 10))  # both keys not in tree

        bst2 = BSTNode()
        nums2 = [3, 5, 6, 2, 7, 4, 1, 8, 8, 400, 400, 0] # test with duplicate values
        for num in nums2:
            bst2.insert(num)

        self.assertEqual(400, bst2.lca(400, 400)) # root < key1 && root < key2

        bst3 = BSTNode()
        nums3 = [100, 4, 3]
        for num in nums3:
            bst3.insert(num)

        self.assertEqual(4, bst3.lca(4, 3))     # root > key1 && root > key2

        bst4 = BSTNode()
        nums4 = [4, 7]
        for num in nums4:
            bst4.insert(num)

        self.assertEqual(-1, bst4.lca(2, 7))    # key1 < root && no left subtree

        bst5 = BSTNode()                        # empty tree
        self.assertEqual(-1, bst5.lca(0, 0))


if __name__ == '__main__':
    unittest.main()