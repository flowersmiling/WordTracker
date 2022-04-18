package sait.wordtracker.application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Formatter;
import java.util.Scanner;

import sait.wordtracker.contracts.Iterator;
import sait.wordtracker.models.BSTreeNode;
import sait.wordtracker.models.Word;
import sait.wordtracker.utility.BSTree;
import sait.wordtracker.utility.TreeException;

public class ReadWord 
{
	private static BSTree<Object> wordsBST = new BSTree<Object>();
	
	/** 
	* serialization the Binary Search Tree and storing into a binary file
	 * @throws IOException - exceptions produced by failed I/O operations 
	*/
	
	public static void serialBSTree() throws IOException
	{
		FileOutputStream fos = new FileOutputStream("res/repository.ser");
		ObjectOutputStream oos = new ObjectOutputStream( fos );
		oos.writeObject(wordsBST);
		oos.close();
		fos.close();
	}
	
	/** 
	* de-serialization the Binary Search Tree from the stored file  
	* @param fis - input binary file which stored the Binary Search Tree
	 * @throws IOException - exceptions produced by failed I/O operations
	 * @throws ClassNotFoundException - when the BSTree<Object> doesn't exist
	*/
	
	public static void deserialBSTree(FileInputStream fis) throws IOException, ClassNotFoundException 
	{
		ObjectInputStream ois = new ObjectInputStream( fis );
		wordsBST = (BSTree<Object>) ois.readObject();
		ois.close();
	}
	
	public static void readFile(String filepath,char printype,String outpath) throws TreeException, IOException 
	{
		Scanner input = null;
		File newFile = new File(filepath);
		String[] wordsArray;
		String lineInfor;
		int line = 1;
		
		if (!newFile.exists()) {
		    System.out.println("TXT file does not exists");		      
		    System.exit(0);
		}
		else {
			input = new Scanner( newFile );
		}
		
		while( input.hasNextLine() ) 
		{
			lineInfor = input.nextLine().trim();
			//ignore the blank row
			if(lineInfor != "") {
				//remove all punctuation in the line of words
				lineInfor = lineInfor.replaceAll("\\p{Punct}", "");
				//replace more than one space or tab with one space, split the string to array with one space
				lineInfor = lineInfor.replaceAll("\\s+", " ");
				wordsArray = lineInfor.split(" ");
				
				constructBST(wordsArray, filepath, line);
			}
			
			line++;
		}
		
		input.close();
		
		//print output to screen or file
		printOut(printype,outpath);
	}
	
	public static void constructBST(String[] words, String file, int lineNumber) throws TreeException 
	{
		for(int i = 0; i < words.length; i++)
		{
			Word word = new Word(file, lineNumber, words[i]);
			//check the occurrence of the word object in the row
			if( !wordsBST.add( word ) ) 
			{
				//search the word object in the tree
				BSTreeNode duplNode = wordsBST.search(word);
				
				if(duplNode != null) 
				{
					//the original word object in the tree
					Word orig = (Word)duplNode.getData();
					//increase the occurrence of the word object in the tree
					orig.setOccurtimes();
					//renew the Node data
					duplNode.setData(orig);
				}
			}
		}
	}
	
	public static void printOut(char printype, String outpath) throws IOException
	{
		Iterator<Object> itera = wordsBST.inorderIterator();
		//provides support for layout justification and alignment
		Formatter fmtFile = null;
		boolean header = false;
		
		if( outpath != "" ) {
		    fmtFile = new Formatter(new File(outpath));
		}
		
		
		switch( printype ) 
		{
			case 'f':
				System.out.printf("%-20s%5s%n", "Word", "Files");
				while(itera.hasNext()) 
				{
					Word w = (Word) itera.next();
					System.out.printf("%-20s%100s%n", w.getWord(),w.getFilename());
					
					//output to file
					if( outpath != "" ) {
						if(!header) {
							fmtFile.format("%-20s%20s%n", "Word", "Files");
							header = true;
						}
						fmtFile.format("%-20s%100s%n", w.getWord(),w.getFilename());
					}
				}
			break;
			case 'l':
				System.out.printf("%-20s%11s %5s%n", "Word", "Line Number", "Files");
				while(itera.hasNext()) 
				{
					Word w = (Word) itera.next();
					System.out.printf("%-20s%5s %100s%n", w.getWord(),w.getLineNumber(),w.getFilename());
					
					//output to file
					if( outpath != "" ) {
						if(!header) {
							fmtFile.format("%-20s%11s %20s%n", "Word", "Line Number", "Files");
							header = true;
						}
						fmtFile.format("%-20s%5s %100s%n", w.getWord(),w.getLineNumber(),w.getFilename());
					}
				}
			break;
			case 'o':
				System.out.printf("%-20s%11s%10s %5s%n", "Word", "Line Number", "Occurrence", "Files");
				while(itera.hasNext()) 
				{
					Word w = (Word) itera.next();
					System.out.printf("%-20s%5s%5s %100s%n", w.getWord(),w.getLineNumber(),w.getOccurtimes(),w.getFilename());
					
					//output to file
					if( outpath != "" ) {
						if(!header) {
							fmtFile.format("%-5s%7s%14s %19s%n", "Word", "Line Number", "Occurrence", "Files");
							header = true;
						}
						fmtFile.format("%-20s%5s%5s %100s%n", w.getWord(),w.getLineNumber(),w.getOccurtimes(),w.getFilename());
					}
				}
			break;
		}

		//close formatter
		fmtFile.close();
	}
	
	public static void test()
	{
		//-----------------test begin--------------------------------//
		
		//test file  C:\Users\Administrator\eclipse-workspace\WordTracker\res\textfile1.txt -po -f res\output.txt
		
		System.out.println(wordsBST.getHeight());
		System.out.println(wordsBST.size());
		
//				while( wordsBST.inorderIterator().hasNext() )
//				{
//					Word w = (Word) wordsBST.inorderIterator().next();
//					System.out.println(w.getWord()+" | "+w.getLineNumber()+" | "+w.getOccurtimes()+" | "+w.getFilename());
//				}
		
		Iterator<Object> it = wordsBST.inorderIterator();
		int wordcount=0;
		
//				for(int i=0;i < 1000; i++) 
//				{
//					Word w = (Word) it.next();
//					wordcount = wordcount+w.getOccurtimes();
//					System.out.println(w.getWord()+" | "+w.getLineNumber()+" | "+w.getOccurtimes()+" | "+w.getFilename());
//					System.out.println("WordCount "+wordcount);
//				}
		
		System.out.printf("%-20s%11s%10s %5s%n", "Word", "Line Number", "Occurrence", "Files");
		while(it.hasNext()) {
			Word w = (Word) it.next();
			wordcount = wordcount+w.getOccurtimes();
			System.out.printf("%-20s%5s%4s %100s%n", w.getWord(),w.getLineNumber(),w.getOccurtimes(),w.getFilename());
			//System.out.println(w.getWord()+" | "+w.getLineNumber()+" | "+w.getOccurtimes()+" | "+w.getFilename());
		}
		System.out.println("WordCount |"+wordcount);
		
		//--------------------------test end----------------------------//
	}
}
