package run;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class publications {
	//excluding authors such as Thi Kim Phung Dang whose names have no commas
	//Amelia Rutledge appears as having published but not as faculty
	public static void main (String [] args)
	{
		String [][] authors = new String [255][2];
		Scanner inputStream=null;
		try
		{
			inputStream=new Scanner(new FileInputStream("authorsFirst"));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("dun goofed");
		}
		for(int i=0;i<255;i++)
		{
			authors[i][0] = inputStream.nextLine();
		}
		inputStream.close();
		try
		{
			inputStream=new Scanner(new FileInputStream("authors"));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("dun goofed");
		}
		for(int i=0;i<255;i++)
		{
			authors[i][1] = inputStream.nextLine();
		}
		inputStream.close();
	/*	for(int i=0;i<255;i++)
		{
			System.out.println(authors[i][0]+" "+authors[i][1]);
		}*/
		try
		{
			inputStream=new Scanner(new FileInputStream("articleTitles"));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("dun goofed");
		}
		String [] articleTitles = new String [1589];
		for(int i=0;i<1589;i++)
		{
			articleTitles[i] = inputStream.nextLine();
		}
		inputStream.close();
		try
		{
			inputStream=new Scanner(new FileInputStream("journals"));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("dun goofed");
		}
		String [] journals = new String [1589];
		for(int i=0;i<1589;i++)
		{
			journals[i] = inputStream.nextLine();
		}
		inputStream.close();
		try
		{
			inputStream=new Scanner(new FileInputStream("articleAuthors"));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("dun goofed");
		}
		int c = 0;
		String [] verify = new String[255];
		for(int i=0;i<255;i++)
		{
			verify[i]="";
		}
		int a = 0;
		while(inputStream.hasNext())
		{
			String line = inputStream.nextLine();
			int count = 1+line.length() - line.replace(";", "").length();
			//System.out.println(line+" "+count);
			String [] authorList = new String[count];
			for(int i=0;i<count-1;i++)
			{
				int n = line.indexOf(";");
				authorList[i] = line.substring(0, n);
				line = line.substring(n+1);
			}
			authorList[count-1]=line;
			//System.out.println(a+" "+articleTitles[a]);
			for(int i=0;i<count;i++)
			{
				String author = authorList[i];
				if(author.contains(","))
				{
					int n = author.indexOf(",");
					String before = author.substring(0,n);
					String after = author.substring(n+1);
				//	System.out.println(before);
				//	System.out.println(after);
					for(int j=0;j<255;j++)
					{
						String firstFull = authors[j][0];
						String lastFull = authors[j][1];
						if((before.contains(firstFull)&&after.contains(lastFull))||(before.contains(lastFull)&&after.contains(firstFull)))
						{
							c++;
							verify[j] = verify[j]+"\n"+articleTitles[a]+". "+journals[a]+".";
						//	System.out.println(firstFull+","+lastFull);
						}
				/*		else if((before.equals(""+firstFull.charAt(0))&&after.contains(lastFull))||(before.equals(""+lastFull.charAt(0))&&after.contains(firstFull)))
						{
							System.out.println(firstFull+" "+lastFull+" "+articleTitles[a]);
						}
						else if((firstFull.contains(before)&&lastFull.contains(after))||(lastFull.contains(before)&&firstFull.contains(after)))
						{
							System.out.println(firstFull+" "+lastFull+" "+articleTitles[a]);
						}*/
					}
				}
			}
			a++;
		}
		inputStream.close();
		for(int i=0;i<255;i++)
		{
			System.out.println(authors[i][0]+" "+authors[i][1]+":"+verify[i]);
		}
		System.out.println(c);
	}
}
