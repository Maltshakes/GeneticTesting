package net.maltshakes.genetictesting.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class LivestockTapeMeasureItem extends Item {
    public LivestockTapeMeasureItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult interactLivingEntity(
            ItemStack inStack,
            Player inPlayer,
            LivingEntity inInteractionTarget,
            InteractionHand inUsedHand) {
        String entityType = EntityType.getKey(inInteractionTarget.getType()).toString();
        String heightUnit = "m";
        // Find Entity Type dimensions
        EntityDimensions dims = inInteractionTarget.getDimensions(inInteractionTarget.getPose());
        // Transform height (from float) to a string
        String height = Float.toString(dims.height);
        String message = "This " + entityType + " is " + height + heightUnit + " tall.";
        inPlayer.sendSystemMessage(Component.literal(message));
        return InteractionResult.sidedSuccess(inPlayer.level().isClientSide);
    }
}
