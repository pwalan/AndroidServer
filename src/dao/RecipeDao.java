package dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import domain.Recipe;
/**
 * 对菜谱信息操作的实现
 * @author AlanP
 *
 */
public class RecipeDao extends HibernateDaoSupport{
	
	public List<Recipe> queryAll(){
		return (List<Recipe>)getHibernateTemplate().find("from Recipe");
	}
	
	public List<Recipe> queryByRName(String rname) {
		return (List<Recipe>)getHibernateTemplate()
				.find("from Recipe r where r.rname=?", rname);
	}
	
	public List<Recipe> queryByRid(int rid) {
		return (List<Recipe>)getHibernateTemplate()
				.find("from Recipe r where r.rid=?", rid);
	}
	
	public List<Recipe> queryByUid(int uid) {
		return (List<Recipe>)getHibernateTemplate()
				.find("from Recipe r where r.uid=?", uid);
	}
	
	public List<Recipe> queryBySeason(String season){
		return (List<Recipe>)getHibernateTemplate()
				.find("from Recipe r where r.season like '%"+season+"%'");
	}
	
	public Recipe get(int rid){
		return getHibernateTemplate().get(Recipe.class,rid);
	}
	
	public Integer save(Recipe recipe) {
		return (Integer)getHibernateTemplate().save(recipe);
	}

	public void update(Recipe recipe) {
		getHibernateTemplate().update(recipe);
	}
}
