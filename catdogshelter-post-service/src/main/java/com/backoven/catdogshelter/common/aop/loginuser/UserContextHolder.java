package com.backoven.catdogshelter.common.aop.loginuser;

public class UserContextHolder {
    // ThreadLocal을 쓰면 요청마다 userId가 격리되어 관리됨
    private static final ThreadLocal<Integer> userIdHolder = new ThreadLocal<>();

    public static void setUserId(int userId) {
        userIdHolder.set(userId);
    }

    public static int getUserId() {
        return userIdHolder.get();
    }

    // 요청이 끝나면 반드시 clear
    public static void clear() {
        userIdHolder.remove();
    }
}
