package Pointofsale;

import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import pack.Checking;
import pack.mySQLQueries;

public class ItemEntry extends JFrame implements ActionListener{

	JLabel lb1=new JLabel("Item ID :");
	JLabel lblitemid=new JLabel("  id");
	JTextField txtname=new JTextField(15);
	JTextArea txtRemark=new JTextArea();
	
	JComboBox<String> cbobrandName=new JComboBox<>();
	JComboBox<String> cboTypeName=new JComboBox<>();
	
	
	JLabel lbMerid=new JLabel("t_id");
	
	JButton btnsave=new JButton("Save");
	JButton btncancel=new JButton("Cancel");
	JButton btnClose=new JButton("Close");
	
	mySQLQueries msql=new mySQLQueries();
	
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource().equals(btnsave))
		{
			SaveData();
		}else if(event.getSource().equals(btncancel))
		{
		clear();
			
		}else if(event.getSource().equals(btnClose))
		{
			Close();
		}else if(event.getSource().equals(cboTypeName))
		{
			showMerchandiseID();
		}else if(event.getSource().equals(cbobrandName))
		{
			if(cbobrandName.getSelectedIndex()>0)
			fillType();
		}
		
	}

	public void clear()
	{
		AutoID();
		cbobrandName.setSelectedIndex(0);
		cboTypeName.setSelectedIndex(0);
		lbMerid.setText("");
		txtname.setText("");
		txtRemark.setText("");
	}
	
	
	private void SaveData() {
		
		if(cbobrandName.getSelectedIndex()==0)
		{
			JOptionPane.showMessageDialog(null,"Please choose brand name");
			cbobrandName.requestFocus();
		}else if(cboTypeName.getSelectedIndex()==0)
		{
			JOptionPane.showMessageDialog(null,"Please choose type name");
			cboTypeName.requestFocus();
		}else if(Checking.IsNull(txtname.getText()))
		{
			JOptionPane.showMessageDialog(null," Plese enter item name");
			txtname.requestFocus();	
		}else{
			
			String str[]=new String[2];
			str[0]=txtname.getText();
			str[1]=lbMerid.getText();
			boolean br=msql.isduplicate("itemdetail", str);
			
			if(br)
			{
				JOptionPane.showMessageDialog(null,"Duplicate Record!");
			}else{
				String st[]=new String[4];
				st[0]=lblitemid.getText();
				st[1]=lbMerid.getText();
				st[2]=txtname.getText();
				st[3]=txtRemark.getText();
				
				msql.insertData("itemdetail", st);
				
				JOptionPane.showMessageDialog(null, "Successfullu save record");
				clear();
			}
		}
	}

	
	private void Close()
	{
		if((JOptionPane.showConfirmDialog(this,"Are Your sure you want to exit","Conform exiting.",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE))==JOptionPane.YES_OPTION)
		{
			dispose();
		}
	}
	private void showMerchandiseID() {
		
		if(cbobrandName.getSelectedIndex()>0&&cboTypeName.getSelectedIndex()>0)
		{
			lbMerid.setText(msql.getMerid(cbobrandName.getSelectedItem().toString(),cboTypeName.getSelectedItem().toString()));
		}
		
	}

	public ItemEntry()
	{
		super("Item Entery");
		 setLayout(null);
		 setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		 
		 
		JPanel panel=new JPanel();
		JLabel lb1=new JLabel("Item ID :");
		JLabel lb2=new JLabel("Brand Name :");
		JLabel lb3=new JLabel("Type Name :");
		JLabel lb4=new JLabel("Merchandise ID :");
		JLabel lb5=new JLabel("Item Name :");
		JLabel lb6=new JLabel("Remark :");
		
		lb1.setBounds(30,30,100,30);
		panel.add(lb1);
		
		lb2.setBounds(30,70,100,30);
		panel.add(lb2);
		
		lb3.setBounds(30,110,100,30);
		panel.add(lb3);
		
		lb4.setBounds(30,150,100,30);
		panel.add(lb4);
		
		lb5.setBounds(30,190,100,30);
		panel.add(lb5);
		
		lb6.setBounds(30,230,100,30);
		panel.add(lb6);
		
		
		
		lblitemid.setBounds(140,30,100,30);
		panel.add(lblitemid);
		
		cbobrandName.setBounds(140,70,200,30);
		panel.add(cbobrandName);
		
		cboTypeName.setBounds(140,110,200,30);
		panel.add(cboTypeName);
		
		cboTypeName.addItem("-selected-");
		
		lbMerid.setBounds(140,150,100,30);
		panel.add(lbMerid);
		
		txtname.setBounds(140,190,200,30);
		panel.add(txtname);
		
		txtRemark.setBounds(140,230,200,50);
		panel.add(txtRemark);
		
		
		panel.setBounds(0,0,350,280);
		panel.setLayout(null);
		
		
		
		
		
		
		Panel panelbtn=new Panel();
		btnsave.setBounds(10,10,80,50);
		panelbtn.add(btnsave);
		
		btncancel.setBounds(60,10,80,50);
		panelbtn.add(btncancel);
		
		btnClose.setBounds(90,10,80,50);
		panelbtn.add(btnClose);
		
		panelbtn.setBounds(0,290,280,80);
		
	
		 
		getContentPane().add(panel);
		getContentPane().add(panelbtn);
		setBounds(0,0,400,400);
		setVisible(true);
		
		
		btnsave.addActionListener(this);
		btncancel.addActionListener(this);
		btnClose.addActionListener(this);
		cbobrandName.addActionListener(this);
		cboTypeName.addActionListener(this);
		AutoID();
		fillBrand();
		
	}
	
	
	public void AutoID()
	{
		lblitemid.setText(String.valueOf(msql.getAutoid("itemid","itemdetail", "IT-")));
	}
	

	public void fillBrand()
	{
		String str[]=msql.getNameForChoice("brand");
		cbobrandName.addItem("-Selected-");
		for(int i=0;i<str.length;i++)
			cbobrandName.addItem(str[i].toString());
	}
	
	public void fillType()
	{
		cboTypeName.removeAllItems();
		String str[]=msql.getNameType(cbobrandName.getSelectedItem().toString());
		cboTypeName.addItem("-Selected-");
		for(int i=0;i<str.length;i++)
			cboTypeName.addItem(str[i].toString());
	}
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		new ItemEntry();
	}
	
}
