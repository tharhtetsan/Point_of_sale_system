package Pointofsale;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import pack.*;



public class ItemList extends JDialog implements ActionListener{
	
	JTable tblItem,tempTable;
	JScrollPane jsp;
	JPanel p1,p2;
	JButton btnPrint,btnClose;
	
	MyTable mt=new MyTable();
	mySQLQueries msql=new mySQLQueries();
	Statement ste=null;
	Connection con=null;
	DefaultTableModel dtm=new DefaultTableModel();
	
	public ItemList()
	{		
		setTitle("Item Listing" );
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(null);
		
		p1=new JPanel();
		p1.setBounds(0, 0, 780, 500);
		p1.setLayout(null);
		p1.setBorder(BorderFactory.createLineBorder(Color.black));
		
		p2=new JPanel();
		p2.setBounds(400, 500, 380, 100);
		p2.setLayout(null);
		p2.setBackground(Color.gray);
		p2.setBorder(BorderFactory.createLineBorder(Color.red));	
		
		tblItem=new JTable();
		tblItem=MyTable();
		jsp=new JScrollPane(tblItem);
		jsp.setBounds(0, 0, 770, 490);
		p1.add(jsp);
		
		btnPrint=new JButton("Print");
		btnPrint.setBounds(50, 30, 100, 35);
		btnPrint.addActionListener(this);
		p2.add(btnPrint);
		
		btnClose=new JButton("Close");
		btnClose.setBounds(250, 30, 100, 35);
		btnClose.addActionListener(this);
		p2.add(btnClose);
		
		getContentPane().add(p1);
		getContentPane().add(p2);
		setBounds(50, 50, 800, 640);
		setVisible(true);
		createTable();
		
		
		try
		{
			con=DBConnection.GetMySQLConnection();
		}
			catch(ClassNotFoundException cnfe)
			{
				System.out.println(cnfe);
			}
		catch (SQLException sqle) {
			System.out.println(sqle);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		fillItem();
		
				
		
	}
	public void setColumnWidth(int index,int width)
	{
		DefaultTableColumnModel tcm = (DefaultTableColumnModel) tblItem.getColumnModel();
		TableColumn tc = new TableColumn();
		tc.setPreferredWidth(width);
		
	}
	public void fillItem()
	{
		String strdataitem[]=new String[6];
		String strquery[]=new String[2];
		try
		{
			Statement ste=con.createStatement();
			String str="select * from vi_item";
			ResultSet rs=ste.executeQuery(str);
			while(rs.next())
			{
				strdataitem[0]=rs.getString(1);
				strdataitem[1]=rs.getString(2);
				strdataitem[2]=rs.getString(3);
				strdataitem[3]=rs.getString(4);
				strdataitem[4]=rs.getString(5);
				strdataitem[5]=rs.getString(6);
				
				dtm.addRow(strdataitem);
			}
			tblItem.setModel(dtm);
			
		}catch(SQLException sqle)
		{
			System.out.println(sqle);
		}
	}
	public void printData()
	{
		try
		{
			tblItem.print();
		}catch(Exception e)
		{
			JOptionPane.showMessageDialog(this, e);
		}
	}
				
			
		
	
	
	private void createTable()
	{
		dtm.addColumn("Item ID");
		dtm.addColumn("Brand Name");
		dtm.addColumn("Type Name");
		dtm.addColumn("Item Name");
		dtm.addColumn("Saler Price");
		dtm.addColumn("Quantity");
		
		tblItem.setModel(dtm);
		
		
		setColumnWidth(0, 50);
		setColumnWidth(1, 50);
		setColumnWidth(2, 50);
		setColumnWidth(3, 70);
		setColumnWidth(4, 80);
		setColumnWidth(4, 80);
		
	}
	public void closeData()
	{
		if((JOptionPane.showConfirmDialog(this, "Are you sure to want to exit?","Confirm exit",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION))
			dispose();
	}
	public JTable MyTable(){
    	try
    	{    	
    	tempTable=new JTable(new Object[100][5],new Object[]{"Item ID","Item Detail","Price","Quantity","Remark"});
    	mt.packColumn(tempTable,0,130);	//mt.packColumn(JTable,ColumnNo,Char(ColumnSize));
    	mt.packColumn(tempTable,1,30);
    	mt.packColumn(tempTable,2,50);
    	mt.packColumn(tempTable,3,100);   
    	mt.packColumn(tempTable, 4, 100);
    	tempTable.setEnabled(true);
    	
    	for (int i=0;i<5;i++)
    		for (int j=0;j<100;j++)
    			tempTable.setValueAt(null,j,i);
    	
    	}catch(Exception e)
    	{	
    		e.printStackTrace();return null;
    	}
    	
    	return tempTable;
    }
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ItemList();
	}

	
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource().equals(btnPrint))
		{
			printData();
		}
		
		if(ae.getSource().equals(btnClose))
		{
			closeData();
			
		}
		// TODO Auto-generated method stub
		
	}

}
