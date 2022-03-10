package com.gnz.pms.entities;

import java.util.List;

public class Series
{
    public static final String TYPE_LINE = null;
    public String name;
    public String type;
    public List<Integer> data;
    public Series(String name, String type, List<Integer> data)
    {
        this.name = name;
        this.type = type;
        this.data = data;
    }
    
    public String toName()
    {
        return name;
    }

}