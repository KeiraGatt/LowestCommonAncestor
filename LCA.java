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
		
        int key;
        Node left, right;
 
        public Node(int item){
        	
            key = item;
            left = right = null;
            
        }
    }
	
	Node root; 															// Root node
	
	LCA(){root = null;}
	
	private void insert(int key) { root = insert(root, key); } 			// Call recursive method to insert node in BST
	
	private Node insert(Node root, int key) {
		
		if(root == null) root = new Node(key);							// If root is null -> set new value as root
		else if(key < root.key) root.left = insert(root.left, key);		// If new value less than current node -> go left
		else if (key > root.key) root.right = insert(root.right, key);	// If new value less than current node -> go left
		
		return root;
		
	}
	
	void inorder(){inorderRec(root);}									// Call recursive method to print BST inorder
 
    void inorderRec(Node root) {										// Traverse BST inorder & print keys
        if (root != null) {
        	
            inorderRec(root.left);
            System.out.println(root.key);
            inorderRec(root.right);
            
        }
    }
    
    private int lca(int key1, int key2) {							// Call recursive method if both keys are valid
    																// Else return -1
    	if(keyChecker(key1,key2)) return (lca(root, key1, key2)).key;
    		
    	System.out.println("Invalid key(s)");
    	return -1;
    	
    	
    }
    
    private boolean keyChecker(int key1, int key2) { 					// Check if both keys are in BST
    	
    	Node searchNode = search(key1);									// If first key not in tree return invalid
    	
    	if(searchNode == null) return false;
    	
    	searchNode = search(key2);										// If second key not in tree return invalid
    	
    	if(searchNode == null) return false;
    	else return true;												// Else search query is valid
    	
    }

    private Node search(int key) {									// Search BST for key
    	
    	Node currentNode = root;
    	
    	while(currentNode.key != key) {
    		
    		if(currentNode != null) {

    			if(currentNode.key > key) currentNode = currentNode.left;
    			else if(currentNode.key < key) currentNode = currentNode.right;
    			
    		}
    		
    		if(currentNode == null) return null;
 
    	}
    	
    	return currentNode;
    	
    }
    
    private Node lca(Node node, int key1, int key2) {								// Starting from top of tree, traverse down to find LCA
    	
    	
    	if(node == null) return null;												// If tree is empty return null
    	
    	if(node.key > key1 && node.key > key2) return lca(node.left, key1, key2); 	// If current node is greater than both keys -> go left
    	
    	if(node.key < key1 && node.key < key2) return lca(node.right, key1, key2); 	// If current node is less than both keys -> go right
    	
    	return node;
    }
    
    
    
    
    
	public static void main(String[] args) {


		LCA tree = new LCA();

		// Contruct BST
		
		tree.insert(3);
        tree.insert(5);
        tree.insert(6);
        tree.insert(2);
        tree.insert(7);
        tree.insert(4);
        tree.insert(0);
        tree.insert(1);
        tree.insert(8);
 
        // Print BST inorder
        
        tree.inorder();
        
        //Find Lowest Common Ancestor
        
        System.out.print("\nThe lowest common ancestor is: " + tree.lca(5,1));
        
	}

}

