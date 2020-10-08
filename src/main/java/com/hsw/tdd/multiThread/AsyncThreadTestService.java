package com.hsw.tdd.multiThread;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.RunnableFuture;

@Slf4j
//@Service
public class AsyncThreadTestService {
    int k = 0;

    public AsyncThreadTestService() {
    }

    private int k() {
        synchronized ("k") {
            k++;
            return k;
        }
    }

    @Async
    public CompletableFuture<Void> AnotationAsync() throws Exception {
//        logger.info
//        int a = i;
        log.info("{}", k());
        Thread.sleep(3000);

        synchronized ("1") {
            log.info("value is : {}", k);
        }

        return CompletableFuture.completedFuture(null);
    }

}
