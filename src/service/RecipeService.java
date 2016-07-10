package service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.text.ParseException;

import dao.CommentDao;
import dao.ConcernDao;
import dao.FavoriteDao;
import dao.RankingDao;
import dao.RecipeDao;
import dao.RecommendedDao;
import dao.StepsDao;
import dao.UserDao;
import dao.ZanDao;
import domain.Comment;
import domain.Concern;
import domain.Favorite;
import domain.Ranking;
import domain.Recipe;
import domain.Recommended;
import domain.Steps;
import domain.User;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import utils.Time;

/**
 * 菜谱相关操作服务
 * 
 * @author AlanP
 *
 */
public class RecipeService {
	private static String date="2016-07-06 13:31:40";// 点赞排行榜上次更新时间
	private static int TOPlimit=10;					//排行榜菜谱数量限制
	private static int MaxRecipe=10000;				//假设菜谱量的上限是10000
	private RecipeDao recipeDao;
	private StepsDao stepsDao;
	private UserDao userDao;
	private CommentDao commentDao;
	private ConcernDao concernDao;
	private FavoriteDao favoriteDao;
	private ZanDao zanDao;
	private RecommendedDao recommendedDao;
	private RankingDao rankingDao;

	public void setRankingDao(RankingDao rankingDao) {
		this.rankingDao = rankingDao;
	}

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

	public void setConcernDao(ConcernDao concernDao) {
		this.concernDao = concernDao;
	}

	public void setZanDao(ZanDao zanDao) {
		this.zanDao = zanDao;
	}

	public void setFavoriteDao(FavoriteDao favoriteDao) {
		this.favoriteDao = favoriteDao;
	}

	public void setRecommendedDao(RecommendedDao recommendedDao) {
		this.recommendedDao = recommendedDao;
	}

