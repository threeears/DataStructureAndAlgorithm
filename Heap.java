
public class Heap {
	private int[] array;// for store nodes
	private int size;// get the heap size
	private int capacity;//get the capacity of heap;
	private int heap_type;// minHeap or MaxHeap, here default is max
	
	public Heap(int cap, int type){
		//O(1)
		this.capacity = cap;
		this.heap_type = type;
		array = new int[cap];
		size=0;
	}
	
	public int get(int i){
		//get ith element
		return array[i];
	}
	
	public int getParent(int i){
		//get the parent of ith node
		if(i<=0 || i>=this.capacity)
			return -1;
		return (i-1)/2;
	}
	
	public int leftChild(int i){
		//get the left child of ith node, return index
		return 2*i+1<this.size ? 2*i+1:-1;
	}
	
	public int rightChild(int i){
		// remember index start from 0 to size-1
		return 2*(i+1)<this.size ? 2*(i+1):-1;
	}
	
	public int getMaxmum(){
		if(this.size==0)
			return -1;
		return this.array[0];
	}
	
	public int getMinmum(){
		if(this.size==0)
			return -1;
		return this.array[0];
	}
	
	public boolean isLeaf(int i){
		if(i>= this.size/2)
			return true;
		return false;
	}
	
	public void maxHeapify(int i){
		//check from node i downward, if meet's heap's property
		// suppose all the children below the node is well structured
		// note that left and right could be both leafs or one of them is a leaf.
		//time:O(logN), space O(1)

		if(isLeaf(i)) return ;
		
		int left = leftChild(i);
		int right = rightChild(i);
		int max = 0;
		
		if(left == -1 ) max = right;
		else if (right == -1) max =  left;
		else{
			max = array[left]>array[right] ? left:right;
		}
		
		if(array[max]>array[i]){
			//exchange the max with current node
			int temp = array[max];
			array[max]=array[i];
			array[i] = temp;
		}
		maxHeapify(max);
	}
	
	public int deleteMax(){
		// in heap operation, you can only delete the root
		if(size==0) return -1;//do not forget to check!
		int max = array[0];
		array[0] = array[this.size-1];
		size--;// do not forget to reduce  the heap size
		maxHeapify(0);
		return max;
	}
	
	public void insert(int data){
		// insert from the bottom, each node has only one parent, so compare with its parent recursively
		// is enough to keep heap properties O(logn)
		if(size==capacity) 
			ResizeHeap();
		array[size] = data;
		size++;
		int current = size-1;
		int parent = getParent(current);
		while(parent!=-1 && data>this.array[parent]){
			this.array[current] = this.array[parent];
			current = parent;
			parent = getParent(current);
		}
		this.array[current]=data;
	}
	
	public void ResizeHeap(){
		//double the array space
		int[] newArray = new  int[capacity*2];
		this.capacity = capacity*2;
		if(this.array==null)
		{// the array is empty
			System.out.println("memory error");
			return;
		}
		System.arraycopy(this.array,0,newArray,0,size);
		array = newArray;		
	}
	
	public void destroyHeap(){
		this.size=0;
		this.array=null;
	}
	
	public void buildHeap(int A[], int n){
		//nlogN
		while(n>this.capacity){
			ResizeHeap();
		}
		for(int i = 0;i<n;i++)
			this.array[i]=A[i];
		this.size = n;
		for(int i = (n-1)/2;i>=0;i--){// bottom-up maxHeapify
			this.maxHeapify(i);//logN each
		}
	}
	public static void heapSort(Heap h,int A[],int n){
		//O(nlogN)
		if(n==0)
			System.out.println("you are building max Heap");
		
		h.buildHeap(A, A.length);
		int old_size = h.size;

		for(int i=A.length-1;i>0; i--){
			int temp = h.array[0];
			h.array[0]=h.array[h.size-1];
			h.array[h.size-1] = temp;
			h.size--;
			h.maxHeapify(0);
		}
		h.size=old_size;// increasing order in the h.array[], decreasing order in the output from heap each time.
	}
	public static void main(String[] args){
		int[] A = {3,2,1,7,0,4};
		Heap heap = new Heap(3,0);
		heap.insert(3);
		heap.insert(2);
		heap.insert(1);
		heap.insert(7);
		heap.insert(0);
		heap.insert(4);
		//heapSort(heap,A,0);
		for(int i = 0; i<heap.size;i++){		
			System.out.println(heap.array[i]);
		}
		
	}
}
