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
	private int rid;
	private int cid;
	private String username;
	private String passwd;
	private String head;
	private String status;
	private String data;
	private String gender;
	private String age;
	private String city;
	private String salary;
	private String taste;
	private String question;
	private String answer;
	
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
	
	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
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

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getTaste() {
		return taste;
	}

	public void setTaste(String taste) {
		this.taste = taste;
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
		setGender(getGender());
		setAge(getAge());
		setCity(getCity());
		setSalary(getSalary());
		setTaste(getTaste());
		setQuestion(getQuestion());
		setAnswer(getAnswer());
		System.out.println(username+" with "+passwd+" head:"+head);
		
		if(username==null||passwd==null){
			setStatus("failed");
		}else{
			if(userService.Register(username, passwd, head,gender,age,city,salary,taste,question,answer)){
				setStatus("succeed");
			}else{
				setStatus("failed");
			}
		}	
		return SUCCESS;
	}
	
	/**
	 * 添加收藏
	 */
	public String addFavorite(){
		setUid(getUid());
		setRid(getRid());
		setData(userService.addFavorite(uid, rid));
		return SUCCESS;
	}
	
	/**
	 * 获取有效收藏
	 */
	public String getFavorites(){
		setUid(getUid());
		setData(userService.getFavorites(uid));
		return SUCCESS;
	}
	
	/**
	 * 添加关注
	 */
	public String addConcern(){
		setUid(getUid());
		setCid(getCid());
		setData(userService.addConcern(uid, cid));
		return SUCCESS;
	}
	
	/**
	 * 获取有效关注
	 */
	public String getConcern(){
		setUid(getUid());
		setData(userService.getConcern(uid));
		return SUCCESS;
	}
	
	/**
	 * 获取发布及关注数
	 */
	public String getUserUp(){
		setUid(getUid());
		setData(userService.getUserUp(uid));
		return SUCCESS;
	}
	
	/**
	 * 更改密码
	 * @return
	 */
	public String changePasswd(){
		setUid(getUid());
		setPasswd(getPasswd());
		setData(userService.changePasswd(uid, passwd));
		return SUCCESS;
	}
	
	/**
	 * 更改头像
	 * @return
	 */
	public String changeHead(){
		setUid(getUid());
		setHead(getHead());
		setData(userService.changeHead(uid, head));
		return SUCCESS;
	}
	
	/**
	 * 得到密保问题
	 * @return
	 */
	public String getSquestion(){
		setUsername(getUsername());
		setData(userService.getSquestion(username));
		return SUCCESS;
	}
	
	/**
	 * 找回密码
	 * @return
	 */
	public String findPasswd(){
		setUsername(getUsername());
		setAnswer(getAnswer());
		setData(userService.findPasswd(username, answer));
		return SUCCESS;
	}
}
