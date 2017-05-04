package Pointofsale;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MainFrom extends JFrame implements ActionListener{
JMenuItem m1BEntery;
JMenuItem m1TEntery;
JMenuItem m1IEntery;
JMenuItem m1PEntery;
JMenuItem m1SEntery;
JMenuItem m1MEntery;



JMenuItem m2IEntery;
JMenuItem m2PEntery;
JMenuItem m2SEntery;


JMenuItem m3IEntery;
JMenuItem m3PEntery;
JMenuItem sale;


	
	public MainFrom()
	{
		setTitle("Point of Sale System");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JMenuBar mainMenu=new JMenuBar();
		
		JMenu menuEntery=new JMenu("Data Entry");
		JMenu menuList=new JMenu("Data Listing");
		JMenu menuSearch=new JMenu("Searching");
		JMenu menuSale=new JMenu("Sale");
		sale=new JMenuItem("Sale ");
		menuSale.add(sale);
		
		m1BEntery=new JMenuItem("Brand Entery");
		m1TEntery=new JMenuItem("Type Entery");
		m1IEntery=new JMenuItem("Item Entery");
	 m1SEntery=new JMenuItem("Supplier Entery");
		 m1PEntery=new JMenuItem("Purchase");
		 m1MEntery=new JMenuItem("Merchandise");
		
		
		
		menuEntery.add(m1BEntery);
		menuEntery.add(m1TEntery);
		menuEntery.add(m1IEntery);
		menuEntery.add(m1SEntery);
		menuEntery.add(m1PEntery);
		menuEntery.add(m1MEntery);
		
		
		
		
		 m2IEntery=new JMenuItem("Item ");
		 m2SEntery=new JMenuItem("Supplier");
		 m2PEntery=new JMenuItem("Purchase");
		
		
		menuList.add(m2IEntery);
		menuList.add(m2SEntery);
		menuList.add(m2PEntery);
		
		
		
		 m3IEntery=new JMenuItem("Item ");
		 m3PEntery=new JMenuItem("Purchase");
		
		
		menuSearch.add(m3IEntery);
		menuSearch.add(m3PEntery);
		
		
		mainMenu.add(menuEntery);
		mainMenu.add(menuList);
		mainMenu.add(menuSearch);
		mainMenu.add(menuSale);
		
		setJMenuBar(mainMenu);
		setVisible(true);
		m1BEntery.addActionListener(this);
		m1TEntery.addActionListener(this);
		m1IEntery.addActionListener(this);
		m1SEntery.addActionListener(this);
		m1PEntery.addActionListener(this);
		m1MEntery.addActionListener(this);
		
		
		
		m2IEntery.addActionListener(this);
		m2SEntery.addActionListener(this);
		m2PEntery.addActionListener(this);

		
	
		m3IEntery.addActionListener(this);
		m3PEntery.addActionListener(this);
		sale.addActionListener(this);
		
		
		setBounds(0,0,800,700);
	}
	
	
	public static void main(String[] args) {
		MainFrom m=new MainFrom();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource().equals(m1BEntery))
		{
			new BrandEntry();
		}else if(e.getSource().equals(m1TEntery))
		{
			new TypeEntry();
		}else if(e.getSource().equals(m1IEntery))
		{
			new ItemEntry();
		}else if(e.getSource().equals(m1PEntery))
		{
			new purchase();
		}else if(e.getSource().equals(m1SEntery))
		{
			new supplierEntery();
		}else if(e.getSource().equals(m1MEntery))
		{
			new MerchandiseEntryUI();
		}else if(e.getSource().equals(m2IEntery))
		{
			new ItemList();
		}else if(e.getSource().equals(m2PEntery))
		{
			new PurchaseList();
		}else if(e.getSource().equals(m2SEntery))
		{
			new SupplierList();
		} else if(e.getSource().equals(m3IEntery))
		{
			new ItemSearch();
		}else if(e.getSource().equals(m3PEntery))
		{
			new PurchaseSearch();
		}else if(e.getSource().equals(sale))
		{
			new sale();
		}
		
	}
}
