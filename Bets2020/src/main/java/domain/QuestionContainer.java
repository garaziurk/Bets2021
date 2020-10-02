package domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class QuestionContainer {
	
	private Question galdera;
	private Event gertaera;
	
	public QuestionContainer(Question q) {
		this.galdera = q;
		this.gertaera = q.getEvent();
	}
	
	public Event getEvent() {
		return gertaera;
	}
	
	public Question getQuestion() {
		return galdera;
	}
}
