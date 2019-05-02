package com.city.security.security.core.authentication.mobile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


/**
 *
 *
 */
public class SmsCodeAuthenticationFilter  extends AbstractAuthenticationProcessingFilter {
	// ~ Static fields/initializers
	// =====================================================================================

	public static final String CITY_FORM_MOBILE_KEY = "mobile";

	private String mobileParameter = CITY_FORM_MOBILE_KEY;
	private boolean postOnly = true;

	// ~ Constructors
	// ===================================================================================================

	public SmsCodeAuthenticationFilter() {
		super(new AntPathRequestMatcher("/authentication/mobile", "POST"));
	}

	// ~ Methods
	// ========================================================================================================

	public Authentication attemptAuthentication(HttpServletRequest request,
												HttpServletResponse response) throws AuthenticationException {
		//只能用post请求
		if (postOnly && !request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException(
					"Authentication method not supported: " + request.getMethod());
		}

		String mobile = obtainMobile(request);

		if (mobile == null) {
			mobile = "";
		}

		mobile = mobile.trim();

		SmsCodeAuthenticationToken authRequest = new SmsCodeAuthenticationToken(
				mobile);

		// Allow subclasses to set the "details" property
		setDetails(request, authRequest);

		return this.getAuthenticationManager().authenticate(authRequest);
	}

//	protected String obtainPassword(HttpServletRequest request) {
//		return request.getParameter(passwordParameter);
//	}

	/**
	 * 获取手机号
	 */
	protected String obtainMobile(HttpServletRequest request) {
		return request.getParameter(mobileParameter);
	}

	protected void setDetails(HttpServletRequest request,
							  SmsCodeAuthenticationToken authRequest) {
		authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
	}

//	public void setUsernameParameter(String usernameParameter) {
//		Assert.hasText(usernameParameter, "Username parameter must not be empty or null");
//		this.usernameParameter = usernameParameter;
//	}


	public void setPostOnly(boolean postOnly) {
		this.postOnly = postOnly;
	}

	public String getMobileParameter() {
		return mobileParameter;
	}

	public void setMobileParameter(String mobileParameter) {
		this.mobileParameter = mobileParameter;
	}
}
