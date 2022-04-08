package sait.wordtracker.utility;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;

import sait.wordtracker.contracts.BSTreeADT;
import sait.wordtracker.contracts.Iterator;
import sait.wordtracker.models.BSTreeNode;

public class BSTree<E> implements BSTreeADT
{
	/**
	 *serial ID 
	 */
	private static final long serialVersionUID = 1L;
	
	private BSTreeNode<E> root;
	private int height;
	private int size;
	private BSTree<E>.BSTInorderIterator inorderIterator;
	private BSTree<E>.BSTPreorderIterator preorderIterator;
	private BSTree<E>.BSTPostorderIterator postorderIterator;
	
	public BSTree()
	{
		root = null;
		this.height = 0;
		this.size = 0;
	}
	
	public BSTree(E value) 
	{
		root = new BSTreeNode<E>(value);
		this.height = 1;
		this.size = 1;
	}

	@Override
	public BSTreeNode<E> getRoot() throws TreeException 
	{
		if( root != null )
			return root;
		return null;
	}

	@Override
	public int getHeight() 
	{
		if(this.root == null){
	        return 0;
	    }
	    else{
	    	BSTreeNode<E> node = root;
	        return findHeight(node);
	    }
	}
	
	/** 
	* calculate the height of the BSTree 
	* @param aNode - BSTreeNode
	* @return - the height of the node
	*/
	
	private int findHeight(BSTreeNode<E> aNode) 
	{
	    if (aNode == null) {
	        return -1;
	    }

	    int lefth = findHeight(aNode.getLeft());
	    int righth = findHeight(aNode.getRight());

	    if (lefth > righth) {
	        return lefth + 1;
	    } else {
	        return righth + 1;
	    }
	}

	@Override
	public int size() 
	{
		return size;
	}

	@Override
	public boolean isEmpty() 
	{
		return root == null;
	}

	@Override
	public void clear() 
	{
		root = null;
		this.height = 0;
		this.size = 0;
	}

	@Override
	public Iterator<E> inorderIterator() 
	{
		this.inorderIterator = new BSTInorderIterator(root);
		return inorderIterator;
	}

	@Override
	public Iterator<E> preorderIterator() 
	{
		this.preorderIterator = new BSTPreorderIterator(root);
		return preorderIterator;
	}

	@Override
	public Iterator<E> postorderIterator() 
	{
		this.postorderIterator = new BSTPostorderIterator(root);
		return postorderIterator;
	}

	@Override
	public boolean contains(Comparable entry) throws TreeException 
	{
		return containsNodeRecursive(root, entry);
	}
	
	/** 
	* Check if the tree contains a specific value 
	* @param current - BSTreeNode
	* @param entry - the object that we look for
	* @return - if find the value return true else return false
	*/
	
	private boolean containsNodeRecursive(BSTreeNode<E> current, Comparable entry) 
	{
	    if (current == null) {
	        return false;
	    } 
	    if (entry.compareTo(current.getData()) == 0 ) {
	        return true;
	    } 
	    return entry.compareTo(current.getData()) < 0 
	      ? containsNodeRecursive(current.getLeft(), entry)
	      : containsNodeRecursive(current.getRight(), entry);
	}

	@Override
	public BSTreeNode search(Comparable entry) throws TreeException 
	{
		BSTreeNode<E> node = root;
		while (node != null) 
		{
			if (entry.compareTo(node.getData()) == 0) 
			{
		      return node;
		    } 
			else if (entry.compareTo(node.getData()) < 0) 
		    {
		      node = node.getLeft();
		    } 
			else 
			{
		      node = node.getRight();
		    }
		}

		return null;
	}

