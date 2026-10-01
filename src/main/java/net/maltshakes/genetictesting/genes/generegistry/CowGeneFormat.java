package net.maltshakes.genetictesting.genes.generegistry;

import java.util.List;
import java.util.stream.IntStream;
import net.maltshakes.genetictesting.genes.datamodel.GeneDefinition.GeneType;
import net.maltshakes.genetictesting.genes.format.GeneFormatting;

// spotless:off
public class CowGeneFormat extends GeneFormatting {

    private static final List<String> COW_EXTENSION_GENES = List.of(
        "0",
        "D", // Dom Black
        "+", // Wildtype 
        "e", // Red
        "BR", // Black-red
        "M" // Masked
    );

    private static final List<String> COW_DILUTE_GENES = List.of(
        "0",
        "+", // Wildtype
        "S", // Simmental Dilute
        "C" // Charolais Dilute
    );

    private static final List<String> COW_AGOUTI_GENES = List.of(
        "0",
        "B", // Dark Agouti/Blackish
        "+", // Wildtype
        "w", // White-Bellied Agouti
        "BR", // Brindle
        "f", // Fawn
        "a" // Recessive Black
    );

    private static final List<String> COW_WHITEFACE_GENES = List.of(
        "0",
        "H", // Hereford
        "P", // Pinzgauer
        "+", // Wildtype
        "pi" // Piebald
    );

    private static final List<String> COW_WHITEFACEEXT_GENES = List.of(
        "0",
        "pl", // + Spots
        "+", // Normal
        "mi", // - Spots
        "bk" // + Backstripe
    );

    private static final List<String> COW_LEGACY_BELTED_GENES = List.of(
        "0",
        "LBt", //Legacy Belt
        "LBl", // Legacy Blaze
        "LBc", // Legacy Brockling
        "+" // Wildtype
    );

    private static final List<String> COW_MEALY_GENES = List.of(
        "0",
        "NM", // No nose ring
        "+", // Mealy/wildtype
        "ex" // Extended Mealy
    );

    private static final List<String> COW_RUFOUS_SCALE = List.of(
        "Min", // -20
        "Near Min", "Near Min", "Near Min", // -19 -17
        "Ultra Low", "Ultra Low", "Ultra Low", // -16 -14
        "Super Low", "Super Low", "Super Low", // -13 -11
        "Very Low", "Very Low", "Very Low", // -10 -8
        "Low", "Low", "Low", // -7 -5
        "Med-Low", "Med-Low", "Med-Low", // -4 -2
        "Medium", "Medium", "Medium",  // -1 +1
        "Med-High", "Med-High", "Med-High", // +2 +4
        "High", "High", "High", // +5 +7
        "Very High", "Very High", "Very High", // +8 +10
        "Super High", "Super High", "Super High", // +11 +13
        "Ultra High", "Ultra High", "Ultra High", // +14 +16
        "Near Max", "Near Max", "Near Max", // +17 +19
        "Max" // +20
    );

    private static final List<String> COW_RED_SHADING_SCALE = List.of(
        "Min", // -14
        "Near Min", "Near Min", // -13 -12
        "Ultra Low", "Ultra Low",// -11 -10
        "Super Low", "Super Low", // -9 -8
        "Very Low", "Very Low", // -7 -6
        "Low", "Low", // -5 -4
        "Med-Low", "Med-Low", // -3 -2
        "Medium", "Medium", "Medium",  // -1 +1
        "Med-High", "Med-High", "Med-High", // +2 +4
        "High", "High", "High", // +5 +7
        "Very High", "Very High", // +8 +9
        "Super High", "Super High", // +10 +11
        "Ultra High", "Ultra High", // +12 +13
        "Near Max", "Near Max", // +14 +15
        "Max" // +16
    );

    private static final List<String> COW_SOOTY_SCALE = List.of(
        "Min", // -26
        "Near Min", "Near Min", "Near Min", "Near Min", // -25 -22
        "Ultra Low", "Ultra Low", "Ultra Low", "Ultra Low", // -21 -18
        "Super Low", "Super Low", "Super Low", "Super Low", // -17 -14
        "Very Low", "Very Low", "Very Low", "Very Low", // -13 -10
        "Low", "Low", "Low", "Low", // -9 -6
        "Med-Low", "Med-Low", "Med-Low", "Med-Low", // -5 -2
        "Medium", "Medium", "Medium",  // -1 +1
        "Med-High", "Med-High", "Med-High", "Med-High", // +2 +5
        "High", "High", "High", "High", // +6 +9
        "Very High", "Very High", "Very High", "Very High", // +10 +13
        "Super High", "Super High", "Super High", "Super High", // +14 +17
        "Ultra High", "Ultra High", "Ultra High", // +18 +20
        "Near Max", "Near Max", "Near Max", // +21 +23
        "Max" // +24
    );

