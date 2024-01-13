package cn.solarmoon.immersive_delight.data.tags;

import cn.solarmoon.immersive_delight.ImmersiveDelight;
import cn.solarmoon.immersive_delight.common.IMBlocks;
import cn.solarmoon.immersive_delight.common.IMEntityBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class IMBlockTags extends BlockTagsProvider {

    public IMBlockTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, ImmersiveDelight.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        registerModTags();
    }

    protected void registerModTags() {
        //擀面杖快速收割
        tag(CAN_BE_ROLLED).add(
                IMBlocks.WHEAT_DOUGH.get(),
                IMBlocks.FLATBREAD_DOUGH.get()
        );
        //可被镐子挖
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                IMEntityBlocks.KETTLE.get()
        );
        //热源
        tag(HEAT_SOURCE).add(
                Blocks.FIRE,
                Blocks.MAGMA_BLOCK,
                Blocks.LAVA
        );
    }

    public static final TagKey<Block> CAN_BE_ROLLED = blockTag("can_be_rolled");
    public static final TagKey<Block> HEAT_SOURCE = blockTag("heat_source");

    private static TagKey<Block> blockTag(String path) {
        return BlockTags.create(new ResourceLocation(ImmersiveDelight.MOD_ID, path));
    }

}
