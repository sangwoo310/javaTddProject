package com.hsw.tdd.timer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

import java.util.Timer;
import java.util.TimerTask;

@Slf4j
@Controller
public class TimerController implements Runnable {
    @Override
    public void run() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new AddressGeneratorDaemon(), (long) 0, (long)1);

    }

    class AddressGeneratorDaemon extends TimerTask {
        @Override
        public void run() {
            timerTestMethod();
        }
    }

    private void timerTestMethod() {
        try {
            Thread.sleep(3000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("timerTest");

    }

}
