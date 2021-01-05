package com.lagou.zookeeper;
import org.I0Itec.zkclient.ZkClient;

public class CreateSession {
    public static void main(String[] args) {
        ZkClient zkClient = new ZkClient("linux01:2181");
        System.out.println("ZooKeeper session created.");

    }
}
