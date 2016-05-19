package domain;

public class Favorite {
    
	private int id;
    
	private int uid;
    
	private int rid;
   
	private String time;
	
	private String isValid;
    
	public Favorite(){
		super();
	}
	
	public Favorite(int uid, int rid, String time, String isValid) {
		super();
		this.uid = uid;
		this.rid = rid;
		this.time = time;
		this.isValid = isValid;
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

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}


	public int getRid() {
		return rid;
	}


	public void setRid(int rid) {
		this.rid = rid;
	}


	public String getIsValid() {
		return isValid;
	}


	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
}
