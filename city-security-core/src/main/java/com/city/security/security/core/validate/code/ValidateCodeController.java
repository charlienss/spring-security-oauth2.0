package com.city.security.security.core.validate.code;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.security.Security;
import java.util.Random;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.naming.Name;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.city.security.security.core.properties.SecurityProperties;
import com.city.security.security.core.validate.code.sms.SmsCodeSender;
import org.apache.catalina.manager.util.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author zhailiang
 *
 */
@RestController
public class ValidateCodeController {
	
	public static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";
	
	private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

	@Autowired
	private SecurityProperties securityProperties;

	//短信验证码
	@Autowired
	@Qualifier(value = "smsCodeGenerator")
	private ValidateCodeGenerator smsCodeGenerator;

	@Resource
	private ValidateCodeGenerator imageCodeGenerator;

	@Autowired
	private SmsCodeSender smsCodeSender;

	@GetMapping("/code/image")
	public void createImageCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		ImageCode imageCode = (ImageCode) imageCodeGenerator.generate(new ServletWebRequest(request));
//		System.out.println("imageCode=>"+imageCode);
		sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, imageCode);
		ImageIO.write(imageCode.getImage(), "JPEG", response.getOutputStream());
		
	}
	
	@GetMapping("/code/sms")
	public void createSmsCode(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletRequestBindingException {

		ValidateCode smsCode =  smsCodeGenerator.generate(new ServletWebRequest(request));
//		System.out.println("smsCode==>"+smsCode);
		sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, smsCode);
		String mobile = ServletRequestUtils.getStringParameter(request,"mobile");
		smsCodeSender.send(mobile,smsCode.getCode());
	}



}
