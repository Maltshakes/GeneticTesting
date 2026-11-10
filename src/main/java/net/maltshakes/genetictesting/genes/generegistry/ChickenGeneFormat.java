package net.maltshakes.genetictesting.genes.generegistry;

import java.util.List;

import net.maltshakes.genetictesting.genes.datamodel.GeneDefinition.GeneType;
import net.maltshakes.genetictesting.genes.format.GeneFormatting;

// spotless:off
public class ChickenGeneFormat extends GeneFormatting {

    private static final List<String> CHICKEN_GOLD_GENES = List.of(
        "0",
        "g", // Gold
        "S" // Silver
    );

    private static final List<String> CHICKEN_DOMWHITE_GENES = List.of(
        "0",
        "I", // Dom White
        "+", // Wildtype/Red Jungle Fowl (aka "i")
        "D", // Dun
        "S" // Smokey
    );
    
    private static final List<String> CHICKEN_RECWHITE_GENES = List.of(
        "0",
        "+", // Wildtype
        "c", // Recessive White
        "a" // Albino
    );

    private static final List<String> CHICKEN_MOTTLED_GENES = List.of(
        "0",
        "+", // Wildtype
        "mo", // Mottled
        "cr" // White crested
    );

    private static final List<String> CHICKEN_EXTENSION_GENES = List.of(
        "0",
        "R", // Birchen
        "+", // Duckwing/Wildtype
        "wh", // Wheaten
        "b", // Partridge/Brown
        "E" // Extended black
    );

    private static final List<String> CHICKEN_DILUTE_GENES = List.of(
        "0",
        "Di", // Dilute
        "Cr", // Cream
        "+" // Wildtype
    );

    private static final List<String> CHICKEN_YELSHANK_GENES = List.of(
        "0",
        "w", // White
        "y", // Yellow
        "sy" // Superyellow
    );

    private static final List<String> CHICKEN_EAR_SIZE_GENES = List.of(
        "0",
        "1", 
        "2",
        "3",
        "4",
        "5",
        "6" 
    );

    private static final List<String> CHICKEN_ROSE_COMB_GENES = List.of(
        "0",
        "R1", // Rose1
        "R2", // Rose2
        "+" // Wildtype
    );

    private static final List<String> CHICKEN_DUPLEX_COMB_GENES = List.of(
        "0",
        "+", // Wildtype
        "Dv", // Duplex V
        "Dc" // Duplex C
    );

    private static final List<String> CHICKEN_CREST_GENES = List.of(
        "0",
        "Nc", // Normal Crest
        "Fc", // Forward Crest
        "+" // Wildtype
    );

    private static final List<String> CHICKEN_FOOT_FEATHER_GENES = List.of(
        "0",
        "Sfe", // Small Foot Feather
        "Bfe", // Big Foot Feather
        "+" // Wildtype
    );

    private static final List<String> CHICKEN_EAR_WHITE_GENES = List.of(
        "0",
        "re", // Reducer
        "+", // Wildtype
        "s", // Adds size
        "w", // Adds white
        "sw" // Adds white and size

    );

