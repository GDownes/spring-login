package com.codeaim.twitter.login.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User
{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String username;
    private String accessToken;
    private String password;

    protected User() {}

    public User(String username, String accessToken, String password)
    {
        this.username = username;
        this.accessToken = accessToken;
        this.password = password;
    }

    public long getId()
    {
        return id;
    }

    public String getUsername()
    {
        return username;
    }

    public String getAccessToken()
    {
        return accessToken;
    }

    public String getPassword()
    {
        return password;
    }
}
