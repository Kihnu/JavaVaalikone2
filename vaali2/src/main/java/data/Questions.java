package data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Questions {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private String id;
	private String question;

	
	public Questions() {
	}

	
	public Questions(String question) {
		
		this.setQuestion(question);
	}

	public Questions(String question, String string) {
		
		this.setQuestion(question);
		this.id=string;
	}

	
	
	
	

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}