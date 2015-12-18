package com.codeaim.twitter.login.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DelegatingWebMvcConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import com.codeaim.twitter.login.interceptor.BasicAuthenticationInterceptor;
import com.codeaim.twitter.login.interceptor.BearerAuthenticationInterceptor;
import com.codeaim.twitter.login.interceptor.CookieAuthenticationInterceptor;
import com.codeaim.twitter.login.interceptor.UnauthenticatedInterceptor;

@Configuration
public class InterceptorConfiguration
        extends DelegatingWebMvcConfiguration
{
    @Autowired
    private BearerAuthenticationInterceptor bearerAuthenticationInterceptor;
    @Autowired
    private BasicAuthenticationInterceptor basicAuthenticationInterceptor;
    @Autowired
    private CookieAuthenticationInterceptor cookieAuthenticationInterceptor;
    @Autowired
    private UnauthenticatedInterceptor unauthenticatedInterceptor;

    @Override
    protected void addInterceptors(
            InterceptorRegistry registry
    )
    {
        registry.addInterceptor(bearerAuthenticationInterceptor)
                .excludePathPatterns("/login", "/access-token");
        registry.addInterceptor(basicAuthenticationInterceptor)
                .addPathPatterns("/login");
        registry.addInterceptor(cookieAuthenticationInterceptor)
                .excludePathPatterns("/login", "/access-token");
        registry.addInterceptor(unauthenticatedInterceptor)
                .excludePathPatterns("/login");
    }
}
