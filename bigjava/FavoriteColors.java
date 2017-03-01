package bigjava;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import java.util.Iterator;
import java.io.*;
import java.awt.Color;
import java.lang.reflect.Field;

/*
 * This class is an exercise in reading in an
 * external text file, parsing the lines for data
 * and then storing that data in a HashMap, which
 * may then be changed and saved. 
 */

public class FavoriteColors 
{
	private HashMap<String,String> favoriteColors;
	private Scanner in;
	private class Arrow
	{
		public String name;
		public String color; 
		public Arrow (String name, String color)
		{
			this.name = name;
			this.color = color;
		}
	}
	public FavoriteColors ()
	{
		favoriteColors = new HashMap<String,String>();
		in = new Scanner(System.in);
		
		try
		{
			loadData();
		}
		catch(FileNotFoundException fnf)
		{
			System.out.println("Could not load data file.");
		}
		printData();
		processMainMenuInput(presentMenu());
		in.close();
	}
	
	
	public static void main (String[] args)
	{
		new FavoriteColors();
	}
	
	private void printData()
	{
		Set<String> names = favoriteColors.keySet();
		Iterator<String> iter = names.iterator();
		System.out.println("Existing Data (if any):");
		while(iter.hasNext())
		{
			String key = iter.next();
			System.out.println(key + " : " + favoriteColors.get(key));
		}
	}
	
	
	private void processMainMenuInput(String userArg)
	{
		if (userArg.equals("data")) {
			printData();
			processMainMenuInput(presentMenu());
		} else if (userArg.equals("new")) 
		{
			addNewData();
		} else if (userArg.equals("save"))
		{
			save();
		} else if (userArg.equals("exit"))
		{
			return;
		}
		processMainMenuInput(presentMenu());
	}
	
	private void addNewData()
	{
		System.out.println("Adding a new data entry...");
		Set<String> keys = favoriteColors.keySet();
		String name;
		do {
			System.out.println("Please enter a new unique name.  Or type 'menu' to return to the main menu. >");
			name = in.nextLine();
		} while (keys.contains(name));
		
		if (name.equals("menu"))
			return;
		
		System.out.println("Enter the name of a color >");
		String color = in.nextLine();
		System.out.println("Will add the data (" + name + ", " + color +  ") y/n ");
		String affirm = in.nextLine();
		
		if (affirm.equals("y"))
		{
			System.out.println("Adding new data now...");
			favoriteColors.put(name, color);
		}
		
	}
	
	private void save()
	{
		try{
		    PrintWriter writer = new PrintWriter("favoriteColorData.txt", "UTF-8");
		    Set<String> keys = favoriteColors.keySet();
		    for (String key : keys)
		    {
		    		writer.println(key+"\t"+favoriteColors.get(key));
		    }
		    writer.close();
		} catch (IOException e) {
		   // do something
		}

		
	}
	
	
	private String presentMenu()
	{
		String userArgument = "";
		while(true)
		{
			System.out.println("What would you like to do?");
			System.out.println("'data' : display all data entires");
			System.out.println("'new' :  add new data entry");
			
			//System.out.println("3) remove data entry");
			//System.out.println("4) edit data entry");
			System.out.println("'save' : save changes");
			System.out.println("'exit' :  exit the program without saving");
			//System.out.println("5) exit without saving");
			System.out.print("Enter a command >");
			try
			{
				userArgument = in.next();
				in.nextLine();
				break;
			}
			catch (java.util.InputMismatchException e)
			{
				System.out.println("There was a problem.");
				in.reset();
				continue;
			}
		}
		return userArgument;
	}
	
	private Arrow parseDataEntry(String line)
	{
		String[] words = line.split("\\t");
		String name = words[0];
		String color = words[1];
		System.out.println("parsing.  Found name " + name + " and color " + color);
		Arrow arr = new Arrow(name,color);
		return arr;
	}
	
	
	private void loadData() throws FileNotFoundException
	{
		File in = new File("favoriteColorData.txt");
		Scanner scn = new Scanner(in);
		while (scn.hasNextLine())
		{
			Arrow arr = parseDataEntry(scn.nextLine());
			favoriteColors.put(arr.name, arr.color);
		}
		scn.close();
	}
}
