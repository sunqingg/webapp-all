package com.sun.qing.service;

import com.sun.qing.pojo.SysUser;

public interface SysScheduleService {
    boolean login(SysUser sysUser);

    boolean regist(SysUser sysUser);
}
