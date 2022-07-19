package com.xw.springframwork.test;

public class UserService {
    private String uId;

    private UserDao userDao;

    public void query(String uid){
        System.out.println("查询用户信息"+userDao.query(uid));
    }
}
