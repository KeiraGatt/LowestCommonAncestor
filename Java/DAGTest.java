/*
 * Keira Gatt
 * Student #: 19334557
 * Date: 30.10.21
 * 
 */

/*
 * JUnit 5 Tests for DAG.java
 * Code coverage analysed using EclEmma
 * As of 30.10.21 code coverage = 100%
 * 
 */

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;

class DAGTest {
	
	/*
	 * Test isEmpty():
	 * 
	 * Return:
	 * 
	 * If no nodes have been added to graph return true
	 * Else return false
	 * 
	 */
	@Test
	void testisEmpty() {
		
		DAG graph  = new DAG(2); 						// Create empty graph
		
		assertTrue("testisEmpty(): ", graph.isEmpty());	// Test with empty graph
		
														// Add node to graph
		graph.addEdge(0, 1);
		assertFalse("testisEmpty(): ", graph.isEmpty());
		
	}
	
	/*
	 * Test subgraphSize()
    * Return:
    * If empty graph || invalid key  return -1
    * Else return no. of nodes in graph
    * 
    */
	@Test
	void testSubgraphSize() {
		
		DAG graph  = new DAG(9);
		
		assertEquals("testSubgraphSize(): ", -1, graph.subgraphSize(10)); 	// Test with empty graph
		
		graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 4);
        graph.addEdge(1, 6);
        graph.addEdge(2, 4);
        graph.addEdge(2, 6);
        graph.addEdge(2, 3);  
        graph.addEdge(3, 6);
        graph.addEdge(6, 5);
        graph.addEdge(6, 7);
        graph.addEdge(7, 8);
		
	    assertEquals("testSubgraphSize(): ", 0, graph.subgraphSize(8)); 	// Test with leaf node
	    assertEquals("testSubgraphSize(): ", 2, graph.subgraphSize(1)); 
	    assertEquals("testSubgraphSize(): ", -1,  graph.subgraphSize(40)); // Test with invalid key
	    
		
	}
	
	/*
	 * Test LCA(int key1, int key2) & addEdge(int p, int d):
	 * 
	 * Return:
	 * 
	 * LCA of the 2 keys given
	 * If graph is empty || keys do not exist || cyclic graph return -1
	 * 
	 */
	@Test
	void testfindLCA_addEdge() {

		DAG graph1  = new DAG(9);
		
		// Insert nodes in graph
		 
		graph1.addEdge(0, 1);
        graph1.addEdge(0, 2);
        graph1.addEdge(1, 4);
        graph1.addEdge(1, 6);
        graph1.addEdge(2, 4);
        graph1.addEdge(2, 6);
        graph1.addEdge(2, 3);  
        graph1.addEdge(3, 6);
        graph1.addEdge(6, 5);
        graph1.addEdge(6, 7);
        graph1.addEdge(7, 8);
        
        assertEquals("LCA() ", "1, 2", graph1.LCA(4, 7)); 	// both keys in graph
        assertEquals("LCA() ", "0", graph1.LCA(2, 4)); 		// both keys in graph
        assertEquals("LCA() ", "3, 6", graph1.LCA(8, 7)); 		// both keys in graph
        assertEquals("LCA() ", "-1", graph1.LCA(50,1)); 	// key 1 not in graph
        assertEquals("LCA() ", "-1", graph1.LCA(-50,1)); 	// key 1 not in graph
        assertEquals("LCA() ", "-1", graph1.LCA(5,10)); 	// key 2 not in graph
        assertEquals("LCA() ", "-1", graph1.LCA(50,10)); 	// both keys not in graph
  
        
        DAG graph2  = new DAG(2);
		
        graph2.addEdge(0, 1);		// Test with cyclic graph
        graph2.addEdge(1, 0);
        
        assertEquals("LCA() ", "-1", graph2.LCA(1, 0));
        
        DAG graph3  = new DAG(3);
		
        graph3.addEdge(0, 1);		// Test with larger cyclic graph
        graph3.addEdge(1, 2);
        graph3.addEdge(2, 0);
        
        assertEquals("LCA() ", "-1", graph3.LCA(1, 0));
        
        
	}
	
	/*
	 * Test printGraph():
	 * 
	 * Return:
	 * 
	 * Printed graph 
	 * If graph is empty return "Empty Graph"
	 * 
	 */
	@Test
	void testprintGraph() {
		
		DAG graph  = new DAG(3);
		
		assertEquals("printGraph() ", "Empty Graph", graph.printGraph()); // Test with empty graph
		
		// Insert nodes in graph
		 
		graph.addEdge(0, 1);
		graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        
		assertEquals("printGraph() ", "Vertex 0 -> [1, 2],  Vertex 1 -> [2],  Vertex 2 -> []", graph.printGraph());
		
	}
	
}