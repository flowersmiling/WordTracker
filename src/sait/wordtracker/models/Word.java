package sait.wordtracker.models;

import java.io.Serializable;

/**
 * word data model that hold the word object
 */
public class Word implements Comparable,Serializable
{
	/**
	 *The serial version number for Word class 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 *the file name where the word comes from  
	 */
	private String filename;
	/**
	 *the line number on which the word was used 
	 */
	private int lineNumber;
	/**
	 *the default occurrence of word is 1 
	 */
	private int occurtimes = 1;
	/**
	 *the word string 
	 */
	private String word;
	

	/**
	 * word object constructor
	 * @param filename - the file name where the word comes from
	 * @param lineNumber - the line number on which the word was used
	 * @param word - the word string
	 */
	public Word(String filename, int lineNumber, String word) 
	{
		super();
		this.filename = filename;
		this.lineNumber = lineNumber;
		this.word = word;
	}


	/** 
	* word object getter 
	* @return - file name
	*/
	
	public String getFilename() {
		return filename;
	}


	/** 
	* word object setter  
	* @param filename - file name
	*/
	
	public void setFilename(String filename) {
		this.filename = filename;
	}


	/** 
	* word object getter  
	* @return - the line number that the word locate in the file
	*/
	
	public int getLineNumber() {
		return lineNumber;
	}


	/** 
	* word object setter  
	* @param lineNumber - the line number that the word locate in the file
	*/
	
	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}


	/** 
	* the occurrence of the word in a file 
	* @return - occurrence times
	*/
	
	public int getOccurtimes() {
		return occurtimes;
	}


	/** 
	* increase the occurrence of the word  
	*/
	
	public void setOccurtimes() {
		occurtimes++;
	}


	/** 
	* Word object getter 
	* @return - the word string
	*/
	
	public String getWord() {
		return word;
	}


	/** 
	* Word object setter 
	* @param word - the word string
	*/
	
	public void setWord(String word) {
		this.word = word;
	}


	@Override
	public int compareTo(Object that) 
	{
		Word w = (Word)that;	//cast the same object before comparison
		
		if( this.word.compareToIgnoreCase(w.getWord()) > 0 ) {
			return 1;
		}else if( this.word.compareToIgnoreCase(w.getWord()) < 0 ) {
			return -1;
		}else {
			return 0;
		}
	}

}
