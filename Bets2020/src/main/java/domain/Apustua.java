package domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Apustua implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@XmlID
	@Id
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@GeneratedValue
	private Integer apustuID;
	private Date apustuData;
	private float zenbatDiru;
	//@XmlIDREF
	private PertsonaErregistratua per;
	@XmlIDREF
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private Kuota kuota;
	
	public Apustua() {
		super();
	}
	
	public Apustua(Date apustuData, float zenbatDiru, PertsonaErregistratua p, Kuota kuota) {
		super();
		this.apustuData = apustuData;
		this.zenbatDiru = zenbatDiru;
		this.per = p;
		this.kuota = kuota;
	}
	
	public Date getApustuData() {
		return apustuData;
	}
	public void setApustuData(Date apustuData) {
		this.apustuData = apustuData;
	}
	public float getZenbatDiru() {
		return zenbatDiru;
	}
	public void setZenbatDiru(float zenbatDiru) {
		this.zenbatDiru = zenbatDiru;
	}
	public PertsonaErregistratua getPer() {
		return this.per;
	}
	public void setPer(PertsonaErregistratua p) {
		this.per = p;
	}
	public Kuota getKuota() {
		return kuota;
	}
	public void setKuota(Kuota kuota) {
		this.kuota = kuota;
	}
	public int getApustuID() {
		return apustuID;
	}
	public void setApusutuID(int z) {
		this.apustuID = z;
	}
	@Override
	public String toString() {
		return Integer.toString(apustuID) ;
	}
	
}
