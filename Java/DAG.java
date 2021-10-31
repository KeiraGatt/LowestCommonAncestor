/*
 * Keira Gatt
 * Student #: 19334557
 * Date: 30.10.21
 * 
 */

/*
 * Construct a Directed Acyclic Graph & find lowest common ancestor for 2 given keys
 */

import java.util.*;

public class DAG {
	
	final int DFS_UP = 0;						// Bottom up traversal
	final int DFS_DWN = 1;						// Top down traversal
	
	static int vertCnt;							// Total no. of vertices
	static LinkedList<Integer> adj[];			// Array list for adjacencies
	static LinkedList<Integer> asc[];			// Array list for ascendants (parents)
	
	static int xList[];							// List of intersected vertices
	static int vertDepth[];						// Keeps track of vertex depth
	static ArrayList<Integer> uList, vList;		// Traversed u & v vertices 
	
	
	// Constructor
	
	DAG(int vCnt) {
		
		vertCnt = vCnt;
		
		adj = new LinkedList[vertCnt];
		asc = new LinkedList[vertCnt];
		vertDepth = new int[vertCnt];

		for (int i = 0; i < vertCnt; ++i) {
			vertDepth[i] = 0;
			adj[i] = new LinkedList();
			asc[i] = new LinkedList();
		}
	}

	// Add edges to graph and keep track of parents for each vertex
	
	 public void addEdge(int p, int d)
	{
		adj[p].add(d); 		// Add descendant to parent (for DFS Top Down)
		asc[d].add(p); 		// Add parent to descendant (for DFS Bottom Up)
	}

	// Check that key is valid
	
	private static boolean keyChecker(int key) {

		if(adj.length <= key ||  key < 0) return false;
			
		return true;
	}
	
	// Return size of subgraph for given key
	
    public int subgraphSize(int key) {
    	
    	if(!isEmpty() && keyChecker(key)) return adj[key].size();
    	
    	return -1;
    }
    
    // Return true if graph is empty
    
    public boolean isEmpty() { 
    	
    	for(LinkedList<Integer> x : adj) if(!x.isEmpty()) return false;
						
    	return true;
			
    }
    	
	
    // Return true if graph is cyclic 
 
    private boolean isCyclic() { 
          
        boolean[] visited = new boolean[vertCnt]; 
        boolean[] recursiveStack = new boolean[vertCnt]; 

        for (int i = 0; i < vertCnt; i++) {
        	
        	if (isCyclicUtil(i, visited, recursiveStack)) return true; 
        	
        }
            
        return false;
        
    } 
    
    private boolean isCyclicUtil(int i, boolean[] visited, boolean[] recursiveStack) {
          
        if (recursiveStack[i]) return true;
             
        if (visited[i]) return false; 
                     
        visited[i] = true; 
  
        recursiveStack[i] = true; 
        List<Integer> children = adj[i]; 
          
        for (Integer c: children) {
        	
            if (isCyclicUtil(c, visited, recursiveStack)) return true; 
                
        }
                  
        recursiveStack[i] = false; 
  
        return false; 
        
    } 
    
	// DFS Traversal Top Down
	
	private void DFS_DWN(int v, boolean visited[], int depthList[]) {
		
		visited[v] = true;		// Flag graph node as visited
		
		// Recurse adjacent vertices
		
		Iterator<Integer> i = adj[v].listIterator();
		while (i.hasNext()) {
			int n = i.next();
			if (!visited[n]) {
				depthList[n] = depthList[v] + 1;	// Set depth for child node
				DFS_DWN(n, visited, depthList);		// Traverse child node through recursion
			}
				
		}
	}
	
	
	// DFS Traversal Bottom Up
	
	private void DFS_UP(int v, boolean visited[], ArrayList<Integer> verList) {
		
		visited[v] = true;			// Flag graph node as visited
				
		verList.add(v);				// Record parent vertices in Array List as we go up

		// Recurse adjacent vertices
	
		Iterator<Integer> i = asc[v].listIterator();
		while (i.hasNext()) {
			int n = i.next();
			if (!visited[n]) {
				DFS_UP(n, visited, verList);	// Keep traversing parent nodes through recursion
			}
				
		}
	}

	
	// Depth-First Search (DFS) caller
	
