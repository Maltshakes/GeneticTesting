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
        "0",
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
        addPairMapping("Mealy", "M", GeneType.BINARY_INVERTED, 45); // [90,91] - Mealy
        addPairMapping("Blaze", SHEEP_BLAZE_GENES, GeneType.POLYMORPHIC, 51); // [102,103] - Blaze
        addPairMapping("Piebald", "pi", GeneType.BINARY, 4); // [8,9] - Piebald
        addPairMapping("Pigmented Head", SHEEP_PIGMENTED_HEAD_GENES, GeneType.POLYMORPHIC, 34); // [68,69] - Pigmented Head
        addPairMapping("White Expansion", SHEEP_WHITE_EXPANSION_GENES, GeneType.POLYMORPHIC, 9); // [18,19] - White spot expansion for Persian, 8 values. Less white is more dominant
        addPairMapping("Roan", "Rn", GeneType.BINARY, 50); // [100,101] - Roan
        addPairMapping("Ticking", "Ti", GeneType.BINARY, 35); // [70,71] - Ticking
        addLineBreak();
        addConditionalComment("AFL is homo lethal! Sheep with two copies of the gene die at birth", 34, (v1, v2) -> v1 == 2 || v2 == 2);
        addConditionalComment("Rn is homo lethal! Sheep with two copies of the gene die at birth", 50, (v1, v2) -> v1 == 2 || v2 == 2);
    }
}
// spotless:on
