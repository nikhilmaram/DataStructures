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
		z.height = 1 + Math.max(z.left.height,z.right.height);
		zLeft.height = 1 + Math.max(zLeft.right.height, zLeft.left.height);
				
		return zLeft;
	}
	
	private Node leftRotate(Node z)
	{
		Node zRight = z.right;
		Node zRightLeft = zRight.left;
		//performt the rotation
		zRight.left = z;
		z.right = zRightLeft;
		// compute the heights
		z.height = 1 + Math.max(z.left.height, z.right.height);
		zRight.height = 1 + Math.max(zRight.left.height, zRight.right.height);
		
		return zRight;
	}
	public void put(int val)
	{
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
