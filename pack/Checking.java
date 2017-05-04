package pack;
import javax.swing.*;
import java.util.*;
public class Checking {

	public static boolean IsValidName(String str)
	{
		if(IsNull(str)||str.startsWith(" "))
			return false;
		if(!IsLetter(str))
			return false;
		
		return true;
	}
	
	public static boolean IsNull(String str)
	{
		if(str.trim().equals("")||str.trim().equals(null))
			return true;
		else
			return false;
	}
	
	
	public static boolean IsLetter(String str)
	{
		for(int i=0;i<str.length();i++)
		{
			return true;
		}
		return false;
	}
	
	
	public static boolean IsAllDigit(String str)
	{
		
		for(int i=0;i<str.length();i++)
		if(!Character.isDigit(str.charAt(i)))
			return false;
		
		return true;
	}
	
	public static boolean IsContain(char c,String str)
	{
		for(int i=0;i<str.length();i++)
		{
			if(str.charAt(i)==c)
				return true;
		}
		
		return false;
	}
	
	public static boolean IsContain(String s,Vector str)
	{
		for(int i=0;i<str.size();i++)
		{
		if(s.equals((String)str.elementAt(i)))
		return true;
		}
		
		return false;
	}
	
	public static boolean checktxtquantity(String strqp)
	{
		if(strqp.equals(""))
		{
			JOptionPane.showMessageDialog(null, "You must enter the Quantity");
			return false;
		}else if(!IsAllDigit(strqp))
		{
			JOptionPane.showMessageDialog(null, "You must enter the NUMBER for Quantity");
			return false;
		
		}else if(Integer.parseInt(strqp)>10000)
		{
			JOptionPane.showMessageDialog(null, "The Quantity you entered is too manay to purchase !");
			return false;
		
		}
		else if(Integer.parseInt(strqp)<=0)
		{
			JOptionPane.showMessageDialog(null, "The Quantity you entered must be greather than 0 !");
			return false;
		
		}
		else
			return true;
		
		
	}
	
	
	public static boolean checktxtprice(String strqp)
	{
		if(strqp.equals(""))
		{
			JOptionPane.showMessageDialog(null, "You must enter the price");
			return false;
		}else if(!IsAllDigit(strqp))
		{
			JOptionPane.showMessageDialog(null, "You must enter the NUMBER for price");
			return false;
		
		}else if(Integer.parseInt(strqp)>100000000)
		{
			JOptionPane.showMessageDialog(null, "The price you entered is too much !");
			return false;
		
		}else if(Integer.parseInt(strqp)<=0)
		{
			JOptionPane.showMessageDialog(null, "The price you entered must be greather than 0 !");
			return false;
		
		}
		else
			return true;
		
		
	}
	
	
	public static String Sumamount(Vector data,int t)
	{
		long sum=0;
		for(int i=0;i<data.size();i++)
			sum+=Long.parseLong(data.get(i).toString());
		
		
		if(t==1)
		{
			int len=String.valueOf(sum).length(),index=0;
			StringBuffer str=new StringBuffer();
			for(int i=0;i<len;i++)
			{
				if(index==3)
				{
					str.append(",");
					index=0;
					i--;
				}else
				{
					str.append(String.valueOf(sum).charAt(len-i-1));
					index++;
				}
			}
			
			return str.reverse().toString();
		}
		else
		{
			return String.valueOf(sum);
		}
	}

}
