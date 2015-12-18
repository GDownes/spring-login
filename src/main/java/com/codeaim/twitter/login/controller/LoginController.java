package com.codeaim.twitter.login.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Controller
public class LoginController extends BaseController
{
    @Value("${com.codeaim.twitter.login.authentication.cookie.name:'twitterLoginAuthenticationCookie'}")
    private String authenticationCookieName;

    @Value("${com.codeaim.twitter.login.authentication.cookie.key:'twitterLoginAuthenticationCookieKey'}")
    private String authenticationCookieKey;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login()
    {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String accessToken(HttpServletRequest request, HttpServletResponse response)
    {
        response.addCookie(
                new Cookie(
                        authenticationCookieName,
                        Jwts.builder()
                                .claim("principal", getPrincipal(request))
                                .signWith(SignatureAlgorithm.HS256, authenticationCookieKey)
                                .compact()));
        return "login";
    }
}
