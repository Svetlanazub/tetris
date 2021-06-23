package com.epam.prejap.tetris.block;

import com.epam.prejap.tetris.logger.Logger;

/**
 * Represents Y block in BlockFeed.
 *
 * @author Anna Silenko
 */
final class YBlock extends Block {

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
        Logger.trace("{} was created", getClass().getSimpleName());
    }
}
