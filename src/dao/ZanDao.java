package dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import domain.Zan;

/**
 * 对点赞信息操作的实现
 * @author AlanP
 *
 */
public class ZanDao extends HibernateDaoSupport{
	public List<Zan> queryByUid(int uid) {
		return (List<Zan>)getHibernateTemplate()
				.find("from Zan z where z.uid=?", uid);
	}
	
	public List<Zan> queryByRid(int rid) {
		return (List<Zan>)getHibernateTemplate()
				.find("from Zan z where z.rid=?", rid);
	}
	
	public int countZan(int rid) {
		 String hql = "select count(*) from Zan as z where z.rid="+rid;
		  Long count = (Long)getHibernateTemplate().find(hql).listIterator().next();
		  return count.intValue();

	}
	
	public Integer save(Zan zan) {
		return (Integer)getHibernateTemplate().save(zan);
	}
}
