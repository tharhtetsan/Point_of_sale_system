package Pointofsale;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import pack.*;
public class sale extends JFrame implements ActionListener{
	
	mySQLQueries msql=new mySQLQueries();
	
	
	JPanel p1,p2,p3;
	DefaultTableModel dtm = new DefaultTableModel();
	
	JLabel lbsaleid=new JLabel(" Sale ID :");
	JLabel lblsaleid=new JLabel("sale_id");
	
	JLabel lbDate=new JLabel("Date : ");
	JLabel lblDate=new JLabel("Date_");
	
	JLabel lbitemname=new JLabel("Name : ");
	JLabel lblitemname=new JLabel("name_");
	
	JLabel  lbItemid=new JLabel("Item Id : ");
	JTextField lblitemid=new JTextField();
	
	JLabel lbbrandName=new JLabel("Brand Name :");
	JLabel cboBrandname=new JLabel();
	
	JLabel lbtypeName=new JLabel("Type Name :");
	JLabel cboTypename=new JLabel();
	
	JLabel lbprice=new JLabel("Price :");
	JTextField txtprice=new JTextField();
	
	JLabel lbquantity=new JLabel("Quantity :");
	JTextField txtquty=new JTextField();
	
	JLabel lbcustomerid=new JLabel("Customer ID :");
	JTextField lblcustomerid=new JTextField("customer_id");

	JLabel lbAmount=new JLabel("Amount : ");
	JTextField txtAmount=new JTextField();
	
	JLabel lbShopName=new JLabel("shop_Name");
	JLabel lbShopPhno=new JLabel("PoneNo.09xxxxx");
	JLabel lbShopGamil=new JLabel("Email-shop@gmail.com");
	JLabel lbShopLoc=new JLabel("City, Stree,No.--");
	
	JLabel lbcustomerName=new JLabel(" Name :");
	JTextField txtcustomerName=new JTextField();
	
	JLabel lbcustomeraddree=new JLabel(" Address :");
	JTextField txtcustomerAddress=new JTextField();
	
	JLabel lbcuPhoneno=new JLabel("Phone no :");
	JTextField txtcustomerPhon=new JTextField();
	
	JLabel lbemail=new JLabel("Email :");
	JTextField lblemail=new JTextField();
	
	
	JLabel lbIsnew=new JLabel("(____)");
	
	JButton btndelete,btnsave,btncancel,btnclose;
	
	JLabel lbtotalamount=new JLabel("Total Amount : ");
	JLabel lbltotalamount=new JLabel("totoal_amount");
	int amount;
	String strDataItem[]=new String[6];

	String customerData[]=new String[5];
	String sale[]=new String[3];
	String saleDetail[]=new String[4];
	
