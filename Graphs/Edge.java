package Graphs;

public class Edge implements Comparable<Edge> {
	
	private int mStart;
	private int mEnd;
	private int mWeight;
	
	public Edge(int left, int right, int weight){
		mStart = left;
		mEnd = right;
		mWeight = weight;
	}
	
	public int getStart(){
		return mStart;
	}
	
	public int getEnd(){
		return mEnd;
	}
	
	public int getWeight(){
		return mWeight;
	}

	@Override
	public int compareTo(Edge o) {
		return this.mWeight - o.mWeight;
	}
	
	@Override 
	public String toString(){
		return "(" + mStart +"/" + mEnd + "): " + mWeight;
	}
}

