package com.lsh.Thread;

public class Circulate {
    private void fun1(){
        System.out.println("fun1");
        fun2();
    }
    private void fun2(){
        System.out.println("fun2");
        fun3();
    }
    private void fun3(){
        System.out.println("fun3");
        fun1();
    }

    public static void main(String[] args) {
        Circulate cu = new Circulate();
      try {
          cu.fun1();
      }catch (StackOverflowError error){
          System.out.println("nono");
          error.printStackTrace();
      }
    }
}
