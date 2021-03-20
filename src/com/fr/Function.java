package com.fr;

import java.util.*;

public class Function {
    public static Map<String, Object> env = new HashMap<>();

    //添加你想要的函数
    public static double add(Object... nums) {
        double ans = 0;
        for (var x: nums) {
            ans += (double) x;
        }
        return ans;
    }

    public static double sub(Object... nums) {
        double ans = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                ans = (double) nums[0];
            } else {
                ans -= (double) nums[i];
            }
        }
        return ans;
    }
}