/*
 * Keira Gatt
 * Student #: 19334557
 * Date: 20.09.21
 * 
 */

/*
 * Construct a Binary Search Tree & find lowest common ancestor for 2 given keys
 */
public class LCA {

	
	class Node{ 														// Structure for node
		
        int key;														// Value in node
        Node left, right;												// Nodes to the left and right of itself
        int size;														// Number of nodes in its subtree
 
        public Node(int item){
        	
            key = item;
            left = right = null;
            
        }
    }
	
	Node root; 															// Root node
	
	LCA(){root = null;}
	
	public void insert(int key) { root = insert(root, key); } 			// Call recursive method to insert node in BST
	
	private Node insert(Node root, int key) {
		
		if(root == null) root = new Node(key);							// If root is null -> set new value as root
		else if(key < root.key) root.left = insert(root.left, key);		// If new value less than current node -> go left
		else if (key > root.key) root.right = insert(root.right, key);	// If new value less than current node -> go left
		
		root.size = 1 + size(root.left) + size(root.right);				// Get size of node by adding its left & right subtree to itself
		return root;
		
	}
	
	// Return number of key-value pairs in BST
    public int size() { return size(root); }
    
	// Return number of key-value pairs in BST rooted at x
    private int size(Node x) {
    	
        if (x == null) return 0;
        else return x.size;
        
    }
	
    public boolean isEmpty() { return size() == 0; }				// Check if tree is empty (size = 0)
    
	String inorder(){return inorderRec(root, "");}					// Call recursive method to print BST inorder
 
    String inorderRec(Node root, String strBuff) {					// Traverse BST inorder & print keys
    	
    	StringBuilder strBuilder = new StringBuilder(strBuff);
    	strBuilder = strBuilder.append("(");
    	
        if (root != null) {
        	
        	strBuilder.replace(0, strBuilder.length(), inorderRec(root.left, strBuilder.toString()));
        	strBuilder = strBuilder.append(root.key);	
        	strBuilder.replace(0, strBuilder.length(), inorderRec(root.right, strBuilder.toString()));
            
        }
        
        strBuilder = strBuilder.append(")");
        return strBuilder.toString();
        
    }
    
     int lca(int key1, int key2) {									// Call recursive method if both keys are valid
    																// Else return -1
    	if(keyChecker(key1,key2)) return (lca(root, key1, key2)).key;
    		
    	return -1;

    }
    
    private boolean keyChecker(int key1, int key2) { 					// Check if both keys are in BST
    	
    	if(isEmpty()) {
    		
    		System.out.println("Empty Tree!");
    		return false;
    	
    	}
    	
    	Node searchNode = search(key1);									// If first key not in tree return invalid
    	
    	if(searchNode == null) { 
    		
    		System.out.printf("Invalid key 1 : %d\n", key1);
    		return false;
    	
    	}
    	
    	searchNode = search(key2);										// If second key not in tree return invalid
    	
    	if(searchNode == null) { 
    		
    		System.out.printf("Invalid key 2 : %d\n", key2);
    		return false;
    	
    	}
    	else return true;												// Else search query is valid
    	
    	
    }

    private Node search(int key) {									// Search BST for key
    	
    	Node currentNode = root;
    	
    	while(currentNode.key != key) {
    		
    		if(currentNode != null) {

    			if(currentNode.key > key) currentNode = currentNode.left;
    			else currentNode = currentNode.right; 			// currentNode.key < key
    			
    		}
    		
    		if(currentNode == null) return null;
 
    	}
    	
    	return currentNode;
    	
    }
    
    private Node lca(Node node, int key1, int key2) {								// Starting from top of tree, traverse down to find LCA
    	
    	if(node.key > key1 && node.key > key2) return lca(node.left, key1, key2); 	// If current node is greater than both keys -> go left
    	
    	if(node.key < key1 && node.key < key2) return lca(node.right, key1, key2); 	// If current node is less than both keys -> go right
    	
    	return node;
    }
    
}

