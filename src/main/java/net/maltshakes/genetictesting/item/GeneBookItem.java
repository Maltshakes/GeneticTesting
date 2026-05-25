package net.maltshakes.genetictesting.item;

import java.util.List;
import net.maltshakes.genetictesting.client.ClientTopologyBridge;
import net.maltshakes.genetictesting.genes.datamodel.BookEntry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class GeneBookItem extends Item {

    public GeneBookItem(Properties pProperties) {
        super(pProperties);
    }

    public class LivingEntityEA {
        public static CompoundTag getEntityNBT(LivingEntity entity) {
            CompoundTag tag = entity.serializeNBT();
            return tag;
        }
    }

    /**
     * Safely routes the genetic data to the client-side bridge for page generation. This execution
     * is gated behind a client-side level check to prevent server crashes when attempting to
     * resolve layout formatting and font-splitting metrics.
     *
     * @param itemStack The target stack where the formatted layout components will be saved.
     * @param entries The raw list of genetic information data entries to transform.
     * @param player The player executing the interaction with the entity.
     */
    public void savePagesToBook(ItemStack itemStack, List<BookEntry> entries, Player player) {
        CompoundTag tag = itemStack.getOrCreateTag();
        ListTag fullDataList = new ListTag();
        for (BookEntry entry : entries) {
            CompoundTag entryTag = new CompoundTag();
            entryTag.putString("type", entry.getType().name());
            entryTag.putString("label", Component.Serializer.toJson(entry.getLabel()));

            if (entry.getVal1() != null) {
                entryTag.putString("val1", Component.Serializer.toJson(entry.getVal1()));
            }
            if (entry.getVal2() != null) {
                entryTag.putString("val2", Component.Serializer.toJson(entry.getVal2()));
            }
            fullDataList.add(entryTag);
        }
        tag.put("FullGeneticsData", fullDataList);
    }

    /**
     * Called when the player right-clicks with the gene book in hand. If executed on the logical
     * client, it delegates to the client-side bridge to initialize and open the custom results
     * screen UI.
     *
     * @param level The level context in which the item is being used.
     * @param player The player using the item.
     * @param hand The hand holding the item.
     * @return A sided interaction result holder indicating success based on the current logical
     *     side.
     */
    @Override
    public InteractionResultHolder<ItemStack> use(
            Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (level.isClientSide) {
            triggerClientGui(itemstack, hand);
        }
        return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
    }

    @net.minecraftforge.api.distmarker.OnlyIn(net.minecraftforge.api.distmarker.Dist.CLIENT)
    private void triggerClientGui(ItemStack stack, InteractionHand hand) {
        ClientTopologyBridge.openCustomGeneGui(stack, hand);
    }
}
