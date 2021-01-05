package com.lsh.pox;

import java.lang.reflect.Proxy;

public class App {
    public static void main(String[] args) {
        // 目标对象
        IUserDao target = new UserDao();
        // 【原始的类型 class cn.itcast.b_dynamic.UserDao】
        System.out.println(target.getClass());

        //方法一 给目标对象，创建代理对象
       /* IUserDao proxy = (IUserDao) new ProxyFactory(target).getProxyInstance();
        // class $Proxy0   内存中动态生成的代理对象
        System.out.println(proxy.getClass());

        // 执行方法   【代理对象】
        proxy.save();*/

      //方法二
        DynamicProxy inter = new DynamicProxy(target);
        //获取代理类
        IUserDao userDao = (IUserDao)(Proxy.newProxyInstance(IUserDao.class.getClassLoader(), new Class[] {IUserDao.class}, inter));
        //通过代理类对象调用代理类方法，实际上会转到invoke方法调用
        userDao.save(5,"lsh");
    }
}
