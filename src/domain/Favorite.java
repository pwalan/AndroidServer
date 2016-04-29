package domain;

public class Favorite {
    
	private int id;
    
	private int uid;
    
	private int rid;
    
	private String time;
    
	public Favorite(){
		super();
	}
	public Favorite(int id,int uid,int rid,String time){
		super();
		this.setId(rid);
		this.setRid(rid);
		this.setTime(time);
		this.setUid(uid);
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
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
}
