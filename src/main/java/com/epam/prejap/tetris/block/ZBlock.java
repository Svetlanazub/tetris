package com.epam.prejap.tetris.block;

import com.epam.prejap.tetris.logger.Logger;

/**
 * Z block implementation.
 *
 * @author Łukasz Niedziela
 * @see Block
 */
final class ZBlock extends Block {
    private static final Logger LOGGER = Logger.getLogger(ZBlock.class);

    /**
     * Byte array represents "Z" block.
     * In game will be displayed as (between lines):
     *  -------------------
     *      ##
     *       ##
     *  -------------------
     */
    private static final byte[][] IMAGE = {
            {1, 1, 0},
            {0, 1, 1}
    };

    public ZBlock() {
        super(IMAGE);
        LOGGER.trace("{} was created", getClass().getSimpleName());
    }
}