	@Override
	public boolean add(Comparable newEntry) throws NullPointerException 
	{
		/* If the tree is empty,return a new node */
		 if (root == null) 
		 {
		     root = new BSTreeNode<E>((E) newEntry);
		     size++;
		     return true;
		 }
		
		 /* Otherwise, recur down the tree */
		 BSTreeNode<E> node = root;
		  while (true) 
		  {
		    // Traverse the tree to the left or right depending on the key
		    if (newEntry.compareTo(node.getData()) < 0) 
		    {
		      if (node.getLeft() != null) 
		      {
		        // Left sub-tree exists --> follow
		        node = node.getLeft();
		      } else 
		      {
		        // Left sub-tree does not exist --> insert new node as left child
		        node.setLeft( new BSTreeNode<E>((E) newEntry) );
		        size++;
		        return true;
		      }
		    } 
		    else if (newEntry.compareTo(node.getData()) > 0) 
		    {
		      if (node.getRight() != null) 
		      {
		        // Right sub-tree exists --> follow
		        node = node.getRight();
		      } else 
		      {
		        // Right sub-tree does not exist --> insert new node as right child
		        node.setRight( new BSTreeNode<E>((E) newEntry) );
		        size++;
		        return true;
		      }
		    }
		    else 
		    {
		      throw new IllegalArgumentException("BST already contains a same object!");
		    }
		  }
	}
	
	/*---------------------INNER CLASS(implement iterator)------------------*/
	
	private class BSTPreorderIterator implements Iterator<E>
	{
		//Not using a java.util.Stack cause of STACK OVERFLOW!
		Deque<BSTreeNode<E>> stack = new ArrayDeque<>();
		Deque<BSTreeNode<E>> preorderStack = new ArrayDeque<>();
		
		public BSTPreorderIterator(BSTreeNode<E> root) 
		{
			if ( root != null ) 
			{
				stack.push( root );

			    while (!stack.isEmpty()) 
			    {
			      root = stack.poll();//dequeue
			      preorderStack.push(root);
			      if (root.getRight() != null) //PreOrder Traversal - rootNode:Left:Right (nLR)
			      {
			        stack.push(root.getRight());
			      }
			      if (root.getLeft() != null) 
			      {
			        stack.push(root.getLeft());
			      }
			    }
			}
		}
	 
		@Override
		public boolean hasNext() 
		{
			return !preorderStack.isEmpty();
		}
	 
		@Override
		public E next() 
		{
			if (!hasNext())
	            throw new NoSuchElementException();
			 return (E) preorderStack.pollLast();//the tail of this deque, or null if this deque is empty
		}
	}
	
	private class BSTInorderIterator implements Iterator<E>
	{
		//Not using a java.util.Stack cause of STACK OVERFLOW!
		Deque<BSTreeNode<E>> stack = new ArrayDeque<>();
		Deque<BSTreeNode<E>> inorderStack = new ArrayDeque<>();
	 
		public BSTInorderIterator(BSTreeNode<E> root) 
		{
			while (!stack.isEmpty() || root != null) 
			{
			  if (root != null) 
			  {
			    stack.push(root);
			    root = root.getLeft(); // InOrder Traversal - Left:rootNode:Right (LnR) 
			  } else 
			  {
			    root = stack.pop();
			    inorderStack.push(root);
			    root = root.getRight();
			  }
			}
		}
	 
		@Override
		public boolean hasNext() 
		{
			return !inorderStack.isEmpty();
		}
	 
		@Override
		public E next() 
		{
			if (!hasNext())
	            throw new NoSuchElementException();
			return (E) inorderStack.pollLast();
		}
	}
	
	private class BSTPostorderIterator implements Iterator<E>
	{
		//Not using a java.util.Stack cause of STACK OVERFLOW!
		Deque<BSTreeNode<E>> stack = new ArrayDeque<>();
		Deque<BSTreeNode<E>> postorderStack = new ArrayDeque<>();
	 
		public BSTPostorderIterator(BSTreeNode<E> root) 
		{
			BSTreeNode<E> lastVisitedNode = null;
			
			while (!stack.isEmpty() || root != null) 
			{
			  if (root != null) {
			    stack.push(root);
			    root = root.getLeft();//PostOrder Traversal - Left:Right:rootNode (LRn)
			  } else 
			  {
				  BSTreeNode<E> topNode = stack.peek();
			    if (topNode.getRight() != null && lastVisitedNode != topNode.getRight()) {
			      root = topNode.getRight();
			    } else 
			    {
			    	postorderStack.push(topNode);
			    	lastVisitedNode = stack.poll();
			    }
			  }
			}
		}
	 
		@Override
		public boolean hasNext() 
		{
			return !postorderStack.isEmpty();
		}
	 
		@Override
		public E next() 
		{
			if (!hasNext())
	            throw new NoSuchElementException();
			return (E) postorderStack.pollLast();
		}
	}
	
}
