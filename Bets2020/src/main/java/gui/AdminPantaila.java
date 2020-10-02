package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;

public class AdminPantaila extends JFrame {

	private String etik = "Etiquetas";
	private JPanel contentPane;
	private JButton btnSaioaItxi = new JButton(ResourceBundle.getBundle(etik).getString("LogOut"));
	private JButton btnGalderaSortu = new JButton(ResourceBundle.getBundle(etik).getString("CreateQuestion"));
	private JButton btnKuotaSortu = new JButton(ResourceBundle.getBundle(etik).getString("CreateFee"));
	private JButton gertaeraSortu = new JButton(ResourceBundle.getBundle(etik).getString("CreateEvent"));
	private JButton btnErantzun = new JButton(ResourceBundle.getBundle(etik).getString("CreateAnswer"));
	private JButton  btnGertaeraKantzelatu = new JButton(ResourceBundle.getBundle(etik).getString("CancelEvent"));
	private JButton gehienIrabazi = new JButton(ResourceBundle.getBundle(etik).getString("MostEarning"));

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminPantaila frame = new AdminPantaila();
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
	public AdminPantaila() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		gertaeraSortu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				//Gertaera sortzeko pantailara
				GertaeraSortu gs = new GertaeraSortu();
				gs.setVisible(true);
				dispose();
			}
		});
		gertaeraSortu.setBounds(25, 35, 177, 36);
		contentPane.add(gertaeraSortu);
		
		btnSaioaItxi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Login l = new Login();
				l.setVisible(true);
				dispose();
			}
		});
		btnSaioaItxi.setBounds(298, 199, 115, 29);
		contentPane.add(btnSaioaItxi);
		
		btnGalderaSortu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				CreateQuestionGUI cq = new CreateQuestionGUI(null);
				cq.setVisible(true);
				dispose();
			}
		});
		btnGalderaSortu.setBounds(25, 87, 177, 36);
		contentPane.add(btnGalderaSortu);
		
		btnKuotaSortu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				KuotaSortu ks = new KuotaSortu();
				ks.setVisible(true);
				dispose();
			}
		});
		btnKuotaSortu.setBounds(228, 35, 177, 36);
		contentPane.add(btnKuotaSortu);
		
		btnErantzun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				ErantzunaSortu es = new ErantzunaSortu();
				es.setVisible(true);
				dispose();
			}
		});
		btnErantzun.setBounds(228, 87, 177, 36);
		contentPane.add(btnErantzun);
		
		btnGertaeraKantzelatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				GertaeraKantzelatu gk = new GertaeraKantzelatu();
				gk.setVisible(true);
				dispose();
			}
		});
		btnGertaeraKantzelatu.setBounds(25, 139, 177, 36);
		contentPane.add(btnGertaeraKantzelatu);
		
		gehienIrabazi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				GehienIrabazitakoak gi = new GehienIrabazitakoak();
				gi.setVisible(true);
				dispose();
			}
		});
		gehienIrabazi.setBounds(228, 139, 177, 36);
		contentPane.add(gehienIrabazi);
	}
}
