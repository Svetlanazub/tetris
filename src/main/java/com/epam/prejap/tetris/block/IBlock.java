package com.epam.prejap.tetris.block;

import com.epam.prejap.tetris.logger.Logger;

/**
 *  It creates I block of tetris
 *
 * @author Kanybek Mukalaev
 * @see BlockFeed
 * @see Block
 */
final class IBlock extends Block {

    /**
     * Byte array represents "S" block.
     * In game will be displayed as (between lines):
     *  -------------------
     *       #
     *       #
     *       #
     *       #
     *  -------------------
     */
    private static final byte [][] IMAGE = {
            {1},
            {1},
            {1},
            {1},
    };
    public IBlock(){
        super(IMAGE);
        Logger.trace("{} was created", getClass().getSimpleName());
    }
}
