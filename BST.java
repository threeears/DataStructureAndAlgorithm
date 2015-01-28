
public class BST {
	private int data;
	private BST left;
	private BST right;
	
	public BST(int data){
		this.data = data;
		this.left = null;
		this.right = null;
	}
	
	public int getData(){
		return data;
	}
	
	public void setData(int data){
		this.data = data;
	}
	
	public BST getLeft(){
		return left;
	}
	public BST getRight(){
		return right;
	}
	
	public static BST findElement(BST root, int data){
		// find an element in the bst, O(n), space O(n) for recursive stack
		// non-recursive take space O(1)
		if(root==null) return null;
		if(data < root.getData()) 
			return findElement(root.left, data);
		else if(data > root.getData())
			return findElement(root.right, data);
		else
			return root;
	}
	
	public static BST findMinimum(BST root){
		//left most
		if(root==null) return null;
		while(root.getLeft()!=null){
			root = root.getLeft();
		}
		return root;
	}
	
	public static BST findMaximum(BST root){
		// right most
		if(root==null) return null;
		while(root.getRight()!=null){
			root = root.getRight();
		}
		return root;
	}
	
	public static BST insertBST(BST root, int data){
		BST node = new BST(data);
		if(root==null)
			root = node;
		else{
			if(data<root.getData()){
				root.left = insertBST(root.getLeft(),data);
			}
			else if(data>root.getData()){
				root.right = insertBST(root.getRight(),data);
			}
		}		
		return root;		
	}
	
	public static BST deleteElement(BST root, int data){
		if(root.getData()==data){
			if(root.left==null && root.right==null){
				return null;//just delete the leaf node
			}
			else if(root.left!=null){
				// find the right-most of sub-left tree, add it to 
				// the root position
				int max = findMaximum(root.left).getData();
				if(root.left.left==null && root.left.right==null){
					root.left=null;
				}
				else{
					deleteElement(root.left,max);
				}
				root.setData(max);
				
			}
			else{
				// find the left-most of sub-right tree, add it to 
				// the root position
				//BST min = findMinimum(root.right); do not right like this, once root.right has changed, the BST address changed, therefore, the
				//value changed too.
				int min = findMinimum(root.right).getData();
				if(root.right.left==null && root.right.left==null){
					root.right=null;
				}
				else{
					deleteElement(root.getRight(),min);
				}
				root.setData(min);
			}
		}
		else if (root.getData()>data){
			root.left = deleteElement(root.getLeft(),data);
		}
		else{
			root.right = deleteElement(root.getRight(),data);
		}
		return root;
	}
	
	
	
	public static void main(String[] args){
		BST one = new BST(4);
		insertBST(one, 0);
		insertBST(one,6);
		insertBST(one,5);
		insertBST(one,8);
		insertBST(one,3);
		insertBST(one,2);
		insertBST(one,13);
		insertBST(one,9);
		insertBST(one,20);

		
		
		deleteElement(one, 8);
		System.out.println(findMinimum(one).getData());
		System.out.println(findMaximum(one).getData());
	}
}
