package cn.junone.util;

import java.util.LinkedList;
import java.util.Queue;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;

public class Md5Util {

    private Md5Util() {

    }

    public static String createRandom() {
        return new SecureRandomNumberGenerator().nextBytes().toString();
    }

    /**
     * 简易加密
     */
    public static String md5(String data, String random) {
        int times = 2; // 加密次数：2
        String alogrithmName = "md5"; // 加密算法
        String encodePassword = new SimpleHash(alogrithmName, data, random, times).toString();
        return encodePassword;
    }
}
