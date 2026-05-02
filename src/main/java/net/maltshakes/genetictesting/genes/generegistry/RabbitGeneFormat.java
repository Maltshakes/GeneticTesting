package net.maltshakes.genetictesting.genes.generegistry;

import java.util.List;
import net.maltshakes.genetictesting.genes.datamodel.GeneDefinition.GeneType;
import net.maltshakes.genetictesting.genes.format.GeneFormatting;

public class RabbitGeneFormat extends GeneFormatting {

  private static final List<String> RABBIT_EXTENSION_GENES =
      List.of(
          "0",
          "S", // Steel
          "E", // Extension/Wildtype
          "j", // Brindle/Japanese
          "e" // Non Extension
          );

  private static final List<String> RABBIT_AGOUTI_GENES =
      List.of(
          "0",
          "A", // Agouti/Wildtype
          "t", // Tan
          "a" // Self
          );

  private static final List<String> RABBIT_COLOUR_GENES =
      List.of(
          "0",
          "C", // Full Color/Wildtype
          "chd", // Dark Chinchilla
          "chl", // Light Chincilla
          "h", // Himalayan
          "c" // Albino
          );

  // Maps the genes of a rabbit
  public RabbitGeneFormat() {
    setBookColour(0x98D840); // Lime
    addCategory("Genetic tests (color)");
    addPairMapping(
        "Extension", RABBIT_EXTENSION_GENES, GeneType.POLYMORPHIC, 4); // [8,9] - Extension
    addPairMapping("Agouti", RABBIT_AGOUTI_GENES, GeneType.POLYMORPHIC, 0); // [0,1] - Agouti
    addPairMapping(
        "Color", RABBIT_COLOUR_GENES, GeneType.POLYMORPHIC, 2); // [4,5] - Color completion
    addPairMapping("Chocolate", "b", GeneType.BINARY, 1); // [2,3] - Brown/Chocolate
    addPairMapping("Dilute", "d", GeneType.BINARY, 3); // [6,7] - Dilute
    addPairMapping("Lutino", "p", GeneType.BINARY, 8); // [16,17] - Lutino
    addPairMapping(
        "Spotted", "En", GeneType.BINARY, 5); // [10,11] - English Spotting/Broken/Charlie
    addPairMapping("Dutch", "du", GeneType.BINARY, 6); // [12,13] - Dutch
    addPairMapping("Vienna", "v", GeneType.BINARY, 7); // [14,15] - Vienna
  }
}
