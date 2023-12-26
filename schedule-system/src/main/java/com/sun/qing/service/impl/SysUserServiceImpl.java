package com.sun.qing.service.impl;

import com.sun.qing.dao.SysUserDao;
import com.sun.qing.dao.impl.SysUserDaoImpl;
import com.sun.qing.pojo.SysUser;
import com.sun.qing.service.SysUserService;
import com.sun.qing.utils.MD5Util;

public class SysUserServiceImpl implements SysUserService {
    SysUserDao sysUserDao =  new SysUserDaoImpl();
    @Override
    public boolean login(SysUser sysUser) {
        String username = sysUser.getUsername();
        String encrypt = MD5Util.encrypt(username);
        SysUser user = sysUserDao.getPassword(username);
        if (user.getUserPwd().equals(encrypt)) {
            return true;
        }
        return false;
    }
}
