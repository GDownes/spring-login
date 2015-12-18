package com.codeaim.twitter.login.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class UnauthenticatedInterceptor extends HandlerInterceptorAdapter
{
    @Value("${com.codeaim.twitter.login.authentication.principal.name:'twitterLoginAuthenticationPrincipalName'}")
    private String authenticationPrincipalName;

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) throws Exception
    {
        if(request.getAttribute(authenticationPrincipalName) == null) {
            response.sendRedirect("/login");
            return false;
        }
        return true;
    }
}
