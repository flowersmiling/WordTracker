package sait.wordtracker.application;

import java.io.FileNotFoundException;
import java.util.Scanner;

import sait.wordtracker.utility.TreeException;

public class WordTracker {

	public static void main(String[] args) throws FileNotFoundException, TreeException 
	{
		char printtype = ' ';
		String inpath = "";
		String outpath = "";
		boolean isContinue = true;
		String input = null;
		
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
				inpath = cmds[0];
				
//				for(int i = 0; i < cmds.length; i++) 
//				{
//					if(Character.toLowerCase(cmds[i].charAt(1))=='p') 
//					{
//						printtype = cmds[i].charAt(2);
//					}
//					if(Character.toLowerCase(cmds[i].charAt(1))=='f')
//					{
//						outpath = cmds[i].toString().replace("\"", "").substring(2);
//					}
//				}
				
				System.out.println("Load TXT file......");

				ReadWord.readFile(inpath);
				
				System.out.println("Print completed!");
				
				incmd.close();
				isContinue = false;
			}else if(input.equals("?"))
			{
				Message();
				System.out.println("Please enter the Search Command: Type yes for contiue, ? for help, quit to exit");
			}else if(input.equals("quit")) {
				isContinue = false;
				System.out.println("Thanks for using!");
			}else
			{
				System.out.println("Please enter the Search Command: Type yes for contiue, ? for help, quit to exit");
			}
		}
			
		scan.close();

	}
	
	/** 
	* command line arguments manual  
	*/
	
	public static void Message() 
	{
		System.out.println("[Syntax:] -t[h|v|a] -s[b|s|m|q|z] -f[path]");
		System.out.println("-t:compare type, v is volume, h is height, a is base area");
		System.out.println("-s:algorithm name, b is bubble, s is selection, i is insertion,\r\n"
				+ "m is merge,q is quick, z is some algorithm ^-^");
		System.out.println("-f:the path of the data file");
	}

}
