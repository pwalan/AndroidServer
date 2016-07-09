package dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import domain.Recommended;

/**
 * 对已推荐的操作实现
 * @author AlanP
 *
 */
public class RecommendedDao extends HibernateDaoSupport{
	
	public List<Recommended> queryByRid(int rid){
		return (List<Recommended>)getHibernateTemplate()
				.find("from Recommended r where r.rid=?",rid);
	}
	
	public Integer save(Recommended recommended) {
		return (Integer)getHibernateTemplate().save(recommended);
	}
	
	public void update(Recommended recommended) {
		getHibernateTemplate().update(recommended);
	}

	public List<Recommended> queryByUid(int uid) {
		return (List<Recommended>)getHibernateTemplate()
				.find("from Recommended r where r.uid=?",uid);
	}
	public void delete(Recommended recommended) {
		getHibernateTemplate().delete(recommended);
	}
}
