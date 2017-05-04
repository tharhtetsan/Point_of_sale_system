package Pointofsale;

import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import pack.Checking;
import pack.mySQLQueries;

public class SupplierUpdate extends JFrame implements ActionListener{
	
	JComboBox cbosupplier_id=new JComboBox();
	JTextField txtname=new JTextField(15);
	
	JTextField txtAddress=new JTextField();
	
	JTextField txtphno=new JTextField();
	
	JTextField txtEmail=new JTextField();
	
	JButton btnUpdate=new JButton("Update");
	JButton btnDelete=new JButton("Delete");
	JButton btnClose=new JButton("Close");
	
	mySQLQueries msql=new mySQLQueries();
	
	
	public SupplierUpdate()
	{

		 
		 super("Supplier Update");
		 setLayout(null);
		 setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		 
		 
		JPanel panel=new JPanel();
		JLabel lb1=new JLabel("Supplier ID :");
		JLabel lb3=new JLabel("Address :");
		JLabel lb2=new JLabel("Supplier Name :");
		JLabel lb5=new JLabel("Phone No :");
		JLabel lb4=new JLabel("Email  :");
		
		
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
		
		
		
		cbosupplier_id.setBounds(140,30,100,30);
		panel.add(cbosupplier_id);
		
		txtname.setBounds(140,70,100,30);
		panel.add(txtname);
		
		txtAddress.setBounds(140,110,100,30);
		panel.add(txtAddress);
		
		txtphno.setBounds(140,150,100,30);
		panel.add(txtphno);
		
		txtEmail.setBounds(140,190,100,30);
		panel.add(txtEmail);
		
		panel.setBounds(0,0,280,280);
		panel.setLayout(null);
		
		
		
		
		
		
		Panel panelbtn=new Panel();
		btnUpdate.setBounds(10,10,20,20);
		panelbtn.add(btnUpdate);
		
		btnDelete.setBounds(40,10,20,20);
		panelbtn.add(btnDelete);
		
		btnClose.setBounds(70,10,20,20);
		panelbtn.add(btnClose);
		
		panelbtn.setBounds(0,290,250,80);
		
	
		 
		getContentPane().add(panel);
		getContentPane().add(panelbtn);
		setBounds(0,0,300,400);
		setVisible(true);
		
		
		btnUpdate.addActionListener(this);
		btnDelete.addActionListener(this);
		btnClose.addActionListener(this);
		cbosupplier_id.addActionListener(this);
		
		
		AutoID();
		clear();
	}
	
	public static void main(String[] args) {
		new SupplierUpdate();
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource().equals(btnUpdate))
		{
			UpdateData();
		}else if(event.getSource().equals(btnDelete))
		{
		
			Delete();
			
		}else if(event.getSource().equals(btnClose))
		{
			CloseData();
		}else if(event.getSource().equals(cbosupplier_id))
		{
			fillSupplierData();
		}
		
	}
	private void CloseData()
	{
		if((JOptionPane.showConfirmDialog(this,"Are Your sure you want to exit","Conform exiting.",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE))==JOptionPane.YES_OPTION)
		{
			dispose();
		}
	}

	private void UpdateData() {
		
		if(cbosupplier_id.getSelectedIndex()==0)
		{
			JOptionPane.showMessageDialog(null,"Your must choose  supplier ID");
			cbosupplier_id.requestFocus();
		}
		else if(Checking.IsNull(txtname.getText()))
		{
			JOptionPane.showMessageDialog(null,"Please enter supplier name");
			txtname.requestFocus();
		}
		else if(Checking.IsNull(txtAddress.getText()))
		{
			JOptionPane.showMessageDialog(null,"Please enter supplier address");
			txtAddress.requestFocus();
		}else if(Checking.IsNull(txtEmail.getText()))
		{
			JOptionPane.showMessageDialog(null,"Please enter supplier email");
			txtEmail.requestFocus();
		}else if(Checking.IsNull(txtphno.getText()))
		{
			JOptionPane.showMessageDialog(null,"Please enter supplier phone number");
			txtphno.requestFocus();
		}
		else{
				String st[]=new String[5];
				st[0]=cbosupplier_id.getSelectedItem().toString();
				st[1]=txtname.getText();
				st[2]=txtAddress.getText();
				st[3]=txtphno.getText();
				st[4]=txtEmail.getText();
				
				boolean save=msql.updateData("supplier", st);
				if(save)
				{
					JOptionPane.showMessageDialog(null,"Successfully updated record!");
					
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Fail in supplier updating");
				}
				
				clear();
		}
	}

	private void AutoID() {
		
		cbosupplier_id.removeAllItems();
		
		cbosupplier_id.addItem("-Select-");
		String str[]=msql.getNameForChoice("supplier");
	
		for(int i=0;i<str.length;i++)
			cbosupplier_id.addItem(str[i].toString());
		cbosupplier_id.setSelectedIndex(0);
		
		fillSupplierData();
	}

	private void Delete() {
	if(cbosupplier_id.getSelectedIndex()==0)
	{
		JOptionPane.showMessageDialog(null, "Please choose Supplier ID");
		cbosupplier_id.requestFocus();
	}else
	{
		if(JOptionPane.showConfirmDialog(null,"Are your sure want to DELETE this supplier ? Once done,there is no rolling back action !","Comform Delete",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION)
		{
			try{
				String id=cbosupplier_id.getSelectedItem().toString();
				msql.deleteRecord("supplier",id);
			
				AutoID();
				
				
			}catch(Exception sqle)
			{
				sqle.printStackTrace();
			}
		}	
	}
	
	}
	
	
	private void clear() {
		// TODO Auto-generated method stub
		cbosupplier_id.setSelectedIndex(0);
		txtname.setText("");
		txtAddress.setText("");
		txtEmail.setText("");
		txtphno.setText("");
	}
	
	public void fillSupplierData()
	{
		if(cbosupplier_id.getSelectedIndex()>0)
		{
			String result[]=msql.getSupplierData("supplier",cbosupplier_id.getSelectedItem().toString());
			txtname.setText(result[0]);
			txtAddress.setText(result[1]);
			txtEmail.setText(result[2]);
			txtphno.setText(result[3]);
		
				
		}else
		{

			txtname.setText("");
			txtAddress.setText("");
			txtEmail.setText("");
			txtphno.setText("");
		
		}
	}
	
}
