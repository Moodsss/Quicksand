package net.moodssmc.quicksand.mixins.render;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Matrix4f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.ScreenEffectRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.Event;
import net.moodssmc.quicksand.Reference;
import net.moodssmc.quicksand.blocks.QuicksandBlock;
import net.moodssmc.quicksand.events.RenderQuicksandOverlayEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ScreenEffectRenderer.class)
@OnlyIn(Dist.CLIENT)
public class ScreenEffectRendererMixin
{
    private static final ResourceLocation QUICKSAND_OVERLAY = new ResourceLocation(Reference.MOD_ID, "textures/overlay/quicksand.png");

    @Inject(method = "renderScreenEffect", at = @At("HEAD"))
    private static void onRenderScreenEffect(Minecraft minecraft, PoseStack stack, CallbackInfo ci)
    {
        Player player = minecraft.player;
        if(player != null)
        {
            if (!player.isSpectator())
            {
                RenderQuicksandOverlayEvent event = new RenderQuicksandOverlayEvent(player, stack);
                if(!postEvent(event))
                {
                    PoseStack newStack = event.getPoseStack();
                    if(QuicksandBlock.isEntityFacingBlock(player.level, player))
                    {
                        renderQuicksandOverlay(player, newStack);
                    }
                }
            }
        }
    }

    @Unique
    private static void renderQuicksandOverlay(Player player, PoseStack stack)
    {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.enableTexture();
        RenderSystem.setShaderTexture(0, QUICKSAND_OVERLAY);

        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);
        RenderSystem.enableBlend();
        RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE);
        RenderSystem.setShaderColor(0F, 0F, 0F, 1F);
        RenderSystem.setShaderColor(0.025F, 0.025F, 0.025F, 0.01F);

        BufferBuilder bufferBuilder = Tesselator.getInstance().getBuilder();
        bufferBuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);

        addTexQuad(bufferBuilder, stack.last().pose(), player);

        bufferBuilder.end();
        BufferUploader.end(bufferBuilder);
        RenderSystem.setShaderColor(1F, 1F, 1F, 1F);
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableBlend();
        RenderSystem.depthMask(true);
        RenderSystem.enableDepthTest();
    }

    @Unique
    private static void addTexQuad(VertexConsumer consumer, Matrix4f matrix, Player player)
    {
        float rotationY = -player.getYRot() / 64F;
        float rotationX = player.getXRot() / 64F;

        consumer.vertex(matrix, -1.0F, -1.0F, -0.5F).uv(4.0F + rotationY, 4.0F + rotationX).endVertex();
        consumer.vertex(matrix, 1.0F, -1.0F, -0.5F).uv(0.0F + rotationY, 4.0F + rotationX).endVertex();
        consumer.vertex(matrix, 1.0F, 1.0F, -0.5F).uv(0.0F + rotationY, 0.0F + rotationX).endVertex();
        consumer.vertex(matrix, -1.0F, 1.0F, -0.5F).uv(4.0F + rotationY, 0.0F + rotationX).endVertex();
    }

    @Unique
    private static boolean postEvent(Event event)
    {
        return MinecraftForge.EVENT_BUS.post(event);
    }
}
