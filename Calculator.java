public class Calculator
{
	public int evaluatePostFix (String s) throws InvalidPostfixException
	{
		try{
		MyStack obj = new MyStack();			
		int p=s.length();					// length of the incoming string
		int a;						// used for pop when (+,-,*) encounters
		int b;
		int t=0;
		int h;
		for(int j=0;j<p;j++)
		{
			while(s.charAt(j)==' ')
			{
				j++;   
				if(j==p)				// if last character of string is ' ' then to break the while loop otherwise we get string out of index
				{
					break;
				}       
			}
			if(j==p)					// to verify we havenot encountered break statement
				break;
			if(s.charAt(j)>='0' && s.charAt(j)<='9')
			{
				h=j;				// for starting index of digit
				while(s.charAt(j)>='0' && s.charAt(j)<='9')
				{         
					j++; 
					if(j==p)
						break;       
				}
				Integer g =Integer.parseInt(s.substring(h,j));	// convert a digit string to integer
				obj.push(g);
			}
			else if(s.charAt(j)=='+' || s.charAt(j)=='-' || s.charAt(j)=='*')
			{
				t=obj.i;
				if(t-2>=0)					// to check if there are more than one operands otherwise will get empty stack exception
				{
					b= (int) obj.pop();
					a=(int) obj.pop();
					if(s.charAt(j)=='+')
						obj.push(a+b);
					else if(s.charAt(j)=='-')
						obj.push(a-b);
					else if(s.charAt(j)=='*')
						obj.push(a*b);
					j++;
				}
				else
					throw new InvalidPostfixException("Expression invalid");
			}
			else						//  if none of the operator nor operands are encountered
				throw new InvalidPostfixException("Expression invalid");
			if(j==p)						// beacuse we have to check for atleast one whitespace and if string is complete then it may show string out of index
				break;
			else if(s.charAt(j)!=' ')
			{
				throw new InvalidPostfixException("Expression invalid");
			}			
		}
		t=obj.i;
		if(t!=1)					// if t is 2 or more then there are more than 1 operands left without a opertor
			throw new InvalidPostfixException("Expression invalid");
		return (int) obj.pop();
		}
		catch(EmptyStackException ex)
		{
			throw new InvalidPostfixException("Expression invalid");
		}
	}
	public String convertExpression (String s) throws InvalidExprException 
	{
		try{
		MyStack obj=new MyStack();
		int p=s.length();				// length of the incoming string
		int h;
		Object q=new Object();
		String ans=new String();			// for returning the string
		int j=0;
		int e=0;
		for(int l=0;l<p;l++)				// to check if a operator is between two operands or not
		{
			if(s.charAt(l)=='+' || s.charAt(l)=='-' || s.charAt(l)=='*')
			{
				if(l==0 || l==(p-1))		
					throw new InvalidExprException("Invalid Expression");
				for(e=l-1;e>=0;e--)
				{
					if(s.charAt(e)>='0' && s.charAt(e)<='9')
						break;
					else if(s.charAt(e)==')' || s.charAt(e)==' ')
						continue;
					else
						throw new InvalidExprException("Invalid Expression");
				}
				if(e==(-1))
					throw new InvalidExprException("Invalid Expression");
				for(e=l+1;e<p;e++)
				{
					if(s.charAt(e)>='0' && s.charAt(e)<='9')
						break;
					else if(s.charAt(e)=='(' || s.charAt(e)==' ')
						continue;
					else
						throw new InvalidExprException("Invalid Expression");
				}
				if(e==p)
					throw new InvalidExprException("Invalid Expression");
			}
		}
		while(j<p)				// for making it in postfix format
		{
			while(s.charAt(j)==' ')		// neglecting whitespaces
			{
				j++;   
				if(j==p)
				{
					break;
				}       
			}
			if(j==p)
				break;
			if(s.charAt(j)>='0' && s.charAt(j)<='9')		// if digits are there then stored in a string with single whitespace ahead
			{
				h=j;
				while(s.charAt(j)>='0' && s.charAt(j)<='9')
				{        
					 j++;
					if(j==p)
						break;        
				}
				ans=ans+s.substring(h,j)+" ";
			}
			else if(s.charAt(j)=='+' || s.charAt(j)=='-' || s.charAt(j)=='*')			// operators are pushed in a stack
			{
				if(obj.i==0 || obj.top().equals('('))				// if stack is empty then push the operator
					obj.push(s.charAt(j));
				else if(s.charAt(j)=='*')					//  if multiply operator then no need of checking precedence
					obj.push(s.charAt(j));
				else							// if add or minus operator are there
				{
					if(obj.i!=0)					// stack is not empty otherwise checking for top will give exception
					{
						while(obj.top().equals('*'))
						{
							ans=ans+obj.pop()+" ";
							if(obj.i==0)       break;
						}
					}
					if(obj.i==0)					// if stack empty then just push the operator
						obj.push(s.charAt(j));
					else if(obj.top().equals('('))				
						obj.push(s.charAt(j));
					else if(obj.top().equals(s.charAt(j)))			// if we have same operator then push it
						obj.push(s.charAt(j));
					else						// if we have different opertor then first pop it then push the incoming one
					{
						ans=ans+obj.pop()+" ";
						obj.push(s.charAt(j));
					}
				}
				j++;
			}
			else if(s.charAt(j)=='(')			
			{
				obj.push(s.charAt(j));
				j++;
			}
			else if(s.charAt(j)==')')			
			{
				while(!obj.top().equals('('))			// check for its openings symbol
				{
					ans=ans+obj.pop()+" ";
				}
				q=obj.pop();				// if gets it opening symbol then just poping it out
				j++;
			}
			else						//other symbols are invalid
				throw new InvalidExprException("Invalid Expression");
		}
		while(obj.i!=0 && obj.i!=1)					//if stacks remains unemptied
		{
			if(!obj.top().equals('('))
			{
				ans=ans+obj.pop()+" ";
			}
			else
				throw new InvalidExprException("Invalid Expression");
		}
		if(obj.i==1)
		{
			if(!obj.top().equals('('))
			{
				ans=ans+obj.pop()+" ";
			}
			else
				throw new InvalidExprException("Invalid Expression");
		}
		p=ans.length();			// length of answer string 
		if(ans.charAt(p-1)==' ')		// to remove last whitespace if any
		{
			ans=ans.substring(0,p-1);
		}
		return ans;
		}
		catch(EmptyStackException ex)
		{
			throw new InvalidExprException("Invalid Expression");
		}
	}
}