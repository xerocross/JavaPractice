package bigjava;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Iterator;
import java.io.*;

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
		} 
			else if (userArg.equals("new")) 
		{
			addNewData();
		} 
			else if (userArg.equals("save"))
		{
			save();
		} 
			else if (userArg.equals("edit"))
		{
			edit();
		} 
			else if (userArg.equals("exit"))
		{
			return;
		}
		processMainMenuInput(presentMenu());
	}
	
	private Arrow collectNewData()
	{
		System.out.println("Enter a name >");
		String name = in.nextLine();
		if (name.equals("menu"))
			return null;
		System.out.println("Enter a color >");
		String color = in.nextLine();
		return new Arrow(name, color);
	}

	private void addNewData()
	{
		System.out.println("Adding a new data entry...");
		Set<String> keys = favoriteColors.keySet();
		Arrow arr;
		boolean repeating = false;
		do {
			if (repeating)
				System.out.println("That name is already in use.  Try again...");
			arr = collectNewData();
			repeating = true;
		} while (keys.contains(arr.name));
		System.out.println("Will add the data (" + arr.name + ", " + arr.color +  ") y/n ");
		String affirm = in.nextLine();
		if (affirm.equals("y"))
		{
			System.out.println("Adding new data now...");
			favoriteColors.put(arr.name, arr.color);
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
		    System.out.println("saved.");
		} catch (IOException e) {
			System.out.println("There was a problem saving...");
		}
	}
	
	private String chooseData()
	{
		System.out.println("Choose a data item...");
		Map<Integer,String> choiceMap = new HashMap<Integer,String>();
		Set<String> keys = favoriteColors.keySet();
		int i = 1;
		for (String key : keys) 
		{
			System.out.println(i+") " + key + " : " + favoriteColors.get(key));
			choiceMap.put(i, key);
			i++;
		}
		int arg;
		while(true)
		{
			try 
			{
				System.out.println("Select a data entry by line number. >");
				arg = in.nextInt();
				in.nextLine();
				break;
			} catch (Exception e) {
				System.out.println("There was a problem with the input.  Please try again or terminate the program.");
				continue;
			}
		}
		return choiceMap.get(arg);
	}
	
	private void edit()
	{
		String key = chooseData();
		System.out.println("Now gathering new data to replace this entry...");
		Arrow arr = collectNewData();
		System.out.println("Replace the data...");
		System.out.println(key + " : " + favoriteColors.get(key));
		System.out.println(arr.name + " : " + arr.color);
		System.out.println("Confirmed?  y/n");
		String input = in.nextLine();
		if (input.equals("y"))
		{
			favoriteColors.remove(key);
			favoriteColors.put(arr.name, arr.color);
			System.out.println("Edit was made.");
		} else {
			System.out.println("No change was made.");
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
			System.out.println("'edit' : edit data entry");
			System.out.println("'save' : save changes");
			System.out.println("'exit' :  exit the program without saving");
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
