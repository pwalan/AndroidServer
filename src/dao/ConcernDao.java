package dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import domain.Concern;

/**
 * 对关注操作的实现
 * @author AlanP
 *
 */
public class ConcernDao extends HibernateDaoSupport{
	public List<Concern> queryByUid(int uid){
		return (List<Concern>)getHibernateTemplate()
				.find("from Concern c where c.uid=?",uid);
	}
	
	public Integer save(Concern concern) {
		return (Integer)getHibernateTemplate().save(concern);
	}
	
	public void update(Concern concern) {
		getHibernateTemplate().update(concern);
	}
}
