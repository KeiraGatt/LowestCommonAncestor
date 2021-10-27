/*
 * Keira Gatt
 * Student #: 19334557
 * Date: 05.10.21 (Version 1)
 *
 * Added Tests to check if current implementation handles DAGs
 * Date: 27.10.21
 * 
 */

/*
 * JUnit Tests for LCA.java
 * Code coverage analysed using EclEmma
 * As of 05.10.21 code coverage = 100%
 * 
 */
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;

class LCATest {
	
	/*
	 * Test isEmpty():
	 * 
	 * Return:
	 * 
	 * If no nodes have been added to tree return true
	 * Else return false
	 * 
	 */
	@Test
	void testisEmpty() {
		
		LCA tree  = new LCA(); // Create empty tree
		
		assertEquals("testisEmpty(): ", true,  tree.isEmpty());
		
		tree.insert(1);	// add node to tree
		assertEquals("testisEmpty(): ", false,  tree.isEmpty());
	}
	
	/*
	 * Test size()
    * Return:
    * If empty tree return 0
    * Else return no. of nodes in tree
    * 
    */
	@Test
	void testSize() {
		
		LCA tree  = new LCA();
		
		assertEquals("testSize(): ", 0,  tree.size()); 	// Test with empty tree
		
		tree.insert(1);									// Add nodes to tree
		tree.insert(4);
		tree.insert(10);
		tree.insert(11);
		
		assertEquals("testSize(): ", 4,  tree.size());
		
	}
	
	/*
	 * Test inorder():
	 * 
	 * Return:
	 * 
	 * String of tree printed inorder
	 * If tree is empty returns "()" 
	 * 
	 */
	@Test
	void testInorder() {
		
		LCA tree  = new LCA();
		
		assertEquals("inOrder() : BST Keys Inorder", "()", tree.inorder()); // test with empty tree
		
		// contruct BST
		
		tree.insert(1);
		tree.insert(5);
		tree.insert(3);
		tree.insert(3);
		tree.insert(4);
		tree.insert(5);
		tree.insert(6);
				
		assertEquals("inOrder() : BST Keys Inorder", "(()1((()3(()4()))5(()6())))", tree.inorder()); 
		
	}
	
	/*
	 * Test LCA(int key1, int key2) & insert(int key):
	 * 
	 * Return:
	 * 
	 * LCA of the 2 keys given
	 * If tree is empty or keys do not exist in tree return -1
	 * 
	 */
	@Test
	void testLCA_Insert() {
		
		LCA tree1  = new LCA();
		
		 assertEquals("LCA() ", -1, tree1.lca(5,1)); 	// test with empty tree
		 
		 // Insert nodes in tree
		 
		 tree1.insert(3);
		 tree1.insert(5);
		 tree1.insert(6);
		 tree1.insert(2);
		 tree1.insert(7);
		 tree1.insert(4);
		 tree1.insert(0);
		 tree1.insert(1);
		 tree1.insert(8);
        
        assertEquals("LCA() ", 3, tree1.lca(5,1)); 	// both keys in tree
        assertEquals("LCA() ", -1, tree1.lca(50,1)); // key 1 not in tree
        assertEquals("LCA() ", -1, tree1.lca(5,10)); // key 2 not in tree
        assertEquals("LCA() ", -1, tree1.lca(50,10)); // both keys not in tree
        
        
        LCA tree2  = new LCA();
		
        tree2.insert(3);
        tree2.insert(5);
        tree2.insert(6);
        tree2.insert(2);
        tree2.insert(7);
        tree2.insert(4);
        tree2.insert(1);
        tree2.insert(8);
        tree2.insert(8);
        tree2.insert(400); 			// test with duplicate values
        tree2.insert(400);
        tree2.insert(0);
        
        assertEquals("LCA() ", 400, tree2.lca(400,400));		// root < key1 && root < key2
        
        LCA tree3  = new LCA();
		
        tree3.insert(100);
        tree3.insert(4);
        tree3.insert(3);
        
        assertEquals("LCA() ", 4, tree3.lca(4,3)); 				// root > key1 && root > key2
        
        LCA tree4  = new LCA();
		
        tree4.insert(4);
        tree4.insert(7);										// key1 < root && no left subtree
        
        assertEquals("LCA() ", -1, tree4.lca(2,7)); 
        
	}
	
	/*
	 * Test if LCA implementation can already handle DAGs:
	 * 
	 * Return:
	 * 
	 * LCA of the 2 keys given
	 * If tree is empty or keys do not exist in tree return -1
	 * 
	 */
	@Test
	void testLCA_DAG() {
		
		LCA tree  = new LCA();
		
        	tree.insert(1);		// Note there is no functionality in current implementation to specify direction
        	tree.insert(2);		// as this implementation uses an undirected graph
        	tree.insert(3);
        	tree.insert(4);
        	tree.insert(5);
        	tree.insert(6);
        	tree.insert(7);
        
        	assertEquals("LCA() ", 1, tree.lca(4,7));	// The LCA of 4 & 7 should give 1 & 2
        	assertEquals("LCA() ", 2, tree.lca(4,7));	// The current implementation outputs 4
        
	}

}
