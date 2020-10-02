package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import businessLogic.BLFacade;
import domain.Pertsona;

import javax.swing.JPasswordField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;

public class Erregistratu extends JFrame {

	private JTextField izen;
	private JTextField abiz1;
	private JTextField abiz2;
	private JTextField email;
	private JTextField urte;
	private JTextField nanZ;
	private JTextField nanL;
	private JPasswordField pasahitz1;
	private JPasswordField pasahitz2;
	private JTextField egun;
	private JComboBox<String> hilabeteComboBox;
	private DefaultComboBoxModel<String> hilabeteak = new DefaultComboBoxModel<String>();
	private JLabel lblIzena = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Name"));
	private JLabel lblAbizena = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("FirstSurname"));
	private JLabel lblAbizena2 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SecondSurname"));
	private JLabel lblEmaila = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Email"));
	private JLabel lblJaiotzedata = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Birthday"));
	private JLabel lblNan = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Nan"));
	private JLabel lblPasahitza = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Password"));
	private JLabel lblPasahitzaErrepikatu = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("RepeatPassword"));
	private JButton btnErregistratu = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Register"));
	private JTextField diruK;
	private JLabel diruKantitatea = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("HowMuchMoney"));
	private JButton btnAtzera = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Back"));

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Erregistratu window = new Erregistratu();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Erregistratu() {
		initialize();
	}
	
	private static Date newDate(int year,int month,int day) {

	     Calendar calendar = Calendar.getInstance();
	     calendar.set(year, month, day,0,0,0);
	     calendar.set(Calendar.MILLISECOND, 0);

	     return calendar.getTime();
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
	
	private boolean isNumericF(String hitza) {
		boolean emaitza;
		try {
			Float.parseFloat(hitza);
			emaitza = true;
		} catch (NumberFormatException excepcion) {
			emaitza = false;
		}
		return emaitza;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setBounds(100, 100, 454, 415);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		lblIzena.setBounds(42, 38, 70, 14);
		getContentPane().add(lblIzena);
		
		lblAbizena.setBounds(42, 63, 145, 14);
		getContentPane().add(lblAbizena);
		
		lblAbizena2.setBounds(42, 88, 145, 14);
		getContentPane().add(lblAbizena2);
		
		lblEmaila.setBounds(42, 113, 46, 14);
		getContentPane().add(lblEmaila);
		
		lblJaiotzedata.setBounds(42, 138, 94, 14);
		getContentPane().add(lblJaiotzedata);
		
		lblNan.setBounds(42, 163, 46, 14);
		getContentPane().add(lblNan);
		
		lblPasahitza.setBounds(42, 216, 82, 14);
		getContentPane().add(lblPasahitza);
		
		izen = new JTextField();
		izen.setBounds(199, 35, 86, 20);
		getContentPane().add(izen);
		izen.setColumns(10);
		
		abiz1 = new JTextField();
		abiz1.setBounds(199, 60, 86, 20);
		getContentPane().add(abiz1);
		abiz1.setColumns(10);
		
		abiz2 = new JTextField();
		abiz2.setBounds(199, 85, 86, 20);
		getContentPane().add(abiz2);
		abiz2.setColumns(10);
		
		email = new JTextField();
		email.setBounds(199, 110, 86, 20);
		getContentPane().add(email);
		email.setColumns(10);
		
		urte = new JTextField();
		urte.setText(ResourceBundle.getBundle("Etiquetas").getString("Year"));
		urte.setBounds(199, 135, 46, 20);
		getContentPane().add(urte);
		urte.setColumns(10);
		
		nanZ = new JTextField();
		nanZ.setBounds(199, 160, 86, 20);
		getContentPane().add(nanZ);
		nanZ.setColumns(10);
		
		nanL = new JTextField();
		nanL.setBounds(300, 160, 25, 20);
		getContentPane().add(nanL);
		nanL.setColumns(10);
		
		pasahitz1 = new JPasswordField();
		pasahitz1.setBounds(199, 213, 86, 20);
		getContentPane().add(pasahitz1);
		
		lblPasahitzaErrepikatu.setBounds(42, 246, 161, 14);
		getContentPane().add(lblPasahitzaErrepikatu);
		
		pasahitz2 = new JPasswordField();
		pasahitz2.setBounds(199, 246, 86, 20);
		getContentPane().add(pasahitz2);
		
		btnErregistratu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BLFacade bl = MainGUI.getBusinessLogic();

				if (!nanZ.getText().isEmpty() && !izen.getText().isEmpty() && !urte.getText().isEmpty() && !egun.getText().isEmpty() && !diruK.getText().isEmpty() && pasahitz1.getPassword().length!=0 && pasahitz2.getPassword().length!=0) {
					if (isNumeric(urte.getText()) && nanZ.getText().length()==8 && isNumeric(egun.getText()) && Integer.parseInt(egun.getText())>0 && Integer.parseInt(egun.getText())<=31) {
						java.util.Date data = newDate(Integer.parseInt(urte.getText()),hilabeteComboBox.getSelectedIndex(),Integer.parseInt(egun.getText()));

						int adina = 2020-Integer.parseInt(urte.getText());
						if (adina>=18 && isNumericF(diruK.getText()) && Float.parseFloat(diruK.getText())>=0) {
							String pas1 = new String(pasahitz1.getPassword());
							String pas2 = new String(pasahitz2.getPassword());
							Boolean erBai = bl.pertsonaErregistratu(izen.getText(), abiz1.getText(), abiz2.getText(), email.getText(), Float.parseFloat(diruK.getText()), data, Integer.parseInt(nanZ.getText()), nanL.getText(), pas1, pas2);

							//Ziurtatzeko ongi erregistratu dela:
							if (erBai) {
								JOptionPane.showMessageDialog(null, ResourceBundle.getBundle("Etiquetas").getString("RegisterCorrect"));
								System.out.println(izen.getText() + " erabiltzailea erregistratu da nan zenbaki honekin " + nanZ.getText() + " " + nanL.getText());

								//Login-era itzultzeko:
								setVisible(false);
								Login log = new Login();
								log.setVisible(true);
								dispose();
							}


						} else {
							if(adina<18) {
								JOptionPane.showMessageDialog(null, ResourceBundle.getBundle("Etiquetas").getString("IsMinor"));
							} else {
								JOptionPane.showMessageDialog(null, ResourceBundle.getBundle("Etiquetas").getString("MoneyNumber"));
							}

						}
					} else {
						if (nanZ.getText().length()!=8) {
							JOptionPane.showMessageDialog(null, ResourceBundle.getBundle("Etiquetas").getString("NoNanNumber"));
						} else {
							JOptionPane.showMessageDialog(null, ResourceBundle.getBundle("Etiquetas").getString("NoYearDay"));
						}
					}
									
				} else {
					if (nanZ.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, ResourceBundle.getBundle("Etiquetas").getString("EnterNan"));
					} else if (izen.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, ResourceBundle.getBundle("Etiquetas").getString("EnterName"));
					} else if (urte.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, ResourceBundle.getBundle("Etiquetas").getString("EnterYear"));
					} else if (egun.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, ResourceBundle.getBundle("Etiquetas").getString("EnterDay"));
					} else if (diruK.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, ResourceBundle.getBundle("Etiquetas").getString("EnterMoney"));
					} else if (pasahitz1.getPassword().length==0 || pasahitz2.getPassword().length==0) {
						JOptionPane.showMessageDialog(null, ResourceBundle.getBundle("Etiquetas").getString("Enter2Pass"));
					}
				}
					
			}
		});
		btnErregistratu.setBounds(300, 289, 113, 23);
		getContentPane().add(btnErregistratu);
		
		hilabeteComboBox = new JComboBox<String>();
		hilabeteComboBox.setBounds(263, 135, 72, 20);
		getContentPane().add(hilabeteComboBox);
		hilabeteComboBox.setModel(hilabeteak);
		
		hilabeteak.addElement(ResourceBundle.getBundle("Etiquetas").getString("January"));
		hilabeteak.addElement(ResourceBundle.getBundle("Etiquetas").getString("February"));
		hilabeteak.addElement(ResourceBundle.getBundle("Etiquetas").getString("March"));
		hilabeteak.addElement(ResourceBundle.getBundle("Etiquetas").getString("April"));
		hilabeteak.addElement(ResourceBundle.getBundle("Etiquetas").getString("May"));
		hilabeteak.addElement(ResourceBundle.getBundle("Etiquetas").getString("June"));
		hilabeteak.addElement(ResourceBundle.getBundle("Etiquetas").getString("July"));
		hilabeteak.addElement(ResourceBundle.getBundle("Etiquetas").getString("August"));
		hilabeteak.addElement(ResourceBundle.getBundle("Etiquetas").getString("September"));
		hilabeteak.addElement(ResourceBundle.getBundle("Etiquetas").getString("October"));
		hilabeteak.addElement(ResourceBundle.getBundle("Etiquetas").getString("November"));
		hilabeteak.addElement(ResourceBundle.getBundle("Etiquetas").getString("December"));
		hilabeteComboBox.setSelectedIndex(0);
		
		egun = new JTextField();
		egun.setText(ResourceBundle.getBundle("Etiquetas").getString("Day"));
		egun.setBounds(356, 135, 46, 20);
		getContentPane().add(egun);
		egun.setColumns(10);
		
		diruKantitatea.setBounds(42, 193, 154, 14);
		getContentPane().add(diruKantitatea);
		
		diruK = new JTextField();
		diruK.setBounds(199, 187, 126, 20);
		getContentPane().add(diruK);
		diruK.setColumns(10);
		
		btnAtzera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Login l = new Login();
				l.setVisible(true);
				dispose();
			}
		});
		btnAtzera.setBounds(300, 320, 113, 23);
		getContentPane().add(btnAtzera);
		
	
		
	}
}
