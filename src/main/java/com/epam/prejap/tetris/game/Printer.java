package com.epam.prejap.tetris.game;

import com.epam.prejap.tetris.logger.Logger;

import java.io.PrintStream;

public class Printer {
    private static final Logger LOGGER = Logger.getLogger(Printer.class);

    private final PrintStream out;

    public Printer(PrintStream out) {
        this.out = out;
        LOGGER.trace("New {} object is created", getClass().getSimpleName());
    }

    void draw(Grid grid) {
        clear();
        LOGGER.trace("Drawing the grid");
        border(grid.columnsNumber);
        for (Grid.Row line : grid.getLines()) {
            startRow();
            line.getRow().forEach(this::print);
            endRow();
        }
        border(grid.columnsNumber);
    }

    void clear() {
        LOGGER.trace("Clearing the old grid");
        out.print("\u001b[2J\u001b[H");
    }

    void print(int dot) {
        out.format(dot == 0 ? " " :"#");
    }

    void startRow() {
        out.print("|");
    }

    void endRow() {
        out.println("|");
    }

    void border(int width) {
        out.println("+" + "-".repeat(width) + "+");
    }
}
