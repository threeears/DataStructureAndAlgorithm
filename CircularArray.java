
public class CircularArray {
		private int[] array;
		private int front;
		private int rear;
		private int capacity;
		
		public CircularArray(){
			capacity = 3;
			array =  new int[capacity];
			front = rear = -1;
		}
		
		public int size(){
			if(isEmpty())
				return 0;
			return 1+(rear-front+capacity)%capacity;//size of the queue
		}
		
		private boolean isFull(){
			// one emtpy left considered as full
			return (rear+1)%capacity==front;
		}
		
		public boolean isEmpty(){
			return front==-1;
		}
		
		public void EnQueue(int data){
			if(isFull()){
				resize();				
			}
			
			if(isEmpty()){
				array[++rear]=data;
				front = rear;
			}
			else{
				rear = (rear+1)%capacity;
				array[rear]=data;
				
			}
			
		}
		
		public int Dequeue(){
			int result = 0;
			if(isEmpty()){
				System.out.println("nothing in the queue");
				return -1;
			}
			else{
				
				if(front==rear){//只剩下最后一个元素
					front=-1;
				}
				else{
					result = array[front];
					front = (front+1)%capacity;
				}
			}
			return result;
		}
		
		private void resize(){
			int s = size();//get the size of the current queue
			capacity = capacity*2;
			int[] arr = new int[capacity];
			int i = 0;
			while(s>0)
			{
				s--;
				arr[i]=array[front];
				i++;
				front=(front+1)%(capacity/2);
			}
			front = 0;
			rear = --i;	
			array = arr;
		}
		
		public static void main(String[] args){
			CircularArray test = new CircularArray();
			test.EnQueue(1);
			test.EnQueue(3);
			test.EnQueue(-1);
			test.Dequeue();
			test.EnQueue(-3);
			test.Dequeue();
			test.EnQueue(5);
			test.Dequeue();
			test.EnQueue(3);
			test.Dequeue();
			test.EnQueue(6);
			test.Dequeue();
			test.EnQueue(7);
			
			
			int i = test.size();
			System.out.println(i);
			
		}
}
