import java.util.*;
public class BinarySearchTree<Key extends Comparable<Key>,Value> {
	
	private Node root;
	private class Node{
		private Key key;
		private Value val;
		private Node left,right;
		private int count;
		public Node(Key key,Value val)
		{
			this.key = key;	this.val = val;this.count = 0;
			this.left = null;this.right = null;
		}
	}

	public void put(Key key, Value val)
	{
		root = put(root,key, val);
	}
	
	private Node put(Node x, Key key, Value val)
	{
		if (x == null) return new Node(key,val);
		int compare = key.compareTo(x.key);
		if(compare < 0)
			x.left = put(x.left, key, val);
		else if (compare > 0)
			x.right = put(x.right,key,val);
		else
			x.val = val;
		x.count = 1+ size(x.left)+size(x.right);
		return x;
	}
	
	public Value get(Key key)
	{
		Node x = root;
		while(x!= null)
		{
			int compare = key.compareTo(x.key);
			if(compare < 0) x = x.left;
			else if (compare > 0) x = x.right;
			else return x.val;
		}
		return null;
	}
	
	public Key floor(Key key)
	{
		Node x = floor(root,key);
		if(x == null) return null;
		return x.key;
	}
	
	private Node floor(Node x, Key key)
	{
		Node r;
		if(x == null) return null;
		int comp = key.compareTo(x.key);
		if(comp == 0) return x;
		else if (comp < 0)
			return floor(x.left,key);
		else
		{
			r = floor(x.right,key);
			if(r == null) return x;
			else return r;
		}		
			
	}

	public int size()
	{
		return size(root);
	}

	private int size(Node x)
	{
		if (x == null) return 0;
		return x.count;
	}
	
	public int rank(Key key)
	{
		return rank(root,key);
	}

	private int rank(Node x, Key key)
	{
		if (x == null) return 0;
		int compare = key.compareTo(x.key);
		if(compare < 0) return rank(x.left,key);
		else if (compare > 0)
			return 1 + size(x.left) +rank(x.right,key);
		else
			return size(x.left);
	}

	public Iterable<Key> keys()
	{
		Queue<Key> q = new LinkedList<Key>();
		inorder(root,q);
		return q;
	}

	private void inorder(Node x, Queue<Key> q)
	{
		if(x==null) return;
		inorder(x.left,q);
		q.add(x.key);
		inorder(x.right,q);
	}
	
	public void deleteMin()
	{
		root = deleteMin(root);
	}
	
	private Node deleteMin(Node x)
	{
		if(x.left == null) return x.right;
		x.left = deleteMin(x.left);
		x.count = 1 + size(x.left) +size(x.right);
		return x;
	}

	public Node min(Node x)
	{
		//Node x = root;
		while(x.left!=null)
			x = x.left;
		return x;
	}
	
	public void delete(Key key)
	{
		root = delete(root,key);
	}
	
	private Node delete(Node x,Key key)
	{
		if(x==null) return null;
		int compare = key.compareTo(x.key);
		if (compare < 0) x.left = delete(x.left,key);
		else if(compare > 0) x.right = delete(x.right,key);
		else
		{
			if(x.left==null) return x.right;
			if(x.right == null) return x.left;
			
			// If both the children are present
			
			
			Node t = x;
			x = min(t.right);
			x.left = t.left;
			x.right = deleteMin(t.right);
		}
		x.count = 1 + size(x.left) + size(x.right);
		return x;
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		BinarySearchTree<Integer, Integer>   bst = new BinarySearchTree<>();
		bst.put(1, 1);
		bst.put(2,2);
		bst.put(3, 4);
		System.out.print(bst.rank(1));
		for(Integer i : bst.keys())
			System.out.print(i);

	}

}
