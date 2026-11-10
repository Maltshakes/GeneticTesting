package net.maltshakes.genetictesting.genes.datamodel;

import javax.annotation.Nullable;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

public class BookEntry {
    public enum Type {
        HEADER, // section title used to group genes
        GENE_PAIR, // labeled row displaying two genetic values (Allele 1 | 2)
        COMMENT // descriptive or conditional text entry.
    }

    public final Type type;
    public final Component label;

    @Nullable public final Component val1;

    @Nullable public final Component val2;

    // For Headers or comments
    public BookEntry(Type type, String text) {
        this.type = type;
        this.label = Component.literal(text);
        this.val1 = this.val2 = null;
    }

    // For genes
    public BookEntry(String label, Component v1, Component v2) {
        this.type = Type.GENE_PAIR;
        this.label = Component.literal(label);
        this.val1 = v1;
        this.val2 = v2;
    }

    public Type getType() {
        return type;
    }

    public MutableComponent getLabel() {
        return label.copy();
    }

    public Component getVal1() {
        return val1;
    }

    public Component getVal2() {
        return val2;
    }
}
