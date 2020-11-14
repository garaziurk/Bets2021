package iterator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.sun.tools.javac.util.Iterators;

import businessLogic.BLFacade;
import domain.Event;
import factory.BusinessLogicFactory;

public class IteratorProba {

	public static void main(String[] args) {
		boolean isLocal=true;
		//Facade objektua lortu lehendabiziko ariketa erabiliz
		BLFacade facadeInterface= BusinessLogicFactory.createBusinessLogic(isLocal);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date date;
		try {
			date = sdf.parse("17/12/2020");
			ExtendedIterator<Event> i = facadeInterface.getEvents(date);
			Event e;
			System.out.println("ATZETIK AURRERAKA:");
			i.goLast();//Azkeneko elementuan kokatu
			while (i.hasPrevious()) {
				e = i.previous();
				System.out.println(e.toString());
			}
			System.out.println();
			System.out.println("AURRETIK ATZERAKA:");
			i.goFirst(); // Lehen elem. kokatu
			while (i.hasNext()) {
				e = i.next();
				System.out.println(e.toString());
			}
		} catch (ParseException e1) {
			System.out.println("Errorea gertatu da.");
		}
	}
}
