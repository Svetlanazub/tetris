package com.epam.prejap.tetris;

import com.epam.prejap.tetris.block.BlockFeed;
import com.epam.prejap.tetris.game.Move;
import com.epam.prejap.tetris.game.Playfield;
import com.epam.prejap.tetris.game.Printer;
import com.epam.prejap.tetris.game.Waiter;
import com.epam.prejap.tetris.logger.Logger;
import com.epam.prejap.tetris.player.HumanPlayer;
import com.epam.prejap.tetris.player.Player;
import com.epam.prejap.tetris.player.RandomPlayer;


class Tetris {
    private final Playfield playfield;
    private final Waiter waiter;
    private final Player player;
    private static final Logger LOGGER = Logger.getLogger(Tetris.class);

    public Tetris(Playfield playfield, Waiter waiter, Player player) {
        this.playfield = playfield;
        this.waiter = waiter;
        this.player = player;
        LOGGER.trace("New {} object is created", getClass().getSimpleName());
    }

    public Score play() {
        LOGGER.info("Game was started");
        boolean moved;
        int score = 0;
        LOGGER.debug("Initial score is: {}", score);
        do {
            LOGGER.trace("Adding new block to the playfield");
            moved = false;

            playfield.nextBlock();
            score++;
            LOGGER.debug("Score updated to: {}", score);

            boolean nextMove;
            do {
                waiter.waitForIt();
                LOGGER.trace("Next move");
                Move move = player.nextMove()
                        .orElse(Move.NONE);
                LOGGER.trace("Next side to move the block: {}", move);
                moved |= (nextMove = playfield.move(move));
                if (move == Move.TO_BOTTOM_NOW) {
                    moved = true;
                }
                LOGGER.trace("The game continues: {}", moved);
            } while (nextMove);
            playfield.findAndRemoveFilledLines();
            LOGGER.trace("This block cannot be moved anymore");
            LOGGER.trace("There is a place for a new block: {}", moved);
        } while (moved);
        LOGGER.info("Game ended with score: {}", score);

        if (player instanceof HumanPlayer)
            ((HumanPlayer) player).killHumanPlayer();

        return new Score(score);
    }

    public static void main(String[] args) {
        Parameters parameters = new Parameters(args);
        int rows = parameters.getRows();
        int cols = parameters.getCols();
        int delay = parameters.getDelay();
        LOGGER.info("Starting the game with {} rows, {} columns, and a delay of {}ms", rows, cols, delay);

        Player player = new RandomPlayer();
        if (args.length > 0 && args[0].contains("human"))
            player = new HumanPlayer();

        LOGGER.info("The game will be played by {}", player.getClass()
                .getSimpleName());

        var feed = new BlockFeed();
        var printer = new Printer(System.out);
        var playfield = new Playfield(rows, cols, feed, printer);
        var game = new Tetris(playfield, new Waiter(delay), player);

        var score = game.play();

        SavedScore savedScore = new SavedScore("score_list.json");
        System.out.println(savedScore.toString());
      
        LOGGER.trace("Printing the score");
        System.out.println("Score: " + score.points());
        savedScore.writeSavedScore(score.points());
        LOGGER.info("Exiting te game");

    }
}
