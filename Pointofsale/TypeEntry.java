package Pointofsale;

import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import pack.*;
import pack.mySQLQueries;

public class TypeEntry extends JFrame implements ActionListener{

	JLabel lblid=new JLabel("type_id");
	JTextField txtname=new JTextField(15);
	JButton btnsave=new JButton("Save");
	JButton btncancel=new JButton("Cancel");
	JButton btnClose=new JButton("Close");
	mySQLQueries msql=new mySQLQueries();
	
	
	public TypeEntry()
	{

		AutoID();
		 setLayout(null);
		 setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			 
		JPanel panel=new JPanel();
		JLabel lb1=new JLabel("Type ID :");
		JLabel lb2=new JLabel("Type Name :");
		
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

	
	
	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
	
		if(event.getSource().equals(btnsave))
		{
			SaveData();
		}else if(event.getSource().equals(btncancel))
		{
		clear();
			
		}else if(event.getSource().equals(btnClose))
		{
			Close();
		}
	}
	
	public void AutoID()
	{
		lblid.setText(String.valueOf(msql.getAutoid("typeid", "type", "TY-")));
	}
	
	public void SaveData()
	{
		String st[]=new String[1];
		if(Checking.IsNull(txtname.getText()))
		{
			JOptionPane.showMessageDialog(null, "Fist you must enter valid Type name");
			txtname.requestFocus();
			txtname.selectAll();
		}
		else
		{
			st[0]=(String)txtname.getText();
			boolean ee=msql.isduplicate("type", st);
			
			if(ee)
			{
				JOptionPane.showMessageDialog(null, "Duplicate Record");
				
			txtname.requestFocus();
			txtname.selectAll();
			}else
			{
				String str[]=new String[2];
				str[1]=txtname.getText();
				str[0]=lblid.getText();
				
				boolean save=msql.insertData("type", str);
				
				if(save)
				{
					JOptionPane.showMessageDialog(null, "Successfully saved record!","Saved Record. ",JOptionPane.INFORMATION_MESSAGE);
					AutoID();
					txtname.setText("");
					txtname.requestFocus();
				}else
				{
					JOptionPane.showMessageDialog(null,"Failed to save new record ","Cannot Saved ",JOptionPane.INFORMATION_MESSAGE);
					AutoID();
				}
			}
		}
	}
	
	public void clear()
	{
		txtname.setText("");
		txtname.requestFocus();
	}
	
	public void Close()
	{
		if((JOptionPane.showConfirmDialog(this, "Are your sure you want to exit ?","Confirm exiting.",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE))==JOptionPane.YES_OPTION)
		{
			dispose();
		}
	}
	
	public static void main(String[] args) {
		new TypeEntry();
	}
}
