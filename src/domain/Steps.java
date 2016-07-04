package domain;

public class Steps {
	private int rid;
	
	private int id ;
	
	private int num;
	
	private String pic;
	
	private String content;
	
	public Steps(){
		super();
	}
	public Steps(int rid,int num,String pic,String content){
		super();
		this.setContent(content);
		this.setNum(num);
		this.setPic(pic);
		this.setRid(rid);
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

}