	private void DFS(int mode, int vertex, ArrayList<Integer> verList, int depthList[]) {
		
		int i;
		boolean visited[] = new boolean[vertCnt];	// Create visited array (set to false by default)
		
		if(mode == DFS_UP) {
			
			DFS_UP(vertex, visited, verList);	// Traverse graph Bottom Up starting from vertex
			
		}
		else {
			
			for (i = 0; i < vertCnt; i++) {		// Traverse entire graph Top Down starting from root
				if (visited[i] == false) {
					DFS_DWN(i, visited, depthList);
				}
			}
			
		}
	}
	
	
	// Find intersection of set u-vertices and set v-vertices
	
	private void findIntersection() {
		
		Object[] arrObj;
		Set<Integer> vSet, xSet;
			
		xSet = createSet(uList);
		vSet = createSet(vList);
		
		xSet.retainAll(vSet);
	
		arrObj = xSet.toArray();
		xList = new int[arrObj.length];				// Create intersection int array
		
		for(int i = 0; i < arrObj.length; i++) {	// Copy intersection vertices to result array
			xList[i] = (int) arrObj[i];
		}
		
	}
	
	
	// Generate Hashes for use with Intersection function
	
	private static Set<Integer> createSet(ArrayList<Integer> verList) {
		HashSet<Integer> HS = new HashSet<Integer>();
		
		for(int i = 0; i < verList.size(); i++) {	// Add u-vertices or v-vertices to hash
			HS.add(verList.get(i));
		}
		
		return HS;
	}

		
	// Find LCA(u, v)

	private String findLCA(int u, int v) {
		
		int xDepth, LCADepth;
		String LCAStr;
		
		LCAStr = "";
		xDepth = LCADepth = 0;
		
		for(int i = 0; i < xList.length; i++) {
			
			if(xList[i] == u || xList[i] == v) {		// Skip if these are the u or v vertices
				continue;
			}

			xDepth = vertDepth[xList[i]];
						
			if(i == 0) {								// Start with first vertex from the intersection vertices
				LCADepth = xDepth;						
				LCAStr = String.valueOf(xList[i]);
				continue;
			}
			
			if(xDepth > LCADepth) {						// Retain vertex that is deeper in graph 
				LCADepth = xDepth;
				LCAStr = String.valueOf(xList[i]);
				continue;
			}
			
			if(xDepth == LCADepth) {					// Might have more than one vertex
				LCAStr = LCAStr + ", " + String.valueOf(xList[i]);
				continue;
			}
			
		}
		
		return LCAStr;
	}
	
	// Encapsulates all methods needed for findLCA()
	
	public String LCA(int key1, int key2) {
		
		uList = new ArrayList<Integer>();
		vList = new ArrayList<Integer>();
		
		if(!keyChecker(key1)) return "-1";
					
		if(!keyChecker(key2)) return "-1";
					
		if(isCyclic()) return "-1";
			
		// Do DFS Top Down and record Depth
		DFS(DFS_DWN, 0, null, vertDepth);
		
		// Do DFS Bottom up starting from key1 vertex and record parents
		DFS(DFS_UP, key1, uList, null);
		
		// Do DFS Bottom up starting from key2 vertex and record parents
        
		DFS(DFS_UP, key2, vList, null);
				
		// Find intersected vertices for key1 & key2
		        
		findIntersection();
		
		return findLCA(key1,key2);
		
	}
	
	public String printGraph() {
		
		String strBuff = "";
		
		StringBuilder strBuilder = new StringBuilder(strBuff);
		
		if(isEmpty()) return "Empty Graph";
		
		for (int i = 0; i < vertCnt; ++i) {
			
			if(i == vertCnt - 1) strBuilder.append(" Vertex " + i + " -> " + adj[i].toString());
			else if (i == 0) strBuilder.append("Vertex " + i + " -> " + adj[i].toString() + ", ");
			else strBuilder.append(" Vertex " + i + " -> " + adj[i].toString() + ", ");
			
		}
		
		return strBuilder.toString();
		
	}

}