	Vector vid=new Vector();
	Vector vamount=new Vector();
	public JTable tblpurchase;
	boolean check=false;
	String data[]=new String[5];
	public sale()
	{
		p1=new JPanel();
    	p1.setBounds(5,5,780,620);
    	p1.setBorder(BorderFactory.createLineBorder(Color.black));
    	p1.setLayout(null);
    	

    	p2=new JPanel();
    	p2.setBounds(860,50,500,150);
    //	p2.setBorder(BorderFactory.createTitledBorder(" Item Info : "));
    	p2.setLayout(null);
    	
    	
    	
    	
    	
    	setDefaultCloseOperation(EXIT_ON_CLOSE);
    	
    	tblpurchase=new JTable();
    	JScrollPane jsp=new JScrollPane(tblpurchase);
    	jsp.setBounds(10,55,760,520);
    	p1.add(jsp);
    	
   		lbShopName.setBounds(300,0,100,30);
    	p1.add(lbShopName);
    	
    	lbShopPhno.setBounds(20,35,100,20);
    	p1.add(lbShopPhno);
    	
    	lbShopGamil.setBounds(200,35,200,20);
    	p1.add(lbShopGamil);
    	
    	lbShopLoc.setBounds(420,35,100,20);
    	p1.add(lbShopLoc);
    	
    	lbtotalamount=new JLabel("Total Amount: ");
    	lbtotalamount.setBounds(500,575,110,20);
    	p1.add(lbtotalamount);
    	
    	lbltotalamount=new JLabel();
    	lbltotalamount.setBounds(620,575,150,20);
    	lbltotalamount.setBorder(BorderFactory.createLineBorder(Color.black));
    	p1.add(lbltotalamount);
    	
    	
    	
    	

    	
    	
    	
    	
    	lbsaleid.setBounds(800,60,80,20);
    	p2.add(lbsaleid);
    	
    	lblsaleid.setBounds(880,60,120,20);
    	p2.add(lblsaleid);
    	
    	lbDate.setBounds(1040,60,80,20);
    	p2.add(lbDate);
    	
    	lblDate.setBounds(1120,60,120,20);
    	p2.add(lblDate);
    	
    	
    	
    	lbbrandName.setBounds(800,120,80,20);
    	p2.add(lbbrandName);
    	
    	cboBrandname.setBounds(880,120,120,20);
    	p2.add(cboBrandname);
    	
    	lbtypeName.setBounds(1040,120,80,20);
    	p2.add(lbtypeName);
    	
    	cboTypename.setBounds(1120,120,120,20);
    	p2.add(cboTypename);
    	
    	
    	
    	lbitemname.setBounds(800,180,80,20);
    	p2.add(lbitemname);
    	
    	lblitemname.setBounds(880,180,120,20);
    	p2.add(lblitemname);
    	
    	lbItemid.setBounds(1040,180,80,20);
    	p2.add(lbItemid);
    	
    	lblitemid.setBounds(1120,180,120,20);
    	p2.add(lblitemid);
    	
    	
    	
    	lbprice.setBounds(800,240,80,20);
    	p2.add(lbprice);
    	
    	txtprice.setBounds(880,240,120,20);
    	p2.add(txtprice);
    	
    	lbquantity.setBounds(1040,240,80,20);
    	p2.add(lbquantity);
    	
    	txtquty.setBounds(1120,240,120,20);
    	p2.add(txtquty);
    	
    	lbAmount.setBounds(1040,300,80,20);
    	p2.add(lbAmount);
    	
    	txtAmount.setBounds(1120,300,120,20);
    	p2.add(txtAmount);
    	
    	
    	
    	lbcustomerName.setBounds(800,350,80,20);
    	p2.add(lbcustomerName);
    	
    	txtcustomerName.setBounds(880,350,120,20);
    	p2.add(txtcustomerName);
    	
    	lbcuPhoneno.setBounds(1040,350,80,20);
    	p2.add(lbcuPhoneno);
    	
    	txtcustomerPhon.setBounds(1120,350,120,20);
    	p2.add(txtcustomerPhon);
    	
    	
    	
    	lbcustomeraddree.setBounds(800,390,80,20);
    	p2.add(lbcustomeraddree);
    	
    	txtcustomerAddress.setBounds(880,390,180,20);
    	p2.add(txtcustomerAddress);
    	
    	
    	lbcustomerid.setBounds(800,460,80,20);
    	p2.add(lbcustomerid);
    	
    	lblcustomerid.setBounds(880,460,120,20);
    	p2.add(lblcustomerid);
    	
    	lbIsnew.setBounds(1010,460,100,20);
    	p2.add(lbIsnew);
    	
    	lbemail.setBounds(800,490,80,20);
    	p2.add(lbemail);
    	
    	lblemail.setBounds(880,490,180,20);
    	p2.add(lblemail);
    	lblemail.setEditable(false);
    	
    	
    	
    	
    	
    	
    	btndelete=new JButton("Delete");
    	btndelete.setBounds(950,550,90,25);
    	btndelete.setMnemonic('D');
    	p2.add(btndelete);
    	
    	
    	
    	btnsave=new JButton("Save");
    	btnsave.setBounds(500,650,80,25);
    	btnsave.setMnemonic('S');
    	getContentPane().add(btnsave);
    	
    	btnclose=new JButton("Close");
    	btnclose.setBounds(700,650,80,25);
    	btnclose.setMnemonic('C');
    	getContentPane().add(btnclose);
    	
    	
    	getContentPane().add(p1);
    	getContentPane().add(p2);
    	//getContentPane().add(p3);
    	setBounds(0,0,1350,730);
    	setVisible(true);
    	
    
    	
    	createTable();
    	
    	fillDate();
    	AutoID();
    	lblitemid.addActionListener(this);
    	txtquty.addActionListener(this);
    	txtcustomerAddress.addActionListener(this);
    	btndelete.addActionListener(this);
    	btnsave.addActionListener(this);
    	
    	tblpurchase.addMouseListener(new MouseAdapter() {
    		@Override
    		public void mouseClicked(MouseEvent arg0) {
    			// TODO Auto-generated method stub
    		int r=tblpurchase.getSelectedRow();
    		lblitemid.setText(tblpurchase.getValueAt(r, 1).toString());
    		System.out.println(tblpurchase.getValueAt(r, 2).toString());
    		String about[]=tblpurchase.getValueAt(r, 2).toString().split("[|]");
    		lblitemname.setText(about[0]);
    		cboBrandname.setText(about[1]);
    		cboTypename.setText(about[2]);
    		
    		txtprice.setText(tblpurchase.getValueAt(r, 3).toString());
    		txtquty.setText(tblpurchase.getValueAt(r, 4).toString());
    		txtAmount.setText(tblpurchase.getValueAt(r, 5).toString());
    		}
    	});
    	
    	txtprice.setEditable(false);
    	txtAmount.setEditable(false);
    	lblcustomerid.setEditable(false);
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


	 public void fillDate()
	 {
		 myDate mydate=new myDate();
		 lblDate.setText(mydate.getDate());
		
		 
	 }
	 
	 public void AutoID()
	 {
		 lblsaleid.setText(String.valueOf(msql.getAutoid("saleid","sale", "S-")));
	 }
	 
	
		
	
	 
	 public static void main(String[] args) {
		new sale();
	}
	 
	@Override
	public void actionPerformed(ActionEvent event) {
		
		
		if(event.getSource().equals(lblitemid))
		{
			
			fillData();

		}
		else if(event.getSource().equals(txtquty))
		{
			fillAmount();
		}
		else if(event.getSource().equals(txtcustomerAddress))
		{
				if(txtcustomerName.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null,"Enter customer Name !");
					txtcustomerName.requestFocus();
					
				}else if(txtcustomerPhon.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null,"Enter customer phone number !");
					txtcustomerPhon.requestFocus();
				}else if(txtcustomerAddress.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null,"Enter customer Address !");
					txtcustomerAddress.requestFocus();
				}
				else
				{
					CheckCustomerID();
				}
		}else if(event.getSource().equals(btndelete))
		{
			DeleteData();
		}else if(event.getSource().equals(btnsave))
		{
			
			SaveData();
		}
		
	}
	
	private void SaveData() {
		myDate mydate=new myDate();
		String date=mydate.getDate().substring(0,mydate.getDate().length()-5);
		 sale[0]=lblsaleid.getText().toString();
		 sale[1]=lblcustomerid.getText().toString();
		 sale[2]=date;
		 
		 

			if(JOptionPane.showConfirmDialog(null,"Are you sure to Save ?","Comfirm", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION)
			{
				boolean save,save1,save2=false,save3;
				customerData[0]=lblcustomerid.getText();
				customerData[4]=lblemail.getText();
				if(lblemail.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null,"Enter customer Email !");
					lblemail.requestFocus();
				}
				else{
					System.out.println(customerData[0]+"\t"+customerData[1]+"\t"+customerData[2]+"\t"+customerData[3]+"\t"+customerData[4]);
					if(!check)
						save=msql.insertData("customer", customerData);
					
					save1=msql.insertData("sale", sale);
					if(save1)
					{
						saleDetail[0]=lblsaleid.getText().toString();
						for(int i=0;i<vid.size();i++)
						{
						 saleDetail[1]=tblpurchase.getValueAt(i,3).toString();
						 saleDetail[2]=tblpurchase.getValueAt(i,4).toString();
						 saleDetail[3]=tblpurchase.getValueAt(i,1).toString();
						 
						 save2=msql.insertData("saledetail", saleDetail);
						 
						 msql.P_updateitemquantity("sale",saleDetail[3],saleDetail[1],saleDetail[2]);
						 
						 
						}
						if(save2)
							JOptionPane.showMessageDialog(this,"Savae Successfully!");
					}
					
					
				}
				
				
				
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
	
	
	private void CheckCustomerID() {

		
		customerData[0]="";
		customerData[1]=txtcustomerName.getText().toString();
		customerData[2]=txtcustomerPhon.getText().toString();
		customerData[3]=txtcustomerAddress.getText().toString();
		
		
		check=msql.isduplicate("customer",customerData);
		System.out.println(check);
		fillCustomerID();
		
	}
	
	private void fillCustomerID() {
		if(check)
		{
			
			customerData[0]=msql.getCusteomerID("customer",customerData)[0];
			customerData[4]=(msql.getCusteomerID("customer",customerData))[1];
			System.out.println(customerData[4]);
			lblemail.setText(customerData[4]);
			lblcustomerid.setText(customerData[0]);
			lbIsnew.setText("(Old)");
			
			
			
		}
		else
		{
			lblemail.setEditable(true);
			lbIsnew.setText("(New)");
			lblcustomerid.setText(msql.getAutoid("customerid", "customer","CU-"));
			lblemail.requestFocus();
		}
	}
	private void fillAmount() {
		
		if(!Checking.checktxtquantity(txtquty.getText().toString()))
		{
			txtquty.requestFocus();
			
		}else if(Checking.IsNull(txtprice.getText().toString()))
		{
			JOptionPane.showMessageDialog(null, "Plase Enter Item Id !");
			lblitemid.requestFocus();
		}
		else if(Integer.parseInt(txtquty.getText().toString())>Integer.parseInt(data[4]))
		{
			JOptionPane.showMessageDialog(this,"avalilabale Quantity is "+data[4]+" .Please Renter Quantity");
			txtquty.requestFocus();
		}
		else
		{
			amount=Integer.parseInt(txtquty.getText().toString())*Integer.parseInt(txtprice.getText().toString());
			
			txtAmount.setText(String.valueOf(amount)+" Kyats");
			
			if(Checking.IsContain(lblitemid.getText(), vid))
			{
				int r=tblpurchase.getSelectedRow();
				tblpurchase.setValueAt(txtprice.getText(), r, 3);
				tblpurchase.setValueAt(txtquty.getText(),r, 4);
				amount=Integer.parseInt(txtprice.getText().toString())*Integer.parseInt(txtquty.getText().toString());
				vamount.setElementAt(amount,r);
				
				tblpurchase.setValueAt(amount, r, 5);
				
				
			}
			else
				AddItem();
		
			lbltotalamount.setText(Checking.Sumamount(vamount, 1)+" Kyats");
			clearItem();
			
		}
	}
	private void clearItem() {
		
		lblitemid.setText("");
		txtAmount.setText("");
		cboBrandname.setText("");
		cboTypename.setText("");
		txtprice.setText("");
		txtquty.setText("");
		
		
	}
	private void AddItem() {
		strDataItem[0]=String.valueOf(vid.size()+1);
		strDataItem[1]=lblitemid.getText();
		strDataItem[2]=lblitemname.getText()+"|"+cboBrandname.getText()+"|"+cboTypename.getText();
		strDataItem[3]=txtprice.getText();
		strDataItem[4]=txtquty.getText();
		strDataItem[5]=String.valueOf(amount);
		
		vid.addElement(lblitemid.getText());
		vamount.addElement(strDataItem[5]);
		
		dtm.addRow(strDataItem);
		tblpurchase.setModel(dtm);
	}
	private void fillData() {
		
	if(Checking.IsNull(lblitemid.getText().toString()))
	{
		JOptionPane.showMessageDialog(null,"Enter Item ID !");
		lblitemid.requestFocus();
		
	}else
	{
		data=msql.getItemSale(lblitemid.getText().toString());
		
		if(data==null)
		{
			JOptionPane.showMessageDialog(this,"Please check item ID !");
			lblitemid.requestFocus();
			clearItem();
		}
		else
		{
			cboBrandname.setText(data[0]);
			cboTypename.setText(data[1]);
			
			lblitemname.setText(data[2]);
			txtprice.setText(data[3]);
			
			
		}
		
	}
	
	}
	
}
