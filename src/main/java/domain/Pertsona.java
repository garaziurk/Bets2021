package domain;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlSeeAlso ({Administratzailea.class, PertsonaErregistratua.class})
@Entity
public class Pertsona implements Serializable{

	private String izena;
	private String abizena1;
	private String abizena2;
	private String emaila;
	@XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@Id
	private Integer NanZbkia;
	private String NanLetra;
	private String pasahitza;
	
	public Pertsona(String iz, String ab1, String ab2, String email, int nZ, String nL, String pasahitz) {
		this.izena = iz;
		this.abizena1 = ab1;
		this.abizena2 = ab2;
		this.emaila = email;
		this.NanZbkia = nZ;
		this.NanLetra = nL;
		this.pasahitza = pasahitz;
	}
	
	public Pertsona() {
		super();
	}
	
	//Getterrak eta setterrak:
	public String getIzena() {
		return izena;
	}
	public void setIzena(String izena) {
		this.izena = izena;
	}
	public String getAbizena1() {
		return abizena1;
	}
	public void setAbizena1(String abizena1) {
		this.abizena1 = abizena1;
	}
	public String getAbizena2() {
		return abizena2;
	}
	public void setAbizena2(String abizena2) {
		this.abizena2 = abizena2;
	}
	public String getEmaila() {
		return emaila;
	}
	public void setEmaila(String emaila) {
		this.emaila = emaila;
	}
	public int getNanZbkia() {
		return NanZbkia;
	}
	public void setNanZbkia(int nanZbkia) {
		NanZbkia = nanZbkia;
	}
	public String getNanLetra() {
		return NanLetra;
	}
	public void setNanLetra(String nanLetra) {
		NanLetra = nanLetra;
	}
	public String getPasahitza() {
		return pasahitza;
	}
	public void setPasahitza(String pass) {
		pasahitza = pass;
	}
	
	
}
