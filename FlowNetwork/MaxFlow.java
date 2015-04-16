package FlowNetwork;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class MaxFlow {

	public static int fordFulkerson(FlowGraph g, int source, int target){
		//set the flow between all edges to 0
		g.initialiseFlow();
		
		//compute residual graph
		g.computeResidualGraph();
		//if there exists path between source and target in the residual graph
		while(g.findPath(source, target)){
			//find minimum capacity to augment
			int parent = g.getResidualGraphParent(target);
			int minCapacity = g.getResidualCapacity(parent, target);
			int v;
			while(parent != source){
				v = parent;
				parent = g.getResidualGraphParent(v);
				
				if(g.getResidualCapacity(parent, v) < minCapacity){
					minCapacity = g.getResidualCapacity(parent, v);
				}
			}
			
			//update flow
			parent = target;
			int new_flow;
			while(parent != source){
				v = parent;
				parent = g.getResidualGraphParent(v);
				//TODO update flow
				//if (parent, v) is in the edges of g, increment flow
				if(g.getAdj(parent).contains(v)){
					new_flow = g.getFlow(parent, v) + minCapacity;
					g.setFlow(parent, v, new_flow);
				}
				//else (v, parent) has to be in the edges and to be decremented
				else {
					new_flow = g.getFlow(v,parent) - minCapacity;
					g.setFlow(v, parent, new_flow);
				}
			}
			g.computeResidualGraph();
			System.out.println(g);
		}
		//calculate flow from source
		int flow = 0;
		for(int v : g.getAdj(source)){
			flow += g.getFlow(source, v);
		}
		return flow;
	}
	
	public static void main(String[] args){
		int n = FlowGraph.NO_EDGE;
		int[][] capacityMatrix = new int[][]{
				{0,3,n,2,3,n},
				{n,0,4,1,n,n},
				{n,n,0,1,n,1},
				{n,n,n,0,n,2},
				{n,n,n,1,0,2},
				{n,n,n,n,n,0}
		};
		FlowGraph g = new FlowGraph(capacityMatrix);
		int maxflow = MaxFlow.fordFulkerson(g,0,5);
		System.out.println("Max flow on g: " + maxflow);
	}
}
