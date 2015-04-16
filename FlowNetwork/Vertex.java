package FlowNetwork;

public class Vertex {
	private String mLabel;
	
	public Vertex(String label){
		mLabel = label;
	}
	
	@Override
	public String toString(){
		return "Vertex: " + mLabel;
	}
}
