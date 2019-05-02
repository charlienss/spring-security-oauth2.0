/**
 * 
 */
package com.city.security.security.core.validate.code;

import org.springframework.security.core.AuthenticationException;

public class ValidateCodeException extends AuthenticationException {

	/**
	 * 
	 */

	public ValidateCodeException(String msg) {
		super(msg);
	}

}
