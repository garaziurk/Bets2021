package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import businessLogic.BLFacade;
import domain.ApustuContainer;
import domain.Apustua;
import domain.DiruMugimendua;
import domain.KuotaContainer;
import domain.QuestionContainer;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class Mugimenduak extends JFrame {

	private JPanel contentPane;
	private JButton btnAtzera = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Back"));
	private JLabel lblAzkenekoMugimenduak = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("LastMovements"));
	
	private JScrollPane scrollPaneMugimendu = new JScrollPane();
	private JTable tableMugimendu= new JTable();
	private DefaultTableModel tableModelMugimendu;
	private String[] columnNamesMugimendu = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("Date"), 
			ResourceBundle.getBundle("Etiquetas").getString("Event"), 
			ResourceBundle.getBundle("Etiquetas").getString("Win")
	};
	
	private JScrollPane scrollPaneDirua = new JScrollPane();
	private JTable tableDirua= new JTable();
	private DefaultTableModel tableModelDirua;
	private String[] columnNamesDirua = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("Date"), 
			ResourceBundle.getBundle("Etiquetas").getString("Money")
	};
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mugimenduak frame = new Mugimenduak();
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
	public Mugimenduak() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Apustuak
		scrollPaneMugimendu.setBounds(new Rectangle(25, 49, 377, 92));
		
		scrollPaneMugimendu.setViewportView(tableMugimendu);
		tableModelMugimendu = new DefaultTableModel(null, columnNamesMugimendu);

		tableMugimendu.setModel(tableModelMugimendu);
		tableMugimendu.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableMugimendu.getColumnModel().getColumn(1).setPreferredWidth(268);

		
		lblAzkenekoMugimenduak.setBounds(52, 16, 199, 20);
		contentPane.add(lblAzkenekoMugimenduak);
		
		scrollPaneMugimendu.setViewportView(tableMugimendu);
		tableModelMugimendu = new DefaultTableModel(null, columnNamesMugimendu);

		tableMugimendu.setModel(tableModelMugimendu);
		tableMugimendu.getColumnModel().getColumn(0).setPreferredWidth(218);
		tableMugimendu.getColumnModel().getColumn(1).setPreferredWidth(268);
		tableMugimendu.getColumnModel().getColumn(2).setPreferredWidth(150);
		
		this.getContentPane().add(scrollPaneMugimendu, null);
		
		//Mugimenduak sartzeko:
		BLFacade bl = MainGUI.getBusinessLogic();
		
		int nan = Integer.parseInt(Login.nanZb.getText());
		
		List<Apustua> apustuak = bl.getApustuakByNan(nan);
		
		if (apustuak.isEmpty() ) {
			lblAzkenekoMugimenduak.setText(ResourceBundle.getBundle("Etiquetas").getString("NoBetsMovements"));
		}
		
		for (Apustua a:apustuak){
			Vector<Object> row = new Vector<Object>();
			row.add(a.getApustuData());
			System.out.println(bl.getKuota(a));
			row.add(bl.getEvent(bl.getGaldera(bl.getKuota(a))));
			row.add(a.getZenbatDiru()*bl.getKuota(a).getIrabaziak());
			row.add(a); 
			tableModelMugimendu.addRow(row);		
		}
		tableMugimendu.getColumnModel().getColumn(0).setPreferredWidth(218);
		tableMugimendu.getColumnModel().getColumn(1).setPreferredWidth(268);
		tableMugimendu.getColumnModel().getColumn(2).setPreferredWidth(150);
		//
		
		//Dirua sartu eta atera
		scrollPaneDirua.setBounds(new Rectangle(25, 148, 255, 92));
		
		scrollPaneDirua.setViewportView(tableDirua);
		tableModelDirua = new DefaultTableModel(null, columnNamesDirua);

		tableDirua.setModel(tableModelDirua);
		tableDirua.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableDirua.getColumnModel().getColumn(1).setPreferredWidth(268);
		
		scrollPaneDirua.setViewportView(tableDirua);
		tableModelDirua = new DefaultTableModel(null, columnNamesDirua);

		tableDirua.setModel(tableModelDirua);
		tableDirua.getColumnModel().getColumn(0).setPreferredWidth(218);
		tableDirua.getColumnModel().getColumn(1).setPreferredWidth(268);
		
		this.getContentPane().add(scrollPaneDirua, null);
		
		//Mugimenduak sartzeko:
		
		List<DiruMugimendua> diruMugimenduak = bl.getDiruMugimenduakByNan(nan);
		
		
		for (DiruMugimendua d:diruMugimenduak){
			Vector<Object> row = new Vector<Object>();

			row.add(d.getDiruData());
			row.add(d.getZenbatDiru());
			row.add(d); 
			tableModelDirua.addRow(row);		
		}
		tableDirua.getColumnModel().getColumn(0).setPreferredWidth(218);
		tableDirua.getColumnModel().getColumn(1).setPreferredWidth(268);

		
		//Atzera egiteko
		btnAtzera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				UserPantaila up = new UserPantaila();
				up.setVisible(true);
				dispose();
			}
		});
		btnAtzera.setBounds(287, 210, 115, 29);
		contentPane.add(btnAtzera);
		
	}
}
