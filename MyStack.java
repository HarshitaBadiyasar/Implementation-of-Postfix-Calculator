public class MyStack implements StackInterface
{
	public Object[] array=new Object[1];		// array for the stack  
	public int s=1;				// for the size of array
	public int i=0;				// shows array is filled till (i-1)th index
	public Object p;				// used to top or pop
	public Object[] arr;				// used during doubling the array size for stack
	public void push(Object o)
	{
	if(o!=null){				// if null object is not pushed
		if(i==s)				// if array is full
		{
			arr=new Object[2*s];
			for(int q=0;q<s;q++)
				arr[q]=array[q];
			//Object[] array=new Object[2*s];
			//for(int q=0;q<s;q++)
				//array[q]=arr[q];
			s=2*s;
			array=arr;
			array[i]=o;
			i++;
		}
		else
		{
			array[i]=o;
			i++;
		}
	}
	}
	public Object pop() throws EmptyStackException
	{
		if(i==0)
		{
			throw new EmptyStackException(
			"Stack is empty ");
		}
		else
		{
			p=array[i-1];
			i=i-1;			
			return p;
		}	
	}
	public Object top() throws EmptyStackException
	{
		if(i==0)
		{
			throw new EmptyStackException(
			"Stack is empty ");
		}
		else
		{
			p=array[i-1];
			return p;
		}	
	}
	public boolean isEmpty()
	{
		if(i==0)
			return true;		// return true if array or stack is empty
		else
			return false;
	}
	public String toString()
	{
		String m="[";
		for(int y=(i-1);y>=0;y--)
		{
			m=m+String.valueOf(array[y]);
			if(y>0)
			{
				m=m+", ";
			}
		}
		m=m+"]";
		return m;
	}
}


// defining all the exception classes
class EmptyStackException extends Exception
{
	public EmptyStackException(String m)
	{
		super(m);
	}
}

class InvalidPostfixException extends Exception
{
	public InvalidPostfixException(String m)
	{
		super(m);
	}
}

class InvalidExprException extends Exception
{
	public InvalidExprException (String m)
	{
		super(m);
	}
}