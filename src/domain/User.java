package domain;

import java.io.Serializable;

/**
 * 用户信息
 */
public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//用户id
	private int uid;
	//用户名
	private String username;
	//密码
	private String passwd;
	//头像地址
	private String head;

	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}
	
	
}
