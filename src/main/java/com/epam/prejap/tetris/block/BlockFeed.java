package com.epam.prejap.tetris.block;

import com.epam.prejap.tetris.logger.Logger;

import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class BlockFeed {
    private static final Logger LOGGER = Logger.getLogger(BlockFeed.class);

    private final Random rnd = new Random();
    private final List<Supplier<Block>> blocks = List.of(
            HBlock::new,
            IBlock::new,
            OBlock::new,
            SBlock::new,
            TBlock::new,
            YBlock::new,
            ZBlock::new
    );

    public BlockFeed() {
        LOGGER.trace("New {} object is created", getClass().getSimpleName());
    }

    public Block nextBlock() {
        return blocks.get(rnd.nextInt(blocks.size())).get();
    }
}
