package net.maltshakes.genetictesting.genes.generegistry;

import java.util.List;
import java.util.stream.IntStream;
import net.maltshakes.genetictesting.genes.datamodel.GeneDefinition.GeneType;
import net.maltshakes.genetictesting.genes.format.GeneFormatting;

// spotless:off
public class SheepGeneFormat extends GeneFormatting {

    private static final List<String> SHEEP_AGOUTI_GENES = List.of(
        "0",
        "Wt", // Dominant White/Tan
        "G", // Grey
        "B", // Blackbelly 0
        "T", // Mouflon
        "EB", // English Blue
        "a", // Recessive Black
        "B1", // Blackbelly 1
        "B2", // Blackbelly 2
        "B3", // Blackbelly 3
        "B4", // Blackbelly 4
        "B5", // Blackbelly 5
        "LBF", // Light Mouflon
        "+", // Wild Mouflon
        "BLG", // Blue German
        "LBL", // Light Blue
        "PBL" //Paddington Blue
    );

    private static final List<String> SHEEP_EXTENSION_GENES = List.of(
        "0",
        "D", // Dominant Black
        "+", // Wildtype
        "e" // Recessive Red
    );

    private static final List<String> SHEEP_PIGMENTED_HEAD_GENES = List.of(
        "0",
        "+", // Wildtype
        "AFL", // Afghan Lethal
        "PT", // Turkish
        "p" // Persian
    );

    private static final List<String> SHEEP_REDINHIB_GENES = List.of(
        "L", // Legacy
        "+", // Wildtype
        "Da", // Darker
        "Ta", // Tan
        "Cr", // Cream
        "Ow", // Off-white
        "Wh" // White 
    );

    private static final List<String> SHEEP_BLAZE_GENES = List.of(
        "0",
        "+", // Wildtype
        "n", // Nadji
        "we", // White Extremities
        "b" // Blaze
    );

    private static final List<String> SHEEP_WHITE_EXPANSION_GENES = List.of(
        "0",
        "8", // max, array value: 1
        "7", // near max
        "6", // high
        "5", // medium
        "4", // medium
        "3", // low
        "2", // near min
        "1" // min, array value: 8
    );

    private static final List<String> SHEEP_RUFOUS_SCALE = List.of(
        "Min", // -8
        "Near Min", // -7
        "Ultra Low", // -6
        "Super Low", // -5
        "Very Low", // -4
        "Low", // -3
        "Med-Low", // -2
        "Medium", "Medium", "Medium", // -1 +1
        "Med-High", // +2
        "High", // +3
        "Very High", // +4
        "Super High", // +5
        "Ultra High", // +6
        "Near Max",// +7
        "Max" // +8
    );

    private static final List<String> SHEEP_POLLED_GENES = List.of(
        "0",
        "P", // No horns/Polled
        "+", // Wildtype/Horns
        "l" // Males have horns/Sex-limited Polled
    );

    private static final List<String> SHEEP_FACE_WOOL_EXT1_GENES = List.of(
        "0",
        "EX1", // Extender
        "+", // Wildtype
        "li" // Limiter
    );

    private static final List<String> SHEEP_SIZE_GENES = List.of(
        "0",
        "+", // Wildtype
        "1",
        "2",
        "3",
        "4",
        "5",
        "6",
        "7",
        "8",
        "9",
        "10",
        "11",
        "12",
        "13",
        "14",
        "15"
    );

    private static final List<String> SHEEP_SIZE_VARIANT_GENES = List.of(
        "0",
        "+", // Wildtype
        "Sr", // Smaller
        "St" // Smallest
    );

