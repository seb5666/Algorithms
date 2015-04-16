package FlowNetwork;

import java.util.LinkedList;
import java.util.Queue;

public class FlowGraph {
	
	private int[][] mAdjacencyMatrix;
	private int mVertices;
	private int[][] mFlowMatrix;
	private int[][] mResidualAdjacencyMatrix;
	private int[] mResidualGraphParents;
	private boolean[] marked;
	
	public final static int NO_PARENT = -1;
	public final static int NO_EDGE = -1;
	
	public FlowGraph(int[][] adjacencyMatrix){
		this.mAdjacencyMatrix = adjacencyMatrix;
		this.mVertices = adjacencyMatrix.length;
		mFlowMatrix = new int[mVertices][mVertices];
		mResidualAdjacencyMatrix = new int[mVertices][mVertices];
		intitialiseParentsList();
	}

	private void intitialiseParentsList() {
		//create new array representing parents
		mResidualGraphParents = new int[mVertices];
		//initialise each vertex to have no parent;
		for(int i=0; i<mVertices; i++){
			mResidualGraphParents[i] = NO_PARENT;
		}
	}

	public LinkedList<Integer> getAdj(int v){
		LinkedList<Integer> adjList = new LinkedList<Integer>();
		for(int i=0; i<mVertices; i++){
			if(mAdjacencyMatrix[v][i] != NO_EDGE){
				adjList.add(i);
			}
		}
		return adjList;
	}
	
	public int getFlow(int u, int v){
		return mFlowMatrix[u][v];
	}
	
	public void setFlow(int u, int v, int flow){
		mFlowMatrix[u][v] = flow;
	}
	
	public void computeResidualGraph() {
		mResidualAdjacencyMatrix = new int[mVertices][mVertices]; //initialise all residual capacities to 0
		for(int u=0; u<mVertices; u++){
			for(int v=u; v<mVertices; v++){
				if(mAdjacencyMatrix[u][v] != NO_EDGE){
					mResidualAdjacencyMatrix[u][v] = mAdjacencyMatrix[u][v] - mFlowMatrix[u][v];
					mResidualAdjacencyMatrix[v][u] = mFlowMatrix[u][v];
				} 
				if(mAdjacencyMatrix[v][u] != NO_EDGE && v!=u){
					mResidualAdjacencyMatrix[v][u] =  mAdjacencyMatrix[v][u] - mFlowMatrix[v][u];
					mResidualAdjacencyMatrix[u][v] = mFlowMatrix[v][u];
				}
			}
		}
	}
	
	public int getResidualCapacity(int u, int v){
		return mResidualAdjacencyMatrix[u][v];
	}
	
	public int[][] getResidualGraph(){
		return mResidualAdjacencyMatrix;
	}
	
	public int getResidualGraphParent(int v){
		return mResidualGraphParents[v];
	}
	
	public String toString(){
		StringBuilder s = new StringBuilder();
		StringBuilder t = new StringBuilder();
		s.append("GRAPH: \n");
		t.append("Residual GRAPH: \n");
		for(int i=0; i<mVertices; i++){
			for(int v : getAdj(i)){
				if(mAdjacencyMatrix[i][v] != 0){
					s.append(i + "->" + v +": " + mFlowMatrix[i][v] + "/" + mAdjacencyMatrix[i][v] + "\n");
				}
			}
			for(int u=0; u<mVertices; u++){
				if(mResidualAdjacencyMatrix[i][u] != 0){
					t.append(i + "->" + u +": " + mResidualAdjacencyMatrix[i][u] + "\n");
				}
			}
		}
		return s.append(t).toString();
	}
	
	//reset the flow from each vertex to 0
	public void initialiseFlow(){
		mFlowMatrix = new int[mVertices][mVertices];
	}

	public boolean findPath(int source, int target) {
		
		Queue<Integer> queue = new LinkedList<Integer>();
		marked = new boolean[mVertices]; // initialise marked array to false

		queue.add(source);
		marked[source] = true;
		
		intitialiseParentsList();
		int v;
		while(!queue.isEmpty() && !marked[target]){
			v = queue.remove();
			for(int i=0; i<mVertices; i++){
				if((!marked[i]) &&( mResidualAdjacencyMatrix[v][i] > 0)){
					marked[i] = true;
					mResidualGraphParents[i] = v;
					queue.add(i);
				}
			}
		}
		return marked[target];
	}
	
}
