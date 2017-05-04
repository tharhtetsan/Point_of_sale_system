package Pointofsale;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

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

import java.sql.Connection;
import java.sql.Statement;

import pack.*;

public class SupplierList extends JDialog implements ActionListener{
	
	JTable tblSupplier,tempTable;
	JScrollPane jsp;
	JPanel p1,p2;
	JButton btnPrint,btnClose;
	

	mySQLQueries msql=new mySQLQueries();
	Statement ste=null;
	Connection con=null;
	
	DefaultTableModel dtm=new DefaultTableModel();
	
	public SupplierList()
	{		
		setTitle("Supplier Listing");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(null);
		
		try{
			con=DBConnection.GetMySQLConnection();
			}
		catch(ClassNotFoundException cnfe)
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
		
		p1=new JPanel();
		p1.setBounds(0, 0, 780, 500);
		p1.setLayout(null);
		p1.setBorder(BorderFactory.createLineBorder(Color.black));
		
		p2=new JPanel();
		p2.setBounds(400, 500, 380, 100);
		p2.setLayout(null);
		p2.setBackground(Color.gray);
		p2.setBorder(BorderFactory.createLineBorder(Color.red));	
		
		tblSupplier=new JTable();
		jsp=new JScrollPane(tblSupplier);
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
		
		fillSupplier();
		
	}
	public void fillSupplier()
	{
		String strdataitem[]=new String[5];
		try
		{
			Statement ste=con.createStatement();
			String str="select * from Supplier";
			ResultSet rs=ste.executeQuery(str);
			while(rs.next())
			{
				strdataitem[0]=rs.getString(1);
				strdataitem[1]=rs.getString(2);
				strdataitem[2]=rs.getString(3);
				strdataitem[3]=rs.getString(4);
				strdataitem[4]=rs.getString(5);
				dtm.addRow(strdataitem);
			}
			tblSupplier.setModel(dtm);
			
		}catch(SQLException sqle)
		{
			System.out.println(sqle);
		}
	}
	public void printData()
	{
		try
		{
			tblSupplier.print();
		}catch(Exception e)
		{
			JOptionPane.showMessageDialog(this, e);
		}
	}
	
	public void setColumnWidth(int index,int width)
	{
		DefaultTableColumnModel tcm = (DefaultTableColumnModel) tblSupplier.getColumnModel();
		TableColumn tc = new TableColumn();
		tc.setPreferredWidth(width);
		
	}
	
	private void createTable()
	{
		dtm.addColumn("Supplier ID");
		dtm.addColumn("Supplier Name");
		dtm.addColumn("Address");
		dtm.addColumn("Phone No");
		dtm.addColumn("Email");
		tblSupplier.setModel(dtm);
		
		
		setColumnWidth(0, 50);
		setColumnWidth(1, 100);
		setColumnWidth(2, 50);
		setColumnWidth(3, 100);
		setColumnWidth(4, 80);
	}
	public void closeData()
	{
		if((JOptionPane.showConfirmDialog(this, "Are you sure to want to exit?","Confirm exit",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION))
			dispose();
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new SupplierList();
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
		
	}

}
