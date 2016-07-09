package dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import domain.User;

/**
 * 对用户信息操作的实现
 * 
 * @author AlanP
 *
 */
public class UserDao extends HibernateDaoSupport {
	public List<User> queryByUserName(String username) {
		return (List<User>) getHibernateTemplate().find("from User u where u.username=?", username);
	}

	public List<User> queryByUid(int uid) {
		return (List<User>) getHibernateTemplate().find("from User u where u.uid=?", uid);
	}

	public User get(int uid) {
		return getHibernateTemplate().get(User.class, uid);
	}

	public Integer save(User user) {
		return (Integer) getHibernateTemplate().save(user);
	}

	public void update(User user) {
		getHibernateTemplate().update(user);
	}

	// 此方法搜索的结果相似度最高（性别，年龄段，工薪段，城市完成相同）
	public List<User> queryBySimilar(String gender, String age, String salary, String city, int uid) {
		return (List<User>) getHibernateTemplate().find("from User u where u.gender=" + gender + "and u.age=" + age
				+ "and u.salary=" + salary + "and u.city=" + city + "and u.uid!=" + uid);
	}

	// 此方法搜索的结果相似度次高（性别，年龄段，工薪段完成相同，城市不同）
	public List<User> queryBySimilar2(String gender, String age, String salary, String city) {
		return (List<User>) getHibernateTemplate().find("from User u where u.gender=" + gender + "and u.age=" + age
				+ "and u.salary=" + salary + "and u.city!=" + city);
	}

	// 此方法搜索的结果相似度次高（性别，工薪段，城市完成相同，年龄段不同）
	public List<User> queryBySimilar3(String gender, String age, String salary, String city) {
		return (List<User>) getHibernateTemplate().find("from User u where u.gender=" + gender + "and u.age!=" + age
				+ "and u.salary=" + salary + "and u.city=" + city);
	}
}
