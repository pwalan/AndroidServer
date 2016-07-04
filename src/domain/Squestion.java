package domain;

public class Squestion {
	private int id;
	private int uid;
	private String question;
	private String answer;
	
	public Squestion() {
		super();
	}
	
	public Squestion(int uid, String question, String answer) {
		super();
		this.uid = uid;
		this.question = question;
		this.answer = answer;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
}
