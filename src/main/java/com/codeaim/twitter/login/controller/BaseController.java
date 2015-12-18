package com.codeaim.twitter.login.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;

import com.codeaim.twitter.login.model.Principal;


public class BaseController
{
    @Value("${com.codeaim.twitter.login.authentication.principal.name:'twitterLoginAuthenticationPrincipalName'}")
    private String authenticationPrincipalName;

    public Principal getPrincipal(HttpServletRequest request)
    {
        return (Principal) request.getAttribute(authenticationPrincipalName);
    }
}
