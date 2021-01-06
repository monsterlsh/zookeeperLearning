package com.lagou.mysql_zk;

import org.I0Itec.zkclient.ZkClient;

public class Server {
    private ZkClient zkClient = null;

    private void Connect(){
        zkClient = new ZkClient("linux01:2181,linux10:2181,linux11:2181");
        if(!zkClient.exists("/mysql")){
            zkClient.createPersistent("/mysql");
        }
    }

    public static void main(String[] args) {

    }
}
