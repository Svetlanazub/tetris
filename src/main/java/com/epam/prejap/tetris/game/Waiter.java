package com.epam.prejap.tetris.game;

import org.tinylog.Logger;

import java.util.concurrent.TimeUnit;

public class Waiter {

    private final int milliseconds;

    public Waiter(int milliseconds) {
        this.milliseconds = milliseconds;
        Logger.trace("New {} is created with a delay of {}ms", getClass().getSimpleName(), milliseconds);
    }

    public void waitForIt() {
        try {
            TimeUnit.MILLISECONDS.sleep(milliseconds);
        } catch (InterruptedException ignore) {
        }
        Logger.trace("Waited {} milliseconds", milliseconds);
    }
}
