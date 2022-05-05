package data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SingleCandidateAnswers {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int answer_int;
	private String question;
	private String answer_string;
	
	public SingleCandidateAnswers(String answer_int, String question, String answer_string) {
		setAnswer_int(answer_int);
		this.question=question;
	}
	
	public SingleCandidateAnswers() {
	
	}
	
	public int getAnswer_int() {
		return answer_int;
	}
	public void setAnswer_int(int answer_int) {
		this.answer_int = answer_int;
	}
	public void setAnswer_int(String answer_int) {
		try {
			this.answer_int = Integer.parseInt(answer_int);
		}
		catch(NumberFormatException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer_string() {
		return answer_string;
	}

	public void setAnswer_string(String answer_string) {
		this.answer_string = answer_string;
	}
	
	
	
}
