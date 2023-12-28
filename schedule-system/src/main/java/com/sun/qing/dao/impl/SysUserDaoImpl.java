package com.sun.qing.dao.impl;

import com.sun.qing.dao.BaseDao;
import com.sun.qing.dao.SysUserDao;
import com.sun.qing.pojo.SysUser;
import com.sun.qing.utils.MD5Util;

import java.util.List;

public class SysUserDaoImpl implements SysUserDao {
    BaseDao baseDao =  new BaseDao();
    @Override
    public SysUser getPassword(String username) {
        String sql = "select uid,username,user_pwd userPwd from sys_user where username = ?";
        List<SysUser> sysUserList = baseDao.baseQuery(SysUser.class, sql, username);
        return sysUserList.get(0);
//        return sysUserList.size() >0 ? sysUserList.get(0) : null;

    }

    /**
     * @param sysUser
     */
    @Override
    public Long userCount(SysUser sysUser) {
        String username = sysUser.getUsername();
        String sql = "select count(1) from sys_user where username = ?";
        Long count = baseDao.baseQueryObject(Long.class, sql, username);
        return count;

    }

    /**
     * @param sysUser
     */
    @Override
    public int regist(SysUser sysUser) {
        String username = sysUser.getUsername();
        String userPwd = sysUser.getUserPwd();
        userPwd = MD5Util.encrypt(userPwd);
        String sql = "insert into sys_user values(DEFAULT,?,?)";
        int i = baseDao.baseUpdate(sql, username, userPwd);
        return i;
    }

}
