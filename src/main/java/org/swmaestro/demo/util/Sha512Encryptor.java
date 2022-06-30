package org.swmaestro.demo.util;

import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;

/**
 * SHA-256 Encryptor
 *
 * @since  2022-06-29
 * @author lifeandsong
 */
@Slf4j
public class Sha512Encryptor {
 
    /**
     * Encrypt
     * 
     * @param	source
     * @return	target encrypted in SHA-256
     */
    public String encrypt(String source) {
    	if (source == null || source.isEmpty())
    		return "";

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            byte[] hash = digest.digest(source.getBytes("UTF-8"));
            StringBuffer sb = new StringBuffer();
            for(int i=0; i<hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1)
                	sb.append('0');
 
                sb.append(hex);
            }
            return sb.toString();
        } catch(Exception e){
        	log.error(e.getMessage());
        }
        return "";
    }
 
    /**
     * Test SHA-512 encryption
     *
     * @param args
     */
    public static void main(String[] args) {
    	String source = "youHost20!!@&";

    	Sha512Encryptor sha256 = new Sha512Encryptor();
	   	String encryped = sha256.encrypt(source);
	   	log.info(source + " -> " + encryped + " : " + encryped.length());

        encryped = sha256.encrypt(source);
        log.info(source + " -> " + encryped + " : " + encryped.length());
    }
 
}
