package dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import domain.User;

public class UserDao extends HibernateDaoSupport{
	public List<User> queryByUserName(String username) {
		return (List<User>)getHibernateTemplate()
				.find("from User u where u.username=?", username);
	}
	
	public List<User> queryByUid(int uid) {
		return (List<User>)getHibernateTemplate()
				.find("from User u where u.uid=?", uid);
	}
	
	public Integer save(User user) {
		return (Integer)getHibernateTemplate().save(user);
	}

	public void update(User user) {
		getHibernateTemplate().update(user);
	}
}
