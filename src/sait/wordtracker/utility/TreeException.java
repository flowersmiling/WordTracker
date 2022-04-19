package sait.wordtracker.utility;

/**
 * define the Binary Search Tree empty exception
 */
public class TreeException extends Exception
{
	/**
	 * TreeException constructor
	 * @param message - message specific to cause of error.
	 */
	public TreeException(String message) 
	{
		super(message);
	}
}
