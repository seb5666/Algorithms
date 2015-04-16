package DataStructures;

/**
 * 
 * @author sebastianborgeaud
 * A deque of fixed capacity stored using a circular buffer on an array
 */
public class CircularDeque{

	private int[] mData;
	private int mLength;
	private int iStart;
	private int iEnd;
	private boolean isEmpty;
	
	public CircularDeque(int length){
		mLength = length;
		mData = new int[mLength];
		iStart = mLength/2;
		iEnd = iStart;
		isEmpty = true;
	}
	
	public void putFront(int toInsert) throws FullDequeException{
		if(!isEmpty && iEnd == iStart){
			throw new FullDequeException();
		} 
		
		if(iStart == 0){
			iStart = mLength -1;
		} else {
			iStart--;
		}
		mData[iStart] = toInsert;
		isEmpty = false;
		
	}
	
	public void putBack(int toInsert) throws FullDequeException{
		if(!isEmpty && iEnd == iStart){
			throw new FullDequeException();
		} 
		if(iEnd == mLength){
			iEnd = 1;
		} else {
			iEnd++;
		}
		mData[iEnd-1] = toInsert;
		isEmpty = false;
	}
	
	public int getFront() throws EmptyDequeException{
		if(isEmpty){
			throw new EmptyDequeException();
		}
		int toDelete = mData[iStart];
		if(iStart == mLength -1){
			iStart = 0;
		} else {
			iStart ++;
		}
		if(iStart == iEnd){
			isEmpty = true;
		}
		return toDelete;
	}
	
	public int getBack() throws EmptyDequeException{
		if(isEmpty){
			throw new EmptyDequeException();
		}
		int toDelete = mData[iEnd-1];
		if(iEnd == 0){
			iEnd = mLength;
		} else {
			iEnd --;
		}
		if(iStart == iEnd){
			isEmpty = true;
		}
		return toDelete;
	}
	
	public String toString(){
		String s = "(";
		if(iEnd > iStart){
			for(int i=iStart; i<iEnd; i++){
				s += mData[i] + ",";
			}
		} else if(iEnd < iStart){
			for(int i=iStart; i<mLength; i++){
				s += mData[i] + ",";
			}
			for(int i=0; i<iEnd; i++){
				s += mData[i] + ",";
			}
		} else {
			for(int i=iStart; i<mLength; i++){
				s += mData[i] + ",";
			}
			for(int i=0; i<iEnd; i++){
				s += mData[i] + ",";
			}
		}
		return s + ")";
	}
	
	public static void main(String[] args) {
		CircularDeque deque = new CircularDeque(10);
		try{
			deque.putFront(1);
			deque.putFront(2);
			deque.putFront(3);
			deque.putFront(4);
			deque.putFront(5);
			deque.putFront(6);
			deque.putFront(7);
			deque.putFront(8);
			deque.putBack(9);
			deque.putBack(0);
			System.out.println(deque);
			System.out.println(deque.getBack());
			System.out.println(deque);
			System.out.println(deque.getFront());
			System.out.println(deque);
			System.out.println(deque.getBack());
			System.out.println(deque);
			System.out.println(deque.getFront());
			System.out.println(deque);
		} catch(FullDequeException | EmptyDequeException e){
			System.out.println("Error while manipulating the deque");
		}
	}
	
//	Output
//	(8,7,6,5,4,3,2,1,9,0,)
//	0
//	(8,7,6,5,4,3,2,1,9,)
//	8
//	(7,6,5,4,3,2,1,9,)
//	9
//	(7,6,5,4,3,2,1,)
//	7
//	(6,5,4,3,2,1,)
}
