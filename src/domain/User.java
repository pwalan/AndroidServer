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
	
	private String gender;
	
	private String age;
	
	private String city;
	
	private String salary;
	
	private String taste;
	
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

	public User(String username, String passwd, int type, String head, String gender, String age, String city,
			String salary, String taste) {
		super();
		this.username = username;
		this.passwd = passwd;
		this.type = type;
		this.head = head;
		this.gender = gender;
		this.age = age;
		this.city = city;
		this.salary = salary;
		this.taste = taste;
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
    
}
