package domain;

public class Comment {
	private int id;
	
	private int uid;
	
	private int rid;
	
	private String content;
	
	private String time;
	public Comment(){
		super();
	}
	public Comment(int id,int uid,String content,String time){
		super();
		this.id=id;
		this.uid=uid;
		this.content=content;
		this.time=time;
		
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
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
}
