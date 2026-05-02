package net.maltshakes.genetesting.client;

import java.util.ArrayList;
import java.util.List;

import net.maltshakes.genetesting.genes.datamodel.BookEntry;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.item.ItemStack;

/**
 * Handles the layout and data transformation for displaying genetic information
 * within a GeneBook. This class manages the distribution of {@link BookEntry} 
 * data across a dual-column page system.
 */
public class GeneBookDisplay {

    private final List<Page> pages = new ArrayList<>();
    public final static int COLUMN_SIZE = 12;
    public final static int COLUMN_WIDTH = 110;

    /**
     * Constructs a display layout from a raw list of genetic entries.
     * Logic includes:
     *  Formatting headers with underline and comments with italics.
     *  Automatic column wrapping after 12 lines, set to COLUMN_SIZE.
     *  Handling manual page and column breaks via metadata tags.
     * 
     * @param entries The list of genetic data entries to be formatted.
     */
    public GeneBookDisplay(List<BookEntry> entries) {
        Page currentPage = new Page(1);
        pages.add(currentPage);
        boolean targetRight = false;
        int currentLineCount = 0;
        var font = Minecraft.getInstance().font;
        for (BookEntry entry : entries) {
            // Page break logic
            if (entry.getType() == BookEntry.Type.COMMENT && "[PAGE_BREAK]".equals(entry.getLabel().getString())) {
                if (!targetRight) {
                    targetRight = true;
                } else {
                    currentPage = new Page(pages.size() + 1);
                    pages.add(currentPage);
                    targetRight = false;
                }
                currentLineCount = 0; // Reset for new column
                continue;
            }
            MutableComponent line = switch (entry.getType()) {
                case HEADER -> entry.getLabel();
                case COMMENT -> {
                    if ("[CLEAN_BREAK]".equals(entry.getLabel().getString())) yield Component.empty();
                    yield entry.getLabel().withStyle(Style.EMPTY.withItalic(true));
                }
                case GENE_PAIR -> {
                    MutableComponent base = entry.getLabel().copy().append(": ");
                    Component v1 = entry.getVal1() != null ? entry.getVal1() : Component.literal("?");
                    Component v2 = entry.getVal2() != null ? entry.getVal2() : Component.literal("?");
                    // If v2 is an interrobang, only display the first value
                    if ("‽".equals(v2.getString())) {
                        yield base.append(v1); // For polygenic / female sgenes
                    } else {
                        yield base.append(v1).append("/").append(v2); // For monogenic
                    }
                }
            };

            List<FormattedCharSequence> wrappedLines = font.split(line, COLUMN_WIDTH);
            int physicalLinesNeeded = wrappedLines.size();
            int spaceBeforeHeader = (entry.getType() == BookEntry.Type.HEADER && currentLineCount > 0) ? 1 : 0;
            int totalEntryHeight = physicalLinesNeeded + spaceBeforeHeader;
            if (currentLineCount + totalEntryHeight > COLUMN_SIZE) {
                if (!targetRight) {
                    targetRight = true;
                } else {
                    currentPage = new Page(pages.size() + 1);
                    pages.add(currentPage);
                    targetRight = false;
                }
                currentLineCount = 0;
            }

            List<Component> targetCol = targetRight ? currentPage.rightColumn : currentPage.leftColumn;
            
            // Header spacing logic
            if (spaceBeforeHeader > 0) {
                targetCol.add(Component.empty());
                currentLineCount++;
            }
            for (FormattedCharSequence linePart : wrappedLines) {
                // Reconstruct component from char sequence for the column list
                MutableComponent comp = Component.empty();
                linePart.accept((index, style, codePoint) -> {
                    comp.append(Component.literal(String.valueOf((char) codePoint)).withStyle(style));
                    return true;
                });
                targetCol.add(comp);
                currentLineCount++;
            }
        }
    }

    /**
     * Reconstructs a display layout by parsing the NBT data of an existing ItemStack.
     * This is primarily used for reading books that have already been saved to the world.
     * 
     * @param stack The ItemStack (GeneBook) containing the "pages" ListTag.
     */
    public GeneBookDisplay(ItemStack stack) {
        CompoundTag tag = stack.getTag();
         if (tag == null || !tag.contains("pages",9)) {
            // If a player got the book by adding it directly to their inventory
            Page warningPage = new Page(1);
            warningPage.leftColumn.add(Component.literal("[WARNING]")
                .withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xFF0000)).withBold(true)));
            warningPage.leftColumn.add(Component.empty());
            warningPage.leftColumn.add(Component.literal("USE A VANILLA MINECRAFT BOOK ON A GENETIC ANIMAL")
                .withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x404040))));
            pages.add(warningPage);
            return; // Exit
        }
         if (tag != null && tag.contains("pages", 9)) { // 9 = Tag.TAG_LIST
            ListTag pagesList = tag.getList("pages", 8); // 8 = Tag.TAG_STRING
            for (int i = 0; i < pagesList.size(); i++) {
                try {
                    Component pageComponent = Component.Serializer.fromJson(pagesList.getString(i));
                    Page page = new Page(i + 1);
                    List<FormattedCharSequence> lines = Minecraft.getInstance().font.split(pageComponent, 2000);
                    for (int j = 0; j < lines.size(); j++) {
                        MutableComponent wrappedLine = Component.empty();
                        lines.get(j).accept((index, style, codePoint) -> {
                            wrappedLine.append(Component.literal(String.valueOf((char) codePoint)).withStyle(style));
                            return true;
                        });
                        if (j < COLUMN_SIZE) page.leftColumn.add(wrappedLine);
                        else if (j < (COLUMN_SIZE * 2)) page.rightColumn.add(wrappedLine);
                    }
                    pages.add(page);
                } catch (Exception e) {
                    pages.add(new Page(i + 1));
                }
            }
        }
    }

    /**
     * Represents a single physical page in the GeneBook, 
     * partitioned into left and right columns.
     */
    public static class Page {
        // Lines in the left column
        public final List<Component> leftColumn = new ArrayList<>();
        // Lines in the right column
        public final List<Component> rightColumn = new ArrayList<>();
        // Index of the page in the book
        public final int pageNumber;

         /**
         * Creates a new page container.
         * @param pageNumber The sequence number for this page.
         */
        public Page(int pageNumber) { this.pageNumber = pageNumber; }

        /**
         * Converts the page columns into a single Component for saving to NBT.
         */
        public Component toComponent() {
            MutableComponent fullPage = Component.empty();
            for (int i = 0; i < COLUMN_SIZE; i++) {
                Component c = i < leftColumn.size() ? leftColumn.get(i) : Component.empty();
                fullPage.append(c).append("\n");
            }
            for (int i = 0; i < COLUMN_SIZE; i++) {
                Component c = i < rightColumn.size() ? rightColumn.get(i) : Component.empty();
                fullPage.append(c).append("\n");
            }
            return fullPage;
        }
    }

    /**
     * Gets the list of formatted pages generated by this display handler.
     * @return A list of {@link Page} objects.
     */
    public List<Page> getPages() { return pages; }
}
