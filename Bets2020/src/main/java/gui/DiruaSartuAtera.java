package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.PertsonaErregistratua;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;

public class DiruaSartuAtera extends JFrame {

	private JPanel contentPane;
	private JTextField diruK;
	private JRadioButton rdbtnDiruaSartu = new JRadioButton(ResourceBundle.getBundle("Etiquetas").getString("PutMoney"));;
	private JRadioButton rdbtnDiruaAtera = new JRadioButton(ResourceBundle.getBundle("Etiquetas").getString("TakeMoneyAway"));
	private JLabel lblKantitatea = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("HowMuchMoney"));
	private JButton btnGorde = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Save"));
	private ButtonGroup fareButtonGroup = new ButtonGroup();
	private JLabel lblMomentuHonetanKontuan = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("MoneyYouHaveInTheAccount"));
	private JButton btnAtzera = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Back"));

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DiruaSartuAtera frame = new DiruaSartuAtera();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
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
	 * Create the frame.
	 */
	public DiruaSartuAtera() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		rdbtnDiruaSartu.setBounds(99, 67, 120, 29);
		fareButtonGroup.add(rdbtnDiruaSartu);
		contentPane.add(rdbtnDiruaSartu);
		

		rdbtnDiruaAtera.setBounds(234, 67, 155, 29);
		fareButtonGroup.add(rdbtnDiruaAtera);
		contentPane.add(rdbtnDiruaAtera);
		
		lblKantitatea.setBounds(28, 108, 146, 20);
		contentPane.add(lblKantitatea);
		
		diruK = new JTextField();
		diruK.setBounds(224, 105, 146, 26);
		contentPane.add(diruK);
		diruK.setColumns(10);
		
		btnGorde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BLFacade bl = MainGUI.getBusinessLogic();
				if (!Login.nanZb.getText().isEmpty() && !diruK.getText().isEmpty() && isNumeric(Login.nanZb.getText()) && isNumericF(diruK.getText())) {
					int nan = Integer.parseInt(Login.nanZb.getText());
					PertsonaErregistratua per = bl.getErregistratuaByNan(nan);
					if (per!=null) {
						float dirua = Float.parseFloat(diruK.getText());
						if (rdbtnDiruaSartu.isSelected()) {
							bl.updateDirua(nan, dirua);
							JOptionPane.showMessageDialog(null, ResourceBundle.getBundle("Etiquetas").getString("MoneySave"));
							setVisible(false);
							UserPantaila up = new UserPantaila();
							up.setVisible(true);
							dispose();
						} else if (rdbtnDiruaAtera.isSelected()) {
							float dir = bl.updateDirua(nan, dirua*-1);
							if (dir<0) {
								JOptionPane.showMessageDialog(null, ResourceBundle.getBundle("Etiquetas").getString("NoMoney"));
								bl.updateDirua(nan, dirua);
							} else {
								JOptionPane.showMessageDialog(null, ResourceBundle.getBundle("Etiquetas").getString("MoneySave"));
								setVisible(false);
								UserPantaila up = new UserPantaila();
								up.setVisible(true);
								dispose();
							}
						} else {
							JOptionPane.showMessageDialog(null, ResourceBundle.getBundle("Etiquetas").getString("PutTakeMoney"));
						}
					} 					
					
				} else {
					JOptionPane.showMessageDialog(null, ResourceBundle.getBundle("Etiquetas").getString("MoneyNumber"));
						
				}
			}
		});
		btnGorde.setBounds(155, 160, 115, 29);
		contentPane.add(btnGorde);
		
		lblMomentuHonetanKontuan.setBounds(15, 29, 398, 20);
		contentPane.add(lblMomentuHonetanKontuan);
		
		BLFacade bl = MainGUI.getBusinessLogic();
		int nan = Integer.parseInt(Login.nanZb.getText());
		PertsonaErregistratua per = (PertsonaErregistratua)bl.getUserByNan(nan);
		JLabel lblDiruKontu = new JLabel(Float.toString(per.getDiruKantitatea()));
		lblDiruKontu.setBounds(374, 29, 69, 20);
		contentPane.add(lblDiruKontu);
		
		btnAtzera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				UserPantaila up = new UserPantaila();
				up.setVisible(true);
				dispose();
			}
		});
		btnAtzera.setBounds(155, 199, 115, 29);
		contentPane.add(btnAtzera);
	}
}
