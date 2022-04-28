package data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Questions {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private String question_id;
	private String question;

	
	public Questions() {
	}

	
	public Questions(String question) {
		
		this.setQuestion(question);
	}

	public Questions(String question, String question_id) {
		
		this.setQuestion(question);
		this.question_id=question_id;
	}

	
	
	
	

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}
	
	
	public String getId() {
		return question_id;
	}
	public void setId(String id) {
		this.question_id = id;
	}
	
}