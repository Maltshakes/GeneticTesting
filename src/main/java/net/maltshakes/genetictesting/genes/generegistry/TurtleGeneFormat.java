package net.maltshakes.genetictesting.genes.generegistry;

// import java.util.List;

import net.maltshakes.genetictesting.genes.datamodel.GeneDefinition.GeneType;
import net.maltshakes.genetictesting.genes.format.GeneFormatting;

// spotless:off
public class TurtleGeneFormat extends GeneFormatting{
    
    // private static final List<String> TURTLE_DARKNESS_SCALE = List.of(
    //     "Min", // 0
    //     "Near Min", "Near Min", // 1 2
    //     "Ultra Low", "Ultra Low", "Ultra Low",// 3 5
    //     "Super Low", "Super Low", "Super Low", // 6 8
    //     "Very Low", "Very Low", "Very Low", // 9 11
    //     "Low", "Low", "Low", // 12 14
    //     "Medium", "Medium", "Medium",  // 15 17
    //     "High", "High", "High", // 18 20
    //     "Very High", "Very High", "Very High", // 21 23
    //     "Super High", "Super High", "Super High", // 24 26
    //     "Ultra High", "Ultra High", "Ultra High", // 27 29
    //     "Near Max", "Near Max", // 30 31
    //     "Max" // 32
    // );

    // Maps the genes of a turtle
    public TurtleGeneFormat() {
        setBookColour(0x61951E); // Green
        addCategory("Genetic tests (color)");
        addPairMapping("Albino", "a", GeneType.BINARY, 0); // [0,1] - Albino
        addPairMapping("Axanthic", "ax", GeneType.BINARY, 1); // [2,3] - Axanthic
        addPairMapping("Melanized", "m", GeneType.BINARY, 2); // [4,5] - Melanized
        // [66,67] - green pigment
        // [68,69] - melanin pigment
        // [70,71] - lavender dilute
        // addPolyRangeMapping("Darkness", TURTLE_DARKNESS_SCALE, 25, 32); // [50-65] - Hue darkener
        // [34-49] & [74-77] - golden 
        // [72,73] - countershaded (non-countershaded, crisp-countershaded, countershaded, scale-countershaded)
        // addPairMapping("Tortishell", "to", GeneType.BINARY,5 ); // [10,11] - Tortishell
        // [30,31] - Pattern (wildtype, solid (all pattern), flame, patternless)
        // [32,33] - Solid Modifier (wildtype, scale, clown)
        addPairMapping("Piebald", "pi", GeneType.BINARY, 3); // [6,7] - Piebald
        addPairMapping("Speckled", "sp", GeneType.BINARY, 4); // [8,9] - speckle to spot piebald modifier
    }
}
// spotless:on
