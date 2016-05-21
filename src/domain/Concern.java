package domain;

public class Concern {
	private int id;

	private int uid;

	private int cid;

	private String time;

	private String isValid;

	public Concern() {
		super();
	}

	public Concern(int uid, int cid, String time, String isValid) {
		super();
		this.uid = uid;
		this.cid = cid;
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

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

}
