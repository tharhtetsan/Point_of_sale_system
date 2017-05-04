package pack;

import java.util.*;
public class myDate {
String year="";
	
	public String getDate()
	{
		StringBuffer str=new StringBuffer();
		Date date=new Date();
		
		System.out.println(date);
		year=String.valueOf(date).substring(24);
		str.append(year);
		
		str.append("-");
		str.append(date.getMonth()+1);
		str.append("-");
		
		str.append(String.valueOf(date).substring(8,10));
		
		str.append("(");
		str.append(String.valueOf(date).substring(0, 3));
		
		
		str.append(")");
		return str.toString();
		
	}

	public int getYear() {
		
		return Integer.parseInt(year);
	}
	
	public myDate()
	{
		getDate();
	}
}
