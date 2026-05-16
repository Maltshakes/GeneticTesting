package net.maltshakes.genetictesting.genes.generegistry;

import java.util.List;
import net.maltshakes.genetictesting.genes.datamodel.GeneDefinition.GeneType;
import net.maltshakes.genetictesting.genes.format.GeneFormatting;

// spotless:off
public class LlamaGeneFormat extends GeneFormatting {

    private static final List<String> LLAMA_EXTENSION_GENES = List.of(
        "0",
        "D", // Dom Black
        "+", // Wildtype
        "E" // Fawn Self
    );

    private static final List<String> LLAMA_AGOUTI_GENES = List.of(
        "0", 
        "PF", // Pale Shaded Fawn
        "+", // Wildtype
        "r", // Black trimmed Red
        "a", // Bay
        "m", // Mahogany
        "t", // Black and Tan
        "a" // Recessive Black
    );

    // Maps the genes of a llama
    public LlamaGeneFormat() {
        setBookColour(0x71CCEA); // Light blue
        addCategory("Genetic tests (color)");
        addPairMapping("Extension", LLAMA_EXTENSION_GENES, GeneType.POLYMORPHIC, 7); // [14,15] - Extension
        addPairMapping("Agouti", LLAMA_AGOUTI_GENES, GeneType.POLYMORPHIC, 8); // [16,17] - Agouti
        addPairMapping("Dom white", "Wh", GeneType.BINARY_INVERTED, 3); // [6,7] - Dominant White
        addPairMapping("Roan", "Rn", GeneType.BINARY_INVERTED, 4); // [8,9] - Roan
        addPairMapping("Piebald", "s", GeneType.BINARY, 5); // [10,11] - Piebald
        addPairMapping("Tuxedo", "Tu", GeneType.BINARY_INVERTED, 6); // [12,13] - Tuxedo
    }
}
// spotless:on
