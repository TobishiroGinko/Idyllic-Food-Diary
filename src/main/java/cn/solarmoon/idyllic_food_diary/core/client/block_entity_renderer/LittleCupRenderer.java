package cn.solarmoon.idyllic_food_diary.core.client.block_entity_renderer;

import cn.solarmoon.idyllic_food_diary.api.common.block.entity_block.AbstractCupEntityBlock;
import cn.solarmoon.idyllic_food_diary.api.util.FluidRenderUtil;
import cn.solarmoon.idyllic_food_diary.core.common.block_entity.CupBlockEntity;
import cn.solarmoon.solarmoon_core.api.client.blockEntityRenderer.BaseBlockEntityRenderer;
import cn.solarmoon.solarmoon_core.api.util.FluidUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;

public class LittleCupRenderer<E extends CupBlockEntity> extends BaseBlockEntityRenderer<E> {

    public LittleCupRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void render(E blockEntity, float tickDelta, PoseStack poseStack, MultiBufferSource buffer, int light, int overlay) {
        IFluidHandler tank = FluidUtil.getTank(blockEntity);
        if (tank == null || tank.getFluidInTank(0) == null) {
            return;
        }
        FluidStack fluidStack = tank.getFluidInTank(0);

        // 渲染流体过渡动画
        float zoom = 3 / 16f;
        int ticks = blockEntity.getTicks();
        float progress = ticks * 0.01f;
        float targetScale = FluidUtil.getScale(blockEntity.getTank());
        blockEntity.setLast(targetScale - (targetScale - blockEntity.getLast()) * (1 - progress));

        int targetColor = FluidRenderUtil.getColor(fluidStack);
        IClientFluidTypeExtensions fluidAttributes = IClientFluidTypeExtensions.of(fluidStack.getFluid());
        ResourceLocation spriteLocation = fluidAttributes.getStillTexture(fluidStack);

        poseStack.pushPose();
        float posB = 6.5f / 16f;
        poseStack.translate(posB, 0.09375f, posB);
        poseStack.scale(zoom, blockEntity.getLast() * 0.1875f, zoom);
        if (spriteLocation != null) {
            FluidRenderUtil.renderFluid(targetColor, 0, spriteLocation, poseStack, buffer, light, overlay);
        }
        poseStack.popPose();

        //渲染物品
        //让光度和环境一致
        light = blockEntity.getLevel() != null ? LevelRenderer.getLightColor(blockEntity.getLevel(), blockEntity.getBlockPos().above()) : 15728880;
        poseStack.pushPose();
        poseStack.translate(0.5, 0.15, 0.5);
        //根据面朝方向决定旋转角
        poseStack.mulPose(Axis.YP.rotationDegrees(blockEntity.getBlockState().getValue(AbstractCupEntityBlock.FACING).toYRot() + 45));
        poseStack.scale(0.5f, 0.5f, 0.5f);
        itemRenderer.renderStatic(blockEntity.getInventory().getStackInSlot(0), ItemDisplayContext.GROUND, light, overlay, poseStack, buffer, blockEntity.getLevel(), (int) blockEntity.getBlockPos().asLong());
        poseStack.popPose();
    }

}