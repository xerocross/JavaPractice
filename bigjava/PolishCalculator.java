package bigjava;
import java.util.*;

public class PolishCalculator {

	private Stack<Double> inputStack;
	
	public static void main (String[] args)
	{
		new PolishCalculator();
	}
	
	
	public PolishCalculator()
	{
		Scanner in = new Scanner(System.in);
		inputStack =  new Stack<Double>();
		
		System.out.println("Enter an expression in well-formed reverse-Polish notation.");;
		System.out.println("Operations allowed are +, -, *, /, and ^");

		String input = in.nextLine();
		String[] words = input.split(" ");
		int size = words.length;
		
		for (int i = 0; i < size; i++) 
		{
			String word = words[i];
			try 
			{
				double arg = Double.parseDouble(word);
				inputStack.push(arg);
				
			}
			catch (NumberFormatException e)
			{
				if (word.equals("*"))
				{
					double arg2 = inputStack.pop();
					double arg1 = inputStack.pop();
					double ans = arg1*arg2;
					inputStack.push(ans);
				} else if (word.equals("+")) {
					double arg2 = inputStack.pop();
					double arg1 = inputStack.pop();
					double ans = arg1+arg2;
					inputStack.push(ans);
				} else if (word.equals("/")) {
					double arg2 = inputStack.pop();
					double arg1 = inputStack.pop();
					if (arg2 == 0)
						throw new ArithmeticException();
					double ans = arg1/arg2;
					inputStack.push(ans);
				}  else if (word.equals("-")) {
					double arg2 = inputStack.pop();
					double arg1 = inputStack.pop();
					double ans = arg1-arg2;
					inputStack.push(ans);
				} else if (word.equals("^")) {
					double arg2 = inputStack.pop();
					double arg1 = inputStack.pop();
					double ans = Math.pow(arg1, arg2);
					inputStack.push(ans);
				} else {
					throw new IllegalArgumentException();
				}
			}
		}
		double result = inputStack.pop();
		if (inputStack.size() > 0)
			throw new IllegalArgumentException();
		System.out.println(result);
		in.close();
	}
	

}
