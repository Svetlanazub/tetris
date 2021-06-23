package com.epam.prejap.tetris.block;

import com.epam.prejap.tetris.logger.Logger;

final class OBlock extends Block {
    private static final Logger LOGGER = Logger.getLogger(OBlock.class);

    /**
     * Byte array represents "O" block.
     * In game will be displayed as (between lines):
     *  -------------------
     *      ##
     *      ##
     *  -------------------
     */
    private static final byte[][] IMAGE = {
        {1, 1},
        {1, 1},
    };

    public OBlock() {
        super(IMAGE);
        LOGGER.trace("{} was created", getClass().getSimpleName());
    }

}
