package businessLogic;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.jws.WebMethod;
import javax.jws.WebService;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.*;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

/**
 * It implements the business logic as a web service.
 */
@WebService(endpointInterface = "businessLogic.BLFacade")
public class BLFacadeImplementation  implements BLFacade {

	DataAccess dbManager;
	
	public BLFacadeImplementation()  {		
		System.out.println("Creating BLFacadeImplementation instance");
		ConfigXML c=ConfigXML.getInstance();
		
		if (c.getDataBaseOpenMode().equals("initialize")) {
			DataAccess dbManager=new DataAccess(c.getDataBaseOpenMode().equals("initialize"));
			dbManager.initializeDB();
			dbManager.close();
			}

	}

	public BLFacadeImplementation(DataAccess da)  {

		System.out.println("Creating BLFacadeImplementation instance with DataAccess parameter");
		ConfigXML c=ConfigXML.getInstance();

		if (c.getDataBaseOpenMode().equals("initialize")) {
			da.open(true);
			da.initializeDB();
			da.close();

		}
		dbManager=da;		
	}

	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws EventFinished if current data is after data of the event
 	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */
   @WebMethod
   public Question createQuestion(Event event, String question, float betMinimum) throws EventFinished, QuestionAlreadyExist{
	   
	    //The minimum bed must be greater than 0
	    dbManager.open(false);
		Question qry=null;
		
	    
		if(new Date().compareTo(event.getEventDate())>0)
			throw new EventFinished(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished"));
				
		
		 qry=dbManager.createQuestion(event,question,betMinimum);		

		dbManager.close();
		
		return qry;
   };
	
	/**
	 * This method invokes the data access to retrieve the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
    @WebMethod	
	public Vector<Event> getEvents(Date date)  {
		dbManager.open(false);
		Vector<Event>  events=dbManager.getEvents(date);
		dbManager.close();
		return events;
	}

    
	/**
	 * This method invokes the data access to retrieve the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	@WebMethod public Vector<Date> getEventsMonth(Date date) {
		dbManager.open(false);
		Vector<Date>  dates=dbManager.getEventsMonth(date);
		dbManager.close();
		return dates;
	}
	
	////////////////////// GUK EGINDAKO METODOAK ////////////////////////////
	
	//nan zenbakiarekin aurkitzeko
	@WebMethod
	public Pertsona getUserByNan(int nan) {
		dbManager.open(false);
		Pertsona per = dbManager.getUserByNan(nan);
		dbManager.close();
		return per;
	}
	
	@WebMethod
	public PertsonaErregistratua getErregistratuaByNan(int nan) {
		dbManager.open(false);
		PertsonaErregistratua per = dbManager.getErregistratuaByNan(nan);
		dbManager.close();
		return per;
	}
	
	//datuak dban gordetzeko
	@WebMethod
	public boolean pertsonaErregistratu(String iz, String ab1, String ab2, String email, float dirua, Date jaioD, int nZ, String nL, String pasahitz, String pasahitz2) {
		dbManager.open(false);
		boolean erBai = dbManager.pertsonaErregistratu(iz, ab1, ab2, email, dirua, jaioD, nZ, nL, pasahitz, pasahitz2);
		dbManager.close();
		return erBai;
	}
	
	@WebMethod
	public void gertaeraSortu(int gZenb, String gDeskr, Date gData) {
		DataAccess dbManager = new DataAccess();
		dbManager.gertaeraSortu(gZenb, gDeskr, gData);
		dbManager.close();
	}
	
	//Gertaeraren zenbakia lortzeko:
	@WebMethod
	public Event getEventByNumber(int gZenb) {
		dbManager.open(false);
		Event ger = dbManager.getEventByNumber(gZenb);
		dbManager.close();
		return ger;
	}
	
	//logeatzeko
	@WebMethod
	public boolean login(int nan, String pasahitza) {
		dbManager.open(false);
		boolean per = dbManager.login(nan, pasahitza);
		dbManager.close();
		return per;
	}
	
	@WebMethod
	public Collection<Event> getEventList() {
		dbManager.open(false);
		Collection<Event> gertaera = dbManager.getEventList();
		dbManager.close();
		return gertaera;
	}
	
	@WebMethod
	public boolean kuotaGorde(String pronostikoa, float irabaziak, Question galdera) {
		dbManager.open(false);
		boolean gorde = dbManager.kuotaGorde(pronostikoa, irabaziak, galdera);
		dbManager.close();
		return gorde;
	}
	
	@WebMethod
	public float updateDirua(int nan, float dirua) {
		dbManager.open(false);
		float dir = dbManager.updateDirua(nan, dirua);
		dbManager.close();
		return dir;
	}
	
	@WebMethod
	public List<DiruMugimendua> getDiruMugimenduakByNan(int nan) {
		dbManager.open(false);
		List<DiruMugimendua> diruMugimenduak = dbManager.getDiruMugimenduakByNan(nan);
		dbManager.close();
		return diruMugimenduak;
	}
	
	@WebMethod
	public boolean apustuaEgin(Date apustuData, float zenbatDiru, Kuota kuota, int nan) {
		dbManager.open(false);
		Boolean apustu = dbManager.apustuaEgin(apustuData,zenbatDiru,kuota,nan);
		dbManager.close();
		return apustu;
	}
	
	@WebMethod
	public List<Apustua> getApustuakByNan(int nan) {
		dbManager.open(false);
		List<Apustua> apustuak = dbManager.getApustuakByNan(nan);
		dbManager.close();
		return apustuak;
	}
	
	@WebMethod
	public void apustuaEzabatu(Apustua a, int nan) {
		dbManager.open(false);
		dbManager.apustuaEzabatu(a,nan);
		dbManager.close();
	}
	
	@WebMethod
	public void erantzunaGorde(Question g, Kuota k) {
		dbManager.open(false);
		dbManager.erantzunaGorde(g,k);
		dbManager.close();
	}
	
	@WebMethod
	public void gertaeraKantzelatu(Event ev) {
		dbManager.open(false);
		dbManager.gertaeraKantzelatu(ev);
		dbManager.close();
	}
	
	@WebMethod
	public List<Apustua> getApustuakByEvent(Event ev){
		dbManager.open(false);
		List<Apustua> apustuak = dbManager.getApustuakByEvent(ev);
		dbManager.close();
		return apustuak;
	}
	
	@WebMethod
	public List<PertsonaErregistratua> getUsers() {
		dbManager.open(false);
		List<PertsonaErregistratua> users = dbManager.getUsers();
		dbManager.close();
		return users;
	}
	
	@WebMethod
	public void ordaindu(int nan, float i) {
		dbManager.open(false);
		dbManager.ordaindu(nan,i);
		dbManager.close();
	}
	
	//Containerra:
	@WebMethod
	public List<ApustuContainer> getListApustuContainer(){
		dbManager.open(false);
		List<Apustua> listApustuak = dbManager.getApustuak();
		List<ApustuContainer> listAC= new LinkedList<ApustuContainer>();
		for (Apustua a:listApustuak)
			listAC.add(new ApustuContainer(a));
		dbManager.close();
		return listAC;
	}
	
	@WebMethod
	public List<KuotaContainer> getListKuotaContainer(){
		dbManager.open(false);
		List<Kuota> listKuotak = dbManager.getKuotak();
		List<KuotaContainer> listKC= new LinkedList<KuotaContainer>();
		for (Kuota a:listKuotak)
			listKC.add(new KuotaContainer(a));
		dbManager.close();
		return listKC;
	}
	
	@WebMethod
	public List<QuestionContainer> getListQuestionContainer(){
		dbManager.open(false);
		List<Question> listQuestions = dbManager.getQuestions();
		List<QuestionContainer> listQC= new LinkedList<QuestionContainer>();
		for (Question a:listQuestions)
			listQC.add(new QuestionContainer(a));
		dbManager.close();
		return listQC;
	}
	
	@WebMethod
	public Kuota getKuota(Apustua a) {
		List<ApustuContainer> listAC = getListApustuContainer();
		for (ApustuContainer i:listAC) {
			if (i.getApustua().getApustuID()==a.getApustuID()) {
				return i.getKuota();
			}
		}
		return null;
	}
	
	@WebMethod 
	public Question getGaldera(Kuota k) {
		List<KuotaContainer> listKC = getListKuotaContainer();
		for (KuotaContainer i:listKC) {
			if (i.getKuota().getKuotaNumber()==k.getKuotaNumber()) {
				return i.getGaldera();
			}
		}
		return null;
	}
	
	@WebMethod 
	public Event getEvent(Question q) {
		List<QuestionContainer> listQC = getListQuestionContainer();
		for (QuestionContainer i:listQC) {
			System.out.println(i.getQuestion().getQuestionNumber());
			if (i.getQuestion().getQuestionNumber().equals(q.getQuestionNumber())) {
				return i.getEvent();
			}
		}
		return null;
	}
	
	////////////////////////////////////////////////////////
	

	/**
	 * This method invokes the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
    @WebMethod	
	 public void initializeBD(){
		dbManager.open(false);
		dbManager.initializeDB();
		dbManager.close();
	}

}

