package gui;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import com.toedter.calendar.JCalendar;

import java.awt.EventQueue;
import java.awt.Rectangle;

public class GertaeraSortu extends JFrame{
	
	private JPanel contentPane;
	private JTextField gDeskribapen;
	private JTextField gZenbakia;
	private JCalendar jCalendar;
	private JButton btnAtzera = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Back"));
	private JLabel lblGertaerarenDeskribapena = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Description"));
	private JLabel lblGertaerarenZenbakia = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Number"));
	private JLabel lblNewLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Date"));
	private JButton btnNewButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("CreateEvent"));
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GertaeraSortu window = new GertaeraSortu();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	public GertaeraSortu() {
		initialize();
	}
	
	//Ziurtatzeko zenbakia dela funtzio laguntzailea sortu dut (data zuzena dela ziurtatzeko).
	private boolean isNumeric(String hitza) {
		boolean emaitza;
		try {
			Integer.parseInt(hitza);
			emaitza = true;
		} catch (NumberFormatException excepcion) {
			emaitza = false;
		}
		return emaitza;
	}

	public void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setLayout(null);
		
		gDeskribapen = new JTextField();
		gDeskribapen.setBounds(25, 183, 365, 20);
		getContentPane().add(gDeskribapen);
		gDeskribapen.setColumns(10);
		
		lblGertaerarenDeskribapena.setBounds(25, 167, 118, 14);
		getContentPane().add(lblGertaerarenDeskribapena);
		
		lblGertaerarenZenbakia.setBounds(315, 25, 92, 14);
		getContentPane().add(lblGertaerarenZenbakia);
		
		gZenbakia = new JTextField();
		gZenbakia.setBounds(315, 44, 86, 20);
		getContentPane().add(gZenbakia);
		gZenbakia.setColumns(10);
		
		lblNewLabel.setBounds(30, 25, 113, 14);
		getContentPane().add(lblNewLabel);
		
		jCalendar = new JCalendar();
		jCalendar.setBounds(new Rectangle(40, 50, 225, 150));
		jCalendar.setBounds(68, 25, 218, 147);
		getContentPane().add(jCalendar);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BLFacade bl = MainGUI.getBusinessLogic();
				
				if (!isNumeric(gZenbakia.getText())) {
					JOptionPane.showMessageDialog(null, ResourceBundle.getBundle("Etiquetas").getString("NoEventNumber"));
				} else {
					if(jCalendar.getDate()==null) {
						JOptionPane.showMessageDialog(null, ResourceBundle.getBundle("Etiquetas").getString("NoDateSelected"));
					}
					else {
						bl.gertaeraSortu(Integer.parseInt(gZenbakia.getText()), gDeskribapen.getText(), jCalendar.getDate());
					}
				}
				
			}
		});
		btnNewButton.setBounds(68, 215, 145, 35);
		getContentPane().add(btnNewButton);

		btnAtzera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				AdminPantaila ap = new AdminPantaila();
				ap.setVisible(true);
				dispose();
			}
		});
		btnAtzera.setBounds(252, 215, 144, 35);
		getContentPane().add(btnAtzera);
		
	}
}
