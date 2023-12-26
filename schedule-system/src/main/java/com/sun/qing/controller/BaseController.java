package com.sun.qing.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.lang.reflect.Method;

public class BaseController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        String[] split = uri.split("/");
        String methodName = split[split.length - 1];
//        if (methodName.equals("add")) {
//            this.
//        }
        // 反射
        Class aClass = this.getClass();
        try {
            Method declaredMethod = aClass.getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            // 设置方法可以访问
            declaredMethod.setAccessible(true);
            // 通过反射执行代码
            declaredMethod.invoke(this,req,resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        };

    }


}
