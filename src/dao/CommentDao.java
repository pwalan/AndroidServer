package dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import domain.Comment;

/**
 * 对评论的操作实现
 * @author AlanP
 *
 */
public class CommentDao extends HibernateDaoSupport{
	
	public List<Comment> queryByRid(int rid){
		return (List<Comment>)getHibernateTemplate()
				.find("from Comment c where c.rid=? order by c.time desc",rid);
	}
	
	public Integer save(Comment comment) {
		return (Integer)getHibernateTemplate().save(comment);
	}
	
	public void update(Comment comment) {
		getHibernateTemplate().update(comment);
	}
}
