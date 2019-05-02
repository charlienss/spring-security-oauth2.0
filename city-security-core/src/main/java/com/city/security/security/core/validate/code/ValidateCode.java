/**
 * 
 */
package com.city.security.security.core.validate.code;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 *
 */
public class ValidateCode implements Serializable {


	private static final long serialVersionUID = -1025950130941630500L;

	private String code;

	private LocalDateTime expireTime;

	public ValidateCode( String code, int expireIn){
		this.code = code;
		//在这里设置了过期时长
		this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
	}

	public ValidateCode( String code, LocalDateTime expireTime){
		this.code = code;
		this.expireTime = expireTime;
	}
	
	public boolean isExpried() {
		return LocalDateTime.now().isAfter(expireTime);
	}


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public LocalDateTime getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(LocalDateTime expireTime) {
		this.expireTime = expireTime;
	}

}
