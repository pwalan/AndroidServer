package utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.HmacUtils;

/**
 * 计算签名
 * @author AlanP
 *
 */
public class PicProcessSign {
    public static String getSign(int appId, String secret_id,String secret_key, 
                String bucket,
                long expired) {
        //a=[appid]&b=[bucket]&k=[SecretID]&t=[currenTime]&e=[expiredTime]&l=[url link]
        if (isEmpty(secret_id) || isEmpty(secret_key)){
            return null;
        }
    	
        long now = System.currentTimeMillis() / 1000;  
        String plain_text = String.format("a=%d&b=%s&k=%s&t=%d&e=%d",
            appId, bucket, secret_id, now, expired);
      
        byte[] bin = HmacUtils.hmacSha1(secret_key, plain_text);
        
        byte[] all = new byte[bin.length + plain_text.getBytes().length];
        System.arraycopy(bin, 0, all, 0, bin.length);
        System.arraycopy(plain_text.getBytes(), 0, all, bin.length, plain_text.getBytes().length);
        
        all = Base64.encodeBase64(all);
        return new String(all);
    }
            
    public static boolean isEmpty(String s){
        return s == null || s.trim().equals("") || s.trim().equals("null");
    }
}
