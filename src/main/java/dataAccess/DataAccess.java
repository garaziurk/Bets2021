package dataAccess;


import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.jdo.Query;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

import configuration.ConfigXML;
import configuration.UtilDate;
import domain.*;
import exceptions.QuestionAlreadyExist;

/**
 * It implements the data access to the objectDb database
 */
public class DataAccess  {
	protected static EntityManager  db;
	protected static EntityManagerFactory emf;
	
	ConfigXML c=ConfigXML.getInstance();

    public DataAccess(boolean initializeMode)  {
		
		System.out.println("Creating DataAccess instance => isDatabaseLocal: "+c.isDatabaseLocal()+" getDatabBaseOpenMode: "+c.getDataBaseOpenMode());

		open(initializeMode);
		
	}

	public DataAccess()  {	
		 new DataAccess(false);
	}
	
	/**
	 * This is the data access method that initializes the database with some events and questions.
	 * This method is invoked by the business logic (constructor of BLFacadeImplementation) when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	public void initializeDB(){
		
		db.getTransaction().begin();
		
		try {

			
		   Calendar today = Calendar.getInstance();
		   
		   int month=today.get(Calendar.MONTH);
		   month+=1;
		   int year=today.get(Calendar.YEAR);
		   if (month==12) { month=0; year+=1;}  
	    
			Event ev1=new Event(1, "Atlético-Athletic", UtilDate.newDate(year,month,17));
			Event ev2=new Event(2, "Eibar-Barcelona", UtilDate.newDate(year,month,17));
			Event ev3=new Event(3, "Getafe-Celta", UtilDate.newDate(year,month,17));
			Event ev4=new Event(4, "Alavés-Deportivo", UtilDate.newDate(year,month,17));
			Event ev5=new Event(5, "Español-Villareal", UtilDate.newDate(year,month,17));
			Event ev6=new Event(6, "Las Palmas-Sevilla", UtilDate.newDate(year,month,17));
			Event ev7=new Event(7, "Malaga-Valencia", UtilDate.newDate(year,month,17));
			Event ev8=new Event(8, "Girona-Leganés", UtilDate.newDate(year,month,17));
			Event ev9=new Event(9, "Real Sociedad-Levante", UtilDate.newDate(year,month,17));
			Event ev10=new Event(10, "Betis-Real Madrid", UtilDate.newDate(year,month,17));

			Event ev11=new Event(11, "Atletico-Athletic", UtilDate.newDate(year,month,1));
			Event ev12=new Event(12, "Eibar-Barcelona", UtilDate.newDate(year,month,1));
			Event ev13=new Event(13, "Getafe-Celta", UtilDate.newDate(year,month,1));
			Event ev14=new Event(14, "Alavés-Deportivo", UtilDate.newDate(year,month,1));
			Event ev15=new Event(15, "Español-Villareal", UtilDate.newDate(year,month,1));
			Event ev16=new Event(16, "Las Palmas-Sevilla", UtilDate.newDate(year,month,1));
			

			Event ev17=new Event(17, "Málaga-Valencia", UtilDate.newDate(year,month,28));
			Event ev18=new Event(18, "Girona-Leganés", UtilDate.newDate(year,month,28));
			Event ev19=new Event(19, "Real Sociedad-Levante", UtilDate.newDate(year,month,28));
			Event ev20=new Event(20, "Betis-Real Madrid", UtilDate.newDate(year,month,28));
			
			//Administratzaileak sortu:
			Administratzailea a1 = new Administratzailea("admin1", "Bat", "Bat", "admin1@gmail.com", 88888888, "x", "admin1");
			Administratzailea a2 = new Administratzailea("admin2", "Bi", "Bi", "admin2@gmail.com", 77777777, "y", "admin2");
			Administratzailea a3 = new Administratzailea("admin3", "Hiru", "Hiru", "admin3@gmail.com", 66666666, "z", "admin3");
			db.persist(a1);
			db.persist(a2);
			db.persist(a3);
			
			Question q1;
			Question q2;
			Question q3;
			Question q4;
			Question q5;
			Question q6;
					
			if (Locale.getDefault().equals(new Locale("es"))) {
				q1=ev1.addQuestion("¿Quién ganará el partido?",1);
				q2=ev1.addQuestion("¿Quién meterá el primer gol?",2);
				q3=ev11.addQuestion("¿Quién ganará el partido?",1);
				q4=ev11.addQuestion("¿Cuántos goles se marcarán?",2);
				q5=ev17.addQuestion("¿Quién ganará el partido?",1);
				q6=ev17.addQuestion("¿Habrá goles en la primera parte?",2);
			}
			else if (Locale.getDefault().equals(new Locale("en"))) {
				q1=ev1.addQuestion("Who will win the match?",1);
				q2=ev1.addQuestion("Who will score first?",2);
				q3=ev11.addQuestion("Who will win the match?",1);
				q4=ev11.addQuestion("How many goals will be scored in the match?",2);
				q5=ev17.addQuestion("Who will win the match?",1);
				q6=ev17.addQuestion("Will there be goals in the first half?",2);
			}			
			else {
				q1=ev1.addQuestion("Zeinek irabaziko du partidua?",1);
				q2=ev1.addQuestion("Zeinek sartuko du lehenengo gola?",2);
				q3=ev11.addQuestion("Zeinek irabaziko du partidua?",1);
				q4=ev11.addQuestion("Zenbat gol sartuko dira?",2);
				q5=ev17.addQuestion("Zeinek irabaziko du partidua?",1);
				q6=ev17.addQuestion("Golak sartuko dira lehenengo zatian?",2);
				
			}
			
			
			db.persist(q1);
			db.persist(q2);
			db.persist(q3);
			db.persist(q4);
			db.persist(q5);
			db.persist(q6);
	
	        
			db.persist(ev1);
			db.persist(ev2);
			db.persist(ev3);
			db.persist(ev4);
			db.persist(ev5);
			db.persist(ev6);
			db.persist(ev7);
			db.persist(ev8);
			db.persist(ev9);
			db.persist(ev10);
			db.persist(ev11);
			db.persist(ev12);
			db.persist(ev13);
			db.persist(ev14);
			db.persist(ev15);
			db.persist(ev16);
			db.persist(ev17);
			db.persist(ev18);
			db.persist(ev19);
			db.persist(ev20);			
			
			
			db.getTransaction().commit();
			System.out.println("Db initialized");
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	public void open(boolean initializeMode){

		System.out.println("Opening DataAccess instance => isDatabaseLocal: "+c.isDatabaseLocal()+" getDatabBaseOpenMode: "+c.getDataBaseOpenMode());

		String fileName=c.getDbFilename();
		if (initializeMode) {
			fileName=fileName+";drop";
			System.out.println("Deleting the DataBase");
		}

		if (c.isDatabaseLocal()) {
			emf = Persistence.createEntityManagerFactory("objectdb:"+fileName);
			db = emf.createEntityManager();
		} else {
			Map<String, String> properties = new HashMap<String, String>();
			properties.put("javax.persistence.jdbc.user", c.getUser());
			properties.put("javax.persistence.jdbc.password", c.getPassword());

			emf = Persistence.createEntityManagerFactory("objectdb://"+c.getDatabaseNode()+":"+c.getDatabasePort()+"/"+fileName, properties);

			db = emf.createEntityManager();
		}

	}
	
	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
 	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */
	public Question createQuestion(Event event, String question, float betMinimum) throws  QuestionAlreadyExist {
		System.out.println(">> DataAccess: createQuestion=> event= "+event+" question= "+question+" betMinimum="+betMinimum);
		
			Event ev = db.find(Event.class, event.getEventNumber());
			
			if (ev.DoesQuestionExists(question)) throw new QuestionAlreadyExist(ResourceBundle.getBundle("Etiquetas").getString("ErrorQueryAlreadyExist"));
			
			db.getTransaction().begin();
			Question q = ev.addQuestion(question, betMinimum);
			db.persist(ev); // db.persist(q) not required when CascadeType.PERSIST is added in questions property of Event class
							// @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
			db.getTransaction().commit();
			return q;
		
	}
	
