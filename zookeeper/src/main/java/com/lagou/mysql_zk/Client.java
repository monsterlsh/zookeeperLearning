package com.lagou.mysql_zk;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

public class Client {
    private Properties properties;
    private   DataSource dataSource;
    private ZkClient zkClient = null;
    private Connection con = null;
    private Map<String,String> map  = null;
    List<String> children = null;
    //private DruidUtils druidUtils = null;
    private void ConnectZk(){
        zkClient = new ZkClient("linux01:2181,linux10:2181,linux11:2181");
        zkClient.setZkSerializer(new ZkStrSerializer());
        if(!zkClient.exists("/mysql")){
            zkClient.createPersistent("/mysql");
        }
        children = zkClient.getChildren("/mysql");
        for (String child : children) {
            zkClient.subscribeDataChanges("/mysql/"+child, new IZkDataListener() {
                @Override
                public void handleDataChange(String s, Object o) throws Exception {
                    System.out.println("****配置文件的属性  "+s+"  值发生变更为*** " + o);
                    System.out.println("\n======end=====\n\n");
                }

                @Override
                public void handleDataDeleted(String s) throws Exception {
                    System.out.println("*****配置文件 "+s +" 属性被删除****");
                    System.out.println("\n======end=====\n\n");
                }
            });
        }
        zkClient.subscribeChildChanges("/mysql", new IZkChildListener() {
            @Override
            public void handleChildChange(String s, List<String> list) throws Exception {

                System.out.println("****配置文件的属性值变更****");
                //System.out.println(list );
                for (String child : list) {
                    Object o = zkClient.readData("/mysql/" + child);
                    children.add(String.valueOf(o));
                }
                System.out.println("\n======end=====\n\n");
            }

        });


    }
    public  void ConnectMysql(String str){
        //判断配置文件有没有改变
        boolean flag = false;
        try {
            //第一次设置数据库配置信息
            if (properties == null) {
                properties = new Properties();
                InputStream inputStream = DruidUtils.class.getClassLoader().getResourceAsStream(str);
                properties.load(inputStream);
                if(map == null && properties!=null){
                    map = new HashMap<>();
                    readMap(properties);
                }

                //初始化数据库连接池
                setDataSource();
                //初始化zookeeper
                setZkClientChildren();
            }
            //新的配置文件读取
            else {
                Properties newPro = new Properties();
                InputStream inputStream = DruidUtils.class.getClassLoader().getResourceAsStream(str);
                newPro.load(inputStream);
                //新配置文件信息
                Set<String> names = newPro.stringPropertyNames();
                Iterator<String> iterator = names.iterator();
                Map<String,String> newMap = new HashMap<>();

                while (iterator.hasNext()){
                    String key = iterator.next();
                    String value = newPro.getProperty(key);
                    String originValue = map.get(key);
                    if(!value.equals(originValue)) // 有key 不同value：oriValue！=null  或者 无key originvalue==null
                    {
                        flag = true; //表示有变化
                        //map.put(key,value);
                    }
                    newMap.put(key,value);
                }
                //更新map 和 properties
                //有变更 更新连接 和 zookeeper
                if(flag){
                    System.out.println(str);
                    map = newMap;
                    properties = newPro;
                    setDataSource();
                    setZkClientChildren();


                }

            }
            //获取连接
           // con = getConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void setDataSource(){
        this.close(con);
        try {
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    public void setZkClientChildren()  {
        //更新zookeeper信息
        Set<String> keys = map.keySet();
        Iterator<String> iterator = keys.iterator();
        List<String> children = zkClient.getChildren("/mysql");
        for (String child : children) {
            if (!map.containsKey(child)){
                zkClient.delete("/mysql/"+child);

            }else{
                Object o = zkClient.readData("/mysql/" + child);
                String oldData = String.valueOf(o);
                String newData = map.get(child);
                if(!oldData.equals(oldData)){
                    zkClient.writeData("/mysql/" + child,newData);
                    //System.out.println("/mysql/"+key+"====新数据==="+newData);
                    //Thread.sleep(1000);
                }
            }
        }
        while (iterator.hasNext()){
            String key = iterator.next();
            String value = map.get(key);
            if(!zkClient.exists("/mysql/"+key)){
                zkClient.createPersistent("/mysql/"+key,value);
                //System.out.println("/mysql/"+key+"===="+value);
                //Thread.sleep(1000);
            }
            else{
                Object o = zkClient.readData("/mysql/" + key);
                String newData = String.valueOf(o);
                if(!value.equals(newData)){
                    zkClient.writeData("/mysql/" + key,value);
                    //System.out.println("/mysql/"+key+"====新数据==="+newData);
                    //Thread.sleep(1000);
                }
            }
        }
    }

    public void readMap(Properties pro){
        Set<String> name = pro.stringPropertyNames();
        Iterator<String> iterator = name.iterator();
        while (iterator.hasNext()){
            String key = iterator.next();
            String value = pro.getProperty(key);
            map.put(key,value);
        }
    }

    public  Connection getConnection(){
        try {
            return dataSource.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }
    public void close(Connection con){
        if (con!=null){
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.ConnectZk();
        String [] str = {"druid.properties","druid2.properties"};
        Random random = new Random();
        while(true){
            int i = random.nextInt(2);
            client.ConnectMysql(str[i]);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
}
