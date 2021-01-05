package com.lsh.Thread;

public class Student {
    public Student() {

    }

    //**********字段*************//
    public String name;
    protected int age;
    char sex;
    private String phoneNum;
    public void show(){
        System.out.println("is show()");
    }

    @Override
    public String toString() {
        return "Student [name=" + name + ", age=" + age + ", sex=" + sex
                + ", phoneNum=" + phoneNum + "]";
    }
}
