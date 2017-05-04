package Pointofsale;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import pack.*;



public class PurchaseSearch extends JFrame implements ActionListener {
	
	JRadioButton rdoMonthly,rdoyYearly,rdoBoth;
	JComboBox cbomonth,cboyear;
	JButton btnSearch,btnShowAll,btnPrint,btnClose;
	JPanel p1,p2;
	JTable tblList;
	ButtonGroup btg;
	
	JTable tblPurchase,tempTable;
	JScrollPane jsp;
	MyTable m=new MyTable();
	DefaultTableModel dtm=new DefaultTableModel();
	
	mySQLQueries msql =new mySQLQueries();
	
	Statement ste=null;
	Connection con=null;
	
	public PurchaseSearch()
	{
		setTitle("Purchase Searching");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		
		p1=new JPanel();
		p1.setBounds(0, 0, 950, 500);
		p1.setBorder(BorderFactory.createLineBorder(Color.black));
		
		p1.setLayout(null);
		
		p2=new JPanel();
		p2.setBounds(600,430, 320, 50);
		p2.setBorder(BorderFactory.createLineBorder(Color.black));
		p2.setBackground(Color.gray);
		p2.setLayout(null);
		

		btg=new ButtonGroup();
		rdoMonthly=new JRadioButton("Monthly");
		rdoMonthly.setBounds(10, 10, 150, 50);
		rdoMonthly.addActionListener(this);
		btg.add(rdoMonthly);
		p1.add(rdoMonthly);

		
		rdoyYearly=new JRadioButton("Yearly");
		rdoyYearly.setBounds(200, 10, 150, 50);
		rdoyYearly.addActionListener(this);
		btg.add(rdoyYearly);		
		p1.add(rdoyYearly);
		
		rdoBoth=new JRadioButton("Month & Year");
		rdoBoth.setBounds(390, 10, 150, 50);
		rdoBoth.addActionListener(this);
		btg.add(rdoBoth);
		p1.add(rdoBoth);
		
		cbomonth=new JComboBox();
		cbomonth.setBounds(10, 60, 170, 40);
		p1.add(cbomonth);
		
		cboyear=new JComboBox();
		cboyear.setBounds(210, 60, 170, 40);
		p1.add(cboyear);
		
		tblPurchase=new JTable();
		//tblItem=MyTable();
		
		jsp=new JScrollPane(tblPurchase);
		
		jsp.setBounds(10, 110, 800, 300);
		p1.add(jsp);
		
		btnSearch=new JButton("Search");
		btnSearch.setBounds(400, 60, 150, 40);
		btnSearch.addActionListener(this);
		p1.add(btnSearch);
		
		btnShowAll=new JButton("ShowAll");
		btnShowAll.setBounds(570, 60, 150, 40);
		btnShowAll.addActionListener(this);
		p1.add(btnShowAll);
		
		btnPrint=new JButton("Print");
		btnPrint.setBounds(10, 10, 130, 30);
		btnPrint.addActionListener(this);

		p2.add(btnPrint);
		
		btnClose=new JButton("Close");
		btnClose.setBounds(150, 10, 130, 30);
		btnClose.addActionListener(this);
		p2.add(btnClose);
		
		tblList=new JTable();
		//tblList=enable();

		getContentPane().add(p2);
		getContentPane().add(p1);
		setBounds(0, 0, 1000, 700);
		setVisible(true);
		try{
			con=DBConnection.GetMySQLConnection();
		}catch(ClassNotFoundException cnfe)
		{
			System.out.println(cnfe);
		}
		catch(SQLException sqle)
		{
			System.out.println(sqle);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		createtable();
		cbomonth.setVisible(false);
		cboyear.setVisible(false);
		fillMonth();
		fillYear();
		
	}
	public void createtable()
	{
		dtm.addColumn("Purchase ID");
		dtm.addColumn("Date");
		dtm.addColumn("Supplier Name");
		dtm.addColumn("Item Name | Brand | Type ");
		dtm.addColumn("Price");
		dtm.addColumn("Quality");

		tblPurchase.setModel(dtm);

		setColumnWidth(0, 40);
		setColumnWidth(1, 40);
		setColumnWidth(2, 60);
		setColumnWidth(3, 300);
		setColumnWidth(4, 30);
		setColumnWidth(5, 10);
	}
	public void setColumnWidth(int index,int width)
	{
		DefaultTableColumnModel tcm = (DefaultTableColumnModel) tblPurchase
				.getColumnModel();
		TableColumn tc = tcm.getColumn(index);
		tc.setPreferredWidth(width);
	}
	private void fillMonth()
	{
		String month [] = {"Janurary","Feburary","March","April","May","June","July","August","September",
				"October","November","December",};
		cbomonth.addItem("---Selected-----");
		for(int i=0;i<month.length;i++)
		{
			cbomonth.addItem(month[i]);
		}
		cbomonth.setSelectedIndex(0);
	}
	
	private void fillYear()
	{
		cboyear.addItem("---Selected-----");
		myDate md = new myDate();
		System.out.println(md.getYear());
		for(int i= md.getYear();i>= 2000;i--)
		{
			cboyear.addItem(String.valueOf(i));
		}
		cboyear.setSelectedIndex(0);
		
	}
	
	public void fillPurData(String sql)
	{
		String strdataitem[]=new String[6];
		
		try{
			Statement ste=con.createStatement();
			while(dtm.getRowCount()>0)
				dtm.removeRow(0);
			
			ResultSet rs=ste.executeQuery(sql);
			while(rs.next())
			{
				strdataitem[0]=rs.getString(1);
				strdataitem[1]=rs.getString(2);
				strdataitem[2]=rs.getString(3);
				
				strdataitem[3]=rs.getString(4)+" | "+rs.getString(5)+" | "+rs.getString(6);
				strdataitem[4]=rs.getString(7);
				strdataitem[5]=rs.getString(8);
				dtm.addRow(strdataitem);

			}
			tblPurchase.setModel(dtm);
		}catch(SQLException sqle)
		{
			System.out.println(sqle);
		}
	}
	
	public void printData()
	{
		try{
			tblPurchase.print();
		}catch(Exception e)
		{
			JOptionPane.showMessageDialog(this, e);
		}
	}
	
	public void searchData()
	{
		if(rdoMonthly.isSelected())
		{
			if(cbomonth.getSelectedIndex()==0)
			{
				JOptionPane.showMessageDialog(this, "Please choose Month");
				cbomonth.requestFocus();
			}
			else 
			{
				String str="select * from vi_purchase where Month(vi_purchase.purchasedate)="+cbomonth.getSelectedIndex();
				fillPurData(str);
			}
		}
		else if(rdoyYearly.isSelected())
		{
			if(cboyear.getSelectedIndex()==0)
			{

				JOptionPane.showMessageDialog(this, "Please choose Year");
				cboyear.requestFocus();
			}
			else 
			{
				String str="select * from vi_purchase where  Year(vi_purchase.purchasedate)="+cboyear.getSelectedItem().toString();
				fillPurData(str);
			}
		}
		else if(rdoBoth.isSelected())
		{
			if(cbomonth.getSelectedIndex()==0)
			{
				JOptionPane.showMessageDialog(this, "Please choose Month");
				cbomonth.requestFocus();
			}
			else if(cboyear.getSelectedIndex()==0)
			{
				JOptionPane.showMessageDialog(this, "Please choose Year");
				cboyear.requestFocus();
			}
			else
			{
				String str="select * from vi_purchase  where   Month(vi_purchase.purchasedate)="+cbomonth.getSelectedIndex()+" and Year(vi_purchase.purchasedate)="+cboyear.getSelectedItem().toString();
				fillPurData(str);
			}
			
		}
		else
		{
			JOptionPane.showMessageDialog(this, "please choose slect Radio Buton");
		}
	}

	private JTable MyTable() {
		// TODO Auto-generated method stub
		try
    	{    	
    	tempTable=new JTable(new Object[100][4],new Object[]{"ItemName","Price","Quantity","Amount"});
    	m.packColumn(tempTable,0,130);	//mt.packColumn(JTable,ColumnNo,Char(ColumnSize));
    	m.packColumn(tempTable,1,100);
    	m.packColumn(tempTable,2,50);
    	m.packColumn(tempTable,3,100);
    	tempTable.setEnabled(true);
    	
    	for (int i=0;i<4;i++)
    		for (int j=0;j<100;j++)
    			tempTable.setValueAt(null,j,i);
    	
    	}catch(Exception e)
    	{	e.printStackTrace();return null;    	}
    	
    	return tempTable;
	}

	public void showAllData()
	{
		fillPurData("select * from vi_purchase");
		cbomonth.setSelectedIndex(0);
		cboyear.setSelectedIndex(0);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new PurchaseSearch();
	}

	
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		if(ae.getSource().equals(rdoMonthly))
		{
			cbomonth.setVisible(true);
			cboyear.setVisible(false);
			cbomonth.setSelectedIndex(0);
			cboyear.setSelectedIndex(0);
		}
		else if(ae.getSource().equals(rdoyYearly))
		{
			cbomonth.setVisible(false);
			cboyear.setVisible(true);
			cbomonth.setSelectedIndex(0);
			cboyear.setSelectedIndex(0);
		}
		else if(ae.getSource().equals(rdoBoth))
		{
			cbomonth.setVisible(true);
			cboyear.setVisible(true);
			cbomonth.setSelectedIndex(0);
			cboyear.setSelectedIndex(0);
		}
		else if(ae.getSource().equals(btnSearch))
		{
			searchData();
		}
		else if(ae.getSource().equals(btnShowAll))
		{
			showAllData();
		}
		else if(ae.getSource().equals(btnPrint))
		{
			printData();
		}
		else if(ae.getSource().equals(btnClose))
		{
			if((JOptionPane.showConfirmDialog(this, "Are you sure to want to exit?","Confirm exit",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION))
				dispose();
		}
	}

}
