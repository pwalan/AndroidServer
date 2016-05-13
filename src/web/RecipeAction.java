package web;

import com.opensymphony.xwork2.ActionSupport;

import service.RecipeService;

public class RecipeAction extends ActionSupport{
	private int rid;
	private String rname;
	private String data;
	private RecipeService recipeService;
	
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
	
	/**
	 * 获取菜谱制作步骤和相关评论
	 */
	public String getSteps(){
		setRname(getRname());
		setData(recipeService.getSteps(rname));
		return SUCCESS;
	}
	
}
