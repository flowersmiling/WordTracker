package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sait.wordtracker.contracts.Iterator;
import sait.wordtracker.models.BSTreeNode;
import sait.wordtracker.utility.BSTree;
import sait.wordtracker.utility.TreeException;

class BSTreeTests<E> 
{
	private BSTree<E> bst;

	@BeforeEach
	void setUp() throws Exception 
	{
		this.bst = new BSTree<E>();
	}

	@AfterEach
	void tearDown() throws Exception {
		this.bst.clear();
	}

	/** 
	* test if it is empty 
	*/
	
	@Test
	void testIsEmpty() 
	{
		assertTrue(this.bst.isEmpty());
		assertEquals(0, this.bst.size());
		assertEquals(0, this.bst.getHeight());
	}
	
	/**
	 * Tests to add elements to BSTree
	 */
	@Test
	void testadd() 
	{
		this.bst.add(8);
		this.bst.add(3);
		this.bst.add(10);
		this.bst.add(1);
		this.bst.add(6);
		this.bst.add(14);
		this.bst.add(4);
		this.bst.add(7);
		this.bst.add(13);
		
		// Test the BSTree is not empty.
		assertFalse(this.bst.isEmpty());
		
		// Test the size is 9
		assertEquals(9, this.bst.size());
		
		// Test the height is 4
		assertEquals(4, this.bst.getHeight());
		
		//Test if BSTree contains the specific value
		try {
			boolean contains = this.bst.contains(14);
			assertTrue(contains);
			
			boolean notContains = this.bst.contains(5);
			assertFalse(notContains);
		} catch (TreeException e) {
			e.printStackTrace();
		}
		
		//Test add the same element to the BSTree
		try {
			this.bst.add(13);
		}catch(IllegalArgumentException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * Tests to search an elements in BSTree
	 */
	@Test
	void testsearch() 
	{
		this.bst.add(8);
		this.bst.add(3);
		this.bst.add(10);
		this.bst.add(1);
		this.bst.add(6);
		this.bst.add(14);
		this.bst.add(4);
		this.bst.add(7);
		this.bst.add(13);
		
		// Test the BSTree is not empty.
		assertFalse(this.bst.isEmpty());
		
		//Test search an element in BSTree
		try {
			BSTreeNode<E> result = this.bst.search(6);
			assertEquals(6,result.getData());
			
			BSTreeNode<E> noresult = this.bst.search(0);
			assertNull(noresult);
		} catch (TreeException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Test InorderIterator.
	 */
	@Test
	void testInorderIterator() 
	{
		this.bst.add(8);
		this.bst.add(3);
		this.bst.add(10);
		this.bst.add(1);
		this.bst.add(6);
		this.bst.add(14);
		this.bst.add(4);
		this.bst.add(7);
		this.bst.add(13);
		
		// Test the BSTree is not empty.
		assertFalse(this.bst.isEmpty());
		
		// Test the size is 9
		assertEquals(9, this.bst.size());
		
		// Test the height is 4
		assertEquals(4, this.bst.getHeight());
		
		//Test if Iterator has next element
		assertTrue( this.bst.inorderIterator().hasNext() );
		
		
		
		/**
		 * Tree inorder traversal should now be:
		 * 
		 * 1 3 4 6 7 8 10 13 14
		 */
		
		//test inorder tree traversal
		Iterator<E> it = this.bst.inorderIterator();
		assertEquals(1,it.next());
		assertEquals(3,it.next());
		assertEquals(4,it.next());
		assertEquals(6,it.next());
		assertEquals(7,it.next());
		assertEquals(8,it.next());
		assertEquals(10,it.next());
		assertEquals(13,it.next());
		assertEquals(14,it.next());
		
		//test NoSuchElementException exception
		try {			
			assertNull(it.next());
		}catch(NoSuchElementException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * Test PreorderIterator.
	 */
	@Test
	void testPreorderIterator() 
	{
		this.bst.add(8);
		this.bst.add(3);
		this.bst.add(10);
		this.bst.add(1);
		this.bst.add(6);
		this.bst.add(14);
		this.bst.add(4);
		this.bst.add(7);
		this.bst.add(13);
		
		// Test the BSTree is not empty.
		assertFalse(this.bst.isEmpty());
		
		// Test the size is 9
		assertEquals(9, this.bst.size());
		
		// Test the height is 4
		assertEquals(4, this.bst.getHeight());
		
		//Test if Iterator has next element
		assertTrue( this.bst.preorderIterator().hasNext() );
		
		
		
		/**
		 * Tree preorder traversal should now be:
		 * 
		 * 8 3 1 6 4 7 10 14 13
		 */
		
		//test preorder tree traversal
		Iterator<E> it = this.bst.preorderIterator();
		assertEquals(8,it.next());
		assertEquals(3,it.next());
		assertEquals(1,it.next());
		assertEquals(6,it.next());
		assertEquals(4,it.next());
		assertEquals(7,it.next());
		assertEquals(10,it.next());
		assertEquals(14,it.next());
		assertEquals(13,it.next());
		
		//test NoSuchElementException exception
		try {			
			assertNull(it.next());
		}catch(NoSuchElementException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * Test PostorderIterator.
	 */
	@Test
	void testPostorderIterator() 
	{
		this.bst.add(8);
		this.bst.add(3);
		this.bst.add(10);
		this.bst.add(1);
		this.bst.add(6);
		this.bst.add(14);
		this.bst.add(4);
		this.bst.add(7);
		this.bst.add(13);
		
		// Test the BSTree is not empty.
		assertFalse(this.bst.isEmpty());
		
		// Test the size is 9
		assertEquals(9, this.bst.size());
		
		// Test the height is 4
		assertEquals(4, this.bst.getHeight());
		
		//Test if Iterator has next element
		assertTrue( this.bst.postorderIterator().hasNext() );
		
		
		
		/**
		 * Tree postorder traversal should now be:
		 * 
		 * 1 4 7 6 3 13 14 10 8
		 */
		
		//test postorder tree traversal
		Iterator<E> it = this.bst.postorderIterator();
		assertEquals(1,it.next());
		assertEquals(4,it.next());
		assertEquals(7,it.next());
		assertEquals(6,it.next());
		assertEquals(3,it.next());
		assertEquals(13,it.next());
		assertEquals(14,it.next());
		assertEquals(10,it.next());
		assertEquals(8,it.next());
		
		//test NoSuchElementException exception
		try {			
			assertNull(it.next());
		}catch(NoSuchElementException ex) {
			ex.printStackTrace();
		}
	}

}
