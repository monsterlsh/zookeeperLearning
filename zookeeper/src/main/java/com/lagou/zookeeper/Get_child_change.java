package com.lagou.zookeeper;

import jdk.swing.interop.SwingInterOpUtils;
import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;

import java.util.List;

public class Get_child_change {
    public static void main(String[] args) throws InterruptedException {
        final ZkClient zkClient = new ZkClient("linux01:2181");
        zkClient.subscribeChildChanges("/lg-client", new IZkChildListener() {
            @Override
            public void handleChildChange(String s, List<String> list) throws Exception {
                System.out.println(s + " child change " + list);
            }
        });

        zkClient.createPersistent("/lg-client");
        Thread.sleep(1000);
        zkClient.createPersistent("/lg-client/c1");
        Thread.sleep(1000);
        zkClient.delete("/lg-client/c1");
        Thread.sleep(1000);
        zkClient.delete("/lg-client");
        Thread.sleep(Integer.MAX_VALUE);

    }
}
