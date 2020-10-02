package domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class KuotaContainer {
	private Kuota kuota; 
	private Question galdera;
	
	public KuotaContainer(Kuota k) { 
		this.kuota = k;
		this.galdera = k.getGaldera();
	} 
	public KuotaContainer() { 
		kuota = null; 
		galdera = null;
	}  
	public Question getGaldera() {
		return galdera;
	}
	
	public Kuota getKuota() {
		return kuota;
	}
}
