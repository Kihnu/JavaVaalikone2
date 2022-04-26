package data;

public class AnswersC {
	private int answerId;
	private int candidateId;
	private int questionId;
	private int answerint;
	private String answerstring;

	public AnswersC(String answerId, int candidateId, int questionId, int answerint, String answerstring) {
		setId(answerId);

		setCandidateId(candidateId);
		setQuestionId(questionId);
		setanswerint(answerint);
		this.answerstring = answerstring;
	}

	public AnswersC() {
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
