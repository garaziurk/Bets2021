package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import businessLogic.BLFacade;
import domain.Administratzailea;
import domain.PertsonaErregistratua;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

public class Login extends JFrame {

	
	static JTextField nanZb;
	private JTextField nanL;
	private JPasswordField pasahitz;
	private JButton btnLogin;
	private JRadioButton rdbtnEuskara;
	private JRadioButton rdbtnEnglish;
	private JRadioButton rdbtnCastellano;
	private ButtonGroup fareButtonGroup = new ButtonGroup();
	private JLabel lblSartuZureNan;
	private JLabel lblSartuZureNan_1;
	private JLabel lblPasahitza;
	private JButton btnKuotakIkusi;
	private JButton erregistratu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
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
	public Login() {
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

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		setBounds(100, 100, 448, 325);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		nanZb = new JTextField();
		nanZb.setBounds(184, 61, 156, 19);
		getContentPane().add(nanZb);
		nanZb.setColumns(10);
		
		lblSartuZureNan = new JLabel("ID number:");
		lblSartuZureNan.setBounds(69, 63, 131, 17);
		getContentPane().add(lblSartuZureNan);
		
		lblSartuZureNan_1 = new JLabel("ID character:");
		lblSartuZureNan_1.setBounds(69, 95, 131, 14);
		getContentPane().add(lblSartuZureNan_1);
		
		nanL = new JTextField();
		nanL.setBounds(184, 92, 30, 20);
		getContentPane().add(nanL);
		nanL.setColumns(10);
		
		lblPasahitza = new JLabel("Password:");
		lblPasahitza.setBounds(69, 127, 131, 14);
		getContentPane().add(lblPasahitza);
		
		pasahitz = new JPasswordField();
		pasahitz.setBounds(184, 124, 156, 20);
		getContentPane().add(pasahitz);
		
		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				// Ziurtatu NAN zenbakia egokia dela:
				if (isNumeric(nanZb.getText()) && nanZb.getText().length()==8 && nanL.getText().length()==1 && !isNumeric(nanL.getText())) {
					int nanZenbakia = Integer.parseInt(nanZb.getText());
					
					String pass = new String(pasahitz.getPassword());
					
					// Sortu BLFacade
					BLFacade bl = MainGUI.getBusinessLogic();
	
					boolean lBai = bl.login(nanZenbakia, pass);
					if (lBai) {
						if (bl.getUserByNan(nanZenbakia) instanceof PertsonaErregistratua) {
							setVisible(false);
							UserPantaila up = new UserPantaila();
							up.setVisible(true);
							dispose(); 

						} else if (bl.getUserByNan(nanZenbakia) instanceof Administratzailea){
							setVisible(false);
							AdminPantaila ap = new AdminPantaila();
							ap.setVisible(true);
							dispose();
										
						}
					} 
					
					
				} else {
					JOptionPane.showMessageDialog(null, ResourceBundle.getBundle("Etiquetas").getString("NanWrong"));
				}
				
				
			}
		});
		btnLogin.setBounds(155, 183, 131, 23);
		getContentPane().add(btnLogin);
		
		erregistratu = new JButton("Register");
		erregistratu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				Erregistratu err = new Erregistratu();
				err.setVisible(true);
				dispose();
			}
		});
		erregistratu.setBounds(272, 230, 139, 23);
		getContentPane().add(erregistratu);
		
		btnKuotakIkusi = new JButton("See fees");
		btnKuotakIkusi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				KuotakIkusi kI = new KuotakIkusi();
				kI.setVisible(true);
				dispose();
			}
		});
		btnKuotakIkusi.setBounds(49, 230, 115, 23);
		getContentPane().add(btnKuotakIkusi);
		
		
		// HIZKUNTZA ALDATZEKO:
		rdbtnEuskara = new JRadioButton("Euskara");
		rdbtnEuskara.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Locale.setDefault(new Locale("eus"));
				System.out.println("Locale: " + Locale.getDefault());
				berrizendatu();
			}
		});
		rdbtnEuskara.setBounds(93, 9, 89, 25);
		fareButtonGroup.add(rdbtnEuskara);
		getContentPane().add(rdbtnEuskara);
		
		rdbtnEnglish = new JRadioButton("English");
		rdbtnEnglish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Locale.setDefault(new Locale("en"));
				System.out.println("Locale: " + Locale.getDefault());
				berrizendatu();
			}
		});
		rdbtnEnglish.setBounds(8, 9, 81, 25);
		fareButtonGroup.add(rdbtnEnglish);
		getContentPane().add(rdbtnEnglish);
		
		rdbtnCastellano = new JRadioButton("Castellano");
		rdbtnCastellano.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Locale.setDefault(new Locale("es"));
				System.out.println("Locale: " + Locale.getDefault());
				berrizendatu();
			}
		});
		rdbtnCastellano.setBounds(186, 9, 127, 25);
		fareButtonGroup.add(rdbtnCastellano);
		getContentPane().add(rdbtnCastellano);
				
		
	}
	private void berrizendatu() {
		lblSartuZureNan.setText(ResourceBundle.getBundle("Etiquetas").getString("NanNumber"));
		lblSartuZureNan_1.setText(ResourceBundle.getBundle("Etiquetas").getString("NanChar"));
		lblPasahitza.setText(ResourceBundle.getBundle("Etiquetas").getString("Password"));
		btnLogin.setText(ResourceBundle.getBundle("Etiquetas").getString("Login"));
		btnKuotakIkusi.setText(ResourceBundle.getBundle("Etiquetas").getString("SeeFees"));
		erregistratu.setText(ResourceBundle.getBundle("Etiquetas").getString("Register"));
	}
}
