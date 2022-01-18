package net.moodssmc.quicksand.events;

import com.google.common.base.Preconditions;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.Event;

@Cancelable
public class RenderQuicksandOverlayEvent extends Event
{
    private final Player player;
    private PoseStack poseStack;

    public RenderQuicksandOverlayEvent(Player player, PoseStack poseStack)
    {
        this.player = Preconditions.checkNotNull(player, "player");
        this.poseStack = Preconditions.checkNotNull(poseStack, "poseStack");
    }

    public Player getPlayer()
    {
        return player;
    }

    public void setPoseStack(PoseStack poseStack)
    {
        this.poseStack = poseStack;
    }

    public PoseStack getPoseStack()
    {
        return poseStack;
    }
}
