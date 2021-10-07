

class BSTNode:
    def __init__(self, val=None):
        self.left = None
        self.right = None
        self.val = val
        self.size = 1


    def sizex(self):

        if self.val is None:
            return 0
        else:
            return self.size

    def isEmpty(self):
        return self.sizex() == 0

    def insert(self, val):
        if not self.val:
            self.val = val
            self.size = 1
            return

        if self.val == val:
            return

        if val < self.val:
            if self.left:
                self.left.insert(val)
            else:
                self.left = BSTNode(val)

        if val > self.val:
            if self.right:
                self.right.insert(val)
            else:
                self.right = BSTNode(val)
        
        if self.left is not None and self.right is not None:
            self.size = 1  + self.left.sizex() + self.right.sizex()
        elif self.right is not None:
            self.size = 1 + self.right.sizex()
        else:
        #elif self.left is not None:
            self.size = 1  + self.left.sizex()

        return


    def inorder(self):

        if self.isEmpty():
            print("Can't print an empty tree!")
            return -1

        z = self.inorder_rec(self.val, []).copy()

        y = [None] * ((len(z) * 2) - 1)
        flag = 0
        a=0

        for x in range(0, (len(z) * 2) - 1):
            if flag == 0:
                y[x] = z[a]
                a +=1
                flag = 1
            else:
                y[x] = ", "
                flag = 0

        return ''.join(y)



    def inorder_rec(self, x, stringx):

        if self is not None:

            if self.left is not None:
                self.left.inorder_rec(x,stringx)

            stringx.append(str(self.val))

            if self.right is not None:
                self.right.inorder_rec(x, stringx)

        return stringx

    def exists(self, val):

        if val == self.val:
            return True

        if val < self.val:
            if self.left == None:
                return False
            return self.left.exists(val)

        if self.right == None:
            return False
        return self.right.exists(val)

    def valCheck(self, val1, val2):

        if self.exists(val1):
            if self.exists(val2):
                return True
        return False

# Find LCA for 2 given vals
    def lca(self, n1, n2):
        flag = self.valCheck(n1,n2)

        if not flag:
            print("Invalid number(s)")
            return -1

    # If both n1 and n2 are smaller than self, then LCA
    # lies in left
        if(self.val > n1 and self.val > n2):
            return self.left.lca( n1, n2)
 
    # If both n1 and n2 are greater than self, then LCA
    # lies in right
        if(self.val < n1 and self.val < n2):
            return self.right.lca(n1, n2)
 
        return self.val
