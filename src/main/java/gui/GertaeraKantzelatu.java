package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
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
import domain.Event;
import iterator.ExtendedIterator;

import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class GertaeraKantzelatu extends JFrame{

	private JCalendar jCalendar;
	private Calendar calendarMio = null;
	private JPanel contentPane;
	
	private JLabel lblGertaerak = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ChooseEvent"));

	private JComboBox<Event> comboBoxGertaerak;
	private DefaultComboBoxModel<Event> gertaerak = new DefaultComboBoxModel<Event>();
	
	private JButton btnAtzera = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Back"));
	private JButton btnBtngertaerakantzelatu = new JButton(ResourceBundle.getBundle("Etiquetas").getString("CancelEvent"));
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GertaeraKantzelatu window = new GertaeraKantzelatu();
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
	public GertaeraKantzelatu() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 483, 331);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setLayout(null);

		jCalendar = new JCalendar();
		jCalendar.setBounds(new Rectangle(40, 50, 225, 150));
		jCalendar.setBounds(115, 16, 225, 150);
		getContentPane().add(jCalendar);
		
		lblGertaerak.setBounds(40, 192, 276, 14);
		contentPane.add(lblGertaerak);
		
		comboBoxGertaerak = new JComboBox<Event>();
		comboBoxGertaerak.setBounds(50, 222, 186, 25);
		contentPane.add(comboBoxGertaerak);
		comboBoxGertaerak.setModel(gertaerak);
		
		btnAtzera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				AdminPantaila ap = new AdminPantaila();
				ap.setVisible(true);
				dispose();
			}
		});
		btnAtzera.setBounds(317, 223, 96, 24);
		contentPane.add(btnAtzera);
		
		btnBtngertaerakantzelatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BLFacade bl = MainGUI.getBusinessLogic();
				if(comboBoxGertaerak.getSelectedItem()!=null) {
					Event ger = (Event) comboBoxGertaerak.getSelectedItem();
					if(ger.getEventDate().compareTo(Calendar.getInstance().getTime())>0) {
						bl.gertaeraKantzelatu(ger);
						JOptionPane.showMessageDialog(null, ResourceBundle.getBundle("Etiquetas").getString("EventCancelled"));
						setVisible(false);
						AdminPantaila ap = new AdminPantaila();
						ap.setVisible(true);
						dispose();
					}
					else {
						JOptionPane.showMessageDialog(null, ResourceBundle.getBundle("Etiquetas").getString("WrongEventDate"));
					}
				}
				else {
					JOptionPane.showMessageDialog(null, ResourceBundle.getBundle("Etiquetas").getString("NoEventToCancel"));
				}
			}
		});
		btnBtngertaerakantzelatu.setBounds(285, 185, 161, 29);
		contentPane.add(btnBtngertaerakantzelatu);
		
		
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

						ExtendedIterator<domain.Event> events = facade.getEvents(firstDay);

						if (!events.hasNext())
							lblGertaerak.setText(ResourceBundle.getBundle("Etiquetas").getString("NoEvents")
									+ ": " + dateformat1.format(calendarMio.getTime()));
						else
							lblGertaerak.setText(ResourceBundle.getBundle("Etiquetas").getString("Events") + ": "
									+ dateformat1.format(calendarMio.getTime()));
						comboBoxGertaerak.removeAllItems();
						System.out.println("Events " + events);

						while(events.hasNext()) {
							domain.Event ev = events.next();
							gertaerak.addElement(ev);
						}
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
		//int today=calendar.get(Calendar.DAY_OF_MONTH);

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
			Component o = (Component) jCalendar.getDayChooser().getDayPanel()
					.getComponent(calendar.get(Calendar.DAY_OF_MONTH) + offset);
			o.setBackground(Color.CYAN);
		}

		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.MONTH, month);

	}
}

