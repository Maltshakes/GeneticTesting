package net.maltshakes.genetictesting.genes.generegistry;

import java.util.List;
import java.util.stream.IntStream;
import net.maltshakes.genetictesting.genes.datamodel.GeneDefinition.GeneType;
import net.maltshakes.genetictesting.genes.format.GeneFormatting;

// spotless:off
public class BettaGeneFormat extends GeneFormatting {

    private static final List<String> BETTA_NONRED_GENES = List.of(
        "0",
        "Nr", // Wildtype
        "nr2", // Orange
        "nr1" // Yellow
    );

    private static final List<String> BETTA_MARBLE_GENES = List.of(
        "0",
        "+", // Wildtype
        "mb", // Marble
        "v" // Vanda
    );

    private static final List<String> BETTA_MARBLE_SIZE_SCALE = List.of(
        "Min", // -4
        "Near Min", // -3
        "Super Low", // -2
        "Very Low", // -1
        "Low", // -0
        "Medium", // +1
        "High", // +2
        "Very High", // +3
        "Super High", // +4
        "Near Max",// +5
        "Max" // +6
    );

    private static final List<String> BETTA_RUFOUSING_SCALE = List.of(
        "Min", // -10
        "Near Min", // -9
        "Ultra Low", // -8
        "Super Low", // -7
        "Very Low", // -6
        "Low", "Low", // -5 -4
        "Med-Low", "Med-Low", // -3 -2
        "Medium", "Medium", "Medium", // -1 +1
        "Med-High", "Med-High", // +2 +3
        "High", "High", // +4 +5
        "Very High", // +6
        "Super High", // +7
        "Ultra High", // +8
        "Near Max",// +9
        "Max" // +10
    );

    private static final List<String> BETTA_IRI_HUE_SCALE = List.of(
        "Lightest", // -10
        "Near Lightest", // -9
        "Ultra Light", // -8
        "Super Light", // -7
        "Very Light", // -6
        "Light", "Light", // -5 -4
        "Med-Light", "Med-Light", // -3 -2
        "Medium", "Medium", "Medium", // -1 +1
        "Med-Dark", "Med-Dark", // +2 +3
        "Dark", "Dark", // +4 +5
        "Very Dark", // +6
        "Super Dark", // +7
        "Ultra Dark", // +8
        "Near Darkest",// +9
        "Darkest" // +10
    );

    private static final List<String> BETTA_LOW_HIGH_LEVEL_SCALE = List.of(
        "Min",
        "Low",
        "Medium",
        "High",
        "Max"
    );

    private static final List<String> BETTA_MASK_IRI_SCALE = List.of(
        "Min",
        "Near Min",
        "Low",
        "Medium",
        "High",
        "Near Max",
        "Max"
    );

    private static final List<String> BETTA_IRI_INTENSITY_SCALE = List.of(
        "Min",
        "Near Min",
        "Low",
        "Medium",
        "High",
        "Near Max",
        "Max"
    );

    private static final List<String> BETTA_HIGH_LOW_LEVEL_SCALE = List.of(
        "Max",
        "High",
        "Medium",
        "Low",
        "Min"
    );

    private static final List<String> BETTA_MARBLE_QUALITY_SCALE = List.of(
        "Min", // value: 4
        "Near Min", // 5
        "Ultra Low", // 6
        "Super Low", // 7
        "Very Low", // 8
        "Low", // 9
        "Med-Low", // 10
        "Medium", "Medium", "Medium", // 11 - 13
        "Med-High", // 14
        "High", // 15
        "Very High", // 16
        "Super High", // 17
        "Ultra High", // 18
        "Near Max", // 19 
        "Max" // 20
    );

