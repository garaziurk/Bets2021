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

	public BLFacadeImplementation()  {		
		System.out.println("Creating BLFacadeImplementation instance");
		ConfigXML c=ConfigXML.getInstance();
		
		if (c.getDataBaseOpenMode().equals("initialize")) {
			DataAccess dbManager=new DataAccess(c.getDataBaseOpenMode().equals("initialize"));
			dbManager.initializeDB();
			dbManager.close();
			}
		
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
	    DataAccess dBManager=new DataAccess();
		Question qry=null;
		
	    
		if(new Date().compareTo(event.getEventDate())>0)
			throw new EventFinished(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished"));
				
		
		 qry=dBManager.createQuestion(event,question,betMinimum);		

		dBManager.close();
		
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
		DataAccess dbManager=new DataAccess();
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
		DataAccess dbManager=new DataAccess();
		Vector<Date>  dates=dbManager.getEventsMonth(date);
		dbManager.close();
		return dates;
	}
	
	////////////////////// GUK EGINDAKO METODOAK ////////////////////////////
	
	//nan zenbakiarekin aurkitzeko
	@WebMethod
	public Pertsona getUserByNan(int nan) {
		DataAccess dBManager=new DataAccess();
		Pertsona per = dBManager.getUserByNan(nan);
		dBManager.close();
		return per;
	}
	
	@WebMethod
	public PertsonaErregistratua getErregistratuaByNan(int nan) {
		DataAccess dBManager=new DataAccess();
		PertsonaErregistratua per = dBManager.getErregistratuaByNan(nan);
		dBManager.close();
		return per;
	}
	
	//datuak dban gordetzeko
	@WebMethod
	public boolean pertsonaErregistratu(String iz, String ab1, String ab2, String email, float dirua, Date jaioD, int nZ, String nL, String pasahitz, String pasahitz2) {
		DataAccess dBManager=new DataAccess();
		boolean erBai = dBManager.pertsonaErregistratu(iz, ab1, ab2, email, dirua, jaioD, nZ, nL, pasahitz, pasahitz2);
		dBManager.close();
		return erBai;
	}
	
	@WebMethod
	public void gertaeraSortu(int gZenb, String gDeskr, Date gData) {
		DataAccess dBManager = new DataAccess();
		dBManager.gertaeraSortu(gZenb, gDeskr, gData);
		dBManager.close();
	}
	
	//Gertaeraren zenbakia lortzeko:
	@WebMethod
	public Event getEventByNumber(int gZenb) {
		DataAccess dBManager=new DataAccess();
		Event ger = dBManager.getEventByNumber(gZenb);
		dBManager.close();
		return ger;
	}
	
	//logeatzeko
	@WebMethod
	public boolean login(int nan, String pasahitza) {
		DataAccess dBManager=new DataAccess();
		boolean per = dBManager.login(nan, pasahitza);
		dBManager.close();
		return per;
	}
	
	@WebMethod
	public Collection<Event> getEventList() {
		DataAccess dBManager=new DataAccess();
		Collection<Event> gertaera = dBManager.getEventList();
		dBManager.close();
		return gertaera;
	}
	
	@WebMethod
	public boolean kuotaGorde(String pronostikoa, float irabaziak, Question galdera) {
		DataAccess dBManager=new DataAccess();
		boolean gorde = dBManager.kuotaGorde(pronostikoa, irabaziak, galdera);
		dBManager.close();
		return gorde;
	}
	
	@WebMethod
	public float updateDirua(int nan, float dirua) {
		DataAccess dBManager=new DataAccess();
		float dir = dBManager.updateDirua(nan, dirua);
		dBManager.close();
		return dir;
	}
	
	@WebMethod
	public List<DiruMugimendua> getDiruMugimenduakByNan(int nan) {
		DataAccess dBManager=new DataAccess();
		List<DiruMugimendua> diruMugimenduak = dBManager.getDiruMugimenduakByNan(nan);
		dBManager.close();
		return diruMugimenduak;
	}
	
	@WebMethod
	public boolean apustuaEgin(Date apustuData, float zenbatDiru, Kuota kuota, int nan) {
		DataAccess dBManager=new DataAccess();
		Boolean apustu = dBManager.apustuaEgin(apustuData,zenbatDiru,kuota,nan);
		dBManager.close();
		return apustu;
	}
	
	@WebMethod
	public List<Apustua> getApustuakByNan(int nan) {
		DataAccess dBManager=new DataAccess();
		List<Apustua> apustuak = dBManager.getApustuakByNan(nan);
		dBManager.close();
		return apustuak;
	}
	
	@WebMethod
	public void apustuaEzabatu(Apustua a, int nan) {
		DataAccess dBManager=new DataAccess();
		dBManager.apustuaEzabatu(a,nan);
		dBManager.close();
	}
	
	@WebMethod
	public void erantzunaGorde(Question g, Kuota k) {
		DataAccess dBManager=new DataAccess();
		dBManager.erantzunaGorde(g,k);
		dBManager.close();
	}
	
	@WebMethod
	public void gertaeraKantzelatu(Event ev) {
		DataAccess dbManager=new DataAccess();
		dbManager.gertaeraKantzelatu(ev);
		dbManager.close();
	}
	
	@WebMethod
	public List<Apustua> getApustuakByEvent(Event ev){
		DataAccess dbManager=new DataAccess();
		List<Apustua> apustuak = dbManager.getApustuakByEvent(ev);
		dbManager.close();
		return apustuak;
	}
	
	@WebMethod
	public List<PertsonaErregistratua> getUsers() {
		DataAccess dbManager=new DataAccess();
		List<PertsonaErregistratua> users = dbManager.getUsers();
		dbManager.close();
		return users;
	}
	
	@WebMethod
	public void ordaindu(int nan, float i) {
		DataAccess dBManager=new DataAccess();
		dBManager.ordaindu(nan,i);
		dBManager.close();
	}
	
	//Containerra:
	@WebMethod
	public List<ApustuContainer> getListApustuContainer(){
		DataAccess dBManager=new DataAccess();
		List<Apustua> listApustuak = dBManager.getApustuak();
		List<ApustuContainer> listAC= new LinkedList<ApustuContainer>();
		for (Apustua a:listApustuak)
			listAC.add(new ApustuContainer(a));
		dBManager.close();
		return listAC;
	}
	
	@WebMethod
	public List<KuotaContainer> getListKuotaContainer(){
		DataAccess dBManager=new DataAccess();
		List<Kuota> listKuotak = dBManager.getKuotak();
		List<KuotaContainer> listKC= new LinkedList<KuotaContainer>();
		for (Kuota a:listKuotak)
			listKC.add(new KuotaContainer(a));
		dBManager.close();
		return listKC;
	}
	
	@WebMethod
	public List<QuestionContainer> getListQuestionContainer(){
		DataAccess dBManager=new DataAccess();
		List<Question> listQuestions = dBManager.getQuestions();
		List<QuestionContainer> listQC= new LinkedList<QuestionContainer>();
		for (Question a:listQuestions)
			listQC.add(new QuestionContainer(a));
		dBManager.close();
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
		DataAccess dBManager=new DataAccess();
		dBManager.initializeDB();
		dBManager.close();
	}

}