	/**
	 * 获取菜谱详情及相关评论
	 * 
	 * @param rname
	 * @param uid
	 * @return
	 */
	public String getSteps(String rname, int uid) {
		JSONObject jo_details = new JSONObject();

		List<Recipe> list_recipe = recipeDao.queryByRNameAndUid(rname, uid);
		System.out.println("list_recipe size: " + list_recipe.size());
		if (list_recipe == null || list_recipe.size() == 0) {
			return jo_details.toString();
		}
		Recipe recipe = list_recipe.get(0);
		List<Steps> list_steps = stepsDao.queryByRid(recipe.getRid());
		List<Comment> list_comments = commentDao.queryByRid(recipe.getRid());
		User user = userDao.queryByUid(recipe.getUid()).get(0);
		// 获取菜谱制作步骤
		JSONArray ja_steps = new JSONArray();
		for (int i = 0; i < list_steps.size(); i++) {
			JSONObject jo = new JSONObject();
			jo.put("num", list_steps.get(i).getNum());
			jo.put("content", list_steps.get(i).getContent());
			jo.put("pic", list_steps.get(i).getPic());

			ja_steps.add(jo);
		}
		// 获取菜谱评论
		JSONArray ja_comments = new JSONArray();
		for (int i = 0; i < list_comments.size(); i++) {
			JSONObject jo = new JSONObject();
			User cuser = userDao.get(list_comments.get(i).getUid());
			jo.put("cname", cuser.getUsername());
			jo.put("head", cuser.getHead());
			jo.put("time", list_comments.get(i).getTime());
			jo.put("content", list_comments.get(i).getContent());
			ja_comments.add(jo);
		}

		// 获取菜谱详情
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

	/**************************首页推荐*********************************/
	public String getSeason(){
		String time=Time.getNow();
		int month=(time.charAt(5)-'0')*10+(time.charAt(6)-'0');
		if ((month>=3)&&(month<=5))
				return"春";
		else if((month>=3)&&(month<=5))
			return "夏";
		else if ((month>=3)&&(month<=5))
			return "秋";
		else return "冬";
	}
	/**
	 * 获取首页推荐菜谱
	 */
	public String getHomeRecipes(int uid){
		if (uid>0){//若此用户已注册
			List<User> ulist = userDao.queryByUid(uid);
			if (ulist.size() <= 0)	return getHomeRecipes0();
			User user = ulist.get(0);
			if ((user.getAge()!=null)&&(user.getCity()!=null)&&(user.getGender()!=null)&&(user.getSalary()!=null)&&(user.getTaste()!=null))
				return getHomeRecipes2(uid);
			else return getHomeRecipes1(uid,getSeason());
		}else{	//此用户为游客
			return getHomeRecipes0();
		}
	}
	
	// 检测是否需要更新排行榜，如果需要，则进行更新
	public void updateRanking(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String now=new Time().getNow();
		try {
			 Date nowdate=df.parse(now);
			 Date lastdate=df.parse(date);
			 long l=nowdate.getTime()-lastdate.getTime();
			 long day=l/(24*60*60*1000);
			 if (day<=0) return ;
			 
			 //统计各菜谱点赞数
			 int[] arr=new int[MaxRecipe];
			 int[] ridarr=new int[MaxRecipe];
			 List<Recipe> rlist=recipeDao.Audit_queryAll();
			 for (int i=0;i<rlist.size();i++){
				 Recipe recipe=rlist.get(i);
				 int rid=recipe.getRid();
				 int a=zanDao.queryByRid(rid).size();
				 arr[i]=a;
				 ridarr[i]=rid;
			 }
			 //选择排序
			 int max=rlist.size();
			 int x;
			 for (int i=1;i<=TOPlimit;i++){
				 x=0;
				 for (int j=1;j<max;j++){
					 if (arr[x]<arr[j])  x=j;
				 }
				 Ranking ranking=rankingDao.queryById(i).get(0);
				 ranking.setRid(ridarr[x]);
				 rankingDao.update(ranking);//将此菜放入排行榜
				 arr[x]=-1;	//因为此菜已经添加入排行榜，将其点赞数设为0，防止再次放入
			 }
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//推荐点赞排行榜的TOP10
	public String getHomeRecipes0() {
		
		updateRanking();// 检测是否需要更新排行榜，如果需要，则进行更新
		JSONArray ja = new JSONArray();
		
		for (int i=1;i<=TOPlimit;i++){
			List<Ranking> ranklist=rankingDao.queryById(i);
			if (ranklist.size()>0){
				Ranking ranking=ranklist.get(0);
				List<Recipe> rlist = recipeDao.Audit_queryByRid(ranking.getRid());
				JSONObject jo = new JSONObject();
				Recipe recipe = rlist.get(0);
				jo.put("rname", recipe.getRname());
				jo.put("pic", recipe.getPic());
				jo.put("uid", recipe.getUid());
				ja.add(jo);
			}else break;
					
		}
		return ja.toString();
	}

	/**
	 * 主页推荐思路： 1.关注推荐 1.1获取用户的关注列表 1.2获取关注人发布的菜谱 1.3取出最新菜谱（如果有），并记录下菜ID
	 * 1.4控制所取菜谱数<=5 2.收藏推荐 2.1取出用户收藏的菜谱的发布人的最新菜谱，且与已记录的菜ID比较，防止重复 3.季节推荐
	 * 3.1取出符合季节要求的菜谱，在保证不重复的前提下。
	 */
	public String getHomeRecipes1(int uid, String season) {// season用于季节推荐
		JSONArray ja = new JSONArray(); // 用于返回json结果
		// 关注推荐部分

		List<Concern> clist = concernDao.queryByUid(uid);

		int[] rec = new int[10];// 记录已选取的菜谱ID
		int reclen = 0; // 记录rec的长度

		for (int i = 0; i < clist.size(); i++) {
			Concern concern = clist.get(i);
			if (!concern.getIsValid().equals("1"))
				continue; // 保证关注有效

			int cid = concern.getCid();
			// System.out.println(cid);
			List<Recipe> rlist = recipeDao.Audit_queryByUid(cid);
			// System.out.println("recipe size="+rlist.size());
			if (rlist.size() > 0) {
				Recipe recipe = rlist.get(rlist.size() - 1);// 序号最大的菜谱为最新菜谱
				JSONObject jo = new JSONObject();
				jo.put("rname", recipe.getRname()); // 取出菜谱名称
				jo.put("pic", recipe.getPic()); // 取出菜谱样图
				jo.put("uid", recipe.getUid());
				ja.add(jo);
				rec[reclen++] = recipe.getRid(); // 增加菜谱记录
				if (reclen >= 5)
					break; // 控制取出的菜谱数量
			}

		}

		if (reclen >= 5)
			return ja.toString();
		// 收藏推荐
		List<Favorite> flist = favoriteDao.QueryByUid(uid);
		boolean again;// 作为菜谱是否重复的标记
		int rid;
		for (int i = 0; i < flist.size(); i++) {
			Favorite favorite = flist.get(i);
			int f_uid = favorite.getUid();

			List<Recipe> rlist = recipeDao.Audit_queryByUid(f_uid);
			Recipe recipe = new Recipe();
			JSONObject jo = new JSONObject();
			if (rlist.size() > 0 && reclen < 5) {
				recipe = rlist.get(rlist.size() - 1);
				jo.put("rname", recipe.getRname());
				jo.put("pic", recipe.getPic());
				jo.put("uid", recipe.getUid());
				rid = recipe.getRid();
				again = false;// 重置标记
				for (int j = 0; j < reclen; j++)
					if (rid == rec[j]) {
						again = true;// 确认重复
						break;
					}
				if (!again) {
					ja.add(jo);
					rec[reclen++] = rid;// 增加记录
				}
			}
		}

		if (ja.size() < 5) {
			// 季节推荐
			List<Recipe> rlist = recipeDao.Audit_queryBySeason(season);
			for (int i = 0; i < rlist.size(); i++) {
				JSONObject jo = new JSONObject();
				Recipe recipe = rlist.get(i);
				if (reclen < 5) {
					jo.put("rname", recipe.getRname());
					jo.put("pic", recipe.getPic());
					jo.put("uid", recipe.getUid());
					rid = recipe.getRid();
					again = false;
					for (int j = 0; j < reclen; j++)
						if (rid == rec[j]) {
							again = true;
							break;
						}
					if (!again) {
						ja.add(jo);
						rec[reclen++] = rid;
					}
				}

			}
		}

		return ja.toString();

	}

	/*
	 * 主页推荐方法2：人口统计学算法 1.取出用户信息,取出recommended表中，此用户的记录，更新有效期，将到期的菜谱删除
	 * 2.在user表中搜索与其相似度高的用户，取出他们的个人菜谱和收藏菜谱 3.将收罗的菜谱加入recommend表 4.返回2中结果
	 */
	public String getHomeRecipes2(int uid) {
		JSONArray ja = new JSONArray(); // 用于返回json结果
		// 更新此用户的推荐列表(recommmend表)中的validity
		List<Recommended> reclist = recommendedDao.queryByUid(uid);
		for (int i = 0; i < reclist.size(); i++) {
			Recommended rec = reclist.get(i);
			if (rec.getValidity() == 1)
				recommendedDao.delete(rec);
			else {
				rec.setValidity(rec.getValidity() - 1);
				recommendedDao.update(rec);
			}
		}
		// 取出此用户信息
		List<User> ulist = userDao.queryByUid(uid);
		if (ulist.size() <= 0)
			return "";
		User user = ulist.get(0);
		// 取出相似用户,依次按照相似度最高，次高排列，且默认这些用户的菜谱已经足够满足首页推荐的需求
		List<User> ulist2 = userDao.queryBySimilar(user.getGender(), user.getAge(), user.getSalary(), user.getCity(),
				user.getUid());
		List<User> ulist3 = userDao.queryBySimilar2(user.getGender(), user.getAge(), user.getSalary(), user.getCity());
		List<User> ulist4 = userDao.queryBySimilar3(user.getGender(), user.getAge(), user.getSalary(), user.getCity());
		for (int i = 0; i < ulist3.size(); i++)
			ulist2.add(ulist3.get(i));
		for (int i = 0; i < ulist4.size(); i++)
			ulist2.add(ulist4.get(i));
		for (int i = 0; i < ulist2.size(); i++) {
			User similarUser = ulist2.get(i);
			if (ja.size() >= 10)
				break;
			// 获取相似用户的个人菜谱
			List<Recipe> rlist = recipeDao.Audit_queryByUid(similarUser.getUid());
			for (int j = rlist.size() - 1; j >= 0; j--) {
				if (ja.size() >= 10)
					break;
				Recipe recipe = rlist.get(j);
				Recommended recommended = new Recommended(user.getUid(), recipe.getRid(), 7);
				JSONObject jo = new JSONObject();
				if (recommendedDao.queryByRid(recipe.getRid()).size() <= 0) {
					recommendedDao.save(recommended);
					jo.put("rname", recipe.getRname());
					jo.put("pic", recipe.getPic());
					jo.put("uid", recipe.getUid());
					ja.add(jo);
				}
			}
			// 获取相似用户的收藏菜谱
			List<Favorite> flist = favoriteDao.QueryByUid(user.getUid());
			for (int j = 0; j < flist.size(); j++) {
				if (ja.size() >= 10)
					break;
				Favorite favorite = flist.get(j);
				int rid = favorite.getRid();
				rlist = recipeDao.Audit_queryByRid(rid);
				Recipe recipe = rlist.get(0);
				Recommended recommended = new Recommended(user.getUid(), recipe.getRid(), 7);
				JSONObject jo = new JSONObject();
				if (recommendedDao.queryByRid(recipe.getRid()).size() <= 0) {
					recommendedDao.save(recommended);
					jo.put("rname", recipe.getRname());
					jo.put("pic", recipe.getPic());
					jo.put("uid", recipe.getUid());
					ja.add(jo);
				}
			}
		}
		return ja.toString();
	}
	
	/****************************首页推荐********************************/

	/**
	 * 获取季节菜谱
	 */
	public String getSeasonRecipes(String season) {
		List<Recipe> rlist = recipeDao.Audit_queryBySeason(season);
		JSONArray ja = new JSONArray();
		for (int i = 0; i < rlist.size(); i++) {
			JSONObject jo = new JSONObject();
			Recipe recipe = rlist.get(i);
			if (recipe.getAuditResult() == 1) {
				jo.put("rname", recipe.getRname());
				jo.put("pic", recipe.getPic());
				jo.put("uid", recipe.getUid());
				ja.add(jo);
			}
		}
		return ja.toString();
	}

	/**
	 * 发表评论
	 */
	public String makeComment(int uid, int rid, String comment) {
		commentDao.save(new Comment(uid, rid, comment, Time.getNow(), "1"));
		return "add";
	}

	/**
	 * 上传菜谱
	 * 
	 * @param uid
	 * @param rname
	 * @param rcontent
	 * @param pic
	 * @param season
	 * @param stepCon
	 * @param stepUrl
	 * @return
	 */
	public String upRecipe(int uid, String rname, String rcontent, String pic, String season, String stepCon,
			String stepUrl) {
		Recipe recipe = new Recipe(uid, season, "", rcontent, rname, Time.getNow(), pic, "false");
		recipeDao.save(recipe);
		List<Recipe> list_recipe = recipeDao.queryByRName(rname);
		if (list_recipe.size() > 0) {
			Recipe recipetmp = list_recipe.get(0);
			String[] sContents = stepCon.split(" ");
			String[] sUrls = stepUrl.split(" ");
			for (int i = 0; i < sUrls.length; i++) {
				stepsDao.save(new Steps(recipetmp.getRid(), i + 1, sUrls[i], sContents[i]));
			}
			return "add";
		} else {
			return "fail";
		}
	}

	/**
	 * 获取美食圈
	 * 
	 * @return
	 */
	public String getFoodCircle() {
		JSONArray ja = new JSONArray();
		List<Recipe> rlist = recipeDao.queryAll();
		for (int i = 0; i < rlist.size(); i++) {
			JSONObject jo = new JSONObject();
			Recipe recipe = rlist.get(i);
			User user = userDao.queryByUid(recipe.getUid()).get(0);
			jo.put("username", user.getUsername());
			jo.put("uid", user.getUid());
			jo.put("head", user.getHead());
			jo.put("time", recipe.getUptime());
			jo.put("rname", recipe.getRname());
			jo.put("pic", recipe.getPic());
			jo.put("cnum", commentDao.queryByRid(recipe.getRid()).size());
			jo.put("fnum", favoriteDao.QueryByRid(recipe.getRid()).size());
			jo.put("znum", zanDao.queryByRid(recipe.getRid()).size());

			ja.add(jo);
		}
		return ja.toString();
	}

	/**
	 * 搜索菜谱
	 * 
	 * @param rname
	 * @return
	 */

	public String searchRecipe(String rname) {
		JSONArray ja = new JSONArray();
		List<Recipe> rlist = recipeDao.searchByRName(rname);
		for (int i = 0; i < rlist.size(); i++) {
			JSONObject jo = new JSONObject();
			Recipe recipe = rlist.get(i);
			if (recipe.getAuditResult() == 1) {
				jo.put("rname", recipe.getRname());
				jo.put("pic", recipe.getPic());
				jo.put("time", recipe.getUptime());
				jo.put("uid", recipe.getUid());
				ja.add(jo);
			}
		}
		return ja.toString();
	}

}
