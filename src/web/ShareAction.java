package web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;

import dao.CommentDao;
import dao.RecipeDao;
import dao.StepsDao;
import dao.UserDao;
import domain.Comment;
import domain.Recipe;
import domain.Steps;
import domain.User;
import service.RecipeService;
import utils.UserComment;

/**
 * 分享
 * @author AlanP
 *
 */
public class ShareAction implements Action {
	
	private int rid;
	private RecipeDao recipeDao;
	private StepsDao stepsDao;
	private UserDao userDao;
	private CommentDao commentDao;

	public RecipeDao getRecipeDao() {
		return recipeDao;
	}

	public void setRecipeDao(RecipeDao recipeDao) {
		this.recipeDao = recipeDao;
	}

	public StepsDao getStepsDao() {
		return stepsDao;
	}

	public void setStepsDao(StepsDao stepsDao) {
		this.stepsDao = stepsDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public CommentDao getCommentDao() {
		return commentDao;
	}

	public void setCommentDao(CommentDao commentDao) {
		this.commentDao = commentDao;
	}

	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

	@Override
	public String execute() throws Exception {
		Recipe recipe=recipeDao.get(rid);
		User user=userDao.get(recipe.getUid());
		List<Steps> list_steps = stepsDao.queryByRid(rid);
		List<Comment> list_comments = commentDao.queryByRid(rid);
		List<UserComment> listuserpic=new ArrayList<UserComment>();
		for(int i=0;i<list_comments.size();i++){
			User u=userDao.get(list_comments.get(i).getUid());
			UserComment uc=new UserComment(u.getHead(),u.getUsername(),list_comments.get(i).getContent());
			listuserpic.add(uc);
		}
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("recipe", recipe);
		request.put("user",user);
		request.put("listSteps",list_steps);
		request.put("commentlist", listuserpic);
		return SUCCESS;
	}

}
