package com.codeaim.twitter.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController extends BaseController
{
    @RequestMapping("/")
    public String home()
    {
        return "home";
    }
}
