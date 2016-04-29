package domain;

public class Concern {
	 private int id;
	   
	 private int uid;
	   
	 private int cid;
	   
	 private String time;
	 
	 public Concern(){
		 super();
	 }
	 public Concern(int id, int uid,int cid,String time ) {
			super();
			this.id = id;
			this.uid = uid;
			this.cid=cid;
			this.time=time;
		}
	 public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}public int getUid() {
			return uid;
		}

		public void setUid(int uid) {
			this.uid = uid;
		}public int getCid() {
			return cid;
		}

		public void setCid(int cid) {
			this.cid = cid;
		}public String getTime() {
			return time;
		}

		public void setTime(String time) {
			this.time = time;
		}
}
