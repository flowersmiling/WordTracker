package sait.wordtracker.application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import sait.wordtracker.contracts.Iterator;
import sait.wordtracker.models.BSTreeNode;
import sait.wordtracker.models.Word;
import sait.wordtracker.utility.BSTree;
import sait.wordtracker.utility.TreeException;

public class ReadWord 
{
	private static BSTree<Object> wordsBST = new BSTree<Object>();
	
	public static void readFile(String filepath) throws FileNotFoundException, TreeException 
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
		
		//-----------------test begin--------------------------------//
		
		//test file  C:\Users\Administrator\eclipse-workspace\WordTracker\res\textfile1.txt
		
		System.out.println(wordsBST.getHeight());
		System.out.println(wordsBST.size());
		
//		while( wordsBST.inorderIterator().hasNext() )
//		{
//			Word w = (Word) wordsBST.inorderIterator().next();
//			System.out.println(w.getWord()+" | "+w.getLineNumber()+" | "+w.getOccurtimes()+" | "+w.getFilename());
//		}
		
		Iterator<Object> it = wordsBST.inorderIterator();
		int wordcount=0;
		
//		for(int i=0;i < 1000; i++) 
//		{
//			Word w = (Word) it.next();
//			wordcount = wordcount+w.getOccurtimes();
//			System.out.println(w.getWord()+" | "+w.getLineNumber()+" | "+w.getOccurtimes()+" | "+w.getFilename());
//			System.out.println("WordCount "+wordcount);
//		}
		
		while(it.hasNext()) {
			Word w = (Word) it.next();
			wordcount = wordcount+w.getOccurtimes();
			System.out.println(w.getWord()+" | "+w.getLineNumber()+" | "+w.getOccurtimes()+" | "+w.getFilename());
			System.out.println("WordCount |"+wordcount);
		}
		
		//--------------------------test end----------------------------//

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
	
}
