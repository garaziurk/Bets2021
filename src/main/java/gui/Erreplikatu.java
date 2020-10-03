package gui;

import java.awt.EventQueue;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import businessLogic.BLFacade;
import domain.Apustua;
import domain.PertsonaErregistratua;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class Erreplikatu extends JFrame {

	private JPanel contentPane;
	private JButton btnAtzera = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Back"));
	private JLabel lblApustuak = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("UserBets"));
	private JLabel lblAukeratuErabiltzailea = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ChooseUser"));
	private JComboBox<PertsonaErregistratua> comboBoxUsers;
	private DefaultComboBoxModel<PertsonaErregistratua> users = new DefaultComboBoxModel<PertsonaErregistratua>();
	private JButton btnErreplikatu = new JButton(ResourceBundle.getBundle("Etiquetas").getString("btnReplicate"));
	
	private JScrollPane scrollPaneApustu = new JScrollPane();
	private JTable tableApustu= new JTable();
	private DefaultTableModel tableModelApustu;
	private String[] columnNamesApustu = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("Date"), 
			ResourceBundle.getBundle("Etiquetas").getString("Event"), 
			ResourceBundle.getBundle("Etiquetas").getString("Question"),
			ResourceBundle.getBundle("Etiquetas").getString("Fee"),
			ResourceBundle.getBundle("Etiquetas").getString("Money")
	};
	
	private List<Apustua> apustuListaOrain;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Erreplikatu frame = new Erreplikatu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Erreplikatu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 615, 382);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Apustuak
		scrollPaneApustu.setBounds(new Rectangle(25, 126, 550, 92));
		
		scrollPaneApustu.setViewportView(tableApustu);
		tableModelApustu = new DefaultTableModel(null, columnNamesApustu);

		lblApustuak.setBounds(25, 95, 331, 20);
		contentPane.add(lblApustuak);
		
		scrollPaneApustu.setViewportView(tableApustu);
		tableModelApustu = new DefaultTableModel(null, columnNamesApustu);
		
		this.getContentPane().add(scrollPaneApustu, null);
		
		BLFacade bl = MainGUI.getBusinessLogic();
		
		List<PertsonaErregistratua> erabiltzaileak = bl.getUsers();
		
		for (PertsonaErregistratua erab:erabiltzaileak){
			if(erab.getNanZbkia()!=Integer.parseInt(Login.nanZb.getText())) {
				users.addElement(erab);
			}	
		}
		
		comboBoxUsers = new JComboBox<PertsonaErregistratua>();
		comboBoxUsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BLFacade bl = MainGUI.getBusinessLogic();
				lblApustuak.setText(ResourceBundle.getBundle("Etiquetas").getString("UserBets"));
				if(comboBoxUsers.getSelectedItem()!=null) {
					tableModelApustu.setRowCount(0);
					PertsonaErregistratua erab = (PertsonaErregistratua) comboBoxUsers.getSelectedItem();
					List<Apustua> apustuLista = bl.getApustuakByNan(erab.getNanZbkia());
					apustuListaOrain = new ArrayList<Apustua>();
					for(Apustua a : apustuLista) {
						if (bl.getEvent(bl.getGaldera(bl.getKuota(a))).getEventDate().compareTo(Calendar.getInstance().getTime())>0) {
							apustuListaOrain.add(a);
						}
					}
					if(apustuListaOrain.isEmpty()) {
						lblApustuak.setText(ResourceBundle.getBundle("Etiquetas").getString("NoUserBets"));	
					}
					else {
						for(Apustua a:apustuListaOrain) {
							Vector<Object> row = new Vector<Object>();
							row.add(bl.getEvent(bl.getGaldera(bl.getKuota(a))).getEventDate());
							row.add(bl.getEvent(bl.getGaldera(bl.getKuota(a))));
							row.add(bl.getGaldera(bl.getKuota(a)));
							row.add(bl.getKuota(a));
							row.add(a.getZenbatDiru());
							row.add(a);
							tableModelApustu.addRow(row);
						}
					}		
				}
			}
		});
		comboBoxUsers.setBounds(25, 53, 280, 26);
		contentPane.add(comboBoxUsers);
		comboBoxUsers.setModel(users);
		
		tableApustu.setModel(tableModelApustu);
		tableApustu.getColumnModel().getColumn(3).setPreferredWidth(25);
		tableApustu.getColumnModel().getColumn(4).setPreferredWidth(25);
		
		//Atzera egiteko
		btnAtzera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				UserPantaila up = new UserPantaila();
				up.setVisible(true);
				dispose();
			}
		});
		btnAtzera.setBounds(225, 281, 115, 29);
		contentPane.add(btnAtzera);
		lblAukeratuErabiltzailea.setBounds(25, 16, 302, 20);
		
		contentPane.add(lblAukeratuErabiltzailea);
		btnErreplikatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BLFacade bl = MainGUI.getBusinessLogic();
				if(comboBoxUsers.getSelectedItem()!=null) {
					if(!apustuListaOrain.isEmpty()) {
						Date today = Calendar.getInstance().getTime();
						for(Apustua a : apustuListaOrain) {
							bl.apustuaEgin(today, a.getZenbatDiru(), bl.getKuota(a), Integer.parseInt(Login.nanZb.getText()));
							JOptionPane.showMessageDialog(null, ResourceBundle.getBundle("Etiquetas").getString("ReplicateCorrect"));
							setVisible(false);
							UserPantaila up = new UserPantaila();
							up.setVisible(true);
							dispose();
						}
					}
					else {
						JOptionPane.showMessageDialog(null, ResourceBundle.getBundle("Etiquetas").getString("NoUserBets"));
					}
				}
				else {
					JOptionPane.showMessageDialog(null, ResourceBundle.getBundle("Etiquetas").getString("NoUserSelected"));
				}
			}
		});
		
		btnErreplikatu.setBounds(225, 236, 115, 29);
		contentPane.add(btnErreplikatu);
	}
}
