package data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity

public class Questions {

	/**
	 * 
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int question_id;
	private String question;

	public Questions() {
	}
	public Questions(String question) {
		this.question = question; 
	}

	public Questions(int question_id, String question) {
		this.question_id = question_id;
		this.question = question; 
	}

	public Questions(String question, String question_id) {

		this.setQuestion_id(question_id);
		this.question = question;
	}

	public int getQuestion_id() {
		return question_id;
	}

	public void setQuestion_id(int question_id) {
		this.question_id = question_id;
	}
	public void setQuestion_id(String question_id) {
		try {
			this.question_id = Integer.parseInt(question_id);
		}
		catch (NumberFormatException | NullPointerException e) {
			//Do nothing - the value is not changed
		}
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

}