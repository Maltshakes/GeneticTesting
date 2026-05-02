package net.maltshakes.genetictesting.genes.generegistry;

import java.util.List;
import java.util.stream.IntStream;
import net.maltshakes.genetictesting.genes.datamodel.GeneDefinition.GeneType;
import net.maltshakes.genetictesting.genes.format.GeneFormatting;

// spotless:off
public class PigGeneFormat extends GeneFormatting {

    private static final List<String> PIG_EXTENSION_GENES = List.of(
        "0",
        "D1", // Dom Black (MCR1)
        "E", // Wildtype
        "p", // Brindle/Partial Extension
        "e", // Red
        "D2" // Dom Black (MCR2)
    );

    private static final List<String> PIG_AGOUTI_GENES = List.of(
        "0",
        "A", // Wildtype
        "B", // Legacy Brown
        "w", // Whitebelly
        "a", // Non-Agouti
        "s" // Swallowbelly
    );

    private static final List<String> PIG_TYRP1_GENES = List.of(
        "0",
        "+", // No dilute
        "B", // Silver-brown
        "ch" // Chocolate
    );

    private static final List<String> PIG_KIT_GENES = List.of(
        "0",
        "LI", // Legacy Dom White
        "Be", // Belted
        "i", // Wildtype
        "LP", // Legacy Patch
        "Rn", // Roan
        "1", // Dom White
        "2", // Dom White 2
        "Be2", // Large Belt
        "N2", // Tuxedo
        "P", // Patch
        "L" // Lethal
    );

    private static final List<String> PIG_WP_GENES = List.of(
        "0",
        "+", // Wildtype
        "LT", // Legacy Tuxedo
        "be" // White Points
    );

    private static final List<String> PIG_WE_GENES = List.of(
        "0",
        "-", // Undermarked
        "0", // Medium
        "+" // Overmarked
    );

    private static final List<String> PIG_DESAT_SCALE = List.of(
        "Min",
        "Near Min",
        "Very Low",
        "Low",
        "Med-Low",
        "Medium",
        "Med-High",
        "High",
        "Very High",
        "Near Max",
        "Max"
    );

    // private static final List<String> PIG_FATADDER_SCALE = List.of(
    //     "Min",
    //     "Near Min",
    //     "Ultra Low",
    //     "Super Low",
    //     "Very Low",
    //     "Low",
    //     "Med-Low",
    //     "Medium",
    //     "Med-High",
    //     "High",
    //     "Very High",
    //     "Super High",
    //     "Ultra High",
    //     "Near Max",
    //     "Max"
    // );

    private static final List<String> PIG_DARKNESS_SCALE = List.of(
        "Min",
        "Near Min",
        "Low",
        "Med-Low",
        "Medium",
        "Med-High",
        "High",
        "Near Max",
        "Max"
    );

    private static final List<String> PIG_RUFOUS_SCALE = List.of(
        "Min", // -14
        "Near Min", "Near Min", // -13 -12
        "Ultra Low", "Ultra Low", // -11 -10
        "Super Low", "Super Low", // -9 -8
        "Very Low", "Very Low", // -7 -6
        "Low", "Low", // -5 -4
        "Med-Low", "Med-Low", // -3 -2 
        "Medium", "Medium", "Medium", // -1 +1
        "Med-High", "Med-High", // +2 +3
        "High", "High", // +4 +5
        "Very High", "Very High", // +6 +7
        "Super High", "Super High", // +8 +9
        "Ultra High", "Ultra High", // +10 +11
        "Near Max", "Near Max", // +12 +13
        "Max" // +14
    );

    // Maps the genes of a pig
    public PigGeneFormat() {
        setBookColour(0xF588A7); // Pink
        addCategory("Genetic tests (color)");
        addPairMapping("Extension", PIG_EXTENSION_GENES, GeneType.POLYMORPHIC, 0); // [0,1] - Extension
        addPairMapping("Agouti", PIG_AGOUTI_GENES, GeneType.POLYMORPHIC, 1); // [2,3] - Agouti
        addPairMapping("Chinchilla", "CH", GeneType.BINARY_INVERTED, 2); // [4,5] - Chinchilla
        addPairMapping("Subtle Dilute", "di", GeneType.BINARY, 3); // [6,7] - Subtle Dilute
        addPairMapping("Blonde", "e", GeneType.BINARY, 79); // [158,159] - Blonde
        addPairMapping("TYRP1", PIG_TYRP1_GENES, GeneType.POLYMORPHIC, 4); // [8,9] - TYRP1
        addPairMapping("Tamsworth", "P", GeneType.BINARY, 31); // [62,63] - Tamsworth
        addPairMapping("KITLG", "P", GeneType.BINARY, 32); // [64,65] - Oops All Spots
        addPairMapping("Wideband", "w", GeneType.BINARY, 82); // [164,165] - Wideband
        addPairMapping("KIT", PIG_KIT_GENES, GeneType.POLYMORPHIC, 6); // [12,13] - KIT
        addPairMapping("White Points", PIG_WP_GENES, GeneType.POLYMORPHIC, 7); // [14,15] - White Points
        addPairMapping("White Extension", PIG_WE_GENES, GeneType.POLYMORPHIC, 8); // [16,17] - White Extension
        addPairMapping("MITF", "H", GeneType.BINARY, 95); // [190,191] - Hereford/Splash
        addLineBreak();
        addPolyScaleMapping("Rufousing", PIG_RUFOUS_SCALE, 
         IntStream.rangeClosed(120, 133).toArray(), // [120-133] -red
         IntStream.rangeClosed(134, 147).toArray() // [134-147] +red
        );
        addPolyRangeMapping("Darkness", PIG_DARKNESS_SCALE, 150, 157); // [150-157] Darkness
        addPolyRangeMapping("Desaturation", PIG_DESAT_SCALE, 192, 201); // [192-201] Desaturation
        addPairMapping("Blue Eyes", "blu", GeneType.BINARY, 80); // [160,161] Heterochromia

        // addPolyRangeMapping("Fat Adder", PIG_FATADDER_SCALE, 174, 181, 10); // [174-181] Fat Adder
    }
}
// spotless:on
