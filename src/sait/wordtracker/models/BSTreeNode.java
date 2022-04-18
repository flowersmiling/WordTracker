package sait.wordtracker.models;

import java.io.Serializable;

/**
 * BST Nodes model
 * @param <E> - data object that the node hold
 */
public class BSTreeNode<E> implements Serializable
{
	/**
	 *TODO 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 *data object 
	 */
	private E data;
	/**
	 *define the left and right nodes 
	 */
	private BSTreeNode<E> left, right;
	
	/**
	 * constructor for BSTreeNode without children
	 * @param data - data object that the node hold
	 */
	public BSTreeNode(E data) 
	{
		super();
		this.data = data;
		this.left = this.right = null;
	}

	/**
	 * constructor for BSTreeNode
	 * @param data -  data object that the node hold
	 * @param left - left child of the node
	 * @param right - right child of the node
	 */
	public BSTreeNode(E data, BSTreeNode<E> left, BSTreeNode<E> right) 
	{
		super();
		this.data = data;
		this.left = left;
		this.right = right;
	}

	/** 
	* getter 
	* @return - this data object
	*/
	
	public E getData() 
	{
		return data;
	}

	/** 
	* setter 
	* @param data - this data object value
	*/
	
	public void setData(E data) 
	{
		this.data = data;
	}

	/** 
	* get left node  
	* @return - BSTreeNode
	*/
	
	public BSTreeNode<E> getLeft() 
	{
		return left;
	}

	/** 
	* set the left node 
	* @param left - BSTreeNode
	*/
	
	public void setLeft(BSTreeNode<E> left) 
	{
		this.left = left;
	}

	/** 
	* get right node 
	* @return - BSTreeNode
	*/
	
	public BSTreeNode<E> getRight() 
	{
		return right;
	}

	/** 
	* set the right node 
	* @param right - BSTreeNode
	*/
	
	public void setRight(BSTreeNode<E> right) 
	{
		this.right = right;
	}
	
}
