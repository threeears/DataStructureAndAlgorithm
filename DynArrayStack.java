public class DynArrayStack(){
	private capacity;
	private int[] array;
	private int top;

	public DynArrayStack(){
		capacity = 1;
		array = new int[capacity];
		top = -1;
	}

	public boolean isEmpty(){
		return top==-1;
	}

	public boolean isFull(){
		return top==array.size();
	}

	public int pop(){
		//check empty or not
		if(isEmpty()){
			return Integer.MIN_VALUE;
		}
		else{
			return array[top--];
		}
	}

	public int push(int value){
		if(isFull()){
			// double size of the array
			doubleStack();
		}
		return array[++top]=value;
	}

	public doubleStack(){
		int newArray = new Array[capacity*2];
		capacity = capacity*2;
		// copy and array to another
		System.arraycopy(array,0,newArray,0,capacity);
		array = newArray;
	}

	public deleteStack(){
		top = -1;
	}
}