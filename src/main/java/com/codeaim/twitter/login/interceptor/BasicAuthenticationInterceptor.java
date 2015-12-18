package com.codeaim.twitter.login.interceptor;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.codeaim.twitter.login.common.AuthenticationUtilities;
import com.codeaim.twitter.login.model.Principal;
import com.codeaim.twitter.login.model.User;
import com.codeaim.twitter.login.repository.UserRepository;

@Component
public class BasicAuthenticationInterceptor extends HandlerInterceptorAdapter
{
    @Value("${com.codeaim.twitter.login.authentication.principal.name:'twitterLoginAuthenticationPrincipalName'}")
    private String authenticationPrincipalName;

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) throws Exception
    {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Basic "))
        {
            Optional<String[]> tokenParts =
                    AuthenticationUtilities
                            .getBasicAuthorizationTokenParts(
                                    authorizationHeader.substring(
                                            6,
                                            authorizationHeader.length()));
            if (tokenParts.isPresent())
            {
                Optional<User> user = userRepository
                        .findByUsernameAndPassword(
                                tokenParts.get()[0],
                                tokenParts.get()[1]);

                if (user.isPresent())
                {
                    request.setAttribute(
                            authenticationPrincipalName,
                            new Principal(user.get().getId()));
                }
            }
        }
        return true;
    }
}
