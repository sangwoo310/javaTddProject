package com.hsw.tdd.multiThread;

import java.util.ArrayList;

public class RunnableTdd {
    public static void main(String[] args) throws InterruptedException {

        System.out.println("Start main method.");

        Runnable r = new RunnableServiceTdd(); // 실제 구현한 Runnable 인터페이스
        ArrayList<Thread> threadList = new ArrayList<Thread>(); // 쓰레드들을 담을 객체

        for (int i = 0; i < 10; i++) {
            // Runnable 인터페이스를 사용해 새로운 쓰레드를 만듭니다.
            Thread test = new Thread(r);

            test.start(); // 이 메소드를 실행하면 Thread 내의 run()을 수행한다.
            threadList.add(test); // 생성한 쓰레드를 리스트에 삽입
        }

        for (Thread t : threadList) {
            t.join(); // 쓰레드의 처리가 끝날때까지 기다립니다.
        }

        System.out.println("End main method.");
    }
}
