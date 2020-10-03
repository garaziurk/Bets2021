package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Apustua;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;

public class ApustuaEzabatu extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnAtzera = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Back"));
	private JComboBox<Apustua> apustuBox;
	private DefaultComboBoxModel<Apustua> ap = new DefaultComboBoxModel<Apustua>();
	private JButton btnApustuaEzabatu = new JButton(ResourceBundle.getBundle("Etiquetas").getString("DeleteBet"));
	private JPanel contentPane;
	private JLabel lblGertaera = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Event"));
	private JLabel lblGaldera = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Question"));
	private JLabel lblKuota = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Fee"));
	private JLabel lblApostatutakoDirua = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("BetMoney"));
	private JLabel lblGer;
	private JLabel lblGal;
	private JLabel lblKuo;
	private JLabel lblApDiru;
	
	private JFrame frame;
	private JLabel lblNewLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ChooseNumber"));
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ApustuaEzabatu window = new ApustuaEzabatu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ApustuaEzabatu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 472, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel.setBounds(61, 16, 428, 20);
		getContentPane().add(lblNewLabel);
		
		BLFacade bl = MainGUI.getBusinessLogic();
		final int nan = Integer.parseInt(Login.nanZb.getText());
		List<Apustua> apustuak = bl.getApustuakByNan(nan);
		if (apustuak.isEmpty() ) {
			lblNewLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("NoBets"));
		}
		else {
			for (Apustua a:apustuak){
				ap.addElement(a);
			}
		}

		apustuBox = new JComboBox<Apustua>();
		apustuBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BLFacade bl = MainGUI.getBusinessLogic();
				if(apustuBox.getSelectedItem()!=null) {
					Apustua a = (Apustua) apustuBox.getSelectedItem();
					lblGer.setText(bl.getEvent(bl.getGaldera(bl.getKuota(a))).getDescription());
					lblGal.setText(bl.getGaldera(bl.getKuota(a)).getQuestion());
					lblKuo.setText(bl.getKuota(a).toString());
					lblApDiru.setText(Float.toString(a.getZenbatDiru()));
				}
			}
		});
		apustuBox.setBounds(163, 52, 115, 26);
		getContentPane().add(apustuBox);
		apustuBox.setModel(ap);
		
		btnApustuaEzabatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BLFacade bl = MainGUI.getBusinessLogic();
				if (apustuBox.getSelectedItem()!=null) {
					Apustua a = (Apustua) apustuBox.getSelectedItem();
					if(a.getKuota().getGaldera().getEvent().getEventDate().compareTo(Calendar.getInstance().getTime())>0) {
						bl.apustuaEzabatu(a,nan);
						JOptionPane.showMessageDialog(null, ResourceBundle.getBundle("Etiquetas").getString("DeleteBetReturnMoney"));
						setVisible(false);
						UserPantaila up = new UserPantaila();
						up.setVisible(true);
						dispose();
					}
					else {
						JOptionPane.showMessageDialog(null, ResourceBundle.getBundle("Etiquetas").getString("NoDeleteBet"));
					}
				}
				else {
					JOptionPane.showMessageDialog(null, ResourceBundle.getBundle("Etiquetas").getString("NoBetToDelete"));
				}
			}
		});
		btnApustuaEzabatu.setBounds(64, 199, 149, 29);
		getContentPane().add(btnApustuaEzabatu);
		
		btnAtzera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				UserPantaila up = new UserPantaila();
				up.setVisible(true);
				dispose();
			}
		});
		btnAtzera.setBounds(228, 199, 115, 29);
		getContentPane().add(btnAtzera);
		
		lblGertaera.setBounds(15, 98, 69, 20);
		contentPane.add(lblGertaera);
		
		lblGaldera.setBounds(15, 134, 69, 20);
		contentPane.add(lblGaldera);
		
		lblKuota.setBounds(248, 98, 69, 20);
		contentPane.add(lblKuota);
		
		lblApostatutakoDirua.setBounds(248, 134, 140, 20);
		contentPane.add(lblApostatutakoDirua);
		
		lblGer = new JLabel("");
		lblGer.setBounds(92, 98, 141, 20);
		contentPane.add(lblGer);
		
		lblGal = new JLabel("");
		lblGal.setBounds(83, 134, 150, 20);
		contentPane.add(lblGal);
		
		lblKuo = new JLabel("");
		lblKuo.setBounds(307, 98, 143, 20);
		contentPane.add(lblKuo);
		
		lblApDiru = new JLabel("");
		lblApDiru.setBounds(389, 134, 46, 20);
		contentPane.add(lblApDiru);
	}
}
