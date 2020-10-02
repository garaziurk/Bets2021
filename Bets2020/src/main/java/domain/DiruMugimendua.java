package domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlIDREF;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class DiruMugimendua implements Serializable {
	private static final long serialVersionUID = 1L;
	private Date diruData;
	private float zenbatDiru;
	@XmlIDREF
	private PertsonaErregistratua per;
	
	public DiruMugimendua() {
		super();
	}
	
	public DiruMugimendua(Date diruData, float zenbatDiru, PertsonaErregistratua p) {
		super();
		this.diruData = diruData;
		this.zenbatDiru = zenbatDiru;
		this.per = p;
	}

	public Date getDiruData() {
		return diruData;
	}

	public void setDiruData(Date diruData) {
		this.diruData = diruData;
	}

	public float getZenbatDiru() {
		return zenbatDiru;
	}

	public void setZenbatDiru(float zenbatDiru) {
		this.zenbatDiru = zenbatDiru;
	}

	public PertsonaErregistratua getPer() {
		return per;
	}

	public void setPer(PertsonaErregistratua per) {
		this.per = per;
	}

	
}