    // Maps the genes of a sheep
    public SheepGeneFormat() {
        setBookColour(0x8B35B9); // Purple
        addCategory("Genetic tests (color)");
        addPairMapping("Extension", SHEEP_EXTENSION_GENES, GeneType.POLYMORPHIC, 2); // [4,5] - Extension
        addPairMapping("Agouti", SHEEP_AGOUTI_GENES, GeneType.POLYMORPHIC, 0); // [0,1] - Agouti
        addPairMapping("Chocolate", "b", GeneType.BINARY, 1); // [2,3] - Chocolate
        addPairMapping("Red Inhibitors", SHEEP_REDINHIB_GENES, GeneType.POLYMORPHIC, 36); // [72,73] - Red inhibitors
        addPolyScaleMapping("Rufousing", SHEEP_RUFOUS_SCALE, 
            IntStream.rangeClosed(74, 81).toArray(), // [74,81] - red rufousing
            IntStream.rangeClosed(82, 89).toArray()  // [82,89] + red rufousing
        );
        addPairMapping("Mealy", "nm", GeneType.BINARY, 45); // [90,91] - Mealy
        addPairMapping("Blaze", SHEEP_BLAZE_GENES, GeneType.POLYMORPHIC, 51); // [102,103] - Blaze
        addPairMapping("Piebald", "pi", GeneType.BINARY, 4); // [8,9] - Piebald
        addPairMapping("Pigmented Head", SHEEP_PIGMENTED_HEAD_GENES, GeneType.POLYMORPHIC, 34); // [68,69] - Pigmented Head
        addPairMapping("White Expansion", SHEEP_WHITE_EXPANSION_GENES, GeneType.POLYMORPHIC, 9); // [18,19] - White spot expansion for Persian, 8 values. Less white is more dominant
        addPairMapping("Roan", "Rn", GeneType.BINARY, 50); // [100,101] - Roan
        addPairMapping("Ticking", "Ti", GeneType.BINARY, 35); // [70,71] - Ticking
        addLineBreak();
        addConditionalComment("AFL is homo lethal! Sheep with two copies of the gene die at birth", 34, (v1, v2) -> v1 == 2 || v2 == 2);
        addConditionalComment("Rn is homo lethal! Sheep with two copies of the gene die at birth", 50, (v1, v2) -> v1 == 2 || v2 == 2);

        addCategory("Genetic tests (build)");
        addPairMapping("Polled", SHEEP_POLLED_GENES, GeneType.POLYMORPHIC, 3); // [6,7] - polled
        addPairMapping("Multi-horned", "F", GeneType.BINARY_INVERTED, 18); // [36,37] - multi-horned
        addPairMapping("Face Wool", "FW", GeneType.BINARY_INVERTED, 21); // [42,43] - face wool
        addPairMapping("FW Ext.1", SHEEP_FACE_WOOL_EXT1_GENES, GeneType.POLYMORPHIC, 19);// [38,39] - face wool extension 1
        addPairMapping("FW Ext.2", "EX2", GeneType.BINARY_INVERTED, 20); // [40,41] - face wool extension 2
        addPairMapping("Shedding", "SH", GeneType.BINARY_INVERTED, 23); // [46,47] - shedding coat
        addPairMapping("HOXB13", "Ho", GeneType.BINARY, 46); // [92,93] - HOXB13 tail
        addPairMapping("TBXT", "TB", GeneType.BINARY, 47); // [94,95] - TBXT tail
        addPairMapping("PDGFD", "PD", GeneType.BINARY, 48); // [96,97] - PDGFD tail
        addPairMapping("IBH", "IB", GeneType.BINARY, 49); // [98,99] - IBH tail
        addPairMapping("Miniature", "Mi", GeneType.BINARY, 28); // [56,57] - miniature
        addPairMapping("Size Inhibit1", SHEEP_SIZE_VARIANT_GENES, GeneType.POLYMORPHIC, 31); // [62,63] - size variant 1
        addPairMapping("Size Inhibit2", SHEEP_SIZE_VARIANT_GENES, GeneType.POLYMORPHIC, 32); // [64,65] - size variant 2
        addPairMapping("Size Reducer", SHEEP_SIZE_GENES, GeneType.POLYMORPHIC, 29); // [58,59] - size reducer
        addPairMapping("Size Adder", SHEEP_SIZE_GENES, GeneType.POLYMORPHIC, 30); // [60,61] - size adder
    
        addPageBreak();
        addCategory("Genetic tests (production)");
        addComment("Coming in a future update");
        // [20-35] - wool length, polygenic
        // [44,45] - fertility
    }
}
// spotless:on
