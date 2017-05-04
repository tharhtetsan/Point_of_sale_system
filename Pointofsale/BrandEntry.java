package Pointofsale;

import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import pack.*;
import javax.swing.*;
public class BrandEntry extends javax.swing.JDialog implements ActionListener{
	
	JLabel lblid=new JLabel("brand id");
	JTextField txtname=new JTextField(15);
	JButton btnsave=new JButton("Save");
	JButton btncancel=new JButton("Cancel");
	JButton btnClose=new JButton("Close");
	mySQLQueries msql=new mySQLQueries();
	
	
	public BrandEntry()
	{
		
		setTitle("Brand Entery");
		initComponents();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		AutoID();
		getRootPane().setDefaultButton(btnsave);
		
		
	}
	
	public void initComponents()
	{
		
		 setLayout(null);
		 
		JPanel panel=new JPanel();
		JLabel lb1=new JLabel("Brand ID :");
		JLabel lb2=new JLabel("Brand Name :");
		
		lb1.setBounds(30,30,100,30);
		panel.add(lb1);
		
		lb2.setBounds(30,70,100,30);
		panel.add(lb2);
		
		lblid.setBounds(140, 30, 100, 30);
		panel.add(lblid);
		
		txtname.setBounds(140,70,200,30);
		panel.add(txtname);
		
		panel.setBounds(0,0,350,150);
		panel.setLayout(null);
		
		
		
		Panel panelbtn=new Panel();
		btnsave.setBounds(10,10,20,20);
		panelbtn.add(btnsave);
		
		btncancel.setBounds(40,10,20,20);
		panelbtn.add(btncancel);
		
		btnClose.setBounds(70,10,20,20);
		panelbtn.add(btnClose);
		panelbtn.setBounds(0,150,350,100);
	
		
		getContentPane().add(panel);
		getContentPane().add(panelbtn);
		
		setBounds(100,100,450,250);
		setVisible(true);
		
		btnsave.addActionListener(this);
		btncancel.addActionListener(this);
		btnClose.addActionListener(this);
	}
	
	
	public void AutoID()
	{
		System.out.println(String.valueOf(msql.getAutoid("brandid","brand","BR-")));
		lblid.setText(String.valueOf(msql.getAutoid("brandid","brand","BR-")));
	}
	
	private void SaveData()
	{
		String st[]=new String[1];
		
		if(Checking.IsNull(txtname.getText()))
		{
			JOptionPane.showMessageDialog(null,"First you must enter valid Brand name");
			txtname.requestFocus();
			txtname.selectAll();
		}
		else
		{
			st[0]=(String) txtname.getText();
			boolean ee=msql.isduplicate("brand",st);
			
			if(ee)
			{
				JOptionPane.showMessageDialog(null,"Duplicate Record!");
				txtname.requestFocus();
				txtname.selectAll();
			}
			else
			{
				String str[]=new String[2];
				str[1]=txtname.getText();
				str[0]=lblid.getText();
				boolean save=msql.insertData("brand",str);
				
				if(save)
				{
					JOptionPane.showMessageDialog(null, "Successfully Save record! ","Save Record.",JOptionPane.INFORMATION_MESSAGE);
					AutoID();
				}
				else
				{
					JOptionPane.showMessageDialog(null,"Save failure");
					AutoID();
				}
					clear();
			}
		}
		
	}
	
	


	public void clear()
	{
		txtname.setText("");
		txtname.requestFocus();
	}
	
	
	private void CloseData()
	{
		if((JOptionPane.showConfirmDialog(this,"Are Your sure you want to exit","Conform exiting.",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE))==JOptionPane.YES_OPTION)
		{
			dispose();
		}
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
	
	public static void main(String[] args) {
		new BrandEntry();
	}

}
