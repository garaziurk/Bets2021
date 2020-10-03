package domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Kuota implements Serializable {
	
	@XmlID
	@Id 
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@GeneratedValue
	private Integer kuotaNumber;
	private String pronostikoa;
	private float irabaziak;
	@XmlIDREF
	private Question galdera;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private Vector<Apustua> apustuak=new Vector<Apustua>();
	
	
	public Kuota(){
		super();
	}
	
	
	public Kuota(String pro, float irab,  Question gald) {
		super();
		this.pronostikoa = pro;
		this.irabaziak = irab;
		this.galdera = gald;
	}

	public Kuota(Integer kn, String pro, float irab,  Question gald) {
		super();
		this.kuotaNumber = kn;
		this.pronostikoa = pro;
		this.irabaziak = irab;
		this.galdera = gald;
	}
	
	// Getterrak eta setterrak:
	public String getPronostikoa() {
		return pronostikoa;
	}
	public void setPronostikoa(String pronostikoa) {
		this.pronostikoa = pronostikoa;
	}
	public float getIrabaziak() {
		return irabaziak;
	}
	public void setIrabaziak(float irabaziak) {
		this.irabaziak = irabaziak;
	}

	public Question getGaldera() {
		return galdera;
	}
	public void setGaldera(Question galdera) {
		this.galdera = galdera;
	}
	
	public Vector<Apustua> getApustuak() {
		return apustuak;
	}

	public void setApustuak(Vector<Apustua> apustuak) {
		this.apustuak = apustuak;
	}
	public int getKuotaNumber() {
		return this.kuotaNumber;
	}
	public void setKuotaNumber(int kn) {
		this.kuotaNumber=kn;
	}
	
	public String toString(){
		return pronostikoa+";"+Float.toString(irabaziak);
	}
	
	public Apustua addApustua(Apustua a)  {
        apustuak.add(a);
        return a;
	}
	public void removeApustua(Apustua a) {
		apustuak.remove(a);
	}
}
