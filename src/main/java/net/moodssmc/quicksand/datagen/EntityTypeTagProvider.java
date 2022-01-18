package net.moodssmc.quicksand.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.moodssmc.quicksand.Reference;
import net.moodssmc.quicksand.core.ModTags;
import org.jetbrains.annotations.Nullable;

public class EntityTypeTagProvider extends EntityTypeTagsProvider
{
    public EntityTypeTagProvider(DataGenerator generator, @Nullable ExistingFileHelper existingFileHelper)
    {
        super(generator, Reference.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags()
    {
        this.tag(ModTags.QUICKSAND_WALKABLE_MOBS).add(EntityType.WITHER);
    }
}
