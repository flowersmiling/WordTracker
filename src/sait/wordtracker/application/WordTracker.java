package sait.wordtracker.application;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import sait.wordtracker.utility.TreeException;

/**
 *WordTracker that reads text files and collects and stores all the unique words it finds in those files.
 *The BST will be able to store information from multiple text files. It will also keep track of each 
 *occurrence of a word in a file and the line on which it was found in that file.
 *The program will also produce output, specified by the user at command line, 
 *to generate reports using a variety of iterators built into the BST.
 */
public class WordTracker {

	/** 
	* the main entry of the program 
	* @param args - arguments 
	* @throws TreeException - when the tree is empty
	* @throws IOException - produced by failed orinterrupted I/O operations
	*/
	
	public static void main(String[] args) throws TreeException, IOException 
	{
		char printtype = ' ';
		String inpath = "";
		String outpath = "";
		boolean isContinue = true;
		String input = null;
		FileInputStream fis;
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Please enter the Command: Type yes for contiue, ? for help, quit to exit");
		
		
		while(isContinue) 
		{
			input = scan.next();
			
			if(input.equals("yes")) {
				System.out.println("Please enter the TXT file path and print option: ");
				
				Scanner incmd = new Scanner(System.in);
				String strComd = incmd.nextLine();
				
				//replace more than one space or tab with one space, split the string to array with one space
				String[] cmds = strComd.replaceAll("\\s+", " ").split(" ");
				//replace more than one space or tab with empty to make sure there is not space in the file path and name.
				inpath = cmds[0].replaceAll("\\s+", "");
				
				for(int i = 0; i < cmds.length; i++) 
				{
					if(Character.toLowerCase(cmds[i].charAt(1))=='p') 
					{
						printtype = cmds[i].charAt(2);
					}
					if(Character.toLowerCase(cmds[i].charAt(1))=='f')
					{
						//remove the double quotation marks("") in the file path
						outpath = cmds[i+1].toString().replace("\"", "").substring(0);
					}
				}
				
				System.out.println("Load TXT file......");

				try {
					fis = new FileInputStream("res/repository.ser");
					//de-serial the Binary Search Tree from the binary file at first
					ReadWord.deserialBSTree(fis);
					//then read the new file from the input
					ReadWord.readFile(inpath,printtype,outpath);
					fis.close();
					
					//at last, store the Binary Search Tree to the binary file
					ReadWord.serialBSTree();
				}catch(Exception e) {
					//read and serial Binary Search Tree then stored into file 
					ReadWord.readFile(inpath,printtype,outpath);
					ReadWord.serialBSTree();
				}

				System.out.println("Print & Output completed!");
				
				incmd.close();
				isContinue = false;
			}else if(input.equals("?"))
			{
				Message();
				System.out.println("Please enter the Command: Type yes for contiue, ? for help, quit to exit");
			}else if(input.equals("quit")) {
				isContinue = false;
				System.out.println("Thanks for using!");
			}else
			{
				System.out.println("Please enter the Command: Type yes for contiue, ? for help, quit to exit");
			}
		}
			
		scan.close();

	}
	
	/** 
	* command line arguments manual  
	*/
	
	public static void Message() 
	{
		System.out.println("[Syntax:] <input.txt> -p[f|l|o] [-f <output.txt>]");
		System.out.println("<input.txt> is the path and filename of the text file to be processed by the WordTracker program.");
		System.out.println("-p:output type, f to print in alphabetic order all words along with the corresponding list of files in which\r\n"
				+ "the words occur.");
		System.out.println("l to print in alphabetic order all words along with the corresponding list of files and\r\n"
				+ "numbers of the lines in which the word occur.");
		System.out.println("o to print in alphabetic order all words along with the corresponding list of files,\r\n"
				+ "numbers of the lines in which the word occur and the frequency of occurrence of the\r\n"
				+ "words.");
		System.out.println("-f to redirect of the report in the previous step to the path and filename specified\r\n"
				+ "in <output.txt>");
	}

}
