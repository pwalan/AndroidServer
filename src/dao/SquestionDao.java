package dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import domain.Squestion;

/**
 * 对密保问题操作的实现
 * @author AlanP
 *
 */
public class SquestionDao extends HibernateDaoSupport{
	public List<Squestion> queryByUid(int uid){
		return (List<Squestion>)getHibernateTemplate()
				.find("from Squestion s where s.uid=?",uid);
	}
	
	public Integer save(Squestion squestion) {
		return (Integer)getHibernateTemplate().save(squestion);
	}
	
	public void update(Squestion squestion) {
		getHibernateTemplate().update(squestion);
	}
}
