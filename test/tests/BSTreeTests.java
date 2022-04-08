package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sait.wordtracker.utility.BSTree;

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

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
