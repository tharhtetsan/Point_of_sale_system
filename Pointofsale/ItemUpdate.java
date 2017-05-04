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

public class ItemUpdate extends JFrame implements ActionListener{

	JLabel lb1=new JLabel("Item ID :");
	JComboBox cboitemid=new JComboBox();
	JTextField txtname=new JTextField();
	JTextArea txtRemark=new JTextArea();
	
	JLabel txtbrandName=new JLabel();
	JLabel txtTypeName=new JLabel();
	
	
	JTextField txtSalePrice=new JTextField("t_id");
	
	JButton btnUpdate=new JButton("Update");
	JButton btnCancel=new JButton("Cancel");
	JButton btnClose=new JButton("Close");
	
	mySQLQueries msql=new mySQLQueries();
	
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource().equals(btnUpdate))
		{
			UpdateData();
		}else if(event.getSource().equals(btnCancel))
		{
		clear();
			
		}else if(event.getSource().equals(btnClose))
		{
			Close();
		}else if(event.getSource().equals(cboitemid))
		{
			showItemData();
		}
	}
	
	
	private void Close()
	{
		if((JOptionPane.showConfirmDialog(this,"Are Your sure you want to exit","Conform exiting.",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE))==JOptionPane.YES_OPTION)
		{
			dispose();
		}
	}

	public void clear()
	{
			cboitemid.setSelectedIndex(0);
			txtbrandName.setText("");
			txtTypeName.setText("");
			txtname.setText("");
			txtSalePrice.setText("");
			txtRemark.setText("");
	}
	
	private void showItemData() {
		
		if(cboitemid.getSelectedIndex()==0)
		{
			txtbrandName.setText("");
			txtTypeName.setText("");
			txtname.setText("");
			txtSalePrice.setText("");
			txtRemark.setText("");
		}else
		{
		
			String str[]=msql.getItemData(cboitemid.getSelectedItem().toString());
			txtname.setText(str[2]);
			txtSalePrice.setText(str[3]);
			
			String result[]=msql.getMerchandiseData(str[1]);
			txtbrandName.setText(result[0]);
			txtTypeName.setText(result[1]);
			txtRemark.setText(str[4]);
			
			
		}
		
	}

	private void UpdateData() {
		
		if(cboitemid.getSelectedIndex()==0)
		{
			JOptionPane.showMessageDialog(null,"Please choose item ID!");
			cboitemid.requestFocus();
			
		}else if(Checking.IsNull(txtbrandName.getText()))
		{
			JOptionPane.showMessageDialog(null,"Please enter brand name");
			txtbrandName.requestFocus();
		}else if(Checking.IsNull(txtTypeName.getText()))
		{
			JOptionPane.showMessageDialog(null,"Please enter type name");
			txtTypeName.requestFocus();
		}else if(Checking.IsNull(txtname.getText()))
		{
			JOptionPane.showMessageDialog(null," Plese enter item name");
			txtname.requestFocus();	
		}else if(Checking.IsNull(txtname.getText()))
		{
			JOptionPane.showMessageDialog(null," Plese enter item name");
			txtname.requestFocus();	
		}else{
			
		
				String st[]=new String[6];
				st[0]=cboitemid.getSelectedItem().toString();
				st[1]=txtbrandName.getText();
				st[2]=txtTypeName.getText();
				st[3]=txtname.getText();
				st[4]=txtSalePrice.getText();
				st[5]=txtRemark.getText();
				
				msql.updateData("itemdetail", st);
				
				JOptionPane.showMessageDialog(null, "Successfully updated record");
				clear();
		}
	}

	public ItemUpdate()
	{
		super("Item Update");
		 setLayout(null);
		 setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		 
		 
		JPanel panel=new JPanel();
		JLabel lb1=new JLabel("Item ID :");
		JLabel lb2=new JLabel("Brand Name :");
		JLabel lb3=new JLabel("Type Name :");
		JLabel lb4=new JLabel("Item Name :");
		JLabel lb5=new JLabel("Sale Price :");
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
		
		
		
		cboitemid.setBounds(140,30,100,30);
		panel.add(cboitemid);
		
		txtbrandName.setBounds(140,70,200,30);
		panel.add(txtbrandName);
		
		txtTypeName.setBounds(140,110,200,30);
		panel.add(txtTypeName);
		
		
		
		txtname.setBounds(140,150,200,30);
		panel.add(txtname);
		
		txtSalePrice.setBounds(140,190,200,30);
		panel.add(txtSalePrice);
		
		txtRemark.setBounds(140,230,200,50);
		panel.add(txtRemark);
		
		
		panel.setBounds(0,0,350,280);
		panel.setLayout(null);
		
		
		
		
		
		
		Panel panelbtn=new Panel();
		btnUpdate.setBounds(10,10,80,50);
		panelbtn.add(btnUpdate);
		
		btnCancel.setBounds(60,10,80,50);
		panelbtn.add(btnCancel);
		
		btnClose.setBounds(90,10,80,50);
		panelbtn.add(btnClose);
		
		panelbtn.setBounds(0,290,280,80);
		
	
		 
		getContentPane().add(panel);
		getContentPane().add(panelbtn);
		setBounds(0,0,400,400);
		setVisible(true);
		
		
		btnUpdate.addActionListener(this);
		btnCancel.addActionListener(this);
		btnClose.addActionListener(this);
		cboitemid.addActionListener(this);
		AutoID();
		
	}
	
	
	public void AutoID()
	{
		cboitemid.removeAllItems();
		
		cboitemid.addItem("-Select-");
		String str[]=msql.getNameForChoice("itemdetail");
	
		for(int i=0;i<str.length;i++)
			cboitemid.addItem(str[i].toString());
		cboitemid.setSelectedIndex(0);
	}
	


	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		new ItemUpdate();
	}
	
}
