package com.epam.prejap.tetris.game;

import com.epam.prejap.tetris.logger.Logger;

import java.io.PrintStream;

public class Printer {

    private final PrintStream out;

    public Printer(PrintStream out) {
        this.out = out;
        Logger.trace("New {} is created", getClass().getSimpleName());
    }

    void draw(Grid grid) {
        clear();
        Logger.trace("Drawing the grid");
        border(grid.columnsNumber);
        for (Grid.Row line : grid.getLines()) {
            startRow();
            line.getRow().forEach(this::print);
            endRow();
        }
        border(grid.columnsNumber);
    }

    void clear() {
        Logger.trace("Clearing the old grid");
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
