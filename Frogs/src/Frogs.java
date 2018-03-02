import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;


public class Frogs 
{

	public static String SFgenerator(int n, int len, char[] st, char c)
	{
		String s = "";
		if (c == 's')
		{
			int pos = 0;
		    while (pos < n) 
		    {
		        st[pos] = '>';
		        pos++;
		    }
		    st[pos] = '_';
		    pos++;
		    while (pos < len)
		    {
		    	st[pos] = '<';
		    	pos++;
		    }
			s = new String(st);
		}
		else if (c == 'f')
		{
			int pos = 0;
		    while (pos < n) 
		    {
		        st[pos] = '<';
		        pos++;
		    }
		    st[pos] = '_';
		    pos++;
		    while (pos < len)
		    {
		    	st[pos] = '>';
		    	pos++;
		    }
			s = new String(st);	
		}
		return s;
	}
	
	public static String nullgenerator(int len, char[] st)
	{
		int pos = 0;
	    while (pos < len) 
	    {
	        st[pos] = '0';
	        pos++;
	    }
		String s = new String(st);
		return s;
	}
	
	public static boolean validator(String cur, String fin)
	{
		if (cur.equals(fin))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static String nextgenerator(String cur, int len, List<String> v)
	{
		char[] next = cur.toCharArray();
		String s = "";
		int pos = 0;
		char c;
		char[] begin = cur.toCharArray();
		char[] begin1 = cur.toCharArray();
		char[] begin2 = cur.toCharArray();
		char[] begin3 = cur.toCharArray();
		char[] begin4 = cur.toCharArray();
		char[] begin5 = cur.toCharArray();
		while(next[pos] != '_')
		{
			pos++;
			//System.out.println(next[pos]);
		}
		if(pos != len - 1)
		{
			if(next[pos+1] == '<')
			{
				c = next[pos];
				next[pos] = next[pos+1];
				next[pos+1] = c;
				s = new String(next);
				//System.out.println(next);
				next = begin;
				//System.out.println(next);
			}
			if((pos < len - 2 && next[pos+2] == '<' && v.contains(s)) || (pos < len - 2 && 
					next[pos+2] == '<' && s == ""))
			{
				c = next[pos];
				next[pos] = next[pos+2];
				next[pos+2] = c;
				s = new String(next);
				//System.out.println(next);
				next = begin1;
				//System.out.println(next);
			}
			if((pos != 0 && v.contains(s)) || (pos != 0 && s == ""))
			{
				if(next[pos-1] == '>')
				{
					c = next[pos];
					next[pos] = next[pos-1];
					next[pos-1] = c;
					//System.out.println(next);
					s = new String(next);
					next = begin2;
					//System.out.println(next);
				}
				if((pos - 2 >= 0 && next[pos-2] == '>' && v.contains(s)) || 
						(pos - 2 >= 0 && next[pos-2] == '>' && s == ""))
				{
					c = next[pos];
					next[pos] = next[pos-2];
					next[pos-2] = c;
					//System.out.println(next);
					s = new String(next);
					next = begin3;
				}
				/*if(v.contains(s) || s == "")
				{
					s = nullgenerator(len, next);
				}*/
			}
			if(v.contains(s) || s == "")
			{
				s = nullgenerator(len, next);
			}
		}
		else 
		{
			if(next[pos-1] == '>')
			{
				c = next[pos];
				next[pos] = next[pos-1];
				next[pos-1] = c;
				//System.out.println(next);
				s = new String(next);
				next = begin4;
			}
			if(next[pos-2] == '>' && v.contains(s))
			{
				c = next[pos];
				next[pos] = next[pos-2];
				next[pos-2] = c;
				//System.out.println(next);
				s = new String(next);
				next = begin5;
			}
			if (v.contains(s) || s == "")
			{
				s = nullgenerator(len, next);
			}
		}
		if(v.contains(s))
		{	
			s = nullgenerator(len, next);
			return s;
		}
		else
		{
			return s;
		}
	}
	
	/*public static void dfs(String begin, String end, String nulls, Stack<String> s, 
			List<String> visited, int len)
	{
		if(!begin.equals(end))
		{
			if (!begin.equals(nulls))
			{
				s.push(begin);
				visited.add(begin);
				begin = nextgenerator(begin, len, visited);
				dfs(begin, end, nulls, s, visited, len);
			}
			else
			{
				s.pop();
				begin = nextgenerator(s.peek(), len, visited);
				dfs(begin, end, nulls, s, visited, len);
			}
		}
		else
		{
			s.push(begin);
			Stack<String> path = new Stack<String>();
			while(!s.empty())
			{
				path.push(s.pop());
			}
			while(!path.empty())
			{
				System.out.println(path.pop());
			}
		}
	} -- works only for 2-8 frogs */
	
	public static void dfs2(String start, String begin, String end, String nulls, 
			Stack<String> s, List<String> visited, int len)
	{
		Stack<String> path = new Stack<String>();
		s.push(begin);
		while(!s.empty())
		{
			if(validator(s.peek(), end))
			{
				while(!s.empty())
				{
					path.push(s.pop());
				}
				while(!path.empty())
				{
					System.out.println(path.pop());
				}
				break;
			}
			if(!visited.contains(s.peek()) && !s.peek().equals(nulls))
			{
				visited.add(s.peek());
			}
			else
			{
				if(begin.equals(start))
				{
					begin = nextgenerator(begin, len, visited);
				}
				if(!begin.equals(nulls))
				{
					s.push(begin);
					begin = nextgenerator(begin, len, visited);
				}
				else
				{
					s.pop();
					begin = nextgenerator(s.peek(), len, visited);
				}
			}
		}
	}
	
	public static void main(String[] args) 
	{
		int n = 0;
		Scanner s = new Scanner(System.in);
		n = s.nextInt();
		int len = n * 2 + 1;
		char[] st = new char[len];
		String first = SFgenerator(n, len, st, 's');
		String last = SFgenerator(n, len, st, 'f');
		char[] zeroes = new char[len];
		String nulls = nullgenerator(len, zeroes);
		Stack<String> stack = new Stack();
		List<String> visited = new ArrayList<String>();
		//dfs(first, last, nulls, stack, visited, len);
		dfs2(first, first, last, nulls, stack, visited, len);
	}
}
