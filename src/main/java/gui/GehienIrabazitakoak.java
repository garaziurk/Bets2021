package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import businessLogic.BLFacade;
import domain.*;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class GehienIrabazitakoak extends JFrame{

	private JPanel contentPane;
	private JButton btnAtzera = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Back"));
	private JScrollPane scrollUsers;
	private JTable tableUsers;
	private DefaultTableModel tableModelUsers;
	private String[] tableNamesUsers = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("NanNumber"),
			ResourceBundle.getBundle("Etiquetas").getString("Name"),
			ResourceBundle.getBundle("Etiquetas").getString("FirstSurname"),
			ResourceBundle.getBundle("Etiquetas").getString("Profits")
	};
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GehienIrabazitakoak frame = new GehienIrabazitakoak();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the application.
	 */
	public GehienIrabazitakoak() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 483, 331);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setLayout(null);
		
		scrollUsers = new JScrollPane();
		scrollUsers.setBounds(23, 40, 437, 183);
		contentPane.add(scrollUsers);
		
		tableUsers = new JTable();
		
		tableModelUsers = new DefaultTableModel(null, tableNamesUsers);
		tableUsers.setModel(tableModelUsers);
		
		tableUsers.getColumnModel().getColumn(0).setPreferredWidth(268);
	    tableUsers.getColumnModel().getColumn(1).setPreferredWidth(268);
	    tableUsers.getColumnModel().getColumn(2).setPreferredWidth(268);
	    tableUsers.getColumnModel().getColumn(3).setPreferredWidth(268);
	    
	    
	    scrollUsers.setViewportView(tableUsers);
	    
	    
	    BLFacade bl = MainGUI.getBusinessLogic();
	    
	    Collection<PertsonaErregistratua> userL = bl.getUsers();

	    Vector<PertsonaErregistratua> userLista = new Vector<PertsonaErregistratua>();
	    for(PertsonaErregistratua p:userL) {
	    	if(userLista.isEmpty())
	    		userLista.add(p);
	    	else{
	    		int i = 0;
	    		while(i<userLista.size()) {
	    			if(p.getIrabaziak()>userLista.get(i).getIrabaziak()) {
	    				userLista.add(i,p);
	    				break;
	    			}	
	    			i++;
	    		}
	    		if(i==userLista.size())
	    			userLista.add(p);

	    	}
	    }
	    
	    for(PertsonaErregistratua pe:userLista) {
	    	Vector<Object> row = new Vector<Object>();
			row.add(pe.getNanZbkia());
			row.add(pe.getIzena());
			row.add(pe.getAbizena1());
			row.add(pe.getIrabaziak());
			tableModelUsers.addRow(row);	
	    }
	
	    
	    //Atzera
	    btnAtzera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				AdminPantaila ap = new AdminPantaila();
				ap.setVisible(true);
				dispose();
			}
		});
		btnAtzera.setBounds(190, 245, 89, 23);
		contentPane.add(btnAtzera);
	
	}

}
