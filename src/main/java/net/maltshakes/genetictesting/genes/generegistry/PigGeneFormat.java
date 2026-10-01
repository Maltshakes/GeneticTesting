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
        "+", // Wildtype
        "p", // Brindle/Partial Extension
        "e", // Red
        "D2" // Dom Black (MCR2)
    );

    private static final List<String> PIG_AGOUTI_GENES = List.of(
        "0",
        "+", // Wildtype
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
        "+", // Wildtype
        "LP", // Legacy Patch
        "Rn", // Roan
        "I", // Dom White
        "II", // Dom White 2
        "III", // Dom White 3
        "Be2", // Large Belt
        "N2", // Tuxedo
        "P", // Patch
        "L" // Lethal White
    );

    private static final List<String> PIG_WP_GENES = List.of(
        "0",
        "+", // Wildtype
        "LT", // Legacy Tuxedo
        "wp" // White Points
    );

    private static final List<String> PIG_WE_GENES = List.of(
        "0",
        "UN", // Undermarked
        "MED", // Medium
        "ov" // Overmarked
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
        addPairMapping("Blonde", "E", GeneType.BINARY, 79); // [158,159] - Blonde
        addPairMapping("TYRP1", PIG_TYRP1_GENES, GeneType.POLYMORPHIC, 4); // [8,9] - TYRP1
        addPairMapping("Tamsworth", "T", GeneType.BINARY, 31); // [62,63] - Tamsworth
        addPairMapping("KITLG", "P", GeneType.BINARY, 32); // [64,65] - Oops All Spots
        addPairMapping("Wideband", "w", GeneType.BINARY, 82); // [164,165] - Wideband
        addPolyScaleMapping("Rufousing", PIG_RUFOUS_SCALE, 
         IntStream.rangeClosed(120, 133).toArray(), // [120-133] -red
         IntStream.rangeClosed(134, 147).toArray() // [134-147] +red
        );
        addPolyRangeMapping("Darkness", PIG_DARKNESS_SCALE, 150, 157); // [150-157] Darkness
        addPolyRangeMapping("Desaturation", PIG_DESAT_SCALE, 192, 201); // [192-201] Desaturation
        addPairMapping("KIT", PIG_KIT_GENES, GeneType.POLYMORPHIC, 6); // [12,13] - KIT
        addPairMapping("White Points", PIG_WP_GENES, GeneType.POLYMORPHIC, 7); // [14,15] - White Points
        addPairMapping("MITF", "H", GeneType.BINARY, 95); // [190,191] - Hereford/Splash
        addPairMapping("White Extension", PIG_WE_GENES, GeneType.POLYMORPHIC, 8); // [16,17] - White Extension
        addPairMapping("Blue Eyes", "blu", GeneType.BINARY, 80); // [160,161] Heterochromia

        addCategory("Genetic tests (build)");
        // [60,61] - mulefoot
        // [34,35] - hair density
        // [36,37] - baldness
        // [38,39] - wooly
        // [40,41] - thick hair
        
        // [44,45] - potbelly dwarfism 1
        // [46,47] - potbelly dwarfism 2
        // [48,49] - size reducer
        // [50,51] - size adder
        // [52,53] - size genes 1
        // [54,55] - size genes 2

        // [18,19] - face squish 1
        // [42,43] - face squish 2
        // [66,67] - face squish 3
        // [202-205] - snout angle
        // [32,33] - wattles
        // [68,69] - SSC1 ears & size
        // [70,71] - SSC5 ears & size
        // [72,73] - SSC6 ear size
        // [74,75] - SSC7 floppy, big ears
        // [76,77] - SSC9 small change
        // [78,79] - SSC12 least change
        // [80,81] - ear size 1
        // [82,83] - ear size 2
        // [84,85] - ear size 3
        // [86,87] - ear size 4
        // [88,89] - ear size 5
        // [90,91] - ear size 6
        // [92,93] - ear size 7
        // [94,95] - ear size 8
        // [96,97] - ear size 9
        // [98,99] - ear size 10
        // [100,101] - ear size 11
        // [102,103] - ear size 12
        // [104,105] - ear size 13
        // [106,107] - ear size 14
        // [108,109] - ear size 15
        // [110,111] - ear size 16
        // [112,113] - ear size 17
        // [114,115] - ear size 18
        // [116,117] - ear size 19
        // [118,119] - ear size 20
        
        addCategory("Genetic tests (production)");
        addComment("Coming in future update");
        // addPolyRangeMapping("Fat Adder", PIG_FATADDER_SCALE, 174, 181, 10); // [174-181] Fat Adder
        // [166-171] - muscle adders 
        // [172,173] - hypertrophy
        // [174-181] - fat adders
        // [182-189] - body length
    }
}
// spotless:on
