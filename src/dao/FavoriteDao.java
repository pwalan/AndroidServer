package dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import domain.Favorite;

/**
 * 对收藏的操作实现
 * @author AlanP
 *
 */
public class FavoriteDao extends HibernateDaoSupport{
	public List<Favorite> QueryByUidAndRid(int uid,int rid){
		return (List<Favorite>)getHibernateTemplate()
				.find("from Favorite f where f.uid="+uid+" and f.rid="+rid);	
	}
	
	public List<Favorite> QueryByUid(int uid){
		return (List<Favorite>)getHibernateTemplate()
				.find("from Favorite f where f.uid=?",uid);	
	}
	
	public List<Favorite> QueryByRid(int rid){
		return (List<Favorite>)getHibernateTemplate()
				.find("from Favorite f where f.rid=?",rid);	
	}
	
	public Integer save(Favorite favorite) {
		return (Integer)getHibernateTemplate().save(favorite);
	}
	
	public void update(Favorite favorite){
		getHibernateTemplate().update(favorite);
	}
	
}
