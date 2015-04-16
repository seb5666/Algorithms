package Graphs;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Set;

public class MinimumSpanningTree {
	
	public Set<Edge> primsAlgorithm(int[][] G){
		
		int numVertices = G.length;
		
		//If there aren't any vertices return empty Set
		if(numVertices == 0){
			return new HashSet<Edge>();
		}
		
		//Array of boolean indicating if a vertex is included in A, A subset of MST
		boolean[] inMST = new boolean[numVertices];
		for(boolean b : inMST){
			b = false;
		}
		
		HashSet<Edge> MST = new HashSet<Edge>();
		
		PriorityQueue<Edge> edgesQueue = new PriorityQueue<Edge>();
		
		//add first edge
		edgesQueue.add(new Edge(0,0,0));
		
		while(!edgesQueue.isEmpty()){
			//Get lightest edge going outside A
			Edge lightestEdge = edgesQueue.poll();
			
			int newVertex = lightestEdge.getEnd();
			//set reached vertex as marked
			inMST[newVertex] = true;
			
			//add edge to MST, if it is not a circular edge
			if(lightestEdge.getEnd() != lightestEdge.getStart()){
				MST.add(lightestEdge);
			}
			
			//get all vertices the new edge is connected to
			for(int ver=0; ver<numVertices; ver++){
				//check if ver is already in MST
				if(!inMST[ver]){
					//check if newEdge is connected to particular edge
					if(G[newVertex][ver] != 0){
						
						//get weight of the edge
						int weight = G[newVertex][ver];
						Iterator<Edge> it = edgesQueue.iterator();
						
						//boolean flag to check if edge should be added to PQ or not
						boolean smallerWeight = true;
						
						//check if another edge goes to the same end, by going thourgh all edges in queue
						while(it.hasNext()){
							Edge e = it.next();
							
							//edge with same end in queue
							if(e.getEnd() == ver){
								
								//check if new weight is smaller
								if(weight < e.getWeight()){
									it.remove();
								} else {
									//weight is bigger, don't add to Queue
									smallerWeight = false;
								}
							}		
						}
						if(smallerWeight){
							edgesQueue.add(new Edge(newVertex, ver, weight));
						}
					}
				}
			}
		}
		
		return MST;
		
	}
	
	public static void main (String[] args){
		int[][] abc = new int[][]{
			{0,5,0,0,5,1},
			{5,0,5,0,0,1},
			{0,5,0,5,0,1},
			{0,0,5,0,5,1},
			{5,0,0,5,0,1},
			{1,1,1,1,1,0}
		};
		
		
		MinimumSpanningTree mst = new MinimumSpanningTree();
		
		HashSet<Edge> MST = (HashSet<Edge>) mst.primsAlgorithm(abc);
		for(Edge e : MST){
			System.out.println(e);
		}
	}
}

//Output
//(5/3): 1
//(5/1): 1
//(5/2): 1
//(0/5): 1
//(5/4): 1

