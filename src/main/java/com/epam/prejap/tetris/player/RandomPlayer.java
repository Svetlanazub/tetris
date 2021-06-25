package com.epam.prejap.tetris.player;

import com.epam.prejap.tetris.game.Move;
import com.epam.prejap.tetris.logger.Logger;

import java.util.Optional;
import java.util.Random;

public class RandomPlayer implements Player {
    private static final Logger LOGGER = Logger.getLogger(RandomPlayer.class);
    private final Random random = new Random();

    public RandomPlayer(){
        LOGGER.trace("New {} object is created", getClass().getSimpleName());
    }

    @Override
    public Optional<Move> nextMove() {
        return Optional.of(Move.values()[random.nextInt(Move.values().length - 1)]);
    }

}
