package pack;

import java.sql.*;

import javax.swing.JOptionPane;


public class mySQLQueries {

	static Connection con=null;
	static Statement stmt;
	static String query,query1;
	ResultSet rs;
	
	DBConnection connect=new DBConnection();
	
	public mySQLQueries()
	{
		try{
			con=connect.GetMySQLConnection();
			
		}catch(ClassNotFoundException cnfe)
		{
			System.out.println(cnfe);
		}catch(SQLException sqle)
		{
			System.out.println(sqle);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public String getAutoid(String field,String tabel,String prefix)
	{
		if(tabel.equals("sale")||tabel.equals("purchase"))
		{
			return connect.getPrimaryKey(field, tabel, prefix);
		}
		else
		{
			return connect.getPrimaryKey2(field, tabel, prefix);
		}
	}

	public boolean isduplicate(String tbName, String[] data) {
		
		if(tbName.equals("brand"))
		{
			query="select * from brand where brandName ='"+data[0]+"'";
		}else if(tbName.equals("type"))
		{
			query="select * from type where typeName ='"+data[0]+"'";
		}else if(tbName.equals("merchandise"))
		{
			query="select * from merchandise where brandid='"+data[0]+"'and typeid='"+data[1]+"'";
		}else if(tbName.equals("itemdetail"))
		{
			query="select * from itemdetail where itemname='"+data[0]+"'and merid='"+data[1]+"'";
		}else if(tbName.equals("supplier"))
		{
			query="select * from supplier where name='"+data[0]+"'and address='"+data[1]+"'";
		}else if(tbName.equals("customer"))
		{
			query="select * from customer where name='"+data[1]+"'and address='"+data[3]+"' and phoneno= '"+data[2]+"';";
			
		}
		
		System.out.println(query);
		try{
			stmt=con.createStatement();
			rs=stmt.executeQuery(query);
			if(rs.next())
				return true;
			else
				return false;
		}
		catch(SQLException sqle)
		{
			return false;
		}

	}

	public boolean insertData(String tbName, String[] data) {
		if(tbName.equals("brand"))
		{
			query="insert into brand (brandid,brandname) values('"+data[0]+"','"+data[1]+"')";
		}
		else if(tbName.equals("type"))
		{
			query="insert into type (typeid,typename) values('"+data[0]+"','"+data[1]+"')";
				
		}else if(tbName.equals("merchandise"))
		{
			query="insert into merchandise (merid,brandid,typeid) values('"+data[0]+"','"+data[1]+"','"+data[2]+"')";
			
		}else if(tbName.equals("itemdetail"))
		{
			query="insert into itemdetail (itemid,merid,itemname,remark) values('"+data[0]+"','"+data[1]+"','"+data[2]+"','"+data[3]+"')";
				
		}else if(tbName.equals("supplier"))
		{
			query="insert into supplier (supplierid,name,address,phoneno,email) values('"+data[0]+"','"+data[1]+"','"+data[2]+"','"+data[4]+"','"+data[3]+"')";
				
		}else if(tbName.equals("purchase"))
		{
			int cat=data[2].indexOf("(");
			
			query="insert into purchase(purchaseid,supplierid,purchasedate) values ('"+data[0]+"', '"+data[1]+"','"+data[2].substring(0,cat)+"')";
		}else if(tbName.equals("purchasedetail"))
		{
			query="insert into purchasedetail (purchaseid,purchaseprice,purchasequantity,itemid) values ('"+data[0]+"',"+Long.parseLong(data[2])+","+Integer.parseInt(data[3])+",'"+data[1]+"')";
		}else if(tbName.equals("customer"))
		{
			query="insert into customer (customerid,name,address,phoneno,email) values ('"+data[0]+"','"+data[1]+"','"+data[3]+"','"+data[2]+"','"+data[4]+"')";
		}else if(tbName.equals("sale"))
		{
			query="insert into sale (saleid,customerid,slaedate) values ('"+data[0]+"','"+data[1]+"','"+data[2]+"')";
		}else if(tbName.equals("saledetail"))
		{
			query="insert into saledetail (slaeid,slaeprice,slaequantity,itemid) values ('"+data[0]+"','"+data[1]+"','"+data[2]+"','"+data[3]+"')";
		}
		
		System.out.println(query);
		try{
			stmt=con.createStatement();
			int i=stmt.executeUpdate(query);
			if(i==1)
				return true;
			else
				return false;
		}
		catch(SQLException sqle)
		{
			return false;
		}

	}

	public String[] getNameForChoice(String tbName) {
		
		try{
			if(tbName.equals("type"))
				rs=connect.SQLSelect("typeName","type");
			else if(tbName.equals("brand"))
				rs=connect.SQLSelect("brandName", "brand");
			else if(tbName.equals("supplier"))
				rs=connect.SQLSelect("supplierid","supplier");
			else if(tbName.equals("itemdetail"))
				rs=connect.SQLSelect("itemid","itemdetail");
			
			
			int rowcount=0;
			
			while(rs.next())
			{
				rowcount++;
			}
			String []temp=new String[rowcount];
			rs.beforeFirst();
			
			int i=0;
			
			while(rs.next())
			{
				temp[i]=rs.getString(1);
				i++;
			}
			
			return temp;
		}catch(SQLException sqle)
		{
			System.out.println(sqle);
			return null;
		}
		
		
	}

	public String getBrandID(String brandname) {
		try{
			String brandid;
			stmt=con.createStatement();
			query="select * from brand where brandname='"+brandname+"';";
			rs=stmt.executeQuery(query);
			
			rs.next();
			brandid=rs.getString(1);
			
			return brandid;
			
		}catch(SQLException e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
			return null;
		}
	}

	public String getTypeID(String typename) {
	
		try{
			String typeid;
			stmt=con.createStatement();
			query="select * from type where typename='"+typename+"';";
			rs=stmt.executeQuery(query);
			rs.next();
			typeid=rs.getString(1);
			return typeid;
		}catch(SQLException sqle)
		{
			System.out.println(sqle);
			return null;
		}
		
	}

	public String[] getNameType(String bname) {
		
		
		try{
				rs=connect.SQLSelect("typeName", "vi_brantype where brandname='"+bname+"'" );
			
			int rowcount=0;
			
			while(rs.next())
			{
				rowcount++;
			}
			String []temp=new String[rowcount];
			rs.beforeFirst();
			
			int i=0;
			
			while(rs.next())
			{
				temp[i]=rs.getString(1);
				i++;
			}
			
			return temp;
		}catch(SQLException sqle)
		{
			System.out.println(sqle);
			return null;
		}
		
	}

	public String getMerid(String bname, String tname) {

		try{
			String mid;
			stmt=con.createStatement();
			query="select merid from vi_brantype where typename='"+tname+"' and brandname='"+bname+"'";
			rs=stmt.executeQuery(query);
			rs.next();
			mid=rs.getString(1);
			return mid;
		}catch(SQLException sqle)
		{
			System.out.println(sqle);
			return null;
		}
	}

	

	public String[] getSupplierData(String tbName,String id) {
		
		String str[]=new String[5];
		try{
			if(tbName.equals("supplier"))
			rs=connect.SQLSelectAll(id,"supplier");
			
			
			while(rs.next())
			{
				str[0]=rs.getString(2);
				str[1]=rs.getString(3);
				str[2]=rs.getString(4);
				str[3]=rs.getString(5);
				
				
			}
			
			return str;
		}catch(SQLException sqle)
		{
			System.out.println(sqle);
			return null;
		}
	}

	public boolean updateData(String tbName, String[] data) {
	
		if(tbName.equals("supplier"))
		{
			query="update supplier set name='"+data[1]+"', address= '"+data[2]+"',phoneno= '"+data[3]+"', email= '"+data[4]+"' where supplierid='"+data[0]+"';";
				
		}else if(tbName.equals("itemdetail"))
		{
			query="update itemdetail set itemname='"+data[3]+"', cursaleprice= '"+data[4]+"',remark= '"+data[5]+"' where itemid='"+data[0]+"';";
			
		}
		
		System.out.println(query);
		try{
			stmt=con.createStatement();
			int i=stmt.executeUpdate(query);
			if(i==1)
				return true;
			else
				return false;
		}
		catch(SQLException sqle)
		{
			return false;
		}
		
	}

	public void deleteRecord(String tbName, String id) {
		int returnValue=0;
		String query="";
		
		if(tbName.equals("supplier"))
		{
			query="delete from supplier where supplierid ='"+id+"'";
		}
		try{
			
			stmt=con.createStatement();
			if(!query.equals("")&&stmt.executeUpdate(query)==1)
				JOptionPane.showMessageDialog(null,"The record is deleted successfully in "+tbName+" table");
			else
				JOptionPane.showMessageDialog(null,"The specified ID does not found in the table ","Delete Fail",JOptionPane.ERROR_MESSAGE);
				
		}catch(SQLException e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage(),"SQL Exception",JOptionPane.ERROR_MESSAGE);
		}
				
	}

	public String[] getItemData(String itemid) {
		try{
			String str[]=new String[6];
			
			stmt=con.createStatement();
			query="select * from itemdetail where itemid ='"+itemid+"';";
			System.out.println(query);
			rs=stmt.executeQuery(query);
			
			while(rs.next())
			{
				str[0]=rs.getString(1);
				str[1]=rs.getString(2);
				str[2]=rs.getString(3);
				str[3]=rs.getString(4);
				str[4]=rs.getString(5);
				str[5]=rs.getString(6);
				
			}
			
			return str;
		}catch(SQLException sqle)
		{
			System.out.println(sqle);
			return null;
		}
	}

	public String[] getMerchandiseData(String merid) {
		
		
		try{
			String str[]=new String[2];
			stmt=con.createStatement();
			query="select * from vi_brantype where merid='"+merid+"';";
			System.out.println(query);
			rs=stmt.executeQuery(query);
			while(rs.next())
			{
				str[0]=rs.getString(1);
				str[1]=rs.getString(2);
			}
		
			return str;
		}catch(SQLException sqle)
		{
			System.out.println(sqle);
			return null;
		}
	}

	public String getBrandName(String brandid) {
		
	
		try{
			String brandname;
			stmt=con.createStatement();
			query="select brandname from brand where brandid='"+brandid+"';";
			rs=stmt.executeQuery(query);
			rs.next();
			brandname=rs.getString(1);
			return brandname;
		}catch(SQLException sqle)
		{
			System.out.println(sqle);
			return null;
		}
		
		
	}

	public String getTypeName(String typeid) {
		try{
			String typename;
			stmt=con.createStatement();
			query="select typename from type where typeid='"+typeid+"';";
			rs=stmt.executeQuery(query);
			rs.next();
			typename=rs.getString(1);
			return typename;
		}catch(SQLException sqle)
		{
			System.out.println(sqle);
			return null;
		}
	}

	public String[] getIDForChoice(String tbName) {
		try{
			if(tbName.equals("type"))
				rs=connect.SQLSelect("typeid","type");
			else if(tbName.equals("brand"))
				rs=connect.SQLSelect("brandid", "brand");
			else if(tbName.equals("supplier"))
				rs=connect.SQLSelect("supplierid","supplier");
			else if(tbName.equals("itemdetail"))
				rs=connect.SQLSelect("itemid","itemdetail");
			
			
			int rowcount=0;
			
			while(rs.next())
			{
				rowcount++;
			}
			String []temp=new String[rowcount];
			rs.beforeFirst();
			
			int i=0;
			
			while(rs.next())
			{
				temp[i]=rs.getString(1);
				i++;
			}
			
			return temp;
		}catch(SQLException sqle)
		{
			System.out.println(sqle);
			return null;
		}
		
	}

	public void P_updateitemquantity(String tbname, String id, String nprice, String data) {
		
		int r1=0;
		int price=0;
		
		mySQLQueries msql=new mySQLQueries();
		String q=msql.getItemData(id)[5];
		
		System.out.println("Save Qty= "+data);
		System.out.println("Save curQuantity= "+q);
		
		if(tbname.equals("purchase"))
		{
			r1=Integer.parseInt(q)+Integer.parseInt(data);
			
			price=Integer.parseInt(nprice)+(int)(Integer.parseInt(nprice)*0.1);
	
			System.out.println(price);
			query1="update itemdetail set cursaleprice="+price+", totalqty = "+r1+" where itemid ='"+id+"'";
			
		}else if(tbname.equals("sale"))
		{
			r1=Integer.parseInt(q)-Integer.parseInt(data);
			query1="update itemdetail set  totalqty = "+r1+" where itemid ='"+id+"'";
			
		}
		
		try{
			stmt=con.createStatement();
			if(stmt.executeUpdate(query1)==1)
			{
				System.out.println("One Record is updated Successfully");
			}
			else{
				JOptionPane.showMessageDialog(null, "Update Not Successful ","Update Fail",JOptionPane.ERROR_MESSAGE);;
				
			}
			
		}catch(SQLException e)
		{
			JOptionPane.showMessageDialog(null,e.getMessage(),"SQL Exception ",JOptionPane.ERROR_MESSAGE);
		}
		
	}

	public String[] getItemSale(String id) {
		
		String str[]=new String[5];
		try{
			stmt=con.createStatement();		
			rs=stmt.executeQuery("SELECT *  FROM  vi_item where itemid = '"+id+"';");
			
			
			
			if(rs.next())
			{
				str[0]=rs.getString(2);
				str[1]=rs.getString(3);
				str[2]=rs.getString(4);
				str[3]=rs.getString(5);
				str[4]=rs.getString(6);
				
				
				
			}
			
			return str;
		}catch(SQLException sqle)
		{
			System.out.println(sqle);
			return null;
		}
		
	}

	public String[] getCusteomerID(String tbName, String[] strDataItem) {
		
		try{
			String getID[]=new String [2];
			stmt=con.createStatement();
			query="select customerid,email from customer where name='"+strDataItem[1]+"' and address= '"+strDataItem[3]+"' and phoneno= '"+strDataItem[2]+"'";
			rs=stmt.executeQuery(query);
			System.out.println(query);
			rs.next();
			{
				getID[0]=rs.getString(1);
				getID[1]=rs.getString(2);
			}
			return getID;
		}catch(SQLException sqle)
		{
			System.out.println(sqle);
			return null;
		}
		
	}

	

	
	
}
