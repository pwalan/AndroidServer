package domain;

public class Recommended {
	private int recID;
	private int uid;
	private int rid;
	private int validity;
	public Recommended(){
		super();
	}
	public Recommended(int uid,int rid,int validity){
		super();
		this.uid=uid;
		this.rid=rid;
		this.validity=validity;
	}
	public int getRecID() {
		return recID;
	}
	public void setRecID(int recID) {
		this.recID = recID;
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
	public int getValidity() {
		return validity;
	}
	public void setValidity(int validity) {
		this.validity = validity;
	}
	
}
