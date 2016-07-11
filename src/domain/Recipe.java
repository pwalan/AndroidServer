package domain;

public class Recipe {
	private int rid;
	private int uid;
	private String season;
	private String cuisine;
	private String info;
	private String rname;
	private String uptime;
	private String pic;
	private int aid;
	private String isAudit;
	private int auditResult;
	private String auditTime;
	private String content;
	
	public Recipe() {
		super();
	}
	
	public Recipe(int uid, String season, String cuisine, String info, String rname, String uptime, String pic,
			int aid,String isAudit) {
		super();
		this.uid = uid;
		this.season = season;
		this.cuisine = cuisine;
		this.info = info;
		this.rname = rname;
		this.uptime = uptime;
		this.pic = pic;
		this.aid=aid;
		this.isAudit = isAudit;
	}



	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getSeason() {
		return season;
	}
	public void setSeason(String season) {
		this.season = season;
	}
	public String getCuisine() {
		return cuisine;
	}
	public void setCuisine(String cuisine) {
		this.cuisine = cuisine;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getRname() {
		return rname;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}
	public String getUptime() {
		return uptime;
	}
	public void setUptime(String uptime) {
		this.uptime = uptime;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public String getisAudit() {
		return isAudit;
	}
	public void setisAudit(String isAudit) {
		this.isAudit = isAudit;
	}
	public int getAuditResult() {
		return auditResult;
	}
	public void setAuditResult(int auditResult) {
		this.auditResult = auditResult;
	}
	public String getAuditTime() {
		return auditTime;
	}
	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	
}
