package domain;

public class Ranking {
	private int id;
	
	private int rid;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}
	
	Ranking(int id ,int rid){
		this.id=id;
		this.rid=rid;
	}

	public Ranking() {
		super();
	}
	
}
