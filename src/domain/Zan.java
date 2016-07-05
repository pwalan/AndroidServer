package domain;

/**
 * 点赞信息
 * @author AlanP
 *
 */
public class Zan {
	int id;
	int uid;
	int rid;
	String time;

	public Zan() {
		super();
	}
	
	public Zan(int uid, int rid, String time) {
		super();
		this.uid = uid;
		this.rid = rid;
		this.time = time;
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
