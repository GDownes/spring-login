package com.codeaim.twitter.login.model;

public class Principal
{
    private long id;

    protected Principal() {}

    public Principal(long id)
    {
        this.id = id;
    }

    public long getId()
    {
        return id;
    }
}