    // Maps the genes of a cow
    public CowGeneFormat() {
        setBookColour(0x424244); // Black
        addCategory("Genetic tests (color)");
        addPairMapping("Extension", COW_EXTENSION_GENES, GeneType.POLYMORPHIC, 0); // [0,1] - Extension
        addPairMapping("Agouti", COW_AGOUTI_GENES, GeneType.POLYMORPHIC, 2); // [4,5] - Agouti
        addPairMapping("Dom. Red", "DR", GeneType.BINARY_INVERTED, 3); // [6,7] - Dominant Red
        addPairMapping("Dilute", COW_DILUTE_GENES, GeneType.POLYMORPHIC, 1); // [2,3] - Simmental and Charolois Dilutes
        addPairMapping("Dun", "D", GeneType.BINARY, 64); // [128,129] - Dun Dilute, aka chinchilla
        addPairMapping("Chocolate", "c", GeneType.BINARY, 5); // [10,11] - Chocolate Dilute, aka... another dun
        addPairMapping("Mealy", COW_MEALY_GENES, GeneType.POLYMORPHIC, 12); // [24,25] - Mealy
        addPairMapping("Eelstripe", "eel", GeneType.BINARY, 60); // [120,121] - Eelstripe
        addPolyScaleMapping("Rufousing", COW_RUFOUS_SCALE,
            IntStream.rangeClosed(130, 149).toArray(), // [130-149] - Yellow Rufousing (-red)
            IntStream.rangeClosed(150, 169).toArray() // [150-169] - Burgandy Rufousing (+red)
        );
        addPolyScaleMapping("Red Shading", COW_RED_SHADING_SCALE,
            IntStream.rangeClosed(170, 183).toArray(), // [170-183] - lighter
            IntStream.rangeClosed(184, 199).toArray() // [184-199] - darker
        );
        addPolyScaleMapping("Sootiness", COW_SOOTY_SCALE,
            IntStream.rangeClosed(200, 225).toArray(), // [200-225] - lighter pattern
            IntStream.rangeClosed(226, 249).toArray() // [226-249] - darker pattern
        );
        addPairMapping("Roan", "Rn", GeneType.BINARY, 4); // [8,9] - Roan
        addPairMapping("Speckled", "Sp", GeneType.BINARY_INVERTED, 7); // [14,15] - Speckled
        addPairMapping("Spots", COW_WHITEFACE_GENES, GeneType.POLYMORPHIC, 8); // [16,17] - White Face/Spots
        addPairMapping("Pinz. Extension", COW_WHITEFACEEXT_GENES, GeneType.POLYMORPHIC, 11); // [22,23] - Pinzguaer/White Face Extension
        addPairMapping("Colorsided", "Cs", GeneType.BINARY_INVERTED, 10); // [20,21] - Colorsided
        addPairMapping("Belt", "Bt", GeneType.BINARY, 125); // [250,251] - Belted
        addPairMapping("Blaze", "Bl", GeneType.BINARY, 126); // [252,253] - Blaze
        // addPairMapping("Brockling", "Bc", GeneType.BINARY, 127); // [254,255] - Brockling
        addLegacyMapping("Legacy", COW_LEGACY_BELTED_GENES, GeneType.POLYMORPHIC, 9, 4); // [18,19] - Legacy Belted

        addPageBreak();
        addCategory("Genetic tests (build)");
        // [12,13] - horns (polled/horns)
        // [26,27] - bulldog dwarfism (dwarf/wildtype)
        // [28,29] - dwarfism (wildtype/dwarf)
        // [30,31] - size reducer
        // [32,33] - size adder
        // [34,35] - size variant 1
        // [36,37] - size variant 2
        // [38,39] - hump size
        // [40,41] - hump height
        // [42,43] - ear size
        // [44,45] - ear supressor
        // [46,47] - ear floppiness
        // [48,49] - smooth coat (smooth/wildtype)
        // [50,51] - furry coat 1 (wildtype/furry)
        // [52,53] - furry coat 2 (wildtype/furry)
        // [54,55] - body type
        // [70,71] - horn nub 1
        // [72,73] - horn nub 2
        // [74,75] - horn nub 3
        // [76,77] - african horn
        // [78,79] - scurs
        // [80,81] - horn length modifier
        // [82,83] - horn shortener
        // [84,85] - modifier
        // [86,87] - horn scale 1
        // [88,89] - horn scale 2
        // [90,91] - horn scale 3
        // [92,93] - horn smoother (1-9999)
        // [94,95] - horn twist (1-999999)
        // [96,97] - horn base twist (1-9999)
        // [98,99] - horn root (1-999)
        // [100,101] - horn 1, X & Z (1-999)
        // [102,103] - horn 2, X & Z (1-999)
        // [104,105] - horn 2, X & Z (1-999)
        // [106,107] - horn 3, X & Z (1-999)
        // [108,109] - horn 4, X & Z (1-999) 
        // [110,111] - horn 5, X & Z (1-999) 
        // [112,113] - horn 6, X & Z (1-999) 
        // [114,115] - horn 7, X & Z (1-999) 
        // [116,117] - horn 8, X & Z (1-999) 
        // [118,119] - horn 9, X & Z (1-999) 
        // [122,123] - horn modifer

        addCategory("Genetic tests (production)");
        addComment("Coming in future update");
    }
}
// spotless:on
