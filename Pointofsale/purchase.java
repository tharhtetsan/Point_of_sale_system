package Pointofsale;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.net.Inet4Address;

import javax.swing.*;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.StyledEditorKit.ItalicAction;

import pack.*;

public class purchase extends JDialog implements ActionListener
{
	mySQLQueries msql=new mySQLQueries();
	myDate md=new myDate();
	
	JPanel p1,p2,p3;
	JLabel lvl1,lvl2,lvl3,lvl4,lblsupname,lbladd,lblph,lbl5,lbl6,lblpurid,lbldate,lbl7,lbl8,
	lbl9,lbl10,lblitemname,lbl11,lblitemtype,lbl12,lbltotalamount,lblkyat;
	JTextField txtprice,txtquantity;
	
	JComboBox cbosupid,cboitemid;
	JButton btnadd,btndelete,btnupdate,btnsave,btncancel,btnclose;
	
	String str[],stri[];
	
	String strdataitem[]=new String[9];
	String strquery[]=new String[5];
	
	DefaultTableModel dtm = new DefaultTableModel();
	Vector vid = new Vector();
	Vector vamount = new Vector();

	public JTable tblpurchase;
	
    public purchase() {
    	setTitle("Purchase Form");
    	setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    	getContentPane().setLayout(null);
    	
    	p1=new JPanel();
    	p1.setBounds(0,20,800,150);
    	p1.setLayout(null);
    
    	p2=new JPanel();
    	p2.setBounds(5,170,780,150);
    	p2.setBorder(BorderFactory.createTitledBorder(" Item Info : "));
    	p2.setLayout(null);
    	
    	p3=new JPanel();
    	p3.setBounds(5,320,780,170);
    	p3.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
    	p3.setLayout(null);
    	
    	lvl1=new JLabel("Supplier ID :");
    	lvl1.setBounds(70,20,100,20);
    	p1.add(lvl1);
    	
    	lvl2=new JLabel("Supplier Name :");
    	lvl2.setBounds(70,50,100,20);
    	p1.add(lvl2);
    	
    	lvl3=new JLabel("Address :");
    	lvl3.setBounds(70,80,100,20);
    	p1.add(lvl3);
    	
    	lvl4=new JLabel("Phone No :");
    	lvl4.setBounds(70,110,100,20);
    	p1.add(lvl4);
    	
    	cbosupid=new JComboBox();
    	cbosupid.setBounds(180,20,150,20);    	
    	p1.add(cbosupid);
    	
    	lblsupname=new JLabel();
    	lblsupname.setBounds(180,50,150,20);
    	lblsupname.setBorder(BorderFactory.createLineBorder(Color.black));
    	p1.add(lblsupname);
    	
    	lbladd=new JLabel();
    	lbladd.setBounds(180,80,150,20);
    	lbladd.setBorder(BorderFactory.createLineBorder(Color.black));
    	p1.add(lbladd);
    	
    	lblph=new JLabel();
    	lblph.setBounds(180,110,150,20);
    	lblph.setBorder(BorderFactory.createLineBorder(Color.black));
    	p1.add(lblph);
    	
    	lbl5=new JLabel("Voucher No :");
    	lbl5.setBounds(430,20,100,20);
    	p1.add(lbl5); 
    	
    	lbl6=new JLabel("Date :");
    	lbl6.setBounds(430,50,100,20);
    	p1.add(lbl6);
    	
    	lblpurid=new JLabel();
    	lblpurid.setBounds(550,20,150,20);
    	lblpurid.setBorder(BorderFactory.createLineBorder(Color.black));
    	p1.add(lblpurid);
    	
    	lbldate=new JLabel();
    	lbldate.setBounds(550,50,150,20);
    	lbldate.setText(md.getDate());
    	p1.add(lbldate);
    	
    	lbl7=new JLabel("Item ID :");
    	lbl7.setBounds(40,40,80,20);
    	p2.add(lbl7);
    	
    	lbl8=new JLabel("Price :");
    	lbl8.setBounds(40,70,80,20);
    	p2.add(lbl8);
    	
    	lbl9=new JLabel("Quantity :");
    	lbl9.setBounds(40,100,80,20);
    	p2.add(lbl9);
    	
    	cboitemid=new JComboBox();
    	cboitemid.setBounds(140,40,150,20);
    	p2.add(cboitemid);
    	
    	txtprice=new JTextField();
    	txtprice.setBounds(140,70,110,20);
    	txtprice.setBorder(BorderFactory.createLineBorder(Color.black));
    	lblkyat=new JLabel(" Kyats");
    	lblkyat.setBounds(250,70,50,20);
    	p2.add(txtprice);
    	p2.add(lblkyat);
    	
    	txtquantity=new JTextField();
    	txtquantity.setBounds(140,100,150,20);
    	p2.add(txtquantity);
    	
    	lbl10=new JLabel("Item Name :");
    	lbl10.setBounds(350,40,100,20);
    	p2.add(lbl10);
    	
    	lblitemname=new JLabel();
    	lblitemname.setBounds(450,40,100,20);
    	lblitemname.setBorder(BorderFactory.createLineBorder(Color.black));
    	p2.add(lblitemname);
    	
    	lbl11=new JLabel("Item Type :");
    	lbl11.setBounds(570,40,100,20);
    	p2.add(lbl11);
    	
    	lblitemtype=new JLabel();
    	lblitemtype.setBounds(650,40,100,20);
    	lblitemtype.setBorder(BorderFactory.createLineBorder(Color.black));
    	p2.add(lblitemtype);
    	
    	btnadd=new JButton("Add");
    	btnadd.setBounds(400,100,90,20);
    	btnadd.setMnemonic('A');
    	p2.add(btnadd);
    	    	
    	btndelete=new JButton("Delete");
    	btndelete.setBounds(500,100,90,20);
    	btndelete.setMnemonic('D');
    	p2.add(btndelete);
    	
    	btnupdate=new JButton("Update");
    	btnupdate.setBounds(600,100,90,20);
    	btnupdate.setMnemonic('U');
    	p2.add(btnupdate);
    	
    	tblpurchase=new JTable();
    	JScrollPane jsp=new JScrollPane(tblpurchase);
    	jsp.setBounds(10,10,760,130);
    	p3.add(jsp);
    	
    	lbl12=new JLabel("Total Amount: ");
    	lbl12.setBounds(530,145,100,20);
    	p3.add(lbl12);
    	
    	lbltotalamount=new JLabel();
    	lbltotalamount.setBounds(630,145,130,20);
    	lbltotalamount.setBorder(BorderFactory.createLineBorder(Color.black));
    	p3.add(lbltotalamount);
    	    	
    	btnsave=new JButton("Save");
    	btnsave.setBounds(300,500,80,20);
    	btnsave.setMnemonic('S');
    	getContentPane().add(btnsave);
    	
    	btnclose=new JButton("Close");
    	btnclose.setBounds(400,500,80,20);
    	btnclose.setMnemonic('C');
    	getContentPane().add(btnclose);
    	
    	getContentPane().add(p1);
    	getContentPane().add(p2);
    	getContentPane().add(p3);
    	setBounds(100,50,800,570);
    	setVisible(true);
    	
    	AutoID();
    	fillItem();
    	fillSupplier();
    	createTable();
		
    	cboitemid.addActionListener(this);
    	cbosupid.addActionListener(this);
    	btnadd.addActionListener(this);
    	btndelete.addActionListener(this);
    	btnupdate.addActionListener(this);
    	btnclose.addActionListener(this);
    	btnsave.addActionListener(this);
    	
    	
    	tblpurchase.addMouseListener(new MouseAdapter() {
    		@Override
    		public void mouseClicked(MouseEvent arg0) {
    			// TODO Auto-generated method stub
    		int r=tblpurchase.getSelectedRow();
    		cboitemid.setSelectedItem(tblpurchase.getValueAt(r, 1));
    		txtprice.setText(tblpurchase.getValueAt(r, 3).toString());
    		txtquantity.setText(tblpurchase.getValueAt(r, 4).toString());
    		}
    	});
    }
    
