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

    private static final List<String> LLAMA_BANANA_GENES = List.of(
        "0",
        "+", // No Banana/wildtype
        "B", // Banana
        "bl" // Bananaless
    );

    // private static final List<String> LLAMA_COAT_LENGTH_GENES = List.of(
    //     "0",
    //     "+", // Wildtype
    //     "L", // Longer
    //     "LT" // Longest
    // );

    private static final List<String> LLAMA_ENDURANCE_GENES = List.of(
        "0",
        "+", // Wildtype
        "E1", // Strong1
        "E2" // Strong2
    );

    private static final List<String> LLAMA_STRENGTH_GENES = List.of(
        "0",
        "+", // Wildtype
        "S1", // Strong1
        "S2" // Strong2
    );

    private static final List<String> LLAMA_ATTACK_GENES = List.of(
        "0",
        "+", // Wildtype
        "P1", // Strong1
        "P2" // Strong2
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

        addPageBreak();
        addCategory("Genetic tests (build)");
        addPairMapping("Endurance", LLAMA_ENDURANCE_GENES, GeneType.POLYMORPHIC, 0); // [0,1] - Endurance genes
        addPairMapping("Strength", LLAMA_STRENGTH_GENES, GeneType.POLYMORPHIC, 1); // [2,3] - Strength genes
        addPairMapping("Power", LLAMA_ATTACK_GENES, GeneType.POLYMORPHIC, 2); // [4,5] - Power aka Attack genes
        addPairMapping("Suri", "su", GeneType.BINARY, 10); // [20,21] - Suri coat
        addPairMapping("Ear Type", LLAMA_BANANA_GENES, GeneType.POLYMORPHIC, 9); // [18,19] - Banana ears
        // [34-39] - Health genes 
        // [28-33] - Nose placement polygenic and polymorphic (does this do anything?)

        addPageBreak();
        addCategory("Genetic tests (production)");
        addComment("Coming in future update");
        // addPairMapping("Coat Length", LLAMA_COAT_LENGTH_GENES, GeneType.POLYMORPHIC, 11); // [22,23] - Coat length
        // addPairMapping("Coat Suppressor", "S", GeneType.BINARY, 12); // [24,25] - Coat supressor
        // addPairMapping("Coat Amplifier", "am", GeneType.BINARY, 13); // [26,27] - Coat amplifier
    }
}
// spotless:on
