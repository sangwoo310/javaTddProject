package com.hsw.tdd.thread;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ThreadService {
    public void threadRun(int number) throws Exception {
        log.info("threadRun is start");
        Thread.sleep(1000);
        log.info("Thread run finish");
    }
}
