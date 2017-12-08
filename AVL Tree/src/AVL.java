import java.util.*;

public class AVL {
	
	private Node root;
	
	private class Node{
		private int value,height;
		private Node left;
		private Node right;
		public Node(int val)
		{
			this.value = val;
			this.height = 1;
		}
	}

	// If a root value is given when creating the Tree
	public AVL(int val)
	{
		this.root = new Node(val);
	}
	public AVL()
	{
		this.root = null;
	}

	private Node rightRotate(Node z)
	{
		Node zLeft = z.left;
		Node zLeftRight = zLeft.right;
		// perform the rotation
		zLeft.right = z;
		z.left = zLeftRight;
		// compute the heights
		z.height = 1 + Math.max(height(z.left),height(z.right));
		zLeft.height = 1 + Math.max(height(zLeft.right), height(zLeft.left));
				
		return zLeft;
	}
	
	private Node leftRotate(Node z)
	{
		Node zRight = z.right;
		Node zRightLeft = zRight.left;
		//perform the rotation
		zRight.left = z;
		z.right = zRightLeft;
		// compute the heights
		z.height = 1 + Math.max(height(z.left), height(z.right));
		zRight.height = 1 + Math.max(height(zRight.left), height(zRight.right));
		
		return zRight;
	}
	public void put(int val)
	{
		root = putValue(val,root);
	}
	private int height(Node x)
	{
		if (x == null) return 0;
		return x.height;
	}
	private Node putValue(int val,Node x)
	{
		if (x == null) return new Node(val);
		if (val < x.value) x.left = putValue(val,x.left);
		else if(val > x.value) x.right = putValue(val,x.right);
		else return x;
		
		int leftHeight = height(x.left);
		int rightHeight = height(x.right);
		x.height = 1 + Math.max(leftHeight,rightHeight);
		if(leftHeight - rightHeight > 1)
		{
			// Left Left case : Do Right Rotate
			if (val < x.left.value)
				return rightRotate(x);
			// Left Right Rotate : Do Left Rotate followed by right Rotate
			if(val > x.left.value)
			{
				// Left Rotate first
				x.left = leftRotate(x.left);
				return rightRotate(x);
			}
		}
		if(leftHeight - rightHeight < -1)
		{
			// Right Right case : Do Left Rotate
			if(val > x.right.value)
				return leftRotate(x);
			// Right Left case : Do Right Rotate followed by left Rotate
			if(val < x.right.value)
			{
				x.right = rightRotate(x.right);
				return leftRotate(x);
			}
		}
		
		return x;
	}
	
	public void inOrder()
	{
		inOrderPrint(root);
	}
	private void inOrderPrint(Node x)
	{
		if (x == null) return;
		inOrderPrint(x.left);
		System.out.println(x.value);
		inOrderPrint(x.right);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		AVL avl = new AVL();
		avl.put(4);
		avl.put(3);
		avl.put(2);
		avl.put(1);
		avl.put(5);
		avl.put(6);
		avl.put(7);
		avl.put(8);
		avl.inOrder();

	}

}
