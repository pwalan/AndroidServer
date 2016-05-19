package web;

import com.opensymphony.xwork2.ActionSupport;

import domain.User;
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
	private String data;
	
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
	
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
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
			setHead("");
		}else{
			User user=userService.Login(username, passwd);
			if(user==null){
				setStatus("faild");
				setUid(0);
				setHead("");
			}else{
				setStatus("succeed");
				setUid(user.getUid());
				setHead(user.getHead());
			}
		}
		return SUCCESS;
	}
	
	/**
	 * 注册
	 */
	public String register(){
		setUsername(getUsername());
		setPasswd(getPasswd());
		setHead(getHead());
		System.out.println(username+" with "+passwd+" head:"+head);
		
		if(username==null||passwd==null){
			setStatus("failed");
		}else{
			if(userService.Register(username, passwd, head)){
				setStatus("succeed");
			}else{
				setStatus("failed");
			}
		}	
		return SUCCESS;
	}
	
	/**
	 * 获取有效收藏
	 */
	public String getFavorites(){
		setUid(getUid());
		setData(userService.getFarovites(uid));
		return SUCCESS;
	}
}