    // Maps the genes of a chicken
    public ChickenGeneFormat() {
        setBookColour(0xF38B35); // Orange
        addCategory("Genetic tests (color)");
        addComment("Sex-Linked Genes");
        addSexLinkedPairMapping("Gold", CHICKEN_GOLD_GENES, GeneType.POLYMORPHIC, 0); // [0, 1] // Gold
        addSexLinkedPairMapping("Chocolate", "c", GeneType.BINARY, 1); // [2, 3] // Chocolate
        addSexLinkedPairMapping("Barred", "b", GeneType.BINARY, 3); // [6, 7] // Barred
        addSexLinkedPairMapping("Fibro Suppressor", "Id", GeneType.BINARY, 4); // [8, 9] // Fibromelanin Suppressor
        // Males work same as AGenes. Females carry have two alleles for each locus, but only the first allele of each pair is used for females, second one is ignored.
        // Fuck Omni Genders
        addLineBreak();
        addComment("Autosomal Genes");
        addPairMapping("Extension", CHICKEN_EXTENSION_GENES, GeneType.POLYMORPHIC, 12); // [24,25] - D Locus / Extension
        addPairMapping("Pattern", "Pg", GeneType.BINARY_INVERTED, 13); // [26,27] - Pattern
        addPairMapping("Colombian", "Co", GeneType.BINARY_INVERTED, 14); // [28,29] - Colombian
        addPairMapping("Melanized", "Ml", GeneType.BINARY_INVERTED, 15); // [30,31] - Melanized
        addPairMapping("Darkbrown", "Db", GeneType.BINARY_INVERTED, 49); // [98,99] - Darkbrown
        addPairMapping("Charcoal", "cha", GeneType.BINARY, 50); // [100,101] - Charcoal
        addPairMapping("Mahogany", "Mh", GeneType.BINARY_INVERTED, 17); // [34,35] - Mahogany
        addPairMapping("Dilute", CHICKEN_DILUTE_GENES, GeneType.POLYMORPHIC, 16); // [32,33] - Dilute
        addPairMapping("Lavender", "lav", GeneType.BINARY, 18); // [36,37] - Lavender
        addPairMapping("Splash", "Bl", GeneType.BINARY, 20); // [40,41] - Splash/Blue
        addPairMapping("Cream", "ig", GeneType.BINARY, 142); // [284,285] - Cream/Inhibitor of Gold
        addPairMapping("Hen Feathering", "HF", GeneType.BINARY, 98); // [196,197] - Hen feathering
        addPairMapping("Dom. white", CHICKEN_DOMWHITE_GENES, GeneType.POLYMORPHIC, 19); // [38,39] - Dominant white
        addPairMapping("Rec. white", CHICKEN_RECWHITE_GENES, GeneType.POLYMORPHIC, 10); // [20,21] - Recessive white
        addPairMapping("Autosomal Red", "Ar", GeneType.BINARY_INVERTED, 85); // [170,171] - Autosomal Red
        addPairMapping("Mottled", CHICKEN_MOTTLED_GENES, GeneType.POLYMORPHIC, 11); // [22,23] - Mottled
        addPairMapping("Fibromelanin", "Fm", GeneType.BINARY_INVERTED, 21); // [42,43] - Fibromelanin
        // Shank colors can also be affected by other genes like extension and columbian
        addPairMapping("Yellow Shanks", CHICKEN_YELSHANK_GENES, GeneType.POLYMORPHIC, 22); // [44,45] - Yellow Shanks
        addPairMapping("Rec. Black Shanks", "bl", GeneType.BINARY, 83); // [166,167] - Recessive Black Shanks
        addPairMapping("Pearl Eye", "prl", GeneType.BINARY, 148); // [296,297] - Pearl Eye

        addPageBreak();
        addCategory("Genetic tests (build)");
        addComment("Sex-Linked Genes");
        addSexLinkedPairMapping("Ear Size", CHICKEN_EAR_SIZE_GENES, GeneType.POLYMORPHIC, 2); // [4,5] - Ear size
        addSexLinkedPairMapping("Ear Inhib.", "ei", GeneType.BINARY, 9); // [18,19] - Large ear inhibitor
        addSexLinkedPairMapping("White Face", CHICKEN_EAR_SIZE_GENES, GeneType.POLYMORPHIC, 6); // [12,13] - White face
        addSexLinkedPairMapping("Dwarf1", "dw1", GeneType.BINARY, 7); // [14,15] - Dwarf
        addSexLinkedPairMapping("Dwarf2", "dw2", GeneType.BINARY, 8); // [16,17] - Dwarf 2
        addSexLinkedPairMapping("Tailless", "T", GeneType.BINARY, 10); // [20,21] - Tailless gene

        addLineBreak();
        addComment("Autosomal Genes");
        // [104,105] - Frizzle (doesn't spawn right now)
        addPairMapping("Silkie", "si", GeneType.BINARY, 53); // [106,107] - Silkie
        addPairMapping("Scaleless", "sc", GeneType.BINARY, 54); // [108,109] - Scaleless
        // [228-235] - Feather fluff reducer
        // [236-247] - Feather fluff adder
        
        addPairMapping("Combless", "cl", GeneType.BINARY, 147); // [294,295] - Combless
        addPairMapping("Rose Comb", CHICKEN_ROSE_COMB_GENES, GeneType.POLYMORPHIC, 23); // [46,47] - Rose comb
        addPairMapping("Pea Comb", "P", GeneType.BINARY_INVERTED, 24); // [48,49] - Pea comb
        addPairMapping("Duplex Comb", CHICKEN_DUPLEX_COMB_GENES, GeneType.POLYMORPHIC, 25); // [50,51] - Duplex comb or v comb 
        addPairMapping("Small Comb", "Sc", GeneType.BINARY_INVERTED, 40); // [80,81] - Small comb
        addPairMapping("Large Comb", "Lc", GeneType.BINARY_INVERTED, 41); // [82,83] - Large comb
        // new comb floppiness gene

        addPairMapping("Wattle Reduc.", "wr", GeneType.BINARY_INVERTED, 42); // [84,85] - Wattle size reducer
        addPairMapping("Crest", CHICKEN_CREST_GENES, GeneType.POLYMORPHIC, 27); // [54,55] - Crest 
        // [222-227] - Bald face genes
        addPairMapping("Beard", "MB", GeneType.BINARY_INVERTED, 28); // [56,57] - Beard 

        // [152-157, 160-163] - Ear size
        addPairMapping("White Ear Size", CHICKEN_EAR_WHITE_GENES, GeneType.POLYMORPHIC, 79); // [158,159] - Ear size and whitener
        // [164,165] - Ear redness
        addPairMapping("Ear Tufts", "Et", GeneType.BINARY, 75); // [150,151] - Ear tufts (homo lethal)

        addPairMapping("Naked Neck", "Na", GeneType.BINARY_INVERTED, 26); // [52,53] - Naked neck
        // [286-293] - Neck poof adder
        // [10-19] - Neck angle genes 

        // [74,75] - Body size genes
        // [76,77] - body? size reducer
        addPairMapping("Body Size Adder", "Bla", GeneType.BINARY, 39); // [78,79] - body? size adder
        // [146,147] - Body sizer adder
        // [148,149] - Body size reducer
        // [184,185] - Vault, only affects health
        // [186-195] - Body angle genes 1-5
        // [248-267] - More body angle genes 6-10

        // [86,87] - Wing placement
        // [90,91] - Wing length
        // [92,93] - Wing thickness
        // [88,89] - Wing tilt
        // [94-97] - Wing angle multiplier
        
        // [72,73] - Rumpless (homo lethal)
        // [198,199] - Tail Feather growth
        addPairMapping("Extra TF", "Tf", GeneType.BINARY, 139); // [278,279] - Extra tail feathers
        // [280,281] - Non molting gene
        // [282,283] - Short tail feathers
        // new tail pinch gene
        // [258-267] - Tail angle adder
        // [268-277] - Tail angle reducer

        addPairMapping("Foot Feather", CHICKEN_FOOT_FEATHER_GENES, GeneType.POLYMORPHIC, 29); // [58,59] - Foot feathering 
        addPairMapping("FF Enhancer", "fe", GeneType.BINARY_INVERTED, 30); // [60,61] - Foot feather enhancer
        addPairMapping("Vulture Hocks", "v", GeneType.BINARY, 51); // [102,103] - Vulture hocks
        addPairMapping("Long Legs", "LL", GeneType.BINARY, 84); // [168,169] - Long legs
        addPairMapping("Creeper", "Cp", GeneType.BINARY, 35); // [70,71] - Creeper (homo lethal)

        addPageBreak();
        addCategory("Genetic tests (production)");
        addComment("Coming in future update");
    }
}
// spotless:on
