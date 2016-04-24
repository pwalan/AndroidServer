package web;

import com.opensymphony.xwork2.ActionSupport;

import utils.PicProcessSign;

/**
 * 得到签名
 * @author AlanP
 *
 */
public class GetSignAction extends ActionSupport{
	private int appid;
	private String secretId;
	private String secretKey;
	private String bucket;
	private Long expired;
	
	private String sign;
	
	private PicProcessSign picSign;
	
	public void setPicSign(PicProcessSign picSign) {
		this.picSign = picSign;
	}

	public int getAppid() {
		return appid;
	}

	public void setAppid(int appid) {
		this.appid = appid;
	}

	public String getSecretId() {
		return secretId;
	}

	public void setSecretId(String secretId) {
		this.secretId = secretId;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public String getBucket() {
		return bucket;
	}

	public void setBucket(String bucket) {
		this.bucket = bucket;
	}

	public Long getExpired() {
		return expired;
	}

	public void setExpired(Long expired) {
		this.expired = expired;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}
	
	/**
	 * 得到签名并返回给Android端
	 */
	public String Sign(){
		setAppid(10035979);
		setSecretId("AKIDhizdULuWqN7uKovxCzJ4DBZT085dkiAq");
		setSecretKey("bD1qonUx8gBUMOqicAZhdr1RACwNRJnw");
		setBucket("pwalan");
		setExpired(System.currentTimeMillis() / 1000 + 2592000);
		
		setSign(picSign.getSign(appid, secretId, secretKey, bucket, expired));
		System.out.println("sign: "+sign);
		return SUCCESS;
	}
}
