package domain;

/**
 * 用户信息
 * @author AlanP
 *
 */
public class User {
	private int uid;

	private String username;

	private String passwd;

	private int type;

	private String head;
	public User() {
		super();
	}
	public User(String username, String passwd,int type,String head ) {
		super();
		this.username = username;
		this.passwd = passwd;
		this.type=type;
		this.head=head;
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
	public void setType(int type){	
    	this.type =type;
    }
    public int getType(){
    	return type;
    }
    public void setHead(String head){	
    	this.head =head;
    }
    public String getHead(){
    	return head;
    }
}
