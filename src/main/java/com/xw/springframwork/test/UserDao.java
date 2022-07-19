package com.xw.springframwork.test;

import java.util.HashMap;
import java.util.Map;

public class UserDao {
    private static Map<String,String> hashmap=new HashMap<>();
    static{
        hashmap.put("001","xw");
    }
    public String query(String uid){
        return hashmap.get(uid);
    }
}
