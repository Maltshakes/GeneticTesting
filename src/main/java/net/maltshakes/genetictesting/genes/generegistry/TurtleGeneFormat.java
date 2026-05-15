package net.maltshakes.genetictesting.genes.generegistry;

import java.util.List;

import net.maltshakes.genetictesting.genes.datamodel.GeneDefinition.GeneType;
import net.maltshakes.genetictesting.genes.format.GeneFormatting;

// spotless:off
public class TurtleGeneFormat extends GeneFormatting{
    
    private static final List<String> TURTLE_PATTERN_GENES = List.of(
        "0",
        "+", // Wildtype
        "So", // Solid
        "Fl", // Flame
        "pl" // Patternless
    );

    private static final List<String> TURTLE_SOLID_MOD_GENES = List.of(
        "0",
        "+", // Wildtype
        "S", // Scale
        "C" // Clown
    );

    private static final List<String> TURTLE_DARKNESS_SCALE = List.of(
        "Min", // 16
        "Near Min", // 17
        "Ultra Low", // 18
        "Super Low", // 19
        "Very Low", // 20
        "Low", // 21
        "Med-Low", // 22
        "Medium", "Medium", "Medium",  // 23 25
        "Med-High", // 26
        "High", // 27
        "Very High", // 28
        "Super High", // 29
        "Ultra High", // 30
        "Near Max", // 31
        "Max" // 32
    );

    // Maps the genes of a turtle
    public TurtleGeneFormat() {
        setBookColour(0x61951E); // Green
        addCategory("Genetic tests (color)");
        addPairMapping("Albino", "a", GeneType.BINARY, 0); // [0,1] - Albino
        addPairMapping("Axanthic", "ax", GeneType.BINARY, 1); // [2,3] - Axanthic
        addPairMapping("Melanized", "m", GeneType.BINARY, 2); // [4,5] - Melanized
        addPairMapping("Melanin Pigment", "M", GeneType.BINARY, 34); // [68,69] - melanin pigment 
        addPairMapping("Green Pigment", "O", GeneType.BINARY, 33); // [66,67] - green pigment 
        addPairMapping("Lavender", "lav", GeneType.BINARY, 35); // [70,71] - lavender dilute
        addPolyRangeMapping("Darkness", TURTLE_DARKNESS_SCALE, 50, 65); // [50-65] - Hue darkener
        // [34-49] & [74-77] - golden 
        addPairMapping("Tortishell", "To", GeneType.BINARY,5 ); // [10,11] - Tortishell
        addPairMapping("Pattern", TURTLE_PATTERN_GENES, GeneType.POLYMORPHIC, 15); // [30,31] - Pattern (wildtype, solid (all pattern), flame, patternless)
        addPairMapping("Solid Modifier", TURTLE_SOLID_MOD_GENES, GeneType.POLYMORPHIC, 16); // [32,33] - Solid Modifier (wildtype, scale, clown)
        addPairMapping("Piebald", "pi", GeneType.BINARY, 3); // [6,7] - Piebald
        addPairMapping("Speckled", "sp", GeneType.BINARY, 4); // [8,9] - speckle to spot piebald modifier
    }
}
// spotless:on
