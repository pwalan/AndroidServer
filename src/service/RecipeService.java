package service;

import java.util.List;

import dao.CommentDao;
import dao.RecipeDao;
import dao.StepsDao;
import dao.UserDao;
import domain.Comment;
import domain.Recipe;
import domain.Steps;
import domain.User;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import utils.Time;

/**
 * 菜谱相关操作服务
 * @author AlanP
 *
 */
public class RecipeService {
	
	private RecipeDao recipeDao;
	private StepsDao stepsDao;
	private UserDao userDao;
	private CommentDao commentDao;

	public void setRecipeDao(RecipeDao recipeDao) {
		this.recipeDao = recipeDao;
	}

	public void setStepsDao(StepsDao stepsDao) {
		this.stepsDao = stepsDao;
	}	

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void setCommentDao(CommentDao commentDao) {
		this.commentDao = commentDao;
	}

	/**
	 * 获取菜谱详情及相关评论
	 * @param rname
	 * @return
	 */
	public String getSteps(String rname){
		System.out.println(rname);
		JSONObject jo_details=new JSONObject();
		
		List<Recipe> list_recipe=recipeDao.queryByRName(rname);
		System.out.println("list_recipe size: "+list_recipe.size());
		if(list_recipe==null||list_recipe.size()==0){
			return jo_details.toString();
		}
		Recipe recipe=list_recipe.get(0);
		List<Steps> list_steps=stepsDao.queryByRid(recipe.getRid());
		List<Comment> list_comments=commentDao.queryByRid(recipe.getRid());
		User user=userDao.queryByUid(recipe.getUid()).get(0);
		//获取菜谱制作步骤
		JSONArray ja_steps=new JSONArray();
		for(int i=0;i<list_steps.size();i++){
			JSONObject jo = new JSONObject();
			jo.put("num", list_steps.get(i).getNum());
			jo.put("content", list_steps.get(i).getContent());
			jo.put("pic", list_steps.get(i).getPic());
			
			ja_steps.add(jo);
		}
		//获取菜谱评论
		JSONArray ja_comments=new JSONArray();
		for(int i=0;i<list_comments.size();i++){
			JSONObject jo = new JSONObject();
			User cuser=userDao.get(list_comments.get(i).getUid());
			jo.put("cname", cuser.getUsername());
			jo.put("head", cuser.getHead());
			jo.put("time", list_comments.get(i).getTime());
			jo.put("content", list_comments.get(i).getContent());
			ja_comments.add(jo);
		}
		
		//获取菜谱详情
		jo_details.put("rid", recipe.getRid());
		jo_details.put("rpic", recipe.getPic());
		jo_details.put("head", user.getHead());
		jo_details.put("uid", user.getUid());
		jo_details.put("username", user.getUsername());
		jo_details.put("info", recipe.getInfo());
		jo_details.put("steps", ja_steps.toString());
		jo_details.put("comments", ja_comments.toString());
		
		return jo_details.toString();
	}
	
	/**
	 * 获取首页推荐菜谱
	 */
	public String getHomeRecipes(){
		List<Recipe> rlist=recipeDao.queryAll();
		JSONArray ja=new JSONArray();
		for(int i=0;i<rlist.size();i++){
			JSONObject jo = new JSONObject();
			Recipe recipe=rlist.get(i);
			jo.put("rname", recipe.getRname());
			jo.put("pic", recipe.getPic());
			ja.add(jo);
		}
		return ja.toString();
	}
	
	/**
	 * 获取季节菜谱
	 */
	public String getSeasonRecipes(String season){
		List<Recipe> rlist=recipeDao.queryBySeason(season);
		JSONArray ja=new JSONArray();
		for(int i=0;i<rlist.size();i++){
			JSONObject jo = new JSONObject();
			Recipe recipe=rlist.get(i);
			jo.put("rname", recipe.getRname());
			jo.put("pic", recipe.getPic());
			ja.add(jo);
		}
		return ja.toString();
	}
	
	/**
	 * 发表评论
	 */
	public String makeComment(int uid, int rid, String comment){
		commentDao.save(new Comment(uid,rid,comment,Time.getNow(),"1"));
		return "add";
	}
}
