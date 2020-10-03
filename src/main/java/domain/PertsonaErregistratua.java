package domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlIDREF;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class PertsonaErregistratua extends Pertsona implements Serializable{
	
	private float irabaziak;
	private float diruKantitatea;
	private Date jaiotzeData;
	@XmlIDREF
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private Vector<Apustua> apustuak=new Vector<Apustua>();

	public PertsonaErregistratua(String iz, String ab1, String ab2, String email,int nZ, String nL, String pasahitz, float dirua, Date jaioD) {
		super(iz, ab1, ab2, email, nZ, nL, pasahitz);
		this.diruKantitatea = dirua;
		this.jaiotzeData = jaioD;
		this.irabaziak = 0;
	}
	
	public PertsonaErregistratua() {
		super();
	}

	// Getterrak eta setterrak:
	public float getDiruKantitatea() {
		return diruKantitatea;
	}

	public void setDiruKantitatea(float dirua) {
		this.diruKantitatea = dirua;
	}

	public Date getJaiotzeData() {
		return jaiotzeData;
	}

	public void setJaiotzeData(Date jaiotzeData) {
		this.jaiotzeData = jaiotzeData;
	}
	
	public float getIrabaziak() {
		return irabaziak;
	}
	
	public void setIrabaziak(float irabaziak) {
		this.irabaziak = irabaziak;
	}
	
	public Vector<Apustua> getApustuak(){
		return apustuak;
	}
	
	//BEHARREZKO METODOAK:
	public float addDirua(float dirua) {
		return this.diruKantitatea = this.diruKantitatea + dirua;
	}
	
	public Apustua addApustua(Apustua a) {
		apustuak.add(a);
		return a;
	}

	@Override
	public String toString() {
		return this.getIzena() +" "+ this.getAbizena1() +" "+ this.getAbizena2(); 
	}
}