    public void createTable()
    {
    	dtm.addColumn("No.");
    	dtm.addColumn("Item ID");
    	dtm.addColumn("Item Detail");
    	dtm.addColumn("Price");
    	dtm.addColumn("Quantity");
    	dtm.addColumn("Amount");
    	
    	tblpurchase.setModel(dtm);
    	
    	setColumnWidth(0, 10);
    	setColumnWidth(1,40);
    	setColumnWidth(2, 250);
    	setColumnWidth(4, 40);
    	setColumnWidth(4, 20);
    	setColumnWidth(5, 50);
    	
    }
    
    public void setColumnWidth(int index,int width)
    {
    	DefaultTableColumnModel tcm=(DefaultTableColumnModel)tblpurchase.getColumnModel();
    	
    	TableColumn tc=tcm.getColumn(index);
    	tc.setPreferredWidth(width);
    }

    
    
    public void AutoID()
    {
    	lblpurid.setText(String.valueOf(msql.getAutoid("purchaseid","purchase", "P-")));
    }
    
    public void fillItem()
    {
    	String str[]=msql.getIDForChoice("itemdetail");
    	cboitemid.removeAllItems();
    	cboitemid.addItem("-Selected-");
    
    	for(int i=0;i<str.length;i++)
    	{
    		cboitemid.addItem(str[i]);
    	}
    }

