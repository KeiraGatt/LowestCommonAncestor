/*
 * Keira Gatt
 * Student #: 19334557
 * Date: 05.10.21
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
		
		tree.insert(3);
		tree.insert(5);
		tree.insert(6);
		tree.insert(2);
		tree.insert(7);
		tree.insert(4);
		tree.insert(0);
		tree.insert(1);
		tree.insert(8);
				
		assertEquals("inOrder() : BST Keys Inorder", "(((()0(()1()))2())3((()4())5(()6(()7(()8())))))", tree.inorder()); 
		
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
        
        
        // tree2, tree3, tree4 & tree5 test all possible conditions for LCA & insert()
        
        LCA tree2  = new LCA();
		
        tree2.insert(10);
        tree2.insert(5);
        tree2.insert(1);
        tree2.insert(8);
        tree2.insert(6);
        tree2.insert(9);
        tree2.insert(19);
        tree2.insert(17);
        tree2.insert(21);
        
        assertEquals("LCA() ", 10, tree2.lca(5,19));
        
        LCA tree3  = new LCA();
        tree3.insert(3);
        tree3.insert(5);
        tree3.insert(6);
        tree3.insert(2);
        tree3.insert(7);
        tree3.insert(4);
        tree3.insert(1);
        tree3.insert(8);
        tree3.insert(8);  
        tree3.insert(400);
        tree3.insert(400);
        tree3.insert(0);
        
        LCA tree4  = new LCA();
		
        tree4.insert(10);
        tree4.insert(40);
        tree4.insert(50);
        
        assertEquals("LCA() ", 40, tree4.lca(40,50)); 
        
        LCA tree5  = new LCA();
		
        tree5.insert(100);
        tree5.insert(4);
        tree5.insert(3);
        
        assertEquals("LCA() ", 4, tree5.lca(4,3)); 
        
	}
	

}
