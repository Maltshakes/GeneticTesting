package net.maltshakes.genetictesting.item;

import java.util.List;
import net.maltshakes.genetictesting.client.GeneBookDisplay;
import net.maltshakes.genetictesting.client.gui.ResultsBookScreen;
import net.maltshakes.genetictesting.genes.datamodel.BookEntry;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

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
     * Saves the pages to a book.
     *
     * @param itemStack the itemstack being used
     * @param entries a list of BookEntry to save in the book
     * @param player the player interacting with the book
     */
    public void savePagesToBook(ItemStack itemStack, List<BookEntry> entries, Player player) {
        CompoundTag tag = itemStack.getOrCreateTag();
        ListTag pagesList = new ListTag();
        GeneBookDisplay geneDisplay = new GeneBookDisplay(entries);

        for (GeneBookDisplay.Page page : geneDisplay.getPages()) {
            String jsonPage = Component.Serializer.toJson(page.toComponent());
            pagesList.add(StringTag.valueOf(jsonPage));
        }
        tag.put("pages", pagesList);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(
            Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (level.isClientSide) {
            openCustomGeneGui(itemstack);
        }
        return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
    }

    @OnlyIn(Dist.CLIENT)
    private void openCustomGeneGui(ItemStack stack) {
        // Sets the colour of the book
        int colour =
                stack.getOrCreateTag().contains("BookColour")
                        ? stack.getTag().getInt("BookColour")
                        : 0x99452E; // Default Brown
        GeneBookDisplay display = new GeneBookDisplay(stack);
        // Set the screen
        Minecraft.getInstance()
                .setScreen(
                        new ResultsBookScreen(Component.literal("Gene Results"), display, colour));
    }
}
