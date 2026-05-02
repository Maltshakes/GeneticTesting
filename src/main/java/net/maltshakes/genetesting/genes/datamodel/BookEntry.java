package net.maltshakes.genetesting.genes.datamodel;

import javax.annotation.Nullable;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

/**
 * Represents a single entry line within the Gene Book.
 * <p>
 * This class serves as a view-model, transforming raw genetic data into 
 * human-readable {@link Component} objects. It supports three distinct formats:
 * <ul>
 *     <li>{@link Type#HEADER}: A section title used to group genes.</li>
 *     <li>{@link Type#GENE_PAIR}: A labeled row displaying two genetic values (e.g., Allele 1 | Allele 2).</li>
 *     <li>{@link Type#COMMENT}: A descriptive or conditional text entry.</li>
 * </ul>
 */
public class BookEntry {
    public enum Type { HEADER, GENE_PAIR, COMMENT }
    
    public final Type type;
    public final Component label;
    @Nullable
    public final Component val1;
    @Nullable
    public final Component val2;

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