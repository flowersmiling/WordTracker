package sait.wordtracker.utility;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;
import java.util.Queue;

import sait.wordtracker.contracts.BSTreeADT;
import sait.wordtracker.contracts.Iterator;
import sait.wordtracker.models.BSTreeNode;

/**
 * BSTree object
 */
/**
 * TODO
 */
public class BSTree<E> implements BSTreeADT
{
	/**
	 *serial ID 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 *the root Node of the BSTree object 
	 */
	private BSTreeNode<E> root;
	/**
	 *the height(depth) of the BSTree object 
	 */
	private int height;
	/**
	 *the total number of nodes
	 */
	private int size;
	/**
	 *the Iterator implements inorder tree traversal 
	 */
	private BSTree<E>.BSTInorderIterator inorderIterator;
	/**
	 *the Iterator implements preorder tree traversal 
	 */
	private BSTree<E>.BSTPreorderIterator preorderIterator;
	/**
	 *the Iterator implements postorder tree traversal
	 */
	private BSTree<E>.BSTPostorderIterator postorderIterator;
	
	/**
	 * BSTree constructor
	 */
	public BSTree()
	{
		root = null;
		this.height = 0;
		this.size = 0;
	}
	
	/**
	 * BSTree constructor with an element
	 * @param value - the element
	 */
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
	        //return findHeight(node);	//Recursive Solution lead to java.lang.StackOverflowError
	    	return calcHeight(root);
	    }
	}
	
	/** 
	* calculate the height of the BSTree (Recursive Solution)
	* @param aNode - BSTreeNode
	* @return - the height of the BSTree
	*/
	
	/* Attention: Recursive Solution lead to java.lang.StackOverflowError */
	private int findHeight(BSTreeNode<E> aNode) 
	{
	    if (aNode == null) {
	        return 0;
	    }

	    int lefth = findHeight(aNode.getLeft());
	    int righth = findHeight(aNode.getRight());

	    if (lefth > righth) {
	        return lefth + 1;
	    } else {
	        return righth + 1;
	    }
	}
	
	
	/** 
	* calculate the height of the BSTree (Iterative Solution) 
	* @param root - BSTreeNode
	* @return - the height of the BSTree
	*/
	
	/* Iterative Solution */
	private int calcHeight(BSTreeNode<E> root)
    {
        // empty tree has a height of 0
        if (root == null) {
            return 0;
        }
 
        // create an empty queue and enqueue the root node
        Queue<BSTreeNode<E>> queue = new ArrayDeque<BSTreeNode<E>>();
        queue.add(root);
 
        BSTreeNode<E> front = null;
        int height = 0;
 
        // loop till queue is empty
        while (!queue.isEmpty())
        {
            // calculate the total number of nodes at the current level
            int size = queue.size();
 
            // process each node of the current level and enqueue their
            // non-empty left and right child
            while (size-- > 0)
            {
                front = queue.poll();
 
                if (front.getLeft() != null) {
                    queue.add(front.getLeft());
                }
 
                if (front.getRight() != null) {
                    queue.add(front.getRight());
                }
            }
 
            // increment height by 1 for each level
            height++;
        }
 
        return height;
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
		    	//when the two object are equal
		    	return false;
		    }
		  }
	}
	
	/*---------------------INNER CLASS(implement iterator)------------------*/
	
	/**
	 * inner class implements the preorder tree traversal Iterator
	 * for serializing the BSTree object it must implement the Serializable interface
	 */
	private class BSTPreorderIterator implements Iterator<E>,Serializable 
	{
		/**
		 *The version number for BSTPreorderIterator class when it is serialized
		 */
		private static final long serialVersionUID = 1L;
		/**
		 *create a double ended queue to traverse the Binary Search Tree.
		 * Not using a java.util.Stack cause of STACK OVERFLOW!!!
		 */
		Deque<BSTreeNode<E>> stack = new ArrayDeque<>();
		/**
		 *create a double ended queue to hold the nodes of the tree by preorder tree traversal order.
		 *Deque supports element insertion and removal at both ends and no fixed limits on the number of elements they may contain.
		 */
		Deque<BSTreeNode<E>> preorderStack = new ArrayDeque<>();
		
		/**
		 * BSTPreorderIterator constructor
		 * @param root - root node
		 */
		public BSTPreorderIterator(BSTreeNode<E> root) 
		{
			if ( root != null ) 
			{
				stack.push( root );

			    while (!stack.isEmpty()) 
			    {
			      root = stack.poll();//dequeue
			      preorderStack.push(root);//hold the node with double ended queue
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
			 return (E) preorderStack.pollLast().getData();//the tail of this deque, or null if this deque is empty
		}
	}
	
	/**
	 * inner class implements the inorder tree traversal Iterator
	 * for serializing the BSTree object it must implement the Serializable interface
	 */
	private class BSTInorderIterator implements Iterator<E>,Serializable
	{
		/**
		 *The version number for BSTInorderIterator class when it is serialized 
		 */
		private static final long serialVersionUID = 1L;
		
		/**
		 *create a double ended queue to traverse the Binary Search Tree.
		 * Not using a java.util.Stack cause of STACK OVERFLOW!!! 
		 */
		Deque<BSTreeNode<E>> stack = new ArrayDeque<>();
		/**
		 *create a double ended queue to hold the nodes of the tree by inorder tree traversal order.
		 *Deque supports element insertion and removal at both ends and no fixed limits on the number of elements they may contain. 
		 */
		Deque<BSTreeNode<E>> inorderStack = new ArrayDeque<>();
	 
		/**
		 * BSTInorderIterator constructor
		 * @param root - root node
		 */
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
			    inorderStack.push(root);//hold the node with double ended queue
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
			return (E) inorderStack.pollLast().getData();
		}
	}
	
	/**
	 * inner class implements the postorder tree traversal Iterator
	 * for serializing the BSTree object it must implement the Serializable interface
	 */
	private class BSTPostorderIterator implements Iterator<E>,Serializable
	{
		/**
		 *The version number for BSTPostorderIterator class when it is serialized  
		 */
		private static final long serialVersionUID = 1L;

		/**
		 *create a double ended queue to traverse the Binary Search Tree.
		 * Not using a java.util.Stack cause of STACK OVERFLOW!!!  
		 */
		Deque<BSTreeNode<E>> stack = new ArrayDeque<>();
		/**
		 *create a double ended queue to hold the nodes of the tree by postorder tree traversal order.
		 *Deque supports element insertion and removal at both ends and no fixed limits on the number of elements they may contain.  
		 */
		Deque<BSTreeNode<E>> postorderStack = new ArrayDeque<>();
	 
		/**
		 * BSTPostorderIterator constructor
		 * @param root - root node
		 */
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
			    	postorderStack.push(topNode);//hold the node with double ended queue
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
			return (E) postorderStack.pollLast().getData();
		}
	}
	
}
