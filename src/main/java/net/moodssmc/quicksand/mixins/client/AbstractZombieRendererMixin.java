package net.moodssmc.quicksand.mixins.client;

import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.renderer.entity.AbstractZombieRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.world.entity.monster.Zombie;
import net.moodssmc.quicksand.util.Shakable;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(AbstractZombieRenderer.class)
public abstract class AbstractZombieRendererMixin<T extends Zombie, M extends ZombieModel<T>> extends HumanoidMobRenderer<T, M>
{
    public AbstractZombieRendererMixin(EntityRendererProvider.Context p_174169_, M p_174170_, float p_174171_) {
        super(p_174169_, p_174170_, p_174171_);
    }

    @Override
    protected boolean isShaking(@NotNull T zombie)
    {
        return super.isShaking(zombie) || ((Shakable) zombie).isShaking();
    }
}
