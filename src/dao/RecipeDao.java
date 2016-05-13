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
	public List<Recipe> queryByRName(String rname) {
		return (List<Recipe>)getHibernateTemplate()
				.find("from Recipe r where r.rname=?", rname);
	}
	
	public List<Recipe> queryByRid(int rid) {
		return (List<Recipe>)getHibernateTemplate()
				.find("from Recipe r where r.rid=?", rid);
	}
	
	public Integer save(Recipe recipe) {
		return (Integer)getHibernateTemplate().save(recipe);
	}

	public void update(Recipe recipe) {
		getHibernateTemplate().update(recipe);
	}
}