    public BettaGeneFormat() {
        // Add dragonscale and dragonscale modifier
        setBookColour(0x4B52B0); // Blue
        addCategory("Genetic tests (color)");
        // Blue layer
        addPairMapping("Iridescence", "bl", GeneType.BINARY, 0); // [0,1] - Steel bl / Turquoise + or Bl
        addPolyScaleMapping("Iri Hue", BETTA_IRI_HUE_SCALE,
            IntStream.rangeClosed(214, 223).toArray(), // [214,223] greener (lighter)
            IntStream.rangeClosed(224, 233).toArray()  // [224,233] bluer (darker)
        );
        addPolyRangeMapping("Body Iri Area", BETTA_LOW_HIGH_LEVEL_SCALE, 24, 27); // [20-23] Body Iri Level
        addPolyRangeMapping("Fin Iri Area", BETTA_LOW_HIGH_LEVEL_SCALE, 28, 31); // [20-23] Fin Iri Level
        addPairMapping("Iri Rims", "R", GeneType.BINARY, 33); // [66,67] - Iri rims
        addPolyRangeMapping("Iri Rim Level", BETTA_LOW_HIGH_LEVEL_SCALE, 68, 71); // [50-55] Iri Rims Level
        addPairMapping("Iri Spread", "Si", GeneType.BINARY, 1); // [2,3] - Spread Iridescence
        addPairMapping("Masked Iri", "M", GeneType.BINARY, 2); // [4,5] - Masked Iridescence
        addPolyRangeMapping("Masked Iri Area", BETTA_MASK_IRI_SCALE, 50, 55); // [50-55] Masked Iri Area
        addCustomBettaPolyScaleMapping("Iri intensity", BETTA_IRI_INTENSITY_SCALE,
            new int[]{32, 33},
            new int[]{34, 35}
        );
        // NEED ADDED: Iri intensity genes 32-35, 32/33 = +/higher/lower, 34/35 = +/lower
        
        // Black layer
        addPairMapping("Melano Black", "m", GeneType.BINARY, 3); // [6,7] - Melano Black (homo females are infertile)
        addPairMapping("Laced Black", "fb", GeneType.BINARY, 4); // [8,9] - Fertile/Laced Black
        addPairMapping("Cambodian", "c", GeneType.BINARY, 5); // [10,11] - Cambodian
        addPairMapping("Extended Red", "Er", GeneType.BINARY, 6); // [12,13] - Extended Red
        addPairMapping("Blonde", "b", GeneType.BINARY, 7); // [14,15] - Blonde
        addPairMapping("Metallic", "nm", GeneType.BINARY_INVERTED, 24); // [48,49] - Metallic + / Non-metallic nm
        addPairMapping("Red Mask", "R", GeneType.BINARY, 8); // [16,17] - Red Mask
        addPairMapping("Bloodred", "BR", GeneType.BINARY, 76); // [152,153] - Bloodred
        addPairMapping("Ext. Bloodred", "BR", GeneType.BINARY, 77); // [154,155] - Extended Bloodred
        addPairMapping("Bloodred Mask", "BR", GeneType.BINARY, 78); // [156,157] - Bloodred Mask
        addPairMapping("Opaque", "Op", GeneType.BINARY, 20); // [40,41] - Opaque
        addPairMapping("Non Red", BETTA_NONRED_GENES, GeneType.POLYMORPHIC, 21); // [42,43] - Non Red
        addPairMapping("Marble", BETTA_MARBLE_GENES, GeneType.POLYMORPHIC, 40); // [80,81] - Marble/Vanda
        addPairMapping("Butterfly", "B", GeneType.BINARY, 9); // [18,19] - Butterfly
        
        addPolyRangeMapping("Butterfly Level", BETTA_LOW_HIGH_LEVEL_SCALE, 20, 23); // [20-23] Butterfly Level
        // Red Marble Layer
        addPolyScaleMapping("Red Marble Size", BETTA_MARBLE_SIZE_SCALE, 
            IntStream.rangeClosed(82, 85).toArray(), // [82,85] -red marble
            IntStream.rangeClosed(86, 91).toArray()  // [86,91] +red marble
        );
        addPolyRangeMapping("Red Marble Quality", BETTA_MARBLE_QUALITY_SCALE, 92, 95, 5); // [92-95] Red marble quality scale
        addPolyScaleMapping("Red Body Area", BETTA_HIGH_LOW_LEVEL_SCALE,
            IntStream.rangeClosed(44, 45).toArray(), // [44,45] +body red area
            IntStream.rangeClosed(46, 47).toArray()  // [46,47] -body red area
        );
        addPolyRangeMapping("Red Fin Area", BETTA_HIGH_LOW_LEVEL_SCALE, 36, 39); // [36-39] Fin Red Level
        addPolyScaleMapping("Rufousing", BETTA_RUFOUSING_SCALE, 
            IntStream.rangeClosed(174, 183).toArray(), // [174,183] -rufousing
            IntStream.rangeClosed(184, 193).toArray()  // [184,193] +rufousing
        );
        // Black Marble Layer
        addPolyScaleMapping("Black Marble Size", BETTA_MARBLE_SIZE_SCALE, 
            IntStream.rangeClosed(96, 99).toArray(), // [96,99] -black marble
            IntStream.rangeClosed(100, 105).toArray()  // [100,105] +black marble
        );
        addPolyRangeMapping("Black Marble Quality", BETTA_MARBLE_QUALITY_SCALE, 106, 109, 5); // [106-109] Black marble quality scale
        // Bloodred Marble Layer
        addPolyScaleMapping("Bloodred Marble Size", BETTA_MARBLE_SIZE_SCALE, 
            IntStream.rangeClosed(110, 113).toArray(), // [110,113] -bloodred marble
            IntStream.rangeClosed(114, 119).toArray()  // [114,119] +bloodred marble
        );
        addPolyRangeMapping("Bloodred Marble Quality", BETTA_MARBLE_QUALITY_SCALE, 120, 123, 5); // [120-123] Bloodred marble quality scale
        addPolyScaleMapping("Body Bloodred Area", BETTA_HIGH_LOW_LEVEL_SCALE, 
            IntStream.rangeClosed(162, 163).toArray(), // [110,113] +body bloodred
            IntStream.rangeClosed(164, 165).toArray()  // [114,119] -body bloodred
        );
        addPolyRangeMapping("Bloodred Fin Area", BETTA_HIGH_LOW_LEVEL_SCALE, 158, 161); // [158-161] Fin Bloodred Level
        addPolyScaleMapping("Bloodred Rufousing", BETTA_RUFOUSING_SCALE,
            IntStream.rangeClosed(194, 203).toArray(), // [194,203] -bloodred rufousing
            IntStream.rangeClosed(204, 213).toArray()  // [204,213] +bloodred rufousing
        );
        // Blue Marble Layer
        addPolyScaleMapping("Iri Marble Size", BETTA_MARBLE_SIZE_SCALE, 
            IntStream.rangeClosed(124, 127).toArray(), // [124,127] -iri marble
            IntStream.rangeClosed(128, 133).toArray()  // [128,133] +iri marble
        );
        addPolyRangeMapping("Iri Marble Quality", BETTA_MARBLE_QUALITY_SCALE, 134, 137, 5); // [134-137] Iri marble quality scale
        // Opaque Marble Layer
        addPolyScaleMapping("Opaque Marble Size", BETTA_MARBLE_SIZE_SCALE, 
            IntStream.rangeClosed(138, 141).toArray(), // [138,141] -opaque marble
            IntStream.rangeClosed(142, 147).toArray()  // [142,147] +opaque marble
        );
        addPolyRangeMapping("Opaque Marble Quality", BETTA_MARBLE_QUALITY_SCALE, 148, 151, 5); // [148-151] Opaque marble quality scale


    }
}
// spotless:on
