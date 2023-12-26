package com.sun.qing.test;

import com.sun.qing.dao.BaseDao;
import com.sun.qing.pojo.SysUser;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class TestBaseDao {
    private static BaseDao baseDao;

    @BeforeClass
    public static void initBaseDao() {
        baseDao = new BaseDao();
    }
    @Test
    public void TestBaseQueryObject(){
        String sql = "select count(1) from sys_user";
        Long aLong = baseDao.baseQueryObject(Long.class, sql);
        System.out.println(aLong);
    }
    @Test
    public void TestBaseQuery(){
        String sql = "select uid,username,user_pwd userPwd from sys_user";
        List<Object> objectList = baseDao.baseQuery(SysUser.class, sql);
        objectList.forEach(System.out::println);
    }
    @Test
    public void TestBaseUpdate(){
        String sql = "insert into sys_user values(DEFAULT,?,?)";
        int rows = baseDao.baseUpdate(sql, "sun", 123456);
        System.out.println("影响行数： " + rows);

    }
}
