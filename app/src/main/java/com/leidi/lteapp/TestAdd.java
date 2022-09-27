package com.leidi.lteapp;

import java.util.Random;

public class TestAdd {
    public static void main(String[] args) {
        int a = 0;
        Long start = System.currentTimeMillis();
        Random random = new Random();
        for (int i = 0; i < 3000; i++) {
            int num = random.nextInt(10000);
            System.out.println("==" + num);
            a = a + num;
        }
        Long end = System.currentTimeMillis();
        System.out.println("========" + a);
        System.out.println("=====总计用时===" + (end - start)+"毫秒");
    }
}
