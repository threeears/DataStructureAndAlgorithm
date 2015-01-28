package graphics;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class Graph{
	
	public static void main(String[] args){
		Operations test = new Operations();
		test.gL.addEdge(1,2);
		test.gL.addEdge(1,4);
		test.gL.addEdge(2,5);
		test.gL.addEdge(2,4);
		test.gL.addEdge(4,6);
		test.gL.addEdge(4,7);
		test.gL.addEdge(5,6);
		test.gL.addEdge(6,7);
		test.gL.addEdge(3,1);
		test.gL.addEdge(3,7);
		test.TopologicalSort();
//		test.DFSIterative();
//		test.DFSRecursive(0);
//		test.BFS();
	}
	
	
	
	
}
class AdjMatrixGraph extends Graph{
	protected ArrayList<Vertex> vertices;
	private boolean adjMatrix[][];
	private int vertexCount;
	public AdjMatrixGraph(int vertexCount){
		this.vertexCount = vertexCount;
		vertices = new ArrayList<Vertex>();
		for(int i = 0;i<vertexCount;i++)
			vertices.add(new Vertex(i));
		adjMatrix = new boolean[vertexCount][vertexCount];		
	}
	
	public void addEdge(int i, int j)
	{
		if(i>=0 && i<this.vertexCount && j>=0 && j<this.vertexCount){
			adjMatrix[i][j]=true;
			adjMatrix[j][i]=true;//undirected graph
		}
	}
	
	public void removeEdge(int i, int j){
		if(i>=0 && i<this.vertexCount && j>=0 && j<this.vertexCount){
			adjMatrix[i][j]=false;
			adjMatrix[j][i]=false;//undirected graph
		}
	}
	public boolean isEdge(int i, int j){
		if(i>=0 && i<this.vertexCount && j>=0 && j<this.vertexCount){
			return adjMatrix[i][j];
		}
		return adjMatrix[i][j];
	}
	public int getNeighbour(int i){
		// O(V*V) for all nodes find their neighbors
		if(i>=0 && i<this.vertexCount){
			for(int m = 0;m<vertexCount;m++){
				if(adjMatrix[i][m]==true && vertices.get(i).visited==false ){
					return m;
				}
			}
		}
		return -1;
	}
}

class AdjListGraph extends Graph{
	protected List<Vertex> vertices;
	protected List<List<Vertex>> edges;
	protected int vertexCount = 0;
	public AdjListGraph(int vertexCount){
		this.vertexCount = vertexCount;
		vertices = new ArrayList<Vertex>();
		edges = new ArrayList<List<Vertex>>();//edge.get(i): list of neighbours of node i
		for(int i = 0;i<this.vertexCount;i++){
			Vertex node = new Vertex(i);
			vertices.add(node);
			edges.add(new ArrayList<Vertex>());			
		}
	}
	
	public void addEdge(int source,int destination){
		
			edges.get(source-1).add(vertices.get(destination-1));
		
	}

	public int getNeighbour(int i){
		// total O(E) for all nodes find their neighbors
		if(i>=0 && i<this.vertexCount){
			for(int m = 0;m<edges.get(i).size();m++){
				if(edges.get(i).get(m).visited==false)
					return m;
			}
		}
		return -1;
	}
}

class Vertex{
	public int label;
	public boolean visited;
	public Vertex(int lab){
		label = lab;
		visited = false;
	}
}
 class Operations{
	AdjMatrixGraph gM=new AdjMatrixGraph(7);
	AdjListGraph gL= new AdjListGraph(7);
	
	public Operations(){
		// initilize a graph using adjacent matrix or adjacent list
		
		gM.addEdge(1,2);
		gM.addEdge(1,4);
		gM.addEdge(2,5);
		gM.addEdge(2,4);
		gM.addEdge(4,6);
		gM.addEdge(4,7);
		gM.addEdge(5,6);
		gM.addEdge(6,7);
		gM.addEdge(3,1);
		gM.addEdge(3,7);

		
		
		
	}
	
	public void BFS(){
		// use a Queue, level-like traversal
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(0);
		while(!q.isEmpty()){
			gL.vertices.get(q.peek()).visited=true;
			System.out.println(q.peek());
			int neighbor = gL.getNeighbour(q.poll());
			if(neighbor==-1) continue;
			else q.add(neighbor);
		}
		// set visited to false
		for(int j = 0;j<gL.vertices.size();j++){
			gL.vertices.get(j).visited = false;
		}
		
	}
	
	public void DFSIterative(){
		// use a stack
		Stack<Integer> st= new Stack<Integer>();
		st.push(0);
		while(!st.isEmpty()){
			gL.vertices.get(st.peek()).visited=true;
			System.out.println(st.peek());
			int neighbor = gL.getNeighbour(st.pop());
			if(neighbor == -1) continue;
			else st.push(neighbor);
		}
		// set visited to false
				for(int j = 0;j<gL.vertices.size();j++){
					gL.vertices.get(j).visited = false;
				}
	}
	
	public void DFSRecursive( int start){
		int neighbor = gL.getNeighbour(start);
		System.out.println(neighbor);
		while(neighbor!=-1){
			DFSRecursive(neighbor);
			neighbor = gL.getNeighbour(start);
		}
	}
	
	public void TopologicalSort(){
		// like DFS, indegree array, each time find the INDGREE NOT OUTDEGREE count=0 node.if done check total number of nodes
		// Stack, used to check if there is a cycle in it.
		// O(E+V)
		int[] inCount = new int[gL.vertices.size()];// number of indegree links
		Stack<Integer> st = new Stack<Integer>();
		for(int i = 0;i<gL.vertices.size();i++){// how to calculate indgree ?? not outgoing links
			for(int j = 0; j<gL.edges.get(i).size();j++){
				//O(E);
				int nei = gL.edges.get(i).get(j).label;
				inCount[nei]++;
			}			
		}
		for(int i = 0;i<inCount.length;i++){
			if(inCount[i]==0)
				st.push(i);
		}
		while(!st.isEmpty()){
			//O(V)
			int cur = st.pop();
			System.out.println(cur+1);
			for(int i = 0;i<gL.edges.get(cur).size();i++){
				inCount[gL.edges.get(cur).get(i).label]--;
				if(inCount[gL.edges.get(cur).get(i).label]==0) st.push(gL.edges.get(cur).get(i).label);
				
			}
		}
		for(int i = 0; i<inCount.length;i++){
			if(inCount[i]!=0)
				System.out.println("has a cycle in it!");
		}
	}
	
	public void Dijkstra(){
		// like BFS
		int distance[] = new int[gL.vertexCount];
		for(int i = 0; i<distance.length; i++)
		{
			distance[i]=Integer.MAX_VALUE;
		}
		distance[0]=0;
		int temp = 0;
		HashSet<Integer> set = new HashSet<Integer>();
		while(set.size()< distance.length){
			// update neighbor of temp
			// find minimum distance in distanc[i] except elements in set
			//  push temp into set
			// update temp into minim
		}
		
		
	}
	
	public void MSTprim(Graph g){
		// like Dijkstra
		// randomly pick a node to start from, find all its neighbors, update distance[i], put minimum neighbor into set
		// update all neighbor of elements in set can reach to, find minimum one and put into set again...until set numbers
		// equal to node num
	}
	
	public void MSTkruskal(Graph g){
		// pick a smallest weight edge , union into a set
		// pick next smallest weight left union if edges in the set cannot form a cycle.
		//Prioritiy Queue
	}
	
}










