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
        String password = sysUser.getUserPwd();
        String username = sysUser.getUsername();
        String encrypt = MD5Util.encrypt(password);
        SysUser user = sysUserDao.getPassword(username);
        if (user.getUserPwd().equals(encrypt)) {
            return true;
        }
        return false;
    }

    /**
     * @param sysUser
     */
    @Override
    public boolean regist(SysUser sysUser) {
        String username = sysUser.getUsername();
        String userPwd = sysUser.getUserPwd();
        Long count = sysUserDao.userCount(sysUser);
        if (count == 0) {
            int registRow = sysUserDao.regist(sysUser);
            if (registRow > 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param username
     * @return
     */
    @Override
    public SysUser findByUsername(String username) {
        SysUser sysUser =  sysUserDao.findByUsername(username);
        return sysUser;
    }
}
