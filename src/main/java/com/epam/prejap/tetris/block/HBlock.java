package com.epam.prejap.tetris.block;

import com.epam.prejap.tetris.logger.Logger;

/**
 * Implements H shaped block
 * @see com.epam.prejap.tetris.game.Printer#print(byte) 
 * @author Slawomir Kucab
 */
final class HBlock extends Block{
    private static final Logger LOGGER = Logger.getLogger(HBlock.class);

    /**
     * Byte array represents "S" block.
     * In game will be displayed as (between lines):
     *  -------------------
     *       # #
     *       ###
     *       # #
     *  -------------------
     */
    private static final byte[][] IMAGE = {
            {1, 0, 1},
            {1, 1, 1},
            {1, 0, 1}
    };
    public HBlock(){
        super(IMAGE);
        LOGGER.trace("{} was created", getClass().getSimpleName());
    }
}

