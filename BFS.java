/* BFS
 *
 *
 */
 class ANode{
	int adjvex;// the end point of arc
	ANode next;// the pointer
	public ANode(int id){
		adjvex = id;
		next = null;
	}
 }
 class Vertex{
	int id;
	boolean visited;
	ANode firstArc;
	
	public Vertex(int id){
		this.id = id;
		visited = false;
		firstArc = null;
	  }
}

class Graph{
	Vertex vertexList[];
	int vertexNum;
	int adjMat[][];
	int currVertex;
	Queue queue;
	Stack stack;
	public Graph(int num){
		vertexNum = num;
		vertexList = new Vertex[vertexNum];
		adjMat = new int[vertexNum][vertexNum];
		currVertex = 0;
		for(int i = 0;i< vertexNum;i++){
			for(int j= 0;j<vertexNum;j++){
					adjMat[i][j] = 0;
				}
			
			}
	}
	public void addVertex(int label){
			vertexList[currVertex++] = new Vertex(label);
		}
	public void addEdge(int start, int end){
			adjMat[start][end]  = 1;
			adjMat[end][start] = 1;
	}
	public void displayVertex(int label){
			System.out.println(vertexList[label].id);
	}
	public void MatToList()
	{
		for(int i = 0;i<vertexNum;i++){
			for(int j = 0;j<vertexNum;j++){
				if(adjMat[i][j]==1){
					ANode p = new ANode(j);
					p.next = vertexList[i].firstArc.next;// insert from head of the list
					vertexList[i].firstArc.next = p;
				}
			}
		}				
	}
	
	public void BFS(){
		
		queue.insert(vertexList[0].id);
		while(queue.isEmpty()==false){
			
			int temp = queue.remove();
			System.out.println(temp);
			vertexList[temp].visited = true;
			ANode p = vertexList[temp].firstArc;
			while(p.next!=null){
				p=p.next;
				queue.insert(p.adjvex);
			}//end while link
		}//end while queue
	}//end BFS
}

class Queue{
	private final int qsize = 20;
	private int[] queArray;
	private int front;
	private int rear;
	
	public Queue(){
		queArray = new int[qsize];
		front = 0;
		rear = 0;
	}
	
	public void insert(int j){
		
		queArray[rear] = j;
		rear = rear + 1;
		if (rear == qsize)
			rear = 0;
		
	}
	public int remove(){
		int temp = queArray[front];
		front = front + 1;
		if(front == qsize)
			front = 0;
		return temp;
	}
	
	public boolean isEmpty(){
		if (front == rear)
			return true;//true if empty
		return false;
	}
}

class Stack{
	private final int sSize;
	private int[] stackArray;
	private int top;
	
	public Stack(int num){
		sSize = num;
		top = -1;
		stackArray = new int[sSize];
	}
	public boolean isEmpty(){
		if (top==-1)
			return true;
		return false;
	}
	
	public void push(int j){
		top++;
		stackArray[top]=j;
		
	}
	public int pop(){
		int temp = stackArray[top];
		top--;
		return temp;
	}
}

 public class testEclipse {
	// create graph
	// bfs graph use recursion
	// bfs graph use queue
	// print search path
	 public static void main(String[] args){
		 
		 Graph g = new Graph(20);
		 g.addVertex(0);
		 g.addVertex(1);
		 g.addVertex(2);
		 g.addVertex(3);
		 g.addVertex(4);
		 g.addVertex(5);
		 g.addVertex(6);
		 g.addVertex(7);
		 
		 g.addEdge(0, 1);
		 g.addEdge(0, 5);
		 g.addEdge(1, 4);
		 g.addEdge(2, 5);
		 g.addEdge(2, 6);
		 g.addEdge(5, 6);
		 g.addEdge(2, 3);
		 g.addEdge(3, 6);
		 g.addEdge(3, 7);
		 g.addEdge(6, 7);
		 
		 g.displayVertex(6);
		 
		 
		 
	 }
	 
	 
	 

 }
 