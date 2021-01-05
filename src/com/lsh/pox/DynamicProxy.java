package com.lsh.pox;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicProxy implements InvocationHandler {
    //obj为委托类对象;
    private Object obj;

    public DynamicProxy(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before");
        System.out.println(method.getName());
        if(args == null) System.out.println("no args");
        else {
            for (int i = 0; i <args.length;i++){
                System.out.println("args[" + i +"] = " + args[i]);
            }
        }
        Object result = method.invoke(obj, args);
        System.out.println("after");
        return result;
    }
}
