package com.hsw.tdd.multiThread;

import java.util.Random;

public class RunnableServiceTdd implements Runnable {
    private int index = 0;

    @Override
    public void run() {

//        Random r = new Random(System.currentTimeMillis());
//
//        long s = r.nextInt(3000); // 3초내로 끝내자.
//        try {
//            Thread.sleep(s); // 쓰레드를 잠시 멈춤
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        addIndex();

    }

    // 내부 변수를 동기화해서 사용!
    // 동기화 이유가 궁금하다면 synchronized 키워드를 삭제 해서 여러번 돌려보세요!
//    synchronized void addIndex(){
    void addIndex() {
        index++;
        System.out.println("current index value: " + index);
    }
}

