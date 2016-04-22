package web;

import com.opensymphony.xwork2.ActionSupport;

import service.UserService;

/**
 * 用户的相关操作
 * @author AlanP
 *
 */
public class UserAction extends ActionSupport{
	private int uid;
	private String username;
	private String passwd;
	private String head;
	private String status;
	
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * 登录
	 */
	public String login(){
		setUsername(getUsername());
		setPasswd(getPasswd());
		System.out.println(username+" with "+passwd);
		
		if(username == null || passwd == null) {
			setStatus("failed");
			setUid(0);
		}else{
			uid=userService.Login(username, passwd);
			if(uid==0){
				setStatus("faild");
			}else{
				setStatus("succeed");
			}
		}
		return SUCCESS;
	}
	
}
