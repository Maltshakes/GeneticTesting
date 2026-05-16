package net.maltshakes.genetictesting.genes.generegistry;

import java.util.List;
import net.maltshakes.genetictesting.genes.datamodel.GeneDefinition.GeneType;
import net.maltshakes.genetictesting.genes.format.GeneFormatting;

// spotless:off
public class ChickenGeneFormat extends GeneFormatting {

    private static final List<String> CHICKEN_GOLD_GENES = List.of(
        "0",
        "g", // gold
        "S" // silver
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

    // Maps the genes of a chicken
    public ChickenGeneFormat() {
        setBookColour(0xF38B35); // Orange
        addCategory("Genetic tests (color)");
        addComment("Sex-Linked Genes");
        addSexLinkedPairMapping("Gold", CHICKEN_GOLD_GENES, GeneType.POLYMORPHIC, 0); // [0, 1] // Gold
        addSexLinkedPairMapping("Chocolate", "c", GeneType.BINARY, 1); // [2, 3] // Gold
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
        addPairMapping("Dom. white", CHICKEN_DOMWHITE_GENES, GeneType.POLYMORPHIC, 19); // [38,39] - Dominant white
        addPairMapping("Rec. white", CHICKEN_RECWHITE_GENES, GeneType.POLYMORPHIC, 10); // [20,21] - Recessive white
        addPairMapping("Autosomal Red", "Ar", GeneType.BINARY_INVERTED, 85); // [170,171] - Autosomal Red
        addPairMapping("Mottled", CHICKEN_MOTTLED_GENES, GeneType.POLYMORPHIC, 11); // [22,23] - Mottled
        addPairMapping("Fibromelanin", "Fm", GeneType.BINARY_INVERTED, 21); // [42,43] - Fibromelanin
        // Shank colors can also be affected by other genes like extension and columbian
        addPairMapping("Yellow Shanks", CHICKEN_YELSHANK_GENES, GeneType.POLYMORPHIC, 22); // [44,45] - Yellow Shanks
        addPairMapping("Rec. Black Shanks", "bl", GeneType.BINARY, 83); // [166,167] - Recessive Black Shanks
        addPairMapping("Pearl Eye", "prl", GeneType.BINARY, 148); // [296,297] - Pearl Eye
    }
}
// spotless:on
