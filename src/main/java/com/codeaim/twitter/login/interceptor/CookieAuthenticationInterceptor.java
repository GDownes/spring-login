package com.codeaim.twitter.login.interceptor;

import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.codeaim.twitter.login.common.AuthenticationUtilities;
import com.codeaim.twitter.login.model.Principal;

@Component
public class CookieAuthenticationInterceptor extends HandlerInterceptorAdapter
{
    @Value("${com.codeaim.twitter.login.authentication.cookie.name:'twitterLoginAuthenticationCookieName'}")
    private String authenticationCookieName;

    @Value("${com.codeaim.twitter.login.authentication.principal.name:'twitterLoginAuthenticationPrincipalName'}")
    private String authenticationPrincipalName;

    @Value("${com.codeaim.twitter.login.authentication.cookie.key:'twitterLoginAuthenticationCookieKey'}")
    private String authenticationCookieKey;

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) throws Exception
    {
        Cookie[] cookies = request.getCookies();

        if (cookies != null)
        {
            for (Cookie cookie : cookies)
            {
                if (cookie.getName().equals(authenticationCookieName))
                {
                    Optional<Principal> principal =
                            AuthenticationUtilities.getPrincipal(
                                    authenticationCookieKey,
                                    cookie.getValue());

                    if (principal.isPresent())
                    {
                        request.setAttribute(
                                authenticationPrincipalName,
                                principal.get());
                    }
                }
            }
        }
        return true;
    }
}
