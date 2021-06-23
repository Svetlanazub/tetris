package com.epam.prejap.tetris.block;

import com.epam.prejap.tetris.logger.Logger;

/**
 * Represents Y block in BlockFeed.
 *
 * @author Anna Silenko
 */
final class YBlock extends Block {
    private static final Logger LOGGER = Logger.getLogger(YBlock.class);

    /**
     * Byte array represents "S" block.
     * In game will be displayed as (between lines):
     *  -------------------
     *      # #
     *       #
     *       #
     *  -------------------
     */
    private static final byte[][] IMAGE = {
            {1, 0, 1},
            {0, 1, 0},
            {0, 1, 0}
    };

    public YBlock() {
        super(IMAGE);
        LOGGER.trace("{} was created", getClass().getSimpleName());
    }
}
