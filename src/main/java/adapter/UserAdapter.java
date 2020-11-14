package adapter;

import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import domain.*;

public class UserAdapter extends AbstractTableModel{
	//taulako zutabeen izenak hizkuntza desberdinetan ikusteko
	private String event = ResourceBundle.getBundle("Etiquetas").getString("Event");
	private String question = ResourceBundle.getBundle("Etiquetas").getString("Question");
	private String eventDate = ResourceBundle.getBundle("Etiquetas").getString("EventDate");
	private String bet = ResourceBundle.getBundle("Etiquetas").getString("BetMoney");

	private Vector<Apustua> apustuak;
	private String[] colNames = new String[] {event, question, eventDate, bet};
	
	public UserAdapter(PertsonaErregistratua p) {
		apustuak = p.getApustuak();
	}

	@Override
	public String getColumnName(int col) {
		return colNames[col];
	} 

	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public int getRowCount() {
		return apustuak.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int colIndex) {
		switch(colIndex) {
		case 0: return apustuak.get(rowIndex).getKuota().getGaldera().getEvent();
		case 1: return apustuak.get(rowIndex).getKuota().getGaldera();
		case 2: return apustuak.get(rowIndex).getApustuData();
		case 3: return apustuak.get(rowIndex).getZenbatDiru();
		}
		return null;
	}

}
