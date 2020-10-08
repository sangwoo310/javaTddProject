package com.hsw.tdd.tempTestPackage;

import com.hsw.tdd.multiThread.AsyncThreadTestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


public class TempTest {
    @Test
    public void ForLoop() throws ExecutionException, InterruptedException {
        // List for multi thread  result set
        List<CompletableFuture<Void>> threadResult = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            int a = i;
            // Use CompletableFuture for multi thread to keyProvider
            CompletableFuture<Void> keyProviderResult = CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("" + a);
                return null;
            });

            threadResult.add(keyProviderResult);
        }

        // Wait and Get from multi thread result
        CompletableFuture<Void> allFutures = CompletableFuture
                .allOf(threadResult.toArray(new CompletableFuture[threadResult.size()]));
        allFutures.get();
    }

//    @Autowired
//    private AsyncThreadTestService as;

    @Test
    public void AsyncTest() {
        AsyncThreadTestService as = new AsyncThreadTestService();
        for (int i = 0; i < 100000; i++) {
//            as.AnotationAsync();
        }
    }

    @Async
    public void AnotationAsync(int i) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("" + i);
    }

    public void fefef() {
    }
}
