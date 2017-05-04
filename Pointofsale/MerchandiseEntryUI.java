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

import pack.mySQLQueries;

public class MerchandiseEntryUI extends JFrame implements ActionListener{

	JLabel lblmerid=new JLabel("merchandise  id");
	JTextField txtname=new JTextField(15);
	
	JComboBox<String> cbobrandName=new JComboBox<>();
	JComboBox<String> cboTypeName=new JComboBox<>();
	
	JLabel lbBrandid=new JLabel("b_id");
	JLabel lbTypeid=new JLabel("t_id");
	
	JButton btnsave=new JButton("Save");
	JButton btncancel=new JButton("Cancel");
	JButton btnClose=new JButton("Close");
	
	mySQLQueries msql=new mySQLQueries();
	
	 public MerchandiseEntryUI() {
		
		 
		 super("Item Entery");
		 setLayout(null);
		 setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		 
		 
		JPanel panel=new JPanel();
		JLabel lb1=new JLabel("Merchandise ID :");
		JLabel lb3=new JLabel("Name :");
		JLabel lb2=new JLabel("Brand ID :");
		JLabel lb5=new JLabel("Type :");
		JLabel lb4=new JLabel("Type ID :");
		
		
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
		
		
		
		lblmerid.setBounds(140,30,100,30);
		panel.add(lblmerid);
		
		lbBrandid.setBounds(140,70,100,30);
		panel.add(lbBrandid);
		
		cbobrandName.setBounds(140,110,100,30);
		panel.add(cbobrandName);
		
		lbTypeid.setBounds(140,150,100,30);
		panel.add(lbTypeid);
		
		cboTypeName.setBounds(140,190,100,30);
		panel.add(cboTypeName);
		
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
		cbobrandName.addActionListener(this);
		cboTypeName.addActionListener(this);
		
		AutoID();
		fillBrand();
		fillType();
		clear();
		

	}
	 
	public void SaveData()
	{
		
		if(cbobrandName.getSelectedIndex()==0)
			JOptionPane.showMessageDialog(this, "Please choose brand Name");
		else if(cboTypeName.getSelectedIndex()==0)
			JOptionPane.showMessageDialog(this, "Plase choose type Name");
		else
		{
			String st[]=new String[2];
			st[0]=(String)lbBrandid.getText();
			st[1]=(String)lbTypeid.getText();
			
			
			boolean br=msql.isduplicate("merchandise", st);
			if(br)
			{
				JOptionPane.showMessageDialog(null,"Duplicate Record !");
			}else
			{
				String[] str=new String[3];
				str[2]=(String)lbTypeid.getText();
				str[1]=(String)lbBrandid.getText();
				str[0]=(String)lblmerid.getText();
				
				boolean save=msql.insertData("merchandise", str);
				
				if(save)
				{
					JOptionPane.showMessageDialog(null, "Successfully saved record !","Save Record.",JOptionPane.INFORMATION_MESSAGE);
				}else
				{
					JOptionPane.showMessageDialog(null, "Failed to save new record","Cannot Saved",JOptionPane.INFORMATION_MESSAGE);
				}
			}
			
			AutoID();
		}
	}
	 
	public void close()
	{
		if((JOptionPane.showConfirmDialog(this, "Are your sure you want to exit ?","Confirm exiting.",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE))==JOptionPane.YES_OPTION)
		{
			dispose();
		}	
	}
	
	public void AutoID()
	{
		lblmerid.setText((String.valueOf(msql.getAutoid("merid", "merchandise", "ME-"))));
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
		String str[]=msql.getNameForChoice("type");
		cboTypeName.addItem("-Selected-");
		for(int i=0;i<str.length;i++)
			cboTypeName.addItem(str[i].toString());
	}
	
	public void showBrand()
	{
		if(cbobrandName.getSelectedIndex()==0)
		{
			lbBrandid.setText("");
		}else
		{
			String result=msql.getBrandID(cbobrandName.getSelectedItem().toString());
			lbBrandid.setText(result);
		}
	}
	
	
	
	public void showType()
	{
		if(cboTypeName.getSelectedIndex()==0)
		{
			lbTypeid.setText("");
		}else
		{
			String result=msql.getTypeID(cboTypeName.getSelectedItem().toString());
			lbTypeid.setText(result);
		}
	}
	
	public void clear()
	{
		lbBrandid.setText("");
		lbTypeid.setText("");
		cbobrandName.setSelectedIndex(0);
		cboTypeName.setSelectedIndex(0);
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
			close();
		}else if(event.getSource().equals(cboTypeName))
		{
			showType();
		}else if(event.getSource().equals(cbobrandName))
		{
			showBrand();
		}

	}

	public static void main(String[] args) {
		new MerchandiseEntryUI();
	}
	
	
}
