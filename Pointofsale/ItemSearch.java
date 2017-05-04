package Pointofsale;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import pack.*;


public class ItemSearch extends JDialog implements ActionListener{

	JLabel lbl1;
	JRadioButton rdoBrand, rdoType, rdoBoth;
	JComboBox cbobrandname, cbotypename;
	JButton btnSearch, btnShow, btnPrint, btnClose;
	ButtonGroup btg=new ButtonGroup();
	JScrollBar jsb;
	JPanel p1, p2;

	JTable tblitem, tempTable;
	JScrollPane jsp;
	MyTable m = new MyTable();
	DefaultTableModel dtm = new DefaultTableModel();
	mySQLQueries msql= new mySQLQueries();
	Connection con=null;
	ResultSet rs=null;
	Statement ste=null;
		
		
	public ItemSearch() {
		// TODO Auto-generated constructor stub

		setTitle("Item Search");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(null);

		p1 = new JPanel();
		p1.setBounds(0, 0, 800, 500);
		p1.setBorder(BorderFactory.createLineBorder(Color.black));
		p1.setLayout(null);

		p2 = new JPanel();
		p2.setBounds(450, 530, 300, 80);
		p2.setBorder(BorderFactory.createLineBorder(Color.gray));
		p2.setLayout(null);
		p2.setBackground(Color.gray);
		
		rdoBrand=new JRadioButton("Brand");
		rdoBrand.setBounds(50, 10, 80, 20);
		rdoBrand.addActionListener(this);
		btg.add(rdoBrand);
		p1.add(rdoBrand);
		
		rdoType=new JRadioButton("Type");
		rdoType.setBounds(250, 10, 80, 20);
		rdoType.addActionListener(this);
		btg.add(rdoType);
		p1.add(rdoType);
		
		rdoBoth=new JRadioButton("Band & Type");
		rdoBoth.setBounds(450, 10, 100, 20);
		rdoBoth.addActionListener(this);
		btg.add(rdoBoth);
		p1.add(rdoBoth);
		
		cbobrandname=new JComboBox();
		cbobrandname.setBounds(50, 50, 130, 25);
		p1.add(cbobrandname);
		

		cbotypename=new JComboBox();
		cbotypename.setBounds(250, 50, 130, 25);
		p1.add(cbotypename);
		
		btnSearch=new JButton("Search");
		btnSearch.setBounds(450, 50, 120, 25);
		btnSearch.addActionListener(this);
		p1.add(	btnSearch);
		btnSearch.setMnemonic('S');
		

		btnShow=new JButton("Show All");
		btnShow.setBounds(600, 50, 120, 25);
		btnShow.addActionListener(this);
		p1.add(	btnShow);
		
		btnShow.setMnemonic('A');
	/*	
		tblitem=new JTable();
		tblitem.setBounds(10, 80, 760,500 );
		p1.add(tblitem);
		
	*/	btnPrint=new JButton("Print");
		btnPrint.setBounds(10, 20, 120, 30);
		p2.add(	btnPrint);
		btnPrint.addActionListener(this);
		btnPrint.setMnemonic('P');
		

		btnClose=new JButton("Close");
		btnClose.setBounds(150, 20, 120, 30);
		btnClose.addActionListener(this);
		p2.add(	btnClose);
		btnClose.setMnemonic('C');
		
		tblitem = new JTable(); // class Mytable
		tblitem = MyTable();

		jsp = new JScrollPane(tblitem);

		jsp.setBounds(10, 100, 760,500);
		p1.add(jsp);
		
		getContentPane().add(p1);
		getContentPane().add(p2);

		setBounds(0, 0, 800, 700);
		setVisible(true);
		try {
			con=DBConnection.GetMySQLConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		crateTable();
		cbobrandname.setVisible(false);
		cbotypename.setVisible(false);
		fillBrand();
		fillType();
		

	}
	public JTable MyTable() {
		try {
			tempTable = new JTable(new Object[100][5], new Object[] {
					"ItemID", "Item Name | Brand | Type ","Price", "Quantity", "Remark" });
			m.packColumn(tempTable, 0, 100); // mt.packColumn(JTable,ColumnNo,Char(ColumnSize));
			m.packColumn(tempTable, 1, 300);
			m.packColumn(tempTable, 2, 100);
			m.packColumn(tempTable, 3, 100);
			m.packColumn(tempTable, 4, 100);
			tempTable.setEnabled(true);

			for (int i = 0; i < 5; i++)
				for (int j = 0; j < 100; j++)
					tempTable.setValueAt(null, j, i);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return tempTable;
	}
	public void setColumWidth(int index, int width) {
		DefaultTableColumnModel tcm = (DefaultTableColumnModel) tblitem
				.getColumnModel();
		TableColumn tc = tcm.getColumn(index);
		tc.setPreferredWidth(width);
	}
	
	private void crateTable() {
		dtm.addColumn("Item ID");
		dtm.addColumn("Item Name | Brand | Type ");
		dtm.addColumn("Price");
		dtm.addColumn("Quality");
		dtm.addColumn("Remark");
		

		tblitem.setModel(dtm);

		setColumWidth(0, 100);
		setColumWidth(1, 300);
		setColumWidth(2, 100);
		setColumWidth(3, 100);
		setColumWidth(3, 100);

	}
	public void fillItem(String sql)
	{
		String strdataitem[]=new String[7];
		String remark="";
		while(dtm.getRowCount()>0)
		{
			dtm.removeRow(0);
		
		}
		try
		{
			Statement ste=con.createStatement();
			ResultSet rs=ste.executeQuery(sql);
			
			if(rs.next())
			{
				rs.beforeFirst();
				while(rs.next())
				{
					strdataitem[0]=rs.getString(1);
					strdataitem[1]=rs.getString(2);
					strdataitem[1]+="|"+rs.getString(3);
					strdataitem[1]+="|"+rs.getString(4);
					strdataitem[2]=rs.getString(5);
					strdataitem[3]=rs.getString(6);
					strdataitem[4]=rs.getString(7);
					dtm.addRow(strdataitem);
				}
				tblitem.setModel(dtm);
				
				
			}
			else
			{
				JOptionPane.showMessageDialog(this, "Databes is not record");
				
			}
			
			
		}catch(SQLException sqle)
		{
			System.out.println(sqle);
		}
	}
	public void printData()
	{
		try
		{
			tblitem.print();
		}catch(Exception e)
		{
			JOptionPane.showMessageDialog(this, e);
		}
	}
	private void fillBrand()
	{
		String[]str=msql.getNameForChoice("brand");
		cbobrandname.addItem("-Selected-");
		for(int i=0;i<str.length;i++)
		{
			cbobrandname.addItem(str[i].toString());
		
		}
	}
	
	private void fillType()
	{
		String[]str=msql.getNameForChoice("type");
		cbotypename.addItem("-Selected-");
		for(int i=0;i<str.length;i++)
		{
			cbotypename.addItem(str[i].toString());
		
		}
	}
	public void searchData()
	{
		String brandname,typename;
		if(rdoBrand.isSelected()){
			if(cbobrandname.getSelectedIndex()==0)
			{
				JOptionPane.showMessageDialog(this, "please choose brand name");
				cbobrandname.requestFocus();
						
			}
			else
			{
				brandname=cbobrandname.getSelectedItem().toString();
				fillItem("Select * From pointofsale.vi_itemsearch where brandname='"+brandname+"'");
			}
		}
		else if(rdoType.isSelected())
		{
			
			if(cbotypename.getSelectedIndex()==0)
			{
				JOptionPane.showMessageDialog(this, "please choose Type name");
				cbotypename.requestFocus();
						
			}
			else
			{
				typename=cbotypename.getSelectedItem().toString();
				fillItem("Select * From pointofsale.vi_itemsearch where typename='"+typename+"'");
			}
		}
		else if(rdoBoth.isSelected())
		{
			if(cbobrandname.getSelectedIndex()==0)
			{
				JOptionPane.showMessageDialog(this, "please choose brand name");
				cbobrandname.requestFocus();
						
			}
			else if(cbotypename.getSelectedIndex()==0)
			{
				JOptionPane.showMessageDialog(this, "please choose type name");
				cbotypename.requestFocus();
						
			}
			else
			{
				typename=cbotypename.getSelectedItem().toString();
				brandname=cbobrandname.getSelectedItem().toString();
				fillItem("Select * From  pointofsalevi_itemsearch where brandname='"+brandname+"' And typename='"+typename+"'");
			}
			
		}
	}

	public static void main(String[] args) {
		new ItemSearch();
	}
	public void actionPerformed(ActionEvent ae) {
		
		if(ae.getSource().equals(rdoBrand))
		{
			cbobrandname.setVisible(true);
			cbotypename.setVisible(false);
			cbobrandname.setSelectedIndex(0);
			cbotypename.setSelectedIndex(0);
		}
		if(ae.getSource().equals(rdoType))
			
		{
			cbobrandname.setVisible(false);
			cbotypename.setVisible(true);
			cbobrandname.setSelectedIndex(0);
			cbotypename.setSelectedIndex(0);
		}
		if(ae.getSource().equals(rdoBoth))
		{
			cbobrandname.setVisible(true);
			cbotypename.setVisible(true);
			cbobrandname.setSelectedIndex(0);
			cbotypename.setSelectedIndex(0);
		}
		if(ae.getSource().equals(btnPrint))
		{
			printData();
		}
		if(ae.getSource().equals(btnShow))
		{
			fillItem("select * from itemSearch");
			
		}
		
		if(ae.getSource().equals(btnClose))
		{
			this.dispose();
			
		}
		if(ae.getSource().equals(btnSearch))
		{
			searchData();
			
		}
	}
}