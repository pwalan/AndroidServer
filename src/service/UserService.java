package service;

import java.util.List;

import dao.UserDao;
import domain.User;

/**
 * 用户服务
 * @author AlanP
 *
 */
public class UserService {
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public int Login(String username, String passwd){
		List<User> userlist=userDao.queryByUserName(username);
		if(userlist==null){
			return 0;
		}else{
			User user=userlist.get(0);
			if(user.getPasswd().equals(passwd)){
				return user.getUid();
			}
		}
		return 0;
	}
	
	public boolean Register(String username, String passwd, String head){
		List<User> userlist=userDao.queryByUserName(username);
		if(userlist.size()>0){
			return false;
		}else{
			userDao.save(new User(username, passwd, 1, head));
			return true;
		}
	}
}
