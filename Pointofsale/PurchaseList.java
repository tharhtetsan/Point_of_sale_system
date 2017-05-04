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




public class PurchaseList extends JDialog implements ActionListener{
	
	JTable tblItem,tempTable;
	JScrollPane jsp;
	JPanel p1,p2;
	JButton btnPrint,btnClose;
	
	
	mySQLQueries msql=new mySQLQueries();
	DefaultTableModel dtm=new DefaultTableModel();
	Statement ste=null;
	Connection con=null;
	
	
	
	public PurchaseList()
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
		fillPurInfo();
		
	}
	public void setColumnWidth(int index,int width)
	{
		DefaultTableColumnModel tcm = (DefaultTableColumnModel) tblItem.getColumnModel();
		TableColumn tc = new TableColumn();
		tc.setPreferredWidth(width);
		
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
		dtm.addColumn("Purchase ID");
		dtm.addColumn("Date");
		dtm.addColumn("Supplier Name");
		dtm.addColumn("Item Name|Brand|Type");
		dtm.addColumn("Price");
		dtm.addColumn("Quantity");
		tblItem.setModel(dtm);
		
		
		setColumnWidth(0, 30);
		setColumnWidth(1, 70);
		setColumnWidth(2, 60);
		setColumnWidth(3, 160);
		setColumnWidth(4, 70);
		setColumnWidth(5, 70);
	}
	public void fillPurInfo()
	{
		String strdataitem[]=new String[6];
		String strquery[]=new String[6];
		
		try
		{
			Statement ste=con.createStatement();
			
			String str="Select * from vi_purchase";
			ResultSet rs=ste.executeQuery(str);
			while(rs.next())
			{
				strdataitem[0]=rs.getString(1);
				strdataitem[1]=rs.getString(2);
				strdataitem[2]=rs.getString(3);
				
				strdataitem[3]=rs.getString(4)+"|"+rs.getString(5)+"|"+rs.getString(6);
				strdataitem[4]=rs.getString(7);
				strdataitem[5]=rs.getString(8);
				
				
				
			
				dtm.addRow(strdataitem);
			}
			tblItem.setModel(dtm);
			
		}catch(SQLException sqle)
		{
			System.out.println(sqle);
		}
	}
	public void closeData()
	{
		if((JOptionPane.showConfirmDialog(this, "Are you sure to want to exit?","Confirm exit",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION))
			dispose();
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new PurchaseList();
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
