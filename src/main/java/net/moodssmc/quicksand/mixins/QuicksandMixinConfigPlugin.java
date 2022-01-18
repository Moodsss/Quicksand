package net.moodssmc.quicksand.mixins;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

public class QuicksandMixinConfigPlugin implements IMixinConfigPlugin
{
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String MIXIN_PACKAGE_ROOT = "net.moodssmc.quicksand.mixins";

    @Override
    public void onLoad(String mixinPackage)
    {
        //NO-OP
    }

    @Override
    public String getRefMapperConfig()
    {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName)
    {
        if (!mixinClassName.startsWith(MIXIN_PACKAGE_ROOT))
        {
            LOGGER.error("Expected mixin '{}' to start with package root '{}', treating as foreign and " +
                    "disabling!", mixinClassName, MIXIN_PACKAGE_ROOT);

            return false;
        }
        return true;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets)
    {
        //NO-OP
    }

    @Override
    public List<String> getMixins()
    {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo)
    {
        //NO-OP
    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo)
    {
        //NO-OP
    }
}
