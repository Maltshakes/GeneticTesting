package net.maltshakes.genetictesting.client;

import java.util.ArrayList;
import java.util.List;
import net.maltshakes.genetictesting.client.gui.ResultsBookScreen;
import net.maltshakes.genetictesting.genes.datamodel.BookEntry;
import net.maltshakes.genetictesting.server.GeneticTestingPacketHandler;
import net.maltshakes.genetictesting.server.SaveGeneBookPagesPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * A client-only bridge class designed to isolate physical client operations from the dedicated
 * server. This prevents class-loading issues (such as {@link ClassNotFoundException} or dist
 * transformation crashes) by housing logic that references client-specific rendering systems like
 * {@link Minecraft} and font metrics.
 */
@OnlyIn(Dist.CLIENT)
public class ClientTopologyBridge {

    /**
     * Opens the custom genetics user interface and processes stored genetic data.
     *
     * <p>If the item stack contains raw genetics data, this method reconstructs the genetic
     * entries, formats them into displayable book pages, sends a sync packet to the server, and
     * updates the item's NBT tags accordingly. Finally, it initializes and displays the book screen
     * to the player.
     *
     * @param stack The {@link ItemStack} representing the genetics book being opened.
     * @param hand The {@link InteractionHand} the player used to open the book.
     */
    public static void openCustomGeneGui(ItemStack stack, InteractionHand hand) {
        CompoundTag tag = stack.getOrCreateTag();

        if (tag.contains("FullGeneticsData")) {
            ListTag rawList = tag.getList("FullGeneticsData", 10);
            List<BookEntry> reconstructedEntries = new ArrayList<>();

            for (int i = 0; i < rawList.size(); i++) {
                CompoundTag entryTag = rawList.getCompound(i);
                BookEntry.Type type = BookEntry.Type.valueOf(entryTag.getString("type"));
                Component labelComponent =
                        Component.Serializer.fromJson(entryTag.getString("label"));
                String rawLabelString = labelComponent != null ? labelComponent.getString() : "";

                if (type == BookEntry.Type.GENE_PAIR) {
                    Component val1 =
                            entryTag.contains("val1")
                                    ? Component.Serializer.fromJson(entryTag.getString("val1"))
                                    : null;
                    Component val2 =
                            entryTag.contains("val2")
                                    ? Component.Serializer.fromJson(entryTag.getString("val2"))
                                    : null;
                    reconstructedEntries.add(new BookEntry(rawLabelString, val1, val2));
                } else {
                    reconstructedEntries.add(new BookEntry(type, rawLabelString));
                }
            }

            ListTag pagesList = new ListTag();
            GeneBookDisplay geneDisplay = new GeneBookDisplay(reconstructedEntries);
            for (GeneBookDisplay.Page page : geneDisplay.getPages()) {
                String jsonPage = Component.Serializer.toJson(page.toComponent());
                pagesList.add(StringTag.valueOf(jsonPage));
            }

            GeneticTestingPacketHandler.INSTANCE.sendToServer(
                    new SaveGeneBookPagesPacket(pagesList, hand));
            tag.put("pages", pagesList);
            tag.remove("FullGeneticsData");
        }
        int colour = tag.contains("BookColour") ? tag.getInt("BookColour") : 0x99452E;

        GeneBookDisplay display = new GeneBookDisplay(stack);

        Minecraft.getInstance()
                .setScreen(
                        new ResultsBookScreen(Component.literal("Gene Results"), display, colour));
    }
}
