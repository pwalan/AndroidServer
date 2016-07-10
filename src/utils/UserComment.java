package utils;

public class UserComment {
	private String head;
	private String username;
	private String content;
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public UserComment(String head, String username, String content) {
		super();
		this.head = head;
		this.username = username;
		this.content = content;
	}
	
}
