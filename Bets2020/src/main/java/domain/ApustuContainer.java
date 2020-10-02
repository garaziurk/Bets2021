package domain;
import javax.xml.bind.annotation.XmlAccessType; 
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class ApustuContainer {

	private Apustua apustua;
	private Kuota kuota; 
	
	public ApustuContainer(Apustua a) { 
		this.apustua = a; 
		this.kuota = a.getKuota();
	} 
	public ApustuContainer() { 
		kuota = null;  
		apustua = null;
	}  
	public Kuota getKuota() {
		return kuota;
	}
	
	public Apustua getApustua() {
		return apustua;
	}

	public String toString(){  
		return apustua+"/"+kuota;
	}

} 

