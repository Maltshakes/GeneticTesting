package net.maltshakes.genetictesting.genes.generegistry;

import java.util.List;
import net.maltshakes.genetictesting.genes.datamodel.GeneDefinition.GeneType;
import net.maltshakes.genetictesting.genes.format.GeneFormatting;

// spotless:off
public class AxolotlGeneFormat extends GeneFormatting {

    private static final List<String> AXOLOTL_EPGFP_GENES = List.of(
        "0",
        "+", // Wildtype
        "EP", // Extra Pigment
        "GFP" // Green/Glow Fluorescent Protein
    );

    private static final List<String> AXOLOTL_SPLASH_GENES = List.of(
        "0",
        "+", // Wildtype
        "Wh", // Whitebelly
        "Pi" // Piebald
    );

    private static final List<String> AXOLOTL_SPLOTCH_STRENGTH_GENES = List.of(
        "0",
        "1", // weak
        "2", // weak
        "3", // medium weak
        "4", // medium weak
        "5", // medium
        "6", // medium
        "7", // medium strong
        "8", // medium strong
        "9", // strong
        "10" // strong
    );

    private static final List<String> AXOLOTL_SPLOTCH_TYPE_GENES = List.of(
        "0",
        "ns", // No Speckle
        "ns", // No Speckle
        "Hs", // Hard Speckle
        "Hs", // Hard Speckle
        "SP", // Soft Speckle
        "SP" // Soft Speckle
    );

    private static final List<String> AXOLOTL_EYE_TYPE_GENES = List.of(
        "0",
        "+", // Wildtype
        "D", // Dark Eyes
        "P", // Pigmented Eyes
        "L", // Light Eyes
        "P", // Pastel Eyes
        "GFP" // Green/Glow Fluorescent Protein
    );

    private static final List<String> AXOLOTL_GILL_COLOR_GENES = List.of(
        "0",
        "+", // Wildtype (Natural Pink)
        "Whi", // White
        "LiGr", // Light Gray
        "Gr", // Gray
        "Bla", // Black
        "Br", // Brown
        "Pi", // Pink
        "Red", // Red
        "Ora", // Orange
        "Yel", // Yellow
        "Lim", // Lime
        "Gre", // Green
        "Cya", // Cyan
        "LiBlu", // Light Blue
        "Blu", // Blue
        "Pur", // Purple
        "Mag" // Magenta
    );

    // Maps the genes of an axolotl
    public AxolotlGeneFormat() {
        setBookColour(0x309E9D); // Cyan
        addCategory("Genetic tests (color)");
        addPairMapping("Albino", "a", GeneType.BINARY, 0); // [0,1] - Albino
        addPairMapping("Axanthic", "ax", GeneType.BINARY,1); // [2,3] - Axanthic
        addPairMapping("Melano", "m", GeneType.BINARY, 2); // [4,5] - Melano
        addPairMapping("Copper", "c", GeneType.BINARY, 3); // [6,7] - Copper
        addPairMapping("Leucistic", "d", GeneType.BINARY, 4); // [8,9] - Leucistic
        addPairMapping("Splash", AXOLOTL_SPLASH_GENES, GeneType.POLYMORPHIC, 6); // [12,13] - Whitebelly/Piebald
        // Strength of white markings. Sum ((14 + 15)-2)*0.3 to get strength. Remove decimal to get which of 5 textures from 0 - 4 shows
        addPairMapping("Splash Strength", AXOLOTL_SPLOTCH_STRENGTH_GENES, GeneType.POLYMORPHIC, 7); // [14,15] - Strength of splash gene expression, uses above formula
        addPairMapping("Speck Factor", AXOLOTL_SPLOTCH_TYPE_GENES, GeneType.POLYMORPHIC, 8); // [16,17] - Splotch cleanliness, higher number is dominant, 1-2 is clean edges, 3-4 is hard speckle, 5-6 is soft speckle. As factor gets higher the white gets messier
        addPairMapping("Cheekspots", "CS", GeneType.BINARY, 22); // [44,45] - Cheekspots
        addPageBreak();
        addCategory("Extra pigments");
        addComment("Need EP or GFP to express color hues");
        addLineBreak();
        addPairMapping("Body EP", AXOLOTL_EPGFP_GENES, GeneType.POLYMORPHIC, 5); // [10,11] - Body EP/GFP
        addPairMapping("Body Color", "", GeneType.COLOUR, 12); // [24,25] - RGB value of body
        addPairMapping("Eye Type", AXOLOTL_EYE_TYPE_GENES, GeneType.POLYMORPHIC, 10); // [20,21] - Eye color modifiers
        addPairMapping("Eye Color", "", GeneType.COLOUR, 11); // [22,23] - RGB value of eyes
        addPairMapping("Gill EP", AXOLOTL_EPGFP_GENES, GeneType.POLYMORPHIC, 19); // [38,39] - Gill EP/GFP
        addPairMapping("Gill Color", AXOLOTL_GILL_COLOR_GENES, GeneType.POLYMORPHIC, 20); // [40,41] - Gill colors
    }
}
// spotless:on
