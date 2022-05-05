package data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CandidateAnswers {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)   // Tablen nimi "Answers"
	private int answerId;
	private int candidateId;
	private int questionId;
	private int answerint;
	private String answerstring;
	
	public CandidateAnswers() {
	}
	
	public CandidateAnswers(String answerId, String CandidateId, String questionId, String answerint, String answerstring) {
		this.setId(answerId);
		this.setCandidateId(candidateId);
		this.setQuestionId(questionId);
		this.setanswerint(answerint);
		this.answerstring = answerstring;
	}

	public int getId() {
		return answerId;
	}

	public void setId(int answerId) {
		this.answerId = answerId;
	}

	private void setId(String answerId) {

		try {
			this.answerId = Integer.parseInt(answerId);
		} catch (NumberFormatException | NullPointerException e) {
			System.out.println(e.getMessage());
		}

	}

	public int getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(int candidateId) {
		this.candidateId = candidateId;
	}

	public void setCandidateId(String candidateId) {
		try {
			this.candidateId = Integer.parseInt(candidateId);
		} catch (NumberFormatException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public void setQuestionId(String questionId) {
		try {
			this.questionId = Integer.parseInt(questionId);
		} catch (NumberFormatException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}

//	public int answerint() {
//		return answerint;
//	}

	public int getanswerint() {
		return answerint;
	}
	
	public void setanswerint(int answerint) {
		this.answerint = answerint;
	}

	public void setanswerint(String answerint) {
		try {
			this.answerint = Integer.parseInt(answerint);
		} catch (NumberFormatException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}

	public String answerstring() {
		return answerstring;
	}

	public void setanswerstring(String answerstring) {
		this.answerstring = answerstring;

	}

}
