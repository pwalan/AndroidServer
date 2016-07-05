package service;

import java.util.List;

import dao.CommentDao;
import dao.ConcernDao;
import dao.FavoriteDao;
import dao.RecipeDao;
import dao.SquestionDao;
import dao.UserDao;
import domain.Concern;
import domain.Favorite;
import domain.Recipe;
import domain.Squestion;
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
	private SquestionDao squestionDao;

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

	public void setSquestionDao(SquestionDao squestionDao) {
		this.squestionDao = squestionDao;
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
	public boolean Register(String username, String passwd, String head,String gender, String age, String city,
			String salary, String taste, String question, String answer){
		List<User> userlist=userDao.queryByUserName(username);
		if(userlist.size()>0){
			return false;
		}else{
			userDao.save(new User(username, passwd, 1, head,gender,age,city,salary,taste));
			userlist=userDao.queryByUserName(username);
			squestionDao.save(new Squestion(userlist.get(0).getUid(),question,answer));
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
	
	/**
	 * 修改密码
	 * @param uid
	 * @param passwd
	 * @return
	 */
	public String changePasswd(int uid, String passwd){
		User user=userDao.get(uid);
		user.setPasswd(passwd);
		userDao.update(user);
		return "changed";
	}
	
	/**
	 * 更换头像
	 * @param uid
	 * @param head
	 * @return
	 */
	public String changeHead(int uid, String head){
		User user=userDao.get(uid);
		user.setHead(head);
		//http://pwalan-10035979.image.myqcloud.com/test_fileId_4fa53a5e-dea0-4cc4-be57-12fbf5f0e77d
		userDao.update(user);
		return "changed";
	}
	
	/**
	 * 得到密保问题
	 * @param username
	 * @return
	 */
	public String getSquestion(String username){
		JSONObject jo=new JSONObject();
		List<User> userlist=userDao.queryByUserName(username);
		if(userlist.size()>0){
			List<Squestion> slist=squestionDao.queryByUid(userlist.get(0).getUid());
			if(slist.size()>0){
				jo.put("question", slist.get(0).getQuestion());
				return jo.toString();
			}
		}
		jo.put("question", "failed");
		return jo.toString();
	}
	
	/**
	 * 找回密码
	 * @param uid
	 * @param answer
	 * @return
	 */
	public String findPasswd(String username, String answer){
		List<User> userlist=userDao.queryByUserName(username);
		if(userlist.size()>0){
			List<Squestion> slist=squestionDao.queryByUid(userlist.get(0).getUid());
			if(slist.size()>0&&slist.get(0).getAnswer().equals(answer)){
				return userlist.get(0).getPasswd();
			}
		}
		return "failed";
	}
}
