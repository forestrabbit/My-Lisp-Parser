package com.fr;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class Eval {
    public Eval() {
        try {
            //添加你想要的函数
            Function.env.put("+", Class.forName("com.fr.Function").getMethod("add", Object[].class));
            Function.env.put("-", Class.forName("com.fr.Function").getMethod("sub", Object[].class));
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public Object eval(Object parseAns) {
        if (!(parseAns instanceof List)) {
            var token = (Token) parseAns;
            if (token.getType().equals("id")) {
                return Function.env.get(token.getData());
            } else {
                return token.getData();
            }
        } else {
            var list = (List<Object>) parseAns;
            var func = (Method) eval(list.get(0));
            try {
                Object[] args = new Object[list.size() - 1];
                for (int i = 0; i < args.length; i++) {
                    args[i] = eval(list.get(i + 1));
                }
                return func.invoke(null, new Object[]{args});
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}