package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import adapter.UserAdapter;
import businessLogic.BLFacade;
import domain.PertsonaErregistratua;

import java.awt.BorderLayout;
import java.awt.Dimension;

public class ApustuenTaula extends JFrame{

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ApustuenTaula window = new ApustuenTaula();
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
	public ApustuenTaula() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		
		BLFacade bl = MainGUI.getBusinessLogic();
		int nan = Integer.parseInt(Login.nanZb.getText());
		PertsonaErregistratua per = (PertsonaErregistratua)bl.getUserByNan(nan);
		UserAdapter model = new UserAdapter(per);

	    setBounds(10,10,400,300);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(380,280));
		JPanel panel = new JPanel();
		panel.add(scrollPane);
		getContentPane().add(panel,BorderLayout.CENTER);
	}

}
