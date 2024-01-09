package com.sun.qing.controller;

import com.sun.qing.common.Result;
import com.sun.qing.common.ResultCodeEnum;
import com.sun.qing.pojo.SysUser;
import com.sun.qing.service.SysUserService;
import com.sun.qing.service.impl.SysUserServiceImpl;
import com.sun.qing.utils.MD5Util;
import com.sun.qing.utils.WebUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

@WebServlet("/user/*")
public class SysUserController extends BaseController {
    SysUserService userService =  new SysUserServiceImpl();

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("add");
    }
    protected void remove(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("remove");
    }
    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("update");
    }
    protected void find(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("find");
    }
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String username = req.getParameter("username");
//        String userPwd = req.getParameter("userPwd");
//        SysUser sysUser = new SysUser(null, username, userPwd);
//        boolean b = userService.login(sysUser);
//        if (b) {
//            resp.sendRedirect("/index.html");
//        } else {
//            resp.sendRedirect("/loginUserPwdError.html");
////            resp.getWriter().write("密码错了");
//        }
//        resp.getWriter().write("test");
//        System.out.println("cheng功");
//        WebUtil.writeJson(resp, Result.ok(null));
        // 接收用户请求参数
        // 获取要登录的用户名密码
        SysUser inputUser = WebUtil.readJson(req, SysUser.class);
        // 调用服务层方法,根据用户名查询数据库中是否有一个用户
        SysUser loginUser =userService.findByUsername(inputUser.getUsername());

        Result result = null;

        if(null == loginUser){
            // 没有根据用户名找到用户,说明用户名有误
            result=Result.build(null, ResultCodeEnum.USERNAME_ERROR);
        }else if(! loginUser.getUserPwd().equals(MD5Util.encrypt(inputUser.getUserPwd()))){
            // 用户密码有误,
            result=Result.build(null,ResultCodeEnum.PASSWORD_ERROR);
        }else{
            // 登录成功
            loginUser.setUserPwd("");
            result=Result.ok(loginUser);
        }

        WebUtil.writeJson(resp,result);

    }



    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SysUser registUer = WebUtil.readJson(req, SysUser.class);
        System.out.println(registUer);
        String username = req.getParameter("username");
        String userPwd = req.getParameter("userPwd");
        SysUser sysUser = new SysUser(null, username, userPwd);
        boolean b = userService.regist(sysUser);
        if (b) {
            WebUtil.writeJson(resp,Result.ok(null));
        }else {
            WebUtil.writeJson(resp,Result.build(null,ResultCodeEnum.USERNAME_USED));
        }
//        if (b) {
//            resp.sendRedirect("/login.html");
//        }else {
//            resp.sendRedirect("/registFail.html");
//        }
    }

    protected void checkUsernameUsed(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String username = req.getParameter("username");
        SysUser sysUser = userService.findByUsername(username);
        if (sysUser == null){
            WebUtil.writeJson(resp,Result.ok(null));
        }else {
            WebUtil.writeJson(resp,Result.build(null,ResultCodeEnum.USERNAME_USED));
        }

    }
}
