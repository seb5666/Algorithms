package FlowNetwork;

public class Edge {
	
	private Vertex mStart;
	private Vertex mEnd;
	private int mFlow;
	private int mCapacity;
	
	public Edge(Vertex left, Vertex right, int capacity){
		mStart = left;
		mEnd = right;
		mCapacity = capacity;
	}
	
	public Vertex getStart(){
		return mStart;
	}
	
	public Vertex getEnd(){
		return mEnd;
	}
	
	public int getCapacity(){
		return this.mFlow;
	}
	
	public int getFlow(){
		return this.mFlow;
	}
	
	public void setFlow(int flow){
		this.mFlow = flow;
	}
	
	@Override 
	public String toString(){
		return "(" + mStart +"/" + mEnd + "): " + mFlow + "/" + mCapacity;
	}
}

