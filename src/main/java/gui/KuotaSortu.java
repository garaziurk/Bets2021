package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JCalendar;

import businessLogic.BLFacade;
import configuration.UtilDate;
import domain.Event;
import domain.Question;
import iterator.ExtendedIterator;

import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class KuotaSortu extends Paint {
	
	private JCalendar jCalendar;
	private Calendar calendarMio = null;
	private JPanel contentPane;
	private JTextField irabaziak;
	private JTextField pronostikoa;
	private JButton btnAtzera = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Back"));
	private JButton btnKuotaSortu = new JButton(ResourceBundle.getBundle("Etiquetas").getString("CreateFee"));
	private JLabel lblHautatutakoGertaera = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ChoosenEvent"));
	private JLabel lblIrabaziak = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Fee"));
	private JLabel lblPronostikoa = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Prediction"));
	private JLabel lblHautatutakoGaldera = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ChoosenQuestion"));
	private JComboBox<Question> comboBoxGalderak;
	private DefaultComboBoxModel<Question> galderak = new DefaultComboBoxModel<Question>();
	private JComboBox<Event> comboBoxGertaera;
	private DefaultComboBoxModel<Event> gertaerak = new DefaultComboBoxModel<Event>();

	//Ziurtatzeko zenbakia dela funtzio laguntzailea sortu dut (data zuzena dela ziurtatzeko).
	private boolean isNumeric(String hitza) {
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
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KuotaSortu window = new KuotaSortu();
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
	public KuotaSortu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 483, 331);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setLayout(null);
		
		lblPronostikoa.setBounds(266, 139, 105, 14);
		getContentPane().add(lblPronostikoa);
		
		lblIrabaziak.setBounds(266, 164, 83, 14);
		getContentPane().add(lblIrabaziak);
		
		lblHautatutakoGertaera.setBounds(266, 11, 195, 14);
		getContentPane().add(lblHautatutakoGertaera);
		
		jCalendar = new JCalendar();
		jCalendar.setBounds(new Rectangle(40, 50, 225, 150));
		jCalendar.setBounds(10, 18, 225, 150);
		getContentPane().add(jCalendar);
		
		btnKuotaSortu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BLFacade bl = MainGUI.getBusinessLogic();
				
				String pronostiko = pronostikoa.getText();
				
				if (isNumeric(irabaziak.getText())) {
					if (!pronostikoa.getText().isEmpty()) {
						Float irabazi = Float.parseFloat(irabaziak.getText());
						
						if (comboBoxGalderak.getSelectedItem()!=null) {
							Question galdera = (Question) comboBoxGalderak.getSelectedItem();
							boolean gorde = bl.kuotaGorde(pronostiko, irabazi, galdera);
							System.out.println("gorde kuorasortu: "+gorde);
							if(!gorde) {
								JOptionPane.showMessageDialog(null, ResourceBundle.getBundle("Etiquetas").getString("FeeExist"));
							}
						} else {
							JOptionPane.showMessageDialog(null, ResourceBundle.getBundle("Etiquetas").getString("SelectAQuestion"));
						}
					} else {
						JOptionPane.showMessageDialog(null, ResourceBundle.getBundle("Etiquetas").getString("EnterPrediction"));
					}
					
					
				} else {
					JOptionPane.showMessageDialog(null, ResourceBundle.getBundle("Etiquetas").getString("FeeNumber"));
				}
				
				
				
				
			}
		});
		btnKuotaSortu.setBounds(179, 203, 134, 31);
		getContentPane().add(btnKuotaSortu);
		
		btnAtzera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				AdminPantaila ap = new AdminPantaila();
				ap.setVisible(true);
				dispose();
			}
		});
		btnAtzera.setBounds(179, 245, 134, 23);
		getContentPane().add(btnAtzera);
		
		irabaziak = new JTextField();
		irabaziak.setBounds(353, 164, 86, 20);
		getContentPane().add(irabaziak);
		irabaziak.setColumns(10);
		
		pronostikoa = new JTextField();
		pronostikoa.setBounds(353, 136, 86, 20);
		getContentPane().add(pronostikoa);
		pronostikoa.setColumns(10);
		
		lblHautatutakoGaldera.setBounds(266, 73, 195, 14);
		getContentPane().add(lblHautatutakoGaldera);
		
		comboBoxGertaera = new JComboBox<Event>();
		comboBoxGertaera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//comboBoxGalderan galdera posibleak sartzeko:	
				comboBoxGalderak.removeAllItems();
				if (comboBoxGertaera.getSelectedItem()!=null) {
					Event aukeratutakoGertaera = (Event) comboBoxGertaera.getSelectedItem();
					Vector<Question> questions = aukeratutakoGertaera.getQuestions();
					for (Question gal : questions) {
						galderak.addElement(gal);
					}
				}			
			}
		});
		comboBoxGertaera.setBounds(266, 36, 185, 26);
		contentPane.add(comboBoxGertaera);
		comboBoxGertaera.setModel(gertaerak);
		
		
		comboBoxGalderak = new JComboBox<Question>();
		comboBoxGalderak.setBounds(266, 95, 185, 26);
		contentPane.add(comboBoxGalderak);
		comboBoxGalderak.setModel(galderak);
		
		//Calendar kontrolatzeko:
		this.jCalendar.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent propertychangeevent) {
//				this.jCalendar.addPropertyChangeListener(new PropertyChangeListener() {
//					public void propertyChange(PropertyChangeEvent propertychangeevent) {
				if (propertychangeevent.getPropertyName().equals("locale")) {
					jCalendar.setLocale((Locale) propertychangeevent.getNewValue());
				} else if (propertychangeevent.getPropertyName().equals("calendar")) {
					calendarMio = (Calendar) propertychangeevent.getNewValue();
					DateFormat dateformat1 = DateFormat.getDateInstance(1, jCalendar.getLocale());
					jCalendar.setCalendar(calendarMio);
					Date firstDay = UtilDate.trim(new Date(jCalendar.getCalendar().getTime().getTime()));

					try {
						BLFacade facade = MainGUI.getBusinessLogic();

						ExtendedIterator<domain.Event> events = facade.getEvents(firstDay);

						if (!events.hasNext())
							lblHautatutakoGertaera.setText(ResourceBundle.getBundle("Etiquetas").getString("NoEvents")
									+ ": " + dateformat1.format(calendarMio.getTime()));
						else
							lblHautatutakoGertaera.setText(ResourceBundle.getBundle("Etiquetas").getString("Events") + ": "
									+ dateformat1.format(calendarMio.getTime()));
						comboBoxGertaera.removeAllItems();
						System.out.println("Events " + events);

						while(events.hasNext()) {
							domain.Event ev = events.next();
							gertaerak.addElement(ev);
						}
						comboBoxGertaera.repaint();

						events.goFirst();
						if (!events.hasNext())
							btnKuotaSortu.setEnabled(false);
						else
							btnKuotaSortu.setEnabled(true);

					} catch (Exception e1) {

						JOptionPane.showMessageDialog(null, ResourceBundle.getBundle("Etiquetas").getString("NoEvents"));
					}

				}
				paintDaysWithEvents(jCalendar);
			}
		});
		
	}
		
}		
		
