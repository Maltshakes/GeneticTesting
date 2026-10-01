package net.maltshakes.genetictesting.genes.generegistry;

import java.util.List;
import net.maltshakes.genetictesting.genes.datamodel.GeneDefinition.GeneType;
import net.maltshakes.genetictesting.genes.format.GeneFormatting;

// spotless:off
public class RabbitGeneFormat extends GeneFormatting{

    private static final List<String> RABBIT_EXTENSION_GENES = List.of(
        "0",
        "S", // Steel
        "+", // Extension/Wildtype
        "j", // Brindle/Japanese
        "e" // Non Extension
    );
    
    private static final List<String> RABBIT_AGOUTI_GENES = List.of(
        "0",
        "+", // Agouti/Wildtype
        "t", // Tan
        "a" // Self
    );

    private static final List<String> RABBIT_COLOUR_GENES = List.of(
        "0",
        "+", // Full Color/Wildtype
        "chd", // Dark Chinchilla
        "chl", // Light Chincilla
        "h", // Himalayan
        "c" // Albino
    );

    private static final List<String> RABBIT_LOP1_GENES = List.of(
        "0",
        "+", // Wildtype
        "hl", // Half Lop
        "l1" // Lop1
    );

    private static final List<String> RABBIT_EAR_LENGTH_GENES = List.of(
        "0",
        "+", // Wildtype
        "s", // Shorter
        "l" // Longer
    );

    private static final List<String> RABBIT_LONGER_EAR_GENES = List.of(
        "0",
        "+", // Wildtype
        "lr", // Longer
        "lt" // Longest
    );

    private static final List<String> RABBIT_SIZE_TENDENCY_GENES = List.of(
        "0",
        "s1", // Small
        "+", // Wildtype/Normal
        "s2", // Small2
        "b1", // Big
        "el" // Extra Large
    );

    private static final List<String> RABBIT_SIZE_ENHANCER_GENES = List.of(
        "0",
        "b2", // Big
        "+", // Normal
        "s3" // Small
    );

    // Maps the genes of a rabbit
    public RabbitGeneFormat() {
        setBookColour(0x98D840); // Lime
        addCategory("Genetic tests (color)");
        addPairMapping("Extension", RABBIT_EXTENSION_GENES, GeneType.POLYMORPHIC, 4); // [8,9] - Extension
        addPairMapping("Agouti", RABBIT_AGOUTI_GENES, GeneType.POLYMORPHIC, 0); // [0,1] - Agouti
        addPairMapping("Color", RABBIT_COLOUR_GENES, GeneType.POLYMORPHIC, 2); // [4,5] - Color completion
        addPairMapping("Chocolate", "b", GeneType.BINARY, 1); // [2,3] - Brown/Chocolate
        addPairMapping("Dilute", "d", GeneType.BINARY, 3); // [6,7] - Dilute
        addPairMapping("Lutino", "p", GeneType.BINARY, 10); // [20,21] - Lutino
        addPairMapping("Spotted", "En", GeneType.BINARY, 5); // [10,11] - English Spotting/Broken/Charlie
        addPairMapping("Dutch", "du", GeneType.BINARY, 6); // [12,13] - Dutch
        addPairMapping("Vienna", "v", GeneType.BINARY, 7); // [14,15] - Vienna

        addPageBreak();
        addCategory("Genetic tests (build)");
        addPairMapping("Lions Mane", "M", GeneType.BINARY, 12); // [24,25] - Lions mane
        addPairMapping("Angora", "l", GeneType.BINARY, 13); // [26,27] - Angora
        addPairMapping("Rex", "r", GeneType.BINARY, 14); // [28,29] - Rex
        addPairMapping("Satin", "sa", GeneType.BINARY, 15); // [30,31] - Satin
        addPairMapping("Dwarf", "Dw", GeneType.BINARY, 17); // [34,35] - Dwarf
        addPairMapping("Size Tendency", RABBIT_SIZE_TENDENCY_GENES, GeneType.POLYMORPHIC, 23); // [46,47] - Size tendency
        addPairMapping("Size Enhancer", RABBIT_SIZE_ENHANCER_GENES, GeneType.POLYMORPHIC, 24); // [48,49] - Size enhancer
        addPairMapping("Ear Length", RABBIT_EAR_LENGTH_GENES, GeneType.POLYMORPHIC, 21); // [42,43] - Ear length bias
        addPairMapping("Lop1", RABBIT_LOP1_GENES, GeneType.POLYMORPHIC, 18); // [36,37] - Lop 1
        addPairMapping("Lop2", "l2", GeneType.BINARY, 19); // [38,39] - Lop 2
        addPairMapping("Longer Ears", RABBIT_LONGER_EAR_GENES, GeneType.POLYMORPHIC, 20); // [40,41] - Longer ears

        addPageBreak();
        addCategory("Genetic tests (production)");
        addComment("Coming in a future update");
        // [50-55] - Fur length for wool production, polymorphic and polygenic
        // [56-59] - Fertility (56,57 ++), (58,59 --)
    }
}
// spotless:on
