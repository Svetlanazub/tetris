package com.epam.prejap.tetris.game;

import com.epam.prejap.tetris.logger.Logger;

import java.util.concurrent.TimeUnit;

public class Waiter {
    private static final Logger LOGGER = Logger.getLogger(Waiter.class);
    private final int milliseconds;

    public Waiter(int milliseconds) {
        this.milliseconds = milliseconds;
        LOGGER.trace("New {} is created with a delay of {}ms", getClass().getSimpleName(), milliseconds);
    }

    public void waitForIt() {
        try {
            TimeUnit.MILLISECONDS.sleep(milliseconds);
        } catch (InterruptedException ignore) {
        }
        LOGGER.trace("Waited {} milliseconds", milliseconds);
    }
}
