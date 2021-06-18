package com.epam.prejap.tetris.block;

import org.tinylog.Logger;

import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class BlockFeed {

    private final Random rnd = new Random();
    private final List<Supplier<Block>> blocks = List.of(
            HBlock::new,
            IBlock::new,
            OBlock::new,
            SBlock::new,
            TBlock::new,
            YBlock::new
    );

    public BlockFeed() {
        Logger.trace("New {} is created", getClass().getSimpleName());
    }

    public Block nextBlock() {
        return blocks.get(rnd.nextInt(blocks.size())).get();
    }
}
