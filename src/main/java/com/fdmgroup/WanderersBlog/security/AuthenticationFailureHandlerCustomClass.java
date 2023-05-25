package com.fdmgroup.WanderersBlog.security;

import java.io.IOException;

import javax.naming.AuthenticationException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFailureHandlerCustomClass implements AuthenticationFailureHandler {
	
	

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			org.springframework.security.core.AuthenticationException exception) throws IOException, ServletException {
		request.getSession().setAttribute("message2", "Login failed. Please check your credentials and try again.");
        response.sendRedirect("/login");
		
	}


}