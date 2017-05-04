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

public class supplierEntery extends JFrame implements ActionListener{
	
	JLabel lblsupplier_id=new JLabel("merchandise  id");
	JTextField txtname=new JTextField(15);
	
	JTextField txtAddress=new JTextField();
	
	JTextField txtphno=new JTextField();
	
	JTextField txtEmail=new JTextField();
	
	JButton btnsave=new JButton("Save");
	JButton btncancel=new JButton("Cancel");
	JButton btnClose=new JButton("Close");
	
	mySQLQueries msql=new mySQLQueries();
	
	
	public supplierEntery()
	{

		 
		 super("Supplier Entery");
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
		
		
		
		lblsupplier_id.setBounds(140,30,100,30);
		panel.add(lblsupplier_id);
		
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
		btnsave.setBounds(10,10,20,20);
		panelbtn.add(btnsave);
		
		btncancel.setBounds(40,10,20,20);
		panelbtn.add(btncancel);
		
		btnClose.setBounds(70,10,20,20);
		panelbtn.add(btnClose);
		
		panelbtn.setBounds(0,290,250,80);
		
	
		 
		getContentPane().add(panel);
		getContentPane().add(panelbtn);
		setBounds(0,0,300,400);
		setVisible(true);
		
		
		btnsave.addActionListener(this);
		btncancel.addActionListener(this);
		btnClose.addActionListener(this);
	
		AutoID();
	}
	
	public static void main(String[] args) {
		new supplierEntery();
	}

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
			CloseData();
		}
		
	}
	private void CloseData()
	{
		if((JOptionPane.showConfirmDialog(this,"Are Your sure you want to exit","Conform exiting.",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE))==JOptionPane.YES_OPTION)
		{
			dispose();
		}
	}

	private void SaveData() {
		
		if(Checking.IsNull(txtname.getText()))
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
			String str[]=new String[2];
			str[0]=txtname.getText();
			str[1]=txtAddress.getText();
			
			boolean chk=msql.isduplicate("supplier", str);
			
			if(chk)
			{
				JOptionPane.showMessageDialog(null,"Duplicate Record!");
			}else
			{
				String st[]=new String[5];
				st[0]=lblsupplier_id.getText();
				st[1]=txtname.getText();
				st[2]=txtAddress.getText();
				st[3]=txtphno.getText();
				st[4]=txtEmail.getText();
				
				boolean save=msql.insertData("supplier", st);
				if(save)
				{
					JOptionPane.showMessageDialog(null,"Successfully record!");
					
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Fail in supplier saving");
				}
				clear();
				AutoID();
			}
		}
	}

	private void AutoID() {
		lblsupplier_id.setText((String.valueOf(msql.getAutoid("supplierid", "supplier", "SU-"))));
	}

	private void clear() {
		// TODO Auto-generated method stub
		txtname.setText("");
		txtAddress.setText("");
		txtEmail.setText("");
		txtphno.setText("");
	}
}
