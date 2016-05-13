package dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import domain.Steps;
/**
 * 对步骤信息操作的实现
 * @author AlanP
 *
 */
public class StepsDao extends HibernateDaoSupport{
	
	public List<Steps> queryByRid(int rid) {
		return (List<Steps>)getHibernateTemplate()
				.find("from Steps s where s.rid=?", rid);
	}
	
	public Integer save(Steps step) {
		return (Integer)getHibernateTemplate().save(step);
	}

	public void update(Steps step) {
		getHibernateTemplate().update(step);
	}
}
