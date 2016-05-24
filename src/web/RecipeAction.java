package web;

import com.opensymphony.xwork2.ActionSupport;

import service.RecipeService;

public class RecipeAction extends ActionSupport{
	private int rid;
	private int uid;
	private String rname;
	private String season;
	private String data;
	private RecipeService recipeService;
	private String comment;
	
	public void setRecipeService(RecipeService recipeService) {
		this.recipeService = recipeService;
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public String getRname() {
		return rname;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getSeason() {
		return season;
	}
	public void setSeason(String season) {
		this.season = season;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	/**
	 * 获取菜谱制作步骤和相关评论
	 */
	public String getSteps(){
		setRname(getRname());
		setData(recipeService.getSteps(rname));
		return SUCCESS;
	}
	
	/**
	 * 获取首页推荐
	 */
	public String getHomeRecipes(){
		setData(recipeService.getHomeRecipes());
		return SUCCESS;
	}
	
	/**
	 * 获取季节菜谱
	 */
	public String getSeasonRecipes(){
		setSeason(getSeason());
		setData(recipeService.getSeasonRecipes(season));
		return SUCCESS;
	}
	
	/**
	 * 发表评论
	 */
	public String makeComment(){
		setUid(getUid());
		setRid(getRid());
		setComment(getComment());
		setData(recipeService.makeComment(uid, rid, comment));
		return SUCCESS;
	}
}
