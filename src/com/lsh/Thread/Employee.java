package com.lsh.Thread;
import java.lang.*;

public abstract class Employee  {

   private String name;
    private int salary;
    Employee(){}
    Employee(String name){
        this.name = name;
    }
    public abstract void  f();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public    void fn(){
        System.out.println("this is employee" + getName());
    }
    protected void pro(){
        System.out.println("this protect");
    }


    public static void main(String[] args) {
           // Manager a = new Manager("ll");
       // System.out.println(a.getName());
       // a.setName();
        Manager e = new Manager("BOB",20);
       // e.fn();
      //  System.out.println(e.getBouns());
       // Manager b = e.clone();
        /*e  = (Manager)staff;
        staff = e;
        e  = (Manager)staff;
        System.out.println(e.getBouns());*/
    }

}
class Manager extends  Employee{
    int bouns;
    public int getBouns() {
        return bouns;
    }
    public void f(){
        System.out.println("this is from abstract");
    }
    public void setBouns(int bouns) {
        this.bouns = bouns;
    }


    Manager(String name,int bouns){
        super(name);
        this.bouns = bouns;
    }
    public  void fn(){
        System.out.println("this is Manager" + getName());
    }
}