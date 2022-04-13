package sait.wordtracker.models;

import java.io.Serializable;

public class Word implements Comparable,Serializable
{
	/**
	 *The serial version number for Word class 
	 */
	private static final long serialVersionUID = 1L;
	private String filename;
	private int lineNumber;
	/**
	 *the default occurrence of word is 1 
	 */
	private int occurtimes = 1;
	private String word;
	

	public Word(String filename, int lineNumber, String word) 
	{
		super();
		this.filename = filename;
		this.lineNumber = lineNumber;
		this.word = word;
	}


	public String getFilename() {
		return filename;
	}


	public void setFilename(String filename) {
		this.filename = filename;
	}


	public int getLineNumber() {
		return lineNumber;
	}


	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}


	public int getOccurtimes() {
		return occurtimes;
	}


	/** 
	* increase the occurrence of the word  
	*/
	
	public void setOccurtimes() {
		occurtimes++;
	}


	public String getWord() {
		return word;
	}


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
