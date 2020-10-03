package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JCalendar;

import businessLogic.BLFacade;
import configuration.UtilDate;
import domain.Apustua;
import domain.Event;
import domain.Kuota;
import domain.PertsonaErregistratua;
import domain.Question;

import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class ErantzunaSortu extends JFrame{

	private JCalendar jCalendar;
	private Calendar calendarMio = null;
	private JPanel contentPane;
	
	private JLabel lblGertaerak = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Events"));
	private JLabel lblGalderak = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Questions"));

	private JComboBox<Event> comboBoxGertaerak;
	private DefaultComboBoxModel<Event> gertaerak = new DefaultComboBoxModel<Event>();
	private JComboBox<Question> comboBoxGalderak;
	private DefaultComboBoxModel<Question> galderak = new DefaultComboBoxModel<Question>();
	private JComboBox<Kuota> comboBoxKuotak;
	private DefaultComboBoxModel<Kuota> kuotak= new DefaultComboBoxModel<Kuota>();

	private JButton btnAtzera = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Back"));
	private JButton btnErantzunaSortu = new JButton(ResourceBundle.getBundle("Etiquetas").getString("CreateAnswer"));
	private JLabel lblKuotak = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Fees"));
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KuotakIkusi window = new KuotakIkusi();
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
	public ErantzunaSortu() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 483, 331);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setLayout(null);

		jCalendar = new JCalendar();
		jCalendar.setBounds(new Rectangle(40, 50, 225, 150));
		jCalendar.setBounds(10, 18, 225, 172);
		getContentPane().add(jCalendar);
		
		lblGertaerak.setBounds(260, 17, 155, 14);
		contentPane.add(lblGertaerak);
		
		comboBoxGertaerak = new JComboBox<Event>();
		comboBoxGertaerak.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBoxGalderak.removeAllItems();
				if (comboBoxGertaerak.getSelectedItem()!=null) {
					Event gertaera = (Event) comboBoxGertaerak.getSelectedItem();
					Vector<Question> questions = gertaera.getQuestions();
					for (Question g : questions) {
						galderak.addElement(g);
					}
				}	
			}
		});
		comboBoxGertaerak.setBounds(260, 42, 186, 25);
		contentPane.add(comboBoxGertaerak);
		comboBoxGertaerak.setModel(gertaerak);
		
		lblGalderak.setBounds(260, 78, 94, 14);
		contentPane.add(lblGalderak);
		
		comboBoxGalderak = new JComboBox<Question>();
		comboBoxGalderak.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBoxKuotak.removeAllItems();
				if (comboBoxGalderak.getSelectedItem()!=null) {
					Question galdera = (Question) comboBoxGalderak.getSelectedItem();
					Vector<Kuota> kuota = galdera.getKuotak();
					for (Kuota k:kuota){
						kuotak.addElement(k);
					}
				}
			}
		});
		comboBoxGalderak.setBounds(260, 103, 186, 25);
		contentPane.add(comboBoxGalderak);
		comboBoxGalderak.setModel(galderak);
		
	
		lblKuotak.setBounds(260, 139, 79, 14);
		contentPane.add(lblKuotak);
		
		comboBoxKuotak = new JComboBox<Kuota>();
		comboBoxKuotak.setBounds(260, 164, 186, 25);
		contentPane.add(comboBoxKuotak);
		comboBoxKuotak.setModel(kuotak);
		
		
		btnErantzunaSortu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BLFacade bl = MainGUI.getBusinessLogic();
				Question galdera = (Question) comboBoxGalderak.getSelectedItem();
				Kuota kuota = (Kuota) comboBoxKuotak.getSelectedItem();
				bl.erantzunaGorde(galdera, kuota);
				
				//Irabaziak
				Collection<PertsonaErregistratua> per = bl.getUsers();
				for(PertsonaErregistratua p:per) {
					int nan = p.getNanZbkia();
					List<Apustua> apustuak = bl.getApustuakByNan(nan);
					for(Apustua a:apustuak) {
						if (kuota.getKuotaNumber()==bl.getKuota(a).getKuotaNumber()) {
							float irabaziak = a.getZenbatDiru()*bl.getKuota(a).getIrabaziak();
							System.out.println("Irabaziak: "+irabaziak);
							bl.ordaindu(nan, irabaziak);
						}
					}
				}
			}
		});
		btnErantzunaSortu.setBounds(140, 216, 135, 32);
		contentPane.add(btnErantzunaSortu);	
		
		btnAtzera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				AdminPantaila ap = new AdminPantaila();
				ap.setVisible(true);
				dispose();
			}
		});
		btnAtzera.setBounds(165, 258, 89, 23);
		contentPane.add(btnAtzera);
				
		
		//Calendar kontrolatzeko:
		this.jCalendar.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent propertychangeevent) {
				//						this.jCalendar.addPropertyChangeListener(new PropertyChangeListener() {
				//							public void propertyChange(PropertyChangeEvent propertychangeevent) {
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
							lblGertaerak.setText(ResourceBundle.getBundle("Etiquetas").getString("NoEvents")
									+ ": " + dateformat1.format(calendarMio.getTime()));
						else
							lblGertaerak.setText(ResourceBundle.getBundle("Etiquetas").getString("Events") + ": "
									+ dateformat1.format(calendarMio.getTime()));
						comboBoxGertaerak.removeAllItems();
						System.out.println("Events " + events);

						for (domain.Event ev : events)
							gertaerak.addElement(ev);
						comboBoxGertaerak.repaint();

					} catch (Exception e1) {

						JOptionPane.showMessageDialog(null, ResourceBundle.getBundle("Etiquetas").getString("NoEvents"));
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
			//						    		  Component o=(Component) jCalendar.getDayChooser().getDayPanel().getComponent(i+offset);; 
			Component o = jCalendar.getDayChooser().getDayPanel()
					.getComponent(calendar.get(Calendar.DAY_OF_MONTH) + offset);
			o.setBackground(Color.CYAN);
		}

		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.MONTH, month);

	}
}

