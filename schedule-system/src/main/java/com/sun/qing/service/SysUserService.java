package com.sun.qing.service;

import com.sun.qing.pojo.SysUser;

public interface SysUserService {
    boolean login(SysUser sysUser);

    boolean regist(SysUser sysUser);

    SysUser findByUsername(String username);
}
