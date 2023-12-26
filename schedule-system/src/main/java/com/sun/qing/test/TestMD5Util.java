package com.sun.qing.test;

import com.sun.qing.utils.MD5Util;
import org.junit.Test;


public class TestMD5Util {
    @Test
    public void testEncrypt() {
        String encrypt = MD5Util.encrypt("123456");
        System.out.println(encrypt);
    }
}
