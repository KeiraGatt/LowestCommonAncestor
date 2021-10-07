# Keira Gatt
# Student #: 19334557
# Date: 07.10.21

# Unit Tests for LCA.py
# Code coverage analysed using Coverage.py
# As of 07.10.21 code coverage = 100%

import unittest
from LCA2 import BSTNode


class TestBSTNode(unittest.TestCase):


    # Test isEmpty()
    # Return:
    # If no nodes have been added to tree return true
    # Else return false

    def test_is_empty(self):

        bst = BSTNode()                                                # create instance of BSTNode class
        self.assertTrue(bst.isEmpty(), "isEmpty() should be true")     # test with empty tree
        bst.insert(1)                                                  # add node to tree
        self.assertFalse(bst.isEmpty(), "isEmpty() should be false")   # test with non-empty tree

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

        bst2 = BSTNode()  # make new instance
        nums2 = [10, 5, 1, 6, 9, 19, 17, 21]
        for num in nums2:
            bst2.insert(num)

        self.assertEqual(10, bst2.lca(5, 19))

        bst3 = BSTNode()
        nums3 = [3,5,6,2,7,4,1,8,8,400,400,0] # test with duplicate values
        for num in nums3:
            bst3.insert(num)

        self.assertEqual(400, bst3.lca(400, 400))

        bst4 = BSTNode()
        nums4 = [10,40,50]
        for num in nums4:
            bst4.insert(num)

        self.assertEqual(40, bst4.lca(40, 50))

        bst5 = BSTNode()
        nums5 = [100, 4, 3]
        for num in nums5:
            bst5.insert(num)

        self.assertEqual(4, bst5.lca(4, 3))

        bst6 = BSTNode()
        nums6 = [4, 7]
        for num in nums6:
            bst6.insert(num)

        self.assertEqual(-1, bst6.lca(2, 7))

        bst7 = BSTNode()                        # empty tree
        self.assertEqual(-1, bst6.lca(0, 0))


if __name__ == '__main__':
    unittest.main()