	/**
	 * This method retrieves from the database the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	public Vector<Event> getEvents(Date date) {
		System.out.println(">> DataAccess: getEvents");
		Vector<Event> res = new Vector<Event>();	
		TypedQuery<Event> query = db.createQuery("SELECT ev FROM Event ev WHERE ev.eventDate=?1",Event.class);   
		query.setParameter(1, date);
		List<Event> events = query.getResultList();
	 	 for (Event ev:events){
	 	   System.out.println(ev.toString());		 
		   res.add(ev);
		  }
	 	return res;
	}
	
	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	public Vector<Date> getEventsMonth(Date date) {
		System.out.println(">> DataAccess: getEventsMonth");
		Vector<Date> res = new Vector<Date>();	
		
		Date firstDayMonthDate= UtilDate.firstDayMonth(date);
		Date lastDayMonthDate= UtilDate.lastDayMonth(date);
				
		
		TypedQuery<Date> query = db.createQuery("SELECT DISTINCT ev.eventDate FROM Event ev WHERE ev.eventDate BETWEEN ?1 and ?2",Date.class);   
		query.setParameter(1, firstDayMonthDate);
		query.setParameter(2, lastDayMonthDate);
		List<Date> dates = query.getResultList();
	 	 for (Date d:dates){
	 	   System.out.println(d.toString());		 
		   res.add(d);
		  }
	 	return res;
	}
	

/////////////////// GUK EGINDAKO METODOAK /////////////////////////////
	public Pertsona getUserByNan(int nan) {
		return db.find(Pertsona.class, nan);
	}
	
	public PertsonaErregistratua getErregistratuaByNan(int nan) {
		return db.find(PertsonaErregistratua.class, nan);
	}
	
	public Boolean pertsonaErregistratu(String iz, String ab1, String ab2, String email, float dirua, Date jaioD, int nZ, String nL, String pasahitz, String pasahitz2) {
		Pertsona per = this.getUserByNan(nZ);
		if (per!=null) {
			JOptionPane.showMessageDialog(null, ResourceBundle.getBundle("Etiquetas").getString("UserRegistered"));
		} else {
			if (pasahitz.compareTo(pasahitz2)==0) {
				db.getTransaction().begin();
				PertsonaErregistratua pertsona = new PertsonaErregistratua(iz, ab1, ab2, email, nZ, nL, pasahitz, dirua, jaioD);
				db.persist(pertsona);
				db.getTransaction().commit();
				return true;
			} else {
				JOptionPane.showMessageDialog(null, ResourceBundle.getBundle("Etiquetas").getString("PassDifferent"));
			}
		}
		return false;
	}
	
	public Event getEventByNumber(int gZenb) {
		return db.find(Event.class, gZenb);
	}
	
	public void gertaeraSortu(int gZenb, String gDeskr, Date gData) {
		Event gert = this.getEventByNumber(gZenb);
		if(gert!=null) {
			JOptionPane.showMessageDialog(null, ResourceBundle.getBundle("Etiquetas").getString("EventCantSave"));
		}
		else {
				db.getTransaction().begin();
				Event gertaera = new Event(gZenb, gDeskr, gData);
				db.persist(gertaera);
				db.getTransaction().commit();
				JOptionPane.showMessageDialog(null, ResourceBundle.getBundle("Etiquetas").getString("EventSave"));
		}
	}
	
	public boolean login(int nan, String pasahitza) {
		Pertsona per = this.getUserByNan(nan);

		if (per==null) {
			JOptionPane.showMessageDialog(null, ResourceBundle.getBundle("Etiquetas").getString("UserNotRegister"));
		} else {
			if (per.getPasahitza().equals(pasahitza)) {
				return true;
			} else {
				JOptionPane.showMessageDialog(null, ResourceBundle.getBundle("Etiquetas").getString("PassWrong"));
			}
		}
		return false;
	}
	
	public Collection<Event> getEventList() {
		TypedQuery<Event> query = db.createQuery("SELECT p FROM Event p", Event.class);
		List<Event> gertaerak = query.getResultList();
		return gertaerak;
	}
	
	public boolean kuotaGorde(String pronostikoa, float irabaziak, Question galdera) {
		Question q = db.find(Question.class, galdera.getQuestionNumber());
		Vector<Kuota> kuotak = q.getKuotak();
		db.getTransaction().begin();
		for(Kuota k:kuotak) {
			if(k.getPronostikoa().equals(pronostikoa))
				return false;
		}
		Kuota k = q.addKuota(pronostikoa, irabaziak);
		db.persist(k);
		db.getTransaction().commit();
		JOptionPane.showMessageDialog(null, ResourceBundle.getBundle("Etiquetas").getString("FeeSave"));
		return true;
	}
	
	public float updateDirua(int nan, float diru) {
		PertsonaErregistratua per = db.find(PertsonaErregistratua.class, nan);
		db.getTransaction().begin();
		float dir = per.addDirua(diru);
		if (dir>=0) {
			Date today = Calendar.getInstance().getTime();
			DiruMugimendua d = new DiruMugimendua(today, diru, per);
			db.persist(d);
		}
		db.getTransaction().commit();
		return dir;
	}
	
	public List<DiruMugimendua> getDiruMugimenduakByNan(int nan) {
		TypedQuery<DiruMugimendua> query = db.createQuery("SELECT p FROM DiruMugimendua p WHERE p.per=?1", DiruMugimendua.class);
		PertsonaErregistratua per = db.find(PertsonaErregistratua.class, nan);
		query.setParameter(1, per);
		List<DiruMugimendua> diruMugimenduak = query.getResultList();
		return diruMugimenduak;
	}
	
	public boolean apustuaEgin(Date apustuData, float zenbatDiru, Kuota kuota, int nan) {
		Kuota k = db.find(Kuota.class, kuota.getKuotaNumber());
		PertsonaErregistratua per = db.find(PertsonaErregistratua.class, nan);
		if(zenbatDiru>per.getDiruKantitatea()) {
			return false;
		}
		else {
			db.getTransaction().begin();
			Apustua a = new Apustua(apustuData, zenbatDiru, per, k);
			per.addApustua(a);
			k.addApustua(a); 
			per.addDirua((-1)*zenbatDiru);
 			db.persist(a);
			db.getTransaction().commit();
			return true;
		}
	}
	
	public List<Apustua> getApustuakByNan(int nan) {
		TypedQuery<Apustua> query = db.createQuery("SELECT p FROM Apustua p WHERE p.per=?1", Apustua.class);
		PertsonaErregistratua per = db.find(PertsonaErregistratua.class, nan);
		query.setParameter(1, per);
		List<Apustua> apustuak = query.getResultList();
		return apustuak;
	}
	
	public void apustuaEzabatu(Apustua a, int nan) {
		PertsonaErregistratua per = db.find(PertsonaErregistratua.class, nan);
		per.addDirua(a.getZenbatDiru());
		Apustua ap = db.find(Apustua.class, a.getApustuID());
		Kuota k = db.find(Kuota.class, a.getKuota());
		k.removeApustua(ap);
		db.getTransaction().begin();
		db.remove(ap);
		db.getTransaction().commit();
	}
	
	public void erantzunaGorde(Question g, Kuota kuota) {
		Question q = db.find(Question.class, g.getQuestionNumber());
		Kuota k = db.find(Kuota.class, kuota);
		db.getTransaction().begin();
		if(q.getResult()==null) {			
			q.setResult(k.getPronostikoa());
			db.getTransaction().commit();
			JOptionPane.showMessageDialog(null, ResourceBundle.getBundle("Etiquetas").getString("AnswerSave"));
		}
		else
			JOptionPane.showMessageDialog(null, ResourceBundle.getBundle("Etiquetas").getString("AnswerExist"));
	}
	
	public void gertaeraKantzelatu(Event ev) {
		Event gertaera = getEventByNumber(ev.getEventNumber());
		List<Apustua> apustuak = getApustuakByEvent(gertaera);
		for(Apustua a: apustuak) {
			PertsonaErregistratua per = a.getPer();
			per.addDirua(a.getZenbatDiru());
		}
		db.getTransaction().begin();
		db.remove(gertaera);
		db.getTransaction().commit();
	}
	
	public List<Apustua> getApustuakByEvent(Event ev) {
		TypedQuery<Apustua> query = db.createQuery("SELECT p FROM Apustua p WHERE p.kuota.galdera.event=?1", Apustua.class);
		query.setParameter(1, ev);
		List<Apustua> apustuak = query.getResultList();
		return apustuak;
	}
	
	public List<PertsonaErregistratua> getUsers() {
		TypedQuery<PertsonaErregistratua> query = db.createQuery("SELECT p FROM PertsonaErregistratua p", PertsonaErregistratua.class);
		List<PertsonaErregistratua> users = query.getResultList();
		return users;
	}
	
	public void ordaindu(int nan, float irab) {
		PertsonaErregistratua p = db.find(PertsonaErregistratua.class, nan);
		db.getTransaction().begin();
		p.setIrabaziak(p.getIrabaziak()+irab);
		p.setDiruKantitatea(p.getDiruKantitatea()+irab);
		db.getTransaction().commit();
	}
	
	public List<Apustua> getApustuak() {
		TypedQuery<Apustua> query = db.createQuery("SELECT a FROM Apustua a", Apustua.class);
		List<Apustua> apustu = query.getResultList();
		return apustu;
	}
	
	public List<Kuota> getKuotak() {
		TypedQuery<Kuota> query = db.createQuery("SELECT a FROM Kuota a", Kuota.class);
		List<Kuota> kuota = query.getResultList();
		return kuota;
	}
	
	public List<Question> getQuestions() {
		TypedQuery<Question> query = db.createQuery("SELECT a FROM Question a", Question.class);
		List<Question> q = query.getResultList();
		return q;
	}
	
	public void close(){
		db.close();
		System.out.println("DataBase closed");
	}
	
}