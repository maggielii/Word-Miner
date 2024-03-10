package code;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class WordMiner {
	
	public static boolean isValid(String word)
	{
		boolean isValid = false;
		
		int length = word.length();
		if(length >= 3 && length <= 6)
		{
			isValid = true;
		}
		
		if(isValid)
		{
			for(int x = 0; x < word.length(); x++)
			{
				if(word.charAt(x) < 97 || word.charAt(x) > 122)
				{
					isValid = false;
				}
			}
		}
		
		return isValid;
	} 

	public static void main(String[] args) throws Exception {
		
		File infile = new File("The Count of Monte Cristo.txt");
		
		Scanner input = new Scanner(infile);
		
		File outfile = new File("dictionary.txt"); 
		
		PrintWriter pw = new PrintWriter( outfile );
		
		String[][] hashTable = new String[20000][10];
		
		while(input.hasNext())
		{
			String word = input.next();
			
			if(isValid(word))
			{
				char[] wordArray = word.toCharArray();
				
				int hashCode = 0;
				
				for(int x = 0; x < wordArray.length; x++)
				{
					hashCode += wordArray[x]*(x+1);
				}
				
				hashCode = hashCode % 20000;
				
				for(int y = 0; y < 10; y++)
				{
					if(hashTable[hashCode][y] != null)
					{
						if(hashTable[hashCode][y].equals( word ))
						{
							break;
						}	
					}
					
					if(hashTable[hashCode][y] == null)
					{
						hashTable[hashCode][y] = word;
						pw.println(hashTable[hashCode][y]);
						break;
					}
				} 
			}
		}

		pw.close();
	
	}
}
