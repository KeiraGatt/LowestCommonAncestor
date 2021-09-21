#Keira Gatt
# Student no. : 19334557
# Date: 21.09.21
# 
# 
# Construct a Binary Search Tree
# & find lowest common ancestor for 2 given keys
#  

class Node:
    def __init__(self, key):
        self.left = None
        self.right = None
        self.key = key

# Recursive method to insert node in BST
def insert(root, key):
    if root is None:
        root = Node(key)
        return Node(key)
    else:
        if root.key == key:
            return root
        elif root.key < key:
            root.right = insert(root.right, key)
        else:
            root.left = insert(root.left, key)
    return root
 
# Recursive method to print BST inorder
def inorder(root):
    if root:
        inorder(root.left)
        print(root.key)
        inorder(root.right)

# Recursively search BST from top for given key
def search(root,key):  
    # Base Cases: root is null or key is present at root
    if root is None or root.key == key:
        return root
 
    # Key is greater than root's key
    if root.key < key:
        return search(root.right,key)
   
    # Key is smaller than root's key
    return search(root.left,key)

# Find LCA for 2 given keys
def lca(root, n1, n2):   
    flag = keyCheck(root, n1,n2)

    if flag == 0:
        print("Check keys")
        return -1

    # Base Case
    if root is None:
        return None
 
    # If both n1 and n2 are smaller than root, then LCA
    # lies in left
    if(root.key > n1 and root.key > n2):
        return lca(root.left, n1, n2)
 
    # If both n1 and n2 are greater than root, then LCA
    # lies in right
    if(root.key < n1 and root.key < n2):
        return lca(root.right, n1, n2)
 
    return root.key

# Check if bothe keys are in BST
def keyCheck(root, key1, key2):
    currentNode = search(root, key1)

    if currentNode is None:
        return 0

    currentNode = search(root, key2)

    if currentNode is None:
        return 0
    else:
        return 1
 
r = Node(50)
insert(r, 30)
insert(r, 20)
insert(r, 40)
insert(r, 70)
insert(r, 60)
insert(r, 80)
 
# Print inoder traversal of the BST
inorder(r)

key1 = 40
key2 = 80
print "LCA of %d and %d is %d" %(key1, key2, lca(r, key1, key2))