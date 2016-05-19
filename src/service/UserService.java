package service;

import java.util.List;

import dao.CommentDao;
import dao.ConcernDao;
import dao.FavoriteDao;
import dao.RecipeDao;
import dao.UserDao;
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
	 * 添加收藏
	 * 
	 */
	public String addFavorite(int uid, int rid){
		List<Favorite> flist=favoriteDao.QueryByUidAndRid(uid, rid);
		return "add";
	}
	
	/**
	 * 获取有效收藏
	 */
	public String getFarovites(int uid){
		List<Favorite> flist=favoriteDao.QueryByUid(uid);
		Recipe recipe=new Recipe();
		JSONArray ja_favorite=new JSONArray();
		for(int i=0;i<flist.size();i++){
			JSONObject jo = new JSONObject();
			if(flist.get(i).getIsValid().equals("1")){
				recipe=recipeDao.get(flist.get(i).getRid());
				jo.put("rname", recipe.getRname());
				jo.put("pic", recipe.getPic());
				ja_favorite.add(jo);
			}
		}
		return ja_favorite.toString();
		
	}
	
}
