package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JCalendar;

import businessLogic.BLFacade;
import configuration.UtilDate;
import domain.Event;
import domain.Kuota;
import domain.PertsonaErregistratua;
import domain.Question;

import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class ApustuaEgin extends JFrame{
	
	private JCalendar jCalendar;
	private Calendar calendarMio = null;
	private JPanel contentPane;
	private JTextField diruK;
	private JButton btnAtzera = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Back"));
	private JButton btnApustuaEgin = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Bet"));
	private JLabel lblHautatutakoGertaera = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ChoosenEvent"));
	private JLabel lblDiruK = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("HowMuchMoneyDoYouWantToBet"));
	private JLabel lblHautatutakoGaldera = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ChoosenQuestion"));
	private JComboBox<Question> comboBoxGalderak;
	private DefaultComboBoxModel<Question> galderak = new DefaultComboBoxModel<Question>();
	private JComboBox<Event> comboBoxGertaera;
	private DefaultComboBoxModel<Event> gertaerak = new DefaultComboBoxModel<Event>();
	private JComboBox<Kuota> comboBoxKuota;
	private DefaultComboBoxModel<Kuota> kuotak = new DefaultComboBoxModel<Kuota>();
	private JLabel lblMomentuHonetanKontuan = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("MoneyYouHaveInTheAccount"));
	private JButton btnApustuaGorde = new JButton(ResourceBundle.getBundle("Etiquetas").getString("SaveBet"));
	
	//Apustu anitzak gordetzen joateko
	private JScrollPane scrollPaneApustua = new JScrollPane();
	private JTable tableApustua= new JTable();
	private DefaultTableModel tableModelApustua;
	private String[] columnNamesApustua = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("Event"), 
			ResourceBundle.getBundle("Etiquetas").getString("Question"), 
			ResourceBundle.getBundle("Etiquetas").getString("Prediction"), 
			ResourceBundle.getBundle("Etiquetas").getString("Money")
	};
	private JLabel lblKuota = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ChoosenPredictionAndFee"));
	private JLabel lblDiruKontu;

	//diru kantitatea zenbakia dela ziurtatzeko
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
					ApustuaEgin window = new ApustuaEgin();
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
	public ApustuaEgin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setLayout(null);
		
		lblDiruK.setBounds(56, 272, 301, 14);
		getContentPane().add(lblDiruK);
		
		lblHautatutakoGertaera.setBounds(330, 16, 195, 14);
		getContentPane().add(lblHautatutakoGertaera);
		
		jCalendar = new JCalendar();
		jCalendar.setBounds(new Rectangle(40, 50, 225, 150));
		jCalendar.setBounds(10, 18, 266, 194);
		getContentPane().add(jCalendar);
		
		btnAtzera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				UserPantaila up = new UserPantaila();
				up.setVisible(true);
				dispose();
			}
		});
		btnAtzera.setBounds(521, 517, 134, 23);
		getContentPane().add(btnAtzera);
		
		diruK = new JTextField();
		diruK.setBounds(330, 269, 182, 20);
		getContentPane().add(diruK);
		diruK.setColumns(10);
		
		lblHautatutakoGaldera.setBounds(330, 78, 124, 14);
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
		comboBoxGertaera.setBounds(330, 31, 252, 26);
		contentPane.add(comboBoxGertaera);
		comboBoxGertaera.setModel(gertaerak);
		
		
		comboBoxGalderak = new JComboBox<Question>();
		comboBoxGalderak.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				comboBoxKuota.removeAllItems();
				if (comboBoxGalderak.getSelectedItem()!=null) {
					Question aukeratutakoGaldera = (Question) comboBoxGalderak.getSelectedItem();
					Vector<Kuota> kuo = aukeratutakoGaldera.getKuotak();
					for (Kuota k : kuo) {
						kuotak.addElement(k);
					}
				}	
			}
		});
		comboBoxGalderak.setBounds(330, 97, 252, 26);
		contentPane.add(comboBoxGalderak);
		comboBoxGalderak.setModel(galderak);
		
		comboBoxKuota = new JComboBox<Kuota>();
		comboBoxKuota.setBounds(330, 168, 252, 26);
		contentPane.add(comboBoxKuota);
		comboBoxKuota.setModel(kuotak);
		
		lblKuota.setBounds(330, 139, 266, 20);
		contentPane.add(lblKuota);
		
		
		btnApustuaEgin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BLFacade bl = MainGUI.getBusinessLogic();
				int nan = Integer.parseInt(Login.nanZb.getText());

				int rowKop = tableApustua.getRowCount();
				Date today = Calendar.getInstance().getTime();
				float dirua = 0;
				Kuota kuota = null;
				
				for (int i=0; i<rowKop; i++) {		
					dirua = (Float) tableApustua.getValueAt(i,3);
					kuota = (Kuota) tableApustua.getValueAt(i,2);
					boolean gorde = bl.apustuaEgin(today, dirua, kuota, nan);
					if (!gorde) {
						JOptionPane.showMessageDialog(null, i + ResourceBundle.getBundle("Etiquetas").getString("CouldNotSave"));
					}
				}
				JOptionPane.showMessageDialog(null, ResourceBundle.getBundle("Etiquetas").getString("WellKept"));
												
				setVisible(false);
				UserPantaila up = new UserPantaila();
				up.setVisible(true);
				dispose();

			}
		});
		btnApustuaEgin.setBounds(521, 483, 134, 31);
		getContentPane().add(btnApustuaEgin);
		
		lblMomentuHonetanKontuan.setBounds(20, 236, 398, 20);
		contentPane.add(lblMomentuHonetanKontuan);
		
		BLFacade bl = MainGUI.getBusinessLogic();
		int nan = Integer.parseInt(Login.nanZb.getText());
		PertsonaErregistratua per = (PertsonaErregistratua)bl.getUserByNan(nan);
		lblDiruKontu = new JLabel(Float.toString(per.getDiruKantitatea()));
		lblDiruKontu.setBounds(385, 236, 69, 20);
		contentPane.add(lblDiruKontu);
		
		
		btnApustuaGorde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
	
				if (isNumeric(diruK.getText())) {
					Float dirua = Float.parseFloat(diruK.getText());
					if (comboBoxKuota.getSelectedItem()!=null) {
						Kuota kuota = (Kuota) comboBoxKuota.getSelectedItem();
						if (dirua>=kuota.getGaldera().getBetMinimum()) {

							BLFacade bl = MainGUI.getBusinessLogic();
							int nan = Integer.parseInt(Login.nanZb.getText());
							PertsonaErregistratua per = (PertsonaErregistratua)bl.getUserByNan(nan);
							
							int rowKop = tableApustua.getRowCount();
							float momentukoDirua = 0;
							for (int i=0; i<rowKop; i++) {		
								momentukoDirua = momentukoDirua + (Float) tableApustua.getValueAt(i,3);			
							}
							
							if ((per.getDiruKantitatea()-momentukoDirua-dirua)>=0 ) {
								lblDiruKontu.setText(Float.toString(per.getDiruKantitatea()-momentukoDirua-dirua));
								
								Vector<Object> row = new Vector<Object>();
								
								row.add(((Event) comboBoxGertaera.getSelectedItem()).getDescription());
								row.add(((Question) comboBoxGalderak.getSelectedItem()).getQuestion());
								row.add(((Kuota) comboBoxKuota.getSelectedItem()));
								row.add(dirua);
								tableModelApustua.addRow(row);
								tableApustua.getColumnModel().getColumn(0).setPreferredWidth(258);
								tableApustua.getColumnModel().getColumn(1).setPreferredWidth(268);
								tableApustua.getColumnModel().getColumn(2).setPreferredWidth(218);
								tableApustua.getColumnModel().getColumn(3).setPreferredWidth(150); 
								
							} else {
								JOptionPane.showMessageDialog(null, ResourceBundle.getBundle("Etiquetas").getString("EnoughMoney"));
							}						
						}
						else {
							JOptionPane.showMessageDialog(null, ResourceBundle.getBundle("Etiquetas").getString("GreaterThanMinimun"));
						}
					} else {
						JOptionPane.showMessageDialog(null, ResourceBundle.getBundle("Etiquetas").getString("SelectPrediction"));
					}

				} else {
					JOptionPane.showMessageDialog(null, ResourceBundle.getBundle("Etiquetas").getString("BetMoneyNumber"));
				}
			}
		});
		btnApustuaGorde.setBounds(356, 299, 140, 25);
		contentPane.add(btnApustuaGorde);
		
		//Egindako apustuak gordetzen joateko lista:
		scrollPaneApustua.setBounds(new Rectangle(10, 383, 403, 134));
		
		scrollPaneApustua.setViewportView(tableApustua);
		tableModelApustua = new DefaultTableModel(null, columnNamesApustua);

		tableApustua.setModel(tableModelApustua);
		tableApustua.getColumnModel().getColumn(0).setPreferredWidth(258);
		tableApustua.getColumnModel().getColumn(1).setPreferredWidth(268);
		tableApustua.getColumnModel().getColumn(2).setPreferredWidth(218);
		tableApustua.getColumnModel().getColumn(3).setPreferredWidth(150);
		
		scrollPaneApustua.setViewportView(tableApustua);
		tableModelApustua = new DefaultTableModel(null, columnNamesApustua);

		tableApustua.setModel(tableModelApustua);
		tableApustua.getColumnModel().getColumn(0).setPreferredWidth(258);
		tableApustua.getColumnModel().getColumn(1).setPreferredWidth(268);
		tableApustua.getColumnModel().getColumn(2).setPreferredWidth(218);
		tableApustua.getColumnModel().getColumn(3).setPreferredWidth(150);
		
		this.getContentPane().add(scrollPaneApustua, null);
		
		//
		
		//Calendar kontrolatzeko:
		this.jCalendar.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent propertychangeevent) {
				if (propertychangeevent.getPropertyName().equals("locale")) {
					jCalendar.setLocale((Locale) propertychangeevent.getNewValue());
				} else if (propertychangeevent.getPropertyName().equals("calendar")) {
					calendarMio = (Calendar) propertychangeevent.getNewValue();
					DateFormat dateformat1 = DateFormat.getDateInstance(1, jCalendar.getLocale());
					jCalendar.setCalendar(calendarMio);
					Date firstDay = UtilDate.trim(new Date(jCalendar.getCalendar().getTime().getTime()));

					try {
						BLFacade facade = MainGUI.getBusinessLogic();
				
						Vector<domain.Event> events = facade.getEvents(firstDay);

						if (events.isEmpty())
							lblHautatutakoGertaera.setText(ResourceBundle.getBundle("Etiquetas").getString("NoEvents")
									+ ": " + dateformat1.format(calendarMio.getTime()));
						else
							lblHautatutakoGertaera.setText(ResourceBundle.getBundle("Etiquetas").getString("Events") + ": "
									+ dateformat1.format(calendarMio.getTime()));
						comboBoxGertaera.removeAllItems();
						System.out.println("Events " + events);

						for (domain.Event ev : events)
							gertaerak.addElement(ev);
						comboBoxGertaera.repaint();

						if (events.size() == 0)
							btnApustuaEgin.setEnabled(false);
						else
							btnApustuaEgin.setEnabled(true);

					} catch (Exception e1) {
						e1.printStackTrace();
					}

				}
				paintDaysWithEvents(jCalendar);
			}
		});
		
	}
		
		//Margotzeko metodoa:
		public static void paintDaysWithEvents(JCalendar jCalendar) {
			// For each day in current month, it is checked if there are events, and in that
			// case, the background color for that day is changed.

			BLFacade facade = MainGUI.getBusinessLogic();

			Vector<Date> dates=facade.getEventsMonth(jCalendar.getDate());
				
			Calendar calendar = jCalendar.getCalendar();
			
			int month = calendar.get(Calendar.MONTH);

			calendar.set(Calendar.DAY_OF_MONTH, 1);
			int offset = calendar.get(Calendar.DAY_OF_WEEK);

			if (Locale.getDefault().equals(new Locale("es")))
				offset += 4;
			else
				offset += 5;
			
			
		 	for (Date d:dates){

		 		calendar.setTime(d);
		 		System.out.println(d);
		 		

				
				// Obtain the component of the day in the panel of the DayChooser of the
				// JCalendar.
				// The component is located after the decorator buttons of "Sun", "Mon",... or
				// "Lun", "Mar"...,
				// the empty days before day 1 of month, and all the days previous to each day.
				// That number of components is calculated with "offset" and is different in
				// English and Spanish

				Component o = jCalendar.getDayChooser().getDayPanel()
						.getComponent(calendar.get(Calendar.DAY_OF_MONTH) + offset);
				o.setBackground(Color.CYAN);
		 	}
		 	
		 		calendar.set(Calendar.DAY_OF_MONTH, 1);
		 		calendar.set(Calendar.MONTH, month);
		 	
		}
}		
		
