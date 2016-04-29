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
	private boolean isAudit;
	private boolean auditResult;
	private String auditTime;
	public Recipe(){
		super();
	}
	public Recipe(int rid,int uid,String season,String cuisine,String rname,
			String info,String uptime,String pic,int aid,boolean isAudit,boolean auditResult,String auditTime){
		super();
		this.setRid(rid);
		this.setUid(uid);
		this.setSeason(season);
		this.setCuisine(cuisine);
		this.setRname(rname);
		this.setPic(pic);
		this.setInfo(info);
		this.setUptime(uptime);
		this.setAudit(isAudit);
		this.setAid(aid);
		this.setAuditResult(auditResult);
		this.setAuditTime(auditTime);
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public String getSeason() {
		return season;
	}
	public void setSeason(String season) {
		this.season = season;
	}
	public String getRname() {
		return rname;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public boolean isAudit() {
		return isAudit;
	}
	public void setAudit(boolean isAudit) {
		this.isAudit = isAudit;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getCuisine() {
		return cuisine;
	}
	public void setCuisine(String cuisine) {
		this.cuisine = cuisine;
	}
	public String getUptime() {
		return uptime;
	}
	public void setUptime(String uptime) {
		this.uptime = uptime;
	}
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public boolean isAuditResult() {
		return auditResult;
	}
	public void setAuditResult(boolean auditResult) {
		this.auditResult = auditResult;
	}
	public String getAuditTime() {
		return auditTime;
	}
	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime;
	}
}
