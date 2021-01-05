package com.lsh.Exceptin;



import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    static final Log log = LogFactory.getLog(Main.class);
    static void foo() {
    log.info("foo");
}
    public static void main(String[] args) {

    foo();

    }
}
