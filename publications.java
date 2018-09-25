import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class publications {
	//put faculty first names in "authorFirst", faculty last names in "authors". The order of the names in both files should be the same but it doesn't matter what the particular order is.
	//article information in columns, keep order from spreadsheet
	//make sure each file is same length
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
		String [][] articleInfo = new String [3511][5];
		String [] names = {"colB","colJ","colR","colAF","colAG"};
		for(int col=0;col<5;col++)
		{
			try
			{
				inputStream=new Scanner(new FileInputStream(names[col]));
			}
			catch(FileNotFoundException e)
			{
				System.out.println("dun goofed");
			}
			for(int i=0;i<3511;i++)
			{
				articleInfo[i][col] = inputStream.nextLine().trim();
			}
			inputStream.close();
		}
		int c = 0;
		String [] verify = new String[255];
		for(int i=0;i<255;i++)
		{
			verify[i]="";
		}
		for(int a=0;a<3511;a++)
		{
			String line = articleInfo[a][0];
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
							//i = count;
							verify[j] = verify[j]+"\n"+articleInfo[a][1]+". "+articleInfo[a][2]+". "+articleInfo[a][3]+". "+articleInfo[a][4]+". ";
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
		}
		inputStream.close();
		for(int i=0;i<255;i++)
		{
			System.out.println(authors[i][0]+" "+authors[i][1]+": "+verify[i]);
		}
		System.out.println(c);
	}
}
