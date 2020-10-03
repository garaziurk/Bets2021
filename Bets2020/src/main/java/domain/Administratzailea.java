package domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Administratzailea extends Pertsona implements Serializable{
	
	public Administratzailea(String iz, String ab1, String ab2, String email, int nZ, String nL, String pasahitz) {
		super(iz, ab1, ab2, email, nZ, nL, pasahitz);
	}
	
	public Administratzailea() {
		super();
	}
}
