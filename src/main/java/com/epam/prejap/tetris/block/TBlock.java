package com.epam.prejap.tetris.block;

import com.epam.prejap.tetris.logger.Logger;

/**
 * Generates a "T" block in the Tetris game - when "1" in a byte array is present then a "#" is printed,
 * otherwise a whitespace (" ") is printed.
 * @see com.epam.prejap.tetris.game.Printer#print(byte)
 * @author Grzegorz Konopka
 */
final class TBlock extends Block {
    private static final Logger LOGGER = Logger.getLogger(TBlock.class);

    /**
     * Byte array represents "Y" block.
     * In game will be displayed as (between lines):
     *  -------------------
     *      ###
     *       #
     *  -------------------
     */
    private static final byte[][] IMAGE = {
            {1,1,1},
            {0,1,0}
    };

    TBlock() {
        super(IMAGE);
        LOGGER.trace("{} was created", getClass().getSimpleName());
    }
}

