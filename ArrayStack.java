public class ArrayStack{
	private int top;
	private int capacity;
	private int[] array;

	public ArrayStack(){
		capacity = 1;
		array = new int[capacity];
		top=-1;
	}

	public boolean isEmpty(){
		return top==-1;
	}

	public boolean isStackFull(){
	 	return top == capacity - 1;
	}

	public int pop(){
		// do not forget to check empty!
		if(isEmpty()==true)
			return Integer.MIN_VALUE;
		else
			return array[top--];
	}

	public int push(int value){
		// check full!
		if(isStackFull()==true){
			return Integer.MAX_VALUE;
		}
		else{
			array[++top]= value;
		}
	}

	public void deleteStack(){
		top = -1;
	}
}