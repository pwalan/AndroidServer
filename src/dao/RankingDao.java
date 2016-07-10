package dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import domain.Ranking;

/**
 * 对排行榜的操作实现
 * @author AlanP
 *
 */
public class RankingDao extends HibernateDaoSupport{
	
	public List<Ranking> queryByRid(int rid){
		return (List<Ranking>)getHibernateTemplate()
				.find("from Ranking r where r.rid=?",rid);
	}
	
	public Integer save(Ranking Ranking) {
		return (Integer)getHibernateTemplate().save(Ranking);
	}
	
	public void update(Ranking Ranking) {
		getHibernateTemplate().update(Ranking);
	}

	public List<Ranking> queryById(int id) {
		return (List<Ranking>)getHibernateTemplate()
				.find("from Ranking r where r.id=?",id);
	}
	public void delete(Ranking Ranking) {
		getHibernateTemplate().delete(Ranking);
	}
}