    public void fillSupplier()
    {
    	String str[]=msql.getIDForChoice("supplier");
    	cbosupid.removeAllItems();
    	cbosupid.addItem("-Selected-");
    
    	for(int i=0;i<str.length;i++)
    	{
    		cbosupid.addItem(str[i]);
    	}
    }

	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		
		if(event.getSource().equals(cbosupid))
		{
			showSupplier();
		}else if(event.getSource().equals(cboitemid))
		{
			showItem();
		}else if(event.getSource().equals(btnadd))
		{
			AddData();
		}
		else if(event.getSource().equals(btndelete))
		{
			DeleteData();
		}else if(event.getSource().equals(btnupdate))
		{
			UpdateData();
		}else if(event.getSource().equals(btnsave))
		{
			SaveData();
		}
	}
    
	private void SaveData() {

		if(cbosupid.getSelectedIndex()==0)
		{
			JOptionPane.showMessageDialog(null,"You must select a Supplier ID");
			cbosupid.requestFocus();
		}
		else if(vid.size()==0)
		{
			JOptionPane.showMessageDialog(null, "There is no item for Purchase !");
			cbosupid.requestFocus();
		}
		else 
		{
			if(JOptionPane.showConfirmDialog(null,"Are you sure to Save ?","Comfirm", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION)
			{
				boolean save;
				String []savedata1=new String[3];
				String savedata2[]=new String[4];
				savedata1[0]=lblpurid.getText();
				savedata1[1]=cbosupid.getSelectedItem().toString();
				savedata1[2]=lbldate.getText();
				save=msql.insertData("purchase", savedata1);
				
				if(save)
				{
					for(int i=0;i<vid.size();i++)
					{
						savedata2[0]=lblpurid.getText();
						savedata2[1]=(String)tblpurchase.getValueAt(i, 1);
						savedata2[2]=(String)tblpurchase.getValueAt(i, 3);
						savedata2[3]=(String)tblpurchase.getValueAt(i, 4);
						
						save=msql.insertData("purchasedetail", savedata2);
						
						
						msql.P_updateitemquantity("purchase",savedata2[1],savedata2[2],savedata2[3]);
						
					}
				}
				if(save)
				{
					JOptionPane.showMessageDialog(null," All records are successfully SAVED !");
					AutoID();
				}
				else{
					JOptionPane.showMessageDialog(null,"Records cannot be saved because of some ERROR !");
					clearItem();
				}
			}
		}
	}

	private void UpdateData() {
		if(tblpurchase.getSelectedRow()<0)
		{
			JOptionPane.showMessageDialog(this, "Please select row to update");
			
		}else if(!Checking.checktxtprice(txtprice.getText()))
		{
			txtprice.requestFocus();
			txtprice.selectAll();
		}else if(!Checking.checktxtquantity(txtquantity.getText()))
		{
			txtquantity.requestFocus();
			txtquantity.selectAll();
		}
		else
		{
			//deleteRow();
			//itemaddmethod();
			int r=tblpurchase.getSelectedRow();
			tblpurchase.setValueAt(txtprice.getText(), r, 3);
			tblpurchase.setValueAt(txtquantity.getText(),r, 4);
			long amount=Long.parseLong(txtprice.getText())*Integer.parseInt(txtquantity.getText());
			vamount.setElementAt(amount,r);
			
			tblpurchase.setValueAt(amount, r, 5);
			
			lbltotalamount.setText(Checking.Sumamount(vamount, 1)+" Kyats");
			clearItem();
		}
		
	}

	private void DeleteData() {
		
		if(tblpurchase.getSelectedRow()<0)
		{
			JOptionPane.showMessageDialog(this,"Please select row to delete");
			
		}else
		{
			deleteRow();
			lbltotalamount.setText(Checking.Sumamount(vamount, 1)+"Kyats");
			clearItem();
		}
	}
	
	public void deleteRow()
	{
		int i=tblpurchase.getSelectedRow();
		vamount.remove(i);
		vid.remove(i);
		
		dtm.removeRow(i);
		for(int j=0;j<tblpurchase.getRowCount();j++)
		{
			dtm.setValueAt(j+1,	 j, 0);
		}
		tblpurchase.setModel(dtm);
		lbltotalamount.setText(Checking.Sumamount(vamount, 1)+" Kyats");
		
	}

	private void AddData() {
		
		if(cboitemid.getSelectedIndex()==0)
		{
			JOptionPane.showMessageDialog(null,"Your must choose Item ID!");
		}else if(!Checking.checktxtprice(txtprice.getText()))
		{
			txtprice.requestFocus();
			txtprice.selectAll();
		}
		else if(!Checking.checktxtquantity(txtquantity.getText()))
		{
			txtquantity.requestFocus();
			txtquantity.selectAll();
		}
		else if(Checking.IsContain(cboitemid.getSelectedItem().toString(), vid))
		{
			JOptionPane.showMessageDialog(null,"The item you seleted is already existed!");
			cboitemid.requestFocus();
			clearItem();
		}
		else{
			itemaddmethod();
			lbltotalamount.setText(Checking.Sumamount(vamount, 1)+"Kyats");
			clearItem();
		}
	}

	private void itemaddmethod() {
		strdataitem[0]=String.valueOf(vid.size()+1);
		vid.addElement(strdataitem[1]);
		strdataitem[2]="|"+strdataitem[6]+"|"+strdataitem[7]+"|"+strdataitem[8];
		strdataitem[3]=txtprice.getText();
		strdataitem[4]=txtquantity.getText();
		
		Long amount=Integer.parseInt(strdataitem[4])*Long.parseLong(strdataitem[3]);
		strdataitem[5]=String.valueOf(amount);
		vamount.addElement(strdataitem[5]);
		dtm.addRow(strdataitem);
		tblpurchase.setModel(dtm);
		cboitemid.requestFocus();
		
	}

	private void showItem() {
		
		if(cboitemid.getSelectedIndex()>0)
		{
			strquery=msql.getItemData(cboitemid.getSelectedItem().toString());
			strdataitem[1]=strquery[0];
			strdataitem[2]=strquery[2];
			
			strdataitem[8]=strquery[4].equals("")?"-":strquery[4];
			strquery=msql.getMerchandiseData(strquery[1]);
			strdataitem[6]=strquery[0];
			strdataitem[7]=strquery[1];
			lblitemname.setText(strdataitem[2]);
			
			lblitemtype.setText(strdataitem[7]);
			
			txtprice.requestFocus();
			
		}else{
			clearItem();
		}
	}

	private void clearItem() {
		
		lblitemname.setText("");
		lblitemtype.setText("");
		txtprice.setText("");
		txtquantity.setText("");
		cboitemid.setSelectedIndex(0);
		
	}

	private void showSupplier() {
		
		if(cbosupid.getSelectedIndex()>0)
		{
			String str[]=new String[4];
			str=msql.getSupplierData("supplier", cbosupid.getSelectedItem().toString());
			lblsupname.setText(str[0]);
			lbladd.setText(str[1]);
			lblph.setText(str[2]);
			
		}else{
			lblsupname.setText("");
			lbladd.setText("");
			lblph.setText("");
		}
		
	}

	public static void main(String[] args) {
		new purchase();
	}
}