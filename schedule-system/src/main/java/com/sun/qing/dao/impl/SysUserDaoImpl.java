package com.sun.qing.dao.impl;

import com.sun.qing.dao.BaseDao;
import com.sun.qing.dao.SysUserDao;
import com.sun.qing.pojo.SysUser;

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
}
