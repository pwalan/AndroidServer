package service;

import java.util.List;

import dao.CommentDao;
import dao.ConcernDao;
import dao.FavoriteDao;
import dao.RecipeDao;
import dao.UserDao;
import domain.Concern;
import domain.Favorite;
import domain.Recipe;
import domain.User;
import domain.Comment;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import utils.Time;

/**
 * 用户服务
 * @author AlanP
 *
 */
public class UserService {
	private UserDao userDao;
	private CommentDao commentDao;
	private ConcernDao concernDao;
	private FavoriteDao favoriteDao;
	private RecipeDao recipeDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void setCommentDao(CommentDao commentDao) {
		this.commentDao = commentDao;
	}

	public void setConcernDao(ConcernDao concernDao) {
		this.concernDao = concernDao;
	}

	public void setFavoriteDao(FavoriteDao favoriteDao) {
		this.favoriteDao = favoriteDao;
	}

	public void setRecipeDao(RecipeDao recipeDao) {
		this.recipeDao = recipeDao;
	}

	/**
	 * 登录
	 * @param username
	 * @param passwd
	 * @return
	 */
	public User Login(String username, String passwd){
		List<User> userlist=userDao.queryByUserName(username);
		User user=new User();
		if(userlist==null){
			return user;
		}else{
			user=userlist.get(0);
			if(user.getPasswd().equals(passwd)){
				return user;
			}
		}
		return user;
	}
	
	/**
	 * 注册
	 * @param username
	 * @param passwd
	 * @param head
	 * @return
	 */
	public boolean Register(String username, String passwd, String head){
		List<User> userlist=userDao.queryByUserName(username);
		if(userlist.size()>0){
			return false;
		}else{
			userDao.save(new User(username, passwd, 1, head));
			return true;
		}
	}
	
	/**
	 * 添加/取消收藏
	 * 
	 */
	public String addFavorite(int uid, int rid){
		List<Favorite> flist=favoriteDao.QueryByUidAndRid(uid, rid);
		if(flist.size()>0){
			Favorite favorite=flist.get(0);
			if(favorite.getIsValid().equals("1")){
				favorite.setIsValid("0");
				favorite.setTime(Time.getNow());
				favoriteDao.update(favorite);
				return "cancle";
			}else{
				favorite=flist.get(0);
				favorite.setIsValid("1");
				favorite.setTime(Time.getNow());
				favoriteDao.update(favorite);
				return "add";
			}
		}else{
			Favorite favorite=new Favorite(uid,rid,Time.getNow(),"1");
			favoriteDao.save(favorite);
			return "add";
		}
	}
	
	/**
	 * 获取有效收藏
	 */
	public String getFavorites(int uid){
		List<Favorite> flist=favoriteDao.QueryByUid(uid);
		Recipe recipe=new Recipe();
		JSONArray ja_favorite=new JSONArray();
		for(int i=0;i<flist.size();i++){
			JSONObject jo = new JSONObject();
			if(flist.get(i).getIsValid().equals("1")){
				recipe=recipeDao.get(flist.get(i).getRid());
				jo.put("rname", recipe.getRname());
				jo.put("time", flist.get(i).getTime());
				jo.put("pic", recipe.getPic());
				ja_favorite.add(jo);
			}
		}
		return ja_favorite.toString();
		
	}
	
	/**
	 * 添加/取消关注
	 */
	public String addConcern(int uid,int cid){
		List<Concern> clist=concernDao.queryByUidAndCid(uid,cid);
		if(clist.size()>0){
			Concern concern=clist.get(0);
			if(concern.getIsValid().equals("1")){
				concern.setIsValid("0");
				concern.setTime(Time.getNow());
				concernDao.update(concern);
				return "cancle";
			}else{
				concern.setIsValid("1");
				concern.setTime(Time.getNow());
				concernDao.update(concern);
				return "add";
			}
		}else{
			Concern concern=new Concern(uid,cid,Time.getNow(),"1");
			concernDao.save(concern);
			return "add";
		}
	}
	
	/**
	 * 获取有效关注
	 */
	public String getConcern(int uid){
		List<Concern> clist=concernDao.queryByUid(uid);
		JSONArray ja=new JSONArray();
		for(int i=0;i<clist.size();i++){
			JSONObject jo = new JSONObject();
			Concern concern=clist.get(i);
			if(concern.getIsValid().equals("1")){
				User user=userDao.get(concern.getCid());
				jo.put("cid", user.getUid());
				jo.put("cname", user.getUsername());
				jo.put("time", concern.getTime());
				jo.put("head", user.getHead());
				ja.add(jo);
			}
		}
		return ja.toString();
	}
	
	/**
	 * 获取用户一些信息（关注数、菜谱数、已发布的菜谱）
	 */
	public String getUserUp(int uid){
		JSONObject jo_up=new JSONObject();
		User user=userDao.get(uid);
		int conNum=0;
		List<Recipe> rlist=recipeDao.queryByUid(uid);
		List<Concern> clist=concernDao.queryByCid(uid);
		jo_up.put("username", user.getUsername());
		jo_up.put("head", user.getHead());
		for(int i=0;i<clist.size();i++){
			if(clist.get(i).getIsValid().equals("1")){
				conNum++;
			}
		}
		jo_up.put("conNum", conNum);
		jo_up.put("recNum", rlist.size());
		//获取具体发布的菜谱
		JSONArray ja=new JSONArray();
		for(int i=0;i<rlist.size();i++){
			JSONObject jo = new JSONObject();
			jo.put("rname", rlist.get(i).getRname());
			jo.put("pic", rlist.get(i).getPic());
			jo.put("time",rlist.get(i).getUptime());
			ja.add(jo);
		}
		jo_up.put("ups", ja.toString());
		return jo_up.toString();
	}
	
}
