package net.maltshakes.genetictesting.genes.generegistry;

import net.maltshakes.genetictesting.genes.datamodel.GeneDefinition.GeneType;
import net.maltshakes.genetictesting.genes.format.GeneFormatting;

// spotless:off
public class TurtleGeneFormat extends GeneFormatting{
    
    // Maps the genes of a turtle
    public TurtleGeneFormat() {
        setBookColour(0x61951E); // Green
        addCategory("Genetic tests (color)");
        addPairMapping("Albino", "a", GeneType.BINARY, 0); // [0,1] - Albino
        addPairMapping("Axanthic", "ax", GeneType.BINARY, 1); // [2,3] - Axanthic
        addPairMapping("Melanized", "m", GeneType.BINARY, 2); // [4,5] - Melanized
        addPairMapping("Piebald", "pi", GeneType.BINARY, 3); // [6,7] - Piebald
        addPairMapping("Speckled", "sp", GeneType.BINARY, 4); // [8,9] - speckle to spot piebald modifier
        // addPairMapping("Tortishell", "pa", GeneType.BINARY,5 ); // [10,11] - Tortishell
        // Add new genes?
    }
}
// spotless:on
