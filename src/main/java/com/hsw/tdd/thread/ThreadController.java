package com.hsw.tdd.thread;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Controller
@Slf4j
public class ThreadController {

    @Autowired
    ThreadService threadService;

    int number;

    public void start() throws Exception {
        // Executor 프레임워크
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(5);
        executor.setQueueCapacity(50);
        executor.setThreadNamePrefix("hsw-thread-test");
        executor.initialize();

//        SynchronousQueue synchronousQueue = new SynchronousQueue();

        // thread 결과에 대한 저장소 => thread blocking 을 위한 arrayList 처리
        List<CompletableFuture<Void>> threadResult = new ArrayList<>();
        for (int i = 0; i < 60; i++) {

            // Executor 프레임워크의 Thread Pool Queue 사이즈 오버 방지
            while (true) {
                log.info("Queue size is : {}", executor.getThreadPoolExecutor().getQueue().size());
                if (executor.getThreadPoolExecutor().getQueue().size() >= 50) {

                    log.info("Queue is out of range");
                    log.info("wow : {}", executor.getThreadPoolExecutor().getQueue().size());
                    Thread.sleep(500);
                } else {
                    log.info("Pass");
                    break;
                }
            }

            // 클로져를 통해 multi thread 중복 방지
            number = i;

            // CompletableFuture 을 통한 ThreadPool 동작 및 run
            CompletableFuture<Void> threadPoolTest = CompletableFuture.supplyAsync(() -> {
                try {
                    threadService.threadRun(number);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }, executor);

            // Multi Thread 결과 통합 및 lock
            threadResult.add(threadPoolTest);
        }

        // 결과 통합, 응답순서 정렬
        CompletableFuture<Void> allFutures = CompletableFuture
                .allOf(threadResult.toArray(new CompletableFuture[threadResult.size()]));
        allFutures.get();

    }

}
