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
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
		//获取当前时间
		java.util.Date dt = new java.util.Date();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String current = sdf.format(dt);
		
		List<Favorite> flist=favoriteDao.QueryByUidAndRid(uid, rid);
		if(flist.size()>0){
			if(flist.get(0).getIsValid().equals("1")){
				Favorite favorite=flist.get(0);
				favorite.setIsValid("0");
				favorite.setTime(current);
				favoriteDao.update(favorite);
				return "cancle";
			}else{
				Favorite favorite=flist.get(0);
				favorite.setIsValid("1");
				favorite.setTime(current);
				favoriteDao.update(favorite);
				return "add";
			}
		}else{
			Favorite favorite=new Favorite(uid,rid,current,"1");
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
	 * 获取发布及关注数
	 */
	public String getUserUp(int uid){
		JSONObject jo_up=new JSONObject();
		User user=userDao.get(uid);
		List<Recipe> rlist=recipeDao.queryByUid(uid);
		List<Concern> clist=concernDao.queryByCid(uid);
		jo_up.put("username", user.getUsername());
		jo_up.put("head", user.getHead());
		jo_up.put("conNum", clist.size());
		jo_up.put("recNum", rlist.size());
		//获取具体发布的菜谱
		JSONArray ja=new JSONArray();
		for(int i=0;i<rlist.size();i++){
			JSONObject jo = new JSONObject();
			jo.put("rname", rlist.get(i).getRname());
			jo.put("pic", rlist.get(i).getPic());
			ja.add(jo);
		}
		jo_up.put("ups", ja.toString());
		return jo_up.toString();
	}
}
