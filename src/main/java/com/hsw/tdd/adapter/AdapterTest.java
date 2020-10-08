package com.hsw.tdd.adapter;

//import org.apache.kafka.common.serialization.StringSerializer;

public class AdapterTest {
    private String tt;

    public AdapterTest(String qq) {
        System.out.println("constructor : " + qq);
//        this.tt = qq;
    }

    public String So() {
        return "tt";
    }
}
