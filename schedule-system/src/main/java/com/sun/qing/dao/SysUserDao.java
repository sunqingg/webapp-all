package com.sun.qing.dao;

import com.sun.qing.pojo.SysUser;

public interface SysUserDao {
    SysUser getPassword(String username);

    Long userCount(SysUser sysUser);

    int regist(SysUser sysUser);
}
